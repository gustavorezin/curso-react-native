package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsInut;

@Repository
public interface FsInutRepository extends JpaRepository<FsInut, Integer> {
	@Query("SELECT c FROM FsInut c WHERE c.cdpessoaemp.id = ?1 AND c.modelo = ?2 AND c.numero = ?3 AND c.serie = ?4 AND c.tpamb = ?5")
	FsInut findByLocalModeloNumeroSerieAmb(Long idlocal, Integer modelo, Integer numeroInut, Integer serie,
			String ambiente);

	@Query("SELECT c FROM FsInut c WHERE YEAR(c.datacad) = ?1 AND c.modelo = ?2 ORDER BY c.numero ASC ")
	List<FsInut> listaFsInutAno(Integer ano, Integer modelo);

	@Query("SELECT c FROM FsInut c WHERE c.cdpessoaemp.id = ?1 AND YEAR(c.datacad) = ?2 AND c.modelo = ?3 ORDER BY c.numero ASC ")
	List<FsInut> listaFsInutLocalAno(Long local, Integer ano, Integer modelo);
}
