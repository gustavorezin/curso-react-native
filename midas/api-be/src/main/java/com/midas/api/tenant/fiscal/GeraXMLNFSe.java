package com.midas.api.tenant.fiscal;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Component;

import com.midas.api.constant.FsNfseWebService;
import com.midas.api.constant.MidasConfig;
import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.FsNfse;
import com.midas.api.tenant.entity.FsNfseCobr;
import com.midas.api.tenant.entity.FsNfseCobrDup;
import com.midas.api.tenant.entity.FsNfseItem;
import com.midas.api.tenant.entity.FsNfseItemTrib;
import com.midas.api.tenant.entity.FsNfsePart;
import com.midas.api.tenant.entity.FsNfseTot;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.util.DataUtil;

import br.inf.portalfiscal.nfse.EnderPrest;
import br.inf.portalfiscal.nfse.Envio;
import br.inf.portalfiscal.nfse.ListaItens;
import br.inf.portalfiscal.nfse.ListaItens.Item;
import br.inf.portalfiscal.nfse.ListaParcelas;
import br.inf.portalfiscal.nfse.ListaParcelas.Parcela;
import br.inf.portalfiscal.nfse.LocalPrestacao;
import br.inf.portalfiscal.nfse.Prestador;
import br.inf.portalfiscal.nfse.RPS;
import br.inf.portalfiscal.nfse.Servico;
import br.inf.portalfiscal.nfse.Tomador;
import br.inf.portalfiscal.nfse.Valores;

@Component
public class GeraXMLNFSe {
	public String geraXMLNFSe(FsNfse nfse, MidasConfig mc) throws Exception {
		// MONTANDO XML DA NFE
		Envio envio = new Envio();
		envio.setModeloDocumento("NFSe");
		envio.setVersao(BigDecimal.valueOf(Double.valueOf(FsNfseWebService.versaoDados)));
		envio.setRPS(rps(nfse));
		// CRIA XML
		String xml = FsUtil.xmlNFSe(envio);
		return xml;
	}

	// IDENTIFICACAO*******************************************************************************
	private static RPS rps(FsNfse nfse) throws Exception {
		// Datas
		String de = DataUtil.dataPadraoSQL(nfse.getDemis());
		String dhr = DataUtil.horaPadraoSQL(nfse.getDemishora());
		String demishora = DataUtil.dtTim(de, dhr);
		XMLGregorianCalendar demis = DataUtil.dTimG(demishora.substring(0, 19));
		XMLGregorianCalendar dcompetencia = DataUtil.dTimG(demishora.substring(0, 19));
		// RPS
		RPS rps = new RPS();
		rps.setRPSNumero(BigInteger.valueOf(nfse.getRpsnumero()));
		rps.setRPSSerie(nfse.getRpsserie());
		rps.setRPSTipo(BigInteger.valueOf(nfse.getRpstipo()));
		rps.setDEmis(demis);
		rps.setDCompetencia(dcompetencia);
		rps.setLocalPrestServ(Integer.valueOf(nfse.getLocalprestserv()));
		rps.setNatOp(BigInteger.valueOf(Integer.valueOf(nfse.getNatop())));
		rps.setOperacao(nfse.getOperacao());
		rps.setNumProcesso(FsUtil.cpND(nfse.getNumprocesso(), null));
		rps.setRegEspTrib(Integer.valueOf(nfse.getRegesptrib()));
		rps.setOptSN(Integer.valueOf(nfse.getOptsn()));
		rps.setIncCult(Integer.valueOf(nfse.getInccult()));
		rps.setStatus(Integer.valueOf(nfse.getStatusrps()));
		rps.setCVerificaRPS("");
		rps.setTpAmb(nfse.getTpamb());
		rps.setPrestador(prest(nfse));
		rps.setTomador(toma(nfse));
		rps.setListaItens(listaItens(nfse));
		rps.setServico(servico(nfse));
		rps.setListaParcelas(listaParcelas(nfse));
		rps.setNFSOutrasinformacoes(nfse.getOutrasinfo());
		rps.setRPSCanhoto(BigInteger.ZERO);
		return rps;
	}

