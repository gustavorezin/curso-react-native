package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCaixaOpera;

@Repository
public interface CdCaixaOperaRepository extends JpaRepository<CdCaixaOpera, Integer> {
	@Query(value = "SELECT * FROM cd_caixa_opera AS c WHERE c.cdpessoavend_id = ?1 LIMIT 1", nativeQuery = true)
	CdCaixaOpera findAllByVendedor(Long vendedor);
}
