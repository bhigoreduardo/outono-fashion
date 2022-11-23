package com.outonofashion.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Telefone;
import com.outonofashion.domain.model.Usuario;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
	
	public List<Telefone> findByUsuario(Usuario usuario);
	public Optional<Telefone> findByNumero(String numero);

}
