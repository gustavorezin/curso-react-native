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
import com.midas.api.tenant.entity.CdProdutoMarca;
import com.midas.api.tenant.repository.CdProdutoMarcaRepository;
import com.midas.api.tenant.service.integra.ClimbaService;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdmarca")
public class CdProdutoMarcaController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdProdutoMarcaRepository cdMarcaRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private ClimbaService climbaService;

	@RequestAuthorization
	@GetMapping("/marca/{id}")
	public ResponseEntity<CdProdutoMarca> cdProdutoMarca(@PathVariable("id") Integer id) {
		Optional<CdProdutoMarca> obj = cdMarcaRp.findById(id);
		return new ResponseEntity<CdProdutoMarca>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/marca")
	public ResponseEntity<?> cadastrarCdProdutoMarca(@RequestBody CdProdutoMarca cdMarca) throws Exception {
		if (ca.hasAuth(prm, 65, "C", cdMarca.getNome())) {
			CdProdutoMarca cd = cdMarcaRp.save(cdMarca);
			// Verifica integracao Climba--------------------
			String ret[] = climbaService.cadMarca(cd, "POST");
			if (ret[0].equals("201")) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				cdMarcaRp.deleteById(cd.getId());
				return new ResponseEntity<String>(ret[1], HttpStatus.FAILED_DEPENDENCY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/marca")
	public ResponseEntity<?> atualizarCdProdutoMarca(@RequestBody CdProdutoMarca cdMarca) throws Exception {
		if (ca.hasAuth(prm, 65, "A", cdMarca.getNome())) {
			CdProdutoMarca cd = cdMarcaRp.save(cdMarca);
			// Verifica integracao Climba--------------------
			String ret[] = climbaService.cadMarca(cd, "PUT");
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
	@DeleteMapping("/marca/{id}")
	public ResponseEntity<?> removerCdProdutoMarca(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 65, "R", "ID " + id)) {
			// Nao permite remover marca numero 1
			if (id.equals(1)) {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			} else {
				cdMarcaRp.deleteById(id);
				return ResponseEntity.ok(HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/marca")
	public ResponseEntity<List<CdProdutoMarca>> listaCdProdutoMarcas() throws Exception {
		List<CdProdutoMarca> lista = cdMarcaRp.findAll(Sort.by("nome"));
		return new ResponseEntity<List<CdProdutoMarca>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/marca/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdProdutoMarca>> listaCdProdutoMarcasBusca(@PathVariable("pagina") int pagina,
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
			Page<CdProdutoMarca> lista = cdMarcaRp.findAllByNomeBusca(busca, pageable);
			return new ResponseEntity<Page<CdProdutoMarca>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
