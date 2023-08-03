package com.midas.api.controller;

import java.io.Serializable;
import java.math.BigDecimal;
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
import com.midas.api.tenant.entity.CdCondPag;
import com.midas.api.tenant.entity.CdCondPagDia;
import com.midas.api.tenant.repository.CdCondPagDiaRepository;
import com.midas.api.tenant.repository.CdCondPagRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdcondpag")
public class CdCondPagController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdCondPagRepository condPagRp;
	@Autowired
	private CdCondPagDiaRepository condPagDiaRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/condpag/{id}")
	public ResponseEntity<CdCondPag> cdCondPag(@PathVariable("id") Integer id) {
		Optional<CdCondPag> obj = condPagRp.findById(id);
		return new ResponseEntity<CdCondPag>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/condpagdia/{id}")
	public ResponseEntity<CdCondPagDia> cdCondPaDia(@PathVariable("id") Integer id) {
		Optional<CdCondPagDia> obj = condPagDiaRp.findById(id);
		return new ResponseEntity<CdCondPagDia>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/condpag")
	public ResponseEntity<?> cadastrarCdCondPag(@RequestBody CdCondPag cdCondPag) throws Exception {
		if (ca.hasAuth(prm, 5, "C", cdCondPag.getNome())) {
			condPagRp.save(cdCondPag);
			// Cria parcelas
			Integer dias = cdCondPag.getInicial();
			for (int x = 1; x <= cdCondPag.getQtd(); x++) {
				CdCondPagDia cp = new CdCondPagDia();
				cp.setCdcondpag(cdCondPag);
				if (x == 1) {
					cp.setDias(cdCondPag.getInicial());
				}
				if (x != 1) {
					dias = dias + cdCondPag.getIntervalo();
					cp.setDias(dias);
				}
				cp.setJuros(BigDecimal.valueOf(0.00));
				condPagDiaRp.save(cp);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/condpag")
	public ResponseEntity<?> atualizarCdCondPag(@RequestBody CdCondPag cdCondPag) throws Exception {
		if (ca.hasAuth(prm, 5, "A", cdCondPag.getNome())) {
			CdCondPag c = condPagRp.save(cdCondPag);
			// Remove parcelas anteriores
			condPagDiaRp.deleteByCdcondpag(cdCondPag);
			// Cria parcelas
			Integer dias = cdCondPag.getInicial();
			for (int x = 1; x <= cdCondPag.getQtd(); x++) {
				CdCondPagDia cp = new CdCondPagDia();
				cp.setCdcondpag(c);
				if (x == 1) {
					cp.setDias(cdCondPag.getInicial());
				}
				if (x != 1) {
					dias = dias + cdCondPag.getIntervalo();
					cp.setDias(dias);
				}
				cp.setJuros(BigDecimal.valueOf(0.00));
				condPagDiaRp.save(cp);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/condpagdia/{idcondpag}")
	public ResponseEntity<?> atualizarCdCondPagDia(@RequestBody CdCondPagDia cdCondPagDia,
			@PathVariable("idcondpag") Integer idcondpag) throws Exception {
		if (ca.hasAuth(prm, 5, "C", cdCondPagDia.getCdcondpag().getNome())) {
			Optional<CdCondPag> cp = condPagRp.findById(idcondpag);
			cdCondPagDia.setCdcondpag(cp.get());
			condPagDiaRp.save(cdCondPagDia);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/condpag/{id}")
	public ResponseEntity<?> removerCdCondPag(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 5, "R", "ID " + id)) {
			// Nao remover item numero 1 - A vista
			if (id > 1) {
				condPagRp.deleteById(id);
				return ResponseEntity.ok(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.LOCKED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/condpag")
	public ResponseEntity<List<CdCondPag>> listaCdCondPagsAtivo() throws Exception {
		// Verifica se representante
		Long respvend = 0L;
		if (prm.cliente().getRole().getId() == 8) {
			respvend = 1L;
		}
		List<CdCondPag> lista = null;
		if (respvend > 0) {
			lista = condPagRp.findAllByStatusVend("S", "ATIVO");
		} else {
			lista = condPagRp.findAllByStatus("ATIVO", Sort.by("nome"));
		}
		return new ResponseEntity<List<CdCondPag>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/condpag/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdCondPag>> listaCdCondPagsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 5, "L", "LISTAGEM / CONSULTA")) {
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
			Page<CdCondPag> lista = condPagRp.findAllByNomeBusca(busca, pageable);
			return new ResponseEntity<Page<CdCondPag>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
