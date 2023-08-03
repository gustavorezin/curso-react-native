package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdPlconConta;

@Repository
public interface CdPlconContaRepository extends JpaRepository<CdPlconConta, Integer> {
	@Query("SELECT c FROM CdPlconConta c WHERE c.cdpessoaemp.id = ?1 ORDER BY c.masc ASC")
	List<CdPlconConta> findAllByLocal(Long emp);

	@Query("SELECT c FROM CdPlconConta c WHERE c.cdpessoaemp.id = ?1 AND (c.tipo = ?2 OR c.tipo = 'X') ORDER BY c.masc ASC")
	List<CdPlconConta> findAllByLocalandTipo(Long emp, String tipo);

	@Query("SELECT c FROM CdPlconConta c WHERE c.tipo = ?1 OR c.tipo = 'X' ORDER BY c.masc ASC")
	List<CdPlconConta> findAllByTipo(String tipo);

	@Query("SELECT c FROM CdPlconConta c WHERE c.id = ?1 AND c.cdpessoaemp.id = ?2")
	Optional<CdPlconConta> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query("SELECT c FROM CdPlconConta c WHERE c.cdpessoaemp.id = ?1 AND c.masc = ?2")
	CdPlconConta getMascConta(Long emp, Integer masc);

	@Query("SELECT c FROM CdPlconConta c WHERE c.cdpessoaemp.id = ?1 AND c.masc = ?2 AND c.id != ?3")
	CdPlconConta getMascContaDif(Long id, Integer masc, Integer idconta);

	@Modifying
	@Transactional
	@Query("UPDATE CdPlconConta c SET c.exibe = ?1 WHERE c.id = ?2")
	void exibirConta(String exibe, Integer id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE cd_plcon_conta AS c SET c.cdpessoaemp_id = ?1", nativeQuery = true)
	void atualizarLocal(Long id);
}
