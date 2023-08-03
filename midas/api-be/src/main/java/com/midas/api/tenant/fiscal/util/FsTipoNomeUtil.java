package com.midas.api.tenant.fiscal.util;

public class FsTipoNomeUtil {
	// NOME DO TIPO (ENTRADA/SAIDA) DE NOTA
	public static String nomeTipo(String tipo) {
		if (tipo.equals("E")) {
			return "EMITIDA";
		}
		if (tipo.equals("R")) {
			return "RECEBIDA";
		}
		return null;
	}

	// NOME DO TIPO (ENTRADA/SAIDA) DE NOTA
	public static String nomeTipoEmis(String tipo) {
		if (tipo.equals("0")) {
			return "ENTRADA";
		}
		if (tipo.equals("1")) {
			return "SAÍDA";
		}
		return null;
	}

	// NOME MODELO NOTA
	public static String nomeModelo(String tipo) {
		if (tipo != null) {
			if (tipo.equals("0")) {
				return "Não emitido";
			}
			if (tipo.equals("00")) {
				return "SEM DOC";
			}
			if (tipo.equals("55")) {
				return "NF-E";
			}
			if (tipo.equals("57")) {
				return "CT-E";
			}
			if (tipo.equals("58")) {
				return "MDF-E";
			}
			if (tipo.equals("65")) {
				return "NFC-E";
			}
		}
		return "Não emitido";
	}

	// NOME STATUS NOTA
	public static String nomeStatus(String tipo) {
		if (tipo.equals("1")) {
			return "EM EDIÇÃO";
		}
		if (tipo.equals("100")) {
			return "AUTORIZADA";
		}
		if (tipo.equals("101")) {
			return "CANCELADA";
		}
		if (tipo.equals("102")) {
			return "INUTILIZADA";
		}
		if (tipo.equals("110")) {
			return "DENEGADA";
		}
		if (tipo.equals("150")) {
			return "AUTORIZADA FORA DO PRAZO";
		}
		if (tipo.equals("151")) {
			return "CANCELADA FORA DO PRAZO";
		}
		return null;
	}

	// NOME SITUACAO NOTA
	public static String nomeSt(String tipo) {
		if (tipo.equals("X")) {
			return "Todos";
		}
		if (tipo.equals("C")) {
			return "Conferido";
		}
		if (tipo.equals("P")) {
			return "Pendente";
		}
		return null;
	}

	// NOME SITUACAO SPED
	public static String nomeStSped(String tipo) {
		if (tipo.equals("100")) {
			return "00";
		} // AUTORIZADA
		if (tipo.equals("101")) {
			return "02";
		} // CANCELADA
		if (tipo.equals("110")) {
			return "04";
		} // DENEGADA
		if (tipo.equals("102")) {
			return "05";
		} // INUTILIZADA
		return "";
	}

	// NOME AMBIENTE NOTA
	public static String nomeAmb(String tipo) {
		if (tipo.equals("1")) {
			return "EDIÇÃO";
		}
		if (tipo.equals("2")) {
			return "HOMOLOGAÇÃO";
		}
		return null;
	}

	// NOME FINALIDADE NOTA
	public static String nomeFin(String tipo) {
		if (tipo.equals("1")) {
			return "NORMAL";
		}
		if (tipo.equals("2")) {
			return "COMPLEMENTAR";
		}
		if (tipo.equals("3")) {
			return "AJUSTE";
		}
		if (tipo.equals("4")) {
			return "DEVOLUÇÃO";
		}
		return null;
	}

	// NOME DO TIPO - ESTOQUE
	public static String nomeTipoItemEs(String tipo) {
		if (tipo.equals("00")) {
			return "00 - Mercadoria para Revenda";
		}
		if (tipo.equals("01")) {
			return "01 – Matéria-prima";
		}
		if (tipo.equals("02")) {
			return "02 – Embalagem";
		}
		if (tipo.equals("03")) {
			return "03 – Produto em Processo(semi-acabado)";
		}
		if (tipo.equals("04")) {
			return "04 – Produto Acabado";
		}
		if (tipo.equals("05")) {
			return "05 – Subproduto";
		}
		if (tipo.equals("06")) {
			return "06 – Produto Intermediário";
		}
		if (tipo.equals("07")) {
			return "07 – Material de Uso e Consumo";
		}
		if (tipo.equals("08")) {
			return "08 – Ativo Imobilizado";
		}
		if (tipo.equals("09")) {
			return "09 – Serviços";
		}
		if (tipo.equals("10")) {
			return "10 – Outros insumos";
		}
		if (tipo.equals("99")) {
			return "99 – Outras";
		}
		if (tipo.equals("XX")) {
			return "TODOS";
		}
		return "Não identificado";
	}

	// NOME DO STATUS - ESTOQUE
	public static String nomeStEs(String tipo) {
		if (tipo.equals("ATIVO")) {
			return "Ativo";
		}
		if (tipo.equals("INATIVO")) {
			return "Inativo";
		}
		return "Todos";
	}

	// NOME DO ORIGEM
	public static String nomeOrigemEs(String origem) {
		if (origem.equals("0")) {
			return "0 - NACIONAL, EXCETO AS INDICADAS NOS CÓDIGOS 3 A 5";
		}
		if (origem.equals("1")) {
			return "1 - ESTRANGEIRA - IMPORTAÇÃO DIRETA";
		}
		if (origem.equals("2")) {
			return "2 - ESTRANGEIRA - ADQUIRIDA NO MERCADO INTERNO";
		}
		if (origem.equals("3")) {
			return "3 - NACIONAL, MERC. OU BEM COM CONTEÚDO DE IMPORTAÇÃO SUP. A 40%";
		}
		if (origem.equals("4")) {
			return "4 - NACIONAL, CUJA PROD. TENHA SIDO FEITA EM CONFORM. COM OS PROCESSOS";
		}
		if (origem.equals("5")) {
			return "5 - NACIONAL, MERC. OU BEM COM CONTEÚDO DE IMP. INFERIOR OU IGUAL A 40%";
		}
		if (origem.equals("6")) {
			return "6 - ESTRANGEIRA - IMPORTAÇÃO DIRETA, SEM SIMILAR NACIONAL, CONSTANTE RES. CAMEX";
		}
		if (origem.equals("7")) {
			return "7 - ESTRANGEIRA - ADQUIRIDA NO MERCADO INTERNO, SEM SIMILAR NACIONAL, CAMEX";
		}
		if (origem.equals("8")) {
			return "8 - NACIONAL, MERCADORIA OU BEM COM CONTEÚDO DE IMPORTAÇÃO SUPERIOR A 70%";
		}
		return "Não identificado";
	}

	// NOME ENCERRAMENTO
	public static String nomeEncerra(String tipo) {
		if (tipo.equals("S")) {
			return "Encerrados";
		}
		if (tipo.equals("N")) {
			return "Não encerrados";
		}
		return "Todos";
	}

	// NOME ENCERRAMENTO
	public static String nomeEncerraM(String tipo) {
		if (tipo.equals("S")) {
			return "ENCERRADO";
		}
		if (tipo.equals("N")) {
			return "NÃO ENCERRADO";
		}
		return "NÃO IDENTIFICADO";
	}
}
