package com.midas.api.tenant.fiscal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.midas.api.constant.FsCteWebService;
import com.midas.api.constant.MidasConfig;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsCteAut;
import com.midas.api.tenant.entity.FsCteIcms;
import com.midas.api.tenant.entity.FsCteInf;
import com.midas.api.tenant.entity.FsCteInfCarga;
import com.midas.api.tenant.entity.FsCteInfDocEmi;
import com.midas.api.tenant.entity.FsCteInfDocEmiCte;
import com.midas.api.tenant.entity.FsCteInfNfe;
import com.midas.api.tenant.entity.FsCtePart;
import com.midas.api.tenant.entity.FsCteVprest;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.MoedaUtil;
import com.midas.api.util.NumUtil;

import br.inf.portalfiscal.cte.Rodo;
import br.inf.portalfiscal.cte.TCTe;
import br.inf.portalfiscal.cte.TCTe.InfCTeSupl;
import br.inf.portalfiscal.cte.TCTe.InfCte;
import br.inf.portalfiscal.cte.TCTe.InfCte.AutXML;
import br.inf.portalfiscal.cte.TCTe.InfCte.Compl;
import br.inf.portalfiscal.cte.TCTe.InfCte.Compl.ObsCont;
import br.inf.portalfiscal.cte.TCTe.InfCte.Compl.ObsFisco;
import br.inf.portalfiscal.cte.TCTe.InfCte.Dest;
import br.inf.portalfiscal.cte.TCTe.InfCte.Emit;
import br.inf.portalfiscal.cte.TCTe.InfCte.Exped;
import br.inf.portalfiscal.cte.TCTe.InfCte.Ide;
import br.inf.portalfiscal.cte.TCTe.InfCte.Ide.Toma3;
import br.inf.portalfiscal.cte.TCTe.InfCte.Ide.Toma4;
import br.inf.portalfiscal.cte.TCTe.InfCte.Imp;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCTeNorm;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCTeNorm.DocAnt;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCTeNorm.DocAnt.EmiDocAnt;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCTeNorm.DocAnt.EmiDocAnt.IdDocAnt;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCTeNorm.DocAnt.EmiDocAnt.IdDocAnt.IdDocAntEle;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCTeNorm.InfCarga;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCTeNorm.InfCarga.InfQ;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCTeNorm.InfDoc;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCTeNorm.InfDoc.InfNFe;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCTeNorm.InfDoc.InfOutros;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCTeNorm.InfModal;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCteAnu;
import br.inf.portalfiscal.cte.TCTe.InfCte.InfCteComp;
import br.inf.portalfiscal.cte.TCTe.InfCte.Receb;
import br.inf.portalfiscal.cte.TCTe.InfCte.Rem;
import br.inf.portalfiscal.cte.TCTe.InfCte.VPrest;
import br.inf.portalfiscal.cte.TEndeEmi;
import br.inf.portalfiscal.cte.TEndereco;
import br.inf.portalfiscal.cte.TEnviCTe;
import br.inf.portalfiscal.cte.TImp;
import br.inf.portalfiscal.cte.TImp.ICMS00;
import br.inf.portalfiscal.cte.TImp.ICMS20;
import br.inf.portalfiscal.cte.TImp.ICMS45;
import br.inf.portalfiscal.cte.TImp.ICMS60;
import br.inf.portalfiscal.cte.TImp.ICMS90;
import br.inf.portalfiscal.cte.TImp.ICMSSN;
import br.inf.portalfiscal.cte.TRespTec;
import br.inf.portalfiscal.cte.TUFSemEX;
import br.inf.portalfiscal.cte.TUf;

