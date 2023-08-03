/*
This file is part of PorExtenso.

    PorExtenso is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    PorExtenso is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with PorExtenso.  If not, see <http://www.gnu.org/licenses/>.
    
    
Copyright 2008, Marcelo Criscuolo.    
*/
package com.midas.api.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.springframework.stereotype.Component;

/**
 * @author Marcelo Criscuolo (Ja?) - criscuolo.marcelo (at) gmail (dot) com
 *
 */
@Component
public class NumExtensoUtil {
	private static final BigInteger THOUSAND = new BigInteger("1000");
	private static final BigInteger HUNDRED = new BigInteger("100");
	private static final String CENTO = "Cento";
	private static final String CEM = "Cem";
	/**
	 * Nomes das grandezas num?ricas no plural. O mapa a chave do mapa ? o expoente
	 * de dez do n?mero e o valor ? o seu nome no plural. Por exemplo: para chave 3
	 * (10^3) o valor ? "mil", para a chave 6 (10^6) o valor ? "milh?es", e assim
	 * por diante. Os nomes foram obtidos de um artigo publicado na se??o Dois Mais
	 * Dois na revista SuperInteressante n?. 15, de dezembro de 1988 (Editora Abril,
	 * S?o Paulo/SP), dispon?vel em
	 * http://www.novomilenio.inf.br/idioma/19881200.htm.
	 */
	private final Map<Integer, String> grandezasPlural = new HashMap<Integer, String>();
	private final Map<Integer, String> grandezasSingular = new HashMap<Integer, String>();
	/** Nomes dos n?meros. */
	private final Map<Integer, String> nomes = new HashMap<Integer, String>();
	private static final String MOEDA_SINGULAR = "Real";
	private static final String MOEDA_PLURAL = "Reais";
	private static final String FRACAO_SINGULAR = "Centavo";
	private static final String FRACAO_PLURAL = "Centavos";
	private static final String PARTICULA_ADITIVA = "e";
	private static final String PARTICULA_DESCRITIVA = "de";
	/**
	 * O conversor reconhece n?meros at? a ordem dos setilh?es, portanto, o maior
	 * valor suportado atualmente ? o representado abaixo.
	 */
	private static final BigDecimal MAX_SUPPORTED_VALUE = new BigDecimal("999999999999999999999999999.99");

	public NumExtensoUtil() {
		preencherGrandezasPlural();
		preencherGrandezasSingular();
		preencherNomes();
	}

	public String write(final BigDecimal amount) {
		if (null == amount) {
			throw new IllegalArgumentException();
		}
		/*
		 * substituir o m?todo setScale, abaixo, pela vers?o cujo par?metro de
		 * arredondamento ? um enum
		 */
		BigDecimal value = amount.setScale(2, RoundingMode.HALF_EVEN);
		if (value.compareTo(BigDecimal.ZERO) <= 0) {
			return "";
		}
		if (MAX_SUPPORTED_VALUE.compareTo(value) < 0) {
			throw new IllegalArgumentException("Valor acima do limite suportado.");
		}
		Stack<Integer> decomposed = decompose(value);
		/*
		 * Se o n?mero estiver, digamos, na casa dos milh?es, a pilha dever? conter 4
		 * elementos sendo os dois ?ltimos os das centenas e dos centavos,
		 * respectivamente. Assim, o expoente de dez que representa a grandeza no topo
		 * da pilha ? o n?mero de (elementos - 2) * 3
		 */
		int expoente = 3 * (decomposed.size() - 2); // usar um ?ndice de grupos ao inv?s do expoente
		StringBuffer sb = new StringBuffer();
		int lastNonZeroExponent = -1;
		while (!decomposed.empty()) {
			int valor = decomposed.pop();
			if (valor > 0) {
				sb.append(" ").append(PARTICULA_ADITIVA).append(" ");
				sb.append(comporNomeGrupos(valor));
				String nomeGrandeza = obterNomeGrandeza(expoente, valor);
				if (nomeGrandeza.length() > 0) {
					sb.append(" ");
				}
				sb.append(nomeGrandeza);
				lastNonZeroExponent = expoente;
			}
			switch (expoente) {
			case 0:
				BigInteger parteInteira = value.toBigInteger();
				if (BigInteger.ONE.equals(parteInteira)) {
					sb.append(" ").append(MOEDA_SINGULAR);
				} else if (parteInteira.compareTo(BigInteger.ZERO) > 0) {
					if (lastNonZeroExponent >= 6) {
						sb.append(" ").append(PARTICULA_DESCRITIVA);
					}
					sb.append(" ").append(MOEDA_PLURAL);
				}
				break;
			case -3:
				if (1 == valor) {
					sb.append(" ").append(FRACAO_SINGULAR);
				} else if (valor > 1) {
					sb.append(" ").append(FRACAO_PLURAL);
				}
				break;
			}
			expoente -= 3;
		}
		return sb.substring(3);
	}

