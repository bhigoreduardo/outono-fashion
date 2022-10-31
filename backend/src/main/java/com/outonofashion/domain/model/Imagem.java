package com.outonofashion.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.outonofashion.domain.model.id.ImagemId;

import lombok.Data;

@Data
@Entity
@IdClass(ImagemId.class)
public class Imagem {
	
	@Column(nullable = false)
	private String url;
	
	@Id
	@ManyToOne
	private Produto produto;
	
	@Id
	@ManyToOne
	private Cor cor;

}
