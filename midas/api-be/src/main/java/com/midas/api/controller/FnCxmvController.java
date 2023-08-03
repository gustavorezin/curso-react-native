package com.midas.api.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.CdPlconMicro;
import com.midas.api.tenant.entity.FnCartao;
import com.midas.api.tenant.entity.FnCheque;
import com.midas.api.tenant.entity.FnChequeHist;
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnCxmvDTO;
import com.midas.api.tenant.entity.FnCxmvDre;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FnTituloDre;
import com.midas.api.tenant.repository.CdPlconMicroRepository;
import com.midas.api.tenant.repository.FnCartaoRepository;
import com.midas.api.tenant.repository.FnChequeHistRepository;
import com.midas.api.tenant.repository.FnChequeRepository;
import com.midas.api.tenant.repository.FnCxmvCustomRepository;
import com.midas.api.tenant.repository.FnCxmvDreRepository;
import com.midas.api.tenant.repository.FnCxmvRepository;
import com.midas.api.tenant.repository.FnTituloCustomRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.service.FnCartaoService;
import com.midas.api.tenant.service.FnCcustoService;
import com.midas.api.tenant.service.FnChequeService;
import com.midas.api.tenant.service.FnCxmvService;
import com.midas.api.tenant.service.FnDreService;
import com.midas.api.tenant.service.FnTituloService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.NumUtil;

