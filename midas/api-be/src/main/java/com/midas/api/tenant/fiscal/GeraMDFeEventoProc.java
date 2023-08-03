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
public class GeraMDFeEventoProc {
	public String geraProcEvento(String xmlCons, String xmlEnvia, String versaoDados) {
		try {
			String xmlEnviMDFe = xmlEnvia;
			String xmlRetConsReciMDFe = xmlCons;
			String nProt = FsUtil.eXmlDet(xmlRetConsReciMDFe, "infEvento", 0, "nProt");
			Document document = documentFactory(xmlEnviMDFe);
			NodeList nodeListCte = document.getDocumentElement().getElementsByTagName("infEvento");
			NodeList nodeListInfCte = document.getElementsByTagName("infEvento");
			String MdfeEventoProc = null;
			for (int i = 0; i < nodeListCte.getLength(); i++) {
				Element el = (Element) nodeListInfCte.item(i);
				String chaveMDFe = el.getAttribute("Id");
				String xmlProtMDFe = getProtMDFeEvento(xmlRetConsReciMDFe, chaveMDFe);
				info("");
				info("ChaveMDFe.....: " + chaveMDFe);
				info("MDFe..........: " + xmlEnviMDFe);
				info("ProtMDFe......: " + xmlProtMDFe);
				info("MDFeProc......: " + buildMDFeProcEvento(xmlEnviMDFe, xmlProtMDFe, nProt, versaoDados));
				MdfeEventoProc = buildMDFeProcEvento(xmlEnviMDFe, xmlProtMDFe, nProt, versaoDados);
			}
			return MdfeEventoProc;
		} catch (Exception e) {
			error(e.toString());
		}
		return null;
	}

	private static String buildMDFeProcEvento(String xmlMDFeEvento, String xmlProtEventoMDFe, String nProt,
			String versaoDados) {
		xmlMDFeEvento = xmlMDFeEvento.replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");
		xmlMDFeEvento = xmlMDFeEvento.replaceAll("xmlns=\"http://www.portalfiscal.inf.br/mdfe\" ", "");
		xmlMDFeEvento = xmlMDFeEvento.replaceAll("xmlns=\"http://www.portalfiscal.inf.br/mdfe\" ", "");
		xmlMDFeEvento = xmlMDFeEvento.replaceAll("<idLote>000000000000001</idLote>", "");
		xmlMDFeEvento = xmlMDFeEvento.replaceAll("<envEvento versao=\"1.00\">", "");
		xmlMDFeEvento = xmlMDFeEvento.replaceAll("</envEvento>", "");
		xmlProtEventoMDFe = xmlProtEventoMDFe.replaceAll("<infEvento xmlns=\"http://www.portalfiscal.inf.br/mdfe\">",
				"<infEvento Id=\"ID" + nProt + "\">");
		StringBuilder mdfeProc = new StringBuilder();
		mdfeProc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
				.append("<procEventoMDFe versao=\"" + versaoDados + "\" xmlns=\"http://www.portalfiscal.inf.br/mdfe\">")
				.append(xmlMDFeEvento)
				.append("<retEvento versao=\"" + versaoDados + "\" xmlns=\"http://www.portalfiscal.inf.br/mdfe\">")
				.append(xmlProtEventoMDFe).append("</retEvento>").append("</procEventoMDFe>");
		return mdfeProc.toString();
	}

	private static String getProtMDFeEvento(String xml, String chaveMDFe)
			throws SAXException, IOException, ParserConfigurationException, TransformerException {
		Document document = documentFactory(xml);
		NodeList nodeListProtMDFe = document.getDocumentElement().getElementsByTagName("infEvento");
		NodeList nodeListChMDFe = document.getElementsByTagName("chMDFe");
		for (int i = 0; i < nodeListProtMDFe.getLength(); i++) {
			Element el = (Element) nodeListChMDFe.item(i);
			String chaveProtMDFe = el.getTextContent();
			if (chaveMDFe.contains(chaveProtMDFe)) {
				// System.out.println("saida..........."+outputXML(nodeListProtMDFe.item(i)));
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
		// System.out.println("| ICTO MDFE PROC: " + info);
	}
}
