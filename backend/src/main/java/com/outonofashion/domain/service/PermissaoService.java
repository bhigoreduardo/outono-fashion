package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.permissao.PermissaoEmUsoException;
import com.outonofashion.domain.exception.permissao.PermissaoJaCadastradoException;
import com.outonofashion.domain.exception.permissao.PermissaoNaoEncontradaException;
import com.outonofashion.domain.model.Permissao;
import com.outonofashion.domain.repository.PermissaoRepository;

@Service
public class PermissaoService {

	private final String PERMISSAO_EM_USO = "Permissão cód. %d está em uso.";

	@Autowired
	private PermissaoRepository permissaoRepository;

	public List<Permissao> findAll() {
		return permissaoRepository.findAll();
	}

	public Permissao findById(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}

	@Transactional
	public Permissao save(Permissao permissao) {

		try {
			permissao = permissaoRepository.save(permissao);
			permissaoRepository.flush();
			
			return permissao;
		} catch (DataIntegrityViolationException ex) {
			throw new PermissaoJaCadastradoException(permissao.getDescricao());
		}

	}

	@Transactional
	public void deleteById(Long permissaoId) {

		try {
			permissaoRepository.deleteById(permissaoId);
			permissaoRepository.flush();
		} catch (EmptyResultDataAccessException ex) {
			throw new PermissaoNaoEncontradaException(permissaoId);
		} catch (DataIntegrityViolationException ex) {
			throw new PermissaoEmUsoException(String.format(PERMISSAO_EM_USO, permissaoId));
		}

	}

}