@RestController
@RequestMapping("/private/fncxmv")
public class FnCxmvController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FnCxmvRepository fnCxmvRp;
	@Autowired
	private FnTituloService fnTituloService;
	@Autowired
	private FnCxmvCustomRepository fnCxmvCustomRp;
	@Autowired
	private FnDreService fnDreService;
	@Autowired
	private FnTituloCustomRepository fnTituloCustomRp;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private FnChequeRepository fnChequeRp;
	@Autowired
	private FnChequeHistRepository fnChequeHistRp;
	@Autowired
	private FnCartaoService fnCartaoService;
	@Autowired
	private FnChequeService fnChequeService;
	@Autowired
	private FnCartaoRepository fnCartaoRp;
	@Autowired
	private FnCxmvService fnCxmvService;
	@Autowired
	private FnCxmvDreRepository fnCxmvDreRp;
	@Autowired
	private FnCcustoService fnCcustoService;
	@Autowired
	private CdPlconMicroRepository cdPlconMicroRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/cxmv/{id}")
	public ResponseEntity<FnCxmv> fnCxmv(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 49, "L", "CONSULTA DE MOVIMENTO DE CAIXA")) {
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				Optional<FnCxmv> obj = fnCxmvRp.findById(id);
				return new ResponseEntity<FnCxmv>(obj.get(), HttpStatus.OK);
			} else {
				Optional<FnCxmv> obj = fnCxmvRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
				return new ResponseEntity<FnCxmv>(obj.get(), HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cxmv/micro/{micro}/cc/{cc}/cred/{cred}")
	public ResponseEntity<?> cadastrarFnCxmv(@RequestBody FnCxmv fnCxmv, @PathVariable("micro") Integer micro,
			@PathVariable("cc") Integer cc, @PathVariable("cred") String cred) throws Exception {
		if (ca.hasAuth(prm, 46, "C", fnCxmv.getCdcaixa().getNome())) {
			// Cadastra sempre titulo junto com movimento
			Integer transacao = NumUtil.geraNumAleaInteger();
			BigDecimal vsaldo = BigDecimal.ZERO;
			BigDecimal valorZero = BigDecimal.ZERO;
			BigDecimal vSaldoCred = BigDecimal.ZERO;
			if (cred.equals("S")) {
				vSaldoCred = fnCxmv.getVtot();
			}
			FnTitulo titulo = fnTituloService.regUnicoTitulo(fnCxmv.getCdpessoaemp(), fnCxmv.getFntitulo().getTipo(),
					fnCxmv.getFntitulo().getCdpessoapara(), fnCxmv.getCdcaixa(), fnCxmv.getFpag(),
					fnCxmv.getFntitulo().getRef(), fnCxmv.getDatacad(), fnCxmv.getDatacad(), fnCxmv.getDatacad(),
					fnCxmv.getDatacad(), "00", 0L, 0L, "", 0L, 0, 1, 1, fnCxmv.getVsub(), valorZero, valorZero,
					valorZero, valorZero, fnCxmv.getVdesc(), fnCxmv.getVjuro(), fnCxmv.getVtot(), fnCxmv.getVtot(),
					vsaldo, fnCxmv.getInfo(), cred, "N", "N", vSaldoCred, "N", "N", 0L, valorZero, valorZero, "S");
			fnCxmv.setFntitulo(titulo);
			fnCxmv.setTransacao(transacao);
			FnCxmv fnc = fnCxmvRp.save(fnCxmv);
			// DREs
			FnTituloDre fnt = fnDreService.dreFnTitulo100(titulo, micro);
			// Centro de custos
			fnCcustoService.ccFnTitulo100(titulo, cc);
			// SALVA PLANO DE CONTAS DRE -------
			FnCxmvDre fnd = fnDreService.dreFnCxmv100(fnCxmv, micro);
			// VERIFICA SE SEPARA JUROS NA CONFIGURACAO-------------------------------
			fnCxmvService.separaJuros(titulo, fnc, null, null, fnt, fnd, "03", 0, transacao);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/cxmv")
	public ResponseEntity<?> atualizarFnCxmv(@RequestBody FnCxmv fnCxmv) throws Exception {
		if (ca.hasAuth(prm, 47, "A", fnCxmv.getFntitulo().getCdpessoapara().getNome())) {
			fnCxmvRp.save(fnCxmv);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Baixa titulo unico - pelo receber-pagar
	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cxmvs/baixatitulo/databaixa/{databaixa}/troco/{troco}/fpagtroco/{fpagtroco}/infotroco/{infotroco}")
	public ResponseEntity<?> baixaTituloFnCxmvs(@RequestBody List<FnCxmv> fnCxmvs,
			@PathVariable("databaixa") String databaixa, @PathVariable("troco") String troco,
			@PathVariable("fpagtroco") String fpagtroco, @PathVariable("infotroco") String infotroco) throws Exception {
		if (ca.hasAuth(prm, 56, "C", fnCxmvs.get(0).getFntitulo().getCdpessoapara().getNome())) {
			BigDecimal valorBaixa = BigDecimal.ZERO;
			BigDecimal valorDesc = BigDecimal.ZERO;
			BigDecimal valorJuro = BigDecimal.ZERO;
			for (FnCxmv c : fnCxmvs) {
				// Ajuste arredondamentos-----------------------------
				c.setVsub(c.getVsub().setScale(2, RoundingMode.HALF_UP));
				c.setPdesc(c.getPdesc().setScale(2, RoundingMode.HALF_UP));
				c.setVdesc(c.getVdesc().setScale(2, RoundingMode.HALF_UP));
				c.setPjuro(c.getPjuro().setScale(2, RoundingMode.HALF_UP));
				c.setVjuro(c.getVjuro().setScale(2, RoundingMode.HALF_UP));
				c.setVtot(c.getVtot().setScale(2, RoundingMode.HALF_UP));
				valorBaixa = valorBaixa.add(c.getVtot());
				valorDesc = valorDesc.add(c.getVdesc());
				valorJuro = valorJuro.add(c.getVjuro());
			}
			BigDecimal vSaldoTituloAtual = fnCxmvs.get(0).getFntitulo().getVsaldo();
			BigDecimal vSaldo = valorBaixa.add(valorDesc).subtract(valorJuro);
			// AJUSTE TROCO --------------------------------------
			BigDecimal vTroco = BigDecimal.ZERO;
			if (vSaldo.compareTo(vSaldoTituloAtual) > 0) {
				vTroco = vSaldo.subtract(vSaldoTituloAtual);
				vSaldo = vSaldo.subtract(vTroco);
				valorBaixa = vSaldo;
			}
			// VERIFICA TROCO UTILIZADO E LANCA SEPARADAMENTE ----
			if (vTroco.compareTo(new BigDecimal(0)) > 0) {
				// LANCA MOVIMENTO GERAIS - INCLUSIVE CHEQUES - LANCA CREDITOS TAMBEM
				fnCxmvs = fnCxmvService.regTrocoCaixa(vTroco, databaixa, troco, fpagtroco, infotroco, fnCxmvs);
			}
			fnTituloService.baixaUnico(valorBaixa, valorDesc, valorJuro, vSaldo, Date.valueOf(databaixa),
					fnCxmvs.get(0).getCdcaixa().getId(), fnCxmvs.get(0).getFntitulo());
			// AJUSTA DATA DE MOVIMENTO SE NECESSARIO -----------
			for (int x = 0; x < fnCxmvs.size(); x++) {
				fnCxmvs.get(x).setDatacad(Date.valueOf(databaixa));
			}
			List<FnCxmv> fnCxmvsObj = fnCxmvRp.saveAll(fnCxmvs);
			// SALVA PLANO DE CONTAS DRE -------------------------
			List<FnCxmvDre> fnFnCxmvDreObj = fnDreService.dreCxmv(fnCxmvsObj);
			// USANDO CREDITOS -----------------------------------
			fnTituloService.baixaCreditoUnico(fnCxmvsObj, Date.valueOf(databaixa));
			// CHEQUES ------------------------------------------
			for (FnCxmv c : fnCxmvsObj) {
				for (FnCheque ch : c.getFnchequeitem()) {
					if (ch.getId() == null) {
						ch.setFncxmv(c);
						ch.setCdpessoaempatual(c.getCdpessoaemp());
						ch.setCdcaixaatual(c.getCdcaixa());
						ch.setDatacad(Date.valueOf(databaixa));
						fnChequeRp.save(ch);
					} else {
						// Usa cheques em caixa para pagamentos
						if (ch.getSelemcaixaop() != null) {
							if (ch.getSelemcaixaop().equals("S")) {
								fnCxmvService.reChequesEmCaixaUsados(ch, c, Date.valueOf(databaixa));
							}
						}
					}
				}
			}
			// CARTOES DEBITO ---------------------------------
			for (FnCxmv c : fnCxmvsObj) {
				// Permitido apenas um cartao por lancamento
				if (c.getFpag().equals("04")) {
					for (FnCartao cc : c.getFncartaoitem()) {
						cc.setFncxmv(c);
						cc.setDatacad(Date.valueOf(databaixa));
						cc.setQtdparc(1);
						cc.setParcnum(1);
						fnCartaoService.regCalculo(cc);
					}
				}
			}
			// CARTOES CREDITO ---------------------------------
			for (FnCxmv c : fnCxmvs) {
				// Permitido apenas um cartao por lancamentoo
				if (c.getFpag().equals("03")) {
					for (FnCartao cc : c.getFncartaoitem()) {
						// Ajuste de parcela quebrada
						BigDecimal total = BigDecimal.ZERO;
						FnCartao cf = null;
						// Se cartao de credito e parcelado
						int i = cc.getQtdparc();
						int x = 0;
						while (x < i) {
							// Adiciona movimento do cartao
							cf = fnCartaoService.regCalculoParcela(c, cc, Date.valueOf(databaixa), i, x);
							total = total.add(cf.getVtot());
							// SALVA PLANO DE CONTAS DRE -------
							fnDreService.dreCxmvUnico(cf.getFncxmv());
							x++;
						}
						// Ajusta parcela se necessario
						if (total.compareTo(cc.getVtot()) != 0) {
							BigDecimal vlrDif = total.subtract(cc.getVtot());
							if (vlrDif.compareTo(BigDecimal.ZERO) < 0) {
								vlrDif = vlrDif.multiply(new BigDecimal(-1));
								fnCxmvRp.arredondarValores(vlrDif, cf.getFncxmv().getId());
								fnCartaoRp.arredondarValores(vlrDif, cf.getId());
							} else {
								fnCxmvRp.arredondarValoresMaior(vlrDif, cf.getFncxmv().getId());
								fnCartaoRp.arredondarValoresMaior(vlrDif, cf.getId());
							}
						}
						// Remove movimento antigo, e atualiza com dados atuais acima
						c.setFncartaoitem(null);
						fnCxmvDreRp.removeItensPlanoContas(c.getId());
						fnCxmvRp.delete(c);
					}
				}
			}
			// VERIFICA SE SEPARA JUROS NA CONFIGURACAO-------------------------------
			for (FnCxmv fnCxmv : fnCxmvsObj) {
				FnTitulo titulo = fnCxmvs.get(0).getFntitulo();
				fnCxmvService.separaJuros(titulo, fnCxmv, fnFnCxmvDreObj, titulo.getFntitulodreitem(), null, null, "04",
						0, null);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cxmvs/transfere")
	public ResponseEntity<?> transfereTituloFnCxmvs(@RequestBody List<FnCxmv> fnCxmvs) throws Exception {
		// ATENCAO - Transferencia definida como tipo TR ou TP - para naoa aparecer em
		// contas a receber ou pagar
		if (ca.hasAuth(prm, 57, "C",
				fnCxmvs.get(0).getCdpessoaemp().getNome() + " - " + fnCxmvs.get(0).getCdcaixa().getNome())) {
			Integer transacao = NumUtil.geraNumAleaInteger();
			if (fnCxmvs.get(0).getCdcaixa().getId() != fnCxmvs.get(1).getCdcaixa().getId()) {
				// Valores
				BigDecimal vdesc = BigDecimal.ZERO;
				BigDecimal vjuro = BigDecimal.ZERO;
				BigDecimal vsaldo = BigDecimal.ZERO;
				BigDecimal valorZero = BigDecimal.ZERO;
				Integer idplanoe = 0;
				Integer idplanos = 0;
				CdPlconMicro pme = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("07",
						fnCxmvs.get(0).getCdpessoaemp().getId());// Entrada
				CdPlconMicro pms = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("08",
						fnCxmvs.get(0).getCdpessoaemp().getId());// Saida
				if (pme != null) {
					idplanoe = pme.getId();
				}
				if (pms != null) {
					idplanos = pms.getId();
				}
				// Dados fixos
				BigDecimal valor = fnCxmvs.get(0).getVtot();
				String ref = "TRANSFERÊNCIA - " + fnCxmvs.get(0).getFntitulo().getRef();
				java.util.Date datacad = fnCxmvs.get(0).getDatacad();
				// SAIDA
				FnTitulo tituloS = fnTituloService.regUnicoTitulo(fnCxmvs.get(0).getCdpessoaemp(), "TP",
						fnCxmvs.get(0).getCdpessoaemp(), fnCxmvs.get(0).getCdcaixa(), fnCxmvs.get(0).getFpag(), ref,
						datacad, datacad, datacad, datacad, "00", 0L, 0L, "", 0L, 0, 1, 1, valor, valorZero, valorZero,
						valorZero, valorZero, vdesc, vjuro, valor, valor, vsaldo, fnCxmvs.get(0).getInfo(), "N", "N",
						"N", new BigDecimal(0), "N", "N", 0L, valorZero, valorZero, "S");
				// DREs
				fnDreService.dreFnTitulo100(tituloS, idplanos);
				// Demais itens
				fnCxmvs.get(0).setTransacao(transacao);
				fnCxmvs.get(0).setFntitulo(tituloS);
				// ENTRADA
				FnTitulo tituloE = fnTituloService.regUnicoTitulo(fnCxmvs.get(1).getCdpessoaemp(), "TR",
						fnCxmvs.get(1).getCdpessoaemp(), fnCxmvs.get(1).getCdcaixa(), fnCxmvs.get(1).getFpag(), ref,
						datacad, datacad, datacad, datacad, "00", 0L, 0L, "", 0L, 0, 1, 1, valor, valorZero, valorZero,
						valorZero, valorZero, vdesc, vjuro, valor, valor, vsaldo, fnCxmvs.get(1).getInfo(), "N", "N",
						"N", new BigDecimal(0), "N", "N", 0L, valorZero, valorZero, "S");
				// DREs
				fnDreService.dreFnTitulo100(tituloE, idplanoe);
				// Demais itens
				fnCxmvs.get(1).setTransacao(transacao);
				fnCxmvs.get(1).setFntitulo(tituloE);
				fnCxmvRp.saveAll(fnCxmvs);
				// SALVA PLANO DE CONTAS DRE -------
				// Centro de Custos - aqui nao ha necessidade, pois nao aparece nos titulos
				fnDreService.dreFnCxmv100(fnCxmvs.get(0), idplanos);
				fnDreService.dreFnCxmv100(fnCxmvs.get(1), idplanoe);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@Transactional("tenantTransactionManager")
	@RequestAuthorization
	@DeleteMapping("/cxmv/estorno/{id}")
	public ResponseEntity<?> estornoFnCxmv(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 48, "R", "ID " + id)) {
			FnCxmv fnCxmv = fnCxmvRp.getById(id);
			// VERIFICA SE TEM CARTAO PARA ESTORNAR-----------------------
			List<FnCartao> fc = fnCartaoRp.findByFncxmv(fnCxmv);
			for (FnCartao f : fc) {
				List<FnCxmv> obj = fnCxmvRp.getTaxaSimilar(f.getVtaxa(), f.getFncxmv().getCdcaixa().getId(), "99", "P",
						f.getTransacao());
				for (FnCxmv cx : obj) {
					fnCxmvRp.delete(cx);
					fnTituloRp.delete(cx.getFntitulo());
				}
			}
			// VERIFICA SE TEM CHEQUES DE TROCO NESTA MOVIMENTACAO--------
			List<FnCheque> objSimCC = fnChequeRp.getMovimentoSimilar(fnCxmv.getTransacao(), "05");
			for (FnCheque ch : objSimCC) {
				fnChequeRp.atualizaStatusCheque("01", null, ch.getId());
				FnChequeHist fh = fnChequeHistRp.findByChequeHistUltimo(ch.getId(), fnCxmv.getId());
				if (fh != null) {
					fnChequeService.regHistorico(ch, ch.getCdcaixaatual().getId(), null, null,
							Date.valueOf(DataUtil.dataBancoAtual()), "01", "ET", "");
				}
			}
			// VERIFICA SE TEM CHEQUES EM CAIXA PARA PAGAMENTO NESTA MOVIMENTACAO--------
			List<FnCheque> objSimCX = fnChequeRp.getMovimentoChequeStatus("03");
			for (FnCheque ch : objSimCX) {
				FnChequeHist fh = fnChequeHistRp.findByChequeHistUltimo(ch.getId(), fnCxmv.getId());
				if (fh != null) {
					fnChequeRp.atualizaStatusCheque("01", null, ch.getId());
					fnChequeService.regHistorico(ch, ch.getCdcaixaatual().getId(), null, null,
							Date.valueOf(DataUtil.dataBancoAtual()), "01", "ET", "");
				}
			}
			// USANDO CREDITOS--------------------------------------------
			fnTituloService.devolucaoCreditoUnico(fnCxmv);
			// VERIFICA SE TEM VINCULOS DE TRANSACAO DE OUTRAS MOVIMENTACOES
			/*
			 * List<FnCxmv> objSim = fnCxmvRp.getMovimentoSimilar(fnCxmv.getVtot(),
			 * fnCxmv.getTransacao()); if (objSim.size() > 0) { for (FnCxmv cx : objSim) {
			 * fnCxmvRp.delete(cx); fnTituloRp.delete(cx.getFntitulo()); } } else {
			 */
			// Remove
			fnCxmvRp.deleteById(id);
			// }
			// REMOVE TROCOS ----------------------------------------------
			if (fnCxmv.getFntitulo().getTroco().equals("S")) {
				List<FnCxmv> objSim = fnCxmvRp.getMovimentoSimilarTroco(fnCxmv.getVtot(), fnCxmv.getTransacao(), "S");
				if (objSim.size() > 0) {
					for (FnCxmv cx : objSim) {
						fnCxmvRp.delete(cx);
						fnTituloRp.delete(cx.getFntitulo());
					}
				}
				fnTituloRp.delete(fnCxmv.getFntitulo());
			}
			// REMOVE SE TRANSFERENCIA ------------------------------------
			if (fnCxmv.getFntitulo().getTipo().equals("TP") || fnCxmv.getFntitulo().getTipo().equals("TR")) {
				// ATENCAO - Transferencia definida como tipo TR ou TP - para naoa aparecer em
				// contas a receber ou pagar
				List<FnCxmv> objSim = fnCxmvRp.getMovimentoSimilar(fnCxmv.getVtot(), fnCxmv.getTransacao());
				if (objSim.size() > 0) {
					for (FnCxmv cx : objSim) {
						fnCxmvRp.delete(cx);
						fnTituloRp.delete(cx.getFntitulo());
					}
				}
				fnTituloRp.delete(fnCxmv.getFntitulo());
			} else {
				// DEVOLVE VALOR SE NAO FOR CREDITO------------------------
				if (fnCxmv.getFntitulo().getCred().equals("N")) {
					java.util.Date ultimabaixa = fnCxmv.getFntitulo().getDatacad();
					// Verifica se tem outras baixas
					FnCxmv movAnt = fnCxmvRp.findByFntituloUltimaBaixa(fnCxmv.getFntitulo().getId());
					if (movAnt != null) {
						ultimabaixa = movAnt.getDatacad();
					}
					BigDecimal vSaldo = fnCxmv.getVtot().add(fnCxmv.getVdesc()).subtract(fnCxmv.getVjuro());
					fnTituloRp.estornaValor(fnCxmv.getVtot(), fnCxmv.getVdesc(), fnCxmv.getVjuro(), vSaldo, ultimabaixa,
							fnCxmv.getFntitulo().getId());
				} else {
					// Remove se for credito
					fnTituloRp.delete(fnCxmv.getFntitulo());
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@Transactional("tenantTransactionManager")
	@RequestAuthorization
	@PutMapping("/cxmv/status/{id}")
	public ResponseEntity<?> atualizarStatusFnCxmv(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 47, "A", "ID ATUALIZAR STATUS DO MOVIMENTO " + id)) {
			FnCxmv fnCxmv = fnCxmvRp.getById(id);
			if (fnCxmv.getStatus().equals("PENDENTE")) {
				fnCxmv.setStatus("ATIVO");
				fnCxmvRp.save(fnCxmv);
			} else {
				fnCxmv.setStatus("PENDENTE");
				fnCxmvRp.save(fnCxmv);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cxmv/titulo/{id}")
	public ResponseEntity<List<FnCxmv>> listaFnTitulosBusca(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 49, "L", "LISTAGEM / CONSULTA DE MOVIMENTO DE CAIXA")) {
			List<FnCxmv> lista = fnCxmvRp.findByTitulos(id);
			return new ResponseEntity<List<FnCxmv>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cxmv/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/lc/{local}/cx/{caixa}/tp/{tipo}/fpag/{fpag}/ipp/"
			+ "{itenspp}/dti/{dataini}/dtf/{datafim}/para/{para}/lanca/{lanca}/status/{status}")
	public ResponseEntity<Page<FnCxmv>> listaFnCxmvssBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("local") Long local,
			@PathVariable("caixa") Integer caixa, @PathVariable("tipo") String tipo, @PathVariable("fpag") String fpag,
			@PathVariable("itenspp") int itenspp, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("para") Long para,
			@PathVariable("lanca") String lanca, @PathVariable("status") String status) throws Exception {
		if (ca.hasAuth(prm, 49, "L", "LISTAGEM / CONSULTA DE MOVIMENTO DE CAIXA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<FnCxmv> lista = fnCxmvCustomRp.listaFnCxmv(local, caixa, tipo, fpag, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ordem, ordemdir, para, lanca, status, pageable);
			return new ResponseEntity<Page<FnCxmv>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cxmv/valores/b/{busca}/lc/{local}/cx/{caixa}/tp/{tipo}/fpag/{fpag}/dti/{dataini}/dtf/{datafim}/para/{para}/lanca/{lanca}/status/{status}")
	public ResponseEntity<List<FnCxmvDTO>> listaFnCxmvsBuscaValores(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("caixa") Integer caixa, @PathVariable("tipo") String tipo,
			@PathVariable("fpag") String fpag, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("para") Long para,
			@PathVariable("lanca") String lanca, @PathVariable("status") String status) throws Exception {
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
		}
		busca = CaracterUtil.buscaContexto(busca);
		List<FnCxmv> listaValores = fnCxmvCustomRp.listaFnCxmvValores(local, caixa, tipo, fpag, Date.valueOf(dataini),
				Date.valueOf(datafim), busca, para, lanca, status);
		List<FnCxmvDTO> listadto = fnCxmvService.listaCxDTO(listaValores);
		return new ResponseEntity<List<FnCxmvDTO>>(listadto, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cxmv/saldoanterior/b/{busca}/lc/{local}/cx/{caixa}/tp/{tipo}/fpag/{fpag}/dti/{dataini}/dtf/{datafim}/para/{para}/lanca/{lanca}/status/{status}")
	public ResponseEntity<BigDecimal> listaFnCxmvsBuscaValoresSaldoAnterior(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("caixa") Integer caixa, @PathVariable("tipo") String tipo,
			@PathVariable("fpag") String fpag, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("para") Long para,
			@PathVariable("lanca") String lanca, @PathVariable("status") String status) throws Exception {
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
		}
		busca = CaracterUtil.buscaContexto(busca);
		BigDecimal saldoAnterior = fnCxmvCustomRp.retFnCxmvValoresSaldoAnterior(local, caixa, tipo, fpag,
				Date.valueOf(dataini), Date.valueOf(datafim), busca, para, lanca, status);
		return new ResponseEntity<BigDecimal>(saldoAnterior, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cxmv/valoresperiodo/lc/{local}/tp/{tipo}/fpag/{fpag}/ano/{ano}/status/{status}")
	public ResponseEntity<List<BigDecimal>> listaFnCxmvsBuscaValoresPeriodo(@PathVariable("local") Long local,
			@PathVariable("tipo") String tipo, @PathVariable("fpag") String fpag, @PathVariable("ano") Integer ano,
			@PathVariable("status") String status) throws Exception {
		List<BigDecimal> aux = new ArrayList<BigDecimal>();
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
		}
		// Lista meses
		for (int mes = 0; mes < 12; mes++) {
			java.util.Date meuMesIni = DataUtil.addRemMeses(Date.valueOf(ano + "-01-01"), mes, "A");
			java.util.Date meuMesFim = DataUtil.addRemMeses(Date.valueOf(ano + "-01-31"), mes, "A");
			// Calculos
			Date dataini = new Date(meuMesIni.getTime());
			Date datafim = new Date(meuMesFim.getTime());
			BigDecimal valor = fnCxmvCustomRp.retFnCxmvValoresTotais(local, tipo, fpag, status, dataini, datafim);
			aux.add(valor);
		}
		return new ResponseEntity<List<BigDecimal>>(aux, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cxmv/valoresperiodo/resultadomes/lc/{local}/ano/{ano}/status/{status}")
	public ResponseEntity<List<BigDecimal>> listaFnCxmvsBuscaValoresPeriodoResultadoMes(
			@PathVariable("local") Long local, @PathVariable("ano") Integer ano, @PathVariable("status") String status)
			throws Exception {
		List<BigDecimal> aux = new ArrayList<BigDecimal>();
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
		}
		// Lista meses
		for (int mes = 0; mes < 12; mes++) {
			java.util.Date meuMesIni = DataUtil.addRemMeses(Date.valueOf(ano + "-01-01"), mes, "A");
			java.util.Date meuMesFim = DataUtil.addRemMeses(Date.valueOf(ano + "-01-31"), mes, "A");
			// Calculos
			Date dataini = new Date(meuMesIni.getTime());
			Date datafim = new Date(meuMesFim.getTime());
			BigDecimal valor = fnCxmvCustomRp.retFnCxmvValoresTotaisResultadoMes(local, status, dataini, datafim);
			aux.add(valor);
		}
		return new ResponseEntity<List<BigDecimal>>(aux, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cxmv/valoresperiodo/saldoanterior/b/{busca}/lc/{local}/cx/{caixa}/tp/{tipo}/fpag/{fpag}/ano/{ano}/para/{para}/lanca/{lanca}/status/{status}")
	public ResponseEntity<List<BigDecimal>> listaFnCxmvsBuscaValoresSaldoAnterior(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("caixa") Integer caixa, @PathVariable("tipo") String tipo,
			@PathVariable("fpag") String fpag, @PathVariable("ano") Integer ano, @PathVariable("para") Long para,
			@PathVariable("lanca") String lanca, @PathVariable("status") String status) throws Exception {
		List<BigDecimal> aux = new ArrayList<BigDecimal>();
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
		}
		// Lista meses
		for (int mes = 0; mes < 12; mes++) {
			java.util.Date meuMesIni = DataUtil.addRemMeses(Date.valueOf(ano + "-01-01"), mes, "A");
			java.util.Date meuMesFim = DataUtil.addRemMeses(Date.valueOf(ano + "-01-31"), mes, "A");
			// Calculos
			Date dataini = new Date(meuMesIni.getTime());
			Date datafim = new Date(meuMesFim.getTime());
			BigDecimal valor = fnCxmvCustomRp.retFnCxmvValoresSaldoAnterior(local, caixa, tipo, fpag, dataini, datafim,
					busca, para, lanca, status);
			aux.add(valor);
		}
		return new ResponseEntity<List<BigDecimal>>(aux, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cxmv/valoresperiodo/saldoatual/b/{busca}/lc/{local}/cx/{caixa}/tp/{tipo}/fpag/{fpag}/ano/{ano}/para/{para}/lanca/{lanca}/status/{status}")
	public ResponseEntity<List<BigDecimal>> listaFnCxmvsBuscaValoresSaldoAtual(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("caixa") Integer caixa, @PathVariable("tipo") String tipo,
			@PathVariable("fpag") String fpag, @PathVariable("ano") Integer ano, @PathVariable("para") Long para,
			@PathVariable("lanca") String lanca, @PathVariable("status") String status) throws Exception {
		List<BigDecimal> aux = new ArrayList<BigDecimal>();
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
		}
		// Lista meses
		for (int mes = 0; mes < 12; mes++) {
			java.util.Date meuMesIni = DataUtil.addRemMeses(Date.valueOf(ano + "-01-01"), mes, "A");
			java.util.Date meuMesFim = DataUtil.addRemMeses(Date.valueOf(ano + "-01-31"), mes, "A");
			// Calculos
			Date dataini = new Date(meuMesIni.getTime());
			Date datafim = new Date(meuMesFim.getTime());
			BigDecimal saldoAnt = fnCxmvCustomRp.retFnCxmvValoresSaldoAnterior(local, caixa, tipo, fpag, dataini,
					datafim, busca, para, lanca, status);
			BigDecimal resMes = fnCxmvCustomRp.retFnCxmvValoresTotaisResultadoMes(local, status, dataini, datafim);
			BigDecimal resultado = resMes.add(saldoAnt);
			aux.add(resultado);
		}
		return new ResponseEntity<List<BigDecimal>>(aux, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cxmv/valoresperiodo/saldoprevisto/b/{busca}/lc/{local}/cx/{caixa}/fp/{fpagpref}/tp/{tipo}/"
			+ "ano/{ano}/para/{para}/lanca/{lanca}/cred/{cred}/status/{status}")
	public ResponseEntity<List<BigDecimal>> listaFnCxmvsBuscaValoresSaldoPrevisto(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("fpagpref") String fpagpref,
			@PathVariable("caixa") Integer caixa, @PathVariable("tipo") String tipo, @PathVariable("ano") Integer ano,
			@PathVariable("para") Long para, @PathVariable("lanca") String lanca, @PathVariable("cred") String cred,
			@PathVariable("status") String status) throws Exception {
		List<BigDecimal> aux = new ArrayList<BigDecimal>();
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
		}
		// Lista meses
		for (int mes = 0; mes < 12; mes++) {
			java.util.Date meuMesIni = DataUtil.addRemMeses(Date.valueOf(ano + "-01-01"), mes, "A");
			java.util.Date meuMesFim = DataUtil.addRemMeses(Date.valueOf(ano + "-01-31"), mes, "A");
			// Calculos
			Date dataini = new Date(meuMesIni.getTime());
			Date datafim = new Date(meuMesFim.getTime());
			// Mes anterior e Resultado do mes
			BigDecimal saldoAnt = fnCxmvCustomRp.retFnCxmvValoresSaldoAnterior(local, caixa, tipo, fpagpref, dataini,
					datafim, busca, para, lanca, status);
			BigDecimal resMes = fnCxmvCustomRp.retFnCxmvValoresTotaisResultadoMes(local, status, dataini, datafim);
			BigDecimal resultado = resMes.add(saldoAnt);
			// Contas a pagar e receber com saldo atual
			BigDecimal valorPagar = fnTituloCustomRp.retFnTitulosValoresTotais(local, caixa, fpagpref, "P",
					Date.valueOf("1984-01-01"), datafim, "V", cred, "X", "X", "X", "A");
			BigDecimal valorReceber = fnTituloCustomRp.retFnTitulosValoresTotais(local, caixa, fpagpref, "R",
					Date.valueOf("1984-01-01"), datafim, "V", cred, "X", "X", "X", "A");
			// Adiciona itens de caixa PENDENTES (Cartoes)
			BigDecimal vCarts = BigDecimal.ZERO;
			BigDecimal vTaxas = BigDecimal.ZERO;
			if (local > 0) {
				vCarts = fnCartaoRp.somaVtotCartaoPrevisaoStatusLocal(local, Date.valueOf("1984-01-01"), datafim, "01");
				vTaxas = fnCartaoRp.somaVtaxaCartaoPrevisaoStatusLocal(local, Date.valueOf("1984-01-01"), datafim,
						"01");
			} else {
				vCarts = fnCartaoRp.somaVtotCartaoPrevisaoStatus(Date.valueOf("1984-01-01"), datafim, "01");
				vTaxas = fnCartaoRp.somaVtaxaCartaoPrevisaoStatus(Date.valueOf("1984-01-01"), datafim, "01");
			}
			if (vCarts != null) {
				valorReceber = valorReceber.add(vCarts);
			}
			if (vTaxas != null) {
				valorPagar = valorPagar.add(vTaxas);
			}
			BigDecimal resultadoDiferenca = valorReceber.subtract(valorPagar);
			BigDecimal resultadoPrevisto = resultado.add(resultadoDiferenca);
			aux.add(resultadoPrevisto);
		}
		return new ResponseEntity<List<BigDecimal>>(aux, HttpStatus.OK);
	}
}
