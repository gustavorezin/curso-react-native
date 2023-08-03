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
import com.midas.api.tenant.entity.CdProdutoCor;
import com.midas.api.tenant.entity.CdProdutoCorRel;
import com.midas.api.tenant.repository.CdProdutoCorRelRepository;
import com.midas.api.tenant.repository.CdProdutoCorRepository;
import com.midas.api.tenant.service.integra.ClimbaService;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdcor")
public class CdProdutoCorController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdProdutoCorRepository cdCorRp;
	@Autowired
	private CdProdutoCorRelRepository cdProdutoCorRelRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private ClimbaService climbaService;

	@RequestAuthorization
	@GetMapping("/cor/{id}")
	public ResponseEntity<CdProdutoCor> cdProdutoCor(@PathVariable("id") Integer id) {
		Optional<CdProdutoCor> obj = cdCorRp.findById(id);
		return new ResponseEntity<CdProdutoCor>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cor")
	public ResponseEntity<?> cadastrarCdProdutoCor(@RequestBody CdProdutoCor cdCor) throws Exception {
		if (ca.hasAuth(prm, 65, "C", cdCor.getCor())) {
			CdProdutoCor cd = cdCorRp.save(cdCor);
			// Verifica integracao Climba--------------------
			String ret[] = climbaService.cadCorTam(cd, null, "POST", "1");
			if (ret[0].equals("201")) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				cdCorRp.deleteById(cd.getId());
				return new ResponseEntity<String>(ret[1], HttpStatus.FAILED_DEPENDENCY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cor")
	public ResponseEntity<?> atualizarCdProdutoCor(@RequestBody CdProdutoCor cdCor) throws Exception {
		if (ca.hasAuth(prm, 65, "A", cdCor.getCor())) {
			CdProdutoCor cd = cdCorRp.save(cdCor);
			// Verifica integracao Climba--------------------
			String ret[] = climbaService.cadCorTam(cd, null, "PUT", "1");
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
	@DeleteMapping("/cor/{id}")
	public ResponseEntity<?> removerCdProdutoCor(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 65, "R", "ID " + id)) {
			cdCorRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cor")
	public ResponseEntity<List<CdProdutoCor>> listaCdProdutoCors() throws Exception {
		List<CdProdutoCor> lista = cdCorRp.findAll(Sort.by("cor"));
		return new ResponseEntity<List<CdProdutoCor>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cor/produto/{id}")
	public ResponseEntity<List<CdProdutoCorRel>> listaCdProdutoCorsItem(@PathVariable("id") Long id) throws Exception {
		List<CdProdutoCorRel> lista = cdProdutoCorRelRp.findAllByIdProduto(id);
		return new ResponseEntity<List<CdProdutoCorRel>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cor/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdProdutoCor>> listaCdProdutoCorsBusca(@PathVariable("pagina") int pagina,
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
			Page<CdProdutoCor> lista = cdCorRp.findAllByCorBusca(busca, pageable);
			return new ResponseEntity<Page<CdProdutoCor>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cor/apcor")
	public ResponseEntity<?> adicionarCdProdutoCorRel(@RequestBody List<CdProdutoCorRel> cdProdutoCorRel)
			throws Exception {
		if (ca.hasAuth(prm, 6, "C", "INCLUSAO DE COR DE PRODUTO ")) {
			for (CdProdutoCorRel cc : cdProdutoCorRel) {
				cdProdutoCorRelRp.save(cc);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/cor/apcor/{id}")
	public ResponseEntity<?> removerCdProdutoCorRel(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 6, "R", "ID REMOCAO COR DE PRODUTO " + id)) {
			cdProdutoCorRelRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
