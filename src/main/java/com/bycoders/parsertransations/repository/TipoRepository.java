package com.bycoders.parsertransations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bycoders.parsertransations.model.Tipo;

@Repository
public interface TipoRepository  extends JpaRepository<Tipo, Integer> {

}
