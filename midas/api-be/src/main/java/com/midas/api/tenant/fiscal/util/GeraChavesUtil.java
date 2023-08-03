package com.midas.api.tenant.fiscal.util;

public class GeraChavesUtil {
	// GERA CHAVES (NFe, MDFe, CTe...) ------------------------------------------------------------
	public static String geraChavePadrao(Integer ufemit, String data, String cpfcnpj, Integer modelo, Integer serieDoc,
			Integer tpemis, Integer ndoc, Integer cdoc, String nomeDoc) {
		try {
			String cUF = String.valueOf(ufemit); // Codigo da UF do emitente do Documento Fiscal.
			String dataAAMM = data; // Ano e Mes de emissao.
			String cnpjCpf = cpfcnpj; // CNPJ do emitente.
			String mod = modelo + ""; // Modelo do Documento Fiscal.
			String serie = serieDoc + ""; // Serie do Documento Fiscal.
			String tpEmis = tpemis + ""; // Forma de emissao.
			String nDoc = String.valueOf(ndoc); // Numero do Documento Fiscal.
			String cDoc = String.valueOf(cdoc); // Codigo Numerico que compoe a Chave de Acesso.
			StringBuilder chave = new StringBuilder();
			chave.append(lpadTo(cUF, 2, '0'));
			chave.append(lpadTo(dataAAMM, 4, '0'));
			chave.append(lpadTo(cnpjCpf.replaceAll("\\D", ""), 14, '0'));
			chave.append(lpadTo(mod, 2, '0'));
			chave.append(lpadTo(serie, 3, '0'));
			chave.append(lpadTo(String.valueOf(nDoc), 9, '0'));
			chave.append(lpadTo(tpEmis, 1, '0'));
			chave.append(lpadTo(cDoc, 8, '0'));
			chave.append(modulo11(chave.toString()));
			chave.insert(0, nomeDoc);
			return chave.toString();
		} catch (Exception e) {
			System.out.println((e.toString()));
		}
		return null;
	}

	// GERA CHAVE INUTILIZA NFE -------------------------------------------------------------------
	public static String geraChaveInut(String ufemit, String ano, String cpfcnpj, String modelo, String serienf,
			String nNI, String nNF, String seq) {
		try {
			String cUF = ufemit; // Codigo da UF do emitente do Documento Fiscal.
			String dataAA = ano; // Ano de emissao.
			String cnpjCpf = cpfcnpj; // CNPJ do emitente.
			String mod = modelo; // Modelo do Documento Fiscal.
			String serie = serienf; // Serie do Documento Fiscal.
			String nNi = nNI; // Numero do NF inicial.
			String nNf = nNF; // Numero do NF final.
			// String sequencia = seq; // Numero sequencia.
			StringBuilder chave = new StringBuilder();
			chave.append(lpadTo(cUF, 2, '0'));
			chave.append(lpadTo(dataAA, 2, '0'));
			chave.append(lpadTo(cnpjCpf.replaceAll("\\D", ""), 14, '0'));
			chave.append(lpadTo(mod, 2, '0'));
			chave.append(lpadTo(serie, 3, '0'));
			chave.append(lpadTo(nNi, 9, '0'));
			chave.append(lpadTo(nNf, 9, '0'));
			// chave.append(lpadTo(sequencia, 2, '0'));
			chave.insert(0, "ID");
			return chave.toString();
		} catch (Exception e) {
			System.out.println((e.toString()));
		}
		return null;
	}
	
	// GERA CHAVE INUTILIZA CTE -------------------------------------------------------------------
		public static String geraChaveInutCte(String ufemit, String ano, String cpfcnpj, String modelo, String serienf,
				String nNI, String nNF, String seq) {
			try {
				String cUF = ufemit; // Codigo da UF do emitente do Documento Fiscal.
				//String dataAA = ano; // Ano de emissao.
				String cnpjCpf = cpfcnpj; // CNPJ do emitente.
				String mod = modelo; // Modelo do Documento Fiscal.
				String serie = serienf; // Serie do Documento Fiscal.
				String nNi = nNI; // Numero do CT inicial.
				String nNf = nNF; // Numero do CT final.
				// String sequencia = seq; // Numero sequencia.
				StringBuilder chave = new StringBuilder();
				chave.append(lpadTo(cUF, 2, '0'));
				//chave.append(lpadTo(dataAA, 2, '0'));
				chave.append(lpadTo(cnpjCpf.replaceAll("\\D", ""), 14, '0'));
				chave.append(lpadTo(mod, 2, '0'));
				chave.append(lpadTo(serie, 3, '0'));
				chave.append(lpadTo(nNi, 9, '0'));
				chave.append(lpadTo(nNf, 9, '0'));
				// chave.append(lpadTo(sequencia, 2, '0'));
				chave.insert(0, "ID");
				return chave.toString();
			} catch (Exception e) {
				System.out.println((e.toString()));
			}
			return null;
		}
	
	// FUNCOES EXTRAS -----------------------------------------------------------------------------
	public static int modulo11(String chave) {
		int total = 0;
		int peso = 2;
		for (int i = 0; i < chave.length(); i++) {
			total += (chave.charAt((chave.length() - 1) - i) - '0') * peso;
			peso++;
			if (peso == 10)
				peso = 2;
		}
		int resto = total % 11;
		return (resto == 0 || resto == 1) ? 0 : (11 - resto);
	}

	public static String lpadTo(String input, int width, char ch) {
		String strPad = "";
		StringBuffer sb = new StringBuffer(input.trim());
		while (sb.length() < width)
			sb.insert(0, ch);
		strPad = sb.toString();
		if (strPad.length() > width) {
			strPad = strPad.substring(0, width);
		}
		return strPad;
	}
}
