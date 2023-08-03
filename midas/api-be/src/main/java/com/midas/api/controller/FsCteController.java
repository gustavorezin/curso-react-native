package com.midas.api.controller;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.midas.api.constant.MidasConfig;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.AuxDados;
import com.midas.api.tenant.entity.CdCert;
import com.midas.api.tenant.entity.CdCidade;
import com.midas.api.tenant.entity.CdCteCfg;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdVeiculo;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsCteEvento;
import com.midas.api.tenant.entity.FsCteInf;
import com.midas.api.tenant.entity.FsCteInfCarga;
import com.midas.api.tenant.entity.FsCteInfDocEmi;
import com.midas.api.tenant.entity.FsCteInfDocEmiCte;
import com.midas.api.tenant.entity.FsCteInfNfe;
import com.midas.api.tenant.entity.FsCteMan;
import com.midas.api.tenant.entity.FsCteManCons;
import com.midas.api.tenant.entity.FsCteMot;
import com.midas.api.tenant.fiscal.GeraXMLCTe;
import com.midas.api.tenant.fiscal.service.FsCteEnvioService;
import com.midas.api.tenant.fiscal.service.FsCteGerarService;
import com.midas.api.tenant.fiscal.service.FsCteService;
import com.midas.api.tenant.fiscal.service.FsNfeService;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.fiscal.util.GeraChavesUtil;
import com.midas.api.tenant.repository.CdCertRepository;
import com.midas.api.tenant.repository.CdCidadeRepository;
import com.midas.api.tenant.repository.CdCteCfgRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdVeiculoRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.repository.FsCteEventoRepository;
import com.midas.api.tenant.repository.FsCteInfCargaRepository;
import com.midas.api.tenant.repository.FsCteInfDocEmiCteRepository;
import com.midas.api.tenant.repository.FsCteInfDocEmiRepository;
import com.midas.api.tenant.repository.FsCteInfNfeRepository;
import com.midas.api.tenant.repository.FsCteInfRepository;
import com.midas.api.tenant.repository.FsCteManConsRepository;
import com.midas.api.tenant.repository.FsCteManRepository;
import com.midas.api.tenant.repository.FsCteMotRepository;
import com.midas.api.tenant.repository.FsCteRepository;
import com.midas.api.tenant.repository.FsCustomRepository;
import com.midas.api.tenant.service.EmailService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.LerArqUtil;

