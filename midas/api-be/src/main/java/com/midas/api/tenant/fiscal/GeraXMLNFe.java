package com.midas.api.tenant.fiscal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.midas.api.constant.FsNfeWebService;
import com.midas.api.constant.MidasConfig;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfeAut;
import com.midas.api.tenant.entity.FsNfeCobr;
import com.midas.api.tenant.entity.FsNfeCobrDup;
import com.midas.api.tenant.entity.FsNfeFrete;
import com.midas.api.tenant.entity.FsNfeFreteVol;
import com.midas.api.tenant.entity.FsNfeItem;
import com.midas.api.tenant.entity.FsNfeItemCofins;
import com.midas.api.tenant.entity.FsNfeItemIcms;
import com.midas.api.tenant.entity.FsNfeItemIpi;
import com.midas.api.tenant.entity.FsNfeItemPis;
import com.midas.api.tenant.entity.FsNfePag;
import com.midas.api.tenant.entity.FsNfePart;
import com.midas.api.tenant.entity.FsNfeRef;
import com.midas.api.tenant.entity.FsNfeTotIcms;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.fiscal.util.FnPagNomeUtil;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.MoedaUtil;
import com.midas.api.util.NumUtil;

import br.inf.portalfiscal.nfe.ObjectFactory;
import br.inf.portalfiscal.nfe.TEnderEmi;
import br.inf.portalfiscal.nfe.TEndereco;
import br.inf.portalfiscal.nfe.TEnviNFe;
import br.inf.portalfiscal.nfe.TInfRespTec;
import br.inf.portalfiscal.nfe.TIpi;
import br.inf.portalfiscal.nfe.TIpi.IPINT;
import br.inf.portalfiscal.nfe.TIpi.IPITrib;
import br.inf.portalfiscal.nfe.TLocal;
import br.inf.portalfiscal.nfe.TNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.AutXML;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Cobr;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Cobr.Dup;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Cobr.Fat;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Compra;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Dest;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS00;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS10;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS20;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS30;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS40;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS51;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS70;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS90;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMSUFDest;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS.PISNT;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS.PISOutr;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Prod;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Prod.Comb;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Emit;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Exporta;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Ide;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Ide.NFref;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.InfAdic;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Pag;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Pag.DetPag;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Total;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Total.ICMSTot;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Transp;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Transp.Transporta;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Transp.Vol;
import br.inf.portalfiscal.nfe.TNFe.InfNFeSupl;
import br.inf.portalfiscal.nfe.TUf;
import br.inf.portalfiscal.nfe.TUfEmi;
import br.inf.portalfiscal.nfe.TVeiculo;

@Component
public class GeraXMLNFe {
	public String geraXMLNFe(FsNfe nfe, LcDoc lcDoc, MidasConfig mc) throws Exception {
		// MONTANDO XML DA NFE
		TNFe nFe = new TNFe();
		InfNFe infNFe = new InfNFe();
		infNFe.setId("NFe" + nfe.getChaveac());
		infNFe.setVersao(FsNfeWebService.versaoDados);
		infNFe.setIde(ide(nfe));
		infNFe.setEmit(emit(nfe));
		infNFe.setInfRespTec(respTec(mc));
		if (FsUtil.verCompra(nfe) == true) {
			infNFe.setCompra(compra(nfe));
		}
		infNFe.setDest(dest(nfe, lcDoc));
		if (!nfe.getFsnfepartdest().equals(nfe.getFsnfepartent())) {
			infNFe.setEntrega(entrega(nfe));
		}
		infNFe.setPag(pag(nfe));
		if (nfe.getFsnfecobr().getVorig().compareTo(BigDecimal.ZERO) > 0) {
			infNFe.setCobr(cobranca(nfe));
		}
		infNFe.setTotal(totais(nfe));
		infNFe.setTransp(transporte(nfe));
		infNFe.setInfAdic(infoAdd(nfe));
		infNFe.getAutXML().addAll(autXml(nfe));
		if (nfe.getFsnfepartdest().getIdestrangeiro() != null) {
			if (!nfe.getFsnfepartdest().getIdestrangeiro().equals("")) {
				if (!nfe.getUfsaidapais().equals("XX")) {
					infNFe.setExporta(exporta(nfe));
				}
			}
		}
		infNFe.getDet().addAll(det(nfe, lcDoc));
		nFe.setInfNFeSupl(infNFeSupl(nfe));
		nFe.setInfNFe(infNFe);
		TEnviNFe enviNFe = new TEnviNFe();
		enviNFe.setVersao(FsNfeWebService.versaoDados);
		enviNFe.setIndSinc("1");// INDICA AUTOMATICO RETORNO DE PROTOCOLO - CONSULTA NAO UTILIZADA AO ENVIAR NFE
		enviNFe.setIdLote("0000001");
		enviNFe.getNFe().add(nFe);
		// CRIA XML
		String xml = FsUtil.xmlNFe(enviNFe);
		return xml;
	}

	// IDENTIFICACAO***********************************************
	private static Ide ide(FsNfe nfe) throws Exception {
		Ide ide = new Ide();
		ide.setCUF(nfe.getCdpessoaemp().getCdestado().getId().toString());
		ide.setCNF(NumUtil.geraNumEsq(nfe.getCnf().intValue(), 8));
		ide.setNatOp(nfe.getNatop());
		ide.setMod(nfe.getModelo().toString());
		ide.setSerie(nfe.getSerie().toString());
		ide.setNNF(nfe.getNnf().toString());
		String dhemi = DataUtil.dataPadraoSQL(nfe.getDhemi());
		String dhemihr = DataUtil.horaPadraoSQL(nfe.getDhemihr());
		ide.setDhEmi(DataUtil.dtTim(dhemi, dhemihr));
		String dhents = DataUtil.dataPadraoSQL(nfe.getDhsaient());
		String dhentshr = DataUtil.horaPadraoSQL(nfe.getDhemihr());
		// Se for modelo 55
		if (nfe.getModelo().equals(55)) {
			ide.setDhSaiEnt(DataUtil.dtTim(dhents, dhentshr));
		}
		ide.setTpNF(nfe.getTpnf().toString());
		ide.setIdDest(nfe.getIddest().toString());
		ide.setCMunFG(nfe.getCmunfg());
		ide.setTpImp(nfe.getTpimp().toString());
		ide.setTpEmis(nfe.getTpemis().toString());
		ide.setCDV(nfe.getCdv().toString());
		ide.setTpAmb(nfe.getTpamb().toString());
		ide.setFinNFe(nfe.getFinnfe().toString());
		ide.setIndFinal(nfe.getIndfinal().toString());
		ide.setIndPres(nfe.getIndpres().toString());
		if (FsUtil.tpIntermediador(nfe.getIndpres().toString()) == true) {
			ide.setIndIntermed("0");// 0 - Op. Direta sem intermediador
		}
		ide.setProcEmi("0");// APLICATIVO CONTRIBUINTE
		ide.setVerProc(nfe.getVerproc());
		// NFE REFERENCIAS------------
		for (FsNfeRef r : nfe.getFsnferefs()) {
			NFref nfFref = new NFref();
			nfFref.setRefNFe(r.getRefnfe());
			ide.getNFref().add(nfFref);
		}
		return ide;
	}

