package com.outonofashion.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.grupo.GrupoEmUsoException;
import com.outonofashion.domain.exception.grupo.GrupoJaCadastradoException;
import com.outonofashion.domain.exception.grupo.GrupoNaoEncontradoException;
import com.outonofashion.domain.model.Grupo;
import com.outonofashion.domain.repository.GrupoRepository;

@Service
public class GrupoService {

	private final String GRUPO_EM_USO = "Grupo cód. %d está em uso.";

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private PermissaoService permissaoService;

	public List<Grupo> findAll() {
		return grupoRepository.findAll();
	}

	public Grupo findById(Long grupoId) {
		return grupoRepository.findById(grupoId)
				.orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}

	@Transactional
	public Grupo save(Grupo grupo) {

		try {
			Long permissaoId = grupo.getPermissao().getId();
			permissaoService.findById(permissaoId);

			grupo = grupoRepository.save(grupo);
			grupoRepository.flush();

			return grupo;
		} catch (DataIntegrityViolationException ex) {
			throw new GrupoJaCadastradoException(grupo.getDescricao());
		}

	}

	@Transactional
	public void deleteById(Long grupoId) {

		try {
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();
		} catch (EmptyResultDataAccessException ex) {
			throw new GrupoNaoEncontradoException(grupoId);
		} catch (DataIntegrityViolationException ex) {
			throw new GrupoEmUsoException(String.format(GRUPO_EM_USO, grupoId));
		}

	}

}