@Component
public class GeraXMLCTe {
	public String geraXMLCTe(FsCte cte, MidasConfig mc) throws Exception {
		// MONTANDO XML DA CTE
		TCTe tCte = new TCTe();
		InfCte infCte = new InfCte();
		infCte.setId("CTe" + cte.getChaveac());
		infCte.setVersao(FsCteWebService.versaoDados);
		infCte.setIde(ide(cte));
		infCte.setCompl(compl(cte));
		infCte.setEmit(emit(cte));
		infCte.setRem(rem(cte));
		infCte.setDest(dest(cte));
		if (!cte.getFsctepartexp().getCpfcnpj().equals(cte.getFsctepartemit().getCpfcnpj())) {
			infCte.setExped(exped(cte));
		}
		if (!cte.getFsctepartrec().getCpfcnpj().equals(cte.getFsctepartdest().getCpfcnpj())) {
			infCte.setReceb(receb(cte));
		}
		infCte.setVPrest(vPrest(cte.getFsctevprest()));
		infCte.setImp(imp(cte));
		// CTE NORMAL - SUBSTITUTO
		if (cte.getTpcte() == 0 || cte.getTpcte() == 3) {
			infCte.setInfCTeNorm(infCteNorm(cte.getFscteinf(), FsCteWebService.versaoDados));
		}
		// CTE COMPLEMENTO
		if (cte.getTpcte() == 1) {
			infCte.setInfCteComp(infCteComp(cte.getFscteinf().getChcte_comp()));
		}
		// CTE ANULA - SE NECESSARIO
		if (cte.getTpcte() == 2) {
			infCte.setInfCteAnu(infCteAnu(DataUtil.dataPadraoSQL(cte.getDhemi()), cte.getFscteinf().getChcte_anu()));
		}
		infCte.setInfRespTec(respTec(mc));
		infCte.getAutXML().addAll(autxml(cte));
		tCte.setInfCte(infCte);
		tCte.setInfCTeSupl(infCteSupl(cte));
		TEnviCTe enviCTe = new TEnviCTe();
		enviCTe.setIdLote("1");
		enviCTe.setVersao(FsCteWebService.versaoDados);
		enviCTe.getCTe().add(tCte);
		// CRIA XML
		String xml = FsUtil.xmlCTe(enviCTe);
		return xml;
	}

