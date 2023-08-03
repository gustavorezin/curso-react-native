package com.midas.api.tenant.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.CdProdutoDre;
import com.midas.api.tenant.entity.CdProdutoMarkup;
import com.midas.api.tenant.entity.CdProdutoPreco;
import com.midas.api.tenant.entity.CdProdutoTab;
import com.midas.api.tenant.entity.EsEst;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdProdutoDreRepository;
import com.midas.api.tenant.repository.CdProdutoPrecoRepository;
import com.midas.api.tenant.repository.CdProdutoRepository;
import com.midas.api.tenant.repository.CdProdutoTabRepository;
import com.midas.api.tenant.repository.EsEstRepository;
import com.midas.api.util.NumUtil;

@Service
public class CdProdutoService {
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdProdutoDreRepository cdProdutoDreRp;
	@Autowired
	private CdProdutoRepository cdProdutoRp;
	@Autowired
	private CdProdutoTabRepository cdProdutoTabRp;
	@Autowired
	private CdProdutoPrecoRepository cdProdutoPrecoRp;
	@Autowired
	private EsEstRepository esEstRp;

	// VERIFICAR CONFIG INI.ITEM************************************************
	public void verificaDadosIniciais(CdProduto pr) throws SQLException {
		List<CdPessoa> emps = cdPessoaRp.findAllByTipoTodos("EMPRESA");
		// Plano de contas ------------------------------------------
		for (CdPessoa p : emps) {
			if (cdProdutoDreRp.findByEmpProdutoPlCon(p.getId(), pr.getId()) == 0) {
				CdProdutoDre e = new CdProdutoDre();
				e.setCdpessoaemp(p);
				e.setCdproduto(pr);
				e.setCdplconmicro_id(0);
				cdProdutoDreRp.save(e);
			}
		}
	}

	// CONFIGURA CODIGO PRODUTO************************************************
	public Integer retCodigoItem(CdProduto pr) throws Exception {
		String tipoItem = pr.getTipoitem();
		CdProduto ultimo = cdProdutoRp.ultimoProdutoTipo(pr.getTipoitem());
		Integer codigo = 1;
		if (ultimo != null) {
			if (ultimo.getCodigo() != null) {
				// Ja retorna item adicionado
				codigo = ultimo.getCodigo() + 1;
				return Integer.valueOf(codigo);
			}
		}
		// Se nao houver item cadastrado, segue aqui
		if (tipoItem.equals("00")) {
			tipoItem = "49";
			String novoCodigo = NumUtil.geraNumEsq(codigo, 6);
			String montaCodigo = tipoItem + "" + novoCodigo;
			return Integer.valueOf(montaCodigo);
		}
		String novoCodigo = NumUtil.geraNumEsq(codigo, 7);
		String montaCodigo = tipoItem + "" + novoCodigo;
		return Integer.valueOf(montaCodigo);
	}

	public CdProdutoPreco regUnicoPreco(Long id, CdProduto pr, CdProdutoTab cpt, BigDecimal vCusto,
			BigDecimal indMarkup, BigDecimal pMarkup, BigDecimal vVenda, BigDecimal vVendaIdeal, BigDecimal vLucro,
			BigDecimal pDescMax, BigDecimal pCom, BigDecimal vCom) {
		CdProdutoPreco pp = new CdProdutoPreco();
		pp.setId(id);
		pp.setCdproduto(pr);
		pp.setCdprodutotab(cpt);
		pp.setVcusto(vCusto);
		pp.setIndmarkup(indMarkup);
		pp.setPmarkup(pMarkup);
		pp.setVvenda(vVenda);
		pp.setVvendaideal(vVendaIdeal);
		pp.setVlucro(vLucro);
		pp.setPdescmax(pDescMax);
		pp.setPcom(pCom);
		pp.setVcom(vCom);
		return cdProdutoPrecoRp.save(pp);
	}

