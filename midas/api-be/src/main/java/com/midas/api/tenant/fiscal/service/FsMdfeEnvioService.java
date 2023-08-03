package com.midas.api.tenant.fiscal.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.constant.FsMdfeWebService;
import com.midas.api.tenant.entity.CdCert;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsMdfe;
import com.midas.api.tenant.fiscal.GeraMDFeEventoProc;
import com.midas.api.tenant.fiscal.GeraMDFeProc;
import com.midas.api.tenant.fiscal.util.CertLeituraUtil;
import com.midas.api.tenant.fiscal.util.FsNfeManNomeUtil;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.repository.CdCertRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.FsMdfeRepository;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.NumUtil;

import br.inf.portalfiscal.mdfe.EvCancMDFe;
import br.inf.portalfiscal.mdfe.EvEncMDFe;
import br.inf.portalfiscal.mdfe.TConsReciMDFe;
import br.inf.portalfiscal.mdfe.TConsStatServ;
import br.inf.portalfiscal.mdfe.TEnvEvento;
import br.inf.portalfiscal.mdfe.TEvento;
import br.inf.portalfiscal.mdfe.TEvento.InfEvento;
import br.inf.portalfiscal.mdfe.TEvento.InfEvento.DetEvento;
import br.inf.portalfiscal.mdfe.TRetConsReciMDFe;
import br.inf.portalfiscal.mdfe.TRetConsStatServ;
import br.inf.portalfiscal.mdfe.TRetEnviMDFe;
import br.inf.portalfiscal.mdfe.TRetEvento;
import br.inf.portalfiscal.www.mdfe.wsdl.mdferecepcao.MDFeRecepcaoStub;
import br.inf.portalfiscal.www.mdfe.wsdl.mdferecepcaoevento.MDFeRecepcaoEventoStub;
import br.inf.portalfiscal.www.mdfe.wsdl.mdferecepcaoevento.MDFeRecepcaoEventoStub.MdfeRecepcaoEventoResult;
import br.inf.portalfiscal.www.mdfe.wsdl.mdferetrecepcao.MDFeRetRecepcaoStub;
import br.inf.portalfiscal.www.mdfe.wsdl.mdfestatusservico.MDFeStatusServicoStub;

@Service
public class FsMdfeEnvioService {
	@Autowired
	private FsMdfeRepository fsMdfeRp;
	@Autowired
	private CdCertRepository cdCertRp;
	@Autowired
	private CertLeituraUtil certLeituraUtil;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private GeraMDFeProc geraMDFeProc;
	@Autowired
	private GeraMDFeEventoProc geraMDFeEventoProc;

