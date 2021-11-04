package com.bycoders.parsertransations.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bycoders.parsertransations.constante.ParseConstante;
import com.bycoders.parsertransations.model.PageInfo;
import com.bycoders.parsertransations.model.Tipo;
import com.bycoders.parsertransations.model.Transacao;
import com.bycoders.parsertransations.model.dto.TransacaoDto;
import com.bycoders.parsertransations.model.dto.TransacaoSaldoDto;
import com.bycoders.parsertransations.repository.TransacaoRepository;

@Service
public class ParserTransationServiceImpl implements ParserTransationService {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private final DateTimeFormatter horaformatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Override
	public void saveTransations(MultipartFile ficheiro) {
		try {
			List<Transacao> transacaoList = readFile(ficheiro.getInputStream());
			transacaoRepository.saveAll(transacaoList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TransacaoDto> findAll(Pageable pageable) {
		Iterable<Transacao> itreable = transacaoRepository.findAll(pageable);

		List<TransacaoDto> tranzacoes = StreamSupport.stream(itreable.spliterator(), false).map(tranzacao -> {
			TransacaoDto dto = new TransacaoDto();
			BeanUtils.copyProperties(tranzacao, dto);
			return dto;
		}).collect(Collectors.toList());

		return tranzacoes;
	}

	@Override
	public TransacaoSaldoDto findByNomeLoja(String nome_loja, Pageable pageable) {
		List<Transacao> list = transacaoRepository.findByNomeLojaContainingIgnoreCase(nome_loja);

		TransacaoSaldoDto dtoSaldo = new TransacaoSaldoDto();
		dtoSaldo.setTransacoesLoja(list);

		return dtoSaldo;
	}


	@Override
	public Transacao parseFile(String dados) {

		Transacao transacao = new Transacao();

		transacao.setTipo(parseTipo(dados));
		transacao.setValor(parseValor(dados));
		transacao.setCpf(parseCPF(dados));
		transacao.setNomeLoja(parseNomeLoja(dados));
		transacao.setDonoLoja(parseDonoLoja(dados));
		transacao.setCartao(parseCartao(dados));

		String data = parseData(dados);
		LocalDate localDate = convertStringToDate(data);
		transacao.setData(localDate);

		String hora = parseHora(dados);
		LocalTime localTime = convertStringToHora(hora);
		transacao.setHora(localTime);

		return transacao;
	}

	@Override
	public List<Transacao> readFile(InputStream inputStream) {
		List<Transacao> transacaoList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				transacaoList.add(parseFile(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return transacaoList;
	}

	@Override
	public Tipo parseTipo(String dados) {
		Tipo tipo = new Tipo();
		String t = String.valueOf(dados.charAt(ParseConstante.getTipoIndexInicio() - 1));
		tipo.setId(Integer.parseInt(t));
		return tipo;
	}

	@Override
	public String parseData(String dados) {
		return dados.substring(ParseConstante.getDataIndexInicio() - 1, ParseConstante.getDataIndexFim());
	}

	@Override
	public BigDecimal parseValor(String dados) {
		return new BigDecimal(
				dados.substring(ParseConstante.getValorIndexInicio() - 1, ParseConstante.getValorIndexFim()))
						.divide(BigDecimal.valueOf(100)).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public String parseCPF(String dados) {
		return dados.substring(ParseConstante.getCpfIndexInicio() - 1, ParseConstante.getCpfIndexFim());
	}

	@Override
	public String parseCartao(String dados) {
		return dados.substring(ParseConstante.getCartaoIndexInicio() - 1, ParseConstante.getCartaoIndexFim());
	}

	@Override
	public String parseHora(String dados) {
		return dados.substring(ParseConstante.getHoraIndexInicio() - 1, ParseConstante.getHoraIndexFim());
	}

	@Override
	public String parseDonoLoja(String dados) {
		return dados.substring(ParseConstante.getDonoLojaIndexInicio() - 1, ParseConstante.getDonoLojaIndexFim());
	}

	@Override
	public String parseNomeLoja(String dados) {
		return dados.substring(ParseConstante.getNomeLojaIndexInicio() - 1, ParseConstante.getNomeLojaIndexFim());
	}

	@Override
	public LocalDate convertStringToDate(String data) {
		String ano = data.substring(0, 4);
		String mes = data.substring(4, 6);
		String dia = data.substring(6, 8);
		String dataFinal = ano + "-" + mes + "-" + dia;

		return LocalDate.parse(dataFinal, dateFormatter);
	}

	@Override
	public LocalTime convertStringToHora(String hora) {
		String hora_ = hora.substring(0, 2);
		String minuto = hora.substring(2, 4);
		String segundo = hora.substring(4, 6);
		String horaFinal = hora_ + ":" + minuto + ":" + segundo;

		return LocalTime.parse(horaFinal, horaformatter);
	}



}
