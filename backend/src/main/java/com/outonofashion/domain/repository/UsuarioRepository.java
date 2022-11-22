package com.outonofashion.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail(String email);
	public Optional<Usuario> findByCpfCnpj(String cpfCnpj);

}
