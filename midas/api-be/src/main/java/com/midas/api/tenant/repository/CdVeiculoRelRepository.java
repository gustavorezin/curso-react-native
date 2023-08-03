package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdVeiculoRel;

@Repository
public interface CdVeiculoRelRepository extends JpaRepository<CdVeiculoRel, Integer> {
	@Query("SELECT c FROM CdVeiculoRel c GROUP BY c.cdveiculo")
	List<CdVeiculoRel> findAllGroupByVeiculo();
	
	@Query("SELECT c FROM CdVeiculoRel c WHERE c.cdveiculo = ?1")
	List<CdVeiculoRel> getListaMotoristasByVeiculo(Integer cdveiculo);
}
