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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.TelefoneInputDisassembler;
import com.outonofashion.api.assembler.TelefoneModelAssembler;
import com.outonofashion.api.model.TelefoneModel;
import com.outonofashion.api.model.input.TelefoneInput;
import com.outonofashion.domain.model.Telefone;
import com.outonofashion.domain.service.TelefoneService;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

	@Autowired
	private TelefoneService telefoneService;

	@Autowired
	private TelefoneModelAssembler telefoneModelAssembler;

	@Autowired
	private TelefoneInputDisassembler telefoneInputDisassembler;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public TelefoneModel insert(@RequestBody @Valid TelefoneInput telefoneInput) {
		Telefone telefone = telefoneInputDisassembler.toDomain(telefoneInput);

		return telefoneModelAssembler.toModel(telefoneService.insert(telefone));
	}

	@DeleteMapping("/{numero}")
	public ResponseEntity<Void> deleteByNumero(@PathVariable String numero) {
		telefoneService.deleteByNumero(numero);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{usuarioId}")
	public List<TelefoneModel> findByUsuario(@PathVariable Long usuarioId) {
		return telefoneModelAssembler.toCollectionModel(telefoneService.findByUsuario(usuarioId));
	}

}
