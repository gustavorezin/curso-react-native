package com.midas.api.tenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdXmlAutoriza;

@Repository
public interface CdXmlAutorizaRepository extends JpaRepository<CdXmlAutoriza, Integer> {
	@Query(value = "SELECT * FROM cd_xmlautoriza AS c WHERE c.id = ?1 AND c.cdpessoaemp_id = ?2", nativeQuery = true)
	Optional<CdXmlAutoriza> findByIdAndCdpessoaemp(Integer id, Long cdpessoaemp);

	@Query(value = "SELECT * FROM cd_xmlautoriza AS c WHERE c.cdpessoaemp_id = ?1 AND c.tpdoc = ?2", nativeQuery = true)
	List<CdXmlAutoriza> findAllByTpdocAndLocal(Long local, String tpdoc);

	@Query(value = "SELECT * FROM cd_xmlautoriza AS c WHERE c.cdpessoaemp_id = ?1 AND c.tpdoc = ?2 AND c.addauto = ?3", nativeQuery = true)
	List<CdXmlAutoriza> findAllByTpdocAndLocalAutoAdd(Long local, String tpdoc, String autoadd);

	@Query("SELECT c FROM CdXmlAutoriza c WHERE c.nome LIKE %?1% OR c.cnpj LIKE %?1%")
	Page<CdXmlAutoriza> findAllByNomeBusca(String busca, Pageable pageable);

	@Query(value = "SELECT * FROM cd_xmlautoriza AS c WHERE c.cdpessoaemp_id = ?1 AND (c.nome LIKE %?2% OR c.cnpj LIKE %?2%)", nativeQuery = true)
	Page<CdXmlAutoriza> findAllByNomeBuscaLocal(Long cdpessoaemp, String busca, Pageable pageable);

	@Query("SELECT c FROM CdXmlAutoriza c WHERE c.cdpessoaemp.id = ?1 AND c.tpdoc = ?2 AND c.cnpj = ?3 AND c.id != ?4")
	CdXmlAutoriza findByCdpessoaempAndTpdocAndCnpj(Long cdpessoaemp, String tpdoc, String cnpj, Integer id);
}
