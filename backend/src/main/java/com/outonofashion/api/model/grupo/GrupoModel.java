package com.outonofashion.api.model.grupo;

import com.outonofashion.api.model.permissao.PermissaoModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoModel {
	
	private String descricao;
	private PermissaoModel permissaoModel;

}
