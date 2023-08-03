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

import com.midas.api.tenant.entity.FsCte;

@Repository
public interface FsCteRepository extends JpaRepository<FsCte, Long> {
	@Query(value = "SELECT * FROM fs_cte AS c WHERE c.nct = ?1 AND c.cpfcnpjemit = ?2", nativeQuery = true)
	FsCte findByNctAndCpfcnpjemit(String nnf, String cpfcnpjemit);

	@Query(value = "SELECT * FROM fs_cte AS c WHERE c.id = ?1 AND c.cdpessoaemp_id = ?2", nativeQuery = true)
	Optional<FsCte> findByIdAndCdpessoaemp(Long id, Long cdpessoaemp);

	@Query(value = "SELECT * FROM fs_cte AS c WHERE c.cdpessoaemp_id = ?1 AND c.serie = ?2 AND c.tpamb = ?3 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	FsCte ultimaByCdpessoaempSerieTpamb(Long cdpessoaemp, Integer serie, String ambiente);

	@Query("SELECT SUM(c.fsctevprest.vtprest) FROM FsCte c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND "
			+ "c.dhsaient BETWEEN ?2 AND ?3 AND c.tipo = ?4 AND (c.status = ?5 OR c.status = ?6)")
	BigDecimal faturaMesAnoLocalPeriodo(Long local, Date dataini, Date datafim, String tipo, Integer status,
			Integer status2);

	@Query("SELECT SUM(c.fsctevprest.vtprest) FROM FsCte c WHERE c.cdpessoaemp.status = 'ATIVO' AND "
			+ "c.dhsaient BETWEEN ?1 AND ?2 AND c.tipo = ?3 AND (c.status = ?4 OR c.status = ?5)")
	BigDecimal faturaMesAnoPeriodo(Date dataini, Date datafim, String tipo, Integer status, Integer status2);

	@Query("SELECT c FROM FsCte c WHERE c.cpfcnpjemit = ?1 AND c.tipo = ?2 AND c.st_transp = ?3")
	List<FsCte> findAllByIdCnpjTipoStTransp(String cpfcnpjemit, String tipo, String st_transp);

	@Query("SELECT c FROM FsCte c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.dhsaient BETWEEN ?2 AND ?3 ORDER BY c.dhsaient ASC")
	List<FsCte> listaCteEntsai(Long local, java.util.Date dataini, java.util.Date datafim);

	@Modifying
	@Transactional
	@Query("UPDATE FsCte c SET c.st_cob = ?1 WHERE c.id = ?2")
	void entradaCobSt(String st, Long cte);
	
	@Modifying
	@Transactional
	@Query("UPDATE FsCte c SET c.num_mdfe = ?1, c.doc_mdfe = ?2 WHERE c.id = ?3")
	void updateDocMdfe(Integer nmdf, Long idfiscal, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FsCte c SET c.num_mdfe = ?1, c.doc_mdfe = ?2 WHERE c.doc_mdfe = ?3")
	void updateDocMdfeDoc(Integer nmdf, Long idfiscal, Long docfiscal);

	@Modifying
	@Transactional
	@Query("UPDATE FsCte c SET c.dhsaient = ?1, c.cfop = ?2 WHERE c.id = ?3")
	void updateDataEntSaiCfop(Date dhsaient, String cfop, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FsCte f SET f.xml = ?1, f.status = ?2, f.nprot = ?3, f.nrecibo = ?4 WHERE f.id = ?5")
	void updateXMLCTeStatus(String xml, Integer status, String nprot, String nrec, Long id);

	@Query("SELECT c FROM FsCte c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.dhsaient BETWEEN ?2 AND ?3 "
			+ "AND c.tipo = ?4 AND (c.status = ?5 OR c.status = ?6) ORDER BY c.dhsaient ASC")
	List<FsCte> listaCteEntsaiTipoStatus(Long local, Date ini, Date fim, String tipo, Integer status1, Integer status2);

	@Query("SELECT COUNT(c) FROM FsCte c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND "
			+ "c.dhsaient BETWEEN ?2 AND ?3 AND c.tipo = ?4 AND (c.st_cob = 'N' OR c.st_transp = 'N')")
	Integer verificaCteStImpImpLocalDataEntSaiTipo(Long id, Date ini, Date fim, String tipo);

	@Query("SELECT COUNT(c) FROM FsCte c WHERE c.cdpessoaemp.status = 'ATIVO' AND (c.st_cob = 'N' OR c.st_transp = 'N')")
	Integer verificaCteStImp();

	@Query("SELECT COUNT(c) FROM FsCte c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND (c.st_cob = 'N' OR c.st_transp = 'N')")
	Integer verificaCteStImpImpLocal(Long id);

	@Query("SELECT c FROM FsCte c WHERE c.cdpessoaemp.status = 'ATIVO' AND (c.st_cob = 'N' OR c.st_transp = 'N')")
	List<FsCte> listaCtetImp();

	@Query("SELECT c FROM FsCte c WHERE c.cdpessoaemp.status = 'ATIVO' AND (c.st_cob = 'N' OR c.st_transp = 'N')")
	List<FsCte> listaCteStImpLocal(Long id);
}
