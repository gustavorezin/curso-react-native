package com.midas.api.tenant.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.FsNfeItem;

@Repository
public interface FsNfeItemRepository extends JpaRepository<FsNfeItem, Long> {
	@Query(value = "SELECT * FROM fs_nfe AS n JOIN fs_nfeitem AS it ON n.id=it.fsnfe_id JOIN "
			+ "fs_nfepart AS p ON n.fsnfepartemit_id=p.id WHERE p.cpfcnpj = ?1 AND it.cprod = ?2 "
			+ "ORDER BY it.id DESC LIMIT 1", nativeQuery = true)
	FsNfeItem verificaItemEntFornec(String cnpjfornec, String codnofornec);

	@Query("SELECT n FROM FsNfeItem n WHERE n.fsnfe.cdpessoaemp.status = 'ATIVO' AND n.fsnfe.cdpessoaemp.id = ?1 AND "
			+ "n.fsnfe.dhsaient BETWEEN ?2 AND ?3 GROUP BY n.cfop ORDER BY n.cfop ASC")
	List<FsNfeItem> itemFsNfeEntSaiCFOPGroup(Long local, Date dataini, Date datafim);

	@Query("SELECT n FROM FsNfeItem n WHERE n.fsnfe.cdpessoaemp.status = 'ATIVO' AND n.fsnfe.cdpessoaemp.id = ?1 AND "
			+ "n.fsnfe.id = ?2 GROUP BY n.cfop,n.fsnfeitemicms.cst,n.fsnfeitemicms.picms ORDER BY n.cfop ASC")
	List<FsNfeItem> itemFsNfeEntSaiCFOPCSTALIQGroup(Long local, Long idnfe);

	@Query(value = "SELECT COUNT(id) FROM fs_nfeitem AS n WHERE n.fsnfe_id = ?1 AND "
			+ "(n.idprod IS NULL OR n.cfopconv IS NULL)", nativeQuery = true)
	Integer getCountFsnfeitemSemVinculo(Long idfsnfe);
	
	@Query(value = "SELECT COUNT(n.id) FROM fs_nfeitem AS n WHERE n.fsnfe_id = ?1", nativeQuery = true)
	Integer getCountFsnfeitem(Long idfsnfe);

	@Query(value = "SELECT * FROM fs_nfeitem AS it JOIN fs_nfe AS n ON n.id=it.fsnfe_id WHERE n.cdpessoaemp_id = ?1 AND n.dhsaient BETWEEN ?2 AND ?3 "
			+ "GROUP BY it.cprod ORDER BY it.cprod ASC", nativeQuery = true)
	List<FsNfeItem> listaItensPorDataNfe(Long local, Date ini, Date fim);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfeItem l SET l.cdnfecfg = ?1 WHERE l.fsnfe.id = ?2")
	void updateCdNfeCfg(CdNfeCfg nfecfg, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfeItem l SET l.vtottrib = ?1, l.vfrete = ?2, l.vibpt_nacional = ?3, l.vibpt_importado = ?4, l.vibpt_estadual = ?5,"
			+ "l.vibpt_municipal = ?6 WHERE l.id = ?7")
	void updateVtotTrib(BigDecimal vTribs, BigDecimal vfrete, BigDecimal vibptnac, BigDecimal vibptimp,
			BigDecimal vibptest, BigDecimal vibptmun, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfeItem l SET l.cfopconv = ?1 WHERE l.fsnfe.id = ?2")
	void updateCFOPs(String cfop, Long id);
}
