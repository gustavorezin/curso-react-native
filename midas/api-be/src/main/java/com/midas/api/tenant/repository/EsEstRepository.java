package com.midas.api.tenant.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.EsEst;

@Repository
public interface EsEstRepository extends JpaRepository<EsEst, Long> {
	@Query("SELECT e FROM EsEst e WHERE e.cdpessoaemp.id = ?1 AND e.cdproduto.id= ?2")
	EsEst findByEmpProduto(Long emp, Long produto);

	@Query("SELECT e FROM EsEst e WHERE e.cdpessoaemp.id = ?1 AND (e.cdproduto.id = ?2 OR e.cdproduto.codigo = ?3)")
	EsEst findByEmpProdutoIdCodigo(Long emp, Long produto, Integer codigo);

	@Query("SELECT COUNT(e) FROM EsEst e WHERE e.cdpessoaemp.id = ?1 AND e.cdproduto.id= ?2")
	Integer findByEmpProdutoQtd(Long emp, Long produto);

	@Modifying
	@Transactional
	@Query("UPDATE EsEst e SET e.qtd = e.qtd - ?1, e.dataat = NOW() WHERE e.cdproduto.id = ?2 AND e.cdpessoaemp.id = ?3")
	void saidaEstQtd(BigDecimal qtd, Long produto, Long doc);

	@Modifying
	@Transactional
	@Query("UPDATE EsEst e SET e.qtd = e.qtd + ?1, e.dataat = NOW() WHERE e.cdproduto.id = ?2 AND e.cdpessoaemp.id = ?3")
	void entradaEstQtd(BigDecimal qtd, Long produto, Long doc);

	@Query("SELECT c FROM EsEst c WHERE c.cdproduto.tipo = 'PRODUTO' AND (CONVERT(c.cdproduto.id, CHAR) LIKE %?1% OR c.cdproduto.nome LIKE %?1%)")
	Page<EsEst> findAllByNomeBusca(String busca, Pageable pageable);

	@Query("SELECT COUNT(c) FROM EsEst c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.qtdmin > c.qtd")
	Integer verficaEstMin();

	@Query("SELECT COUNT(c) FROM EsEst c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.qtdmin > c.qtd")
	Integer verficaEstMinLocal(Long local);

	@Modifying
	@Transactional
	@Query("UPDATE EsEst e SET e.dataat = NOW(), e.vcusto = ?1 WHERE e.cdproduto.id = ?2 AND e.cdpessoaemp.id = ?3")
	void updateVCustoProdLocal(BigDecimal vcusto, Long prod, Long local);

	@Modifying
	@Transactional
	@Query("UPDATE EsEst e SET e.dataat = NOW(), e.vcusto = ?1 WHERE e.id = ?2")
	void updateVCustoProd(BigDecimal vcusto, Long id);
}
