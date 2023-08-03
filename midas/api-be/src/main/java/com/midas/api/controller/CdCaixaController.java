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
import com.midas.api.tenant.entity.CdCaixa;
import com.midas.api.tenant.repository.CdCaixaRepository;
import com.midas.api.tenant.repository.CdCustomRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdcaixa")
public class CdCaixaController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdCaixaRepository cdCaixaRp;
	@Autowired
	private CdCustomRepository cdCustomRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/caixa/{id}")
	public ResponseEntity<CdCaixa> cdCaixa(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdCaixa> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdCaixaRp.findById(id);
		} else {
			obj = cdCaixaRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdCaixa>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/caixa")
	public ResponseEntity<?> cadastrarCdCaixa(@RequestBody CdCaixa cdCaixa) throws Exception {
		if (ca.hasAuth(prm, 2, "C", cdCaixa.getNome())) {
			cdCaixaRp.save(cdCaixa);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/caixa")
	public ResponseEntity<?> atualizarCdCaixa(@RequestBody CdCaixa cdCaixa) throws Exception {
		if (ca.hasAuth(prm, 2, "A", cdCaixa.getNome())) {
			cdCaixaRp.save(cdCaixa);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/caixa/{id}")
	public ResponseEntity<?> removerCdCaixa(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 2, "R", "ID " + id)) {
			Long local = prm.cliente().getCdpessoaemp();
			Integer conta = cdCaixaRp.findByLocal(local);
			// Nao remover item principal
			if (conta > 1) {
				cdCaixaRp.deleteById(id);
				return ResponseEntity.ok(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.LOCKED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/caixa/tp/{tipo}")
	public ResponseEntity<List<CdCaixa>> listaCdCaixasTipo(@PathVariable("tipo") String tipo) throws Exception {
		List<CdCaixa> lista = cdCaixaRp.findAllByTipo(tipo);
		return new ResponseEntity<List<CdCaixa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/caixa/ativo/emp/{emp}")
	public ResponseEntity<List<CdCaixa>> listaCdCaixasAtivoEmpresa(@PathVariable("emp") Long emp) throws Exception {
		List<CdCaixa> lista = cdCaixaRp.findAllByAtivosLocal(emp, "ATIVO");
		return new ResponseEntity<List<CdCaixa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/caixa/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdCaixa>> listaCdCaixasBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 2, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<CdCaixa> lista = cdCustomRp.listaCdCaixa(local, busca, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<CdCaixa>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
