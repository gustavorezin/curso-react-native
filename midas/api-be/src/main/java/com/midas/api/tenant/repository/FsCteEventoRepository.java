package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsCteEvento;

@Repository
public interface FsCteEventoRepository extends JpaRepository<FsCteEvento, Long> {
	@Query(value = "SELECT * FROM fs_cteevento AS c WHERE c.fscte_id = ?1 AND c.tpevento = ?2 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	FsCteEvento ultimoEvento(Long idcte, String tpevento);
}
