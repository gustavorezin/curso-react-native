package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FnCheque;

@Repository
public interface FnChequeRepository extends JpaRepository<FnCheque, Long> {
	@Query("SELECT c FROM FnCheque c WHERE c.id = ?1 AND c.cdpessoaempatual.id = ?2")
	Optional<FnCheque> findByIdAndCdpessoaemp(Long id, Long cdpessoaempatual);

	@Modifying
	@Transactional
	@Query(value = "UPDATE fn_cheque AS c SET c.cdpessoaempatual_id = ?1, c.cdcaixaatual_id = ?2, c.status = ?3, c.dataat = NOW() WHERE c.id = ?4", nativeQuery = true)
	void transferirCheque(Long local, Integer caixa, String status, Long id);

	@Query("SELECT c FROM FnCheque c WHERE c.cdpessoaempatual.status = 'ATIVO' AND c.vence <= CURDATE() AND c.status != '04'")
	List<FnCheque> listaChequesVencidos();

	@Query("SELECT COUNT(c) FROM FnCheque c WHERE c.cdpessoaempatual.status = 'ATIVO' AND c.vence <= CURDATE() AND c.status != '04'")
	Integer verificaChequesVencidos();

	@Query("SELECT c FROM FnCheque c WHERE c.cdpessoaempatual.status = 'ATIVO' AND c.cdpessoaempatual.id = ?1 AND c.vence <= CURDATE() AND c.status != '04'")
	List<FnCheque> listaChequesVencidosLocal(Long cdpessoaempatual);

	@Query("SELECT COUNT(c) FROM FnCheque c WHERE c.cdpessoaempatual.status = 'ATIVO' AND c.cdpessoaempatual.id = ?1 AND c.vence <= CURDATE() AND c.status != '04'")
	Integer verificaChequesVencidosLocal(Long cdpessoaempatual);

	@Modifying
	@Transactional
	@Query("UPDATE FnCheque c SET c.status = ?1, c.transacao = ?2, c.dataat = NOW() WHERE c.id = ?3")
	void atualizaStatusCheque(String status, Integer transacao, Long id);

	@Query("SELECT c FROM FnCheque c WHERE c.transacao = ?1 AND c.status = ?2")
	List<FnCheque> getMovimentoSimilar(Integer transacao, String status);

	@Query("SELECT c FROM FnCheque c WHERE c.status = ?1")
	List<FnCheque> getMovimentoChequeStatus(String status);
}
