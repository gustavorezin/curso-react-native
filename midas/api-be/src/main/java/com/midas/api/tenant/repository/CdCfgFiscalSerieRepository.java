package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCfgFiscalSerie;

@Repository
public interface CdCfgFiscalSerieRepository extends JpaRepository<CdCfgFiscalSerie, Integer> {
	@Query("SELECT c FROM CdCfgFiscalSerie c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<CdCfgFiscalSerie> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query("SELECT c FROM CdCfgFiscalSerie c WHERE c.cdpessoaemp.id = ?1")
	List<CdCfgFiscalSerie> findByCdpessoaemp(Long cdpessoaemp);

	@Query("SELECT c FROM CdCfgFiscalSerie c WHERE c.cdpessoaemp.id = ?1 AND c.modelo = ?2")
	CdCfgFiscalSerie getByCdpessoaemp(Long id, String modelo);

	@Query("SELECT c FROM CdCfgFiscalSerie c WHERE c.modelo = ?1")
	List<CdCfgFiscalSerie> findAllByModelo(String modelo);

	@Query("SELECT c FROM CdCfgFiscalSerie c WHERE c.modelo = ?1 OR c.modelo = ?2")
	List<CdCfgFiscalSerie> findAllByModeloNFe(String modelo, String modelo2);

	@Query("SELECT c FROM CdCfgFiscalSerie c WHERE c.cdpessoaemp.id = ?1 AND c.modelo = ?2")
	List<CdCfgFiscalSerie> findByCdpessoaempModelo(Long cdpessoaemp, String modelo);

	@Query("SELECT c FROM CdCfgFiscalSerie c WHERE c.cdpessoaemp.id = ?1 AND (c.modelo = ?2 OR c.modelo = ?3)")
	List<CdCfgFiscalSerie> findByCdpessoaempModeloNFe(Long cdpessoaemp, String modelo, String modelo2);
}
