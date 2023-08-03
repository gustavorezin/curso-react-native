package com.midas.api.util.integra;

import java.net.URI;

import com.midas.api.tenant.entity.CdClimbacfg;
import com.midas.api.util.HttpRequest;

public class ClimbaUtil {
	// HTTP REQUEST - PADROES
	public static HttpRequest httpResquest(String json, CdClimbacfg cc, String rota, String hr) {
		// Dados fixos-----------------------
		String contype = "application/json";
		String chave = cc.getApi_token();
		String url = cc.getApi_link() + "" + rota;
		HttpRequest results = null;
		// POST
		if (hr.equals("POST")) {
			results = HttpRequest.post(url).header("Content-Type", contype).header("x-idcommerce-api-token", chave)
					.send(json);
		}
		// PUT
		if (hr.equals("PUT")) {
			results = HttpRequest.put(url).header("Content-Type", contype).header("x-idcommerce-api-token", chave)
					.send(json);
		}
		// GET
		if (hr.equals("GET")) {
			results = HttpRequest.get(url).header("Content-Type", contype).header("x-idcommerce-api-token", chave);
		}
		// DELETE
		if (hr.equals("DELETE")) {
			results = HttpRequest.delete(url).header("Content-Type", contype).header("x-idcommerce-api-token", chave)
					.send(json);
		}
		return results;
	}

	// HTTP REQUEST - PATCH
	public static java.net.http.HttpRequest httpResquestPatch(String json, CdClimbacfg cc, String rota) {
		// Dados fixos-----------------------
		String contype = "application/json";
		String chave = cc.getApi_token();
		String url = cc.getApi_link() + "" + rota;
		java.net.http.HttpRequest results = java.net.http.HttpRequest.newBuilder().uri(URI.create(url))
				.method("PATCH", java.net.http.HttpRequest.BodyPublishers.ofString(json))
				.header("Content-Type", contype).header("x-idcommerce-api-token", chave).build();
		return results;
	}

	// REPOSTA TRADUZIDA
	public static String respClimba(String code) {
		if (code.equals("201")) {
			return "Informação gerada com sucesso na(s) loja(s)!";
		}
		if (code.equals("400")) {
			return "Requisição mal formada, 1 ou mais parâmetros estão ausentes!";
		}
		if (code.equals("401")) {
			return "Requisição requer autenticação!";
		}
		if (code.equals("403")) {
			return "Requisição negada!";
		}
		if (code.equals("404")) {
			return "Recurso não encontrado!";
		}
		if (code.equals("405")) {
			return "Método não permitido!";
		}
		if (code.equals("406")) {
			return "Parametros não aceitados!";
		}
		if (code.equals("409")) {
			return "Uma marca já está cadastrada com o mesmo código!";
		}
		if (code.equals("415")) {
			return "Corpo da requisição inválida!";
		}
		if (code.equals("429")) {
			return "Limite de requisições atingido!";
		}
		return "Erro não especificado!";
	}
}
