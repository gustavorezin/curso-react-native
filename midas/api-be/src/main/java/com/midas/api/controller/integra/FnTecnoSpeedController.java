package com.midas.api.controller.integra;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.midas.api.constant.MidasConfig;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.AuxDados;
import com.midas.api.tenant.entity.CdBolcfg;
import com.midas.api.tenant.entity.CdEmail;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.tecno.TecnoBoleto;
import com.midas.api.tenant.entity.tecno.TecnoBoletoAlterar;
import com.midas.api.tenant.entity.tecno.TecnoBoletoAlterarBoletos;
import com.midas.api.tenant.entity.tecno.TecnoBoletoEmail;
import com.midas.api.tenant.entity.tecno.TecnoBoletoImpressao;
import com.midas.api.tenant.entity.tecno.TecnoCadUtil;
import com.midas.api.tenant.entity.tecno.TecnoRemessa;
import com.midas.api.tenant.entity.tecno.TecnoRetornoBoleto;
import com.midas.api.tenant.entity.tecno.TecnoRetornoBoletoDados;
import com.midas.api.tenant.entity.tecno.TecnoRetornoDadosList;
import com.midas.api.tenant.entity.tecno.TecnoRetornoListStatus;
import com.midas.api.tenant.entity.tecno.TecnoRetornoRemessa;
import com.midas.api.tenant.entity.tecno.TecnoRetornoRemessaList;
import com.midas.api.tenant.repository.CdBolcfgRepository;
import com.midas.api.tenant.repository.CdEmailRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.repository.LcDocRepository;
import com.midas.api.tenant.service.integra.TecnoBoletoService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.CryptPassUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.HttpRequest;
import com.midas.api.util.LerArqUtil;
import com.midas.api.util.integra.TecnoUtil;

