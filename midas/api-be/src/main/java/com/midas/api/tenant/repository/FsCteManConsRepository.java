package com.midas.api.tenant.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsCteManCons;

@Repository
public interface FsCteManConsRepository extends JpaRepository<FsCteManCons, Long> {
	@Query("SELECT c FROM FsCteManCons c WHERE c.cdpessoaemp.id = ?1")
	FsCteManCons findByLocal(Long idlocal);

	@Modifying
	@Transactional
	@Query("UPDATE FsCteManCons c SET c.data = NOW(), c.hora = NOW(), c.ultimonsu = ?1 WHERE c.cdpessoaemp.id = ?2")
	void updateHora(String ultimonsu, Long idlocal);
}
