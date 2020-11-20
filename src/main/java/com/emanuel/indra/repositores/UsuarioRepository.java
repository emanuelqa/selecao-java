package com.emanuel.indra.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuel.indra.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
