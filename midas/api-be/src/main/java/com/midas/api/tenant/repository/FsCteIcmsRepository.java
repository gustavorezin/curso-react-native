package com.midas.api.tenant.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsCteIcms;

@Repository
public interface FsCteIcmsRepository extends JpaRepository<FsCteIcms, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE FsCteIcms c SET c.picms = ?1, c.picmsstret = ?2, c.predbc = ?3, c.vbc = ?4, c.vbcstret = ?4, c.vicms = ?5, c.vicmsstret = ?6 "
			+ "WHERE c.id = ?7")
	void updateICMS(BigDecimal picms, BigDecimal picmsstret, BigDecimal predbc, BigDecimal vbc, BigDecimal vicms,
			BigDecimal vicmsstret, Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE FsCteIcms c SET c.vibpt = ?1 WHERE c.id = ?2")
	void updateVIBPT(BigDecimal vIBPT, Long id);
}
