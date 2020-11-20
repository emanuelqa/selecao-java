package com.emanuel.indra.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.emanuel.indra.model.Arquivo;

import lombok.Data;

@Data
public class ArquivoDTO {
	
	private String regiao;
	private String estado;
	private String municipio;
	private String revenda;
	private String cnpj;
	private String produto;
	private LocalDate dataColeta;
	private BigDecimal valorVenda;
	private BigDecimal valorCompra;
	private String unidadeMedida;
	private String bandeira;
	
	public ArquivoDTO() {}
	
	public ArquivoDTO(Arquivo arquivo) {
		this.regiao = arquivo.getRegiao();
		this.estado = arquivo.getEstado();
		this.municipio = arquivo.getMunicipio();
		this.revenda = arquivo.getRevenda();
		this.cnpj = arquivo.getCnpj();
		this.produto = arquivo.getProduto();
		this.dataColeta = arquivo.getDataColeta();
		this.valorVenda = arquivo.getValorVenda();
		this.valorCompra = arquivo.getValorCompra();
		this.unidadeMedida = arquivo.getUnidadeMedida();
		this.bandeira = arquivo.getBandeira();
	}
	public static List<ArquivoDTO> converter(List<Arquivo> arquivos) {
		return arquivos.stream().map(ArquivoDTO::new).collect(Collectors.toList());
	}
	
	

}
