package com.midas.api.tenant.service.integra;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.midas.api.constant.MidasConfig;
import com.midas.api.security.ClienteParam;
import com.midas.api.tenant.entity.CdBolcfg;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdPlconMicro;
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FnTituloCcusto;
import com.midas.api.tenant.entity.tecno.TecnoBoleto;
import com.midas.api.tenant.entity.tecno.TecnoBoletoEmail;
import com.midas.api.tenant.entity.tecno.TecnoRetornoBoleto;
import com.midas.api.tenant.entity.tecno.TecnoRetornoDadosList;
import com.midas.api.tenant.entity.tecno.TecnoRetornoListStatus;
import com.midas.api.tenant.fiscal.util.FsTipoNomeUtil;
import com.midas.api.tenant.repository.CdBolcfgRepository;
import com.midas.api.tenant.repository.CdPlconMicroRepository;
import com.midas.api.tenant.repository.FnTituloCcustoRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.service.FnCcustoService;
import com.midas.api.tenant.service.FnCxmvService;
import com.midas.api.tenant.service.FnDreService;
import com.midas.api.tenant.service.FnTituloService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.CryptPassUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.HttpRequest;
import com.midas.api.util.LcDocTipoNomeUtil;
import com.midas.api.util.MoedaUtil;
import com.midas.api.util.integra.TecnoUtil;

@Service
public class TecnoBoletoService {
	@Autowired
	private CdBolcfgRepository bolcfgRp;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private FnTituloService fnTituloService;
	@Autowired
	private FnCxmvService fnCxmvService;
	@Autowired
	private FnDreService fnDreService;
	@Autowired
	private FnCcustoService fnCcustoService;
	@Autowired
	private FnTituloCcustoRepository fnTituloCcustoRp;
	@Autowired
	private CdPlconMicroRepository cdPlconMicroRp;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private CryptPassUtil cryptPassUtil;

