package com.outonofashion.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.CupomInputDisassembler;
import com.outonofashion.api.assembler.CupomModelAssembler;
import com.outonofashion.api.model.CupomModel;
import com.outonofashion.api.model.input.CupomInput;
import com.outonofashion.domain.model.Cupom;
import com.outonofashion.domain.service.CupomService;

@RestController
@RequestMapping("/cupons")
public class CupomController {

	@Autowired
	private CupomService cupomService;

	@Autowired
	private CupomModelAssembler cupomModelAssembler;

	@Autowired
	private CupomInputDisassembler cupomInputDisassembler;

	@GetMapping
	public List<CupomModel> findAll() {
		return cupomModelAssembler.toCollectionModel(cupomService.findAll());
	}

	@GetMapping("{cupomId}")
	public CupomModel findById(@PathVariable Long cupomId) {
		return cupomModelAssembler.toModel(cupomService.findById(cupomId));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CupomModel insert(@RequestBody @Valid CupomInput cupomInput) {
		Cupom cupom = cupomService.save(cupomInputDisassembler.toDomain(cupomInput));

		return cupomModelAssembler.toModel(cupom);
	}

	@PutMapping("/{cupomId}")
	public CupomModel update(@RequestBody @Valid CupomInput cupomInput, @PathVariable Long cupomId) {
		Cupom cupomCurrent = cupomService.findById(cupomId);

		cupomInputDisassembler.copyToDomain(cupomInput, cupomCurrent);

		return cupomModelAssembler.toModel(cupomService.save(cupomCurrent));
	}
	
	@PutMapping("/{cupomId}/ativo")
	public ResponseEntity<Void> active(@PathVariable Long cupomId) {
		cupomService.active(cupomId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{cupomId}/inativo")
	public ResponseEntity<Void> inactive(@PathVariable Long cupomId) {
		cupomService.inactive(cupomId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{cupomId}/porcentagem")
	public ResponseEntity<Void> setTipoPorcentagem(@PathVariable Long cupomId) {
		cupomService.setTipoPorcentagem(cupomId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{cupomId}/fixo")
	public ResponseEntity<Void> setTipoFixo(@PathVariable Long cupomId) {
		cupomService.setTipoFixo(cupomId);
		return ResponseEntity.noContent().build();
	}

}
