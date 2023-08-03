package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsMdfeDoc;

@Repository
public interface FsMdfeDocRepository extends JpaRepository<FsMdfeDoc, Integer> {
	@Query("SELECT c FROM FsMdfeDoc c WHERE c.fsmdfe.id = ?1 AND c.chave = ?2")
	FsMdfeDoc findByMdfeAndChave(Long id, String chave);
	
	@Query("SELECT c FROM FsMdfeDoc c WHERE c.fsmdfe.id = ?1 GROUP BY c.cdcidade.id ORDER BY c.cdcidade.id")
	List<FsMdfeDoc> findAllByMdfeGroupByCidade(Long id);
	
	
}
