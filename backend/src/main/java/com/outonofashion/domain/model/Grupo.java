package com.outonofashion.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
public class Grupo {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 40, unique = true)
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "grupo_permissao",
			joinColumns = @JoinColumn(name = "grupod_id", foreignKey = @ForeignKey(name = "fk_grupo_id")),
			inverseJoinColumns = @JoinColumn(name = "permissao_id", foreignKey = @ForeignKey(name = "fk_permissao_id")))
	private Set<Permissao> permissoes = new HashSet<>();
	
	public boolean addPermissao(Permissao permissao) {
		return getPermissoes().add(permissao);
	}
	
	public boolean removePermissao(Permissao permissao) {
		return getPermissoes().remove(permissao);
	}

}
