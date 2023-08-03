package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FnChequeHist;

@Repository
public interface FnChequeHistRepository extends JpaRepository<FnChequeHist, Long> {
	@Query("SELECT c FROM FnChequeHist c WHERE c.fncxmvini = ?1 ORDER BY c.id ASC")
	List<FnChequeHist> listaHistoricoIni(Long cxmv);

	@Query("SELECT c FROM FnChequeHist c WHERE c.fncxmvfim = ?1 ORDER BY c.id ASC")
	List<FnChequeHist> listaHistoricoFim(Long cxmv);

	@Query(value = "SELECT * FROM fn_cheque_hist AS c WHERE c.fncheque_id = ?1 AND c.fncxmvini = ?2 ORDER BY id DESC LIMIT 1", nativeQuery = true)
	FnChequeHist findByChequeHistUltimo(Long fncheque, Long fncxmvini);
}
