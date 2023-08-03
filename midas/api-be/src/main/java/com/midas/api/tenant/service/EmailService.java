package com.midas.api.tenant.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.constant.FsCteWebService;
import com.midas.api.constant.MidasConfig;
import com.midas.api.mt.config.DBContextHolder;
import com.midas.api.mt.entity.Tenant;
import com.midas.api.mt.repository.TenantRepository;
import com.midas.api.security.ClienteParam;
import com.midas.api.tenant.entity.AuxDados;
import com.midas.api.tenant.entity.AuxDadosByte;
import com.midas.api.tenant.entity.AuxEmail;
import com.midas.api.tenant.entity.CdEmail;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsCtePart;
import com.midas.api.tenant.entity.FsMdfe;
import com.midas.api.tenant.entity.FsMdfePart;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfePart;
import com.midas.api.tenant.entity.FsNfse;
import com.midas.api.tenant.entity.FsNfsePart;
import com.midas.api.tenant.entity.FsSped;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcDocItemCot;
import com.midas.api.tenant.fiscal.service.FsSpedService;
import com.midas.api.tenant.repository.CdEmailRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.FsCteRepository;
import com.midas.api.tenant.repository.FsMdfeRepository;
import com.midas.api.tenant.repository.FsNfeRepository;
import com.midas.api.tenant.repository.FsNfseRepository;
import com.midas.api.tenant.repository.LcDocItemCotRepository;
import com.midas.api.tenant.repository.LcDocRepository;
import com.midas.api.tenant.service.excel.LcDocCotacaoExcelService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.EmailServiceUtil;
import com.midas.api.util.LcDocTipoNomeUtil;
import com.midas.api.util.LerArqUtil;
import com.midas.api.util.MoedaUtil;
import com.midas.api.util.ZipUtil;

import net.sf.jasperreports.engine.JasperPrint;

@Service
public class EmailService {
	@Autowired
	private EmailServiceUtil emailServiceUtil;
	@Autowired
	private CdEmailRepository cdEmailRp;
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private FsNfseRepository fsNfseRp;
	@Autowired
	private FsCteRepository fsCteRp;
	@Autowired
	private FsMdfeRepository fsMdfeRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private ReportService reportService;
	@Autowired
	private TenantRepository tenantRp;
	@Autowired
	private LcDocRepository lcDocRp;
	@Autowired
	private FsSpedService fsSpedService;
	@Autowired
	private LcDocItemCotRepository lcDocItemCotRp;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private ClienteParam prm;

	public AuxEmail criaAuxEmail(CdEmail em, MidasConfig mc) {
		AuxEmail a = new AuxEmail();
		if (em != null) {
			a.setRemetente(em.getNome());
			a.setEmail(em.getEmail());
			a.setSenha(em.getSenha());
			a.setSmtp(em.getSmtp());
			a.setRequeraut(em.getRequerauth());
			a.setSslsmtp(em.getSslsmtp());
			a.setPortastmp(em.getPortasmtp());
			a.setTipo(em.getTipo());
		} else {
			a.setRemetente(mc.MidasApresenta);
			a.setEmail(mc.NrEmail);
			a.setSenha(mc.NrSenha);
			a.setSmtp(mc.NrSmtp);
			a.setRequeraut(mc.NrRequeraut);
			a.setSslsmtp(mc.NrSslsmtp);
			a.setPortastmp(mc.NrPortastmp);
			a.setTipo(mc.NrTipo);
		}
		return a;
	}

