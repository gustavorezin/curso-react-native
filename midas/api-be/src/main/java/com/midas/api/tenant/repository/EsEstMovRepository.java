package com.midas.api.tenant.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.EsEstMov;

@Repository
public interface EsEstMovRepository extends JpaRepository<EsEstMov, Long> {
	@Query("SELECT e FROM EsEstMov e WHERE e.tpdoc = ?1 AND e.iddoc = ?2 AND e.iddocitem = ?3 AND e.numdoc = ?4 AND e.tipo = ?5 AND e.cdproduto.id = ?6")
	EsEstMov getItem(String tpdoc, Long iddoc, Long iddocitem, Long numdoc, String tipo, Long produto);

	@Modifying
	@Transactional
	@Query("DELETE FROM EsEstMov e WHERE e.tpdoc = ?1 AND e.iddoc = ?2 AND e.numdoc = ?3 AND e.tipo = ?4")
	void removeItem(String tpdoc, Long iddoc, Long numdoc, String tipo);

	@Modifying
	@Transactional
	@Query("DELETE FROM EsEstMov e WHERE e.tpdoc = ?1 AND e.iddoc = ?2 AND e.numdoc = ?3")
	void removeItem(String tpdoc, Long iddoc, Long numdoc);

	@Modifying
	@Transactional
	@Query("DELETE FROM EsEstMov e WHERE e.tpdoc = ?1 AND e.iddoc = ?2 AND e.numdoc = ?3 AND e.qtd = ?4 AND e.cdproduto.id = ?5")
	void removeItemQtdProd(String tpdoc, Long iddoc, Long numdoc, BigDecimal qtd, Long produto);

	@Query("SELECT e FROM EsEstMov e WHERE e.cdpessoaemp.id = ?1 AND e.cdproduto.id = ?2 AND e.tipo = ?3 AND e.tpdoc = ?4 AND e.data >= ?5 "
			+ "AND e.iddoc != ?6")
	List<EsEstMov> findAllLocalEmpTipoTpdDifDias(Long emp, Long produto, String tipo, String tpdoc, Date data,
			Long iddoc);

	@Query("SELECT e FROM EsEstMov e WHERE e.cdpessoaemp.id = ?1 AND e.tpdoc = ?2 AND e.data BETWEEN ?3 AND ?4")
	List<EsEstMov> findAllLocalEmpTpdData(Long emp, String tpdoc, Date ini, Date fim);

	@Query("SELECT e FROM EsEstMov e WHERE e.cdpessoaemp.id = ?1 AND e.tipo = ?2 AND e.tpdoc = ?3 AND e.data BETWEEN ?4 AND ?5")
	List<EsEstMov> findAllLocalEmpTipoTpdData(Long emp, String tipo, String tpdoc, Date ini, Date fim);

	@Query("SELECT e FROM EsEstMov e WHERE e.cdpessoaemp.id = ?1 AND e.tipo = ?2 AND e.tpdoc = ?3 AND e.data BETWEEN ?4 AND ?5 GROUP BY e.iddoc")
	List<EsEstMov> findAllLocalEmpTipoTpdDataGroupDoc(Long emp, String tipo, String tpdoc, Date ini, Date fim);

	@Query(value = "SELECT * FROM es_estmov AS e WHERE e.cdpessoaemp_id = ?1 AND e.cdproduto_id = ?2 AND e.tipo = ?3 AND e.tpdoc = ?4 AND e.iddoc != ?5 "
			+ "ORDER BY e.id LIMIT 3", nativeQuery = true)
	List<EsEstMov> findAllLocalEmpTipoTpdDifUltimas3(Long emp, Long produto, String tipo, String tpdoc, Long iddoc);

	@Query(value = "SELECT * FROM es_estmov AS e WHERE e.cdpessoaemp_id = ?1 AND e.cdproduto_id = ?2 AND e.data <= ?3 "
			+ "ORDER BY e.id DESC LIMIT 1", nativeQuery = true)
	EsEstMov findUltimoProdutoDataUltima(Long emp, Long produto, Date data);
}
