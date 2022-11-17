package com.outonofashion.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outonofashion.api.assembler.pagamento.PagamentoModelAssembler;
import com.outonofashion.api.assembler.pagamento.PagamentoModelDiassembler;
import com.outonofashion.api.model.pagamento.PagamentoModel;
import com.outonofashion.api.model.pagamento.input.PagamentoInput;
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
	private PagamentoModelDiassembler pagamentoModelDiassembler;
	
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
		Pagamento pagamento = pagamentoModelDiassembler.toDomain(pagamentoInput);
		
		return pagamentoModelAssembler.toModel(pagamentoService.save(pagamento));
	}

}