	// Insere boleto(s)---------------------------------
	public List<TecnoBoleto> incluirBoletos(List<FnTitulo> titulos, CdBolcfg bc, List<String> auxmsg)
			throws ParseException {
		List<TecnoBoleto> bols = new ArrayList<TecnoBoleto>();
		Integer codnum = 1;
		// Inclui boletos via titulos
		for (FnTitulo t : titulos) {
			CdPessoa para = t.getCdpessoapara();
			if (!para.getCpfcnpj().equals("00000000000") && !para.getCpfcnpj().equals("00000000000000")) {
				if (t.getTipo().equals("R")) {
					// Atualiza nosso numero---------------------
					Integer nossoNum = bc.getNossonumatual() + codnum;
					bolcfgRp.updateNossoNum(nossoNum, bc.getId());
					// Verifica cadastro valido
					TecnoBoleto b = new TecnoBoleto();
					// Conta-convenio-
					b.setCedenteContaCodigoBanco(bc.getBanco());
					b.setCedenteContaNumero(bc.getConta());
					b.setCedenteContaNumeroDV(bc.getContadv());
					b.setCedenteConvenioNumero(bc.getNumconvenio());
					// Variacao para alguns bancos
					if (bc.getVarcart() != null) {
						if (!bc.getVarcart().equals("")) {
							b.setTituloVariacaoCarteira(bc.getVarcart());
						}
					}
					// Cliente------
					b.setSacadoCPFCNPJ(para.getCpfcnpj());
					b.setSacadoNome(para.getNome());
					b.setSacadoEnderecoLogradouro(para.getRua());
					b.setSacadoEnderecoNumero(para.getNum());
					b.setSacadoEnderecoBairro(para.getBairro());
					b.setSacadoEnderecoCep(para.getCep());
					b.setSacadoEnderecoCidade(para.getCdcidade().getNome());
					b.setSacadoEnderecoComplemento(para.getComp());
					b.setSacadoEnderecoUf(para.getCdestado().getUf());
					b.setSacadoEnderecoPais(nomePais(para));
					b.setSacadoEmail(endEmail(para));
					b.setSacadoTelefone(numFone(para));
					b.setSacadoCelular(para.getFonedois());
					// Titulo-------
					b.setTituloNumeroDocumento(numDocNota(t));
					b.setTituloNossoNumero(nossoNum + "");
					b.setTituloDataEmissao(DataUtil.dataPadraoBr(new Date()));
					b.setTituloDataVencimento(DataUtil.dataPadraoBr(t.getVence()));
					b.setTituloValor(MoedaUtil.moedaPadrao(t.getVsaldo()));
					b.setTituloAceite("S");
					b.setTituloDocEspecie("01");// 01 - Duplicata mercantil
					b.setTituloLocalPagamento(bc.getLocalpaginfo());
					// Descontos----
					/*
					 * Desativado - aplicando desconto em duplicidade
					 * 
					 * if (t.getVdesc().compareTo(BigDecimal.ZERO) > 0) {
					 * b.setTituloCodDesconto("1");
					 * b.setTituloDataDesconto(DataUtil.dataPadraoBr(t.getVence()));
					 * b.setTituloValorDescontoTaxa(MoedaUtil.moedaPadrao(t.getVdesc())); } else {
					 */
					b.setTituloCodDesconto("0");
					// Juros---------
					if (bc.getPjuro().compareTo(BigDecimal.ZERO) > 0) {
						b.setTituloCodigoJuros("2");
						// Add um dia apos vence para cobrar juros
						Date dataCob = DataUtil.addRemDias(t.getVence(), 1, "A");
						b.setTituloDataJuros(DataUtil.dataPadraoBr(dataCob));
						b.setTituloValorJuros(MoedaUtil.moedaPadrao(bc.getPjuro()));
					} else {
						b.setTituloCodigoJuros("3");
					}
					// Multa----------
					if (bc.getPmulta().compareTo(BigDecimal.ZERO) > 0) {
						b.setTituloCodigoMulta("2");
						// Add um dia apos vence para cobrar multa
						Date dataCob = DataUtil.addRemDias(t.getVence(), 1, "A");
						b.setTituloDataMulta(DataUtil.dataPadraoBr(dataCob));
						b.setTituloValorMultaTaxa(MoedaUtil.moedaPadrao(bc.getPmulta()));
					} else {
						b.setTituloCodigoJuros("0");
					}
					// Protesto-------
					if (bc.getDiasprotesto() > 0) {
						b.setTituloCodProtesto(bc.getCodprotesto());
						b.setTituloPrazoProtesto(bc.getDiasprotesto() + "");
					} else {
						b.setTituloCodProtesto(bc.getCodprotesto());
					}
					// Baixa auto------
					b.setTituloCodBaixaDevolucao("2");
					// Mensagens
					b.setTituloMensagem01(bc.getMsgum());
					b.setTituloMensagem02(bc.getMsgdois());
					b.setTituloMensagem03(bc.getMsgtres());
					b.setTituloMensagem04(bc.getMsgquatro());
					b.setTituloMensagem05(bc.getMsgcinco());
					b.setTituloMensagem06(bc.getMsgseis());
					b.setTituloMensagem07(bc.getMsgsete());
					b.setTituloMensagem08(auxmsg.get(0));
					b.setTituloMensagem09(auxmsg.get(1));
					bols.add(b);
					codnum++;
				}
			}
		}
		return bols;
	}

