package com.midas.api.mt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.mt.entity.CdCodserv;

@Repository
public interface CdCodservRepository extends JpaRepository<CdCodserv, Integer> {
	@Query(value = "SELECT * FROM cd_codserv AS c WHERE c.cnae LIKE %?1% OR c.cnae_desc LIKE %?1% OR c.codserv LIKE %?1% "
			+ "OR c.codserv_desc LIKE %?1% ORDER BY c.codserv LIMIT 150", nativeQuery = true)
	List<CdCodserv> listaTodos(String ncm);

	@Query("SELECT c FROM CdCodserv c WHERE c.cnae LIKE %?1% OR c.cnae_desc LIKE %?1% OR c.codserv LIKE %?1% OR c.codserv_desc LIKE %?1%")
	Page<CdCodserv> findAllBusca(String busca, Pageable pageable);

	@Query(value = "SELECT * FROM cd_codserv AS c WHERE c.codserv = ?1 LIMIT 1", nativeQuery = true)
	CdCodserv findByCodserv(String codserv);
}
