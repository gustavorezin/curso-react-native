package com.midas.api.tenant.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FnTituloDre;

@Repository
public interface FnTituloDreRepository extends JpaRepository<FnTituloDre, Long> {
	@Query("SELECT c FROM FnTituloDre c WHERE c.fntitulo.cdpessoaemp.id = ?1 AND c.fntitulo.datacad BETWEEN ?2 AND ?3 AND c.negocia = 'N' ORDER BY c.cdplconmicro.masc ASC")
	List<FnTituloDre> findAllByLocalCompetencia(Long emp, Date ini, Date fim);

	@Modifying
	@Transactional
	@Query("DELETE FROM FnTituloDre c WHERE c.fntitulo.id = ?1")
	void removeItensPlanoContas(Long id);
}