	// Efetua baixa ate 3 meses---------------------------------
	public void consultaBoletosBaixa(CdPessoa e) throws Exception {
		// Cedente--------------------------
		String rota = "boletos";
		// Tipo filtro ordem
		String dini = DataUtil.dataPadraoSQL(DataUtil.addRemMeses(new Date(), 3, "R"));
		String dfim = DataUtil.dataPadraoSQL(DataUtil.addRemMeses(new Date(), 1, "A"));
		rota = rota + "?TituloDataVencimento=>=" + dini + "&TituloDataVencimento=<=" + dfim + "&limit=" + 450;
		HttpRequest results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
		String retorno = results.body();
		int status = results.code();
		// Se retorna OK
		if (status == 200) {
			TecnoRetornoListStatus rets = new Gson().fromJson(retorno, TecnoRetornoListStatus.class);
			for (TecnoRetornoDadosList es : rets.get_dados()) {
				TecnoBoleto b = new TecnoBoleto();
				b.setIdIntegracao(es.getIdIntegracao());
				b.setCedenteContaCodigoBanco(es.getCedenteCodigoBanco());
				b.setTituloNumeroDocumento(es.getTituloNumeroDocumento());
				b.setTituloDataEmissao(es.getTituloDataEmissao());
				b.setTituloDataVencimento(es.getTituloDataVencimento());
				b.setSacadoNome(es.getSacadoNome());
				b.setSacadoTelefone(es.getSacadoTelefone());
				b.setTituloValor(es.getTituloValor());
				b.setPagamentoData(es.getPagamentoData());
				b.setPagamentoValorTaxaCobranca(es.getPagamentoValorTaxaCobranca());
				b.setPagamentoValorPago(es.getPagamentoValorPago());
				b.setPagamentoValorAcrescimos(es.getPagamentoValorAcrescimos());
				b.setSituacao(es.getSituacao());
				b.setUrlBoleto(es.getUrlBoleto());
				b.setTituloLinhaDigitavel(es.getTituloLinhaDigitavel());
				b.setPagamentoRealizado(es.isPagamentoRealizado());
				// EFETUA BAIXA
				efetuaBaixaTitulos(b, e);
			}
		}
	}

	// Insere boleto(s) emails------------------------------
	public TecnoBoletoEmail enviarEmailBoletos(FnTitulo t) throws Exception {
		CdPessoa emp = t.getCdpessoaemp();
		CdPessoa para = t.getCdpessoapara();
		TecnoBoletoEmail b = new TecnoBoletoEmail();
		List<String> ids = new ArrayList<String>();
		ids.add(t.getTecno_idintegracao());
		b.setIdIntegracao(ids);
		b.setEmailNomeRemetente(nomeFant(emp));
		b.setEmailRemetente(endEmail(emp));
		b.setEmailResponderPara(endEmail(emp));
		b.setEmailAssunto(nomeFant(emp) + " | Envio de Boleto Bancário");
		b.setEmailMensagem(montaMensagem(t));
		List<String> dests = new ArrayList<String>();
		dests.add(endEmail(para));
		b.setEmailDestinatario(dests);
		b.setEmailAnexarBoleto(true);
		b.setEmailConteudoHtml(true);
		b.setTipoImpressao(prm.cliente().getSiscfg().getSis_tecno_tipo_imp());
		return b;
	}

	// Insere boleto(s) emails------------------------------
	public TecnoBoletoEmail enviarEmailBoletosPara(List<FnTitulo> titulos) throws Exception {
		CdPessoa emp = titulos.get(0).getCdpessoaemp();
		CdPessoa para = titulos.get(0).getCdpessoapara();
		List<String> ids = new ArrayList<String>();
		TecnoBoletoEmail b = new TecnoBoletoEmail();
		for (FnTitulo t : titulos) {
			ids.add(t.getTecno_idintegracao());
		}
		b.setIdIntegracao(ids);
		b.setEmailNomeRemetente(nomeFant(emp));
		b.setEmailRemetente(endEmail(emp));
		b.setEmailResponderPara(endEmail(emp));
		b.setEmailAssunto(nomeFant(emp) + " | Envio de Boleto Bancário");
		b.setEmailMensagem(montaMensagemPara(titulos));
		List<String> dests = new ArrayList<String>();
		dests.add(endEmail(para));
		b.setEmailDestinatario(dests);
		b.setEmailAnexarBoleto(true);
		b.setEmailConteudoHtml(true);
		b.setTipoImpressao("0");
		return b;
	}

