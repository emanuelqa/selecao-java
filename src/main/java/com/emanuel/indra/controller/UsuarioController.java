package com.emanuel.indra.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.emanuel.indra.controller.dto.UsuarioDTO;
import com.emanuel.indra.controller.form.UsuarioForm;
import com.emanuel.indra.model.Usuario;
import com.emanuel.indra.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<UsuarioDTO> lista() {
		List<Usuario> usuarios = usuarioService.listarAll();
		return UsuarioDTO.converter(usuarios);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
		Usuario usuario = usuarioService.cadastrar(form); 
		
		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioForm form) {
		Usuario usuario = usuarioService.atualizar(id, form);
		return usuario != null ? ResponseEntity.ok(new UsuarioDTO(usuario)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		return usuarioService.deletar(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

}
