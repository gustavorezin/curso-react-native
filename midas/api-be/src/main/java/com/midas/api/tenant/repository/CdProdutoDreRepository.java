package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoDre;

@Repository
public interface CdProdutoDreRepository extends JpaRepository<CdProdutoDre, Long> {
	@Query("SELECT COUNT(e) FROM CdProdutoDre e WHERE e.cdpessoaemp.id = ?1 AND e.cdproduto.id= ?2")
	Integer findByEmpProdutoPlCon(Long emp, Long produto);
}
