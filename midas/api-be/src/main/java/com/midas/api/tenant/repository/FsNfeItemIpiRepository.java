package com.midas.api.tenant.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfeItemIpi;

@Repository
public interface FsNfeItemIpiRepository extends JpaRepository<FsNfeItemIpi, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE FsNfeItemIpi f SET f.cst = ?1, f.vbc = ?2, f.pipi = ?3, f.vipi = ?4 WHERE f.id = ?5")
	void updateTributos(String cst, BigDecimal vbcipi, BigDecimal ipi_aliq, BigDecimal vipi, Long id);

	@Modifying
	@Transactional
	@Query("DELETE FROM FsNfeItemIpi c WHERE c.id = ?1")
	void deleteItemIpi(Long id);
}
