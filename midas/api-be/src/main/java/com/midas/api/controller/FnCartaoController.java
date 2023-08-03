package com.midas.api.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.FnCartao;
import com.midas.api.tenant.repository.FnCartaoCustomRepository;
import com.midas.api.tenant.repository.FnCartaoRepository;
import com.midas.api.tenant.service.FnCartaoService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.NumUtil;

@RestController
@RequestMapping("/private/fncartao")
public class FnCartaoController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FnCartaoRepository fnCartaoRp;
	@Autowired
	private FnCartaoCustomRepository fnCartaoCustomRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private FnCartaoService fnCartaoService;

	@RequestAuthorization
	@GetMapping("/cartao/{id}")
	public ResponseEntity<FnCartao> fnCartao(@PathVariable("id") Long id) throws InterruptedException {
		Optional<FnCartao> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = fnCartaoRp.findById(id);
		} else {
			obj = fnCartaoRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<FnCartao>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/cartao")
	public ResponseEntity<?> atualizarFnCartao(@RequestBody FnCartao fnCartao) throws Exception {
		if (ca.hasAuth(prm, 55, "A", fnCartao.getCdcartao().getNome())) {
			fnCartaoRp.save(fnCartao);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/cartao/{id}")
	public ResponseEntity<?> removerFnCartao(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 55, "R", "ID " + id)) {
			fnCartaoRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cartao/previstos")
	public ResponseEntity<List<FnCartao>> listaFnCartaoPrevistos() throws Exception {
		if (ca.hasAuth(prm, 55, "L", "LISTAGEM / CONSULTA")) {
			List<FnCartao> lista = null;
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				lista = fnCartaoRp.listaCartaoPrevistos();
			} else {
				lista = fnCartaoRp.listaCartaoPrevistosLocal(prm.cliente().getCdpessoaemp());
			}
			return new ResponseEntity<List<FnCartao>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cartao/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/lc/{local}/cx/{caixa}/ct/{cartao}/dti/{dataini}/dtf/{datafim}/ope/{ope}/tipo/{tipo}/status/{status}")
	public ResponseEntity<Page<FnCartao>> listaFnCartaoBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp,
			@PathVariable("local") Long local, @PathVariable("caixa") Integer caixa,
			@PathVariable("cartao") Integer cartao, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("ope") String ope, @PathVariable("tipo") String tipo,
			@PathVariable("status") String status) throws Exception {
		if (ca.hasAuth(prm, 53, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<FnCartao> lista = fnCartaoCustomRp.listaFnCartao(local, caixa, cartao, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ope, ordem, ordemdir, tipo, status, pageable);
			return new ResponseEntity<Page<FnCartao>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cartao/valores/b/{busca}/lc/{local}/cx/{caixa}/ct/{cartao}/dti/{dataini}/dtf/{datafim}/ope/{ope}/tipo/{tipo}/status/{status}")
	public ResponseEntity<List<FnCartao>> listaFnCartaoBuscaValores(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("caixa") Integer caixa,
			@PathVariable("cartao") Integer cartao, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("ope") String ope, @PathVariable("tipo") String tipo,
			@PathVariable("status") String status) throws Exception {
		if (ca.hasAuth(prm, 11, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<FnCartao> lista = fnCartaoCustomRp.listaFnCartaoValores(local, caixa, cartao, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ope, tipo, status);
			return new ResponseEntity<List<FnCartao>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cartao/alterar/emlote/status/{status}")
	public ResponseEntity<?> compensarFnCartaoLista(@RequestBody List<FnCartao> cartoes,
			@PathVariable("status") String status) throws Exception {
		if (ca.hasAuth(prm, 51, "A", "ALTERA MULTIPLOS CARTOES")) {
			if (cartoes != null) {
				Integer transacao = null;
				if (status.equals("02")) {
					transacao = NumUtil.geraNumAleaInteger();
				}
				for (FnCartao c : cartoes) {
					fnCartaoRp.atualizarCartoes(c.getPtaxa(), c.getVtaxa(), c.getVfinal(), status, transacao,
							c.getId());
					// Grava operacoes
					fnCartaoService.regOperacao(c, transacao, status);
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
