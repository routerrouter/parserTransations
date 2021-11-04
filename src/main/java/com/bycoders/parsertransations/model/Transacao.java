package com.bycoders.parsertransations.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="TRANSACAO")
@Data
public class Transacao implements Serializable {

        private static final long serialVersionUID = 5249382473632350686L;

        @EqualsAndHashCode.Include
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @OneToOne 	
        private Tipo tipo;

        @Column(nullable = false)	
        private LocalDate data;

        @Column(nullable = false)
        private LocalTime hora;

        @Column(nullable = false)
        private BigDecimal valor;

        @Column(nullable = false, length = 11)
        private String cpf;

        @Column(nullable = false, length = 14)
        private String donoLoja;

        @Column(nullable = false, length = 19)
        private String nomeLoja;
        
        @Column(nullable = false, length = 12)
        private String cartao;

      
        
}