	// IDENTIFICACAO ****************************
	private static Ide ide(FsCte cte) throws Exception {
		String cUf = String.valueOf(cte.getCdpessoaemp().getCdestado().getId());
		Ide ide = new Ide();
		ide.setCUF(cUf);
		ide.setCCT(NumUtil.geraNumEsq(cte.getNct(), 8));
		ide.setCFOP(cte.getCfop());
		ide.setNatOp(cte.getNatop());
		ide.setMod(cte.getModelo().toString());
		ide.setSerie(cte.getSerie().toString());
		ide.setNCT(cte.getNct().toString());
		String dhemi = DataUtil.dataPadraoSQL(cte.getDhemi());
		String dhemihr = DataUtil.horaPadraoSQL(cte.getDhemihr());
		ide.setDhEmi(DataUtil.dtTim(dhemi, dhemihr));
		ide.setTpImp("1");// Retrato
		ide.setTpEmis(cte.getTpemis().toString());
		ide.setCDV(cte.getCdv().toString());
		ide.setTpAmb(cte.getTpamb().toString());
		ide.setTpCTe(cte.getTpcte().toString());
		ide.setProcEmi(cte.getProcemi().toString());
		ide.setVerProc(cte.getVerproc());
		ide.setCMunEnv(cte.getCmunenv());
		ide.setXMunEnv(cte.getXmunenv());
		ide.setUFEnv(TUf.valueOf(cte.getUfenv()));
		ide.setModal(cte.getModal());
		ide.setTpServ(cte.getTpserv().toString());
		ide.setCMunIni(cte.getCmunini());
		ide.setXMunIni(cte.getXmunini());
		ide.setUFIni(TUf.valueOf(cte.getUfini()));
		ide.setCMunFim(cte.getCmunfim());
		ide.setXMunFim(cte.getCmunfim());
		ide.setUFFim(TUf.valueOf(cte.getUffim()));
		ide.setRetira(cte.getRetira().toString());
		if (cte.getTomador() != 4) {
			Toma3 toma3 = new Toma3();
			toma3.setToma(cte.getTomador().toString());
			ide.setToma3(toma3);
			ide.setIndIEToma(verIE(cte.getTomador().toString(), cte, null, null));
		} else {
			FsCteInfDocEmi docant = cte.getFscteinf().getFscteinfdocemi().size() > 0
					? cte.getFscteinf().getFscteinfdocemi().get(0)
					: null;
			if (docant != null) {
				Toma4 toma4 = new Toma4();
				toma4.setToma(cte.getTomador().toString());
				String cpfcnpj = docant.getCpfcnpj();
				if (cpfcnpj.length() == 14) {
					toma4.setCNPJ(cpfcnpj);
				} else {
					toma4.setCPF(cpfcnpj);
				}
				String ie = FsUtil.cpND(docant.getIe(), "ISENTO");
				toma4.setIE(ie);
				toma4.setXNome(docant.getXnome());
				toma4.setXFant(docant.getXfant());
				toma4.setFone(docant.getFone());
				toma4.setEmail(docant.getEmail());
				TEndereco endereco = new TEndereco();
				endereco.setXLgr(docant.getXlgr());
				endereco.setNro(FsUtil.cpND(docant.getNro(), "SN"));
				endereco.setXCpl(FsUtil.cpND(docant.getXcpl(), "-"));
				endereco.setXBairro(docant.getXbairro());
				endereco.setCMun(docant.getCmun());
				endereco.setXMun(docant.getXmun());
				endereco.setCEP(docant.getCep());
				endereco.setUF(TUf.valueOf(docant.getUf()));
				endereco.setCPais(docant.getCpais());
				endereco.setXPais(docant.getXpais());
				toma4.setEnderToma(endereco);
				ide.setToma4(toma4);
				ide.setIndIEToma(verIE(cte.getTomador().toString(), cte, docant.getCpfcnpj(), docant.getIe()));
			}
		}
		return ide;
	}

	// COMPLEMENTO ******************************
	private static Compl compl(FsCte cte) {
		// INFO CONT
		Compl compl = new Compl();
		ObsCont obsCont = new ObsCont();
		obsCont.setXCampo("INFO");
		obsCont.setXTexto(FsUtil.cpND(cte.getInfcpl(), "-"));
		compl.getObsCont().add(obsCont);
		// INFO FISCO
		if (!cte.getInfadfisco().equals("")) {
			ObsFisco obsFisco = new ObsFisco();
			obsFisco.setXCampo("FISCO");
			obsFisco.setXTexto(cte.getInfadfisco());
			compl.getObsFisco().add(obsFisco);
		}
		return compl;
	}

