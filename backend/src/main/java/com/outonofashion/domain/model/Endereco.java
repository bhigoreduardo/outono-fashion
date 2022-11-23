package com.outonofashion.domain.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.outonofashion.domain.model.id.EnderecoId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Endereco {

	@EmbeddedId
	private EnderecoId id;

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

	@Column(nullable = false, length = 2)
	private String enderecoUf;

	@Column(nullable = false, length = 120)
	private String enderecoCidade;

	@MapsId("usuarioId")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_endereco_usuario"))
	private Usuario usuario;

	public void active() {
		setEnderecoAtivo(true);
	}

	public void inactive() {
		setEnderecoAtivo(false);
	}

}
