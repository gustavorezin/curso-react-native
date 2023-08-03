package com.midas.api.tenant.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FnTitulo;

@Repository
public interface FnTituloRepository extends JpaRepository<FnTitulo, Long> {
	@Modifying
	@Transactional
	@Query("DELETE FROM FnTitulo f WHERE f.lcdoc = ?1 AND f.tipo = ?2")
	void removeParcelasTipo(Long iddoc, String tipo);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.paracob = ?1 WHERE f.lcdoc = ?2")
	void attParaCob(String paracob, Long iddoc);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.paracob = ?1, f.numnota = ?2, f.tpdocfiscal = ?3, f.docfiscal = ?4 WHERE f.lcdoc = ?5")
	void attParaCobDocFiscal(String paracob, Integer nnf, String modelo, Long docfiscal, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.paracob = ?1, f.numnota = ?2, f.tpdocfiscal = ?3, f.docfiscal = ?4 WHERE f.docfiscal = ?5")
	void attParaDocCobDocFiscal(String paracob, Integer nnf, String modelo, Long iddocfiscal, Long docfiscal);// Repete

	@Query("SELECT c FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.vence <= CURDATE() AND c.paracob = 'S' AND c.tipo = ?1 AND c.vsaldo > 0")
	List<FnTitulo> listaTitulosVencidos(String tipo);

	@Query("SELECT c FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.vence <= CURDATE() AND c.paracob = 'S' AND c.tipo = ?1 AND c.vpago = 0 AND c.cobauto = 'S'")
	List<FnTitulo> listaTitulosVencidosCobAuto(String tipo);

	@Query("SELECT c FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.vence <= CURDATE() AND c.paracob = 'S' AND c.tipo = ?2 "
			+ "AND c.vsaldo > 0")
	List<FnTitulo> listaTitulosVencidosLocal(Long local, String tipo);

	@Query("SELECT COUNT(c) FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.vence <= CURDATE() AND c.paracob = 'S' AND c.vsaldo > 0")
	Integer verificaTitulosVencidos();

	@Query("SELECT COUNT(c) FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.vence <= CURDATE() AND c.paracob = 'S' AND c.vsaldo > 0 AND c.tipo = ?1")
	Integer verificaTitulosVencidos(String tipo);

	@Query("SELECT COUNT(c) FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.vence <= CURDATE() AND c.paracob = 'S' "
			+ "AND c.vsaldo > 0")
	Integer verificaTitulosVencidosLocal(Long local);

	@Query("SELECT COUNT(c) FROM FnTitulo c WHERE c.lcdoc = ?1 AND c.tecno_idintegracao IS NOT NULL")
	Integer verificaTituloIdIntegra(Long iddoc);

	@Query("SELECT c FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<FnTitulo> findByIdAndCdpessoaemp(Long id, Long cdpessoaemp);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.vpago = f.vpago - ?1, f.vdesc = f.vdesc - ?2, f.vjuro = f.vjuro - ?3, f.vsaldo = f.vsaldo + ?4, f.databaixa = ?5,"
			+ "f.vtot = f.vparc + f.vfcpst + f.vicmsst + f.vipi + f.vfrete - f.vdesc + f.vjuro WHERE f.id = ?6")
	void estornaValor(BigDecimal valorBaixa, BigDecimal valorDesc, BigDecimal valorJuro, BigDecimal valorSaldo,
			Date databaixaant, Long fnTitulo);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.vpago = f.vpago + ?1, f.vdesc = f.vdesc + ?2, f.vjuro = f.vjuro + ?3, f.vsaldo = f.vsaldo - ?4, f.databaixa = ?5, "
			+ "f.cdcaixapref.id = ?6, f.vtot = f.vparc + f.vfcpst + f.vicmsst + f.vipi + f.vfrete - f.vdesc + f.vjuro WHERE f.id = ?7")
	void baixaValor(BigDecimal valorBaixa, BigDecimal valorDesc, BigDecimal valorJuro, BigDecimal valorSaldo,
			Date datapag, Integer cdx, Long fnTitulo);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.vparc = ?1, f.vtot = ?1, f.vsaldo = ?1, f.vence = ?2 WHERE f.id = ?3")
	void attValorVence(BigDecimal vtot, Date vence, Long fnTitulo);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.vence = ?1 WHERE f.id = ?2")
	void attVencimento(Date vence, Long fnTitulo);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.cdcaixapref.id = ?1, f.fpagpref = ?2 WHERE f.id = ?3")
	void attCaixaFpagValor(Integer caixa, String fpag, Long fnTitulo);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.paracob = ?1 WHERE f.id = ?2")
	void attTituloCobranca(String paracob, Long fnTitulo);

	@Query("SELECT c FROM FnTitulo c WHERE c.transacao = ?1")
	List<FnTitulo> listaTitulosTransacao(Integer transacao);

	@Query("SELECT COUNT(c) FROM FnTitulo c WHERE c.transacao = ?1 AND c.vpago > 0")
	Integer verificaTitulosMovimentadosTransacao(Integer transacao);