@RestController
@RequestMapping("/private/tecno")
public class FnTecnoSpeedController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdBolcfgRepository cdBolcfgRp;
	@Autowired
	private CdEmailRepository cdEmailRp;
	@Autowired
	private TecnoBoletoService tecnoBoletoService;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private TecnoCadUtil tecnoCadUtil;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private CryptPassUtil cryptPassUtil;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private LcDocRepository lcDocRp;
	// INTEGRACAO TECNOSPEED****************************************

	// CEDENTE-------------------------------------------------------------------
	@RequestAuthorization
	@GetMapping("/cedentes/{id}")
	public ResponseEntity<AuxDados> getCedente(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 76, "Y", "ID CADASTRO CEDENTE " + id)) {
			AuxDados a = new AuxDados();
			CdPessoa e = cdPessoaRp.findById(id).get();
			a = tecnoCadUtil.getCedente(e, mc, prm);
			return new ResponseEntity<AuxDados>(a, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cedentes/{id}")
	public ResponseEntity<List<AuxDados>> configurarCedente(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 76, "A", "CONFIGURAR CEDENTE " + id)) {
			List<AuxDados> aux = new ArrayList<AuxDados>();
			CdPessoa e = cdPessoaRp.findById(id).get();
			aux = tecnoCadUtil.configurarCedente(e, mc, prm);
			// Se teve sucesso------
			if (aux.get(0).getCampo1().equals("200")) {
				String idtecno = aux.get(0).getCampo3();
				String tokentecno = aux.get(0).getCampo4();
				cdPessoaRp.updateClienteIdTecno(idtecno, tokentecno, e.getId());
			}
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/email/configurar/{em}")
	public ResponseEntity<List<AuxDados>> configurarEmail(@PathVariable("em") Integer em) {
		if (ca.hasAuth(prm, 76, "A", "CONFIGURAR E-EMAIL ENVIO BOLETO")) {
			List<AuxDados> aux = new ArrayList<AuxDados>();
			CdEmail email = cdEmailRp.findById(em).get();
			CdPessoa e = email.getCdpessoaemp();
			aux = tecnoCadUtil.configurarEmail(e, mc, prm, email);
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// BOLETOS-INCLUSAO-CONSULTA-GERACAO-------------------------------------
	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/boletos/{id}/bolcfg/{bolcfg}/auxmsg/{auxmsg}/iddoc/{iddoc}")
	public ResponseEntity<List<AuxDados>> incluirBoletos(@RequestBody List<FnTitulo> fnTitulos,
			@PathVariable("id") Long id, @PathVariable("bolcfg") Integer bolcfg,
			@PathVariable("auxmsg") List<String> auxmsg, @PathVariable("iddoc") Long iddoc) throws Exception {
		if (ca.hasAuth(prm, 76, "C", "INCLUSAO DE BOLETOS PARA EMISSAO")) {
			List<AuxDados> aux = new ArrayList<AuxDados>();
			// Cedente--------------------------
			CdPessoa e = cdPessoaRp.findById(id).get();
			// Conta---------------------------
			CdBolcfg bc = cdBolcfgRp.findById(bolcfg).get();
			// Ajusta obejtos
			List<TecnoBoleto> tecnoBoleto = tecnoBoletoService.incluirBoletos(fnTitulos, bc, auxmsg);
			// Converte objeto para json
			String json = LerArqUtil.setJsonString(tecnoBoleto);
			json = json.replace("cedente", "Cedente");
			String rota = "boletos/lote";
			HttpRequest results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
			String retorno = results.body();
			int status = results.code();
			// Se retorna OK
			if (status == 200) {
				TecnoRetornoBoleto rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
				String statusRet = "200";
				int contid = 0;
				for (FnTitulo t : fnTitulos) {
					CdPessoa para = t.getCdpessoapara();
					if (!para.getCpfcnpj().equals("00000000000") && !para.getCpfcnpj().equals("00000000000000")) {
						if (t.getTipo().equals("R")) {
							// Grava ID da integração --------
							String identegracao = rets.get_dados().get_sucesso().get(contid).getIdintegracao();
							fnTituloRp.updateTecnoIdIntegracao(identegracao, t.getId());
							contid++;
						}
					}
				}
				// Monta resposta
				AuxDados a = new AuxDados();
				if (rets.get_dados().get_sucesso().size() > 0) {
					a.setCampo1(statusRet);
					a.setCampo2("Boleto(s) incluído(s) com sucesso!");
					aux.add(a);
					// Atualiza documento, se houver
					if (iddoc > 0) {
						lcDocRp.updateDocBoleto("S", iddoc);
					}
				}
				// Monta resposta de erros, se houver
				if (rets.get_dados().get_falha().size() > 0) {
					statusRet = rets.get_dados().get_falha().get(0).get_status_http();
					a.setCampo1(statusRet);
					a.setCampo2(rets.get_dados().get_falha().get(0).get_erro().getErros().getBoleto());
					aux.add(a);
				}
			}
			if (status != 200) {
				// Monta resposta principal
				AuxDados ap = new AuxDados();
				ap.setCampo1(status + "");
				ap.setCampo2("Problemas ao incluir boleto(s)! Consulte suporte técnico!");
				aux.add(ap);
			}
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/boletos/{id}/sort/{sort}/situacao/{situacao}/nome/{nome}/ipp/{itenspp}/dti/{dataini}/dtf/{datafim}/ove/{ove}")
	public ResponseEntity<List<TecnoBoleto>> listarBoletos(@PathVariable("id") Long id,
			@PathVariable("sort") String sort, @PathVariable("situacao") String situacao,
			@PathVariable("nome") String nome, @PathVariable("itenspp") int itenspp,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("ove") String ove) throws Exception {
		if (ca.hasAuth(prm, 76, "L", "LISTAGEM DE BOLETOS")) {
			List<TecnoBoleto> bols = new ArrayList<TecnoBoleto>();
			// Cedente--------------------------
			CdPessoa e = cdPessoaRp.findById(id).get();
			nome = CaracterUtil.buscaContexto(nome);
			String rota = "boletos?limit=" + itenspp + "&skip=0&sort=" + sort;
			// Verifica situacao----------------
			if (!situacao.equals("X")) {
				rota = "boletos?limit=" + itenspp + "&skip=0&sort=" + sort + "&situacao=" + situacao;
			}
			// Verifica busca sacado------------
			if (!nome.equals("")) {
				rota = rota + "&SacadoNome=$" + nome;
			}
			// Tipo filtro ordem
			String dini = DataUtil.dataPadraoBrContra(DataUtil.dUtil(dataini));
			String dfim = DataUtil.dataPadraoBrContra(DataUtil.dUtil(datafim));
			if (ove.equals("E")) {
				rota = rota + "&TituloDataEmissao=>=" + dini + "&TituloDataEmissao=<=" + dfim;
			} else {
				rota = rota + "&TituloDataVencimento=>=" + dini + "&TituloDataVencimento=<=" + dfim;
			}
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
					b.setCedenteContaNumero(es.getCedenteConta());
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
					// Ocorrencias
					List<TecnoRetornoBoletoDados> ocs = new ArrayList<TecnoRetornoBoletoDados>();
					for (TecnoRetornoBoletoDados d : es.getTituloOcorrencias()) {
						TecnoRetornoBoletoDados rd = new TecnoRetornoBoletoDados();
						rd.setCodigo(d.getCodigo());
						rd.setIdintegracao(d.getIdintegracao());
						rd.setMensagem(d.getMensagem());
						rd.setCriado(d.getCriado());
						rd.setAtualizado(d.getAtualizado());
						ocs.add(rd);
					}
					b.setTituloOcorrencias(ocs);
					bols.add(b);
				}
			}
			// Efetua baixa
			tecnoBoletoService.consultaBoletosBaixa(e);
			return new ResponseEntity<List<TecnoBoleto>>(bols, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// REMESSA E RETORNO--------------------------
	@RequestAuthorization
	@GetMapping("/remessas/{id}/ipp/{itenspp}/dti/{dataini}/dtf/{datafim}")
	public ResponseEntity<List<TecnoRemessa>> listarRemessas(@PathVariable("id") Long id,
			@PathVariable("itenspp") int itenspp, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim) throws Exception {
		if (ca.hasAuth(prm, 76, "L", "LISTAGEM DE REMESSAS")) {
			List<TecnoRemessa> rems = new ArrayList<TecnoRemessa>();
			// Cedente--------------------------
			CdPessoa e = cdPessoaRp.findById(id).get();
			String rota = "remessas/lote?limit=" + itenspp;
			// Tipo filtro ordem
			String dini = DataUtil.dataPadraoBr(DataUtil.dUtil(dataini));
			String dfim = DataUtil.dataPadraoBr(DataUtil.dUtil(datafim));
			rota = rota + "&dataInicial=" + dini + "&dataFinal=" + dfim;
			HttpRequest results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
			String retorno = results.body();
			int status = results.code();
			// Se retorna OK
			if (status == 200) {
				TecnoRetornoRemessa rets = new Gson().fromJson(retorno, TecnoRetornoRemessa.class);
				for (TecnoRetornoRemessaList es : rets.get_dados().get_sucesso()) {
					// Pega link e seu retorno para Decodificar
					String urlString = es.getRemessa();
					URL url = new URL(urlString);
					URLConnection conn = url.openConnection();
					InputStream is = conn.getInputStream();
					String remessa = LerArqUtil.retIS(is);
					TecnoRemessa r = new TecnoRemessa();
					r.setArquivo(es.getArquivo());
					r.setCriado(es.getCriado());
					r.setMotivo(es.getMotivo());
					r.setRemessa(cryptPassUtil.dBase64(remessa));
					r.setSituacao(es.getSituacao());
					r.setVanPendente(es.isVanPendente());
					rems.add(r);
				}
			}
			return new ResponseEntity<List<TecnoRemessa>>(rems, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/boletos/{id}/remessa")
	public ResponseEntity<List<AuxDados>> remessaBoletos(@RequestBody List<TecnoBoleto> boletos,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 76, "N", "GERACAO DE REMESSAS")) {
			List<AuxDados> aux = new ArrayList<AuxDados>();
			// Cedente--------------------------
			CdPessoa e = cdPessoaRp.findById(id).get();
			// Converte em lista simples
			List<String> bols = new ArrayList<String>();
			for (TecnoBoleto t : boletos) {
				bols.add(t.getIdIntegracao());
			}
			// Converte objeto para json
			String json = LerArqUtil.setJsonString(bols);
			String rota = "remessas/lote";
			HttpRequest results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
			String retorno = results.body();
			int status = results.code();
			// Se retorna OK
			if (status == 200) {
				TecnoRetornoBoleto rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
				// Retorno da remessa se nao for automatica
				String nomeremessa = "";
				String remessa = "";
				if (rets.get_dados().get_sucesso().get(0).isTransmissaoAutomatica() == false) {
					nomeremessa = rets.get_dados().get_sucesso().get(0).getArquivo();
					remessa = cryptPassUtil.dBase64(rets.get_dados().get_sucesso().get(0).getRemessa());
				}
				nomeremessa = tecnoBoletoService.nomeRemessa(nomeremessa, boletos.get(0));
				String statusRet = "200";
				// Monta resposta
				AuxDados a = new AuxDados();
				if (rets.get_dados().get_sucesso().size() > 0) {
					a.setCampo1(statusRet);
					a.setCampo2("Boleto(s) enviado(s) com sucesso!");
					a.setCampo3(nomeremessa);
					a.setCampo4(remessa);
					aux.add(a);
				}
				// Monta resposta de erros, se houver
				if (rets.get_dados().get_falha().size() > 0) {
					statusRet = rets.get_dados().get_falha().get(0).get_status_http();
					a.setCampo1(statusRet);
					a.setCampo2(rets.get_dados().get_falha().get(0).get_erro().getErros().getBoleto());
					aux.add(a);
				}
			}
			if (status != 200) {
				// Monta resposta principal
				AuxDados ap = new AuxDados();
				ap.setCampo1(status + "");
				ap.setCampo2("Problemas ao enviar boleto(s)! Consulte suporte técnico!");
				aux.add(ap);
			}
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/boletos/{id}/pedidobaixa")
	public ResponseEntity<List<AuxDados>> pedidoBaixaBoletos(@RequestBody List<TecnoBoleto> boletos,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 76, "N", "GERACAO DE PEDIDO DE BAIXA COM REMESSA")) {
			List<AuxDados> aux = new ArrayList<AuxDados>();
			// Cedente--------------------------
			CdPessoa e = cdPessoaRp.findById(id).get();
			// Converte em lista simples
			List<String> bols = new ArrayList<String>();
			for (TecnoBoleto t : boletos) {
				bols.add(t.getIdIntegracao());
			}
			// Converte objeto para json
			String json = LerArqUtil.setJsonString(bols);
			String rota = "boletos/baixa/lote";
			HttpRequest results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
			String retorno = results.body();
			int status = results.code();
			// Se retorna OK
			if (status == 200) {
				TecnoRetornoBoleto rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
				String statusRet = "200";
				String situacao = rets.get_dados().getSituacao();
				String protocolo = rets.get_dados().getProtocolo();
				String reProt[] = tecnoBoletoService.consultaProtPedidoBaixa(situacao, protocolo, e);
				if (reProt[0].equals("PROCESSADO")) {
					// Monta resposta principal
					AuxDados ap = new AuxDados();
					ap.setCampo1(statusRet);
					ap.setCampo2("Pedido efetuado com sucesso!");
					ap.setCampo3(reProt[2]);
					ap.setCampo4(reProt[3]);
					aux.add(ap);
				} else {
					statusRet = rets.get_dados().get_falha().get(0).get_status_http();
					// Monta resposta principal
					AuxDados ap = new AuxDados();
					ap.setCampo1(statusRet);
					ap.setCampo2(reProt[1] + "!");
					aux.add(ap);
				}
			}
			if (status != 200) {
				// Monta resposta principal
				AuxDados ap = new AuxDados();
				ap.setCampo1(status + "");
				ap.setCampo2("Problemas ao efetuar o pedido! Consulte suporte técnico!");
				aux.add(ap);
			}
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/boletos/{id}/alteravencimento")
	public ResponseEntity<List<AuxDados>> alteraVencimentoBoletos(@RequestBody List<TecnoBoleto> boletos,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 76, "A", "ALTERACAO DE VENCIMENTOS COM REMESSA")) {
			List<AuxDados> aux = new ArrayList<AuxDados>();
			// Cedente--------------------------
			CdPessoa e = cdPessoaRp.findById(id).get();
			// Converte em lista simples
			TecnoBoletoAlterar b = new TecnoBoletoAlterar();
			b.setTipo("0");
			List<TecnoBoletoAlterarBoletos> bols = new ArrayList<TecnoBoletoAlterarBoletos>();
			for (TecnoBoleto bol : boletos) {
				// Pega titulo
				FnTitulo t = fnTituloRp.findByTecno_idintegracao(bol.getIdIntegracao());
				if (t != null) {
					String bv = bol.getTituloDataVencimento().replace(" 00:00:00", "");
					String tv = DataUtil.dataPadraoBr(t.getVence());
					if (!bv.equals(tv)) {
						TecnoBoletoAlterarBoletos item = new TecnoBoletoAlterarBoletos();
						item.setIdIntegracao(t.getTecno_idintegracao());
						item.setTituloDataVencimento(DataUtil.dataPadraoBr(t.getVence()));
						bols.add(item);
					}
				}
			}
			b.setBoletos(bols);
			// Converte objeto para json
			String json = LerArqUtil.setJsonString(b);
			String rota = "boletos/altera/lote";
			HttpRequest results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
			String retorno = results.body();
			int status = results.code();
			// Se retorna OK
			if (status == 200) {
				TecnoRetornoBoleto rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
				String statusRet = "200";
				String situacao = rets.get_dados().getSituacao();
				String protocolo = rets.get_dados().getProtocolo();
				String reProt[] = tecnoBoletoService.consultaProtAltera(situacao, protocolo, e);
				if (reProt[0].equals("PROCESSADO")) {
					// Monta resposta principal
					AuxDados ap = new AuxDados();
					ap.setCampo1(statusRet);
					ap.setCampo2("Alteração efetuada com sucesso!");
					ap.setCampo3(reProt[2]);
					ap.setCampo4(reProt[3]);
					aux.add(ap);
				} else {
					statusRet = rets.get_dados().get_falha().get(0).get_status_http();
					// Monta resposta principal
					AuxDados ap = new AuxDados();
					ap.setCampo1(statusRet);
					ap.setCampo2(reProt[1] + "!");
					aux.add(ap);
				}
			}
			if (status != 200) {
				// Monta resposta principal
				AuxDados ap = new AuxDados();
				ap.setCampo1(status + "");
				ap.setCampo2("Problemas ao efetuar alteração! Consulte suporte técnico!");
				aux.add(ap);
			}
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/boletos/{id}/retorno")
	public ResponseEntity<List<AuxDados>> retornoBoletos(@PathVariable("id") Long id,
			@RequestParam("file") MultipartFile file) throws Exception {
		if (ca.hasAuth(prm, 76, "A", "ENVIO DE RETORNO")) {
			List<AuxDados> aux = new ArrayList<AuxDados>();
			File retornoBanco = LerArqUtil.multipartToFile(file, file.getOriginalFilename());
			String conteudo = FileUtils.readFileToString(retornoBanco);
			// Converte base 64
			conteudo = cryptPassUtil.cBase64(conteudo);
			conteudo = "{\"arquivo\":\"" + conteudo + "\"}";
			// Cedente--------------------------
			CdPessoa e = cdPessoaRp.findById(id).get();
			// Converte em lista simples
			String rota = "retornos";
			HttpRequest results = TecnoUtil.httpResquestPost(mc, prm, rota, conteudo, e);
			String retorno = results.body();
			int status = results.code();
			// Se retorna OK
			if (status == 200) {
				TecnoRetornoBoleto rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
				String situacao = rets.get_dados().getSituacao();
				String protocolo = rets.get_dados().getProtocolo();
				String reProt[] = tecnoBoletoService.consultaProtRetorno(situacao, protocolo, e);
				if (reProt[0].equals("PROCESSADO")) {
					String retStatus = "200";
					// Monta resposta principal
					AuxDados ap = new AuxDados();
					ap.setCampo1(retStatus);
					ap.setCampo2("Arquivo de retorno processado com sucesso!");
					aux.add(ap);
				} else {
					String retStatus = "400";
					// Monta resposta principal
					AuxDados ap = new AuxDados();
					ap.setCampo1(retStatus);
					ap.setCampo2(reProt[1] + "!");
					aux.add(ap);
				}
			}
			if (status != 200) {
				// Monta resposta principal
				AuxDados ap = new AuxDados();
				ap.setCampo1(status + "");
				ap.setCampo2("Problemas ao processar arquivo! Consulte suporte técnico!");
				aux.add(ap);
			}
			// Efetua baixa
			tecnoBoletoService.consultaBoletosBaixa(e);
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/impressao/{id}")
	public ResponseEntity<List<AuxDados>> impressaoBoletos(@RequestBody List<FnTitulo> fnTitulos,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 76, "I", "IMPRESSAO BOLETOS")) {
			List<AuxDados> aux = new ArrayList<AuxDados>();
			// Cedente--------------------------
			CdPessoa e = cdPessoaRp.findById(id).get();
			// Ajusta objetos
			TecnoBoletoImpressao tecnoBoleto = new TecnoBoletoImpressao();
			tecnoBoleto.setTipoImpressao(prm.cliente().getSiscfg().getSis_tecno_tipo_imp());
			List<String> bols = new ArrayList<String>();
			for (FnTitulo t : fnTitulos) {
				if (t.getTipo().equals("R")) {
					if (t.getTecno_idintegracao() != null) {
						String b = new String();
						b = t.getTecno_idintegracao();
						bols.add(b);
					}
				}
			}
			tecnoBoleto.setBoletos(bols);
			// Converte objeto para json
			String json = LerArqUtil.setJsonString(tecnoBoleto);
			// Verifica ambiente----------------
			String url = mc.linkTecnoProd;
			if (prm.cliente().getSiscfg().getSis_amb_boleto().equals("2")) {
				url = mc.linkTecnoHom;
			}
			String rota = "boletos/impressao/lote";
			HttpRequest results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
			String retorno = results.body();
			int status = results.code();
			// Se retorna OK
			if (status == 200) {
				TecnoRetornoBoleto rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
				String situacao = rets.get_dados().getSituacao();
				String protocolo = rets.get_dados().getProtocolo();
				String reProt[] = tecnoBoletoService.consultaProtImpressao(situacao, protocolo, e);
				if (reProt[0].equals("PROCESSADO")) {
					String retStatus = "200";
					url = url + "" + rota + "/" + protocolo;
					// Monta resposta principal
					AuxDados ap = new AuxDados();
					ap.setCampo1(retStatus);
					ap.setCampo2("Impressão realizada com sucesso!");
					ap.setCampo3(url);
					aux.add(ap);
				} else {
					String retStatus = "400";
					// Monta resposta principal
					AuxDados ap = new AuxDados();
					ap.setCampo1(retStatus);
					ap.setCampo2(reProt[1] + "!");
					aux.add(ap);
				}
			}
			if (status != 200) {
				// Monta resposta principal
				AuxDados ap = new AuxDados();
				ap.setCampo1(status + "");
				ap.setCampo2("Problemas ao imprimir boleto(s)! Consulte suporte técnico!");
				aux.add(ap);
			}
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/email/{id}")
	public ResponseEntity<List<AuxDados>> enviarEmailBoletos(@RequestBody List<FnTitulo> fnTitulos,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 76, "E", "ENVIO DE E-MAIL BOLETOS DESTINATARIOS")) {
			List<AuxDados> aux = new ArrayList<AuxDados>();
			// Cedente--------------------------
			CdPessoa e = cdPessoaRp.findById(id).get();
			for (FnTitulo t : fnTitulos) {
				// Ajusta obejtos
				TecnoBoletoEmail tecnoBoleto = tecnoBoletoService.enviarEmailBoletos(t);
				// Converte objeto para json
				String json = LerArqUtil.setJsonString(tecnoBoleto);
				String rota = "email/lote";
				HttpRequest results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
				int status = results.code();
				// Se retorna OK
				if (status == 200) {
					AuxDados a = new AuxDados();
					a.setCampo1(status + "");
					a.setCampo2("Boleto(s) enviado(s) com sucesso!");
					aux.add(a);
				}
				if (status != 200) {
					// Monta resposta principal
					AuxDados ap = new AuxDados();
					ap.setCampo1(status + "");
					ap.setCampo2("Problemas ao enviar boleto(s)! Consulte suporte técnico!");
					aux.add(ap);
				}
			}
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/email/{id}/para")
	public ResponseEntity<List<AuxDados>> enviarEmailBoletosPara(@RequestBody List<FnTitulo> fnTitulos,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 76, "E", "ENVIO DE E-MAIL BOLETOS DESTINATARIO UNICO")) {
			List<AuxDados> aux = new ArrayList<AuxDados>();
			// Cedente--------------------------
			CdPessoa e = cdPessoaRp.findById(id).get();
			// Ajusta obejtos
			TecnoBoletoEmail tecnoBoleto = tecnoBoletoService.enviarEmailBoletosPara(fnTitulos);
			// Converte objeto para json
			String json = LerArqUtil.setJsonString(tecnoBoleto);
			String rota = "email/lote";
			HttpRequest results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
			int status = results.code();
			// Se retorna OK
			if (status == 200) {
				AuxDados a = new AuxDados();
				a.setCampo1(status + "");
				a.setCampo2("Boleto(s) enviado(s) com sucesso!");
				aux.add(a);
			}
			if (status != 200) {
				// Monta resposta principal
				AuxDados ap = new AuxDados();
				ap.setCampo1(status + "");
				ap.setCampo2("Problemas ao enviar boleto(s)! Consulte suporte técnico!");
				aux.add(ap);
			}
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/boletos/{id}/excluir")
	public ResponseEntity<List<AuxDados>> excluirBoletos(@RequestBody List<TecnoBoleto> boletos,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 76, "R", "REMOCAO DE BOLETOS")) {
			List<AuxDados> aux = new ArrayList<AuxDados>();
			// Cedente--------------------------
			CdPessoa e = cdPessoaRp.findById(id).get();
			// Converte em lista simples
			List<String> bols = new ArrayList<String>();
			for (TecnoBoleto t : boletos) {
				bols.add(t.getIdIntegracao());
			}
			// Converte objeto para json
			String json = LerArqUtil.setJsonString(bols);
			String rota = "boletos/descarta/lote";
			HttpRequest results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
			String retorno = results.body();
			int status = results.code();
			// Se retorna OK
			if (status == 200) {
				TecnoRetornoBoleto rets = new Gson().fromJson(retorno, TecnoRetornoBoleto.class);
				String statusRet = "200";
				for (TecnoBoleto t : boletos) {
					FnTitulo fn = fnTituloRp.findByTecno_idintegracao(t.getIdIntegracao());
					fnTituloRp.removeTecnoIdIntegracao(t.getIdIntegracao());
					// Marca Doc como nao gerado boleto
					if (fn != null) {
						if (fn.getLcdoc() != null) {
							if (fnTituloRp.verificaTituloIdIntegra(fn.getLcdoc()) == 0) {
								lcDocRp.updateDocBoleto("N", fn.getLcdoc());
							}
						}
					}
				}
				// Monta resposta
				AuxDados a = new AuxDados();
				if (rets.get_dados().get_sucesso().size() > 0) {
					a.setCampo1(statusRet);
					a.setCampo2("Boleto(s) removido(s) com sucesso!");
					aux.add(a);
				}
				// Monta resposta de erros, se houver
				if (rets.get_dados().get_falha().size() > 0) {
					statusRet = rets.get_dados().get_falha().get(0).get_status_http();
					a.setCampo1(statusRet);
					a.setCampo2(rets.get_dados().get_falha().get(0).get_erro().getErros().getBoleto());
					aux.add(a);
				}
			}
			if (status != 200) {
				// Monta resposta principal
				AuxDados ap = new AuxDados();
				ap.setCampo1(status + "");
				ap.setCampo2("Problemas ao remover boleto(s)! Consulte suporte técnico!");
				aux.add(ap);
			}
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
