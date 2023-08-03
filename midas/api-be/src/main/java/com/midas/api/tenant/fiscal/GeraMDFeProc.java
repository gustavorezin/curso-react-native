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

@Component
public class GeraMDFeProc {
	public String geraProc(String xmlEnvia, String xmlCons, String versaoDados) {
		try {
			String xmlEnviMDFe = xmlEnvia;
			String xmlRetConsReciMDFe = xmlCons;
			Document document = documentFactory(xmlEnviMDFe);
			NodeList nodeListNfe = document.getDocumentElement().getElementsByTagName("MDFe");
			NodeList nodeListInfNfe = document.getElementsByTagName("infMDFe");
			String Nfproc = null;
			for (int i = 0; i < nodeListNfe.getLength(); i++) {
				Element el = (Element) nodeListInfNfe.item(i);
				String chaveMDFe = el.getAttribute("Id");
				String xmlMDFe = outputXML(nodeListNfe.item(i));
				String xmlProtMDFe = getProtMDFe(xmlRetConsReciMDFe, chaveMDFe);
				info("");
				info("ChaveMDFe.....: " + chaveMDFe);
				info("MDFe..........: " + xmlMDFe);
				info("ProtMDFe......: " + xmlProtMDFe);
				info("MDFeProc......: " + buildMDFeProc(xmlMDFe, xmlProtMDFe, versaoDados));
				Nfproc = buildMDFeProc(xmlMDFe, xmlProtMDFe, versaoDados);
			}
			return Nfproc;
		} catch (Exception e) {
			error(e.toString());
		}
		return null;
	}

	private static String buildMDFeProc(String xmlMDFe, String xmlProtMDFe, String versaoDados) {
		StringBuilder mdfeProc = new StringBuilder();
		mdfeProc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
				.append("<mdfeProc versao=\"" + versaoDados + "\" xmlns=\"http://www.portalfiscal.inf.br/mdfe\">")
				.append(xmlMDFe).append(xmlProtMDFe).append("</mdfeProc>");
		return mdfeProc.toString();
	}

	private static String getProtMDFe(String xml, String chaveMDFe) throws Exception {
		Document document = documentFactory(xml);
		NodeList nodeListProtMDFe = document.getDocumentElement().getElementsByTagName("protMDFe");
		NodeList nodeListChMDFe = document.getElementsByTagName("chMDFe");
		for (int i = 0; i < nodeListProtMDFe.getLength(); i++) {
			Element el = (Element) nodeListChMDFe.item(i);
			String chaveProtMDFe = el.getTextContent();
			if (chaveMDFe.contains(chaveProtMDFe)) {
				// System.out.println("saida..........." + outputXML(nodeListProtMDFe.item(i)));
				return outputXML(nodeListProtMDFe.item(i));
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
		System.out.println("| ERROR MDFE PROC: " + error);
	}

	// SAIDA
	private static void info(String info) {
		// System.out.println("| INFO MDFE PROC: " + info);
	}
}
