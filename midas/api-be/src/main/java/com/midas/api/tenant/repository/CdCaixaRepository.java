package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCaixa;

@Repository
public interface CdCaixaRepository extends JpaRepository<CdCaixa, Integer> {
	List<CdCaixa> findAllByTipo(String tipo);

	@Query("SELECT c FROM CdCaixa c WHERE c.cdpessoaemp.id = ?1 AND c.tipo = ?2")
	List<CdCaixa> findAllByTipoLocal(Long emp, String tipo);

	@Query("SELECT c FROM CdCaixa c WHERE c.cdpessoaemp.id = ?1 AND c.status = ?2")
	List<CdCaixa> findAllByAtivosLocal(Long emp, String status);

	@Query("SELECT c FROM CdCaixa c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<CdCaixa> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query("SELECT COUNT(c.id) FROM CdCaixa c WHERE c.cdpessoaemp.id = ?1")
	Integer findByLocal(Long local);

	@Query(value = "SELECT * FROM cd_caixa AS c WHERE c.cdpessoaemp_id = ?1 LIMIT 1", nativeQuery = true)
	Optional<CdCaixa> findAllByLocal(Long local);
}
