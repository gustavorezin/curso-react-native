package com.midas.api.mt.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.mt.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
	@Query("SELECT max(id) FROM Tenant")
	Long getMaxTenantId();

	@Modifying
	@Transactional
	@Query("UPDATE Tenant t SET t.status = ?1 WHERE t.id = ?2")
	void ativarCadastro(String status, Long id);

	@Modifying
	@Transactional
	@Query("UPDATE Tenant t SET t.status = ?1, t.perteste = ?2, datafimteste = ?3 WHERE t.id = ?4")
	void ativarCadastroTeste(String status, String perteste, Date datafimteste, Long id);

	Tenant findByDbname(String database);

	@Query("SELECT COUNT(*) FROM Tenant t WHERE t.id = ?1 AND t.backupult = CURDATE()")
	Integer getBackupUlt(Long id);

	@Modifying
	@Transactional
	@Query("UPDATE Tenant t SET t.backupult = CURDATE(),t.backupulthr = NOW() WHERE t.id = ?1")
	void updateBkpUlt(Long id);

	@Modifying
	@Transactional
	@Query("UPDATE Tenant t SET t.sqlconfig = 'S' WHERE t.id = ?1")
	void updateSqlPadrao(Long id);
}