	// PRESTADOR***********************************************************************************
	private static Prestador prest(FsNfse nfse) throws Exception {
		FsNfsePart prest = nfse.getFsnfsepartprest();
		Prestador p = new Prestador();
		if (prest.getCpfcnpj().length() == 14)
			p.setCNPJPrest(prest.getCpfcnpj());
		if (prest.getCpfcnpj().length() == 11)
			p.setCPFPrest(prest.getCpfcnpj());
		p.setXNome(prest.getXnome());
		p.setXFant(FsUtil.cpND(prest.getXfant(), prest.getXnome()));
		p.setIM(prest.getIm());
		p.setIE(prest.getIe());
		EnderPrest end = new EnderPrest();
		end.setTPEnd(prest.getTipoend());
		end.setXLgr(prest.getXlgr());
		end.setNro(FsUtil.cpND(prest.getNro(), "SN"));
		end.setXCpl(FsUtil.cpND(prest.getXcpl(), "-"));
		end.setXBairro(prest.getXbairro());
		end.setCMun(BigInteger.valueOf(Integer.valueOf(prest.getCmun())));
		end.setUF(prest.getUf());
		end.setCEP(BigInteger.valueOf(Long.valueOf(prest.getCep())));
		end.setFone(BigInteger.valueOf(Long.valueOf(prest.getFone())));
		end.setEmail(prest.getEmail());
		p.setEnderPrest(end);
		return p;
	}

	// TOMADOR*************************************************************************************
	private static Tomador toma(FsNfse nfse) throws Exception {
		FsNfsePart toma = nfse.getFsnfseparttoma();
		Tomador t = new Tomador();
		if (toma.getCpfcnpj().length() == 14)
			t.setTomaCNPJ(toma.getCpfcnpj());
		if (toma.getCpfcnpj().length() == 11)
			t.setTomaCPF(toma.getCpfcnpj());
		t.setTomaIE(toma.getIe());
		t.setTomaIM(toma.getIm());
		t.setTomaIME(toma.getIm());
		t.setTomaRazaoSocial(toma.getXnome());
		t.setTomatpLgr(toma.getTipoend());
		t.setTomaEndereco(toma.getXlgr());
		t.setTomaNumero(FsUtil.cpND(toma.getNro(), "SN"));
		t.setTomaComplemento(FsUtil.cpND(toma.getXcpl(), "-"));
		t.setTomaBairro(toma.getXbairro());
		t.setTomacMun(BigInteger.valueOf(Integer.valueOf(toma.getCmun())));
		t.setTomaxMun(toma.getXmun());
		t.setTomaUF(toma.getUf());
		t.setTomaPais("BR");
		t.setTomaCEP(toma.getCep());
		t.setTomaTipoTelefone(toma.getTipofone());
		t.setTomaTelefone(toma.getFone());
		t.setTomaEmail(toma.getEmail());
		t.setTomaSituacaoEspecial(toma.getStespecial());
		t.setTomaRegEspTrib(BigDecimal.valueOf(Integer.valueOf(toma.getRegesptrib())));
		t.setTomaCadastroMunicipio(BigDecimal.valueOf(Integer.valueOf(toma.getCadmun())));
		t.setTomaOrgaoPublico(BigDecimal.valueOf(Integer.valueOf(toma.getOrgaopublico())));
		return t;
	}

