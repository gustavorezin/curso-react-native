package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsMdfeAut;

@Repository
public interface FsMdfeAutRepository extends JpaRepository<FsMdfeAut, Integer> {
	@Query("SELECT c FROM FsMdfeAut c WHERE c.fsmdfe.id = ?1 AND c.cpfcnpj = ?2 ")
	FsMdfeAut getCpfCnpjMdfe(Long id, String cpfcnpj);
}
