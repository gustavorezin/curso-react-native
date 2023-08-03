package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdMaqequip;

@Repository
public interface CdMaqequipRepository extends JpaRepository<CdMaqequip, Integer> {
	@Query("SELECT c FROM CdMaqequip c WHERE c.cdpessoaemp.id = ?1 AND c.status = ?2")
	List<CdMaqequip> findAllByAtivosLocal(Long emp, String string);

	@Query("SELECT COUNT(c.id) FROM CdMaqequip c WHERE c.cdpessoaemp.id = ?1")
	Integer findByLocal(Long local);

	@Query("SELECT c FROM CdMaqequip c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<CdMaqequip> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);
}
