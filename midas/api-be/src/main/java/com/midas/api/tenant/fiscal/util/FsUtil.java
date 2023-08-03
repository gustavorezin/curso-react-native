package com.midas.api.tenant.fiscal.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.midas.api.constant.FsNfeWebService;
import com.midas.api.mt.entity.SisCfg;
import com.midas.api.tenant.entity.CdNfeCfgSimples;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.util.CryptPassUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.MoedaUtil;

import br.inf.portalfiscal.cte.ObjectFactory_distCte;
import br.inf.portalfiscal.cte.TConsReciCTe;
import br.inf.portalfiscal.cte.TEnviCTe;
import br.inf.portalfiscal.cte.TInutCTe;
import br.inf.portalfiscal.mdfe.ObjectFactory_consr;
import br.inf.portalfiscal.mdfe.ObjectFactory_eve;
import br.inf.portalfiscal.mdfe.ObjectFactory_stat;
import br.inf.portalfiscal.mdfe.TConsReciMDFe;
import br.inf.portalfiscal.mdfe.TEnviMDFe;
import br.inf.portalfiscal.nfe.DistDFeInt;
import br.inf.portalfiscal.nfe.ObjectFactory;
import br.inf.portalfiscal.nfe.ObjectFactoryCC;
import br.inf.portalfiscal.nfe.ObjectFactoryEvento;
import br.inf.portalfiscal.nfe.ObjectFactory_ConsCad;
import br.inf.portalfiscal.nfe.ObjectFactory_Stat;
import br.inf.portalfiscal.nfe.ObjectFactory_dist;
import br.inf.portalfiscal.nfe.ObjectFactory_inut;
import br.inf.portalfiscal.nfe.TConsCad;
import br.inf.portalfiscal.nfe.TConsStatServ;
import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEnvEventoCC;
import br.inf.portalfiscal.nfe.TEnviNFe;
import br.inf.portalfiscal.nfe.TInutNFe;
import br.inf.portalfiscal.nfse.Envio;
import br.inf.portalfiscal.nfse.ObjectFactoryNFSe;