	@Query("SELECT SUM(c.vtot) FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.paracob = 'S' AND c.tipo = 'R' AND MONTH(c.datacad) = ?1 AND YEAR(c.datacad) = ?2")
	BigDecimal faturaMesAno(Integer mes, Integer ano);

	@Query("SELECT SUM(c.vtot) FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.paracob = 'S' AND c.tipo = 'R' AND MONTH(c.datacad) = ?2 AND YEAR(c.datacad) = ?3")
	BigDecimal faturaMesAnoLocal(Long local, Integer mes, Integer ano);

	@Query("SELECT SUM(c.vtot) FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.paracob = 'S' AND c.tipo = 'R' AND c.datacad BETWEEN ?1 AND ?2")
	BigDecimal faturaMesAnoPeriodo(Date ini, Date fim);

	@Query("SELECT SUM(c.vtot) FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.paracob = 'S' AND c.tipo = 'R' AND c.datacad BETWEEN ?2 AND ?3")
	BigDecimal faturaMesAnoLocalPeriodo(Long local, Date ini, Date fim);

	// CREDITOS
	@Transactional
	@Query(value = "SELECT * FROM fn_titulo AS c LEFT JOIN cd_pessoa AS p ON c.cdpessoaemp_id=p.id WHERE p.status = 'ATIVO' AND c.cdpessoaemp_id = ?1 AND c.cdpessoapara_id = ?2 "
			+ "AND c.vpago != c.vcredsaldo AND c.cred = 'S' ORDER BY c.vcredsaldo DESC LIMIT 1", nativeQuery = true)
	FnTitulo findCredito(Long local, Long para);

	@Query("SELECT c FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cred = 'S' AND c.cdpessoaemp.id = ?1 AND c.cdpessoapara.id = ?2 AND c.vcredsaldo > 0 ORDER BY c.vcredsaldo ASC")
	List<FnTitulo> listaTitulosCreditosAbertos(Long local, Long para);

	@Query("SELECT SUM(c.vcredsaldo) FROM FnTitulo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cred = 'S' AND c.cdpessoaemp.id = ?1 AND c.cdpessoapara.id = ?2 AND c.vcredsaldo > 0")
	BigDecimal valorCreditoPessoa(Long local, Long para);

	@Query("SELECT COUNT(c) FROM FnTitulo c WHERE c.vpago > 0 AND c.tpdocfiscal = ?1 AND c.docfiscal = ?2 AND c.numnota = ?3 AND c.paracob = ?4")
	Integer verificaTitulosMovimentadosNota(String tpdocfiscal, Long docfiscal, Integer numnota, String paracob);

	@Query("SELECT c FROM FnTitulo c WHERE c.tpdocfiscal = ?1 AND c.docfiscal = ?2 AND c.numnota = ?3 AND c.paracob = ?4")
	List<FnTitulo> titulosNota(String tpdocfiscal, Long docfiscal, Integer numnota, String paracob);

	@Query("SELECT c FROM FnTitulo c WHERE c.lcdoc = ?1 AND c.tipo = ?2")
	List<FnTitulo> listaTitulosDocTipo(Long lcdoc, String tipo);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.vcredsaldo = ?1, f.databaixa = ?2 WHERE f.id = ?3 AND f.cred = 'S'")
	void baixaValorCredito(BigDecimal valorCred, Date datapag, Long fnTitulo);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.vcredsaldo = f.vcredsaldo + ?1, f.databaixa = NOW() WHERE f.id = ?2 AND f.cred = 'S'")
	void devolveValorCredito(BigDecimal valorCred, Long fnTitulo);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.vcredsaldo = f.vpago, f.databaixa = NOW() WHERE f.id = ?1 AND f.cred = 'S'")
	void devolveValorCreditoTotal(Long fnTitulo);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.vpago = f.vpago - f.vjuro, f.vjuro = 0 WHERE f.id = ?1")
	void removeJurosParcial(Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.vtot = ?1 WHERE f.id = ?2")
	void attVtotDesconto(BigDecimal vtot, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.vparc = f.vparc - f.vjuro, f.vtot = f.vtot - f.vjuro,f.vpago = f.vpago - f.vjuro, f.vjuro = 0 WHERE f.id = ?1")
	void removeJuros(Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.tecno_idintegracao = ?1 WHERE f.id = ?2")
	void updateTecnoIdIntegracao(String identegracao, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.tecno_idintegracao = null WHERE f.tecno_idintegracao = ?1")
	void removeTecnoIdIntegracao(String identegracao);

	@Query("SELECT c FROM FnTitulo c WHERE c.tecno_idintegracao = ?1")
	FnTitulo findByTecno_idintegracao(String idIntegracao);

	@Modifying
	@Transactional
	@Query("UPDATE FnTitulo f SET f.cdpessoapara.id = ?1, f.vbccom = ?2, f.pcom = ?3, f.vparc = ?4, f.vtot = ?4, f.vsaldo = ?4 WHERE f.id = ?5")
	void updateTituloCom(Long vend, BigDecimal vbccom, BigDecimal pcom, BigDecimal vcom, Long id);
}
