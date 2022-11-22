package com.outonofashion.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class ItemPedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private BigDecimal precoUnitario;
	
	private BigDecimal oferta;

	@Column(nullable = false)
	private BigDecimal precoTotal;

	@Column(length = 120)
	private String observacao;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_itempedido_pedido"))
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_itempedido_produto"))
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_itempedido_tamanho"))
	private Tamanho tamanho;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_itempedido_cor"))
	private Cor cor;

	public void calculatePrecoTotal() {
		BigDecimal precoUnitario = this.getPrecoUnitario();
		Integer quantidade = this.getQuantidade();

		if (precoUnitario == null) {
			precoUnitario = BigDecimal.ZERO;
		}

		if (quantidade == null) {
			quantidade = 0;
		}
		
		if (oferta != null) {
			setPrecoTotal(precoUnitario.subtract(oferta).multiply(new BigDecimal(quantidade)));
		} else {
			setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
		}

	}

}
