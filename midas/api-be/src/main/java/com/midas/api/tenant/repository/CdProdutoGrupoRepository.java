package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoGrupo;

@Repository
public interface CdProdutoGrupoRepository extends JpaRepository<CdProdutoGrupo, Integer> {
	@Query("SELECT c FROM CdProdutoGrupo c WHERE c.cdprodutocat.id = ?1 ORDER BY c.nome ASC")
	List<CdProdutoGrupo> findAllByCategoria(Integer id);
}
