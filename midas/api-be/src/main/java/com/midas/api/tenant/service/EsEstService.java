package com.midas.api.tenant.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.CdProdutoComp;
import com.midas.api.tenant.entity.EsEst;
import com.midas.api.tenant.entity.EsEstExt;
import com.midas.api.tenant.entity.EsEstMov;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfeItem;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcDocItem;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdProdutoCompRepository;
import com.midas.api.tenant.repository.CdProdutoRepository;
import com.midas.api.tenant.repository.EsEstMovRepository;
import com.midas.api.tenant.repository.EsEstRepository;

@Service
public class EsEstService {
	@Autowired
	private EsEstRepository esEstRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdProdutoRepository cdProdutoRp;
	@Autowired
	private EsEstMovRepository esEstMovRp;
	@Autowired
	private CdProdutoCompRepository cdProdutoCompRp;

	// GERADOR ESTOQUE
	// INICIAL**********************************************************************
	public void estInicia(CdProduto pr, CdProduto cdProdutoNovo) throws SQLException {
		// Verifica se usuario adicionou manualmente
		if (cdProdutoNovo.getEsestitem().size() > 0) {
			for (EsEst e : cdProdutoNovo.getEsestitem()) {
				e.setCdproduto(pr);
				esEstRp.save(e);
			}
		}
		// Se nao, cria a partir das empresas
		List<CdPessoa> emps = cdPessoaRp.findAllByTipoTodos("EMPRESA");
		for (CdPessoa p : emps) {
			if (esEstRp.findByEmpProdutoQtd(p.getId(), pr.getId()) == 0) {
				EsEst e = new EsEst();
				e.setCdpessoaemp(p);
				e.setCdproduto(pr);
				e.setQtd(new BigDecimal(0));
				e.setQtdmin(new BigDecimal(0));
				e.setVcusto(new BigDecimal(0));
				esEstRp.save(e);
			}
		}
	}

	// CONTROLADOR DE ESTOQUE UNICO*************************************************
	public void entSaiEst(String tipo, BigDecimal qtd, CdProduto pr, Long idlocalemp, String itemStatus,
			String reservaest, String canceladoc) throws SQLException {
		estInicia(pr, pr);
		// Verifica reserva de estoque-------
		if (reservaest.equals("N")) {
			// SAIDA
			if (tipo.equals("S")) {
				if (itemStatus.equals("2")) {
					esEstRp.saidaEstQtd(qtd, pr.getId(), idlocalemp);
				}
			}
			// ENTRADA
			if (tipo.equals("E")) {
				if (itemStatus.equals("2")) {
					esEstRp.entradaEstQtd(qtd, pr.getId(), idlocalemp);
				}
			}
		} else {
			// SAIDA
			if (tipo.equals("S")) {
				if (!itemStatus.equals("2")) {
					esEstRp.saidaEstQtd(qtd, pr.getId(), idlocalemp);
				}
			}
			// ENTRADA
			if (tipo.equals("E")) {
				if (canceladoc.equals("S") || !itemStatus.equals("2")) {
					esEstRp.entradaEstQtd(qtd, pr.getId(), idlocalemp);
				}
			}
		}
	}
	// CONTROLADOR DE ESTOQUE UNICO*************************************************