	// BAIXA DE BOLETOS SE JA PAGOS
	public void efetuaBaixaTitulos(TecnoBoleto b, CdPessoa e) throws Exception {
		// Item para taxa e juros
		boolean sepJuros = prm.cliente().getSiscfg().isSis_separa_juros_plcon();
		if (b != null) {
			if (b.isPagamentoRealizado() == true) {
				// Pega titulo
				FnTitulo t = fnTituloRp.findByTecno_idintegracao(b.getIdIntegracao());
				if (t != null) {
					if (t.getVsaldo().compareTo(BigDecimal.ZERO) > 0) {
						// Converte retornos
						BigDecimal vBaixa = MoedaUtil.moedaStringBig(b.getPagamentoValorPago());
						BigDecimal vDesc = MoedaUtil.moedaStringBig(b.getPagamentoValorDesconto());
						BigDecimal vAcres = MoedaUtil.moedaStringBig(b.getPagamentoValorAcrescimos());
						BigDecimal valorJuroMulta = vAcres;
						BigDecimal vTaxa = MoedaUtil.moedaStringBig(b.getPagamentoValorTaxaCobranca());
						// Add dia a mais para baixa do banco no dia seguinte---
						Date pagoem = DataUtil.brToSql(b.getPagamentoData());
						pagoem = DataUtil.addRemDias(pagoem, 1, "A");
						GregorianCalendar gc = new GregorianCalendar();
						gc.setTime(pagoem);
						if (DataUtil.isSOuD(gc) == 7) {
							pagoem = DataUtil.addRemDias(pagoem, 2, "A");
						}
						Date taxaem = DataUtil.brToSql(b.getPagamentoDataTaxaBancaria());
						Time hora = new Time(new java.util.Date().getTime());
						Integer transacao = null;
						BigDecimal valorZero = BigDecimal.ZERO;
						// Baixa separando ou nao juros e multa
						if (sepJuros == true) {
							// Remove acrescimos para separar as taxas
							vBaixa = vBaixa.subtract(vAcres);
							fnTituloService.baixaUnico(vBaixa, vDesc, valorZero, vBaixa, pagoem,
									t.getCdcaixapref().getId(), t);
							valorJuroMulta = BigDecimal.ZERO;
						} else {
							fnTituloService.baixaUnico(vBaixa, vDesc, vAcres, t.getVtot(), pagoem,
									t.getCdcaixapref().getId(), t);
						}
						FnCxmv fnCxmv = fnCxmvService.regUnico(t.getCdpessoaemp(), t.getCdcaixapref(), t,
								t.getFpagpref(), pagoem, hora, t.getInfo(), t.getVparc(), valorZero, vDesc, valorZero,
								valorJuroMulta, vBaixa, transacao, "ATIVO", null, null);
						// SALVA PLANO DE CONTAS DRE -------------------------
						fnDreService.dreTituloLote(t, fnCxmv);
						// Registra taxa boleto houver-------------------------
						if (vTaxa.compareTo(BigDecimal.ZERO) > 0) {
							Integer idplanocom = 0;
							CdPlconMicro pmc = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("06", e.getId());// Taxa-boleto
							if (pmc != null) {
								idplanocom = pmc.getId();
							}
							String ref = "TAXA DE COBRANCA BOLETO";
							FnTitulo titulo = fnTituloService.regUnicoTitulo(e, "P", t.getCdpessoapara(),
									t.getCdcaixapref(), t.getFpagpref(), ref, taxaem, taxaem, taxaem, taxaem, "02",
									t.getLcdoc(), t.getNumdoc(), "", 0L, 0, 1, 1, vTaxa, valorZero, valorZero,
									valorZero, valorZero, valorZero, valorZero, vTaxa, vTaxa, valorZero, "", "N", "N",
									"N", valorZero, "N", "N", 0L, valorZero, valorZero, "S");
							// DREs
							fnDreService.dreFnTitulo100(titulo, idplanocom);
							// Centro de custos
							FnTituloCcusto cc = fnTituloCcustoRp.findByTitulo(t.getId());
							if (cc != null) {
								fnCcustoService.ccFnTitulo100(titulo, cc.getCdccusto().getId());
							}
							FnCxmv fnCxmvTaxa = fnCxmvService.regUnico(titulo.getCdpessoaemp(), titulo.getCdcaixapref(),
									titulo, titulo.getFpagpref(), taxaem, hora, titulo.getInfo(), titulo.getVparc(),
									titulo.getVparc(), valorZero, valorZero, valorZero, vTaxa, transacao, "ATIVO", null,
									null);
							// SALVA PLANO DE CONTAS DRE -------
							fnDreService.dreFnCxmv100(fnCxmvTaxa, idplanocom);
						}
						// Registra acrescimos boleto houver----------------------
						if (sepJuros == true) {
							if (vAcres.compareTo(BigDecimal.ZERO) > 0) {
								Integer idplanocom = 0;
								CdPlconMicro pmc = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("01", e.getId());// Juro-multa-boleto
								if (pmc != null) {
									idplanocom = pmc.getId();
								}
								String ref = "JUROS/MULTA BOLETO";
								FnTitulo titulo = fnTituloService.regUnicoTitulo(e, "R", t.getCdpessoapara(),
										t.getCdcaixapref(), t.getFpagpref(), ref, pagoem, pagoem, pagoem, pagoem, "02",
										t.getLcdoc(), t.getNumdoc(), "", 0L, 0, 1, 1, vAcres, valorZero, valorZero,
										valorZero, valorZero, valorZero, valorZero, vAcres, vAcres, valorZero, "", "N",
										"N", "N", valorZero, "N", "N", 0L, valorZero, valorZero, "S");
								// DREs
								fnDreService.dreFnTitulo100(titulo, idplanocom);
								// Centro de custos
								FnTituloCcusto cc = fnTituloCcustoRp.findByTitulo(t.getId());
								if (cc != null) {
									fnCcustoService.ccFnTitulo100(titulo, cc.getCdccusto().getId());
								}
								FnCxmv fnCxmvJM = fnCxmvService.regUnico(titulo.getCdpessoaemp(),
										titulo.getCdcaixapref(), titulo, titulo.getFpagpref(), pagoem, hora,
										titulo.getInfo(), titulo.getVparc(), titulo.getVparc(), valorZero, valorZero,
										valorZero, vAcres, transacao, "ATIVO", null, null);
								// SALVA PLANO DE CONTAS DRE ---------
								fnDreService.dreFnCxmv100(fnCxmvJM, idplanocom);
							}
						}
					}
				}
			}
		}
	}

