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

import com.outonofashion.api.assembler.usuario.UsuarioModelAssembler;
import com.outonofashion.api.assembler.usuario.UsuarioModelDiassembler;
import com.outonofashion.api.model.usuario.UsuarioModel;
import com.outonofashion.api.model.usuario.input.UsuarioInput;
import com.outonofashion.domain.model.Usuario;
import com.outonofashion.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioModelDiassembler usuarioModelDiassembler;
	
	@GetMapping
	public List<UsuarioModel> findAll() {
		return usuarioModelAssembler.toCollectionModel(usuarioService.findAll());
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioModel findById(@PathVariable Long usuarioId) {
		Usuario usuarioCurrent = usuarioService.findById(usuarioId);
		
		return usuarioModelAssembler.toModel(usuarioCurrent);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public UsuarioModel insert(@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario = usuarioModelDiassembler.toDomain(usuarioInput);
		
		return usuarioModelAssembler.toModel(usuarioService.save(usuario));
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioModel update(@RequestBody @Valid UsuarioInput usuarioInput, @PathVariable Long usuarioId) {
		Usuario usuarioCurrent = usuarioService.findById(usuarioId);
		
		usuarioModelDiassembler.copyToDomain(usuarioInput, usuarioCurrent);
		
		return usuarioModelAssembler.toModel(usuarioService.save(usuarioCurrent));
	}

}
