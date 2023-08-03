package com.midas.api.util;

public class PessoaUtil {
	// VERIFICA CPF CNPJ COM ZEROS
	public static boolean verCPF_CNPJ(String cpfcnpj) {
		if (cpfcnpj.equals("00000000000") || cpfcnpj.equals("00000000000000")) {
			return true;
		} else {
			return false;
		}
	}
	
	// TRAZ CPF OU CNPJ
	public static String informaCPF_CNPJ(String cpfcnpj) {
		if (cpfcnpj.length() == 11) {
			return "CPF";
		} else {
			return "CNPJ";
		}
	}
}
