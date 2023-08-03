package com.midas.api.mt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.mt.entity.CdBancos;

@Repository
public interface CdBancosRepository extends JpaRepository<CdBancos, Integer> {
	CdBancos findFirstByCodigo(String codigo);

	@Query("SELECT c FROM CdBancos c WHERE c.codigo = ?1")
	Optional<CdBancos> findByCodigo(String codigo);

	@Query("SELECT c FROM CdBancos c WHERE c.nome LIKE %?1% OR c.codigo LIKE %?1% OR c.nomecompleto LIKE %?1%")
	Page<CdBancos> findAllByNomeBusca(String busca, Pageable pageable);

	List<CdBancos> findAllByBolhom(String bolhom);
}
