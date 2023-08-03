package com.midas.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.midas.api.mt.entity.Cep;

public class CepConsUtil {
	private final static String urlcon = "https://viacep.com.br/ws/";
	private final static String urlcomp = "/json/";

	public static Cep consulta(String cepconsulta) throws IOException {
		cepconsulta = CaracterUtil.remUpper(cepconsulta);
		URL url = new URL(urlcon + cepconsulta + urlcomp);
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String cep = "";
		StringBuilder jsonCep = new StringBuilder();
		while ((cep = br.readLine()) != null) {
			jsonCep.append(cep);
		}
		Cep newObj = new Gson().fromJson(jsonCep.toString(), Cep.class);
		return newObj;
	}
}
