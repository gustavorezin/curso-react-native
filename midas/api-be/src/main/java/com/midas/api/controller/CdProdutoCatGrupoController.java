package com.midas.api.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.midas.api.tenant.entity.CdProdutoCat;
import com.midas.api.tenant.entity.CdProdutoGrupo;
import com.midas.api.tenant.entity.CdProdutoSubgrupo;
import com.midas.api.tenant.repository.CdProdutoCatRepository;
import com.midas.api.tenant.repository.CdProdutoGrupoRepository;
import com.midas.api.tenant.repository.CdProdutoSubgrupoRepository;
import com.midas.api.tenant.service.integra.ClimbaService;

@RestController
@RequestMapping("/private/cdcatgrupo")
public class CdProdutoCatGrupoController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdProdutoCatRepository cdProdutoCatRp;
	@Autowired
	private CdProdutoGrupoRepository cdProdutoGrupoRp;
	@Autowired
	private CdProdutoSubgrupoRepository cdProdutoSubgrupoRp;
	@Autowired
	private ClimbaService climbaService;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	// CATEGORIAS ********************
	@RequestAuthorization
	@GetMapping("/cat/{id}")
	public ResponseEntity<CdProdutoCat> cdCdProdutoCat(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdProdutoCat> obj = cdProdutoCatRp.findById(id);
		return new ResponseEntity<CdProdutoCat>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/cat")
	public ResponseEntity<?> cadastrarCdProdutoCat(@RequestBody CdProdutoCat cdProdutoCat) throws Exception {
		if (ca.hasAuth(prm, 65, "C", "CATEGORIA: " + cdProdutoCat.getNome())) {
			CdProdutoCat cd = cdProdutoCatRp.save(cdProdutoCat);
			// Verifica integracao Climba--------------------
			String ret[] = climbaService.cadCategoria(cd, "POST");
			if (ret[0].equals("201")) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				cdProdutoCatRp.deleteById(cd.getId());
				return new ResponseEntity<String>(ret[1], HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PutMapping("/cat")
	public ResponseEntity<?> atualizarCdProdutoCat(@RequestBody CdProdutoCat cdProdutoCat) throws Exception {
		if (ca.hasAuth(prm, 65, "A", "CATEGORIA: " + cdProdutoCat.getNome())) {
			CdProdutoCat cd = cdProdutoCatRp.save(cdProdutoCat);
			// Verifica integracao Climba--------------------
			String ret[] = climbaService.cadCategoria(cd, "PUT");
			if (ret[0].equals("201")) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(ret[1], HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@DeleteMapping("/cat/{id}")
	public ResponseEntity<?> removerCdProdutoCat(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 65, "R", "ID CATEGORIA" + id)) {
			cdProdutoCatRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cat/lista")
	public ResponseEntity<List<CdProdutoCat>> listaCdProdutoCat() throws InterruptedException {
		List<CdProdutoCat> lista = cdProdutoCatRp.findAll(Sort.by("nome"));
		return new ResponseEntity<List<CdProdutoCat>>(lista, HttpStatus.OK);
	}
	// CATEGORIAS ********************

	// GRUPOS ************************
	@RequestAuthorization
	@GetMapping("/grupo/{id}")
	public ResponseEntity<CdProdutoGrupo> cdCdProdutoGrupo(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdProdutoGrupo> obj = cdProdutoGrupoRp.findById(id);
		return new ResponseEntity<CdProdutoGrupo>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/grupo")
	public ResponseEntity<?> cadastrarCdProdutoGrupo(@RequestBody CdProdutoGrupo cdProdutoGrupo) throws Exception {
		if (ca.hasAuth(prm, 65, "C", "GRUPO: " + cdProdutoGrupo.getNome())) {
			CdProdutoGrupo cd = cdProdutoGrupoRp.save(cdProdutoGrupo);
			// Verifica integracao Climba--------------------
			String ret[] = climbaService.cadGrupo(cd, "POST");
			if (ret[0].equals("201")) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				cdProdutoGrupoRp.deleteById(cd.getId());
				return new ResponseEntity<String>(ret[1], HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PutMapping("/grupo")
	public ResponseEntity<?> atualizarCdProdutoGrupo(@RequestBody CdProdutoGrupo cdProdutoGrupo) throws Exception {
		if (ca.hasAuth(prm, 65, "A", "GRUPO: " + cdProdutoGrupo.getNome())) {
			CdProdutoGrupo cd = cdProdutoGrupoRp.save(cdProdutoGrupo);
			// Verifica integracao Climba--------------------
			String ret[] = climbaService.cadGrupo(cd, "PUT");
			if (ret[0].equals("201")) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(ret[1], HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@DeleteMapping("/grupo/{id}")
	public ResponseEntity<?> removerCdProdutoGrupo(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 65, "R", "ID GRUPO" + id)) {
			cdProdutoGrupoRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/grupo/lista")
	public ResponseEntity<List<CdProdutoGrupo>> listaCdProdutoGrupo() throws InterruptedException {
		List<CdProdutoGrupo> lista = cdProdutoGrupoRp.findAll(Sort.by("nome"));
		return new ResponseEntity<List<CdProdutoGrupo>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/grupo/lista/cat/{id}")
	public ResponseEntity<List<CdProdutoGrupo>> listaCdProdutoGrupoCategoria(@PathVariable("id") Integer id)
			throws InterruptedException {
		List<CdProdutoGrupo> lista = cdProdutoGrupoRp.findAllByCategoria(id);
		return new ResponseEntity<List<CdProdutoGrupo>>(lista, HttpStatus.OK);
	}
	// GRUPOS ************************

	// SUBGRUPOS *********************
	@RequestAuthorization
	@GetMapping("/subgrupo/{id}")
	public ResponseEntity<CdProdutoSubgrupo> cdCdProdutoSubgrupo(@PathVariable("id") Integer id)
			throws InterruptedException {
		Optional<CdProdutoSubgrupo> obj = cdProdutoSubgrupoRp.findById(id);
		return new ResponseEntity<CdProdutoSubgrupo>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/subgrupo")
	public ResponseEntity<?> cadastrarCdProdutoSubgrupo(@RequestBody CdProdutoSubgrupo cdProdutoSubgrupo)
			throws Exception {
		if (ca.hasAuth(prm, 65, "C", "SUBGRUPO: " + cdProdutoSubgrupo.getNome())) {
			cdProdutoSubgrupoRp.save(cdProdutoSubgrupo);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/subgrupo")
	public ResponseEntity<?> atualizarCdProdutoSubgrupo(@RequestBody CdProdutoSubgrupo cdProdutoSubgrupo)
			throws Exception {
		if (ca.hasAuth(prm, 65, "A", "SUBGRUPO: " + cdProdutoSubgrupo.getNome())) {
			cdProdutoSubgrupoRp.save(cdProdutoSubgrupo);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/subgrupo/{id}")
	public ResponseEntity<?> removerCdProdutoSubgrupo(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 65, "R", "ID SUBGRUPO" + id)) {
			cdProdutoSubgrupoRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/subgrupo/lista/grupo/{id}")
	public ResponseEntity<List<CdProdutoSubgrupo>> listaCdProdutoSubgrupoCategoria(@PathVariable("id") Integer id)
			throws InterruptedException {
		List<CdProdutoSubgrupo> lista = cdProdutoSubgrupoRp.findAllByGrupo(id);
		return new ResponseEntity<List<CdProdutoSubgrupo>>(lista, HttpStatus.OK);
	}
	// SUBGRUPOS *********************
}
