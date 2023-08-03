package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FnTituloRel;

@Repository
public interface FnTituloRelRepository extends JpaRepository<FnTituloRel, Long> {
	@Query("SELECT c FROM FnTituloRel c WHERE c.fntituloant.id = ?1")
	List<FnTituloRel> listaTitulosRel(Long titulo);
}
