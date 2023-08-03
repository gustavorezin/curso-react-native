package com.midas.api.tenant.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfePag;

@Repository
public interface FsNfePagRepository extends JpaRepository<FsNfePag, Long> {
	@Query(value = "SELECT SUM(fp.vpag) FROM fs_nfepag AS fp LEFT JOIN fs_nfe AS f ON fp.fsnfe_id=f.id LEFT JOIN lc_doc AS l ON f.lcdoc=l.id "
			+ "WHERE f.cdpessoaemp_id = ?1 AND f.dhsaient BETWEEN ?2 AND ?3 AND l.cdcaixa_id = ?4 AND (f.status = 100 OR f.status = 150)", nativeQuery = true)
	BigDecimal sumNfeEntsaiPagsDocsCx(Long local, java.util.Date dataini, java.util.Date datafim, Integer cdcaixa);

	@Query(value = "SELECT SUM(fp.vpag) FROM fs_nfepag AS fp LEFT JOIN fs_nfe AS f ON fp.fsnfe_id=f.id LEFT JOIN lc_doc AS l ON f.lcdoc=l.id "
			+ "WHERE f.cdpessoaemp_id = ?1 AND f.dhsaient BETWEEN ?2 AND ?3 AND fp.tpag = ?4 AND l.cdcaixa_id = ?5 AND (f.status = 100 OR f.status = 150)", nativeQuery = true)
	BigDecimal sumNfeEntsaiPagsDocsCxTp(Long local, java.util.Date dataini, java.util.Date datafim, String tpag,
			Integer cdcaixa);
}
