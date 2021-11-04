package com.bycoders.parsertransations.model.dto;



import java.math.BigDecimal;
import java.util.List;


import com.bycoders.parsertransations.model.Transacao;
import com.bycoders.parsertransations.model.enums.Natureza;

import lombok.Data;

@Data
public class TransacaoSaldoDto {

	private List<Transacao> transacoesLoja;
	
	private BigDecimal saldo;
	
	
	public BigDecimal getSaldo() {
		
		BigDecimal val = BigDecimal.ZERO;
		
		for (int i = 0; i < transacoesLoja.size(); i++) {
			Natureza nat = transacoesLoja.get(i).getTipo().getNatureza();
			if (nat.name().equals("ENTRADA")) {
				val = val.add(transacoesLoja.get(i).getValor());
			} else {
				val = val.subtract(transacoesLoja.get(i).getValor());
			}
		}
		
		return val;

	};
	
}
