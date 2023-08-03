package com.midas.api.mt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.mt.entity.Acesso;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Integer> {
	List<Acesso> findAllByLocalOrderByNomeAsc(String local);
}
