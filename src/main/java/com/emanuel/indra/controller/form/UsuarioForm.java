package com.emanuel.indra.controller.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.emanuel.indra.model.Endereco;
import com.emanuel.indra.model.Usuario;

import lombok.Data;

@Data
public class UsuarioForm {

	@NotBlank(message = "Nome é obrigatorio")
	private String nome;
	
	@NotBlank(message = "Nome é obrigatorio")
	@Pattern(regexp = "^(((0[1-9]|[12][0-9]|30)[-/]?(0[13-9]|1[012])|31[-/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[-/]?02)[-/]?[0-9]{4}|29[-/]?02[-/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$"
			,message = "A data não pode ser vazia e ter o formato dd/mm/yyyy")
	private String dataNascimento;
	
	private String telefone;
	
	@NotBlank(message = "A rua é obrigatorio ")
	private String rua;
	
	@NotBlank(message = "O bairro é obrigatorio")
	private String bairro;
	
	@NotBlank(message = "O numero é obrigatorio")
	private String numero;

	public Usuario converter() {

		Endereco endereco = new Endereco();
		endereco.setRua(rua);
		endereco.setBairro(bairro);
		endereco.setNumero(numero);

		Usuario usuario = new Usuario(nome, dataNascimento, telefone, endereco);
		endereco.setUsuario(usuario);

		return usuario;
	}

	public Usuario converterAtualizar(Usuario usuario) {

		usuario.setNome(nome);
		usuario.setDataNascimento(LocalDate.parse(dataNascimento, Usuario.formato));
		usuario.setTelefone(telefone);
		
		List<Endereco> enderecosAtualizados = new ArrayList<>();
		
		List<Endereco> enderecos = usuario.getEnderecos();
		for (Endereco endereco : enderecos) {
			endereco.setRua(rua);
			endereco.setBairro(bairro);
			endereco.setNumero(numero);
			enderecosAtualizados.add(endereco);
		}
		usuario.setEnderecos(enderecosAtualizados);
		
		return usuario;
	}

	public UsuarioForm() {
	}

}
