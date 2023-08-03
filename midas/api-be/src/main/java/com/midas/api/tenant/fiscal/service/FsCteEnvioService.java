package com.midas.api.tenant.fiscal.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.constant.FsCteWebService;
import com.midas.api.constant.FsMdfeWebService;
import com.midas.api.tenant.entity.CdCert;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.fiscal.GeraCTeEventoProc;
import com.midas.api.tenant.fiscal.GeraCTeProc;
import com.midas.api.tenant.fiscal.util.CertLeituraUtil;
import com.midas.api.tenant.fiscal.util.FsNfeManNomeUtil;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.fiscal.util.GeraChavesUtil;
import com.midas.api.tenant.repository.CdCertRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.FsCteRepository;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.NumUtil;

import br.inf.portalfiscal.cte.DistDFeInt;
import br.inf.portalfiscal.cte.DistDFeInt.ConsNSU;
import br.inf.portalfiscal.cte.EvCancCTe;
import br.inf.portalfiscal.cte.RetDistDFeInt;
import br.inf.portalfiscal.cte.RetDistDFeInt.LoteDistDFeInt;
import br.inf.portalfiscal.cte.TConsReciCTe;
import br.inf.portalfiscal.cte.TConsStatServ;
import br.inf.portalfiscal.cte.TEnvEvento;
import br.inf.portalfiscal.cte.TEvento;
import br.inf.portalfiscal.cte.TEvento.InfEvento;
import br.inf.portalfiscal.cte.TEvento.InfEvento.DetEvento;
import br.inf.portalfiscal.cte.TInutCTe;
import br.inf.portalfiscal.cte.TInutCTe.InfInut;
import br.inf.portalfiscal.cte.TRetConsReciCTe;
import br.inf.portalfiscal.cte.TRetConsStatServ;
import br.inf.portalfiscal.cte.TRetEnviCTe;
import br.inf.portalfiscal.cte.TRetEvento;
import br.inf.portalfiscal.cte.TRetInutCTe;
import br.inf.portalfiscal.www.cte.wsdl.ctedistribuicaodfe.CTeDistribuicaoDFeStub;
import br.inf.portalfiscal.www.cte.wsdl.cteinutilizacao.CteInutilizacaoStub;
import br.inf.portalfiscal.www.cte.wsdl.cterecepcao.CteRecepcaoStub;
import br.inf.portalfiscal.www.cte.wsdl.cterecepcaoevento.CteRecepcaoEventoStub;
import br.inf.portalfiscal.www.cte.wsdl.cterecepcaoevento.CteRecepcaoEventoStub.CteRecepcaoEventoResult;
import br.inf.portalfiscal.www.cte.wsdl.cteretrecepcao.CteRetRecepcaoStub;
import br.inf.portalfiscal.www.cte.wsdl.ctestatusservico.CteStatusServicoStub;

@Service
public class FsCteEnvioService {
	@Autowired
	private FsCteRepository fsCteRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdCertRepository cdCertRp;
	@Autowired
	private GeraCTeProc geraCTeProc;
	@Autowired
	private GeraCTeEventoProc geraCTeEventoProc;
	@Autowired
	private CertLeituraUtil certLeituraUtil;
	@Autowired
	private FsCteService fsCtService;

