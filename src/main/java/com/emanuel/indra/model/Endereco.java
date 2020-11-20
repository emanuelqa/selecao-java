package com.emanuel.indra.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Endereco {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rua;
	private String bairro;
	private String numero;
	
	@JsonIgnore
	@ManyToOne
	private Usuario usuario;

	public Endereco() {}

	public Endereco(String rua, String bairro, String numero, Usuario usuario) {
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.usuario = usuario;
	}
}