	// ITENS***************************************************************************************
	private static ListaItens listaItens(FsNfse nfse) throws Exception {
		ListaItens listaItens = new ListaItens();
		for (FsNfseItem it : nfse.getFsnfseitems()) {
			FsNfseItemTrib itTrib = it.getFsnfseitemtrib();
			Item item = new Item();
			item.setItemSeq(BigInteger.valueOf(it.getNseq()));
			item.setItemCod(it.getCprod());
			item.setItemDesc(it.getXprod());
			item.setItemQtde(FsUtil.arredondaBigDec(it.getQtd(), 2));
			item.setItemvUnit(FsUtil.arredondaBigDec(it.getVunit(), 2));
			item.setItemuMed(it.getUnmed());
			item.setItemvlDed(FsUtil.arredondaBigDec(it.getVded(), 2));
			item.setItemTributavel(it.getTributavel());
			item.setItemTributMunicipio(it.getXprod());
			item.setItemnAlvara(nfse.getCdpessoaemp().getAlvara());
			item.setItemvIss(FsUtil.arredondaBigDec(itTrib.getViss(), 2));
			item.setItemvDesconto(FsUtil.arredondaBigDec(it.getVdesc(), 2));
			item.setItemAliquota(itTrib.getValiqiss());
			item.setItemVlrTotal(it.getVtot());
			item.setItemBaseCalculo(FsUtil.arredondaBigDec(itTrib.getVbc(), 2));
			item.setItemvlrISSRetido(FsUtil.arredondaBigDec(itTrib.getVissretido(), 2));
			int issretido = itTrib.getVissretido().compareTo(BigDecimal.ZERO) > 0 ? 1 : 2;
			item.setItemIssRetido(issretido);
			item.setItemRespRetencao(it.getRespretencao());
			item.setItemIteListServico(it.getCodserv());
			item.setItemExigibilidadeISS(BigInteger.valueOf(Integer.valueOf(nfse.getNatop())));
			item.setItemcMunIncidencia(
					BigInteger.valueOf(Integer.valueOf(nfse.getCdpessoaemp().getCdcidade().getIbge())));
			item.setItemNumProcesso(nfse.getNumprocesso());
			item.setItemDedTipo(it.getDedtp());
			item.setItemDedCPFRef("");
			item.setItemDedCNPJRef("");
			item.setItemDedNFRef(BigInteger.ZERO);
			item.setItemDedvlTotRef(BigDecimal.ZERO);
			item.setItemDedPer(BigDecimal.ZERO);
			item.setItemVlrLiquido(FsUtil.arredondaBigDec(itTrib.getVliquido(), 2));
			item.setItemValAliqINSS(itTrib.getValiqinss());
			item.setItemValINSS(FsUtil.arredondaBigDec(itTrib.getVinss(), 2));
			item.setItemValAliqIR(itTrib.getValiqir());
			item.setItemValIR(FsUtil.arredondaBigDec(itTrib.getVir(), 2));
			item.setItemValAliqCOFINS(itTrib.getValiqcofins());
			item.setItemValCOFINS(FsUtil.arredondaBigDec(itTrib.getVcofins(), 2));
			item.setItemValAliqCSLL(itTrib.getValiqcsll());
			item.setItemValCSLL(FsUtil.arredondaBigDec(itTrib.getVcsll(), 2));
			item.setItemValAliqPIS_0020(itTrib.getValiqpis());
			item.setItemValPIS(FsUtil.arredondaBigDec(itTrib.getVpis(), 2));
			item.setItemRedBCRetido(FsUtil.arredondaBigDec(itTrib.getVredbcretido(), 2));
			item.setItemBCRetido(FsUtil.arredondaBigDec(itTrib.getVbcretido(), 2));
			item.setItemValAliqISSRetido(itTrib.getValiqissret());
			item.setItemPaisImpDevido("BR");
			item.setItemJustDed("OUTROS");
			item.setItemvOutrasRetencoes(BigDecimal.ZERO);
			item.setItemDescIncondicionado(BigDecimal.ZERO);
			item.setItemDescCondicionado(BigDecimal.ZERO);
			listaItens.getItem().add(item);
		}
		return listaItens;
	}

