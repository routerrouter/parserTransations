package com.bycoders.parsertransations.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.List;

import com.bycoders.parsertransations.model.Transacao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bycoders.parsertransations.exception.ApiErrorResponse;
import com.bycoders.parsertransations.model.PageInfo;
import com.bycoders.parsertransations.model.dto.TransacaoDto;
import com.bycoders.parsertransations.model.dto.TransacaoSaldoDto;
import com.bycoders.parsertransations.service.ParserTransationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transacao")
public class ParserTransationController {

	@Autowired
	private ParserTransationService parserTransationService;

	@Operation(summary = "Upload do Fiheiro CNAB para Transação", description = "Carregar dados do Ficheiro", method = "POST", tags = {
			"Carregamento-Parser -CNAB.txt" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Carregamento das transações feito com sucesso!", content = @Content(schema = @Schema(implementation = TransacaoDto.class), mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)),
			@ApiResponse(responseCode = "400", description = "Erro ao fazer upload dos dados de transação", content = @Content(schema = @Schema(implementation = TransacaoDto.class), mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)),
			@ApiResponse(responseCode = "415", description = "Erro ao carregar ficheiro vázio", content = @Content(schema = @Schema(implementation = TransacaoDto.class), mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)),
			@ApiResponse(responseCode = "500", description = "Exceção do Servidor", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) })
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody()
	public ResponseEntity<?> salvar(@RequestPart MultipartFile file) {
		parserTransationService.saveTransations(file);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Carregamento geral ds Transações", description = "Carregar todas as Transações", method = "GET", tags = {
			"Lista Geral" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listagem geral das Transações carregadas", content = @Content(schema = @Schema(implementation = TransacaoDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Erro ao listar Transações", content = @Content(schema = @Schema(implementation = TransacaoDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Exceção do Servidor", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	@GetMapping("")
	public ResponseEntity<Page<?>> findAll(
			@PageableDefault(page = 0, size = 5, sort = "cartao", direction = Sort.Direction.ASC) Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<TransacaoDto> list = parserTransationService.findAll(pageable);
		for (TransacaoDto dto : list) {
			Link link = linkTo(
					methodOn(ParserTransationController.class).importacaoPorLoja(dto.getNomeLoja(), pageable))
							.withSelfRel();
			dto.add(link);

		}

		Page<TransacaoDto> pages = new PageImpl<TransacaoDto>(list, PageRequest.of(currentPage, pageSize), list.size());
		return new ResponseEntity<>(pages, HttpStatus.OK);
	}

	@Operation(summary = "Lista das operações importadas por Lojas", description = "Carregar todas as Transações da Loja", method = "GET", tags = {
			"Lista por Loja" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listagem de Transações por Lojas", content = @Content(schema = @Schema(implementation = TransacaoDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Erro ao listar Transações da Loja", content = @Content(schema = @Schema(implementation = TransacaoDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Exceção do Servidor", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)) })

	@GetMapping(path = "/byLoja")
	public ResponseEntity<TransacaoSaldoDto> importacaoPorLoja(
			@RequestParam(required = false, defaultValue = "") String nomeLoja,
			@PageableDefault(page = 0, size = 5, sort = "cartao", direction = Sort.Direction.ASC) Pageable pageable) {

		return new ResponseEntity<>(parserTransationService.findByNomeLoja(nomeLoja, pageable), HttpStatus.OK);
	}



}
