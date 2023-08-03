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
import com.midas.api.tenant.entity.CdCartao;
import com.midas.api.tenant.repository.CdCartaoRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdcartao")
public class CdCartaoController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdCartaoRepository cdCartaoRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/cartao/{id}")
	public ResponseEntity<CdCartao> cdCartao(@PathVariable("id") Integer id) {
		Optional<CdCartao> obj = cdCartaoRp.findById(id);
		return new ResponseEntity<CdCartao>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/cartao")
	public ResponseEntity<?> cadastrarCdCartao(@RequestBody CdCartao cdCartao) throws Exception {
		if (ca.hasAuth(prm, 54, "C", cdCartao.getNome())) {
			cdCartaoRp.save(cdCartao);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/cartao")
	public ResponseEntity<?> atualizarCdCartao(@RequestBody CdCartao cdCartao) throws Exception {
		if (ca.hasAuth(prm, 54, "A", cdCartao.getNome())) {
			cdCartaoRp.save(cdCartao);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/cartao/{id}")
	public ResponseEntity<?> removerCdCartao(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 54, "R", "ID " + id)) {
			cdCartaoRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cartao/ativo/local/{local}")
	public ResponseEntity<List<CdCartao>> listaCdCartaos(@PathVariable("local") Long local) throws Exception {
		List<CdCartao> lista = cdCartaoRp.findAllByLocalAtivo(local);
		return new ResponseEntity<List<CdCartao>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cartao/ativo/cx/{caixa}/tipo/{tipo}")
	public ResponseEntity<List<CdCartao>> listaCdCartaos(@PathVariable("caixa") Integer caixa,
			@PathVariable("tipo") String tipo) throws Exception {
		List<CdCartao> lista = cdCartaoRp.findAllByCaixaAndTipoAtivo(caixa, tipo);
		return new ResponseEntity<List<CdCartao>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cartao/ativo/local/{local}/tipo/{tipo}")
	public ResponseEntity<List<CdCartao>> listaCdCartaosLocal(@PathVariable("local") Long local,
			@PathVariable("tipo") String tipo) throws Exception {
		List<CdCartao> lista = cdCartaoRp.findAllByLocalAndTipoAtivo(local, tipo);
		return new ResponseEntity<List<CdCartao>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cartao/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdCartao>> listaCdCartaosBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 54, "L", "LISTAGEM / CONSULTA")) {
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
			Page<CdCartao> lista = null;
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				lista = cdCartaoRp.findAllByNomeBusca(busca, pageable);
			} else {
				lista = cdCartaoRp.findAllByNomeBuscaLocal(prm.cliente().getCdpessoaemp(), busca, pageable);
			}
			return new ResponseEntity<Page<CdCartao>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
