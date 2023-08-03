package com.midas.api.util.integra;

import com.midas.api.constant.MidasConfig;
import com.midas.api.security.ClienteParam;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.util.HttpRequest;

public class TecnoUtil {
	// HTTP REQUEST - POST
	public static HttpRequest httpResquestPost(MidasConfig mc, ClienteParam prm, String rota, String json, CdPessoa e) {
		// Verifica ambiente----------------
		String url = mc.linkTecnoProd;
		if (prm.cliente().getSiscfg().getSis_amb_boleto().equals("2")) {
			url = mc.linkTecnoHom;
		}
		// Dados fixos-----------------------
		String contype = "application/json";
		String cnpj_sh = mc.MidasCNPJ;
		String token_sh = mc.tokenTecno;
		String cpfcnpj_cedente = e.getCpfcnpj();
		HttpRequest results = null;
		url = url + rota;
		if (e.getCpfcnpj().length() == 14) {
			results = HttpRequest.post(url).header("Content-Type", contype).header("cnpj-sh", cnpj_sh)
					.header("token-sh", token_sh).header("cnpj-cedente", cpfcnpj_cedente).send(json);
		} else {
			results = HttpRequest.post(url).header("Content-Type", contype).header("cnpj-sh", cnpj_sh)
					.header("token-sh", token_sh).header("cpf-cedente", cpfcnpj_cedente).send(json);
		}
		return results;
	}

	// HTTP REQUEST - PUT
	public static HttpRequest httpResquestPut(MidasConfig mc, ClienteParam prm, String rota, String json, CdPessoa e) {
		// Verifica ambiente----------------
		String url = mc.linkTecnoProd;
		if (prm.cliente().getSiscfg().getSis_amb_boleto().equals("2")) {
			url = mc.linkTecnoHom;
		}
		// Dados fixos-----------------------
		String contype = "application/json";
		String cnpj_sh = mc.MidasCNPJ;
		String token_sh = mc.tokenTecno;
		String cpfcnpj_cedente = e.getCpfcnpj();
		HttpRequest results = null;
		url = url + rota;
		if (e.getCpfcnpj().length() == 14) {
			results = HttpRequest.put(url).header("Content-Type", contype).header("cnpj-sh", cnpj_sh)
					.header("token-sh", token_sh).header("cnpj-cedente", cpfcnpj_cedente).send(json);
		} else {
			results = HttpRequest.put(url).header("Content-Type", contype).header("cnpj-sh", cnpj_sh)
					.header("token-sh", token_sh).header("cpf-cedente", cpfcnpj_cedente).send(json);
		}
		return results;
	}

	// HTTP REQUEST - GET
	public static HttpRequest httpResquestGet(MidasConfig mc, ClienteParam prm, String rota, CdPessoa e) {
		// Verifica ambiente----------------
		String url = mc.linkTecnoProd;
		if (prm.cliente().getSiscfg().getSis_amb_boleto().equals("2")) {
			url = mc.linkTecnoHom;
		}
		// Dados fixos-----------------------
		String contype = "application/json";
		String cnpj_sh = mc.MidasCNPJ;
		String token_sh = mc.tokenTecno;
		String cpfcnpj_cedente = e.getCpfcnpj();
		HttpRequest results = null;
		url = url + rota;
		if (e.getCpfcnpj().length() == 14) {
			results = HttpRequest.get(url).header("Content-Type", contype).header("cnpj-sh", cnpj_sh)
					.header("token-sh", token_sh).header("cnpj-cedente", cpfcnpj_cedente);
		} else {
			results = HttpRequest.get(url).header("Content-Type", contype).header("cnpj-sh", cnpj_sh)
					.header("token-sh", token_sh).header("cpf-cedente", cpfcnpj_cedente);
		}
		return results;
	}

	// HTTP REQUEST - DELETE
	public static HttpRequest httpResquestDelete(MidasConfig mc, ClienteParam prm, String rota, CdPessoa e) {
		// Verifica ambiente----------------
		String url = mc.linkTecnoProd;
		if (prm.cliente().getSiscfg().getSis_amb_boleto().equals("2")) {
			url = mc.linkTecnoHom;
		}
		// Dados fixos-----------------------
		String contype = "application/json";
		String cnpj_sh = mc.MidasCNPJ;
		String token_sh = mc.tokenTecno;
		String cpfcnpj_cedente = e.getCpfcnpj();
		HttpRequest results = null;
		url = url + rota;
		if (e.getCpfcnpj().length() == 14) {
			results = HttpRequest.delete(url).header("Content-Type", contype).header("cnpj-sh", cnpj_sh)
					.header("token-sh", token_sh).header("cnpj-cedente", cpfcnpj_cedente);
		} else {
			results = HttpRequest.delete(url).header("Content-Type", contype).header("cnpj-sh", cnpj_sh)
					.header("token-sh", token_sh).header("cpf-cedente", cpfcnpj_cedente);
		}
		return results;
	}
}
