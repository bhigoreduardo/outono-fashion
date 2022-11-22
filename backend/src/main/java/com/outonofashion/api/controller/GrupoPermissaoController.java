package com.outonofashion.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {
	
	@Autowired
	private GrupoService grupoService;
	
	@PutMapping("/{permissaoId}")
	public ResponseEntity<Void> addPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.addPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{permissaoId}")
	public ResponseEntity<Void> removePermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.removePermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}

}
