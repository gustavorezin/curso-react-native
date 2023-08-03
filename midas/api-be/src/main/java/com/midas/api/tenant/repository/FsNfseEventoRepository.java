package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfseEvento;

@Repository
public interface FsNfseEventoRepository extends JpaRepository<FsNfseEvento, Long> {
	@Query(value = "SELECT * FROM fs_nfseevento AS c WHERE c.fsnfse_id = ?1  ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	FsNfseEvento ultimoEvento(Long idnfse);
}
