package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoTamRel;

@Repository
public interface CdProdutoTamRelRepository extends JpaRepository<CdProdutoTamRel, Long> {
	@Query("SELECT c FROM CdProdutoTamRel c WHERE c.cdproduto_id = ?1")
	List<CdProdutoTamRel> findAllByIdProduto(Long id);
}
