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

import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.util.DataUtil;

@Repository
public class FnCxmvCustomRepository {
	private final EntityManager em;

	@Autowired
	public FnCxmvCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FnCxmv> listaFnCxmv(Long local, Integer caixa, String tipo, String fpag, Date dataini, Date datafim,
			String busca, String ordem, String ordemdir, Long para, String lanca, String status, Pageable pageable) {
		String query = retFnCxmv(local, caixa, tipo, fpag, dataini, datafim, busca, ordem, ordemdir, para, lanca,
				status);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FnCxmv> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<FnCxmv> results = em.createQuery(query, FnCxmv.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<FnCxmv> listaFnCxmvExcel(Long local, Integer caixa, String tipo, String fpag, Date dataini,
			Date datafim, String busca, String ordem, String ordemdir, Long para, String lanca, String status) {
		String query = retFnCxmv(local, caixa, tipo, fpag, dataini, datafim, busca, ordem, ordemdir, para, lanca,
				status);
		List<FnCxmv> results = em.createQuery(query, FnCxmv.class).getResultList();
		return results;
	}

	public List<FnCxmv> listaFnCxmvValores(Long local, Integer caixa, String tipo, String fpag, Date dataini,
			Date datafim, String busca, Long para, String lanca, String status) {
		String query = retFnCxmv(local, caixa, tipo, fpag, dataini, datafim, busca, "id", "ASC", para, lanca, status);
		List<FnCxmv> results = em.createQuery(query, FnCxmv.class).getResultList();
		return results;
	}

	public BigDecimal retFnCxmvValoresSaldoAnterior(Long local, Integer caixa, String tipo, String fpag, Date dataini,
			Date datafim, String busca, Long para, String lanca, String status) {
		// Encontra ultima dia de movimento abaixo da data atual final
		Date DataAnterior = new Date(DataUtil.addRemDias(dataini, 1, "R").getTime());
		// DESATIVADO - Daniel 13-02-20223
		// String query = retFnCxmv(local, caixa, tipo, Date.valueOf("1984-11-23"),
		// DataAnterior, busca, "datacad", "DESC",
		// para, lanca, status);
		// List<FnCxmv> results = em.createQuery(query, FnCxmv.class).getResultList();
		// Lista valores para contagem se houver movimentos anteriores
		BigDecimal saldo = new BigDecimal(0);
		BigDecimal saldoEnt = new BigDecimal(0);
		BigDecimal saldoSai = new BigDecimal(0);
		// DESATIVADO - Daniel 13-02-20223
		// if (results.size() > 0) {
		// Date DataAtual = Date.valueOf(results.get(0).getDatacad().toString());
		// System.out.println("DATA " + DataAtual);
		List<FnCxmv> lista = listaFnCxmvValores(local, caixa, tipo, fpag, Date.valueOf("1984-11-23"), DataAnterior,
				busca, para, lanca, status);
		// Calculos
		for (FnCxmv c : lista) {
			if (c.getFntitulo().getTipo().equals("P") || c.getFntitulo().getTipo().equals("TP")) {
				saldoSai = saldoSai.add(c.getVtot());
			}
			if (c.getFntitulo().getTipo().equals("R") || c.getFntitulo().getTipo().equals("TR")) {
				saldoEnt = saldoEnt.add(c.getVtot());
			}
		}
		saldo = saldoEnt.subtract(saldoSai);
		return saldo;
	}

	public BigDecimal retFnCxmvValoresTotais(Long local, String tipo, String fpag, String status, Date dataini,
			Date datafim) {
		String query = retFnCxmv(local, 0, tipo, fpag, dataini, datafim, "", "id", "ASC", 0L, "X", status);
		List<FnCxmv> results = em.createQuery(query, FnCxmv.class).getResultList();
		BigDecimal valorfinal = new BigDecimal(0);
		if (results.size() > 0) {
			// Calculos
			for (FnCxmv c : results) {
				valorfinal = valorfinal.add(c.getVtot());
			}
		}
		return valorfinal;
	}

	public BigDecimal retFnCxmvValoresTotaisResultadoMes(Long local, String status, Date dataini, Date datafim) {
		String query = retFnCxmv(local, 0, "X", "X", dataini, datafim, "", "id", "ASC", 0L, "X", status);
		List<FnCxmv> results = em.createQuery(query, FnCxmv.class).getResultList();
		BigDecimal valorfinal = new BigDecimal(0);
		if (results.size() > 0) {
			// Calculos
			for (FnCxmv c : results) {
				if (c.getFntitulo().getTipo().equals("P")) {
					valorfinal = valorfinal.subtract(c.getVtot());
				}
				if (c.getFntitulo().getTipo().equals("R")) {
					valorfinal = valorfinal.add(c.getVtot());
				}
			}
		}
		return valorfinal;
	}

	private String retFnCxmv(Long local, Integer caixa, String tipo, String fpag, Date dataini, Date datafim,
			String busca, String ordem, String ordemdir, Long para, String lanca, String status) {
		String query = "SELECT c FROM FnCxmv c LEFT JOIN FETCH c.fncxmvdreitem d WHERE c.cdpessoaemp.status = 'ATIVO' AND ";
		// Se lancamento manual, pela conta ou todos
		if (lanca.equals("C")) {
			query += "c.fntitulo.cdpessoaemp.id != c.fntitulo.cdpessoapara.id AND ";
		}
		if (lanca.equals("M")) {
			query += "c.fntitulo.cdpessoaemp.id = c.fntitulo.cdpessoapara.id AND ";
		}
		// Tipo
		if (!tipo.equals("X")) {
			query += "c.fntitulo.tipo = '" + tipo + "' AND ";
		}
		// F. pagamento
		if (!fpag.equals("X")) {
			query += "c.fpag = '" + fpag + "' AND ";
		}
		// Status
		if (!status.equals("X")) {
			query += "c.status = '" + status + "' AND ";
		}
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp = " + local + " AND ";
		}
		// Caixa
		if (caixa > 0) {
			query += "c.cdcaixa = " + caixa + " AND ";
		}
		// Para quem
		if (para > 0) {
			query += "c.fntitulo.cdpessoapara = " + para + " AND ";
		}
		query += "c.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (CONVERT(c.fntitulo.id, CHAR) LIKE '%" + busca + "%' OR c.fntitulo.cdpessoapara.nome LIKE '%"
					+ busca + "%' OR c.fntitulo.cdpessoapara.fantasia LIKE '%" + busca
					+ "%' OR c.fntitulo.cdpessoapara.cpfcnpj LIKE '%" + busca + "%' OR c.info LIKE '%" + busca + "%' "
					+ "OR c.fntitulo.ref LIKE '%" + busca + "%' OR d.cdplconmicro.nome LIKE '%" + busca + "%' "
					+ "OR d.cdplconmicro.mascfinal = '" + busca + "') ";
		}
		// Ordem - se data e hora, adicioan diferente
		if (ordem.equals("datacad,horacad")) {
			query += " GROUP BY c.id ORDER BY c.datacad " + ordemdir + ", horacad " + ordemdir;
		} else {
			query += " GROUP BY c.id ORDER BY c." + ordem + " " + ordemdir;
		}
		return query;
	}
}
