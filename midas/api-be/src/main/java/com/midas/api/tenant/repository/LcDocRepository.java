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

import com.midas.api.tenant.entity.LcDoc;

@Repository
public interface LcDocRepository extends JpaRepository<LcDoc, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.fpag = ?1, l.cdcondpag.id = ?2, l.cdcaixa.id = ?3 WHERE l.id = ?4 ")
	void atualizaPagamento(String fpag, Integer cpag, Integer ccaixa, Long iddoc);

	@Query(value = "SELECT * FROM lc_doc AS c WHERE c.id = ?1 AND c.cdpessoaemp_id = ?2", nativeQuery = true)
	Optional<LcDoc> findByIdAndCdpessoaemp(Long id, Long cdpessoaemp);

	@Query("SELECT c FROM LcDoc c WHERE c.id = ?1 AND c.cdpessoavendedor.id = ?2")
	Optional<LcDoc> findByIdAndCdpessoavend(Long id, Long cdpessoavend);

	@Query(value = "SELECT * FROM lc_doc AS c WHERE c.cdpessoaemp_id = ?1 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	LcDoc ultimoLcDoc(Long emp);

	@Query(value = "SELECT * FROM lc_doc AS c WHERE c.tipo = ?1 AND c.cdpessoaemp_id = ?2 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	LcDoc ultimoLcDoc(String tipo, Long emp);

	@Query(value = "SELECT * FROM lc_doc AS c WHERE c.cdpessoapara_id = ?1 AND c.datafat >= ?2 "
			+ "AND c.status = '2' ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	LcDoc lcDocNC(Long para, Date datafat);

	@Query(value = "SELECT * FROM lc_doc AS c WHERE c.cdpessoaemp_id = ?1 AND c.cdpessoapara_id = ?2 AND c.datafat >= ?3 "
			+ "AND c.status = '2' ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	LcDoc lcDocNCLocal(Long emp, Long para, Date datafat);

	@Modifying
	@Transactional
	@Query(value = "UPDATE lc_doc AS l SET l.vtottrib = (SELECT SUM(it.vtottrib) FROM lc_docitem AS it WHERE it.lcdoc_id = ?1) WHERE l.id = ?1", nativeQuery = true)
	void updateVtotTrib(Long id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE lc_doc AS l SET l.vtottrib = l.vtottrib + l.vdesloca - l.vdescext  WHERE l.id = ?1", nativeQuery = true)
	void updateVtotTribDescExtraDesloca(Long id);

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.numnota = ?1, l.tpdocfiscal = ?2, l.docfiscal = ?3 WHERE l.id = ?4 ")
	void updateDocFiscal(Integer nnf, String modelo, Long idfiscal, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.numnota = ?1, l.tpdocfiscal = ?2, l.docfiscal = ?3 WHERE l.docfiscal = ?4 ")
	void updateDocFiscalNfe(Integer nnf, String modelo, Long idfiscal, Long docfiscal);// Repete mesmo...

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.numnota_nfse = ?1, l.docfiscal_nfse = ?2 WHERE l.id = ?3")
	void updateDocFiscalNfse(Integer nnfs, Long idfiscal, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.numnota_nfse = ?1, l.docfiscal_nfse = ?2 WHERE l.docfiscal_nfse = ?3")
	void updateDocFiscalNfseDocfiscal(Integer nnfs, Long idfiscal, Long docfiscal);

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.boldoc = ?1 WHERE l.id = ?2 ")
	void updateDocBoleto(String boldoc, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.adminconf = ?1 WHERE l.id = ?2 ")
	void updateDocAdminConf(String adminconf, Long id);

	@Query("SELECT SUM(c.vtottrib) FROM LcDoc c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.status = ?2 AND c.datafat "
			+ "BETWEEN ?3 AND ?4  AND c.tipo = ?5 AND (c.fpag != '90' AND c.fpag != '99')")
	BigDecimal faturaMesAnoLocalPeriodo(Long local, String status, Date ini, Date fim, String tpdoc);

	@Query("SELECT SUM(c.vtottrib) FROM LcDoc c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.cdpessoavendedor.id = ?2 "
			+ "AND c.status = ?3 AND c.datafat BETWEEN ?4 AND ?5  AND c.tipo = ?6 AND (c.fpag != '90' AND c.fpag != '99')")
	BigDecimal faturaMesAnoLocalPeriodoResp(Long local, Long respvend, String status, Date ini, Date fim, String tpdoc);

	@Query("SELECT SUM(c.vtottrib) FROM LcDoc c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.status = ?1 AND c.datafat BETWEEN ?2 AND ?3 "
			+ "AND c.tipo = ?4 AND (c.fpag != '90' AND c.fpag != '99')")
	BigDecimal faturaMesAnoPeriodo(String status, Date dataini, Date datafim, String tpdoc);

	@Query("SELECT SUM(c.vtottrib) FROM LcDoc c WHERE c.cdpessoavendedor.id = ?1 AND c.cdpessoaemp.status = 'ATIVO' AND c.status = ?2 AND "
			+ "c.datafat BETWEEN ?3 AND ?4 AND c.tipo = ?5 AND (c.fpag != '90' AND c.fpag != '99')")
	BigDecimal faturaMesAnoPeriodoResp(Long respvend, String status, Date dataini, Date datafim, String tpdoc);

	@Query("SELECT SUM(c.vtottrib) FROM LcDoc c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.status = ?2 "
			+ "AND c.datafat BETWEEN ?3 AND ?4 AND c.cdpessoapara.cdestado.id = ?5 AND c.tipo = ?6 AND (c.fpag != '90' AND c.fpag != '99')")
	BigDecimal faturaMesAnoLocalPeriodoEstado(Long local, String status, Date ini, Date fim, Integer coduf,
			String tpdoc);

	@Query("SELECT SUM(c.vtottrib) FROM LcDoc c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.cdpessoavendedor.id = ?2 "
			+ "AND c.status = ?3 AND c.datafat BETWEEN ?4 AND ?5 AND c.cdpessoapara.cdestado.id = ?6 AND c.tipo = ?7 AND (c.fpag != '90' AND c.fpag != '99')")
	BigDecimal faturaMesAnoLocalPeriodoEstadoResp(Long local, Long respvend, String status, Date ini, Date fim,
			Integer coduf, String tpdoc);

	@Query("SELECT SUM(c.vtottrib) FROM LcDoc c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.status = ?1 AND c.datafat BETWEEN ?2 AND ?3 "
			+ "AND c.cdpessoapara.cdestado.id = ?4 AND c.tipo = ?5 AND (c.fpag != '90' AND c.fpag != '99')")
	BigDecimal faturaMesAnoPeriodoEstado(String status, Date dataini, Date datafim, Integer coduf, String tpdoc);

	@Query("SELECT SUM(c.vtottrib) FROM LcDoc c WHERE c.cdpessoavendedor.id = ?1 AND c.cdpessoaemp.status = 'ATIVO' AND c.status = ?2 AND "
			+ "c.datafat BETWEEN ?3 AND ?4 AND c.cdpessoapara.cdestado.id = ?5 AND c.tipo = ?6 AND (c.fpag != '90' AND c.fpag != '99')")
	BigDecimal faturaMesAnoPeriodoEstadoResp(Long respvend, String status, Date dataini, Date datafim, Integer coduf,
			String tpdoc);

	@Query("SELECT COUNT(c) FROM LcDoc c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.tipo = ?1 AND c.status = ?2")
	Integer verificaDocStatus(String tipo, String status);

	@Query("SELECT COUNT(c) FROM LcDoc c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.tipo = ?2 AND c.status = ?3")
	Integer verificaDocStatusLocal(Long local, String tipo, String status);

	@Query("SELECT c FROM LcDoc c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.tipo = ?1 AND c.status = ?2")
	List<LcDoc> listaDocStatus(String tipo, String status);

	@Query("SELECT c FROM LcDoc c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.tipo = ?2 AND c.status = ?3")
	List<LcDoc> listaDocStatusLocal(Long local, String tipo, String status);

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.tproma = ?1, l.lcroma = ?2, l.numroma = ?3, l.entregast = ?4 WHERE l.id = ?5 ")
	void updateLcRomaDoc(String tipo, Long id, Integer numero, String entregast, Long lcdoc);

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.tproma = '00', l.lcroma = 0, l.numroma = 0, l.entregast = ?1 WHERE l.lcroma = ?2 ")
	void updateLcRomaDocTodos(String entregast, Long idroma);

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.entregast = ?1 WHERE l.id = ?2")
	void updateLcDocEntregaSt(String entregast, Long lcdoc);

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.entregast = ?1 WHERE l.lcroma = ?2")
	void updateLcDocEntregaStRoma(String entregast, Long lcroma);

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.lcdocdevo = ?1, l.numdocdevo = ?2 WHERE l.id = ?3")
	void updateDocDevo(Long id, Long numero, Long idorig);

	@Modifying
	@Transactional
	@Query("UPDATE LcDoc l SET l.pcom = ?1, l.vcom = ?2, l.cdpessoavendedor.id = ?3 WHERE l.id = ?4")
	void updateDocComissaoVend(BigDecimal pcom, BigDecimal vcom, Long vend, Long id);
}
