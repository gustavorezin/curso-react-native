package com.midas.api.tenant.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdCaixa;
import com.midas.api.tenant.entity.CdCondPagDia;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdPlconMicro;
import com.midas.api.tenant.entity.CdProdutoDre;
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcDocItem;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdPlconMicroRepository;
import com.midas.api.tenant.repository.FnTituloCcustoRepository;
import com.midas.api.tenant.repository.FnTituloDreRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.repository.LcDocItemRepository;
import com.midas.api.tenant.repository.LcDocRepository;
import com.midas.api.util.DataUtil;
import com.midas.api.util.LcDocTipoNomeUtil;
import com.midas.api.util.NumUtil;

@Service
public class FnTituloService {
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private FnDreService fnDreService;
	@Autowired
	private FnCcustoService fnCcustoService;
	@Autowired
	private FnTituloDreRepository fnTituloDreRp;
	@Autowired
	private FnTituloCcustoRepository fnTituloCcustoRp;
	@Autowired
	private CdPlconMicroRepository cdPlconMicroRp;
	@Autowired
	private LcDocItemRepository lcDocItemRp;
	@Autowired
	private FnCxmvService fnCxmvService;
	@Autowired
	private LcDocRepository lcDocRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	// TODO configurar juros por dia de atraso na hora de pagar

	// Registro de titulos apenas neste local
	// *******************************************************
	public FnTitulo regUnicoTitulo(CdPessoa cdpessoaemp, String tipo, CdPessoa cdpessoapara, CdCaixa cdcaixapref,
			String fpag, String ref, Date datacad, Date dataat, Date vence, Date databaixa, String tpdoc, Long lcdoc,
			Long numdoc, String tpdocfiscal, Long docfiscal, Integer numnota, Integer qtdparc, Integer parcnum,
			BigDecimal vparc, BigDecimal vicmsst, BigDecimal vipi, BigDecimal vfcpst, BigDecimal vfrete,
			BigDecimal vdesc, BigDecimal vjuro, BigDecimal vtot, BigDecimal vpago, BigDecimal vsaldo, String info,
			String cred, String negocia, String troco, BigDecimal vcredsaldo, String cobauto, String comissao,
			Long fntitulo_com, BigDecimal vbccom, BigDecimal pcom, String paracob) {
		FnTitulo t = new FnTitulo();
		t.setCdpessoaemp(cdpessoaemp);
		t.setTipo(tipo);
		t.setCdpessoapara(cdpessoapara);
		t.setCdcaixapref(cdcaixapref);
		t.setFpagpref(fpag);
		t.setRef(ref);
		t.setDatacad(datacad);
		t.setDataat(dataat);
		t.setVence(vence);
		t.setDatabaixa(databaixa);
		t.setTpdoc(tpdoc);
		t.setLcdoc(lcdoc);
		t.setNumdoc(numdoc);
		t.setTpdocfiscal(tpdocfiscal);
		t.setDocfiscal(docfiscal);
		t.setNumnota(numnota);
		t.setQtdparc(qtdparc);
		t.setParcnum(parcnum);
		t.setVparc(vparc);
		t.setVicmsst(vicmsst);
		t.setVipi(vipi);
		t.setVfcpst(vfcpst);
		t.setVfrete(vfrete);
		t.setVdesc(vdesc);
		t.setVjuro(vjuro);
		t.setVtot(vtot);
		t.setVpago(vpago);
		t.setVsaldo(vsaldo);
		t.setInfo(info);
		t.setCred(cred);
		t.setNegocia(negocia);
		t.setTroco(troco);
		t.setVcredsaldo(vcredsaldo);
		// t.setTransacao(transacao);
		t.setCobauto(cobauto);
		t.setComissao(comissao);
		t.setFntitulo_com(fntitulo_com);
		t.setVbccom(vbccom);
		t.setPcom(pcom);
		t.setParacob(paracob);
		return fnTituloRp.save(t);
	}

