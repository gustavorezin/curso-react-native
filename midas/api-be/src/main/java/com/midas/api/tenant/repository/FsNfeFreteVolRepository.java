package com.midas.api.tenant.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfeFreteVol;

@Repository
public interface FsNfeFreteVolRepository extends JpaRepository<FsNfeFreteVol, Long> {
	@Modifying
	@Transactional
	@Query("DELETE FROM FsNfeFreteVol c WHERE c.fsnfefrete.id = ?1")
	void deleteItemFrete(Long id);
}
