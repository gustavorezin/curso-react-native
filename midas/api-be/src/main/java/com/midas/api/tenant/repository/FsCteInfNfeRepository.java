package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.midas.api.tenant.entity.FsCteInfNfe;

@Repository
public interface FsCteInfNfeRepository extends JpaRepository<FsCteInfNfe, Long> {
	@Query("SELECT c FROM FsCteInfNfe c WHERE c.fscteinf.id = ?1 AND c.chave = ?2")
	FsCteInfNfe findByCteAndChave(Long id, String chave);
}
