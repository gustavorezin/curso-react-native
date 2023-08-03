package com.midas.api.mt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.mt.entity.ClienteCfg;

@Repository
public interface ClientecfgRepository extends JpaRepository<ClienteCfg, Long> {
	@Query("SELECT max(id) FROM ClienteCfg")
	Long getMaxClienteCfgId();

	@Modifying
	@Transactional
	@Query("UPDATE ClienteCfg c SET c.modulo = ?1 WHERE c.id = ?2")
	void updateModuloCliente(String modulo, Long id);
}