	// GRAVA MOVIMENTACAO***********************************************************
	private void geraMov(CdPessoa emp, CdPessoa para, CdProduto pr, String tpdoc, String lctpdoc, Long iddoc,
			Long iddocitem, Long numdoc, String tipo, BigDecimal qtdini, BigDecimal qtd, BigDecimal qtdfim,
			BigDecimal vuni, BigDecimal vsub, BigDecimal vfrete, BigDecimal vseg, BigDecimal vicms, BigDecimal vicmsst,
			BigDecimal vfcpst, BigDecimal vipi, BigDecimal vpis, BigDecimal vcofins, BigDecimal voutro, BigDecimal vtot,
			Integer cdmaqequip_id, String info) {
		EsEstMov e = new EsEstMov();
		e.setCdpessoaemp(emp);
		e.setCdpessoapara(para);
		e.setCdproduto(pr);
		e.setTpdoc(tpdoc);
		e.setLctpdoc(lctpdoc);
		e.setIddoc(iddoc);
		e.setIddocitem(iddocitem);
		e.setNumdoc(numdoc);
		e.setTipo(tipo);
		e.setQtdini(qtdini);
		e.setQtd(qtd);
		e.setQtdfim(qtdfim);
		e.setVuni(vuni);
		e.setVsub(vsub);
		e.setVfrete(vfrete);
		e.setVseg(vseg);
		e.setVicms(vicms);
		e.setVicmsst(vicmsst);
		e.setVfcpst(vfcpst);
		e.setVipi(vipi);
		e.setVpis(vpis);
		e.setVcofins(vcofins);
		e.setVoutro(voutro);
		e.setVtot(vtot);
		e.setCdmaqequip_id(cdmaqequip_id);
		e.setInfo(info);
		esEstMovRp.save(e);
	}
	// GRAVA MOVIMENTACAO***********************************************************

	// ENTRADAS E SAIDAS DOCUMENTOS*************************************************
	public void geradorLcDoc(LcDoc doc, String tipo, String finalizadoc, String canceladoc) throws SQLException {
		for (LcDocItem item : doc.getLcdocitem()) {
			BigDecimal qtdini = new BigDecimal(0);
			BigDecimal qtdfim = new BigDecimal(0);
			EsEst e = esEstRp.findByEmpProduto(doc.getCdpessoaemp().getId(), item.getCdproduto().getId());
			if (e != null) {
				qtdini = e.getQtd();
			}
			if (tipo.equals("S")) {
				qtdfim = qtdini.subtract(item.getQtd());
			} else {
				qtdfim = qtdini.add(item.getQtd());
			}
			// Gera movimentacao---------------------
			if (finalizadoc.equals("S")) {
				geraMov(doc.getCdpessoaemp(), doc.getCdpessoapara(), item.getCdproduto(), "04", doc.getTipo(),
						doc.getId(), item.getId(), doc.getNumero(), tipo, qtdini, item.getQtd(), qtdfim, item.getVuni(),
						item.getVsub(), item.getVfrete(), new BigDecimal(0), item.getVicms(), item.getVicmsst(),
						item.getVfcpst(), item.getVipi(), item.getVpis(), item.getVcofins(), new BigDecimal(0),
						item.getVtottrib(), 0, "");
			}
			entSaiEst(tipo, item.getQtd(), item.getCdproduto(), doc.getCdpessoaemp().getId(), doc.getStatus(),
					doc.getReservaest(), canceladoc);
		}
	}
	// ENTRADAS E SAIDAS DOCUMENTOS*************************************************

	// ENTRADA E SAIDA DOCUMENTO****************************************************
	public void geradorLcDocUnico(LcDoc doc, LcDocItem item, String tipo) throws SQLException {
		// Usado quando ha reserva de estoque----------
		BigDecimal qtdini = new BigDecimal(0);
		BigDecimal qtdfim = new BigDecimal(0);
		EsEst e = esEstRp.findByEmpProduto(doc.getCdpessoaemp().getId(), item.getCdproduto().getId());
		if (e != null) {
			qtdini = e.getQtd();
		}
		if (tipo.equals("S")) {
			qtdfim = qtdini.subtract(item.getQtd());
		} else {
			qtdfim = qtdini.add(item.getQtd());
		}
		// Gera movimentacao---------------------
		if (tipo.equals("S")) {
			geraMov(doc.getCdpessoaemp(), doc.getCdpessoapara(), item.getCdproduto(), "04", doc.getTipo(), doc.getId(),
					item.getId(), doc.getNumero(), tipo, qtdini, item.getQtd(), qtdfim, item.getVuni(), item.getVsub(),
					item.getVfrete(), new BigDecimal(0), item.getVicms(), item.getVicmsst(), item.getVfcpst(),
					item.getVipi(), item.getVpis(), item.getVcofins(), new BigDecimal(0), item.getVtottrib(), 0, "");
		} else {
			// Remove movimento similar----------
			esEstMovRp.removeItemQtdProd("04", doc.getId(), doc.getNumero(), item.getQtd(),
					item.getCdproduto().getId());
		}
		entSaiEst(tipo, item.getQtd(), item.getCdproduto(), doc.getCdpessoaemp().getId(), doc.getStatus(),
				doc.getReservaest(), "N");
	}
	// ENTRADAS E SAIDAS DOCUMENTOS*************************************************

