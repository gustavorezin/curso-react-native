package com.midas.api.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.midas.api.tenant.entity.CdClimbacfg;
import com.midas.api.tenant.repository.CdClimbacfgRepository;

@RestController
@RequestMapping("/private/cdclimbacfg")
public class CdClimacfgController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdClimbacfgRepository cdClimacfgRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/climbacfg/{id}")
	public ResponseEntity<CdClimbacfg> cdClimacfg(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdClimbacfg> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdClimacfgRp.findById(id);
		} else {
			obj = cdClimacfgRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdClimbacfg>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/climbacfg")
	public ResponseEntity<?> cadastrarCdClimbacfg(@RequestBody CdClimbacfg cdClimacfg) throws Exception {
		if (ca.hasAuth(prm, 85, "C", cdClimacfg.getLoja_nome())) {
			cdClimacfgRp.save(cdClimacfg);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/climbacfg")
	public ResponseEntity<?> atualizarCdClimbacfg(@RequestBody CdClimbacfg cdClimacfg) throws Exception {
		if (ca.hasAuth(prm, 85, "A", cdClimacfg.getLoja_nome())) {
			cdClimacfgRp.save(cdClimacfg);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/climbacfg/{id}")
	public ResponseEntity<?> removerCdClimbacfg(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 85, "R", "ID " + id)) {
			cdClimacfgRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/climbacfg")
	public ResponseEntity<List<CdClimbacfg>> listaCdClimbacfgsEmpresa() throws Exception {
		List<CdClimbacfg> lista = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			lista = cdClimacfgRp.findAll();
		} else {
			lista = cdClimacfgRp.findAllByLocal(prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<List<CdClimbacfg>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/climbacfg/ativo/emp/{emp}")
	public ResponseEntity<List<CdClimbacfg>> listaCdClimbacfgsAtivoEmpresa(@PathVariable("emp") Long emp)
			throws Exception {
		List<CdClimbacfg> lista = cdClimacfgRp.findAllByAtivosLocal(emp, "ATIVO");
		return new ResponseEntity<List<CdClimbacfg>>(lista, HttpStatus.OK);
	}
}
