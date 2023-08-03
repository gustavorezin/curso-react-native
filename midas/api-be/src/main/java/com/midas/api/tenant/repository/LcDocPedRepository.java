package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.LcDocPed;

@Repository
public interface LcDocPedRepository extends JpaRepository<LcDocPed, Long> {
}
