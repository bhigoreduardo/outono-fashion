package com.outonofashion.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.UsuarioInputDisassembler;
import com.outonofashion.api.assembler.UsuarioModelAssembler;
import com.outonofashion.api.model.UsuarioModel;
import com.outonofashion.api.model.input.SenhaInput;
import com.outonofashion.api.model.input.UsuarioInput;
import com.outonofashion.api.model.input.UsuarioLoginInput;
import com.outonofashion.api.model.input.UsuarioSenhaInput;
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
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@PostMapping("/login")
	public UsuarioModel authLogin(@RequestBody @Valid UsuarioLoginInput usuarioLoginInput) {
		Usuario usuario = usuarioService.authLogin(usuarioLoginInput.getEmail(), usuarioLoginInput.getSenha());
		
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioModel findById(@PathVariable Long usuarioId) {
		Usuario usuarioCurrent = usuarioService.findById(usuarioId);
		
		return usuarioModelAssembler.toModel(usuarioCurrent);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public UsuarioModel insert(@RequestBody @Valid UsuarioSenhaInput usuarioInput) {
		Usuario usuario = usuarioInputDisassembler.toDomain(usuarioInput);
		
		usuario = usuarioService.save(usuario);
		usuarioService.addGrupo(Long.parseLong("1"), usuario.getId());
		
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioModel update(@RequestBody @Valid UsuarioInput usuarioInput, @PathVariable Long usuarioId) {
		Usuario usuarioCurrent = usuarioService.findById(usuarioId);
		
		usuarioInputDisassembler.copyToDomain(usuarioInput, usuarioCurrent);
		
		return usuarioModelAssembler.toModel(usuarioService.save(usuarioCurrent));
	}
	
	@PutMapping("/{usuarioId}/senha")
	public ResponseEntity<Void> setSenha(@PathVariable Long usuarioId, @RequestBody SenhaInput senha) {
		usuarioService.setSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
		return ResponseEntity.noContent().build();
	}

}
