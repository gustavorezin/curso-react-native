package com.midas.api.tenant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoMarca;

@Repository
public interface CdProdutoMarcaRepository extends JpaRepository<CdProdutoMarca, Integer> {
	@Query("SELECT c FROM CdProdutoMarca c WHERE c.nome LIKE %?1% OR c.site LIKE %?1%")
	Page<CdProdutoMarca> findAllByNomeBusca(String busca, Pageable pageable);
}