	// Consulta protocolo impressao---------------------------
	public String[] consultaProtImpressao(String situacao, String protocolo, CdPessoa e) throws Exception {
		String rota = "boletos/impressao/lote/" + protocolo;
		HttpRequest results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
		String retorno = results.body();
		// Processa ate ter resposta PROCESSADO
		if (situacao.contains("PROCESSANDO") || retorno.contains("PROCESSANDO")) {
			results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
			retorno = results.body();
			Thread.sleep(4000);
		}
		String[] ret = new String[2];
		ret[0] = "PROCESSADO";
		ret[1] = "Impressao processada!";
		return ret;
	}

	// Consulta protocolo retorno---------------------------
	public String[] consultaProtRetorno(String situacao, String protocolo, CdPessoa e) throws Exception {
		String rota = "retornos/" + protocolo;
		HttpRequest results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
		String retorno = results.body();
		TecnoRetornoBoleto rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
		// Processa ate ter resposta PROCESSADO
		while (situacao.equals("PROCESSANDO")) {
			results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
			retorno = results.body();
			rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
			situacao = rets.get_dados().getSituacao();
			Thread.sleep(5000);
		}
		String[] ret = new String[2];
		ret[0] = rets.get_dados().getSituacao();
		ret[1] = rets.get_dados().getMensagem();
		return ret;
	}

