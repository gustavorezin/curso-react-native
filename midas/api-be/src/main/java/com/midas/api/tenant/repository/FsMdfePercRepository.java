package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsMdfePerc;

@Repository
public interface FsMdfePercRepository extends JpaRepository<FsMdfePerc, Long> {
}
