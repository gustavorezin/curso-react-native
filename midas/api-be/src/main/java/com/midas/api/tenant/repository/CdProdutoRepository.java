package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProduto;

@Repository
public interface CdProdutoRepository extends JpaRepository<CdProduto, Long> {
	List<CdProduto> findAllByTipo(String tipo);

	List<CdProduto> findAllByStatus(String status);

	@Query(value = "SELECT * FROM cd_produto AS c WHERE c.status = 'ATIVO' AND "
			+ "(CONVERT(c.id, CHAR) LIKE %?1% OR c.nome LIKE %?1% OR CONVERT(c.codigo, CHAR) LIKE %?1% OR c.cean LIKE %?1% OR c.ceantrib LIKE %?1% "
			+ "OR c.codalt LIKE %?1% OR c.ncm LIKE %?1% OR c.codserv LIKE %?1% OR c.ref LIKE %?1% OR c.descricao LIKE %?1%) ORDER BY c.nome ASC LIMIT 350", nativeQuery = true)
	List<CdProduto> findAllByNomeFilterByAtivo(String busca);

	@Query(value = "SELECT * FROM cd_produto AS c WHERE c.status = 'ATIVO' AND c.tipo = 'PRODUTO' AND (c.tipoitem = '00' OR c.tipoitem = '03' "
			+ "OR c.tipoitem = '04' OR c.tipoitem = '05' OR c.tipoitem = '06') AND (CONVERT(c.id, CHAR) LIKE %?1% OR c.nome LIKE %?1% OR CONVERT(c.codigo, CHAR) "
			+ "LIKE %?1% OR c.codalt LIKE %?1%) ORDER BY c.nome ASC LIMIT 25", nativeQuery = true)
	List<CdProduto> findAllByNomeFilterByTipoAcabadoAtivo(String busca);

	@Query(value = "SELECT * FROM cd_produto AS c WHERE c.status = 'ATIVO' AND (c.id = ?1 OR c.codigo = ?2 "
			+ " OR c.cean = ?3 OR c.ceantrib = ?3 OR c.codalt = ?3) ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	Optional<CdProduto> findByIdTipo(Long id, Integer codigo, String codigo2);

	@Query(value = "SELECT * FROM cd_produto AS c WHERE c.status = 'ATIVO' AND (c.codigo = ?1 "
			+ " OR c.cean = ?2 OR c.ceantrib = ?2 OR c.codalt = ?2) ORDER BY c.codalt DESC LIMIT 1", nativeQuery = true)
	Optional<CdProduto> findByIdTipoSemID(Integer codigo, String codigo2);

	@Query(value = "SELECT * FROM cd_produto AS c WHERE c.status = 'ATIVO' AND (c.tipoitem = '00' OR c.tipoitem = '03' "
			+ "	OR c.tipoitem = '04' OR c.tipoitem = '05' OR c.tipoitem = '06') AND (c.id = ?1 OR c.codigo = ?2 "
			+ " OR c.cean = ?3 OR c.ceantrib = ?3 OR c.codalt = ?3) ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	Optional<CdProduto> findByIdTipoExt(Long id, Integer codigo, String codigo2);

	@Query(value = "SELECT * FROM cd_produto AS c WHERE c.status = 'ATIVO' AND c.tipo = ?1 AND "
			+ "(CONVERT(c.id, CHAR) LIKE %?2% OR c.nome LIKE %?2% OR CONVERT(c.codigo, CHAR) LIKE %?2% OR c.cean LIKE %?2% OR c.ceantrib LIKE %?2% "
			+ "OR c.codalt LIKE %?2% OR c.ncm LIKE %?2% OR c.codserv LIKE %?2% OR c.ref LIKE %?2% OR c.descricao LIKE %?2%) ORDER BY c.nome ASC LIMIT ?3", nativeQuery = true)
	List<CdProduto> findAllByNomeFilterByAtivoTipoLimit(String tipo, String busca, Integer limite);

	@Query(value = "SELECT * FROM cd_produto AS c WHERE c.status = 'ATIVO' AND "
			+ "(CONVERT(c.id, CHAR) LIKE %?1% OR c.nome LIKE %?1% OR CONVERT(c.codigo, CHAR) LIKE %?1% OR c.cean LIKE %?1% OR c.ceantrib LIKE %?1% "
			+ "OR c.codalt LIKE %?1% OR c.ncm LIKE %?1% OR c.codserv LIKE %?1% OR c.ref LIKE %?1% OR c.descricao LIKE %?1%) ORDER BY c.nome ASC LIMIT ?2", nativeQuery = true)
	List<CdProduto> findAllByNomeFilterByAtivoLimit(String busca, Integer limite);

	@Query(value = "SELECT * FROM cd_produto AS c WHERE c.tipoitem = ?1 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	CdProduto ultimoProdutoTipo(String tipoitem);

	@Query("SELECT c FROM CdProduto c WHERE c.id = ?1 OR c.codigo = ?1")
	CdProduto findByIdCodigos(Long idprod);

	@Query(value = "SELECT * FROM cd_produto AS c WHERE c.id = ?1 OR c.codigo = ?1 LIMIT 1", nativeQuery = true)
	CdProduto findByIdCodigoUnico(Long idprod);

	@Modifying
	@Transactional
	@Query("UPDATE CdProduto c SET c.imagem = ?1 WHERE c.id = ?2")
	void updateImagem(byte[] imagem, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE CdProduto c SET c.ncm = ?1 WHERE c.id = ?2")
	void updateNCM(String novoncm, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE CdProduto c SET c.codserv = ?1 WHERE c.id = ?2")
	void updateCS(String novoCODSERV, Long id);
}
