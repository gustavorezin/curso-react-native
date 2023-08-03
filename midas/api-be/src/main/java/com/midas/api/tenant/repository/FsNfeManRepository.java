package com.midas.api.tenant.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsNfeMan;

@Repository
public interface FsNfeManRepository extends JpaRepository<FsNfeMan, Long> {
	FsNfeMan findFirstByChaveAndCdpessoaemp(String chave, CdPessoa cdpessoaemp);

	@Query(value = "SELECT * FROM fs_nfeman AS c WHERE c.cdpessoaemp_id = ?1 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	FsNfeMan findLastByLocal(Long idlocal);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfeMan c SET c.st = ?1 WHERE c.chave = ?2")
	void updateSituacao(Integer tpevento, String chave);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfeMan c SET c.st_imp = ?1 WHERE c.chave = ?2")
	void updateSituacaoImp(String st, String chave);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfeMan c SET c.st_imp = ?1 WHERE c.id = ?2")
	void updateSituacaoImpId(String st, Long id);

	@Query("SELECT COUNT(c) FROM FsNfeMan c WHERE c.cdpessoaemp.status = 'ATIVO' AND (c.st = 1 OR c.st_imp = 'N')")
	Integer verificaNfemanManifestoStImp();

	@Query("SELECT COUNT(c) FROM FsNfeMan c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND (c.st = 1 OR c.st_imp = 'N')")
	Integer verificaNfemanManifestoStImpLocal(Long id);

	@Query("SELECT c FROM FsNfeMan c WHERE c.cdpessoaemp.status = 'ATIVO' AND (c.st = 1 OR c.st_imp = 'N')")
	List<FsNfeMan> listaNfemanManifestoStImp();

	@Query("SELECT c FROM FsNfeMan c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND (c.st = 1 OR c.st_imp = 'N')")
	List<FsNfeMan> listaNfemanManifestoStImpLocal(Long id);
}
