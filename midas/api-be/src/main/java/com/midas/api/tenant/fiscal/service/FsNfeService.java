package com.midas.api.tenant.fiscal.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midas.api.constant.MidasConfig;
import com.midas.api.tenant.entity.AuxDados;
import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.CdNfeCfgSimples;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdPessoaGrupo;
import com.midas.api.tenant.entity.EsEst;
import com.midas.api.tenant.entity.EsEstMov;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FnTituloCcusto;
import com.midas.api.tenant.entity.FnTituloDre;
import com.midas.api.tenant.entity.FsInut;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfeItem;
import com.midas.api.tenant.entity.FsNfeItemCofins;
import com.midas.api.tenant.entity.FsNfeItemIcms;
import com.midas.api.tenant.entity.FsNfeItemIpi;
import com.midas.api.tenant.entity.FsNfeItemPis;
import com.midas.api.tenant.entity.FsNfeMan;
import com.midas.api.tenant.entity.FsNfeManCons;
import com.midas.api.tenant.entity.FsNfeManEvento;
import com.midas.api.tenant.fiscal.XmlExtrairNfe;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.repository.CdCidadeRepository;
import com.midas.api.tenant.repository.CdEstadoRepository;
import com.midas.api.tenant.repository.CdNfeCfgSimplesRepository;
import com.midas.api.tenant.repository.CdPessoaGrupoRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.EsEstMovRepository;
import com.midas.api.tenant.repository.EsEstRepository;
import com.midas.api.tenant.repository.FnTituloCcustoRepository;
import com.midas.api.tenant.repository.FnTituloDreRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.repository.FsInutRepository;
import com.midas.api.tenant.repository.FsNfeItemCofinsRepository;
import com.midas.api.tenant.repository.FsNfeItemIcmsRepository;
import com.midas.api.tenant.repository.FsNfeItemIpiRepository;
import com.midas.api.tenant.repository.FsNfeItemPisRepository;
import com.midas.api.tenant.repository.FsNfeItemRepository;
import com.midas.api.tenant.repository.FsNfeManConsRepository;
import com.midas.api.tenant.repository.FsNfeManEventoRepository;
import com.midas.api.tenant.repository.FsNfeManRepository;
import com.midas.api.tenant.repository.FsNfeRepository;
import com.midas.api.tenant.repository.FsNfeTotIcmsRepository;
import com.midas.api.tenant.service.EsEstService;
import com.midas.api.util.CNPJConsUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.NumUtil;

import br.inf.portalfiscal.nfe.TNfeProc;

@Service
public class FsNfeService {
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private FsNfeItemRepository fsNfeItemRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdPessoaGrupoRepository cdGruPesRp;
	@Autowired
	private CdEstadoRepository cdEstadoRp;
	@Autowired
	private CdCidadeRepository cdCidadeRp;
	@Autowired
	private FsNfeManRepository fsNfeDistRp;
	@Autowired
	private FsNfeManEventoRepository fsNfeDistEventoRp;
	@Autowired
	private XmlExtrairNfe extrair;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private FnTituloDreRepository fnTituloDreRp;
	@Autowired
	private FnTituloCcustoRepository fnTituloCcustoRp;
	@Autowired
	private EsEstService esEstService;
	@Autowired
	private CdNfeCfgSimplesRepository cdNfeCfgSimplesRp;
	@Autowired
	private FsNfeTributoService fsNfeTributoService;
	@Autowired
	private FsNfeTotIcmsRepository fsNfeTotIcmsRp;
	@Autowired
	private FsNfeItemIcmsRepository fsNfeItemIcmsRp;
	@Autowired
	private FsNfeItemPisRepository fsNfeItemPisRp;
	@Autowired
	private FsNfeItemCofinsRepository fsNfeItemCofinsRp;
	@Autowired
	private FsNfeItemIpiRepository fsNfeItemIpiRp;
	@Autowired
	private EsEstRepository esEstRp;
	@Autowired
	private EsEstMovRepository esEstMovRp;
	@Autowired
	private FsNfeEnvioService fsNfeServiceEnvio;
	@Autowired
	private FsNfeManConsRepository fsNfeManConsRp;
	@Autowired
	private FsInutRepository fsInutRp;
	@Autowired
	private MidasConfig mc;

