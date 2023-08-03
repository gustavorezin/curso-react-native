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

import com.midas.api.tenant.entity.FnCheque;

@Repository
public class FnChequeCustomRepository {
	private final EntityManager em;

	@Autowired
	public FnChequeCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FnCheque> listaFnCheques(Long local, Integer caixa, Date dataini, Date datafim, String busca,
			String ove, String ordem, String ordemdir, String tipo, String status, Pageable pageable) {
		String query = retFnCheque(local, caixa, dataini, datafim, busca, ove, ordem, ordemdir, tipo, status);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FnCheque> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<FnCheque> results = em.createQuery(query, FnCheque.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<FnCheque> listaFnChequeExcel(Long local, Integer caixa, Date dataini, Date datafim, String busca,
			String ove, String ordem, String ordemdir, String tipo, String status) {
		String query = retFnCheque(local, caixa, dataini, datafim, busca, ove, ordem, ordemdir, tipo, status);
		List<FnCheque> results = em.createQuery(query, FnCheque.class).getResultList();
		return results;
	}

	public List<FnCheque> listaFnChequeValores(Long local, Integer caixa, Date dataini, Date datafim, String busca,
			String ove, String tipo, String status) {
		String query = retFnCheque(local, caixa, dataini, datafim, busca, ove, "id", "ASC", tipo, status);
		List<FnCheque> results = em.createQuery(query, FnCheque.class).getResultList();
		return results;
	}

	private String retFnCheque(Long local, Integer caixa, Date dataini, Date datafim, String busca, String ove,
			String ordem, String ordemdir, String tipo, String status) {
		String query = "SELECT c FROM FnCheque c WHERE c.cdpessoaempatual.status = 'ATIVO' AND ";
		// Local
		if (local > 0) {
			query += "c.cdpessoaempatual = " + local + " AND ";
		}
		// Caixa atual
		if (caixa > 0) {
			query += "c.cdcaixaatual = " + caixa + " AND ";
		}
		// Vence, emissao ou entrada
		if (ove.equals("N")) {
			query += "c.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		}
		if (ove.equals("E")) {
			query += "c.data BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		}
		if (ove.equals("V")) {
			query += "c.vence BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		}
		// Tipo
		if (!tipo.equals("X")) {
			query += "AND c.tipo = " + tipo + " ";
		}
		// Status
		if (!status.equals("X")) {
			query += "AND c.status = " + status + " ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (c.agencia LIKE '%" + busca + "%' OR c.cdbancos_id = '" + busca + "' OR c.conta LIKE '%"
					+ busca + "%' OR c.cpfcnpj LIKE '%" + busca + "%' OR c.emissor LIKE '%" + busca
					+ "%' OR c.info LIKE '%" + busca + "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}
}
