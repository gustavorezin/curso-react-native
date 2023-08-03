package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsCteInfCarga;

@Repository
public interface FsCteInfCargaRepository extends JpaRepository<FsCteInfCarga, Long> {
	@Query("SELECT c FROM FsCteInfCarga c WHERE c.fscteinf.id = ?1 AND c.cunid = ?2")
	FsCteInfCarga findByCteAndUnidade(Long id, String unidade);
}
