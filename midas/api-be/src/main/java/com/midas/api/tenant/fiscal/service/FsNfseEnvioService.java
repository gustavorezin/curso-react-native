package com.midas.api.tenant.fiscal.service;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.constant.FsNfseWebService;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsNfse;
import com.midas.api.tenant.fiscal.GeraNFSeProc;
import com.midas.api.util.CaracterUtil;

import br.inf.portalfiscal.nfse.EnvioEvento;
import br.inf.portalfiscal.nfse.Evento;
import br.inf.portalfiscal.nfse.ObjectFactory;

@Service
public class FsNfseEnvioService {
	@Autowired
	private GeraNFSeProc geraNFSeProc;

	// ENVIO NFSE
	// *********************************************************************************
	public String[] envioNFSe(FsNfse nfse, String xml) throws Exception {
		String[] reps = envia(nfse, xml, "ENVIAR");
		return reps;
	}

	// CANCELA NFSE
	// *******************************************************************************
	public String[] cancelaNFSe(FsNfse nfse, String justifica) throws Exception {
		String xml = xmlCanc(nfse, justifica);
		String[] reps = envia(nfse, xml, "CANCELAR");
		return reps;
	}

	// UTILS NFSE
	// *********************************************************************************
	// ENVIO NFSE
	public String[] envia(FsNfse nfse, String xml, String evento) throws Exception {
		try {
			// Adiciono para variaveis o texto do XML da tela
			String EmpPK = FsNfseWebService.chavePk;
			String XmlIntegracao = xml;
			String ChaveComunicacao = nfse.getCdnfecfg().getChavecom_nfse();
			// Chama função que faz a escrita do SOAP
			String xmlGerado = retornaEscritaSoap(XmlIntegracao, EmpPK, ChaveComunicacao);
			// Guarda em stream o contedúdo da escrita do XML
			InputStream stream = new ByteArrayInputStream(xmlGerado.getBytes());
			// Pega o conteudo stream e define codificação a ele.
			// Antigo--InputStreamEntity reqEntity = new InputStreamEntity(stream, -1,
			// ContentType.TEXT_XML);
			InputStreamEntity reqEntity = new InputStreamEntity(stream, -1);
			// Informar True, pois o tamanho da entidade é desconhecido, ou seja, pode
			// variar dependendo do tamanho da string
			reqEntity.setChunked(true);
			// Informa o nome da URL que será feito o POST
			HttpPost httppost = new HttpPost("");
			httppost = new HttpPost(FsNfseWebService.linkEnvioServico(nfse.getTpamb().toString()));
			httppost.setEntity(reqEntity);
			// Instancia um objeto httpclient
			CloseableHttpClient httpclient = HttpClients.createDefault();
			// Instancia um objeto httpresponse para receber o retorno da requisição
			CloseableHttpResponse response = httpclient.execute(httppost);
			// Grava em buffer o stream do retorno da requisição
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			// Faz a leitura linha a linha do retorno da requisição
			StringBuilder result = new StringBuilder();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line.replaceAll("\t", "").replaceAll("\n", ""));
			}
			String retorno = CaracterUtil.rem(result + "");
			String codigoRetorno = "0";
			String descricaoRetorno = "0";
			String NFSeNumero = "0";
			String NFSeCodVerificacao = "0";
			// VERIFICA ERROS - ERRO INICAL
			if (retorno.toString().contains("<Codigo>")) {
				codigoRetorno = retorno.substring(retorno.indexOf("<Codigo>"), retorno.indexOf("</Codigo>"))
						.replaceAll("<Codigo>", "");
				descricaoRetorno = retorno.substring(retorno.indexOf("<Descricao>"), retorno.indexOf("</Descricao>"))
						.replaceAll("<Descricao>", "");
			}
			// VERIFICA ERROS - ERRO NFSE PREENCHIMENTO
			if (!retorno.toString().contains("NFSeNumero") && retorno.toString().contains("<SitCodigo>")) {
				codigoRetorno = retorno.substring(retorno.indexOf("<SitCodigo>"), retorno.indexOf("</SitCodigo>"))
						.replaceAll("<SitCodigo>", "");
				descricaoRetorno = retorno
						.substring(retorno.indexOf("<SitDescricao>"), retorno.indexOf("</SitDescricao>"))
						.replaceAll("<SitDescricao>", "");
				if (evento.equals("ENVIAR")) {
					NFSeNumero = retorno.substring(retorno.indexOf("<NFSeNumero>"), retorno.indexOf("</NFSeNumero>"))
							.replaceAll("<NFSeNumero>", "");
					NFSeCodVerificacao = result.substring(retorno.indexOf("<NFSeCodVerificacao>"),
							retorno.indexOf("</NFSeCodVerificacao>")).replaceAll("<NFSeCodVerificacao>", "");
				}
			}
			// APROVADO NFSE
			if (retorno.toString().contains("NFSeNumero")) {
				codigoRetorno = retorno.substring(retorno.indexOf("<SitCodigo>"), retorno.indexOf("</SitCodigo>"))
						.replaceAll("<SitCodigo>", "");
				descricaoRetorno = retorno
						.substring(retorno.indexOf("<SitDescricao>"), retorno.indexOf("</SitDescricao>"))
						.replaceAll("<SitDescricao>", "");
				if (evento.equals("ENVIAR")) {
					NFSeNumero = retorno.substring(retorno.indexOf("<NFSeNumero>"), retorno.indexOf("</NFSeNumero>"))
							.replaceAll("<NFSeNumero>", "");
					if (!NFSeNumero.equals("0")) {
						NFSeCodVerificacao = retorno
								.substring(retorno.indexOf("<NFSeCodVerificacao>"),
										retorno.indexOf("</NFSeCodVerificacao>"))
								.replaceAll("<NFSeCodVerificacao>", "");
					}
				}
			}
			// GERA NFSE PROC
			String xmlProc = geraNFSeProc.GeraProc(xml, NFSeNumero, NFSeCodVerificacao);
			String[] reps = new String[5];
			reps[0] = codigoRetorno;
			reps[1] = descricaoRetorno;
			reps[2] = NFSeNumero;
			reps[3] = NFSeCodVerificacao;
			reps[4] = xmlProc;
			return reps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// CRIA XML DE CANCELAMENTO
	private static String xmlCanc(FsNfse nfse, String justifica)
			throws SQLException, PropertyVetoException, JAXBException {
		EnvioEvento envioEvento = new EnvioEvento();
		envioEvento.setModeloDocumento("NFSe");
		envioEvento.setVersao(BigDecimal.valueOf(Double.valueOf(FsNfseWebService.versaoDados)));
		envioEvento.setEvento(evento(nfse, justifica));
		String xml = strValueOfCanc(envioEvento);
		return xml;
	}

