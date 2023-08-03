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
public class GeraCTeEventoProc {
	public String geraProcEvento(String xmlCons, String xmlEnvia, String versaoDados) {
		try {
			String xmlEnviCTe = xmlEnvia;
			String xmlRetConsReciCTe = xmlCons;
			String nProt = FsUtil.eXmlDet(xmlRetConsReciCTe, "infEvento", 0, "nProt");
			Document document = documentFactory(xmlEnviCTe);
			NodeList nodeListCte = document.getDocumentElement().getElementsByTagName("infEvento");
			NodeList nodeListInfCte = document.getElementsByTagName("infEvento");
			String CteEventoProc = null;
			for (int i = 0; i < nodeListCte.getLength(); i++) {
				Element el = (Element) nodeListInfCte.item(i);
				String chaveCTe = el.getAttribute("Id");
				String xmlProtCTe = getProtCTeEvento(xmlRetConsReciCTe, chaveCTe);
				info("");
				info("ChaveCTe.....: " + chaveCTe);
				info("CTe..........: " + xmlEnviCTe);
				info("ProtCTe......: " + xmlProtCTe);
				info("CTeProc......: " + buildCTeProcEvento(xmlEnviCTe, xmlProtCTe, nProt, versaoDados));
				CteEventoProc = buildCTeProcEvento(xmlEnviCTe, xmlProtCTe, nProt, versaoDados);
			}
			return CteEventoProc;
		} catch (Exception e) {
			error(e.toString());
		}
		return null;
	}

	private static String buildCTeProcEvento(String xmlCTeEvento, String xmlProtEventoCTe, String nProt,
			String versaoDados) {
		xmlCTeEvento = xmlCTeEvento.replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");
		xmlCTeEvento = xmlCTeEvento.replaceAll("xmlns=\"http://www.portalfiscal.inf.br/cte\" ", "");
		xmlProtEventoCTe = xmlProtEventoCTe.replaceAll("xmlns=\"http://www.portalfiscal.inf.br/cte\" ", "");
		StringBuilder cteProc = new StringBuilder();
		cteProc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
				.append("<procEventoCTe versao=\"" + versaoDados + "\" xmlns=\"http://www.portalfiscal.inf.br/cte\">")
				.append(xmlCTeEvento).append("<retEventoCTe versao=\"" + versaoDados + "\">").append(xmlProtEventoCTe)
				.append("</retEventoCTe>").append("</procEventoCTe>");
		return cteProc.toString();
	}

	private static String getProtCTeEvento(String xml, String chaveCTe)
			throws SAXException, IOException, ParserConfigurationException, TransformerException {
		Document document = documentFactory(xml);
		NodeList nodeListProtCTe = document.getDocumentElement().getElementsByTagName("infEvento");
		NodeList nodeListChCTe = document.getElementsByTagName("chCTe");
		for (int i = 0; i < nodeListProtCTe.getLength(); i++) {
			Element el = (Element) nodeListChCTe.item(i);
			String chaveProtCTe = el.getTextContent();
			if (chaveCTe.contains(chaveProtCTe)) {
				// System.out.println("saida..........."+outputXML(nodeListProtCTe.item(i)));
				return outputXML(nodeListProtCTe.item(i));
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
		System.out.println("| ERROR CTE PROC: " + error);
	}

	// SAIDA
	private static void info(String info) {
		// System.out.println("| ICTO CTE PROC: " + info);
	}
}