	// CONFIGURA TABELAS DE PRECOS*******************************************
	public void configTabPreco(CdProduto p) {
		// Lista tabelas para geracao - inclusive desativadas
		List<CdProdutoTab> tabs = cdProdutoTabRp.findAll();
		for (CdProdutoTab t : tabs) {
			// Verifica se ja tem
			CdProdutoPreco pv = cdProdutoPrecoRp.findByCdprodutoAndCdprodutotab(p, t);
			if (pv == null) {
				// Estoque-valor-custo---------------
				EsEst e = esEstRp.findByEmpProduto(t.getCdpessoaemp().getId(), p.getId());
				BigDecimal vCusto = new BigDecimal(0);
				if (e != null) {
					vCusto = e.getVcusto().setScale(8, RoundingMode.HALF_EVEN);
					// Indice markup----------------------
					BigDecimal indMarkup = calculoIndMarkup(t.getCdprodutomarkup());
					if (e.getVcusto().compareTo(BigDecimal.ZERO) > 0) {
						// Valor de venda
						BigDecimal vVenda = vCusto.multiply(indMarkup).setScale(8, RoundingMode.HALF_EVEN);
						// Valor de lucro e percentual
						BigDecimal vLucro = vVenda.subtract(vCusto);
						BigDecimal pMarkup = NumUtil.calculoPerc(vCusto, vVenda, 4);
						// Comissao aprox.
						BigDecimal pCom = t.getCdprodutomarkup().getPcom();
						BigDecimal vCom = vVenda.multiply(pCom).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
						// Cria
						regUnicoPreco(null, p, t, vCusto, indMarkup, pMarkup, vVenda, vVenda, vLucro,
								t.getCdprodutomarkup().getPdescmax(), pCom, vCom);
					} else {
						// Cria vazio
						BigDecimal zero = new BigDecimal(0);
						regUnicoPreco(null, p, t, zero, zero, zero, zero, zero, zero,
								t.getCdprodutomarkup().getPdescmax(), zero, zero);
					}
				}
			}
		}
	}

	// ATUALIZA TABELAS DE PRECOS*******************************************
	public void atualizaTabPreco(Long local, CdProduto p, BigDecimal vCusto) {
		// Lista tabelas para geracao - inclusive desativadas
		List<CdProdutoTab> tabs = cdProdutoTabRp.findAllByLocal(local);
		for (CdProdutoTab t : tabs) {
			// Verifica se ja tem
			CdProdutoPreco pv = cdProdutoPrecoRp.findByCdprodutoAndCdprodutotab(p, t);
			if (pv != null) {
				// Indice markup----------------------
				BigDecimal indMarkup = calculoIndMarkup(t.getCdprodutomarkup());
				if (vCusto.compareTo(BigDecimal.ZERO) > 0) {
					vCusto = vCusto.setScale(8, RoundingMode.HALF_EVEN);
					// Valor de venda
					BigDecimal vVenda = vCusto.multiply(indMarkup).setScale(8, RoundingMode.HALF_EVEN);
					// Valor de lucro e percentual
					BigDecimal vLucro = vVenda.subtract(vCusto).setScale(8, RoundingMode.HALF_EVEN);
					;
					BigDecimal pMarkup = NumUtil.calculoPerc(vCusto, vVenda, 4);
					// Comissao aprox.
					BigDecimal pCom = t.getCdprodutomarkup().getPcom();
					BigDecimal vCom = vVenda.multiply(pCom).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
					// Cria
					regUnicoPreco(pv.getId(), p, t, vCusto, indMarkup, pMarkup, vVenda, vVenda, vLucro,
							t.getCdprodutomarkup().getPdescmax(), pCom, vCom);
				} else {
					// Cria vazio
					BigDecimal zero = new BigDecimal(0);
					regUnicoPreco(null, p, t, zero, zero, zero, zero, zero, zero, t.getCdprodutomarkup().getPdescmax(),
							zero, zero);
				}
			}
		}
	}

