package com.emanuel.indra.config.validacao;

import lombok.Data;

@Data
public class ErroForm {

	private String campo;
	private String erro;
	
	public ErroForm(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
}
