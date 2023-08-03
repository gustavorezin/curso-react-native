package com.midas.api.tenant.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdPessoa;

@Repository
public interface CdPessoaRepository extends JpaRepository<CdPessoa, Long> {
	@Query("SELECT c FROM CdPessoa c WHERE c.tipo = ?1 ORDER BY c.nome ASC")
	List<CdPessoa> findAllByTipoTodos(String tipo);

	@Query("SELECT c FROM CdPessoa c WHERE c.status = ?1 ORDER BY c.nome ASC")
	List<CdPessoa> findAllByTodosAtivo(String status);

	@Query("SELECT c FROM CdPessoa c GROUP BY c.cpfcnpj ORDER BY c.nome ASC")
	List<CdPessoa> findAllByTodosBGCpfCnpj();

	@Query("SELECT c FROM CdPessoa c WHERE c.tipo = ?1 AND c.status = ?2 ORDER BY c.nome ASC")
	List<CdPessoa> findAllByTipoTodosAtivo(String tipo, String status);

	@Query("SELECT c FROM CdPessoa c WHERE c.id = ?1 AND c.tipo = ?2 ORDER BY c.nome ASC")
	List<CdPessoa> findAllByTipoTodosLocal(Long cdpessoaemp, String tipo);

	@Query("SELECT c FROM CdPessoa c WHERE c.emp = ?1 AND c.tipo = ?2 AND c.status = ?3 ORDER BY c.nome ASC")
	List<CdPessoa> findAllByTipoTodosLocalAtivo(Long cdpessoaemp, String tipo, String status);

	@Query("SELECT c FROM CdPessoa c WHERE c.id = ?1 AND c.status = ?2 ORDER BY c.nome ASC")
	List<CdPessoa> findAllByRespAtivo(Long id, String status);

