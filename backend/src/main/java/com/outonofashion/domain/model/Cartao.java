package com.outonofashion.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Cartao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 16)
	private String numero;

	@Column(nullable = false, length = 120)
	private String nomeImpresso;

	@Column(nullable = false, length = 2)
	private String mesValidade;

	@Column(nullable = false, length = 2)
	private String anoValidade;

	@Column(nullable = false, length = 3)
	private String cvv;
	
	@ManyToMany
	@JoinTable(name = "usuario_cartao",
			  joinColumns = @JoinColumn(name = "cartao_id"),
			  inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Usuario> usuarios = new ArrayList<>();

}
