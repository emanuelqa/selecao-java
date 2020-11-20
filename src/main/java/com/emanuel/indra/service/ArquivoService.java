package com.emanuel.indra.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emanuel.indra.controller.dto.ValorCompraVendaDTO;
import com.emanuel.indra.model.Arquivo;
import com.emanuel.indra.repositores.ArquivoRepository;
import com.emanuel.indra.util.Util;

@Service
public class ArquivoService {
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	public void lerArquivo(MultipartFile file) {
		List<Arquivo> arquivos;
		try {
			arquivos = Util.lerArquivoCSV(file.getInputStream());
			arquivoRepository.saveAll(arquivos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BigDecimal mediaPrecoCombustivel(String municipio) {
		Optional<BigDecimal> vendaOptional = arquivoRepository.mediaVendaMunicipio(municipio);
		return vendaOptional.isPresent() ? vendaOptional.get().setScale(2, RoundingMode.HALF_EVEN) : new BigDecimal(0);
	}

	public List<Arquivo> listarPorEstado(String estado) {
		return arquivoRepository.findByEstado(estado);
	}

	public ValorCompraVendaDTO mediaValorCompraVenda(String municipio) {
		Optional<BigDecimal> vendaOptional = arquivoRepository.mediaVendaMunicipio(municipio);
		Optional<BigDecimal> compraOptional = arquivoRepository.mediaCompraMunicipio(municipio);
		
		BigDecimal venda = vendaOptional.isPresent() ? vendaOptional.get().setScale(2, RoundingMode.HALF_EVEN) : new BigDecimal(0);
		BigDecimal compra = compraOptional.isPresent() ? compraOptional.get().setScale(2, RoundingMode.HALF_EVEN) : new BigDecimal(0);
		return new ValorCompraVendaDTO(venda, compra);
	}

	public ValorCompraVendaDTO mediaValorCompraVendaBandeira(String bandeira) {
		Optional<BigDecimal> vendaOptional = arquivoRepository.mediaVendaByBandeira(bandeira);
		Optional<BigDecimal> compraOptional = arquivoRepository.mediaCompraByBandeira(bandeira);
		
		BigDecimal venda = vendaOptional.isPresent() ? vendaOptional.get().setScale(2, RoundingMode.HALF_EVEN) : new BigDecimal(0);
		BigDecimal compra = compraOptional.isPresent() ? compraOptional.get().setScale(2, RoundingMode.HALF_EVEN) : new BigDecimal(0);
		return new ValorCompraVendaDTO(venda, compra);
	}

}
