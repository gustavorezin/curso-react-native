package com.midas.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.midas.api.tenant.entity.FsNfePart;
import com.midas.api.tenant.entity.ReceitaWSCNPJ;

public class CNPJConsUtil {
	public static ReceitaWSCNPJ consulta(String cnpj) throws Exception {
		URL url = new URL("http://www.receitaws.com.br/v1/cnpj/" + cnpj);
		URLConnection openConnection = url.openConnection();
		if (openConnection == null) {
			throw new IOException("Não foi possível conectar com o serviço de consulta de CNPJ receitaws");
		}
		InputStream is = openConnection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		Gson gson = new Gson();
		// Transforma o resultado vindo em um objeto
		ReceitaWSCNPJ obj = gson.fromJson(reader, ReceitaWSCNPJ.class);
		return obj;
	}

	public static String cIcms(FsNfePart part) {
		String retorno = "9";
		// Contribuinte
		if (part.getIe() != null) {
			if (part.getCpfcnpj().length() == 14 && !part.getIe().equals("")) {
				retorno = "1";
			}
		} else {
			if (part.getCpfcnpj().length() == 14) {
				retorno = "2";
			}
		}
		return retorno;
	}
}
