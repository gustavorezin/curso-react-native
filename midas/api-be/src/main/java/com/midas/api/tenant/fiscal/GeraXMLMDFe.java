package com.midas.api.tenant.fiscal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.midas.api.constant.FsMdfeWebService;
import com.midas.api.constant.MidasConfig;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdVeiculo;
import com.midas.api.tenant.entity.FsMdfe;
import com.midas.api.tenant.entity.FsMdfeAut;
import com.midas.api.tenant.entity.FsMdfeAverb;
import com.midas.api.tenant.entity.FsMdfeContr;
import com.midas.api.tenant.entity.FsMdfeDoc;
import com.midas.api.tenant.entity.FsMdfePag;
import com.midas.api.tenant.entity.FsMdfePagComp;
import com.midas.api.tenant.entity.FsMdfePagPrazo;
import com.midas.api.tenant.entity.FsMdfePart;
import com.midas.api.tenant.entity.FsMdfePerc;
import com.midas.api.tenant.entity.FsMdfeReboq;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.NumUtil;

import br.inf.portalfiscal.mdfe.Rodo;
import br.inf.portalfiscal.mdfe.Rodo.InfANTT;
import br.inf.portalfiscal.mdfe.Rodo.InfANTT.InfCIOT;
import br.inf.portalfiscal.mdfe.Rodo.InfANTT.InfContratante;
import br.inf.portalfiscal.mdfe.Rodo.InfANTT.InfPag;
import br.inf.portalfiscal.mdfe.Rodo.InfANTT.InfPag.Comp;
import br.inf.portalfiscal.mdfe.Rodo.InfANTT.InfPag.InfBanc;
import br.inf.portalfiscal.mdfe.Rodo.InfANTT.InfPag.InfPrazo;
import br.inf.portalfiscal.mdfe.Rodo.VeicReboque;
import br.inf.portalfiscal.mdfe.Rodo.VeicTracao;
import br.inf.portalfiscal.mdfe.Rodo.VeicTracao.Condutor;
import br.inf.portalfiscal.mdfe.Rodo.VeicTracao.Prop;
import br.inf.portalfiscal.mdfe.TEndeEmi;
import br.inf.portalfiscal.mdfe.TEnviMDFe;
import br.inf.portalfiscal.mdfe.TMDFe;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.AutXML;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.Emit;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.Ide;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.Ide.InfMunCarrega;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.Ide.InfPercurso;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.InfAdic;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.InfDoc;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.InfDoc.InfMunDescarga;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfNFe;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.InfModal;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.ProdPred;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.ProdPred.InfLotacao;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalCarrega;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalDescarrega;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.Seg;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.Seg.InfResp;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.Seg.InfSeg;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFe.Tot;
import br.inf.portalfiscal.mdfe.TMDFe.InfMDFeSupl;
import br.inf.portalfiscal.mdfe.TRespTec;
import br.inf.portalfiscal.mdfe.TUf;

@Component
public class GeraXMLMDFe {
	public String geraXMLMDFe(FsMdfe mdfe, MidasConfig mc, List<FsMdfeDoc> cidades) throws Exception {
		// MONTANDO XML DA NFE
		TMDFe mdf = new TMDFe();
		InfMDFe infMdfe = new InfMDFe();
		infMdfe.setId("MDFe" + mdfe.getChaveac());
		infMdfe.setVersao(FsMdfeWebService.versaoDados);
		infMdfe.setIde(ide(mdfe));
		infMdfe.setEmit(emit(mdfe));
		infMdfe.setInfModal(modal(mdfe));
		infMdfe.setInfDoc(docs(mdfe, cidades));
		if (mdfe.getTpemit() == 1 && mdfe.getCdpessoaemp() != mdfe.getCdpessoaseg()) {
			infMdfe.getSeg().add(seg(mdfe));
		}
		infMdfe.setProdPred(prodPred(mdfe));
		infMdfe.setInfRespTec(respTec(mc));
		infMdfe.setTot(tot(mdfe));
		infMdfe.getAutXML().addAll(autxml(mdfe));
		infMdfe.setInfAdic(infadc(mdfe));
		mdf.setInfMDFe(infMdfe);
		mdf.setInfMDFeSupl(infSup(mdfe));
		TEnviMDFe enviMDFe = new TEnviMDFe();
		enviMDFe.setVersao(FsMdfeWebService.versaoDados);
		enviMDFe.setIdLote("0000001");
		enviMDFe.setMDFe(mdf);
		// CRIA XML
		String xml = FsUtil.xmlMDFe(enviMDFe);
		return xml;
	}

