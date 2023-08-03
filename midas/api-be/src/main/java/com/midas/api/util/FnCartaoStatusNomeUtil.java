package com.midas.api.util;

import org.springframework.stereotype.Component;

@Component
public class FnCartaoStatusNomeUtil {
	public static String nomeSt(String cod) {
		if (cod.equals("01")) {
			return "Pendente";
		}
		if (cod.equals("02")) {
			return "Entrada confirmada";
		}
		if (cod.equals("03")) {
			return "Recusado";
		}
		if (cod.equals("04")) {
			return "Devolvido ou estornado";
		}
		return "Não identificado";
	}
}
