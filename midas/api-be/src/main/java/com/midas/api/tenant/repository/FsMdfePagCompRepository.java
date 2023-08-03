package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsMdfePagComp;

@Repository
public interface FsMdfePagCompRepository extends JpaRepository<FsMdfePagComp, Integer> {
	
}
