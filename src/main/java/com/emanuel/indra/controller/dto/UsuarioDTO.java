package com.emanuel.indra.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.emanuel.indra.model.Endereco;
import com.emanuel.indra.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	private String nome;
	private LocalDate dataNascimento;
	private String telefone;
	private List<Endereco> enderecos;
	
	public UsuarioDTO(Usuario usuario) {
		this.nome = usuario.getNome();
		this.dataNascimento = usuario.getDataNascimento();
		this.telefone = usuario.getTelefone();
		this.enderecos = usuario.getEnderecos();
	}

	public static List<UsuarioDTO> converter(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}

}
