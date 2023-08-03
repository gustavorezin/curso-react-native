package com.midas.api.tenant.fiscal.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midas.api.constant.MidasConfig;
import com.midas.api.tenant.entity.CdCteCfg;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdPessoaGrupo;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FnTituloCcusto;
import com.midas.api.tenant.entity.FnTituloDre;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsCteMan;
import com.midas.api.tenant.entity.FsCteManCons;
import com.midas.api.tenant.entity.FsCteManEvento;
import com.midas.api.tenant.fiscal.XmlExtrairCte;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.repository.CdCidadeRepository;
import com.midas.api.tenant.repository.CdEstadoRepository;
import com.midas.api.tenant.repository.CdPessoaGrupoRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.FnTituloCcustoRepository;
import com.midas.api.tenant.repository.FnTituloDreRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.repository.FsCteIcmsRepository;
import com.midas.api.tenant.repository.FsCteManConsRepository;
import com.midas.api.tenant.repository.FsCteManEventoRepository;
import com.midas.api.tenant.repository.FsCteManRepository;
import com.midas.api.tenant.repository.FsCteRepository;
import com.midas.api.util.DataUtil;
import com.midas.api.util.NumUtil;

import br.inf.portalfiscal.cte.CteProc;

@Service
public class FsCteService {
	@Autowired
	private FsCteRepository fsCteRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private XmlExtrairCte extrair;
	@Autowired
	private CdPessoaGrupoRepository cdPessoaGrupoRp;
	@Autowired
	private CdCidadeRepository cdCidadeRp;
	@Autowired
	private CdEstadoRepository cdEstadoRp;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private FnTituloDreRepository fnTituloDreRp;
	@Autowired
	private FnTituloCcustoRepository fnTituloCcustoRp;
	@Autowired
	private FsCteIcmsRepository fsCteIcmsRp;
	@Autowired
	private FsCteManConsRepository fsCteManConsRp;
	@Autowired
	private FsCteEnvioService fsCteEnvioService;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private FsCteManRepository fsCteDistRp;
	@Autowired
	private FsCteManEventoRepository fsCteDistEventoRp;

