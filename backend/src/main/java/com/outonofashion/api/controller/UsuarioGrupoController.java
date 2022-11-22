package com.outonofashion.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PutMapping("/{grupoId}")
	public ResponseEntity<Void> addGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.addGrupo(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{grupoId}")
	public ResponseEntity<Void> removeGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.removeGrupo(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}

}
