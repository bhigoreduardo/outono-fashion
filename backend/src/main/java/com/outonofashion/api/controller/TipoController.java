package com.outonofashion.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.TipoInputDisassembler;
import com.outonofashion.api.assembler.TipoModelAssembler;
import com.outonofashion.api.model.TipoModel;
import com.outonofashion.api.model.input.TipoInput;
import com.outonofashion.domain.model.Tipo;
import com.outonofashion.domain.repository.TipoRepository;
import com.outonofashion.domain.service.TipoService;

@RestController
@RequestMapping("/tipos")
public class TipoController {

	@Autowired
	private TipoRepository tipoRepository;

	@Autowired
	private TipoService tipoService;

	@Autowired
	private TipoModelAssembler tipoModelAssembler;

	@Autowired
	private TipoInputDisassembler tipoInputDisassembler;

	@GetMapping
	public List<TipoModel> findAll() {
		return tipoModelAssembler.toCollectionModel(tipoRepository.findAll());
	}

	@GetMapping("/{tipoId}")
	public TipoModel findById(@PathVariable Long tipoId) {
		return tipoModelAssembler.toModel(tipoService.findById(tipoId));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public TipoModel insert(@RequestBody @Valid TipoInput tipoInput) {
		Tipo tipo = tipoInputDisassembler.toDomain(tipoInput);

		return tipoModelAssembler.toModel(tipoService.save(tipo));
	}

}
