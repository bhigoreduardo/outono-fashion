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

import com.outonofashion.api.assembler.GrupoInputDisassembler;
import com.outonofashion.api.assembler.GrupoModelAssembler;
import com.outonofashion.api.model.GrupoModel;
import com.outonofashion.api.model.input.GrupoInput;
import com.outonofashion.domain.model.Grupo;
import com.outonofashion.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;

	@GetMapping
	public List<GrupoModel> findAll() {
		return grupoModelAssembler.toCollectionModel(grupoService.findAll());
	}

	@GetMapping("/{grupoId}")
	public GrupoModel findById(@PathVariable Long grupoId) {
		Grupo grupo = grupoService.findById(grupoId);

		return grupoModelAssembler.toModel(grupo);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public GrupoModel insert(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoInputDisassembler.toDomain(grupoInput);

		return grupoModelAssembler.toModel(grupoService.save(grupo));
	}

	@PutMapping("/{grupoId}")
	public GrupoModel update(@RequestBody @Valid GrupoInput grupoInput, @PathVariable Long grupoId) {
		Grupo grupoCurrent = grupoService.findById(grupoId);

		grupoInputDisassembler.copyToDomain(grupoInput, grupoCurrent);

		return grupoModelAssembler.toModel(grupoService.save(grupoCurrent));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{grupoId}")
	public void deleteById(@PathVariable Long grupoId) {
		grupoService.deleteById(grupoId);
	}
	
	

}