	// ENTRADA E SAIDA MANUAIS******************************************************
	public void geradorAjusteEstoque(EsEst e, BigDecimal qtd, BigDecimal vUni, BigDecimal vSub, BigDecimal vTot,
			String tipo, String tpdoc, Integer cdmaqequip, String info) throws SQLException {
		// Usado quando ha reserva de estoque----------
		BigDecimal qtdini = new BigDecimal(0);
		BigDecimal qtdfim = new BigDecimal(0);
		if (e != null) {
			qtdini = e.getQtd();
		}
		if (tipo.equals("S")) {
			qtdfim = qtdini.subtract(qtd);
		} else {
			qtdfim = qtdini.add(qtd);
		}
		BigDecimal zero = new BigDecimal(0);
		// Gera movimentacao
		geraMov(e.getCdpessoaemp(), e.getCdpessoaemp(), e.getCdproduto(), tpdoc, "00", 0L, 0L, 0L, tipo, qtdini, qtd,
				qtdfim, vUni, vSub, zero, zero, zero, zero, zero, zero, zero, zero, zero, vTot, cdmaqequip, info);
		entSaiEst(tipo, qtd, e.getCdproduto(), e.getCdpessoaemp().getId(), "2", "N", "N");
	}
	// ENTRADAS E SAIDAS MANUAIS****************************************************

	// AJUSTE DE ESTOQUE PRODUTOS COM
	// REPRESENTANTE************************************************
	public void ajustaEstoqueExterno(BigDecimal qtdEntrada, EsEstExt lcEstExt, String tipo) throws SQLException {
		CdProduto produto = lcEstExt.getCdproduto();
		CdPessoa vendedor = cdPessoaRp.findById(lcEstExt.getCdpessoavendedor().getId()).get();
		Long idemp = vendedor.getEmp();
		BigDecimal qtdSaida = lcEstExt.getQtd();
		if (tipo.equals("ATUALIZA")) {
			esEstRp.entradaEstQtd(qtdEntrada, produto.getId(), idemp);
			esEstRp.saidaEstQtd(qtdSaida, produto.getId(), idemp);
		} else if (tipo.equals("SAIDA")) {
			esEstRp.saidaEstQtd(qtdSaida, produto.getId(), idemp);
		} else {
			esEstRp.entradaEstQtd(qtdEntrada, produto.getId(), idemp);
		}
	}
	// AJUSTE DE ESTOQUE PRODUTOS COM
	// REPRESENTANTE************************************************

