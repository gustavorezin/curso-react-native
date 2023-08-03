package com.midas.api.tenant.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.AuxDados;
import com.midas.api.tenant.entity.AuxDadosList;
import com.midas.api.tenant.entity.CdCaixa;
import com.midas.api.tenant.entity.CdCartao;
import com.midas.api.tenant.entity.CdPlconMicro;
import com.midas.api.tenant.entity.FnCartao;
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.repository.CdCaixaRepository;
import com.midas.api.tenant.repository.CdCartaoRepository;
import com.midas.api.tenant.repository.CdPlconMicroRepository;
import com.midas.api.tenant.repository.FnCxmvDreRepository;
import com.midas.api.tenant.repository.FnCxmvRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.util.NumUtil;

@Service
public class FnCxmvPDVService {
	@Autowired
	private FnCxmvRepository fnCxmvRp;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private FnCxmvService fnCxmvService;
	@Autowired
	private FnCxmvDreRepository fnCxmvDreRp;
	@Autowired
	private FnTituloService fnTituloService;
	@Autowired
	private CdCartaoRepository cdCartaoRp;
	@Autowired
	private CdCaixaRepository cdCaixaRp;
	@Autowired
	private FnDreService fnDreService;
	@Autowired
	private FnCartaoService fnCartaoService;
	@Autowired
	private CdPlconMicroRepository cdPlconMicroRp;
	@Autowired
	private FnCcustoService fnCcustoService;
	/*
	 * 
	 * LEGENDA - AuxDados
	 * 
	 * Campo1 = Forma pagamento / Campo2 = Valor
	 * 
	 * LEGENDA - AuxDadosList
	 * 
	 * Campo1 = Parcelas / Campo2 = Valor / Campo3 = Cartao / Campo4 = Caixa
	 * 
	 * Crediario = sobra do pagamento
	 * 
	 */

