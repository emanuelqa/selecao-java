package com.emanuel.indra.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Arquivo {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String regiao;
	private String estado;
	private String municipio;
	private String revenda;
	private String cnpj;
	private String produto;
	private LocalDate dataColeta;
	private BigDecimal valorVenda;
	private BigDecimal valorCompra;
	private String unidadeMedida;
	private String bandeira;

}
