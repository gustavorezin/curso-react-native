package com.midas.api.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnCxmvDre;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FnTituloCcusto;
import com.midas.api.tenant.entity.FnTituloDre;
import com.midas.api.tenant.entity.FnTituloRel;
import com.midas.api.tenant.repository.FnCartaoRepository;
import com.midas.api.tenant.repository.FnCxmvDreRepository;
import com.midas.api.tenant.repository.FnCxmvRepository;
import com.midas.api.tenant.repository.FnTituloCcustoRepository;
import com.midas.api.tenant.repository.FnTituloCustomRepository;
import com.midas.api.tenant.repository.FnTituloDreRepository;
import com.midas.api.tenant.repository.FnTituloRelRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.service.EmailService;
import com.midas.api.tenant.service.FnCcustoService;
import com.midas.api.tenant.service.FnCxmvService;
import com.midas.api.tenant.service.FnDreService;
import com.midas.api.tenant.service.FnTituloService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.MoedaUtil;
import com.midas.api.util.NumUtil;

@RestController
@RequestMapping("/private/fntitulo")
public class FnTituloController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private FnTituloRelRepository fnTituloRelRp;
	@Autowired
	private FnTituloDreRepository fnTituloDreRp;
	@Autowired
	private FnTituloCcustoRepository fnTituloCcustoRp;
	@Autowired
	private FnDreService fnDreService;
	@Autowired
	private FnTituloCustomRepository fnTituloCustomRp;
	@Autowired
	private FnTituloService fnTituloService;
	@Autowired
	private FnCxmvService fnCxmvService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private FnCartaoRepository fnCartaoRp;
	@Autowired
	private FnCcustoService fnCcustoService;
	@Autowired
	private FnCxmvRepository fnCxmvRp;
	@Autowired
	private FnCxmvDreRepository fnCxmvDreRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	// TODO inserir usuario que fez as operacoes em todas as tabelas, titulos, fluxo
	// TODO fazer verificacao de modulos que cliente contratou, se permite usa-lo

	@RequestAuthorization
	@GetMapping("/titulo/{id}")
	public ResponseEntity<FnTitulo> fnTitulo(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 15, "L", "CONSULTA DE TITULOS DE COBRANCA")) {
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				Optional<FnTitulo> obj = fnTituloRp.findById(id);
				return new ResponseEntity<FnTitulo>(obj.get(), HttpStatus.OK);
			} else {
				Optional<FnTitulo> obj = fnTituloRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
				return new ResponseEntity<FnTitulo>(obj.get(), HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/titulos/lancamesmomes/{lancamesmomes}/cobauto/{cobauto}/pgtoprogramado/{pgtoprogramado}")
	public ResponseEntity<?> cadastrarFnTitulos(@RequestBody List<FnTitulo> fnTitulos,
			@PathVariable("lancamesmomes") String lancamesmomes, @PathVariable("cobauto") String cobauto,
			@PathVariable("pgtoprogramado") String pgtoprogramado) throws Exception {
		if (ca.hasAuth(prm, 12, "C", fnTitulos.get(0).getCdpessoapara().getNome())) {
			// Emissao igual do vencimento
			if (lancamesmomes.equals("S")) {
				for (FnTitulo fn : fnTitulos) {
					fn.setDatacad(fn.getVence());
				}
			}
			// Cobranca automatica no vencimento
			if (cobauto.equals("S")) {
				for (FnTitulo fn : fnTitulos) {
					fn.setCobauto("S");
				}
			}
			// Pagamento programado
			if (pgtoprogramado.equals("S")) {
				for (FnTitulo fn : fnTitulos) {
					fn.setPgtoprogramado("S");
				}
			}
			List<FnTitulo> titulos = fnTituloRp.saveAll(fnTitulos);
			for (FnTitulo fn : titulos) {
				// DREs
				for (FnTituloDre fd : fn.getFntitulodreitem()) {
					fd.setFntitulo(fn);
					fnTituloDreRp.save(fd);
				}
				// Centros
				for (FnTituloCcusto fd : fn.getFntituloccustoitem()) {
					fd.setFntitulo(fn);
					fnTituloCcustoRp.save(fd);
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/titulo")
	public ResponseEntity<?> atualizarFnTitulo(@RequestBody FnTitulo fnTitulo) throws Exception {
		if (ca.hasAuth(prm, 13, "A", fnTitulo.getCdpessoapara().getNome())) {
			fnTituloRp.save(fnTitulo);
			// Remove todos planos e custos
			fnTituloDreRp.removeItensPlanoContas(fnTitulo.getId());
			fnTituloCcustoRp.removeItensCcustos(fnTitulo.getId());
			// Adiciona novamente
			for (FnTituloDre td : fnTitulo.getFntitulodreitem()) {
				td.setFntitulo(fnTitulo);
				fnTituloDreRp.save(td);
			}
			// Centros
			for (FnTituloCcusto fd : fnTitulo.getFntituloccustoitem()) {
				fd.setFntitulo(fnTitulo);
				fnTituloCcustoRp.save(fd);
			}
			// Atualiza fluxo do caixa para alterar DRE
			List<FnCxmv> fcs = fnCxmvRp.findByTitulos(fnTitulo.getId());
			for (FnCxmv c : fcs) {
				// Remove todos planos da movimentacao para alterar
				fnCxmvDreRp.removeItensPlanoContas(c.getId());
				// Adiciona novamente
				fnDreService.dreTituloLote(fnTitulo, c);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/titulo/ngparcela")
	public ResponseEntity<?> atualizarFnTituloVenceValor(@RequestParam("idtitulo") Long idtitulo,
			@RequestParam("vence") Date vence, @RequestParam("vtot") BigDecimal vtot) throws Exception {
		if (ca.hasAuth(prm, 13, "A", "ID TITULO ALTERADO - EDITADO MANUALMENTE VIA DOC: " + idtitulo)) {
			fnTituloRp.attValorVence(vtot, vence, idtitulo);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/titulo/{id}")
	public ResponseEntity<?> removerFnTitulo(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 14, "R", "ID " + id)) {
			FnTitulo titulo = fnTituloRp.getById(id);
			// Verifica se ja foi movimentado
			Integer verificaQtd = fnTituloRp.verificaTitulosMovimentadosTransacao(titulo.getTransacao());
			if (verificaQtd == 0) {
				for (FnTituloRel t : titulo.getFntitulorelitem()) {
					fnTituloRp.attTituloCobranca("S", t.getFntituloant().getId());
				}
				// Remove item por item - Para titulos com mais vinculos
				for (FnTitulo t : fnTituloRp.listaTitulosTransacao(titulo.getTransacao())) {
					for (FnTituloRel tr : t.getFntitulorelitem()) {
						fnTituloRelRp.delete(tr);
					}
					// Por fim, remove titulo
					fnTituloRp.delete(t);
				}
				fnTituloRp.delete(titulo);
			} else {
				// Aviso de movimento
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/titulo/alteraplcon/emlote/{idnovo}/ccusto/{ccusto}")
	public ResponseEntity<?> alterarFnTituloPlconEmLote(@RequestBody List<FnTitulo> fnTitulos,
			@PathVariable("idnovo") Integer idnovo, @PathVariable("ccusto") Integer ccusto) {
		if (ca.hasAuth(prm, 14, "A", "ATUALIZACAO DE PLANO DE CONTAS DE TITULOS EM LOTE ")) {
			for (FnTitulo t : fnTitulos) {
				// Planos - mantem ou atualiza tambem
				if (idnovo > 0) {
					fnTituloDreRp.removeItensPlanoContas(t.getId());
					// Adiciona novamente
					fnDreService.dreFnTitulo100(t, idnovo);
					// Atualiza fluxo do caixa para alterar DRE
					List<FnCxmv> fcs = fnCxmvRp.findByTitulos(t.getId());
					for (FnCxmv c : fcs) {
						// Remove todos planos da movimentacao para alterar
						fnCxmvDreRp.removeItensPlanoContas(c.getId());
						// Adiciona novamente
						fnDreService.dreFnCxmv100(c, idnovo);
					}
				}
				// Centros - mantem ou atualiza tambem
				if (ccusto > 0) {
					fnTituloCcustoRp.removeItensCcustos(t.getId());
					fnCcustoService.ccFnTitulo100(t, ccusto);
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/titulo/alteravence/emlote/{novovencimento}")
	public ResponseEntity<?> alterarFnTituloVenceEmLote(@RequestBody List<FnTitulo> fnTitulos,
			@PathVariable("novovencimento") String novovencimento) {
		if (ca.hasAuth(prm, 14, "A", "ATUALIZACAO DE VENCIMENTOS DE TITULOS EM LOTE ")) {
			for (FnTitulo t : fnTitulos) {
				fnTituloRp.attVencimento(Date.valueOf(novovencimento), t.getId());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/titulo/remover/emlote")
	public ResponseEntity<?> removerFnTituloEmLote(@RequestBody List<FnTitulo> fnTitulos) {
		if (ca.hasAuth(prm, 14, "R", "REMOCAO DE TITULOS EM LOTE ")) {
			boolean verifica = false;
			for (FnTitulo titulo : fnTitulos) {
				// Verifica se algum deles ja foi movimentado
				Integer verificaQtd = fnTituloRp.verificaTitulosMovimentadosTransacao(titulo.getTransacao());
				if (verificaQtd > 0) {
					verifica = true;
				}
				// Se nao teve nenhum, prossegue
				if (verifica == false) {
					for (FnTituloRel t : titulo.getFntitulorelitem()) {
						fnTituloRp.attTituloCobranca("S", t.getFntituloant().getId());
					}
					// Remove item por item - Para titulos com mais vinculos
					for (FnTitulo t : fnTituloRp.listaTitulosTransacao(titulo.getTransacao())) {
						for (FnTituloRel tr : t.getFntitulorelitem()) {
							fnTituloRelRp.delete(tr);
						}
						// Por fim, remove titulo
						fnTituloRp.delete(t);
					}
					fnTituloRp.delete(titulo);
				} else {
					// Aviso de movimento
					return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/titulo/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/lc/{local}/cx/{caixapref}/fp/{fpagpref}/tp/{tipo}/ipp/"
			+ "{itenspp}/dti/{dataini}/dtf/{datafim}/ove/{ove}/para/{para}/resp/{resp}/cred/{cred}/com/{com}/compago/{compago}/status/{status}")
	public ResponseEntity<Page<FnTitulo>> listaFnTitulosBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("local") Long local,
			@PathVariable("caixapref") Integer caixapref, @PathVariable("fpagpref") String fpagpref,
			@PathVariable("tipo") String tipo, @PathVariable("itenspp") int itenspp,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("ove") String ove, @PathVariable("para") Long para, @PathVariable("resp") Long resp,
			@PathVariable("cred") String cred, @PathVariable("com") String com, @PathVariable("compago") String compago,
			@PathVariable("status") String status) throws Exception {
		if (ca.hasAuth(prm, 15, "L", "LISTAGEM / CONSULTA DE TITULOS DE COBRANCA")) {
			// Verifica se tem acesso a todos locais
			Page<FnTitulo> lista;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			String paracob = "S";
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			lista = fnTituloCustomRp.listaFnTitulos(local, caixapref, fpagpref, tipo, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ove, ordem, ordemdir, para, resp, cred, paracob, com, compago, status,
					pageable);
			return new ResponseEntity<Page<FnTitulo>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/titulo/valores/b/{busca}/lc/{local}/cx/{caixapref}/fp/{fpagpref}/tp/{tipo}/dti/{dataini}/dtf/{datafim}/"
			+ "ove/{ove}/para/{para}/resp/{resp}/cred/{cred}/com/{com}/compago/{compago}/status/{status}")
	public ResponseEntity<List<FnTitulo>> listaFnTitulosBuscaValores(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("caixapref") Integer caixapref,
			@PathVariable("fpagpref") String fpagpref, @PathVariable("tipo") String tipo,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("ove") String ove, @PathVariable("para") Long para, @PathVariable("resp") Long resp,
			@PathVariable("cred") String cred, @PathVariable("com") String com, @PathVariable("compago") String compago,
			@PathVariable("status") String status) throws Exception {
		// Verifica se tem acesso a todos locais
		busca = CaracterUtil.buscaContexto(busca);
		String paracob = "S";
		List<FnTitulo> listaValores = fnTituloCustomRp.listaFnTitulosValores(local, caixapref, fpagpref, tipo,
				Date.valueOf(dataini), Date.valueOf(datafim), busca, ove, para, resp, cred, paracob, com, compago,
				status);
		return new ResponseEntity<List<FnTitulo>>(listaValores, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/titulo/valoresperiodo/lc/{local}/cx/{caixapref}/fp/{fpagpref}/tp/{tipo}/ano/{ano}/ove/{ove}/cred/{cred}"
			+ "/com/{com}/compago/{compago}/status/{status}")
	public ResponseEntity<List<BigDecimal>> listaFnTitulosBuscaValoresPeriodo(@PathVariable("local") Long local,
			@PathVariable("caixapref") Integer caixapref, @PathVariable("fpagpref") String fpagpref,
			@PathVariable("tipo") String tipo, @PathVariable("ano") Integer ano, @PathVariable("ove") String ove,
			@PathVariable("cred") String cred, @PathVariable("com") String com, @PathVariable("compago") String compago,
			@PathVariable("status") String status) throws Exception {
		List<BigDecimal> aux = new ArrayList<BigDecimal>();
		String paracob = "X";
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
		}
		// Lista meses
		for (int mes = 0; mes < 12; mes++) {
			java.util.Date menuMesIni = DataUtil.addRemMeses(Date.valueOf(ano + "-01-01"), mes, "A");
			java.util.Date menuMesFim = DataUtil.addRemMeses(Date.valueOf(ano + "-01-31"), mes, "A");
			// Calculos
			Date dataini = new Date(menuMesIni.getTime());
			Date datafim = new Date(menuMesFim.getTime());
			BigDecimal valor = fnTituloCustomRp.retFnTitulosValoresTotais(local, caixapref, fpagpref, tipo, dataini,
					datafim, ove, cred, paracob, com, compago, status);
			// Adiciona itens de caixa PENDENTES (Cartoes)
			BigDecimal vCarts = new BigDecimal(0);
			BigDecimal vTaxas = new BigDecimal(0);
			if (local > 0) {
				vCarts = fnCartaoRp.somaVtotCartaoPrevisaoStatusLocal(local, Date.valueOf("1984-01-01"), datafim, "01");
				vTaxas = fnCartaoRp.somaVtaxaCartaoPrevisaoStatusLocal(local, Date.valueOf("1984-01-01"), datafim,
						"01");
			} else {
				vCarts = fnCartaoRp.somaVtotCartaoPrevisaoStatus(Date.valueOf("1984-01-01"), datafim, "01");
				vTaxas = fnCartaoRp.somaVtaxaCartaoPrevisaoStatus(Date.valueOf("1984-01-01"), datafim, "01");
			}
			if (tipo.equals("R") && vCarts != null) {
				valor = valor.add(vCarts);
			}
			if (tipo.equals("P") && vTaxas != null) {
				valor = valor.add(vTaxas);
			}
			aux.add(valor);
		}
		return new ResponseEntity<List<BigDecimal>>(aux, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/titulo/tp/{tipo}/local/{local}")
	public ResponseEntity<List<FnTitulo>> listaFnTitulosVencidos(@PathVariable("tipo") String tipo,
			@PathVariable("local") Long local) throws Exception {
		if (ca.hasAuth(prm, 15, "L", "LISTAGEM / CONSULTA DE TITULOS DE COBRANCA")) {
			List<FnTitulo> lista = null;
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				if (local > 0) {
					lista = fnTituloRp.listaTitulosVencidosLocal(local, tipo);
				} else {
					lista = fnTituloRp.listaTitulosVencidos(tipo);
				}
			} else {
				lista = fnTituloRp.listaTitulosVencidosLocal(prm.cliente().getCdpessoaemp(), tipo);
			}
			return new ResponseEntity<List<FnTitulo>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/titulo/fatura/mes/{mes}/ano/{ano}/local/{local}")
	public ResponseEntity<BigDecimal> faturaFnTitulos(@PathVariable("mes") Integer mes,
			@PathVariable("ano") Integer ano, @PathVariable("local") Long local) throws Exception {
		if (ca.hasAuth(prm, 15, "L", "LISTAGEM / CONSULTA DE TITULOS DE COBRANCA")) {
			BigDecimal valor = new BigDecimal(0);
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				if (local > 0) {
					valor = fnTituloRp.faturaMesAnoLocal(local, mes, ano);
				} else {
					valor = fnTituloRp.faturaMesAno(mes, ano);
				}
			} else {
				valor = fnTituloRp.faturaMesAnoLocal(prm.cliente().getCdpessoaemp(), mes, ano);
			}
			return new ResponseEntity<BigDecimal>(valor, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/titulo/faturamento/anual/local/{local}/ano/{ano}")
	public ResponseEntity<List<BigDecimal>> listaFnTitulosFaturaAnual(@PathVariable("local") Long local,
			@PathVariable("ano") Integer ano) throws Exception {
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
		}
		List<BigDecimal> aux = new ArrayList<BigDecimal>();
		// Lista meses
		for (int mes = 0; mes < 12; mes++) {
			java.util.Date menuMesIni = DataUtil.addRemMeses(Date.valueOf(ano + "-01-01"), mes, "A");
			java.util.Date menuMesFim = DataUtil.addRemMeses(Date.valueOf(ano + "-01-31"), mes, "A");
			// Calculos
			Date dataini = new Date(menuMesIni.getTime());
			Date datafim = new Date(menuMesFim.getTime());
			BigDecimal valor = new BigDecimal(0);
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				if (local > 0) {
					valor = fnTituloRp.faturaMesAnoLocalPeriodo(local, dataini, datafim);
				} else {
					valor = fnTituloRp.faturaMesAnoPeriodo(dataini, datafim);
				}
			} else {
				valor = fnTituloRp.faturaMesAnoLocalPeriodo(local, dataini, datafim);
			}
			aux.add(valor);
		}
		return new ResponseEntity<List<BigDecimal>>(aux, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/titulo/baixar/emlote")
	public ResponseEntity<?> baixarTitulos(@RequestBody List<FnTitulo> titulos) throws Exception {
		if (ca.hasAuth(prm, 58, "A", "BAIXA TITULOS CONJUNTO")) {
			if (titulos != null) {
				for (FnTitulo t : titulos) {
					BigDecimal vSaldo = t.getVsaldo().add(t.getVdesc()).subtract(t.getVjuro());
					fnTituloService.baixaUnico(t.getVsaldo(), t.getVdesc(), t.getVjuro(), vSaldo, t.getDatabaixa(),
							t.getCdcaixapref().getId(), t);
					// Atualiza caixa e forma pagamento
					fnTituloRp.attCaixaFpagValor(t.getCdcaixapref().getId(), t.getFpagpref(), t.getId());
					// Grava no caixa
					BigDecimal pDesc = MoedaUtil.pJD(t.getVtot(), t.getVdesc());
					BigDecimal pJuro = MoedaUtil.pJD(t.getVtot(), t.getVjuro());
					Time hora = new Time(new java.util.Date().getTime());
					Integer transacao = NumUtil.geraNumAleaInteger();
					FnCxmv fnCxmv = fnCxmvService.regUnico(t.getCdpessoaemp(), t.getCdcaixapref(), t, t.getFpagpref(),
							t.getDatabaixa(), hora, t.getInfo(), t.getVparc(), pDesc, t.getVdesc(), pJuro, t.getVjuro(),
							t.getVsaldo(), transacao, "ATIVO", null, null);
					// SALVA PLANO DE CONTAS DRE -------------------------
					List<FnCxmvDre> fnFnCxmvDreObj = fnDreService.dreTituloLote(t, fnCxmv);
					// VERIFICA SE SEPARA JUROS NA CONFIGURACAO-----------
					fnCxmvService.separaJuros(t, fnCxmv, fnFnCxmvDreObj, null, null, null, "01", 0, transacao);
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/titulo/renegociar/emlote/darbaixa/{darbaixa}")
	public ResponseEntity<?> renegociarTitulos(@RequestBody List<FnTitulo> titulos,
			@PathVariable("darbaixa") String darbaixa) throws Exception {
		if (ca.hasAuth(prm, 59, "A", "RENEGOCIACAO E BAIXA TITULOS CONJUNTO")) {
			if (titulos != null) {
				// Cria for apenas com itens que ja existem
				List<FnTitulo> antigos = new ArrayList<FnTitulo>();
				for (FnTitulo t : titulos) {
					if (t.getId() != null) {
						antigos.add(t);
					}
				}
				Integer transacao = NumUtil.geraNumAleaInteger();
				FnTitulo novoT = new FnTitulo();
				int conta = 0;
				for (FnTitulo t : titulos) {
					if (t.getId() == null) {
						conta++;
					}
				}
				// NOVO TITULO
				for (FnTitulo t : titulos) {
					if (t.getId() == null) {
						// SALVA E FAZ BAIXA SE NECESSARIO
						t.setNegocia("S");
						t.setTransacao(transacao);
						novoT = fnTituloRp.save(t);
						// SALVA RELACIONADOS
						// ATUALIZA ANTIGOS RELACIONADOS
						for (FnTitulo ta : antigos) {
							// MARCA ANTIGOS NAO COBRANCA
							fnTituloRp.attTituloCobranca("N", ta.getId());
							FnTituloRel tr = new FnTituloRel();
							tr.setFntitulo(novoT);
							tr.setFntituloant(ta);
							fnTituloRelRp.save(tr);
							// DREs dos titulos antigos para o novo
							for (FnTituloDre fd : ta.getFntitulodreitem()) {
								fnDreService.dreFnTituloNegocia(novoT, fd);
							}
							// Centro de custos
							fnCcustoService.ccFnTituloCc(ta, novoT);
						}
						if (darbaixa.equals("S")) {
							fnTituloService.baixaUnico(t.getVsaldo(), new BigDecimal(0), new BigDecimal(0),
									t.getVsaldo(), t.getDatabaixa(), t.getCdcaixapref().getId(), t);
							Integer transacaoCx = NumUtil.geraNumAleaInteger();
							// Grava no caixa
							BigDecimal pDesc = MoedaUtil.pJD(t.getVtot(), t.getVdesc());
							BigDecimal pJuro = MoedaUtil.pJD(t.getVtot(), t.getVjuro());
							Time hora = new Time(new java.util.Date().getTime());
							FnCxmv fnCxmv = fnCxmvService.regUnico(t.getCdpessoaemp(), t.getCdcaixapref(), t,
									t.getFpagpref(), t.getDatabaixa(), hora, t.getInfo(), t.getVparc(), pDesc,
									t.getVdesc(), pJuro, t.getVjuro(), t.getVsaldo(), transacaoCx, "ATIVO", null, null);
							List<FnCxmvDre> fnFnCxmvDreObj = new ArrayList<FnCxmvDre>();
							for (FnTitulo ta : antigos) {
								for (FnTituloDre td : ta.getFntitulodreitem()) {
									FnCxmvDre fn = fnDreService.dreFnCxmv(fnCxmv, td, t);
									fnFnCxmvDreObj.add(fn);
								}
							}
							// VERIFICA SE SEPARA JUROS NA CONFIGURACAO-------------------------------
							fnCxmvService.separaJuros(t, fnCxmv, fnFnCxmvDreObj, null, null, null, "02", conta,
									transacaoCx);
						}
					}
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/titulo/renegociar/unifica")
	public ResponseEntity<FnTitulo> renegociarTitulosUnifica(@RequestBody List<FnTitulo> titulos) throws Exception {
		FnTitulo novoT = new FnTitulo();
		if (ca.hasAuth(prm, 59, "A", "RENEGOCIACAO E BAIXA TITULOS CONJUNTO")) {
			if (titulos != null) {
				// Cria for apenas com itens que ja existem
				List<FnTitulo> antigos = new ArrayList<FnTitulo>();
				for (FnTitulo t : titulos) {
					if (t.getId() != null) {
						antigos.add(t);
					}
				}
				Integer transacao = NumUtil.geraNumAleaInteger();
				// NOVO TITULO
				for (FnTitulo t : titulos) {
					if (t.getId() == null) {
						// SALVA E FAZ BAIXA SE NECESSARIO
						t.setNegocia("S");
						t.setTransacao(transacao);
						novoT = fnTituloRp.save(t);
						// SALVA RELACIONADOS
						// ATUALIZA ANTIGOS RELACIONADOS
						for (FnTitulo ta : antigos) {
							// MARCA ANTIGOS NAO COBRANCA
							fnTituloRp.attTituloCobranca("N", ta.getId());
							FnTituloRel tr = new FnTituloRel();
							tr.setFntitulo(novoT);
							tr.setFntituloant(ta);
							fnTituloRelRp.save(tr);
							// DREs dos titulos antigos para o novo
							for (FnTituloDre fd : ta.getFntitulodreitem()) {
								fnDreService.dreFnTituloNegocia(novoT, fd);
							}
							// Centro de custos
							fnCcustoService.ccFnTituloCc(ta, novoT);
						}
					}
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<FnTitulo>(novoT, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/titulo/renegociar/estornar/{id}")
	public ResponseEntity<?> renegociarEstornarTitulos(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 59, "A", "ESTORNO DE NEGOCIACAO DE TITULOS")) {
			FnTitulo titulo = fnTituloRp.getById(id);
			// Verifica se ja foi movimentado
			Integer verificaQtd = fnTituloRp.verificaTitulosMovimentadosTransacao(titulo.getTransacao());
			if (verificaQtd == 0) {
				for (FnTituloRel t : titulo.getFntitulorelitem()) {
					fnTituloRp.attTituloCobranca("S", t.getFntituloant().getId());
				}
				// Remove item por item - Para titulos com mais vinculos
				for (FnTitulo t : fnTituloRp.listaTitulosTransacao(titulo.getTransacao())) {
					for (FnTituloRel tr : t.getFntitulorelitem()) {
						fnTituloRelRp.delete(tr);
					}
					// Por fim, remove titulo
					fnTituloRp.delete(t);
				}
				fnTituloRp.delete(titulo);
			} else {
				// Aviso de movimento
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/titulo/emailaviso")
	public ResponseEntity<?> enviaEmailAviso(@RequestBody List<FnTitulo> titulos) throws Exception {
		if (ca.hasAuth(prm, 62, "A", "ENVIO DE E-MAIL DE COBRANCA")) {
			if (titulos != null) {
				emailService.enviaEmailCobranca(titulos);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/titulo/credito/local/{local}/pessoa/{pessoa}")
	public ResponseEntity<BigDecimal> fnTituloCreditosPessoa(@PathVariable("local") Long local,
			@PathVariable("pessoa") Long pessoa) {
		if (ca.hasAuth(prm, 15, "L", "CONSULTA DE CREDITOS PESSOA")) {
			BigDecimal creditos = fnTituloRp.valorCreditoPessoa(local, pessoa);
			return new ResponseEntity<BigDecimal>(creditos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
