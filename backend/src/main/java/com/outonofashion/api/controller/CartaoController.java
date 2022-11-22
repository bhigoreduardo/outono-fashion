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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.CartaoInputDisassembler;
import com.outonofashion.api.assembler.CartaoModelAssembler;
import com.outonofashion.api.model.CartaoModel;
import com.outonofashion.api.model.input.CartaoInput;
import com.outonofashion.domain.model.Cartao;
import com.outonofashion.domain.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;

	@Autowired
	private CartaoModelAssembler cartaoModelAssembler;

	@Autowired
	private CartaoInputDisassembler cartaoInputDisassembler;
	
	@GetMapping
	public List<CartaoModel> findByUsuario(@RequestParam Long usuarioId) {
		return cartaoModelAssembler.toCollectionModel(cartaoService.findByUsuario(usuarioId));
	}

	@PostMapping
	public CartaoModel insert(@RequestBody @Valid CartaoInput cartaoInput) {
		Cartao cartao = cartaoInputDisassembler.toDomain(cartaoInput);

		return cartaoModelAssembler.toModel(cartaoService.save(cartao));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping("/{cartaoId}")
	public CartaoModel update(@PathVariable Long cartaoId, @RequestBody @Valid CartaoInput cartaoInput) {
		Cartao cartaoCurrent = cartaoService.findById(cartaoId);

		cartaoInputDisassembler.copyToDomain(cartaoInput, cartaoCurrent);

		return cartaoModelAssembler.toModel(cartaoService.save(cartaoCurrent));
	}
	
	@DeleteMapping("/{cartaoId}")
	public ResponseEntity<Void> deleteById(@PathVariable Long cartaoId) {
		cartaoService.deleteById(cartaoId);
		return ResponseEntity.noContent().build();
	}

}
