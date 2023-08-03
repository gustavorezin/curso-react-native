package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.EsEstExt;

@Repository
public interface EsEstExtRepository extends JpaRepository<EsEstExt, Long> {
	EsEstExt findByCdprodutoAndCdpessoavendedor(CdProduto cdproduto, CdPessoa cdpessoavendedor);
}
