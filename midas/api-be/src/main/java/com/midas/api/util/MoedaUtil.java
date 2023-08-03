package com.midas.api.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MoedaUtil {
	// MOEDA PADRAO
	public static String moedaPadrao(BigDecimal valor) {
		DecimalFormat decFormat = new DecimalFormat("#,###,##0.00");
		return decFormat.format(valor);
	}

	// MOEDA PADRAO - 3 DIGITOS FINAL
	public static String moedaPadrao3(BigDecimal valor) {
		DecimalFormat decFormat = new DecimalFormat("#,###,##0.000");
		return decFormat.format(valor);
	}

	// MOEDA PADRAO - 4 DIGITOS FINAL
	public static String moedaPadrao4(BigDecimal valor) {
		DecimalFormat decFormat = new DecimalFormat("#,###,##0.0000");
		return decFormat.format(valor);
	}

	// MOEDA PADRAO - 5 DIGITOS FINAL
	public static String moedaPadrao5(BigDecimal valor) {
		DecimalFormat decFormat = new DecimalFormat("#,###,##0.00000");
		return decFormat.format(valor);
	}

	// MOEDA PADRAO SQL
	public static String moedaPadraoScale(BigDecimal valor, int casas) {
		BigDecimal bd = BigDecimal.ZERO;
		if (valor.compareTo(BigDecimal.ZERO) > 0) {
			bd = valor.setScale(casas, RoundingMode.HALF_EVEN);
		} else {
			if (casas >= 10) {
				bd = valor.setScale(2, RoundingMode.HALF_EVEN);
			} else {
				bd = valor.setScale(casas, RoundingMode.HALF_EVEN);
			}
		}
		return bd.toString();
	}

	// MOEDA PADRAO SEM PONTOS
	public static String moedaPadraoSP(BigDecimal valor) {
		DecimalFormat decFormat = new DecimalFormat("#,###,##0.00");
		String retorno = decFormat.format(valor);
		retorno = retorno.replace(".", "");
		return retorno;
	}

	// MOEDA PADRAO SEM PONTOS 3
	public static String moedaPadraoSP3(BigDecimal valor) {
		DecimalFormat decFormat = new DecimalFormat("#,###,##0.000");
		String retorno = decFormat.format(valor);
		retorno = retorno.replace(".", "");
		return retorno;
	}

	// MOEDA PADRAO SEM PONTOS 4
	public static String moedaPadraoSP4(BigDecimal valor) {
		DecimalFormat decFormat = new DecimalFormat("#,###,##0.0000");
		String retorno = decFormat.format(valor);
		retorno = retorno.replace(".", "");
		return retorno;
	}

	// MOEDA PADRAO SEM PONTOS 5
	public static String moedaPadraoSP5(BigDecimal valor) {
		DecimalFormat decFormat = new DecimalFormat("#,###,##0.00000");
		String retorno = decFormat.format(valor);
		retorno = retorno.replace(".", "");
		return retorno;
	}

	// MOEDA PADRAO SEM PONTOS 6
	public static String moedaPadraoSP6(BigDecimal valor) {
		DecimalFormat decFormat = new DecimalFormat("#,###,##0.000000");
		String retorno = decFormat.format(valor);
		retorno = retorno.replace(".", "");
		return retorno;
	}

	// MOEDA CONVERTE PADRAO
	public static BigDecimal moedaStringBigPD(String valor) {
		BigDecimal ret = BigDecimal.ZERO;
		if (valor != null) {
			if (!valor.equals("")) {
				valor = valor.replace(".", "");
				valor = valor.replace(",", ".");
				ret = new BigDecimal(valor);
			}
		}
		return ret;
	}

	// MOEDA CONVERTE PADRAO COM PONTO
	public static BigDecimal moedaStringBigPDP(String valor) {
		BigDecimal ret = BigDecimal.ZERO;
		if (valor != null) {
			if (!valor.equals("")) {
				valor = valor.replace(",", ".");
				ret = new BigDecimal(valor);
			}
		}
		return ret;
	}

	// MOEDA CONVERTE PADRAO TECNO
	public static BigDecimal moedaStringBig(String valor) {
		BigDecimal ret = BigDecimal.ZERO;
		if (valor != null) {
			if (!valor.equals("")) {
				ret = new BigDecimal(valor.replace(",", "."));
			}
		}
		return ret;
	}

	// MOEDA ADD PONTO PADRAO SQL
	public static BigDecimal moedaStringPadraoSQL(String valor) {
		BigDecimal ret = BigDecimal.ZERO;
		String aposVirgula = valor.substring(valor.length() - 2, valor.length());
		String antesVirgula = valor.substring(0, valor.length() - 2);
		String novoValor = antesVirgula + "." + aposVirgula;
		ret = new BigDecimal(novoValor);
		return ret;
	}

	// CALCULO DESC E JUROS AVULSO
	public static BigDecimal pJD(BigDecimal vOriginal, BigDecimal vPorc) {
		BigDecimal ret = BigDecimal.ZERO;
		if (vPorc.compareTo(BigDecimal.ZERO) == 1) {
			BigDecimal calculo01 = vOriginal.subtract(vPorc);
			BigDecimal calculo02 = vOriginal.subtract(calculo01);
			BigDecimal calculo03 = calculo02.divide(vOriginal, 8, RoundingMode.HALF_EVEN);
			ret = calculo03.multiply(new BigDecimal(100));
			ret = ret.setScale(2, RoundingMode.HALF_UP);
		}
		return ret;
	}

	// CALCULO PERCENTUAL 8 DIGITOS FINAL
	public static BigDecimal pPerc8(BigDecimal vOriginal, BigDecimal vPorc) {
		BigDecimal ret = BigDecimal.ZERO;
		if (vPorc.compareTo(BigDecimal.ZERO) == 1) {
			BigDecimal calculo01 = vOriginal.subtract(vPorc);
			BigDecimal calculo02 = vOriginal.subtract(calculo01);
			BigDecimal calculo03 = calculo02.divide(vOriginal, 8, RoundingMode.HALF_EVEN);
			ret = calculo03.multiply(new BigDecimal(100));
			ret = ret.setScale(8, RoundingMode.HALF_UP);
		}
		return ret;
	}
}
