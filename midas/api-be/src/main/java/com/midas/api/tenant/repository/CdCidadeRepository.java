package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCidade;

@Repository
public interface CdCidadeRepository extends JpaRepository<CdCidade, Integer> {
	CdCidade findByIbge(String ibge);

	CdCidade findByNome(String nome);

	@Query(value = "SELECT * FROM cd_cidade AS c WHERE c.cdestado_id = ?1 ORDER BY c.nome ASC", nativeQuery = true)
	List<CdCidade> findAllByCdestado(Integer cdestado);
	
	@Query("SELECT c FROM CdCidade c WHERE c.cdestado.uf = ?1 ORDER BY c.nome ASC")
	List<CdCidade> findAllByCdestadoUf(String cdestado);

	@Query("SELECT c FROM CdCidade c WHERE c.nome = ?1 AND c.cdestado.id = ?2")
	CdCidade findByNomeAndCdestadoId(String municipio, Integer id);
}
