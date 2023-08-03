package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdEstado;

@Repository
public interface CdEstadoRepository extends JpaRepository<CdEstado, Integer> {
	CdEstado findByUf(String uf);

	List<CdEstado> findAllByOrderByUfAsc();

	List<CdEstado> findAllByOrderByNomeAsc();
}
