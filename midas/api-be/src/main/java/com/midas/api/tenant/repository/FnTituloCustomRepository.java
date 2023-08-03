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

import com.midas.api.tenant.entity.FnTitulo;

@Repository
public class FnTituloCustomRepository {
	private final EntityManager em;

	@Autowired
	public FnTituloCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<FnTitulo> listaFnTitulos(Long local, Integer caixapref, String fpagpref, String tipo, Date dataini,
			Date datafim, String busca, String ove, String ordem, String ordemdir, Long para, Long resp, String cred,
			String paracob, String comissao, String compago, String status, Pageable pageable) {
		String query = retFnTitulo(local, caixapref, fpagpref, tipo, dataini, datafim, busca, ove, ordem, ordemdir,
				para, resp, cred, paracob, comissao, compago, status);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<FnTitulo> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<FnTitulo> results = em.createQuery(query, FnTitulo.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<FnTitulo> listaFnTituloExcel(Long local, Integer caixapref, String fpagpref, String tipo, Date dataini,
			Date datafim, String busca, String ove, String ordem, String ordemdir, Long para, Long resp, String cred,
			String paracob, String comissao, String compago, String status) {
		String query = retFnTitulo(local, caixapref, fpagpref, tipo, dataini, datafim, busca, ove, ordem, ordemdir,
				para, resp, cred, paracob, comissao, compago, status);
		List<FnTitulo> results = em.createQuery(query, FnTitulo.class).getResultList();
		return results;
	}

	public List<FnTitulo> listaFnTitulosValores(Long local, Integer caixapref, String fpagpref, String tipo,
			Date dataini, Date datafim, String busca, String ove, Long para, Long resp, String cred, String paracob,
			String comissao, String compago, String status) {
		String query = retFnTitulo(local, caixapref, fpagpref, tipo, dataini, datafim, busca, ove, "id", "ASC", para,
				resp, cred, paracob, comissao, compago, status);
		List<FnTitulo> results = em.createQuery(query, FnTitulo.class).getResultList();
		return results;
	}

	public BigDecimal retFnTitulosValoresTotais(Long local, Integer caixapref, String fpagpref, String tipo,
			Date dataini, Date datafim, String ove, String cred, String paracob, String comissao, String compago,
			String status) {
		String query = retFnTitulo(local, caixapref, fpagpref, tipo, dataini, datafim, "", ove, "id", "ASC", 0L, 0L,
				cred, paracob, comissao, compago, status);
		List<FnTitulo> results = em.createQuery(query, FnTitulo.class).getResultList();
		BigDecimal valorfinal = new BigDecimal(0);
		if (results.size() > 0) {
			// Calculos
			for (FnTitulo c : results) {
				// Todos
				if (status.equals("X")) {
					if (c.getParacob().equals("S")) {
						valorfinal = valorfinal.add(c.getVtot());
					}
					/*
					 * Desativado - divergencias alguns clientes else { if (paraCobAmos(c) == true)
					 * { valorfinal = valorfinal.add(c.getVtot()); } }
					 */
				}
				// Em aberto
				if (status.equals("A")) {
					if (c.getParacob().equals("S")) {
						valorfinal = valorfinal.add(c.getVsaldo());
					}
					/*
					 * Desativado - divergencias alguns clientes else {
					 * 
					 * if (paraCobAmos(c) == true) { valorfinal = valorfinal.add(c.getVsaldo()); } }
					 */
				}
				// Finalizados
				if (status.equals("F")) {
					if (c.getParacob().equals("S")) {
						valorfinal = valorfinal.add(c.getVpago());
					}
					/*
					 * Desativado - divergencias alguns clientes else { if (paraCobAmos(c) == true)
					 * { valorfinal = valorfinal.add(c.getVpago()); } }
					 */
				}
			}
		}
		return valorfinal;
	}
	/*
	 * Desativado - divergencias alguns clientes private boolean
	 * paraCobAmos(FnTitulo c) { if (c.getLcdoc() > 0) { LcDoc doc =
	 * lcDocRp.findById(c.getLcdoc()).get(); if (doc != null) { if
	 * (!doc.getStatus().equals("3")) { return true; } } } return false; }
	 */

	private String retFnTitulo(Long local, Integer caixapref, String fpagpref, String tipo, Date dataini, Date datafim,
			String busca, String ove, String ordem, String ordemdir, Long para, Long resp, String cred, String paracob,
			String comissao, String compago, String status) {
		String query = "SELECT c FROM FnTitulo c LEFT JOIN FETCH c.fntitulodreitem d "
				+ "LEFT JOIN FnTitulo cp ON c.fntitulo_com=cp.id WHERE c.cdpessoaemp.status = 'ATIVO' AND ";
		// Tipo
		if (!tipo.equals("X")) {
			query += "c.tipo = '" + tipo + "' AND ";
		}
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp = " + local + " AND ";
		}
		// Para quem
		if (para > 0) {
			query += "c.cdpessoapara = " + para + " AND ";
		}
		// Responsavel / Representante / Vendedor
		if (resp > 0) {
			query += "c.cdpessoapara.cdpessoaresp_id = " + resp + " AND ";
		}
		// Caixa
		if (caixapref > 0) {
			query += "c.cdcaixapref = " + caixapref + " AND ";
		}
		// Forma pagamento
		if (!fpagpref.equals("X")) {
			query += "c.fpagpref = " + fpagpref + " AND ";
		}
		// Se comissao
		if (!comissao.equals("X")) {
			query += "c.comissao = '" + comissao + "' AND ";
		}
		// Comissao paga vinda do titulo
		if (compago.equals("S")) {
			query += "cp.vsaldo != cp.vtot AND ";
		}
		if (compago.equals("N")) {
			query += "cp.vsaldo = cp.vtot AND ";
		}
		// Vence, emissao ou ultima baixa - pela comissao do periodo ou padrao
		if (!comissao.equals("S")) {
			if (ove.equals("E")) {
				query += "c.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' AND ";
			}
			if (ove.equals("V")) {
				query += "c.vence BETWEEN '" + dataini + "' AND '" + datafim + "' AND ";
			}
			if (ove.equals("B")) {
				query += "c.databaixa BETWEEN '" + dataini + "' AND '" + datafim + "' AND ";
			}
		} else {
			query += "cp.databaixa BETWEEN '" + dataini + "' AND '" + datafim + "' AND ";
		}
		if (!paracob.equals("X")) {
			query += "c.paracob = '" + paracob + "' ";
		}
		if (paracob.equals("X")) {
			query += "c.paracob != '" + paracob + "' ";
		}
		// Se credito ou nao
		if (cred.equals("X")) {
			// Status se aberto ou quitado
			if (status.equals("A")) {
				query += "AND c.vsaldo != 0 ";
			}
			if (status.equals("F")) {
				query += "AND (c.vsaldo = 0 OR c.vpago > 0) ";
			}
		}
		// Se credito ou nao
		if (!cred.equals("X")) {
			query += "AND c.cred = '" + cred + "' ";
			// Status se aberto ou quitado
			if (status.equals("A")) {
				query += "AND c.vcredsaldo != 0 ";
			}
			if (status.equals("F")) {
				query += "AND (c.vcredsaldo = 0) ";
			}
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (CONVERT(c.id, CHAR) LIKE '%" + busca + "%' OR c.cdpessoapara.nome LIKE '%" + busca
					+ "%' OR c.cdpessoapara.fantasia LIKE '%" + busca + "%' OR c.cdpessoapara.cpfcnpj LIKE '%" + busca
					+ "%' OR c.ref LIKE '%" + busca + "%' OR c.info LIKE '%" + busca + "%' OR c.vparc LIKE '%" + busca
					+ "%' OR c.vtot LIKE '%" + busca + "%' OR c.vsaldo LIKE '%" + busca + "%' OR c.vpago LIKE '%"
					+ busca + "%' OR d.cdplconmicro.nome LIKE '%" + busca + "%' OR d.cdplconmicro.mascfinal = '" + busca
					+ "' OR CONVERT(c.numnota, CHAR) = '" + busca + "') ";
		}
		// Grupo e Ordem
		query += "GROUP BY c.id ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}
}
