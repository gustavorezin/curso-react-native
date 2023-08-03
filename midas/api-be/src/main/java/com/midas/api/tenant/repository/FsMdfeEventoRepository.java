package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsMdfeEvento;

@Repository
public interface FsMdfeEventoRepository extends JpaRepository<FsMdfeEvento, Long> {
	@Query(value = "SELECT * FROM fs_mdfeevento AS c WHERE c.fsmdfe_id = ?1 AND c.tpevento = ?2 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	FsMdfeEvento ultimoEvento(Long idmdfe, String tpevento);
}