	// IDENTIFICACAO*********************
	private static Ide ide(FsMdfe mdfe) throws Exception {
		Ide ide = new Ide();
		ide.setCUF(mdfe.getCuf().toString());
		ide.setTpAmb(mdfe.getTpamb().toString());
		ide.setTpEmit(mdfe.getTpemit().toString());
		// TP TRANSP - PROP.
		CdVeiculo veicPropVT = mdfe.getCdveiculo();
		// Se veiculo nao for do emitente
		if (!mdfe.getCdpessoaemp().getCpfcnpj().equals(veicPropVT.getPcpfcnpj())) {
			ide.setTpTransp(mdfe.getTptransp().toString());
		}
		ide.setMod(mdfe.getModelo().toString());
		ide.setSerie(mdfe.getSerie().toString());
		ide.setNMDF(mdfe.getNmdf().toString());
		ide.setCMDF(NumUtil.geraNumEsq(mdfe.getCmdf(), 8));
		ide.setCDV(mdfe.getCdv().toString());
		ide.setModal(mdfe.getModal());
		String dhemi = DataUtil.dataPadraoSQL(mdfe.getDhemi());
		String dhemihr = DataUtil.horaPadraoSQL(mdfe.getDhemihr());
		ide.setDhEmi(DataUtil.dtTim(dhemi, dhemihr));
		ide.setTpEmis(mdfe.getTpemis().toString());
		ide.setProcEmi(mdfe.getProcemi().toString());
		ide.setVerProc(mdfe.getVerproc());
		ide.setUFIni(TUf.valueOf(mdfe.getCdestadoini().getUf()));
		ide.setUFFim(TUf.valueOf(mdfe.getCdestadofim().getUf()));
		// Grupo municipio de carregamento
		InfMunCarrega infMunCarrega = new InfMunCarrega();
		infMunCarrega.setCMunCarrega(mdfe.getCdcidadeini().getIbge());
		infMunCarrega.setXMunCarrega(mdfe.getCdcidadeini().getNome());
		ide.getInfMunCarrega().add(infMunCarrega);
		// Percurso
		for (FsMdfePerc p : mdfe.getFsmdfepercitems()) {
			InfPercurso infPercurso = new InfPercurso();
			infPercurso.setUFPer(TUf.valueOf(p.getCdestado().getUf()));
			ide.getInfPercurso().add(infPercurso);
		}
		return ide;
	}

