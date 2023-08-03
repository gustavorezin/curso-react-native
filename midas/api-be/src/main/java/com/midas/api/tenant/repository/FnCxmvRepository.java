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

import com.midas.api.tenant.entity.FnCxmv;

@Repository
public interface FnCxmvRepository extends JpaRepository<FnCxmv, Long> {
	@Query("SELECT c FROM FnCxmv c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<FnCxmv> findByIdAndCdpessoaemp(Long id, Long cdpessoaemp);

	@Query(value = "SELECT * FROM fn_cxmv AS c WHERE c.fntitulo_id = ?1 ORDER BY id DESC LIMIT 1", nativeQuery = true)
	FnCxmv findByFntituloUltimaBaixa(Long fntitulo);

	@Query("SELECT c FROM FnCxmv c WHERE c.fntitulo.id = ?1")
	List<FnCxmv> findByFntituloBaixa(Long fntitulo);

	@Modifying
	@Transactional
	@Query("UPDATE FnCxmv c SET c.status = ?1,c.transacao = ?2, c.datacad = ?3, c.dataat = NOW() WHERE c.id = ?4")
	void atualizarStatusTransacao(String string, Integer transacao, Date datacad, Long id);

	@Query(value = "SELECT * FROM fn_cxmv AS c JOIN fn_titulo AS t ON c.fntitulo_id=t.id WHERE "
			+ " c.vtot = ?1 AND c.cdcaixa_id = ?2 AND c.fpag = ?3 AND t.tipo = ?4 AND c.transacao = ?5", nativeQuery = true)
	List<FnCxmv> getTaxaSimilar(BigDecimal vtaxa, Integer id, String fpag, String tipo, Integer transacao);

	@Query("SELECT c FROM FnCxmv c WHERE c.vtot = ?1 AND c.transacao = ?2")
	List<FnCxmv> getMovimentoSimilar(BigDecimal valor, Integer transacao);

	@Query("SELECT c FROM FnCxmv c WHERE c.vtot = ?1 AND c.transacao = ?2 AND c.fntitulo.troco = ?3")
	List<FnCxmv> getMovimentoSimilarTroco(BigDecimal valor, Integer transacao, String troco);

	@Query("SELECT c FROM FnCxmv c WHERE c.fntitulo.id = ?1")
	List<FnCxmv> findByTitulos(Long id);

	@Query(value = "SELECT COUNT(c.id) FROM fn_cxmv AS c INNER JOIN fn_titulo AS t ON c.fntitulo_id=t.id WHERE t.lcdoc = ?1", nativeQuery = true)
	Integer qtdTitulosPagos(Long lcdoc);

	@Modifying
	@Transactional
	@Query("UPDATE FnCxmv c SET c.vsub = c.vsub + ?1, c.vtot = c.vtot + ?1 WHERE c.id = ?2")
	void arredondarValores(BigDecimal valor, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FnCxmv c SET c.vsub = c.vsub - ?1, c.vtot = c.vtot - ?1 WHERE c.id = ?2")
	void arredondarValoresMaior(BigDecimal valor, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FnCxmv c SET c.pjuro = 0, c.vjuro = 0, c.vtot=c.vsub WHERE c.id = ?1")
	void removeJuros(Long id);
}