	// EFETUA BAIXA NO(S) TITULO(S)
	// ****************************************************************
	public void baixaUnico(BigDecimal valorBaixa, BigDecimal valorDesc, BigDecimal valorJuro, BigDecimal valorSaldo,
			Date datapag, Integer cdx, FnTitulo fnTitulo) {
		fnTituloRp.baixaValor(valorBaixa, valorDesc, valorJuro, valorSaldo, datapag, cdx, fnTitulo.getId());
	}

	// EFETUA BAIXA NO(S) TITULO(S) DE CREDITO
	// ****************************************************************
	public void baixaCreditoUnico(List<FnCxmv> fnCxmvs, Date datapag) {
		BigDecimal valorCred = BigDecimal.ZERO;
		for (FnCxmv c : fnCxmvs) {
			if (c.getFpag().equals("05")) {
				valorCred = valorCred.add(c.getVsub());
			}
		}
		FnTitulo titulo = fnCxmvs.get(0).getFntitulo();
		List<FnTitulo> titulos = fnTituloRp.listaTitulosCreditosAbertos(titulo.getCdpessoaemp().getId(),
				titulo.getCdpessoapara().getId());
		for (FnTitulo t : titulos) {
			// SE IGUAIS
			if (t.getVcredsaldo().compareTo(valorCred) == 0 && valorCred.compareTo(new BigDecimal(0)) != 0) {
				// ATUALIZA PARA ZERO
				valorCred = BigDecimal.ZERO;
				fnTituloRp.baixaValorCredito(valorCred, datapag, t.getId());
			}
			// SE valorCred FOR MENOR
			if (t.getVcredsaldo().compareTo(valorCred) == 1 && valorCred.compareTo(new BigDecimal(0)) != 0) {
				valorCred = t.getVcredsaldo().subtract(valorCred);
				fnTituloRp.baixaValorCredito(valorCred, datapag, t.getId());
				valorCred = BigDecimal.ZERO;
			}
			// SE valorCred FOR MAIOR
			if (t.getVcredsaldo().compareTo(valorCred) == -1 && valorCred.compareTo(new BigDecimal(0)) != 0) {
				valorCred = valorCred.subtract(t.getVcredsaldo());
				fnTituloRp.baixaValorCredito(new BigDecimal(0), datapag, t.getId());
			}
		}
	}

	// EFETUA DEVOLUCAO NO(S) TITULO(S) DE CREDITO
	// ***************************************************************
	public void devolucaoCreditoUnico(FnCxmv fnCxmv) {
		BigDecimal valorCred = BigDecimal.ZERO;
		if (fnCxmv.getFpag().equals("05")) {
			valorCred = fnCxmv.getVsub();
		}
		FnTitulo titulo = fnCxmv.getFntitulo();
		while (valorCred.compareTo(new BigDecimal(0)) > 0) {
			FnTitulo t = fnTituloRp.findCredito(titulo.getCdpessoaemp().getId(), titulo.getCdpessoapara().getId());
			// Verifica nulo para outros estornos
			if (t != null) {
				BigDecimal vSaldo = t.getVpago().subtract(t.getVcredsaldo());
				// Cred igual
				if (vSaldo.compareTo(valorCred) == 0) {
					fnTituloRp.devolveValorCredito(valorCred, t.getId());
					valorCred = BigDecimal.ZERO;
				}
				// Cred menor
				if (vSaldo.compareTo(valorCred) == 1) {
					fnTituloRp.devolveValorCredito(valorCred, t.getId());
					valorCred = BigDecimal.ZERO;
				}
				// Cred maior
				if (vSaldo.compareTo(valorCred) == -1) {
					fnTituloRp.devolveValorCreditoTotal(t.getId());
					valorCred = valorCred.subtract(vSaldo);
				}
			} else {
				valorCred = BigDecimal.ZERO;
			}
		}
	}

