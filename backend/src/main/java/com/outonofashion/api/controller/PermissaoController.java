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

import com.outonofashion.api.assembler.PermissaoModelAssembler;
import com.outonofashion.api.assembler.PermissaoInputDisassembler;
import com.outonofashion.api.model.PermissaoModel;
import com.outonofashion.api.model.input.PermissaoInput;
import com.outonofashion.domain.model.Permissao;
import com.outonofashion.domain.service.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

	@Autowired
	private PermissaoService permissaoService;

	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;

	@Autowired
	private PermissaoInputDisassembler permissaoInputDisassembler;
	
	@GetMapping
	public List<PermissaoModel> findAll() {
		return permissaoModelAssembler.toCollectionModel(permissaoService.findAll());
	}
	
	@GetMapping("/{permissaoId}")
	public PermissaoModel findById(@PathVariable Long permissaoId) {
		Permissao permissaoCurrent = permissaoService.findById(permissaoId);

		return permissaoModelAssembler.toModel(permissaoCurrent);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public PermissaoModel insert(@RequestBody @Valid PermissaoInput permissaoInput) {
		Permissao permissao = permissaoInputDisassembler.toDomain(permissaoInput);

		return permissaoModelAssembler.toModel(permissaoService.save(permissao));
	}

	@PutMapping("/{permissaoId}")
	public PermissaoModel update(@RequestBody @Valid PermissaoInput permissaoInput, @PathVariable Long permissaoId) {
		Permissao permissaoCurrent = permissaoService.findById(permissaoId);

		permissaoInputDisassembler.copyToDomain(permissaoInput, permissaoCurrent);

		return permissaoModelAssembler.toModel(permissaoService.save(permissaoCurrent));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{permissaoId}")
	public void deleteById(@PathVariable Long permissaoId) {
		permissaoService.deleteById(permissaoId);
	}

}
