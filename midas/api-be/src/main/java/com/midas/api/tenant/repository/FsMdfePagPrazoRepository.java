package com.midas.api.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsMdfePagPrazo;

@Repository
public interface FsMdfePagPrazoRepository extends JpaRepository<FsMdfePagPrazo, Integer> {	
	@Query("SELECT c FROM FsMdfePagPrazo c WHERE c.fsmdfepag.id = ?1 ORDER BY c.dvenc ASC")
	List<FsMdfePagPrazo> listaMdfePagPrazo(Integer id);
}