	@Transactional("tenantTransactionManager")
	public void importaDist(String xml, String nsu, CdPessoa localemp) throws Exception {
		// CONSULTA RESPOSTA SEM XML
		String chaveresCTe = FsUtil.eXmlDet(xml, "resCTe", 0, "chCTe");
		if (!chaveresCTe.equals("")) {
			FsCteMan drs = fsCteDistRp.findFirstByChaveAndCdpessoaemp(chaveresCTe, localemp);
			if (drs == null) {
				FsCteMan d = new FsCteMan();
				d.setCdpessoaemp(localemp);
				d.setChave(chaveresCTe);
				d.setNprot(FsUtil.eXmlDet(xml, "resCTe", 0, "nProt"));
				d.setNome(FsUtil.eXmlDet(xml, "resCTe", 0, "xNome"));
				d.setCpfcnpj(FsUtil.eXmlDet(xml, "resCTe", 0, "CNPJ"));
				d.setIe(FsUtil.eXmlDet(xml, "resCTe", 0, "IE"));
				d.setDhemi(DataUtil.dUtil(FsUtil.eXmlDet(xml, "resCTe", 0, "dhEmi")));
				d.setDhemihr(DataUtil.hUtil(FsUtil.eXmlDet(xml, "resCTe", 0, "dhEmi").substring(11, 19)));
				String nct = NumUtil.removeZeros(chaveresCTe.substring(25, 34));
				d.setNct(nct);
				d.setVct(FsUtil.eXmlDetBD(xml, "resCTe", 0, "vTPrest"));
				d.setCsitcte(FsUtil.eXmlDet(xml, "resCTe", 0, "cSitCTe"));
				d.setNsu(nsu);
				d.setSt_imp("N");
				d.setSt(1);// Define sem situacao
				fsCteDistRp.save(d);
			}
		}
		// CONSULTA EVENTOS - SE HOUVER
		String chaveresEvento = FsUtil.eXmlDet(xml, "procEventoCTe", 0, "chCTe");
		String tpEvento = FsUtil.eXmlDet(xml, "procEventoCTe", 0, "tpEvento");
		Integer nSeqEvento = FsUtil.eXmlDetInt(xml, "procEventoCTe", 0, "nSeqEvento");
		if (!chaveresEvento.equals("")) {
			FsCteManEvento drs = fsCteDistEventoRp.findFirstByChaveAndCdpessoaempAndTpeventoAndNseqevento(
					chaveresEvento, localemp, tpEvento, nSeqEvento);
			if (drs == null) {
				FsCteMan drcte = fsCteDistRp.findFirstByChaveAndCdpessoaemp(chaveresEvento, localemp);
				if (drcte != null) {
					FsCteManEvento d = new FsCteManEvento();
					d.setFscteman(drcte);
					d.setCdpessoaemp(localemp);
					d.setDhevento(DataUtil.dUtil(FsUtil.eXmlDet(xml, "procEventoCTe", 0, "dhEvento")));
					d.setDheventohr(
							DataUtil.hUtil(FsUtil.eXmlDet(xml, "procEventoCTe", 0, "dhEvento").substring(11, 19)));
					d.setTpevento(tpEvento);
					d.setNseqevento(nSeqEvento);
					d.setXevento(FsUtil.eXmlDet(xml, "procEventoCTe", 0, "xEvento"));
					d.setChave(chaveresEvento);
					d.setNprot(FsUtil.eXmlDet(xml, "procEventoCTe", 0, "nProt"));
					fsCteDistEventoRp.save(d);
				}
			}
		}
		// CONSULTA RESPOSTA COM XML JA MANIFESTADO OU ESTADO PERMITE DOWNLOAD
		String chaveprotCTe = FsUtil.eXmlDet(xml, "protCTe", 0, "chCTe");
		if (!chaveprotCTe.equals("")) {
			FsCteMan drs = fsCteDistRp.findFirstByChaveAndCdpessoaemp(chaveprotCTe, localemp);
			if (drs == null) {
				FsCteMan d = new FsCteMan();
				d.setCdpessoaemp(localemp);
				d.setChave(chaveprotCTe);
				d.setNprot(FsUtil.eXmlDet(xml, "protCTe", 0, "nProt"));
				d.setNome(FsUtil.eXmlDet(xml, "emit", 0, "xNome"));
				d.setCpfcnpj(FsUtil.eXmlDet(xml, "emit", 0, "CNPJ"));
				d.setIe(FsUtil.eXmlDet(xml, "emit", 0, "IE"));
				d.setDhemi(DataUtil.dUtil(FsUtil.eXmlDet(xml, "ide", 0, "dhEmi")));
				d.setDhemihr(DataUtil.hUtil(FsUtil.eXmlDet(xml, "ide", 0, "dhEmi").substring(11, 19)));
				String nct = NumUtil.removeZeros(chaveprotCTe.substring(25, 34));
				d.setNct(nct);
				d.setVct(FsUtil.eXmlDetBD(xml, "vPrest", 0, "vTPrest"));
				d.setCsitcte("1");
				d.setNsu(nsu);
				d.setSt_imp("N");
				d.setSt(210200);
				fsCteDistRp.save(d);
			}
		}
	}

