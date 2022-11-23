package com.outonofashion.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.EnderecoInputDisassembler;
import com.outonofashion.api.assembler.EnderecoModelAssembler;
import com.outonofashion.api.model.EnderecoModel;
import com.outonofashion.api.model.input.EnderecoInput;
import com.outonofashion.domain.model.Endereco;
import com.outonofashion.domain.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private EnderecoModelAssembler enderecoModelAssembler;

	@Autowired
	private EnderecoInputDisassembler enderecoInputDisassembler;

	@GetMapping("/{usuarioId}")
	public List<EnderecoModel> findByUsuario(@PathVariable Long usuarioId) {
		return enderecoModelAssembler.toCollectionModel(enderecoService.findByUsuario(usuarioId));
	}

	@PostMapping
	public EnderecoModel insert(@RequestBody @Valid EnderecoInput enderecoInput) {
		Endereco endereco = enderecoInputDisassembler.toDomain(enderecoInput);

		return enderecoModelAssembler.toModel(enderecoService.save(endereco));
	}

	@PutMapping
	public EnderecoModel update(@RequestBody @Valid EnderecoInput enderecoInput) {
		Endereco enderecoCurrent = enderecoService.findByIdEnderecoApelidoAndUsuario(
				enderecoInput.getId().getEnderecoApelido(), enderecoInput.getUsuario().getId());

		enderecoInputDisassembler.copyToDomain(enderecoInput, enderecoCurrent);

		return enderecoModelAssembler.toModel(enderecoService.save(enderecoCurrent));
	}

	@PutMapping("/{enderecoApelido}/{usuarioId}/ativo")
	public ResponseEntity<Void> active(@PathVariable String enderecoApelido, @PathVariable Long usuarioId) {
		enderecoService.active(enderecoApelido, usuarioId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{enderecoApelido}/{usuarioId}/inativo")
	public ResponseEntity<Void> inactive(@PathVariable String enderecoApelido, @PathVariable Long usuarioId) {
		enderecoService.inactive(enderecoApelido, usuarioId);
		return ResponseEntity.noContent().build();
	}

}