	// REDEFINIR TABELAS DE PRECOS MARKUP*******************************************
	public void configTabPreco(Integer markup) {
		// Lista tabelas para geracao - inclusive desativadas
		List<CdProdutoTab> tabs = cdProdutoTabRp.findAllByMarkup(markup);
		for (CdProdutoTab t : tabs) {
			List<CdProdutoPreco> precos = cdProdutoPrecoRp.findByTab(t.getId());
			for (CdProdutoPreco ps : precos) {
				// Estoque-valor-custo---------------
				EsEst e = esEstRp.findByEmpProduto(t.getCdpessoaemp().getId(), ps.getCdproduto().getId());
				BigDecimal vCusto = new BigDecimal(0);
				if (e != null) {
					vCusto = e.getVcusto().setScale(8, RoundingMode.HALF_EVEN);
					;
					// Indice markup----------------------
					BigDecimal indMarkup = calculoIndMarkup(t.getCdprodutomarkup());
					if (e.getVcusto().compareTo(BigDecimal.ZERO) > 0) {
						// Valor de venda
						BigDecimal vVenda = vCusto.multiply(indMarkup).setScale(8, RoundingMode.HALF_EVEN);
						// Valor de lucro e percentual
						BigDecimal vLucro = vVenda.subtract(vCusto).setScale(8, RoundingMode.HALF_EVEN);
						;
						BigDecimal pMarkup = NumUtil.calculoPerc(vCusto, vVenda, 4);
						// Comissao aprox.
						BigDecimal pCom = t.getCdprodutomarkup().getPcom();
						BigDecimal vCom = vVenda.multiply(pCom).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
						// Cria
						regUnicoPreco(ps.getId(), ps.getCdproduto(), t, vCusto, indMarkup, pMarkup, vVenda, vVenda,
								vLucro, t.getCdprodutomarkup().getPdescmax(), pCom, vCom);
					}
				}
			}
		}
	}

	// REDEFINIR DE PRECOS ATUALIZA
	// TABELA*******************************************
	public void configTabPrecoTab(CdProdutoTab t) {
		List<CdProdutoPreco> precos = cdProdutoPrecoRp.findByTab(t.getId());
		for (CdProdutoPreco ps : precos) {
			// Estoque-valor-custo---------------
			EsEst e = esEstRp.findByEmpProduto(t.getCdpessoaemp().getId(), ps.getCdproduto().getId());
			BigDecimal vCusto = new BigDecimal(0);
			if (e != null) {
				vCusto = e.getVcusto().setScale(8, RoundingMode.HALF_EVEN);
				;
				// Indice markup----------------------
				BigDecimal indMarkup = calculoIndMarkup(t.getCdprodutomarkup());
				if (e.getVcusto().compareTo(BigDecimal.ZERO) > 0) {
					// Valor de venda
					BigDecimal vVenda = vCusto.multiply(indMarkup).setScale(8, RoundingMode.HALF_EVEN);
					// Valor de lucro e percentual
					BigDecimal vLucro = vVenda.subtract(vCusto).setScale(8, RoundingMode.HALF_EVEN);
					;
					BigDecimal pMarkup = NumUtil.calculoPerc(vCusto, vVenda, 4);
					// Comissao aprox.
					BigDecimal pCom = t.getCdprodutomarkup().getPcom();
					BigDecimal vCom = vVenda.multiply(pCom).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
					// Cria
					regUnicoPreco(ps.getId(), ps.getCdproduto(), t, vCusto, indMarkup, pMarkup, vVenda, vVenda, vLucro,
							t.getCdprodutomarkup().getPdescmax(), pCom, vCom);
				}
			}
		}
	}

