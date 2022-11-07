package com.outonofashion.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.domain.model.Tipo;
import com.outonofashion.domain.service.TipoService;

@RestController
@RequestMapping("/tipos")
public class TipoController {

	@Autowired
	private TipoService tipoService;

	@GetMapping
	public List<Tipo> findByDescricaoAndGenero(@RequestParam String genero, @RequestParam String categoria) {
		
		return tipoService.findByDescricaoAndGenero(genero, categoria);
		
	}

}
