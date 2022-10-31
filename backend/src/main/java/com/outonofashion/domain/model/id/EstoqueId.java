package com.outonofashion.domain.model.id;

import java.io.Serializable;

import com.outonofashion.domain.model.Cor;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.model.Tamanho;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EstoqueId implements Serializable {
	
	private Produto produto;
	private Cor cor;
	private Tamanho tamanho;
	
}
