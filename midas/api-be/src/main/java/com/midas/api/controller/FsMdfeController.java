package com.midas.api.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

import com.midas.api.constant.MidasConfig;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.CdCidade;
import com.midas.api.tenant.entity.CdEstado;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdVeiculo;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsMdfe;
import com.midas.api.tenant.entity.FsMdfeAut;
import com.midas.api.tenant.entity.FsMdfeAverb;
import com.midas.api.tenant.entity.FsMdfeContr;
import com.midas.api.tenant.entity.FsMdfeDoc;
import com.midas.api.tenant.entity.FsMdfeEvento;
import com.midas.api.tenant.entity.FsMdfePag;
import com.midas.api.tenant.entity.FsMdfePagComp;
import com.midas.api.tenant.entity.FsMdfePagPrazo;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.fiscal.GeraXMLMDFe;
import com.midas.api.tenant.fiscal.service.FsMdfeEnvioService;
import com.midas.api.tenant.fiscal.service.FsMdfeGerarService;
import com.midas.api.tenant.fiscal.service.FsMdfeService;
import com.midas.api.tenant.fiscal.util.GeraChavesUtil;
import com.midas.api.tenant.repository.CdCidadeRepository;
import com.midas.api.tenant.repository.CdEstadoRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdVeiculoRepository;
import com.midas.api.tenant.repository.FsCteRepository;
import com.midas.api.tenant.repository.FsCustomRepository;
import com.midas.api.tenant.repository.FsMdfeAutRepository;
import com.midas.api.tenant.repository.FsMdfeAverbRepository;
import com.midas.api.tenant.repository.FsMdfeContrRepository;
import com.midas.api.tenant.repository.FsMdfeDocRepository;
import com.midas.api.tenant.repository.FsMdfeEventoRepository;
import com.midas.api.tenant.repository.FsMdfePagCompRepository;
import com.midas.api.tenant.repository.FsMdfePagPrazoRepository;
import com.midas.api.tenant.repository.FsMdfePagRepository;
import com.midas.api.tenant.repository.FsMdfePartRepository;
import com.midas.api.tenant.repository.FsMdfePercRepository;
import com.midas.api.tenant.repository.FsMdfeReboqRepository;
import com.midas.api.tenant.repository.FsMdfeRepository;
import com.midas.api.tenant.repository.FsNfeRepository;
import com.midas.api.tenant.service.EmailService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;