	// EMITENTE*****************************************************
	private static Emit emit(FsNfe nfe) throws Exception {
		CdPessoa em = nfe.getCdpessoaemp();
		Emit emit = new Emit();
		emit.setCNPJ(em.getCpfcnpj());
		emit.setXNome(em.getNome());
		emit.setXFant(FsUtil.cpND(em.getFantasia(), em.getNome()));
		// Formata para Ambiente 2 - homologacao
		if (nfe.getTpamb().equals(2)) {
			emit.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
			emit.setXFant("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		}
		emit.setCRT(em.getCrt().toString());
		emit.setIE(FsUtil.cpND(em.getIerg(), "000000000"));
		String iest = nfe.getFsnfepartemit().getIest();
		if (iest != null) {
			if (!iest.equals("")) {
				emit.setIEST(iest);
			}
		}
		TEnderEmi en = new TEnderEmi();
		en.setXLgr(em.getRua());
		en.setNro(FsUtil.cpND(em.getNum(), "SN"));
		en.setXCpl(FsUtil.cpND(em.getComp(), "-"));
		en.setXBairro(em.getBairro());
		en.setCMun(em.getCdcidade().getIbge());
		en.setXMun(em.getCdcidade().getNome());
		en.setUF(TUfEmi.valueOf(em.getCdestado().getUf()));
		en.setCEP(em.getCep());
		en.setCPais(em.getCodpais());
		en.setXPais("BRASIL");// Sempre deve ser BRASIL
		en.setFone(FsUtil.cpND(em.getFonefat(), em.getFoneum()));
		emit.setEnderEmit(en);
		return emit;
	}

	// RESP. TECNICO*********************************************
	private static TInfRespTec respTec(MidasConfig mc) throws Exception {
		String cnpj = mc.MidasCNPJ;
		String nome = mc.MidasApresenta;
		String fone = mc.MidasFone;
		String email = mc.NrEmail;
		TInfRespTec rt = new TInfRespTec();
		rt.setCNPJ(cnpj);
		rt.setXContato(nome);
		rt.setEmail(email);
		rt.setFone(fone);
		// e.setIdCSRT("");
		// e.setHashCSRT(null);
		return rt;
	}

	// COMPRAS***************************************************
	private static Compra compra(FsNfe nfe) throws Exception {
		Compra cp = new Compra();
		cp.setXNEmp(FsUtil.cpND(nfe.getXnemp(), "0"));
		cp.setXPed(FsUtil.cpND(nfe.getXped(), "0"));
		cp.setXCont(FsUtil.cpND(nfe.getXcont(), "0"));
		return cp;
	}

	// DESTINATARIO***********************************************
	private static Dest dest(FsNfe nfe, LcDoc lcDoc) throws Exception {
		FsNfePart emi = nfe.getFsnfepartemit();
		FsNfePart de = nfe.getFsnfepartdest();
		Dest dest = new Dest();
		// NFe------------------------------
		if (nfe.getModelo().equals(55)) {
			dest = destCria(nfe);
			return dest;
		}
		// NFCe-------------------------------
		if (nfe.getModelo().equals(65)) {
			// Verifica se tem dados de cliente
			if (de.getCpfcnpj().equals(emi.getCpfcnpj())) {
				if (!lcDoc.getCpfcnpjnota().equals("00000000000")
						&& !lcDoc.getCpfcnpjnota().equals("0000000000000000")) {
					dest.setXNome(lcDoc.getNomenota());
					// Formata para Ambiente 2 - homologacao
					if (nfe.getTpamb().equals(2)) {
						dest.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
					}
					// CPF ou CNPJ na nota
					if (lcDoc.getCpfcnpjnota().length() == 14) {
						dest.setCNPJ(lcDoc.getCpfcnpjnota());
						dest.setIndIEDest("1");
					}
					if (lcDoc.getCpfcnpjnota().length() == 11) {
						dest.setCPF(lcDoc.getCpfcnpjnota());
						dest.setIndIEDest("9");
					}
					return dest;
				}
				return null;
			} else {
				dest = destCria(nfe);
			}
		}
		return dest;
	}

	// DESTINATARIO - GERA COMP.*************************************
	private static Dest destCria(FsNfe nfe) throws Exception {
		FsNfePart de = nfe.getFsnfepartdest();
		Dest dest = new Dest();
		if (de.getCpfcnpj().length() == 14) {
			dest.setCNPJ(de.getCpfcnpj());
		}
		if (de.getCpfcnpj().length() == 11) {
			dest.setCPF(de.getCpfcnpj());
		}
		if (de.getIe() != null) {
			if (!de.getIe().equals("")) {
				dest.setIE(de.getIe());
			}
		}
		dest.setXNome(de.getXnome());
		// Formata para Ambiente 2 - homologacao
		if (nfe.getTpamb().equals(2)) {
			dest.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		}
		dest.setEmail(FsUtil.cpND(de.getEmail().split(",")[0], "emailnaoregistrado@email.com"));
		dest.setIndIEDest(de.getIndiedest());
		if (de.getIsuf() != null) {
			if (!de.getIsuf().equals("")) {
				dest.setISUF(de.getIsuf());
			}
		}
		// Verifica operacao exterior
		if (de.getIdestrangeiro() != null) {
			if (!de.getIdestrangeiro().equals("")) {
				dest.setIdEstrangeiro(de.getIdestrangeiro());
			}
		}
		TEndereco en = new TEndereco();
		en.setXLgr(de.getXlgr());
		en.setNro(FsUtil.cpND(de.getNro(), "SN"));
		en.setXCpl(FsUtil.cpND(de.getXcpl(), "-"));
		en.setXBairro(de.getXbairro());
		en.setCMun(de.getCmun());
		en.setXMun(de.getXmun());
		en.setUF(TUf.valueOf(de.getUf()));
		en.setCEP(de.getCep());
		en.setCPais(de.getCpais());
		en.setXPais(de.getXpais());
		en.setFone(de.getFone());
		dest.setEnderDest(en);
		return dest;
	}

	// LOCAL ENTREGA*************************************************
	private static TLocal entrega(FsNfe nfe) throws Exception {
		FsNfePart ent = nfe.getFsnfepartent();
		TLocal en = new TLocal();
		if (ent.getCpfcnpj().length() == 14) {
			en.setCNPJ(ent.getCpfcnpj());
		}
		if (ent.getCpfcnpj().length() == 11) {
			en.setCPF(ent.getCpfcnpj());
		}
		en.setXLgr(ent.getXlgr());
		en.setNro(FsUtil.cpND(ent.getNro(), "SN"));
		en.setXCpl(FsUtil.cpND(ent.getXcpl(), "-"));
		en.setXBairro(ent.getXbairro());
		en.setCMun(ent.getCmun());
		en.setXMun(ent.getXmun());
		en.setUF(TUf.valueOf(ent.getUf()));
		return en;
	}

	// PAGAMENTO*****************************************************
	private static Pag pag(FsNfe nfe) throws Exception {
		List<FsNfePag> pgtos = nfe.getFsnfepags();
		Pag p = new Pag();
		for (FsNfePag e : pgtos) {
			DetPag dp = new DetPag();
			dp.setTPag(e.getTpag());
			dp.setVPag(MoedaUtil.moedaPadraoScale(e.getVpag(), 2));
			if (e.getTpag().equals("99")) {
				dp.setXPag(FnPagNomeUtil.nomePg(e.getTpag()));
			}
			p.getDetPag().add(dp);
		}
		return p;
	}

	// TRANSPORTE#########################################################################################
	private static Transp transporte(FsNfe nfe) throws Exception {
		Transp t = new Transp();
		FsNfeFrete frete = nfe.getFsnfefrete();
		FsNfePart transp = nfe.getFsnfeparttransp();
		// MODO DE FRETE------------------------
		String mdf = frete.getModfrete();
		t.setModFrete(mdf);
		if (!mdf.equals("9")) {
			// TRANSPORTADORA SE HOUVER---------
			if (!nfe.getFsnfepartemit().getCpfcnpj().equals(transp.getCpfcnpj())) {
				Transporta transporta = new Transporta();
				if (transp.getCpfcnpj().length() == 14) {
					transporta.setCNPJ(transp.getCpfcnpj());
					if (transp.getIe() != null) {
						if (transp.getIe().equals("")) {
							transporta.setIE(transp.getIe());
						}
					}
				}
				if (transp.getCpfcnpj().length() == 11) {
					transporta.setCPF(transp.getCpfcnpj());
				}
				transporta.setXNome(transp.getXnome());
				transporta.setXEnder(transp.getXlgr());
				transporta.setXMun(transp.getXmun());
				transporta.setUF(TUf.valueOf(transp.getUf()));
				t.setTransporta(transporta);
			}
		}
		// DADOS VEICULO--------------------------
		String placa = frete.getC_placa();
		if (placa != null) {
			if (placa.length() == 7) {
				TVeiculo v = new TVeiculo();
				v.setPlaca(placa);
				if (!frete.getC_rntc().equals("")) {
					v.setRNTC(frete.getC_rntc());
				}
				if (!frete.getC_uf().equals("XX")) {
					v.setUF(TUf.valueOf(frete.getC_uf()));
				}
				t.setVeicTransp(v);
			}
		}
		// VOLUMES---------------------------------
		List<FsNfeFreteVol> vols = frete.getFsnfefretevols();
		for (FsNfeFreteVol v : vols) {
			Vol vo = new Vol();
			vo.setQVol(MoedaUtil.moedaPadraoScale(v.getQvol(), 0));
			vo.setEsp(FsUtil.cpND(v.getEsp(), "ND"));
			vo.setNVol(FsUtil.cpND(v.getNvol(), "ND"));
			vo.setMarca(FsUtil.cpND(v.getMarca(), "ND"));
			vo.setPesoL(MoedaUtil.moedaPadraoScale(v.getPesol(), 3));
			vo.setPesoB(MoedaUtil.moedaPadraoScale(v.getPesob(), 3));
			t.getVol().add(vo);
		}
		return t;
	}

	// COBRANCA******************************************************
	private static Cobr cobranca(FsNfe nfe) throws Exception {
		// Apenas NFe - 55
		if (nfe.getModelo().equals(55)) {
			FsNfeCobr c = nfe.getFsnfecobr();
			List<FsNfeCobrDup> ds = nfe.getFsnfecobr().getFsnfecobrdups();
			Cobr cob = new Cobr();
			// Dados fatura geral
			Fat f = new Fat();
			f.setNFat(FsUtil.cpND(c.getNfat(), nfe.getNnf() + ""));
			f.setVOrig(MoedaUtil.moedaPadraoScale(c.getVorig(), 2));
			f.setVDesc(MoedaUtil.moedaPadraoScale(c.getVdesc(), 2));
			f.setVLiq(MoedaUtil.moedaPadraoScale(c.getVliq(), 2));
			cob.setFat(f);
			// Dados de titulos
			for (FsNfeCobrDup dp : ds) {
				Dup d = new Dup();
				d.setNDup(dp.getNdup());
				d.setDVenc(DataUtil.dataPadraoSQL(dp.getDvenc()));
				d.setVDup(MoedaUtil.moedaPadraoScale(dp.getVdup(), 2));
				cob.getDup().add(d);
			}
			return cob;
		}
		return null;
	}

	// VALORES DA NFE***********************************************
	private static Total totais(FsNfe nfe) throws Exception {
		FsNfeTotIcms icms = nfe.getFsnfetoticms();
		Total t = new Total();
		ICMSTot i = new ICMSTot();
		i.setVFCPUFDest(MoedaUtil.moedaPadraoScale(icms.getVfcpufdest(), 2));
		i.setVICMSUFDest(MoedaUtil.moedaPadraoScale(icms.getVicmsufdest(), 2));
		i.setVICMSUFRemet(MoedaUtil.moedaPadraoScale(icms.getVicmsufremet(), 2));
		i.setVFCP(MoedaUtil.moedaPadraoScale(icms.getVfcp(), 2));
		i.setVFCPST(MoedaUtil.moedaPadraoScale(icms.getVfcpst(), 2));
		i.setVFCPSTRet(MoedaUtil.moedaPadraoScale(icms.getVfcpstret(), 2));
		i.setVBC(MoedaUtil.moedaPadraoScale(icms.getVbc(), 2));
		i.setVICMS(MoedaUtil.moedaPadraoScale(icms.getVicms(), 2));
		i.setVICMSDeson(MoedaUtil.moedaPadraoScale(icms.getVicmsdeson(), 2));
		i.setVBCST(MoedaUtil.moedaPadraoScale(icms.getVbcst(), 2));
		i.setVST(MoedaUtil.moedaPadraoScale(icms.getVst(), 2));
		i.setVProd(MoedaUtil.moedaPadraoScale(icms.getVprod(), 2));
		i.setVFrete(MoedaUtil.moedaPadraoScale(icms.getVfrete(), 2));
		i.setVSeg(MoedaUtil.moedaPadraoScale(icms.getVseg(), 2));
		i.setVDesc(MoedaUtil.moedaPadraoScale(icms.getVdesc(), 2));
		i.setVII(MoedaUtil.moedaPadraoScale(icms.getVii(), 2));
		i.setVIPI(MoedaUtil.moedaPadraoScale(icms.getVipi(), 2));
		i.setVIPIDevol(MoedaUtil.moedaPadraoScale(icms.getVipidevol(), 2));
		i.setVPIS(MoedaUtil.moedaPadraoScale(icms.getVpis(), 2));
		i.setVCOFINS(MoedaUtil.moedaPadraoScale(icms.getVcofins(), 2));
		i.setVOutro(MoedaUtil.moedaPadraoScale(icms.getVoutro(), 2));
		i.setVTotTrib(MoedaUtil.moedaPadraoScale(icms.getVtottrib(), 2));
		i.setVNF(MoedaUtil.moedaPadraoScale(icms.getVnf(), 2));
		t.setICMSTot(i);
		return t;
	}

	// INFORMACOES-ADICIONAIS****************************************
	private static InfAdic infoAdd(FsNfe nfe) throws Exception {
		InfAdic i = new InfAdic();
		if (!nfe.getInfcpl().equals("")) {
			i.setInfCpl(nfe.getInfcpl());
		}
		if (!nfe.getInfadfisco().equals("")) {
			i.setInfAdFisco(nfe.getInfadfisco());
		}
		return i;
	}

	// XML-AUTORIZADO************************************************
	private static List<AutXML> autXml(FsNfe nfe) throws Exception {
		List<FsNfeAut> autos = nfe.getFsnfeauts();
		List<AutXML> i = new ArrayList<AutXML>();
		for (FsNfeAut x : autos) {
			AutXML a = new AutXML();
			if (x.getCpfcnpj().length() == 14) {
				a.setCNPJ(x.getCpfcnpj());
			}
			if (x.getCpfcnpj().length() == 11) {
				a.setCPF(x.getCpfcnpj());
			}
			i.add(a);
		}
		if (!nfe.getFsnfepartemit().getCpfcnpj().equals(nfe.getFsnfeparttransp().getCpfcnpj())) {
			FsNfePart tr = nfe.getFsnfeparttransp();
			AutXML transp = new AutXML();
			if (tr.getCpfcnpj().length() == 14) {
				transp.setCNPJ(tr.getCpfcnpj());
			}
			if (tr.getCpfcnpj().length() == 11) {
				transp.setCPF(tr.getCpfcnpj());
			}
			i.add(transp);
		}
		return i;
	}

	// INFORMACOES-EXPORTACAO**************************************
	private static Exporta exporta(FsNfe nfe) throws Exception {
		Exporta ex = new Exporta();
		ex.setUFSaidaPais(TUfEmi.valueOf(nfe.getUfsaidapais()));
		ex.setXLocDespacho(nfe.getXlocdespacho());
		ex.setXLocExporta(nfe.getXlocexporta());
		return ex;
	}

	// QR CODE NFC-e***********************************************
	private static InfNFeSupl infNFeSupl(FsNfe nfe) throws Exception {
		if (nfe.getModelo().equals(65)) {
			InfNFeSupl e = new InfNFeSupl();
			String ret = FsUtil.qrCodeNFCe(nfe)[0];
			String url = FsUtil.qrCodeNFCe(nfe)[1];
			e.setQrCode(ret);
			e.setUrlChave(url);
			return e;
		}
		return null;
	}

	// ITEMS************************************************
	private static List<Det> det(FsNfe nfe, LcDoc lcDoc) throws Exception {
		List<Det> i = new ArrayList<Det>();
		List<FsNfeItem> ps = nfe.getFsnfeitems();
		int x = 1;
		for (FsNfeItem it : ps) {
			FsNfeItemIcms ic = it.getFsnfeitemicms();
			String cstcsosn = ic.getCst();
			Det det = new Det();
			det.setNItem(String.valueOf(x));
			Prod p = new Prod();
			p.setCProd(it.getCprod());
			p.setXProd(it.getXprod());
			p.setCEAN(FsUtil.cpND(it.getCean(), "SEM GTIN"));
			p.setCEANTrib(FsUtil.cpND(it.getCeantrib(), "SEM GTIN"));
			p.setNCM(it.getNcm());
			// CEST SE NECESSARIO
			String cest = it.getCest();
			if (cest != null) {
				if (!cest.equals("")) {
					p.setCEST(cest);
				}
			}
			p.setCFOP(it.getCfop());
			// IndEscala e Fabricante
			String indEscala = it.getIndescala();
			String cnpjFab = it.getCnpjfab();
			if (!indEscala.equals("X")) {
				p.setIndEscala(indEscala);
				if (!cnpjFab.equals("00000000000000") && indEscala.equals("N")) {
					p.setCNPJFab(cnpjFab);
				}
			}
			// CBenef depende se for deducao ou reducao de base de calculo
			if (nfe.getTpnf().equals(1)) {
				if (cstcsosn.equals("20") || cstcsosn.equals("30") || cstcsosn.equals("40") || cstcsosn.equals("41")
						|| cstcsosn.equals("50") || cstcsosn.equals("51") || cstcsosn.equals("70")) {
					if (it.getCbenef() != null) {
						if (!it.getCbenef().equals("")) {
							p.setCBenef(it.getCbenef());
						}
					}
				}
			}
			p.setUCom(it.getUcom());
			p.setQCom(MoedaUtil.moedaPadraoScale(it.getQcom(), 4));
			p.setVUnCom(MoedaUtil.moedaPadraoScale(it.getVuncom(), 10));
			p.setVProd(MoedaUtil.moedaPadraoScale(it.getVprod(), 2));
			p.setUTrib(it.getUtrib());
			p.setQTrib(MoedaUtil.moedaPadraoScale(it.getQtrib(), 4));
			p.setVUnTrib(MoedaUtil.moedaPadraoScale(it.getVuntrib(), 10));
			if (it.getVfrete().compareTo(BigDecimal.ZERO) > 0) {
				p.setVFrete(MoedaUtil.moedaPadraoScale(it.getVfrete(), 2));
			}
			if (it.getVseg().compareTo(BigDecimal.ZERO) > 0) {
				p.setVSeg(MoedaUtil.moedaPadraoScale(it.getVseg(), 2));
			}
			if (it.getVdesc().compareTo(BigDecimal.ZERO) > 0) {
				p.setVDesc(MoedaUtil.moedaPadraoScale(it.getVdesc(), 2));
			}
			if (it.getVoutro().compareTo(BigDecimal.ZERO) > 0) {
				p.setVOutro(MoedaUtil.moedaPadraoScale(it.getVoutro(), 2));
			}
			p.setIndTot("1");// PARTICIPA DA SOMA DOS ITENS
			p.setXPed(FsUtil.cpND(it.getXped(), "-"));
			p.setNItemPed(String.valueOf(x));
			// OLEOS - COMBUSTIVEIS
			if (it.getCprodanp() != null) {
				if (!it.getCprodanp().equals("")) {
					// OLEOS - COMBUSTIVEIS
					Comb co = new Comb();
					co.setCProdANP(it.getCprodanp());
					co.setDescANP(it.getDescanp());
					co.setUFCons(TUf.valueOf(nfe.getFsnfepartdest().getUf()));
					p.setComb(co);
				}
			}
			det.setProd(p);
			// IMPOSTO----------------------------
			Imposto im = new Imposto();
			String vTotTrib = MoedaUtil.moedaPadraoScale(it.getVtottrib(), 2);
			im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoVTotTrib(vTotTrib));
			ICMS icms = new ICMS();
			// 00---------------------------------
			if (cstcsosn.equals("00")) {
				ICMS00 icms00 = new ICMS00();
				// ICMS
				icms00.setOrig(ic.getOrig());
				icms00.setCST(ic.getCst());
				icms00.setModBC(ic.getModbc().toString());
				icms00.setVBC(MoedaUtil.moedaPadraoScale(ic.getVbc(), 2));
				icms00.setPICMS(MoedaUtil.moedaPadraoScale(ic.getPicms(), 4));
				icms00.setVICMS(MoedaUtil.moedaPadraoScale(ic.getVicms(), 2));
				// FCP ICMS
				if (ic.getPfcp().compareTo(BigDecimal.ZERO) > 0) {
					icms00.setPFCP(MoedaUtil.moedaPadraoScale(ic.getPfcp(), 4));
					icms00.setVFCP(MoedaUtil.moedaPadraoScale(ic.getVfcp(), 2));
				}
				icms.setICMS00(icms00);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 10---------------------------------
			if (cstcsosn.equals("10")) {
				ICMS10 icms10 = new ICMS10();
				// ICMS
				icms10.setOrig(ic.getOrig());
				icms10.setCST(ic.getCst());
				icms10.setModBC(ic.getModbc().toString());
				icms10.setVBC(MoedaUtil.moedaPadraoScale(ic.getVbc(), 2));
				icms10.setPICMS(MoedaUtil.moedaPadraoScale(ic.getPicms(), 4));
				icms10.setVICMS(MoedaUtil.moedaPadraoScale(ic.getVicms(), 2));
				// ICMS ST
				icms10.setModBCST(ic.getModbcst().toString());
				icms10.setPMVAST(MoedaUtil.moedaPadraoScale(ic.getPmvast(), 4));
				icms10.setPRedBCST(MoedaUtil.moedaPadraoScale(ic.getPredbcst(), 4));
				icms10.setVBCST(MoedaUtil.moedaPadraoScale(ic.getVbcst(), 2));
				icms10.setPICMSST(MoedaUtil.moedaPadraoScale(ic.getPicmsst(), 4));
				icms10.setVICMSST(MoedaUtil.moedaPadraoScale(ic.getVicmsst(), 2));
				// FCP ICMS
				if (ic.getPfcp().compareTo(BigDecimal.ZERO) > 0) {
					icms10.setVBCFCP(MoedaUtil.moedaPadraoScale(ic.getVbcfcp(), 2));
					icms10.setPFCP(MoedaUtil.moedaPadraoScale(ic.getPfcp(), 4));
					icms10.setVFCP(MoedaUtil.moedaPadraoScale(ic.getVfcp(), 2));
				}
				// FCP ICMS ST
				if (ic.getPfcpst().compareTo(BigDecimal.ZERO) > 0) {
					icms10.setVBCFCPST(MoedaUtil.moedaPadraoScale(ic.getVbcfcpst(), 2));
					icms10.setPFCPST(MoedaUtil.moedaPadraoScale(ic.getPfcpst(), 4));
					icms10.setVFCPST(MoedaUtil.moedaPadraoScale(ic.getVfcpst(), 2));
				}
				icms.setICMS10(icms10);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 20--------------------------------
			if (cstcsosn.equals("20")) {
				ICMS20 icms20 = new ICMS20();
				// ICMS
				icms20.setOrig(ic.getOrig());
				icms20.setCST(ic.getCst());
				icms20.setModBC(ic.getModbc().toString());
				icms20.setPRedBC(MoedaUtil.moedaPadraoScale(ic.getPredbc(), 4));
				icms20.setVBC(MoedaUtil.moedaPadraoScale(ic.getVbc(), 2));
				icms20.setPICMS(MoedaUtil.moedaPadraoScale(ic.getPicms(), 4));
				icms20.setVICMS(MoedaUtil.moedaPadraoScale(ic.getVicms(), 2));
				// FCP ICMS
				if (ic.getPfcp().compareTo(BigDecimal.ZERO) > 0) {
					icms20.setVBCFCP(MoedaUtil.moedaPadraoScale(ic.getVbcfcp(), 2));
					icms20.setPFCP(MoedaUtil.moedaPadraoScale(ic.getPfcp(), 4));
					icms20.setVFCP(MoedaUtil.moedaPadraoScale(ic.getVfcp(), 2));
				}
				icms.setICMS20(icms20);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 30--------------------------------
			if (cstcsosn.equals("30")) {
				ICMS30 icms30 = new ICMS30();
				// ICMS ST
				icms30.setOrig(ic.getOrig());
				icms30.setCST(ic.getCst());
				icms30.setModBCST(ic.getModbcst().toString());
				icms30.setPMVAST(MoedaUtil.moedaPadraoScale(ic.getPmvast(), 4));
				icms30.setPRedBCST(MoedaUtil.moedaPadraoScale(ic.getPredbcst(), 4));
				icms30.setVBCST(MoedaUtil.moedaPadraoScale(ic.getVbcst(), 2));
				icms30.setPICMSST(MoedaUtil.moedaPadraoScale(ic.getPicmsst(), 4));
				icms30.setVICMSST(MoedaUtil.moedaPadraoScale(ic.getVicmsst(), 2));
				// FCP ICMS ST
				if (ic.getPfcpst().compareTo(BigDecimal.ZERO) > 0) {
					icms30.setVBCFCPST(MoedaUtil.moedaPadraoScale(ic.getVbcfcpst(), 2));
					icms30.setPFCPST(MoedaUtil.moedaPadraoScale(ic.getPfcpst(), 4));
					icms30.setVFCPST(MoedaUtil.moedaPadraoScale(ic.getVfcpst(), 2));
				}
				icms.setICMS30(icms30);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 40 - 41 - 50----------------------
			if (cstcsosn.equals("40") || cstcsosn.equals("41") || cstcsosn.equals("50")) {
				ICMS40 icms40 = new ICMS40();
				icms40.setOrig(ic.getOrig());
				icms40.setCST(ic.getCst());
				icms.setICMS40(icms40);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 51--------------------------------
			if (cstcsosn.equals("51")) {
				ICMS51 icms51 = new ICMS51();
				icms51.setOrig(ic.getOrig());
				icms51.setCST(ic.getCst());
				icms.setICMS51(icms51);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 60--------------------------------
			if (cstcsosn.equals("60")) {
				ICMS60 icms60 = new ICMS60();
				// ICMS ST
				icms60.setOrig(ic.getOrig());
				icms60.setCST(ic.getCst());
				icms60.setVBCSTRet(MoedaUtil.moedaPadraoScale(ic.getVbcstret(), 2));
				icms60.setPST(MoedaUtil.moedaPadraoScale(ic.getPst(), 4));
				icms60.setVICMSSTRet(MoedaUtil.moedaPadraoScale(ic.getVicmsstret(), 2));
				icms.setICMS60(icms60);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 70--------------------------------
			if (cstcsosn.equals("70")) {
				ICMS70 icms70 = new ICMS70();
				// ICMS
				icms70.setOrig(ic.getOrig());
				icms70.setCST(ic.getCst());
				icms70.setModBC(ic.getModbc().toString());
				icms70.setPRedBC(MoedaUtil.moedaPadraoScale(ic.getPredbc(), 4));
				icms70.setVBC(MoedaUtil.moedaPadraoScale(ic.getVbc(), 2));
				icms70.setPICMS(MoedaUtil.moedaPadraoScale(ic.getPicms(), 4));
				icms70.setVICMS(MoedaUtil.moedaPadraoScale(ic.getVicms(), 2));
				// ICMS ST
				icms70.setModBCST(ic.getModbcst().toString());
				icms70.setPMVAST(MoedaUtil.moedaPadraoScale(ic.getPmvast(), 4));
				icms70.setPRedBCST(MoedaUtil.moedaPadraoScale(ic.getPredbcst(), 4));
				icms70.setVBCST(MoedaUtil.moedaPadraoScale(ic.getVbcst(), 2));
				icms70.setPICMSST(MoedaUtil.moedaPadraoScale(ic.getPicmsst(), 4));
				icms70.setVICMSST(MoedaUtil.moedaPadraoScale(ic.getVicmsst(), 2));
				// FCP ICMS
				if (ic.getPfcp().compareTo(BigDecimal.ZERO) > 0) {
					icms70.setVBCFCP(MoedaUtil.moedaPadraoScale(ic.getVbcfcp(), 2));
					icms70.setPFCP(MoedaUtil.moedaPadraoScale(ic.getPfcp(), 4));
					icms70.setVFCP(MoedaUtil.moedaPadraoScale(ic.getVfcp(), 2));
				}
				// FCP ICMS ST
				if (ic.getPfcpst().compareTo(BigDecimal.ZERO) > 0) {
					icms70.setVBCFCPST(MoedaUtil.moedaPadraoScale(ic.getVbcfcpst(), 2));
					icms70.setPFCPST(MoedaUtil.moedaPadraoScale(ic.getPfcpst(), 4));
					icms70.setVFCPST(MoedaUtil.moedaPadraoScale(ic.getVfcpst(), 2));
				}
				icms.setICMS70(icms70);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 90-------------------------------
			if (cstcsosn.equals("90")) {
				ICMS90 icms90 = new ICMS90();
				icms90.setOrig(ic.getOrig());
				icms90.setCST(ic.getCst());
				icms90.setModBC(ic.getModbc().toString());
				icms90.setPRedBC(MoedaUtil.moedaPadraoScale(ic.getPredbc(), 4));
				icms90.setVBC(MoedaUtil.moedaPadraoScale(ic.getVbc(), 2));
				icms90.setPICMS(MoedaUtil.moedaPadraoScale(ic.getPicms(), 4));
				icms90.setVICMS(MoedaUtil.moedaPadraoScale(ic.getVicms(), 2));
				// ICMS ST
				icms90.setModBCST(ic.getModbcst().toString());
				icms90.setPMVAST(MoedaUtil.moedaPadraoScale(ic.getPmvast(), 4));
				icms90.setPRedBCST(MoedaUtil.moedaPadraoScale(ic.getPredbcst(), 4));
				icms90.setVBCST(MoedaUtil.moedaPadraoScale(ic.getVbcst(), 2));
				icms90.setPICMSST(MoedaUtil.moedaPadraoScale(ic.getPicmsst(), 4));
				icms90.setVICMSST(MoedaUtil.moedaPadraoScale(ic.getVicmsst(), 2));
				icms.setICMS90(icms90);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 101--------------------------------
			if (cstcsosn.equals("101")) {
				ICMSSN101 icmssn101 = new ICMSSN101();
				// ICMS
				icmssn101.setOrig(ic.getOrig());
				icmssn101.setCSOSN(ic.getCst());
				icmssn101.setPCredSN(MoedaUtil.moedaPadraoScale(ic.getPcredsn(), 4));
				icmssn101.setVCredICMSSN(MoedaUtil.moedaPadraoScale(ic.getVcredicmssn(), 2));
				icms.setICMSSN101(icmssn101);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 102 - 103 - 300 - 400--------------
			if (cstcsosn.equals("102") || cstcsosn.equals("103") || cstcsosn.equals("300") || cstcsosn.equals("400")) {
				ICMSSN102 icmssn102 = new ICMSSN102();
				icmssn102.setOrig(ic.getOrig());
				icmssn102.setCSOSN(ic.getCst());
				icms.setICMSSN102(icmssn102);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 201--------------------------------
			if (cstcsosn.equals("201")) {
				ICMSSN201 icmssn201 = new ICMSSN201();
				// ICMS ST
				icmssn201.setOrig(ic.getOrig());
				icmssn201.setCSOSN(ic.getCst());
				icmssn201.setModBCST(ic.getModbcst().toString());
				icmssn201.setPRedBCST(MoedaUtil.moedaPadraoScale(ic.getPredbcst(), 4));
				icmssn201.setPMVAST(MoedaUtil.moedaPadraoScale(ic.getPmvast(), 4));
				icmssn201.setVBCST(MoedaUtil.moedaPadraoScale(ic.getVbcst(), 2));
				icmssn201.setPICMSST(MoedaUtil.moedaPadraoScale(ic.getPicmsst(), 4));
				icmssn201.setVICMSST(MoedaUtil.moedaPadraoScale(ic.getVicmsst(), 2));
				icmssn201.setPCredSN(MoedaUtil.moedaPadraoScale(ic.getPcredsn(), 2));
				icmssn201.setVCredICMSSN(MoedaUtil.moedaPadraoScale(ic.getVcredicmssn(), 2));
				icms.setICMSSN201(icmssn201);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 202 - 203---------------------------
			if (cstcsosn.equals("202") || cstcsosn.equals("203")) {
				ICMSSN202 icmssn202 = new ICMSSN202();
				// ICMS
				icmssn202.setOrig(ic.getOrig());
				icmssn202.setCSOSN(ic.getCst());
				icmssn202.setModBCST(ic.getModbcst().toString());
				icmssn202.setPRedBCST(MoedaUtil.moedaPadraoScale(ic.getPredbcst(), 4));
				icmssn202.setPMVAST(MoedaUtil.moedaPadraoScale(ic.getPmvast(), 4));
				icmssn202.setVBCST(MoedaUtil.moedaPadraoScale(ic.getVbcst(), 2));
				icmssn202.setPICMSST(MoedaUtil.moedaPadraoScale(ic.getPicmsst(), 4));
				icmssn202.setVICMSST(MoedaUtil.moedaPadraoScale(ic.getVicmsst(), 2));
				icms.setICMSSN202(icmssn202);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 500---------------------------------
			if (cstcsosn.equals("500")) {
				ICMSSN500 icmssn500 = new ICMSSN500();
				// ICMS ST
				icmssn500.setOrig(ic.getOrig());
				icmssn500.setCSOSN(ic.getCst());
				icmssn500.setVBCSTRet(MoedaUtil.moedaPadraoScale(ic.getVbcstret(), 2));
				icmssn500.setPST(MoedaUtil.moedaPadraoScale(ic.getPst(), 4));
				icmssn500.setVICMSSTRet(MoedaUtil.moedaPadraoScale(ic.getVicmsstret(), 2));
				icms.setICMSSN500(icmssn500);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// 900---------------------------------
			if (cstcsosn.equals("900")) {
				ICMSSN900 icmssn900 = new ICMSSN900();
				// ICMS
				icmssn900.setOrig(ic.getOrig());
				icmssn900.setCSOSN(ic.getCst());
				icmssn900.setModBC(ic.getModbc().toString());
				icmssn900.setPRedBC(MoedaUtil.moedaPadraoScale(ic.getPredbc(), 4));
				icmssn900.setVBC(MoedaUtil.moedaPadraoScale(ic.getVbc(), 2));
				icmssn900.setPICMS(MoedaUtil.moedaPadraoScale(ic.getPicms(), 4));
				icmssn900.setVICMS(MoedaUtil.moedaPadraoScale(ic.getVicms(), 2));
				// ICMS ST
				icmssn900.setModBCST(ic.getModbcst().toString());
				icmssn900.setPMVAST(MoedaUtil.moedaPadraoScale(ic.getPmvast(), 4));
				icmssn900.setPRedBCST(MoedaUtil.moedaPadraoScale(ic.getPredbcst(), 4));
				icmssn900.setVBCST(MoedaUtil.moedaPadraoScale(ic.getVbcst(), 2));
				icmssn900.setPICMSST(MoedaUtil.moedaPadraoScale(ic.getPicmsst(), 4));
				icmssn900.setVICMSST(MoedaUtil.moedaPadraoScale(ic.getVicmsst(), 2));
				// Simples nacional
				icmssn900.setPCredSN(MoedaUtil.moedaPadraoScale(ic.getPcredsn(), 2));
				icmssn900.setVCredICMSSN(MoedaUtil.moedaPadraoScale(ic.getVcredicmssn(), 2));
				icms.setICMSSN900(icmssn900);
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
			}
			// IPI - Apenas NFe*********************************************
			if (nfe.getModelo().equals(55)) {
				FsNfeItemIpi ipi = it.getFsnfeitemipi();
				IPITrib ipiT = new IPITrib();
				IPINT ipNT = new IPINT();
				TIpi ipTr = new TIpi();
				// NAO TRIBUTADA---------------
				if (ipi.getCst().equals("51") || ipi.getCst().equals("52") || ipi.getCst().equals("53")
						|| ipi.getCst().equals("54") || ipi.getCst().equals("55")) {
					ipTr.setCEnq(ipi.getCenq());
					ipNT.setCST(ipi.getCst());
					ipTr.setIPINT(ipNT);
					im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoIPI(ipTr));
				} else {
					// TRIBUTADA---------------
					ipTr.setCEnq(ipi.getCenq());
					ipiT.setCST(ipi.getCst());
					ipiT.setVBC(MoedaUtil.moedaPadraoScale(ipi.getVbc(), 2));
					ipiT.setPIPI(MoedaUtil.moedaPadraoScale(ipi.getPipi(), 4));
					ipiT.setVIPI(MoedaUtil.moedaPadraoScale(ipi.getVipi(), 2));
					ipTr.setIPITrib(ipiT);
					im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoIPI(ipTr));
				}
			}
			// PIS**************************************************
			FsNfeItemPis pi = it.getFsnfeitempis();
			PIS pis = new PIS();
			// 01 a 05 / 50 a 70-----------------
			if (pi.getCst().equals("01") || pi.getCst().equals("02") || pi.getCst().equals("03")
					|| pi.getCst().equals("04") || pi.getCst().equals("05") || pi.getCst().equals("06")
					|| pi.getCst().equals("07") || pi.getCst().equals("08") || pi.getCst().equals("50")
					|| pi.getCst().equals("51") || pi.getCst().equals("52") || pi.getCst().equals("53")
					|| pi.getCst().equals("54") || pi.getCst().equals("55") || pi.getCst().equals("56")
					|| pi.getCst().equals("60") || pi.getCst().equals("61") || pi.getCst().equals("62")
					|| pi.getCst().equals("63") || pi.getCst().equals("64") || pi.getCst().equals("65")
					|| pi.getCst().equals("66") || pi.getCst().equals("67") || pi.getCst().equals("70")
					|| pi.getCst().equals("71") || pi.getCst().equals("73") || pi.getCst().equals("74")) {
				PISAliq pisal = new PISAliq();
				pisal.setCST(pi.getCst());
				pisal.setVBC(MoedaUtil.moedaPadraoScale(pi.getVbc(), 2));
				pisal.setPPIS(MoedaUtil.moedaPadraoScale(pi.getPpis(), 4));
				pisal.setVPIS(MoedaUtil.moedaPadraoScale(pi.getVpis(), 2));
				pis.setPISAliq(pisal);
			}
			// 49 - 98 - 99--------------------------
			if (pi.getCst().equals("49") || pi.getCst().equals("98") || pi.getCst().equals("99")) {
				PISOutr pisal = new PISOutr();
				pisal.setCST(pi.getCst());
				pisal.setVBC(MoedaUtil.moedaPadraoScale(pi.getVbc(), 2));
				pisal.setPPIS(MoedaUtil.moedaPadraoScale(pi.getPpis(), 4));
				pisal.setVPIS(MoedaUtil.moedaPadraoScale(pi.getVpis(), 2));
				pis.setPISOutr(pisal);
			}
			// 09 e 72-------------------------------
			if (pi.getCst().equals("09") || pi.getCst().equals("72")) {
				PISNT pisal = new PISNT();
				pisal.setCST(pi.getCst());
				pis.setPISNT(pisal);
			}
			im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(pis));
			// COFINS************************************************
			FsNfeItemCofins co = it.getFsnfeitemcofins();
			COFINS cof = new COFINS();
			// 01 a 05 / 50 a 70-----------------
			if (co.getCst().equals("01") || co.getCst().equals("02") || co.getCst().equals("03")
					|| co.getCst().equals("04") || co.getCst().equals("05") || co.getCst().equals("06")
					|| co.getCst().equals("07") || co.getCst().equals("08") || co.getCst().equals("50")
					|| co.getCst().equals("51") || co.getCst().equals("52") || co.getCst().equals("53")
					|| co.getCst().equals("54") || co.getCst().equals("55") || co.getCst().equals("56")
					|| co.getCst().equals("60") || co.getCst().equals("61") || co.getCst().equals("62")
					|| co.getCst().equals("63") || co.getCst().equals("64") || co.getCst().equals("65")
					|| co.getCst().equals("66") || co.getCst().equals("67") || co.getCst().equals("70")
					|| co.getCst().equals("71") || co.getCst().equals("73") || co.getCst().equals("74")) {
				COFINSAliq cofal = new COFINSAliq();
				cofal.setCST(co.getCst());
				cofal.setVBC(MoedaUtil.moedaPadraoScale(co.getVbc(), 2));
				cofal.setPCOFINS(MoedaUtil.moedaPadraoScale(co.getPcofins(), 4));
				cofal.setVCOFINS(MoedaUtil.moedaPadraoScale(co.getVcofins(), 2));
				cof.setCOFINSAliq(cofal);
			}
			// 49 - 98 - 99--------------------------
			if (co.getCst().equals("49") || co.getCst().equals("98") || co.getCst().equals("99")) {
				COFINSOutr cofal = new COFINSOutr();
				cofal.setCST(co.getCst());
				cofal.setVBC(MoedaUtil.moedaPadraoScale(co.getVbc(), 2));
				cofal.setPCOFINS(MoedaUtil.moedaPadraoScale(co.getPcofins(), 4));
				cofal.setVCOFINS(MoedaUtil.moedaPadraoScale(co.getVcofins(), 2));
				cof.setCOFINSOutr(cofal);
			}
			// 09 e 72-------------------------------
			if (co.getCst().equals("09") || co.getCst().equals("72")) {
				COFINSNT cofal = new COFINSNT();
				cofal.setCST(co.getCst());
				cof.setCOFINSNT(cofal);
			}
			im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(cof));
			// ICMS UF DEST***********************************************
			if (ic.getVicmsdif().compareTo(BigDecimal.ZERO) > 0 || ic.getVfcpdif().compareTo(BigDecimal.ZERO) > 0) {
				ICMSUFDest ud = new ICMSUFDest();
				ud.setPFCPUFDest(MoedaUtil.moedaPadraoScale(ic.getPfcpdif(), 2));
				ud.setPICMSInter(MoedaUtil.moedaPadraoScale(lcDoc.getCdnfecfg().getPicmsinter(), 2));
				ud.setPICMSInterPart(MoedaUtil.moedaPadraoScale(lcDoc.getCdnfecfg().getPicmsinterpart(), 2));
				ud.setPICMSUFDest(MoedaUtil.moedaPadraoScale(ic.getPdif(), 2));
				ud.setVBCFCPUFDest(MoedaUtil.moedaPadraoScale(ic.getVbc(), 2));
				ud.setVBCUFDest(MoedaUtil.moedaPadraoScale(ic.getVbc(), 2));
				ud.setVFCPUFDest(MoedaUtil.moedaPadraoScale(ic.getVfcpdif(), 2));
				ud.setVICMSUFDest(MoedaUtil.moedaPadraoScale(ic.getVicmsdif(), 2));
				ud.setVICMSUFRemet(MoedaUtil.moedaPadraoScale(ic.getVicmsdifremet(), 2));
				im.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMSUFDest(ud));
			}
			det.setImposto(im);
			i.add(det);
			x++;
		}
		return i;
	}
}
