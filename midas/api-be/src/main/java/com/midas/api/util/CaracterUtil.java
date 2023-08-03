package com.midas.api.util;

import java.text.Normalizer;

public class CaracterUtil {
	// AJUSTA CONTEXTO DAS BUSCAS NAS LISTAS
	public static String buscaContexto(String busca) {
		String ret = busca;
		if (busca == null || (busca != null && busca.trim().isEmpty()) || busca.equalsIgnoreCase("undefined")) {
			ret = "";
		}
		return ret;
	}

	// CARACTERES ESPECIAS
	public static String rem(String info) {
		String texto = info;
		if (info != null) {
			if (!info.equals("")) {
				texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
				texto = texto.replaceAll("[^\\p{ASCII}]", "");
				texto = texto.replaceAll("\\|+", " ");
				texto = texto.trim();
			}
		}
		return texto;
	}

	// CARACTERES ESPECIAS E ACENTOS CAIXA ALTA
	public static String remUpper(String info) {
		String texto = info;
		if (info != null) {
			if (!info.equals("")) {
				texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
				texto = texto.replaceAll("[^\\p{ASCII}]", "");
				texto = texto.replaceAll("\\|+", " ");
				texto = texto.trim();
				texto = texto.toUpperCase();
			}
		}
		return texto;
	}

	// CARACTERES ESPECIAS E ACENTOS CAIXA ALTA
	public static String remAcUpper(String info) {
		String texto = info;
		if (info != null) {
			if (!info.equals("")) {
				texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
				texto = texto.replaceAll("[^\\p{ASCII}]", "");
				texto = texto.trim();
				texto = texto.toUpperCase();
			}
		}
		return texto;
	}

	// CARACTERES ESPECIAS E ACENTOS CAIXA ALTA - REMOVE ENTER
	public static String remUpperEnter(String info) {
		String texto = info;
		if (info != null) {
			if (!info.equals("")) {
				texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
				texto = texto.replaceAll("[^\\p{ASCII}]", "");
				texto = texto.replaceAll("\\|+", " ");
				texto = texto.replaceAll("[\\r\\n]+", " ");
				texto = texto.trim();
				texto = texto.toUpperCase();
			}
		}
		return texto;
	}

	// MAXIMO DE 60 CARACTERES
	public static String tamMax(String info, int tam) {
		String texto = info;
		if (info != null) {
			if (!info.equals("")) {
				if (info.length() > tam) {
					texto = info.substring(0, (tam - 1));
				}
			}
		}
		return texto;
	}

	public static String remLower(String info) {
		String texto = info;
		if (info != null) {
			if (!info.equals("")) {
				texto = texto.trim();
				texto = texto.toLowerCase();
			}
		}
		return texto;
	}

	public static String remPout(String info) {
		String texto = info;
		if (info != null) {
			if (!info.equals("")) {
				texto = texto.replace(".", "");
				texto = texto.replace("/", "");
				texto = texto.replace("-", "");
				texto = texto.replace("(", "");
				texto = texto.replace(")", "");
				texto = texto.replace("[", "");
				texto = texto.replace("]", "");
				texto = texto.replace(",", "");
				texto = texto.replace("{", "");
				texto = texto.replace("}", "");
				texto = texto.trim();
			}
		}
		return texto;
	}

	// Formata CPF e CNPJ
	public static String formataCPFCNPJ(String info) {
		String texto = info;
		if (info != null) {
			if (!info.equals("")) {
				if (info.length() == 14) {
					texto = info.substring(0, 2) + "." + info.substring(2, 5) + "." + info.substring(5, 8) + "/"
							+ info.substring(8, 12) + "-" + info.substring(12, 14);
				} else {
					texto = info.substring(0, 3) + "." + info.substring(3, 6) + "." + info.substring(6, 9) + "-"
							+ info.substring(9, 11);
				}
			}
		}
		return texto;
	}

	// Mostra tipo boolean
	public static String confereBoolean(boolean info) {
		String ret = "S";
		if (info == false) {
			ret = "N";
		}
		return ret;
	}

	// Retorna string de um parametro URL
	public static String retStringURLParam(String url, String parametro) {
		String[] params = url.split("&");
		for (String param : params) {
			String name = param.split("=")[0];
			if (parametro.equals(name)) {
				return param.split("=")[1];
			}
		}
		return null;
	}
}
