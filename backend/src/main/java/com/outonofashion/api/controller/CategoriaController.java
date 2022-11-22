package com.outonofashion.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.CategoriaInputDisassembler;
import com.outonofashion.api.assembler.CategoriaModelAssembler;
import com.outonofashion.api.model.CategoriaModel;
import com.outonofashion.api.model.input.CategoriaInput;
import com.outonofashion.domain.model.Categoria;
import com.outonofashion.domain.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CategoriaModelAssembler categoriaModelAssembler;

	@Autowired
	private CategoriaInputDisassembler categoriaInputDisassembler;

	@GetMapping
	public List<CategoriaModel> findAll() {
		return categoriaModelAssembler.toCollectionModel(categoriaService.findAll());
	}

	@GetMapping("{categoriaId}")
	public CategoriaModel findById(@PathVariable Long categoriaId) {
		return categoriaModelAssembler.toModel(categoriaService.findById(categoriaId));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CategoriaModel insert(@RequestBody @Valid CategoriaInput categoriaInput) {
		Categoria categoria = categoriaService.save(categoriaInputDisassembler.toDomain(categoriaInput));

		return categoriaModelAssembler.toModel(categoria);
	}

	@PutMapping("/{categoriaId}")
	public CategoriaModel update(@RequestBody @Valid CategoriaInput categoriaInput, @PathVariable Long categoriaId) {
		Categoria categoriaCurrent = categoriaService.findById(categoriaId);

		categoriaInputDisassembler.copyToDomain(categoriaInput, categoriaCurrent);

		return categoriaModelAssembler.toModel(categoriaService.save(categoriaCurrent));
	}

}