	// ENTRADAS-SAIDAS E NFE ENTRADA************************************************
	public void geradorFsNfe(FsNfe nfe, String tipo) throws SQLException {
		// ENTRADA
		if (tipo.equals("E")) {
			CdPessoa para = cdPessoaRp.findFirstByCpfcnpj(nfe.getCpfcnpjemit());
			for (FsNfeItem i : nfe.getFsnfeitems()) {
				CdProduto pr = cdProdutoRp.findByIdCodigoUnico(i.getIdprod());
				EsEst e = esEstRp.findByEmpProduto(nfe.getCdpessoaemp().getId(), pr.getId());
				BigDecimal qtdini = new BigDecimal(0);
				BigDecimal qtdfim = new BigDecimal(0);
				BigDecimal vtot = calcTpEmp(i);
				if (e != null) {
					qtdini = e.getQtd();
				}
				qtdfim = qtdini.add(i.getQtdconv());
				// Gera movimentacao
				geraMov(nfe.getCdpessoaemp(), para, pr, "01", "00", nfe.getId(), i.getId(), nfe.getNnf().longValue(),
						tipo, qtdini, i.getQtdconv(), qtdfim, i.getVuncom(), i.getVprod(), i.getVfrete(), i.getVseg(),
						i.getFsnfeitemicms().getVicms(), i.getFsnfeitemicms().getVicmsst(),
						i.getFsnfeitemicms().getVfcpst(), i.getFsnfeitemipi().getVipi(), i.getFsnfeitempis().getVpis(),
						i.getFsnfeitemcofins().getVcofins(), i.getVoutro(), vtot, 0, "");
				entSaiEst(tipo, i.getQtdconv(), pr, nfe.getCdpessoaemp().getId(), "2", "N", "N");
			}
		}
		// SAIDA
		if (tipo.equals("S")) {
			for (FsNfeItem i : nfe.getFsnfeitems()) {
				if (i.getIdprod() != null) {
					CdProduto pr = cdProdutoRp.findByIdCodigoUnico(i.getIdprod());
					if (pr != null) {
						EsEstMov e = esEstMovRp.getItem("01", nfe.getId(), i.getId(), nfe.getNnf().longValue(), "E",
								pr.getId());
						if (e != null) {
							entSaiEst(tipo, i.getQtdconv(), pr, nfe.getCdpessoaemp().getId(), "", "N", "N");
						}
					}
				}
			}
		}
	}
	// ENTRADAS-SAIDAS E NFE ENTRADA************************************************

	// CONTROLADOR MATERIA PRIMA PRODUCAO*******************************************
	public void movMatProdPrima(EsEstMov esEstMov) throws SQLException {
		// Materia-prima
		List<CdProdutoComp> comps = cdProdutoCompRp.findByProduto(esEstMov.getCdproduto().getId());
		for (CdProdutoComp comp : comps) {
			// Apenas produtos, sem servicos
			if (comp.getCdprodutocomp().getTipo().equals("PRODUTO")) {
				// Apenas da empresa selecionada
				if (esEstMov.getCdpessoaemp().getId().equals(comp.getCdpessoaemp().getId())) {
					// Movimenta conforme operacao - Entrada ou saida, faz o contrario
					String tipo = "S";
					if (esEstMov.getTipo().equals("S")) {
						tipo = "E";
					}
					BigDecimal qtdMov = comp.getQtd().multiply(esEstMov.getQtd()).setScale(8, RoundingMode.HALF_UP);
					EsEst e = esEstRp.findByEmpProduto(comp.getCdpessoaemp().getId(), comp.getCdprodutocomp().getId());
					BigDecimal vTot = comp.getVcusto().multiply(qtdMov).setScale(6, RoundingMode.HALF_UP);
					geradorAjusteEstoque(e, qtdMov, comp.getVcusto(), vTot, vTot, tipo, "06",
							esEstMov.getCdmaqequip_id(), esEstMov.getInfo());
				}
			}
		}
	}
	// CONTROLADOR MATERIA PRIMA PRODUCAO*******************************************

	// CALC TIPO DE EMPRESA ENTRADA*************************************************
	private BigDecimal calcTpEmp(FsNfeItem i) {
		BigDecimal retorno = new BigDecimal(0);
		// SIMPLES NACIONAL---------------------------------
		if (i.getFsnfe().getCdpessoaemp().getCrt() == 1) {
			retorno = i.getVprod().add(i.getVfrete().add(i.getVseg()).add(i.getFsnfeitemicms().getVicmsst())
					.add(i.getFsnfeitemipi().getVipi()).add(i.getVoutro()));
		}
		// LUCRO PRESUMIDO---------------------------------
		if (i.getFsnfe().getCdpessoaemp().getCrt() == 2) {
			retorno = i.getVprod().add(i.getVfrete().add(i.getVseg()).add(i.getVoutro()));
		}
		// LUCRO REAL---------------------------------
		if (i.getFsnfe().getCdpessoaemp().getCrt() == 3) {
			retorno = i.getVprod().add(i.getVfrete().add(i.getVseg()).add(i.getVoutro()))
					.subtract(i.getFsnfeitemcofins().getVcofins()).subtract(i.getFsnfeitempis().getVpis());
		}
		return retorno;
	}
	// CALC TIPO DE EMPRESA ENTRADA*************************************************
}
