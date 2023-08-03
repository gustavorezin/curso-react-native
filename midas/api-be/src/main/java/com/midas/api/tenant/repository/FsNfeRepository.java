package com.midas.api.tenant.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfe;

@Repository
public interface FsNfeRepository extends JpaRepository<FsNfe, Long> {
	@Query(value = "SELECT * FROM fs_nfe AS c WHERE c.nnf = ?1 AND c.cpfcnpjemit = ?2", nativeQuery = true)
	FsNfe findByNnfAndCpfcnpjemit(String nnf, String cpfcnpjemit);

	@Query(value = "SELECT * FROM fs_nfe AS c WHERE c.id = ?1 AND c.cdpessoaemp_id = ?2", nativeQuery = true)
	Optional<FsNfe> findByIdAndCdpessoaemp(Long id, Long cdpessoaemp);

	@Query(value = "SELECT * FROM fs_nfe AS c WHERE c.cdpessoaemp_id = ?1 AND c.serie = ?2 AND c.modelo = ?3 AND c.tpamb = ?4 AND c.tipo = ?5 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	FsNfe ultimaByIdAndCdpessoaempSerieModelo(Long cdpessoaemp, Integer serie, Integer modelo, String ambiente,
			String tipo);

	@Query(value = "SELECT * FROM fs_nfe AS n JOIN fs_nfeitem AS it ON n.id=it.fsnfe_id WHERE it.idprod = ?1 AND n.id != ?2 "
			+ "AND n.tipo = 'R' AND (n.status = 100 OR n.status = 150) GROUP BY n.id,n.cdpessoaemp_id "
			+ "ORDER BY n.dhemi DESC LIMIT 10", nativeQuery = true)
	List<FsNfe> findAllByIdprod(Long idprod, Long idfsnfe);

	@Query("SELECT c FROM FsNfe c WHERE c.cpfcnpjemit = ?1 AND c.tipo = ?2 AND c.st_fornec = ?3")
	List<FsNfe> findAllByIdCnpjTipoStFornec(String cpfcnpjemit, String tipo, String st_fornec);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfe f SET f.st_est = ?1 WHERE f.id = ?2")
	void entradaEstSt(String st, Long nfe);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfe f SET f.st_cob = ?1 WHERE f.id = ?2")
	void entradaCobSt(String st, Long nfe);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfe f SET f.st_custos = ?1 WHERE f.id = ?2")
	void entradaCustoSt(String st, Long nfe);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfe f SET f.xml = ?1, f.status = ?2, f.nprot = ?3 WHERE f.id = ?4")
	void updateXMLNFeStatus(String xml, Integer status, String nprot, Long nfe);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfe f SET f.numdoc = ?1, f.lcdoc = ?2, f.tpdoc = ?3 WHERE f.id = ?4")
	void updateDocFiscal(Long numdoc, Long lcdoc, String tpdoc, Long nfe);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfe f SET f.num_mdfe = ?1, f.doc_mdfe = ?2 WHERE f.id = ?3")
	void updateDocMdfe(Integer nmdf, Long idfiscal, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfe f SET f.num_mdfe = ?1, f.doc_mdfe = ?2 WHERE f.doc_mdfe = ?3")
	void updateDocMdfeDoc(Integer nmdf, Long idfiscal, Long docfiscal);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfe f SET f.dhsaient = ?1 WHERE f.id = ?2")
	void updateDataEntSai(Date dhsaient, Long id);

	@Query("SELECT COUNT(c) FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND (c.st_fornec = 'N' OR c.st_cob = 'N' OR c.st_est = 'N' OR c.st_custos = 'N')")
	Integer verificaNfeStImp();

	@Query("SELECT COUNT(c) FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND (c.st_fornec = 'N' OR c.st_cob = 'N' OR c.st_est = 'N' OR c.st_custos = 'N')")
	Integer verificaNfeStImpImpLocal(Long id);

	@Query("SELECT COUNT(c) FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND "
			+ "c.dhsaient BETWEEN ?2 AND ?3 AND c.tipo = ?4 AND (c.st_fornec = 'N' OR c.st_cob = 'N' OR c.st_est = 'N' OR c.st_custos = 'N')")
	Integer verificaNfeStImpImpLocalDataEntSaiTipo(Long id, Date ini, Date fim, String tipo);

	@Query("SELECT c FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND (c.st_fornec = 'N' OR c.st_cob = 'N' OR c.st_est = 'N' OR c.st_custos = 'N')")
	List<FsNfe> listaNfetImp();

	@Query("SELECT c FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND (c.st_fornec = 'N' OR c.st_cob = 'N' OR c.st_est = 'N' OR c.st_custos = 'N')")
	List<FsNfe> listaNfeStImpLocal(Long id);

	@Query("SELECT COUNT(c) FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.tipo = ?1 AND c.status = ?2")
	Integer verificaNfeStTipo(String tipo, Integer status);

	@Query("SELECT c FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.tipo = ?1 AND c.status = ?2")
	List<FsNfe> listaNfeStTipo(String tipo, Integer status);

	@Query("SELECT COUNT(c) FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.tipo = ?2 AND c.status = ?3")
	Integer verificaNfeStTipoLocal(Long local, String tipo, Integer status);

	@Query("SELECT c FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.tipo = ?2 AND c.status = ?3")
	List<FsNfe> listaNfeStTipoLocal(Long local, String tipo, Integer status);

	@Query("SELECT c FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.dhsaient BETWEEN ?2 AND ?3 ORDER BY c.dhsaient ASC")
	List<FsNfe> listaNfeEntsai(Long local, java.util.Date dataini, java.util.Date datafim);

	@Query("SELECT c FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.dhsaient BETWEEN ?2 AND ?3 "
			+ " GROUP BY c.fsnfepartdest.cpfcnpj ORDER BY c.dhsaient ASC")
	List<FsNfe> listaNfeEntsaiGbCNPJ(Long local, java.util.Date dataini, java.util.Date datafim);

	@Query("SELECT SUM(c.fsnfetoticms.vnf) FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND "
			+ "c.dhsaient BETWEEN ?2 AND ?3 AND c.tipo = ?4 AND (c.status = ?5 OR c.status = ?6)")
	BigDecimal faturaMesAnoLocalPeriodo(Long local, Date ini, Date fim, String tipo, Integer status, Integer status2);

	@Query("SELECT SUM(c.fsnfetoticms.vnf) FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.dhsaient BETWEEN ?1 AND ?2 "
			+ "AND c.tipo = ?3 AND (c.status = ?4 OR c.status = ?5)")
	BigDecimal faturaMesAnoPeriodo(Date dataini, Date datafim, String tipo, Integer status, Integer status2);

	@Query("SELECT c FROM FsNfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.dhsaient BETWEEN ?2 AND ?3 "
			+ "AND c.modelo = ?4 AND c.tipo = ?5 AND (c.status = ?6 OR c.status = ?7) ORDER BY c.dhsaient ASC")
	List<FsNfe> listaNfeEntsaiTipoStatus(Long local, Date dataini, Date datafim, Integer modelo, String tipo,
			Integer status1, Integer status2);
}
