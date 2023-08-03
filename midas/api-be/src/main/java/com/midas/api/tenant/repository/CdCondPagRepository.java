package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCondPag;

@Repository
public interface CdCondPagRepository extends JpaRepository<CdCondPag, Integer> {
	@Query("SELECT c FROM CdCondPag c WHERE c.nome LIKE %?1%")
	Page<CdCondPag> findAllByNomeBusca(String busca, Pageable pageable);

	List<CdCondPag> findAllByStatus(String status, Sort by);

	@Query("SELECT c FROM CdCondPag c WHERE c.libvendedor = ?1 AND c.status = ?2")
	List<CdCondPag> findAllByStatusVend(String libvendedor, String status);
}
