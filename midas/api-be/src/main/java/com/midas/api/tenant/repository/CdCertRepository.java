package com.midas.api.tenant.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCert;
import com.midas.api.tenant.entity.CdPessoa;

@Repository
public interface CdCertRepository extends JpaRepository<CdCert, Integer> {
	@Query(value = "SELECT * FROM cd_cert AS c WHERE c.cdpessoaemp_id = ?1", nativeQuery = true)
	Page<CdCert> findAllLocal(Long emp, Pageable pageable);

	@Query(value = "SELECT * FROM cd_cert AS c JOIN cd_pessoa AS p ON c.cdpessoaemp_id=p.id WHERE c.cdpessoaemp_id = ?1 AND "
			+ "p.status = 'ATIVO' ORDER BY p.nome ASC", nativeQuery = true)
	List<CdCert> findAllLocalTodos(Long emp);

	@Query(value = "SELECT COUNT(id) FROM cd_cert AS c WHERE c.cdpessoaemp_id = ?1", nativeQuery = true)
	Integer verificaExiste(Long emp);

	Optional<CdCert> findByCdpessoaemp(CdPessoa cdpessoaemp);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO cd_cert (cdpessoaemp_id,datacad,dataexp,nomearq,arquivo,senha) VALUES (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
	void addCert(Long id, Date datacad, Date dataexp, String nomearq, byte[] arquivo, String senha);

	@Query(value = "SELECT * FROM cd_cert AS c WHERE c.id = ?1 AND c.cdpessoaemp_id = ?2", nativeQuery = true)
	Optional<CdCert> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query(value = "SELECT COUNT(id) FROM cd_cert AS c WHERE c.cdpessoaemp_id = ?1 AND (c.dataexp BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL ?2 DAY) OR "
			+ "c.dataexp <= CURDATE())", nativeQuery = true)
	Integer verificaExpiraLocal(Long local, Integer dia);

	@Query(value = "SELECT COUNT(id) FROM cd_cert AS c WHERE c.dataexp BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL ?1 DAY) OR "
			+ "c.dataexp <= CURDATE()", nativeQuery = true)
	Integer verificaExpira(Integer dia);

	@Query("SELECT c FROM CdCert c WHERE c.cdpessoaemp.status = ?1")
	List<CdCert> findByStatus(String status);
}
