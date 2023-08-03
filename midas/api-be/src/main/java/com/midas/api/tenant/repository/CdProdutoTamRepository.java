package com.midas.api.tenant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoTam;

@Repository
public interface CdProdutoTamRepository extends JpaRepository<CdProdutoTam, Integer> {
	@Query("SELECT c FROM CdProdutoTam c WHERE c.tamanho LIKE %?1%")
	Page<CdProdutoTam> findAllByTamBusca(String busca, Pageable pageable);

	@Query(value = "SELECT * FROM cd_produto_tam AS c WHERE c.id = ?1", nativeQuery = true)
	CdProdutoTam findTamById(Integer id);
}