	// GERADOR DE PARCELAS
	// *****************************************************************
	public void parcelaDocs(LcDoc lcDoc, String tipo) throws Exception {
		// Verifica se ja negociou parcela, para nao gerar novamente
		if (lcDoc.getNgparcela().equals("N")) {
			BigDecimal valorZero = BigDecimal.ZERO;
			// Remove antigos
			removeParcelaDocs(lcDoc, tipo);
			// Dados
			Date dataCad = new Date(System.currentTimeMillis());
			Date dataAtual = new Date(System.currentTimeMillis());
			Integer qtdParc = lcDoc.getCdcondpag().getQtd();
			// GERANDO VALOR DE PARCELAS
			BigDecimal vdesc = BigDecimal.ZERO;
			BigDecimal vjuro = BigDecimal.ZERO;
			BigDecimal vpago = BigDecimal.ZERO;
			BigDecimal vcred = BigDecimal.ZERO;
			BigDecimal divisor = new BigDecimal(qtdParc);
			// Separa descontos e outros valores iniciais para recalculo -----
			BigDecimal valorTot = lcDoc.getVtot();
			// Se tem deslocamento
			valorTot = valorTot.add(lcDoc.getVdesloca());
			// Se tem desconto extra
			valorTot = valorTot.subtract(lcDoc.getVdescext());
			BigDecimal vlrparcela = valorTot.divide(divisor, 2, RoundingMode.HALF_EVEN);
			BigDecimal vlrfinal = vlrparcela;
			BigDecimal vFrete = lcDoc.getVtransp();
			BigDecimal vIcmsst = lcDoc.getVicmsst();
			BigDecimal vIpi = lcDoc.getVipi();
			BigDecimal vFcpst = lcDoc.getVfcpst();
			BigDecimal primParc = vlrPrimParc(valorTot, vlrparcela, qtdParc);
			// Verifica se tem pagamento ou se cancelado---------------------
			if (!lcDoc.getFpag().equals("90") && !lcDoc.getFpag().equals("99")) {
				// Cria novas
				Integer parcnum = 1;
				for (CdCondPagDia cpd : lcDoc.getCdcondpag().getCdcondpagdiaitem()) {
					// DIAS PARCELAS ----------------------------------------
					Integer dias = cpd.getDias();
					GregorianCalendar gc = new GregorianCalendar();
					gc.setTime(dataAtual);
					gc.add(Calendar.DAY_OF_MONTH, dias);
					// Verifica se fim de semana - sabado e domingo ---------
					if (DataUtil.isSOuD(gc) == 1) {
						gc.add(Calendar.DAY_OF_MONTH, 1);
					}
					if (DataUtil.isSOuD(gc) == 7) {
						gc.add(Calendar.DAY_OF_MONTH, 2);
					}
					Date vence = gc.getTime();
					// Verifica dia definido pelo vencimento
					if (lcDoc.getDiavencefixo() > 0) {
						gc.set(Calendar.DAY_OF_MONTH, lcDoc.getDiavencefixo());
						vence = gc.getTime();
					}
					// Emissao igual ao vencimento ---------------------------
					if (lcDoc.getLcmesmomes().equals("S")) {
						dataCad = vence;
					}
					String ref = LcDocTipoNomeUtil.nomeTipoDoc(lcDoc.getTipo()) + " número " + lcDoc.getNumero()
							+ " (ID " + lcDoc.getId() + ")";
					// Ajuste de parcela se tiver sobra e impostos -----------
					if (parcnum == 1) {
						vlrparcela = primParc;
						vlrfinal = primParc.add(vIcmsst).add(vIpi).add(vFcpst).add(vFrete);
					} else {
						vlrparcela = valorTot.divide(divisor, 2, RoundingMode.HALF_EVEN);
						vlrfinal = vlrparcela;
						vIcmsst = BigDecimal.ZERO;
						vIpi = BigDecimal.ZERO;
						vFcpst = BigDecimal.ZERO;
						vFrete = BigDecimal.ZERO;
					}
					regUnicoTitulo(lcDoc.getCdpessoaemp(), tipo, lcDoc.getCdpessoapara(), lcDoc.getCdcaixa(),
							lcDoc.getFpag(), ref, dataCad, dataAtual, vence, vence, lcDoc.getTipo(), lcDoc.getId(),
							lcDoc.getNumero(), "", 0L, 0, qtdParc, parcnum, vlrparcela, vIcmsst, vIpi, vFcpst, vFrete,
							vdesc, vjuro, vlrfinal, vpago, vlrfinal, "", "N", "N", "N", vcred, lcDoc.getCobauto(), "N",
							0L, valorZero, valorZero, "N");
					parcnum++;
				}
			}
		}
	}

