package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.CartaoEmUsoException;
import com.outonofashion.domain.exception.CartaoNaoEncontradoException;
import com.outonofashion.domain.model.Cartao;
import com.outonofashion.domain.model.Usuario;
import com.outonofashion.domain.repository.CartaoRepository;

@Service
public class CartaoService {
	
	private final String CARTAO_EM_USO = "Cartão cód. %d está em uso.";
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Cartao findById(Long cartaoId) {
		return cartaoRepository.findById(cartaoId)
				.orElseThrow(() -> new CartaoNaoEncontradoException(cartaoId));
	}
	
	public List<Cartao> findByUsuario(Long usuarioId) {
		Usuario usuario = usuarioService.findById(usuarioId);
		return cartaoRepository.findByUsuario(usuario);
	}
	
	@Transactional
	public Cartao save(Cartao cartao) {
		Usuario usuario = usuarioService.findById(cartao.getUsuario().getId());
		
		cartao.setUsuario(usuario);
		
		return cartaoRepository.save(cartao);
	}
	
	@Transactional
	public void deleteById(Long cartaoId) {

		try {
			cartaoRepository.deleteById(cartaoId);
			cartaoRepository.flush();
		} catch (EmptyResultDataAccessException ex) {
			throw new CartaoNaoEncontradoException(cartaoId);
		} catch (DataIntegrityViolationException ex) {
			throw new CartaoEmUsoException(String.format(CARTAO_EM_USO, cartaoId));
		}
		
	}

}
