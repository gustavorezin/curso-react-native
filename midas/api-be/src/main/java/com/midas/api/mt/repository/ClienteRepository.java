package com.midas.api.mt.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.mt.entity.Cliente;
import com.midas.api.mt.entity.Tenant;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	@Query("SELECT c FROM Cliente c WHERE c.emaillogin = ?1")
	Cliente findByEmaillogin(String emaillogin);

	Cliente findClienteByChaveativa(String chave);

	@Modifying
	@Transactional
	@Query("UPDATE Cliente c SET c.status = ?1 WHERE c.id = ?2")
	void ativarCadastro(String status, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE Cliente c SET c.senhalogin = ?1 WHERE c.id = ?2")
	void alterarSenha(String senhalogin, Long id);

	Optional<Cliente> findByIdAndTenant(Long id, Tenant tenant);

	@Query(value = "SELECT * FROM cliente AS c WHERE c.tenant_id = ?1 AND c.role_id = ?2 ORDER BY c.id ASC LIMIT 1", nativeQuery = true)
	Cliente findByTenantUnico(Long idt, Integer role);

	@Query("SELECT c FROM Cliente c WHERE c.tenant = ?1 AND (c.nome LIKE %?2% OR c.sobrenome LIKE %?2% OR c.emaillogin LIKE %?2%)")
	Page<Cliente> findAllByTenantNomeBusca(Tenant tenant, String busca, Pageable pageable);

	@Query("SELECT c FROM Cliente c WHERE c.tenant.dbname LIKE %?1% OR c.nome LIKE %?1% OR c.sobrenome LIKE %?1% OR "
			+ "c.emaillogin LIKE %?1% OR c.cidade LIKE %?1% OR c.cdpessoaempnome LIKE %?1%")
	Page<Cliente> findAllByNomeBusca(String busca, Pageable pageable);

	@Modifying
	@Transactional
	@Query("UPDATE Cliente c SET c.cdpessoaemp = ?1, cdpessoaempnome = ?2 WHERE c.id = ?3")
	void updateLocal(Long emp, String nomeemp, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE Cliente c SET c.imagem = ?1 WHERE c.id = ?2")
	void updateImagem(byte[] imagem, Long id);
}
