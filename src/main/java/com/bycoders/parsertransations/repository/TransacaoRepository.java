package com.bycoders.parsertransations.repository;

import com.bycoders.parsertransations.model.Transacao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	public List<Transacao>findByNomeLojaContainingIgnoreCase(String nome_loja);
}
