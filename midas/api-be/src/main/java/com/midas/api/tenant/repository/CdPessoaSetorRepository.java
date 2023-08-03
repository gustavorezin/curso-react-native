package com.midas.api.tenant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdPessoaSetor;

@Repository
public interface CdPessoaSetorRepository extends JpaRepository<CdPessoaSetor, Integer> {
	@Query("SELECT c FROM CdPessoaSetor c WHERE c.nome LIKE %?1%")
	Page<CdPessoaSetor> findAllByNomeBusca(String busca, Pageable pageable);

	@Query(value = "SELECT COUNT(*) FROM cd_pessoa AS c WHERE c.cdpessoasetorpes_id = ?1", nativeQuery = true)
	Integer qtdUtilizado(Integer id);
}
