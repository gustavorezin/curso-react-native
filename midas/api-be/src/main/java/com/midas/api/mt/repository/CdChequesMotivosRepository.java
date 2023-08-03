package com.midas.api.mt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.mt.entity.CdChequeMotivos;

@Repository
public interface CdChequesMotivosRepository extends JpaRepository<CdChequeMotivos, Integer> {
}
