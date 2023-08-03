package com.midas.api.tenant.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FnCxmvDre;

@Repository
public interface FnCxmvDreRepository extends JpaRepository<FnCxmvDre, Long> {
	@Query("SELECT c FROM FnCxmvDre c WHERE c.fncxmv.cdpessoaemp.id = ?1 AND c.fncxmv.datacad BETWEEN ?2 AND ?3 AND c.fncxmv.status = 'ATIVO' ORDER BY c.cdplconmicro.masc ASC")
	List<FnCxmvDre> findAllByLocalCaixa(Long emp, Date ini, Date fim);

	@Modifying
	@Transactional
	@Query("DELETE FROM FnCxmvDre c WHERE c.fncxmv.id = ?1")
	void removeItensPlanoContas(Long fncxmv);
}
