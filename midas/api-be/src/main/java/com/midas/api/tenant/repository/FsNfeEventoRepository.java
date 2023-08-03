package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfeEvento;

@Repository
public interface FsNfeEventoRepository extends JpaRepository<FsNfeEvento, Long> {
	@Query(value = "SELECT * FROM fs_nfeevento AS c WHERE c.fsnfe_id = ?1 AND c.tpevento = ?2 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	FsNfeEvento ultimoEvento(Long idnfe, String tpevento);
}
