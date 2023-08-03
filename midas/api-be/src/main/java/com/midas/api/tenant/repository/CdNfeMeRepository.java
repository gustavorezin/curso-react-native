package com.midas.api.tenant.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdNfeMe;

@Repository
public interface CdNfeMeRepository extends JpaRepository<CdNfeMe, Integer> {
	@Query("SELECT c FROM CdNfeMe c WHERE c.cdpessoaemp.id = ?1")
	CdNfeMe findByLocal(Long emp);

	@Query("SELECT c FROM CdNfeMe c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<CdNfeMe> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query(value = "SELECT * FROM cd_nfe_me AS c WHERE c.cdpessoaemp_id = ?1 LIMIT 1", nativeQuery = true)
	CdNfeMe findByIdAndCdpessoaemp(Long cdpessoaemp);

	@Query("SELECT c FROM CdNfeMe c WHERE c.marca LIKE %?1% OR c.especie LIKE %?1%")
	Page<CdNfeMe> findAllByNomeBusca(String busca, Pageable pageable);

	@Query("SELECT c FROM CdNfeMe c WHERE c.cdpessoaemp.id = ?1 AND (c.marca LIKE %?2% OR c.especie LIKE %?2%)")
	Page<CdNfeMe> findAllByNomeBuscaLocal(Long emp, String busca, Pageable pageable);
}
