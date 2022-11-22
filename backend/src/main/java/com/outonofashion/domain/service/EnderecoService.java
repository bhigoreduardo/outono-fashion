package com.outonofashion.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.EnderecoNaoEncontradoException;
import com.outonofashion.domain.model.Endereco;
import com.outonofashion.domain.model.Usuario;
import com.outonofashion.domain.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Endereco findByIdEnderecoApelidoAndUsuario(String enderecoApelido, Long usuarioId) {
		Usuario usuario = usuarioService.findById(usuarioId);
		
		return enderecoRepository.findByIdEnderecoApelidoAndUsuario(enderecoApelido, usuario)
				.orElseThrow(() -> new EnderecoNaoEncontradoException(enderecoApelido, usuario.getNome()));
	}
	
	@Transactional
	public Endereco save(Endereco endereco) {
		Usuario usuario = usuarioService.findById(endereco.getUsuario().getId());
		
		endereco.setUsuario(usuario);
		
		return enderecoRepository.save(endereco);
	}
	
	@Transactional
	public void active(String enderecoApelido, Long usuarioId) {
		Endereco endereco = findByIdEnderecoApelidoAndUsuario(enderecoApelido, usuarioId);
		endereco.active();
	}
	
	@Transactional
	public void inactive(String enderecoApelido, Long usuarioId) {
		Endereco endereco = findByIdEnderecoApelidoAndUsuario(enderecoApelido, usuarioId);
		endereco.inactive();
	}

}
