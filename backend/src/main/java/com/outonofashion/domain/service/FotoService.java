package com.outonofashion.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.foto.FotoEmUsoException;
import com.outonofashion.domain.exception.foto.FotoNaoEncontradaException;
import com.outonofashion.domain.model.Foto;
import com.outonofashion.domain.model.Usuario;
import com.outonofashion.domain.repository.FotoRepository;

@Service
public class FotoService {
	
	private final String FOTO_EM_USO = "Foto cód. %d está em uso.";
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Foto findById(Long fotoId) {
		return fotoRepository.findById(fotoId)
				.orElseThrow(() -> new FotoNaoEncontradaException(fotoId));
	}
	
	@Transactional
	public Foto save(Foto foto) {
		
		Long usuarioId = foto.getUsuario().getId();
		Usuario usuario = usuarioService.findById(usuarioId);
		
		foto = fotoRepository.save(foto);
		fotoRepository.flush();
		
		return foto;

	}
	
	@Transactional
	public void deleteById(Long fotoId) {
		
		try {
			fotoRepository.deleteById(fotoId);
			fotoRepository.flush();
		} catch (EmptyResultDataAccessException ex) {
			throw new FotoNaoEncontradaException(fotoId);
		} catch (DataIntegrityViolationException ex) {
			throw new FotoEmUsoException(String.format(FOTO_EM_USO, fotoId));
		}
		
	}

}
