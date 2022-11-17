package com.outonofashion.api.assembler.pagamento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.pagamento.input.PagamentoInput;
import com.outonofashion.domain.model.Pagamento;

@Component
public class PagamentoModelDiassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Pagamento toDomain(PagamentoInput pagamentoInput) {
		return modelMapper.map(pagamentoInput, Pagamento.class);
	}

	public void copyToDomain(PagamentoInput pagamentoInput, Pagamento pagamento) {
		modelMapper.map(pagamentoInput, pagamento);
	}

}
