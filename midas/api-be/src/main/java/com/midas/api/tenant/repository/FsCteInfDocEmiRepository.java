package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsCteInfDocEmi;

@Repository
public interface FsCteInfDocEmiRepository extends JpaRepository<FsCteInfDocEmi, Long> {

	@Query(value = "SELECT * FROM fs_cteinf_docemi AS d JOIN fs_cteinf_docemi_cte AS c ON d.id = c.fscteinfdocemi_id "
			+ "WHERE d.id = ?1 AND c.chave = ?2", nativeQuery = true)
	FsCteInfDocEmi findByCteAndChave(Long id, String chave);
}
