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

import com.outonofashion.api.assembler.TamanhoInputDisassembler;
import com.outonofashion.api.assembler.TamanhoModelAssembler;
import com.outonofashion.api.model.TamanhoModel;
import com.outonofashion.api.model.input.TamanhoInput;
import com.outonofashion.domain.model.Tamanho;
import com.outonofashion.domain.service.TamanhoService;

@RestController
@RequestMapping("/tamanhos")
public class TamanhoController {

	@Autowired
	private TamanhoService tamanhoService;

	@Autowired
	private TamanhoModelAssembler tamanhoModelAssembler;

	@Autowired
	private TamanhoInputDisassembler tamanhoInputDisassembler;

	@GetMapping
	public List<TamanhoModel> findAll() {
		return tamanhoModelAssembler.toCollectionModel(tamanhoService.findAll());
	}

	@GetMapping("{tamanhoId}")
	public TamanhoModel findById(@PathVariable Long tamanhoId) {
		return tamanhoModelAssembler.toModel(tamanhoService.findById(tamanhoId));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public TamanhoModel insert(@RequestBody @Valid TamanhoInput tamanhoInput) {
		Tamanho tamanho = tamanhoService.save(tamanhoInputDisassembler.toDomain(tamanhoInput));

		return tamanhoModelAssembler.toModel(tamanho);
	}

	@PutMapping("/{tamanhoId}")
	public TamanhoModel update(@RequestBody @Valid TamanhoInput tamanhoInput, @PathVariable Long tamanhoId) {
		Tamanho tamanhoCurrent = tamanhoService.findById(tamanhoId);

		tamanhoInputDisassembler.copyToDomain(tamanhoInput, tamanhoCurrent);

		return tamanhoModelAssembler.toModel(tamanhoService.save(tamanhoCurrent));
	}

}