	// Consulta protocolo para pedido baixa---------------------------
	public String[] consultaProtPedidoBaixa(String situacao, String protocolo, CdPessoa e) throws Exception {
		String rota = "boletos/baixa/lote/" + protocolo;
		HttpRequest results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
		String retorno = results.body();
		String nomeremessa = null;
		String remessa = null;
		TecnoRetornoBoleto rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
		// Processa ate ter resposta PROCESSADO
		while (situacao.equals("PROCESSANDO")) {
			results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
			retorno = results.body();
			rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
			situacao = rets.get_dados().getSituacao();
			if (rets.get_dados().get_sucesso() != null) {
				nomeremessa = rets.get_dados().get_sucesso().get(0).getArquivo();
			}
			if (nomeremessa == null) {
				situacao = "PROCESSANDO";
			} else {
				situacao = "PROCESSADO";
			}
			Thread.sleep(5000);
		}
		if (rets.get_dados().get_sucesso().get(0).isTransmissaoAutomatica() == false) {
			nomeremessa = rets.get_dados().get_sucesso().get(0).getArquivo();
			remessa = cryptPassUtil.dBase64(rets.get_dados().get_sucesso().get(0).getRemessa());
		}
		String[] ret = new String[4];
		ret[0] = situacao;
		ret[1] = rets.get_dados().getMensagem();
		ret[2] = nomeremessa;
		ret[3] = remessa;
		return ret;
	}

	// Consulta protocolo para alteracoes------------------------------
	public String[] consultaProtAltera(String situacao, String protocolo, CdPessoa e) throws Exception {
		String rota = "boletos/altera/lote/" + protocolo;
		HttpRequest results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
		String retorno = results.body();
		System.out.println(retorno);
		String nomeremessa = null;
		String remessa = null;
		TecnoRetornoBoleto rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
		// Processa ate ter resposta PROCESSADO
		while (situacao.equals("PROCESSANDO")) {
			results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
			retorno = results.body();
			rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
			situacao = rets.get_dados().getSituacao();
			if (rets.get_dados().get_sucesso() != null) {
				nomeremessa = rets.get_dados().get_sucesso().get(0).getArquivo();
			}
			if (nomeremessa == null) {
				situacao = "PROCESSANDO";
			} else {
				situacao = "PROCESSADO";
			}
			Thread.sleep(5000);
		}
		if (rets.get_dados().get_sucesso().get(0).isTransmissaoAutomatica() == false) {
			nomeremessa = rets.get_dados().get_sucesso().get(0).getArquivo();
			remessa = cryptPassUtil.dBase64(rets.get_dados().get_sucesso().get(0).getRemessa());
		}
		String[] ret = new String[4];
		ret[0] = situacao;
		ret[1] = rets.get_dados().getMensagem();
		ret[2] = nomeremessa;
		ret[3] = remessa;
		return ret;
	}

	@SuppressWarnings("unused")
	private TecnoBoleto consultaBoletosIdIntegracao(CdPessoa e, String idIntegracao) throws Exception {
		TecnoBoleto b = new TecnoBoleto();
		String rota = "boletos?IdIntegracao=" + idIntegracao;
		HttpRequest results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
		String retorno = results.body();
		int status = results.code();
		// Se retorna OK
		if (status == 200) {
			TecnoRetornoListStatus rets = new Gson().fromJson(retorno, TecnoRetornoListStatus.class);
			TecnoRetornoDadosList es = rets.get_dados().get(0);
			b.setIdIntegracao(es.getIdIntegracao());
			b.setCedenteContaCodigoBanco(es.getCedenteCodigoBanco());
			b.setTituloNumeroDocumento(es.getTituloNumeroDocumento());
			b.setTituloDataEmissao(es.getTituloDataEmissao());
			b.setTituloDataVencimento(es.getTituloDataVencimento());
			b.setSacadoNome(es.getSacadoNome());
			b.setSacadoTelefone(es.getSacadoTelefone());
			b.setTituloValor(es.getTituloValor());
			b.setPagamentoData(es.getPagamentoData());
			b.setPagamentoValorTaxaCobranca(es.getPagamentoValorTaxaCobranca());
			b.setPagamentoValorPago(es.getPagamentoValorPago());
			b.setPagamentoValorAcrescimos(es.getPagamentoValorAcrescimos());
			b.setSituacao(es.getSituacao());
			b.setUrlBoleto(es.getUrlBoleto());
			b.setTituloLinhaDigitavel(es.getTituloLinhaDigitavel());
			b.setPagamentoRealizado(es.isPagamentoRealizado());
		}
		return b;
	}