	private StringBuffer comporNomeGrupos(int valor) {
		StringBuffer nome = new StringBuffer();
		int centenas = valor - (valor % 100);
		int unidades = valor % 10;
		int dezenas = (valor - centenas) - unidades;
		int duasCasas = dezenas + unidades;
		if (centenas > 0) {
			nome.append(" ").append(PARTICULA_ADITIVA).append(" ");
			if (100 == centenas) {
				if (duasCasas > 0) {
					nome.append(CENTO);
				} else {
					nome.append(CEM);
				}
			} else {
				nome.append(nomes.get(centenas));
			}
		}
		if (duasCasas > 0) {
			nome.append(" ").append(PARTICULA_ADITIVA).append(" ");
			if (duasCasas < 20) {
				nome.append(nomes.get(duasCasas));
			} else {
				if (dezenas > 0) {
					nome.append(nomes.get(dezenas));
				}
				if (unidades > 0) {
					nome.append(" ").append(PARTICULA_ADITIVA).append(" ");
					nome.append(nomes.get(unidades));
				}
			}
		}
		return nome.delete(0, 3);
	}

	private String obterNomeGrandeza(int exponent, int value) {
		if (exponent < 3) {
			return "";
		}
		if (1 == value) {
			return grandezasSingular.get(exponent);
		} else {
			return grandezasPlural.get(exponent);
		}
	}

	private Stack<Integer> decompose(BigDecimal value) {
		BigInteger intermediate = value.multiply(new BigDecimal(100)).toBigInteger();
		Stack<Integer> decomposed = new Stack<Integer>();
		BigInteger[] result = intermediate.divideAndRemainder(HUNDRED);
		intermediate = result[0];
		decomposed.add(result[1].intValue());
		while (intermediate.compareTo(BigInteger.ZERO) > 0) {
			result = intermediate.divideAndRemainder(THOUSAND);
			intermediate = result[0];
			decomposed.add(result[1].intValue());
		}
		/*
		 * Se o valor for apenas em centavos, adicionar zero para a casa dos reais
		 * inteiros
		 */
		if (decomposed.size() == 1) {
			decomposed.add(0);
		}
		return decomposed;
	}

	private void preencherGrandezasPlural() {
		grandezasPlural.put(3, "Mil");
		grandezasPlural.put(6, "Milhoes");
		grandezasPlural.put(9, "Bilhoes");
		grandezasPlural.put(12, "Trilhoes");
		grandezasPlural.put(15, "Quatrilhoes");
		grandezasPlural.put(18, "Quintilhoes");
		grandezasPlural.put(21, "Sextilhoes");
		grandezasPlural.put(24, "Setilhoes");
	}

	private void preencherGrandezasSingular() {
		grandezasSingular.put(3, "Mil");
		grandezasSingular.put(6, "Milhao");
		grandezasSingular.put(9, "Bilhao");
		grandezasSingular.put(12, "Trilhao");
		grandezasSingular.put(15, "Quatrilhao");
		grandezasSingular.put(18, "Quintilhao");
		grandezasSingular.put(21, "Sextilhao");
		grandezasSingular.put(24, "Setilhao");
	}

	private void preencherNomes() {
		nomes.put(1, "Um");
		nomes.put(2, "Dois");
		nomes.put(3, "Tres");
		nomes.put(4, "Quatro");
		nomes.put(5, "Cinco");
		nomes.put(6, "Seis");
		nomes.put(7, "Sete");
		nomes.put(8, "Oito");
		nomes.put(9, "Nove");
		nomes.put(10, "Dez");
		nomes.put(11, "Onze");
		nomes.put(12, "Doze");
		nomes.put(13, "Treze");
		nomes.put(14, "Quatorze");
		nomes.put(15, "Quinze");
		nomes.put(16, "Dezesseis");
		nomes.put(17, "Dezessete");
		nomes.put(18, "Dezoito");
		nomes.put(19, "Dezenove");
		nomes.put(20, "Vinte");
		nomes.put(30, "Trinta");
		nomes.put(40, "Quarenta");
		nomes.put(50, "Cinquenta");
		nomes.put(60, "Sessenta");
		nomes.put(70, "Setenta");
		nomes.put(80, "Oitenta");
		nomes.put(90, "Noventa");
		nomes.put(200, "Duzentos");
		nomes.put(300, "Trezentos");
		nomes.put(400, "Quatrocentos");
		nomes.put(500, "Quinhentos");
		nomes.put(600, "Seiscentos");
		nomes.put(700, "Setecentos");
		nomes.put(800, "Oitocentos");
		nomes.put(900, "Novecentos");
	}
}