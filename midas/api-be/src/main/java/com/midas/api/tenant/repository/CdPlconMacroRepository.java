package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdPlconMacro;

@Repository
public interface CdPlconMacroRepository extends JpaRepository<CdPlconMacro, Integer> {
	@Query("SELECT c FROM CdPlconMacro c WHERE c.id = ?1 AND c.cdplconconta.cdpessoaemp.id = ?2")
	Optional<CdPlconMacro> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query("SELECT c FROM CdPlconMacro c WHERE c.cdplconconta.id = ?1 AND c.masc = ?2")
	CdPlconMacro getMascMacro(Integer conta, Integer masc);

	@Query("SELECT c FROM CdPlconMacro c WHERE c.cdplconconta.cdpessoaemp.id = ?1 AND c.cdplconconta.id = ?2 ORDER BY c.masc ASC")
	List<CdPlconMacro> findAllByLocal(Long idlocal, Integer conta);

	@Query("SELECT c FROM CdPlconMacro c WHERE c.cdplconconta.id = ?1 AND c.masc = ?2 AND c.id != ?3")
	CdPlconMacro getMascMacroDif(Integer conta, Integer masc, Integer id);

	@Modifying
	@Transactional
	@Query("UPDATE CdPlconMacro c SET c.exibe = ?1 WHERE c.id = ?2")
	void exibirMacro(String exibe, Integer id);
}
