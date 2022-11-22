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

import com.outonofashion.api.assembler.PagamentoInputDisassembler;
import com.outonofashion.api.assembler.PagamentoModelAssembler;
import com.outonofashion.api.model.PagamentoModel;
import com.outonofashion.api.model.input.PagamentoInput;
import com.outonofashion.domain.model.Pagamento;
import com.outonofashion.domain.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@Autowired
	private PagamentoModelAssembler pagamentoModelAssembler;
	
	@Autowired
	private PagamentoInputDisassembler pagamentoInputDisassembler;
	
	@GetMapping
	public List<PagamentoModel> findAll() {
		return pagamentoModelAssembler.toCollectionModel(pagamentoService.findAll());
	}
	
	@GetMapping("/{pagamentoId}")
	public PagamentoModel findById(@PathVariable Long pagamentoId) {
		Pagamento pagamentoCurrent = pagamentoService.findById(pagamentoId);
		
		return pagamentoModelAssembler.toModel(pagamentoCurrent);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public PagamentoModel insert(@RequestBody @Valid PagamentoInput pagamentoInput) {
		Pagamento pagamento = pagamentoInputDisassembler.toDomain(pagamentoInput);
		
		return pagamentoModelAssembler.toModel(pagamentoService.save(pagamento));
	}
	
	@PutMapping("/{pagamentoId}/ativo")
	public ResponseEntity<Void> active(@PathVariable Long pagamentoId) {
		pagamentoService.active(pagamentoId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{pagamentoId}/inativo")
	public ResponseEntity<Void> inactive(@PathVariable Long pagamentoId) {
		pagamentoService.inactive(pagamentoId);
		return ResponseEntity.noContent().build();
	}

}