	// REMOVER DE PARCELAS ANTIGAS
	// ***************************************************************
	public void removeParcelaDocs(LcDoc lcDoc, String tipo) throws Exception {
		for (FnTitulo f : lcDoc.getLcdoctitulo()) {
			fnTituloDreRp.removeItensPlanoContas(f.getId());
			fnTituloCcustoRp.removeItensCcusto(f.getId());
		}
		fnTituloRp.removeParcelasTipo(lcDoc.getId(), tipo);
	}

	// ATUALIZA DE PARCELAS ANTIGAS E REMOVE COMISSAO
	// ***************************************************************
	public void atualizaParcelaDocs(LcDoc lcDoc) throws Exception {
		// Verifica se Negociou parcela--------------------------
		if (lcDoc.getNgparcela().equals("S")) {
			fnTituloRp.attParaCob("N", lcDoc.getId());
		}
		// Remove plano de contas para adicionar ao Finalizar----
		for (FnTitulo f : lcDoc.getLcdoctitulo()) {
			if (f.getTipo().equals("R")) {
				fnTituloDreRp.removeItensPlanoContas(f.getId());
				fnTituloCcustoRp.removeItensCcusto(f.getId());
			}
		}
		// Remove comissoes--------------------------------------
		List<FnTitulo> lista = fnTituloRp.listaTitulosDocTipo(lcDoc.getId(), "P");
		for (FnTitulo f : lista) {
			fnTituloDreRp.removeItensPlanoContas(f.getId());
			fnTituloCcustoRp.removeItensCcusto(f.getId());
		}
		fnTituloRp.removeParcelasTipo(lcDoc.getId(), "P");
	}

	// GERADOR DE PARCELAS COMISSAO DIRETA TOTAL
	// *****************************************************************
	public void parcelaDocsComissao(LcDoc lcDoc, Long vend, BigDecimal pcom) throws Exception {
		// Titulo para vendedor -------------------------
		Integer idplanocom = 0;
		CdPlconMicro pmc = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("09", lcDoc.getCdpessoaemp().getId());// Comissao
		if (pmc != null) {
			idplanocom = pmc.getId();
		}
		String nomedoc = LcDocTipoNomeUtil.nomeTipoDoc(lcDoc.getTipo());
		String ref = "COMISSÃO DE VENDAS/OUTROS | " + nomedoc + " " + lcDoc.getNumero() + " (ID " + lcDoc.getId() + ")";
		Integer qtdParc = lcDoc.getLcdoctitulo().size();
		Integer parcnum = 1;
		BigDecimal valorZero = BigDecimal.ZERO;
		BigDecimal pCom = lcDoc.getPcom();
		if (pcom.compareTo(BigDecimal.ZERO) > 0) {
			pCom = pcom;
		}
		CdPessoa para = lcDoc.getCdpessoavendedor();
		if (vend > 0) {
			para = cdPessoaRp.findById(vend).get();
		}
		// Conforme parcelamento
		for (FnTitulo t : lcDoc.getLcdoctitulo()) {
			if (t.getTipo().equals("R")) {
				BigDecimal vlrparcela = t.getVparc().multiply(pCom).divide(new BigDecimal(100), 2,
						RoundingMode.HALF_EVEN);
				FnTitulo titulo = regUnicoTitulo(lcDoc.getCdpessoaemp(), "P", para, lcDoc.getCdcaixa(), lcDoc.getFpag(),
						ref, lcDoc.getDatafat(), lcDoc.getDatafat(), t.getVence(), lcDoc.getDatafat(), "05",
						lcDoc.getId(), lcDoc.getNumero(), lcDoc.getTpdocfiscal(), lcDoc.getDocfiscal(),
						lcDoc.getNumnota(), qtdParc, parcnum, vlrparcela, valorZero, valorZero, valorZero, valorZero,
						valorZero, valorZero, vlrparcela, valorZero, vlrparcela, "", "N", "N", "N", new BigDecimal(0),
						"N", "S", t.getId(), t.getVparc(), pCom, "S");
				// DREs
				fnDreService.dreFnTitulo100(titulo, idplanocom);
				// Centro de custos
				fnCcustoService.ccFnTitulo100(titulo, lcDoc.getCdccusto_id());
				parcnum++;
			}
		}
	}

