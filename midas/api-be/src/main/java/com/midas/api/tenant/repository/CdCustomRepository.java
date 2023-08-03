package com.midas.api.tenant.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.CdCaixa;
import com.midas.api.tenant.entity.CdMaqequip;
import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.CdProdutoDTO;
import com.midas.api.tenant.entity.CdProdutoPreco;
import com.midas.api.tenant.entity.CdProdutoPrecoDTO;

@Repository
public class CdCustomRepository {
	private final EntityManager em;

	@Autowired
	public CdCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	// PESSOA------------------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<CdPessoa> listaCdPessoa(Long local, String tipo, Date dataini, Date datafim, String busca, Integer uf,
			Integer cidade, Long respvend, String status, String ordem, String ordemdir, Pageable pageable) {
		String query = retCdPessoa(local, tipo, dataini, datafim, busca, uf, cidade, respvend, status, ordem, ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<CdPessoa> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<CdPessoa> results = em.createQuery(query, CdPessoa.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<CdPessoa> listaCdPessoaExcel(Long local, String tipo, Date dataini, Date datafim, String busca,
			Integer uf, Integer cidade, Long respvend, String status, String ordem, String ordemdir) {
		String query = retCdPessoa(local, tipo, dataini, datafim, busca, uf, cidade, respvend, status, ordem, ordemdir);
		List<CdPessoa> results = em.createQuery(query, CdPessoa.class).getResultList();
		return results;
	}

	private String retCdPessoa(Long local, String tipo, Date dataini, Date datafim, String busca, Integer uf,
			Integer cidade, Long respvend, String status, String ordem, String ordemdir) {
		String query = "SELECT c FROM CdPessoa c WHERE ";
		// Data cadastro
		query += "c.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' AND ";
		// Tipo
		if (!tipo.equals("")) {
			query += "c.tipo = '" + tipo + "' ";
		} else {
			query += "c.tipo != '0' ";
		}
		// Local
		if (local > 0) {
			query += "AND c.emp = " + local + " ";
		}
		// Cidade
		if (cidade > 0) {
			query += "AND c.cdcidade = " + cidade + " ";
		}
		// UF
		if (uf > 0) {
			query += "AND c.cdestado = " + uf + " ";
		}
		// Status
		if (!status.equals("X")) {
			query += "AND c.status = '" + status + "' ";
		}
		// Representante, responsavel ou vendedor
		if (respvend > 0) {
			query += "AND c.cdpessoaresp_id = '" + respvend + "' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (CONVERT(c.id, CHAR) LIKE '%" + busca + "%' OR " + "c.nome LIKE '%" + busca
					+ "%' OR c.fantasia LIKE '%" + busca + "%' OR c.cpfcnpj LIKE '%" + busca + "%' OR c.foneum LIKE '%"
					+ busca + "%' OR " + "c.email LIKE '%" + busca + "%' OR c.nomefin LIKE '%" + busca
					+ "%' OR c.emailfin LIKE '%" + busca + "%' ) ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}

	// CAIXA------------------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<CdCaixa> listaCdCaixa(Long local, String busca, String ordem, String ordemdir, Pageable pageable) {
		String query = "SELECT c FROM CdCaixa c WHERE c.nome LIKE '%" + busca + "%' ";
		// Local
		if (local > 0) {
			query += "AND c.cdpessoaemp = " + local + " ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<CdCaixa> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<CdCaixa> results = em.createQuery(query, CdCaixa.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	// PRECOS-----------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<CdProdutoPreco> listaCdProdutoPreco(Long local, Integer tabela, String tipoitem, String busca,
			String status, String ordem, String ordemdir, Pageable pageable) {
		String query = retCdProdutoPreco(local, tabela, tipoitem, busca, status, ordem, ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<CdProdutoPreco> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<CdProdutoPreco> results = em.createQuery(query, CdProdutoPreco.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<CdProdutoPreco> listaCdProdutoPrecoExcel(Long local, Integer tabela, String tipoitem, String busca,
			String status, String ordem, String ordemdir) {
		String query = retCdProdutoPreco(local, tabela, tipoitem, busca, status, ordem, ordemdir);
		List<CdProdutoPreco> results = em.createQuery(query, CdProdutoPreco.class).getResultList();
		return results;
	}

	public List<CdProdutoPreco> listaCdProdutoPrecoDiversos(Date dataini, Date datafim, Integer tabela, String tipoitem,
			Integer cat, Integer grupo, Integer gruposub, String status, String ordem, String ordemdir) {
		String query = retCdProdutoPrecoDiversos(dataini, datafim, tabela, tipoitem, cat, grupo, gruposub, status,
				ordem, ordemdir);
		List<CdProdutoPreco> results = em.createQuery(query, CdProdutoPreco.class).getResultList();
		return results;
	}

	private String retCdProdutoPreco(Long local, Integer tabela, String tipoitem, String busca, String status,
			String ordem, String ordemdir) {
		String query = "SELECT c FROM CdProdutoPreco c WHERE ";
		// Local
		if (local > 0) {
			query += "c.cdprodutotab.cdpessoaemp = " + local + " AND ";
		}
		// Tabela
		if (tabela > 0) {
			query += "c.cdprodutotab = " + tabela + " ";
		}
		// Tipo item
		if (!tipoitem.equals("XX")) {
			query += "AND c.cdproduto.tipoitem = '" + tipoitem + "' ";
		}
		// Status
		if (!status.equals("X")) {
			query += "AND c.cdproduto.status = '" + status + "' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (CONVERT(c.cdproduto.id, CHAR) LIKE '%" + busca + "%' OR c.cdproduto.nome LIKE '%" + busca
					+ "%' " + "OR c.cdproduto.codigo LIKE '%" + busca + "%' OR c.cdproduto.cean LIKE '%" + busca + "%' "
					+ "OR c.cdproduto.ceantrib LIKE '%" + busca + "%' OR c.cdproduto.ref LIKE '%" + busca
					+ "%' OR c.cdproduto.info LIKE '%" + busca + "%'" + "OR c.cdproduto.ncm LIKE '%" + busca
					+ "%' OR c.cdproduto.codserv LIKE '%" + busca + "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}

	private String retCdProdutoPrecoDiversos(Date dataini, Date datafim, Integer tabela, String tipoitem, Integer cat,
			Integer grupo, Integer gruposub, String status, String ordem, String ordemdir) {
		String query = "SELECT c FROM CdProdutoPreco c LEFT JOIN CdProduto cp ON c.cdproduto.id=cp.id WHERE ";
		// Data cadastro - ainda nao utilizado diretamente
		query += "cp.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Tipo item sped
		if (!tipoitem.equals("X")) {
			query += "AND cp.tipoitem = '" + tipoitem + "' ";
		}
		// Tabela
		if (tabela > 0) {
			query += "AND c.cdprodutotab.id = " + tabela + " ";
		}
		// Categoria
		if (cat > 0) {
			query += "AND cp.cdprodutosubgrupo.cdprodutogrupo.cdprodutocat = " + cat + " ";
			// Grupo
			if (grupo > 0) {
				query += "AND cp.cdprodutosubgrupo.cdprodutogrupo = " + grupo + " ";
				// SubGrupo
				if (gruposub > 0) {
					query += "AND cp.cdprodutosubgrupo = " + gruposub + " ";
				}
			}
		}
		// Status
		if (!status.equals("X")) {
			query += "AND cp.status = '" + status + "' ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}

	// PRODUTO--------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<CdProduto> listaCdProduto(Date dataini, Date datafim, String tipo, String tipoitem, Integer cat,
			Integer grupo, Integer gruposub, String busca, String status, String ordem, String ordemdir,
			Pageable pageable) {
		String query = retCdProduto(dataini, datafim, tipo, tipoitem, cat, grupo, gruposub, busca, status, ordem,
				ordemdir);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<CdProduto> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<CdProduto> results = em.createQuery(query, CdProduto.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	public List<CdProduto> listaCdProdutoDTO(Date dataini, Date datafim, String tipo, String tipoitem, Integer cat,
			Integer grupo, Integer gruposub, String busca, String status, String ordem, String ordemdir,
			Pageable pageable) {
		String query = retCdProduto(dataini, datafim, tipo, tipoitem, cat, grupo, gruposub, busca, status, ordem,
				ordemdir);
		List<CdProduto> results = em.createQuery(query, CdProduto.class).getResultList();
		return results;
	}

	public List<CdProduto> listaCdProdutoExcel(Date dataini, Date datafim, String tipo, String tipoitem, Integer cat,
			Integer grupo, Integer gruposub, String busca, String status, String ordem, String ordemdir) {
		String query = retCdProduto(dataini, datafim, tipo, tipoitem, cat, grupo, gruposub, busca, status, ordem,
				ordemdir);
		List<CdProduto> results = em.createQuery(query, CdProduto.class).getResultList();
		return results;
	}

	private String retCdProduto(Date dataini, Date datafim, String tipo, String tipoitem, Integer cat, Integer grupo,
			Integer gruposub, String busca, String status, String ordem, String ordemdir) {
		String query = "SELECT c FROM CdProduto c WHERE ";
		// Data cadastro - ainda nao utilizado diretamente
		query += "c.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' ";
		// Tipo
		if (!tipo.equals("X")) {
			query += "AND c.tipo = '" + tipo + "' ";
		}
		// Tipo item sped
		if (!tipoitem.equals("X")) {
			query += "AND c.tipoitem = '" + tipoitem + "' ";
		}
		// Categoria
		if (cat > 0) {
			query += "AND c.cdprodutosubgrupo.cdprodutogrupo.cdprodutocat = " + cat + " ";
			// Grupo
			if (grupo > 0) {
				query += "AND c.cdprodutosubgrupo.cdprodutogrupo = " + grupo + " ";
				// SubGrupo
				if (gruposub > 0) {
					query += "AND c.cdprodutosubgrupo = " + gruposub + " ";
				}
			}
		}
		// Status
		if (!status.equals("X")) {
			query += "AND c.status = '" + status + "' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (CONVERT(c.id, CHAR) LIKE '%" + busca + "%' OR c.nome LIKE '%" + busca
					+ "%' OR c.codigo LIKE '%" + busca + "%' " + "OR c.cean LIKE '%" + busca
					+ "%' OR c.ceantrib LIKE '%" + busca + "%' OR c.ref LIKE '%" + busca + "%' OR c.info LIKE '%"
					+ busca + "%' " + "OR c.ncm LIKE '%" + busca + "%' OR c.codserv LIKE '%" + busca + "%' "
					+ "OR c.codalt LIKE '%" + busca + "%' OR c.descricao LIKE '%" + busca + "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}

	public Page<CdProdutoDTO> listaCdProdutosDTO(List<CdProduto> lista, Pageable pageable) {
		List<CdProdutoDTO> dtos = new ArrayList<CdProdutoDTO>();
		for (CdProduto l : lista) {
			CdProdutoDTO dt = new CdProdutoDTO();
			dt.setId(l.getId());
			dt.setCodigo(l.getCodigo());
			dt.setTipo(l.getTipo());
			dt.setNome(l.getNome());
			dt.setNcm(l.getNcm());
			dt.setCodserv(l.getCodserv());
			dt.setCdprodutounmed(l.getCdprodutounmed());
			dt.setStatus(l.getStatus());
			dtos.add(dt);
		}
		int total = lista.size();
		int toIndex = (pageable.getPageNumber() + 1) * pageable.getPageSize();
		int fromIndex = Math.max(toIndex - pageable.getPageSize(), 0);
		if (toIndex > total) {
			toIndex = total;
		}
		Page<CdProdutoDTO> pages = new PageImpl<CdProdutoDTO>(dtos.subList(fromIndex, toIndex), pageable, total);
		return pages;
	}

	public List<CdProdutoPrecoDTO> listaCdProdutoPrecoDTO(List<CdProdutoPreco> lista) {
		List<CdProdutoPrecoDTO> dtos = new ArrayList<CdProdutoPrecoDTO>();
		for (CdProdutoPreco l : lista) {
			CdProdutoPrecoDTO dt = new CdProdutoPrecoDTO();
			dt.setId(l.getId());
			dt.setCdproduto(l.getCdproduto());
			dt.setVvenda(l.getVvenda());
			dtos.add(dt);
		}
		return dtos;
	}

	// MAQUINAS E
	// EQUIPAMENTOS------------------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<CdMaqequip> listaCdMaqequip(Long local, String busca, String ordem, String ordemdir,
			Pageable pageable) {
		String query = "SELECT c FROM CdMaqequip c WHERE c.nome LIKE '%" + busca + "%' OR c.posto LIKE '%" + busca
				+ "%' " + "OR  c.tarefas LIKE '%" + busca + "%'";
		// Local
		if (local > 0) {
			query += "AND c.cdpessoaemp = " + local + " ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<CdMaqequip> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<CdMaqequip> results = em.createQuery(query, CdMaqequip.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	// CONFIGURACAO-FISCAL-------------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<CdNfeCfg> listaCdNfeCfg(Long local, String busca, String ordem, String ordemdir, Integer coduf,
			Pageable pageable) {
		String query = retCdNfeCfg(local, busca, ordem, ordemdir, coduf);
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		int limit = pageable.getPageSize();
		List<CdNfeCfg> itemsInNextPage = em.createQuery(query).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		List<CdNfeCfg> results = em.createQuery(query, CdNfeCfg.class).getResultList();
		return new PageImpl(itemsInNextPage, pageable, results.size());
	}

	private String retCdNfeCfg(Long local, String busca, String ordem, String ordemdir, Integer coduf) {
		String query = "SELECT c FROM CdNfeCfg c WHERE ";
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp.id = " + local + " ";
		} else {
			query += "c.cdpessoaemp.id != " + local + " ";
		}
		// Cod. UF
		if (coduf > 0) {
			query += "AND c.cdestadoaplic.id = " + coduf + " ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (CONVERT(c.id, CHAR) LIKE '%" + busca + "%' OR c.cfop LIKE '%" + busca
					+ "%' OR c.natop LIKE '%" + busca + "%' OR c.descricao LIKE '%" + busca + "%' OR c.info LIKE '%"
					+ busca + "%' OR c.cdestadoaplic.uf LIKE '%" + busca + "%' OR c.cdpessoaemp.nome LIKE '%" + busca
					+ "%') ";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}
}
