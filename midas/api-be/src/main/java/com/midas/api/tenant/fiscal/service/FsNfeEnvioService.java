package com.midas.api.tenant.fiscal.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.constant.FsNfeWebService;
import com.midas.api.constant.MidasConfig;
import com.midas.api.tenant.entity.CdCert;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfeEvento;
import com.midas.api.tenant.fiscal.GeraNFeEventoProc;
import com.midas.api.tenant.fiscal.GeraNFeProc;
import com.midas.api.tenant.fiscal.util.CertLeituraUtil;
import com.midas.api.tenant.fiscal.util.FsNfeManNomeUtil;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.fiscal.util.GeraChavesUtil;
import com.midas.api.tenant.repository.CdCertRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.FsNfeEventoRepository;
import com.midas.api.tenant.repository.FsNfeRepository;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.NumUtil;

import br.inf.portalfiscal.nfe.DistDFeInt;
import br.inf.portalfiscal.nfe.DistDFeInt.ConsChNFe;
import br.inf.portalfiscal.nfe.RetDistDFeInt;
import br.inf.portalfiscal.nfe.RetDistDFeInt.LoteDistDFeInt;
import br.inf.portalfiscal.nfe.TConsCad;
import br.inf.portalfiscal.nfe.TConsCad.InfCons;
import br.inf.portalfiscal.nfe.TConsStatServ;
import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEnvEventoCC;
import br.inf.portalfiscal.nfe.TEvento;
import br.inf.portalfiscal.nfe.TEvento.InfEvento;
import br.inf.portalfiscal.nfe.TEvento.InfEvento.DetEvento;
import br.inf.portalfiscal.nfe.TEventoCC;
import br.inf.portalfiscal.nfe.TInutNFe;
import br.inf.portalfiscal.nfe.TInutNFe.InfInut;
import br.inf.portalfiscal.nfe.TRetConsCad;
import br.inf.portalfiscal.nfe.TRetConsStatServ;
import br.inf.portalfiscal.nfe.TRetEnvEvento;
import br.inf.portalfiscal.nfe.TRetEnviNFe;
import br.inf.portalfiscal.nfe.TRetInutNFe;
import br.inf.portalfiscal.nfe.TUfCons;
import br.inf.portalfiscal.www.nfe.wsdl.cadconsultacadastro4.CadConsultaCadastro4Stub;
import br.inf.portalfiscal.www.nfe.wsdl.nfeautorizacao4.NFeAutorizacao4Stub;
import br.inf.portalfiscal.www.nfe.wsdl.nfedistribuicaodfe.NFeDistribuicaoDFeStub;
import br.inf.portalfiscal.www.nfe.wsdl.nfeinutilizacao4.NFeInutilizacao4Stub;
import br.inf.portalfiscal.www.nfe.wsdl.nferecepcaoevento4.NFeRecepcaoEvento4Stub;
import br.inf.portalfiscal.www.nfe.wsdl.nfestatusservico4.NfeStatusServico4Stub;

@Service
public class FsNfeEnvioService {
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdCertRepository cdCertRp;
	@Autowired
	private FsNfeService fsNfeService;
	@Autowired
	private CertLeituraUtil certLeituraUtil;
	@Autowired
	private GeraNFeProc geraNFeProc;
	@Autowired
	private GeraNFeEventoProc geraNFeEventoProc;
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private FsNfeEventoRepository fsNfeEventoRp;

