package com.midas.api.tenant.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsMdfe;

@Repository
public interface FsMdfeRepository extends JpaRepository<FsMdfe, Long> {
	@Query("SELECT c FROM FsMdfe c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<FsMdfe> findByIdAndCdpessoaemp(Long id, Long cdpessoaemp);

	@Query(value = "SELECT * FROM fs_mdfe AS c WHERE c.cdpessoaemp_id = ?1 AND c.serie = ?2 AND c.tpamb = ?3 AND c.tipo = ?4 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	FsMdfe ultimaByIdAndCdpessoaempSerieModelo(Long cdpessoaemp, Integer serie, String ambiente, String tipo);

	@Modifying
	@Transactional
	@Query("UPDATE FsMdfe f SET f.xml = ?1, f.status = ?2, f.nprot = ?3, f.nrecibo = ?4 WHERE f.id = ?5")
	void updateXMLMDFeStatus(String xml, Integer status, String nprot, String nrec, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FsMdfe f SET f.encerrado = ?1 WHERE f.id = ?2")
	void updateMDFeEncerra(String encerrado, Long id);

	@Query("SELECT COUNT(c) FROM FsMdfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.encerrado = 'N' AND (c.status = 100 OR c.status = 150)")
	int verificaMdfeEncerrados();

	@Query("SELECT COUNT(c) FROM FsMdfe c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.encerrado = 'N' "
			+ "AND (c.status = 100 OR c.status = 150)")
	int verificaMdfeEncerradosLocal(Long local);
}
