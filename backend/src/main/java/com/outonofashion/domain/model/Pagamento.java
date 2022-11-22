package com.outonofashion.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Pagamento {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30, unique = true)
	private String descricao;

	@Column(nullable = false)
	private Boolean ativo = true;
	
	@Column(nullable = false)
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;
	
	public void active() {
		setAtivo(true);
	}
	
	public void inactive() {
		setAtivo(false);
	}

}
