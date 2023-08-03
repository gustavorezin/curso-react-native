package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.midas.api.tenant.entity.SisAcesso;

@Repository
public interface SisAcessoRepository extends JpaRepository<SisAcesso, Long> {
	@Query("SELECT c FROM SisAcesso c WHERE c.role = ?1 AND c.local = ?2")
	List<SisAcesso> findAllByRoleId(Integer role, String local);

	@Query("SELECT c FROM SisAcesso c WHERE c.role = ?1 AND c.local = ?2 AND (c.nome LIKE %?3%)")
	Page<SisAcesso> findAllByRoleIdBusca(Integer role, String local, String busca, Pageable pageable);

	@Transactional
	void deleteByRoleAndLocal(Integer role, String local);

	SisAcesso findByRoleAndAcesso(Integer role, Integer acesso);
}
