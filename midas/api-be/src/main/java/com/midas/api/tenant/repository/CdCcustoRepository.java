package com.midas.api.tenant.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCcusto;

@Repository
public interface CdCcustoRepository extends JpaRepository<CdCcusto, Integer> {
	@Query("SELECT c FROM CdCcusto c WHERE c.nome LIKE %?1% OR c.info LIKE %?1%")
	Page<CdCcusto> findAllByNomeBusca(String busca, Pageable pageable);

	@Query("SELECT c FROM CdCcusto c WHERE c.cdpessoaemp.id = ?1 AND c.status = ?2")
	List<CdCcusto> findAllByLocanAndStatus(Long local, String string);

	@Modifying
	@Transactional
	@Query("UPDATE CdCcusto c SET c.usa_padrao = 'N' WHERE c.id != ?1 AND c.cdpessoaemp.id = ?2")
	void alteraTipoLocalSimilarLocal(Integer id, Long local);

	@Modifying
	@Transactional
	@Query("UPDATE CdCcusto c SET c.usa_padrao = 'N' WHERE c.cdpessoaemp.id = ?1")
	void alteraTipoLocalSimilar(Long local);

	@Query("SELECT c FROM CdCcusto c WHERE c.cdpessoaemp.id = ?1 AND c.usa_padrao = 'S'")
	CdCcusto findByLocalCdpessoaemp(Long id);
}
