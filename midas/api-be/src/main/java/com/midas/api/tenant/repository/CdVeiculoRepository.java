package com.midas.api.tenant.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdVeiculo;

@Repository
public interface CdVeiculoRepository extends JpaRepository<CdVeiculo, Integer> {
	@Query("SELECT c FROM CdVeiculo c WHERE c.cdpessoaemp.id = ?1")
	List<CdVeiculo> findAllLocalTodos(Long emp);

	@Query("SELECT c FROM CdVeiculo c WHERE c.placa LIKE %?1% OR c.renavam LIKE %?1% OR c.pnome LIKE %?1%")
	Page<CdVeiculo> findAllByNomeBusca(String busca, Pageable pageable);

	@Query("SELECT c FROM CdVeiculo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND (c.placa LIKE %?2% OR c.renavam LIKE %?2% OR c.pnome LIKE %?2%)")
	Page<CdVeiculo> findAllByNomeBuscaLocal(Long local, String busca, Pageable pageable);

	@Query("SELECT c FROM CdVeiculo c WHERE c.placa LIKE %?1% OR c.renavam LIKE %?1% OR c.pnome LIKE %?1%")
	List<CdVeiculo> findAllByNomeBusca(String busca);

	@Query("SELECT c FROM CdVeiculo c WHERE c.cdpessoaemp.status = 'ATIVO' AND c.cdpessoaemp.id = ?1 AND (c.placa LIKE %?2% OR c.renavam LIKE %?2% OR c.pnome LIKE %?2%)")
	List<CdVeiculo> findAllByNomeBuscaLocal(Long local, String busca);

	@Query(value = "SELECT COUNT(id) FROM cd_veiculo_rel AS c WHERE c.cdpessoamotora_id = ?1 AND c.cdveiculo_id = ?2", nativeQuery = true)
	Long verVinculo(Long id, Integer idveic);

	@Query(value = "SELECT * FROM cd_veiculo AS c WHERE c.cdpessoaemp_id = ?1 LIMIT 1", nativeQuery = true)
	CdVeiculo findFirstByEmp(Long emp);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO cd_veiculo_rel (cdpessoamotora_id,cdveiculo_id) VALUES (?1,?2)", nativeQuery = true)
	void addVinculo(Long id, Integer idveic);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM cd_veiculo_rel AS c WHERE c.cdpessoamotora_id = ?1 AND c.cdveiculo_id = ?2", nativeQuery = true)
	void removeVinculo(Long id, Integer idveic);
}
