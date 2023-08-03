package com.midas.api.tenant.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsSped;

@Repository
public interface FsSpedRepository extends JpaRepository<FsSped, Integer> {
	@Query("SELECT c FROM FsSped c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<FsSped> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query("SELECT c FROM FsSped c WHERE c.cdpessoaemp.id = ?1")
	Page<FsSped> findAllLocal(Long cdpessoaemp, Pageable pageable);

	@Query("SELECT c FROM FsSped c WHERE c.cdpessoaemp.id = ?1 AND c.mes = ?2 AND c.ano = ?3")
	FsSped verificaSped(Long id, Integer mes, Integer ano);
}
