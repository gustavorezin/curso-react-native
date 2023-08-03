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

import com.midas.api.tenant.entity.LcRoma;

@Repository
public class LcRomaCustomRepository {
	private final EntityManager em;

	@Autowired
	public LcRomaCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<LcRoma> listaLcRomas(Long local, Long transp, String tipo, Date dataini, Date datafim, String busca,
			String ordem, String ordemdir, String status, Pageable pageable) {
		String query = retLcRoma(local, transp, tipo, dataini, datafim, busca, ordem, ordemdir, status);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<LcRoma> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<LcRoma> results = em.createQuery(query, LcRoma.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<LcRoma> listaLcRomaExcel(Long local, Long transp, String tipo, Date dataini, Date datafim, String busca,
			String ordem, String ordemdir, String status) {
		String query = retLcRoma(local, transp, tipo, dataini, datafim, busca, ordem, ordemdir, status);
		List<LcRoma> results = em.createQuery(query, LcRoma.class).getResultList();
		return results;
	}

	public List<LcRoma> listaLcRomasValores(Long local, Long transp, String tipo, Date dataini, Date datafim,
			String busca, String status) {
		String query = retLcRoma(local, transp, tipo, dataini, datafim, busca, "id", "ASC", status);
		List<LcRoma> results = em.createQuery(query, LcRoma.class).getResultList();
		return results;
	}

	private String retLcRoma(Long local, Long transp, String tipo, Date dataini, Date datafim, String busca,
			String ordem, String ordemdir, String status) {
		String query = "SELECT c FROM LcRoma c WHERE c.dataem BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Local
		if (local > 0) {
			query += "AND c.cdpessoaemp = " + local + " ";
		}
		// Para - transportador
		if (transp > 0) {
			query += "AND c.cdpessoatransp = " + transp + " ";
		}
		// Tipo
		if (!tipo.equals("00")) {
			query += "AND c.tipo = '" + tipo + "' ";
		}
		// Status
		if (!status.equals("0")) {
			query += "AND c.status = '" + status + "' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (CONVERT(c.id, CHAR) LIKE '%" + busca + "%' OR c.cdpessoatransp.cpfcnpj LIKE '%" + busca
					+ "%' OR c.cdpessoatransp.nome LIKE '%" + busca + "%' OR c.info LIKE '%" + busca
					+ "%' OR c.localdesc LIKE '%" + busca + "%' " + " OR c.motcan LIKE '%" + busca + "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}
}
