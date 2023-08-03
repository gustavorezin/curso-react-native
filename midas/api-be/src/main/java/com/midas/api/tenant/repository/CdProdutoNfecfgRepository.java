package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.CdProdutoNfecfg;

@Repository
public interface CdProdutoNfecfgRepository extends JpaRepository<CdProdutoNfecfg, Integer> {
	CdProdutoNfecfg findByCdprodutoAndCdnfecfg(CdProduto cdproduto, CdNfeCfg cdnfecfg);

	@Query("SELECT c FROM CdProdutoNfecfg c WHERE c.cdnfecfg.cdpessoaemp.id = ?1 AND c.cdproduto.id = ?2 AND "
			+ "c.cdnfecfg.crtdest = ?3 AND c.cdnfecfg.cdestadoaplic.id = ?4")
	CdProdutoNfecfg getCdNfeCfgDestinoSimilar(Long local, Long idprod, Integer crtdest, Integer coduf);

	@Query("SELECT MAX(c) FROM CdProdutoNfecfg c WHERE c.cdnfecfg.cdpessoaemp.id = ?1 AND c.cdnfecfg.tipoop = ?2 AND "
			+ "c.cdnfecfg.crtdest = ?3 AND c.cdnfecfg.cdestadoaplic.id = ?4 AND c.cdproduto.id = ?5 AND c.cdnfecfg.status = 'ATIVO'")
	CdProdutoNfecfg getCdNfeCfgDestinoItemAtivo(Long local, String tpop, Integer crtdest, Integer coduf, Long idprod);

	@Query("SELECT c FROM CdProdutoNfecfg c WHERE c.cdproduto.id = ?1 ORDER BY c.cdnfecfg.cfop ASC")
	List<CdProdutoNfecfg> findAllByCdprodutoID(Long prod);
}
