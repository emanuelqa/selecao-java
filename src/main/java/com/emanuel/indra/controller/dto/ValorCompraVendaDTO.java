package com.emanuel.indra.controller.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ValorCompraVendaDTO {
	
	private BigDecimal valorVenda;
	private BigDecimal valoeCompra;

	public ValorCompraVendaDTO(BigDecimal venda, BigDecimal compra) {
		this.valorVenda = venda;
		this.valoeCompra = compra;
	}

}
