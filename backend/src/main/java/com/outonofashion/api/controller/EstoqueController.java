package com.outonofashion.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.EstoqueInputDisassembler;
import com.outonofashion.api.assembler.EstoqueModelAssembler;
import com.outonofashion.api.model.EstoqueModel;
import com.outonofashion.api.model.input.EstoqueInput;
import com.outonofashion.domain.model.Estoque;
import com.outonofashion.domain.service.EstoqueService;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

	@Autowired
	private EstoqueService estoqueService;

	@Autowired
	private EstoqueModelAssembler estoqueModelAssembler;

	@Autowired
	private EstoqueInputDisassembler estoqueInputDisassembler;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public EstoqueModel insert(@RequestBody @Valid EstoqueInput estoqueInput) {
		Estoque estoque = estoqueInputDisassembler.toDomain(estoqueInput);

		System.out.println(estoque.getCor().getId());
		estoqueService.save(estoque);

		return estoqueModelAssembler.toModel(estoqueService.save(estoque));
	}

	@PutMapping
	public EstoqueModel update(@RequestBody @Valid EstoqueInput estoqueInput) {
		Estoque estoqueCurrent = estoqueService.findByProdutoAndCorAndTamanho(estoqueInput.getProduto().getId(),
				estoqueInput.getCor().getId(), estoqueInput.getTamanho().getId());
		
		estoqueInputDisassembler.copyToDomain(estoqueInput, estoqueCurrent);
		
		return estoqueModelAssembler.toModel(estoqueService.save(estoqueCurrent));
	}

}
