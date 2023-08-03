package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsMdfeAverb;

@Repository
public interface FsMdfeAverbRepository extends JpaRepository<FsMdfeAverb, Integer> {
	@Query("SELECT c FROM FsMdfeAverb c WHERE c.fsmdfe.id = ?1 AND c.numaverb = ?2")
	FsMdfeAverb findByMdfeAndNumaverb(Long id, String numaverb);
}
