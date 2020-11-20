package com.emanuel.indra.controller.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MediaPrecoDTO {
	
	private BigDecimal preco;

	public MediaPrecoDTO(BigDecimal preco) {
		this.preco = preco;
	}

	
}
