package com.midas.api.tenant.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FnTituloCcusto;

@Repository
public class FnTituloCcustoCustomRepository {
	private final EntityManager em;

	@Autowired
	public FnTituloCcustoCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FnTituloCcusto> listaFnTituloCcusto(Long local, String tipo, Integer centro, Date dataini, Date datafim,
			String status, String ordem, String ordemdir, Pageable pageable) {
		String query = retFnTituloCcusto(local, tipo, centro, dataini, datafim, status, ordem, ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FnTituloCcusto> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<FnTituloCcusto> results = em.createQuery(query, FnTituloCcusto.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	// Calculo saldo geral para Relatorio
	public BigDecimal[] valorSaldo(List<FnTituloCcusto> titulosAberto, List<FnTituloCcusto> titulosPagos) {
		BigDecimal[] valores = new BigDecimal[7];
		// Receber em aberto
		valores[0] = new BigDecimal(0);
		for (FnTituloCcusto ft : titulosAberto) {
			if (ft.getFntitulo().getVsaldo().compareTo(new BigDecimal(0)) > 0
					&& ft.getFntitulo().getTipo().equals("R")) {
				BigDecimal valor = new BigDecimal(0);
				valor = (ft.getFntitulo().getVsaldo().multiply(ft.getPvalor())).divide(new BigDecimal(100));
				valores[0] = valores[0].add(valor);
			}
		}
		// Pagar em aberto
		valores[1] = new BigDecimal(0);
		for (FnTituloCcusto ft : titulosAberto) {
			if (ft.getFntitulo().getVsaldo().compareTo(new BigDecimal(0)) > 0
					&& ft.getFntitulo().getTipo().equals("P")) {
				BigDecimal valor = new BigDecimal(0);
				valor = (ft.getFntitulo().getVsaldo().multiply(ft.getPvalor())).divide(new BigDecimal(100));
				valores[1] = valores[1].add(valor);
			}
		}
		// Receber baixados
		valores[2] = new BigDecimal(0);
		for (FnTituloCcusto ft : titulosPagos) {
			if (ft.getFntitulo().getVpago().compareTo(new BigDecimal(0)) > 0
					&& ft.getFntitulo().getTipo().equals("R")) {
				BigDecimal valor = new BigDecimal(0);
				valor = (ft.getFntitulo().getVpago().multiply(ft.getPvalor())).divide(new BigDecimal(100));
				valores[2] = valores[2].add(valor);
			}
		}
		// Pagar baixados
		valores[3] = new BigDecimal(0);
		for (FnTituloCcusto ft : titulosPagos) {
			if (ft.getFntitulo().getVpago().compareTo(new BigDecimal(0)) > 0
					&& ft.getFntitulo().getTipo().equals("P")) {
				BigDecimal valor = new BigDecimal(0);
				valor = (ft.getFntitulo().getVpago().multiply(ft.getPvalor())).divide(new BigDecimal(100));
				valores[3] = valores[3].add(valor);
			}
		}
		return valores;
	}

	private String retFnTituloCcusto(Long local, String tipo, Integer centro, Date dataini, Date datafim, String status,
			String ordem, String ordemdir) {
		String query = "SELECT c FROM FnTituloCcusto c WHERE c.fntitulo.cdpessoaemp.status = 'ATIVO' AND ";
		// Tipo
		if (!tipo.equals("X")) {
			query += "c.fntitulo.tipo = '" + tipo + "' AND ";
		}
		// Local
		if (local > 0) {
			query += "c.fntitulo.cdpessoaemp.id = " + local + " AND ";
		}
		// Centro de custo
		if (centro > 0) {
			query += "c.cdccusto.id = " + centro + " ";
		}
		// Status se aberto ou quitado
		if (status.equals("A")) {
			query += "AND c.fntitulo.vsaldo != 0 AND ";
			query += "c.fntitulo.vence BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		}
		if (status.equals("F")) {
			query += "AND (c.fntitulo.vsaldo = 0 OR c.fntitulo.vpago > 0) AND ";
			query += "c.fntitulo.databaixa BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		}
		// Ordem
		query += "ORDER BY c.fntitulo." + ordem + " " + ordemdir;
		return query;
	}
}
