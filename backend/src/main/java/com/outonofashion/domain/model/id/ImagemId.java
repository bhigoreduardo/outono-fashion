package com.outonofashion.domain.model.id;

import java.io.Serializable;

import com.outonofashion.domain.model.Cor;
import com.outonofashion.domain.model.Produto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ImagemId implements Serializable {
	private Produto produto;
	private Cor cor;
}
