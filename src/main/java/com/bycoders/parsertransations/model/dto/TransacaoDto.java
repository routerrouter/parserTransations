package com.bycoders.parsertransations.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import com.bycoders.parsertransations.model.Tipo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class TransacaoDto extends RepresentationModel<TransacaoDto> { 

	@EqualsAndHashCode.Include
    private long id;
	
	@NotBlank
	@Schema(name = "tipo" , description = "Tipo da Transação")
    private Tipo tipo;
	
	@NotBlank
	@Schema(name = "data" , description = "Data da ocorrência")
    private LocalDate data;
	
	@NotBlank
	@Schema(name = "hora" , description = "Hora da ocorrência atendendo ao fuso de UTC-3")
    private LocalTime hora;
	
	@NotBlank
	@Schema(name = "valor" , description = "Valor da movimentação.")
    private BigDecimal valor;
	
	@NotBlank
	@Schema(name = "cpf" , description = "CPF do beneficiário")
    private String cpf;
	
	@NotBlank
	@Schema(name = "donoLoja" , description = "Nome do representante da loja")
    private String donoLoja;
	
	@NotBlank
	@Schema(name = "nomeLoja" , description = "Nome da loja")
    private String nomeLoja;
	
	
	@Schema(name="cartao", description = "Cartão utilizado na transação")
    private String cartao;
	
	private BigDecimal saldo;
	
	
	
}
