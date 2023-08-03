package com.midas.api.tenant.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.midas.api.tenant.entity.SisReguser;

@Repository
public interface SisReguserRepository extends JpaRepository<SisReguser, Integer> {
	@Transactional
	@Modifying
	@Query("DELETE FROM SisReguser c WHERE c.datacad < ?1")
	void removeDias(Date dias);
}