	@Query("SELECT c FROM CdPessoa c WHERE c.id = ?1 AND c.tipo = ?2 AND c.status = ?3 ORDER BY c.nome ASC")
	List<CdPessoa> findAllByTipoTodosLocalIdAtivo(Long cdpessoaemp, String tipo, String status);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE c.tipo = ?1 AND "
			+ "(CONVERT(c.id, CHAR) LIKE %?2% OR c.nome LIKE %?2% OR c.fantasia LIKE %?2% "
			+ "OR c.cpfcnpj LIKE %?2% OR c.foneum LIKE %?2% OR c.email LIKE %?2% OR c.nomefin LIKE %?2% "
			+ "OR c.emailfin LIKE %?2%) ORDER BY c.nome,c.fantasia LIMIT 50", nativeQuery = true)
	List<CdPessoa> findAllByNomeFilterByTipo(String tipo, String busca);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE c.status = 'ATIVO' AND c.tipo = ?1 AND "
			+ "(CONVERT(c.id, CHAR) LIKE %?2% OR c.nome LIKE %?2% OR c.fantasia LIKE %?2% "
			+ "OR c.cpfcnpj LIKE %?2% OR c.foneum LIKE %?2% OR c.email LIKE %?2% OR c.nomefin LIKE %?2% "
			+ "OR c.emailfin LIKE %?2%) ORDER BY c.nome,c.fantasia LIMIT 50", nativeQuery = true)
	List<CdPessoa> findAllByNomeFilterByTipoAtivo(String tipo, String busca);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE c.cdpessoaresp_id = ?1 AND c.tipo = ?2 AND "
			+ "(CONVERT(c.id, CHAR) LIKE %?3% OR c.nome LIKE %?3% OR c.fantasia LIKE %?3% "
			+ "OR c.cpfcnpj LIKE %?3% OR c.foneum LIKE %?3% OR c.email LIKE %?3% OR c.nomefin LIKE %?3% "
			+ "OR c.emailfin LIKE %?3%) ORDER BY c.nome,c.fantasia LIMIT 50", nativeQuery = true)
	List<CdPessoa> findAllByNomeFilterByTipoResp(Long respvend, String tipo, String busca);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE c.cdpessoaresp_id = ?1 AND c.status = 'ATIVO' AND c.tipo = ?2 AND "
			+ "(CONVERT(c.id, CHAR) LIKE %?3% OR c.nome LIKE %?3% OR c.fantasia LIKE %?3% "
			+ "OR c.cpfcnpj LIKE %?3% OR c.foneum LIKE %?3% OR c.email LIKE %?3% OR c.nomefin LIKE %?3% "
			+ "OR c.emailfin LIKE %?3%) ORDER BY c.nome,c.fantasia LIMIT 50", nativeQuery = true)
	List<CdPessoa> findAllByNomeFilterByTipoAtivoResp(Long respvend, String tipo, String busca);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE c.emp = ?1 AND c.status = 'ATIVO' AND c.tipo = ?2 AND "
			+ "(CONVERT(c.id, CHAR) LIKE %?3% OR c.nome LIKE %?3% OR c.fantasia LIKE %?3% "
			+ "OR c.cpfcnpj LIKE %?3% OR c.foneum LIKE %?3% OR c.email LIKE %?3% OR c.nomefin LIKE %?3% "
			+ "OR c.emailfin LIKE %?3%) ORDER BY c.nome,c.fantasia LIMIT 50", nativeQuery = true)
	List<CdPessoa> findAllByNomeFilterByTipoAtivoLocal(Long local, String tipo, String busca);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE CONVERT(c.id, CHAR) LIKE %?1% OR "
			+ "c.nome LIKE %?1% OR c.fantasia LIKE %?1% OR c.cpfcnpj LIKE %?1% OR c.foneum LIKE %?1% OR "
			+ "c.email LIKE %?1% OR c.nomefin LIKE %?1% "
			+ "OR c.emailfin LIKE %?1% ORDER BY c.nome,c.fantasia LIMIT 50", nativeQuery = true)
	List<CdPessoa> findAllByNomeFilter(String busca);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE c.status = 'ATIVO' AND (CONVERT(c.id, CHAR) LIKE %?1% OR "
			+ "c.nome LIKE %?1% OR c.fantasia LIKE %?1% OR c.cpfcnpj LIKE %?1% OR c.foneum LIKE %?1% OR "
			+ "c.email LIKE %?1% OR c.nomefin LIKE %?1% "
			+ "OR c.emailfin LIKE %?1% ) ORDER BY c.nome,c.fantasia LIMIT 50", nativeQuery = true)
	List<CdPessoa> findAllByNomeFilterByAtivo(String busca);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE c.cdpessoaresp_id = ?1 AND (CONVERT(c.id, CHAR) LIKE %?2% OR "
			+ "c.nome LIKE %?2% OR c.fantasia LIKE %?2% OR c.cpfcnpj LIKE %?2% OR c.foneum LIKE %?2% OR c.email LIKE %?2% OR c.nomefin LIKE %?2% "
			+ "OR c.emailfin LIKE %?2% ) ORDER BY c.nome,c.fantasia LIMIT 50", nativeQuery = true)
	List<CdPessoa> findAllByNomeFilterByResp(Long respvend, String busca);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE c.cdpessoaresp_id = ?1 AND c.status = 'ATIVO' AND (CONVERT(c.id, CHAR) LIKE %?2% OR "
			+ "c.nome LIKE %?2% OR c.fantasia LIKE %?2% OR c.cpfcnpj LIKE %?2% OR c.foneum LIKE %?2% OR c.email LIKE %?2% OR c.nomefin LIKE %?2% "
			+ "OR c.emailfin LIKE %?2% ) ORDER BY c.nome,c.fantasia LIMIT 50", nativeQuery = true)
	List<CdPessoa> findAllByNomeFilterByAtivoResp(Long respvend, String busca);

	@Query(value = "SELECT * FROM cd_pessoa AS c JOIN cd_veiculo_rel AS v ON v.cdpessoamotora_id=c.id WHERE v.cdveiculo_id = ?1 AND "
			+ "c.status = 'ATIVO' ORDER BY c.nome ASC", nativeQuery = true)
	List<CdPessoa> findAllCdVeiculoRel(Integer idveic);

	CdPessoa findFirstByTipo(String tipo);

	CdPessoa findFirstByCpfcnpj(String cpfcnpj);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE c.tipo = ?1 AND c.status = ?2 AND c.emp = ?3 LIMIT 1", nativeQuery = true)
	CdPessoa findFirstByTipoLocal(String tipo, String status, Long id);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE c.tipo = ?1 AND c.status = ?2 AND c.emp = ?3 AND c.id = ?4 LIMIT 1", nativeQuery = true)
	CdPessoa findFirstByTipoLocalId(String tipo, String status, Long id, Long idv);

	@Query(value = "SELECT * FROM cd_pessoa AS c WHERE c.cpfcnpj = ?1 AND c.id != ?2 LIMIT 1", nativeQuery = true)
	CdPessoa findFirstByCpfcnpjDifId(String cpfcnpj, Long id);
	// CdPessoa findFirstByCpfcnpjAndTipo(String cpfcnpj, String tipo);

	@Query("SELECT COUNT(c) FROM CdPessoa c WHERE c.emp = ?1 AND c.tipo = ?2")
	Integer findAllByTipoTodosLocalQtd(Long local, String tipo);

	@Query("SELECT COUNT(c) FROM CdPessoa c WHERE c.tipo = ?1")
	Integer findAllByTipoTodosQtd(String tipo);

	@Query("SELECT COUNT(c) FROM CdPessoa c WHERE c.tipo = ?1 AND c.cdpessoaresp_id = ?2")
	Integer findAllByTipoTodosQtdRep(String tipo, Long cdpessoaresp);

	@Query("SELECT COUNT(c) FROM CdPessoa c WHERE c.emp = ?1 AND c.tipo = ?2 AND c.cdpessoaresp_id = ?3")
	Integer findAllByTipoTodosLocalQtdResp(Long local, String tipo, Long cdpessoaresp);

	@Query("SELECT COUNT(c) FROM CdPessoa c WHERE c.emp = ?1 AND c.tipo = ?2 AND c.status = ?3")
	Integer findAllByTipoTodosQtdLocalStatus(Long local, String tipo, String status);

	@Query("SELECT COUNT(c) FROM CdPessoa c WHERE c.tipo = ?1 AND c.status = ?2")
	Integer findAllByTipoTodosQtdStatus(String tipo, String status);

	@Modifying
	@Transactional
	@Query("UPDATE CdPessoa c SET c.imagem = ?1 WHERE c.id = ?2")
	void updateImagem(byte[] imagem, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE CdPessoa c SET c.cedente_id_tecno = ?1, c.cedente_token_tecno = ?2 WHERE c.id = ?3")
	void updateClienteIdTecno(String cedente_id, String token, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE CdPessoa c SET c.cdprodutotab_id = ?1 WHERE c.id = ?2")
	void updatePessoaTabPreco(Integer cdprodutotab_id, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE CdPessoa c SET c.cdnfecfg_id = ?1 WHERE c.id = ?2")
	void updatePessoaCfgFiscal(Integer cdnfecfg_id, Long id);
}
