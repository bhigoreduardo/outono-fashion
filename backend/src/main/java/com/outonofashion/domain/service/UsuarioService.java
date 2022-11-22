package com.outonofashion.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.NegocioException;
import com.outonofashion.domain.exception.UsuarioNaoEncontradoException;
import com.outonofashion.domain.model.Grupo;
import com.outonofashion.domain.model.Usuario;
import com.outonofashion.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final String USER_EMAIL_CPFCNPJ = "Usuário com E-mail: %s e CPF-CNPJ: %s já cadastrado.";
	private final String USER_EMAIL = "Usuário com E-mail: %s já cadastrado.";
	private final String USER_CPFCNPJ = "Usuário com CPF-CNPJ: %s já cadastrado.";

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
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

		usuarioRepository.detach(usuario);

		Optional<Usuario> usuarioEmailPresent = usuarioRepository.findByEmail(usuario.getEmail());
		Optional<Usuario> usuarioCpfCnpjPresent = usuarioRepository.findByCpfCnpj(usuario.getCpfCnpj());

		if (usuario.isNovoUsuario()) {
			
			if (usuarioEmailPresent.isPresent() && usuarioCpfCnpjPresent.isPresent()) {
				throw new NegocioException(String.format(USER_EMAIL_CPFCNPJ, usuario.getEmail(), usuario.getCpfCnpj()));
			} else if (usuarioEmailPresent.isPresent()) {
				throw new NegocioException(String.format(USER_EMAIL, usuario.getEmail()));
			} else if (usuarioCpfCnpjPresent.isPresent()) {
				throw new NegocioException(String.format(USER_CPFCNPJ, usuario.getCpfCnpj()));
			}
			
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			
		}
		
		return usuarioRepository.save(usuario);

	}
	
	@Transactional
	public void setSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = findById(usuarioId);

		if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}
		
		usuario.setSenha(passwordEncoder.encode(novaSenha));
	}
	
	@Transactional
	public void addGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = findById(usuarioId);
		Grupo grupo = grupoService.findById(grupoId);
		
		usuario.addGrupo(grupo);
	}
	
	@Transactional
	public void removeGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = findById(usuarioId);
		Grupo grupo = grupoService.findById(grupoId);
		
		usuario.removeGrupo(grupo);
	}

}
