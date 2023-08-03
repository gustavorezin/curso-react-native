package com.midas.api.tenant.fiscal.util;

import org.springframework.stereotype.Component;

@Component
public class FnPagNomeUtil {
	public static String nomePg(String cod) {
		if (cod.equals("00")) {
			return "Cheques em caixa";
		}
		if (cod.equals("01")) {
			return "Dinheiro";
		}
		if (cod.equals("02")) {
			return "Cheque";
		}
		if (cod.equals("03")) {
			return "Cartão de Crédito";
		}
		if (cod.equals("04")) {
			return "Cartão de Débito";
		}
		if (cod.equals("05")) {
			return "Crédito Empresa/Loja ou Fornecedor";
		}
		if (cod.equals("10")) {
			return "Vale Alimentação";
		}
		if (cod.equals("11")) {
			return "Vale Refeição";
		}
		if (cod.equals("12")) {
			return "Vale Presente";
		}
		if (cod.equals("13")) {
			return "Vale Combustível";
		}
		if (cod.equals("15")) {
			return "Boleto Bancário";
		}
		if (cod.equals("16")) {
			return "Depósito Bancário";
		}
		if (cod.equals("17")) {
			return "Pagamento Instantâneo (PIX)";
		}
		if (cod.equals("18")) {
			return "Transferência bancária, Cart. Digital";
		}
		if (cod.equals("19")) {
			return "Prog. fidelidade, Cashback, Créd. Virtual";
		}
		if (cod.equals("90")) {
			return "Sem Pagamento";
		}
		if (cod.equals("99")) {
			return "Outros";
		}
		return "Não identificado";
	}
}