	public String consultaServico(Long idlocal, String ambiente, HttpServletRequest request) throws Exception {
		try {
			Optional<CdPessoa> p = cdPessoaRp.findById(idlocal);
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(p.get());
			String coduf = p.get().getCdestado().getId() + "";
			TConsStatServ cs = new TConsStatServ();
			cs.setVersao(FsMdfeWebService.versaoDados);
			cs.setTpAmb(ambiente);
			cs.setXServ("STATUS");
			String xml = FsUtil.xmlCons(cs);
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), "58", request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			MDFeStatusServicoStub.MdfeDadosMsg mdfeDadosMsg = new MDFeStatusServicoStub.MdfeDadosMsg();
			mdfeDadosMsg.setExtraElement(omeElement);
			MDFeStatusServicoStub.MdfeCabecMsg mdfeCabecMsg = new MDFeStatusServicoStub.MdfeCabecMsg();
			mdfeCabecMsg.setCUF(coduf);
			mdfeCabecMsg.setVersaoDados(FsMdfeWebService.versaoDados);
			MDFeStatusServicoStub.MdfeCabecMsgE mdfeCabecMsgE = new MDFeStatusServicoStub.MdfeCabecMsgE();
			mdfeCabecMsgE.setMdfeCabecMsg(mdfeCabecMsg);
			// CRIA SERVICO
			String url = FsMdfeWebService.linkConsultaServico(ambiente);
			MDFeStatusServicoStub stub = new MDFeStatusServicoStub(url);
			// RESPOSTA
			MDFeStatusServicoStub.MdfeStatusServicoMDFResult result = stub.mdfeStatusServicoMDF(mdfeDadosMsg,
					mdfeCabecMsgE);
			// PROCESSA RESULTADO
			TRetConsStatServ ret = FsUtil.xmlToObject(result.getExtraElement().toString(), TRetConsStatServ.class);
			String[] reps = new String[2];
			reps[0] = ret.getCStat();
			reps[1] = ret.getXMotivo();
			return reps[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}

	public String[] envioMDFe(FsMdfe mdfe, String xml, HttpServletRequest request) throws Exception {
		try {
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(mdfe.getCdpessoaemp());
			xml = FsMdfeAssinaService.assinaEnviMDFe(xml, cert.get());
			String coduf = mdfe.getCdpessoaemp().getCdestado().getId() + "";
			String url = FsMdfeWebService.linkEnvioServico(mdfe.getTpamb().toString());
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), mdfe.getModelo().toString(), request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			MDFeRecepcaoStub.MdfeDadosMsg dadosMsg = new MDFeRecepcaoStub.MdfeDadosMsg();
			dadosMsg.setExtraElement(omeElement);
			MDFeRecepcaoStub.MdfeCabecMsg mdfeCabecMsg = new MDFeRecepcaoStub.MdfeCabecMsg();
			mdfeCabecMsg.setCUF(coduf);
			mdfeCabecMsg.setVersaoDados(FsMdfeWebService.versaoDados);
			MDFeRecepcaoStub.MdfeCabecMsgE mdfeCabecMsgE = new MDFeRecepcaoStub.MdfeCabecMsgE();
			mdfeCabecMsgE.setMdfeCabecMsg(mdfeCabecMsg);
			MDFeRecepcaoStub stub = new MDFeRecepcaoStub(url.toString());
			// RESPOSTA
			MDFeRecepcaoStub.MdfeRecepcaoLoteResult result = stub.mdfeRecepcaoLote(dadosMsg, mdfeCabecMsgE);
			// PROCESSA RESULTADO
			String xmlCons = result.getExtraElement().toString();
			TRetEnviMDFe ret = FsUtil.xmlToObject(xmlCons, TRetEnviMDFe.class);
			String[] reps = new String[11];
			if (ret.getCStat().equals("103")) {
				reps = verificaReciboMDFe(mdfe, ret.getInfRec().getNRec(), request);
				String mdfeProc = geraMDFeProc.geraProc(xml, reps[3], FsMdfeWebService.versaoDados);
				if (reps[0].equals("100") || reps[0].equals("150")) {
					fsMdfeRp.updateXMLMDFeStatus(mdfeProc, Integer.valueOf(reps[0]), reps[2], ret.getInfRec().getNRec(),
							mdfe.getId());
				} else {
					fsMdfeRp.updateXMLMDFeStatus(mdfeProc, 1, null, null, mdfe.getId());
				}
			} else {
				fsMdfeRp.updateXMLMDFeStatus(xml, 1, null, null, mdfe.getId());
				reps[0] = ret.getCStat();
				reps[1] = ret.getXMotivo();
			}
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] verificaReciboMDFe(FsMdfe mdfe, String recibo, HttpServletRequest request) throws Exception {
		try {
			String coduf = mdfe.getCdpessoaemp().getCdestado().getId() + "";
			String url = FsMdfeWebService.linkEnvioRetServico(mdfe.getTpamb().toString());
			// XML
			TConsReciMDFe cs = new TConsReciMDFe();
			cs.setNRec(recibo);
			cs.setTpAmb(mdfe.getTpamb().toString());
			cs.setVersao(FsMdfeWebService.versaoDados);
			String xml = FsUtil.xmlConsRecMDFe(cs);
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			MDFeRetRecepcaoStub.MdfeDadosMsg mdfeDadosMsg = new MDFeRetRecepcaoStub.MdfeDadosMsg();
			mdfeDadosMsg.setExtraElement(omeElement);
			// CABECALHO
			MDFeRetRecepcaoStub.MdfeCabecMsg mdfeCabecMsg = new MDFeRetRecepcaoStub.MdfeCabecMsg();
			mdfeCabecMsg.setCUF(coduf);
			mdfeCabecMsg.setVersaoDados(FsMdfeWebService.versaoDados);
			MDFeRetRecepcaoStub.MdfeCabecMsgE mdfeCabecMsgE = new MDFeRetRecepcaoStub.MdfeCabecMsgE();
			mdfeCabecMsgE.setMdfeCabecMsg(mdfeCabecMsg);
			// CRIA SERVICO
			MDFeRetRecepcaoStub stub = new MDFeRetRecepcaoStub(url);
			// RESPOSTA
			MDFeRetRecepcaoStub.MdfeRetRecepcaoResult result = stub.mdfeRetRecepcao(mdfeDadosMsg, mdfeCabecMsgE);
			// PROCESSA RESULTADO
			String xmlCons = result.getExtraElement().toString();
			TRetConsReciMDFe ret = FsUtil.xmlToObject(xmlCons, TRetConsReciMDFe.class);
			String[] reps = new String[11];
			if (ret.getProtMDFe() != null) {
				reps[0] = ret.getProtMDFe().getInfProt().getCStat();
				reps[1] = ret.getProtMDFe().getInfProt().getXMotivo();
				reps[2] = ret.getProtMDFe().getInfProt().getNProt();
				reps[3] = xmlCons;
			} else {
				reps[0] = ret.getCStat();
				reps[1] = ret.getXMotivo();
			}
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] eventoMDFe(FsMdfe mdfe, String tpevento, String justifica, String ufenc, String ibgeenc,
			HttpServletRequest request) throws Exception {
		try {
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(mdfe.getCdpessoaemp());
			String chave = mdfe.getChaveac();
			String ambiente = mdfe.getTpamb() + "";
			String cpfcnpj = CaracterUtil.remPout(mdfe.getCdpessoaemp().getCpfcnpj());
			String dtemi = DataUtil.dtTim(DataUtil.dataBancoAtual(), DataUtil.dataBancoTimeAtual());
			String coduf = mdfe.getCdpessoaemp().getCdestado().getId() + "";
			String codufenc = ufenc;
			String codcidenc = ibgeenc;
			String nomeopera = FsNfeManNomeUtil.nomeMan(tpevento);
			Integer numseq = 1; // Definido como 1 sempre
			String versadoDados = FsMdfeWebService.versaoDados;
			String xml = "";
			// Verifica se cancela-----------
			if (tpevento.equals("110111")) {
				TEvento evento = new TEvento();
				TEnvEvento envEvento = new TEnvEvento();
				envEvento.setVersao(versadoDados);
				InfEvento infEvento = new InfEvento();
				infEvento.setId("ID" + tpevento + chave + NumUtil.geraNumEsq(numseq, 2));
				infEvento.setCOrgao(coduf);
				infEvento.setTpAmb(ambiente);
				if (cpfcnpj.length() == 14) {
					infEvento.setCNPJ(cpfcnpj);
				}
				if (cpfcnpj.length() == 11) {
					infEvento.setCPF(cpfcnpj);
				}
				infEvento.setChMDFe(chave);
				infEvento.setDhEvento(dtemi);
				infEvento.setTpEvento(tpevento);
				infEvento.setNSeqEvento(numseq.toString());
				DetEvento detEvento = new DetEvento();
				detEvento.setVersaoEvento(versadoDados);
				EvCancMDFe e = new EvCancMDFe();
				e.setDescEvento(nomeopera);
				e.setNProt(mdfe.getNprot());
				e.setXJust(CaracterUtil.remUpper(justifica));
				detEvento.setEvCancMDFe(e);
				infEvento.setDetEvento(detEvento);
				evento.setInfEvento(infEvento);
				envEvento.getEvento().add(evento);
				xml = FsUtil.xmlEventoMDFe(envEvento);
			}
			// Verifica se encerra-----------
			if (tpevento.equals("110112")) {
				TEvento evento = new TEvento();
				TEnvEvento envEvento = new TEnvEvento();
				envEvento.setVersao(versadoDados);
				InfEvento infEvento = new InfEvento();
				infEvento.setId("ID" + tpevento + chave + NumUtil.geraNumEsq(numseq, 2));
				infEvento.setCOrgao(coduf);
				infEvento.setTpAmb(ambiente);
				if (cpfcnpj.length() == 14) {
					infEvento.setCNPJ(cpfcnpj);
				}
				if (cpfcnpj.length() == 11) {
					infEvento.setCPF(cpfcnpj);
				}
				infEvento.setChMDFe(chave);
				infEvento.setDhEvento(dtemi);
				infEvento.setTpEvento(tpevento);
				infEvento.setNSeqEvento(numseq.toString());
				DetEvento detEvento = new DetEvento();
				detEvento.setVersaoEvento(versadoDados);
				EvEncMDFe e = new EvEncMDFe();
				e.setDescEvento(nomeopera);
				e.setCMun(codcidenc);
				e.setCUF(codufenc);
				e.setDtEnc(DataUtil.dataBancoAtual());
				e.setNProt(mdfe.getNprot());
				detEvento.setEvEncMDFe(e);
				detEvento.setEvEncMDFe(e);
				infEvento.setDetEvento(detEvento);
				evento.setInfEvento(infEvento);
				envEvento.getEvento().add(evento);
				xml = FsUtil.xmlEventoMDFe(envEvento);
			}
			xml = FsMdfeAssinaService.assinarEvento(xml, cert.get());
			xml = xml.replaceAll("<evento>", "");
			xml = xml.replaceAll("</evento>", "");
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), mdfe.getModelo() + "", request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			MDFeRecepcaoEventoStub.MdfeDadosMsg dadosMsg = new MDFeRecepcaoEventoStub.MdfeDadosMsg();
			dadosMsg.setExtraElement(omeElement);
			MDFeRecepcaoEventoStub.MdfeCabecMsg mdfeCabecMsg = new MDFeRecepcaoEventoStub.MdfeCabecMsg();
			// CODIGO UF
			mdfeCabecMsg.setCUF(coduf);
			// CRIA SERVICO
			String url = FsMdfeWebService.linkEnvioEventosServico(ambiente);
			mdfeCabecMsg.setVersaoDados(FsMdfeWebService.versaoDados);
			MDFeRecepcaoEventoStub.MdfeCabecMsgE mdfeCabecMsgE = new MDFeRecepcaoEventoStub.MdfeCabecMsgE();
			mdfeCabecMsgE.setMdfeCabecMsg(mdfeCabecMsg);
			MDFeRecepcaoEventoStub stub = new MDFeRecepcaoEventoStub(url.toString());
			// RESPOSTA
			MdfeRecepcaoEventoResult result = stub.mdfeRecepcaoEvento(dadosMsg, mdfeCabecMsgE);
			// PROCESSA RESULTADO
			TRetEvento ret = FsUtil.xmlToObject(result.getExtraElement().toString(), TRetEvento.class);
			String[] reps = new String[5];
			// Se tem resposta OK
			if (ret.getInfEvento().getCStat().equals("135")) {
				String mdfeProc = geraMDFeEventoProc.geraProcEvento(result.getExtraElement().toString(), xml,
						versadoDados);
				reps[0] = ret.getInfEvento().getCStat();
				reps[1] = ret.getInfEvento().getXMotivo();
				reps[2] = ret.getInfEvento().getXEvento();
				reps[3] = mdfeProc;
				reps[4] = numseq + "";
			}
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