	// IMPORTACAO DE XML
	public String importarXMLNfeChave(String xml, Long idlocal) throws Exception {
		try {
			String retorno = "ok";
			// DADOS CONFERE
			String nnf = "";
			String cpfcnpjdest = "";
			String cpfcnpjemit = "";
			// AJUSTE DE LEITURA PARA ARQUIVOS COM CARACTERES INVISIVEIS - ERRO SARX
			if (xml.contains("<?xml")) {
				xml = xml.substring(xml.indexOf("<?xml"));
			}
			// VERIFICA IMPORTACAO
			TNfeProc nfe = FsUtil.xmlToObject(xml, TNfeProc.class);
			// VERIFICA SE ARQUIVO E VALIDO
			if (nfe.getNFe() != null) {
				nnf = nfe.getNFe().getInfNFe().getIde().getNNF();
				// Cpf ou Cnpj EMITENTE
				if (nfe.getNFe().getInfNFe().getEmit().getCPF() != null) {
					cpfcnpjemit = nfe.getNFe().getInfNFe().getEmit().getCPF();
				}
				if (nfe.getNFe().getInfNFe().getEmit().getCNPJ() != null) {
					cpfcnpjemit = nfe.getNFe().getInfNFe().getEmit().getCNPJ();
				}
				// Cpf ou Cnpj DESTINATARIO
				if (nfe.getNFe().getInfNFe().getDest().getCPF() != null) {
					cpfcnpjdest = nfe.getNFe().getInfNFe().getDest().getCPF();
				}
				if (nfe.getNFe().getInfNFe().getDest().getCNPJ() != null) {
					cpfcnpjdest = nfe.getNFe().getInfNFe().getDest().getCNPJ();
				}
				if (fsNfeRp.findByNnfAndCpfcnpjemit(nnf, cpfcnpjemit) == null) {
					// DESTINATARIO LOCAL
					Optional<CdPessoa> cdpessoaemp = cdPessoaRp.findById(idlocal);
					// VERIFICA SE PERTENCE A EMPRESA MESMO
					if (cdpessoaemp.get().getCpfcnpj().equals(cpfcnpjdest)) {
						// EXTRAI
						extrair.ExtraiNFe(xml, cdpessoaemp, nfe);
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
	public String importarXMLNfe(File uploadedFile, Long idlocal) throws Exception {
		try {
			String retorno = "ok";
			// LEITURA
			FileReader arq = new FileReader(uploadedFile);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			// DADOS CONFERE
			String nnf = "";
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
			TNfeProc nfe = FsUtil.xmlToObject(xml, TNfeProc.class);
			// VERIFICA SE ARQUIVO E VALIDO
			if (nfe.getNFe() != null) {
				nnf = nfe.getNFe().getInfNFe().getIde().getNNF();
				// Cpf ou Cnpj EMITENTE
				if (nfe.getNFe().getInfNFe().getEmit().getCPF() != null) {
					cpfcnpjemit = nfe.getNFe().getInfNFe().getEmit().getCPF();
				}
				if (nfe.getNFe().getInfNFe().getEmit().getCNPJ() != null) {
					cpfcnpjemit = nfe.getNFe().getInfNFe().getEmit().getCNPJ();
				}
				// Cpf ou Cnpj DESTINATARIO
				if (nfe.getNFe().getInfNFe().getDest().getCPF() != null) {
					cpfcnpjdest = nfe.getNFe().getInfNFe().getDest().getCPF();
				}
				if (nfe.getNFe().getInfNFe().getDest().getCNPJ() != null) {
					cpfcnpjdest = nfe.getNFe().getInfNFe().getDest().getCNPJ();
				}
				if (fsNfeRp.findByNnfAndCpfcnpjemit(nnf, cpfcnpjemit) == null) {
					// DESTINATARIO LOCAL
					Optional<CdPessoa> cdpessoaemp = cdPessoaRp.findById(idlocal);
					// VERIFICA SE PERTENCE A EMPRESA MESMO
					if (cdpessoaemp.get().getCpfcnpj().equals(cpfcnpjdest)) {
						// EXTRAI
						extrair.ExtraiNFe(xml, cdpessoaemp, nfe);
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
	public String[] importarXMLNfeMultiplos(File uploadedFile, Long idlocal) throws Exception {
		try {
			String retorno[] = new String[6];
			// LEITURA
			FileReader arq = new FileReader(uploadedFile);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			// DADOS CONFERE
			String nnf = "";
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
			TNfeProc nfe = FsUtil.xmlToObject(xml, TNfeProc.class);
			// VERIFICA SE ARQUIVO E VALIDO
			if (nfe.getNFe() != null) {
				nnf = nfe.getNFe().getInfNFe().getIde().getNNF();
				// Cpf ou Cnpj EMITENTE
				if (nfe.getNFe().getInfNFe().getEmit().getCPF() != null) {
					cpfcnpjemit = nfe.getNFe().getInfNFe().getEmit().getCPF();
				}
				if (nfe.getNFe().getInfNFe().getEmit().getCNPJ() != null) {
					cpfcnpjemit = nfe.getNFe().getInfNFe().getEmit().getCNPJ();
				}
				// Cpf ou Cnpj DESTINATARIO
				if (nfe.getNFe().getInfNFe().getDest().getCPF() != null) {
					cpfcnpjdest = nfe.getNFe().getInfNFe().getDest().getCPF();
				}
				if (nfe.getNFe().getInfNFe().getDest().getCNPJ() != null) {
					cpfcnpjdest = nfe.getNFe().getInfNFe().getDest().getCNPJ();
				}
				if (fsNfeRp.findByNnfAndCpfcnpjemit(nnf, cpfcnpjemit) == null) {
					// DESTINATARIO LOCAL
					Optional<CdPessoa> cdpessoaemp = cdPessoaRp.findById(idlocal);
					// VERIFICA SE PERTENCE A EMPRESA MESMO
					if (cdpessoaemp.get().getCpfcnpj().equals(cpfcnpjdest)) {
						// EXTRAI
						extrair.ExtraiNFe(xml, cdpessoaemp, nfe);
						retorno[0] = "ok";
						retorno[1] = nfe.getNFe().getInfNFe().getIde().getNNF();
						retorno[2] = nfe.getNFe().getInfNFe().getEmit().getXNome();
						retorno[3] = nfe.getNFe().getInfNFe().getIde().getNatOp();
						retorno[4] = uploadedFile.getName();
						retorno[5] = nfe.getProtNFe().getInfProt().getXMotivo();
					} else {
						retorno[0] = "naopertence";
						retorno[1] = nfe.getNFe().getInfNFe().getIde().getNNF();
						retorno[2] = nfe.getNFe().getInfNFe().getEmit().getXNome();
						retorno[3] = nfe.getNFe().getInfNFe().getIde().getNatOp();
						retorno[4] = uploadedFile.getName();
						retorno[5] = nfe.getProtNFe().getInfProt().getXMotivo();
					}
				} else {
					retorno[0] = "jaimportado";
					retorno[1] = nfe.getNFe().getInfNFe().getIde().getNNF();
					retorno[2] = nfe.getNFe().getInfNFe().getEmit().getXNome();
					retorno[3] = nfe.getNFe().getInfNFe().getIde().getNatOp();
					retorno[4] = uploadedFile.getName();
					retorno[5] = nfe.getProtNFe().getInfProt().getXMotivo();
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
	public String importaFornecedor(Long id, Integer idgrupes) throws Exception {
		FsNfe fsnfe = fsNfeRp.getById(id);
		CdPessoa p = cdPessoaRp.findFirstByCpfcnpj(fsnfe.getFsnfepartemit().getCpfcnpj());
		// Atualiza de outras notas tambem se ja houver
		List<FsNfe> nfes = fsNfeRp.findAllByIdCnpjTipoStFornec(fsnfe.getFsnfepartemit().getCpfcnpj(), "R", "N");
		for (FsNfe n : nfes) {
			n.setSt_fornec("S");
			fsNfeRp.save(n);
		}
		if (p == null) {
			CdPessoaGrupo cg = cdGruPesRp.getById(idgrupes);
			CdPessoa f = new CdPessoa();
			f.setTipo("FORNECEDOR");
			f.setEmp(fsnfe.getCdpessoaemp().getId());
			f.setCdpessoagrupo(cg);
			f.setNome(fsnfe.getFsnfepartemit().getXnome());
			f.setFantasia(fsnfe.getFsnfepartemit().getXfant());
			f.setCpfcnpj(fsnfe.getFsnfepartemit().getCpfcnpj());
			f.setIerg(fsnfe.getFsnfepartemit().getIe());
			f.setFoneum(fsnfe.getFsnfepartemit().getFone());
			if (fsnfe.getFsnfepartemit().getEmail() != null) {
				if (!fsnfe.getFsnfepartemit().getEmail().equals("")) {
					f.setEmail(fsnfe.getFsnfepartemit().getEmail());
				}
			} else {
				f.setEmail("email@email.com");
			}
			f.setRua(fsnfe.getFsnfepartemit().getXlgr());
			f.setNum(fsnfe.getFsnfepartemit().getNro());
			f.setComp(fsnfe.getFsnfepartemit().getXcpl());
			f.setBairro(fsnfe.getFsnfepartemit().getXbairro());
			f.setCdcidade(cdCidadeRp.findByIbge(fsnfe.getFsnfepartemit().getCmun()));
			f.setCdestado(cdEstadoRp.findByUf(fsnfe.getFsnfepartemit().getUf()));
			f.setCep(fsnfe.getFsnfepartemit().getCep());
			f.setIdestrangeiro("-");
			String indiedest = CNPJConsUtil.cIcms(fsnfe.getFsnfepartemit());
			f.setIndiedest(indiedest);
			f.setStatus("ATIVO");
			cdPessoaRp.save(f);
			// Marca conferido
			fsnfe.setSt_fornec("S");
			fsNfeRp.save(fsnfe);
			return "ok";
		}
		return "";
	}

	@Transactional("tenantTransactionManager")
	public String importaCobranca(List<FnTitulo> fnTitulos, Long id) throws Exception {
		FsNfe fsnfe = fsNfeRp.getById(id);
		CdPessoa para = cdPessoaRp.findFirstByCpfcnpj(fsnfe.getCpfcnpjemit());
		if (para != null) {
			// Ajusta dados padroes faltantes da lista
			for (int x = 0; x < fnTitulos.size(); x++) {
				fnTitulos.get(x).setCdpessoaemp(fsnfe.getCdpessoaemp());
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
			fsnfe.setSt_cob("S");
			fsNfeRp.save(fsnfe);
			return "ok";
		} else {
			return "naocadastrado";
		}
	}

	@Transactional("tenantTransactionManager")
	public String importaEstoque(Long id) throws Exception {
		FsNfe fsnfe = fsNfeRp.getById(id);
		Integer qtdSV = fsNfeItemRp.getCountFsnfeitemSemVinculo(id);
		if (qtdSV == 0) {
			CdPessoa para = cdPessoaRp.findFirstByCpfcnpj(fsnfe.getCpfcnpjemit());
			if (para != null) {
				esEstService.geradorFsNfe(fsnfe, "E");
				// Marca conferido
				fsnfe.setSt_est("S");
				fsNfeRp.save(fsnfe);
				return "ok";
			} else {
				return "naocadastrado";
			}
		} else {
			return "naovinculado";
		}
	}

	@Transactional("tenantTransactionManager")
	public void importaDist(String xml, String nsu, CdPessoa localemp) throws Exception {
		// CONSULTA RESPOSTA SEM XML
		String chaveresNFe = FsUtil.eXmlDet(xml, "resNFe", 0, "chNFe");
		if (!chaveresNFe.equals("")) {
			FsNfeMan drs = fsNfeDistRp.findFirstByChaveAndCdpessoaemp(chaveresNFe, localemp);
			if (drs == null) {
				FsNfeMan d = new FsNfeMan();
				d.setCdpessoaemp(localemp);
				d.setChave(chaveresNFe);
				d.setNprot(FsUtil.eXmlDet(xml, "resNFe", 0, "nProt"));
				d.setNome(FsUtil.eXmlDet(xml, "resNFe", 0, "xNome"));
				d.setCpfcnpj(FsUtil.eXmlDet(xml, "resNFe", 0, "CNPJ"));
				d.setIe(FsUtil.eXmlDet(xml, "resNFe", 0, "IE"));
				d.setDhemi(DataUtil.dUtil(FsUtil.eXmlDet(xml, "resNFe", 0, "dhEmi")));
				d.setDhemihr(DataUtil.hUtil(FsUtil.eXmlDet(xml, "resNFe", 0, "dhEmi").substring(11, 19)));
				String nnf = NumUtil.removeZeros(chaveresNFe.substring(25, 34));
				d.setNnf(nnf);
				d.setVnf(FsUtil.eXmlDetBD(xml, "resNFe", 0, "vNF"));
				d.setCsitnfe(FsUtil.eXmlDet(xml, "resNFe", 0, "cSitNFe"));
				d.setNsu(nsu);
				d.setSt_imp("N");
				d.setSt(1);// Define sem situacao
				fsNfeDistRp.save(d);
			}
		}
		// CONSULTA EVENTOS - SE HOUVER
		String chaveresEvento = FsUtil.eXmlDet(xml, "resEvento", 0, "chNFe");
		String tpEvento = FsUtil.eXmlDet(xml, "resEvento", 0, "tpEvento");
		Integer nSeqEvento = FsUtil.eXmlDetInt(xml, "resEvento", 0, "nSeqEvento");
		if (!chaveresEvento.equals("")) {
			FsNfeManEvento drs = fsNfeDistEventoRp.findFirstByChaveAndCdpessoaempAndTpeventoAndNseqevento(
					chaveresEvento, localemp, tpEvento, nSeqEvento);
			if (drs == null) {
				FsNfeMan drnfe = fsNfeDistRp.findFirstByChaveAndCdpessoaemp(chaveresEvento, localemp);
				if (drnfe != null) {
					FsNfeManEvento d = new FsNfeManEvento();
					d.setFsnfeman(drnfe);
					d.setCdpessoaemp(localemp);
					d.setDhevento(DataUtil.dUtil(FsUtil.eXmlDet(xml, "resEvento", 0, "dhEvento")));
					d.setDheventohr(DataUtil.hUtil(FsUtil.eXmlDet(xml, "resEvento", 0, "dhEvento").substring(11, 19)));
					d.setTpevento(tpEvento);
					d.setNseqevento(nSeqEvento);
					d.setXevento(FsUtil.eXmlDet(xml, "resEvento", 0, "xEvento"));
					d.setChave(chaveresEvento);
					d.setNprot(FsUtil.eXmlDet(xml, "resEvento", 0, "nProt"));
					fsNfeDistEventoRp.save(d);
				}
			}
		}
		// CONSULTA RESPOSTA COM XML JA MANIFESTADO OU ESTADO PERMITE DOWNLOAD
		String chaveprotNFe = FsUtil.eXmlDet(xml, "protNFe", 0, "chNFe");
		if (!chaveprotNFe.equals("")) {
			FsNfeMan drs = fsNfeDistRp.findFirstByChaveAndCdpessoaemp(chaveprotNFe, localemp);
			if (drs == null) {
				FsNfeMan d = new FsNfeMan();
				d.setCdpessoaemp(localemp);
				d.setChave(chaveprotNFe);
				d.setNprot(FsUtil.eXmlDet(xml, "protNFe", 0, "nProt"));
				d.setNome(FsUtil.eXmlDet(xml, "emit", 0, "xNome"));
				d.setCpfcnpj(FsUtil.eXmlDet(xml, "emit", 0, "CNPJ"));
				d.setIe(FsUtil.eXmlDet(xml, "emit", 0, "IE"));
				d.setDhemi(DataUtil.dUtil(FsUtil.eXmlDet(xml, "ide", 0, "dhEmi")));
				d.setDhemihr(DataUtil.hUtil(FsUtil.eXmlDet(xml, "ide", 0, "dhEmi").substring(11, 19)));
				String nnf = FsUtil.eXmlDet(xml, "ide", 0, "nNF");
				d.setNnf(nnf);
				d.setVnf(FsUtil.eXmlDetBD(xml, "ICMSTot", 0, "vNF"));
				d.setCsitnfe("1");
				d.setNsu(nsu);
				d.setSt_imp("N");
				d.setSt(210200);
				fsNfeDistRp.save(d);
			}
		}
	}

	@Transactional("tenantTransactionManager")
	public void addItemManual(FsNfeItem fsNfeItem, FsNfe fsnfe) throws Exception {
		fsNfeItem.setFsnfe(fsnfe);
		// ICMS------------
		FsNfeItemIcms ic = new FsNfeItemIcms();
		ic.setFsnfe_id(fsnfe.getId());
		ic.setOrig(fsNfeItem.getOrig());
		ic.setCst(fsnfe.getCdnfecfg().getCst());
		ic.setModbc(Integer.valueOf(fsnfe.getCdnfecfg().getIcms_modbc()));
		ic.setModbcst(Integer.valueOf(fsnfe.getCdnfecfg().getIcmsst_modbc()));
		ic.setUfst("");
		ic.setMotdesicmsst(0);
		fsNfeItemIcmsRp.save(ic);
		fsNfeItem.setFsnfeitemicms(ic);
		// PIS E COFINS
		FsNfeItemPis pis = new FsNfeItemPis();
		pis.setFsnfe_id(fsnfe.getId());
		pis.setCst(fsnfe.getCdnfecfg().getPis());
		pis.setIndsomapisst(1);
		fsNfeItemPisRp.save(pis);
		fsNfeItem.setFsnfeitempis(pis);
		FsNfeItemCofins cof = new FsNfeItemCofins();
		cof.setFsnfe_id(fsnfe.getId());
		cof.setCst(fsnfe.getCdnfecfg().getCofins());
		cof.setIndsomacofinsst(1);
		fsNfeItemCofinsRp.save(cof);
		fsNfeItem.setFsnfeitemcofins(cof);
		// IPI-------------
		FsNfeItemIpi ipi = new FsNfeItemIpi();
		ipi.setFsnfe_id(fsnfe.getId());
		ipi.setCenq(fsnfe.getCdnfecfg().getIpi_classeenq());
		ipi.setCst(fsnfe.getCdnfecfg().getIpi());
		fsNfeItemIpiRp.save(ipi);
		fsNfeItem.setFsnfeitemipi(ipi);
		fsNfeItemRp.save(fsNfeItem);
	}

	// DIVISAO FRETE
	private BigDecimal divisaoFrete(FsNfe nfe) throws Exception {
		BigDecimal vFrete = new BigDecimal(0);
		if (nfe.getFsnfetoticms().getVfrete().compareTo(BigDecimal.ZERO) > 0) {
			if (nfe.getFsnfeitems().size() > 0) {
				vFrete = nfe.getFsnfetoticms().getVfrete().divide(new BigDecimal(nfe.getFsnfeitems().size()), 6,
						RoundingMode.HALF_EVEN);
			}
		}
		return vFrete;
	}

	// CALCULO DOS TRIBUTOS DA EMISSAO *************************************
	public void calculoTributoFsNfe(FsNfe nfe) throws Exception {
		if (nfe.getTipo().equals("E")) {
			// Verifica se tem itens na nota
			if (nfe.getFsnfeitems().size() == 0) {
				fsNfeTotIcmsRp.updateTributosZera(nfe.getId());
			} else {
				// Divisao frete
				BigDecimal vFrete = divisaoFrete(nfe);
				// Busca simples, mesmo se nao for ou houver config.
				CdNfeCfgSimples csn = cdNfeCfgSimplesRp.getByCdpessoaemp(nfe.getCdpessoaemp().getId());
				// Força usar Cfg. fiscal do documento
				if (nfe.getUsacfgfiscal().equals("S")) {
					fsNfeItemRp.updateCdNfeCfg(nfe.getCdnfecfg(), nfe.getId());
				}
				for (FsNfeItem i : nfe.getFsnfeitems()) {
					// Verifica se todos os itens possuem CdNfeCfg - se nao, usa da NF
					CdNfeCfg ncfg = nfe.getCdnfecfg();
					if (!i.getCdnfecfg().equals(nfe.getCdnfecfg()) && nfe.getUsacfgfiscal().equals("N")) {
						ncfg = i.getCdnfecfg();
					}
					fsNfeTributoService.fsNfeTrib(ncfg, csn, null, i, vFrete);
				}
			}
		}
	}

	// LISTAGEM DOS ULTIMOS VALORES PARA IMPORTACAO DE CUSTOS**************
	public List<AuxDados> dadosUltimasCompras(Long idfsnfe) {
		// Monta para tela lista dados----------------------------------------------
		List<AuxDados> lista = new ArrayList<AuxDados>();
		List<FsNfeItem> its = fsNfeRp.findById(idfsnfe).get().getFsnfeitems();
		for (FsNfeItem n : its) {
			if (n.getIdprod() != null) {
				BigDecimal vcustoatual = new BigDecimal(0);
				BigDecimal vtotdias = new BigDecimal(0);
				BigDecimal vtoticms = new BigDecimal(0);
				BigDecimal vtoticmsst = new BigDecimal(0);
				BigDecimal vtotfcpst = new BigDecimal(0);
				BigDecimal vtotipi = new BigDecimal(0);
				BigDecimal vtotpis = new BigDecimal(0);
				BigDecimal vtotcofins = new BigDecimal(0);
				BigDecimal vicmsmedio = new BigDecimal(0);
				BigDecimal vicmsstmedio = new BigDecimal(0);
				BigDecimal vfcpstmedio = new BigDecimal(0);
				BigDecimal vipimedio = new BigDecimal(0);
				BigDecimal vpismedio = new BigDecimal(0);
				BigDecimal vcofinsmedio = new BigDecimal(0);
				BigDecimal vmediodias = new BigDecimal(0);
				Long emp = n.getFsnfe().getCdpessoaemp().getId();
				// Pega valor estoque atual----------------------------------------------
				EsEst e = esEstRp.findByEmpProdutoIdCodigo(emp, n.getIdprod(), n.getIdprod().intValue());
				if (e != null) {
					vcustoatual = e.getVcusto();
					// Seleciona ultimos 90 dias - nao havendo, pega ultimas 3-----------
					java.util.Date data = DataUtil.addRemMeses(new Date(System.currentTimeMillis()), 3, "R");
					List<EsEstMov> ems = esEstMovRp.findAllLocalEmpTipoTpdDifDias(emp, e.getCdproduto().getId(), "E",
							"01", data, idfsnfe);
					// Se houver mais de 3 notas-----------------------------------------
					if (ems.size() > 3) {
						for (EsEstMov em : ems) {
							vtotdias = vtotdias.add(em.getVtot().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtoticms = vtoticms.add(em.getVicms().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtoticmsst = vtoticmsst.add(em.getVicmsst().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtotfcpst = vtotfcpst.add(em.getVfcpst().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtotipi = vtotipi.add(em.getVipi().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtotpis = vtotpis.add(em.getVpis().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtotcofins = vtotcofins.add(em.getVcofins().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
						}
						if (ems.size() > 1) {
							vmediodias = vtotdias.divide(new BigDecimal(ems.size()), 4, RoundingMode.HALF_UP);
							vicmsmedio = vtoticms.divide(new BigDecimal(ems.size()), 4, RoundingMode.HALF_UP);
							vicmsstmedio = vtoticmsst.divide(new BigDecimal(ems.size()), 4, RoundingMode.HALF_UP);
							vfcpstmedio = vtotfcpst.divide(new BigDecimal(ems.size()), 4, RoundingMode.HALF_UP);
							vipimedio = vtotipi.divide(new BigDecimal(ems.size()), 4, RoundingMode.HALF_UP);
							vpismedio = vtotpis.divide(new BigDecimal(ems.size()), 4, RoundingMode.HALF_UP);
							vcofinsmedio = vtotcofins.divide(new BigDecimal(ems.size()), 4, RoundingMode.HALF_UP);
						} else {
							vmediodias = vtotdias;
							vicmsmedio = vtoticms;
							vicmsstmedio = vtoticmsst;
							vfcpstmedio = vtotfcpst;
							vipimedio = vtotipi;
							vpismedio = vtotpis;
							vcofinsmedio = vtotcofins;
						}
					} else {
						// Busca ultimas 3 independente de tempo--------------------------
						List<EsEstMov> ems3 = esEstMovRp.findAllLocalEmpTipoTpdDifUltimas3(emp,
								e.getCdproduto().getId(), "E", "01", idfsnfe);
						for (EsEstMov em : ems3) {
							vtotdias = vtotdias.add(em.getVtot().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtoticms = vtoticms.add(em.getVicms().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtoticmsst = vtoticmsst.add(em.getVicmsst().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtotfcpst = vtotfcpst.add(em.getVfcpst().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtotipi = vtotipi.add(em.getVipi().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtotpis = vtotpis.add(em.getVpis().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
							vtotcofins = vtotcofins.add(em.getVcofins().divide(em.getQtd(), 4, RoundingMode.HALF_UP));
						}
						if (ems3.size() > 1) {
							vmediodias = vtotdias.divide(new BigDecimal(ems3.size()), 4, RoundingMode.HALF_UP);
							vicmsmedio = vtoticms.divide(new BigDecimal(ems3.size()), 4, RoundingMode.HALF_UP);
							vicmsstmedio = vtoticmsst.divide(new BigDecimal(ems3.size()), 4, RoundingMode.HALF_UP);
							vfcpstmedio = vtotfcpst.divide(new BigDecimal(ems3.size()), 4, RoundingMode.HALF_UP);
							vipimedio = vtotipi.divide(new BigDecimal(ems3.size()), 4, RoundingMode.HALF_UP);
							vpismedio = vtotpis.divide(new BigDecimal(ems3.size()), 4, RoundingMode.HALF_UP);
							vcofinsmedio = vtotcofins.divide(new BigDecimal(ems3.size()), 4, RoundingMode.HALF_UP);
						} else {
							vmediodias = vtotdias;
							vicmsmedio = vtoticms;
							vicmsstmedio = vtoticmsst;
							vfcpstmedio = vtotfcpst;
							vipimedio = vtotipi;
							vpismedio = vtotpis;
							vcofinsmedio = vtotcofins;
						}
					}
				}
				AuxDados a = new AuxDados();
				a.setCampo1(e.getCdproduto().getCodigo() + "");
				a.setCampo2(n.getVuncom() + "");
				a.setCampo3(e.getVcusto() + "");
				a.setCampo9(vicmsmedio + "");
				a.setCampo10(vicmsstmedio + "");
				a.setCampo11(vfcpstmedio + "");
				a.setCampo12(vipimedio + "");
				a.setCampo13(vpismedio + "");
				a.setCampo14(vcofinsmedio + "");
				a.setCampo15(vcustoatual + "");
				a.setCampo16(vmediodias + "");
				a.setCampo20(n.getId() + "");
				lista.add(a);
			}
		}
		return lista;
	}

	// CONSULTA PADRAO NFE DESTINADAS CNPJ************************{
	public String[] consultaDist(Long idlocal, String ambiente, HttpServletRequest request) throws Exception {
		// Inicializa dados padroes
		FsNfeManCons obj = fsNfeManConsRp.findByLocal(idlocal);
		if (obj == null) {
			CdPessoa local = cdPessoaRp.getById(idlocal);
			FsNfeManCons c = new FsNfeManCons();
			c.setCdpessoaemp(local);
			c.setUltimonsu("000000000000000");
			c.setData(new java.sql.Date(System.currentTimeMillis()));
			c.setHora(DataUtil.addRemMinTime(new Time(System.currentTimeMillis()), mc.tempobuscaman + 1, "R"));
			obj = fsNfeManConsRp.save(c);
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
		fsNfeManConsRp.updateHora(obj.getUltimonsu(), idlocal);
		if (diffMin > mc.tempobuscaman || comData == true) {
			obj = fsNfeManConsRp.findByLocal(idlocal);
			diffMin = diffMinFunc(obj);
			if (ret[2] == null) {
				ret[2] = obj.getUltimonsu();
			}
			ret[2] = NumUtil.geraNumEsq(Integer.valueOf(ret[2]), 15);
			while (ultNSU <= maxNSU && status.equals("138")) {
				ret = fsNfeServiceEnvio.consultaDistDest(idlocal, ambiente, ultNSU, maxNSU, request);
				status = ret[0];
				ultNSU = Long.valueOf(ret[2]);
				maxNSU = Long.valueOf(ret[3]);
				// Atualiza com Ultimo e hora
				String ultimoNSU = NumUtil.geraNumEsq(Integer.valueOf(ultNSU + ""), 15);
				fsNfeManConsRp.updateHora(ultimoNSU, idlocal);
				// Tempo aguardo e retoma----4 minutos
				Thread.sleep(240000);
			}
			Thread.sleep(5000);
		}
		return ret;
	}

	// Calculo hora-tempo
	private long diffMinFunc(FsNfeManCons obj) {
		Time horacons = Time.valueOf(obj.getHora().toString());
		Time horaatual = Time.valueOf(DataUtil.dataBancoTimeAtual());
		long diff = horaatual.getTime() - horacons.getTime();
		long diffMin = diff / (60 * 1000);
		return diffMin;
	}

	public void inutilizaDoc(Long idlocal, String ambiente, Integer modelo, Integer serie, Integer numNI, Integer numNF,
			String justifica) {
		CdPessoa emp = cdPessoaRp.findById(idlocal).get();
		Integer numeroInut = numNI;
		while (numeroInut <= numNF) {
			// Verifica se ja foi, se nao, faz outros...
			FsInut n = fsInutRp.findByLocalModeloNumeroSerieAmb(idlocal, modelo, numeroInut, serie, ambiente);
			if (n == null) {
				FsInut in = new FsInut();
				in.setCdpessoaemp(emp);
				in.setModelo(modelo);
				in.setMotivo(justifica);
				in.setNumero(numeroInut);
				in.setSerie(serie);
				in.setTpamb(ambiente);
				fsInutRp.save(in);
			}
			numeroInut++;
		}
	}
}
