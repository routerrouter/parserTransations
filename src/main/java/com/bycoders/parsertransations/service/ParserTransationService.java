package com.bycoders.parsertransations.service;

import com.bycoders.parsertransations.model.PageInfo;
import com.bycoders.parsertransations.model.Tipo;
import com.bycoders.parsertransations.model.Transacao;
import com.bycoders.parsertransations.model.dto.TransacaoDto;
import com.bycoders.parsertransations.model.dto.TransacaoSaldoDto;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public interface ParserTransationService {
    
	public void saveTransations(MultipartFile ficheiro);
    public List<TransacaoDto> findAll(Pageable pageable);
    public TransacaoSaldoDto findByNomeLoja(String nome_loja,Pageable pageable);


    Transacao parseFile(String dados);
    List<Transacao> readFile(InputStream inputStream);
    Tipo parseTipo(String dados);
    String parseData(String dados);
    BigDecimal parseValor(String dados);
    String parseCPF(String dados);
    String parseCartao(String dados);
    String parseHora(String dados);
    String parseDonoLoja(String dados);
    String parseNomeLoja(String dados);
    LocalDate convertStringToDate(String data);
    LocalTime convertStringToHora(String hora);
}
