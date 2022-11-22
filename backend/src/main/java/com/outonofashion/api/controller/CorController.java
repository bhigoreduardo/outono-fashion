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

import com.outonofashion.api.assembler.CorInputDisassembler;
import com.outonofashion.api.assembler.CorModelAssembler;
import com.outonofashion.api.model.CorModel;
import com.outonofashion.api.model.input.CorInput;
import com.outonofashion.domain.model.Cor;
import com.outonofashion.domain.service.CorService;

@RestController
@RequestMapping("/cores")
public class CorController {

	@Autowired
	private CorService corService;

	@Autowired
	private CorModelAssembler corModelAssembler;
	
	@Autowired
	private CorInputDisassembler corInputDisassembler;

	@GetMapping
	public List<CorModel> findAll() {
		return corModelAssembler.toCollectionModel(corService.findAll());
	}

	@GetMapping("{corId}")
	public CorModel findById(@PathVariable Long corId) {
		return corModelAssembler.toModel(corService.findById(corId));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CorModel insert(@RequestBody @Valid CorInput corInput) {
		Cor cor = corService.save(corInputDisassembler.toDomain(corInput));

		return corModelAssembler.toModel(cor);
	}

	@PutMapping("/{corId}")
	public CorModel update(@RequestBody @Valid CorInput corInput, @PathVariable Long corId) {
		Cor corCurrent = corService.findById(corId);

		corInputDisassembler.copyToDomain(corInput, corCurrent);

		return corModelAssembler.toModel(corService.save(corCurrent));
	}

}
