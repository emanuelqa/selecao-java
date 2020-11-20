package com.emanuel.indra.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuel.indra.controller.form.UsuarioForm;
import com.emanuel.indra.model.Usuario;
import com.emanuel.indra.repositores.EnderecoRepository;
import com.emanuel.indra.repositores.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Usuario cadastrar(UsuarioForm form) {
		Usuario usuario = usuarioRepository.save(form.converter());
		enderecoRepository.saveAll(usuario.getEnderecos());
		return usuario;
	}

	public List<Usuario> listarAll() {
		return usuarioRepository.findAll();
	}

	public Usuario atualizar(Long id, @Valid UsuarioForm form) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			Usuario usuario = form.converterAtualizar(optional.get());
			return usuarioRepository.save(usuario);
		}
		return null;
	}

	public boolean deletar(Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			usuarioRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
