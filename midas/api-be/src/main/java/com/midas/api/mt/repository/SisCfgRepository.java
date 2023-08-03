package com.midas.api.mt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.mt.entity.SisCfg;

@Repository
public interface SisCfgRepository extends JpaRepository<SisCfg, Integer> {
	@Query("SELECT max(id) FROM SisCfg")
	Integer getMaxSisCfgId();
}