	// FUNCOES DE FINALIZACAO***************************************
	public void finalizaDocs(LcDoc lcDoc, String tipoPR) throws Exception {
		// Se nao gera cobranca----------------------------------
		if (lcDoc.getFpag().equals("90") || lcDoc.getFpag().equals("99")) {
			fnTituloRp.removeParcelasTipo(lcDoc.getId(), tipoPR);
		} else {
			// Poe para cobranca -----------------------------------
			fnTituloRp.attParaCob("S", lcDoc.getId());
			// Gerar Centro de custos
			for (FnTitulo f : lcDoc.getLcdoctitulo()) {
				if (f.getTipo().equals(tipoPR)) {
					fnCcustoService.ccFnTitulo100(f, lcDoc.getCdccusto_id());
				}
			}
			rateioPlCon(lcDoc, tipoPR);
			// Gerador de comissão ----------------------------------
			if (lcDoc.getVcom().compareTo(BigDecimal.ZERO) > 0) {
				parcelaDocsComissao(lcDoc, 0L, BigDecimal.ZERO);
			}
		}
	}

	// Rateio titulos
	public void rateioPlCon(LcDoc lcDoc, String tipoPR) {
		// Verifica se plano direito ou pelos itens ------------
		if (lcDoc.getCdplconmicro_id() > 0) {
			// Gerar DRE
			for (FnTitulo f : lcDoc.getLcdoctitulo()) {
				if (f.getTipo().equals(tipoPR)) {
					fnDreService.dreFnTitulo100(f, lcDoc.getCdplconmicro_id());
				}
			}
		} else {
			// Verifica quais produtos tem no lancamento -------
			List<LcDocItem> itensGroup = lcDocItemRp.listaItensGroupById(lcDoc.getId());
			List<LcDocItem> itensTodos = lcDocItemRp.listaItensOrderByItem(lcDoc.getId());
			Integer plcon = 0;
			// Pega lista simples de cada item
			for (LcDocItem di : itensGroup) {
				BigDecimal vlrTot = BigDecimal.ZERO;
				// Lista completa dos itens do lancamento ------
				for (LcDocItem dit : itensTodos) {
					for (CdProdutoDre pd : dit.getCdproduto().getCdprodutodreitem()) {
						if (pd.getCdpessoaemp().getId().equals(lcDoc.getCdpessoaemp().getId())
								&& di.getCdproduto().getId().equals(dit.getCdproduto().getId())) {
							// Operacoes
							vlrTot = vlrTot.add(dit.getVtot());
							plcon = pd.getCdplconmicro_id();
						}
					}
				}
				// Calculo percentual ---------------------------
				BigDecimal perc = NumUtil.paraPerc8Decimais(lcDoc.getVtot(), vlrTot);
				// GRAVA PLANO SEPARADO -------------------------
				for (FnTitulo f : lcDoc.getLcdoctitulo()) {
					if (f.getTipo().equals(tipoPR)) {
						fnDreService.dreFnTituloPerc(f, perc, plcon);
					}
				}
			}
		}
	}

