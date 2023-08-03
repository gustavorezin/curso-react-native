package com.midas.api.tenant.fiscal.util;

import org.springframework.stereotype.Component;

@Component
public class FsModFreteNomeUtil {
	public static String nomeFrete(String cod) {
		if (cod.equals("0")) {
	        return "0 - Contratação do Frete por conta do Remetente(CIF)";
		}
		if (cod.equals("1")) {
	        return "1 - Contratação do Frete por conta do Destinatário (FOB)";
		}
		if (cod.equals("2")) {
	        return "2 - Contratação do Frete por conta de Terceiros";
		}
		if (cod.equals("3")) {
	        return "3 - Transporte Próprio por conta do Remetente";
		}
		if (cod.equals("4")) {
	        return "4 - Transporte Próprio por conta do Destinatário";
		}
		if (cod.equals("9")) {
	        return "9 - Sem Ocorrência de Transporte";
		}
		return "Não identificado";
	}
}
