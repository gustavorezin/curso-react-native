package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdEmail;

@Repository
public interface CdEmailRepository extends JpaRepository<CdEmail, Integer> {
	@Query(value = "SELECT * FROM cd_email AS c WHERE c.id = ?1 AND c.cdpessoaemp_id = ?2", nativeQuery = true)
	Optional<CdEmail> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query("SELECT c FROM CdEmail c WHERE c.cdpessoaemp.id = ?1 AND c.localenvio = ?2")
	Optional<CdEmail> findByLocalEnvio(Long cdpessoaemp, String tipo);

	@Query(value = "SELECT * FROM cd_email AS c WHERE c.cdpessoaemp_id = ?1 ORDER BY c.nome ASC", nativeQuery = true)
	List<CdEmail> findAllLocalTodos(Long cdpessoaemp);

	@Query(value = "SELECT * FROM cd_email AS c WHERE c.cdpessoaemp_id = ?1", nativeQuery = true)
	Page<CdEmail> findAllLocal(Long cdpessoaemp, Pageable pageable);
}