	// ENVIO TESTE
	// ********************************************************************************
	public void enviaEmailTeste(CdEmail email) throws Exception {
		String assunto = "Midas - Sucesso no envio de mensagem teste!";
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append("<br />");
		s.append("Olá");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Um mensagem de teste foi enviada para: <b>" + email.getNome() + " [ " + email.getEmail() + " ] "
				+ "</b>");
		s.append("<br />");
		s.append("<br />");
		s.append("Se recebeu este e-mail, suas configurações foram efetuadas com sucesso!");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>E-mail enviado automaticamente!</b>");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		AuxEmail em = criaAuxEmail(email, null);
		emailServiceUtil.EnviaHTML(email.getEmail(), assunto, s.toString(), null, null, em);
	}

	// NFE
	// ****************************************************************************************
	// Envio nota e XML - nfe
	public void enviaNFeEmail(HttpServletRequest request, Long idnfe, String emailsOutros) throws Exception {
		FsNfe nfe = fsNfeRp.findById(idnfe).get();
		LcDoc lcDoc = lcDocRp.findById(nfe.getLcdoc()).get();
		CdPessoa emit = nfe.getCdpessoaemp();
		FsNfePart dest = nfe.getFsnfepartdest();
		// Seleciona email fiscal/faturamento padrao
		Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(nfe.getCdpessoaemp().getId(), "02");
		String assunto = emit.getNome() + " | Envio de NF-e Número " + nfe.getNnf();
		// Boletos-se houver
		String urlbol = mc.linkTecnoHom;
		if (prm.cliente().getSiscfg().getSis_amb_boleto().equals("1")) {
			urlbol = mc.linkTecnoProd;
		}
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append("<br />");
		s.append(emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj()));
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Você está recebendo em anexo os arquivos XML e PDF da nota número <b>" + nfe.getNnf()
				+ "</b> neste e-mail. ");
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Emitido por:</b> " + emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj())
				+ "<br />");
		s.append("<b>Número da nota:</b> " + nfe.getNnf() + "<br />");
		s.append("<b>Emitido em:</b> " + DataUtil.dataPadraoBr(nfe.getDhemi()) + "<br />");
		s.append("<b>Chave de acesso:</b> " + nfe.getChaveac() + "<br />");
		s.append("<b>Consulta autenticidade em:</b> https://www.nfe.fazenda.gov.br <br />");
		s.append("<br />");
		// Monta titulos, se houver
		for (FnTitulo t : lcDoc.getLcdoctitulo()) {
			if (t.getTipo().equals("R")) {
				s.append("<b>Emitido em:</b> " + DataUtil.dataPadraoBr(t.getDatacad()) + "<br />");
				s.append("<b>Parcela:</b> " + t.getParcnum() + "/" + t.getQtdparc() + "<br />");
				s.append("<b>Vencimento em:</b> " + DataUtil.dataPadraoBr(t.getVence()) + "<br />");
				s.append("<b>Valor R$:</b> " + MoedaUtil.moedaPadrao(t.getVtot()) + "<br />");
				// Se tiver boletos
				String idintegracao = t.getTecno_idintegracao();
				if (idintegracao != null) {
					if (!idintegracao.equals("")) {
						s.append("<b>Link do boleto:</b> " + urlbol + "boletos/impressao/" + idintegracao + "<br />");
						s.append("<b>Consulta autenticidade por:</b> " + rem.get().getEmail() + " <br />");
						s.append(
								"<i><font style='font-size:9px;color:#347deb'><b>Boleto via e-mail gerado por TecnoSpeed! Dúvidas na autenticidade, entre em contato com emitente!</b></font></i>");
						s.append("<br />");
					}
				}
				s.append("<br />");
			}
		}
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(rem.get().getAssinatura());
		s.append("<br />");
		s.append("<br />");
		// CRIA ANEXOS
		String nomeXml = "NFe" + nfe.getChaveac() + "-proc.xml";
		String nomePDF = "NFe" + nfe.getChaveac() + "-proc.pdf";
		// Configs--------
		Map<String, Object> par = new HashMap<String, Object>();
		String sql = " WHERE f.id = " + idnfe;
		par.put("sql", sql);
		par.put("id", idnfe);
		par.put("software", mc.MidasApresenta);
		par.put("site", mc.MidasSite);
		JasperPrint jp = reportService.geraRelPrintExport("0027", par, request.getServletContext());
		// Files----------
		File tempXML = LerArqUtil.criaConteudoFile(nomeXml, nfe.getXml(), "xml");
		File tempPDF = LerArqUtil.criaFilePDF(nomePDF, jp, "pdf");
		List<AuxDados> anexos = new ArrayList<AuxDados>();
		// XML------------
		AuxDados emXML = new AuxDados();
		emXML.setCampo1(nomeXml);
		emXML.setCampo2(tempXML.getAbsolutePath());
		anexos.add(emXML);
		// PDF------------
		AuxDados pdfXML = new AuxDados();
		pdfXML.setCampo1(nomePDF);
		pdfXML.setCampo2(tempPDF.getAbsolutePath());
		anexos.add(pdfXML);
		// Envio
		AuxEmail em = criaAuxEmail(rem.get(), null);
		String email = dest.getEmail();
		if (emailsOutros != null) {
			if (!emailsOutros.equals("")) {
				email = email + "," + emailsOutros;
			}
		}
		emailServiceUtil.EnviaHTML(email, assunto, s.toString(), anexos, null, em);
		// REMOVE ARQUIVOS
		tempXML.delete();
		tempPDF.delete();
	}

	// Envio xml - nfe
	public void enviaNFeEmailXML(HttpServletRequest request, Long local, Date ini, Date fim, String destinatarios,
			String info, String enviopdf, String modelo, String tipo) throws Exception {
		CdPessoa emit = cdPessoaRp.findById(local).get();
		// Seleciona email fiscal/faturamento padrao
		Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(local, "02");
		String assunto = emit.getNome() + " | Envio de Arquivos XML de NF-e por e-mail ";
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append("<br />");
		s.append("Olá, ");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Você está recebendo em anexo os arquivos XML e/ou PDF do período <b>" + DataUtil.dataPadraoBr(ini)
				+ "</b> até <b>" + DataUtil.dataPadraoBr(fim) + "</b> neste e-mail. ");
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Emitente:</b> " + emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj())
				+ "<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append(info);
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(rem.get().getAssinatura());
		s.append("<br />");
		s.append("<br />");
		// Cria anexos
		List<AuxDados> anexos = new ArrayList<AuxDados>();
		// Lista NFes
		List<FsNfe> nfes = fsNfeRp.listaNfeEntsaiTipoStatus(local, ini, fim, Integer.valueOf(modelo), tipo, 100, 150);
		for (FsNfe n : nfes) {
			// CRIA ANEXOS
			String nomeXml = "NFe" + n.getChaveac() + "-proc_";
			// Files----------
			File tempXML = LerArqUtil.criaConteudoFile(nomeXml, n.getXml(), "xml");
			// XML------------
			AuxDados emXML = new AuxDados();
			emXML.setCampo1(nomeXml);
			emXML.setCampo2(tempXML.getAbsolutePath());
			anexos.add(emXML);
			// Se envia PDF junto-----
			if (enviopdf.equals("S")) {
				// Configs--------
				Map<String, Object> par = new HashMap<String, Object>();
				String sql = " WHERE f.id = " + n.getId();
				par.put("sql", sql);
				par.put("id", n.getId());
				par.put("software", mc.MidasApresenta);
				par.put("site", mc.MidasSite);
				String nomePDF = "NFe" + n.getChaveac() + "-proc_";
				JasperPrint jp = reportService.geraRelPrintExport("0027", par, request.getServletContext());
				File tempPDF = LerArqUtil.criaFilePDF(nomePDF, jp, "pdf");
				// PDF------------
				AuxDados pdfXML = new AuxDados();
				pdfXML.setCampo1(nomePDF);
				pdfXML.setCampo2(tempPDF.getAbsolutePath());
				anexos.add(pdfXML);
			}
		}
		// Cria anexo final ZIP-------
		Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
		String nomeZip = tipo + "_" + ini + "_" + fim + "_" + emit.getCpfcnpj() + ".zip";
		List<AuxDados> anexoFinal = new ArrayList<AuxDados>();
		ZipUtil.zipFiles(nomeZip, tn.getTempfd(), anexos, assunto);
		// Nova lista de anexo--------
		AuxDados emXMLFinal = new AuxDados();
		emXMLFinal.setCampo1(nomeZip);
		emXMLFinal.setCampo2(tn.getTempfd() + nomeZip);
		anexoFinal.add(emXMLFinal);
		// Envio----------------------
		AuxEmail em = criaAuxEmail(rem.get(), null);
		emailServiceUtil.EnviaHTML(destinatarios, assunto, s.toString(), anexoFinal, null, em);
		// REMOVE ARQUIVOS
		new File(anexoFinal.get(0).getCampo2()).delete();
	}

	// NFSE ***************************************************************************************
	// Envio nota e XML - nfse
	public void enviaNFSeEmail(HttpServletRequest request, Long idnfse, String emailsOutros) throws Exception {
		FsNfse nfse = fsNfseRp.findById(idnfse).get();
		CdPessoa emit = nfse.getCdpessoaemp();
		FsNfsePart toma = nfse.getFsnfseparttoma();
		// Seleciona email fiscal/faturamento padrao
		Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(nfse.getCdpessoaemp().getId(), "02");
		String assunto = emit.getNome() + " | Envio de NFS-e Número " + nfse.getNnfs();
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append("<br />");
		s.append(emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj()));
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Você está recebendo em anexo os arquivos XML e PDF da nota de serviços número <b>" + nfse.getNnfs()
				+ "</b> neste e-mail. ");
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Emitido por:</b> " + emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj())
				+ "<br />");
		s.append("<b>Número da nota:</b> " + nfse.getNnfs() + "<br />");
		s.append("<b>Emitido em:</b> " + DataUtil.dataPadraoBr(nfse.getDemis()) + "<br />");
		s.append("<b>Chave de verificação:</b> " + nfse.getCverifica() + "<br />");
		s.append("<b>Consulta autenticidade no site da prefeitura</b>");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(rem.get().getAssinatura());
		s.append("<br />");
		s.append("<br />");
		// CRIA ANEXOS
		String nomeXml = "NFSe" + nfse.getCverifica() + "-proc.xml";
		String nomePDF = "NFSe" + nfse.getCverifica() + "-proc.pdf";
		// Configs--------
		Map<String, Object> par = new HashMap<String, Object>();
		String sql = " WHERE f.id = " + idnfse;
		par.put("sql", sql);
		par.put("id", idnfse);
		par.put("software", mc.MidasApresenta);
		par.put("site", mc.MidasSite);
		JasperPrint jp = reportService.geraRelPrintExport("0059", par, request.getServletContext());
		// Files----------
		File tempXML = LerArqUtil.criaConteudoFile(nomeXml, nfse.getXml(), "xml");
		File tempPDF = LerArqUtil.criaFilePDF(nomePDF, jp, "pdf");
		List<AuxDados> anexos = new ArrayList<AuxDados>();
		// XML------------
		AuxDados emXML = new AuxDados();
		emXML.setCampo1(nomeXml);
		emXML.setCampo2(tempXML.getAbsolutePath());
		anexos.add(emXML);
		// PDF------------
		AuxDados pdfXML = new AuxDados();
		pdfXML.setCampo1(nomePDF);
		pdfXML.setCampo2(tempPDF.getAbsolutePath());
		anexos.add(pdfXML);
		// Envio
		AuxEmail em = criaAuxEmail(rem.get(), null);
		String email = toma.getEmail();
		if (emailsOutros != null) {
			if (!emailsOutros.equals("")) {
				email = email + "," + emailsOutros;
			}
		}
		emailServiceUtil.EnviaHTML(email, assunto, s.toString(), anexos, null, em);
		// REMOVE ARQUIVOS
		tempXML.delete();
		tempPDF.delete();
	}

	public void enviaNFSeEmailXML(HttpServletRequest request, Long local, Date ini, Date fim, String destinatarios,
			String info, String enviopdf, String tipo) throws Exception {
		CdPessoa emit = cdPessoaRp.findById(local).get();
		// Seleciona email fiscal/faturamento padrao
		Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(local, "02");
		String assunto = emit.getNome() + " | Envio de Arquivos XML de NFS-e por e-mail ";
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append("<br />");
		s.append("Olá, ");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Você está recebendo em anexo os arquivos XML e/ou PDF do período <b>" + DataUtil.dataPadraoBr(ini)
				+ "</b> até <b>" + DataUtil.dataPadraoBr(fim) + "</b> neste e-mail. ");
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Emitente:</b> " + emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj())
				+ "<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append(info);
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(rem.get().getAssinatura());
		s.append("<br />");
		s.append("<br />");
		// Cria anexos
		List<AuxDados> anexos = new ArrayList<AuxDados>();
		// Lista NFes
		List<FsNfse> nfses = fsNfseRp.listaNfseEntsaiTipoStatus(local, ini, fim, tipo, 100);
		for (FsNfse n : nfses) {
			// CRIA ANEXOS
			String nomeXml = "NFSe" + n.getNnfs() + "-proc_";
			// Files----------
			File tempXML = LerArqUtil.criaConteudoFile(nomeXml, n.getXml(), "xml");
			// XML------------
			AuxDados emXML = new AuxDados();
			emXML.setCampo1(nomeXml);
			emXML.setCampo2(tempXML.getAbsolutePath());
			anexos.add(emXML);
			// Se envia PDF junto-----
			if (enviopdf.equals("S")) {
				// Configs--------
				Map<String, Object> par = new HashMap<String, Object>();
				String sql = " WHERE f.id = " + n.getId();
				par.put("sql", sql);
				par.put("id", n.getId());
				par.put("software", mc.MidasApresenta);
				par.put("site", mc.MidasSite);
				String nomePDF = "NFSe" + n.getNnfs() + "-proc_";
				JasperPrint jp = reportService.geraRelPrintExport("0059", par, request.getServletContext());
				File tempPDF = LerArqUtil.criaFilePDF(nomePDF, jp, "pdf");
				// PDF------------
				AuxDados pdfXML = new AuxDados();
				pdfXML.setCampo1(nomePDF);
				pdfXML.setCampo2(tempPDF.getAbsolutePath());
				anexos.add(pdfXML);
			}
		}
		// Cria anexo final ZIP-------
		Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
		String nomeZip = tipo + "_" + ini + "_" + fim + "_" + emit.getCpfcnpj() + ".zip";
		List<AuxDados> anexoFinal = new ArrayList<AuxDados>();
		ZipUtil.zipFiles(nomeZip, tn.getTempfd(), anexos, assunto);
		// Nova lista de anexo--------
		AuxDados emXMLFinal = new AuxDados();
		emXMLFinal.setCampo1(nomeZip);
		emXMLFinal.setCampo2(tn.getTempfd() + nomeZip);
		anexoFinal.add(emXMLFinal);
		// Envio----------------------
		AuxEmail em = criaAuxEmail(rem.get(), null);
		emailServiceUtil.EnviaHTML(destinatarios, assunto, s.toString(), anexoFinal, null, em);
		// REMOVE ARQUIVOS
		new File(anexoFinal.get(0).getCampo2()).delete();
	}

	// CTE
	// ****************************************************************************************
	// Envio nota e xml - cte
	public void enviaCTeEmail(HttpServletRequest request, Long idcte, String emailsOutros) throws Exception {
		FsCte cte = fsCteRp.findById(idcte).get();
		CdPessoa emit = cte.getCdpessoaemp();
		FsCtePart dest = cte.getFsctepartdest();
		FsCtePart recebe = cte.getFsctepartrec();
		// Seleciona email fiscal/faturamento padrao
		Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(cte.getCdpessoaemp().getId(), "02");
		String assunto = emit.getNome() + " | Envio de CT-e Número " + cte.getNct();
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append("<br />");
		s.append(emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj()));
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Você está recebendo em anexo os arquivos XML e PDF do conhecimento de frete número <b>" + cte.getNct()
				+ "</b> neste e-mail. ");
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Emitido por:</b> " + emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj())
				+ "<br />");
		s.append("<b>Número do conhecimento:</b> " + cte.getNct() + "<br />");
		s.append("<b>Emitido em:</b> " + DataUtil.dataPadraoBr(cte.getDhemi()) + "<br />");
		s.append("<b>Chave de acesso:</b> " + cte.getChaveac() + "<br />");
		s.append("<b>Consulta autenticidade em:</b> https://www.cte.fazenda.gov.br/ <br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(rem.get().getAssinatura());
		s.append("<br />");
		s.append("<br />");
		// CRIA ANEXOS
		String nomeXml = "CTe" + cte.getChaveac() + "-proc.xml";
		String nomePDF = "CTe" + cte.getChaveac() + "-proc.pdf";
		// Configs--------
		Map<String, Object> par = new HashMap<String, Object>();
		String qrcode = FsCteWebService.CTeQrCode + "?chCTe=" + cte.getChaveac() + "&tpAmb=" + cte.getTpamb();
		String sql = " WHERE f.id = " + idcte;
		par.put("software", mc.MidasApresenta);
		par.put("site", mc.MidasSite);
		par.put("sql", sql);
		par.put("id", idcte);
		par.put("qrcode", qrcode);
		JasperPrint jp = reportService.geraRelPrintExport("0047", par, request.getServletContext());
		// Files----------
		File tempXML = LerArqUtil.criaConteudoFile(nomeXml, cte.getXml(), "xml");
		File tempPDF = LerArqUtil.criaFilePDF(nomePDF, jp, "pdf");
		List<AuxDados> anexos = new ArrayList<AuxDados>();
		// XML------------
		AuxDados emXML = new AuxDados();
		emXML.setCampo1(nomeXml);
		emXML.setCampo2(tempXML.getAbsolutePath());
		anexos.add(emXML);
		// PDF------------
		AuxDados pdfXML = new AuxDados();
		pdfXML.setCampo1(nomePDF);
		pdfXML.setCampo2(tempPDF.getAbsolutePath());
		anexos.add(pdfXML);
		// Envio
		AuxEmail em = criaAuxEmail(rem.get(), null);
		String emails = dest.getEmail() + "," + recebe.getEmail();
		if (emailsOutros != null) {
			if (!emailsOutros.equals("")) {
				emails = emails + "," + emailsOutros;
			}
		}
		emailServiceUtil.EnviaHTML(emails, assunto, s.toString(), anexos, null, em);
		// REMOVE ARQUIVOS
		tempXML.delete();
		tempPDF.delete();
	}

	// Envio xml - cte
	public void enviaCTeEmailXML(HttpServletRequest request, Long local, Date ini, Date fim, String destinatarios,
			String info, String enviopdf, String tipo) throws Exception {
		CdPessoa emit = cdPessoaRp.findById(local).get();
		// Seleciona email fiscal/faturamento padrao
		Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(local, "02");
		String assunto = emit.getNome() + " | Envio de Arquivos XML de CT-e por e-mail ";
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append("<br />");
		s.append("Olá, ");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Você está recebendo em anexo os arquivos XML e/ou PDF do período <b>" + DataUtil.dataPadraoBr(ini)
				+ "</b> até <b>" + DataUtil.dataPadraoBr(fim) + "</b> neste e-mail. ");
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Emitente:</b> " + emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj())
				+ "<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append(info);
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(rem.get().getAssinatura());
		s.append("<br />");
		s.append("<br />");
		// Cria anexos
		List<AuxDados> anexos = new ArrayList<AuxDados>();
		// Lista NFes
		List<FsCte> ctes = fsCteRp.listaCteEntsaiTipoStatus(local, ini, fim, tipo, 100, 150);
		for (FsCte n : ctes) {
			// CRIA ANEXOS
			String nomeXml = "CTe" + n.getChaveac() + "-proc_";
			// Files----------
			File tempXML = LerArqUtil.criaConteudoFile(nomeXml, n.getXml(), "xml");
			// XML------------
			AuxDados emXML = new AuxDados();
			emXML.setCampo1(nomeXml);
			emXML.setCampo2(tempXML.getAbsolutePath());
			anexos.add(emXML);
			// Se envia PDF junto-----
			if (enviopdf.equals("S")) {
				// Configs--------
				Map<String, Object> par = new HashMap<String, Object>();
				String qrcode = FsCteWebService.CTeQrCode + "?chCTe=" + n.getChaveac() + "&tpAmb=" + n.getTpamb();
				String sql = " WHERE f.id = " + n.getId();
				par.put("software", mc.MidasApresenta);
				par.put("site", mc.MidasSite);
				par.put("sql", sql);
				par.put("id", n.getId());
				par.put("qrcode", qrcode);
				String nomePDF = "CTe" + n.getChaveac() + "-proc_";
				JasperPrint jp = reportService.geraRelPrintExport("0047", par, request.getServletContext());
				File tempPDF = LerArqUtil.criaFilePDF(nomePDF, jp, "pdf"); // PDF------------
				AuxDados pdfXML = new AuxDados();
				pdfXML.setCampo1(nomePDF);
				pdfXML.setCampo2(tempPDF.getAbsolutePath());
				anexos.add(pdfXML);
			}
		}
		// Cria anexo final ZIP-------
		Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
		String nomeZip = tipo + "_" + ini + "_" + fim + "_" + emit.getCpfcnpj() + ".zip";
		List<AuxDados> anexoFinal = new ArrayList<AuxDados>();
		ZipUtil.zipFiles(nomeZip, tn.getTempfd(), anexos, assunto);
		// Nova lista de anexo--------
		AuxDados emXMLFinal = new AuxDados();
		emXMLFinal.setCampo1(nomeZip);
		emXMLFinal.setCampo2(tn.getTempfd() + nomeZip);
		anexoFinal.add(emXMLFinal);
		// Envio----------------------
		AuxEmail em = criaAuxEmail(rem.get(), null);
		emailServiceUtil.EnviaHTML(destinatarios, assunto, s.toString(), anexoFinal, null, em);
		// REMOVE ARQUIVOS
		new File(anexoFinal.get(0).getCampo2()).delete();
	}

	// MDFE
	// ***************************************************************************************
	// Envio nota e xml - mdfe
	public void enviaMDFeEmail(HttpServletRequest request, Long idmdfe, String emailsOutros) throws Exception {
		FsMdfe mdfe = fsMdfeRp.findById(idmdfe).get();
		CdPessoa emit = mdfe.getCdpessoaemp();
		FsMdfePart dest = mdfe.getFsmdfepartitems().get(0);
		// Seleciona email fiscal/faturamento padrao
		Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(mdfe.getCdpessoaemp().getId(), "02");
		String assunto = emit.getNome() + " | Envio de MDF-e Número " + mdfe.getNmdf();
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append("<br />");
		s.append(emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj()));
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Você está recebendo em anexo os arquivos XML e PDF da nota de manifesto número <b>" + mdfe.getNmdf()
				+ "</b> neste e-mail. ");
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Emitido por:</b> " + emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj())
				+ "<br />");
		s.append("<b>Número da nota de manifesto:</b> " + mdfe.getNmdf() + "<br />");
		s.append("<b>Emitido em:</b> " + DataUtil.dataPadraoBr(mdfe.getDhemi()) + "<br />");
		s.append("<b>Chave de acesso:</b> " + mdfe.getChaveac() + "<br />");
		s.append("<b>Consulta autenticidade em:</b> https://mdfe-portal.sefaz.rs.gov.br/ <br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(rem.get().getAssinatura());
		s.append("<br />");
		s.append("<br />");
		// CRIA ANEXOS
		String nomeXml = "MDFe" + mdfe.getChaveac() + "-proc.xml";
		String nomePDF = "MDFe" + mdfe.getChaveac() + "-proc.pdf";
		// Configs--------
		Map<String, Object> par = new HashMap<String, Object>();
		String sql = " WHERE f.id = " + idmdfe;
		par.put("sql", sql);
		par.put("id", idmdfe);
		par.put("software", mc.MidasApresenta);
		par.put("site", mc.MidasSite);
		JasperPrint jp = reportService.geraRelPrintExport("0040", par, request.getServletContext());
		// Files----------
		File tempXML = LerArqUtil.criaConteudoFile(nomeXml, mdfe.getXml(), "xml");
		File tempPDF = LerArqUtil.criaFilePDF(nomePDF, jp, "pdf");
		List<AuxDados> anexos = new ArrayList<AuxDados>();
		// XML------------
		AuxDados emXML = new AuxDados();
		emXML.setCampo1(nomeXml);
		emXML.setCampo2(tempXML.getAbsolutePath());
		anexos.add(emXML);
		// PDF------------
		AuxDados pdfXML = new AuxDados();
		pdfXML.setCampo1(nomePDF);
		pdfXML.setCampo2(tempPDF.getAbsolutePath());
		anexos.add(pdfXML);
		// Envio
		AuxEmail em = criaAuxEmail(rem.get(), null);
		String email = dest.getEmail();
		if (emailsOutros != null) {
			if (!emailsOutros.equals("")) {
				email = email + "," + emailsOutros;
			}
		}
		emailServiceUtil.EnviaHTML(email, assunto, s.toString(), anexos, null, em);
		// REMOVE ARQUIVOS
		tempXML.delete();
		tempPDF.delete();
	}

	// LCDOC
	// **************************************************************************************
	public void enviaDocEmail(HttpServletRequest request, Long iddoc, String emailsOutros) throws Exception {
		LcDoc lcDoc = lcDocRp.findById(iddoc).get();
		CdPessoa emit = lcDoc.getCdpessoaemp();
		CdPessoa dest = lcDoc.getCdpessoapara();
		// Seleciona email fiscal/faturamento padrao
		Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(emit.getId(), "02");
		// Verifica se pedido de compra
		if (lcDoc.getTipo().equals("08")) {
			rem = cdEmailRp.findByLocalEnvio(emit.getId(), "05");
		}
		String doctipo = LcDocTipoNomeUtil.nomeTipoDoc(lcDoc.getTipo());
		String assunto = emit.getNome() + " | Envio do Documento de " + doctipo + " Número " + lcDoc.getNumero();
		// Boletos-se houver
		String urlbol = mc.linkTecnoHom;
		if (prm.cliente().getSiscfg().getSis_amb_boleto().equals("1")) {
			urlbol = mc.linkTecnoProd;
		}
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append("<br />");
		s.append(emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj()));
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Você está recebendo em anexo o arquivo de PDF do documento de " + doctipo + " número <b>"
				+ lcDoc.getNumero() + "</b> neste e-mail. ");
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Emitido por:</b> " + emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj())
				+ "<br />");
		s.append("<b>Tipo do documento:</b> " + doctipo + "<br />");
		s.append("<b>Número do documento:</b> " + lcDoc.getNumero() + "<br />");
		s.append("<b>Emitido em:</b> " + DataUtil.dataPadraoBr(lcDoc.getDataem()) + "<br />");
		s.append("<b>Consulta autenticidade em:</b> " + rem.get().getEmail() + " <br />");
		s.append("<br />");
		// Monta titulos, se houver
		for (FnTitulo t : lcDoc.getLcdoctitulo()) {
			if (t.getTipo().equals("R")) {
				s.append("<b>Emitido em:</b> " + DataUtil.dataPadraoBr(t.getDatacad()) + "<br />");
				s.append("<b>Parcela:</b> " + t.getParcnum() + "/" + t.getQtdparc() + "<br />");
				s.append("<b>Vencimento em:</b> " + DataUtil.dataPadraoBr(t.getVence()) + "<br />");
				s.append("<b>Valor R$:</b> " + MoedaUtil.moedaPadrao(t.getVtot()) + "<br />");
				// Se tiver boletos
				String idintegracao = t.getTecno_idintegracao();
				if (idintegracao != null) {
					if (!idintegracao.equals("")) {
						s.append("<b>Link do boleto:</b> " + urlbol + "boletos/impressao/" + idintegracao + "<br />");
						s.append("<b>Consulta autenticidade por:</b> " + rem.get().getEmail() + " <br />");
						s.append(
								"<i><font style='font-size:9px;color:#347deb'><b>Boleto via e-mail gerado por TecnoSpeed! Dúvidas na autenticidade, entre em contato com emitente!</b></font></i>");
						s.append("<br />");
					}
				}
				s.append("<br />");
			}
		}
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(rem.get().getAssinatura());
		s.append("<br />");
		s.append("<br />");
		// CRIA ANEXOS
		String nomePDF = "DOC_" + doctipo + "_" + lcDoc.getNumero() + ".pdf";
		// Configs--------
		Map<String, Object> par = new HashMap<String, Object>();
		String sql = " WHERE doc.id = " + iddoc;
		par.put("software", mc.MidasApresenta);
		par.put("site", mc.MidasSite);
		par.put("sql", sql);
		par.put("id", iddoc);
		JasperPrint jp = reportService.geraRelPrintExport("0029", par, request.getServletContext());
		// Verifica se pedido de compra
		if (lcDoc.getTipo().equals("08")) {
			jp = reportService.geraRelPrintExport("0053", par, request.getServletContext());
		}
		// Files----------
		File tempPDF = LerArqUtil.criaFilePDF(nomePDF, jp, "pdf");
		List<AuxDados> anexos = new ArrayList<AuxDados>();
		// PDF------------
		AuxDados pdfXML = new AuxDados();
		pdfXML.setCampo1(nomePDF);
		pdfXML.setCampo2(tempPDF.getAbsolutePath());
		anexos.add(pdfXML);
		// Envio
		AuxEmail em = criaAuxEmail(rem.get(), null);
		String email = dest.getEmail();
		if (emailsOutros != null) {
			if (!emailsOutros.equals("")) {
				email = email + "," + emailsOutros;
			}
		}
		emailServiceUtil.EnviaHTML(email, assunto, s.toString(), anexos, null, em);
		// REMOVE ARQUIVOS
		tempPDF.delete();
	}

	// COBRANCA
	// ***********************************************************************************
	public void enviaEmailCobranca(List<FnTitulo> titulos) throws Exception {
		List<CdPessoa> listaPessoa = new ArrayList<CdPessoa>();
		// Filtro para pegar apenas um nome de cada titulo
		for (FnTitulo t : titulos) {
			if (!listaPessoa.contains(t.getCdpessoapara())) {
				listaPessoa.add(t.getCdpessoapara());
			}
		}
		for (CdPessoa p : listaPessoa) {
			List<FnTitulo> titulosPessoa = new ArrayList<FnTitulo>();
			String assunto = "Lembrete de Débitos";
			CdPessoa empPrincipal = null;
			for (FnTitulo t : titulos) {
				if (t.getCdpessoapara().equals(p)) {
					titulosPessoa.add(t);
					// Adiciona nome da empresa para assunto principal
					empPrincipal = t.getCdpessoaemp();
					assunto = empPrincipal.getNome() + " | Lembrete de Débito(s)";
				}
			}
			// Email de envio
			String emailEnvio = p.getEmail();
			if (p.getEmailfin() != null) {
				if (!p.getEmailfin().equals("")) {
					emailEnvio = p.getEmailfin();
				}
			}
			if (!emailEnvio.equals("") && titulosPessoa.size() > 0) {
				StringBuilder s = new StringBuilder();
				// Montagem email
				s.append("<br />");
				s.append("Olá, <b>" + p.getNome() + "</b>");
				s.append("<br />");
				s.append("<br />");
				s.append("<br />");
				s.append("Estamos passando para lembrar você sobre valores em aberto em nosso sistema!");
				s.append("<br />");
				s.append("<br />");
				s.append("Fique atento as datas e não perca nenhum prazo!");
				s.append("<br />");
				s.append("<br />");
				s.append("<i style='color:#e34a17;'><b>Se você já efetuou o pagamento, ignore esta mensagem.</b></i>");
				s.append("<br />");
				s.append("<br />");
				s.append("<br />");
				s.append("Listagem de débito(s)");
				s.append("<br />");
				s.append("___________________");
				s.append("<br />");
				s.append("<br />");
				// For para listagem dos titulos
				for (FnTitulo t : titulosPessoa) {
					s.append("<font style='font-size:13px;'><b>Emitido por: </b><i>" + t.getCdpessoaemp().getNome()
							+ "</i>");
					s.append("<br />");
					if (t.getNumnota() > 0) {
						s.append("<b>Número da Nota: </b>" + t.getNumnota());
					} else {
						String doctipo = LcDocTipoNomeUtil.nomeTipoDoc(t.getTpdoc());
						s.append("<b>Número do Documento: </b>" + doctipo + " " + t.getNumdoc());
					}
					s.append("<br />");
					s.append("<b>Parcela:</b> " + t.getParcnum() + "/" + t.getQtdparc());
					s.append("<br />");
					s.append("<b>Vencimento: </b>" + DataUtil.dataPadraoBr(t.getVence()));
					s.append("<br />");
					s.append("<b>Total em aberto R$: </b>" + MoedaUtil.moedaPadrao(t.getVsaldo()));
					s.append("<br />");
					if (t.getTecno_idintegracao() != null) {
						String urlBoleto = mc.linkTecnoHom + "boletos/impressao/" + t.getTecno_idintegracao();
						if (prm.cliente().getSiscfg().getSis_amb_boleto().equals("1")) {
							urlBoleto = mc.linkTecnoProd + "boletos/impressao/" + t.getTecno_idintegracao();
						}
						s.append("<b>Link boleto: </b>" + urlBoleto);
					}
					s.append("</font>");
					s.append("<br /></font>");
					s.append("___________________");
					s.append("<br />");
					s.append("<br />");
				}
				s.append("<br />");
				s.append("<br />");
				s.append("<b>E-mail enviado automaticamente!</b>");
				s.append("<br />");
				s.append("<br />");
				s.append("<br />");
				s.append("<b>Atenciosamente,</b>");
				s.append("<br />");
				s.append("<br />");
				s.append("<b>" + empPrincipal.getNome() + "</b>");
				s.append("<br />");
				s.append(empPrincipal.getFoneum());
				s.append("<br />");
				s.append(empPrincipal.getCdcidade().getNome() + "/" + empPrincipal.getCdestado().getUf());
				s.append("<br />");
				// Seleciona email financeiro padrao
				Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(empPrincipal.getId(), "04");
				AuxEmail em = criaAuxEmail(rem.get(), null);
				emailServiceUtil.EnviaHTML(emailEnvio, assunto, s.toString(), null, null, em);
			}
		}
	}

	// SPED
	// ***************************************************************************************
	public void enviaSPEDEmail(HttpServletRequest request, FsSped sped) throws Exception {
		CdPessoa emit = sped.getCdpessoaemp();
		CdPessoa dest = cdPessoaRp.findFirstByTipoLocal("CONTADOR", "ATIVO", emit.getId());
		// Ajuste mes
		String mes = sped.getMes() + "";
		if (mes.length() == 1) {
			mes = "0" + mes;
		}
		// Seleciona email fiscal/faturamento padrao
		Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(emit.getId(), "02");
		String assunto = emit.getNome() + " | Envio de SPED de " + mes + "/" + sped.getAno();
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append("<br />");
		s.append(emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj()));
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Você está recebendo em anexo o arquivo de texto do SPED de <b>" + mes + "/" + sped.getAno()
				+ "</b> neste e-mail. ");
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Emitido por:</b> " + emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj())
				+ "<br />");
		s.append("<b>Período:</b> " + mes + "/" + sped.getAno() + "<br />");
		s.append("<b>Emitido em:</b> " + DataUtil.dataPadraoBr(sped.getDatacad()) + "<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(rem.get().getAssinatura());
		s.append("<br />");
		s.append("<br />");
		// CRIA ANEXOS
		String nomeSPED = "SPED_" + mes + "_" + sped.getAno() + "_CNPJ-" + emit.getCpfcnpj() + ".txt";
		byte[] spedByte = fsSpedService.geraSped(sped);
		String spedtexto = new String(spedByte, StandardCharsets.UTF_8);
		// Files----------
		File tempSPED = LerArqUtil.criaConteudoFile(nomeSPED, spedtexto, "xml");
		List<AuxDados> anexos = new ArrayList<AuxDados>();
		// XML------------
		AuxDados emSPED = new AuxDados();
		emSPED.setCampo1(nomeSPED);
		emSPED.setCampo2(tempSPED.getAbsolutePath());
		anexos.add(emSPED);
		// Envio
		AuxEmail em = criaAuxEmail(rem.get(), null);
		emailServiceUtil.EnviaHTML(dest.getEmail(), assunto, s.toString(), anexos, null, em);
		// REMOVE ARQUIVOS
		tempSPED.delete();
	}

	// COTACAO
	// **************************************************************************************
	public void enviaCotacaoEmail(HttpServletRequest request, LcDoc lcdoc, String emailsOutros) throws Exception {
		CdPessoa emit = lcdoc.getCdpessoaemp();
		// Seleciona email fiscal/faturamento padrao
		Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(emit.getId(), "05");
		// GERA EXCEL
		ByteArrayInputStream cotByteIS = LcDocCotacaoExcelService.exportToExcel(lcdoc);
		byte[] cotByte = IOUtils.toByteArray(cotByteIS);
		String assunto = emit.getNome() + " | Solicitamos Cotação de Preços ";
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append("<br />");
		s.append(emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj()));
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Você está recebendo em anexo o arquivo de Excel da Nossa Cotação de Preços neste e-mail. ");
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Emitido por:</b> " + emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj())
				+ "<br />");
		s.append("<b>Número da cotação:</b> " + lcdoc.getNumero() + "<br />");
		s.append("<b>Emitido em:</b> " + DataUtil.dataPadraoBr(lcdoc.getDatacad()) + "<br />");
		s.append("<b>Previsão de compra:</b> " + DataUtil.dataPadraoBr(lcdoc.getDataprev()) + "<br />");
		s.append(
				"<i><b>Aviso: É essencial que o arquivo seja preenchido com o padrão fornecido. Dúvidas entre em contato com o emitente!</b></i> <br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(rem.get().getAssinatura());
		s.append("<br />");
		s.append("<br />");
		// Envio
		AuxEmail em = criaAuxEmail(rem.get(), null);
		// Envia varios
		List<LcDocItemCot> cots = lcDocItemCotRp.listaPorFonecLcdoc(lcdoc.getId());
		for (LcDocItemCot c : cots) {
			CdPessoa p = c.getCdpessoacot();
			String emailEnvio = p.getEmail();
			if (p.getEmailfin() != null) {
				if (!p.getEmailfin().equals("")) {
					emailEnvio = p.getEmailfin();
				}
			}
			// BYTES - dentro para poder renomear
			// Cria arquivo unico
			String nomeCot = "COTACAO_" + lcdoc.getNumero() + "_CNPJ-" + emit.getCpfcnpj() + "-" + p.getNome();
			List<AuxDadosByte> anexos = new ArrayList<AuxDadosByte>();
			AuxDadosByte emByte = new AuxDadosByte();
			emByte.setArquivo(cotByte);
			emByte.setExtensao("xlsx");
			emByte.setFiletype("xml/xml; charset=utf-8");
			emByte.setNome(nomeCot);
			anexos.add(emByte);
			emailServiceUtil.EnviaHTML(emailEnvio, assunto, s.toString(), null, anexos, em);
		}
		// Outros - nao passa pelo For para nao repetir
		if (emailsOutros != null) {
			if (!emailsOutros.equals("")) {
				// Cria arquivo unico
				String nomeCot = "COTACAO_" + lcdoc.getNumero() + "_CNPJ-" + emit.getCpfcnpj();
				List<AuxDadosByte> anexos = new ArrayList<AuxDadosByte>();
				AuxDadosByte emByte = new AuxDadosByte();
				emByte.setArquivo(cotByte);
				emByte.setExtensao("xlsx");
				emByte.setFiletype("xml/xml; charset=utf-8");
				emByte.setNome(nomeCot);
				anexos.add(emByte);
				emailServiceUtil.EnviaHTML(emailsOutros, assunto, s.toString(), null, anexos, em);
			}
		}
	}

	// Frete - cotacao
	public void enviaEmailCotacaoFrete(List<AuxDados> auxdados) throws Exception {
		// GET(0) e (1) - Pega primeiros itens da lista, como emails envio e primeiro
		// documento
		Long iddoc = Long.valueOf(auxdados.get(1).getCampo1());
		LcDoc doc = lcDocRp.findById(iddoc).get();
		CdPessoa empPrincipal = doc.getCdpessoaemp();
		String assunto = "Cotação de frete | " + empPrincipal.getNome();
		StringBuilder s = new StringBuilder();
		// Monta demais dados
		String destinatarios = auxdados.get(0).getCampo1();
		// Seleciona email faturamento padrao
		Optional<CdEmail> rem = cdEmailRp.findByLocalEnvio(empPrincipal.getId(), "02");
		// Montagem email
		s.append("<br />");
		s.append("Olá,");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Segue pedido de cotação de frete:");
		s.append("<br />");
		s.append("<br />");
		for (AuxDados a : auxdados) {
			if (!a.getCampo1().contains("@")) {
				Long iddocaux = Long.valueOf(a.getCampo1());
				LcDoc docaux = lcDocRp.findById(iddocaux).get();
				s.append("Pedido: <b>" + docaux.getNumero() + "</b><br />");
				if (docaux.getNumnota() > 0) {
					s.append("NF-e: <b>" + docaux.getNumnota() + "</b><br />");
				} else {
					s.append("NF-e: <b>" + "Não emitido" + "</b><br />");
				}
				s.append("Emissão: <b>" + DataUtil.dataPadraoBr(docaux.getDataem()) + "</b><br />");
				s.append("<br />");
				s.append("Destinatário: <b>" + docaux.getCdpessoapara().getNome() + "</b><br />");
				s.append("CPF/CNPJ: <b>" + CaracterUtil.formataCPFCNPJ(docaux.getCdpessoapara().getCpfcnpj())
						+ "</b><br />");
				s.append("Endereço: <b>" + docaux.getCdpessoapara().getCdcidade().getNome() + "/"
						+ docaux.getCdpessoapara().getCdestado().getUf() + "</b><br />");
				s.append("CEP: <b>" + docaux.getCdpessoapara().getCep() + "</b><br />");
				s.append("<br />");
				s.append("Quantidade: <b>" + MoedaUtil.moedaPadraoScale(docaux.getQtd(), 3) + "</b><br />");
				s.append("Peso Bruto KG: <b>" + MoedaUtil.moedaPadraoScale(docaux.getMpesob(), 3) + "</b><br />");
				s.append("Metragem M3: <b>" + MoedaUtil.moedaPadraoScale(docaux.getMmcub(), 3) + "</b><br />");
				s.append("Valor NF-e/Pedido R$: <b>" + MoedaUtil.moedaPadrao(docaux.getVtottrib()) + "</b><br />");
				s.append("______________________________________________________");
				s.append("<br />");
				s.append("<br />");
			}
		}
		s.append("<br />");
		s.append("<br />");
		s.append(auxdados.get(0).getCampo2());
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>E-mail enviado automaticamente!</b>");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Atenciosamente,</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(rem.get().getAssinatura());
		s.append("<br />");
		s.append("<br />");
		AuxEmail em = criaAuxEmail(rem.get(), null);
		emailServiceUtil.EnviaHTML(destinatarios, assunto, s.toString(), null, null, em);
	}
}
