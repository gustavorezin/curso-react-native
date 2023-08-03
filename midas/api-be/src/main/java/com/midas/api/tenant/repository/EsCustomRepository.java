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

import com.midas.api.tenant.entity.EsEst;
import com.midas.api.tenant.entity.EsEstExt;
import com.midas.api.tenant.entity.EsEstMov;

@Repository
public class EsCustomRepository {
	private final EntityManager em;

	@Autowired
	public EsCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	// ESTOQUE-----------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<EsEst> listaEsEst(Long local, Date dataini, Date datafim, String busca, String tipoitem, String status,
			String ordem, String ordemdir, Pageable pageable) {
		String query = retEsEst(local, dataini, datafim, busca, tipoitem, status, ordem, ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<EsEst> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
		List<EsEst> results = em.createQuery(query, EsEst.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<EsEst> listaEsEstExcel(Long local, Date dataini, Date datafim, String busca, String tipoitem,
			String status, String ordem, String ordemdir) {
		String query = retEsEst(local, dataini, datafim, busca, tipoitem, status, ordem, ordemdir);
		List<EsEst> results = em.createQuery(query, EsEst.class).getResultList();
		return results;
	}

	public List<EsEst> listaEsEstValores(Long local, Date dataini, Date datafim, String busca, String tipoitem,
			String status) {
		String query = retEsEst(local, dataini, datafim, busca, tipoitem, status, "id", "ASC");
		List<EsEst> results = em.createQuery(query, EsEst.class).getResultList();
		return results;
	}

	private String retEsEst(Long local, Date dataini, Date datafim, String busca, String tipoitem, String status,
			String ordem, String ordemdir) {
		String query = "SELECT c FROM EsEst c WHERE ";
		// Data cadastro
		query += "c.dataat BETWEEN '" + dataini + "' AND '" + datafim + "' AND c.cdproduto.tipo = 'PRODUTO' ";
		// Local
		if (local > 0) {
			query += "AND c.cdpessoaemp.id = " + local + " ";
		}
		// Tipo de item
		if (!tipoitem.equals("X")) {
			query += "AND c.cdproduto.tipoitem = " + tipoitem + " ";
		}
		// Status
		if (!status.equals("X")) {
			query += "AND c.cdproduto.status = '" + status + "' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (CONVERT(c.cdproduto.id, CHAR) LIKE '%" + busca + "%' OR " + "c.cdproduto.nome LIKE '%"
					+ busca + "%' OR c.cdproduto.descricao LIKE '%" + busca + "%' OR c.cdproduto.infoncmserv LIKE '%"
					+ busca + "%' OR c.cdproduto.ref LIKE '%" + busca + "%' OR " + "c.cdproduto.info LIKE '%" + busca
					+ "%' OR c.cdproduto.ncm LIKE '%" + busca + "%' OR c.cdproduto.codigo LIKE '%" + busca + "%' ) ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}

	// HISTORICO---------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<EsEstMov> listaEsEstMov(Long local, Date dataini, Date datafim, Long idprod, String tpdoc, String ordem,
			String ordemdir, Pageable pageable) {
		String query = retEsEstMov(local, dataini, datafim, idprod, tpdoc, ordem, ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<EsEstMov> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<EsEstMov> results = em.createQuery(query, EsEstMov.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<EsEstMov> listaEsEstMovExcel(Long local, Date dataini, Date datafim, Long idprod, String tpdoc,
			String ordem, String ordemdir) {
		String query = retEsEstMov(local, dataini, datafim, idprod, tpdoc, ordem, ordemdir);
		List<EsEstMov> results = em.createQuery(query, EsEstMov.class).getResultList();
		return results;
	}

	private String retEsEstMov(Long local, Date dataini, Date datafim, Long idprod, String tpdoc, String ordem,
			String ordemdir) {
		String query = "SELECT c FROM EsEstMov c WHERE ";
		// Data cadastro
		query += "c.data BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Local
		if (local > 0) {
			query += "AND c.cdpessoaemp.id = " + local + " ";
		}
		// Produto
		if (idprod > 0) {
			query += "AND c.cdproduto.id = " + idprod + " ";
		}
		// Tipo de documento
		if (!tpdoc.equals("X")) {
			query += "AND c.tpdoc = '" + tpdoc + "' ";
		}
		// Ordem - se data e hora, adicioan diferente
		if (ordem.equals("data,hora")) {
			query += "ORDER BY c.data " + ordemdir + ", hora " + ordemdir;
		} else {
			query += "ORDER BY c." + ordem + " " + ordemdir;
		}
		return query;
	}

	// ESTOQUE EXTERNO --------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<EsEstExt> listaEsEstExt(Long vep, Date dataini, Date datafim, String busca, String ordem,
			String ordemdir, Pageable pageable) {
		String query = retEsEstExt(vep, dataini, datafim, busca, ordem, ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<EsEstExt> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<EsEstExt> results = em.createQuery(query, EsEstExt.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	private String retEsEstExt(Long vep, Date dataini, Date datafim, String busca, String ordem, String ordemdir) {
		String query = "SELECT e FROM EsEstExt e WHERE ";
		// Vendedor
		if (vep > 0) {
			query += "e.cdpessoavendedor = " + vep + " AND ";
		}
		// Data
		query += "e.data BETWEEN '" + dataini + "' AND '" + datafim + "'";
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (CONVERT(e.id, CHAR) LIKE '%" + busca + "%' OR e.cdpessoavendedor.cpfcnpj LIKE '%" + busca
					+ "%' OR e.cdpessoavendedor.nome LIKE '%" + busca + "%' OR e.cdproduto.nome LIKE '%" + busca
					+ "%')";
		}
		// Ordem
		query += "ORDER BY e." + ordem + " " + ordemdir;
		return query;
	}
}
