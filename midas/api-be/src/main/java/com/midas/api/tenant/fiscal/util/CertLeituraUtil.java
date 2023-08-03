package com.midas.api.tenant.fiscal.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.httpclient.protocol.Protocol;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DLSequence;
import org.springframework.stereotype.Component;

import com.midas.api.tenant.entity.CdCert;

@Component
public class CertLeituraUtil {
	private static final ASN1ObjectIdentifier CNPJ = new ASN1ObjectIdentifier("2.16.76.1.3.3");
	private static final ASN1ObjectIdentifier CPF = new ASN1ObjectIdentifier("2.16.76.1.3.1");

	// DADOS CERTIFICADO**********************************
	public X509Certificate lerCertificado(String certificado, char[] senha) throws Exception {
		// DADOS DO CERTIFICADO
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(new FileInputStream(certificado), senha);
		String alias = ks.aliases().nextElement();
		X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
		return certificate;
	}

	// DADOS PARA EMISSAO DE DOCUMENTOS FISCAIS
	public void geraKS(CdCert cert, String modelo, ServletContext context) throws Exception {
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			InputStream arquivo = new ByteArrayInputStream(cert.getArquivo());
			ks.load(arquivo, cert.getSenha().toCharArray());
			String alias = ks.aliases().nextElement();
			X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
			PrivateKey privatekey = (PrivateKey) ks.getKey(alias, cert.getSenha().toCharArray());
			SocketFactoryDinamico socketFactory = new SocketFactoryDinamico(certificate, privatekey);
			// CADEIA DE CERTIFICADOS**************************************
			// NFE
			if (modelo.equals("55") || modelo.equals("65")) {
				String cacert = context.getRealPath("/cacerts") + File.separator + "nfe-cacerts";
				socketFactory.setFileCacerts(new FileInputStream(cacert));
			}
			// CTE
			if (modelo.equals("57")) {
				String cacert = context.getRealPath("/cacerts") + File.separator + "cte-cacerts";
				socketFactory.setFileCacerts(new FileInputStream(cacert));
			}
			// MDFE
			if (modelo.equals("58")) {
				String cacert = context.getRealPath("/cacerts") + File.separator + "mdfe-cacerts";
				socketFactory.setFileCacerts(new FileInputStream(cacert));
			}
			// PROTOCOLO CONEXAO********************************************
			Protocol protocol = new Protocol("https", socketFactory, 443);
			Protocol.registerProtocol("https", protocol);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}

	// EXTRAI CPF OU CNPJ*********************************
	public String getCpfCnpj(X509Certificate certificate) throws Exception {
		final String[] cnpjCpf = { "" };
		try {
			Optional.ofNullable(certificate.getSubjectAlternativeNames())
					.ifPresent(lista -> lista.stream().filter(x -> x.get(0).equals(0)).forEach(a -> {
						byte[] data = (byte[]) a.get(1);
						try (ASN1InputStream is = new ASN1InputStream(data)) {
							DLSequence derSequence = (DLSequence) is.readObject();
							ASN1ObjectIdentifier tipo = ASN1ObjectIdentifier.getInstance(derSequence.getObjectAt(0));
							if (CNPJ.equals(tipo) || CPF.equals(tipo)) {
								Object objeto = ((DERTaggedObject) ((DERTaggedObject) derSequence.getObjectAt(1))
										.getObject()).getObject();
								if (objeto instanceof DEROctetString) {
									cnpjCpf[0] = new String(((DEROctetString) objeto).getOctets());
								} else if (objeto instanceof DERPrintableString) {
									cnpjCpf[0] = ((DERPrintableString) objeto).getString();
								} else if (objeto instanceof DERUTF8String) {
									cnpjCpf[0] = ((DERUTF8String) objeto).getString();
								} else if (objeto instanceof DERIA5String) {
									cnpjCpf[0] = ((DERIA5String) objeto).getString();
								}
							}
							if (CPF.equals(tipo) && cnpjCpf[0].length() > 25) {
								cnpjCpf[0] = cnpjCpf[0].substring(8, 19);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}));
		} catch (Exception e) {
			throw new Exception("Erro ao pegar CPF/CNPJ do Certificado: " + e.getMessage());
		}
		return cnpjCpf[0];
	}
}
