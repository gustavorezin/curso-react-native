package com.midas.api.constant;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class FsCteWebService implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String versaoDados = "3.00";
	// SVRS - AC, AL, AM, BA, CE, DF, ES, GO, MA, PA, PB, PI, RJ, RN, RO, SC, SE, TO
	private final static String SVRS_CteStatusServicoHom = "https://cte-homologacao.svrs.rs.gov.br/ws/ctestatusservico/CteStatusServico.asmx";
	private final static String SVRS_CteRecepcaoHom = "https://cte-homologacao.svrs.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx";
	private final static String SVRS_CteRetRecepcaoHom = "https://cte-homologacao.svrs.rs.gov.br/ws/cteretrecepcao/cteRetRecepcao.asmx";
	private final static String SVRS_CteInutilizacaoHom = "https://cte-homologacao.svrs.rs.gov.br/ws/cteinutilizacao/cteinutilizacao.asmx";
	private final static String SVRS_CteConsultaProtocoloHom = "https://cte-homologacao.svrs.rs.gov.br/ws/cteconsulta/CteConsulta.asmx";
	private final static String SVRS_CteRecepcaoEventoHom = "https://cte-homologacao.svrs.rs.gov.br/ws/cterecepcaoevento/cterecepcaoevento.asmx";
	private final static String SVRS_CTeRecepcaoOSHom = "https://cte-homologacao.svrs.rs.gov.br/ws/cterecepcaoos/cterecepcaoos.asmx";
	private final static String SVRS_CteStatusServico = "https://cte.svrs.rs.gov.br/ws/ctestatusservico/CteStatusServico.asmx";
	private final static String SVRS_CteRecepcao = "https://cte.svrs.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx";
	private final static String SVRS_CteRetRecepcao = "https://cte.svrs.rs.gov.br/ws/cteretrecepcao/cteRetRecepcao.asmx";
	private final static String SVRS_CteInutilizacao = "https://cte.svrs.rs.gov.br/ws/cteinutilizacao/cteinutilizacao.asmx";
	private final static String SVRS_CteConsultaProtocolo = "https://cte.svrs.rs.gov.br/ws/cteconsulta/CteConsulta.asmx";
	private final static String SVRS_CteRecepcaoEvento = "https://cte.svrs.rs.gov.br/ws/cterecepcaoevento/cterecepcaoevento.asmx";
	private final static String SVRS_CTeRecepcaoOS = "https://cte.svrs.rs.gov.br/ws/cterecepcaoos/cterecepcaoos.asmx";
	// MT
	private final static String MT_CteStatusServicoHom = "https://homologacao.sefaz.mt.gov.br/ctews/services/CteStatusServico";
	private final static String MT_CteRecepcaoHom = "https://homologacao.sefaz.mt.gov.br/ctews/services/CteRecepcao";
	private final static String MT_CteRetRecepcaoHom = "https://homologacao.sefaz.mt.gov.br/ctews/services/CteRetRecepcao";
	private final static String MT_CteInutilizacaoHom = "https://homologacao.sefaz.mt.gov.br/ctews/services/CteInutilizacao";
	private final static String MT_CteConsultaProtocoloHom = "https://homologacao.sefaz.mt.gov.br/ctews/services/CteConsulta";
	private final static String MT_CteRecepcaoEventoHom = "https://homologacao.sefaz.mt.gov.br/ctews2/services/CteRecepcaoEvento?wsdl";
	private final static String MT_CTeRecepcaoOSHom = "https://homologacao.sefaz.mt.gov.br/ctews/services/CteRecepcaoOS?wsdl";
	private final static String MT_CteStatusServico = "https://cte.sefaz.mt.gov.br/ctews/services/CteStatusServico";
	private final static String MT_CteRecepcao = "https://cte.sefaz.mt.gov.br/ctews/services/CteRecepcao";
	private final static String MT_CteRetRecepcao = "https://cte.sefaz.mt.gov.br/ctews/services/CteRetRecepcao";
	private final static String MT_CteInutilizacao = "https://cte.sefaz.mt.gov.br/ctews/services/CteInutilizacao";
	private final static String MT_CteConsultaProtocolo = "https://cte.sefaz.mt.gov.br/ctews/services/CteConsulta";
	private final static String MT_CteRecepcaoEvento = "https://cte.sefaz.mt.gov.br/ctews2/services/CteRecepcaoEvento?wsdl";
	private final static String MT_CTeRecepcaoOS = "https://cte.sefaz.mt.gov.br/ctews/services/CteRecepcaoOS?wsdl";
	// MS
	private final static String MS_CteStatusServicoHom = "https://homologacao.cte.ms.gov.br/ws/CteStatusServico";
	private final static String MS_CteRecepcaoHom = "https://homologacao.cte.ms.gov.br/ws/CteRecepcao";
	private final static String MS_CteRetRecepcaoHom = "https://homologacao.cte.ms.gov.br/ws/CteRetRecepcao";
	private final static String MS_CteInutilizacaoHom = "https://homologacao.cte.ms.gov.br/ws/CteInutilizacao";
	private final static String MS_CteConsultaProtocoloHom = "https://homologacao.cte.ms.gov.br/ws/CteConsulta";
	private final static String MS_CteRecepcaoEventoHom = "https://homologacao.cte.ms.gov.br/ws/CteRecepcaoEvento";
	private final static String MS_CTeRecepcaoOSHom = "https://homologacao.cte.ms.gov.br/ws/CteRecepcaoOS";
	private final static String MS_CteStatusServico = "https://producao.cte.ms.gov.br/ws/CteStatusServico";
	private final static String MS_CteRecepcao = "https://producao.cte.ms.gov.br/ws/CteRecepcao";
	private final static String MS_CteRetRecepcao = "https://producao.cte.ms.gov.br/ws/CteRetRecepcao";
	private final static String MS_CteInutilizacao = "https://producao.cte.ms.gov.br/ws/CteInutilizacao";
	private final static String MS_CteConsultaProtocolo = "https://producao.cte.ms.gov.br/ws/CteConsulta";
	private final static String MS_CteRecepcaoEvento = "https://producao.cte.ms.gov.br/ws/CteRecepcaoEvento";
	private final static String MS_CTeRecepcaoOS = "https://producao.cte.ms.gov.br/ws/CteRecepcaoOS";
	// MG
	private final static String MG_CteStatusServicoHom = "https://hcte.fazenda.mg.gov.br/cte/services/CteStatusServico?wsdl";
	private final static String MG_CteRecepcaoHom = "https://hcte.fazenda.mg.gov.br/cte/services/CteRecepcao?wsdl";
	private final static String MG_CteRetRecepcaoHom = "https://hcte.fazenda.mg.gov.br/cte/services/CteRetRecepcao?wsdl";
	private final static String MG_CteInutilizacaoHom = "https://hcte.fazenda.mg.gov.br/cte/services/CteInutilizacao?wsdl";
	private final static String MG_CteConsultaProtocoloHom = "https://hcte.fazenda.mg.gov.br/cte/services/CteConsulta?wsdl";
	private final static String MG_CteRecepcaoEventoHom = "https://hcte.fazenda.mg.gov.br/cte/services/RecepcaoEvento?wsdl";
	private final static String MG_CTeRecepcaoOSHom = "https://hcte.fazenda.mg.gov.br/cte/services/CteRecepcaoOS?wsdl";
	private final static String MG_CteStatusServico = "https://cte.fazenda.mg.gov.br/cte/services/CteStatusServico";
	private final static String MG_CteRecepcao = "https://cte.fazenda.mg.gov.br/cte/services/CteRecepcao";
	private final static String MG_CteRetRecepcao = "https://cte.fazenda.mg.gov.br/cte/services/CteRetRecepcao";
	private final static String MG_CteInutilizacao = "https://cte.fazenda.mg.gov.br/cte/services/CteInutilizacao";
	private final static String MG_CteConsultaProtocolo = "https://cte.fazenda.mg.gov.br/cte/services/CteConsulta";
	private final static String MG_CteRecepcaoEvento = "https://cte.fazenda.mg.gov.br/cte/services/RecepcaoEvento";
	private final static String MG_CTeRecepcaoOS = "https://cte.fazenda.mg.gov.br/cte/services/CteRecepcaoOS";
	// PR
	private final static String PR_CteStatusServicoHom = "https://homologacao.cte.fazenda.pr.gov.br/cte/CteStatusServico?wsdl";
	private final static String PR_CteRecepcaoHom = "https://homologacao.cte.fazenda.pr.gov.br/cte/CteRecepcao?wsdl";
	private final static String PR_CteRetRecepcaoHom = "https://homologacao.cte.fazenda.pr.gov.br/cte/CteRetRecepcao?wsdl";
	private final static String PR_CteInutilizacaoHom = "https://homologacao.cte.fazenda.pr.gov.br/cte/CteInutilizacao?wsdl";
	private final static String PR_CteConsultaProtocoloHom = "https://homologacao.cte.fazenda.pr.gov.br/cte/CteConsulta?wsdl";
	private final static String PR_CteRecepcaoEventoHom = "https://homologacao.cte.fazenda.pr.gov.br/cte/CteRecepcaoEvento?wsdl";
	private final static String PR_CTeRecepcaoOSHom = "https://homologacao.cte.fazenda.pr.gov.br/cte/CteRecepcaoOS?wsdl";
	private final static String PR_CteStatusServico = "https://cte.fazenda.pr.gov.br/cte/CteStatusServico?wsdl";
	private final static String PR_CteRecepcao = "https://cte.fazenda.pr.gov.br/cte/CteRecepcao?wsdl";
	private final static String PR_CteRetRecepcao = "https://cte.fazenda.pr.gov.br/cte/CteRetRecepcao?wsdl";
	private final static String PR_CteInutilizacao = "https://cte.fazenda.pr.gov.br/cte/CteInutilizacao?wsdl";
	private final static String PR_CteConsultaProtocolo = "https://cte.fazenda.pr.gov.br/cte/CteConsulta?wsdl";
	private final static String PR_CteRecepcaoEvento = "https://cte.fazenda.pr.gov.br/cte/CteRecepcaoEvento?wsdl";
	private final static String PR_CTeRecepcaoOS = "https://cte.fazenda.pr.gov.br/cte/CteRecepcaoOS?wsdl";
	// SP
	private final static String SP_CteStatusServicoHom = "https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteStatusServico.asmx";
	private final static String SP_CteRecepcaoHom = "https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteRecepcao.asmx";
	private final static String SP_CteRetRecepcaoHom = "https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteRetRecepcao.asmx";
	private final static String SP_CteInutilizacaoHom = "https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteInutilizacao.asmx";
	private final static String SP_CteConsultaProtocoloHom = "https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteConsulta.asmx";
	private final static String SP_CteRecepcaoEventoHom = "https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteRecepcaoEvento.asmx";
	private final static String SP_CTeRecepcaoOSHom = "https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteRecepcaoOS.asmx";
	private final static String SP_CteStatusServico = "https://nfe.fazenda.sp.gov.br/cteWEB/services/cteStatusServico.asmx";
	private final static String SP_CteRecepcao = "https://nfe.fazenda.sp.gov.br/cteWEB/services/cteRecepcao.asmx";
	private final static String SP_CteRetRecepcao = "https://nfe.fazenda.sp.gov.br/cteWEB/services/cteRetRecepcao.asmx";
	private final static String SP_CteInutilizacao = "https://nfe.fazenda.sp.gov.br/cteWEB/services/cteInutilizacao.asmx";
	private final static String SP_CteConsultaProtocolo = "https://nfe.fazenda.sp.gov.br/cteWEB/services/cteConsulta.asmx";
	private final static String SP_CteRecepcaoEvento = "https://nfe.fazenda.sp.gov.br/cteWEB/services/cteRecepcaoEvento.asmx";
	private final static String SP_CTeRecepcaoOS = "https://nfe.fazenda.sp.gov.br/cteWEB/services/cteRecepcaoOS.asmx";
	// SVSP
	private final static String SVSP_CteStatusServicoHom = "https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/CteStatusServico.asmx";
	private final static String SVSP_CteRecepcaoHom = "https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/CteRecepcao.asmx";
	private final static String SVSP_CteRetRecepcaoHom = "https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/CteRetRecepcao.asmx";
	private final static String SVSP_CteConsultaProtocoloHom = "https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/CteConsulta.asmx";
	private final static String SVSP_CteStatusServico = "https://nfe.fazenda.sp.gov.br/cteWEB/services/CteStatusServico.asmx";
	private final static String SVSP_CteRecepcao = "https://nfe.fazenda.sp.gov.br/cteWEB/services/cteRecepcao.asmx";
	private final static String SVSP_CteRetRecepcao = "https://nfe.fazenda.sp.gov.br/cteWEB/services/CteRetRecepcao.asmx";
	private final static String SVSP_CteConsultaProtocolo = "https://nfe.fazenda.sp.gov.br/cteWEB/services/CteConsulta.asmx";
	// AN
	private final static String AN_CTeDistribuicaoDFeHom = "https://hom1.cte.fazenda.gov.br/CTeDistribuicaoDFe/CTeDistribuicaoDFe.asmx";
	private final static String AN_CTeDistribuicaoDFe = "https://www1.cte.fazenda.gov.br/CTeDistribuicaoDFe/CTeDistribuicaoDFe.asmx";
	// QRCODE
	public final static String CTeQrCode = "https://dfe-portal.svrs.rs.gov.br/cte/qrCode";

	// Consulta servico
	public static String linkConsultaServico(String uf, String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AM") || uf.equals("BA") || uf.equals("CE")
					|| uf.equals("DF") || uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ")
					|| uf.equals("RN") || uf.equals("ES") || uf.equals("GO") || uf.equals("MA") || uf.equals("PA")
					|| uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN") || uf.equals("RO")
					|| uf.equals("RS") || uf.equals("SC") || uf.equals("SE") || uf.equals("TO")) {
				return SVRS_CteStatusServico;
			}
			if (uf.equals("MT")) {
				return MT_CteStatusServico;
			}
			if (uf.equals("MS")) {
				return MS_CteStatusServico;
			}
			if (uf.equals("MG")) {
				return MG_CteStatusServico;
			}
			if (uf.equals("PR")) {
				return PR_CteStatusServico;
			}
			if (uf.equals("SP")) {
				return SP_CteStatusServico;
			}
			if (uf.equals("AP") || uf.equals("PE") || uf.equals("RR")) {
				return SVSP_CteStatusServico;
			}
		}
		// Homologacao
		if (ambiente.equals("2")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AM") || uf.equals("BA") || uf.equals("CE")
					|| uf.equals("DF") || uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ")
					|| uf.equals("RN") || uf.equals("ES") || uf.equals("GO") || uf.equals("MA") || uf.equals("PA")
					|| uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN") || uf.equals("RO")
					|| uf.equals("RS") || uf.equals("SC") || uf.equals("SE") || uf.equals("TO")) {
				return SVRS_CteStatusServicoHom;
			}
			if (uf.equals("MT")) {
				return MT_CteStatusServicoHom;
			}
			if (uf.equals("MS")) {
				return MS_CteStatusServicoHom;
			}
			if (uf.equals("MG")) {
				return MG_CteStatusServicoHom;
			}
			if (uf.equals("PR")) {
				return PR_CteStatusServicoHom;
			}
			if (uf.equals("SP")) {
				return SP_CteStatusServicoHom;
			}
			if (uf.equals("AP") || uf.equals("PE") || uf.equals("RR")) {
				return SVSP_CteStatusServicoHom;
			}
		}
		return null;
	}

	// Envio servico
	public static String linkEnvioServico(String uf, String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AM") || uf.equals("BA") || uf.equals("CE")
					|| uf.equals("DF") || uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ")
					|| uf.equals("RN") || uf.equals("ES") || uf.equals("GO") || uf.equals("MA") || uf.equals("PA")
					|| uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN") || uf.equals("RO")
					|| uf.equals("RS") || uf.equals("SC") || uf.equals("SE") || uf.equals("TO")) {
				return SVRS_CteRecepcao;
			}
			if (uf.equals("MT")) {
				return MT_CteRecepcao;
			}
			if (uf.equals("MS")) {
				return MS_CteRecepcao;
			}
			if (uf.equals("MG")) {
				return MG_CteRecepcao;
			}
			if (uf.equals("PR")) {
				return PR_CteRecepcao;
			}
			if (uf.equals("SP")) {
				return SP_CteRecepcao;
			}
			if (uf.equals("AP") || uf.equals("PE") || uf.equals("RR")) {
				return SVSP_CteRecepcao;
			}
		}
		// Homologacao
		if (ambiente.equals("2")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AM") || uf.equals("BA") || uf.equals("CE")
					|| uf.equals("DF") || uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ")
					|| uf.equals("RN") || uf.equals("ES") || uf.equals("GO") || uf.equals("MA") || uf.equals("PA")
					|| uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN") || uf.equals("RO")
					|| uf.equals("RS") || uf.equals("SC") || uf.equals("SE") || uf.equals("TO")) {
				return SVRS_CteRecepcaoHom;
			}
			if (uf.equals("MT")) {
				return MT_CteRecepcaoHom;
			}
			if (uf.equals("MS")) {
				return MS_CteRecepcaoHom;
			}
			if (uf.equals("MG")) {
				return MG_CteRecepcaoHom;
			}
			if (uf.equals("PR")) {
				return PR_CteRecepcaoHom;
			}
			if (uf.equals("SP")) {
				return SP_CteRecepcaoHom;
			}
			if (uf.equals("AP") || uf.equals("PE") || uf.equals("RR")) {
				return SVSP_CteRecepcaoHom;
			}
		}
		return null;
	}

	// Consulta CT-e
	public static String linkEnvioRetServico(String uf, String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AM") || uf.equals("BA") || uf.equals("CE")
					|| uf.equals("DF") || uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ")
					|| uf.equals("RN") || uf.equals("ES") || uf.equals("GO") || uf.equals("MA") || uf.equals("PA")
					|| uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN") || uf.equals("RO")
					|| uf.equals("RS") || uf.equals("SC") || uf.equals("SE") || uf.equals("TO")) {
				return SVRS_CteRetRecepcao;
			}
			if (uf.equals("MT")) {
				return MT_CteRetRecepcao;
			}
			if (uf.equals("MS")) {
				return MS_CteRetRecepcao;
			}
			if (uf.equals("MG")) {
				return MG_CteRetRecepcao;
			}
			if (uf.equals("PR")) {
				return PR_CteRetRecepcao;
			}
			if (uf.equals("SP")) {
				return SP_CteRetRecepcao;
			}
			if (uf.equals("AP") || uf.equals("PE") || uf.equals("RR")) {
				return SVSP_CteRetRecepcao;
			}
		}
		// Homologacao
		if (ambiente.equals("2")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AM") || uf.equals("BA") || uf.equals("CE")
					|| uf.equals("DF") || uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ")
					|| uf.equals("RN") || uf.equals("ES") || uf.equals("GO") || uf.equals("MA") || uf.equals("PA")
					|| uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN") || uf.equals("RO")
					|| uf.equals("RS") || uf.equals("SC") || uf.equals("SE") || uf.equals("TO")) {
				return SVRS_CteRetRecepcaoHom;
			}
			if (uf.equals("MT")) {
				return MT_CteRetRecepcaoHom;
			}
			if (uf.equals("MS")) {
				return MS_CteRetRecepcaoHom;
			}
			if (uf.equals("MG")) {
				return MG_CteRetRecepcaoHom;
			}
			if (uf.equals("PR")) {
				return PR_CteRetRecepcaoHom;
			}
			if (uf.equals("SP")) {
				return SP_CteRetRecepcaoHom;
			}
			if (uf.equals("AP") || uf.equals("PE") || uf.equals("RR")) {
				return SVSP_CteRetRecepcaoHom;
			}
		}
		return null;
	}

	// Envio eventos
	public static String linkEnvioEventosServico(String uf, String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AM") || uf.equals("BA") || uf.equals("CE")
					|| uf.equals("DF") || uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ")
					|| uf.equals("RN") || uf.equals("ES") || uf.equals("GO") || uf.equals("MA") || uf.equals("PA")
					|| uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN") || uf.equals("RO")
					|| uf.equals("RS") || uf.equals("SC") || uf.equals("SE") || uf.equals("TO")) {
				return SVRS_CteRecepcaoEvento;
			}
			if (uf.equals("MT")) {
				return MT_CteRecepcaoEvento;
			}
			if (uf.equals("MS")) {
				return MS_CteRecepcaoEvento;
			}
			if (uf.equals("MG")) {
				return MG_CteRecepcaoEvento;
			}
			if (uf.equals("PR")) {
				return PR_CteRecepcaoEvento;
			}
			if (uf.equals("SP")) {
				return SP_CteRecepcaoEvento;
			}
		}
		// Homologacao
		if (ambiente.equals("2")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AM") || uf.equals("BA") || uf.equals("CE")
					|| uf.equals("DF") || uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ")
					|| uf.equals("RN") || uf.equals("ES") || uf.equals("GO") || uf.equals("MA") || uf.equals("PA")
					|| uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN") || uf.equals("RO")
					|| uf.equals("RS") || uf.equals("SC") || uf.equals("SE") || uf.equals("TO")) {
				return SVRS_CteRecepcaoEventoHom;
			}
			if (uf.equals("MT")) {
				return MT_CteRecepcaoEventoHom;
			}
			if (uf.equals("MS")) {
				return MS_CteRecepcaoEventoHom;
			}
			if (uf.equals("MG")) {
				return MG_CteRecepcaoEventoHom;
			}
			if (uf.equals("PR")) {
				return PR_CteRecepcaoEventoHom;
			}
			if (uf.equals("SP")) {
				return SP_CteRecepcaoEventoHom;
			}
		}
		return null;
	}

	// Inutilizar NFe
	public static String linkInutiliza(String uf, String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AM") || uf.equals("BA") || uf.equals("CE")
					|| uf.equals("DF") || uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ")
					|| uf.equals("RN") || uf.equals("ES") || uf.equals("GO") || uf.equals("MA") || uf.equals("PA")
					|| uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN") || uf.equals("RO")
					|| uf.equals("RS") || uf.equals("SC") || uf.equals("SE") || uf.equals("TO")) {
				return SVRS_CteInutilizacao;
			}
			if (uf.equals("MT")) {
				return MT_CteInutilizacao;
			}
			if (uf.equals("MS")) {
				return MS_CteInutilizacao;
			}
			if (uf.equals("MG")) {
				return MG_CteInutilizacao;
			}
			if (uf.equals("PR")) {
				return PR_CteInutilizacao;
			}
			if (uf.equals("SP")) {
				return SP_CteInutilizacao;
			}
		}
		// Homologacao
		if (ambiente.equals("2")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AM") || uf.equals("BA") || uf.equals("CE")
					|| uf.equals("DF") || uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ")
					|| uf.equals("RN") || uf.equals("ES") || uf.equals("GO") || uf.equals("MA") || uf.equals("PA")
					|| uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN") || uf.equals("RO")
					|| uf.equals("RS") || uf.equals("SC") || uf.equals("SE") || uf.equals("TO")) {
				return SVRS_CteInutilizacaoHom;
			}
			if (uf.equals("MT")) {
				return MT_CteInutilizacaoHom;
			}
			if (uf.equals("MS")) {
				return MS_CteInutilizacaoHom;
			}
			if (uf.equals("MG")) {
				return MG_CteInutilizacaoHom;
			}
			if (uf.equals("PR")) {
				return PR_CteInutilizacaoHom;
			}
			if (uf.equals("SP")) {
				return SP_CteInutilizacaoHom;
			}
		}
		return null;
	}

	// Consulta Distribuicao CTe Destinatario
	public static String linkConsultaDisCte(String uf, String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			return AN_CTeDistribuicaoDFe;
		}
		// Homologacao
		if (ambiente.equals("2")) {
			return AN_CTeDistribuicaoDFeHom;
		}
		return null;
	}
}
