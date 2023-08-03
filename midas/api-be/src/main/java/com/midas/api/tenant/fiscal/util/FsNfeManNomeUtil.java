package com.midas.api.tenant.fiscal.util;

public class FsNfeManNomeUtil {
	public static String nomeMan(String cod) {
		if (cod.equals("110111")) {
			return "Cancelamento";
		}
		if (cod.equals("110112")) {
			return "Encerramento";
		}
		if (cod.equals("110110")) {
			return "Carta de Correcao";
		}
		if (cod.equals("210200")) {
			return "Confirmacao da Operacao";
		}
		if (cod.equals("210210")) {
			return "Ciencia da Operacao";
		}
		if (cod.equals("210220")) {
			return "Desconhecimento da Operacao";
		}
		if (cod.equals("210240")) {
			return "Operacao nao Realizada";
		}
		return null;
	}
}
