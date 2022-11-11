package com.outonofashion.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Telefone {
	
	@EqualsAndHashCode.Include
	@Id
	@Column(length = 11)
	private String numero;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_telefone_usuario"))
	private Usuario usuario;

}
