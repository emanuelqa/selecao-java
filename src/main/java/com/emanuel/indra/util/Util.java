package com.emanuel.indra.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.emanuel.indra.model.Arquivo;

public class Util {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static List<Arquivo> lerArquivoCSV(InputStream is) {
		String linha;
		boolean primeiraLinha = true;
		List<Arquivo> linhasArquivo = new ArrayList();
		

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-16"));
			while ((linha = br.readLine()) != null) {
				if (!primeiraLinha) {
					String linhaSplit[] = linha.split("\t");
					if(!(linhaSplit == null || linhaSplit.length < 11))
							linhasArquivo.add(popularArquivo(linhaSplit));
				}
				primeiraLinha = false;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linhasArquivo;

	}
	
	public static Arquivo popularArquivo(String linhaSplit[]) {
		Arquivo arquivo = new Arquivo();
		arquivo.setRegiao(isBlank(linhaSplit[0]) ? "" : linhaSplit[0]);
		arquivo.setEstado(isBlank(linhaSplit[1]) ? "" : linhaSplit[1]);
		arquivo.setMunicipio(isBlank(linhaSplit[2]) ? "" : linhaSplit[2]);
		arquivo.setRevenda(isBlank(linhaSplit[3]) ? "" : linhaSplit[3]);
		arquivo.setCnpj(isBlank(linhaSplit[4]) ? "" : linhaSplit[4]);
		arquivo.setProduto(isBlank(linhaSplit[5]) ? "" : linhaSplit[5]);
		arquivo.setDataColeta(isBlank(linhaSplit[6]) ? null : LocalDate.parse(linhaSplit[6].replace(" ", ""), formatter));
		arquivo.setValorVenda(
				isBlank(linhaSplit[7]) ? null : new BigDecimal(linhaSplit[7].replace(",", ".")));
		arquivo.setValorCompra(
				isBlank(linhaSplit[8]) ? null : new BigDecimal(linhaSplit[8].replace(",", ".")));
		arquivo.setUnidadeMedida(isBlank(linhaSplit[9]) ? "" : linhaSplit[9]);
		arquivo.setBandeira(isBlank(linhaSplit[10]) ? "" : linhaSplit[10]);
		return arquivo;
	}
	
	public static boolean isBlank(String string) {
		return string == null || string.isBlank() ? true : false;
	}

}
