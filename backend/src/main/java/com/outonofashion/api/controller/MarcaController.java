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

import com.outonofashion.api.assembler.MarcaInputDisassembler;
import com.outonofashion.api.assembler.MarcaModelAssembler;
import com.outonofashion.api.model.MarcaModel;
import com.outonofashion.api.model.input.MarcaInput;
import com.outonofashion.domain.model.Marca;
import com.outonofashion.domain.service.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;

	@Autowired
	private MarcaModelAssembler marcaModelAssembler;

	@Autowired
	private MarcaInputDisassembler marcaInputDisassembler;

	@GetMapping
	public List<MarcaModel> findAll() {
		return marcaModelAssembler.toCollectionModel(marcaService.findAll());
	}

	@GetMapping("{marcaId}")
	public MarcaModel findById(@PathVariable Long marcaId) {
		return marcaModelAssembler.toModel(marcaService.findById(marcaId));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public MarcaModel insert(@RequestBody @Valid MarcaInput marcaInput) {
		Marca marca = marcaService.save(marcaInputDisassembler.toDomain(marcaInput));

		return marcaModelAssembler.toModel(marca);
	}

	@PutMapping("/{marcaId}")
	public MarcaModel update(@RequestBody @Valid MarcaInput marcaInput, @PathVariable Long marcaId) {
		Marca marcaCurrent = marcaService.findById(marcaId);

		marcaInputDisassembler.copyToDomain(marcaInput, marcaCurrent);

		return marcaModelAssembler.toModel(marcaService.save(marcaCurrent));
	}

}
