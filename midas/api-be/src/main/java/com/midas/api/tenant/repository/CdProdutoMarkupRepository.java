package com.midas.api.tenant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoMarkup;

@Repository
public interface CdProdutoMarkupRepository extends JpaRepository<CdProdutoMarkup, Integer> {
	@Query("SELECT c FROM CdProdutoMarkup c WHERE CONVERT(c.id, CHAR) LIKE %?1% OR c.descricao LIKE %?1%")
	Page<CdProdutoMarkup> findAllByNomeBusca(String busca, Pageable pageable);
}
