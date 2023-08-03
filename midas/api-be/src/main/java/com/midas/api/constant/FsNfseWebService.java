package com.midas.api.constant;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class FsNfseWebService implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String versaoDados = "1.00";
	public final static String chavePk = "V7tGKA2erpADfJxRHZrvZA==";
	// HOMOLOGACAO
	private final static String NFSeRecepcaoHom = "https://homolog.invoicy.com.br:443/arecepcao.aspx";
	// PRODUCAO
	private final static String NFSeRecepcao = "https://nfse.invoicy.com.br/arecepcao.aspx?WSDL";

	// Envio servico
	public static String linkEnvioServico(String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			return NFSeRecepcao;
		}
		// Homologacao
		if (ambiente.equals("2")) {
			return NFSeRecepcaoHom;
		}
		return null;
	}
}
