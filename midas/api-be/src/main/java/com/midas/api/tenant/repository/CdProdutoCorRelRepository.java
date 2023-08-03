package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoCorRel;

@Repository
public interface CdProdutoCorRelRepository extends JpaRepository<CdProdutoCorRel, Long> {
	@Query("SELECT c FROM CdProdutoCorRel c WHERE c.cdproduto_id = ?1")
	List<CdProdutoCorRel> findAllByIdProduto(Long id);
}
