package com.outonofashion.api.model;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoModel {
	
	private String nome;
	private Set<PermissaoModel> permissoes;

}
