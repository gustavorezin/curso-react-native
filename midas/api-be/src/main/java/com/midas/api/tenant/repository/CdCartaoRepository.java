package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCartao;

@Repository
public interface CdCartaoRepository extends JpaRepository<CdCartao, Integer> {
	@Query("SELECT c FROM CdCartao c WHERE c.nome LIKE %?1%")
	Page<CdCartao> findAllByNomeBusca(String busca, Pageable pageable);

	@Query("SELECT c FROM CdCartao c WHERE c.cdpessoaemp.id = ?1 AND c.nome LIKE %?2%")
	Page<CdCartao> findAllByNomeBuscaLocal(Long local, String busca, Pageable pageable);

	@Query("SELECT c FROM CdCartao c WHERE c.cdpessoaemp.id = ?1 AND c.status = 'ATIVO'")
	List<CdCartao> findAllByLocalAtivo(Long local);

	@Query("SELECT c FROM CdCartao c WHERE c.cdcaixa.id = ?1 AND c.tipo = ?2 AND c.status = 'ATIVO'")
	List<CdCartao> findAllByCaixaAndTipoAtivo(Integer caixa, String tipo);

	@Query("SELECT c FROM CdCartao c WHERE c.cdpessoaemp.id = ?1 AND c.tipo = ?2 AND c.status = 'ATIVO'")
	List<CdCartao> findAllByLocalAndTipoAtivo(Long local, String tipo);
}
