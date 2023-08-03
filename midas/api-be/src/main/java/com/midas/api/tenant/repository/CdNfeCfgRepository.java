package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdNfeCfg;

@Repository
public interface CdNfeCfgRepository extends JpaRepository<CdNfeCfg, Integer> {
	@Query("SELECT c FROM CdNfeCfg c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<CdNfeCfg> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query(value = "SELECT * FROM cd_nfecfg AS c WHERE c.cdpessoaemp_id = ?1 AND c.usa_nfce = 'S' ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	CdNfeCfg findByCdpessoaempNFCe(Long cdpessoaemp);

	@Query("SELECT c FROM CdNfeCfg c WHERE c.cdpessoaemp.id = ?1")
	List<CdNfeCfg> findAllByLocal(Long local);

	@Query("SELECT c FROM CdNfeCfg c WHERE c.cdpessoaemp.id = ?1 AND c.tipoop = ?2")
	List<CdNfeCfg> findAllByLocalAndTpop(Long local, String tipoop);

	@Query("SELECT c FROM CdNfeCfg c WHERE c.cdpessoaemp.id = ?1 AND c.tipoop = ?2 AND c.crtdest = ?3 AND c.cdestadoaplic.id = ?4 AND c.status = 'ATIVO'")
	CdNfeCfg getCdNfeCfgDestinoAtivo(Long local, String tpop, Integer crtdest, Integer coduf);

	@Query("SELECT c FROM CdNfeCfg c WHERE c.cdpessoaemp.id = ?1 AND c.tipoop = ?2 AND c.crtdest = ?3 AND c.cdestadoaplic.id = ?4 AND c.status = 'ATIVO'")
	List<CdNfeCfg> listaCdNfeCfgDestinoAtivo(Long local, String tpop, Integer crtdest, Integer coduf);

	@Query("SELECT c FROM CdNfeCfg c WHERE c.cdpessoaemp.id = ?1 AND c.crtdest = ?2 AND c.cdestadoaplic.id = ?3 AND c.status = 'ATIVO'")
	List<CdNfeCfg> listaCdNfeCfgDestinoSemOpAtivo(Long local, Integer crtdest, Integer coduf);

	@Modifying
	@Transactional
	@Query("UPDATE CdNfeCfg c SET c.usa_nfce = 'N' WHERE c.id != ?1 AND c.cdpessoaemp.id = ?2")
	void alteraTipoLocalSimilarLocal(Integer id, Long local);

	@Modifying
	@Transactional
	@Query("UPDATE CdNfeCfg c SET c.usa_nfce = 'N' WHERE c.cdpessoaemp.id = ?1")
	void alteraTipoLocalSimilar(Long local);
}
