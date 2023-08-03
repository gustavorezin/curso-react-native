package com.midas.api.tenant.fiscal.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.midas.api.tenant.entity.CdCert;

public class FsMdfeAssinaService {
	private static final String MDFE = "MDFe";
	private static final String EVENTO = "evento";
	private static PrivateKey privateKey;
	private static KeyInfo keyInfo;

	// ASSINA ENVIA
	public static String assinaEnviMDFe(String xml, CdCert cert) throws Exception {
		Document document = documentFactory(xml);
		XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
		ArrayList<Transform> transformList = signatureFactory(signatureFactory);
		loadCertificatesA1(cert, signatureFactory);
		for (int i = 0; i < document.getDocumentElement().getElementsByTagName(MDFE).getLength(); i++) {
			assinarMDFe(signatureFactory, transformList, privateKey, keyInfo, document, i);
		}
		return outputXML(document);
	}

	// ASSINA MDFE
	private static void assinarMDFe(XMLSignatureFactory fac, ArrayList<Transform> transformList, PrivateKey privateKey,
			KeyInfo ki, Document document, int indexMDFe) throws Exception {
		NodeList elements = document.getElementsByTagName("infMDFe");
		org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(indexMDFe);
		String id = el.getAttribute("Id");
		el.setIdAttribute("Id", true);
		Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null,
				null);
		SignedInfo si = fac.newSignedInfo(
				fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
				fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));
		XMLSignature signature = fac.newXMLSignature(si, ki);
		DOMSignContext dsc = new DOMSignContext(privateKey,
				document.getDocumentElement().getElementsByTagName(MDFE).item(indexMDFe));
		signature.sign(dsc);
	}

	// ASSINA - EVENTO
	public static String assinarEvento(String xml, CdCert cert) throws Exception {
		Document document = documentFactory(xml);
		XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
		ArrayList<Transform> transformList = signatureFactory(signatureFactory);
		loadCertificatesA1(cert, signatureFactory);
		for (int i = 0; i < document.getDocumentElement().getElementsByTagName(EVENTO).getLength(); i++) {
			assinarEvento(EVENTO, signatureFactory, transformList, privateKey, keyInfo, document, i);
		}
		return outputXML(document);
	}

	// ASSINA EVENTOS
	private static void assinarEvento(String tipo, XMLSignatureFactory fac, ArrayList<Transform> transformList,
			PrivateKey privateKey, KeyInfo ki, Document document, int index) throws Exception {
		NodeList elements = null;
		elements = document.getElementsByTagName("infEvento");
		org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(index);
		String id = el.getAttribute("Id");
		el.setIdAttribute("Id", true);
		Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null,
				null);
		SignedInfo si = fac.newSignedInfo(
				fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
				fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));
		XMLSignature signature = fac.newXMLSignature(si, ki);
		DOMSignContext dsc = new DOMSignContext(privateKey,
				document.getDocumentElement().getElementsByTagName(tipo).item(index));
		signature.sign(dsc);
	}

	// CARREGA CERTIFICADO
	private static void loadCertificatesA1(CdCert cert, XMLSignatureFactory signatureFactory) throws Exception {
		InputStream arquivo = new ByteArrayInputStream(cert.getArquivo());
		KeyStore ks = KeyStore.getInstance("pkcs12");
		ks.load(arquivo, cert.getSenha().toCharArray());
		KeyStore.PrivateKeyEntry pkEntry = null;
		Enumeration<String> aliasesEnum = ks.aliases();
		while (aliasesEnum.hasMoreElements()) {
			String alias = (String) aliasesEnum.nextElement();
			if (ks.isKeyEntry(alias)) {
				pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,
						new KeyStore.PasswordProtection(cert.getSenha().toCharArray()));
				privateKey = pkEntry.getPrivateKey();
				break;
			}
		}
		// KEYINFO
		X509Certificate certificado = (X509Certificate) pkEntry.getCertificate();
		KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
		List<X509Certificate> x509Content = new ArrayList<X509Certificate>();
		x509Content.add(certificado);
		X509Data x509Data = keyInfoFactory.newX509Data(x509Content);
		keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
	}

	// SIGN FACTORY
	private static ArrayList<Transform> signatureFactory(XMLSignatureFactory signatureFactory)
			throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
		ArrayList<Transform> transformList = new ArrayList<Transform>();
		TransformParameterSpec tps = null;
		Transform envelopedTransform = signatureFactory.newTransform(Transform.ENVELOPED, tps);
		Transform c14NTransform = signatureFactory.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", tps);
		transformList.add(envelopedTransform);
		transformList.add(c14NTransform);
		return transformList;
	}

	// LEITURA XML
	private static Document documentFactory(String xml) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		Document document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
		return document;
	}

	// SAIDA XML
	private static String outputXML(Document doc) throws TransformerException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.transform(new DOMSource(doc), new StreamResult(os));
		String xml = os.toString();
		if ((xml != null) && (!"".equals(xml))) {
			xml = xml.replaceAll("\\r\\n", "");
			xml = xml.replaceAll(" standalone=\"no\"", "");
		}
		return xml;
	}
}
