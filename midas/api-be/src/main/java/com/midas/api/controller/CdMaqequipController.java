package com.midas.api.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.midas.api.tenant.entity.CdMaqequip;
import com.midas.api.tenant.repository.CdCustomRepository;
import com.midas.api.tenant.repository.CdMaqequipRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdmaqequip")
public class CdMaqequipController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdMaqequipRepository cdMaqequipRp;
	@Autowired
	private CdCustomRepository cdCustomRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/me/{id}")
	public ResponseEntity<CdMaqequip> cdMaqequip(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdMaqequip> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdMaqequipRp.findById(id);
		} else {
			obj = cdMaqequipRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdMaqequip>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/me")
	public ResponseEntity<?> cadastrarCdMaqequip(@RequestBody CdMaqequip cdMaqequip) throws Exception {
		if (ca.hasAuth(prm, 74, "C", cdMaqequip.getNome())) {
			cdMaqequipRp.save(cdMaqequip);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/me")
	public ResponseEntity<?> atualizarCdMaqequip(@RequestBody CdMaqequip cdMaqequip) throws Exception {
		if (ca.hasAuth(prm, 74, "A", cdMaqequip.getNome())) {
			cdMaqequipRp.save(cdMaqequip);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/me/{id}")
	public ResponseEntity<?> removerCdMaqequip(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 74, "R", "ID " + id)) {
			cdMaqequipRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/me/ativo/emp/{emp}")
	public ResponseEntity<List<CdMaqequip>> listaCdMaqequipsAtivoEmpresa(@PathVariable("emp") Long emp)
			throws Exception {
		List<CdMaqequip> lista = cdMaqequipRp.findAllByAtivosLocal(emp, "ATIVO");
		return new ResponseEntity<List<CdMaqequip>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/me/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdMaqequip>> listaCdMaqequipsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 74, "L", "LISTAGEM / CONSULTA CADASTRO MAQUINAS E EQUIPAMENTOS")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<CdMaqequip> lista = cdCustomRp.listaCdMaqequip(local, busca, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<CdMaqequip>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
