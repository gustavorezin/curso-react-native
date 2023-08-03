package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoSubgrupo;

@Repository
public interface CdProdutoSubgrupoRepository extends JpaRepository<CdProdutoSubgrupo, Integer> {
	@Query("SELECT c FROM CdProdutoSubgrupo c WHERE c.cdprodutogrupo.id = ?1 ORDER BY c.nome ASC")
	List<CdProdutoSubgrupo> findAllByGrupo(Integer id);
}
