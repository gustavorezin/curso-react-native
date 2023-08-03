package com.midas.api.tenant.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.LcDocItemCot;

@Repository
public interface LcDocItemCotRepository extends JpaRepository<LcDocItemCot, Long> {
	@Query("SELECT c FROM LcDocItemCot c WHERE c.lcdocitem.lcdoc.id = ?1 GROUP BY c.cdpessoacot")
	List<LcDocItemCot> listaPorFonecLcdoc(Long id);

	@Query("SELECT c FROM LcDocItemCot c WHERE c.lcdocitem.lcdoc.id = ?1 AND c.mpreco = 'S' GROUP BY c.cdpessoacot")
	List<LcDocItemCot> listaPorFonecLcdocMP(Long id);

	@Query("SELECT c FROM LcDocItemCot c WHERE c.lcdocitem.lcdoc.id = ?1 GROUP BY c.lcdocitem")
	List<LcDocItemCot> listaPorItemLcdoc(Long id);

	@Query("SELECT COUNT(c) FROM LcDocItemCot c WHERE c.lcdocitem.lcdoc.id = ?1 AND c.cdpessoacot.id = ?2")
	Integer qtdFonecLcdoc(Long id, Long idfornec);

	@Query(value = "SELECT * FROM lc_docitem_cot AS c WHERE c.lcdocitem_id = ?1 ORDER BY c.vtot ASC LIMIT 1", nativeQuery = true)
	LcDocItemCot verificaMPreco(Long id);

	@Query("SELECT c FROM LcDocItemCot c WHERE c.cdpessoacot.id = ?1 AND c.lcdocitem.id = ?2")
	LcDocItemCot findByPessoaAndItem(Long pessoacot, Long iditem);

	@Query("SELECT c FROM LcDocItemCot c WHERE c.mpreco = 'S' AND c.cdpessoacot.id = ?1 AND c.lcdocitem.id = ?2")
	LcDocItemCot findByMelhorPrecoAndPessoaAndItem(Long pessoacot, Long iditem);

	@Modifying
	@Transactional
	@Query("UPDATE LcDocItemCot c SET c.mpreco = 'S' WHERE c.id = ?1 AND c.lcdocitem.id = ?2")
	void updateMpreco(Long id, Long iditem);

	@Modifying
	@Transactional
	@Query("UPDATE LcDocItemCot c SET c.mpreco = 'N' WHERE c.id != ?1 AND c.lcdocitem.id = ?2")
	void updatePpreco(Long id, Long iditem);

	@Modifying
	@Transactional
	@Query("UPDATE LcDocItemCot c SET c.qtd = ?1, c.vuni = ?2, c.vsub = ?3, c.pdesc = ?4, c.vdesc = ?5, c.vtot = ?6 "
			+ "WHERE c.lcdocitem.id = ?7 AND c.cdpessoacot.id = ?8 ")
	void updateItemCotFor(BigDecimal qtdOferta, BigDecimal vUni, BigDecimal vSub, BigDecimal pDesc, BigDecimal vDesc,
			BigDecimal vTot, Long iditem, Long idfornec);
}
