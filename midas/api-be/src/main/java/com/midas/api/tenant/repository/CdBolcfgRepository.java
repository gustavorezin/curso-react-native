package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdBolcfg;

@Repository
public interface CdBolcfgRepository extends JpaRepository<CdBolcfg, Integer> {
	@Query("SELECT c FROM CdBolcfg c WHERE c.id = ?1 AND c.cdcaixa.cdpessoaemp.id = ?2")
	Optional<CdBolcfg> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query("SELECT c FROM CdBolcfg c WHERE c.cdcaixa.cdpessoaemp.id = ?1 AND c.status = ?2")
	List<CdBolcfg> findAllByAtivosLocal(Long emp, String stauts);

	@Query("SELECT c FROM CdBolcfg c WHERE c.cdcaixa.cdpessoaemp.id = ?1")
	List<CdBolcfg> findAllByLocal(Long emp);

	@Modifying
	@Transactional
	@Query("UPDATE CdBolcfg c SET c.nossonumatual = ?1 WHERE c.id = ?2")
	void updateNossoNum(Integer nossoNum, Integer id);
}