	public String consultaServico(Long idlocal, String ambiente, HttpServletRequest request) throws Exception {
		try {
			Optional<CdPessoa> p = cdPessoaRp.findById(idlocal);
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(p.get());
			String codigoUf = String.valueOf(p.get().getCdestado().getId());
			TConsStatServ cs = new TConsStatServ();
			cs.setVersao(FsCteWebService.versaoDados);
			cs.setTpAmb(ambiente);
			cs.setXServ("STATUS");
			String xml = FsUtil.xmlConsCTe(cs);
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), "57", request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			CteStatusServicoStub.CteDadosMsg cteDadosMsg = new CteStatusServicoStub.CteDadosMsg();
			cteDadosMsg.setExtraElement(omeElement);
			// CABECALHO
			CteStatusServicoStub.CteCabecMsg cteCabecMsg = new CteStatusServicoStub.CteCabecMsg();
			cteCabecMsg.setCUF(codigoUf);
			cteCabecMsg.setVersaoDados(FsCteWebService.versaoDados);
			CteStatusServicoStub.CteCabecMsgE cteCabecMsgE = new CteStatusServicoStub.CteCabecMsgE();
			cteCabecMsgE.setCteCabecMsg(cteCabecMsg);
			// CRIA SERVICO
			String url = FsCteWebService.linkConsultaServico(p.get().getCdestado().getUf(), ambiente);
			CteStatusServicoStub stub = new CteStatusServicoStub(url);
			// RESPOSTA
			CteStatusServicoStub.CteStatusServicoCTResult result = stub.cteStatusServicoCT(cteDadosMsg, cteCabecMsgE);
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

	public String[] consultaDistDest(Long idlocal, String ambiente, Long ultNSU, Long maxNSU,
			HttpServletRequest request) throws Exception {
		try {
			String modelo = "57";
			Optional<CdPessoa> p = cdPessoaRp.findById(idlocal);
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(p.get());
			String cpfcnpj = CaracterUtil.remPout(p.get().getCpfcnpj());
			String coduf = p.get().getCdestado().getId() + "";
			String[] reps = new String[4];
			DistDFeInt distDFeInt = new DistDFeInt();
			distDFeInt.setVersao("1.00");
			distDFeInt.setTpAmb(ambiente);
			distDFeInt.setCUFAutor(coduf);
			distDFeInt.setCNPJ(cpfcnpj);
			distDFeInt.setConsNSU(null);
			DistDFeInt.DistNSU distNSU = new DistDFeInt.DistNSU();
			String ultNSUst = NumUtil.geraNumEsq(ultNSU.intValue(), 15);
			distNSU.setUltNSU(ultNSUst);
			distDFeInt.setDistNSU(distNSU);
			String xml = FsUtil.xmlDistcte(distDFeInt);
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), modelo, request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			CTeDistribuicaoDFeStub.CteDadosMsg_type0 cteDadosMsg = new CTeDistribuicaoDFeStub.CteDadosMsg_type0();
			cteDadosMsg.setExtraElement(omeElement);
			CTeDistribuicaoDFeStub.CteDistDFeInteresse distDFeInteresse = new CTeDistribuicaoDFeStub.CteDistDFeInteresse();
			distDFeInteresse.setCteDadosMsg(cteDadosMsg);
			// CRIA SERVICO
			String url = FsCteWebService.linkConsultaDisCte(coduf, ambiente);
			CTeDistribuicaoDFeStub stub = new CTeDistribuicaoDFeStub(url);
			// RESPOSTA
			CTeDistribuicaoDFeStub.CteDistDFeInteresseResponse result = stub.cteDistDFeInteresse(distDFeInteresse);
			// PROCESSA RESULTADO
			RetDistDFeInt ret = FsUtil.xmlToObject(result.getCteDistDFeInteresseResult().getExtraElement().toString(),
					RetDistDFeInt.class);
			if (ret.getLoteDistDFeInt() != null) {
				if (ret.getLoteDistDFeInt().getDocZip().size() > 0) {
					for (LoteDistDFeInt.DocZip i : ret.getLoteDistDFeInt().getDocZip()) {
						fsCtService.importaDist(FsUtil.gZipToXml(i.getValue()), i.getNSU(), p.get());
					}
				}
			}
			reps[0] = ret.getCStat();
			reps[1] = ret.getXMotivo();
			reps[2] = ret.getUltNSU();
			reps[3] = ret.getMaxNSU();
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] dwnloadXmlCTe(Long idlocal, String ambiente, String nsu, HttpServletRequest request)
			throws Exception {
		try {
			String modelo = "57";
			Optional<CdPessoa> p = cdPessoaRp.findById(idlocal);
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(p.get());
			String cpfcnpj = CaracterUtil.remPout(p.get().getCpfcnpj());
			String uf = String.valueOf(p.get().getCdestado().getId());
			String xmlretorno = "";
			DistDFeInt distDFeInt = new DistDFeInt();
			distDFeInt.setVersao("1.00");
			distDFeInt.setTpAmb(ambiente);
			distDFeInt.setCUFAutor(uf);
			if (cpfcnpj.length() == 14) {
				distDFeInt.setCNPJ(cpfcnpj);
			}
			if (cpfcnpj.length() == 11) {
				distDFeInt.setCPF(cpfcnpj);
			}
			ConsNSU n = new ConsNSU();
			n.setNSU(nsu);
			distDFeInt.setConsNSU(n);
			String xml = FsUtil.xmlDistcte(distDFeInt);
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), modelo, request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			CTeDistribuicaoDFeStub.CteDadosMsg_type0 cteDadosMsg = new CTeDistribuicaoDFeStub.CteDadosMsg_type0();
			cteDadosMsg.setExtraElement(omeElement);
			CTeDistribuicaoDFeStub.CteDistDFeInteresse distDFeInteresse = new CTeDistribuicaoDFeStub.CteDistDFeInteresse();
			distDFeInteresse.setCteDadosMsg(cteDadosMsg);
			// CRIA SERVICO
			String url = FsCteWebService.linkConsultaDisCte(uf, ambiente);
			CTeDistribuicaoDFeStub stub = new CTeDistribuicaoDFeStub(url);
			// RESPOSTA
			CTeDistribuicaoDFeStub.CteDistDFeInteresseResponse result = stub.cteDistDFeInteresse(distDFeInteresse);
			// PROCESSA RESULTADO
			RetDistDFeInt ret = FsUtil.xmlToObject(result.getCteDistDFeInteresseResult().getExtraElement().toString(),
					RetDistDFeInt.class);
			if (ret.getLoteDistDFeInt() != null) {
				if (ret.getLoteDistDFeInt().getDocZip().size() > 0) {
					for (LoteDistDFeInt.DocZip i : ret.getLoteDistDFeInt().getDocZip()) {
						xmlretorno = FsUtil.gZipToXml(i.getValue());
					}
				}
			}
			String[] reps = new String[4];
			reps[0] = ret.getCStat();
			reps[1] = ret.getXMotivo();
			reps[2] = xmlretorno;
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] envioCTe(FsCte cte, String xml, HttpServletRequest request) throws Exception {
		try {
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(cte.getCdpessoaemp());
			xml = FsCteAssinaService.assinaEnviCTe(xml, cert.get());
			String codigoUf = cte.getCdpessoaemp().getCdestado().getId() + "";
			String uf = cte.getCdpessoaemp().getCdestado().getUf();
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), "57", request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			// CABECALHO
			CteRecepcaoStub.CteDadosMsg dadosMsg = new CteRecepcaoStub.CteDadosMsg();
			dadosMsg.setExtraElement(omeElement);
			CteRecepcaoStub.CteCabecMsg cteCabecMsg = new CteRecepcaoStub.CteCabecMsg();
			cteCabecMsg.setCUF(codigoUf);
			cteCabecMsg.setVersaoDados(FsCteWebService.versaoDados);
			CteRecepcaoStub.CteCabecMsgE cteCabecMsgE = new CteRecepcaoStub.CteCabecMsgE();
			cteCabecMsgE.setCteCabecMsg(cteCabecMsg);
			String url = FsCteWebService.linkEnvioServico(uf, cte.getTpamb().toString());
			CteRecepcaoStub stub = new CteRecepcaoStub(url.toString());
			CteRecepcaoStub.CteRecepcaoLoteResult result = stub.cteRecepcaoLote(dadosMsg, cteCabecMsgE);
			// PROCESSA RESULTADO
			String xmlCons = result.getExtraElement().toString();
			TRetEnviCTe ret = FsUtil.xmlToObject(xmlCons, TRetEnviCTe.class);
			String[] reps = new String[11];
			if (ret.getCStat().equals("103")) {
				reps = verificaReciboCTe(cte, ret.getInfRec().getNRec(), request);
				String cteProc = geraCTeProc.geraProc(xml, reps[3], FsMdfeWebService.versaoDados);
				if (reps[0].equals("100") || reps[0].equals("150")) {
					fsCteRp.updateXMLCTeStatus(cteProc, Integer.valueOf(reps[0]), reps[2], ret.getInfRec().getNRec(),
							cte.getId());
				} else {
					fsCteRp.updateXMLCTeStatus(cteProc, 1, null, null, cte.getId());
				}
			} else {
				fsCteRp.updateXMLCTeStatus(xml, 1, null, null, cte.getId());
				reps[0] = ret.getCStat();
				reps[1] = ret.getXMotivo();
			}
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] verificaReciboCTe(FsCte cte, String recibo, HttpServletRequest request) throws Exception {
		try {
			String codigoUf = cte.getCdpessoaemp().getCdestado().getId() + "";
			String uf = cte.getCdpessoaemp().getCdestado().getUf();
			String url = FsCteWebService.linkEnvioRetServico(uf, cte.getTpamb().toString());
			// XML
			TConsReciCTe cs = new TConsReciCTe();
			cs.setNRec(recibo);
			cs.setTpAmb(cte.getTpamb().toString());
			cs.setVersao(FsMdfeWebService.versaoDados);
			String xml = FsUtil.xmlConsRecCTe(cs);
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			CteRetRecepcaoStub.CteDadosMsg cteDadosMsg = new CteRetRecepcaoStub.CteDadosMsg();
			cteDadosMsg.setExtraElement(omeElement);
			// CABECALHO
			CteRetRecepcaoStub.CteCabecMsg cteCabecMsg = new CteRetRecepcaoStub.CteCabecMsg();
			cteCabecMsg.setCUF(codigoUf);
			cteCabecMsg.setVersaoDados(FsCteWebService.versaoDados);
			CteRetRecepcaoStub.CteCabecMsgE cteCabecMsgE = new CteRetRecepcaoStub.CteCabecMsgE();
			cteCabecMsgE.setCteCabecMsg(cteCabecMsg);
			// CRIA SERVICO
			CteRetRecepcaoStub stub = new CteRetRecepcaoStub(url);
			// RESPOSTA
			CteRetRecepcaoStub.CteRetRecepcaoResult result = stub.cteRetRecepcao(cteDadosMsg, cteCabecMsgE);
			// PROCESSA RESULTADO
			String xmlCons = result.getExtraElement().toString();
			TRetConsReciCTe ret = FsUtil.xmlToObject(xmlCons, TRetConsReciCTe.class);
			String[] reps = new String[11];
			if (ret.getProtCTe() != null) {
				if (ret.getProtCTe().size() > 0) {
					reps[0] = ret.getProtCTe().get(0).getInfProt().getCStat();
					reps[1] = ret.getProtCTe().get(0).getInfProt().getXMotivo();
					reps[2] = ret.getProtCTe().get(0).getInfProt().getNProt();
					reps[3] = xmlCons;
				} else {
					reps[0] = ret.getCStat();
					reps[1] = ret.getXMotivo();
				}
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

	public String[] inutilizaCTe(Long idlocal, String ambiente, String modelo, String serie, String numNI, String numNF,
			String justifica, HttpServletRequest request) throws Exception {
		try {
			Optional<CdPessoa> p = cdPessoaRp.findById(idlocal);
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(p.get());
			String cpfcnpj = CaracterUtil.remPout(p.get().getCpfcnpj());
			String uf = String.valueOf(p.get().getCdestado().getId());
			String ano = DataUtil.anoAtual().substring(2, 4);
			String chave = GeraChavesUtil.geraChaveInutCte(uf, ano, cpfcnpj, modelo, serie, numNI, numNF, "01");
			TInutCTe inu = new TInutCTe();
			inu.setVersao(FsCteWebService.versaoDados);
			InfInut infInu = new InfInut();
			infInu.setId(chave);
			infInu.setTpAmb(ambiente);
			infInu.setXServ("INUTILIZAR");
			infInu.setCUF(uf);
			infInu.setAno(Short.valueOf(ano));
			infInu.setCNPJ(cpfcnpj);
			infInu.setMod("57");
			infInu.setSerie(serie);
			infInu.setNCTIni(numNI);
			infInu.setNCTFin(numNF);
			infInu.setXJust(CaracterUtil.remUpper(justifica));
			inu.setInfInut(infInu);
			String xml = FsUtil.xmlInutCte(inu);
			xml = FsCteAssinaService.assinarInut(xml, cert.get());
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), modelo, request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			CteInutilizacaoStub.CteDadosMsg dadosMsg = new CteInutilizacaoStub.CteDadosMsg();
			dadosMsg.setExtraElement(omeElement);
			CteInutilizacaoStub.CteCabecMsg cteCabecMsg = new CteInutilizacaoStub.CteCabecMsg();
			// CODIGO UF
			cteCabecMsg.setCUF(uf);
			// VERSAO XML
			cteCabecMsg.setVersaoDados(FsCteWebService.versaoDados);
			CteInutilizacaoStub.CteCabecMsgE cteCabecMsgE = new CteInutilizacaoStub.CteCabecMsgE();
			cteCabecMsgE.setCteCabecMsg(cteCabecMsg);
			String url = FsCteWebService.linkInutiliza(p.get().getCdestado().getUf(), ambiente);
			CteInutilizacaoStub stub = new CteInutilizacaoStub(url);
			CteInutilizacaoStub.CteInutilizacaoCTResult result = stub.cteInutilizacaoCT(dadosMsg, cteCabecMsgE);
			// PROCESSA RESULTADO
			TRetInutCTe ret = FsUtil.xmlToObject(result.getExtraElement().toString(), TRetInutCTe.class);
			String reps[] = new String[2];
			reps[0] = ret.getInfInut().getCStat();
			reps[1] = ret.getInfInut().getXMotivo();
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] eventoCTe(FsCte cte, String tpevento, String justifica, String ufenc, String ibgeenc,
			HttpServletRequest request) throws Exception {
		try {
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(cte.getCdpessoaemp());
			String chave = cte.getChaveac();
			String ambiente = cte.getTpamb() + "";
			String cpfcnpj = CaracterUtil.remPout(cte.getCdpessoaemp().getCpfcnpj());
			String dtemi = DataUtil.dtTim(DataUtil.dataBancoAtual(), DataUtil.dataBancoTimeAtual());
			String coduf = cte.getCdpessoaemp().getCdestado().getId() + "";
			String uf = cte.getCdpessoaemp().getCdestado().getUf();
			String nomeopera = FsNfeManNomeUtil.nomeMan(tpevento);
			Integer numseq = 1; // Definido como 1 sempre
			String versadoDados = FsCteWebService.versaoDados;
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
				infEvento.setChCTe(chave);
				infEvento.setDhEvento(dtemi);
				infEvento.setTpEvento(tpevento);
				infEvento.setNSeqEvento(numseq.toString());
				DetEvento detEvento = new DetEvento();
				detEvento.setVersaoEvento(versadoDados);
				EvCancCTe e = new EvCancCTe();
				e.setDescEvento(nomeopera);
				e.setNProt(cte.getNprot());
				e.setXJust(CaracterUtil.remUpper(justifica));
				detEvento.setEvCancCTe(e);
				infEvento.setDetEvento(detEvento);
				evento.setInfEvento(infEvento);
				envEvento.getEvento().add(evento);
				xml = FsUtil.xmlEventoCTe(envEvento);
			}
			xml = FsMdfeAssinaService.assinarEvento(xml, cert.get());
			xml = xml.replaceAll("<evento>", "");
			xml = xml.replaceAll("</evento>", "");
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), "57", request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			CteRecepcaoEventoStub.CteDadosMsg dadosMsg = new CteRecepcaoEventoStub.CteDadosMsg();
			dadosMsg.setExtraElement(omeElement);
			CteRecepcaoEventoStub.CteCabecMsg cteCabecMsg = new CteRecepcaoEventoStub.CteCabecMsg();
			// CODIGO UF
			cteCabecMsg.setCUF(coduf);
			// VERSAO XML
			cteCabecMsg.setVersaoDados(versadoDados);
			CteRecepcaoEventoStub.CteCabecMsgE cteCabecMsgE = new CteRecepcaoEventoStub.CteCabecMsgE();
			cteCabecMsgE.setCteCabecMsg(cteCabecMsg);
			// CRIA SERVICO
			String url = FsCteWebService.linkEnvioEventosServico(uf, ambiente);
			CteRecepcaoEventoStub stub = new CteRecepcaoEventoStub(url.toString());
			// RESPOSTA
			CteRecepcaoEventoResult result = stub.cteRecepcaoEvento(dadosMsg, cteCabecMsgE);
			// PROCESSA RESULTADO
			TRetEvento ret = FsUtil.xmlToObject(result.getExtraElement().toString(), TRetEvento.class);
			String[] reps = new String[5];
			// Se tem resposta OK
			if (ret.getInfEvento().getCStat().equals("135")) {
				String mdfeProc = geraCTeEventoProc.geraProcEvento(result.getExtraElement().toString(), xml,
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
