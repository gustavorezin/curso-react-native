package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCteCfg;

@Repository
public interface CdCteCfgRepository extends JpaRepository<CdCteCfg, Integer> {
	@Query("SELECT c FROM CdCteCfg c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<CdCteCfg> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query("SELECT c FROM CdCteCfg c WHERE c.cfop LIKE %?1% OR c.natop LIKE %?1% OR c.msgcont LIKE %?1%")
	Page<CdCteCfg> findAllByNomeBusca(String busca, Pageable pageable);

	@Query("SELECT c FROM CdCteCfg c WHERE c.cdpessoaemp.id = ?1 AND (c.cfop LIKE %?2% OR c.natop LIKE %?2% OR c.msgcont LIKE %?2%)")
	Page<CdCteCfg> findAllByNomeBuscaLocal(Long cdpessoaemp, String busca, Pageable pageable);

	@Query("SELECT c FROM CdCteCfg c WHERE c.cdpessoaemp.id = ?1")
	List<CdCteCfg> findAllByLocal(Long local);
}
