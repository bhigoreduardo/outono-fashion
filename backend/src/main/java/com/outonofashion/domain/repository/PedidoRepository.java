package com.outonofashion.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Cupom;
import com.outonofashion.domain.model.Pedido;
import com.outonofashion.domain.model.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	public Pedido findTopByOrderByIdDesc();
	
	public Optional<Pedido> findByCodigoPedido(String codigoPedido);
	
	public List<Pedido> findByUsuario(Usuario usuario);
	
	public Optional<Pedido> findByUsuarioAndCupom(Usuario usuario, Cupom cupom);

}
