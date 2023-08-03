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
import com.midas.api.tenant.entity.CdCcusto;
import com.midas.api.tenant.repository.CdCcustoRepository;

@RestController
@RequestMapping("/private/cdccusto")
public class CdCcustoController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdCcustoRepository cdCcustoRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/ccusto/{id}")
	public ResponseEntity<CdCcusto> cdCcusto(@PathVariable("id") Integer id) {
		Optional<CdCcusto> obj = cdCcustoRp.findById(id);
		return new ResponseEntity<CdCcusto>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/ccusto")
	public ResponseEntity<?> cadastrarCdCcusto(@RequestBody CdCcusto cdCcusto) throws Exception {
		if (ca.hasAuth(prm, 64, "C", cdCcusto.getNome())) {
			// Altera outros locais
			if (cdCcusto.getUsa_padrao().equals("S")) {
				cdCcustoRp.alteraTipoLocalSimilar(cdCcusto.getCdpessoaemp().getId());
			}
			cdCcustoRp.save(cdCcusto);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/ccusto")
	public ResponseEntity<?> atualizarCdCcusto(@RequestBody CdCcusto cdCcusto) throws Exception {
		if (ca.hasAuth(prm, 64, "A", cdCcusto.getNome())) {
			// Altera outros locais
			if (cdCcusto.getUsa_padrao().equals("S")) {
				cdCcustoRp.alteraTipoLocalSimilarLocal(cdCcusto.getId(), cdCcusto.getCdpessoaemp().getId());
			}
			cdCcusto.setDataat(new Date());
			cdCcustoRp.save(cdCcusto);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/ccusto/{id}")
	public ResponseEntity<?> removerCdCcusto(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 64, "R", "ID " + id)) {
			cdCcustoRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/ccusto/ativos/local/{local}")
	public ResponseEntity<List<CdCcusto>> listaCdCcustoAtivos(@PathVariable("local") Long local) throws Exception {
		List<CdCcusto> lista = cdCcustoRp.findAllByLocanAndStatus(local, "ATIVO");
		return new ResponseEntity<List<CdCcusto>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/ccusto/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdCcusto>> listaCdCcustoBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 64, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdCcusto> lista = null;
			if (busca == null || (busca != null && busca.trim().isEmpty()) || busca.equalsIgnoreCase("undefined")) {
				lista = cdCcustoRp.findAll(pageable);
			} else {
				lista = cdCcustoRp.findAllByNomeBusca(busca, pageable);
			}
			return new ResponseEntity<Page<CdCcusto>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
