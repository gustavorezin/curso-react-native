package com.midas.api.tenant.fiscal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;



@Component
public class GeraNFSeProc {


	public String GeraProc( String xmlEnvia, String numNFSe, String codVer) {
		try {
			String Nfsproc = buildNFeProc(xmlEnvia, numNFSe, codVer);
			return Nfsproc;
		} catch (Exception e) {
			error(e.toString());
		}
		return null;
	}

	private static String buildNFeProc(String xmlNFe, String numNFSe, String codVer) {
		StringBuilder nfeProc = new StringBuilder();
		nfeProc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
		.append("<nfseProc>")
		.append(xmlNFe)
		.append("<midas>")
		.append("<NumNFSe>"+numNFSe+"</NumNFSe>")
		.append("<CodVerNFSe>"+codVer+"</CodVerNFSe>")
		.append("</midas>")
		.append("</nfseProc>");
		return nfeProc.toString();
	}

	public static String lerXML(String fileXML) throws IOException {  
		String linha = "";  
		StringBuilder xml = new StringBuilder();  

		BufferedReader in = new BufferedReader(new InputStreamReader(  
				new FileInputStream(fileXML)));  
		while ((linha = in.readLine()) != null) {  
			xml.append(linha);  
		}  
		in.close();  

		return xml.toString();  
	}

	private static void error(String error) {
		System.out.println("| ERROR NFSE PROC: " + error);
	}

	//SAIDA
	/*private static void info(String info) {
		//System.out.println("| INFO NFE PROC: " + info);
	}*/

}