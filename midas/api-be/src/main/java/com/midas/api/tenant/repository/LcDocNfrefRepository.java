package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.LcDocNfref;

@Repository
public interface LcDocNfrefRepository extends JpaRepository<LcDocNfref, Long> {
	@Query("SELECT c FROM LcDocNfref c WHERE c.lcdoc.id = ?1 AND c.refnf = ?2")
	LcDocNfref findByDocAndChave(Long id, String refnf);
}
