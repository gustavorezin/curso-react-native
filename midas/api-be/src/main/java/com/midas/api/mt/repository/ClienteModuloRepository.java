package com.midas.api.mt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.mt.entity.ClienteModulo;

@Repository
public interface ClienteModuloRepository extends JpaRepository<ClienteModulo, Integer> {
	@Query("SELECT c FROM ClienteModulo c WHERE c.tenant.id = ?1 AND c.cdmodulo.id = ?2")
	ClienteModulo findByTenantAndModulo(Long id, Integer modulo);
}