	// Rateio titulos no caixa
	public void rateioPlConFncxmv(LcDoc lcDoc, FnCxmv fnCxmv) {
		// Verifica se plano direito ou pelos itens ------------
		if (lcDoc.getCdplconmicro_id() > 0) {
			// Gerar DRE
			for (FnTitulo f : lcDoc.getLcdoctitulo()) {
				if (f.getTipo().equals("R")) {
					fnDreService.dreFnCxmv100(fnCxmv, lcDoc.getCdplconmicro_id());
				}
			}
		} else {
			// Verifica quais produtos tem no lancamento -------
			List<LcDocItem> itensGroup = lcDocItemRp.listaItensGroupById(lcDoc.getId());
			List<LcDocItem> itensTodos = lcDocItemRp.listaItensOrderByItem(lcDoc.getId());
			Integer plcon = 0;
			// Pega lista simples de cada item
			for (LcDocItem di : itensGroup) {
				BigDecimal vlrTot = BigDecimal.ZERO;
				// Lista completa dos itens do lancamento ------
				for (LcDocItem dit : itensTodos) {
					for (CdProdutoDre pd : dit.getCdproduto().getCdprodutodreitem()) {
						if (pd.getCdpessoaemp().getId().equals(lcDoc.getCdpessoaemp().getId())
								&& di.getCdproduto().getId().equals(dit.getCdproduto().getId())) {
							// Operacoes
							vlrTot = vlrTot.add(dit.getVtot());
							plcon = pd.getCdplconmicro_id();
						}
					}
				}
				// Calculo percentual ---------------------------
				BigDecimal perc = NumUtil.paraPerc8Decimais(lcDoc.getVtot(), vlrTot);
				// GRAVA PLANO SEPARADO -------------------------
				for (FnTitulo f : lcDoc.getLcdoctitulo()) {
					if (f.getTipo().equals("R")) {
						fnDreService.dreFnCxmvPerc(fnCxmv, perc, plcon);
					}
				}
			}
		}
	}

	// FUNCOES DE FINALIZACAO DEVOLUCA********************************
	public void finalizaDocsDevolve(LcDoc lcDoc, String geraCred, String devVendaouCompra) throws Exception {
		if (geraCred.equals("S")) {
			String tipo = "R";
			if (devVendaouCompra.equals("C")) {
				tipo = "P";
			}
			// Conversoes basicas
			BigDecimal valorZero = BigDecimal.ZERO;
			BigDecimal vDesc = lcDoc.getVdesc().setScale(2, RoundingMode.HALF_UP);
			BigDecimal vSub = lcDoc.getVsub().setScale(2, RoundingMode.HALF_UP);
			BigDecimal vIcmsst = lcDoc.getVicmsst().setScale(2, RoundingMode.HALF_UP);
			BigDecimal vIpi = lcDoc.getVipi().setScale(2, RoundingMode.HALF_UP);
			BigDecimal vFcpst = lcDoc.getVfcpst().setScale(2, RoundingMode.HALF_UP);
			BigDecimal vTransp = lcDoc.getVtransp().setScale(2, RoundingMode.HALF_UP);
			BigDecimal vTottrib = lcDoc.getVtottrib().setScale(2, RoundingMode.HALF_UP);
			String ref = "CREDITO DE DEVOLUCAO | DEV. " + lcDoc.getNumero() + " (ID" + lcDoc.getId() + " )";
			if (lcDoc.getTipo().equals("04")) {
				ref = "CREDITO DE DEVOLUCAO COMPRA | DEV. " + lcDoc.getNumero() + " (ID" + lcDoc.getId() + " )";
			}
			FnTitulo titulo = regUnicoTitulo(lcDoc.getCdpessoaemp(), tipo, lcDoc.getCdpessoapara(), lcDoc.getCdcaixa(),
					"01", ref, lcDoc.getDatafat(), lcDoc.getDatafat(), lcDoc.getDatafat(), lcDoc.getDatafat(),
					lcDoc.getTipo(), lcDoc.getId(), lcDoc.getNumero(), "", 0L, 0, 1, 1, vSub, vIcmsst, vIpi, vFcpst,
					vTransp, vDesc, valorZero, vTottrib, vTottrib, valorZero, lcDoc.getInfo(), "S", "N", "N", vTottrib,
					"N", "N", 0L, valorZero, valorZero, "S");
			fnDreService.dreFnTitulo100(titulo, lcDoc.getCdplconmicro_id());
		}
	}