@RestController
@RequestMapping("/private/fs")
public class FsCteController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FsCteRepository fsCteRp;
	@Autowired
	private FsCustomRepository fsCustomRp;
	@Autowired
	private FsCteService fsCteService;
	@Autowired
	private FsCteGerarService fsCteGerarService;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private FsCteInfNfeRepository fsCteInfNfeRp;
	@Autowired
	private FsCteInfDocEmiRepository fsCteInfDocEmiRp;
	@Autowired
	private FsCteInfDocEmiCteRepository fsCteInfDocEmiCteRp;
	@Autowired
	private FsCteInfCargaRepository fsCteInfCargaRp;
	@Autowired
	private FsCteInfRepository fsCteInfRp;
	@Autowired
	private CdCidadeRepository cdCidadeRp;
	@Autowired
	private FsCteEventoRepository fsCteEventoRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdVeiculoRepository cdVeiculoRp;
	@Autowired
	private FsCteMotRepository fsCteMotRp;
	@Autowired
	private GeraXMLCTe geraXMLCTe;
	@Autowired
	private CdCertRepository cdCertRp;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private FsCteEnvioService fsCteEnvioService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private FsCteManConsRepository cteManConsRp;
	@Autowired
	private FsCteManRepository fsCteManRp;
	@Autowired
	private CdCteCfgRepository cdCteCfgRp;
	@Autowired
	private FsNfeService fsNfeService;

	// DOCUMENTOS FISCAIS - CTE****************************************
	@RequestAuthorization
	@GetMapping("/cte/{id}")
	public ResponseEntity<FsCte> fsCte(@PathVariable("id") Long id) {
		Optional<FsCte> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = fsCteRp.findById(id);
		} else {
			obj = fsCteRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<FsCte>(obj.get(), HttpStatus.OK);
	}

	@DeleteMapping("/cte/{id}/tipo/{tipo}")
	public ResponseEntity<?> removerFscte(@PathVariable("id") Long id, @PathVariable("tipo") String tipo) {
		Integer acesso = 42;
		FsCte fscte = fsCteRp.findById(id).get();
		if (tipo.equals("E")) {
			acesso = 43;
		}
		if (ca.hasAuth(prm, acesso, "R", "ID " + id)) {
			fsCteRp.deleteById(id);
			// Desvincula, titulos e manifesto
			fnTituloRp.attParaDocCobDocFiscal("N", 0, "", 0L, id);
			fsCteManRp.updateSituacaoImpChave("N", fscte.getChaveac());
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cte/remover/emlote/tipo/{tipo}")
	public ResponseEntity<?> removerFscteEmLote(@RequestBody List<FsCte> fsCte, @PathVariable("tipo") String tipo) {
		Integer acesso = 16;
		if (tipo.equals("E")) {
			acesso = 18;
		}
		if (ca.hasAuth(prm, acesso, "R", "REMOCAO DE CTES EM LOTE")) {
			for (FsCte n : fsCte) {
				fsCteRp.deleteById(n.getId());
				fnTituloRp.attParaDocCobDocFiscal("N", 0, "", 0L, n.getId());
				fsCteManRp.updateSituacaoImpChave("N", n.getChaveac());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cte/man/marcarimportado/{id}/st/{st}")
	public ResponseEntity<?> atualizarFsCteManImportado(@PathVariable("id") Long id, @PathVariable("st") String st)
			throws Exception {
		if (ca.hasAuth(prm, 42, "A", "ID MARCAR MANIFESTO COMO IMPORTADO " + id)) {
			fsCteManRp.updateSituacaoImpId(st, id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cte/man/marcarimportado/emlote/st/{st}")
	public ResponseEntity<?> atualizarFsCteManImportadoEmLte(@RequestBody List<FsCteMan> fsCteMan,
			@PathVariable("st") String st) throws Exception {
		if (ca.hasAuth(prm, 42, "A", "ID MARCAR MANIFESTO COMO IMPORTADO EM LOTE")) {
			for (FsCteMan m : fsCteMan) {
				fsCteManRp.updateSituacaoImpId(st, m.getId());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cte/p/{pagina}/b/{busca}/oensai/{oensai}/o/{ordem}/d/{ordemdir}/lc/{local}/tp/{tipo}/ipp/{itenspp}/dti/{dataini}/dtf/{datafim}/st/{st}/uffim/{uffim}")
	public ResponseEntity<Page<FsCte>> listaFsCtesBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("oensai") String oensai,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("local") Long local, @PathVariable("tipo") String tipo, @PathVariable("itenspp") int itenspp,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("st") String st, @PathVariable("uffim") String uffim) throws Exception {
		if (ca.hasAuth(prm, 43, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<FsCte> lista = fsCustomRp.listaFsCte(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo, busca,
					uffim, st, oensai, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<FsCte>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cte/entrada/p/{pagina}/b/{busca}/oensai/{oensai}/o/{ordem}/d/{ordemdir}/lc/{local}/tp/{tipo}/ipp/{itenspp}/dti/{dataini}/dtf/{datafim}/st/{st}/uffim/{uffim}")
	public ResponseEntity<Page<FsCte>> listaFsCtesEntBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("oensai") String oensai,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("local") Long local, @PathVariable("tipo") String tipo, @PathVariable("itenspp") int itenspp,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("st") String st, @PathVariable("uffim") String uffim) throws Exception {
		if (ca.hasAuth(prm, 42, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<FsCte> lista = fsCustomRp.listaFsCte(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo, busca,
					uffim, st, oensai, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<FsCte>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cte/valores/b/{busca}/oensai/{oensai}/lc/{local}/tp/{tipo}/dti/{dataini}/dtf/{datafim}/st/{st}/uffim/{uffim}")
	public ResponseEntity<List<FsCte>> listaFsCtesValores(@PathVariable("busca") String busca,
			@PathVariable("oensai") String oensai, @PathVariable("local") Long local, @PathVariable("tipo") String tipo,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("st") String st, @PathVariable("uffim") String uffim) throws Exception {
		if (ca.hasAuth(prm, 43, "L", "LISTAGEM / CONSULTA VALORES")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<FsCte> lista = fsCustomRp.listaFsCteValores(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo,
					st, oensai, busca, uffim);
			return new ResponseEntity<List<FsCte>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cte/faturamento/anual/local/{local}/ano/{ano}/tp/{tipo}")
	public ResponseEntity<List<BigDecimal>> listaFsNfeFaturaAnual(@PathVariable("local") Long local,
			@PathVariable("ano") Integer ano, @PathVariable("tipo") String tipo) throws Exception {
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
					valor = fsCteRp.faturaMesAnoLocalPeriodo(local, dataini, datafim, tipo, 100, 150);
				} else {
					valor = fsCteRp.faturaMesAnoPeriodo(dataini, datafim, tipo, 100, 150);
				}
			} else {
				valor = fsCteRp.faturaMesAnoLocalPeriodo(local, dataini, datafim, tipo, 100, 150);
			}
			aux.add(valor);
		}
		return new ResponseEntity<List<BigDecimal>>(aux, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cte/gerar/cte/lc/{local}/cfop/{cfop}/rem/{rem}/dest/{dest}/mot/{mot}/veic/{veic}")
	public ResponseEntity<FsCte> gerarCte(@PathVariable("local") Long local, @PathVariable("cfop") Integer cfop,
			@PathVariable("rem") Long rem, @PathVariable("dest") Long dest, @PathVariable("mot") Long mot,
			@PathVariable("veic") Integer veic) throws Exception {
		FsCte cte = null;
		if (ca.hasAuth(prm, 43, "N", "GERAR CTE")) {
			String ambiente = prm.cliente().getSiscfg().getSis_amb_cte();
			cte = fsCteGerarService.gerarCte(ambiente, local, cfop, rem, dest, mot, veic);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<FsCte>(cte, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/cte/{id}/grupes/{idgrupes}/importatransportadora")
	public ResponseEntity<?> importaTransportadora(@PathVariable("id") Long id,
			@PathVariable("idgrupes") Integer idgrupes) throws Exception {
		if (ca.hasAuth(prm, 42, "C", "ID IMPORTA CADASTRO TRANSPORTADORA CT-E" + id)) {
			if (fsCteService.importaTransportadora(id, idgrupes) == "ok") {
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cte/{id}/importacobranca")
	public ResponseEntity<?> importaCobranca(@RequestBody List<FnTitulo> fnTitulos, @PathVariable("id") Long id)
			throws Exception {
		if (ca.hasAuth(prm, 42, "C", "ID IMPORTA COBRANCA CT-E " + id)) {
			String retorno = fsCteService.importaCobranca(fnTitulos, id);
			if (retorno == "ok") {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			if (retorno == "naocadastrado") {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
			if (retorno == "") {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return null;
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/cte/{id}/devolvecobranca")
	public ResponseEntity<?> devolveCobranca(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 42, "C", "ID DEVOLVE COBRANCA CT-E")) {
			Optional<FsCte> n = fsCteRp.findById(id);
			Integer qtdmovs = fnTituloRp.verificaTitulosMovimentadosNota(String.valueOf(n.get().getModelo()),
					n.get().getId(), n.get().getNct(), "S");
			if (qtdmovs == 0) {
				fsCteRp.entradaCobSt("N", id);
				List<FnTitulo> ts = fnTituloRp.titulosNota(String.valueOf(n.get().getModelo()), n.get().getId(),
						n.get().getNct(), "S");
				for (FnTitulo f : ts) {
					fnTituloRp.delete(f);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return null;
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cte")
	public ResponseEntity<FsCte> atualizarFsCte(@RequestBody FsCte fsCte) throws Exception {
		FsCte cte = null;
		if (ca.hasAuth(prm, 43, "A", "ID " + fsCte.getId())) {
			String dataAAMM = DataUtil.anoMesAAMM(fsCte.getDhemi());
			Integer nct = fsCte.getNct();
			String chaveac = GeraChavesUtil
					.geraChavePadrao(fsCte.getCdpessoaemp().getCdestado().getId(), dataAAMM,
							fsCte.getCdpessoaemp().getCpfcnpj(), 57, fsCte.getSerie(), 1, nct, nct, "CTe")
					.replace("CTe", "");
			// DIGITO CHAVE--------------
			String cDv = chaveac.substring(43, 44);
			fsCte.setCdv(Integer.valueOf(cDv));
			fsCte.setChaveac(chaveac);
			cte = fsCteRp.save(fsCte);
			int ctecfg = fsCte.getCdctecfg().getId();
			CdCteCfg cdCteCfg = cdCteCfgRp.getById(ctecfg);
			String cfop = cdCteCfg.getCfop();
			System.out.println(cfop);
			fsCte.setCfop(cfop);
			// CALCULOS
			fsCteService.calculoIcms(cte);
			// MONTA INFO ADD
			FsUtil.montaInfoAddCte(cte);
			cte = fsCteRp.save(fsCte);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<FsCte>(cte, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cte/atualizaorigem/cmunini/{cmunini}/cmunfim/{cmunfim}")
	public ResponseEntity<FsCte> atualizarFsCteOrigem(@RequestBody FsCte fsCte, @PathVariable("cmunini") String cmunini,
			@PathVariable("cmunfim") String cmunfim) throws Exception {
		FsCte cte = null;
		if (ca.hasAuth(prm, 43, "A", "ID ATUALIZAR ORIGEM / DESTINO " + fsCte.getId())) {
			CdCidade cini = cdCidadeRp.findByIbge(cmunini);
			CdCidade cfim = cdCidadeRp.findByIbge(cmunfim);
			fsCte.setXmunini(cini.getNome());
			fsCte.setXmunfim(cfim.getNome());
			cte = fsCteRp.save(fsCte);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<FsCte>(cte, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cte/atualizapart/id/{id}/tipo/{tipo}")
	public ResponseEntity<?> atualizarFsCtePart(@RequestBody CdPessoa cdPessoa, @PathVariable("id") Long id,
			@PathVariable("tipo") String tipo) throws Exception {
		if (ca.hasAuth(prm, 42, "A", "ID ATUALIZAR PARTICIPANTE " + id)) {
			fsCteGerarService.part(cdPessoa, tipo, id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/cte/atualiza/cte/{id}/dhsaient/{dhsaient}/cfop/{cfop}")
	public ResponseEntity<?> atualizarFsCteDataEntSai(@PathVariable("id") Long id,
			@PathVariable("dhsaient") String dhsaient, @PathVariable("cfop") String cfop) throws Exception {
		if (ca.hasAuth(prm, 42, "A", "ID ATUALIZAR DATA DE ENTRADA-SAIDA E CFOP " + id)) {
			fsCteRp.updateDataEntSaiCfop(Date.valueOf(dhsaient), cfop, id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cte/conferida")
	public ResponseEntity<?> atualizarFsCteConferida(@RequestParam("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 42, "A", "ID " + id)) {
			FsCte fscte = fsCteRp.getById(id);
			fscte.setSt_transp("S");
			fscte.setSt_cob("S");
			fsCteRp.save(fscte);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cte/man/{id}")
	public ResponseEntity<FsCteMan> fsCteMan(@PathVariable("id") Long id) {
		Optional<FsCteMan> obj = fsCteManRp.findById(id);
		return new ResponseEntity<FsCteMan>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/cte/downloadxmlctearquivo")
	public ResponseEntity<byte[]> downloadXmlCteArquivo(HttpServletRequest request,
			@RequestParam("idlocal") Long idlocal, @RequestParam("nsu") String nsu) throws Exception {
		if (ca.hasAuth(prm, 42, "Y", "DOWNLOAD ARQUIVO XML CT-E " + nsu)) {
			String ambiente = prm.cliente().getSiscfg().getSis_amb_cte();
			String ret[] = fsCteEnvioService.dwnloadXmlCTe(idlocal, ambiente, nsu, request);
			String chaveprotCTe = "";
			if (!ret[2].equals("")) {
				chaveprotCTe = FsUtil.eXmlDet(ret[2], "protCTe", 0, "chCTe");
			}
			if (!chaveprotCTe.equals("")) {
				byte[] contents = ret[2].getBytes();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.parseMediaType("application/xml"));
				headers.set("Content-Disposition", "attachment; filename=CTe" + chaveprotCTe + "-proc.xml");
				return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestAuthorization
	@GetMapping("/cte/man/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/lc/{local}/ipp/{itenspp}/dti/{dataini}/dtf/{datafim}/st/{st}/stimp/{stimp}")
	public ResponseEntity<Page<FsCteMan>> listaFsCteMansManBuscaMan(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("local") Long local,
			@PathVariable("itenspp") int itenspp, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("st") String st, @PathVariable("stimp") String stimp)
			throws Exception {
		if (ca.hasAuth(prm, 78, "L", "LISTAGEM / CONSULTA MANIFESTO")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<FsCteMan> lista = fsCustomRp.listaFsCteMan(local, Date.valueOf(dataini), Date.valueOf(datafim), busca,
					st, stimp, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<FsCteMan>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cte/man/valores/b/{busca}/lc/{local}/dti/{dataini}/dtf/{datafim}/st/{st}/stimp/{stimp}")
	public ResponseEntity<List<FsCteMan>> listaFsNfesBuscaManValores(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("st") String st, @PathVariable("stimp") String stimp)
			throws Exception {
		if (ca.hasAuth(prm, 78, "L", "LISTAGEM / CONSULTA VALORES MANIFESTO")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<FsCteMan> lista = fsCustomRp.listaFsCteManValores(local, Date.valueOf(dataini), Date.valueOf(datafim),
					busca, st, stimp);
			return new ResponseEntity<List<FsCteMan>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/cte/mancons/{idlocal}")
	public ResponseEntity<FsCteManCons> fsCteManConsLocal(@PathVariable("idlocal") Long idlocal) {
		FsCteManCons obj = cteManConsRp.findByLocal(idlocal);
		if (obj == null) {
			CdPessoa local = cdPessoaRp.getById(idlocal);
			FsCteManCons c = new FsCteManCons();
			c.setCdpessoaemp(local);
			c.setData(new java.sql.Date(System.currentTimeMillis()));
			c.setHora(DataUtil.addRemMinTime(new Time(System.currentTimeMillis()), mc.tempobuscaman + 1, "R"));
			obj = cteManConsRp.save(c);
		}
		return new ResponseEntity<FsCteManCons>(obj, HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/cte/distcte")
	public ResponseEntity<String[]> disNfe(HttpServletRequest request, @RequestParam("idlocal") Long idlocal)
			throws Exception {
		// Controle modulo
		String ambiente = prm.cliente().getSiscfg().getSis_amb_cte();
		if (ca.hasAuthModulo(prm, 18)) {
			// Autoridade
			if (ca.hasAuth(prm, 42, "Y", "CONSULTA CT-E DIST. CONTRA CNPJ")) {
				String ret[] = fsCteService.consultaDist(idlocal, ambiente, request);
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		}
	}

	@RequestAuthorization
	@PostMapping("/cte/distcteauto")
	public ResponseEntity<String[]> disCteAuto(HttpServletRequest request) throws Exception {
		// Controle modulo
		String ambiente = prm.cliente().getSiscfg().getSis_amb_cte();
		if (ca.hasAuthModulo(prm, 18) && prm.cliente().getSiscfg().isSis_busca_auto_cte() == true) {
			// Autoridade
			if (ca.hasAuth(prm, 42, "Y", "CONSULTA CT-E DIST. CONTRA CNPJ")) {
				String ret[] = new String[4];
				for (CdCert cp : cdCertRp.findByStatus("ATIVO")) {
					ret = fsCteService.consultaDist(cp.getCdpessoaemp().getId(), ambiente, request);
				}
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cte/downloadxmlcte")
	public ResponseEntity<String[]> downloadXmlCte(HttpServletRequest request, @RequestParam("idlocal") Long idlocal,
			@RequestParam("nsu") String nsu) throws Exception {
		if (ca.hasAuth(prm, 42, "Y", "DOWNLOAD XML CT-E " + nsu)) {
			String ambiente = prm.cliente().getSiscfg().getSis_amb_cte();
			String ret[] = fsCteEnvioService.dwnloadXmlCTe(idlocal, ambiente, nsu, request);
			ret[3] = "ok";
			String chaveprotCTe = "";
			if (!ret[2].equals("")) {
				chaveprotCTe = FsUtil.eXmlDet(ret[2], "protCTe", 0, "chCTe");
			}
			if (!chaveprotCTe.equals("")) {
				ret[3] = fsCteService.importarXMLCteChave(ret[2], idlocal);
				if (ret[3].equals("ok") || ret[3].equals("jaimportado")) {
					fsCteManRp.updateSituacaoImp("S", nsu);
				}
			}
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cte/man/pendente/local/{local}")
	public ResponseEntity<List<FsCteMan>> listaFsCteManSt(@PathVariable("local") Long local) throws Exception {
		if (ca.hasAuth(prm, 42, "L", "LISTAGEM / CONSULTA MANIFESTO")) {
			List<FsCteMan> lista = null;
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				if (local > 0) {
					lista = fsCteManRp.listaCtemanManifestoStImpLocal(local);
				} else {
					lista = fsCteManRp.listaCtemanManifestoStImp();
				}
			} else {
				lista = fsCteManRp.listaCtemanManifestoStImpLocal(prm.cliente().getCdpessoaemp());
			}
			return new ResponseEntity<List<FsCteMan>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cte/pendente/local/{local}")
	public ResponseEntity<List<FsCte>> listaFsCtesSt(@PathVariable("local") Long local) throws Exception {
		if (ca.hasAuth(prm, 42, "L", "LISTAGEM / CONSULTA CT-E")) {
			List<FsCte> lista = null;
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				if (local > 0) {
					lista = fsCteRp.listaCteStImpLocal(local);
				} else {
					lista = fsCteRp.listaCtetImp();
				}
			} else {
				lista = fsCteRp.listaCteStImpLocal(prm.cliente().getCdpessoaemp());
			}
			return new ResponseEntity<List<FsCte>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/cte/addctemot/veic/{idveic}/mot/{idmot}/cte/{idcte}")
	public ResponseEntity<?> addFsCteMot(@PathVariable("idveic") Integer idveic, @PathVariable("idmot") Long idmot,
			@PathVariable("idcte") Long idcte) throws Exception {
		if (ca.hasAuth(prm, 43, "N", "ID ADICIONAR MOTORISTA E VEICULO REFERENCIADO " + idcte + " - " + idmot)) {
			FsCte fscte = fsCteRp.findById(idcte).get();
			// veiculo
			CdVeiculo v = cdVeiculoRp.findById(idveic).get();
			CdPessoa p = cdPessoaRp.findById(idmot).get();
			fsCteGerarService.mot(fscte, v, p);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cte/remctemot/{id}")
	public ResponseEntity<?> remFsCteMot(@PathVariable("id") Long id, @RequestBody FsCteMot fsCteMot) throws Exception {
		if (ca.hasAuth(prm, 43, "R", "ID REMOVER MOTORISTA REFERENCIADO " + id)) {
			fsCteMotRp.delete(fsCteMot);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cte/uploadcteimporta")
	public ResponseEntity<String> uploadCteImporta(@RequestParam("idlocal") Long idlocal,
			@RequestParam("file") MultipartFile file) throws Exception {
		if (ca.hasAuth(prm, 42, "C", "IMPORTA XML CT-E")) {
			try {
				String retorno = "ok";
				File xmlimportado = LerArqUtil.multipartToFile(file, file.getOriginalFilename());
				retorno = fsCteService.importarXMLCte(xmlimportado, idlocal);
				xmlimportado.delete();
				return new ResponseEntity<String>(retorno, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cte/uploadcteimportamultiplos")
	public ResponseEntity<List<AuxDados>> uploadCteImportaMultiplos(@RequestParam("idlocal") Long idlocal,
			@RequestParam("files") MultipartFile[] files) throws Exception {
		if (ca.hasAuth(prm, 42, "C", "IMPORTA MULTIPLOS XML CT-E")) {
			try {
				List<AuxDados> aux = new ArrayList<AuxDados>();
				for (int i = 0; i < files.length; i++) {
					File xmlimportado = LerArqUtil.multipartToFile(files[i], files[i].getOriginalFilename());
					String[] resposta = fsCteService.importarXMLCteMultiplos(xmlimportado, idlocal);
					AuxDados a = new AuxDados();
					a.setCampo1(resposta[0]);
					a.setCampo2(resposta[1]);
					a.setCampo3(resposta[2]);
					a.setCampo4(resposta[3]);
					a.setCampo5(resposta[4]);
					a.setCampo6(resposta[5]);
					aux.add(a);
					xmlimportado.delete();
				}
				return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<List<AuxDados>>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// DOC ITEM ****************************************
	@RequestAuthorization
	@GetMapping("/cteinfnfe/{id}")
	public ResponseEntity<FsCteInfNfe> fsCteInfNfe(@PathVariable("id") Long id) throws InterruptedException {
		Optional<FsCteInfNfe> obj = fsCteInfNfeRp.findById(id);
		return new ResponseEntity<FsCteInfNfe>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cteinfnfe/{id}")
	public ResponseEntity<?> addFsCteInfNfe(@PathVariable("id") Long id, @RequestBody FsCteInfNfe fsCteInfNfe)
			throws Exception {
		if (ca.hasAuth(prm, 43, "A", "DOC " + fsCteInfNfe.getChave())) {
			FsCteInfNfe chave = fsCteInfNfeRp.findByCteAndChave(id, fsCteInfNfe.getChave());
			if (chave == null) {
				FsCteInf cte = fsCteInfRp.getById(id);
				fsCteInfNfe.setFscteinf(cte);
				fsCteInfNfeRp.save(fsCteInfNfe);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cteinfnfe/{id}")
	public ResponseEntity<?> removerFsCteInfNfe(@PathVariable("id") Long id, @RequestBody FsCteInfNfe fsCteInfNfe)
			throws Exception {
		if (ca.hasAuth(prm, 43, "R", "ID DOC " + fsCteInfNfe.getId() + " - " + fsCteInfNfe.getChave())) {
			fsCteInfNfeRp.delete(fsCteInfNfe);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	// DOC ITEM ANTERIOR ****************************************
	@RequestAuthorization
	@GetMapping("/cteinfdocemi/{id}")
	public ResponseEntity<FsCteInfDocEmi> fsCteInfDocEmi(@PathVariable("id") Long id) throws InterruptedException {
		Optional<FsCteInfDocEmi> obj = fsCteInfDocEmiRp.findById(id);
		return new ResponseEntity<FsCteInfDocEmi>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cteinfdocemi/{id}")
	public ResponseEntity<?> addFsCteInfDocEmi(@PathVariable("id") Long id, @RequestBody FsCteInfDocEmi fsCteInfDocEmi)
			throws Exception {
		if (ca.hasAuth(prm, 43, "A", "DOC EMI " + id)) {
			FsCteInf cteinf = fsCteInfRp.getById(id);
			fsCteInfDocEmi.setFscteinf(cteinf);
			fsCteInfDocEmiRp.save(fsCteInfDocEmi);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cteinfdocemi/emi")
	public ResponseEntity<?> removerFsCteInfDocEmi(@RequestBody FsCteInfDocEmi fsCteInfDocEmi) throws Exception {
		if (ca.hasAuth(prm, 43, "R", "ID DOC " + fsCteInfDocEmi.getId() + " - " + fsCteInfDocEmi.getXnome())) {
			fsCteInfDocEmiRp.delete(fsCteInfDocEmi);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cteinfdocemi/{id}/chave/{chave}")
	public ResponseEntity<?> addFsCteInfDocEmiCte(@PathVariable("id") Long id, @PathVariable("chave") String chave,
			@RequestBody FsCteInfDocEmi fsCteInfDocEmi) throws Exception {
		if (ca.hasAuth(prm, 43, "A", "DOC EMI CTE " + chave)) {
			FsCteInfDocEmi chaveacesso = fsCteInfDocEmiRp.findByCteAndChave(id, chave);
			if (chaveacesso == null) {
				FsCteInfDocEmiCte doccte = new FsCteInfDocEmiCte();
				doccte.setChave(chave);
				doccte.setFscteinfdocemi(fsCteInfDocEmi);
				fsCteInfDocEmiCteRp.save(doccte);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cteinfdocemi/cte")
	public ResponseEntity<?> removerFsCteInfDocEmiCte(@RequestBody FsCteInfDocEmiCte fsCteInfDocEmiCte)
			throws Exception {
		if (ca.hasAuth(prm, 43, "R", "ID DOC " + fsCteInfDocEmiCte.getId() + " - " + fsCteInfDocEmiCte.getChave())) {
			fsCteInfDocEmiCteRp.delete(fsCteInfDocEmiCte);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	// CARGA ITEM ****************************************
	@RequestAuthorization
	@GetMapping("/cteinfcarga/{id}")
	public ResponseEntity<FsCteInfCarga> fsCteInfCarga(@PathVariable("id") Long id) throws InterruptedException {
		Optional<FsCteInfCarga> obj = fsCteInfCargaRp.findById(id);
		return new ResponseEntity<FsCteInfCarga>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cteinfcarga/{id}")
	public ResponseEntity<?> addFsCteInfCarga(@PathVariable("id") Long id, @RequestBody FsCteInfCarga fsCteInfCarga)
			throws Exception {
		if (ca.hasAuth(prm, 43, "A", "CARGA " + fsCteInfCarga.getTpmed())) {
			FsCteInfCarga unidade = fsCteInfCargaRp.findByCteAndUnidade(id, fsCteInfCarga.getCunid());
			if (unidade == null) {
				String tpmed = null;
				String cunid = fsCteInfCarga.getCunid();
				if (cunid.equals("00"))
					tpmed = "M3";
				if (cunid.equals("01"))
					tpmed = "KG";
				if (cunid.equals("02"))
					tpmed = "TON";
				if (cunid.equals("03"))
					tpmed = "UNIDADE";
				if (cunid.equals("04"))
					tpmed = "LITRO";
				if (cunid.equals("05"))
					tpmed = "MMBTU";
				fsCteInfCarga.setTpmed(tpmed);
				FsCteInf cte = fsCteInfRp.getById(id);
				fsCteInfCarga.setFscteinf(cte);
				fsCteInfCargaRp.save(fsCteInfCarga);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cteinfcarga/{id}")
	public ResponseEntity<?> removerFsCteInfCarga(@PathVariable("id") Long id, @RequestBody FsCteInfCarga fsCteInfCarga)
			throws Exception {
		if (ca.hasAuth(prm, 43, "R", "ID CARGA " + fsCteInfCarga.getId() + " - " + fsCteInfCarga.getTpmed())) {
			fsCteInfCargaRp.delete(fsCteInfCarga);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/cte/enviacte/{id}")
	public ResponseEntity<String[]> enviarCte(HttpServletRequest request, @PathVariable("id") Long id)
			throws Exception {
		if (ca.hasAuth(prm, 73, "E", "ID Envio CTE " + id)) {
			FsCte c = fsCteRp.findById(id).get();
			String ambiente = prm.cliente().getSiscfg().getSis_amb_cte();
			c.setTpamb(Integer.valueOf(ambiente));
			fsCteRp.save(c);
			c = fsCteRp.findById(id).get();
			String xml = geraXMLCTe.geraXMLCTe(c, mc);
			String ret[] = fsCteEnvioService.envioCTe(c, xml, request);
			// Se Autorizado------------------
			if (ret[0].equals("100")) {
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			} else {
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/cte/envioemail/{id}")
	public ResponseEntity<?> envioCTeEmail(HttpServletRequest request, @PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 43, "E", "ID Documento para Envio por E-mail CT-e " + id)) {
			emailService.enviaCTeEmail(request, id, null);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/cte/envioemail/{id}/email/{email}")
	public ResponseEntity<?> envioCTeEmailOutros(HttpServletRequest request, @PathVariable("id") Long id,
			@PathVariable("email") String email) throws Exception {
		if (ca.hasAuth(prm, 43, "E", "ID Documento para Envio por E-mail CT-e " + id)) {
			emailService.enviaCTeEmail(request, id, email);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cte/envioxmlemail")
	public ResponseEntity<?> envioCTeEmailXML(HttpServletRequest request, @RequestParam("local") Long local,
			@RequestParam("dtini") String dtini, @RequestParam("dtfim") String dtfim,
			@RequestParam("dests") String dests, @RequestParam("info") String info,
			@RequestParam("enviopdf") String enviopdf, @RequestParam("tipo") String tipo) throws Exception {
		if (ca.hasAuth(prm, 43, "E", "XML de Arquivos de CT-e para envio por E-mail " + dests)) {
			emailService.enviaCTeEmailXML(request, local, Date.valueOf(dtini), Date.valueOf(dtfim), dests, info,
					enviopdf, tipo);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/cte/conserv")
	public ResponseEntity<String> conServicoCte(HttpServletRequest request, @RequestParam("idlocal") Long idlocal)
			throws Exception {
		if (ca.hasAuth(prm, 43, "Y", "ID " + idlocal)) {
			String ambiente = prm.cliente().getSiscfg().getSis_amb_cte();
			String status = fsCteEnvioService.consultaServico(idlocal, ambiente, request);
			return new ResponseEntity<>(status, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cte/cancelacte")
	public ResponseEntity<String[]> cancelaCTe(HttpServletRequest request, @RequestParam("idcte") Long idcte,
			@RequestParam("justifica") String justifica) throws Exception {
		if (ca.hasAuth(prm, 43, "X", "ID CANCELAMENTO DE CT-E " + idcte)) {
			FsCte cte = fsCteRp.findById(idcte).get();
			String tpevento = "110111";
			String nprot = cte.getNprot();
			String nrecibo = cte.getNrecibo();
			String ret[] = fsCteEnvioService.eventoCTe(cte, tpevento, justifica, "", "", request);
			// Ajusta situacao
			if (ret[0].equals("135")) {
				FsCteEvento e = new FsCteEvento();
				e.setFscte(cte);
				e.setNumseq(Integer.valueOf(ret[4]));
				e.setTpevento(tpevento);
				e.setXjust(CaracterUtil.remUpper(justifica));
				e.setXml(ret[3]);
				fsCteEventoRp.save(e);
				fsCteRp.updateXMLCTeStatus(cte.getXml(), 101, nprot, nrecibo, cte.getId());
			}
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cte/inutcte")
	public ResponseEntity<String[]> inutilizaCte(HttpServletRequest request, @RequestParam("idlocal") Long idlocal,
			@RequestParam("modelo") String modelo, @RequestParam("serie") String serie,
			@RequestParam("numNI") String numNI, @RequestParam("numNF") String numNF,
			@RequestParam("justifica") String justifica) throws Exception {
		if (ca.hasAuth(prm, 43, "E", "INUTILIZACAO CT-E | " + justifica)) {
			String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
			String ret[] = fsCteEnvioService.inutilizaCTe(idlocal, ambiente, modelo, serie, numNI, numNF, justifica,
					request);
			// Registra itens
			if (ret[0].equals("102") || ret[0].equals("256") || ret[0].equals("563")) {
				fsNfeService.inutilizaDoc(idlocal, ambiente, Integer.valueOf(modelo), Integer.valueOf(serie),
						Integer.valueOf(numNI), Integer.valueOf(numNF), justifica);
			}
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
