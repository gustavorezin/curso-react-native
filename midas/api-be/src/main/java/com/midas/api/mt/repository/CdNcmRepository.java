package com.midas.api.mt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.mt.entity.CdNcm;

@Repository
public interface CdNcmRepository extends JpaRepository<CdNcm, Integer> {
	@Query("SELECT c FROM CdNcm c WHERE c.ncm = ?1")
	CdNcm findNcm(String ncm);

	Optional<CdNcm> findByNcm(String ncm);

	@Query("SELECT c FROM CdNcm c WHERE c.ncm = ?1")
	CdNcm findNcmClasse(String ncm);

	@Query(value = "SELECT * FROM cd_ncm AS c WHERE LENGTH(c.ncm) = 8 AND (c.ncm LIKE %?1% OR c.cest LIKE %?1% OR "
			+ "c.descricao LIKE %?1%) ORDER BY c.ncm ASC LIMIT 150", nativeQuery = true)
	List<CdNcm> listaNcm(String ncm);

	@Query("SELECT c FROM CdNcm c WHERE c.ncm LIKE %?1% OR c.cest LIKE %?1% OR c.descricao LIKE %?1%")
	Page<CdNcm> findAllByNcmBusca(String busca, Pageable pageable);
}
