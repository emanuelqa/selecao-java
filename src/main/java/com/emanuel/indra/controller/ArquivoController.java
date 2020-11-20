package com.emanuel.indra.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emanuel.indra.controller.dto.ArquivoDTO;
import com.emanuel.indra.controller.dto.MediaPrecoDTO;
import com.emanuel.indra.controller.dto.ValorCompraVendaDTO;
import com.emanuel.indra.service.ArquivoService;

@RestController
@RequestMapping("/arquivo")
public class ArquivoController {
	
	@Autowired
	private ArquivoService arquivoService;
	
	@PostMapping("/upload")
	@Transactional
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
		arquivoService.lerArquivo(file);
		return ResponseEntity.ok().build();
	}
	
//	Implementar recurso que retorne a média de preço de combustível com base no nome do município

	@GetMapping("/mediaPrecoCombustivel/{municipio}")
	public ResponseEntity<MediaPrecoDTO> mediaPrecoCombustivel(@PathVariable String municipio) {
		if (municipio == null || municipio.isBlank()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(new MediaPrecoDTO(arquivoService.mediaPrecoCombustivel(municipio)));
		}
	}
	
//	Implementar recurso que retorne todas as informações importadas por sigla da região
	@GetMapping("/listarPorEstado/{estado}")
	public ResponseEntity<List<ArquivoDTO>> listarPorEstado(@PathVariable String estado) {
		if (estado == null || estado.isBlank()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(ArquivoDTO.converter(arquivoService.listarPorEstado(estado)));
		}
	}
	
//	Implementar recurso que retorne o valor médio do valor da compra e do valor da venda por município
	@GetMapping("/mediaValorCompraVenda/{municipio}")
	public ResponseEntity<ValorCompraVendaDTO> mediaValorCompraVenda(@PathVariable String municipio) {
		if (municipio == null || municipio.isBlank()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(arquivoService.mediaValorCompraVenda(municipio));
		}
	}
	
//	Implementar recurso que retorne o valor médio do valor da compra e do valor da venda por bandeira
	@GetMapping("/mediaValorCompraVendaBandeira/{bandeira}")
	public ResponseEntity<ValorCompraVendaDTO> mediaValorCompraVendaBandeira(@PathVariable String bandeira) {
		if (bandeira == null || bandeira.isBlank()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(arquivoService.mediaValorCompraVendaBandeira(bandeira));
		}
	}

}
