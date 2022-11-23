package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.NegocioException;
import com.outonofashion.domain.exception.TelefoneNaoEncontradoException;
import com.outonofashion.domain.model.Telefone;
import com.outonofashion.domain.model.Usuario;
import com.outonofashion.domain.repository.TelefoneRepository;

@Service
public class TelefoneService {

	private final String TELEFONE_JA_CADASTRADO = "Telefone número %s já foi cadastrado.";

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Transactional
	public Telefone insert(Telefone telefone) {
		try {
			Usuario usuario = usuarioService.findById(telefone.getUsuario().getId());
			telefone.setUsuario(usuario);

			telefone = telefoneRepository.save(telefone);
			telefoneRepository.flush();
			return telefone;
		} catch (DataIntegrityViolationException ex) {
			throw new NegocioException(String.format(TELEFONE_JA_CADASTRADO, telefone.getNumero()));
		}
	}

	@Transactional
	public void deleteByNumero(String numero) {
		Telefone telefone = telefoneRepository.findByNumero(numero)
				.orElseThrow(() -> new TelefoneNaoEncontradoException(numero));
		
		telefoneRepository.delete(telefone);
	}

	public List<Telefone> findByUsuario(Long usuarioId) {
		Usuario usuario = usuarioService.findById(usuarioId);

		return telefoneRepository.findByUsuario(usuario);
	}

}
