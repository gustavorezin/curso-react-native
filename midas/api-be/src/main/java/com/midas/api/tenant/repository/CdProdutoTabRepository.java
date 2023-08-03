package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoTab;

@Repository
public interface CdProdutoTabRepository extends JpaRepository<CdProdutoTab, Integer> {
	@Query("SELECT c FROM CdProdutoTab c WHERE CONVERT(c.id, CHAR) LIKE %?1% OR c.nome LIKE %?1% OR c.ufaplic LIKE %?1% OR c.info LIKE %?1%")
	Page<CdProdutoTab> findAllByNomeBusca(String busca, Pageable pageable);

	@Query("SELECT c FROM CdProdutoTab c WHERE c.cdpessoaemp.id = ?1 AND (CONVERT(c.id, CHAR) LIKE %?1% OR c.nome LIKE %?1% OR "
			+ "c.ufaplic LIKE %?1% OR c.info LIKE %?1%)")
	Page<CdProdutoTab> findAllByNomeBuscaLocal(Long local, String busca, Pageable pageable);

	@Query("SELECT c FROM CdProdutoTab c WHERE c.cdpessoaemp.id = ?1 AND c.status = 'ATIVO' AND (c.ufaplic = 'XX' OR c.ufaplic = ?2)")
	List<CdProdutoTab> findAllByLocalAndUf(Long local, String uf);

	@Query("SELECT c FROM CdProdutoTab c WHERE c.cdprodutomarkup.id = ?1")
	List<CdProdutoTab> findAllByMarkup(Integer markup);

	@Query("SELECT c FROM CdProdutoTab c WHERE c.cdpessoaemp.id = ?1 AND c.status = 'ATIVO'")
	List<CdProdutoTab> findAllByLocal(Long local);

	@Query(value = "SELECT * FROM cd_produto_tab AS c WHERE c.cdpessoaemp_id = ?1 ORDER BY id DESC LIMIT 1", nativeQuery = true)
	CdProdutoTab findByCdpessoaempPDV(Long idlocal);
}