	// EMITENTE**********************
	private static Emit emit(FsMdfe mdfe) throws Exception {
		CdPessoa em = mdfe.getCdpessoaemp();
		Emit emit = new Emit();
		emit.setCNPJ(em.getCpfcnpj());
		emit.setIE(FsUtil.cpND(em.getIerg(), "000000000"));
		emit.setXNome(em.getNome());
		emit.setXFant(FsUtil.cpND(em.getFantasia(), em.getNome()));
		// Formata para Ambiente 2 - homologacao
		if (mdfe.getTpamb().equals(2)) {
			emit.setXNome("MDF-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
			emit.setXFant("MDF-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		}
		TEndeEmi endeEmi = new TEndeEmi();
		endeEmi.setXLgr(em.getRua());
		endeEmi.setNro(FsUtil.cpND(em.getNum(), "SN"));
		endeEmi.setXCpl(FsUtil.cpND(em.getComp(), "-"));
		endeEmi.setXBairro(em.getBairro());
		endeEmi.setCMun(em.getCdcidade().getIbge());
		endeEmi.setXMun(em.getCdcidade().getNome());
		endeEmi.setCEP(em.getCep());
		endeEmi.setUF(TUf.valueOf(em.getCdestado().getUf()));
		endeEmi.setFone(FsUtil.cpND(em.getFonefat(), em.getFoneum()));
		emit.setEnderEmit(endeEmi);
		return emit;
	}

	// MODAL***********************
	private static InfModal modal(FsMdfe mdfe) throws Exception {
		InfModal infModal = new InfModal();
		infModal.setVersaoModal("3.00");
		Rodo rodo = new Rodo();
		// ANTT
		InfANTT infAntt = new InfANTT();
		infAntt.setRNTRC(mdfe.getV_antt());
		// ANTT > CIOT
		String cpfcnpj = mdfe.getCiot_cpfcnpj();
		if (cpfcnpj != null) {
			InfCIOT infCiot = new InfCIOT();
			infCiot.setCIOT(mdfe.getCiot());
			infCiot.setCPF(cpfcnpj);
			if (cpfcnpj.length() > 11) {
				infCiot.setCPF("");
				infCiot.setCNPJ(cpfcnpj);
			}
			infAntt.getInfCIOT().add(infCiot);
		}
		// ANTT > CONTRATANTES
		List<FsMdfeContr> contratantes = mdfe.getFsmdfecontritems();
		if (contratantes != null) {
			for (FsMdfeContr c : contratantes) {
				InfContratante infContr = new InfContratante();
				String cpfcnpjContr = c.getCpfcnpj();
				infContr.setCPF(cpfcnpjContr);
				if (cpfcnpjContr.length() > 11) {
					infContr.setCPF(null);
					infContr.setCNPJ(cpfcnpjContr);
				}
				infContr.setXNome(c.getNome());
				// infContr.setIdEstrangeiro(c.getIdest());
				infAntt.getInfContratante().add(infContr);
			}
		}
		// ANTT > PAGAMENTO FRETE
		List<FsMdfePag> pagamentos = mdfe.getFsmdfepagitems();
		if (pagamentos != null) {
			for (FsMdfePag p : pagamentos) {
				InfPag infPag = new InfPag();
				infPag.setXNome(p.getNome());
				String cpfcnpjPag = p.getCpfcnpj();
				infPag.setCPF(cpfcnpjPag);
				if (cpfcnpjPag.length() > 11) {
					infPag.setCPF(null);
					infPag.setCNPJ(cpfcnpjPag);
				}
				// infPag.setIdEstrangeiro(p.getIdest());
				infPag.setVContrato(p.getVcontrato().toString());
				infPag.setIndPag(p.getIndpag().toString());
				infPag.setVAdiant(p.getVadiant().toString());
				;
				InfBanc infBanc = new InfBanc();
				String pix = p.getPix();
				if (pix != null && !pix.equals("")) {
					infBanc.setPIX(p.getPix());
				} else {
					infBanc.setCodBanco(p.getBanco());
					infBanc.setCodAgencia(p.getAgencia());
				}
				infPag.setInfBanc(infBanc);
				List<FsMdfePagComp> componentes = p.getFsmdfepagcompitems();
				if (componentes != null) {
					for (FsMdfePagComp c : componentes) {
						Comp comp = new Comp();
						comp.setTpComp(c.getTpcomp());
						comp.setVComp(c.getVcomp().toString());
						comp.setXComp(c.getXcomp());
						infPag.getComp().add(comp);
					}
				}
				List<FsMdfePagPrazo> prazos = p.getFsmdfepagprazoitems();
				if (prazos != null) {
					for (FsMdfePagPrazo prazo : prazos) {
						InfPrazo infPrazo = new InfPrazo();
						infPrazo.setNParcela(prazo.getNparcela());
						infPrazo.setDVenc(prazo.getDvenc().toString());
						infPrazo.setVParcela(prazo.getVparcela().toString());
						infPag.getInfPrazo().add(infPrazo);
					}
				}
				infAntt.getInfPag().add(infPag);
			}
		}
		rodo.setInfANTT(infAntt);
		// VEIC. TRACAO
		VeicTracao veicTracao = new VeicTracao();
		veicTracao.setPlaca(mdfe.getV_placa());
		veicTracao.setTara(mdfe.getV_tara());
		veicTracao.setCapKG(mdfe.getCdveiculo().getCapkg());
		veicTracao.setCapM3(mdfe.getCdveiculo().getCapmc());
		veicTracao.setTpRod(mdfe.getCdveiculo().getTprod());
		veicTracao.setTpCar(mdfe.getCdveiculo().getTpcar());
		veicTracao.setUF(TUf.valueOf(mdfe.getV_ufplaca()));
		// VEIC. TRACAO > CONDUTOR
		for (FsMdfePart p : mdfe.getFsmdfepartitems()) {
			if (p.getTipo().equals("M")) {
				Condutor condutor = new Condutor();
				condutor.setXNome(p.getXnome());
				condutor.setCPF(p.getCpfcnpj());
				veicTracao.getCondutor().add(condutor);
			}
		}
		// VEIC. TRACAO > PROPRIETARIO
		CdVeiculo veicPropVT = mdfe.getCdveiculo();
		if (!mdfe.getCdpessoaemp().getCpfcnpj().equals(veicPropVT.getPcpfcnpj())) { // Se veiculo nao for do emitente
			Prop prop = new Prop();
			String cpfcnpjPropVT = veicPropVT.getPcpfcnpj();
			if (cpfcnpjPropVT.length() > 11) {
				prop.setCPF(null);
				prop.setCNPJ(cpfcnpjPropVT);
			}
			prop.setRNTRC(veicPropVT.getAntt());
			prop.setXNome(veicPropVT.getPnome());
			prop.setIE("");
			if (veicPropVT.getPie() != null) {
				if (!veicPropVT.getPie().equals("")) {
					prop.setIE(veicPropVT.getPie());
				}
			}
			prop.setUF(TUf.valueOf(veicPropVT.getPuf()));
			prop.setTpProp(veicPropVT.getPtpprop());
			veicTracao.setProp(prop);
		}
		rodo.setVeicTracao(veicTracao);
		// VEIC. REBOQ.
		for (FsMdfeReboq r : mdfe.getFsmdfereboqitems()) {
			VeicReboque veicReboque = new VeicReboque();
			veicReboque.setPlaca(r.getPlaca());
			veicReboque.setTara(r.getCdveiculo().getTara());
			veicReboque.setCapKG(r.getCdveiculo().getCapkg());
			veicReboque.setCapM3(r.getCdveiculo().getCapmc());
			veicReboque.setTpCar(r.getCdveiculo().getTpcar());
			veicReboque.setUF(TUf.valueOf(r.getUfplaca()));
			// VEIC. REBOQ. > PROPRIETARIO
			CdVeiculo veicPropRbq = r.getCdveiculo();
			if (!mdfe.getCdpessoaemp().getCpfcnpj().equals(veicPropRbq.getPcpfcnpj())) { // Se veiculo nao for do
																							// emitente
				VeicReboque.Prop prop = new VeicReboque.Prop();
				String cpfcnpjPropRbq = veicPropRbq.getPcpfcnpj();
				prop.setCPF(cpfcnpjPropRbq);
				if (cpfcnpjPropRbq.length() > 11) {
					prop.setCPF(null);
					prop.setCNPJ(cpfcnpjPropRbq);
				}
				prop.setRNTRC(veicPropRbq.getAntt());
				prop.setXNome(veicPropRbq.getPnome());
				prop.setIE("");
				if (veicPropRbq.getPie() != null) {
					if (!veicPropRbq.getPie().equals("")) {
						prop.setIE(veicPropRbq.getPie());
					}
				}
				prop.setUF(TUf.valueOf(veicPropRbq.getPuf()));
				prop.setTpProp(veicPropRbq.getPtpprop());
				veicReboque.setProp(prop);
			}
			rodo.getVeicReboque().add(veicReboque);
		}
		infModal.setRodo(rodo);
		return infModal;
	}

	// DOCUMENTOS VINCULADOS*********************
	private static InfDoc docs(FsMdfe mdfe, List<FsMdfeDoc> cidades) throws Exception {
		InfDoc infDoc = new InfDoc();
		for (FsMdfeDoc d : cidades) {
			InfMunDescarga infMunDescarga = new InfMunDescarga();
			infMunDescarga.setCMunDescarga(d.getCdcidade().getIbge());
			infMunDescarga.setXMunDescarga(d.getCdcidade().getNome());
			infDoc.getInfMunDescarga().add(infMunDescarga);
			// NAO REPETE MUNICIPIO, APENAS NOTA FISCAL
			for (FsMdfeDoc d1 : mdfe.getFsmdfedocitems()) {
				if (d.getCdcidade() == d1.getCdcidade()) {
					if (d1.getTipo().equals("N")) {
						InfNFe infNfe = new InfNFe();
						infNfe.setChNFe(d1.getChave());
						infMunDescarga.getInfNFe().add(infNfe);
					} else {
						InfCTe infCte = new InfCTe();
						infCte.setChCTe(d1.getChave());
						infMunDescarga.getInfCTe().add(infCte);
					}
				}
			}
		}
		return infDoc;
	}

	// SEGURO************************************
	private static Seg seg(FsMdfe mdfe) throws Exception {
		Seg seg = new Seg();
		InfResp infResp = new InfResp();
		Integer tpResp = mdfe.getRespseg();
		String cpfcnpjResp = mdfe.getCdpessoaemp().getCpfcnpj();
		if (tpResp == 2 && mdfe.getFsmdfecontritems() != null) {
			FsMdfeContr contratante = mdfe.getFsmdfecontritems().get(0);
			cpfcnpjResp = contratante.getCpfcnpj();
		}
		if (cpfcnpjResp.length() > 11) {
			infResp.setCNPJ(cpfcnpjResp);
		} else {
			infResp.setCPF(cpfcnpjResp);
		}
		infResp.setRespSeg(tpResp.toString());
		seg.setInfResp(infResp);
		InfSeg infSeg = new InfSeg();
		String xSeg = mdfe.getCdpessoaseg().getNome();
		if (xSeg.length() > 30) {
			xSeg = xSeg.substring(0, 29);
		}
		infSeg.setXSeg(xSeg);
		infSeg.setCNPJ(mdfe.getCdpessoaseg().getCpfcnpj());
		seg.setInfSeg(infSeg);
		seg.setNApol(mdfe.getCdpessoaseg().getNumapol());
		List<FsMdfeAverb> averbacoes = mdfe.getFsmdfeaverbitems();
		if (averbacoes != null) {
			for (FsMdfeAverb a : averbacoes) {
				seg.getNAver().add(a.getNumaverb());
			}
		}
		return seg;
	}

	// PRODUTO PREDOMINANTE**********************
	private static ProdPred prodPred(FsMdfe mdfe) throws Exception {
		ProdPred prodPred = new ProdPred();
		prodPred.setTpCarga(mdfe.getPp_tipo());
		prodPred.setXProd(mdfe.getPp_nome());
		prodPred.setNCM(mdfe.getPp_ncm());
		prodPred.setCEAN(mdfe.getPp_cean());
		if (mdfe.getPp_lotacao() == 1) {
			InfLotacao infLotacao = new InfLotacao();
			InfLocalCarrega infLocalCarrega = new InfLocalCarrega();
			infLocalCarrega.setCEP(mdfe.getPp_cepcar());
			infLotacao.setInfLocalCarrega(infLocalCarrega);
			InfLocalDescarrega infLocalDescarrega = new InfLocalDescarrega();
			infLocalDescarrega.setCEP(mdfe.getPp_cepdesc());
			infLotacao.setInfLocalDescarrega(infLocalDescarrega);
			prodPred.setInfLotacao(infLotacao);
		}
		return prodPred;
	}

	// TOTALIZADORES*********************
	private static Tot tot(FsMdfe mdfe) throws Exception {
		Tot tot = new Tot();
		tot.setQNFe(mdfe.getQnfe().toString());
		if (mdfe.getQcte() > 0) {
			tot.setQNFe(null);
			tot.setQCTe(mdfe.getQcte().toString());
		}
		tot.setVCarga(mdfe.getVcarga().toString());
		tot.setCUnid("01");
		tot.setQCarga(mdfe.getQcarga().toString());
		return tot;
	}

	// XML AUTORIZADOS*********************
	private static List<AutXML> autxml(FsMdfe mdfe) throws Exception {
		List<FsMdfeAut> autos = mdfe.getFsmdfeautitems();
		List<AutXML> i = new ArrayList<AutXML>();
		for (FsMdfeAut x : autos) {
			AutXML autXML = new AutXML();
			if (x.getCpfcnpj().length() == 14) {
				autXML.setCNPJ(x.getCpfcnpj());
			}
			if (x.getCpfcnpj().length() == 11) {
				autXML.setCPF(x.getCpfcnpj());
			}
			i.add(autXML);
		}
		return i;
	}

	// INFOS ADICIONAIS********************
	private static InfAdic infadc(FsMdfe mdfe) throws Exception {
		InfAdic infAdic = new InfAdic();
		infAdic.setInfAdFisco(mdfe.getInfadfisco().equals("") ? null : mdfe.getInfadfisco());
		infAdic.setInfCpl(mdfe.getInfcpl().equals("") ? null : mdfe.getInfcpl());
		return infAdic;
	}

	// RESP. TECNICO*********************
	private static TRespTec respTec(MidasConfig mc) throws Exception {
		String cnpj = mc.MidasCNPJ;
		String nome = mc.MidasApresenta;
		String fone = mc.MidasFone;
		String email = mc.NrEmail;
		TRespTec respTec = new TRespTec();
		respTec.setCNPJ(cnpj);
		respTec.setXContato(nome);
		respTec.setEmail(email);
		respTec.setFone(fone);
		// e.setIdCSRT("");
		// e.setHashCSRT(null);
		return respTec;
	}

	// INF SUPL.
	private static InfMDFeSupl infSup(FsMdfe mdfe) {
		InfMDFeSupl infMdfeSupl = new InfMDFeSupl();
		String chave = mdfe.getChaveac().replace("MDFe", "");
		String amb = mdfe.getTpamb().toString();
		String url = FsMdfeWebService.MDFeQrCode;
		if (amb.equals("2")) {
			url = FsMdfeWebService.MDFeQrCodeHom;
		}
		String ret = url + "?chMDFe=" + chave + "&tpAmb=" + amb;
		infMdfeSupl.setQrCodMDFe(ret);
		return infMdfeSupl;
	}
}