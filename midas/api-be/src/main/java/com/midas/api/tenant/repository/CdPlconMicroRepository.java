package com.midas.api.tenant.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdPlconMicro;

@Repository
public interface CdPlconMicroRepository extends JpaRepository<CdPlconMicro, Integer> {
	@Query("SELECT c FROM CdPlconMicro c WHERE c.id = ?1 AND c.cdplconmacro.cdplconconta.cdpessoaemp.id = ?2")
	Optional<CdPlconMicro> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query("SELECT c FROM CdPlconMicro c WHERE c.cdplconmacro.id = ?1 AND c.masc = ?2")
	CdPlconMicro getMascMicro(Integer macro, Integer masc);

	@Query("SELECT c FROM CdPlconMicro c WHERE c.cdplconmacro.id = ?1 AND c.masc = ?2 AND c.id != ?3")
	CdPlconMicro getMascMicroDif(Integer macro, Integer masc, Integer id);

	@Query("SELECT c FROM CdPlconMicro c WHERE c.tipolocal = ?1 AND c.cdplconmacro.cdplconconta.cdpessoaemp.id = ?2")
	CdPlconMicro findByLocalTipoAndCdpessoaemp(String tipolocal, Long cdpessoaemp);

	@Modifying
	@Transactional
	@Query("UPDATE CdPlconMicro c SET c.exibe = ?1 WHERE c.id = ?2")
	void exibirMicro(String exibe, Integer id);

	@Modifying
	@Transactional
	@Query("UPDATE CdPlconMicro c SET c.tipolocal = '00' WHERE c.id != ?1 AND c.tipolocal = ?2")
	void alteraTipoLocalSimilar(Integer id, String tipolocal);

	@Modifying
	@Transactional
	@Query("UPDATE CdPlconMicro c SET c.tipolocal = '00' WHERE c.tipolocal = ?1")
	void alteraTipoLocalSimilarSemID(String tipolocal);
}
