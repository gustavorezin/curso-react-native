package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.CdProdutoPreco;
import com.midas.api.tenant.entity.CdProdutoTab;

@Repository
public interface CdProdutoPrecoRepository extends JpaRepository<CdProdutoPreco, Long> {
	CdProdutoPreco findByCdprodutoAndCdprodutotab(CdProduto p, CdProdutoTab t);

	@Query("SELECT c FROM CdProdutoPreco c WHERE c.cdprodutotab.id = ?1 AND c.cdproduto.id = ?2")
	Optional<CdProdutoPreco> findByTabProd(Integer id, Long prod);

	@Modifying
	@Transactional
	@Query("DELETE FROM CdProdutoPreco c WHERE c.cdproduto.id = ?1")
	void deleteByProd(Long id);

	List<CdProdutoPreco> findAllByCdproduto(CdProduto produto);

	@Query("SELECT c FROM CdProdutoPreco c WHERE c.cdproduto.id = ?1 ORDER BY c.cdprodutotab.nome ASC")
	List<CdProdutoPreco> findAllByCdprodutoID(Long produto);

	@Query("SELECT c FROM CdProdutoPreco c WHERE c.cdprodutotab.id = ?1")
	List<CdProdutoPreco> findByTab(Integer id);

	@Query("SELECT c FROM CdProdutoPreco c WHERE c.cdprodutotab.id = ?1 AND c.cdprodutotab.cdpessoaemp.id = ?2 AND (CONVERT(c.cdproduto.id, CHAR) LIKE %?3% "
			+ "OR c.cdproduto.nome LIKE %?3% OR c.cdproduto.codigo LIKE %?3%)")
	Page<CdProdutoPreco> findByTabBuscaLocal(Integer id, Long cdpessoaemp, String busca, Pageable pageable);

	@Query("SELECT c FROM CdProdutoPreco c WHERE c.cdprodutotab.id = ?1 AND (CONVERT(c.cdproduto.id, CHAR) LIKE %?2% "
			+ "OR c.cdproduto.nome LIKE %?2% OR c.cdproduto.codigo LIKE %?2%)")
	Page<CdProdutoPreco> findByTabBusca(Integer id, String busca, Pageable pageable);

	@Query("SELECT c FROM CdProdutoPreco c WHERE c.cdproduto.id = ?1 AND c.cdprodutotab.status_loja = ?2")
	List<CdProdutoPreco> findAllByProdutoStatusLoja(Long id, String status_loja);
}
