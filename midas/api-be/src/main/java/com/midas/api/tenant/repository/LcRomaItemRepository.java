package com.midas.api.tenant.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.LcRomaItem;

@Repository
public interface LcRomaItemRepository extends JpaRepository<LcRomaItem, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE LcRomaItem l SET l.ordem = ?1 WHERE l.id = ?2 ")
	void atualizaItemDocOrdem(Integer ordem, Long id);

	@Modifying
	@Transactional
	@Query("DELETE FROM LcRomaItem l WHERE l.lcroma.id = ?1 ")
	void removeItemDocTodos(Long id);

	@Query("SELECT c FROM LcRomaItem c WHERE c.lcdoc.id = ?1")
	LcRomaItem findByLcDoc(Long iddoc);

	@Query(value = "SELECT * FROM lc_romaitem AS c WHERE c.lcroma_id = ?1 ORDER BY c.ordem DESC LIMIT 1", nativeQuery = true)
	LcRomaItem ultimoLcRomaItem(Long idroma);
}
