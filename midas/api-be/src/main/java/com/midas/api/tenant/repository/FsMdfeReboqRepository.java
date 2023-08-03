package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsMdfeReboq;

@Repository
public interface FsMdfeReboqRepository extends JpaRepository<FsMdfeReboq, Long> {
}
