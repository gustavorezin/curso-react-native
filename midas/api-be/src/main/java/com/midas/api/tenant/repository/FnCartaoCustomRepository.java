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

import com.midas.api.tenant.entity.FnCartao;

@Repository
public class FnCartaoCustomRepository {
	private final EntityManager em;

	@Autowired
	public FnCartaoCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FnCartao> listaFnCartao(Long local, Integer caixa, Integer cartao, Date dataini, Date datafim,
			String busca, String ope, String ordem, String ordemdir, String tipo, String status, Pageable pageable) {
		String query = retFnCartao(local, caixa, cartao, dataini, datafim, busca, ope, ordem, ordemdir, tipo, status);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FnCartao> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<FnCartao> results = em.createQuery(query, FnCartao.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<FnCartao> listaFnCartaoExcel(Long local, Integer caixa, Integer cartao, Date dataini, Date datafim,
			String busca, String ope, String ordem, String ordemdir, String tipo, String status) {
		String query = retFnCartao(local, caixa, cartao, dataini, datafim, busca, ope, ordem, ordemdir, tipo, status);
		List<FnCartao> results = em.createQuery(query, FnCartao.class).getResultList();
		return results;
	}

	public List<FnCartao> listaFnCartaoValores(Long local, Integer caixa, Integer cartao, Date dataini, Date datafim,
			String busca, String ope, String tipo, String status) {
		String query = retFnCartao(local, caixa, cartao, dataini, datafim, busca, ope, "id", "ASC", tipo, status);
		List<FnCartao> results = em.createQuery(query, FnCartao.class).getResultList();
		return results;
	}

	private String retFnCartao(Long local, Integer caixa, Integer cartao, Date dataini, Date datafim, String busca,
			String ope, String ordem, String ordemdir, String tipo, String status) {
		String query = "SELECT c FROM FnCartao c WHERE c.fncxmv.cdpessoaemp.status = 'ATIVO' AND ";
		// Local
		if (local > 0) {
			query += "c.fncxmv.cdpessoaemp = " + local + " AND ";
		}
		// Caixa
		if (caixa > 0) {
			query += "c.fncxmv.cdcaixa = " + caixa + " AND ";
		}
		// Cartao
		if (cartao > 0) {
			query += "c.cdcartao = " + cartao + " AND ";
		}
		// Previsao ou cadastro
		if (ope.equals("E")) {
			query += "c.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		}
		if (ope.equals("P")) {
			query += "c.dataprev BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		}
		// Tipo
		if (!tipo.equals("X")) {
			query += "AND c.fncxmv.fntitulo.tipo = '" + tipo + "' ";
		}
		// Status
		if (!status.equals("X")) {
			query += "AND c.status = '" + status + "' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (c.fncxmv.fntitulo.cdpessoapara.nome LIKE '%" + busca + "%' OR c.cdcartao.nome LIKE '%"
					+ busca + "%' OR c.fncxmv.cdcaixa.nome LIKE '%" + busca + "%' OR c.fncxmv.fntitulo.ref LIKE '%"
					+ busca + "%' OR CONVERT(c.fncxmv.fntitulo.id, CHAR) LIKE '" + busca + "') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}
}
