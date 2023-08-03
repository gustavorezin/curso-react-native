package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsMdfeAverb;
import com.midas.api.tenant.entity.FsMdfeContr;

@Repository
public interface FsMdfeContrRepository extends JpaRepository<FsMdfeContr, Integer> {
	@Query("SELECT c FROM FsMdfeContr c WHERE c.fsmdfe.id = ?1 AND c.cpfcnpj = ?2")
	FsMdfeContr findByMdfeAndCpfcnpj(Long id, String cpfcnpj);
}
