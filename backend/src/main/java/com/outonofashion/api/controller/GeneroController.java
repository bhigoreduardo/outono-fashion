package com.outonofashion.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.genero.GeneroModelAssembler;
import com.outonofashion.api.assembler.genero.GeneroModelDiassembler;
import com.outonofashion.api.model.genero.GeneroModel;
import com.outonofashion.api.model.genero.input.GeneroInput;
import com.outonofashion.domain.model.Genero;
import com.outonofashion.domain.service.GeneroService;

@RestController
@RequestMapping("/generos")
public class GeneroController {

	@Autowired
	private GeneroService generoService;

	@Autowired
	private GeneroModelAssembler generoModelAssembler;

	@Autowired
	private GeneroModelDiassembler generoModelDiassembler;

	@GetMapping
	public List<GeneroModel> findAll() {
		return generoModelAssembler.toCollectionModel(generoService.findAll());
	}

	@GetMapping("{generoId}")
	public GeneroModel findById(@PathVariable Long generoId) {
		return generoModelAssembler.toModel(generoService.findById(generoId));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public GeneroModel insert(@RequestBody @Valid GeneroInput generoInput) {
		Genero genero = generoService.save(generoModelDiassembler.toDomain(generoInput));

		return generoModelAssembler.toModel(genero);
	}

	@PutMapping("/{generoId}")
	public GeneroModel update(@RequestBody @Valid GeneroInput generoInput, @PathVariable Long generoId) {
		Genero generoCurrent = generoService.findById(generoId);
		
		generoModelDiassembler.copyToDomain(generoInput, generoCurrent);
		
		return generoModelAssembler.toModel(generoService.save(generoCurrent));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{generoId}")
	public void deleteById(@PathVariable Long generoId) {
		generoService.deleteById(generoId);
	}

}
