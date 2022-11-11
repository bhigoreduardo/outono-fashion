package com.outonofashion.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outonofashion.domain.model.id.EstoqueId;

import lombok.Data;

@Entity
@Data
@IdClass(EstoqueId.class)
public class Estoque {

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private BigDecimal preco;

	private BigDecimal oferta;

	@JsonIgnore
	@Id
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_estoque_produto"))
	private Produto produto;

	@Id
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_estoque_cor"))
	private Cor cor;

	@Id
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_estoque_tamanho"))
	private Tamanho tamanho;

}
