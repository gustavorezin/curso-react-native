package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCaixaOpera;
import com.midas.api.tenant.entity.CdNfeCfgVend;

@Repository
public interface CdNfeCfgVendRepository extends JpaRepository<CdNfeCfgVend, Integer> {
	@Query(value = "SELECT * FROM cd_nfecfg_vend AS c WHERE c.cdpessoavend_id = ?1 LIMIT 1", nativeQuery = true)
	CdCaixaOpera findAllByVendedor(Long vendedor);
}
