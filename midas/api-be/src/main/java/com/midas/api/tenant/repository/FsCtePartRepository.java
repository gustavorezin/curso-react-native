package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsCtePart;
import com.midas.api.tenant.entity.FsNfeAut;

@Repository
public interface FsCtePartRepository extends JpaRepository<FsCtePart, Long> {
}
