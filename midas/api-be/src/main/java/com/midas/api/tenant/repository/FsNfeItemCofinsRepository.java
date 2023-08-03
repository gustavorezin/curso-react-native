package com.midas.api.tenant.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfeItemCofins;

@Repository
public interface FsNfeItemCofinsRepository extends JpaRepository<FsNfeItemCofins, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE FsNfeItemCofins f SET f.cst = ?1, f.vbc = ?2, f.pcofins = ?3, f.vcofins = ?4, f.vbcst = ?5, f.pcofinsst = ?6, f.vcofinsst = ?7 WHERE f.id = ?8")
	void updateTributos(String cofins, BigDecimal vbccofins, BigDecimal cofins_aliq, BigDecimal vcofins,
			BigDecimal vbccofinsst, BigDecimal cofins_aliqst, BigDecimal vcofinsst, Long id);

	@Modifying
	@Transactional
	@Query("DELETE FROM FsNfeItemCofins c WHERE c.id = ?1")
	void deleteItemCofins(Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfeItemCofins l SET l.cst = ?1 WHERE l.fsnfe_id = ?2")
	void updateCofins(String cof, Long id);
}
