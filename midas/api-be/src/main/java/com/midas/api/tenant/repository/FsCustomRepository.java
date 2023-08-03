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

import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsCteMan;
import com.midas.api.tenant.entity.FsMdfe;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfeMan;
import com.midas.api.tenant.entity.FsNfse;

@Repository
public class FsCustomRepository {
	private final EntityManager em;

	@Autowired
	public FsCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	// CTE***************************************************************
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FsCte> listaFsCte(Long local, Date dataini, Date datafim, String tipo, String busca, String uffim,
			String st, String oensai, String ordem, String ordemdir, Pageable pageable) {
		String query = retFsCTe(local, dataini, datafim, tipo, busca, uffim, st, oensai, ordem, ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FsCte> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
		List<FsCte> results = em.createQuery(query, FsCte.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<FsCte> listaFsCteValores(Long local, Date dataini, Date datafim, String tipo, String st,
			String ordemensai, String busca, String uffim) {
		String query = retFsCTe(local, dataini, datafim, tipo, busca, uffim, st, ordemensai, "id", "ASC");
		List<FsCte> results = em.createQuery(query, FsCte.class).getResultList();
		return results;
	}

	public List<FsCte> listaFsCteExcel(Long local, Date dataini, Date datafim, String tipo, String busca, String uffim,
			Integer modelo, String st, String oensai, String ordem, String ordemdir) {
		String query = retFsCTe(local, dataini, datafim, tipo, busca, uffim, st, oensai, ordem, ordemdir);
		List<FsCte> results = em.createQuery(query, FsCte.class).getResultList();
		return results;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FsCteMan> listaFsCteMan(Long local, Date dataini, Date datafim, String busca, String st, String st_imp,
			String ordem, String ordemdir, Pageable pageable) {
		String query = "SELECT c FROM FsCteMan c WHERE ";
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp = " + local + " AND ";
		}
		// Data
		query += "c.dhemi BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Situação manifestado
		if (st.equals("P")) {
			query += "AND c.st = 1 ";
		}
		if (st.equals("M")) {
			query += "AND c.st > 1 ";
		}
		// Situação importacao
		if (st_imp.equals("N")) {
			query += "AND c.st_imp = 'N' ";
		}
		if (st_imp.equals("S")) {
			query += "AND c.st_imp = 'S' ";
		}
		// Para descartado = D
		if (st_imp.equals("D")) {
			query += "AND c.st_imp = 'D' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (c.chave LIKE '%" + busca + "%' OR c.nprot LIKE '%" + busca + "%' " + "OR c.nome LIKE '%"
					+ busca + "%' OR c.cpfcnpj LIKE '%" + busca + "%' OR c.ie LIKE '%" + busca + "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FsCteMan> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<FsCteMan> results = em.createQuery(query, FsCteMan.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<FsCteMan> listaFsCteManValores(Long local, Date dataini, Date datafim, String busca, String st,
			String st_imp) {
		String query = "SELECT c FROM FsCteMan c WHERE ";
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp = " + local + " AND ";
		}
		// Data
		query += "c.dhemi BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Situação manifestado
		if (st.equals("P")) {
			query += "AND c.st = 1 ";
		}
		if (st.equals("M")) {
			query += "AND c.st > 1 ";
		}
		// Situação importacao
		if (st_imp.equals("N")) {
			query += "AND c.st_imp = 'N' ";
		}
		if (st_imp.equals("S")) {
			query += "AND c.st_imp = 'S' ";
		}
		// Para descartado = D
		if (st_imp.equals("D")) {
			query += "AND c.st_imp = 'D' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (c.chave LIKE '%" + busca + "%' OR c.nprot LIKE '%" + busca + "%' " + "OR c.nome LIKE '%"
					+ busca + "%' OR c.cpfcnpj LIKE '%" + busca + "%' OR c.ie LIKE '%" + busca + "%') ";
		}
		List<FsCteMan> results = em.createQuery(query, FsCteMan.class).getResultList();
		return results;
	}

	private String retFsCTe(Long local, Date dataini, Date datafim, String tipo, String busca, String uffim, String st,
			String oensai, String ordem, String ordemdir) {
		String query = "SELECT c FROM FsCte c WHERE ";
		// Tipo
		query += "c.tipo = '" + tipo + "' AND ";
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp = " + local + " AND ";
		}
		// Data ou entrada e saida
		if (oensai.equals("E")) {
			query += "c.dhemi BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		}
		if (oensai.equals("S")) {
			query += "c.dhsaient BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		}
		// Situação conferida
		if (st.equals("C")) {
			query += "AND (c.st_transp = 'S' AND c.st_cob = 'S') ";
		}
		// Situação pendentes
		if (st.equals("P")) {
			query += "AND (c.st_transp = 'N' OR c.st_cob = 'N') ";
		}
		// Destino - Uffim
		if (!uffim.equals("0")) {
			query += "AND c.uffim = '" + uffim + "' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += "AND (CONVERT(c.nct, CHAR) LIKE '%" + busca + "%' OR fsctepartemit.xnome LIKE '%" + busca
					+ "%' OR " + "fsctepartemit.xfant LIKE '%" + busca + "%' OR fsctepartemit.xmun LIKE '%" + busca
					+ "%' OR " + "fsctepartemit.cpfcnpj LIKE '%" + busca + "%' OR fsctepartdest.xnome LIKE '%" + busca
					+ "%' OR " + "fsctepartdest.xfant LIKE '%" + busca + "%' OR fsctepartdest.xmun LIKE '%" + busca
					+ "%' OR " + "fsctepartdest.cpfcnpj LIKE '%" + busca + "%' OR fsctepartrem.xnome LIKE '%" + busca
					+ "%' OR " + "fsctepartrem.xfant LIKE '%" + busca + "%' OR fsctepartrem.xmun LIKE '%" + busca
					+ "%' OR " + "fsctepartrem.cpfcnpj LIKE '%" + busca + "%' OR c.natop LIKE '%" + busca
					+ "%' OR c.chaveac " + "LIKE '%" + busca + "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}

	// NFE*******************************************************
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FsNfe> listaFsNfe(Long local, Date dataini, Date datafim, String tipo, String busca, String uffim,
			Integer modelo, String st, Integer status, String oensai, String ordem, String ordemdir,
			Pageable pageable) {
		String query = retFsNFe(local, dataini, datafim, tipo, busca, uffim, modelo, st, status, oensai, ordem,
				ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FsNfe> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
		List<FsNfe> results = em.createQuery(query, FsNfe.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<FsNfe> listaFsNfeExcel(Long local, Date dataini, Date datafim, String tipo, String busca, String uffim,
			Integer modelo, String st, Integer status, String oensai, String ordem, String ordemdir) {
		String query = retFsNFe(local, dataini, datafim, tipo, busca, uffim, modelo, st, status, oensai, ordem,
				ordemdir);
		List<FsNfe> results = em.createQuery(query, FsNfe.class).getResultList();
		return results;
	}

	public List<FsNfe> listaFsNfeValores(Long local, Date dataini, Date datafim, String tipo, String busca,
			String uffim, Integer modelo, String st, Integer status, String oensai) {
		String query = retFsNFe(local, dataini, datafim, tipo, busca, uffim, modelo, st, status, oensai, "id", "ASC");
		List<FsNfe> results = em.createQuery(query, FsNfe.class).getResultList();
		return results;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FsNfeMan> listaFsNfeMan(Long local, Date dataini, Date datafim, String busca, String st, String st_imp,
			String ordem, String ordemdir, Pageable pageable) {
		String query = "SELECT c FROM FsNfeMan c WHERE ";
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp = " + local + " AND ";
		}
		// Data
		query += "c.dhemi BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Situação manifestado
		if (st.equals("P")) {
			query += "AND c.st = 1 ";
		}
		if (st.equals("M")) {
			query += "AND c.st > 1 ";
		}
		// Situação importacao
		if (st_imp.equals("N")) {
			query += "AND c.st_imp = 'N' ";
		}
		if (st_imp.equals("S")) {
			query += "AND c.st_imp = 'S' ";
		}
		// Para descartado = D
		if (st_imp.equals("D")) {
			query += "AND c.st_imp = 'D' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (c.chave LIKE '%" + busca + "%' OR c.nprot LIKE '%" + busca + "%' " + "OR c.nome LIKE '%"
					+ busca + "%' OR c.cpfcnpj LIKE '%" + busca + "%' OR c.ie LIKE '%" + busca + "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FsNfeMan> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<FsNfeMan> results = em.createQuery(query, FsNfeMan.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<FsNfeMan> listaFsNfeManValores(Long local, Date dataini, Date datafim, String busca, String st,
			String st_imp) {
		String query = "SELECT c FROM FsNfeMan c WHERE ";
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp = " + local + " AND ";
		}
		// Data
		query += "c.dhemi BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Situação manifestado
		if (st.equals("P")) {
			query += "AND c.st = 1 ";
		}
		if (st.equals("M")) {
			query += "AND c.st > 1 ";
		}
		// Situação importacao
		if (st_imp.equals("N")) {
			query += "AND c.st_imp = 'N' ";
		}
		if (st_imp.equals("S")) {
			query += "AND c.st_imp = 'S' ";
		}
		// Para descartado = D
		if (st_imp.equals("D")) {
			query += "AND c.st_imp = 'D' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (c.chave LIKE '%" + busca + "%' OR c.nprot LIKE '%" + busca + "%' " + "OR c.nome LIKE '%"
					+ busca + "%' OR c.cpfcnpj LIKE '%" + busca + "%' OR c.ie LIKE '%" + busca + "%') ";
		}
		List<FsNfeMan> results = em.createQuery(query, FsNfeMan.class).getResultList();
		return results;
	}

	private String retFsNFe(Long local, Date dataini, Date datafim, String tipo, String busca, String uffim,
			Integer modelo, String st, Integer status, String oensai, String ordem, String ordemdir) {
		String query = "SELECT c FROM FsNfe c WHERE ";
		// Tipo
		query += "c.tipo = '" + tipo + "' AND ";
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp = " + local + " AND ";
		}
		// Modelo 55 ou 65
		if (modelo > 0) {
			query += "c.modelo = " + modelo + " AND ";
		}
		// UF fim
		if (!uffim.equals("0")) {
			query += "c.fsnfepartent.uf = '" + uffim + "' AND ";
		}
		// Status - Autorizado, Cancelado...
		if (status > 0) {
			query += "c.status = " + status + " AND ";
		}
		// Data
		query += "c.dhemi BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Situação conferida
		if (st.equals("C")) {
			query += "AND (c.st_fornec = 'S' AND c.st_cob = 'S' AND c.st_est = 'S' AND c.st_custos = 'S') ";
		}
		// Situação pendentes
		if (st.equals("P")) {
			query += "AND (c.st_fornec = 'N' OR c.st_cob = 'N' OR c.st_est = 'N' OR c.st_custos = 'N') ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += "AND (CONVERT(c.nnf, CHAR) LIKE '%" + busca + "%' OR fsnfepartemit.xnome LIKE '%" + busca
					+ "%' OR fsnfepartemit.xfant LIKE '%" + busca + "%' OR " + "fsnfepartemit.xmun LIKE '%" + busca
					+ "%' OR fsnfepartemit.cpfcnpj LIKE '%" + busca + "%' OR fsnfepartdest.xnome LIKE '%" + busca
					+ "%' OR fsnfepartdest.xfant LIKE '%" + busca + "%' OR fsnfepartdest.xmun LIKE '%" + busca
					+ "%' OR fsnfepartdest.cpfcnpj LIKE '%" + busca + "%' OR " + "c.natop LIKE '%" + busca
					+ "%' OR c.chaveac LIKE '%" + busca + "%' OR c.infcpl LIKE '%" + busca + "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}

	// MDFE****************************************************************
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FsMdfe> listaFsMdfe(Long local, Date dataini, Date datafim, String tipo, String busca, Integer status,
			String enc, String ordem, String ordemdir, Pageable pageable) {
		String query = retFsMDFe(local, dataini, datafim, tipo, busca, status, enc, ordem, ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FsMdfe> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<FsMdfe> results = em.createQuery(query, FsMdfe.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<FsMdfe> listaFsMdfeExcel(Long local, Date dataini, Date datafim, String tipo, String busca,
			Integer status, String enc, Integer modelo, String ordem, String ordemdir) {
		String query = retFsMDFe(local, dataini, datafim, tipo, busca, status, enc, ordem, ordemdir);
		List<FsMdfe> results = em.createQuery(query, FsMdfe.class).getResultList();
		return results;
	}

	public List<FsMdfe> listaFsMdfeValores(Long local, Date dataini, Date datafim, String tipo, String busca,
			Integer status, String enc) {
		String query = retFsMDFe(local, dataini, datafim, tipo, busca, status, enc, "id", "ASC");
		List<FsMdfe> results = em.createQuery(query, FsMdfe.class).getResultList();
		return results;
	}

	private String retFsMDFe(Long local, Date dataini, Date datafim, String tipo, String busca, Integer status,
			String enc, String ordem, String ordemdir) {
		String query = "SELECT c FROM FsMdfe c WHERE ";
		// Tipo
		query += "c.tipo = '" + tipo + "' ";
		// Local
		if (local > 0) {
			query += "AND c.cdpessoaemp = " + local + " ";
		}
		// Status - Autorizado, Cancelado...
		if (status > 0) {
			query += "AND c.status = " + status + " ";
		}
		// Se encerrado
		if (!enc.equals("X")) {
			query += "AND c.encerrado = '" + enc + "' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += "AND (CONVERT(c.nmdf, CHAR) LIKE '%" + busca + "%' OR c.cdestadoini.nome LIKE '%" + busca
					+ "%' OR c.cdestadofim.nome LIKE '%" + busca + "%' OR c.vcarga LIKE '%" + busca + "%' OR c.chaveac "
					+ "LIKE '%" + busca + "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}

	// NFSE**********************************************************
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FsNfse> listaFsNfse(Long local, Date dataini, Date datafim, String tipo, String busca, String st,
			Integer status, String oensai, String ordem, String ordemdir, Pageable pageable) {
		String query = retFsNFSe(local, dataini, datafim, tipo, busca, st, status, oensai, ordem, ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FsNfse> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<FsNfse> results = em.createQuery(query, FsNfse.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<FsNfse> listaFsNfseExcel(Long local, Date dataini, Date datafim, String tipo, String busca, String st,
			Integer status, String oensai, String ordem, String ordemdir) {
		String query = retFsNFSe(local, dataini, datafim, tipo, busca, st, status, oensai, ordem, ordemdir);
		List<FsNfse> results = em.createQuery(query, FsNfse.class).getResultList();
		return results;
	}

	public List<FsNfse> listaFsNfseValores(Long local, Date dataini, Date datafim, String tipo, String busca, String st,
			Integer status, String oensai) {
		String query = retFsNFSe(local, dataini, datafim, tipo, busca, st, status, oensai, "id", "ASC");
		List<FsNfse> results = em.createQuery(query, FsNfse.class).getResultList();
		return results;
	}

	private String retFsNFSe(Long local, Date dataini, Date datafim, String tipo, String busca, String st,
			Integer status, String oensai, String ordem, String ordemdir) {
		String query = "SELECT c FROM FsNfse c WHERE ";
		// Tipo
		query += "c.tipo = '" + tipo + "' AND ";
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp = " + local + " AND ";
		}
		// Status - Autorizado, Cancelado...
		if (status > 0) {
			query += "c.status = " + status + " AND ";
		}
		// Data
		query += "c.demis BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Situação conferida
		if (st.equals("C")) {
			query += "AND (c.st_fornec = 'S' AND c.st_cob = 'S') ";
		}
		// Situação pendentes
		if (st.equals("P")) {
			query += "AND (c.st_fornec = 'N' OR c.st_cob = 'N') ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += "AND (CONVERT(c.nnfs, CHAR) LIKE '%" + busca + "%' OR fsnfsepartprest.xnome LIKE '%" + busca
					+ "%' OR fsnfsepartprest.xfant LIKE '%" + busca + "%' OR " + "fsnfsepartprest.xmun LIKE '%" + busca
					+ "%' OR fsnfsepartprest.cpfcnpj LIKE '%" + busca + "%' OR fsnfseparttoma.xnome LIKE '%" + busca
					+ "%' OR fsnfseparttoma.xfant LIKE '%" + busca + "%' OR fsnfseparttoma.xmun LIKE '%" + busca
					+ "%' OR fsnfseparttoma.cpfcnpj LIKE '%" + busca + "%' OR c.outrasinfo LIKE '%" + busca + "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}
}
