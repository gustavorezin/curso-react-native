package com.midas.api.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.CdNfeCfgSimples;
import com.midas.api.tenant.entity.CdNfeCfgVend;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.repository.CdCustomRepository;
import com.midas.api.tenant.repository.CdNfeCfgRepository;
import com.midas.api.tenant.repository.CdNfeCfgSimplesRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.service.CdPessoaCadConsService;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdnfecfg")
public class CdNfeCfgController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdNfeCfgRepository cdNfeCfgRp;
	@Autowired
	private CdNfeCfgSimplesRepository cdNfeCfgSimplesRp;
	@Autowired
	private CdPessoaCadConsService cadConsService;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdCustomRepository cdCustomRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/nfecfg/{id}")
	public ResponseEntity<CdNfeCfg> cdNfeCfg(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdNfeCfg> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdNfeCfgRp.findById(id);
		} else {
			obj = cdNfeCfgRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdNfeCfg>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/nfecfg")
	public ResponseEntity<?> cadastrarCdNfeCfg(@RequestBody CdNfeCfg cdNfeCfg) throws Exception {
		if (ca.hasAuth(prm, 67, "C", cdNfeCfg.getCfop())) {
			// Altera outros locais
			if (cdNfeCfg.getUsa_nfce().equals("S")) {
				cdNfeCfgRp.alteraTipoLocalSimilar(cdNfeCfg.getCdpessoaemp().getId());
			}
			cdNfeCfgRp.save(cdNfeCfg);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PutMapping("/nfecfg")
	public ResponseEntity<?> atualizarCdNfeCfg(@RequestBody CdNfeCfg cdNfeCfg) throws Exception {
		if (ca.hasAuth(prm, 67, "A", cdNfeCfg.getCfop())) {
			// Altera outros locais
			if (cdNfeCfg.getUsa_nfce().equals("S")) {
				cdNfeCfgRp.alteraTipoLocalSimilarLocal(cdNfeCfg.getId(), cdNfeCfg.getCdpessoaemp().getId());
			}
			cdNfeCfgRp.save(cdNfeCfg);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@DeleteMapping("/nfecfg/{id}")
	public ResponseEntity<?> removerCdNfeCfg(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 67, "R", "ID " + id)) {
			cdNfeCfgRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfecfg/todas")
	public ResponseEntity<List<CdNfeCfg>> listaCdNfeCfgsTodas() throws Exception {
		List<CdNfeCfg> lista = cdNfeCfgRp.findAll();
		return new ResponseEntity<List<CdNfeCfg>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfecfg/local/{local}")
	public ResponseEntity<List<CdNfeCfg>> listaCdNfeCfgs(@PathVariable("local") Long local) throws Exception {
		List<CdNfeCfg> lista = cdNfeCfgRp.findAllByLocal(local);
		return new ResponseEntity<List<CdNfeCfg>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfecfg/local/{local}/tpop/{tpop}")
	public ResponseEntity<List<CdNfeCfg>> listaCdNfeCfgsTipo(@PathVariable("local") Long local,
			@PathVariable("tpop") String tpop) throws Exception {
		List<CdNfeCfg> lista = cdNfeCfgRp.findAllByLocalAndTpop(local, tpop);
		return new ResponseEntity<List<CdNfeCfg>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfecfg/local/{local}/tpop/{tpop}/crtdest/{crtdest}/coduf/{coduf}")
	public ResponseEntity<List<CdNfeCfg>> listaCdNfeCfgDestAtivos(@PathVariable("local") Long local,
			@PathVariable("tpop") String tpop, @PathVariable("crtdest") Integer crtdest,
			@PathVariable("coduf") Integer coduf) throws InterruptedException {
		// Verifica controle representante
		Long respvend = 0L;
		if (prm.cliente().getRole().getId() == 8) {
			if (prm.cliente().getCdpessoavendedor() != null) {
				respvend = prm.cliente().getCdpessoavendedor();
			}
		}
		List<CdNfeCfg> lista = new ArrayList<CdNfeCfg>();
		if (respvend > 0) {
			CdPessoa vend = cdPessoaRp.findById(respvend).get();
			for (CdNfeCfgVend nv : vend.getCdnfecfgvenditems()) {
				CdNfeCfg t = cdNfeCfgRp.findById(nv.getCdnfecfg()).get();
				if (t.getCdpessoaemp().getId().equals(local) && t.getTipoop().equals(tpop)
						&& t.getCrtdest().equals(crtdest) && t.getCdestadoaplic().getId().equals(coduf)) {
					lista.add(t);
				}
			}
		} else {
			lista = cdNfeCfgRp.listaCdNfeCfgDestinoAtivo(local, tpop, crtdest, coduf);
		}
		return new ResponseEntity<List<CdNfeCfg>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfecfg/local/{local}/crtdest/{crtdest}/coduf/{coduf}")
	public ResponseEntity<List<CdNfeCfg>> listaCdNfeCfgDestSemOpAtivos(@PathVariable("local") Long local,
			@PathVariable("crtdest") Integer crtdest, @PathVariable("coduf") Integer coduf)
			throws InterruptedException {
		// Verifica controle representante
		Long respvend = 0L;
		if (prm.cliente().getRole().getId() == 8) {
			if (prm.cliente().getCdpessoavendedor() != null) {
				respvend = prm.cliente().getCdpessoavendedor();
			}
		}
		List<CdNfeCfg> lista = new ArrayList<CdNfeCfg>();
		if (respvend > 0) {
			CdPessoa vend = cdPessoaRp.findById(respvend).get();
			for (CdNfeCfgVend nv : vend.getCdnfecfgvenditems()) {
				CdNfeCfg t = cdNfeCfgRp.findById(nv.getCdnfecfg()).get();
				if (t.getCdpessoaemp().getId().equals(local) && t.getCrtdest().equals(crtdest)
						&& t.getCdestadoaplic().getId().equals(coduf)) {
					lista.add(t);
				}
			}
		} else {
			lista = cdNfeCfgRp.listaCdNfeCfgDestinoSemOpAtivo(local, crtdest, coduf);
		}
		return new ResponseEntity<List<CdNfeCfg>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfecfg/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/coduf/{coduf}/lc/{local}")
	public ResponseEntity<Page<CdNfeCfg>> listaCdNfeCfgsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp,
			@PathVariable("coduf") int coduf, @PathVariable("local") Long local) throws Exception {
		if (ca.hasAuth(prm, 67, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdNfeCfg> lista = cdCustomRp.listaCdNfeCfg(local, busca, ordem, ordemdir, coduf, pageable);
			return new ResponseEntity<Page<CdNfeCfg>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// SIMPLES NACIONAL - CREDITO ICMS *******************************
	@RequestAuthorization
	@GetMapping("/nfecfg/sn/{id}")
	public ResponseEntity<CdNfeCfgSimples> cdNfeCfgSimples(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdNfeCfgSimples> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdNfeCfgSimplesRp.findById(id);
		} else {
			obj = cdNfeCfgSimplesRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdNfeCfgSimples>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfecfg/sn")
	public ResponseEntity<List<CdNfeCfgSimples>> listaCdNfeCfgSimples() throws InterruptedException {
		List<CdNfeCfgSimples> lista = null;
		cadConsService.geraCfgFiscalIni("EMPRESA");
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			lista = cdNfeCfgSimplesRp.findAll();
		} else {
			lista = cdNfeCfgSimplesRp.findByCdpessoaemp(prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<List<CdNfeCfgSimples>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfecfg/sn")
	public ResponseEntity<?> atualizarCdNfeCfgSimples(@RequestBody List<CdNfeCfgSimples> cdNfeCfgSimples)
			throws Exception {
		if (ca.hasAuth(prm, 68, "A", cdNfeCfgSimples.get(0).getCdpessoaemp().getNome())) {
			cdNfeCfgSimplesRp.saveAll(cdNfeCfgSimples);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
