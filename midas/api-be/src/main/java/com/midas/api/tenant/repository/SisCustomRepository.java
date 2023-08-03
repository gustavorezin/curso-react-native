package com.midas.api.tenant.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.SisReguser;

@Repository
public class SisCustomRepository {
	private final EntityManager em;

	@Autowired
	public SisCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<SisReguser> listaSisReguser(Long local, Date dataini, Date datafim, String busca, String ordem,
			String ordemdir, Pageable pageable) {
		String query = "SELECT c FROM SisReguser c WHERE ";
		// Data
		query += "c.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Local
		if (local > 0) {
			query += "AND c.cdpessoaemp_id = " + local + " ";
		}
		if (!busca.equals("")) {
			query += "AND (c.descricao LIKE '%" + busca + "%' OR c.onde LIKE '%" + busca + "%' "
					+ "OR c.usuario LIKE '%" + busca + "%' OR c.login LIKE '%" + busca + "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<SisReguser> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<SisReguser> results = em.createQuery(query, SisReguser.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}
}
