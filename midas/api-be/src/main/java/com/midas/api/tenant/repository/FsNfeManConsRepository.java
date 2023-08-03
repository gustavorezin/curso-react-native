package com.midas.api.tenant.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfeManCons;

@Repository
public interface FsNfeManConsRepository extends JpaRepository<FsNfeManCons, Long> {
	@Query("SELECT c FROM FsNfeManCons c WHERE c.cdpessoaemp.id = ?1")
	FsNfeManCons findByLocal(Long idlocal);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfeManCons c SET c.data = NOW(), c.hora = NOW(), c.ultimonsu = ?1 WHERE c.cdpessoaemp.id = ?2")
	void updateHora(String ultimonsu, Long idlocal);
}
