package com.midas.api.tenant.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfeItemPis;

@Repository
public interface FsNfeItemPisRepository extends JpaRepository<FsNfeItemPis, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE FsNfeItemPis f SET f.cst = ?1, f.vbc = ?2, f.ppis = ?3, f.vpis = ?4, f.vbcst = ?5, f.ppisst = ?6, f.vpisst = ?7 WHERE f.id = ?8")
	void updateTributos(String cst, BigDecimal vbc, BigDecimal ppis, BigDecimal vpis, BigDecimal vbcst,
			BigDecimal ppisst, BigDecimal vpisst, Long id);

	@Modifying
	@Transactional
	@Query("DELETE FROM FsNfeItemPis c WHERE c.id = ?1")
	void deleteItemPis(Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfeItemPis l SET l.cst = ?1 WHERE l.fsnfe_id = ?2")
	void updatePis(String pis, Long id);
}
