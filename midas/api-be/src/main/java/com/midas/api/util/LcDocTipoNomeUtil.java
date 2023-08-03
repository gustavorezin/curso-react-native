package com.midas.api.util;

public class LcDocTipoNomeUtil {
	// NOME DO TIPO DE DOC
	public static String nomeTipoDoc(String tipo) {
		if (tipo.equals("01")) {
			return "VENDA";
		}
		if (tipo.equals("02")) {
			return "OS";
		}
		if (tipo.equals("03")) {
			return "DEVOLUCAO";
		}
		if (tipo.equals("04")) {
			return "DEVOLUCAO DE COMPRA";
		}
		if (tipo.equals("05")) {
			return "COMISSAO";
		}
		if (tipo.equals("06")) {
			return "CONSUMO INTERNO";
		}
		if (tipo.equals("07")) {
			return "PEDIDO DE COTAÇÃO";
		}
		if (tipo.equals("08")) {
			return "PEDIDO DE COMPRA";
		}
		if (tipo.equals("09")) {
			return "REMESSA";
		}
		return null;
	}

	// NOME SITUACAO DOC
	public static String nomeStDoc(String tipo) {
		if (tipo.equals("1")) {
			return "EM EDIÇÃO";
		}
		if (tipo.equals("2")) {
			return "FATURADO";
		}
		if (tipo.equals("3")) {
			return "CANCELADO";
		}
		return null;
	}

	// NOME SITUACAO DOC
	public static String nomeStDocEnt(String tipo) {
		if (tipo.equals("1")) {
			return "EM EDIÇÃO";
		}
		if (tipo.equals("2")) {
			return "ENTREGUE";
		}
		if (tipo.equals("3")) {
			return "CANCELADO";
		}
		return null;
	}

	public static String nomeCat(String tipo) {
		if (tipo.equals("01")) {
			return "VENDA";
		}
		if (tipo.equals("02")) {
			return "PEDIDO";
		}
		if (tipo.equals("03")) {
			return "ORÇAMENTO";
		}
		if (tipo.equals("04")) {
			return "CONDICIONAL";
		}
		if (tipo.equals("05")) {
			return "LOCAÇÃO";
		}
		if (tipo.equals("06")) {
			return "SERVIÇO";
		}
		if (tipo.equals("07")) {
			return "COMODATO";
		}
		if (tipo.equals("08")) {
			return "FINANCIAMENTO";
		}
		if (tipo.equals("09")) {
			return "BONIFICAÇÃO";
		}
		if (tipo.equals("10")) {
			return "AMOSTRA";
		}
		return "Não identificado";
	}
}