	// PROCESSA RECEBIMENTO PDV-CAIXA***************************************
	public void efetuaRecebimento(LcDoc doc, List<AuxDados> auxdados, BigDecimal vTroco) throws Exception {
		Integer transacao = NumUtil.geraNumAleaInteger();
		CdCaixa cxDin = null;
		BigDecimal vCred = new BigDecimal(0);
		// Verifica sobra para crediario---------
		for (AuxDados au : auxdados) {
			if (au.getCampo1().equals("999")) {
				vCred = new BigDecimal(au.getAuxdadolists().get(0).getCampo2());
			}
		}
		Date dataatual = new Date();
		BigDecimal valorZero = new BigDecimal(0);
		BigDecimal vTotal = doc.getVtottrib().setScale(2, RoundingMode.HALF_UP);
		BigDecimal vPago = doc.getVtottrib().subtract(vCred).setScale(2, RoundingMode.HALF_UP);
		BigDecimal vSaldo = vTotal.subtract(vPago);
		String ref = "VENDA BALCAO " + doc.getNumero() + " (ID" + doc.getId() + ")";
		// Titulo principal-----------------------
		FnTitulo titulo = doc.getLcdoctitulo().get(0);
		titulo.setDatabaixa(new Date());
		titulo.setRef(ref);
		titulo.setVtot(vTotal);
		titulo.setVpago(vPago);
		titulo.setVsaldo(vSaldo);
		titulo.setParacob("S");
		fnTituloRp.save(titulo);
		// Efetuando recebimentos--------------------------------------
		for (AuxDados au : auxdados) {
			// DINHEIRO-----------------------------------------------
			if (au.getCampo1().equals("01")) {
				// Pagamento unico
				BigDecimal vpag = new BigDecimal(au.getAuxdadolists().get(0).getCampo2());
				Integer cxint = Integer.valueOf(au.getAuxdadolists().get(0).getCampo4());
				// Verifica se o troco vem de dinheiro
				if (vpag.compareTo(vTroco) > 0) {
					vpag = vpag.subtract(vTroco);
				}
				cxDin = cdCaixaRp.findById(cxint).get();
				if (vpag.compareTo(BigDecimal.ZERO) > 0) {
					// Grava movimentos
					FnCxmv fnCxmv = fnCxmvService.regUnico(doc.getCdpessoaemp(), cxDin, titulo, "01", dataatual,
							new Time(new Date().getTime()), "", vpag, valorZero, valorZero, valorZero, valorZero, vpag,
							transacao, "ATIVO", null, null);
					fnTituloService.rateioPlConFncxmv(doc, fnCxmv);
				}
			}
			// CREDITO OU DEBITO-------------------------------------------
			if (au.getCampo1().equals("03") || au.getCampo1().equals("04")) {
				String fpag = au.getCampo1();
				for (AuxDadosList al : au.getAuxdadolists()) {
					if (al.getCampo3() != null) {
						CdCartao cc = cdCartaoRp.findById(Integer.valueOf(al.getCampo3())).get();
						BigDecimal vpag = new BigDecimal(al.getCampo2());
						CdCaixa cx = cc.getCdcaixa();
						int qtdparc = Integer.valueOf(al.getCampo1());
						if (vpag.compareTo(BigDecimal.ZERO) > 0) {
							// Grava movimentos e Servicos de cartao
							int x = 0;
							while (x < qtdparc) {
								FnCartao cf = new FnCartao();
								cf.setCdcartao(cc);
								cf.setVsub(vpag);
								cf.setVdesc(valorZero);
								cf.setVjuro(valorZero);
								cf.setVtot(vpag);
								FnCxmv fnCxmv = fnCxmvService.regUnico(doc.getCdpessoaemp(), cx, titulo, fpag,
										dataatual, new Time(new Date().getTime()), "", vpag, valorZero, valorZero,
										valorZero, valorZero, vpag, null, "ATIVO", null, null);
								fnTituloService.rateioPlConFncxmv(doc, fnCxmv);
								fnCartaoService.regCalculoParcela(fnCxmv, cf, dataatual, qtdparc, x);
								fnCxmvDreRp.removeItensPlanoContas(fnCxmv.getId());
								fnCxmvRp.delete(fnCxmv);
								x++;
							}
						}
					}
				}
			}
			// PIX----------------------------------------------------
			if (au.getCampo1().equals("17")) {
				// Pagamento unico
				BigDecimal vpag = new BigDecimal(au.getAuxdadolists().get(0).getCampo2());
				Integer cxint = Integer.valueOf(au.getAuxdadolists().get(0).getCampo4());
				if (vpag.compareTo(BigDecimal.ZERO) > 0) {
					// Grava movimentos
					CdCaixa cx = cdCaixaRp.findById(cxint).get();
					FnCxmv fnCxmv = fnCxmvService.regUnico(doc.getCdpessoaemp(), cx, titulo, "17", dataatual,
							new Time(new Date().getTime()), "", vpag, valorZero, valorZero, valorZero, valorZero, vpag,
							null, "ATIVO", null, null);
					fnTituloService.rateioPlConFncxmv(doc, fnCxmv);
				}
			}
		}
		// REGISTRA TROCO---------------------------------------------
		if (vTroco.compareTo(BigDecimal.ZERO) > 0) {
			Integer idplanos = 0;
			CdPlconMicro pms = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("04", titulo.getCdpessoaemp().getId());// Saida
			if (pms != null) {
				idplanos = pms.getId();
			}
			String refsai = "OUTRAS SAIDAS - TROCO TIT. ORIG. " + titulo.getId();
			// Saida do troco-------------
			FnTitulo titulos2 = fnTituloService.regUnicoTitulo(titulo.getCdpessoaemp(), "P", titulo.getCdpessoaemp(),
					cxDin, "01", refsai, dataatual, dataatual, dataatual, dataatual, "00", 0L, 0L, "", 0L, 0, 1, 1,
					vTroco, valorZero, valorZero, valorZero, valorZero, vTroco, valorZero, vTroco, valorZero, valorZero,
					"", "N", "N", "S", valorZero, "N", "N", 0L, valorZero, valorZero, "S");
			fnDreService.dreFnTitulo100(titulos2, idplanos);
			// Centro de custos
			fnCcustoService.ccFnTitulo100(titulos2, doc.getCdccusto_id());
			FnCxmv fns = fnCxmvService.regUnico(titulo.getCdpessoaemp(), cxDin, titulos2, "01", dataatual,
					new Time(new Date().getTime()), "", vTroco, valorZero, vTroco, valorZero, valorZero, valorZero,
					transacao, "ATIVO", null, null);
			fnTituloService.rateioPlConFncxmv(doc, fns);
		}
	}
}
