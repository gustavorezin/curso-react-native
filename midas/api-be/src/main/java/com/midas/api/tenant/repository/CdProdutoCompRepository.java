package com.midas.api.tenant.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoComp;

@Repository
public interface CdProdutoCompRepository extends JpaRepository<CdProdutoComp, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE CdProdutoComp c SET c.vcusto = ?1 WHERE c.cdprodutocomp.id = ?2 AND c.cdpessoaemp.id = ?3")
	void updateVCustoProd(BigDecimal bigDecimal, Long id, Long local);

	@Query(value = "SELECT * FROM cd_produto_comp AS c WHERE c.cdproduto_id = ?1 AND c.cdprodutocomp_id = ?2 LIMIT 1", nativeQuery = true)
	CdProdutoComp findByPC(Long id, Long id2);

	@Query("SELECT c FROM CdProdutoComp c WHERE c.cdproduto.id = ?1")
	List<CdProdutoComp> findByProduto(Long prod);

	@Query("SELECT c FROM CdProdutoComp c WHERE c.cdproduto.id = ?1 AND c.cdpessoaemp.id = ?2")
	List<CdProdutoComp> findByProdutoAcabado(Long prod, Long local);

	@Query("SELECT SUM(c.vcusto*c.qtd) FROM CdProdutoComp c WHERE c.cdproduto.id = ?1 AND c.cdpessoaemp.id = ?2")
	BigDecimal somaByProdutoAcabado(Long prod, Long local);
}
