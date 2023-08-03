package com.midas.api.tenant.service;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.midas.api.constant.MidasConfig;
import com.midas.api.mt.config.DBContextHolder;
import com.midas.api.security.ClienteParam;
import com.midas.api.tenant.entity.CdCaixa;
import com.midas.api.tenant.entity.CdCartao;
import com.midas.api.tenant.entity.CdCcusto;
import com.midas.api.tenant.entity.CdEstado;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.CdProdutoCat;
import com.midas.api.tenant.entity.CdProdutoGrupo;
import com.midas.api.tenant.entity.CdProdutoPreco;
import com.midas.api.tenant.entity.CdProdutoSubgrupo;
import com.midas.api.tenant.entity.CdProdutoTab;
import com.midas.api.tenant.entity.FnTituloCcusto;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcDocItem;
import com.midas.api.tenant.fiscal.util.FnPagNomeUtil;
import com.midas.api.tenant.fiscal.util.FsTipoNomeUtil;
import com.midas.api.tenant.repository.CdCaixaRepository;
import com.midas.api.tenant.repository.CdCartaoRepository;
import com.midas.api.tenant.repository.CdCcustoRepository;
import com.midas.api.tenant.repository.CdCidadeRepository;
import com.midas.api.tenant.repository.CdEstadoRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdProdutoCatRepository;
import com.midas.api.tenant.repository.CdProdutoGrupoRepository;
import com.midas.api.tenant.repository.CdProdutoRepository;
import com.midas.api.tenant.repository.CdProdutoSubgrupoRepository;
import com.midas.api.tenant.repository.CdProdutoTabRepository;
import com.midas.api.tenant.repository.FnCxmvCustomRepository;
import com.midas.api.tenant.repository.FnTituloCcustoCustomRepository;
import com.midas.api.tenant.repository.FnTituloCcustoRepository;
import com.midas.api.tenant.repository.LcDocRepository;
import com.midas.api.util.FnCartaoStatusNomeUtil;
import com.midas.api.util.LcDocTipoNomeUtil;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CdEstadoRepository cdEstadoRp;
	@Autowired
	private CdCidadeRepository cdCidadeRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdCaixaRepository cdCaixaRp;
	@Autowired
	private CdCartaoRepository cdCartaoRp;
	@Autowired
	private CdCcustoRepository cdCcustoRp;
	@Autowired
	private FnTituloCcustoRepository fnTituloCcustoRp;
	@Autowired
	private CdProdutoCatRepository cdProdutoCatRp;
	@Autowired
	private CdProdutoGrupoRepository cdProdutoGrupoRp;
	@Autowired
	private CdProdutoSubgrupoRepository cdProdutoSubgrupoRp;
	@Autowired
	private CdProdutoTabRepository cdProdutoTabRp;
	@Autowired
	private CdProdutoRepository cdProdutoRp;
	@Autowired
	private LcDocRepository lcDocRp;
	@Autowired
	private FnTituloCcustoCustomRepository fnTituloCcustoCustomRp;
	@Autowired
	private FnCxmvCustomRepository fnCxmvCustomRp;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private ClienteParam prm;

	public byte[] geraRel(String nomerel, Map<String, Object> par, ServletContext context) {
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			connection.setCatalog(DBContextHolder.getCurrentDb());
			String camJasper = context.getRealPath("/reports") + File.separator + nomerel + ".jasper";
			par.put("REPORT_LOCALE", new Locale("pt", "BR"));
			JasperPrint print = JasperFillManager.fillReport(camJasper, par, connection);
			byte[] retorno = JasperExportManager.exportReportToPdf(print);
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] geraRelBean(String nomerel, Map<String, Object> par, ServletContext context, List<?> objs) {
		try {
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(objs);
			String camJasper = context.getRealPath("/reports") + File.separator + nomerel + ".jasper";
			par.put("REPORT_LOCALE", new Locale("pt", "BR"));
			JasperPrint print = JasperFillManager.fillReport(camJasper, par, ds);
			byte[] retorno = JasperExportManager.exportReportToPdf(print);
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public JasperPrint geraRelPrintExport(String nomerel, Map<String, Object> par, ServletContext context) {
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			connection.setCatalog(DBContextHolder.getCurrentDb());
			String camJasper = context.getRealPath("/reports") + File.separator + nomerel + ".jasper";
			par.put("REPORT_LOCALE", new Locale("pt", "BR"));
			JasperPrint print = JasperFillManager.fillReport(camJasper, par, connection);
			return print;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// DADOS DE PARAMETROS GERAIS *********************************
	public Map<String, Object> cdPessoaPDF(Long local, String pessoatipo, String dataini, String datafim,
			Integer cidade, Integer estado, String busca, String ordem, String ordemdir, Long respvend, String status,
			String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		String nomeestado = "Todos";
		String nomecidade = "Todas";
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE ");
		sql.append("p.tipo = '" + pessoatipo + "' AND ");
		// Local
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append(" p.emp = " + local + " AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Representante, resp. vendedor
		String nomevend = "Todos os repres. ou vendedores";
		if (respvend > 0) {
			sql.append(" p.cdpessoaresp_id = " + respvend + " AND ");
			Optional<CdPessoa> rs = cdPessoaRp.findById(respvend);
			nomevend = rs.get().getNome();
		}
		// Estado e cidade
		if (!estado.equals(0)) {
			sql.append(" es.id = '" + estado + "' AND ");
			nomeestado = cdEstadoRp.findById(estado).get().getNome();
		}
		if (!cidade.equals(0)) {
			sql.append(" c.id = '" + cidade + "' AND ");
			nomecidade = cdCidadeRp.findById(cidade).get().getNome();
		}
		// Data cadastro
		sql.append("p.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' AND ");
		// Status
		if (status.equals("X")) {
			sql.append(" p.status != 'TODOS' ");
			status = "Ativos e Inativos";
		}
		if (status.equals("ATIVO")) {
			sql.append(" p.status = 'ATIVO' ");
			status = "Ativos";
		}
		if (status.equals("INATIVO")) {
			sql.append(" p.status = 'INATIVO' ");
			status = "Inativos";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append(" AND (CONVERT(p.id, CHAR) LIKE '%" + busca + "%' OR " + "p.nome LIKE '%" + busca
					+ "%' OR p.fantasia LIKE '%" + busca + "%' OR p.cpfcnpj LIKE '%" + busca + "%' OR p.foneum LIKE '%"
					+ busca + "%' OR " + "p.email LIKE '%" + busca + "%' OR p.nomefin LIKE '%" + busca
					+ "%' OR p.emailfin LIKE '%" + busca + "%' ) ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		if (tipoimpressao.equals("0076")) {
			ordemID = " tab.nome,";
		}
		if (tipoimpressao.equals("0079")) {
			ordemID = " v.nome,";
		}
		// Ordem
		if (ordem.equals("id")) {
			sql.append(" ORDER BY " + ordemID + " p.id " + ordemdir);
			ordem = "Numérica [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("nome")) {
			sql.append(" ORDER BY " + ordemID + " p.nome " + ordemdir);
			ordem = "Alfabética [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cpfcnpj")) {
			sql.append(" ORDER BY " + ordemID + "  p.cpfcnpj " + ordemdir);
			ordem = "CPF/CNPJ [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("foneum")) {
			sql.append(" ORDER BY " + ordemID + "  p.foneum " + ordemdir);
			ordem = "Fone [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdcidade.nome")) {
			sql.append(" ORDER BY " + ordemID + "  c.nome " + ordemdir);
			ordem = "Cidade/Estado [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("status")) {
			sql.append(" ORDER BY " + ordemID + "  p.status " + ordemdir);
			ordem = "Status [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("pessoatipo", pessoatipo);
		par.put("nomelocal", nomelocal);
		par.put("nomevend", nomevend);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("nomeestado", nomeestado);
		par.put("nomecidade", nomecidade);
		par.put("ordem", ordem);
		par.put("status", status);
		return par;
	}

	public Map<String, Object> fnRecAvPDF(Long local, String dataini, String datafim, String tipo, String busca,
			String ordem, String ordemdir) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append(" e.id = '" + local + "' AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Tipo
		if (tipo.equals("X")) {
			sql.append(" r.tipo != '" + tipo + "' AND ");
			tipo = "Todos";
		}
		if (tipo.equals("P")) {
			sql.append(" r.tipo = 'P' AND ");
			tipo = "Pagamento";
		}
		if (tipo.equals("R")) {
			sql.append(" r.tipo = 'R' AND ");
			tipo = "Recebimento";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append(" (r.num LIKE '%" + busca + "%' OR r.ref LIKE '%" + busca + "%' OR c.nome LIKE '%" + busca
					+ "%') AND ");
		}
		// Data cadastro
		sql.append("(r.data BETWEEN '" + dataini + "' AND '" + datafim + "'  ");
		// Ordem
		if (ordem.equals("id")) {
			sql.append(") ORDER BY r.id " + ordemdir);
			ordem = "Númerica [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("data")) {
			sql.append(") ORDER BY r.data " + ordemdir);
			ordem = "Emissão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdpessoapara.nome")) {
			sql.append(") ORDER BY c.nome " + ordemdir);
			ordem = "Alfabética [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("ref")) {
			sql.append(") ORDER BY r.ref " + ordemdir);
			ordem = "Referência [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdpessoaemp")) {
			sql.append(") ORDER BY r.cdpessoaemp_id " + ordemdir);
			ordem = "Local [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("tipo")) {
			sql.append(") ORDER BY r.tipo " + ordemdir);
			ordem = "Tipo [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vtot")) {
			sql.append(") ORDER BY r.vtot " + ordemdir);
			ordem = "Valor [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("nomelocal", nomelocal);
		par.put("tipo", tipo);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> fnTituloPDF(Long local, Integer caixapref, String fpagpref, String tipo, String dataini,
			String datafim, String busca, String ove, String ordem, String ordemdir, Long para, Long resp,
			String comissao, String compago, String status, String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("e.id = '" + local + "' AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Caixa
		String nomecaixa = "Todos";
		if (caixapref > 0) {
			sql.append("r.cdcaixapref_id = " + caixapref + " AND ");
			Optional<CdCaixa> cx = cdCaixaRp.findById(caixapref);
			nomecaixa = cx.get().getNome();
		}
		// Forma pagamento
		String nomefpag = "Todas";
		if (!fpagpref.equals("X")) {
			sql.append("r.fpagpref = " + fpagpref + " AND ");
			nomefpag = FnPagNomeUtil.nomePg(fpagpref);
		}
		// Para quem
		String nomepara = "Todos";
		if (para > 0) {
			sql.append("c.id = " + para + " AND ");
			Optional<CdPessoa> pes = cdPessoaRp.findById(para);
			nomepara = pes.get().getNome();
		}
		// Responsavel - rep.
		String nomeresp = "Todos";
		if (resp > 0) {
			sql.append("c.cdpessoaresp_id = " + resp + " AND ");
			Optional<CdPessoa> pes = cdPessoaRp.findById(resp);
			nomeresp = pes.get().getNome();
		}
		// Tipo
		String nomede = "Fornecedor";
		String nometipo = "Contas a pagar";
		if (tipo.equals("P")) {
			sql.append("r.tipo = 'P' AND ");
		}
		if (tipo.equals("R")) {
			sql.append("r.tipo = 'R' AND ");
			nometipo = "Contas a receber";
			nomede = "Cliente";
		}
		// Se comissao
		if (!comissao.equals("X")) {
			sql.append("r.comissao = '" + comissao + "' AND ");
		}
		// Comissao paga vinda do titulo
		if (compago.equals("S")) {
			sql.append("tc.vsaldo != tc.vtot AND ");
		}
		if (compago.equals("N")) {
			sql.append("tc.vsaldo = tc.vtot AND ");
		}
		// Vence ou emissao
		String nomeove = "Emissão";
		// Se comissao
		if (!comissao.equals("S")) {
			if (ove.equals("E")) {
				sql.append("r.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' AND r.paracob = 'S' ");
			}
			if (ove.equals("V")) {
				sql.append("r.vence BETWEEN '" + dataini + "' AND '" + datafim + "' AND r.paracob = 'S' ");
				nomeove = "Vencimento";
			}
			if (ove.equals("B")) {
				sql.append("r.databaixa BETWEEN '" + dataini + "' AND '" + datafim + "' AND r.paracob = 'S' ");
				nomeove = "Última baixa";
			}
		} else {
			sql.append("tc.databaixa BETWEEN '" + dataini + "' AND '" + datafim + "' AND tc.paracob = 'S' ");
			nomeove = "Última baixa";
		}
		// Status se aberto ou quitado
		String nomestatus = "Todos";
		if (status.equals("A")) {
			sql.append("AND  r.vsaldo != 0 ");
			nomestatus = "A pagar";
			if (tipo.equals("R")) {
				nomestatus = "A receber";
			}
		}
		if (status.equals("F")) {
			sql.append("AND (r.vsaldo = 0 OR r.vpago > 0) ");
			nomestatus = "Pagos";
			if (tipo.equals("R")) {
				nomestatus = "Recebidos";
			}
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append(" AND (CONVERT(r.id, CHAR) LIKE '%" + busca + "%' OR c.nome LIKE '%" + busca
					+ "%' OR c.fantasia LIKE '%" + busca + "%' OR c.cpfcnpj LIKE '%" + busca + "%' OR r.ref LIKE '%"
					+ busca + "%' OR r.info LIKE '%" + busca + "%' OR r.vparc LIKE '%" + busca + "%' OR r.vtot LIKE '%"
					+ busca + "%' OR r.vsaldo LIKE " + "'%" + busca + "%' OR r.vpago LIKE '%" + busca
					+ "%' OR pc.nome LIKE '%" + busca + "%' " + "OR pc.mascfinal = '" + busca
					+ "' OR CONVERT(r.numnota, CHAR) = '" + busca + "') ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		if (tipoimpressao.equals("0006")) {
			ordemID = "c.nome,";
		}
		if (tipoimpressao.equals("0007")) {
			ordemID = "pc.mascfinal,";
		}
		if (tipoimpressao.equals("0022")) {
			ordemID = "cdc.id,";
			sql.append(" GROUP BY ftc.id ");
		}
		if (tipoimpressao.equals("0057")) {
			ordemID = "c.nome,c.id,";
		}
		if (tipoimpressao.equals("0077")) {
			ordemID = "cx.id,c.nome,";
		}
		if (tipoimpressao.equals("0082")) {
			ordemID = "r.vence,";
		}
		// Agrupa por ID - se nao for relatorio de classificacao
		if (!tipoimpressao.equals("0007") && !tipoimpressao.equals("0022")) {
			sql.append(" GROUP BY r.id ");
		}
		// Ordem
		if (ordem.equals("id")) {
			sql.append(" ORDER BY " + ordemID + "r.id " + ordemdir);
			ordem = "Númerica [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("parcnum")) {
			sql.append(" ORDER BY " + ordemID + "r.parcnum " + ordemdir);
			ordem = "Parcela [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("datacad")) {
			sql.append(" ORDER BY " + ordemID + "r.datacad " + ordemdir);
			ordem = "Emissão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vence")) {
			sql.append(" ORDER BY " + ordemID + "r.vence " + ordemdir);
			ordem = "Vencimento [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("databaixa")) {
			sql.append(" ORDER BY " + ordemID + "r.databaixa " + ordemdir);
			ordem = "Baixa (Últ.) [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdpessoapara.nome")) {
			sql.append(" ORDER BY " + ordemID + "c.nome " + ordemdir);
			ordem = "Alfabética [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vparc")) {
			sql.append(" ORDER BY " + ordemID + "r.vparc " + ordemdir);
			ordem = "Valor total [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vdesc")) {
			sql.append(" ORDER BY " + ordemID + "r.vdesc " + ordemdir);
			ordem = "Valor descontos [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vjuro")) {
			sql.append(" ORDER BY " + ordemID + "r.vjuro " + ordemdir);
			ordem = "Valor juros [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vpago")) {
			sql.append(" ORDER BY " + ordemID + "r.vpago " + ordemdir);
			ordem = "Valor pago [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vsaldo")) {
			sql.append(" ORDER BY " + ordemID + "r.vsaldo " + ordemdir);
			ordem = "Valor saldo [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql.toString());
		par.put("tipo", tipo);
		par.put("nomelocal", nomelocal);
		par.put("nomecaixa", nomecaixa);
		par.put("nomefpag", nomefpag);
		par.put("nomepara", nomepara);
		par.put("nomeresp", nomeresp);
		par.put("nomeove", nomeove);
		par.put("nomede", nomede);
		par.put("nomestatus", nomestatus);
		par.put("nometipo", nometipo);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> fnCxmvPDF(Long local, Integer caixa, String tipo, String fpag, String dataini,
			String datafim, String busca, String ordem, String ordemdir, Long para, String lanca, String status,
			String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		// Geracao saldo anterior
		BigDecimal saldoAnterior = fnCxmvCustomRp.retFnCxmvValoresSaldoAnterior(local, caixa, tipo, fpag,
				Date.valueOf(dataini), Date.valueOf(datafim), busca, para, lanca, status);
		// String sql
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("e.id = '" + local + "' AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Lancamento
		String nomelanca = "Todos";
		if (lanca.equals("C")) {
			sql.append("f.cdpessoaemp_id != f.cdpessoapara_id AND ");
			nomelanca = "PELAS CONTAS";
		}
		if (lanca.equals("M")) {
			sql.append("f.cdpessoaemp_id = f.cdpessoapara_id AND ");
			nomelanca = "MANUAIS";
		}
		// F. pagamento
		String nomefpag = "Todas";
		if (!fpag.equals("X")) {
			sql.append("c.fpag = '" + fpag + "' AND ");
			nomefpag = FnPagNomeUtil.nomePg(fpag);
		}
		// Tipo
		if (!tipo.equals("X")) {
			sql.append("f.tipo = '" + tipo + "' AND ");
		}
		// Status
		String nomestatus = "Todos";
		if (!status.equals("X")) {
			sql.append("c.status = '" + status + "' AND ");
			nomestatus = status;
		}
		// Caixa
		String nomecaixa = "Todos";
		if (caixa > 0) {
			sql.append("c.cdcaixa_id = " + caixa + " AND ");
			Optional<CdCaixa> cx = cdCaixaRp.findById(caixa);
			nomecaixa = cx.get().getNome();
		}
		// Para quem
		String nomepara = "Todos";
		if (para > 0) {
			sql.append("f.cdpessoapara_id = " + para + " AND ");
			Optional<CdPessoa> pes = cdPessoaRp.findById(para);
			nomepara = pes.get().getNome();
		}
		// Data
		sql.append("c.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append(" AND (CONVERT(f.id, CHAR) LIKE '%" + busca + "%' OR p.nome LIKE '%" + busca
					+ "%' OR p.fantasia LIKE '%" + busca + "%' OR p.cpfcnpj LIKE '%" + busca + "%' OR c.info LIKE '%"
					+ busca + "%' OR f.ref LIKE '%" + busca + "%' OR pc.nome LIKE '%" + busca + "%' OR pc.mascfinal = '"
					+ busca + "') ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		if (tipoimpressao.equals("0010")) {
			ordemID = "cx.id,";
		}
		if (tipoimpressao.equals("0011")) {
			sql.append(" GROUP BY fdt.id ");
			ordemID = "pc.mascfinal,";
		}
		if (tipoimpressao.equals("0012")) {
			ordemID = "c.fpag,";
		}
		if (tipoimpressao.equals("0023")) {
			ordemID = "cdc.id,";
			sql.append(" GROUP BY ftc.id ");
		}
		if (!tipoimpressao.equals("0011") && !tipoimpressao.equals("0023")) {
			sql.append(" GROUP BY c.id ");
		}
		// Ordem
		if (ordem.equals("id")) {
			sql.append(" ORDER BY " + ordemID + "c.id " + ordemdir);
			ordem = "Númerica [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fntitulo.tipo")) {
			sql.append(" ORDER BY " + ordemID + "f.tipo " + ordemdir);
			ordem = "Tipo [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("datacad,horacad")) {
			sql.append(" ORDER BY " + ordemID + "c.datacad,c.horacad " + ordemdir);
			ordem = "Data [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fntitulo.cdpessoapara.nome")) {
			sql.append(" ORDER BY " + ordemID + "p.nome " + ordemdir);
			ordem = "Movimento para [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fntitulo.ref")) {
			sql.append(" ORDER BY " + ordemID + "f.ref " + ordemdir);
			ordem = "Referência / Descr. [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdcaixa.nome,fpag")) {
			sql.append(" ORDER BY " + ordemID + "cx.nome, c.fpag " + ordemdir);
			ordem = "Caixa e Forma Pag. [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vsub")) {
			sql.append(" ORDER BY " + ordemID + "c.vsub " + ordemdir);
			ordem = "Valor subtotal [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vdesc")) {
			sql.append(" ORDER BY " + ordemID + "c.vdesc " + ordemdir);
			ordem = "Valor descontos [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vjuro")) {
			sql.append(" ORDER BY " + ordemID + "c.vjuro " + ordemdir);
			ordem = "Valor juros [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vtot")) {
			sql.append(" ORDER BY " + ordemID + "c.vtot " + ordemdir);
			ordem = "Valor total [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("tipo", tipo);
		par.put("nomelocal", nomelocal);
		par.put("nomecaixa", nomecaixa);
		par.put("nomepara", nomepara);
		par.put("nomestatus", nomestatus);
		par.put("nomelanca", nomelanca);
		par.put("nomefpag", nomefpag);
		par.put("saldoanterior", saldoAnterior);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> fnCartaoPDF(Long local, Integer caixa, Integer cartao, String dataini, String datafim,
			String busca, String ope, String ordem, String ordemdir, String tipo, String status, String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("e.id = '" + local + "' AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Caixa
		String nomecaixa = "Todos";
		if (caixa > 0) {
			sql.append("cn.cdcaixa_id = " + caixa + " AND ");
			Optional<CdCaixa> cx = cdCaixaRp.findById(caixa);
			nomecaixa = cx.get().getNome();
		}
		// Cartao
		String nomecartao = "Todos";
		if (cartao > 0) {
			sql.append("c.cdcartao_id = " + cartao + " AND ");
			Optional<CdCartao> cd = cdCartaoRp.findById(cartao);
			nomecartao = cd.get().getNome();
		}
		// Data previsao ou cadastro
		String nomeope = "EMISSÃO";
		if (ope.equals("E")) {
			sql.append("c.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		}
		if (ope.equals("P")) {
			sql.append("c.dataprev BETWEEN '" + dataini + "' AND '" + datafim + "' ");
			nomeope = "PREVISÃO";
		}
		// Tipo
		String nometipo = "Todos";
		if (!tipo.equals("X")) {
			sql.append("AND f.tipo = '" + tipo + "' ");
			nometipo = "PAGAMENTO";
			if (tipo.equals("R")) {
				nometipo = "RECEBIMENTO";
			}
		}
		// Status
		String nomestatus = "Todos";
		if (!status.equals("X")) {
			sql.append("AND c.status = '" + status + "' ");
			nomestatus = FnCartaoStatusNomeUtil.nomeSt(status);
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append(" AND (p.nome LIKE '%" + busca + "%' OR cd.nome LIKE '%" + busca + "%' OR cx.nome LIKE '%"
					+ busca + "%' OR f.ref LIKE '%" + busca + "%' OR CONVERT(f.id, CHAR) LIKE '" + busca + "') ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		if (tipoimpressao.equals("0014")) {
			ordemID = "cd.id,";
		}
		if (tipoimpressao.equals("0015")) {
			ordemID = "cx.id,";
		}
		if (tipoimpressao.equals("0016")) {
			ordemID = "p.id,";
		}
		// Ordem
		if (ordem.equals("id")) {
			sql.append(" ORDER BY " + ordemID + "c.id " + ordemdir);
			ordem = "Númerica [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("status")) {
			sql.append(" ORDER BY " + ordemID + "c.status " + ordemdir);
			ordem = "Status [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("datacad")) {
			sql.append(" ORDER BY " + ordemID + "c.datacad " + ordemdir);
			ordem = "Emissão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("dataprev")) {
			sql.append(" ORDER BY " + ordemID + "c.dataprev " + ordemdir);
			ordem = "Previsão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fncxmv.fntitulo.cdpessoapara.nome")) {
			sql.append(" ORDER BY " + ordemID + "p.nome " + ordemdir);
			ordem = "Movimento para [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fncxmv.cdpessoaemp.nome,fncxmv.cdcaixa.nome")) {
			sql.append(" ORDER BY " + ordemID + "e.nome, cd.nome " + ordemdir);
			ordem = "Local e caixa de controle [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdcartao.nome")) {
			sql.append(" ORDER BY " + ordemID + "cd.nome " + ordemdir);
			ordem = "Cartão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vtot")) {
			sql.append(" ORDER BY " + ordemID + "c.vtot " + ordemdir);
			ordem = "Valor total [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("ptaxa")) {
			sql.append(" ORDER BY " + ordemID + "c.ptaxa " + ordemdir);
			ordem = "Perc. taxa [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vtaxa")) {
			sql.append(" ORDER BY " + ordemID + "c.vtaxa " + ordemdir);
			ordem = "Valor taxa [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vfinal")) {
			sql.append(" ORDER BY " + ordemID + "c.vfinal " + ordemdir);
			ordem = "Valor final [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("tipo", tipo);
		par.put("nomelocal", nomelocal);
		par.put("nomecaixa", nomecaixa);
		par.put("nomecartao", nomecartao);
		par.put("nometipo", nometipo);
		par.put("nomestatus", nomestatus);
		par.put("nomeope", nomeope);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> fnChequePDF(Long local, Integer caixa, String dataini, String datafim, String busca,
			String ove, String ordem, String ordemdir, String tipo, String status, String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("e.id = '" + local + "' AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Caixa atual
		String nomecaixa = "Todos";
		if (caixa > 0) {
			sql.append("c.cdcaixaatual_id = " + caixa + " AND ");
			Optional<CdCaixa> cx = cdCaixaRp.findById(caixa);
			nomecaixa = cx.get().getNome();
		}
		// Vence, emissao ou ultima baixa
		String nomeove = "Emissão";
		if (ove.equals("N")) {
			sql.append("c.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' ");
			nomeove = "Entrada";
		}
		if (ove.equals("E")) {
			sql.append("c.data BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		}
		if (ove.equals("V")) {
			sql.append("c.vence BETWEEN '" + dataini + "' AND '" + datafim + "' ");
			nomeove = "Vencimento";
		}
		// Tipo
		String nometipo = "Todos";
		if (!tipo.equals("X")) {
			sql.append("AND c.tipo = '" + tipo + "' ");
			nometipo = "PRÓPRIO - EMITIDO PELA EMPRESA";
			if (tipo.equals("02")) {
				nometipo = "TERCEIRO";
			}
		}
		// Status
		String nomestatus = "Todos";
		if (!status.equals("X")) {
			sql.append("AND c.status = '" + status + "' ");
			nomestatus = FnCartaoStatusNomeUtil.nomeSt(status);
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append(" AND (c.agencia LIKE '%" + busca + "%' OR c.cdbancos_id = '" + busca + "' OR c.conta LIKE '%"
					+ busca + "%' OR c.cpfcnpj LIKE '%" + busca + "%' OR c.emissor LIKE '%" + busca
					+ "%' OR c.info LIKE '%" + busca + "%') ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		if (tipoimpressao.equals("0018")) {
			ordemID = "c.cdbancos_id,";
		}
		if (tipoimpressao.equals("0019")) {
			ordemID = "c.cdcaixaatual_id,";
		}
		if (tipoimpressao.equals("0020")) {
			ordemID = "c.tipo,";
		}
		if (tipoimpressao.equals("0021")) {
			ordemID = "c.cpfcnpj,";
		}
		// Ordem
		if (ordem.equals("id")) {
			sql.append(" ORDER BY " + ordemID + "c.id " + ordemdir);
			ordem = "Númerica [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("datacad")) {
			sql.append(" ORDER BY " + ordemID + "c.datacad " + ordemdir);
			ordem = "Emissão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdbancos_id")) {
			sql.append(" ORDER BY " + ordemID + "c.cdbancos_id " + ordemdir);
			ordem = "Banco [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("agencia,conta")) {
			sql.append(" ORDER BY " + ordemID + "c.agencia,c.conta " + ordemdir);
			ordem = "Agência e conta [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("data")) {
			sql.append(" ORDER BY " + ordemID + "c.data " + ordemdir);
			ordem = "Data de emissão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vence")) {
			sql.append(" ORDER BY " + ordemID + "c.vence " + ordemdir);
			ordem = "Vencimento [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("emissor")) {
			sql.append(" ORDER BY " + ordemID + "c.emissor " + ordemdir);
			ordem = "Emissor [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdcaixaatual.nome")) {
			sql.append(" ORDER BY " + ordemID + "cx.nome " + ordemdir);
			ordem = "Caixa atual [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("tipo")) {
			sql.append(" ORDER BY " + ordemID + "c.tipo " + ordemdir);
			ordem = "Tipo [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("status")) {
			sql.append(" ORDER BY " + ordemID + "c.status " + ordemdir);
			ordem = "Status [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vtot")) {
			sql.append(" ORDER BY " + ordemID + "c.vtot " + ordemdir);
			ordem = "Valor total [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("num")) {
			sql.append(" ORDER BY " + ordemID + "c.num " + ordemdir);
			ordem = "Número cheque [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("tipo", tipo);
		par.put("nomelocal", nomelocal);
		par.put("nomecaixa", nomecaixa);
		par.put("nometipo", nometipo);
		par.put("nomestatus", nomestatus);
		par.put("nomeove", nomeove);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> fnTituloCcustoPDF(Long local, Integer ccusto, String dataini, String datafim,
			String ordem, String ordemdir) {
		List<FnTituloCcusto> titulosAberto = null;
		// Lista para calculos de saldos -------------------------
		if (local > 0) {
			titulosAberto = fnTituloCcustoRp.findAllByCdCustoEmabertoLocal(local, ccusto, Date.valueOf(dataini),
					Date.valueOf(datafim));
		} else {
			titulosAberto = fnTituloCcustoRp.findAllByCdCustoEmaberto(ccusto, Date.valueOf(dataini),
					Date.valueOf(datafim));
		}
		List<FnTituloCcusto> titulosPagos = null;
		// Lista para calculos de saldos
		if (local > 0) {
			titulosPagos = fnTituloCcustoRp.findAllByCdCustoPagosLocal(local, ccusto, Date.valueOf(dataini),
					Date.valueOf(datafim));
		} else {
			titulosPagos = fnTituloCcustoRp.findAllByCdCustoPagos(ccusto, Date.valueOf(dataini), Date.valueOf(datafim));
		}
		BigDecimal saldoAberto = new BigDecimal(0);
		BigDecimal saldoBaixado = new BigDecimal(0);
		BigDecimal saldoFinalGeral = new BigDecimal(0);
		BigDecimal[] saldo = fnTituloCcustoCustomRp.valorSaldo(titulosAberto, titulosPagos);
		saldoAberto = saldo[0].subtract(saldo[1]);
		saldoBaixado = saldo[2].subtract(saldo[3]);
		saldoFinalGeral = saldoAberto.add(saldoBaixado);
		// Fim calculo saldos gerais -------------------------------
		Map<String, Object> par = new HashMap<String, Object>();
		// Centro de custo
		Optional<CdCcusto> cuc = cdCcustoRp.findById(ccusto);
		String nomecentro = cuc.get().getNome();
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Vence
		String nomeove = "Vencimento e data de baixa";
		String sqla = fnTituloCcustoPDFOpt(local, ccusto, dataini, datafim, ordem, ordemdir, "A");
		String sqlf = fnTituloCcustoPDFOpt(local, ccusto, dataini, datafim, ordem, ordemdir, "F");
		// Ordem
		if (ordem.equals("id")) {
			ordem = "Númerica [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vence")) {
			ordem = "Vencimento [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("databaixa")) {
			ordem = "Baixa (Últ.) [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdpessoapara.nome")) {
			ordem = "Alfabética [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vtot")) {
			ordem = "Valor total [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vpago")) {
			ordem = "Valor pago [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vsaldo")) {
			ordem = "Valor saldo [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sqla", sqla);
		par.put("sqlf", sqlf);
		par.put("ccusto", ccusto + "");
		par.put("nomecentro", nomecentro);
		par.put("nomelocal", nomelocal);
		par.put("nomeove", nomeove);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		par.put("valorentradaaberto", saldo[0]);
		par.put("valorsaidaaberto", saldo[1]);
		par.put("valorentradabaixado", saldo[2]);
		par.put("valorsaidabaixado", saldo[3]);
		par.put("saldoemaberto", saldoAberto);
		par.put("saldobaixado", saldoBaixado);
		par.put("saldofinalgeral", saldoFinalGeral);
		return par;
	}

	private String fnTituloCcustoPDFOpt(Long local, Integer ccusto, String dataini, String datafim, String ordem,
			String ordemdir, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND ftc.cdccusto_id = " + ccusto + " AND ");
		// Local empresa
		if (local > 0) {
			sql.append("e.id = '" + local + "' AND ");
		}
		// Se em aberto ou nao
		if (status.equals("A")) {
			sql.append("t.vence BETWEEN '" + dataini + "' AND '" + datafim + "' AND t.paracob = 'S' ");
			sql.append("AND  t.vsaldo != 0 ");
		}
		if (status.equals("F")) {
			sql.append("t.databaixa BETWEEN '" + dataini + "' AND '" + datafim + "' AND t.paracob = 'S' ");
			sql.append("AND (t.vsaldo = 0 OR t.vpago > 0) ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		sql.append(" GROUP BY ftc.id ");
		String ordemID = " t.tipo,";
		// Ordem
		if (ordem.equals("id")) {
			sql.append(" ORDER BY " + ordemID + "t.id " + ordemdir);
		}
		if (ordem.equals("vence")) {
			sql.append(" ORDER BY " + ordemID + "t.vence " + ordemdir);
		}
		if (ordem.equals("databaixa")) {
			sql.append(" ORDER BY " + ordemID + "t.databaixa " + ordemdir);
		}
		if (ordem.equals("cdpessoapara.nome")) {
			sql.append(" ORDER BY " + ordemID + "c.nome " + ordemdir);
		}
		if (ordem.equals("vtot")) {
			sql.append(" ORDER BY " + ordemID + "t.vtot " + ordemdir);
		}
		if (ordem.equals("vpago")) {
			sql.append(" ORDER BY " + ordemID + "t.vpago " + ordemdir);
		}
		if (ordem.equals("vsaldo")) {
			sql.append(" ORDER BY " + ordemID + "t.vsaldo " + ordemdir);
		}
		return sql.toString();
	}

	public Map<String, Object> lcDocPDF(Long local, Long para, Long vep, String tipo, String categoria, String ofe,
			String dataini, String datafim, String busca, Integer coduf, String ordem, String ordemdir, String status,
			String tipoimpressao, Long[] itenssel) {
		Map<String, Object> par = new HashMap<String, Object>();
		// Verifica se mostra veiculo nos relatorios
		boolean mostraveiculo = prm.cliente().getSiscfg().isSis_mostra_veiculo();
		String nometipo = LcDocTipoNomeUtil.nomeTipoDoc(tipo);
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND l.tipo = '" + tipo + "' AND ");
		// Apenas alguns itens de DOC
		if (itenssel.length > 0) {
			String sIN = " l.id IN (";
			for (int x = 0; x < itenssel.length; x++) {
				if (x == 0) {
					sIN = sIN + "" + itenssel[x];
				} else {
					sIN = sIN + ", " + itenssel[x];
				}
			}
			sIN = sIN + ") AND ";
			sql.append(sIN);
		}
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("e.id = '" + local + "' AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Para - cliente
		String nomepara = "Todos os destinatários";
		if (para > 0) {
			sql.append("c.id = '" + para + "' AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(para);
			nomepara = loc.get().getNome();
		}
		// Para - estado
		String nomeestado = "Todos os estados";
		if (coduf > 0) {
			sql.append("c.cdestado_id = '" + coduf + "' AND ");
			Optional<CdEstado> loc = cdEstadoRp.findById(coduf);
			nomeestado = loc.get().getNome();
		}
		// Vendedor
		String nomevendedor = "Todos os vendedores(as) ou representantes";
		if (tipo.equals("02") || tipo.equals("04") || tipo.equals("06") || tipo.equals("07") || tipo.equals("08")) {
			nomevendedor = "Todos os responsáveis";
		}
		if (vep > 0) {
			sql.append("v.id = '" + vep + "' AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(vep);
			nomevendedor = loc.get().getNome();
		}
		// Categoria
		String nomecat = "Todas as categorias";
		if (!categoria.equals("00")) {
			sql.append("l.categoria = '" + categoria + "' AND ");
			nomecat = LcDocTipoNomeUtil.nomeCat(categoria);
		}
		// Datas
		String nomeofe = "EMISSÃO";
		if (ofe.equals("E")) {
			sql.append("l.dataem BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		}
		if (ofe.equals("F")) {
			sql.append("l.datafat BETWEEN '" + dataini + "' AND '" + datafim + "' ");
			nomeofe = "FATURAMENTO";
		}
		// Status
		String nomestatus = "Todos";
		if (!status.equals("0")) {
			sql.append("AND l.status = '" + status + "' ");
			nomestatus = LcDocTipoNomeUtil.nomeStDoc(status);
		}
		// Verifica tipo de relatorio para apenas servicos
		if (tipoimpressao.equals("0064")) {
			sql.append("AND pr.tipo != 'PRODUTO' ");
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append(" AND (CONVERT(l.id, CHAR) LIKE '%" + busca + "%' OR c.cpfcnpj LIKE '%" + busca
					+ "%' OR c.nome LIKE '%" + busca + "%' " + "OR l.ordemcps LIKE '%" + busca + "%' OR l.info LIKE '%"
					+ busca + "%' OR l.motcan LIKE '%" + busca + "%' " + "OR l.infolocal LIKE '%" + busca + "%'"
					+ " OR l.numero LIKE '%" + busca + "%' OR vei.placa LIKE '%" + busca + "%') ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		if (tipoimpressao.equals("0058") || tipoimpressao.equals("0066") || tipoimpressao.equals("0067")) {
			ordemID = " pr.codigo,pr.nome,";
		}
		if (tipoimpressao.equals("0032")) {
			ordemID = " v.nome,";
		}
		if (tipoimpressao.equals("0060")) {
			ordemID = " gr.id,gr.nome,";
		}
		if (tipoimpressao.equals("0064")) {
			ordemID = " tec.nome,tec.id,";
		}
		// Ordem
		if (ordem.equals("id")) {
			sql.append(" ORDER BY " + ordemID + "l.id " + ordemdir);
			ordem = "Númerica [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("dataem")) {
			sql.append(" ORDER BY " + ordemID + "l.datacad " + ordemdir);
			ordem = "Emissão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("datafat")) {
			sql.append(" ORDER BY " + ordemID + "l.datafat " + ordemdir);
			ordem = "Faturamento [" + ordemdir.toLowerCase() + "]";
			// Devolucoes
			if (tipo.equals("03") || tipo.equals("04")) {
				ordem = "Devolução [" + ordemdir.toLowerCase() + "]";
			}
			// Consumo interno
			if (tipo.equals("06")) {
				ordem = "Entrega [" + ordemdir.toLowerCase() + "]";
			}
			// Cotacao fornecedor
			if (tipo.equals("07")) {
				ordem = "Prev. Compra [" + ordemdir.toLowerCase() + "]";
			}
		}
		if (ordem.equals("numero")) {
			sql.append(" ORDER BY " + ordemID + "l.numero " + ordemdir);
			ordem = "Número doc. [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdpessoapara.nome")) {
			sql.append(" ORDER BY " + ordemID + "c.nome " + ordemdir);
			ordem = "Cliente [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("descgeral")) {
			sql.append(" ORDER BY " + ordemID + "l.descgeral " + ordemdir);
			ordem = "Desc. Geral [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdveiculo.placa")) {
			sql.append(" ORDER BY " + ordemID + "vei.placa " + ordemdir);
			ordem = "Placa [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("categoria")) {
			sql.append(" ORDER BY " + ordemID + "l.categoria " + ordemdir);
			ordem = "Categoria [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("numnota")) {
			sql.append(" ORDER BY " + ordemID + "l.numnota " + ordemdir);
			ordem = "Número nota [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("status")) {
			sql.append(" ORDER BY " + ordemID + "l.status " + ordemdir);
			ordem = "Status [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("adminconf")) {
			sql.append(" ORDER BY " + ordemID + "l.adminconf " + ordemdir);
			ordem = "Conferência [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vtottrib")) {
			sql.append(" ORDER BY " + ordemID + "l.vtottrib " + ordemdir);
			ordem = "Valor total trib. [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("nometipo", nometipo);
		par.put("nomelocal", nomelocal);
		par.put("nomepara", nomepara);
		par.put("nomevendedor", nomevendedor);
		par.put("nomestatus", nomestatus);
		par.put("nomeofe", nomeofe);
		par.put("nomecat", nomecat);
		par.put("nomeestado", nomeestado);
		par.put("mostraveiculo", mostraveiculo);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> fsNfePDF(String tipoimpressao, Long local, String dataini, String datafim, String tipo,
			String busca, String uffim, Integer modelo, String st, Integer status, String oensai, String ordem,
			String ordemdir) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("e.id = '" + local + "' AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Modelo nota
		String nomemodelo = FsTipoNomeUtil.nomeModelo(String.valueOf(modelo));
		if (modelo > 0) {
			sql.append("f.modelo = " + modelo + " AND ");
		}
		// UF Fim
		String nomeuffim = "Todos os estados";
		if (!uffim.equals("0")) {
			sql.append("entre.uf = '" + uffim + "' AND ");
			nomeuffim = uffim;
		}
		// Tipo emitida ou recebida
		String nometipo = FsTipoNomeUtil.nomeTipo(tipo);
		sql.append("f.tipo = '" + tipo + "' AND ");
		// Data ou entrada e saida
		String nomeoensai = "EMISSÃO";
		if (oensai.equals("E")) {
			sql.append("f.dhemi BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		}
		if (oensai.equals("S")) {
			sql.append("f.dhsaient BETWEEN '" + dataini + "' AND '" + datafim + "' ");
			nomeoensai = "ENTRADA OU SAÍDA";
		}
		// Situacao
		String nomest = "Todas";
		if (!st.equals("X")) {
			// Situação conferida
			if (st.equals("C")) {
				sql.append("AND (f.st_fornec = 'S' AND f.st_cob = 'S' AND f.st_est = 'S' AND f.st_custos = 'S') ");
			}
			// Situação pendentes
			if (st.equals("P")) {
				sql.append("AND (f.st_fornec = 'N' OR f.st_cob = 'N' OR f.st_est = 'N' OR f.st_custos = 'N') ");
			}
			nomest = FsTipoNomeUtil.nomeSt(String.valueOf(st));
		}
		// Status
		String nomestatus = "Todos";
		if (status != 0) {
			sql.append("AND f.status = '" + status + "' ");
			nomestatus = FsTipoNomeUtil.nomeStatus(String.valueOf(status));
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append("AND (CONVERT(f.id, CHAR) LIKE '%" + busca + "%' OR e.xnome LIKE '%" + busca
					+ "%' OR e.xfant LIKE '%" + busca + "%' OR " + "e.xmun LIKE '%" + busca + "%' OR e.cpfcnpj LIKE '%"
					+ busca + "%' OR d.xnome LIKE '%" + busca + "%' OR d.xfant LIKE '%" + busca + "%' OR d.xmun LIKE '%"
					+ busca + "%' OR d.cpfcnpj LIKE '%" + busca + "%')");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		String sqlJoinCdProduto = "";
		if (tipoimpressao.equals("0083")) {
			ordemID = " pr.codigo,pr.nome,";
			// FAZER "READ FIELDS" ANTES DE PASSAR COMO UM PARAMETRO
			if (tipo.equals("R")) {
				sqlJoinCdProduto = "LEFT JOIN cd_produto AS pr ON fi.idprod = pr.id";
			} else {
				sqlJoinCdProduto = "LEFT JOIN cd_produto AS pr ON fi.cprod = pr.codigo ";
			}
		}
		// Ordem
		if (ordem.equals("id")) {
			sql.append(" ORDER BY " + ordemID + "f.id " + ordemdir);
			ordem = "Númerica [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("nnf")) {
			sql.append(" ORDER BY " + ordemID + "f.nnf " + ordemdir);
			ordem = "Número nota [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("dhemi")) {
			sql.append(" ORDER BY " + ordemID + "f.dhemi " + ordemdir);
			ordem = "Emissão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("dhsaient")) {
			sql.append(" ORDER BY " + ordemID + "f.dhsaient " + ordemdir);
			ordem = "Entrada ou saída [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fsnfepartdest.xnome")) {
			sql.append(" ORDER BY " + ordemID + "d.xnome " + ordemdir);
			ordem = "Destinatário [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fsnfepartent.uf")) {
			sql.append(" ORDER BY " + ordemID + "entre.uf " + ordemdir);
			ordem = "Destino [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("natop")) {
			sql.append(" ORDER BY " + ordemID + "f.natop " + ordemdir);
			ordem = "Natureza da op. [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fsnfetoticms.vnf")) {
			sql.append(" ORDER BY " + ordemID + "t.vnf " + ordemdir);
			ordem = "Valor total [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("status")) {
			sql.append(" ORDER BY " + ordemID + "f.status " + ordemdir);
			ordem = "Status [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("sqlJoinCdProduto", sqlJoinCdProduto);
		par.put("nometipo", nometipo);
		par.put("nomemodelo", nomemodelo);
		par.put("nomelocal", nomelocal);
		par.put("nomestatus", nomestatus);
		par.put("nomest", nomest);
		par.put("nomeoensai", nomeoensai);
		par.put("nomeuffim", nomeuffim);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> fsNfsePDF(Long local, String dataini, String datafim, String tipo, String busca,
			String st, Integer status, String ordem, String ordemdir) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("AND e.id = '" + local + "' ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Tipo emitida ou recebida
		String nometipo = FsTipoNomeUtil.nomeTipo(tipo);
		sql.append("AND f.tipo = '" + tipo + "' ");
		// Situacao
		String nomest = "Todas";
		if (!st.equals("X")) {
			// Situação conferida
			if (st.equals("C")) {
				sql.append("AND (f.st_fornec = 'S' AND f.st_cob = 'S') ");
			}
			// Situação pendentes
			if (st.equals("P")) {
				sql.append("AND (f.st_fornec = 'N' OR f.st_cob = 'N') ");
			}
			nomest = FsTipoNomeUtil.nomeSt(String.valueOf(st));
		}
		// Status
		String nomestatus = "Todos";
		if (status != 0) {
			sql.append("AND f.status = '" + status + "' ");
			nomestatus = FsTipoNomeUtil.nomeStatus(String.valueOf(status));
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append("AND (CONVERT(f.id, CHAR) LIKE '%" + busca + "%' OR e.xnome LIKE '%" + busca
					+ "%' OR e.xfant LIKE '%" + busca + "%' OR " + "e.xmun LIKE '%" + busca + "%' OR e.cpfcnpj LIKE '%"
					+ busca + "%' OR t.xnome LIKE '%" + busca + "%' OR t.xfant LIKE '%" + busca + "%' OR t.xmun LIKE '%"
					+ busca + "%' OR t.cpfcnpj LIKE '%" + busca + "%') ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		// Ordem
		if (ordem.equals("id")) {
			sql.append("ORDER BY " + ordemID + "f.id " + ordemdir);
			ordem = "Númerica [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("nnfs")) {
			sql.append("ORDER BY " + ordemID + "f.nnfs " + ordemdir);
			ordem = "Número nota [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("demis")) {
			sql.append("ORDER BY " + ordemID + "f.demis " + ordemdir);
			ordem = "Emissão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fsnfseparttoma.xnome")) {
			sql.append("ORDER BY " + ordemID + "t.xnome " + ordemdir);
			ordem = "Tomador [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("natop")) {
			sql.append("ORDER BY " + ordemID + "f.natop " + ordemdir);
			ordem = "Natureza da op. [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fsnfsetot.vservicos")) {
			sql.append("ORDER BY " + ordemID + "tot.vservicos " + ordemdir);
			ordem = "Valor total [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("status")) {
			sql.append("ORDER BY " + ordemID + "f.status " + ordemdir);
			ordem = "Status [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("nometipo", nometipo);
		par.put("nomelocal", nomelocal);
		par.put("nomestatus", nomestatus);
		par.put("nomest", nomest);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> fsCtePDF(Long local, String dataini, String datafim, String tipo, String busca,
			String uffim, Integer modelo, String st, String oensai, String ordem, String ordemdir,
			String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("e.id = '" + local + "' AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Modelo nota
		String nomemodelo = FsTipoNomeUtil.nomeModelo(String.valueOf(modelo));
		if (modelo > 0) {
			sql.append("f.modelo = " + modelo + " AND ");
		}
		// Tipo emitida ou recebida
		String nometipo = FsTipoNomeUtil.nomeTipo(tipo);
		sql.append("f.tipo = '" + tipo + "' AND ");
		// Data ou entrada e saida
		String nomeoensai = "EMISSÃO";
		if (oensai.equals("E")) {
			sql.append("f.dhemi BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		}
		if (oensai.equals("S")) {
			sql.append("f.dhsaient BETWEEN '" + dataini + "' AND '" + datafim + "' ");
			nomeoensai = "ENTRADA OU SAÍDA";
		}
		// Status
		String nomest = "Todos";
		// Situação conferida
		if (st.equals("C")) {
			sql.append("AND (f.st_transp = 'S' AND f.st_cob = 'S') ");
			nomest = FsTipoNomeUtil.nomeSt(String.valueOf(st));
		}
		// Situação pendentes
		if (st.equals("P")) {
			sql.append("AND (f.st_transp = 'N' OR f.st_cob = 'N') ");
			nomest = FsTipoNomeUtil.nomeSt(String.valueOf(st));
		}
		String nomeuffim = "Todos";
		// Destino - Uffim
		if (!uffim.equals("0")) {
			sql.append("AND f.uffim = '" + uffim + "' ");
			nomeuffim = uffim;
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append("AND (CONVERT(f.nct, CHAR) LIKE '%" + busca + "%' OR ee.xnome LIKE '%" + busca + "%' OR "
					+ "e.xfant LIKE '%" + busca + "%' OR e.xmun LIKE '%" + busca + "%' OR " + "e.cpfcnpj LIKE '%"
					+ busca + "%' OR fsctepartdest.xnome LIKE '%" + busca + "%' OR " + "d.xfant LIKE '%" + busca
					+ "%' OR d.xmun LIKE '%" + busca + "%' OR " + "d.cpfcnpj LIKE '%" + busca
					+ "%' OR fsctepartrem.xnome LIKE '%" + busca + "%' OR " + "r.xfant LIKE '%" + busca
					+ "%' OR r.xmun LIKE '%" + busca + "%' OR " + "r.cpfcnpj LIKE '%" + busca + "%' OR f.natop LIKE '%"
					+ busca + "%' OR f.chaveac " + "LIKE '%" + busca + "%') ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		if (tipoimpressao.equals("0055")) {
			ordemID = " f.uffim,";
		}
		// Ordem
		if (ordem.equals("id")) {
			sql.append(" ORDER BY " + ordemID + "f.id " + ordemdir);
			ordem = "Númerica [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("nct")) {
			sql.append(" ORDER BY " + ordemID + "f.nct " + ordemdir);
			ordem = "Número nota [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("dhemi")) {
			sql.append(" ORDER BY " + ordemID + "f.dhemi " + ordemdir);
			ordem = "Emissão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("dhsaient")) {
			sql.append(" ORDER BY " + ordemID + "f.dhsaient " + ordemdir);
			ordem = "Entrada ou saída [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fsctepartemit")) {
			sql.append(" ORDER BY " + ordemID + "emi.xnome " + ordemdir);
			ordem = "Emitente [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("uffim")) {
			sql.append(" ORDER BY " + ordemID + "f.uffim " + ordemdir);
			ordem = "Destino [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("natop")) {
			sql.append(" ORDER BY " + ordemID + "f.natop " + ordemdir);
			ordem = "Natureza da op. [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("fsctevprest.vtprest")) {
			sql.append(" ORDER BY " + ordemID + "v.vtprest " + ordemdir);
			ordem = "Valor total [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("nometipo", nometipo);
		par.put("nomemodelo", nomemodelo);
		par.put("nomelocal", nomelocal);
		par.put("nomest", nomest);
		par.put("nomeoensai", nomeoensai);
		par.put("nomeuffim", nomeuffim);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> fsMdfePDF(Long local, String dataini, String datafim, String tipo, String busca,
			Integer status, String enc, String ordem, String ordemdir) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("e.id = '" + local + "' AND ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Tipo emitida ou recebida
		String nometipo = FsTipoNomeUtil.nomeTipo(tipo);
		sql.append("f.tipo = '" + tipo + "' AND ");
		// Data
		sql.append("f.dhemi BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		// Status
		String nomestatus = "Todos";
		if (status != 0) {
			sql.append("AND f.status = '" + status + "' ");
			nomestatus = FsTipoNomeUtil.nomeStatus(String.valueOf(status));
		}
		// Encerramento
		String nomeencerra = "Todos";
		if (!enc.equals("X")) {
			sql.append("AND f.encerrado = '" + enc + "' ");
			nomeencerra = FsTipoNomeUtil.nomeEncerra(enc);
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append("AND (CONVERT(f.nmdf, CHAR) LIKE '%" + busca + "%' OR ufi.nome LIKE '%" + busca
					+ "%' OR uff.nome LIKE '%" + busca + "%' OR f.vcarga LIKE '%" + busca + "%' OR f.chaveac LIKE '%"
					+ busca + "%') ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		// Ordem
		if (ordem.equals("nmdf")) {
			sql.append(" ORDER BY " + ordemID + "f.nmdf " + ordemdir);
			ordem = "Número nota [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("dhemi")) {
			sql.append(" ORDER BY " + ordemID + "f.dhemi " + ordemdir);
			ordem = "Emissão [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdestadofim.nome")) {
			sql.append(" ORDER BY " + ordemID + "uff.nome " + ordemdir);
			ordem = "Destino [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("qnfe")) {
			sql.append(" ORDER BY " + ordemID + "f.qnfe " + ordemdir);
			ordem = "Quantidade [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("qcarga")) {
			sql.append(" ORDER BY " + ordemID + "f.qcarga " + ordemdir);
			ordem = "Peso total [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vcarga")) {
			sql.append(" ORDER BY " + ordemID + "f.vcarga " + ordemdir);
			ordem = "Valor total [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("status")) {
			sql.append(" ORDER BY " + ordemID + "f.status " + ordemdir);
			ordem = "Status [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("nometipo", nometipo);
		par.put("nomelocal", nomelocal);
		par.put("nomestatus", nomestatus);
		par.put("nomeencerra", nomeencerra);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> esEstPDF(Long local, String dataini, String datafim, String tipoitem, String busca,
			String st, String ordem, String ordemdir, String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND p.tipo = 'PRODUTO' ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("AND e.id = '" + local + "' ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Tipoitem
		String nometipoitem = "Todos";
		if (!tipoitem.equals("X")) {
			sql.append("AND p.tipoitem = '" + tipoitem + "' ");
			nometipoitem = FsTipoNomeUtil.nomeTipoItemEs(tipoitem);
		}
		// Datas
		sql.append("AND c.dataat BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		// Status
		String nomestatus = "Todos";
		if (!st.equals("X")) {
			sql.append("AND p.status = '" + st + "' ");
			nomestatus = FsTipoNomeUtil.nomeStEs(st);
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append("AND (CONVERT(p.id, CHAR) LIKE '%" + busca + "%' OR " + "p.nome LIKE '%" + busca
					+ "%' OR p.descricao LIKE '%" + busca + "%' OR p.infoncmserv LIKE '%" + busca
					+ "%' OR p.ref LIKE '%" + busca + "%' OR p.info LIKE '%" + busca + "%' OR p.ncm LIKE '%" + busca
					+ "%' OR p.codigo LIKE '%" + busca + "%') ");
		}
		// Apenas itens positivos se impressao 0042
		if (tipoimpressao.equals("0042")) {
			sql.append("AND c.qtd > 0 ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		if (tipoimpressao.equals("0035")) {
			ordemID = " e.id,";
		}
		// Ordem
		if (ordem.equals("cdproduto.codigo")) {
			sql.append(" ORDER BY " + ordemID + "p.codigo " + ordemdir);
			ordem = "Código [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdproduto.nome")) {
			sql.append(" ORDER BY " + ordemID + "p.nome " + ordemdir);
			ordem = "Produto [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdproduto.cdprodutounmed.sigla")) {
			sql.append(" ORDER BY " + ordemID + "u.sigla " + ordemdir);
			ordem = "UN. [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("dataat")) {
			sql.append(" ORDER BY " + ordemID + "c.dataat " + ordemdir);
			ordem = "Últ. atualização [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("qtdmin")) {
			sql.append(" ORDER BY " + ordemID + "c.qtdmin " + ordemdir);
			ordem = "Estoque mínimo [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("qtd")) {
			sql.append(" ORDER BY " + ordemID + "c.qtd " + ordemdir);
			ordem = "Estoque atual [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vcusto")) {
			sql.append(" ORDER BY " + ordemID + "c.vcusto " + ordemdir);
			ordem = "Valor custo atual [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdproduto.status")) {
			sql.append(" ORDER BY " + ordemID + "p.status " + ordemdir);
			ordem = "Status [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("nometipoitem", nometipoitem);
		par.put("nomelocal", nomelocal);
		par.put("nomestatus", nomestatus);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> esEstMovPDF(Long local, String dataini, String datafim, Long idprod, String ordem,
			String ordemdir, String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND p.tipo = 'PRODUTO' ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("AND e.id = '" + local + "' ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Datas
		sql.append("AND c.data BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		// Produto
		if (idprod > 0) {
			sql.append("AND p.id = " + idprod + " ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		// Ordem
		if (ordem.equals("cdproduto.codigo")) {
			sql.append(" ORDER BY " + ordemID + "p.codigo " + ordemdir);
			ordem = "Código [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdpessoapara.nome")) {
			sql.append(" ORDER BY " + ordemID + "pp.nome " + ordemdir);
			ordem = "Para [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("tpdoc")) {
			sql.append(" ORDER BY " + ordemID + "c.tpdoc " + ordemdir);
			ordem = "Tipo mov. [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("data,hora")) {
			sql.append(" ORDER BY " + ordemID + "c.data, c.hora " + ordemdir);
			ordem = "Data e hora [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("qtdini")) {
			sql.append(" ORDER BY " + ordemID + "c.qtdini " + ordemdir);
			ordem = "Qtd. inicial [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("qtd")) {
			sql.append(" ORDER BY " + ordemID + "c.qtd " + ordemdir);
			ordem = "Qtd. movimento [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("qtdfim")) {
			sql.append(" ORDER BY " + ordemID + "c.qtdfim " + ordemdir);
			ordem = "Qtd. final [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("tipo")) {
			sql.append(" ORDER BY " + ordemID + "c.tipo " + ordemdir);
			ordem = "Tipo [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("nomelocal", nomelocal);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> esEstExtPDF(Long local, Long vep, String dataini, String datafim, String busca,
			String ordem, String ordemdir, String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.data BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("AND emp.id = '" + local + "' ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Vendedor
		String nomevendedor = "Todos os vendedores(as) ou representantes";
		if (vep > 0) {
			sql.append("AND v.id = '" + vep + "' ");
			Optional<CdPessoa> vendedor = cdPessoaRp.findById(vep);
			nomevendedor = vendedor.get().getNome();
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append("AND (CONVERT(e.id, CHAR) LIKE '%" + busca + "%' OR e.cdpessoavendedor.cpfcnpj LIKE '%" + busca
					+ "%' OR e.cdpessoavendedor.nome LIKE '%" + busca + "%' OR e.cdproduto.nome LIKE '%" + busca
					+ "%') ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		if (tipoimpressao.equals("0071")) {
			ordemID = " v.id,";
		}
		// Ordem
		if (ordem.equals("cdproduto.codigo")) {
			sql.append(" ORDER BY " + ordemID + "p.codigo " + ordemdir);
			ordem = "Código [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("data")) {
			sql.append(" ORDER BY " + ordemID + "e.data " + ordemdir);
			ordem = "Data [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("descricao")) {
			sql.append(" ORDER BY " + ordemID + "e.descricao " + ordemdir);
			ordem = "Produto [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdpessoavendedor.nome")) {
			sql.append(" ORDER BY " + ordemID + "v.nome " + ordemdir);
			ordem = "Vendedor [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdproduto.cdprodutounmed.sigla")) {
			sql.append(" ORDER BY " + ordemID + "u.sigla " + ordemdir);
			ordem = "Unidade [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("qtd")) {
			sql.append(" ORDER BY " + ordemID + "e.qtd " + ordemdir);
			ordem = "Quantidade [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("nomelocal", nomelocal);
		par.put("nomevendedor", nomevendedor);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> producaoMovPDF(Long local, String dataini, String datafim, Long idprod, String ordem,
			String ordemdir, String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE e.status = 'ATIVO' AND p.tipo = 'PRODUTO' AND tpdoc = '05' ");
		// Local empresa
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append("AND e.id = '" + local + "' ");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Datas
		sql.append("AND c.data BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		// Produto
		if (idprod > 0) {
			sql.append("AND p.id = " + idprod + " ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		// Ordem
		if (ordem.equals("cdproduto.codigo")) {
			sql.append(" ORDER BY " + ordemID + "p.codigo " + ordemdir);
			ordem = "Código [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdpessoapara.nome")) {
			sql.append(" ORDER BY " + ordemID + "pp.nome " + ordemdir);
			ordem = "Para [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("tpdoc")) {
			sql.append(" ORDER BY " + ordemID + "c.tpdoc " + ordemdir);
			ordem = "Tipo mov. [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("data,hora")) {
			sql.append(" ORDER BY " + ordemID + "c.data, c.hora " + ordemdir);
			ordem = "Data e hora [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("qtdini")) {
			sql.append(" ORDER BY " + ordemID + "c.qtdini " + ordemdir);
			ordem = "Qtd. inicial [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("qtd")) {
			sql.append(" ORDER BY " + ordemID + "c.qtd " + ordemdir);
			ordem = "Qtd. movimento [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("qtdfim")) {
			sql.append(" ORDER BY " + ordemID + "c.qtdfim " + ordemdir);
			ordem = "Qtd. final [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("tipo")) {
			sql.append(" ORDER BY " + ordemID + "c.tipo " + ordemdir);
			ordem = "Tipo [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("nomelocal", nomelocal);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> cdProdPDF(String dataini, String datafim, String tipo, String tipoitem, Integer cat,
			Integer grupo, Integer gruposub, String busca, String status, String ordem, String ordemdir,
			String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		// Datas
		sql.append("WHERE p.datacad BETWEEN '" + dataini + "' AND '" + datafim + "' ");
		// Tipo
		String nometipo = "PRODUTOS E SERVIÇOS";
		if (!tipo.equals("X")) {
			sql.append("AND p.tipo = '" + tipo + "' ");
			nometipo = tipo;
		}
		// Tipo item sped
		String nometipoitem = "Todas as qualificações";
		if (!tipoitem.equals("X")) {
			sql.append("AND p.tipoitem = '" + tipoitem + "' ");
			nometipoitem = FsTipoNomeUtil.nomeTipoItemEs(tipoitem);
		}
		// Categoria, grupo e subgrupo
		String nomecat = "Todas as categorias";
		String nomegp = "Todos os grupos";
		String nomesubgp = "Todos os subgrupos";
		if (cat > 0) {
			sql.append("AND c.id = " + cat + " ");
			Optional<CdProdutoCat> categoria = cdProdutoCatRp.findById(cat);
			nomecat = categoria.get().getNome();
			// Grupo
			if (grupo > 0) {
				sql.append("AND g.id = " + grupo + " ");
				Optional<CdProdutoGrupo> gp = cdProdutoGrupoRp.findById(grupo);
				nomegp = gp.get().getNome();
				// SubGrupo
				if (gruposub > 0) {
					sql.append("AND sg.id = " + gruposub + " ");
					Optional<CdProdutoSubgrupo> subgp = cdProdutoSubgrupoRp.findById(gruposub);
					nomesubgp = subgp.get().getNome();
				}
			}
		}
		// Status
		String nomestatus = "Todos";
		if (!status.equals("X")) {
			sql.append("AND p.status = '" + status + "' ");
			nomestatus = FsTipoNomeUtil.nomeStEs(status);
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append("AND (CONVERT(p.id, CHAR) LIKE '%" + busca + "%' OR p.nome LIKE '%" + busca
					+ "%' OR p.codigo LIKE '%" + busca + "%' " + "OR p.cean LIKE '%" + busca
					+ "%' OR p.ceantrib LIKE '%" + busca + "%' OR p.ref LIKE '%" + busca + "%' OR p.info LIKE '%"
					+ busca + "%' " + "OR p.ncm LIKE '%" + busca + "%' OR p.codserv LIKE '%" + busca + "%' "
					+ "OR p.codalt LIKE '%" + busca + "%' OR p.descricao LIKE '%" + busca + "%') ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		// if (tipoimpressao.equals("0038")) ordemID = " p.id,";
		// Ordem
		if (ordem.equals("codigo")) {
			sql.append(" ORDER BY " + ordemID + "p.codigo " + ordemdir);
			ordem = "Código [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("nome")) {
			sql.append(" ORDER BY " + ordemID + "p.nome " + ordemdir);
			ordem = "Produto [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("tipo")) {
			sql.append(" ORDER BY " + ordemID + "p.tipo " + ordemdir);
			ordem = "Tipo [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vlrvenda")) {
			sql.append(" ORDER BY " + ordemID + "p.vlrvenda " + ordemdir);
			ordem = "Valor venda [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("status")) {
			sql.append(" ORDER BY " + ordemID + "p.status " + ordemdir);
			ordem = "Status [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("nometipo", nometipo);
		par.put("nometipoitem", nometipoitem);
		par.put("nomestatus", nomestatus);
		par.put("nomecat", nomecat);
		par.put("nomegp", nomegp);
		par.put("nomesubgp", nomesubgp);
		par.put("dataini", dataini);
		par.put("datafim", datafim);
		par.put("ordem", ordem);
		return par;
	}

	public Map<String, Object> cdProdPrecoPDF(String busca, Long local, Integer tabela, String tipoitem, String status,
			String ordem, String ordemdir, String tipoimpressao) {
		Map<String, Object> par = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		// Inicio where
		sql.append("WHERE");
		// Local
		String nomelocal = "Todos os locais";
		if (local > 0) {
			sql.append(" pt.cdpessoaemp_id = " + local + " AND");
			Optional<CdPessoa> loc = cdPessoaRp.findById(local);
			nomelocal = loc.get().getNome();
		}
		// Tabela
		String nometabela = "Todas as tabelas";
		if (tabela > 0) {
			sql.append(" pp.cdprodutotab_id = " + tabela);
			Optional<CdProdutoTab> tab = cdProdutoTabRp.findById(tabela);
			nometabela = tab.get().getNome();
		}
		// Tipo item
		String nometipoitem = "Todos";
		if (!tipoitem.equals("XX")) {
			sql.append(" AND p.tipoitem = '" + tipoitem + "'");
			nometipoitem = FsTipoNomeUtil.nomeTipoItemEs(tipoitem);
		}
		// Status
		String nomestatus = "Todos";
		if (!status.equals("X")) {
			sql.append(" AND p.status = '" + status + "'");
			nomestatus = FsTipoNomeUtil.nomeStEs(status);
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			sql.append(" AND (CONVERT(pp.id, CHAR) LIKE '%" + busca + "%' OR p.nome LIKE '%" + busca
					+ "%' OR p.codigo LIKE '%" + busca + "%' OR p.cean LIKE '%" + busca + "%' "
					+ "OR p.ceantrib LIKE '%" + busca + "%' OR p.ref LIKE '%" + busca + "%' OR p.info LIKE '%" + busca
					+ "%'" + "OR p.ncm LIKE '%" + busca + "%' OR p.codserv LIKE '%" + busca + "%') ");
		}
		// Agrupamento para varias colunas - usado em alguns relatorios
		String ordemID = "";
		if (tipoimpressao.equals("0048") || tipoimpressao.equals("0048_1") || tipoimpressao.equals("0063")
				|| tipoimpressao.equals("0063_1")) {
			ordemID = "cs.nome,";
		}
		// Ordem
		if (ordem.equals("cdproduto.codigo")) {
			sql.append(" ORDER BY " + ordemID + "p.codigo " + ordemdir);
			ordem = "Código [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdproduto.nome")) {
			sql.append(" ORDER BY " + ordemID + "p.nome " + ordemdir);
			ordem = "Produto [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdproduto.cdprodutounmed.sigla")) {
			sql.append(" ORDER BY " + ordemID + "u.sigla " + ordemdir);
			ordem = "Un. [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("vvenda")) {
			sql.append(" ORDER BY " + ordemID + "pp.vvenda " + ordemdir);
			ordem = "Valor venda [" + ordemdir.toLowerCase() + "]";
		}
		if (ordem.equals("cdproduto.status")) {
			sql.append(" ORDER BY " + ordemID + "p.status " + ordemdir);
			ordem = "Status [" + ordemdir.toLowerCase() + "]";
		}
		// Outros dados
		par.put("software", mc.MidasApresenta);
		par.put("sql", sql);
		par.put("nomelocal", nomelocal);
		par.put("nomestatus", nomestatus);
		par.put("nometabela", nometabela);
		par.put("nometipoitem", nometipoitem);
		par.put("ordem", ordem);
		return par;
	}

	// Mais comercializados**************************
	public List<LcDocItem> itensMC(List<LcDoc> docs, String incluiZerado) {
		List<LcDocItem> lista = new ArrayList<LcDocItem>();
		List<CdProduto> prods = cdProdutoRp.findAllByStatus("ATIVO");
		// Lista todos os itens do cadastro
		for (CdProduto p : prods) {
			// Soma
			BigDecimal qtd = BigDecimal.ZERO;
			BigDecimal vuni = BigDecimal.ZERO;
			BigDecimal vtotrib = BigDecimal.ZERO;
			// Docs
			for (LcDoc i : docs) {
				// Itens - produtos
				for (LcDocItem it : i.getLcdocitem()) {
					// Se igual
					if (p.getId().equals(it.getCdproduto().getId())) {
						qtd = qtd.add(it.getQtd());
						vtotrib = vtotrib.add(it.getVtottrib());
					}
				}
			}
			if (qtd.compareTo(BigDecimal.ZERO) > 0) {
				vuni = vtotrib.divide(qtd, 4, RoundingMode.HALF_EVEN);
			}
			// Adiciona a lista principal
			if (qtd.compareTo(BigDecimal.ZERO) > 0) {
				LcDocItem item = new LcDocItem();
				item.setId(p.getId());
				item.setCdproduto(p);
				item.setCdnfecfg(docs.get(0).getCdnfecfg());
				item.setCdpessoatec(docs.get(0).getCdpessoaemp());
				item.setQtd(qtd);
				item.setVuni(vuni);
				item.setVtottrib(vtotrib);
				lista.add(item);
			}
			if (qtd.compareTo(BigDecimal.ZERO) == 0 && incluiZerado.equals("S")) {
				LcDocItem item = new LcDocItem();
				item.setId(p.getId());
				item.setCdproduto(p);
				item.setCdnfecfg(docs.get(0).getCdnfecfg());
				item.setCdpessoatec(docs.get(0).getCdpessoaemp());
				item.setQtd(qtd);
				item.setVuni(vuni);
				item.setVtottrib(vtotrib);
				lista.add(item);
			}
		}
		// Ordenacao por QTD
		Collections.sort(lista, Comparator.comparing(LcDocItem::getQtd).reversed());
		return lista;
	}

	// Cliente inativos - nao compra a dias**************************
	public List<CdPessoa> clientesInativosNC(Long local, Long vendedor, Date database) {
		List<CdPessoa> pessoas = cdPessoaRp.findAllByTodosAtivo("ATIVO");
		List<CdPessoa> pesreturn = new ArrayList<CdPessoa>();
		LcDoc doc = null;
		for (CdPessoa p : pessoas) {
			if (local > 0) {
				doc = lcDocRp.lcDocNCLocal(local, p.getId(), database);
			} else {
				doc = lcDocRp.lcDocNC(p.getId(), database);
			}
			if (doc != null) {
				// COMPRARAM NO PERIODO
			}
			if (doc == null) {
				// NAO COMPRARAM NO PERIODO
				if (!p.getTipo().equals("EMPRESA")) {
					pesreturn.add(p);
				}
			}
		}
		// Filtra vendedor se necessario
		if (vendedor > 0) {
			pesreturn = pesreturn.stream().filter(p -> vendedor.equals(p.getCdpessoaresp_id()))
					.collect(Collectors.toList());
		}
		return pesreturn;
	}

	// Monta List Bean conforme qtd*******************
	public List<LcDoc> listDocsEtq(Long iddoc, Integer qtd) {
		List<LcDoc> docs = new ArrayList<LcDoc>();
		// Pega item
		LcDoc doc = lcDocRp.findById(iddoc).get();
		for (int i = 1; i <= qtd; i++) {
			docs.add(doc);
		}
		return docs;
	}

	public List<CdProdutoPreco> listProdutoEtq(CdProdutoPreco prodPreco, Integer qtd) {
		List<CdProdutoPreco> prods = new ArrayList<CdProdutoPreco>();
		for (int i = 1; i <= qtd; i++) {
			prods.add(prodPreco);
		}
		return prods;
	}

	// Gerador de assinatura****************
	public String assinaturaDigital() {
		String retorno = "";
		if (prm.cliente().getSiscfg().isSis_assinafn_auto() == true) {
			String nome = prm.cliente().getNome();
			String sobrenome = prm.cliente().getSobrenome();
			retorno = nome + " " + sobrenome;
		}
		return retorno;
	}
}
