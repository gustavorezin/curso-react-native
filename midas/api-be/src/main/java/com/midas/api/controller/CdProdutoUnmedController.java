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
import com.midas.api.tenant.entity.CdProdutoUnmed;
import com.midas.api.tenant.repository.CdProdutoUnmedRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdunmed")
public class CdProdutoUnmedController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdProdutoUnmedRepository cdUnmedRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/unmed/{id}")
	public ResponseEntity<CdProdutoUnmed> cdProdutoUnmed(@PathVariable("id") Integer id) {
		Optional<CdProdutoUnmed> obj = cdUnmedRp.findById(id);
		return new ResponseEntity<CdProdutoUnmed>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/unmed")
	public ResponseEntity<?> cadastrarCdProdutoUnmed(@RequestBody CdProdutoUnmed cdUnmed) throws Exception {
		if (ca.hasAuth(prm, 9, "C", cdUnmed.getNome())) {
			cdUnmedRp.save(cdUnmed);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/unmed")
	public ResponseEntity<?> atualizarCdProdutoUnmed(@RequestBody CdProdutoUnmed cdUnmed) throws Exception {
		if (ca.hasAuth(prm, 9, "A", cdUnmed.getNome())) {
			cdUnmedRp.save(cdUnmed);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/unmed/{id}")
	public ResponseEntity<?> removerCdProdutoUnmed(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 9, "R", "ID " + id)) {
			cdUnmedRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/unmed")
	public ResponseEntity<List<CdProdutoUnmed>> listaCdProdutoUnmeds() throws Exception {
		List<CdProdutoUnmed> lista = cdUnmedRp.findAll(Sort.by("nome"));
		return new ResponseEntity<List<CdProdutoUnmed>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/unmed/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdProdutoUnmed>> listaCdProdutoUnmedsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 9, "L", "LISTAGEM / CONSULTA")) {
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
			Page<CdProdutoUnmed> lista = cdUnmedRp.findAllByNomeBusca(busca, pageable);
			return new ResponseEntity<Page<CdProdutoUnmed>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
