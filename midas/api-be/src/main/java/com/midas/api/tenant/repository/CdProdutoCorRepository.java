package com.midas.api.tenant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoCor;

@Repository
public interface CdProdutoCorRepository extends JpaRepository<CdProdutoCor, Integer> {
	@Query("SELECT c FROM CdProdutoCor c WHERE c.cor LIKE %?1%")
	Page<CdProdutoCor> findAllByCorBusca(String busca, Pageable pageable);

	@Query(value = "SELECT * FROM cd_produto_cor AS c WHERE c.id = ?1", nativeQuery = true)
	CdProdutoCor findCorById(Integer id);
}