@RestController
@RequestMapping("/private/fs")
public class FsMdfeController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FsMdfeRepository fsMdfeRp;
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private FsCteRepository fsCteRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdEstadoRepository cdEstadoRp;
	@Autowired
	private CdCidadeRepository cdCidadeRp;
	@Autowired
	private CdVeiculoRepository cdVeiculoRp;
	@Autowired
	private FsMdfePartRepository fsMdfePartRp;
	@Autowired
	private FsMdfePercRepository fsMdfePercRp;
	@Autowired
	private FsMdfeReboqRepository fsMdfeReboqRp;
	@Autowired
	private FsMdfeDocRepository fsMdfeDocRp;
	@Autowired
	private FsMdfeAutRepository fsMdfeAutRp;
	@Autowired
	private FsMdfeAverbRepository fsMdfeAverbRp;
	@Autowired
	private FsMdfeContrRepository fsMdfeContrRp;
	@Autowired
	private FsMdfePagRepository fsMdfePagRp;
	@Autowired
	private FsMdfePagCompRepository fsMdfePagCompRp;
	@Autowired
	private FsMdfePagPrazoRepository fsMdfePagPrazoRp;
	@Autowired
	private FsMdfeService fsMdfeService;
	@Autowired
	private FsCustomRepository fsCustomRp;
	@Autowired
	private FsMdfeEventoRepository fsMdfeEventoRp;
	@Autowired
	private GeraXMLMDFe geraXMLMDFe;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private FsMdfeEnvioService fsMdfeEnvioService;
	@Autowired
	private FsMdfeGerarService fsMdfeGerarService;
	@Autowired
	private EmailService emailService;

	// DOCUMENTOS FISCAIS - MDFE****************************************
	@RequestAuthorization
	@GetMapping("/mdfe/{id}")
	public ResponseEntity<FsMdfe> fsMdfe(@PathVariable("id") Long id) {
		Optional<FsMdfe> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = fsMdfeRp.findById(id);
		} else {
			obj = fsMdfeRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<FsMdfe>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/mdfepag/{id}")
	public ResponseEntity<FsMdfePag> fsMdfePag(@PathVariable("id") Integer id) {
		FsMdfePag obj = null;
		obj = fsMdfePagRp.findById(id).get();
		return new ResponseEntity<FsMdfePag>(obj, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/mdfe")
	public ResponseEntity<FsMdfe> atualizarFsMdfe(@RequestBody FsMdfe fsMdfe) throws Exception {
		FsMdfe mdfe = null;
		if (ca.hasAuth(prm, 73, "A", "ID " + fsMdfe.getId())) {
			String dataAAMM = DataUtil.anoMesAAMM(fsMdfe.getDhemi());
			Integer nmdf = fsMdfe.getNmdf();
			String chaveac = GeraChavesUtil
					.geraChavePadrao(fsMdfe.getCdpessoaemp().getCdestado().getId(), dataAAMM,
							fsMdfe.getCdpessoaemp().getCpfcnpj(), 58, fsMdfe.getSerie(), 1, nmdf, nmdf, "MDFe")
					.replace("MDFe", "");
			// DIGITO CHAVE--------------------------------------
			String cDv = chaveac.substring(43, 44);
			fsMdfe.setCdv(Integer.valueOf(cDv));
			fsMdfe.setChaveac(chaveac);
			mdfe = fsMdfeRp.save(fsMdfe);
			fsMdfeGerarService.atualizaFsMdfeAdicionais(mdfe.getId());
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<FsMdfe>(mdfe, HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/mdfe/{id}")
	public ResponseEntity<?> removerFsMdfe(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 73, "R", "ID " + id)) {
			fsMdfeRp.deleteById(id);
			fsNfeRp.updateDocMdfeDoc(0, 0L, id);
			fsCteRp.updateDocMdfeDoc(0, 0L, id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/mdfe/remover/emlote")
	public ResponseEntity<?> removerFsmdfeEmLote(@RequestBody List<FsMdfe> fsMdfe) {
		if (ca.hasAuth(prm, 73, "R", "REMOCAO DE NOTAS FISCAIS EM LOTE")) {
			for (FsMdfe n : fsMdfe) {
				fsMdfeRp.deleteById(n.getId());
				fsNfeRp.updateDocMdfeDoc(0, 0L, n.getId());
				fsCteRp.updateDocMdfeDoc(0, 0L, n.getId());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/mdfe/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/lc/{local}/tp/{tipo}/ipp/{itenspp}/dti/{dataini}/dtf/{datafim}/status/{status}/enc/{enc}")
	public ResponseEntity<Page<FsMdfe>> listaFsMdfesBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("local") Long local,
			@PathVariable("tipo") String tipo, @PathVariable("itenspp") int itenspp,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("status") Integer status, @PathVariable("enc") String enc) throws Exception {
		if (ca.hasAuth(prm, 73, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<FsMdfe> lista = fsCustomRp.listaFsMdfe(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo,
					busca, status, enc, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<FsMdfe>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/mdfe/valores/b/{busca}/lc/{local}/tp/{tipo}/dti/{dataini}/dtf/{datafim}/status/{status}/enc/{enc}")
	public ResponseEntity<List<FsMdfe>> listaFsMdfesValores(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("tipo") String tipo,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("status") Integer status, @PathVariable("enc") String enc) throws Exception {
		if (ca.hasAuth(prm, 73, "L", "LISTAGEM / CONSULTA VALORES")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<FsMdfe> lista = fsCustomRp.listaFsMdfeValores(local, Date.valueOf(dataini), Date.valueOf(datafim),
					tipo, busca, status, enc);
			return new ResponseEntity<List<FsMdfe>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/mdfe/conferida")
	public ResponseEntity<?> atualizarFsMdfeConferida(@RequestParam("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 73, "A", "ID " + id)) {
			FsMdfe fsmdfe = fsMdfeRp.getById(id);
			fsmdfe.setSt_imp("S");
			fsMdfeRp.save(fsmdfe);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/mdfe/gerar/nfe/lc/{local}/mot/{motorista}/tpe/{tpemit}/v/{veiculo}/es/{estado}")
	public ResponseEntity<FsMdfe> gerarMdfeNfe(@RequestBody List<FsNfe> nfes, @PathVariable("local") Long local,
			@PathVariable("motorista") Long motorista, @PathVariable("tpemit") Integer tpemit,
			@PathVariable("veiculo") Integer veiculo, @PathVariable("estado") Integer estado) throws Exception {
		FsMdfe mdfe = null;
		if (ca.hasAuth(prm, 73, "N", "GERAR MDFE VIA NFE")) {
			// Verifica se ja foi emitido ------
			boolean todasNaoEmitidas = true;
			for (FsNfe nfe : nfes) {
				if (nfe.getDoc_mdfe() != 0L) {
					todasNaoEmitidas = false;
					break;
				}
			}
			// Se nao foi emitido---------------
			if (todasNaoEmitidas) {
				String ambiente = prm.cliente().getSiscfg().getSis_amb_mdfe();
				mdfe = fsMdfeGerarService.gerarMdfe(ambiente, nfes, null, local, motorista, null, tpemit, veiculo, estado);
				for (FsNfe nfe : nfes) {
					fsNfeRp.updateDocMdfe(mdfe.getNmdf(), mdfe.getId(), nfe.getId());					
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<FsMdfe>(mdfe, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/mdfe/gerar/cte/lc/{local}/mot/{motorista}/seg/{seguradora}/tpe/{tpemit}/v/{veiculo}/es/{estado}")
	public ResponseEntity<FsMdfe> gerarMdfeCte(@RequestBody List<FsCte> ctes, @PathVariable("local") Long local,
			@PathVariable("motorista") Long motorista, @PathVariable("seguradora") Long seguradora,
			@PathVariable("tpemit") Integer tpemit, @PathVariable("veiculo") Integer veiculo,
			@PathVariable("estado") Integer estado) throws Exception {
		FsMdfe mdfe = null;
		if (ca.hasAuth(prm, 73, "N", "GERAR MDFE VIA CTE")) {
			// Verifica se ja foi emitido-------
			boolean todasNaoEmitidas = true;
			for (FsCte cte : ctes) {
				if (cte.getDoc_mdfe() != 0L) {
					todasNaoEmitidas = false;
					break;
				}
			}
			// Se nao foi emitido---------------
			if (todasNaoEmitidas) {
				String ambiente = prm.cliente().getSiscfg().getSis_amb_mdfe();
				mdfe = fsMdfeGerarService.gerarMdfe(ambiente, null, ctes, local, motorista, seguradora, tpemit, veiculo,
						estado);
				for (FsCte cte : ctes) {
					fsCteRp.updateDocMdfe(mdfe.getNmdf(), mdfe.getId(), cte.getId());					
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<FsMdfe>(mdfe, HttpStatus.OK);
	}

	/* FUNCOES ADICIONAIS EXTRAS */
	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/mdfe/addmdfemot/{id}/mot/{motorista}")
	public ResponseEntity<?> addFsMdfeMot(@PathVariable("id") Long id, @PathVariable("motorista") Long motorista)
			throws Exception {
		if (ca.hasAuth(prm, 73, "N", "ID ADICIONAR MOTORISTA REFERENCIADO " + id + " - " + motorista)) {
			FsMdfe fsmdfe = fsMdfeRp.findById(id).get();
			CdPessoa p = cdPessoaRp.findById(motorista).get();
			fsMdfeGerarService.part(p, "M", fsmdfe);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/mdfe/remmdfemot/{id}")
	public ResponseEntity<?> remFsMdfeMot(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 73, "R", "ID REMOVER MOTORISTA REFERENCIADO " + id)) {
			fsMdfePartRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/mdfe/addmdfeperc/{id}/es/{estado}")
	public ResponseEntity<?> addFsMdfePerc(@PathVariable("id") Long id, @PathVariable("estado") Integer estado)
			throws Exception {
		if (ca.hasAuth(prm, 73, "N", "ID ADICIONAR PERCURSO REFERENCIADO " + id + " - " + estado)) {
			FsMdfe fsmdfe = fsMdfeRp.findById(id).get();
			CdEstado es = cdEstadoRp.findById(estado).get();
			fsMdfeGerarService.perc(es, fsmdfe);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/mdfe/remmdfeperc/{id}")
	public ResponseEntity<?> remFsMdfePerc(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 73, "R", "ID REMOVER PERCURSO REFERENCIADO " + id)) {
			fsMdfePercRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/mdfe/addmdfereboq/{id}/v/{veiculo}")
	public ResponseEntity<?> addFsMdfeReboq(@PathVariable("id") Long id, @PathVariable("veiculo") Integer veiculo)
			throws Exception {
		if (ca.hasAuth(prm, 73, "N", "ID ADICIONAR REBOQUE REFERENCIADO " + id + " - " + veiculo)) {
			FsMdfe fsmdfe = fsMdfeRp.findById(id).get();
			CdVeiculo v = cdVeiculoRp.findById(veiculo).get();
			fsMdfeGerarService.reboq(v, fsmdfe);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/mdfe/remmdfereboq/{id}")
	public ResponseEntity<?> remFsMdfeReboq(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 73, "R", "ID REMOVER PERCURSO REFERENCIADO " + id)) {
			fsMdfeReboqRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// DOC ITEM ****************************************
	@RequestAuthorization
	@GetMapping("/mdfedoc/{id}")
	public ResponseEntity<FsMdfeDoc> fsMdfeDoc(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<FsMdfeDoc> obj = fsMdfeDocRp.findById(id);
		return new ResponseEntity<FsMdfeDoc>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/mdfedoc")
	public ResponseEntity<?> addFsMdfeDoc(@RequestBody FsMdfeDoc fsMdfeDoc) throws Exception {
		if (ca.hasAuth(prm, 73, "A", "DOC " + fsMdfeDoc.getChave())) {
			FsMdfeDoc chave = fsMdfeDocRp.findByMdfeAndChave(fsMdfeDoc.getFsmdfe().getId(), fsMdfeDoc.getChave());
			if (chave == null) {
				fsMdfeDocRp.save(fsMdfeDoc);
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
	@PutMapping("/mdfedoc/{id}")
	public ResponseEntity<?> removerFsMdfeDoc(@PathVariable("id") Long id, @RequestBody FsMdfeDoc fsMdfeDoc)
			throws Exception {
		if (ca.hasAuth(prm, 73, "R", "ID DOC " + fsMdfeDoc.getId() + " - " + fsMdfeDoc.getChave())) {
			fsMdfeDocRp.delete(fsMdfeDoc);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/mdfe/addmdfeaut")
	public ResponseEntity<?> addFsMdfeCNPJAut(@RequestParam("id") Long id, @RequestParam("cpfcnpj") String cpfcnpj)
			throws Exception {
		if (ca.hasAuth(prm, 73, "N", "ID ADICIONAR CNPJs AUTORIZADOS " + id + " - " + cpfcnpj)) {
			FsMdfe fsmdfe = fsMdfeRp.findById(id).get();
			FsMdfeAut aut = fsMdfeAutRp.getCpfCnpjMdfe(id, cpfcnpj);
			if (aut == null) {
				FsMdfeAut v = new FsMdfeAut();
				v.setFsmdfe(fsmdfe);
				v.setCpfcnpj(cpfcnpj);
				fsMdfeAutRp.save(v);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/mdfe/remmdfeaut/{id}")
	public ResponseEntity<?> remFsMdfeCNPJAut(@PathVariable("id") Integer id) throws Exception {
		if (ca.hasAuth(prm, 73, "R", "ID REMOVER CNPJs AUTORIZADOS " + id)) {
			fsMdfeAutRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/mdfeaverb")
	public ResponseEntity<?> addFsMdfeAverb(@RequestBody FsMdfeAverb fsMdfeAverb) throws Exception {
		if (ca.hasAuth(prm, 73, "A", "AVERBACAO " + fsMdfeAverb.getNumaverb())) {
			FsMdfeAverb numaverb = fsMdfeAverbRp.findByMdfeAndNumaverb(fsMdfeAverb.getFsmdfe().getId(),
					fsMdfeAverb.getNumaverb());
			if (numaverb == null) {
				fsMdfeAverbRp.save(fsMdfeAverb);
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
	@PutMapping("/mdfeaverb/{id}")
	public ResponseEntity<?> removerFsMdfeAverb(@PathVariable("id") Long id, @RequestBody FsMdfeAverb fsMdfeAverb)
			throws Exception {
		if (ca.hasAuth(prm, 73, "R", "ID AVERBACAO " + fsMdfeAverb.getId() + " - " + fsMdfeAverb.getNumaverb())) {
			fsMdfeAverbRp.delete(fsMdfeAverb);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/mdfecontr")
	public ResponseEntity<?> addFsMdfeContr(@RequestBody FsMdfeContr fsMdfeContr) throws Exception {
		if (ca.hasAuth(prm, 73, "A", "CONTRATANTE " + fsMdfeContr.getCpfcnpj())) {
			FsMdfeContr numaverb = fsMdfeContrRp.findByMdfeAndCpfcnpj(fsMdfeContr.getFsmdfe().getId(),
					fsMdfeContr.getCpfcnpj());
			if (numaverb == null) {
				fsMdfeContrRp.save(fsMdfeContr);
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
	@PutMapping("/mdfecontr/{id}")
	public ResponseEntity<?> removerFsMdfeContr(@PathVariable("id") Long id, @RequestBody FsMdfeContr fsMdfeContr)
			throws Exception {
		if (ca.hasAuth(prm, 73, "R", "ID CONTRATANTE " + fsMdfeContr.getId() + " - " + fsMdfeContr.getCpfcnpj())) {
			fsMdfeContrRp.delete(fsMdfeContr);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/mdfepag")
	public ResponseEntity<?> addFsMdfePag(@RequestBody FsMdfePag fsMdfePag) throws Exception {
		if (ca.hasAuth(prm, 73, "A", "ID PAGAMENTO " + fsMdfePag.getId() + " - " + fsMdfePag.getCpfcnpj())) {
			fsMdfePagRp.save(fsMdfePag);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/mdfepag/{id}")
	public ResponseEntity<?> removerFsMdfePag(@PathVariable("id") Long id, @RequestBody FsMdfePag fsMdfePag)
			throws Exception {
		if (ca.hasAuth(prm, 73, "R", "ID PAGAMENTO " + fsMdfePag.getId() + " - " + fsMdfePag.getCpfcnpj())) {
			fsMdfePagRp.delete(fsMdfePag);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/mdfepagprazo/fsmdfepag/{id}")
	public ResponseEntity<?> addFsMdfePagPrazo(@PathVariable("id") Integer id,
			@RequestBody FsMdfePagPrazo fsMdfePagPrazo) throws Exception {
		if (ca.hasAuth(prm, 73, "A", "ID PRAZO " + fsMdfePagPrazo.getId() + " - " + fsMdfePagPrazo.getNparcela())) {
			fsMdfePagPrazoRp.save(fsMdfePagPrazo);
			fsMdfeService.nparcelaPrazo(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/mdfepagprazo/fsmdfepag/{id}")
	public ResponseEntity<?> removerFsMdfePagPrazo(@PathVariable("id") Integer id,
			@RequestBody FsMdfePagPrazo fsMdfePagPrazo) throws Exception {
		if (ca.hasAuth(prm, 73, "R", "ID PRAZO " + fsMdfePagPrazo.getId() + " - " + fsMdfePagPrazo.getNparcela())) {
			fsMdfePagPrazoRp.delete(fsMdfePagPrazo);
			fsMdfeService.nparcelaPrazo(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/mdfepagcomp")
	public ResponseEntity<?> addFsMdfePagComp(@RequestBody FsMdfePagComp fsMdfePagComp) throws Exception {
		if (ca.hasAuth(prm, 73, "A", "ID COMPOSICAO " + fsMdfePagComp.getId() + " - " + fsMdfePagComp.getTpcomp())) {
			fsMdfePagCompRp.save(fsMdfePagComp);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/mdfepagcomp")
	public ResponseEntity<?> removerFsMdfePagComp(@RequestBody FsMdfePagComp fsMdfePagComp) throws Exception {
		if (ca.hasAuth(prm, 73, "R", "ID COMPOSICAO " + fsMdfePagComp.getId() + " - " + fsMdfePagComp.getTpcomp())) {
			fsMdfePagCompRp.delete(fsMdfePagComp);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/mdfe/conserv")
	public ResponseEntity<String> conServicoMdfe(HttpServletRequest request, @RequestParam("idlocal") Long idlocal)
			throws Exception {
		if (ca.hasAuth(prm, 18, "Y", "ID " + idlocal)) {
			String ambiente = prm.cliente().getSiscfg().getSis_amb_mdfe();
			String status = fsMdfeEnvioService.consultaServico(idlocal, ambiente, request);
			return new ResponseEntity<>(status, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/mdfe/enviamdfe/{id}")
	public ResponseEntity<String[]> enviarMdfe(HttpServletRequest request, @PathVariable("id") Long id)
			throws Exception {
		if (ca.hasAuth(prm, 73, "E", "ID Envio MDFE " + id)) {
			FsMdfe m = fsMdfeRp.findById(id).get();
			String ambiente = prm.cliente().getSiscfg().getSis_amb_mdfe();
			m.setTpamb(Integer.valueOf(ambiente));
			fsMdfeRp.save(m);
			m = fsMdfeRp.findById(id).get();
			List<FsMdfeDoc> cidades = fsMdfeDocRp.findAllByMdfeGroupByCidade(id);
			String xml = geraXMLMDFe.geraXMLMDFe(m, mc, cidades);
			String ret[] = fsMdfeEnvioService.envioMDFe(m, xml, request);
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
	@PostMapping("/mdfe/cancelamdfe")
	public ResponseEntity<String[]> cancelaMDFe(HttpServletRequest request, @RequestParam("idmdfe") Long idmdfe,
			@RequestParam("justifica") String justifica) throws Exception {
		if (ca.hasAuth(prm, 73, "X", "ID CANCELAMENTO DE MDF-E " + idmdfe)) {
			FsMdfe mdfe = fsMdfeRp.findById(idmdfe).get();
			String tpevento = "110111";
			String nprot = mdfe.getNprot();
			String nrecibo = mdfe.getNrecibo();
			String ret[] = fsMdfeEnvioService.eventoMDFe(mdfe, tpevento, justifica, "", "", request);
			// Ajusta situacao
			if (ret[0].equals("135")) {
				FsMdfeEvento e = new FsMdfeEvento();
				e.setFsmdfe(mdfe);
				e.setNumseq(Integer.valueOf(ret[4]));
				e.setTpevento(tpevento);
				e.setXjust(CaracterUtil.remUpper(justifica));
				e.setXml(ret[3]);
				fsMdfeEventoRp.save(e);
				fsMdfeRp.updateXMLMDFeStatus(mdfe.getXml(), 101, nprot, nrecibo, mdfe.getId());
			}
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/mdfe/encerramdfe")
	public ResponseEntity<String[]> encerraMDFe(HttpServletRequest request, @RequestParam("idmdfe") Long idmdfe,
			@RequestParam("cidfim") Integer cidfim) throws Exception {
		if (ca.hasAuth(prm, 73, "X", "ID ENCERRAMENTO DE MDF-E " + idmdfe)) {
			CdCidade cidadefim = cdCidadeRp.findById(cidfim).get();
			FsMdfe mdfe = fsMdfeRp.findById(idmdfe).get();
			String tpevento = "110112";
			String coduffim = cidadefim.getCdestado().getId() + "";
			String ret[] = fsMdfeEnvioService.eventoMDFe(mdfe, tpevento, "", coduffim, cidadefim.getIbge(), request);
			// Ajusta situacao
			if (ret[0].equals("135")) {
				FsMdfeEvento e = new FsMdfeEvento();
				e.setFsmdfe(mdfe);
				e.setNumseq(Integer.valueOf(ret[4]));
				e.setTpevento(tpevento);
				e.setXjust("");
				e.setXml(ret[3]);
				fsMdfeEventoRp.save(e);
				fsMdfeRp.updateMDFeEncerra("S", mdfe.getId());
			}
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/mdfe/qtdnaoencerrados")
	public ResponseEntity<Integer> qtdNaoEncerrados() throws Exception {
		if (ca.hasAuth(prm, 73, "L", "LISTAGEM / CONSULTA DE NAO ENCERRADOS")) {
			Integer qtd = 0;
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				qtd = fsMdfeRp.verificaMdfeEncerrados();
			} else {
				qtd = fsMdfeRp.verificaMdfeEncerradosLocal(prm.cliente().getCdpessoaemp());
			}
			return new ResponseEntity<Integer>(qtd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/mdfe/envioemail/{id}")
	public ResponseEntity<?> envioMDFeEmail(HttpServletRequest request, @PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 73, "E", "ID Documento para Envio por E-mail MDF-e " + id)) {
			emailService.enviaMDFeEmail(request, id, null);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/mdfe/envioemail/{id}/email/{email}")
	public ResponseEntity<?> envioMDFeEmailOutros(HttpServletRequest request, @PathVariable("id") Long id,
			@PathVariable("email") String email) throws Exception {
		if (ca.hasAuth(prm, 73, "E", "ID Documento para Envio por E-mail MDF-e " + id)) {
			emailService.enviaMDFeEmail(request, id, email);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
