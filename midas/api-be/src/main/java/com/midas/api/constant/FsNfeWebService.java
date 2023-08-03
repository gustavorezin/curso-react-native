package com.midas.api.constant;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class FsNfeWebService implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String versaoDados = "4.00";
	// SVRS - AC, AL, AP, CE, DF, ES, PB, PI, RJ, RN, RO, RR, RS, SC, SE, TO
	private final static String SVRS_NfeStatusServicoHom = "https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico4.asmx";
	private final static String SVRS_NfeAutorizacaoHom = "https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao4.asmx";
	private final static String SVRS_NfeRetAutorizacaoHom = "https://nfe-homologacao.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao4.asmx";
	private final static String SVRS_NfeInutilizacaoHom = "https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao4.asmx";
	private final static String SVRS_RecepcaoEventoHom = "https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento4.asmx";
	private final static String SVRS_NfeConsultaHom = "https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta4.asmx";
	private final static String SVRS_CadconsultaCadastroHom = "https://cad.svrs.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx";
	private final static String SVRS_NfeStatusServico = "https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico4.asmx";
	private final static String SVRS_NfeAutorizacao = "https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao4.asmx";
	private final static String SVRS_NFeRetAutorizacao4 = "https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao4.asmx";
	private final static String SVRS_NfeInutilizacao = "https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao4.asmx";
	private final static String SVRS_RecepcaoEvento = "https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento4.asmx";
	private final static String SVRS_NfeConsulta = "https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta4.asmx";
	private final static String SVRS_CadconsultaCadastro = "https://cad.svrs.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro4.asmx";
	// --NFCe
	private final static String SVRS_NfceStatusServicoHom = "https://nfce-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico4.asmx";
	private final static String SVRS_NfceAutorizacaoHom = "https://nfce-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao4.asmx";
	private final static String SVRS_NfceRetAutorizacaoHom = "https://nfce-homologacao.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao4.asmx";
	private final static String SVRS_NfceInutilizacaoHom = "https://nfce-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao4.asmx";
	private final static String SVRS_RecepcaoEventoHomNFCe = "https://nfce-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento4.asmx";
	private final static String SVRS_NfceConsultaHom = "https://nfce-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta4.asmx";
	public final static String SVRS_NfceQrCodeHom = "https://hom.sat.sef.sc.gov.br/nfce/consulta";
	public final static String SVRS_NfceUrlHom = "https://hom.sat.sef.sc.gov.br/nfce/consulta";
	private final static String SVRS_NfceStatusServico = "https://nfce.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico4.asmx";
	private final static String SVRS_NfceAutorizacao = "https://nfce.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao4.asmx";
	private final static String SVRS_NFceRetAutorizacao4 = "https://nfce.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao4.asmx";
	private final static String SVRS_NfceInutilizacao = "https://nfce.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao4.asmx";
	private final static String SVRS_RecepcaoEventoNFCe = "https://nfce.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento4.asmx";
	private final static String SVRS_NfceConsulta = "https://nfce.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta4.asmx";
	public final static String SVRS_NfceQrCode = "https://sat.sef.sc.gov.br/nfce/consulta";
	public final static String SVRS_NfceUrl = "https://sat.sef.sc.gov.br/nfce/consulta";
	// AM
	private final static String AM_NfeStatusServicoHom = "https://homnfe.sefaz.am.gov.br/services2/services/NfeStatusServico4";
	private final static String AM_NfeAutorizacaoHom = "https://homnfe.sefaz.am.gov.br/services2/services/NfeAutorizacao4";
	private final static String AM_NfeRetAutorizacaoHom = "https://homnfe.sefaz.am.gov.br/services2/services/NfeRetAutorizacao4";
	private final static String AM_NfeInutilizacaoHom = "https://homnfe.sefaz.am.gov.br/services2/services/NfeInutilizacao4";
	private final static String AM_RecepcaoEventoHom = "https://homnfe.sefaz.am.gov.br/services2/services/RecepcaoEvento4";
	private final static String AM_NfeConsultaHom = "https://homnfe.sefaz.am.gov.br/services2/services/NfeConsulta4";
	private final static String AM_NfeStatusServico = "https://nfe.sefaz.am.gov.br/services2/services/NfeStatusServico4";
	private final static String AM_NfeAutorizacao = "https://nfe.sefaz.am.gov.br/services2/services/NfeAutorizacao4";
	private final static String AM_NFeRetAutorizacao4 = "https://nfe.sefaz.am.gov.br/services2/services/NfeRetAutorizacao4";
	private final static String AM_NfeInutilizacao = "https://nfe.sefaz.am.gov.br/services2/services/NfeInutilizacao4";
	private final static String AM_RecepcaoEvento = "https://nfe.sefaz.am.gov.br/services2/services/RecepcaoEvento4";
	private final static String AM_NfeConsulta = "https://nfe.sefaz.am.gov.br/services2/services/NfeConsulta4";
	// BA
	private final static String BA_NfeInutilizacaoHom = "https://hnfe.sefaz.ba.gov.br/webservices/NFeInutilizacao4/NFeInutilizacao4.asmx";
	private final static String BA_NfeConsultaProtocoloHom = "https://hnfe.sefaz.ba.gov.br/webservices/NFeConsultaProtocolo4/NFeConsultaProtocolo4.asmx";
	private final static String BA_NfeStatusServicoHom = "https://hnfe.sefaz.ba.gov.br/webservices/NFeStatusServico4/NFeStatusServico4.asmx";
	private final static String BA_NfeConsultaCadastroHom = "https://hnfe.sefaz.ba.gov.br/webservices/CadConsultaCadastro4/CadConsultaCadastro4.asmx";
	private final static String BA_RecepcaoEventoHom = "https://hnfe.sefaz.ba.gov.br/webservices/NFeRecepcaoEvento4/NFeRecepcaoEvento4.asmx";
	private final static String BA_NFeAutorizacaoHom = "https://hnfe.sefaz.ba.gov.br/webservices/NFeAutorizacao4/NFeAutorizacao4.asmx";
	private final static String BA_NFeRetAutorizacaoHom = "https://hnfe.sefaz.ba.gov.br/webservices/NFeRetAutorizacao4/NFeRetAutorizacao4.asmx";
	private final static String BA_NfeInutilizacao = "https://nfe.sefaz.ba.gov.br/webservices/NFeInutilizacao4/NFeInutilizacao4.asmx";
	private final static String BA_NfeConsultaProtocolo = "https://nfe.sefaz.ba.gov.br/webservices/NFeConsultaProtocolo4/NFeConsultaProtocolo4.asmx";
	private final static String BA_NfeStatusServico = "	https://nfe.sefaz.ba.gov.br/webservices/NFeStatusServico4/NFeStatusServico4.asmx";
	private final static String BA_NfeConsultaCadastro = "https://nfe.sefaz.ba.gov.br/webservices/CadConsultaCadastro4/CadConsultaCadastro4.asmx";
	private final static String BA_RecepcaoEvento = "https://nfe.sefaz.ba.gov.br/webservices/NFeRecepcaoEvento4/NFeRecepcaoEvento4.asmx";
	private final static String BA_NFeAutorizacao = "https://nfe.sefaz.ba.gov.br/webservices/NFeAutorizacao4/NFeAutorizacao4.asmx";
	private final static String BA_NFeRetAutorizacao = "https://nfe.sefaz.ba.gov.br/webservices/NFeRetAutorizacao4/NFeRetAutorizacao4.asmx";
	// GO
	private final static String GO_NfeInutilizacaoHom = "https://homolog.sefaz.go.gov.br/nfe/services/NFeInutilizacao4";
	private final static String GO_NfeConsultaProtocoloHom = "https://homolog.sefaz.go.gov.br/nfe/services/NFeConsultaProtocolo4";
	private final static String GO_NfeStatusServicoHom = "https://homolog.sefaz.go.gov.br/nfe/services/NFeStatusServico4";
	private final static String GO_NfeConsultaCadastroHom = "https://homolog.sefaz.go.gov.br/nfe/services/CadConsultaCadastro4";
	private final static String GO_RecepcaoEventoHom = "https://homolog.sefaz.go.gov.br/nfe/services/NFeRecepcaoEvento4";
	private final static String GO_NFeAutorizacaoHom = "https://homolog.sefaz.go.gov.br/nfe/services/NFeAutorizacao4";
	private final static String GO_NFeRetAutorizacaoHom = "ttps://homolog.sefaz.go.gov.br/nfe/services/NFeRetAutorizacao4";
	private final static String GO_NfeInutilizacao = "https://nfe.sefaz.go.gov.br/nfe/services/NFeInutilizacao4";
	private final static String GO_NfeConsultaProtocolo = "https://nfe.sefaz.go.gov.br/nfe/services/NFeConsultaProtocolo4";
	private final static String GO_NfeStatusServico = "https://nfe.sefaz.go.gov.br/nfe/services/NFeStatusServico4";
	private final static String GO_NfeConsultaCadastro = "https://nfe.sefaz.go.gov.br/nfe/services/CadConsultaCadastro4";
	private final static String GO_RecepcaoEvento = "https://nfe.sefaz.go.gov.br/nfe/services/NFeRecepcaoEvento4";
	private final static String GO_NFeAutorizacao = "https://nfe.sefaz.go.gov.br/nfe/services/NFeAutorizacao4";
	private final static String GO_NFeRetAutorizacao = "https://nfe.sefaz.go.gov.br/nfe/services/NFeRetAutorizacao4";
	// MG
	private final static String MG_NfeInutilizacaoHom = "https://hnfe.fazenda.mg.gov.br/nfe2/services/NFeInutilizacao4";
	private final static String MG_NfeConsultaProtocoloHom = "https://hnfe.fazenda.mg.gov.br/nfe2/services/NFeConsultaProtocolo4";
	private final static String MG_NfeStatusServicoHom = "https://hnfe.fazenda.mg.gov.br/nfe2/services/NFeStatusServico4";
	private final static String MG_RecepcaoEventoHom = "https://hnfe.fazenda.mg.gov.br/nfe2/services/NFeRecepcaoEvento4";
	private final static String MG_NFeAutorizacaoHom = "https://hnfe.fazenda.mg.gov.br/nfe2/services/NFeAutorizacao4";
	private final static String MG_NFeRetAutorizacaoHom = "https://hnfe.fazenda.mg.gov.br/nfe2/services/NFeRetAutorizacao4";
	private final static String MG_NfeInutilizacao = "https://nfe.fazenda.mg.gov.br/nfe2/services/NFeInutilizacao4";
	private final static String MG_NfeConsultaProtocolo = "https://nfe.fazenda.mg.gov.br/nfe2/services/NFeConsultaProtocolo4";
	private final static String MG_NfeStatusServico = "https://nfe.fazenda.mg.gov.br/nfe2/services/NFeStatusServico4";
	private final static String MG_RecepcaoEvento = "https://nfe.fazenda.mg.gov.br/nfe2/services/NFeRecepcaoEvento4";
	private final static String MG_NFeAutorizacao = "https://nfe.fazenda.mg.gov.br/nfe2/services/NFeAutorizacao4";
	private final static String MG_NFeRetAutorizacao = "https://nfe.fazenda.mg.gov.br/nfe2/services/NFeRetAutorizacao4";
	// MS
	private final static String MS_NfeInutilizacaoHom = "https://hom.nfe.sefaz.ms.gov.br/ws/NFeInutilizacao4";
	private final static String MS_NfeConsultaProtocoloHom = "https://hom.nfe.sefaz.ms.gov.br/ws/NFeConsultaProtocolo4";
	private final static String MS_NfeStatusServicoHom = "https://hom.nfe.sefaz.ms.gov.br/ws/NFeStatusServico4";
	private final static String MS_NfeConsultaCadastroHom = "https://hom.nfe.sefaz.ms.gov.br/ws/CadConsultaCadastro4";
	private final static String MS_RecepcaoEventoHom = "https://hom.nfe.sefaz.ms.gov.br/ws/NFeRecepcaoEvento4";
	private final static String MS_NFeAutorizacaoHom = "https://hom.nfe.sefaz.ms.gov.br/ws/NFeAutorizacao4";
	private final static String MS_NFeRetAutorizacaoHom = "https://hom.nfe.sefaz.ms.gov.br/ws/NFeRetAutorizacao4";
	private final static String MS_NfeInutilizacao = "https://nfe.sefaz.ms.gov.br/ws/NFeInutilizacao4";
	private final static String MS_NfeConsultaProtocolo = "https://nfe.sefaz.ms.gov.br/ws/NFeConsultaProtocolo4";
	private final static String MS_NfeStatusServico = "https://nfe.sefaz.ms.gov.br/ws/NFeStatusServico4";
	private final static String MS_NfeConsultaCadastro = "https://nfe.sefaz.ms.gov.br/ws/CadConsultaCadastro4";
	private final static String MS_RecepcaoEvento = "https://nfe.sefaz.ms.gov.br/ws/NFeRecepcaoEvento4";
	private final static String MS_NFeAutorizacao = "https://nfe.sefaz.ms.gov.br/ws/NFeAutorizacao4";
	private final static String MS_NFeRetAutorizacao = "https://nfe.sefaz.ms.gov.br/ws/NFeRetAutorizacao4";
	// MT
	private final static String MT_NfeInutilizacaoHom = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeInutilizacao4";
	private final static String MT_NfeConsultaProtocoloHom = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeConsulta4";
	private final static String MT_NfeStatusServicoHom = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeStatusServico4";
	private final static String MT_NfeConsultaCadastroHom = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/CadConsultaCadastro4";
	private final static String MT_RecepcaoEventoHom = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/RecepcaoEvento4";
	private final static String MT_NFeAutorizacaoHom = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeAutorizacao4";
	private final static String MT_NFeRetAutorizacaoHom = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeRetAutorizacao4";
	private final static String MT_NfeInutilizacao = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeInutilizacao4";
	private final static String MT_NfeConsultaProtocolo = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeConsulta4";
	private final static String MT_NfeStatusServico = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeStatusServico4";
	private final static String MT_NfeConsultaCadastro = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/CadConsultaCadastro4";
	private final static String MT_RecepcaoEvento = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/RecepcaoEvento4";
	private final static String MT_NFeAutorizacao = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeAutorizacao4";
	private final static String MT_NFeRetAutorizacao = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeRetAutorizacao4";
	// PE
	private final static String PE_NfeInutilizacaoHom = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NFeInutilizacao4";
	private final static String PE_NfeConsultaProtocoloHom = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NFeConsultaProtocolo4";
	private final static String PE_NfeStatusServicoHom = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NFeStatusServico4";
	private final static String PE_NfeConsultaCadastroHom = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/CadConsultaCadastro4";
	private final static String PE_RecepcaoEventoHom = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NFeRecepcaoEvento4";
	private final static String PE_NFeAutorizacaoHom = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NFeAutorizacao4";
	private final static String PE_NFeRetAutorizacaoHom = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NFeRetAutorizacao4";
	private final static String PE_NfeInutilizacao = "https://nfe.sefaz.pe.gov.br/nfe-service/services/NFeInutilizacao4";
	private final static String PE_NfeConsultaProtocolo = "https://nfe.sefaz.pe.gov.br/nfe-service/services/NFeConsultaProtocolo4";
	private final static String PE_NfeStatusServico = "https://nfe.sefaz.pe.gov.br/nfe-service/services/NFeStatusServico4";
	private final static String PE_NfeConsultaCadastro = "https://nfe.sefaz.pe.gov.br/nfe-service/services/CadConsultaCadastro4";
	private final static String PE_RecepcaoEvento = "https://nfe.sefaz.pe.gov.br/nfe-service/services/NFeRecepcaoEvento4";
	private final static String PE_NFeAutorizacao = "https://nfe.sefaz.pe.gov.br/nfe-service/services/NFeAutorizacao4";
	private final static String PE_NFeRetAutorizacao = "https://nfe.sefaz.pe.gov.br/nfe-service/services/NFeRetAutorizacao4";
	// PR
	private final static String PR_NfeInutilizacaoHom = "https://homologacao.nfe.sefa.pr.gov.br/nfe/NFeInutilizacao4";
	private final static String PR_NfeConsultaProtocoloHom = "https://homologacao.nfe.sefa.pr.gov.br/nfe/NFeConsultaProtocolo4";
	private final static String PR_NfeStatusServicoHom = "https://homologacao.nfe.sefa.pr.gov.br/nfe/NFeStatusServico4";
	private final static String PR_NfeConsultaCadastroHom = "https://homologacao.nfe.sefa.pr.gov.br/nfe/CadConsultaCadastro4";
	private final static String PR_RecepcaoEventoHom = "https://homologacao.nfe.sefa.pr.gov.br/nfe/NFeRecepcaoEvento4";
	private final static String PR_NFeAutorizacaoHom = "https://homologacao.nfe.sefa.pr.gov.br/nfe/NFeAutorizacao4";
	private final static String PR_NFeRetAutorizacaoHom = "https://homologacao.nfe.sefa.pr.gov.br/nfe/NFeRetAutorizacao4";
	private final static String PR_NfeInutilizacao = "https://nfe.sefa.pr.gov.br/nfe/NFeInutilizacao4";
	private final static String PR_NfeConsultaProtocolo = "https://nfe.sefa.pr.gov.br/nfe/NFeConsultaProtocolo4";
	private final static String PR_NfeStatusServico = "https://nfe.sefa.pr.gov.br/nfe/NFeStatusServico4";
	private final static String PR_NfeConsultaCadastro = "https://nfe.sefa.pr.gov.br/nfe/CadConsultaCadastro4";
	private final static String PR_RecepcaoEvento = "https://nfe.sefa.pr.gov.br/nfe/NFeRecepcaoEvento4";
	private final static String PR_NFeAutorizacao = "https://nfe.sefa.pr.gov.br/nfe/NFeAutorizacao4";
	private final static String PR_NFeRetAutorizacao = "https://nfe.sefa.pr.gov.br/nfe/NFeRetAutorizacao4";
	// SP
	private final static String SP_NfeInutilizacaoHom = "https://homologacao.nfe.fazenda.sp.gov.br/ws/nfeinutilizacao4.asmx";
	private final static String SP_NfeConsultaProtocoloHom = "https://homologacao.nfe.fazenda.sp.gov.br/ws/nfeconsultaprotocolo4.asmx";
	private final static String SP_NfeStatusServicoHom = "https://homologacao.nfe.fazenda.sp.gov.br/ws/nfestatusservico4.asmx";
	private final static String SP_NfeConsultaCadastroHom = "https://homologacao.nfe.fazenda.sp.gov.br/ws/cadconsultacadastro4.asmx";
	private final static String SP_RecepcaoEventoHom = "https://homologacao.nfe.fazenda.sp.gov.br/ws/nferecepcaoevento4.asmx";
	private final static String SP_NFeAutorizacaoHom = "https://homologacao.nfe.fazenda.sp.gov.br/ws/nfeautorizacao4.asmx";
	private final static String SP_NFeRetAutorizacaoHom = "https://homologacao.nfe.fazenda.sp.gov.br/ws/nferetautorizacao4.asmx";
	private final static String SP_NfeInutilizacao = "https://nfe.fazenda.sp.gov.br/ws/nfeinutilizacao4.asmx";
	private final static String SP_NfeConsultaProtocolo = "https://nfe.fazenda.sp.gov.br/ws/nfeconsultaprotocolo4.asmx";
	private final static String SP_NfeStatusServico = "https://nfe.fazenda.sp.gov.br/ws/nfestatusservico4.asmx";
	private final static String SP_NfeConsultaCadastro = "https://nfe.fazenda.sp.gov.br/ws/cadconsultacadastro4.asmx";
	private final static String SP_RecepcaoEvento = "https://nfe.fazenda.sp.gov.br/ws/nferecepcaoevento4.asmx";
	private final static String SP_NFeAutorizacao = "https://nfe.fazenda.sp.gov.br/ws/nfeautorizacao4.asmx";
	private final static String SP_NFeRetAutorizacao = "https://nfe.fazenda.sp.gov.br/ws/nferetautorizacao4.asmx";
	// SVAN - MA, PA
	private final static String MA_NfeConsultaCadastroHom = "https://sistemas.sefaz.ma.gov.br/wscadastro/CadConsultaCadastro2?wsdl";
	private final static String MA_NfeConsultaCadastro = "https://sistemas.sefaz.ma.gov.br/wscadastro/CadConsultaCadastro2?wsdl";
	private final static String SVAN_NfeInutilizacaoHom = "https://hom.sefazvirtual.fazenda.gov.br/NFeInutilizacao4/NFeInutilizacao4.asmx";
	private final static String SVAN_NfeConsultaProtocoloHom = "https://hom.sefazvirtual.fazenda.gov.br/NFeConsultaProtocolo4/NFeConsultaProtocolo4.asmx";
	private final static String SVAN_NfeStatusServicoHom = "https://hom.sefazvirtual.fazenda.gov.br/NFeStatusServico4/NFeStatusServico4.asmx";
	private final static String SVAN_RecepcaoEventoHom = "https://hom.sefazvirtual.fazenda.gov.br/NFeRecepcaoEvento4/NFeRecepcaoEvento4.asmx";
	private final static String SVAN_NFeAutorizacaoHom = "https://hom.sefazvirtual.fazenda.gov.br/NFeAutorizacao4/NFeAutorizacao4.asmx";
	private final static String SVAN_NFeRetAutorizacaoHom = "https://hom.sefazvirtual.fazenda.gov.br/NFeRetAutorizacao4/NFeRetAutorizacao4.asmx";
	private final static String SVAN_NfeInutilizacao = "https://www.sefazvirtual.fazenda.gov.br/NFeInutilizacao4/NFeInutilizacao4.asmx";
	private final static String SVAN_NfeConsultaProtocolo = "https://www.sefazvirtual.fazenda.gov.br/NFeConsultaProtocolo4/NFeConsultaProtocolo4.asmx";
	private final static String SVAN_NfeStatusServico = "https://www.sefazvirtual.fazenda.gov.br/NFeStatusServico4/NFeStatusServico4.asmx";
	private final static String SVAN_RecepcaoEvento = "https://www.sefazvirtual.fazenda.gov.br/NFeRecepcaoEvento4/NFeRecepcaoEvento4.asmx";
	private final static String SVAN_NFeAutorizacao = "https://www.sefazvirtual.fazenda.gov.br/NFeAutorizacao4/NFeAutorizacao4.asmx";
	private final static String SVAN_NFeRetAutorizacao = "https://www.sefazvirtual.fazenda.gov.br/NFeRetAutorizacao4/NFeRetAutorizacao4.asmx";
	// AN - AMBIENTE NACIONAL
	private final static String AN_NFeDistribuicaoDFeHom = "https://hom1.nfe.fazenda.gov.br/NFeDistribuicaoDFe/NFeDistribuicaoDFe.asmx";
	private final static String AN_RecepcaoEventoHom = "https://hom1.nfe.fazenda.gov.br/NFeRecepcaoEvento4/NFeRecepcaoEvento4.asmx";
	private final static String AN_NFeDistribuicaoDFe = "https://www1.nfe.fazenda.gov.br/NFeDistribuicaoDFe/NFeDistribuicaoDFe.asmx";
	private final static String AN_RecepcaoEvento = "https://www.nfe.fazenda.gov.br/NFeRecepcaoEvento4/NFeRecepcaoEvento4.asmx";

	// Envio servico
	public static String linkEnvioServico(String uf, String ambiente, String modelo) {
		// Producao
		if (ambiente.equals("1")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AP") || uf.equals("CE") || uf.equals("DF")
					|| uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN")
					|| uf.equals("RO") || uf.equals("RR") || uf.equals("RS") || uf.equals("SC") || uf.equals("SE")
					|| uf.equals("TO")) {
				if (modelo.equals("55")) {
					return SVRS_NfeAutorizacao;
				} else {
					return SVRS_NfceAutorizacao;
				}
			}
			if (uf.equals("MA") || uf.equals("PA")) {
				return SVAN_NFeAutorizacao;
			}
			if (uf.equals("AM")) {
				return AM_NfeAutorizacao;
			}
			if (uf.equals("BA")) {
				return BA_NFeAutorizacao;
			}
			if (uf.equals("GO")) {
				return GO_NFeAutorizacao;
			}
			if (uf.equals("MG")) {
				return MG_NFeAutorizacao;
			}
			if (uf.equals("MS")) {
				return MS_NFeAutorizacao;
			}
			if (uf.equals("MT")) {
				return MT_NFeAutorizacao;
			}
			if (uf.equals("PE")) {
				return PE_NFeAutorizacao;
			}
			if (uf.equals("PR")) {
				return PR_NFeAutorizacao;
			}
			if (uf.equals("SP")) {
				return SP_NFeAutorizacao;
			}
		}
		// Homologacao
		if (ambiente.equals("2")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AP") || uf.equals("CE") || uf.equals("DF")
					|| uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN")
					|| uf.equals("RO") || uf.equals("RR") || uf.equals("RS") || uf.equals("SC") || uf.equals("SE")
					|| uf.equals("TO")) {
				if (modelo.equals("55")) {
					return SVRS_NfeAutorizacaoHom;
				} else {
					return SVRS_NfceAutorizacaoHom;
				}
			}
			if (uf.equals("MA") || uf.equals("PA")) {
				return SVAN_NFeAutorizacaoHom;
			}
			if (uf.equals("AM")) {
				return AM_NfeAutorizacaoHom;
			}
			if (uf.equals("BA")) {
				return BA_NFeAutorizacaoHom;
			}
			if (uf.equals("GO")) {
				return GO_NFeAutorizacaoHom;
			}
			if (uf.equals("MG")) {
				return MG_NFeAutorizacaoHom;
			}
			if (uf.equals("MT")) {
				return MT_NFeAutorizacaoHom;
			}
			if (uf.equals("PR")) {
				return PR_NFeAutorizacaoHom;
			}
			if (uf.equals("SP")) {
				return SP_NfeStatusServicoHom;
			}
		}
		return null;
	}

	// Consulta servico
	public static String linkConsultaServico(String uf, String ambiente, String modelo) {
		// Producao
		if (ambiente.equals("1")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AP") || uf.equals("CE") || uf.equals("DF")
					|| uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN")
					|| uf.equals("RO") || uf.equals("RR") || uf.equals("RS") || uf.equals("SC") || uf.equals("SE")
					|| uf.equals("TO")) {
				if (modelo.equals("55")) {
					return SVRS_NfeStatusServico;
				} else {
					return SVRS_NfceStatusServico;
				}
			}
			if (uf.equals("MA") || uf.equals("PA")) {
				return SVAN_NfeStatusServico;
			}
			if (uf.equals("AM")) {
				return AM_NfeStatusServico;
			}
			if (uf.equals("BA")) {
				return BA_NfeStatusServico;
			}
			if (uf.equals("GO")) {
				return GO_NfeStatusServico;
			}
			if (uf.equals("MG")) {
				return MG_NfeStatusServico;
			}
			if (uf.equals("MS")) {
				return MS_NfeStatusServico;
			}
			if (uf.equals("MT")) {
				return MT_NfeStatusServico;
			}
			if (uf.equals("PE")) {
				return PE_NfeStatusServico;
			}
			if (uf.equals("PR")) {
				return PR_NfeStatusServico;
			}
			if (uf.equals("SP")) {
				return SP_NfeStatusServico;
			}
		}
		// Homologacao
		if (ambiente.equals("2")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AP") || uf.equals("CE") || uf.equals("DF")
					|| uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN")
					|| uf.equals("RO") || uf.equals("RR") || uf.equals("RS") || uf.equals("SC") || uf.equals("SE")
					|| uf.equals("TO")) {
				if (modelo.equals("55")) {
					return SVRS_NfeStatusServicoHom;
				} else {
					return SVRS_NfceStatusServicoHom;
				}
			}
			if (uf.equals("MA") || uf.equals("PA")) {
				return SVAN_NfeStatusServicoHom;
			}
			if (uf.equals("AM")) {
				return AM_NfeStatusServicoHom;
			}
			if (uf.equals("BA")) {
				return BA_NfeStatusServicoHom;
			}
			if (uf.equals("GO")) {
				return GO_NfeStatusServicoHom;
			}
			if (uf.equals("MG")) {
				return MG_NfeStatusServicoHom;
			}
			if (uf.equals("MT")) {
				return MT_NfeStatusServicoHom;
			}
			if (uf.equals("PR")) {
				return PR_NfeStatusServicoHom;
			}
			if (uf.equals("SP")) {
				return SP_NfeStatusServicoHom;
			}
		}
		return null;
	}

	// Consulta cadastro
	public static String linkConsultaCadastro(String uf, String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AP") || uf.equals("CE") || uf.equals("DF")
					|| uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN")
					|| uf.equals("RO") || uf.equals("RR") || uf.equals("RS") || uf.equals("SC") || uf.equals("SE")
					|| uf.equals("TO") || uf.equals("PA")) {
				return SVRS_CadconsultaCadastro;
			}
			if (uf.equals("MA")) {
				return MA_NfeConsultaCadastro;
			}
			if (uf.equals("AM")) {
				return null;
			}
			if (uf.equals("BA")) {
				return BA_NfeConsultaCadastro;
			}
			if (uf.equals("GO")) {
				return GO_NfeConsultaCadastro;
			}
			if (uf.equals("MG")) {
				return null;
			}
			if (uf.equals("MS")) {
				return MS_NfeConsultaCadastro;
			}
			if (uf.equals("MT")) {
				return MT_NfeConsultaCadastro;
			}
			if (uf.equals("PE")) {
				return PE_NfeConsultaCadastro;
			}
			if (uf.equals("PR")) {
				return PR_NfeConsultaCadastro;
			}
			if (uf.equals("SP")) {
				return SP_NfeConsultaCadastro;
			}
		}
		// Homologacao
		if (ambiente.equals("2")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AP") || uf.equals("CE") || uf.equals("DF")
					|| uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN")
					|| uf.equals("RO") || uf.equals("RR") || uf.equals("RS") || uf.equals("SC") || uf.equals("SE")
					|| uf.equals("TO") || uf.equals("PA")) {
				return SVRS_CadconsultaCadastroHom;
			}
			if (uf.equals("MA")) {
				return MA_NfeConsultaCadastroHom;
			}
			if (uf.equals("AM")) {
				return null;
			}
			if (uf.equals("BA")) {
				return BA_NfeConsultaCadastroHom;
			}
			if (uf.equals("GO")) {
				return GO_NfeConsultaCadastroHom;
			}
			if (uf.equals("MG")) {
				return null;
			}
			if (uf.equals("MT")) {
				return MT_NfeConsultaCadastroHom;
			}
			if (uf.equals("PR")) {
				return PR_NfeConsultaCadastroHom;
			}
			if (uf.equals("SP")) {
				return SP_NfeConsultaCadastroHom;
			}
		}
		return null;
	}

	// Consulta Distribuicao NFe Destinatario
	public static String linkConsultaDisNfe(String uf, String ambiente) {
		// Producao
		if (ambiente.equals("1")) {
			return AN_NFeDistribuicaoDFe;
		}
		// Homologacao
		if (ambiente.equals("2")) {
			return AN_NFeDistribuicaoDFeHom;
		}
		return null;
	}

	// Inutilizar NFe
	public static String linkInutiliza(String uf, String ambiente, String modelo) {
		// Producao
		if (ambiente.equals("1")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AP") || uf.equals("CE") || uf.equals("DF")
					|| uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN")
					|| uf.equals("RO") || uf.equals("RR") || uf.equals("RS") || uf.equals("SC") || uf.equals("SE")
					|| uf.equals("TO")) {
				if (modelo.equals("55")) {
					return SVRS_NfeInutilizacao;
				} else {
					return SVRS_NfceInutilizacao;
				}
			}
			if (uf.equals("MA") || uf.equals("PA")) {
				return SVAN_NfeInutilizacao;
			}
			if (uf.equals("AM")) {
				return AM_NfeInutilizacao;
			}
			if (uf.equals("BA")) {
				return BA_NfeInutilizacao;
			}
			if (uf.equals("GO")) {
				return GO_NfeInutilizacao;
			}
			if (uf.equals("MG")) {
				return MG_NfeInutilizacao;
			}
			if (uf.equals("MS")) {
				return MS_NfeInutilizacao;
			}
			if (uf.equals("MT")) {
				return MT_NfeInutilizacao;
			}
			if (uf.equals("PE")) {
				return PE_NfeInutilizacao;
			}
			if (uf.equals("PR")) {
				return PR_NfeInutilizacao;
			}
			if (uf.equals("SP")) {
				return SP_NfeInutilizacao;
			}
		}
		// Homologacao
		if (ambiente.equals("2")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AP") || uf.equals("CE") || uf.equals("DF")
					|| uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN")
					|| uf.equals("RO") || uf.equals("RR") || uf.equals("RS") || uf.equals("SC") || uf.equals("SE")
					|| uf.equals("TO")) {
				if (modelo.equals("55")) {
					return SVRS_NfeInutilizacaoHom;
				} else {
					return SVRS_NfceInutilizacaoHom;
				}
			}
			if (uf.equals("MA") || uf.equals("PA")) {
				return SVAN_NfeInutilizacaoHom;
			}
			if (uf.equals("AM")) {
				return AM_NfeInutilizacaoHom;
			}
			if (uf.equals("BA")) {
				return BA_NfeInutilizacaoHom;
			}
			if (uf.equals("GO")) {
				return GO_NfeInutilizacaoHom;
			}
			if (uf.equals("MG")) {
				return MG_NfeInutilizacaoHom;
			}
			if (uf.equals("MS")) {
				return MS_NfeInutilizacaoHom;
			}
			if (uf.equals("MT")) {
				return MT_NfeInutilizacaoHom;
			}
			if (uf.equals("PE")) {
				return PE_NfeInutilizacaoHom;
			}
			if (uf.equals("PR")) {
				return PR_NfeInutilizacaoHom;
			}
			if (uf.equals("SP")) {
				return SP_NfeInutilizacaoHom;
			}
		}
		return null;
	}

	// Eventos NFe
	public static String linkEvento(String uf, String ambiente, String modelo) {
		// Producao
		if (ambiente.equals("1")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AP") || uf.equals("CE") || uf.equals("DF")
					|| uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN")
					|| uf.equals("RO") || uf.equals("RR") || uf.equals("RS") || uf.equals("SC") || uf.equals("SE")
					|| uf.equals("TO")) {
				if (modelo.equals("55")) {
					return SVRS_RecepcaoEvento;
				} else {
					return SVRS_RecepcaoEventoNFCe;
				}
			}
			if (uf.equals("MA") || uf.equals("PA")) {
				return SVAN_RecepcaoEvento;
			}
			if (uf.equals("AM")) {
				return AM_RecepcaoEvento;
			}
			if (uf.equals("BA")) {
				return BA_RecepcaoEvento;
			}
			if (uf.equals("GO")) {
				return GO_RecepcaoEvento;
			}
			if (uf.equals("MG")) {
				return MG_RecepcaoEvento;
			}
			if (uf.equals("MS")) {
				return MS_RecepcaoEvento;
			}
			if (uf.equals("MT")) {
				return MT_RecepcaoEvento;
			}
			if (uf.equals("PE")) {
				return PE_RecepcaoEvento;
			}
			if (uf.equals("PR")) {
				return PR_RecepcaoEvento;
			}
			if (uf.equals("SP")) {
				return SP_RecepcaoEvento;
			}
			if (uf.equals("AN")) {
				return AN_RecepcaoEvento;
			}
		}
		// Homologacao
		if (ambiente.equals("2")) {
			if (uf.equals("AC") || uf.equals("AL") || uf.equals("AP") || uf.equals("CE") || uf.equals("DF")
					|| uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN")
					|| uf.equals("RO") || uf.equals("RR") || uf.equals("RS") || uf.equals("SC") || uf.equals("SE")
					|| uf.equals("TO")) {
				if (modelo.equals("55")) {
					return SVRS_RecepcaoEventoHom;
				} else {
					return SVRS_RecepcaoEventoHomNFCe;
				}
			}
			if (uf.equals("MA") || uf.equals("PA")) {
				return SVAN_RecepcaoEventoHom;
			}
			if (uf.equals("AM")) {
				return AM_RecepcaoEventoHom;
			}
			if (uf.equals("BA")) {
				return BA_RecepcaoEventoHom;
			}
			if (uf.equals("GO")) {
				return GO_RecepcaoEventoHom;
			}
			if (uf.equals("MG")) {
				return MG_RecepcaoEventoHom;
			}
			if (uf.equals("MS")) {
				return MS_RecepcaoEventoHom;
			}
			if (uf.equals("MT")) {
				return MT_RecepcaoEventoHom;
			}
			if (uf.equals("PE")) {
				return PE_RecepcaoEventoHom;
			}
			if (uf.equals("PR")) {
				return PR_RecepcaoEventoHom;
			}
			if (uf.equals("SP")) {
				return SP_RecepcaoEventoHom;
			}
			if (uf.equals("AN")) {
				return AN_RecepcaoEventoHom;
			}
		}
		return null;
	}

	// Identifica UF emissao
	public static String ufEmissao(String uf) {
		if (uf.equals("AC") || uf.equals("AL") || uf.equals("AP") || uf.equals("CE") || uf.equals("DF")
				|| uf.equals("ES") || uf.equals("PB") || uf.equals("PI") || uf.equals("RJ") || uf.equals("RN")
				|| uf.equals("RO") || uf.equals("RR") || uf.equals("RS") || uf.equals("SC") || uf.equals("SE")
				|| uf.equals("TO")) {
			return "43";
		}
		if (uf.equals("MA") || uf.equals("PA")) {
			return "91";
		}
		if (uf.equals("AM")) {
			return "13";
		}
		if (uf.equals("BA")) {
			return "29";
		}
		if (uf.equals("GO")) {
			return "52";
		}
		if (uf.equals("MG")) {
			return "31";
		}
		if (uf.equals("MS")) {
			return "50";
		}
		if (uf.equals("MT")) {
			return "51";
		}
		if (uf.equals("PE")) {
			return "26";
		}
		if (uf.equals("PR")) {
			return "43";
		}
		if (uf.equals("SP")) {
			return "35";
		}
		return null;
	}
}
