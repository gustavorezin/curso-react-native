package com.midas.api.tenant.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.LcDocItem;

@Repository
public interface LcDocItemRepository extends JpaRepository<LcDocItem, Long> {
	@Query("SELECT c FROM LcDocItem c WHERE c.lcdoc.id = ?1 GROUP BY c.cdproduto.id")
	List<LcDocItem> listaItensGroupById(Long id);

	@Query("SELECT c FROM LcDocItem c WHERE c.lcdoc.id = ?1 ORDER BY c.cdproduto.id")
	List<LcDocItem> listaItensOrderByItem(Long id);

	@Query("SELECT COUNT(c) FROM LcDocItem c WHERE c.lcdoc.id = ?1 AND c.cdproduto.tipo = 'PRODUTO'")
	Integer verificaPossuiItemTipo(Long id);

	@Query("SELECT COUNT(c) FROM LcDocItem c WHERE c.lcdoc.id = ?1 AND c.cdproduto.tipo != 'PRODUTO'")
	Integer verificaPossuiItemTipoServico(Long id);

	@Modifying
	@Transactional
	@Query("UPDATE LcDocItem l SET l.cdnfecfg = ?1 WHERE l.lcdoc.id = ?2")
	void updateCdNfeCfg(CdNfeCfg nfecfg, Long id2);

	@Modifying
	@Transactional
	@Query("UPDATE LcDocItem l SET l.vfrete = ?1 WHERE l.lcdoc.id = ?2")
	void updateVOutros(BigDecimal vfrete, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE LcDocItem l SET l.vtottrib = l.vtot + l.vfrete WHERE l.lcdoc.id = ?1")
	void updateVtotTrib(Long id);

	@Modifying
	@Transactional
	@Query("UPDATE LcDocItem l SET l.vbccredsn = ?1, l.pcredsn = ?2, l.vcredsn = ?3, l.vbcpis = ?4, l.ppis = ?5, l.vpis = ?6, "
			+ "l.vbcpisst = ?7, l.ppisst = ?8, l.vpisst = ?9, l.vbccofins = ?10, l.pcofins = ?11, l.vcofins = ?12, l.vbccofinsst = ?13, "
			+ "l.pcofinsst = ?14, l.vcofinsst = ?15, l.vbcipi = ?16, l.pipi = ?17, l.vipi = ?18, l.vbcicms = ?19, l.picms = ?20, "
			+ "l.vicms = ?21, l.vbcicmsst = ?22, l.picmsst = ?23, l.vicmsst = ?24, l.vfrete = ?25, l.vbcfcpst = ?26, l.pfcpst = ?27, "
			+ "l.vfcpst = ?28, l.pfcpufdest = ?29, l.picmsufdest = ?30, l.picmsinter = ?31, l.picmsinterpart = ?32, l.vfcpufdest = ?33,"
			+ "l.vicmsufdest = ?34, l.vicmsufremet = ?35, l.vtottrib = ?36, l.vtribcob = ?37, l.vibpt_nacional = ?38, l.vibpt_importado = ?39,"
			+ "l.vibpt_estadual = ?40, l.vibpt_municipal = ?41, l.vbciss = ?42, l.piss = ?43, l.viss = ?44 WHERE l.id = ?45")
	void updateTributos(BigDecimal vbccredsn, BigDecimal pcredsn, BigDecimal vcredsn, BigDecimal vbcpis,
			BigDecimal ppis, BigDecimal vpis, BigDecimal vbcpisst, BigDecimal ppisst, BigDecimal vpisst,
			BigDecimal vbccofins, BigDecimal pcofins, BigDecimal vcofins, BigDecimal vbccofinsst, BigDecimal pcofinsst,
			BigDecimal vcofinsst, BigDecimal vbcipi, BigDecimal pipi, BigDecimal vipi, BigDecimal vbcicms,
			BigDecimal picms, BigDecimal vicms, BigDecimal vbcicmsst, BigDecimal picmsst, BigDecimal vicmsst,
			BigDecimal vfrete, BigDecimal vbcfcpst, BigDecimal pfcpst, BigDecimal vfcpst, BigDecimal pfcpufdest,
			BigDecimal picmsufdest, BigDecimal picmsinter, BigDecimal picmsinterpart, BigDecimal vfcpufdest,
			BigDecimal vicmsufdest, BigDecimal vicmsdifremet, BigDecimal vtottrib, BigDecimal vtribcob,
			BigDecimal vibptnac, BigDecimal vibptimp, BigDecimal vibptest, BigDecimal vibptmun, BigDecimal vbciss,
			BigDecimal piss, BigDecimal viss, Long id);
}
