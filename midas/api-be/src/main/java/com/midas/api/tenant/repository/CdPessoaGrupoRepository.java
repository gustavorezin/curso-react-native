package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdPessoaGrupo;

@Repository
public interface CdPessoaGrupoRepository extends JpaRepository<CdPessoaGrupo, Integer> {
	@Query("SELECT c FROM CdPessoaGrupo c WHERE c.tipo LIKE %?1% OR c.nome LIKE %?1%")
	Page<CdPessoaGrupo> findAllByNomeBusca(String busca, Pageable pageable);

	List<CdPessoaGrupo> findByTipoOrderByNomeAsc(String tipo);

	CdPessoaGrupo findFirstByTipo(String tipocadmidas);
}
