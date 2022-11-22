package com.outonofashion.domain.model.id;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EstoqueId implements Serializable {

	private static final long serialVersionUID = -96922390495200225L;

	private Long produtoId;
	private Long corId;
	private Long tamanhoId;
	
}