	// SERVICO*************************************************************************************
	private static Servico servico(FsNfse nfse) throws Exception {
		FsNfseItem item = nfse.getFsnfseitems().get(0);
		String local = nfse.getLocal();
		// Duplicatas e vencimento
		FsNfseCobrDup dup = null;
		if (nfse.getFsnfsecobr().getFsnfsecobrdups().size() > 0) {
			dup = nfse.getFsnfsecobr().getFsnfsecobrdups().get(0);
		}
		String dvence = DataUtil.dataBancoAtual();
		Integer qtdparc = 1;
		if (dup != null) {
			dvence = DataUtil.dataPadraoSQL(dup.getDvenc());
			qtdparc = nfse.getFsnfsecobr().getFsnfsecobrdups().size();
		}
		XMLGregorianCalendar datavence = DataUtil.dTimG(dvence);
		Servico servico = new Servico();
		servico.setIteListServico(item.getCodserv());
		servico.setFPagamento(nfse.getFpag());
		servico.setTributMunicipio(item.getCodserv());
		servico.setTributMunicDesc(item.getXprod());
		servico.setDiscriminacao(nfse.getDiscriminacao());
		if (local.equals("1")) {
			servico.setCMun(BigInteger.valueOf(Integer.valueOf(nfse.getFsnfsepartprest().getCmun())));
		} else if (local.equals("2")) {
			servico.setCMun(BigInteger.valueOf(Integer.valueOf(nfse.getFsnfseparttoma().getCmun())));
		} else {
			servico.setCMun(BigInteger.valueOf(Integer.valueOf(nfse.getFsnfsepartlocal().getCmun())));
		}
		servico.setSerQuantidade(FsUtil.arredondaBigDec(nfse.getSerquantidade(), 2));
		servico.setSerUnidade("UN");
		servico.setSerNumAlva(nfse.getCdpessoaemp().getAlvara());
		servico.setPaiPreServico("BR");
		servico.setCMunIncidencia(BigInteger.valueOf(Integer.valueOf(nfse.getCdpessoaemp().getCdcidade().getIbge())));
		servico.setDVencimento(datavence);
		servico.setObsInsPagamento("-");
		servico.setObrigoMunic(BigInteger.ONE);
		servico.setTributacaoISS(BigInteger.ONE); // 1 - normal
		servico.setCodigoAtividadeEconomica(nfse.getCdpessoaemp().getCnae());
		servico.setServicoViasPublicas(BigDecimal.valueOf(2));
		servico.setNumeroParcelas(BigDecimal.valueOf(qtdparc));
		servico.setNroOrcamento(BigDecimal.ZERO);
		servico.setCodigoNBS("0");
		// Valores --------------------------------------------------
		FsNfseTot tot = nfse.getFsnfsetot();
		CdNfeCfg cdNfeCfg = nfse.getCdnfecfg();
		Valores valores = new Valores();
		valores.setValServicos(tot.getVservicos());
		valores.setValDeducoes(tot.getVdeducoes());
		valores.setValPIS(tot.getVpis());
		valores.setValCOFINS(tot.getVcofins());
		valores.setValINSS(tot.getVinss());
		valores.setValIR(tot.getVir());
		valores.setValCSLL(tot.getVcsll());
		valores.setValBCPIS(tot.getVservicos());
		valores.setValBCCOFINS(tot.getVservicos());
		valores.setValBCINSS(tot.getVservicos());
		valores.setValBCIRRF(tot.getVservicos());
		valores.setValBCCSLL(tot.getVservicos());
		int issretido = tot.getVissretido().compareTo(BigDecimal.ZERO) > 0 ? 1 : 2;
		valores.setISSRetido(issretido);
		valores.setRespRetencao(item.getRespretencao());
		valores.setTributavel(item.getTributavel());
		valores.setValISS(tot.getViss());
		valores.setValISSRetido(tot.getVissretido());
		valores.setValOutrasRetencoes(BigDecimal.ZERO);
		valores.setValBaseCalculo(tot.getVbc());
		valores.setValAliqISS(cdNfeCfg.getIss_aliq_nfse());
		valores.setValAliqPIS(cdNfeCfg.getPis_aliq());
		valores.setValAliqCOFINS(cdNfeCfg.getCofins_aliq());
		valores.setValAliqIR(cdNfeCfg.getIr_aliq_nfse());
		valores.setValAliqCSLL(cdNfeCfg.getCsll_aliq_nfse());
		valores.setValAliqINSS(cdNfeCfg.getInss_aliq_nfse());
		valores.setValLiquido(tot.getVliquido());
		valores.setValDescIncond(BigDecimal.ZERO);
		valores.setValDescCond(BigDecimal.ZERO);
		valores.setValAliqISSoMunic(cdNfeCfg.getIss_aliq_nfse());
		valores.setInfValPIS(tot.getVpis());
		valores.setInfValCOFINS(tot.getVcofins());
		valores.setValLiqFatura(tot.getVliquido());
		valores.setValBCISSRetido(tot.getVbcretido());
		valores.setNroFatura(BigDecimal.valueOf(Integer.valueOf(nfse.getFsnfsecobr().getNfat())));
		valores.setCargaTribValor(tot.getViss());
		valores.setCargaTribPercentual(cdNfeCfg.getPcargtrib_aliq_nfse());
		valores.setCargaTribFonte(""); // NF paulista
		valores.setJustDed("JUST.");
		servico.setValores(valores);
		// Local prestacao servico ----------------------------------
		LocalPrestacao localPrestacao = new LocalPrestacao();
		switch (local) {
		case "1":
			FsNfsePart prest = nfse.getFsnfsepartprest();
			localPrestacao.setSerEndTpLgr(prest.getTipoend());
			localPrestacao.setSerEndLgr(prest.getXlgr());
			localPrestacao.setSerEndNumero(prest.getNro());
			localPrestacao.setSerEndComplemento(FsUtil.cpND(prest.getXcpl(), "-"));
			localPrestacao.setSerEndBairro(prest.getXbairro());
			localPrestacao.setSerEndxMun(prest.getXmun());
			localPrestacao.setSerEndcMun(BigInteger.valueOf(Integer.valueOf(prest.getCmun())));
			localPrestacao.setSerEndCep(BigInteger.valueOf(Integer.valueOf(prest.getCep())));
			localPrestacao.setSerEndSiglaUF(prest.getUf());
			break;
		case "2":
			FsNfsePart toma = nfse.getFsnfseparttoma();
			localPrestacao.setSerEndTpLgr(toma.getTipoend());
			localPrestacao.setSerEndLgr(toma.getXlgr());
			localPrestacao.setSerEndNumero(toma.getNro());
			localPrestacao.setSerEndComplemento(FsUtil.cpND(toma.getXcpl(), "-"));
			localPrestacao.setSerEndBairro(toma.getXbairro());
			localPrestacao.setSerEndxMun(toma.getXmun());
			localPrestacao.setSerEndcMun(BigInteger.valueOf(Integer.valueOf(toma.getCmun())));
			localPrestacao.setSerEndCep(BigInteger.valueOf(Integer.valueOf(toma.getCep())));
			localPrestacao.setSerEndSiglaUF(toma.getUf());
			break;
		default:
			FsNfsePart outroLocal = nfse.getFsnfsepartlocal();
			localPrestacao.setSerEndTpLgr(outroLocal.getTipoend());
			localPrestacao.setSerEndLgr(outroLocal.getXlgr());
			localPrestacao.setSerEndNumero(outroLocal.getNro());
			localPrestacao.setSerEndComplemento(FsUtil.cpND(outroLocal.getXcpl(), "-"));
			localPrestacao.setSerEndBairro(outroLocal.getXbairro());
			localPrestacao.setSerEndxMun(outroLocal.getXmun());
			localPrestacao.setSerEndcMun(BigInteger.valueOf(Integer.valueOf(outroLocal.getCmun())));
			localPrestacao.setSerEndCep(BigInteger.valueOf(Integer.valueOf(outroLocal.getCep())));
			localPrestacao.setSerEndSiglaUF(outroLocal.getUf());
			break;
		}
		servico.setLocalPrestacao(localPrestacao);
		return servico;
	}

	// PARCELAS*************************************************************************************
	private static ListaParcelas listaParcelas(FsNfse nfse) throws Exception {
		ListaParcelas listaParcelas = new ListaParcelas();
		FsNfseCobr cobr = nfse.getFsnfsecobr();
		for (FsNfseCobrDup dup : cobr.getFsnfsecobrdups()) {
			String dvenc = DataUtil.dataPadraoSQL(dup.getDvenc());
			XMLGregorianCalendar datavence = DataUtil.dTimG(dvenc);
			Parcela parcela = new Parcela();
			parcela.setPrcSequencial(BigInteger.valueOf(Integer.valueOf(dup.getNdup())));
			parcela.setPrcValor(dup.getVdup());
			parcela.setPrcDtaVencimento(datavence);
			parcela.setPrcNroFatura(BigInteger.valueOf(Integer.valueOf(cobr.getNfat())));
			parcela.setPrcTipVenc(BigInteger.ONE); // Na data
			parcela.setPrcDscTipVenc("Outros"); // Padrao
			parcela.setPrcValLiquido(dup.getVdup());
			parcela.setPrcValDesconto(dup.getVdesc());
			listaParcelas.getParcela().add(parcela);
		}
		return listaParcelas;
	}
}