	public String consultaServico(Long idlocal, String ambiente, String modelo, HttpServletRequest request)
			throws Exception {
		try {
			Optional<CdPessoa> p = cdPessoaRp.findById(idlocal);
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(p.get());
			TConsStatServ cs = new TConsStatServ();
			cs.setVersao(FsNfeWebService.versaoDados);
			cs.setTpAmb(ambiente);
			cs.setCUF(String.valueOf(p.get().getCdestado().getId()));
			cs.setXServ("STATUS");
			String xml = FsUtil.xmlCons(cs);
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), modelo, request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			NfeStatusServico4Stub.NfeDadosMsg nfeDadosMsg = new NfeStatusServico4Stub.NfeDadosMsg();
			nfeDadosMsg.setExtraElement(omeElement);
			// CRIA SERVICO
			String url = FsNfeWebService.linkConsultaServico(p.get().getCdestado().getUf(), ambiente, modelo);
			NfeStatusServico4Stub stub = new NfeStatusServico4Stub(url);
			// RESPOSTA
			NfeStatusServico4Stub.NfeResultMsg result = stub.nfeStatusServicoNF(nfeDadosMsg);
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

	public String[] consultaCadastroIE(Long idlocal, String cpfcnpj, String uf, HttpServletRequest request)
			throws Exception {
		try {
			String ambiente = "1";
			String ie = "";
			cpfcnpj = CaracterUtil.remPout(cpfcnpj);
			TConsCad c = new TConsCad();
			c.setVersao("2.00");
			InfCons i = new InfCons();
			i.setXServ("CONS-CAD");
			if (uf.equals("")) {
				uf = "SC";
				i.setUF(TUfCons.valueOf(uf));
			} else {
				i.setUF(TUfCons.valueOf(uf));
			}
			if (cpfcnpj.length() == 11) {
				i.setCPF(cpfcnpj);
			} else {
				i.setCNPJ(cpfcnpj);
			}
			c.setInfCons(i);
			String xml = FsUtil.xmlConsCad(c);
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			CadConsultaCadastro4Stub.NfeDadosMsg nfeDadosMsg = new CadConsultaCadastro4Stub.NfeDadosMsg();
			nfeDadosMsg.setExtraElement(omeElement);
			// CRIA SERVICO
			String url = FsNfeWebService.linkConsultaCadastro(uf, ambiente);
			CadConsultaCadastro4Stub stub = new CadConsultaCadastro4Stub(url);
			// RESPOSTA
			CadConsultaCadastro4Stub.NfeResultMsg result = stub.consultaCadastro(nfeDadosMsg);
			// PROCESSA RESULTADO
			TRetConsCad ret = FsUtil.xmlToObject(result.getExtraElement().toString(), TRetConsCad.class);
			if (ret.getInfCons().getInfCad().size() > 0) {
				ie = ret.getInfCons().getInfCad().get(0).getIE();
			}
			String[] reps = new String[3];
			reps[0] = ret.getInfCons().getCStat();
			reps[1] = ret.getInfCons().getXMotivo();
			reps[2] = ie;
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] consultaDistDest(Long idlocal, String ambiente, Long ultNSU, Long maxNSU,
			HttpServletRequest request) throws Exception {
		try {
			String modelo = "55";
			Optional<CdPessoa> p = cdPessoaRp.findById(idlocal);
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(p.get());
			String cpfcnpj = CaracterUtil.remPout(p.get().getCpfcnpj());
			String coduf = p.get().getCdestado().getId() + "";
			String[] reps = new String[4];
			DistDFeInt distDFeInt = new DistDFeInt();
			distDFeInt.setVersao("1.01");
			distDFeInt.setTpAmb(ambiente);
			distDFeInt.setCUFAutor(coduf);
			distDFeInt.setCNPJ(cpfcnpj);
			distDFeInt.setConsNSU(null);
			DistDFeInt.DistNSU distNSU = new DistDFeInt.DistNSU();
			String ultNSUst = NumUtil.geraNumEsq(ultNSU.intValue(), 15);
			distNSU.setUltNSU(ultNSUst);
			distDFeInt.setDistNSU(distNSU);
			String xml = FsUtil.xmlDistfe(distDFeInt);
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), modelo, request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			NFeDistribuicaoDFeStub.NfeDadosMsg_type0 nfeDadosMsg = new NFeDistribuicaoDFeStub.NfeDadosMsg_type0();
			nfeDadosMsg.setExtraElement(omeElement);
			NFeDistribuicaoDFeStub.NfeDistDFeInteresse distDFeInteresse = new NFeDistribuicaoDFeStub.NfeDistDFeInteresse();
			distDFeInteresse.setNfeDadosMsg(nfeDadosMsg);
			// CRIA SERVICO
			String url = FsNfeWebService.linkConsultaDisNfe(coduf, ambiente);
			NFeDistribuicaoDFeStub stub = new NFeDistribuicaoDFeStub(url);
			// RESPOSTA
			NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse result = stub.nfeDistDFeInteresse(distDFeInteresse);
			// PROCESSA RESULTADO
			RetDistDFeInt ret = FsUtil.xmlToObject(result.getNfeDistDFeInteresseResult().getExtraElement().toString(),
					RetDistDFeInt.class);
			if (ret.getLoteDistDFeInt() != null) {
				if (ret.getLoteDistDFeInt().getDocZip().size() > 0) {
					for (LoteDistDFeInt.DocZip i : ret.getLoteDistDFeInt().getDocZip()) {
						fsNfeService.importaDist(FsUtil.gZipToXml(i.getValue()), i.getNSU(), p.get());
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

	public String[] inutilizaNFe(Long idlocal, String ambiente, String modelo, String serie, String numNI, String numNF,
			String justifica, HttpServletRequest request) throws Exception {
		try {
			Optional<CdPessoa> p = cdPessoaRp.findById(idlocal);
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(p.get());
			String cpfcnpj = CaracterUtil.remPout(p.get().getCpfcnpj());
			String uf = String.valueOf(p.get().getCdestado().getId());
			String ano = DataUtil.anoAtual().substring(2, 4);
			String chave = GeraChavesUtil.geraChaveInut(uf, ano, cpfcnpj, modelo, serie, numNI, numNF, "01");
			TInutNFe inu = new TInutNFe();
			inu.setVersao(FsNfeWebService.versaoDados);
			InfInut infInu = new InfInut();
			infInu.setId(chave);
			infInu.setTpAmb(ambiente);
			infInu.setXServ("INUTILIZAR");
			infInu.setCUF(uf);
			infInu.setAno(ano);
			infInu.setCNPJ(cpfcnpj);
			infInu.setMod("55");
			infInu.setSerie(serie);
			infInu.setNNFIni(numNI);
			infInu.setNNFFin(numNF);
			infInu.setXJust(CaracterUtil.remUpper(justifica));
			inu.setInfInut(infInu);
			String xml = FsUtil.xmlInut(inu);
			xml = FsNfeAssinaService.assinarInut(xml, cert.get());
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), modelo, request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			NFeInutilizacao4Stub.NfeDadosMsg nfeDadosMsg = new NFeInutilizacao4Stub.NfeDadosMsg();
			nfeDadosMsg.setExtraElement(omeElement);
			// CRIA SERVICO
			String url = FsNfeWebService.linkInutiliza(p.get().getCdestado().getUf(), ambiente, modelo);
			NFeInutilizacao4Stub stub = new NFeInutilizacao4Stub(url);
			// RESPOSTA
			NFeInutilizacao4Stub.NfeResultMsg result = stub.nfeInutilizacaoNF(nfeDadosMsg);
			// PROCESSA RESULTADO
			TRetInutNFe ret = FsUtil.xmlToObject(result.getExtraElement().toString(), TRetInutNFe.class);
			String[] reps = new String[2];
			reps[0] = ret.getInfInut().getCStat();
			reps[1] = ret.getInfInut().getXMotivo();
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] eventoNFe(Long idnfe, Long idlocal, String ambiente, String modelo, String uf, String ufn,
			String chave, String nprot, String tpevento, String justifica, HttpServletRequest request)
			throws Exception {
		try {
			Optional<CdPessoa> p = cdPessoaRp.findById(idlocal);
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(p.get());
			String cpfcnpj = CaracterUtil.remPout(p.get().getCpfcnpj());
			String dtemi = DataUtil.dtTim(DataUtil.dataBancoAtual(), DataUtil.dataBancoTimeAtual());
			String nomeopera = FsNfeManNomeUtil.nomeMan(tpevento);
			Integer numseq = 1; // Definido como 1 sempre
			String versadoDados = "1.00";
			String xml = "";
			// Verifica se nao e carta correcao-----------
			if (!tpevento.equals("110110")) {
				TEvento evento = new TEvento();
				TEnvEvento envEvento = new TEnvEvento();
				evento.setVersao(versadoDados);
				InfEvento infEvento = new InfEvento();
				infEvento.setId("ID" + tpevento + chave + NumUtil.geraNumEsq(numseq, 2));
				infEvento.setCOrgao(uf);
				infEvento.setTpAmb(ambiente);
				if (cpfcnpj.length() == 14) {
					infEvento.setCNPJ(cpfcnpj);
				}
				if (cpfcnpj.length() == 11) {
					infEvento.setCPF(cpfcnpj);
				}
				infEvento.setChNFe(chave);
				infEvento.setDhEvento(dtemi);
				infEvento.setTpEvento(tpevento);
				infEvento.setNSeqEvento(numseq.toString());
				infEvento.setVerEvento(versadoDados);
				DetEvento detEvento = new DetEvento();
				detEvento.setVersao(versadoDados);
				detEvento.setDescEvento(nomeopera);
				// Se cancelamento
				if (tpevento.equals("110111")) {
					detEvento.setNProt(nprot);
				}
				if (!justifica.equals("")) {
					detEvento.setXJust(CaracterUtil.remUpper(justifica));
				}
				infEvento.setDetEvento(detEvento);
				evento.setInfEvento(infEvento);
				envEvento.setVersao(versadoDados);
				envEvento.setIdLote("000000000000001");
				envEvento.getEvento().add(evento);
				xml = FsUtil.xmlEvento(envEvento);
			}
			// Se for carta de correcao------------------
			if (tpevento.equals("110110")) {
				FsNfeEvento ne = fsNfeEventoRp.ultimoEvento(idnfe, tpevento);
				if (ne != null) {
					numseq = ne.getNumseq() + 1;
				}
				TEventoCC evento = new TEventoCC();
				TEnvEventoCC envEvento = new TEnvEventoCC();
				evento.setVersao(versadoDados);
				br.inf.portalfiscal.nfe.TEventoCC.InfEvento infEvento = new br.inf.portalfiscal.nfe.TEventoCC.InfEvento();
				infEvento.setId("ID" + tpevento + chave + NumUtil.geraNumEsq(numseq, 2));
				infEvento.setCOrgao(uf);
				infEvento.setTpAmb(ambiente);
				if (cpfcnpj.length() == 14) {
					infEvento.setCNPJ(cpfcnpj);
				}
				if (cpfcnpj.length() == 11) {
					infEvento.setCPF(cpfcnpj);
				}
				infEvento.setChNFe(chave);
				infEvento.setDhEvento(dtemi);
				infEvento.setTpEvento(tpevento);
				infEvento.setNSeqEvento(numseq.toString());
				infEvento.setVerEvento(versadoDados);
				br.inf.portalfiscal.nfe.TEventoCC.InfEvento.DetEvento deCC = new br.inf.portalfiscal.nfe.TEventoCC.InfEvento.DetEvento();
				deCC.setVersao(versadoDados);
				deCC.setDescEvento(nomeopera);
				deCC.setXCorrecao(CaracterUtil.remUpper(justifica));
				deCC.setXCondUso(mc.xusocartacorrecao);
				infEvento.setDetEvento(deCC);
				evento.setInfEvento(infEvento);
				envEvento.setVersao(versadoDados);
				envEvento.setIdLote("000000000000001");
				envEvento.getEvento().add(evento);
				xml = FsUtil.xmlEventoCC(envEvento);
			}
			xml = FsNfeAssinaService.assinaEnvento(xml, cert.get());
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), modelo, request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			NFeRecepcaoEvento4Stub.NfeDadosMsg nfeDadosMsg = new NFeRecepcaoEvento4Stub.NfeDadosMsg();
			nfeDadosMsg.setExtraElement(omeElement);
			// CRIA SERVICO
			String url = FsNfeWebService.linkEvento(ufn, ambiente, modelo);
			NFeRecepcaoEvento4Stub stub = new NFeRecepcaoEvento4Stub(url);
			// RESPOSTA
			NFeRecepcaoEvento4Stub.NfeResultMsg result = stub.nfeRecepcaoEvento(nfeDadosMsg);
			// PROCESSA RESULTADO
			TRetEnvEvento ret = FsUtil.xmlToObject(result.getExtraElement().toString(), TRetEnvEvento.class);
			String[] reps = new String[5];
			reps[0] = ret.getCStat();
			reps[1] = ret.getXMotivo();
			reps[2] = "";
			// Se tem resposta OK
			if (ret.getRetEvento().size() > 0) {
				String nfeProc = geraNFeEventoProc.geraProcEvento(result.getExtraElement().toString(), xml,
						versadoDados);
				reps[0] = ret.getRetEvento().get(0).getInfEvento().getCStat();
				reps[1] = ret.getRetEvento().get(0).getInfEvento().getXMotivo();
				reps[2] = ret.getRetEvento().get(0).getInfEvento().getXEvento();
				reps[3] = nfeProc;
				reps[4] = numseq + "";
			}
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] dwnloadXmlNFe(Long idlocal, String ambiente, String chave, HttpServletRequest request)
			throws Exception {
		try {
			String modelo = "55";
			Optional<CdPessoa> p = cdPessoaRp.findById(idlocal);
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(p.get());
			String cpfcnpj = CaracterUtil.remPout(p.get().getCpfcnpj());
			String uf = String.valueOf(p.get().getCdestado().getId());
			String xmlretorno = "";
			DistDFeInt distDFeInt = new DistDFeInt();
			distDFeInt.setVersao("1.01");
			distDFeInt.setTpAmb(ambiente);
			distDFeInt.setCUFAutor(uf);
			if (cpfcnpj.length() == 14) {
				distDFeInt.setCNPJ(cpfcnpj);
			}
			if (cpfcnpj.length() == 11) {
				distDFeInt.setCPF(cpfcnpj);
			}
			ConsChNFe n = new ConsChNFe();
			n.setChNFe(chave);
			distDFeInt.setConsChNFe(n);
			String xml = FsUtil.xmlDistfe(distDFeInt);
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), modelo, request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			NFeDistribuicaoDFeStub.NfeDadosMsg_type0 nfeDadosMsg = new NFeDistribuicaoDFeStub.NfeDadosMsg_type0();
			nfeDadosMsg.setExtraElement(omeElement);
			NFeDistribuicaoDFeStub.NfeDistDFeInteresse distDFeInteresse = new NFeDistribuicaoDFeStub.NfeDistDFeInteresse();
			distDFeInteresse.setNfeDadosMsg(nfeDadosMsg);
			// CRIA SERVICO
			String url = FsNfeWebService.linkConsultaDisNfe(uf, ambiente);
			NFeDistribuicaoDFeStub stub = new NFeDistribuicaoDFeStub(url);
			// RESPOSTA
			NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse result = stub.nfeDistDFeInteresse(distDFeInteresse);
			// PROCESSA RESULTADO
			RetDistDFeInt ret = FsUtil.xmlToObject(result.getNfeDistDFeInteresseResult().getExtraElement().toString(),
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

	public String[] envioNFe(FsNfe nfe, String xml, HttpServletRequest request) throws Exception {
		try {
			Optional<CdCert> cert = cdCertRp.findByCdpessoaemp(nfe.getCdpessoaemp());
			xml = FsNfeAssinaService.assinaEnviNFe(xml, cert.get());
			// DADOS DO CERTIFICADO****************************************
			certLeituraUtil.geraKS(cert.get(), nfe.getModelo().toString(), request.getServletContext());
			// DADOS MENSAGEM
			OMElement omeElement = AXIOMUtil.stringToOM(xml.toString());
			NFeAutorizacao4Stub.NfeDadosMsg dadosMsg = new NFeAutorizacao4Stub.NfeDadosMsg();
			dadosMsg.setExtraElement(omeElement);
			String uf = nfe.getCdpessoaemp().getCdestado().getUf();
			String url = FsNfeWebService.linkEnvioServico(uf, nfe.getTpamb().toString(), nfe.getModelo().toString());
			NFeAutorizacao4Stub stub = new NFeAutorizacao4Stub(url.toString());
			// RESPOSTA
			NFeAutorizacao4Stub.NfeResultMsg result = stub.nfeAutorizacaoLote(dadosMsg);
			// PROCESSA RESULTADO
			String xmlCons = result.getExtraElement().toString();
			TRetEnviNFe ret = FsUtil.xmlToObject(xmlCons, TRetEnviNFe.class);
			String[] reps = new String[11];
			if (ret.getProtNFe() != null) {
				reps[0] = ret.getProtNFe().getInfProt().getCStat();
				reps[1] = ret.getProtNFe().getInfProt().getXMotivo();
				reps[2] = ret.getProtNFe().getInfProt().getXMsg();
				reps[3] = ret.getProtNFe().getInfProt().getCMsg();
				String nfeProc = geraNFeProc.geraProc(xml, xmlCons, FsNfeWebService.versaoDados);
				if (ret.getProtNFe().getInfProt().getCStat().equals("100")
						|| ret.getProtNFe().getInfProt().getCStat().equals("150")
						|| ret.getProtNFe().getInfProt().getCStat().equals("205")
						|| ret.getProtNFe().getInfProt().getCStat().equals("302")) {
					fsNfeRp.updateXMLNFeStatus(nfeProc, Integer.valueOf(ret.getProtNFe().getInfProt().getCStat()),
							ret.getProtNFe().getInfProt().getNProt(), nfe.getId());
				} else {
					fsNfeRp.updateXMLNFeStatus(nfeProc, 1, null, nfe.getId());
				}
			} else {
				reps[0] = ret.getCStat();
				reps[1] = ret.getXMotivo();
				fsNfeRp.updateXMLNFeStatus(xml, 1, null, nfe.getId());
			}
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
