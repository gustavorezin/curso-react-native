package com.midas.api.controller;

import java.io.Serializable;
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
import com.midas.api.tenant.entity.CdProdutoTam;
import com.midas.api.tenant.entity.CdProdutoTamRel;
import com.midas.api.tenant.repository.CdProdutoTamRelRepository;
import com.midas.api.tenant.repository.CdProdutoTamRepository;
import com.midas.api.tenant.service.integra.ClimbaService;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdtam")
public class CdProdutoTamController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdProdutoTamRepository cdTamRp;
	@Autowired
	private CdProdutoTamRelRepository cdProdutoTamRelRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private ClimbaService climbaService;

	@RequestAuthorization
	@GetMapping("/tam/{id}")
	public ResponseEntity<CdProdutoTam> cdProdutoTam(@PathVariable("id") Integer id) {
		Optional<CdProdutoTam> obj = cdTamRp.findById(id);
		return new ResponseEntity<CdProdutoTam>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/tam")
	public ResponseEntity<?> cadastrarCdProdutoTam(@RequestBody CdProdutoTam cdTam) throws Exception {
		if (ca.hasAuth(prm, 65, "C", cdTam.getTamanho())) {
			CdProdutoTam cd = cdTamRp.save(cdTam);
			// Verifica integracao Climba--------------------
			String ret[] = climbaService.cadCorTam(null, cd, "POST", "2");
			if (ret[0].equals("201")) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				cdTamRp.deleteById(cd.getId());
				return new ResponseEntity<String>(ret[1], HttpStatus.FAILED_DEPENDENCY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/tam")
	public ResponseEntity<?> atualizarCdProdutoTam(@RequestBody CdProdutoTam cdTam) throws Exception {
		if (ca.hasAuth(prm, 65, "A", cdTam.getTamanho())) {
			CdProdutoTam cd = cdTamRp.save(cdTam);
			// Verifica integracao Climba--------------------
			String ret[] = climbaService.cadCorTam(null, cd, "PUT", "2");
			if (ret[0].equals("201")) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(ret[1], HttpStatus.FAILED_DEPENDENCY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/tam/{id}")
	public ResponseEntity<?> removerCdProdutoTam(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 65, "R", "ID " + id)) {
			cdTamRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/tam")
	public ResponseEntity<List<CdProdutoTam>> listaCdProdutoTams() throws Exception {
		List<CdProdutoTam> lista = cdTamRp.findAll(Sort.by("tamanho"));
		return new ResponseEntity<List<CdProdutoTam>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/tam/produto/{id}")
	public ResponseEntity<List<CdProdutoTamRel>> listaCdProdutoTamsItem(@PathVariable("id") Long id) throws Exception {
		List<CdProdutoTamRel> lista = cdProdutoTamRelRp.findAllByIdProduto(id);
		return new ResponseEntity<List<CdProdutoTamRel>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/tam/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdProdutoTam>> listaCdProdutoTamsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 65, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdProdutoTam> lista = cdTamRp.findAllByTamBusca(busca, pageable);
			return new ResponseEntity<Page<CdProdutoTam>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/tam/aptam")
	public ResponseEntity<?> adicionarCdProdutoTamRel(@RequestBody List<CdProdutoTamRel> cdProdutoTamRel)
			throws Exception {
		if (ca.hasAuth(prm, 6, "C", "INCLUSAO DE TAMANHO DE PRODUTO ")) {
			for (CdProdutoTamRel cp : cdProdutoTamRel) {
				cdProdutoTamRelRp.save(cp);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/tam/aptam/{id}")
	public ResponseEntity<?> removerCdProdutoTamRel(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 6, "R", "ID TAMANHO COR DE PRODUTO " + id)) {
			cdProdutoTamRelRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