	// FUNCOES DE COBRANCA AUTOMATICA*********************************
	public void cobAuto() throws Exception {
		List<FnTitulo> ts = fnTituloRp.listaTitulosVencidosCobAuto("R");
		for (FnTitulo t : ts) {
			BigDecimal zero = BigDecimal.ZERO;
			baixaUnico(t.getVsaldo(), zero, zero, t.getVsaldo(), t.getVence(), t.getCdcaixapref().getId(), t);
			Time hora = new Time(new java.util.Date().getTime());
			Integer transacao = NumUtil.geraNumAleaInteger();
			FnCxmv fnCxmv = fnCxmvService.regUnico(t.getCdpessoaemp(), t.getCdcaixapref(), t, t.getFpagpref(),
					t.getDatabaixa(), hora, t.getInfo(), t.getVparc(), zero, zero, zero, zero, t.getVsaldo(), transacao,
					"ATIVO", null, null);
			// SALVA PLANO DE CONTAS DRE -------------------------
			fnDreService.dreTituloLote(t, fnCxmv);
		}
	}

	// AJUSTE DE PARCELA QUEBRADA
	// ****************************************************************
	public BigDecimal vlrPrimParc(BigDecimal vlrOriginal, BigDecimal vlrparcela, Integer qtdParc) {
		BigDecimal ret = vlrparcela;
		BigDecimal fator = new BigDecimal(qtdParc);
		BigDecimal conta = vlrparcela.multiply(fator);
		if (vlrOriginal.compareTo(conta) == 1) {
			BigDecimal diferenca = vlrOriginal.subtract(conta);
			ret = ret.add(diferenca);
		} else {
			BigDecimal diferenca = vlrOriginal.subtract(conta);
			ret = ret.add(diferenca);
		}
		return ret.setScale(2, RoundingMode.HALF_UP);
	}

	// AJUSTE DE COMISSAO DE VENDAS
	public void atualizaComDocs(LcDoc doc, Long id, Long vend, BigDecimal pcom) throws Exception {
		// Calculo comissao venda---------------------------
		BigDecimal vBCcom = doc.getVsub().subtract(doc.getVdesc()).subtract(doc.getVdescext());
		BigDecimal vCom = vBCcom.multiply(pcom).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
		lcDocRp.updateDocComissaoVend(pcom, vCom, vend, id);
		// Remove antigos
		for (FnTitulo t : doc.getLcdoctitulo()) {
			if (t.getTipo().equals("P") && t.getComissao().equals("S")) {
				fnTituloRp.delete(t);
			}
		}
		// Se com comissao, atualiza titulos--------------------
		if (pcom.compareTo(BigDecimal.ZERO) > 0) {
			if (doc.getLcdoctitulo().size() > 0) {
				parcelaDocsComissao(doc, vend, pcom);
			}
		}
	}
}
