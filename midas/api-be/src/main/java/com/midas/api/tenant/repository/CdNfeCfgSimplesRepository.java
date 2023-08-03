package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdNfeCfgSimples;

@Repository
public interface CdNfeCfgSimplesRepository extends JpaRepository<CdNfeCfgSimples, Integer> {
	@Query("SELECT c FROM CdNfeCfgSimples c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<CdNfeCfgSimples> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query("SELECT c FROM CdNfeCfgSimples c WHERE c.cdpessoaemp.id = ?1")
	List<CdNfeCfgSimples> findByCdpessoaemp(Long cdpessoaemp);

	@Query("SELECT c FROM CdNfeCfgSimples c WHERE c.cdpessoaemp.id = ?1")
	CdNfeCfgSimples getByCdpessoaemp(Long id);
}