	// CRIA EVENTO
	private static Evento evento(FsNfse nfse, String justifica) throws SQLException, PropertyVetoException {
		CdPessoa emp = nfse.getCdpessoaemp();
		String cpfcnpj = emp.getCpfcnpj();
		Evento evento = new Evento();
		evento.setCNPJ(cpfcnpj);
		evento.setNFSeNumero(BigInteger.valueOf(nfse.getNnfs()));
		evento.setRPSNumero(BigInteger.valueOf(nfse.getRpsnumero()));
		evento.setRPSSerie(nfse.getRpsserie());
		evento.setEveTp(BigInteger.valueOf(110111));
		evento.setTpAmb(BigInteger.valueOf(Integer.valueOf(nfse.getTpamb())));
		evento.setEveCodigo("1");// Erro na emissao
		evento.setEveMotivo(justifica);
		return evento;
	}

	// TRANSFORMA OBEJTO EM STRING
	private static String strValueOfCanc(EnvioEvento canc) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(EnvioEvento.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<EnvioEvento> element = new ObjectFactory().createEnvEvento(canc);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll("ns2:envioEvento xmlns:ns2=\"http://www.portalfiscal.inf.br/nfse\"", "EnvioEvento");
		xml = xml.replaceAll("/ns2:envioEvento", "/EnvioEvento");
		return xml;
	}

	public static String retornaEscritaSoap(String XmlIntegracao, String EmpPK, String ChaveComunicacao) {
		String sBody = "";
		sBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:inv=\"InvoiCy\">";
		sBody += "<soapenv:Header/>";
		sBody += "<soapenv:Body>";
		sBody += "<inv:recepcao.Execute>";
		sBody += "<inv:Invoicyrecepcao>";
		sBody += "<inv:Cabecalho>";
		sBody += "<inv:EmpPK>" + EmpPK + "</inv:EmpPK>";
		// Lineariza o xml de integração com expressão regular.
		XmlIntegracao = XmlIntegracao.replaceAll("(?ism)(?<=>)[^a-z|0-9]*(?=<)", "");
		// Gera Hash MD5
		String texto = ChaveComunicacao + XmlIntegracao;
		String HashGerado = GeraHashMD5(texto);
		// JOptionPane.showMessageDialog(null, HashGerado);
		sBody += "<inv:EmpCK>" + HashGerado + "</inv:EmpCK>";
		sBody += "<inv:EmpCO></inv:EmpCO>";
		sBody += "</inv:Cabecalho>";
		sBody += "<inv:Informacoes>";
		sBody += "<inv:Texto></inv:Texto>";
		sBody += "</inv:Informacoes>";
		sBody += "<inv:Dados>";
		sBody += "<inv:DadosItem>";
		// Converte XML de integração para texto
		String XmlEnvio = XmlIntegracao;
		XmlEnvio = XmlEnvio.replaceAll("<", "&lt;");
		XmlEnvio = XmlEnvio.replaceAll(">", "&gt;");
		XmlEnvio = XmlEnvio.replaceAll("\"", "&quot;");
		sBody += "<inv:Documento>" + XmlEnvio + "</inv:Documento>";
		sBody += "<inv:Parametros></inv:Parametros>";
		sBody += "</inv:DadosItem>";
		sBody += "</inv:Dados>";
		sBody += "</inv:Invoicyrecepcao>";
		sBody += "</inv:recepcao.Execute>";
		sBody += "</soapenv:Body>";
		sBody += "</soapenv:Envelope>";
		return sBody;
	}

	public static String GeraHashMD5(String xml) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(xml.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < thedigest.length; i++) {
				sb.append(Integer.toHexString((thedigest[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (Exception ex) {
			return ex.toString();
		}
	}
}
