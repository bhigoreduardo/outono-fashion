package com.outonofashion.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.foto.FotoModelAssembler;
import com.outonofashion.api.assembler.foto.FotoModelDiassembler;
import com.outonofashion.api.model.foto.FotoModel;
import com.outonofashion.api.model.foto.input.FotoInput;
import com.outonofashion.domain.model.Foto;
import com.outonofashion.domain.service.FotoService;

@RestController
@RequestMapping("/fotos")
public class FotoController {

	@Autowired
	private FotoService fotoService;

	@Autowired
	private FotoModelAssembler fotoModelAssembler;
	
	@Autowired
	private FotoModelDiassembler fotoModelDiassembler;

	@GetMapping("/{fotoId}")
	public FotoModel findById(@PathVariable Long fotoId) {
		return fotoModelAssembler.toModel(fotoService.findById(fotoId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public FotoModel insert(@RequestBody @Valid FotoInput fotoInput) {
		Foto foto = fotoModelDiassembler.toDomain(fotoInput);
		
		return fotoModelAssembler.toModel(fotoService.save(foto));
	}
	
	@PutMapping("/{fotoId}")
	public FotoModel update(@RequestBody @Valid FotoInput fotoInput, @PathVariable Long fotoId) {
		Foto fotoCurrent = fotoService.findById(fotoId);
		
		fotoModelDiassembler.copyToDomain(fotoInput, fotoCurrent);
		
		return fotoModelAssembler.toModel(fotoService.save(fotoCurrent));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{fotoId}")
	public void deleteById(@PathVariable Long fotoId) {
		fotoService.deleteById(fotoId);
	}

}
