package com.midas.api.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.StringTokenizer;

public class NumUtil {
	// NUMERO ESQUERDA
	public static String geraNumEsq(Integer numero, Integer qtd) throws Exception {
		StringBuilder plvComp = new StringBuilder();
		for (int i = 0; i < qtd; i++) {
			plvComp.append("0");
		}
		String plv = plvComp.toString();
		int geraNum[] = { numero };
		NumberFormat format = new DecimalFormat(plv);
		for (int i : geraNum) {
			String formatado = format.format(i);
			return formatado;
		}
		return null;
	}

	// GERA NUMERO ALEATORIA INTEGER
	public static Integer geraNumAleaInteger() {
		Random random = new Random();
		Integer dia = LocalDateTime.now().getDayOfMonth();
		Integer min = LocalDateTime.now().getMinute();
		Integer sec = LocalDateTime.now().getSecond();
		Integer ret = random.nextInt(99999999) + min + sec + dia;
		return ret;
	}

	// REMOVE ZEROS ESQUERDA
	public static String removeZeros(String info) {
		String texto = info;
		if (info != null) {
			if (!info.equals("")) {
				texto = info.replaceFirst("0*", "");
			}
		}
		return texto;
	}

	// TRANSFORMA TUDO PARA METROS
	public static BigDecimal paraMetro(BigDecimal medida, BigDecimal qtd, String tipomedida) {
		BigDecimal ret = medida;
		if (tipomedida.equals("MM")) {
			ret = medida.divide(new BigDecimal(1000000000), 8, RoundingMode.HALF_UP);
		}
		if (tipomedida.equals("CM")) {
			ret = medida.divide(new BigDecimal(1000000), 8, RoundingMode.HALF_UP);
		}
		ret = ret.multiply(qtd);
		return ret.setScale(8, RoundingMode.HALF_UP);
	}

	// TRANSFORMA TUDO PARA QUILOS - KG
	public static BigDecimal paraKg(BigDecimal peso, BigDecimal qtd, String tipopeso) {
		BigDecimal ret = peso;
		if (tipopeso.equals("MG")) {
			ret = peso.divide(new BigDecimal(1000000), 8, RoundingMode.HALF_UP);
		}
		if (tipopeso.equals("G")) {
			ret = peso.divide(new BigDecimal(1000), 8, RoundingMode.HALF_UP);
		}
		if (tipopeso.equals("TON")) {
			ret = peso.multiply(new BigDecimal(1000));
		}
		ret = ret.multiply(qtd);
		return ret.setScale(8, RoundingMode.HALF_UP);
	}

	// TRANSFORMA VALOR PARA PERCENTUAL
	public static BigDecimal paraPerc8Decimais(BigDecimal vTotGeral, BigDecimal vCalculo) {
		BigDecimal ret = new BigDecimal(0);
		BigDecimal calculo01 = vTotGeral.subtract(vCalculo);
		BigDecimal calculo02 = vTotGeral.subtract(calculo01);
		BigDecimal calculo03 = calculo02.divide(vTotGeral, 8, RoundingMode.HALF_UP);
		ret = calculo03.multiply(new BigDecimal(100));
		return ret.setScale(8, RoundingMode.HALF_EVEN);
	}

	// CONTAR QUANTIDADE DE LINHAS
	public static Integer qtdLinhas(String linhas) {
		Integer qt = 0;
		StringTokenizer st = new StringTokenizer(linhas.toString(), "\n");
		while (st.hasMoreTokens()) {
			@SuppressWarnings("unused")
			String linha = st.nextToken();
			qt++;
		}
		return qt;
	}

	// CALCULO VALOR PARA PERCENTUAL
	public static BigDecimal calculoPerc(BigDecimal vInicial, BigDecimal vFinal, int casas) {
		BigDecimal ret = new BigDecimal(0);
		BigDecimal calculo01 = vFinal.subtract(vInicial);
		BigDecimal calculo02 = calculo01.divide(vInicial, casas, RoundingMode.HALF_EVEN);
		ret = calculo02.multiply(new BigDecimal(100));
		return ret.setScale(casas, RoundingMode.HALF_EVEN);
	}
}
