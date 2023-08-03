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
import com.midas.api.tenant.entity.CdXmlAutoriza;
import com.midas.api.tenant.repository.CdXmlAutorizaRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdxmlautoriza")
public class CdXmlAutorizaController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdXmlAutorizaRepository cdXmlAutorizaRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/xmlautoriza/{id}")
	public ResponseEntity<CdXmlAutoriza> cdXmlAutoriza(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdXmlAutoriza> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdXmlAutorizaRp.findById(id);
		} else {
			obj = cdXmlAutorizaRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdXmlAutoriza>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/xmlautoriza")
	public ResponseEntity<?> cadastrarCdXmlAutoriza(@RequestBody CdXmlAutoriza cdXmlAutoriza) throws Exception {
		if (ca.hasAuth(prm, 41, "C", cdXmlAutoriza.getNome())) {
			CdXmlAutoriza obj = cdXmlAutorizaRp.findByCdpessoaempAndTpdocAndCnpj(cdXmlAutoriza.getCdpessoaemp().getId(),
					cdXmlAutoriza.getTpdoc(), cdXmlAutoriza.getCnpj(), 0);
			if (obj == null) {
				cdXmlAutorizaRp.save(cdXmlAutoriza);
				return ResponseEntity.ok(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PutMapping("/xmlautoriza")
	public ResponseEntity<?> atualizarCdXmlAutoriza(@RequestBody CdXmlAutoriza cdXmlAutoriza) throws Exception {
		if (ca.hasAuth(prm, 41, "A", cdXmlAutoriza.getNome())) {
			CdXmlAutoriza obj = cdXmlAutorizaRp.findByCdpessoaempAndTpdocAndCnpj(cdXmlAutoriza.getCdpessoaemp().getId(),
					cdXmlAutoriza.getTpdoc(), cdXmlAutoriza.getCnpj(), cdXmlAutoriza.getId());
			if (obj == null) {
				cdXmlAutorizaRp.save(cdXmlAutoriza);
				return ResponseEntity.ok(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@DeleteMapping("/xmlautoriza/{id}")
	public ResponseEntity<?> removerCdXmlAutoriza(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 41, "R", "ID " + id)) {
			cdXmlAutorizaRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/xmlautoriza/local/{local}/tpdoc/{tpdoc}")
	public ResponseEntity<List<CdXmlAutoriza>> listaCdXmlAutorizasTipo(@PathVariable("local") Long local,
			@PathVariable("tpdoc") String tpdoc) throws Exception {
		List<CdXmlAutoriza> lista = cdXmlAutorizaRp.findAllByTpdocAndLocal(local, tpdoc);
		return new ResponseEntity<List<CdXmlAutoriza>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/xmlautoriza/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdXmlAutoriza>> listaCdXmlAutorizasBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 41, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdXmlAutoriza> lista = null;
			if (aclocal.equals("S")) {
				lista = cdXmlAutorizaRp.findAllByNomeBusca(busca, pageable);
			} else {
				lista = cdXmlAutorizaRp.findAllByNomeBuscaLocal(prm.cliente().getCdpessoaemp(), busca, pageable);
			}
			return new ResponseEntity<Page<CdXmlAutoriza>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
