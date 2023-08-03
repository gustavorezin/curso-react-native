package com.midas.api.tenant.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfeCobrDup;

@Repository
public interface FsNfeCobrDupRepository extends JpaRepository<FsNfeCobrDup, Long> {
	@Modifying
	@Transactional
	@Query("DELETE FROM FsNfeCobrDup c WHERE c.fsnfecobr.id = ?1")
	void deleteItemCob(Long id);
}
