package com.midas.api.tenant.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FnTituloCcusto;

@Repository
public interface FnTituloCcustoRepository extends JpaRepository<FnTituloCcusto, Long> {
	@Query("SELECT c FROM FnTituloCcusto c WHERE c.cdccusto.id = ?1 AND c.fntitulo.vence BETWEEN ?2 AND ?3 AND c.fntitulo.vsaldo > 0")
	List<FnTituloCcusto> findAllByCdCustoEmaberto(Integer centro, Date dataini, Date datafim);

	@Query("SELECT c FROM FnTituloCcusto c WHERE c.fntitulo.cdpessoaemp.status = 'ATIVO' AND c.fntitulo.cdpessoaemp.id = ?1 AND c.cdccusto.id = ?2 AND "
			+ "c.fntitulo.vence BETWEEN ?3 AND ?4 AND c.fntitulo.vsaldo > 0")
	List<FnTituloCcusto> findAllByCdCustoEmabertoLocal(Long local, Integer centro, Date dataini, Date datafim);

	@Query("SELECT c FROM FnTituloCcusto c WHERE c.cdccusto.id = ?1 AND c.fntitulo.databaixa BETWEEN ?2 AND ?3 AND c.fntitulo.vpago > 0")
	List<FnTituloCcusto> findAllByCdCustoPagos(Integer centro, Date dataini, Date datafim);

	@Query("SELECT c FROM FnTituloCcusto c WHERE c.fntitulo.cdpessoaemp.status = 'ATIVO' AND c.fntitulo.cdpessoaemp.id = ?1 AND c.cdccusto.id = ?2 "
			+ "AND c.fntitulo.databaixa BETWEEN ?3 AND ?4 AND c.fntitulo.vpago > 0")
	List<FnTituloCcusto> findAllByCdCustoPagosLocal(Long local, Integer centro, Date dataini, Date datafim);

	@Modifying
	@Transactional
	@Query("DELETE FROM FnTituloCcusto c WHERE c.fntitulo.id = ?1")
	void removeItensCcusto(Long id);

	@Modifying
	@Transactional
	@Query("DELETE FROM FnTituloCcusto c WHERE c.fntitulo.id = ?1")
	void removeItensCcustos(Long id);

	@Query(value = "SELECT * FROM fn_titulo_ccusto AS c WHERE c.fntitulo_id = ?1 ORDER BY c.pvalor DESC LIMIT 1", nativeQuery = true)
	FnTituloCcusto findByTitulo(Long id);
}
