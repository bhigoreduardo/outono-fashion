package com.outonofashion.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {

	FALHA_REQUISICAO("/falha-requisicao", "Falha na requisição."),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado."),
	ENTIDADE_JA_CADASTRADA("/entidade-ja-cadastrada", "Entidade já cadastrada."),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso."),
	
	CONTEUDO_INVALIDO("/conteudo-invalido", "Conteúdo inválido."),
	PROPRIEDADE_INEXISTENTE("/propriedade-inexistente", "Proprieade Inexistente."),
	FORMATO_INVALIDO("/formato-invalido", "Formato inválido."),
	
	CAMPOS_INVALIDOS("/campos-invalidos", "Campos inválidos."),
	
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro da URL inválido."),
	ERRO_INTERNO("/erro-interno", "Erro interno.");

	private String uri;
	private String title;

	private ApiErrorType(String path, String title) {
		this.uri = "http://127.0.0.1" + path;
		this.title = title;
	}

}
