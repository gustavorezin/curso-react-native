package com.midas.api.tenant.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfse;

@Repository
public interface FsNfseRepository extends JpaRepository<FsNfse, Long> {
	@Query(value = "SELECT * FROM fs_nfse AS c WHERE c.id = ?1 AND c.cdpessoaemp_id = ?2", nativeQuery = true)
	Optional<FsNfse> findByIdAndCdpessoaemp(Long id, Long cdpessoaemp);

	@Query(value = "SELECT * FROM fs_nfse AS c WHERE c.cdpessoaemp_id = ?1 AND c.rpsserie = ?2 AND c.tpamb = ?3 AND c.tipo = ?4 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	FsNfse ultimaByIdAndCdpessoaempSerie(Long cdpessoaemp, Integer serie, String ambiente, String tipo);

	@Query("SELECT COUNT(c) FROM FsNfse c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.tipo = ?1 AND c.status = ?2")
	Integer verificaNfseStTipo(String tipo, Integer status);

	@Query("SELECT COUNT(c) FROM FsNfse c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.tipo = ?2 AND c.status = ?3")
	Integer verificaNfseStTipoLocal(Long local, String tipo, Integer status);

	@Query("SELECT c FROM FsNfse c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.tipo = ?2 AND c.status = ?3")
	List<FsNfse> listaNfseStTipoLocal(Long local, String tipo, Integer status);

	@Query("SELECT c FROM FsNfse c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.tipo = ?1 AND c.status = ?2")
	List<FsNfse> listaNfseStTipo(String tipo, Integer status);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfse f SET f.xml = ?1, f.status = ?2 WHERE f.id = ?3")
	void updateXMLNFSeStatus(String xml, Integer status, Long nfse);

	@Query("SELECT c FROM FsNfse c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND c.demis BETWEEN ?2 AND ?3 "
			+ "AND c.tipo = ?4 AND (c.status = ?5) ORDER BY c.demis ASC")
	List<FsNfse> listaNfseEntsaiTipoStatus(Long local, Date dataini, Date datafim, String tipo, Integer status1);
}
