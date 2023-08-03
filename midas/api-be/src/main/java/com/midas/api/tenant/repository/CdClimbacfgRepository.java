package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdClimbacfg;

@Repository
public interface CdClimbacfgRepository extends JpaRepository<CdClimbacfg, Integer> {
	@Query("SELECT c FROM CdClimbacfg c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<CdClimbacfg> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query("SELECT c FROM CdClimbacfg c WHERE c.cdpessoaemp.id = ?1")
	List<CdClimbacfg> findAllByLocal(Long emp);

	@Query("SELECT c FROM CdClimbacfg c WHERE c.cdpessoaemp.id = ?1 AND c.status = ?2")
	List<CdClimbacfg> findAllByAtivosLocal(Long emp, String status);

	@Query("SELECT c FROM CdClimbacfg c WHERE c.status = ?1")
	List<CdClimbacfg> findAllByAtivos(String status);
}
