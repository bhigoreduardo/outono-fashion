package com.outonofashion.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoModel {
	
	private String numero;
	private String nomeImpresso;
	private String mesValidade;
	private String anoValidade;
	private String cvv;

}