	// CONFIGURA NOVA TABELA DE PRECO*******************************************
	public void novaTabPreco(CdProdutoTab t, CdProdutoMarkup mk) {
		// Lanca para todos os itens
		List<CdProduto> prs = cdProdutoRp.findAll();
		for (CdProduto p : prs) {
			// Estoque-valor-custo---------------
			EsEst e = esEstRp.findByEmpProduto(t.getCdpessoaemp().getId(), p.getId());
			BigDecimal vCusto = new BigDecimal(0);
			if (e != null) {
				vCusto = e.getVcusto().setScale(8, RoundingMode.HALF_EVEN);
				;
				// Indice markup----------------------
				BigDecimal indMarkup = calculoIndMarkup(mk);
				if (e.getVcusto().compareTo(BigDecimal.ZERO) > 0) {
					// Valor de venda
					BigDecimal vVenda = vCusto.multiply(indMarkup).setScale(8, RoundingMode.HALF_EVEN);
					// Valor de lucro e percentual
					BigDecimal vLucro = vVenda.subtract(vCusto).setScale(8, RoundingMode.HALF_EVEN);
					;
					BigDecimal pMarkup = NumUtil.calculoPerc(vCusto, vVenda, 4);
					// Comissao aprox.
					BigDecimal pCom = mk.getPcom();
					BigDecimal vCom = vVenda.multiply(pCom).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
					// Cria
					regUnicoPreco(null, p, t, vCusto, indMarkup, pMarkup, vVenda, vVenda, vLucro, mk.getPdescmax(),
							pCom, vCom);
				} else {
					// Cria vazio
					BigDecimal zero = new BigDecimal(0);
					regUnicoPreco(null, p, t, zero, zero, zero, zero, zero, zero, mk.getPdescmax(), zero, zero);
				}
			}
		}
	}

	// ALTERA CUSTO TABELA DE PRECO*********************************
	public void alterarParTabPrecoCusto(CdProdutoPreco pre, BigDecimal vcusto) {
		if (vcusto.compareTo(BigDecimal.ZERO) > 0) {
			// Atualiza na tabela do estoque tambem
			esEstRp.updateVCustoProdLocal(vcusto, pre.getCdproduto().getId(),
					pre.getCdprodutotab().getCdpessoaemp().getId());
			// Valor de venda
			BigDecimal vVenda = vcusto.multiply(pre.getIndmarkup()).setScale(8, RoundingMode.HALF_EVEN);
			// Valor de lucro e percentual
			BigDecimal vLucro = vVenda.subtract(vcusto).setScale(8, RoundingMode.HALF_EVEN);
			;
			BigDecimal pMarkup = NumUtil.calculoPerc(vcusto, vVenda, 4);
			// Comissao aprox.
			BigDecimal pCom = pre.getPcom();
			BigDecimal vCom = vVenda.multiply(pCom).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
			// Cria
			regUnicoPreco(pre.getId(), pre.getCdproduto(), pre.getCdprodutotab(), vcusto, pre.getIndmarkup(), pMarkup,
					vVenda, vVenda, vLucro, pre.getPdescmax(), pCom, vCom);
		}
	}

	// ALTERA PARAMETROS TABELAS DE PRECOS*********************************
	public void alterarParTabPreco(CdProduto p, BigDecimal pdescmax, BigDecimal pcom) {
		List<CdProdutoPreco> prs = cdProdutoPrecoRp.findAllByCdproduto(p);
		for (CdProdutoPreco pre : prs) {
			BigDecimal vCom = pre.getVvenda().multiply(pcom).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
			regUnicoPreco(pre.getId(), p, pre.getCdprodutotab(), pre.getVcusto(), pre.getIndmarkup(), pre.getPmarkup(),
					pre.getVvenda(), pre.getVvendaideal(), pre.getVlucro(), pdescmax, pcom, vCom);
		}
	}

	// CALCULO PADRAO INDICE MARKUP****************************************
	private BigDecimal calculoIndMarkup(CdProdutoMarkup pm) {
		BigDecimal ret = new BigDecimal(0);
		// Variaveis-------------
		BigDecimal cemporc = new BigDecimal(100);
		BigDecimal df = pm.getPdespfixas();
		BigDecimal dv = pm.getPdespvar();
		BigDecimal lp = pm.getPlucropret();
		// Calculo 01
		BigDecimal calc01 = df.add(dv).add(lp);
		// Calculo 02
		BigDecimal calc02 = cemporc.subtract(calc01);
		// Calculo 03
		ret = cemporc.divide(calc02, 4, RoundingMode.HALF_EVEN);
		return ret;
	}
}
