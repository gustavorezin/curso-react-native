package com.midas.api.controller;

import java.io.Serializable;
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
import com.midas.api.tenant.entity.CdNfeMe;
import com.midas.api.tenant.repository.CdNfeMeRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdnfeme")
public class CdNfeMeController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdNfeMeRepository cdNfeMeRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/me/{id}")
	public ResponseEntity<CdNfeMe> cdNfeMe(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdNfeMe> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdNfeMeRp.findById(id);
		} else {
			obj = cdNfeMeRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdNfeMe>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/me/local/{id}")
	public ResponseEntity<CdNfeMe> cdNfeMeLocal(@PathVariable("id") Long id) throws InterruptedException {
		CdNfeMe obj = cdNfeMeRp.findByLocal(id);
		return new ResponseEntity<CdNfeMe>(obj, HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/me")
	public ResponseEntity<?> cadastrarCdNfeMe(@RequestBody CdNfeMe cdNfeMe) throws Exception {
		if (ca.hasAuth(prm, 18, "C", cdNfeMe.getMarca())) {
			if (cdNfeMeRp.findByIdAndCdpessoaemp(cdNfeMe.getCdpessoaemp().getId()) == null) {
				cdNfeMeRp.save(cdNfeMe);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/me")
	public ResponseEntity<?> atualizarCdNfeMe(@RequestBody CdNfeMe cdNfeMe) throws Exception {
		if (ca.hasAuth(prm, 18, "A", cdNfeMe.getMarca())) {
			cdNfeMeRp.save(cdNfeMe);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/me/{id}")
	public ResponseEntity<?> removerCdNfeMe(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 18, "R", "ID " + id)) {
			cdNfeMeRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/me/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdNfeMe>> listaCdNfeMesBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 18, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
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
			Page<CdNfeMe> lista = null;
			if (local > 0) {
				lista = cdNfeMeRp.findAllByNomeBuscaLocal(local, busca, pageable);
			} else {
				lista = cdNfeMeRp.findAllByNomeBusca(busca, pageable);
			}
			return new ResponseEntity<Page<CdNfeMe>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
