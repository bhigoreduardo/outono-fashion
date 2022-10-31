package com.outonofashion.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.outonofashion.domain.model.id.EnderecoId;

import lombok.Data;

@Data
@Entity
@IdClass(EnderecoId.class)
public class Endereco {
	
	@Id
	@Column(length = 60)
	private String enderecoApelido;
	
	@Column(nullable = false, length = 8)
	private String enderecoCep;
	
	@Column(nullable = false, length = 60)
	private String enderecoBairro;
	
	@Column(nullable = false, length = 60)
	private String enderecoLogradouro;
	
	@Column(nullable = false, length = 6)
	private String enderecoNumero;
	
	@Column(length = 60)
	private String enderecoComplemento;
	
	@Column(length = 60)
	private String enderecoReferencia;
	
	@Column(nullable = false)
	private Boolean enderecoAtivo;
	
	@Id
	@ManyToOne
	private Usuario usuario;

}
