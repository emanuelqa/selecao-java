package com.emanuel.indra.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Usuario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private String telefone;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	public static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 

	public Usuario() {
	}

	public Usuario(String nome, String dataNascimento, String telefone, Endereco endereco) {
		this.nome = nome;
		this.dataNascimento = LocalDate.parse(dataNascimento, formato);
		this.telefone = telefone;
		this.enderecos.add(endereco);
	}
}
