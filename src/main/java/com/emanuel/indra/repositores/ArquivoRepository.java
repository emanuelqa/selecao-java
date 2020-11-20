package com.emanuel.indra.repositores;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emanuel.indra.model.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long>{
	
	@Query(value = "SELECT avg(a.valorVenda) FROM Arquivo a where a.municipio = ?1")
	Optional<BigDecimal> mediaVendaMunicipio(String municipio);
	
	@Query(value = "SELECT avg(a.valorCompra) FROM Arquivo a where a.municipio = ?1")
	Optional<BigDecimal> mediaCompraMunicipio(String municipio);
	
	@Query(value = "SELECT avg(a.valorVenda) FROM Arquivo a where a.bandeira = ?1")
	Optional<BigDecimal> mediaVendaByBandeira(String bandeira);
	
	@Query(value = "SELECT avg(a.valorCompra) FROM Arquivo a where a.bandeira = ?1")
	Optional<BigDecimal> mediaCompraByBandeira(String bandeira);
	
	List<Arquivo> findByEstado(String estado);
	
}