	// Controle do numero do documento----------------------
	private String numDocNota(FnTitulo t) {
		String retorno = "SD";
		// Documento
		if (t.getNumdoc() > 0) {
			retorno = t.getNumdoc() + "/" + t.getParcnum();
		}
		// Nota
		if (t.getNumnota() > 0) {
			String tiponota = FsTipoNomeUtil.nomeModelo(t.getTpdocfiscal());
			tiponota = tiponota.substring(0, 1) + ".";
			retorno = tiponota + "" + t.getNumnota() + "/" + t.getParcnum();
		}
		return retorno;
	}

	// Controle de telefone----------------------------------
	private String numFone(CdPessoa p) {
		String retorno = p.getFoneum();
		if (p.getFonefin() != null) {
			if (!p.getFonefin().equals("")) {
				retorno = p.getFonefin();
			}
		}
		return retorno;
	}

	// Controle de email-------------------------------------
	private String endEmail(CdPessoa p) {
		String retorno = p.getEmail();
		if (p.getEmailfin() != null) {
			if (!p.getEmailfin().equals("")) {
				retorno = p.getEmailfin();
			}
		}
		return retorno;
	}

	// Controle de pais-------------------------------------
	private String nomePais(CdPessoa p) {
		String retorno = "BRASIL";
		if (p.getCodpais() != null) {
			if (p.getCodpais().equals("1058")) {
				retorno = "BRASIL";
			} else {
				retorno = "EXTERIOR";
			}
		}
		return retorno;
	}

	// Controle de nome e fantasia---------------------------
	private String nomeFant(CdPessoa p) {
		String retorno = p.getNome();
		if (p.getFantasia() != null) {
			if (!p.getFantasia().equals("")) {
				retorno = p.getFantasia();
			}
		}
		return retorno;
	}

