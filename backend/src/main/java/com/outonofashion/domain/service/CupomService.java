package com.outonofashion.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.cupom.CupomNaoEncontradoException;
import com.outonofashion.domain.model.Cupom;
import com.outonofashion.domain.repository.CupomRepository;

@Service
public class CupomService {
	
	@Autowired
	private CupomRepository cupomRepository;
	
	public Cupom findById(Long cupomId) {
		
		return cupomRepository.findById(cupomId)
				.orElseThrow(() -> new CupomNaoEncontradoException(cupomId));
		
	}

}
