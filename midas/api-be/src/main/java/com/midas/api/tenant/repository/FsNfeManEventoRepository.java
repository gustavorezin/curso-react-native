package com.midas.api.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsNfeManEvento;

@Repository
public interface FsNfeManEventoRepository extends JpaRepository<FsNfeManEvento, Long> {
	FsNfeManEvento findFirstByChaveAndCdpessoaempAndTpeventoAndNseqevento(String chave, CdPessoa cdpessoaemp,
			String tpevento, Integer nseqevento);
}
