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
public class EnderecoId implements Serializable {

	private static final long serialVersionUID = -982009941858908729L;
	
	private String enderecoApelido;
	private Long usuarioId;
	
}
