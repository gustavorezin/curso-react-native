package com.midas.api.tenant.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoNs;

@Repository
public interface CdProdutoNsRepository extends JpaRepository<CdProdutoNs, Integer> {
	@Query("SELECT c FROM CdProdutoNs c WHERE c.cdpessoaemp.id = ?1")
	Page<CdProdutoNs> findAllByLocal(Long local, Pageable pageable);

	@Query("SELECT c FROM CdProdutoNs c WHERE c.cdpessoaemp.id = ?1 AND c.status = ?2")
	List<CdProdutoNs> findAllByLocal(Long local, String status);

	@Modifying
	@Transactional
	@Query("UPDATE CdProdutoNs c SET c.contador = ?1 WHERE c.id = ?2")
	void updateContador(Integer contador, Integer id);
}
