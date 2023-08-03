package com.midas.api.tenant.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsCteMan;

@Repository
public interface FsCteManRepository extends JpaRepository<FsCteMan, Long> {
	FsCteMan findFirstByChaveAndCdpessoaemp(String chave, CdPessoa cdpessoaemp);

	@Query(value = "SELECT * FROM fs_cteman AS c WHERE c.cdpessoaemp_id = ?1 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	FsCteMan findLastByLocal(Long idlocal);

	@Modifying
	@Transactional
	@Query("UPDATE FsCteMan c SET c.st = ?1 WHERE c.chave = ?2")
	void updateSituacao(Integer tpevento, String chave);

	@Modifying
	@Transactional
	@Query("UPDATE FsCteMan c SET c.st_imp = ?1 WHERE c.nsu = ?2")
	void updateSituacaoImp(String st, String nsu);

	@Modifying
	@Transactional
	@Query("UPDATE FsCteMan c SET c.st_imp = ?1 WHERE c.chave = ?2")
	void updateSituacaoImpChave(String st, String chave);

	@Modifying
	@Transactional
	@Query("UPDATE FsCteMan c SET c.st_imp = ?1 WHERE c.id = ?2")
	void updateSituacaoImpId(String st, Long id);

	@Query("SELECT COUNT(c) FROM FsCteMan c WHERE c.cdpessoaemp.status = 'ATIVO' AND (c.st = 1 OR c.st_imp = 'N')")
	Integer verificaCtemanManifestoStImp();

	@Query("SELECT COUNT(c) FROM FsCteMan c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND (c.st = 1 OR c.st_imp = 'N')")
	Integer verificaCtemanManifestoStImpLocal(Long id);

	@Query("SELECT c FROM FsCteMan c WHERE c.cdpessoaemp.status = 'ATIVO' AND (c.st = 1 OR c.st_imp = 'N')")
	List<FsCteMan> listaCtemanManifestoStImp();

	@Query("SELECT c FROM FsCteMan c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND (c.st = 1 OR c.st_imp = 'N')")
	List<FsCteMan> listaCtemanManifestoStImpLocal(Long id);
}
