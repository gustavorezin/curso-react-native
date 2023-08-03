package com.midas.api.tenant.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfseCobrDup;

@Repository
public interface FsNfseCobrDupRepository extends JpaRepository<FsNfseCobrDup, Long> {
	@Modifying
	@Transactional
	@Query("DELETE FROM FsNfseCobrDup c WHERE c.fsnfsecobr.id = ?1")
	void deleteItemCob(Long id);
}
