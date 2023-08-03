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
import com.midas.api.tenant.entity.CdCteCfg;
import com.midas.api.tenant.repository.CdCteCfgRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdctecfg")
public class CdCteCfgController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdCteCfgRepository cdCteCfgRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/ctecfg/{id}")
	public ResponseEntity<CdCteCfg> cdCteCfg(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdCteCfg> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdCteCfgRp.findById(id);
		} else {
			obj = cdCteCfgRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdCteCfg>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/ctecfg")
	public ResponseEntity<?> cadastrarCdCteCfg(@RequestBody CdCteCfg cdCteCfg) throws Exception {
		if (ca.hasAuth(prm, 77, "C", cdCteCfg.getCfop())) {
			cdCteCfgRp.save(cdCteCfg);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PutMapping("/ctecfg")
	public ResponseEntity<?> atualizarCdCteCfg(@RequestBody CdCteCfg cdCteCfg) throws Exception {
		if (ca.hasAuth(prm, 77, "A", cdCteCfg.getCfop())) {
			cdCteCfgRp.save(cdCteCfg);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@DeleteMapping("/ctecfg/{id}")
	public ResponseEntity<?> removerCdCteCfg(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 77, "R", "ID " + id)) {
			cdCteCfgRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/ctecfg/local/{local}")
	public ResponseEntity<List<CdCteCfg>> listaCdCteCfgs(@PathVariable("local") Long local) throws Exception {
		List<CdCteCfg> lista = cdCteCfgRp.findAllByLocal(local);
		return new ResponseEntity<List<CdCteCfg>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/ctecfg/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdCteCfg>> listaCdCteCfgsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 77, "L", "LISTAGEM / CONSULTA")) {
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
			Page<CdCteCfg> lista = null;
			if (aclocal.equals("S")) {
				lista = cdCteCfgRp.findAllByNomeBusca(busca, pageable);
			} else {
				lista = cdCteCfgRp.findAllByNomeBuscaLocal(prm.cliente().getCdpessoaemp(), busca, pageable);
			}
			return new ResponseEntity<Page<CdCteCfg>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