	// IMPORTACAO DE XML
	public String importarXMLCte(File uploadedFile, Long idlocal) throws Exception {
		try {
			String retorno = "ok";
			// LEITURA
			FileReader arq = new FileReader(uploadedFile);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			// DADOS CONFERE
			String nct = "";
			String cpfcnpjdest = "";
			String cpfcnpjemit = "";
			String xml = "";
			while (linha != null) {
				xml += linha;
				linha = lerArq.readLine();
			}
			arq.close();
			// AJUSTE DE LEITURA PARA ARQUIVOS COM CARACTERES INVISIVEIS - ERRO SARX
			if (xml.contains("<?xml")) {
				xml = xml.substring(xml.indexOf("<?xml"));
			}
			// VERIFICA IMPORTACAO
			CteProc cte = FsUtil.xmlToObject(xml, CteProc.class);
			// VERIFICA SE ARQUIVO E VALIDO
			if (cte.getCTe() != null) {
				nct = cte.getCTe().getInfCte().getIde().getNCT();
				// Cnpj EMITENTE
				if (cte.getCTe().getInfCte().getEmit().getCNPJ() != null) {
					cpfcnpjemit = cte.getCTe().getInfCte().getEmit().getCNPJ();
				}
				// Cpf ou Cnpj DESTINATARIO
				if (cte.getCTe().getInfCte().getDest().getCPF() != null) {
					cpfcnpjdest = cte.getCTe().getInfCte().getDest().getCPF();
				}
				if (cte.getCTe().getInfCte().getDest().getCNPJ() != null) {
					cpfcnpjdest = cte.getCTe().getInfCte().getDest().getCNPJ();
				}
				if (fsCteRp.findByNctAndCpfcnpjemit(nct, cpfcnpjemit) == null) {
					// DESTINATARIO LOCAL
					Optional<CdPessoa> cdpessoaemp = cdPessoaRp.findById(idlocal);
					// VERIFICA SE PERTENCE A EMPRESA MESMO
					if (cdpessoaemp.get().getCpfcnpj().equals(cpfcnpjdest)) {
						// EXTRAI
						extrair.ExtraiCTe(xml, cdpessoaemp, cte);
					} else {
						retorno = "naopertence";
					}
				} else {
					retorno = "jaimportado";
				}
			} else {
				retorno = "arquivoinvalido";
			}
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// IMPORTACAO DE XML
	public String importarXMLCteChave(String xml, Long idlocal) throws Exception {
		try {
			String retorno = "ok";
			// DADOS CONFERE
			String nct = "";
			String cpfcnpjdest = "";
			String cpfcnpjrem = "";
			String cpfcnpjexped = "";
			String cpfcnpjreceb = "";
			String cpfcnpjemit = "";
			// AJUSTE DE LEITURA PARA ARQUIVOS COM CARACTERES INVISIVEIS - ERRO SARX
			if (xml.contains("<?xml")) {
				xml = xml.substring(xml.indexOf("<?xml"));
			}
			// VERIFICA IMPORTACAO
			CteProc cte = FsUtil.xmlToObject(xml, CteProc.class);
			// VERIFICA SE ARQUIVO E VALIDO
			if (cte.getCTe() != null) {
				nct = cte.getCTe().getInfCte().getIde().getNCT();
				// Cnpj EMITENTE
				if (cte.getCTe().getInfCte().getEmit().getCNPJ() != null) {
					cpfcnpjemit = cte.getCTe().getInfCte().getEmit().getCNPJ();
				}
				// Cpf ou Cnpj DESTINATARIO
				if (cte.getCTe().getInfCte().getDest().getCPF() != null) {
					cpfcnpjdest = cte.getCTe().getInfCte().getDest().getCPF();
				}
				if (cte.getCTe().getInfCte().getDest().getCNPJ() != null) {
					cpfcnpjdest = cte.getCTe().getInfCte().getDest().getCNPJ();
				}
				// Cpf ou Cnpj REMETENTE
				if (cte.getCTe().getInfCte().getRem() != null) {
					if (cte.getCTe().getInfCte().getRem().getCPF() != null) {
						cpfcnpjrem = cte.getCTe().getInfCte().getRem().getCPF();
					}
					if (cte.getCTe().getInfCte().getRem().getCNPJ() != null) {
						cpfcnpjrem = cte.getCTe().getInfCte().getRem().getCNPJ();
					}
				}
				// Cpf ou Cnpj EXPEDIDOR
				if (cte.getCTe().getInfCte().getExped() != null) {
					if (cte.getCTe().getInfCte().getExped().getCPF() != null) {
						cpfcnpjexped = cte.getCTe().getInfCte().getExped().getCPF();
					}
					if (cte.getCTe().getInfCte().getExped().getCNPJ() != null) {
						cpfcnpjexped = cte.getCTe().getInfCte().getExped().getCNPJ();
					}
				}
				// Cpf ou Cnpj RECEBEDOR
				if (cte.getCTe().getInfCte().getReceb() != null) {
					if (cte.getCTe().getInfCte().getReceb().getCPF() != null) {
						cpfcnpjreceb = cte.getCTe().getInfCte().getReceb().getCPF();
					}
					if (cte.getCTe().getInfCte().getReceb().getCNPJ() != null) {
						cpfcnpjreceb = cte.getCTe().getInfCte().getReceb().getCNPJ();
					}
				}
				if (fsCteRp.findByNctAndCpfcnpjemit(nct, cpfcnpjemit) == null) {
					// DESTINATARIO LOCAL
					Optional<CdPessoa> cdpessoaemp = cdPessoaRp.findById(idlocal);
					// VERIFICA SE PERTENCE A EMPRESA MESMO
					if (cdpessoaemp.get().getCpfcnpj().equals(cpfcnpjdest)
							|| cdpessoaemp.get().getCpfcnpj().equals(cpfcnpjrem)
							|| cdpessoaemp.get().getCpfcnpj().equals(cpfcnpjexped)
							|| cdpessoaemp.get().getCpfcnpj().equals(cpfcnpjreceb)) {
						// EXTRAI
						extrair.ExtraiCTe(xml, cdpessoaemp, cte);
					} else {
						retorno = "naopertence";
					}
				} else {
					retorno = "jaimportado";
				}
			} else {
				retorno = "arquivoinvalido";
			}
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// IMPORTACAO DE XML - MULTIPLOS
	public String[] importarXMLCteMultiplos(File uploadedFile, Long idlocal) throws Exception {
		try {
			String retorno[] = new String[6];
			// LEITURA
			FileReader arq = new FileReader(uploadedFile);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			// DADOS CONFERE
			String nct = "";
			String cpfcnpjdest = "";
			String cpfcnpjemit = "";
			String xml = "";
			while (linha != null) {
				xml += linha;
				linha = lerArq.readLine();
			}
			arq.close();
			// AJUSTE DE LEITURA PARA ARQUIVOS COM CARACTERES INVISIVEIS - ERRO SARX
			if (xml.contains("<?xml")) {
				xml = xml.substring(xml.indexOf("<?xml"));
			}
			// VERIFICA IMPORTACAO
			CteProc cte = FsUtil.xmlToObject(xml, CteProc.class);
			// VERIFICA SE ARQUIVO E VALIDO
			if (cte.getCTe() != null) {
				nct = cte.getCTe().getInfCte().getIde().getNCT();
				// Cnpj EMITENTE
				if (cte.getCTe().getInfCte().getEmit().getCNPJ() != null) {
					cpfcnpjemit = cte.getCTe().getInfCte().getEmit().getCNPJ();
				}
				// Cpf ou Cnpj DESTINATARIO
				if (cte.getCTe().getInfCte().getDest().getCPF() != null) {
					cpfcnpjdest = cte.getCTe().getInfCte().getDest().getCPF();
				}
				if (cte.getCTe().getInfCte().getDest().getCNPJ() != null) {
					cpfcnpjdest = cte.getCTe().getInfCte().getDest().getCNPJ();
				}
				if (fsCteRp.findByNctAndCpfcnpjemit(nct, cpfcnpjemit) == null) {
					// DESTINATARIO LOCAL
					Optional<CdPessoa> cdpessoaemp = cdPessoaRp.findById(idlocal);
					// VERIFICA SE PERTENCE A EMPRESA MESMO
					if (cdpessoaemp.get().getCpfcnpj().equals(cpfcnpjdest)) {
						// EXTRAI
						extrair.ExtraiCTe(xml, cdpessoaemp, cte);
						retorno[0] = "ok";
						retorno[1] = cte.getCTe().getInfCte().getIde().getNCT();
						retorno[2] = cte.getCTe().getInfCte().getEmit().getXNome();
						retorno[3] = cte.getCTe().getInfCte().getIde().getNatOp();
						retorno[4] = uploadedFile.getName();
						retorno[5] = cte.getProtCTe().getInfProt().getXMotivo();
					} else {
						retorno[0] = "naopertence";
						retorno[1] = cte.getCTe().getInfCte().getIde().getNCT();
						retorno[2] = cte.getCTe().getInfCte().getEmit().getXNome();
						retorno[3] = cte.getCTe().getInfCte().getIde().getNatOp();
						retorno[4] = uploadedFile.getName();
						retorno[5] = cte.getProtCTe().getInfProt().getXMotivo();
					}
				} else {
					retorno[0] = "jaimportado";
					retorno[1] = cte.getCTe().getInfCte().getIde().getNCT();
					retorno[2] = cte.getCTe().getInfCte().getEmit().getXNome();
					retorno[3] = cte.getCTe().getInfCte().getIde().getNatOp();
					retorno[4] = uploadedFile.getName();
					retorno[5] = cte.getProtCTe().getInfProt().getXMotivo();
				}
			} else {
				retorno[0] = "arquivoinvalido";
				retorno[1] = " - ";
				retorno[2] = " - ";
				retorno[3] = " - ";
				retorno[4] = uploadedFile.getName();
				retorno[5] = " - ";
			}
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional("tenantTransactionManager")
	public String importaTransportadora(Long id, Integer idgrupes) throws Exception {
		FsCte fscte = fsCteRp.getById(id);
		CdPessoa p = cdPessoaRp.findFirstByCpfcnpj(fscte.getFsctepartemit().getCpfcnpj());
		// Atualiza de outras notas tambem se ja houver
		List<FsCte> ctes = fsCteRp.findAllByIdCnpjTipoStTransp(fscte.getFsctepartemit().getCpfcnpj(), "R", "N");
		for (FsCte c : ctes) {
			c.setSt_transp("S");
			fsCteRp.save(c);
		}
		if (p == null) {
			CdPessoaGrupo cg = cdPessoaGrupoRp.getById(idgrupes);
			CdPessoa f = new CdPessoa();
			f.setTipo("TRANSPORTADORA");
			f.setEmp(fscte.getCdpessoaemp().getId());
			f.setCdpessoagrupo(cg);
			f.setNome(fscte.getFsctepartemit().getXnome());
			f.setFantasia(fscte.getFsctepartemit().getXfant());
			f.setCpfcnpj(fscte.getFsctepartemit().getCpfcnpj());
			f.setIerg(fscte.getFsctepartemit().getIe());
			f.setFoneum(fscte.getFsctepartemit().getFone());
			f.setEmail(fscte.getFsctepartemit().getEmail());
			f.setRua(fscte.getFsctepartemit().getXlgr());
			f.setNum(fscte.getFsctepartemit().getNro());
			f.setComp(fscte.getFsctepartemit().getXcpl());
			f.setBairro(fscte.getFsctepartemit().getXbairro());
			f.setCdcidade(cdCidadeRp.findByIbge(fscte.getFsctepartemit().getCmun()));
			f.setCdestado(cdEstadoRp.findByUf(fscte.getFsctepartemit().getUf()));
			f.setCep(fscte.getFsctepartemit().getCep());
			f.setStatus("ATIVO");
			cdPessoaRp.save(f);
			// Marca conferido
			fscte.setSt_transp("S");
			fsCteRp.save(fscte);
			return "ok";
		}
		return "";
	}

	@Transactional("tenantTransactionManager")
	public String importaCobranca(List<FnTitulo> fnTitulos, Long id) throws Exception {
		FsCte fscte = fsCteRp.getById(id);
		CdPessoa para = cdPessoaRp.findFirstByCpfcnpj(fscte.getCpfcnpjemit());
		if (para != null) {
			// Ajusta dados padroes faltantes da lista
			for (int x = 0; x < fnTitulos.size(); x++) {
				fnTitulos.get(x).setCdpessoaemp(fscte.getCdpessoaemp());
				fnTitulos.get(x).setCdpessoapara(para);
			}
			List<FnTitulo> titulos = fnTituloRp.saveAll(fnTitulos);
			for (FnTitulo fn : titulos) {
				// DREs
				for (FnTituloDre fd : fn.getFntitulodreitem()) {
					fd.setFntitulo(fn);
					fnTituloDreRp.save(fd);
				}
				// Centros
				for (FnTituloCcusto fd : fn.getFntituloccustoitem()) {
					fd.setFntitulo(fn);
					fnTituloCcustoRp.save(fd);
				}
			}
			// Marca conferido
			fscte.setSt_cob("S");
			fsCteRp.save(fscte);
			return "ok";
		} else {
			return "naocadastrado";
		}
	}

	// CALCULOS ICMS CTE-SIMPLES
	public void calculoIcms(FsCte c) {
		CdCteCfg ctecfg = c.getCdctecfg();
		BigDecimal vtprest = c.getFsctevprest().getVtprest();
		// ICMS - quando nao for do Simples nacional
		BigDecimal vBC = vtprest;
		if (vBC.compareTo(BigDecimal.ZERO) > 0) {
			if (!ctecfg.getCst().equals("0")) {
				BigDecimal pICMS = ctecfg.getIcms_aliq();
				BigDecimal pICMSst = ctecfg.getIcmsst_aliq();
				BigDecimal pRBC = ctecfg.getIcms_redbc();
				BigDecimal vICMS = new BigDecimal(0);
				BigDecimal vICMSst = new BigDecimal(0);
				// Calculo RBC de houver -----------------
				if (pRBC.compareTo(BigDecimal.ZERO) > 0) {
					vBC = vBC.multiply(pRBC).divide(new BigDecimal(100));
				}
				vICMS = vBC.multiply(pICMS).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
				vICMSst = vBC.multiply(pICMSst).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
				fsCteIcmsRp.updateICMS(pICMS, pICMSst, pRBC, vBC, vICMS, vICMSst, c.getFscteicms().getId());
			}
			BigDecimal vIBPT = new BigDecimal(0);
			BigDecimal pIBPT = ctecfg.getPibpt();
			if (pIBPT.compareTo(BigDecimal.ZERO) > 0) {
				vIBPT = vtprest.multiply(pIBPT).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
			}
			fsCteIcmsRp.updateVIBPT(vIBPT, c.getFscteicms().getId());
		}
	}

	// CONSULTA PADRAO CTE DESTINADAS CNPJ************************{
	public String[] consultaDist(Long idlocal, String ambiente, HttpServletRequest request) throws Exception {
		// Inicializa dados padroes
		FsCteManCons obj = fsCteManConsRp.findByLocal(idlocal);
		if (obj == null) {
			CdPessoa local = cdPessoaRp.getById(idlocal);
			FsCteManCons c = new FsCteManCons();
			c.setCdpessoaemp(local);
			c.setUltimonsu("000000000000000");
			c.setData(new java.sql.Date(System.currentTimeMillis()));
			c.setHora(DataUtil.addRemMinTime(new Time(System.currentTimeMillis()), mc.tempobuscaman + 1, "R"));
			obj = fsCteManConsRp.save(c);
		}
		// Comparando datas atuais
		boolean comData = DataUtil.comparaDataAtualMenor(obj.getData());
		// Calculo de tempo para consultas
		long diffMin = diffMinFunc(obj);
		// NSU Maximo - total de itens ainda faltantes-sempre da consulta
		Long maxNSU = 999999999999999L;
		Long ultNSU = Long.valueOf(obj.getUltimonsu());
		String ret[] = new String[4];
		ret[0] = "000";
		String status = "138";
		// Mantem While enquanto menor---------------------
		fsCteManConsRp.updateHora(obj.getUltimonsu(), idlocal);
		if (diffMin > mc.tempobuscaman || comData == true) {
			obj = fsCteManConsRp.findByLocal(idlocal);
			diffMin = diffMinFunc(obj);
			if (ret[2] == null) {
				ret[2] = obj.getUltimonsu();
			}
			ret[2] = NumUtil.geraNumEsq(Integer.valueOf(ret[2]), 15);
			while (ultNSU <= maxNSU && status.equals("138")) {
				ret = fsCteEnvioService.consultaDistDest(idlocal, ambiente, ultNSU, maxNSU, request);
				status = ret[0];
				ultNSU = Long.valueOf(ret[2]);
				maxNSU = Long.valueOf(ret[3]); // Atualiza com Ultimo e hora
				String ultimoNSU = NumUtil.geraNumEsq(Integer.valueOf(ultNSU + ""), 15);
				fsCteManConsRp.updateHora(ultimoNSU, idlocal);
				// Tempo aguardo e retoma----4 minutos
				Thread.sleep(240000);
			}
			Thread.sleep(5000);
		}
		return ret;
	}

	// Calculo hora-tempo
	private long diffMinFunc(FsCteManCons obj) {
		Time horacons = Time.valueOf(obj.getHora().toString());
		Time horaatual = Time.valueOf(DataUtil.dataBancoTimeAtual());
		long diff = horaatual.getTime() - horacons.getTime();
		long diffMin = diff / (60 * 1000);
		return diffMin;
	}
}
