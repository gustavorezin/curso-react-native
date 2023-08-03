package com.midas.api.controller;

import java.io.Serializable;
import java.util.Date;
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
import com.midas.api.tenant.entity.CdEmail;
import com.midas.api.tenant.repository.CdEmailRepository;
import com.midas.api.tenant.service.EmailService;

@RestController
@RequestMapping("/private/cdemail")
public class CdEmailController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdEmailRepository cdEmailRp;
	@Autowired
	private EmailService emailService;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/email/{id}")
	public ResponseEntity<CdEmail> cdEmail(@PathVariable("id") Integer id) {
		Optional<CdEmail> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdEmailRp.findById(id);
		} else {
			obj = cdEmailRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdEmail>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/email")
	public ResponseEntity<?> cadastrarCdEmail(@RequestBody CdEmail cdEmail) throws Exception {
		if (ca.hasAuth(prm, 37, "C", cdEmail.getNome())) {
			cdEmailRp.save(cdEmail);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/email")
	public ResponseEntity<?> atualizarCdEmail(@RequestBody CdEmail cdEmail) throws Exception {
		if (ca.hasAuth(prm, 37, "A", cdEmail.getNome())) {
			cdEmail.setDataat(new Date(System.currentTimeMillis()));
			cdEmailRp.save(cdEmail);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/email/emailteste/{id}")
	public ResponseEntity<CdEmail> testeCdEmail(@PathVariable("id") Integer id) throws Exception {
		Optional<CdEmail> email = null;
		if (ca.hasAuth(prm, 37, "L", "Teste e-mail de ID " + id)) {
			email = cdEmailRp.findById(id);
			emailService.enviaEmailTeste(email.get());
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<CdEmail>(email.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/email/{id}")
	public ResponseEntity<?> removerCdEmail(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 37, "R", "ID " + id)) {
			cdEmailRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/email/listatodos")
	public ResponseEntity<List<CdEmail>> listaTodos() {
		List<CdEmail> lista = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			lista = cdEmailRp.findAll(Sort.by("nome"));
		} else {
			lista = cdEmailRp.findAllLocalTodos(prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<List<CdEmail>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/email/p/{pagina}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdEmail>> listaCdEmailsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 37, "L", "LISTAGEM / CONSULTA")) {
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
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdEmail> lista = null;
			if (aclocal.equals("S")) {
				lista = cdEmailRp.findAll(pageable);
			} else {
				lista = cdEmailRp.findAllLocal(prm.cliente().getCdpessoaemp(), pageable);
			}
			return new ResponseEntity<Page<CdEmail>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
