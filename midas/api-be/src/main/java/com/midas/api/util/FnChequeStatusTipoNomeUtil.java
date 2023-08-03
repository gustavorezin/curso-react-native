package com.midas.api.util;

import org.springframework.stereotype.Component;

@Component
public class FnChequeStatusTipoNomeUtil {
	public static String nomeSt(String cod) {
		if (cod.equals("01")) {
			return "Em caixa";
		}
		if (cod.equals("02")) {
			return "Depositado";
		}
		if (cod.equals("03")) {
			return "Pagamento fornecedor";
		}
		if (cod.equals("04")) {
			return "Compensado";
		}
		if (cod.equals("05")) {
			return "Troco";
		}
		if (cod.equals("06")) {
			return "Retornado";
		}
		if (cod.equals("07")) {
			return "Cancelado";
		}
		return "Não identificado";
	}

	public static String nomeTipo(String cod) {
		if (cod.equals("01")) {
			return "Próprio";
		}
		if (cod.equals("02")) {
			return "Terceiro";
		}
		return "Não identificado";
	}
}
