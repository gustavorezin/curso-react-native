package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.LcRoma;

@Repository
public interface LcRomaRepository extends JpaRepository<LcRoma, Long> {
	@Query("SELECT c FROM LcRoma c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<LcRoma> findByIdAndCdpessoaemp(Long id, Long cdpessoaemp);

	@Query(value = "SELECT * FROM lc_roma AS c WHERE c.tipo = ?1 AND c.cdpessoaemp_id = ?2 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	LcRoma ultimoLcRoma(String tipo, Long emp);

	@Query("SELECT COUNT(c) FROM LcRoma c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.status = '1'")
	Integer verificaLcRomaSt();

	@Query("SELECT COUNT(c) FROM LcRoma c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.status = '1'")
	Integer verificaLcRomaStLocal(Long local);

	@Query("SELECT c FROM LcRoma c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.tipo = ?1 AND c.status = ?2")
	List<LcRoma> listaRomaStatus(String tipo, String st);

	@Query("SELECT c FROM LcRoma c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.tipo = ?2 AND c.status = ?3")
	List<LcRoma> listaRomaStatusLocal(Long local, String tipo, String st);
}
