package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.usuario.UsuarioJaCadastradoException;
import com.outonofashion.domain.exception.usuario.UsuarioNaoEncontradoException;
import com.outonofashion.domain.model.Usuario;
import com.outonofashion.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final String USER_EXISTS = "E-mail: %s e/ou CPF-CNPJ: %s já cadastrado.";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private GeneroService generoService;
	
	@Autowired
	private GrupoService grupoService;
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Usuario findById(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
	
	@Transactional
	public Usuario save(Usuario usuario) {

		try {
			Long generoId = usuario.getGenero().getId();
			generoService.findById(generoId);
			
			Long grupoId = usuario.getGrupo().getId();
			grupoService.findById(grupoId);

			usuario = usuarioRepository.save(usuario);
			usuarioRepository.flush();

			return usuario;
		} catch (DataIntegrityViolationException ex) {
			throw new UsuarioJaCadastradoException(String.format(USER_EXISTS, usuario.getEmail(), usuario.getCpfCnpj()));
		}

	}
	
}
