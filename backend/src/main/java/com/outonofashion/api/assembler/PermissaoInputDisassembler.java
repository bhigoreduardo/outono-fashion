package com.outonofashion.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outonofashion.api.model.input.PermissaoInput;
import com.outonofashion.domain.model.Permissao;

@Component
public class PermissaoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Permissao toDomain(PermissaoInput permissaoInput) {
		return modelMapper.map(permissaoInput, Permissao.class);
	}

	public void copyToDomain(PermissaoInput permissaoInput, Permissao permissaoCurrent) {
		modelMapper.map(permissaoInput, permissaoCurrent);
	}

}
