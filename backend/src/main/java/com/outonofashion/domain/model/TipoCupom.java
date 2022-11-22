package com.outonofashion.domain.model;

public enum TipoCupom {
	
	PORCENTAGEM("Porcentagem"),
	FIXO("Valor Fixo");
	
	private String descricao;
	
	TipoCupom(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
