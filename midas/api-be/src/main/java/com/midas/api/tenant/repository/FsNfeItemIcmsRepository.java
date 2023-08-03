package com.midas.api.tenant.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfeItemIcms;

@Repository
public interface FsNfeItemIcmsRepository extends JpaRepository<FsNfeItemIcms, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE FsNfeItemIcms f SET f.cst = ?1, f.modbc = ?2, f.predbc = ?3, f.vbc = ?4, f.picms = ?5, f.vicms = ?6,"
			+ "f.modbcst = ?7, f.pmvast = ?8, f.predbcst = ?9, f.vbcst = ?10, f.picmsst = ?11, f.vicmsst = ?12, "
			+ "f.pcredsn = ?13, f.vcredicmssn = ?14, f.vbcfcpst = ?15, f.pfcpst = ?16, f.vfcpst = ?17, f.vfcpdif = ?18, f.vicmsdif = ?19,"
			+ "f.vicmsdifremet = ?20 WHERE f.id = ?21")
	void updateTributos(String cst, Integer icms_modbc, BigDecimal icms_redbc, BigDecimal vbcicms, BigDecimal icms_aliq,
			BigDecimal vicms, Integer icmsst_modbc, BigDecimal icmsst_mva, BigDecimal icmsst_redbc,
			BigDecimal vbcicmsst, BigDecimal icmsst_aliq, BigDecimal vicmsst, BigDecimal aliq, BigDecimal vcredsn,
			BigDecimal vbcfcpst, BigDecimal icmsst_aliqfcp, BigDecimal vfcpst, BigDecimal vfcpdif, BigDecimal vicmsdif,
			BigDecimal vicmsdifremet, Long id);

	@Modifying
	@Transactional
	@Query("DELETE FROM FsNfeItemIcms c WHERE c.id = ?1")
	void deleteItemIcms(Long id);
}