	// EMITENTE *********************************
	private static Emit emit(FsCte cte) throws Exception {
		CdPessoa emp = cte.getCdpessoaemp();
		Emit emit = new Emit();
		emit.setCNPJ(emp.getCpfcnpj());
		emit.setIE(FsUtil.cpND(emp.getIerg(), "ISENTO"));
		emit.setXNome(emp.getNome());
		emit.setXFant(emp.getFantasia());
		// Formata para Ambiente 2 - homologacao
		if (cte.getTpamb().equals(2)) {
			emit.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
			emit.setXFant("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		}
		TEndeEmi end = new TEndeEmi();
		end.setXLgr(emp.getRua());
		end.setNro(FsUtil.cpND(emp.getNum(), "SN"));
		end.setXCpl(FsUtil.cpND(emp.getComp(), "-"));
		end.setXBairro(emp.getBairro());
		end.setCMun(emp.getCdcidade().getIbge());
		end.setXMun(emp.getCdcidade().getNome());
		end.setCEP(emp.getCep());
		end.setUF(TUFSemEX.valueOf(emp.getCdestado().getUf()));
		end.setFone(FsUtil.cpND(emp.getFonefat(), emp.getFoneum()));
		emit.setEnderEmit(end);
		return emit;
	}

	// REMETENTE ********************************
	private static Rem rem(FsCte cte) throws Exception {
		FsCtePart p = cte.getFsctepartrem();
		Rem rem = new Rem();
		String cpfcnpj = p.getCpfcnpj();
		if (cpfcnpj.length() == 14)
			rem.setCNPJ(p.getCpfcnpj());
		if (cpfcnpj.length() == 11)
			rem.setCPF(p.getCpfcnpj());
		rem.setIE(FsUtil.cpND(p.getIe(), "ISENTO"));
		rem.setXNome(p.getXnome());
		rem.setXFant(p.getXfant());
		// Formata para Ambiente 2 - homologacao
		if (cte.getTpamb().equals(2)) {
			rem.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
			rem.setXFant("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		}
		rem.setFone(p.getFone());
		rem.setEmail(FsUtil.cpND(p.getEmail(), null));
		TEndereco end = endPart(p);
		rem.setEnderReme(end);
		return rem;
	}

	// DESTINATARIO *****************************
	private static Dest dest(FsCte cte) throws Exception {
		FsCtePart p = cte.getFsctepartdest();
		Dest dest = new Dest();
		String cpfcnpj = p.getCpfcnpj();
		if (cpfcnpj.length() == 14)
			dest.setCNPJ(p.getCpfcnpj());
		if (cpfcnpj.length() == 11)
			dest.setCPF(p.getCpfcnpj());
		dest.setIE(FsUtil.cpND(p.getIe(), "ISENTO"));
		dest.setXNome(p.getXnome());
		// Formata para Ambiente 2 - homologacao
		if (cte.getTpamb().equals(2)) {
			dest.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		}
		dest.setFone(p.getFone());
		dest.setISUF(FsUtil.cpND(p.getIsuf(), null));
		dest.setEmail(FsUtil.cpND(p.getEmail(), null));
		TEndereco end = endPart(p);
		dest.setEnderDest(end);
		return dest;
	}

	// EXPEDIDOR ********************************
	private static Exped exped(FsCte cte) throws Exception {
		FsCtePart p = cte.getFsctepartexp();
		Exped exped = new Exped();
		String cpfcnpj = p.getCpfcnpj();
		if (cpfcnpj.length() == 14)
			exped.setCNPJ(p.getCpfcnpj());
		if (cpfcnpj.length() == 11)
			exped.setCPF(p.getCpfcnpj());
		exped.setIE(FsUtil.cpND(p.getIe(), "ISENTO"));
		exped.setXNome(p.getXnome());
		// Formata para Ambiente 2 - homologacao
		if (cte.getTpamb().equals(2)) {
			exped.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		}
		exped.setFone(p.getFone());
		exped.setEmail(FsUtil.cpND(p.getEmail(), null));
		TEndereco end = endPart(p);
		exped.setEnderExped(end);
		return exped;
	}

	// RECEBEDOR ********************************
	private static Receb receb(FsCte cte) throws Exception {
		FsCtePart p = cte.getFsctepartrec();
		Receb receb = new Receb();
		String cpfcnpj = p.getCpfcnpj();
		if (cpfcnpj.length() == 14)
			receb.setCNPJ(p.getCpfcnpj());
		if (cpfcnpj.length() == 11)
			receb.setCPF(p.getCpfcnpj());
		receb.setIE(FsUtil.cpND(p.getIe(), "ISENTO"));
		receb.setXNome(p.getXnome());
		// Formata para Ambiente 2 - homologacao
		if (cte.getTpamb().equals(2)) {
			receb.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		}
		receb.setFone(p.getFone());
		receb.setEmail(FsUtil.cpND(p.getEmail(), null));
		TEndereco end = endPart(p);
		receb.setEnderReceb(end);
		return receb;
	}

	// VALOR PRESTACAO **************************
	private static VPrest vPrest(FsCteVprest ctevp) throws Exception {
		VPrest vprest = new VPrest();
		vprest.setVTPrest(ctevp.getVtprest().toString());
		vprest.setVRec(ctevp.getVrec().toString());
		return vprest;
	}

	// IMPOSTOS *********************************
	private static Imp imp(FsCte cte) throws Exception {
		String cst = cte.getCdctecfg().getCst();
		FsCteIcms cteicms = cte.getFscteicms();
		Imp imp = new Imp();
		TImp timp = new TImp();
		imp.setVTotTrib(MoedaUtil.moedaPadraoScale(cteicms.getVibpt(), 2));
		if (cst.equals("0")) {
			ICMSSN i = new ICMSSN();
			i.setIndSN("1");
			i.setCST("90");
			timp.setICMSSN(i);
		} else if (cst.equals("00")) {
			ICMS00 icms00 = new ICMS00();
			icms00.setCST(cst);
			icms00.setPICMS(MoedaUtil.moedaPadraoScale(cteicms.getPicms(), 2));
			icms00.setVBC(MoedaUtil.moedaPadraoScale(cteicms.getVbc(), 2));
			icms00.setVICMS(MoedaUtil.moedaPadraoScale(cteicms.getVicms(), 2));
			timp.setICMS00(icms00);
		} else if (cst.equals("20")) {
			ICMS20 icms20 = new ICMS20();
			icms20.setCST(cst);
			icms20.setPICMS(MoedaUtil.moedaPadraoScale(cteicms.getPicms(), 2));
			if (cteicms.getPredbc().compareTo(new BigDecimal(0)) > 0) {
				icms20.setPRedBC(MoedaUtil.moedaPadraoScale(cteicms.getPredbc(), 2));
			}
			icms20.setVBC(MoedaUtil.moedaPadraoScale(cteicms.getVbc(), 2));
			icms20.setVICMS(MoedaUtil.moedaPadraoScale(cteicms.getVicms(), 2));
			timp.setICMS20(icms20);
		} else if (cst.equals("40") || cst.equals("41") || cst.equals("51")) {
			ICMS45 icms45 = new ICMS45();
			icms45.setCST(cst);
			timp.setICMS45(icms45);
		} else if (cst.equals("60")) {
			ICMS60 icms60 = new ICMS60();
			icms60.setCST(cst);
			icms60.setPICMSSTRet(MoedaUtil.moedaPadraoScale(cteicms.getPicmsstret(), 2));
			icms60.setVBCSTRet(MoedaUtil.moedaPadraoScale(cteicms.getVbcstret(), 2));
			icms60.setVICMSSTRet(MoedaUtil.moedaPadraoScale(cteicms.getVicmsstret(), 2));
			timp.setICMS60(icms60);
		} else if (cst.equals("90")) {
			ICMS90 icms90 = new ICMS90();
			icms90.setCST(cst);
			icms90.setPICMS(MoedaUtil.moedaPadraoScale(cteicms.getPicms(), 2));
			if (cteicms.getPredbc().compareTo(new BigDecimal(0)) > 0) {
				icms90.setPRedBC(MoedaUtil.moedaPadraoScale(cteicms.getPredbc(), 2));
			}
			icms90.setVBC(MoedaUtil.moedaPadraoScale(cteicms.getVbc(), 2));
			icms90.setVICMS(MoedaUtil.moedaPadraoScale(cteicms.getVicms(), 2));
			timp.setICMS90(icms90);
		}
		imp.setICMS(timp);
		return imp;
	}

	// CTE NORMAL - SUBSTITUTO ******************
	private static InfCTeNorm infCteNorm(FsCteInf cteinf, String versao) throws Exception {
		InfCTeNorm infCteNorm = new InfCTeNorm();
		// Carga
		InfCarga infCarga = new InfCarga();
		infCarga.setVCarga(MoedaUtil.moedaPadraoScale(cteinf.getVcarga(), 2));
		infCarga.setProPred(cteinf.getProdpred());
		List<FsCteInfCarga> cargas = cteinf.getFscteinfcarga();
		for (FsCteInfCarga c : cargas) {
			InfQ infQ = new InfQ();
			infQ.setCUnid(c.getCunid());
			infQ.setTpMed(c.getTpmed());
			infQ.setQCarga(MoedaUtil.moedaPadraoScale(c.getQcarga(), 4));
			infCarga.getInfQ().add(infQ);
		}
		infCteNorm.setInfCarga(infCarga);
		// Modal rodoviario
		InfModal infModal = new InfModal();
		infModal.setVersaoModal(versao);
		Rodo rodo = new Rodo();
		rodo.setRNTRC(cteinf.getRntrc());
		infModal.setRodo(rodo);
		infCteNorm.setInfModal(infModal);
		// DOCS - Verifica se possui NFs
		InfDoc infDoc = new InfDoc();
		if (cteinf.getFscteinfnfe().size() > 0) {
			// NF
			for (FsCteInfNfe n : cteinf.getFscteinfnfe()) {
				InfNFe nf = new InfNFe();
				nf.setChave(n.getChave());
				infDoc.getInfNFe().add(nf);
			}
		} else {
			// SEM NF
			InfOutros ou = new InfOutros();
			ou.setTpDoc("99");
			infDoc.getInfOutros().add(ou);
		}
		infCteNorm.setInfDoc(infDoc);
		// Substituto
		FsCteInfDocEmi docEmi = cteinf.getFscteinfdocemi().size() > 0 ? cteinf.getFscteinfdocemi().get(0) : null;
		if (docEmi != null) {
			EmiDocAnt emiDocAnt = new EmiDocAnt();
			emiDocAnt.setXNome(docEmi.getXnome());
			String cpfcnpj = docEmi.getCpfcnpj();
			if (cpfcnpj.length() == 14) {
				emiDocAnt.setCNPJ(cpfcnpj);
			}
			if (cpfcnpj.length() == 11) {
				emiDocAnt.setCPF(cpfcnpj);
			}
			emiDocAnt.setIE(FsUtil.cpND(docEmi.getIe(), null));
			emiDocAnt.setUF(TUf.valueOf(docEmi.getUf()));
			DocAnt docAnt = new DocAnt();
			docAnt.getEmiDocAnt().add(emiDocAnt);
			for (FsCteInfDocEmiCte docEmiCte : docEmi.getFscteinfdocemicte()) {
				IdDocAnt idDocAnt = new IdDocAnt();
				IdDocAntEle idDocAntEle = new IdDocAntEle();
				idDocAntEle.setChCTe(docEmiCte.getChave());
				idDocAnt.getIdDocAntEle().add(idDocAntEle);
				emiDocAnt.getIdDocAnt().add(idDocAnt);
			}
			infCteNorm.setDocAnt(docAnt);
		}
		// TODO criar aba de substituto com Chave cte, indica altera toma e chave da nfe
		// - PODE SER Q NAO PRECISE MAIS
		// de anulacao de valores
		// Substituto
		/*
		 * InfCteSub infCteSub = new InfCteSub();
		 * infCteSub.setChCte("42230408881137000104570010000010051000010059");
		 * infCteSub.setIndAlteraToma("1"); TomaICMS tic = new TomaICMS();
		 * tic.setRefNFe("42230483852137000100550010000023951288231024");
		 * infCteSub.setTomaICMS(tic); infCteNorm.setInfCteSub(infCteSub);
		 */
		return infCteNorm;
	}

	// CTE COMPLEMENTO ***************************
	private static InfCteComp infCteComp(String chcte) {
		InfCteComp infCteComp = new InfCteComp();
		infCteComp.setChCTe(chcte);
		return infCteComp;
	}

	// CTE ANULA ********************************
	private static InfCteAnu infCteAnu(String daf, String chcte) {
		InfCteAnu infCteAnu = new InfCteAnu();
		infCteAnu.setChCte(chcte);
		infCteAnu.setDEmi(daf);
		return infCteAnu;
	}

	// RESP. TECNICO *********************
	private static TRespTec respTec(MidasConfig mc) throws Exception {
		String cnpj = mc.MidasCNPJ;
		String nome = mc.MidasApresenta;
		String fone = mc.MidasFone;
		String email = mc.NrEmail;
		TRespTec rt = new TRespTec();
		rt.setCNPJ(cnpj);
		rt.setXContato(nome);
		rt.setEmail(email);
		rt.setFone(fone);
		// e.setIdCSRT("");
		// e.setHashCSRT(null);
		return rt;
	}

	// XML AUTORIZADOS **************************
	private static List<AutXML> autxml(FsCte cte) throws Exception {
		List<FsCteAut> autos = cte.getFscteauts();
		List<AutXML> i = new ArrayList<AutXML>();
		for (FsCteAut x : autos) {
			AutXML a = new AutXML();
			if (x.getCpfcnpj().length() == 14) {
				a.setCNPJ(x.getCpfcnpj());
			}
			if (x.getCpfcnpj().length() == 11) {
				a.setCPF(x.getCpfcnpj());
			}
			i.add(a);
		}
		return i;
	}

	// INF SUPL. ********************************
	private static InfCTeSupl infCteSupl(FsCte cte) {
		InfCTeSupl infCteSupl = new InfCTeSupl();
		String chave = cte.getChaveac().replace("CTe", "");
		String url = FsCteWebService.CTeQrCode;
		String ret = url + "?chCTe=" + chave + "&tpAmb=" + cte.getTpamb();
		infCteSupl.setQrCodCTe(ret);
		return infCteSupl;
	}

	// FUNCOES EXTRAS
	// #############################################################################
	//
	// ENDERECO PARTICIPANTES *******************
	private static TEndereco endPart(FsCtePart p) {
		TEndereco end = new TEndereco();
		end.setXLgr(p.getXlgr());
		end.setNro(FsUtil.cpND(p.getNro(), "SN"));
		end.setXCpl(FsUtil.cpND(p.getXcpl(), "-"));
		end.setXBairro(p.getXbairro());
		end.setCMun(p.getCmun());
		end.setXMun(p.getXmun());
		end.setCEP(p.getCep());
		end.setUF(TUf.valueOf(p.getUf()));
		end.setCPais(p.getCpais());
		end.setXPais(p.getXpais());
		return end;
	}

	// VERIFICA TOMADOR *************************
	private static String verIE(String num, FsCte cte, String cpfcnpj4, String ierg4) {
		String retorno = "1";
		FsCtePart part = null;
		if (num.equals("0"))
			part = cte.getFsctepartrem();
		if (num.equals("1"))
			part = cte.getFsctepartexp();
		if (num.equals("2"))
			part = cte.getFsctepartrec();
		if (num.equals("3"))
			part = cte.getFsctepartdest();
		if (!num.equals("4")) {
			String cpfcnpj = part.getCpfcnpj();
			String ie = part.getIe();
			if (cpfcnpj.length() == 14) {
				if (ie == null || ie.equals("")) {
					retorno = "2";
				}
			}
			if (cpfcnpj.length() == 11) {
				retorno = "9";
			}
		} else {
			// OUTROS - DOCUMENTO ANTERIOR
			if (cpfcnpj4.length() == 14) {
				if (ierg4 == null || ierg4.equals("")) {
					retorno = "2";
				}
			}
			if (cpfcnpj4.length() == 11) {
				retorno = "9";
			}
		}
		return retorno;
	}
}