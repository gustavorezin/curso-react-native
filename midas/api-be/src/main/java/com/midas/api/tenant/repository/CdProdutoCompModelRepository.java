package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoCompModel;

@Repository
public interface CdProdutoCompModelRepository extends JpaRepository<CdProdutoCompModel, Long> {
	@Query("SELECT c FROM CdProdutoCompModel c WHERE CONVERT(c.id, CHAR) LIKE %?1% OR c.descricao LIKE %?1%")
	Page<CdProdutoCompModel> findAllByNomeBusca(String busca, Pageable pageable);

	@Query("SELECT c FROM CdProdutoCompModel c WHERE c.cdpessoaemp.id = ?1 AND (CONVERT(c.id, CHAR) LIKE %?1% OR c.descricao LIKE %?1%)")
	Page<CdProdutoCompModel> findAllByNomeBuscaLocal(Long local, String busca, Pageable pageable);

	@Query("SELECT c FROM CdProdutoCompModel c WHERE c.cdpessoaemp.id = ?1 ORDER BY c.descricao ASC")
	List<CdProdutoCompModel> findByLocal(Long idlocal);
}
