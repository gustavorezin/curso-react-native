package com.midas.api.tenant.fiscal;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.midas.api.tenant.fiscal.util.FsUtil;

@Component
public class GeraNFeEventoProc {
	public String geraProcEvento(String xmlCons, String xmlEnvia, String versaoDados) {
		try {
			String xmlEnviNFe = xmlEnvia;
			String xmlRetConsReciNFe = xmlCons;
			String nProt = FsUtil.eXmlDet(xmlRetConsReciNFe, "infEvento", 0, "nProt");
			Document document = documentFactory(xmlEnviNFe);
			NodeList nodeListCte = document.getDocumentElement().getElementsByTagName("infEvento");
			NodeList nodeListInfCte = document.getElementsByTagName("infEvento");
			String NfeEventoProc = null;
			for (int i = 0; i < nodeListCte.getLength(); i++) {
				Element el = (Element) nodeListInfCte.item(i);
				String chaveNFe = el.getAttribute("Id");
				String xmlProtNFe = getProtNFeEvento(xmlRetConsReciNFe, chaveNFe);
				info("");
				info("ChaveNFe.....: " + chaveNFe);
				info("NFe..........: " + xmlEnviNFe);
				info("ProtNFe......: " + xmlProtNFe);
				info("NFeProc......: " + buildNFeProcEvento(xmlEnviNFe, xmlProtNFe, nProt, versaoDados));
				NfeEventoProc = buildNFeProcEvento(xmlEnviNFe, xmlProtNFe, nProt, versaoDados);
			}
			return NfeEventoProc;
		} catch (Exception e) {
			error(e.toString());
		}
		return null;
	}

	private static String buildNFeProcEvento(String xmlNFeEvento, String xmlProtEventoNFe, String nProt,
			String versaoDados) {
		xmlNFeEvento = xmlNFeEvento.replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");
		xmlNFeEvento = xmlNFeEvento.replaceAll("xmlns=\"http://www.portalfiscal.inf.br/nfe\" ", "");
		xmlNFeEvento = xmlNFeEvento.replaceAll("xmlns=\"http://www.portalfiscal.inf.br/nfe\" ", "");
		xmlNFeEvento = xmlNFeEvento.replaceAll("<idLote>000000000000001</idLote>", "");
		xmlNFeEvento = xmlNFeEvento.replaceAll("<envEvento versao=\"1.00\">", "");
		xmlNFeEvento = xmlNFeEvento.replaceAll("</envEvento>", "");
		xmlProtEventoNFe = xmlProtEventoNFe.replaceAll("<infEvento xmlns=\"http://www.portalfiscal.inf.br/nfe\">",
				"<infEvento Id=\"ID" + nProt + "\">");
		StringBuilder nfeProc = new StringBuilder();
		nfeProc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
				.append("<procEventoNFe versao=\"" + versaoDados + "\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">")
				.append(xmlNFeEvento)
				.append("<retEvento versao=\"" + versaoDados + "\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">")
				.append(xmlProtEventoNFe).append("</retEvento>").append("</procEventoNFe>");
		return nfeProc.toString();
	}

	private static String getProtNFeEvento(String xml, String chaveNFe)
			throws SAXException, IOException, ParserConfigurationException, TransformerException {
		Document document = documentFactory(xml);
		NodeList nodeListProtNFe = document.getDocumentElement().getElementsByTagName("infEvento");
		NodeList nodeListChNFe = document.getElementsByTagName("chNFe");
		for (int i = 0; i < nodeListProtNFe.getLength(); i++) {
			Element el = (Element) nodeListChNFe.item(i);
			String chaveProtNFe = el.getTextContent();
			if (chaveNFe.contains(chaveProtNFe)) {
				// System.out.println("saida..........."+outputXML(nodeListProtNFe.item(i)));
				return outputXML(nodeListProtNFe.item(i));
			}
		}
		return "";
	}

	private static String outputXML(Node node) throws TransformerException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.transform(new DOMSource(node), new StreamResult(os));
		String xml = os.toString();
		if ((xml != null) && (!"".equals(xml))) {
			xml = xml.replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");
		}
		return xml;
	}

	private static Document documentFactory(String xml) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		Document document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
		return document;
	}

	public static String lerXML(String fileXML) throws IOException {
		String linha = "";
		StringBuilder xml = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileXML)));
		while ((linha = in.readLine()) != null) {
			xml.append(linha);
		}
		in.close();
		return xml.toString();
	}

	private static void error(String error) {
		System.out.println("| ERROR NFE PROC: " + error);
	}

	// SAIDA
	private static void info(String info) {
		// System.out.println("| ICTO NFE PROC: " + info);
	}
}