public class FsUtil {
	// CONVERTE DADOS DO RETORNO DO SEFAZ
	public static <T> T xmlToObject(String xml, Class<T> classe) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(classe);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), classe).getValue();
	}

	// XML DA NFE ENVIO
	public static String xmlNFe(TEnviNFe enviNFe) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TEnviNFe.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TEnviNFe> element = new ObjectFactory().createEnviNFe(enviNFe);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll("<NFe>", "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">");
		xml = xml.replaceAll("<enviNFe versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\" >",
				"<enviNFe versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">");
		return xml;
	}

	// EXTRAIR XML DETALHE
	public static String eXmlDet(String xml, String elemento, Integer item, String tagext)
			throws SAXException, IOException, ParserConfigurationException {
		ByteArrayInputStream ins = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(ins);
		NodeList elements = doc.getElementsByTagName(elemento);
		Element el = (Element) elements.item(item);
		if (el != null) {
			NodeList cst = el.getElementsByTagName(tagext);
			Element ele = (Element) cst.item(0);
			if (ele == null) {
				return "";
			} else {
				String retorno = ele.getTextContent();
				return retorno;
			}
		}
		return "";
	}

	// EXTRAIR XML DETALHE - INTEGER
	public static Integer eXmlDetInt(String xml, String elemento, Integer item, String tagext)
			throws SAXException, IOException, ParserConfigurationException {
		ByteArrayInputStream ins = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(ins);
		NodeList elements = doc.getElementsByTagName(elemento);
		Element el = (Element) elements.item(item);
		if (el != null) {
			NodeList cst = el.getElementsByTagName(tagext);
			Element ele = (Element) cst.item(0);
			if (ele == null) {
				return null;
			} else {
				Integer retorno = Integer.valueOf(ele.getTextContent());
				return retorno;
			}
		}
		return null;
	}

	// EXTRAIR XML DETALHE - BIG DECIMAL
	public static BigDecimal eXmlDetBD(String xml, String elemento, Integer item, String tagext)
			throws SAXException, IOException, ParserConfigurationException {
		ByteArrayInputStream ins = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(ins);
		NodeList elements = doc.getElementsByTagName(elemento);
		Element el = (Element) elements.item(item);
		if (el != null) {
			NodeList cst = el.getElementsByTagName(tagext);
			Element ele = (Element) cst.item(0);
			if (ele == null) {
				return new BigDecimal(0);
			} else {
				BigDecimal retorno = new BigDecimal(ele.getTextContent());
				return retorno;
			}
		}
		return new BigDecimal(0);
	}

	// EXTRAIR ITEM E SUBITEM - STRING
	public static String eXmlSub(String xml, String elemento, Integer item)
			throws SAXException, IOException, ParserConfigurationException {
		ByteArrayInputStream ins = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(ins);
		NodeList nodeList = doc.getDocumentElement().getElementsByTagName(elemento);
		String retorno = "";
		Element element = (Element) nodeList.item(item);
		if (element == null) {
			return "";
		} else {
			retorno = element.getTextContent();
			return retorno;
		}
	}

	// EXTRAIR ITEM E SUBITEM - BIGDECIMAL
	public static BigDecimal eXmlSubBD(String xml, String elemento, Integer item)
			throws SAXException, IOException, ParserConfigurationException {
		ByteArrayInputStream ins = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(ins);
		NodeList nodeList = doc.getDocumentElement().getElementsByTagName(elemento);
		Element element = (Element) nodeList.item(item);
		if (element == null) {
			return new BigDecimal(0);
		} else {
			BigDecimal retorno = new BigDecimal(element.getTextContent());
			return retorno;
		}
	}

	// EXTRAIR ITEM E SUBITEM - INTEGER
	public static Integer eXmlSubInt(String xml, String elemento, Integer item)
			throws SAXException, IOException, ParserConfigurationException {
		ByteArrayInputStream ins = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(ins);
		NodeList nodeList = doc.getDocumentElement().getElementsByTagName(elemento);
		Element element = (Element) nodeList.item(item);
		if (element == null) {
			return null;
		} else {
			Integer retorno = Integer.valueOf(element.getTextContent());
			return retorno;
		}
	}

	// METODO CONVERTE OBJETO EM STRING STATUS
	public static String xmlCons(TConsStatServ cons) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TConsStatServ.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TConsStatServ> element = ObjectFactory_Stat.createConsStatServ(cons);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		return xml;
	}

	// METODO CONVERTE OBJETO EM STRING CONSCAD
	public static String xmlConsCad(TConsCad cons) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TConsCad.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TConsCad> element = ObjectFactory_ConsCad.createConsCadNFe(cons);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		return xml;
	}

	// METODO CONVERTE OBJETO EM STRING DIST NFE
	public static String xmlDistfe(DistDFeInt cons) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(DistDFeInt.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<DistDFeInt> element = ObjectFactory_dist.createDistDFeInt(cons);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		return xml;
	}

	// METODO CONVERTE OBJETO EM STRING STATUS
	public static String xmlInut(TInutNFe cons) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TInutNFe.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TInutNFe> element = ObjectFactory_inut.createInutNFe(cons);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		return xml;
	}

	// METODO CONVERTE OBJETO EM STRING DIST CTE
	public static String xmlDistcte(br.inf.portalfiscal.cte.DistDFeInt cons) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(br.inf.portalfiscal.cte.DistDFeInt.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<br.inf.portalfiscal.cte.DistDFeInt> element = ObjectFactory_distCte.createDistDFeInt(cons);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		return xml;
	}

	public static String xmlInutCte(TInutCTe cons) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TInutCTe.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TInutCTe> element = new br.inf.portalfiscal.cte.ObjectFactory_inut().createInutCTe(cons);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		return xml;
	}

	public static String xmlEvento(TEnvEvento envEvento) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TEnvEvento.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TEnvEvento> element = new ObjectFactoryEvento().createEnvEvento(envEvento);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");
		return xml;
	}

	public static String xmlEventoCC(TEnvEventoCC envEvento) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TEnvEventoCC.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TEnvEventoCC> element = new ObjectFactoryCC().createEnvEvento(envEvento);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");
		return xml;
	}

	// METODO CONVERTE OBJETO EM STRING STATUS
	public static String xmlConsCTe(br.inf.portalfiscal.cte.TConsStatServ cons) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(br.inf.portalfiscal.cte.TConsStatServ.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<br.inf.portalfiscal.cte.TConsStatServ> element = br.inf.portalfiscal.cte.ObjectFactory_Stat
				.createConsStatServCte(cons);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		return xml;
	}

	// XML DA NFSE ENVIO
	public static String xmlNFSe(Envio enviNFSe) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Envio.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<Envio> element = new ObjectFactoryNFSe().createEnviNFSe(enviNFSe);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll("ns2:envio xmlns:ns2=\"http://www.portalfiscal.inf.br/nfse\"", "Envio");
		xml = xml.replaceAll("/ns2:envio", "/Envio");
		return xml;
	}

	// XML DA CTE ENVIO
	public static String xmlCTe(TEnviCTe enviCTe) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TEnviCTe.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TEnviCTe> element = new br.inf.portalfiscal.cte.ObjectFactory().createEnviCTe(enviCTe);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll("<CTe>", "<CTe xmlns=\"http://www.portalfiscal.inf.br/cte\">");
		xml = xml.replaceAll("xmlns=\"http://www.portalfiscal.inf.br/cte\" ",
				"xmlns=\"http://www.portalfiscal.inf.br/cte\"");
		return xml;
	}

	// XML DA MDFE ENVIO
	public static String xmlMDFe(TEnviMDFe enviMDFe) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TEnviMDFe.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TEnviMDFe> element = new br.inf.portalfiscal.mdfe.ObjectFactory().createEnviMDFe(enviMDFe);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll("<MDFe>", "<MDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\">");
		return xml;
	}

	// METODO CONVERTE OBJETO EM STRING RECIBO MDFE
	public static String xmlConsRecMDFe(TConsReciMDFe csN) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TConsReciMDFe.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TConsReciMDFe> element = ObjectFactory_consr.createConsReciMDFe(csN);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		return xml;
	}

	// METODO CONVERTE OBJETO EM STRING RECIBO CTE
	public static String xmlConsRecCTe(TConsReciCTe csN) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(TConsReciCTe.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TConsReciCTe> element = br.inf.portalfiscal.cte.ObjectFactory_consr.createConsReciCTe(csN);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		return xml;
	}

	// METODO CONVERTE OBJETO EM STRING STATUS
	public static String xmlCons(br.inf.portalfiscal.mdfe.TConsStatServ cons) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(br.inf.portalfiscal.mdfe.TConsStatServ.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<br.inf.portalfiscal.mdfe.TConsStatServ> element = ObjectFactory_stat.createConsStatServMDFe(cons);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		return xml;
	}

	public static String xmlEventoMDFe(br.inf.portalfiscal.mdfe.TEnvEvento envEvento) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(br.inf.portalfiscal.mdfe.TEnvEvento.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<br.inf.portalfiscal.mdfe.TEnvEvento> element = new ObjectFactory_eve().createEventoMDFe(envEvento);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll("<MDFe>", "<MDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\">");
		return xml;
	}

	public static String xmlEventoCTe(br.inf.portalfiscal.cte.TEnvEvento envEvento) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(br.inf.portalfiscal.cte.TEnvEvento.class);
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<br.inf.portalfiscal.cte.TEnvEvento> element = new br.inf.portalfiscal.cte.ObjectFactory_eve()
				.createEventoCTe(envEvento);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String xml = sw.toString();
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
		xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll("<MDFe>", "<MDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\">");
		return xml;
	}

	// LE PARTE XML
	public static String leNo(String no, Document doc) {
		String retorno = "";
		NodeList nodeList = doc.getDocumentElement().getElementsByTagName(no);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			retorno = element.getTextContent();
		}
		return retorno;
	}

	// CONVERTE ZIP DOCZIP - DIST NFE
	public static String gZipToXml(byte[] conteudo) throws Exception {
		if (conteudo == null || conteudo.length == 0) {
			return "";
		}
		GZIPInputStream gis;
		gis = new GZIPInputStream(new ByteArrayInputStream(conteudo));
		BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		StringBuilder outStr = new StringBuilder();
		String line;
		while ((line = bf.readLine()) != null) {
			outStr.append(line);
		}
		return outStr.toString();
	}

	// CONTROLE DE VALORES NULOS - STRING
	public static String cpN(String campo) {
		String retorno = "";
		if (campo != null) {
			if (!campo.equals("")) {
				if (!campo.equals("null")) {
					retorno = campo;
				}
			}
		}
		return retorno;
	}

	// CONTROLE DE VALORES NULOS - INTEGER
	public static Integer cpInt(String campo) {
		Integer retorno = 0;
		if (campo != null) {
			if (!campo.equals("")) {
				if (!campo.equals("null")) {
					retorno = Integer.valueOf(campo);
				}
			}
		}
		return retorno;
	}

	// CONTROLE DE VALORES NULOS - BIG DECIMAL
	public static BigDecimal cpBD(String campo) {
		BigDecimal retorno = new BigDecimal(0);
		if (campo != null) {
			if (!campo.equals("")) {
				retorno = new BigDecimal(campo);
			}
		}
		return retorno;
	}

	// CONTROLE DE VALORES NULOS - DATA
	public static Date cpDT(String campo) throws ParseException {
		Date retorno = new Date();
		if (campo != null) {
			if (!campo.equals("")) {
				retorno = DataUtil.dUtil(campo);
			}
		}
		return retorno;
	}

	// CONTROLE DE VALORES NULOS - STRING DUPLO
	public static String cpND(String campo, String campo2) {
		String retorno = campo2;
		if (campo != null) {
			if (!campo.equals("")) {
				if (!campo.equals("null")) {
					retorno = campo;
				}
			}
		}
		return retorno;
	}

	// VERIFICA ID-DEST INTERNO, EXTERNO OU EXTERIOR
	public static Integer idDest(LcDoc lcDoc) {
		Integer retorno = 1; // 1 - Op. interna-------------
		String ufsaida = lcDoc.getCdpessoaemp().getCdestado().getUf();
		String ufentrada = lcDoc.getCdpessoapara().getCdestado().getUf();
		if (!ufsaida.equals(ufentrada)) {
			retorno = 2;// 2 - Op. interestadual
		}
		// Verifica op. exterior-----------------------------
		if (lcDoc.getCdpessoapara().getCdestado().getUf().equals("EX")) {
			retorno = 3;// 3 - Op. exterior
		}
		return retorno;
	}

	// VERIFICA TIPO IMPRESSAO
	public static Integer tpImp(String modelo) {
		Integer retorno = 1; // 1 - Danfe normal - retrato
		if (modelo.equals("65")) {
			retorno = 4;// 4 -Danfe normal NFC-e - retrato
		}
		return retorno;
	}

	// SE HOUVER INFO DE COMPRA
	public static boolean verCompra(FsNfe nfe) {
		if (!nfe.getXcont().equals("") || !nfe.getXnemp().equals("") || !nfe.getXped().equals("")) {
			return true;
		}
		return false;
	}

	// VERIFICA INTERMEDIADOR
	public static boolean tpIntermediador(String indpres) {
		if (indpres.equals("2") || indpres.equals("3") || indpres.equals("4") || indpres.equals("9")) {
			return true;
		}
		return false;
	}

	// VERIFICA PAGAMENTO E COBRANCAS
	public static boolean fPagCob(LcDoc lcDoc) {
		String fpag = lcDoc.getFpag();
		if (fpag.equals("90") || fpag.equals("99") || lcDoc.getVtottrib().compareTo(BigDecimal.ZERO) == 0
				|| lcDoc.getCdnfecfg().getFinnfe().equals("3") || lcDoc.getCdnfecfg().getFinnfe().equals("4")) {
			return true;
		}
		return false;
	}

	// MONTA INFORMACOES ADICIONAIS NFE
	public static String montaInfoAddNFe(LcDoc lcDoc, CdNfeCfgSimples cdNfeCfgSimples, SisCfg sisCfg) {
		String retorno = lcDoc.getInfodocfiscal();
		// Verifica se tem Aliquota Simples Nacional-------------------------------
		String incluimsg = lcDoc.getCdnfecfg().getIncluimsgsn();
		if (lcDoc.getVbccredsn().compareTo(BigDecimal.ZERO) > 0 && incluimsg.equals("S")) {
			String paliq = MoedaUtil.moedaPadrao(cdNfeCfgSimples.getAliq());
			String valor = MoedaUtil.moedaPadrao(lcDoc.getVcredsn());
			String msgSN = "PERMITE APROV. DE CRED. FISCAL DE ICMS NO VALOR DE R$ " + valor + " CORRESP. A "
					+ "ALIQUOTA DE " + paliq
					+ "%, NOS TERMOS DO ARTIGO 23 DA LC 123/06. NAO GERA DIREITO CREDITO IPI FISCAL.";
			retorno = retorno + " - " + msgSN;
		}
		// Adiciona metragem cubica se configurado---------------------------------
		if (sisCfg.isSis_mt_auto_nfe()) {
			String mtC = MoedaUtil.moedaPadrao4(lcDoc.getMmcub());
			String msgMT = "TOTAL DE METROS CÚBICOS M3: " + mtC;
			retorno = retorno + " - " + msgMT;
		}
		// Adiciona o IBPT de 33,33 porcento padrao - se documento vendas etc.-----
		if (lcDoc.getVtottrib().compareTo(BigDecimal.ZERO) > 0
				&& (lcDoc.getTipo().equals("01") || lcDoc.getTipo().equals("02"))) {
			String vnacional = MoedaUtil.moedaPadrao(lcDoc.getVibpt_nacional());
			String vimportado = MoedaUtil.moedaPadrao(lcDoc.getVibpt_nacional());
			String vestadual = MoedaUtil.moedaPadrao(lcDoc.getVibpt_nacional());
			String vmunicipal = MoedaUtil.moedaPadrao(lcDoc.getVibpt_nacional());
			String msgIBPT = "V. aprox. de trib.(Fonte IBPT 33,33%). Fed. Nac. R$ " + vnacional + ". " + "Fed. Imp. R$ "
					+ vimportado + ". Est. R$ " + vestadual + ". Mun. R$ " + vmunicipal + ".";
			retorno = retorno + " - " + msgIBPT;
		}
		// Adiciona DIFAL se configurado---------------------------------
		if (lcDoc.getVicmsufdest().compareTo(BigDecimal.ZERO) > 0) {
			String vDif = MoedaUtil.moedaPadrao(lcDoc.getVicmsufdest());
			String msgDif = "DIFAL UF DEST. VALOR R$ " + vDif + " REC. CONFORME GUIA EM ANEXO";
			retorno = retorno + " - " + msgDif;
		}
		// Verifica se tem Ordem compra/serv. e inclui--------------------
		if (lcDoc.getOrdemcps() != null) {
			if (!lcDoc.getOrdemcps().equals("")) {
				retorno = retorno + " - ORDEM C. NUM.: " + lcDoc.getOrdemcps();
			}
		}
		return retorno;
	}

	// MONTA INFORMACOES ADICIONAIS NFSE
	public static String montaInfoAddNFSe(LcDoc lcDoc) {
		String retorno = lcDoc.getInfodocfiscal();
		// Verifica se tem Ordem compra/serv. e inclui--------------------
		if (lcDoc.getOrdemcps() != null) {
			if (!lcDoc.getOrdemcps().equals("")) {
				retorno = retorno + " - ORDEM C. NUM.: " + lcDoc.getOrdemcps();
			}
		}
		if (retorno.length() > 999) {
			retorno = retorno.substring(0, 999);
		}
		return retorno;
	}

	// MONTA INFORMACOES ADICIONAIS CTE
	public static String montaInfoAddCte(FsCte cte) {
		String retorno = cte.getInfcpl();
		// Adiciona o IBPT -----
		if (cte.getFscteicms().getVibpt().compareTo(BigDecimal.ZERO) > 0) {
			String vibpt = MoedaUtil.moedaPadrao(cte.getFscteicms().getVibpt());
			String msgIBPT = "Valor aprox. de tributos R$ " + vibpt + ".";
			retorno = retorno + " - " + msgIBPT;
		}
		return retorno;
	}

	// ARREDONDA BIGDECIMAL 2
	public static BigDecimal aBD2(BigDecimal valor) {
		BigDecimal retorno = valor.setScale(2, RoundingMode.HALF_EVEN);
		return retorno;
	}

	// ARREDONDA BIGDECIMAL
	public static BigDecimal arredondaBigDec(BigDecimal valor, int qtd) {
		BigDecimal retorno = valor.setScale(qtd, RoundingMode.HALF_EVEN);
		return retorno;
	}

	public static String[] qrCodeNFCe(FsNfe nfe) {
		String retorno[] = new String[2];
		CdPessoa emp = nfe.getCdpessoaemp();
		String chave = nfe.getChaveac();
		String amb = nfe.getTpamb() + "";
		String csc = emp.getNfce_csc();
		String url = FsNfeWebService.SVRS_NfceQrCode;
		if (amb.equals("2")) {
			url = FsNfeWebService.SVRS_NfceQrCodeHom;
		}
		String idcsc = emp.getNfce_idcsc();
		String ret = url + "?p=" + chave + "|2|" + amb + "|" + idcsc;
		String chaveSha1 = CryptPassUtil.cSHA1(chave + "|2|" + amb + "|" + idcsc + csc);
		retorno[0] = ret + "|" + chaveSha1;
		retorno[1] = url;
		return retorno;
	}

	public static String cstIPIConv(String ci, BigDecimal vIpi) {
		// Geralmente para notas de entrada do SPED
		String cstIpi = ci;
		if (vIpi.compareTo(BigDecimal.ZERO) > 0) {
			cstIpi = "00";
		}
		if (vIpi.compareTo(BigDecimal.ZERO) == 0) {
			cstIpi = "49";
		}
		return cstIpi;
	}
}