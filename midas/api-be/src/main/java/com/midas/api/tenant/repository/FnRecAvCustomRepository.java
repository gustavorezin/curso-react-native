package com.midas.api.tenant.repository;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FnRecAv;

@Repository
public class FnRecAvCustomRepository {
	private final EntityManager em;

	@Autowired
	public FnRecAvCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FnRecAv> listaFnRecAv(Long local, String tipo, Date dataini, Date datafim, String busca, String ordem,
			String ordemdir, Pageable pageable) {
		String query = retFnRecAv(local, tipo, dataini, datafim, busca, ordem, ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FnRecAv> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<FnRecAv> results = em.createQuery(query, FnRecAv.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<FnRecAv> listaFnRecAvExcel(Long local, String tipo, Date dataini, Date datafim, String busca,
			String ordem, String ordemdir) {
		String query = retFnRecAv(local, tipo, dataini, datafim, busca, ordem, ordemdir);
		List<FnRecAv> results = em.createQuery(query, FnRecAv.class).getResultList();
		return results;
	}

	public List<FnRecAv> listaFnRecAvValores(Long local, String tipo, Date dataini, Date datafim, String busca) {
		String query = retFnRecAv(local, tipo, dataini, datafim, busca, "id", "ASC");
		List<FnRecAv> results = em.createQuery(query, FnRecAv.class).getResultList();
		return results;
	}

	private String retFnRecAv(Long local, String tipo, Date dataini, Date datafim, String busca, String ordem,
			String ordemdir) {
		String query = "SELECT c FROM FnRecAv c WHERE c.cdpessoaemp.status = 'ATIVO' AND ";
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp = " + local + " AND ";
		}
		// Datas
		query += "c.data BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Tipo
		if (!tipo.equals("X")) {
			query += "AND c.tipo = '" + tipo + "' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (c.num LIKE '%" + busca + "%' OR c.ref LIKE '%" + busca + "%' OR c.cdpessoapara.nome LIKE '%"
					+ busca + "%') ";
		}
		// Ordem
		query += "ORDER BY " + ordem + " " + ordemdir;
		return query;
	}
}
