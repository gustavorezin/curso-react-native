package com.midas.api.constant;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class FsMdfeWebService implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String versaoDados = "3.00";
	// HOMOLOGACAO
	private final static String MDFeRecepcaoHom = "https://mdfe-homologacao.svrs.rs.gov.br/ws/MDFeRecepcao/MDFeRecepcao.asmx";
	private final static String MDFeRetRecepcaoHom = "https://mdfe-homologacao.svrs.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx";
	private final static String MDFeConsultaStatusHom = "https://mdfe-homologacao.svrs.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx";
	public final static String MDFeQrCodeHom = "https://dfe-portal.svrs.rs.gov.br/mdfe/qrCode";
	private final static String MDFeEventoHom = "https://mdfe-homologacao.svrs.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx";
	// PRODUCAO
	private final static String MDFeRecepcao = "https://mdfe.svrs.rs.gov.br/ws/MDFeRecepcao/MDFeRecepcao.asmx";
	private final static String MDFeRetRecepcao = "https://mdfe.svrs.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx";
	private final static String MDFeConsultaStatus = "https://mdfe.svrs.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx";
	public final static String MDFeQrCode = "https://dfe-portal.svrs.rs.gov.br/mdfe/qrCode";
	private final static String MDFeEvento = "https://mdfe.svrs.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx";

	// Consulta servico
	public static String linkConsultaServico(String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			return MDFeConsultaStatus;
		}
		// Homologacao
		if (ambiente.equals("2")) {
			return MDFeConsultaStatusHom;
		}
		return null;
	}

	// Envio servico
	public static String linkEnvioServico(String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			return MDFeRecepcao;
		}
		// Homologacao
		if (ambiente.equals("2")) {
			return MDFeRecepcaoHom;
		}
		return null;
	}

	// Consulta MDF-e
	public static String linkEnvioRetServico(String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			return MDFeRetRecepcao;
		}
		// Homologacao
		if (ambiente.equals("2")) {
			return MDFeRetRecepcaoHom;
		}
		return null;
	}

	// Eventos MDF-e
	public static String linkEnvioEventosServico(String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			return MDFeEvento;
		}
		// Homologacao
		if (ambiente.equals("2")) {
			return MDFeEventoHom;
		}
		return null;
	}
}
