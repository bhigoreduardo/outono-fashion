package com.outonofashion.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Cartao;
import com.outonofashion.domain.model.Usuario;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	public List<Cartao> findByUsuario(Usuario usuario);
	
}