	private String montaMensagem(FnTitulo t) throws Exception {
		CdPessoa emit = t.getCdpessoaemp();
		String urlbol = mc.linkTecnoHom;
		if (prm.cliente().getSiscfg().getSis_amb_boleto().equals("1")) {
			urlbol = mc.linkTecnoProd;
		}
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append(
				"<i><font style='font-size:9px;color:#347deb'><b>Boleto via e-mail gerado por TecnoSpeed! Dúvidas na autenticidade, entre em contato com emitente!</b></font></i>");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append(emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj()));
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		if (t.getNumdoc() == 0) {
			s.append("Você está recebendo em anexo o(s) boleto(s) em PDF neste e-mail. ");
		}
		if (t.getNumdoc() > 0) {
			s.append("Você está recebendo em anexo o(s) boleto(s) em PDF do documento número <b> "
					+ LcDocTipoNomeUtil.nomeTipoDoc(t.getTpdoc()) + " Núm.: " + t.getNumdoc() + "</b> neste e-mail. ");
		}
		if (t.getNumnota() > 0) {
			s.append("Você está recebendo em anexo o(s) boleto(s) em PDF da <b>"
					+ FsTipoNomeUtil.nomeModelo(t.getTpdocfiscal()) + " Núm.: " + t.getNumnota()
					+ "</b> neste e-mail. ");
		}
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Emitido por:</b> " + emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj())
				+ "<br />");
		if (t.getNumdoc() == 0) {
			s.append("<b>Número da nota:</b> Verifique com emitente! <br />");
		}
		if (t.getNumdoc() > 0) {
			s.append("<b>Número documento:</b> " + t.getNumdoc() + "<br />");
		}
		if (t.getNumnota() > 0) {
			s.append("<b>Número da nota:</b> " + t.getNumnota() + "<br />");
		}
		s.append("<b>Emitido em:</b> " + DataUtil.dataPadraoBr(t.getDatacad()) + "<br />");
		s.append("<b>Vencimento em:</b> " + DataUtil.dataPadraoBr(t.getVence()) + "<br />");
		s.append("<b>Valor R$:</b> " + MoedaUtil.moedaPadrao(t.getVsaldo()) + "<br />");
		s.append("<b>Link do boleto:</b> " + urlbol + "boletos/impressao/" + t.getTecno_idintegracao() + "<br />");
		s.append("<b>Consulta autenticidade por:</b> " + endEmail(emit) + " <br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(emit.getNome() + "<br />");
		s.append(emit.getCdcidade().getNome() + "/" + emit.getCdestado().getUf() + "<br />");
		s.append(emit.getCep() + "<br />");
		s.append("<br />");
		s.append("<br />");
		return s.toString();
	}

	private String montaMensagemPara(List<FnTitulo> titulos) throws Exception {
		CdPessoa emit = titulos.get(0).getCdpessoaemp();
		String urlbol = mc.linkTecnoHom;
		if (prm.cliente().getSiscfg().getSis_amb_boleto().equals("1")) {
			urlbol = mc.linkTecnoProd;
		}
		StringBuilder s = new StringBuilder();
		// Montagem email
		s.append(
				"<i><font style='font-size:9px;color:#347deb'><b>Boleto via e-mail gerado por TecnoSpeed! Dúvidas na autenticidade, entre em contato com emitente!</b></font></i>");
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append(emit.getNome() + " | CNPJ: " + CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj()));
		s.append("<br />");
		s.append("<br />");
		s.append("<br />");
		s.append("Você está recebendo em anexo o(s) boleto(s) em PDF neste e-mail. ");
		s.append("<br />");
		s.append("<br />");
		s.append("___________________");
		s.append("<br />");
		s.append("<br />");
		for (FnTitulo t : titulos) {
			s.append("<b>Emitido por:</b> " + emit.getNome() + " | CNPJ: "
					+ CaracterUtil.formataCPFCNPJ(emit.getCpfcnpj()) + "<br />");
			if (t.getNumdoc() == 0) {
				s.append("<b>Número da nota:</b> Verifique com emitente! <br />");
			}
			if (t.getNumdoc() > 0) {
				s.append("<b>Número documento:</b> " + t.getNumdoc() + "<br />");
			}
			if (t.getNumnota() > 0) {
				s.append("<b>Número da nota:</b> " + t.getNumnota() + "<br />");
			}
			s.append("<b>Emitido em:</b> " + DataUtil.dataPadraoBr(t.getDatacad()) + "<br />");
			s.append("<b>Vencimento em:</b> " + DataUtil.dataPadraoBr(t.getVence()) + "<br />");
			s.append("<b>Link do boleto:</b> " + urlbol + "boletos/impressao/" + t.getTecno_idintegracao() + "<br />");
			s.append("<b>Consulta autenticidade por:</b> " + endEmail(emit) + " <br />");
			s.append("<br />");
			s.append("___________________");
			s.append("<br />");
			s.append("<br />");
		}
		s.append("<br />");
		s.append("<br />");
		s.append("<b>Não responder esta mensagem.</b>");
		s.append("<br />");
		s.append("<br />");
		s.append(emit.getNome() + "<br />");
		s.append(emit.getCdcidade().getNome() + "/" + emit.getCdestado().getUf() + "<br />");
		s.append(emit.getCep() + "<br />");
		s.append("<br />");
		s.append("<br />");
		return s.toString();
	}

	public String nomeRemessa(String nomeatual, TecnoBoleto t) {
		String retorno = nomeatual;
		// Sicredi------------------------
		if (t.getCedenteContaCodigoBanco().equals("748")) {
			String codBen = t.getCedenteContaNumero();
			String cMes = codigoMesSicredi();
			String diahoje = Calendar.getInstance().get(Calendar.DATE) + "";
			if (diahoje.length() == 1) {
				diahoje = "0" + diahoje;
			}
			String extensao = "CRM";
			retorno = codBen + "" + cMes + "" + diahoje + "." + extensao;
		}
		return retorno;
	}

	private String codigoMesSicredi() {
		String retorno = "";
		Integer mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (mes == 10) {
			retorno = "O";
		}
		if (mes == 11) {
			retorno = "N";
		}
		if (mes == 12) {
			retorno = "D";
		}
		if (mes <= 9) {
			retorno = mes + "";
		}
		return retorno;
	}
}
