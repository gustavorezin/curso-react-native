package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfeAut;

@Repository
public interface FsNfeAutRepository extends JpaRepository<FsNfeAut, Integer> {
	@Query("SELECT c FROM FsNfeAut c WHERE c.fsnfe.id = ?1 AND c.cpfcnpj = ?2 ")
	FsNfeAut getCpfCnpjNfe(Long id, String cpfcnpj);
}
