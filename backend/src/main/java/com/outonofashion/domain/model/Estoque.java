package com.outonofashion.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.outonofashion.domain.model.id.EstoqueId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Estoque {

	@EmbeddedId
	private EstoqueId id;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private BigDecimal preco;

	private BigDecimal oferta;

	@MapsId("produtoId")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_estoque_produto"))
	private Produto produto;

	@MapsId("corId")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_estoque_cor"))
	private Cor cor;

	@MapsId("tamanhoId")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_estoque_tamanho"))
	private Tamanho tamanho;

}
