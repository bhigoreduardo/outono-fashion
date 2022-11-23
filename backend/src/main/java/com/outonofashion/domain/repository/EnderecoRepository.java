package com.outonofashion.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Endereco;
import com.outonofashion.domain.model.Usuario;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	public Optional<Endereco> findByIdEnderecoApelidoAndUsuario(String idEnderecoApelido, Usuario usuario);
	public List<Endereco> findByUsuario(Usuario usuario);
	
}
