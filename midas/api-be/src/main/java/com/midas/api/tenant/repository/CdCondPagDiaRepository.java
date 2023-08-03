package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCondPag;
import com.midas.api.tenant.entity.CdCondPagDia;

@Repository
public interface CdCondPagDiaRepository extends JpaRepository<CdCondPagDia, Integer> {
	void deleteByCdcondpag(CdCondPag condPag);
}
