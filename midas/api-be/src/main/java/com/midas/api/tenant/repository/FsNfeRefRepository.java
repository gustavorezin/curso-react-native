package com.midas.api.tenant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfeRef;

@Repository
public interface FsNfeRefRepository extends JpaRepository<FsNfeRef, Integer> {
	
	@Query(value = "SELECT * FROM fs_nferef AS r JOIN fs_nfe AS n ON n.id=r.fsnfe_id WHERE n.cdpessoaemp_id = ?1 AND n.dhsaient BETWEEN ?2 AND ?3 "
		, nativeQuery = true)
	List<FsNfeRef> listaNferefPorDataNfe(Long local, Date ini, Date fim);
}
