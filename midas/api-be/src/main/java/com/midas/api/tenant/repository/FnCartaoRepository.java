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

import com.midas.api.tenant.entity.FnCartao;
import com.midas.api.tenant.entity.FnCxmv;

@Repository
public interface FnCartaoRepository extends JpaRepository<FnCartao, Long> {
	@Query("SELECT c FROM FnCartao c WHERE c.id = ?1 AND c.fncxmv.cdpessoaemp.id = ?2")
	Optional<FnCartao> findByIdAndCdpessoaemp(Long id, Long cdpessoaemp);

	@Query("SELECT c FROM FnCartao c WHERE c.fncxmv.cdpessoaemp.status = 'ATIVO' AND c.dataprev <= CURDATE() AND c.status = '01'")
	List<FnCartao> listaCartaoPrevistos();

	@Query("SELECT COUNT(c) FROM FnCartao c WHERE c.fncxmv.cdpessoaemp.status = 'ATIVO' AND c.dataprev <= CURDATE() AND c.status = '01'")
	Integer verificaCartaoPrevistos();

	@Query("SELECT c FROM FnCartao c WHERE c.fncxmv.cdpessoaemp.status = 'ATIVO' AND c.fncxmv.cdpessoaemp.id = ?1 AND c.dataprev <= CURDATE() AND c.status = '01'")
	List<FnCartao> listaCartaoPrevistosLocal(Long cdpessoaemp);

	@Query("SELECT COUNT(c) FROM FnCartao c WHERE c.fncxmv.cdpessoaemp.status = 'ATIVO' AND c.fncxmv.cdpessoaemp.id = ?1 AND c.dataprev <= CURDATE() AND c.status = '01'")
	Integer verificaCartaoPrevistosLocal(Long cdpessoaemp);

	@Query("SELECT SUM(c.vtot) FROM FnCartao c WHERE c.fncxmv.cdpessoaemp.status = 'ATIVO' AND c.dataprev BETWEEN ?1 AND ?2 "
			+ "AND c.status = ?3")
	BigDecimal somaVtotCartaoPrevisaoStatus(Date ini, Date fim, String status);

	@Query("SELECT SUM(c.vtot) FROM FnCartao c WHERE c.fncxmv.cdpessoaemp.status = 'ATIVO' AND c.fncxmv.cdpessoaemp.id = ?1 AND c.dataprev BETWEEN ?2 AND ?3 "
			+ "AND c.status = ?4")
	BigDecimal somaVtotCartaoPrevisaoStatusLocal(Long cdpessoaemp, Date ini, Date fim, String status);

	@Query("SELECT SUM(c.vtaxa) FROM FnCartao c WHERE c.fncxmv.cdpessoaemp.status = 'ATIVO' AND c.dataprev BETWEEN ?1 AND ?2 "
			+ "AND c.status = ?3")
	BigDecimal somaVtaxaCartaoPrevisaoStatus(Date ini, Date fim, String status);

	@Query("SELECT SUM(c.vtaxa) FROM FnCartao c WHERE c.fncxmv.cdpessoaemp.status = 'ATIVO' AND c.fncxmv.cdpessoaemp.id = ?1 AND c.dataprev BETWEEN ?2 AND ?3 "
			+ "AND c.status = ?4")
	BigDecimal somaVtaxaCartaoPrevisaoStatusLocal(Long cdpessoaemp, Date ini, Date fim, String status);

	@Modifying
	@Transactional
	@Query("UPDATE FnCartao c SET c.ptaxa = ?1, c.vtaxa = ?2, c.vfinal = ?3,  c.status = ?4, c.transacao = ?5, c.dataat = NOW() WHERE c.id = ?6")
	void atualizarCartoes(BigDecimal ptaxa, BigDecimal vtaxa, BigDecimal vfinal, String status, Integer transacao,
			Long id);

	List<FnCartao> findByFncxmv(FnCxmv fnCxmv);

	@Query("SELECT COUNT(c) FROM FnCartao c WHERE c.fncxmv.id = ?1 AND c.status != '02'")
	Integer verificaEntradaTodos(Long fncxmv);

	@Modifying
	@Transactional
	@Query("UPDATE FnCartao c SET c.vsub = c.vsub + ?1, c.vtot = c.vtot + ?1, c.vfinal = c.vfinal + ?1 WHERE c.id = ?2")
	void arredondarValores(BigDecimal valor, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FnCartao c SET c.vsub = c.vsub - ?1, c.vtot = c.vtot - ?1, c.vfinal = c.vfinal - ?1 WHERE c.id = ?2")
	void arredondarValoresMaior(BigDecimal valor, Long id);
}
