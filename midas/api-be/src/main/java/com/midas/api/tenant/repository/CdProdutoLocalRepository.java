package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdProdutoLocal;

@Repository
public interface CdProdutoLocalRepository extends JpaRepository<CdProdutoLocal, Long> {
}
