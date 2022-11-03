package com.outonofashion.domain.model.id;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.outonofashion.domain.model.Cor;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.model.Tamanho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EstoqueId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	private Cor cor;
	private Tamanho tamanho;

}
