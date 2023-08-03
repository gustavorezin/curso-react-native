package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsMdfePag;

@Repository
public interface FsMdfePagRepository extends JpaRepository<FsMdfePag, Integer> {
	
}
