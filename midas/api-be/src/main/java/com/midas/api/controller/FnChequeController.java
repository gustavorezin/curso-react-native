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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.FnCheque;
import com.midas.api.tenant.entity.FnChequeHist;
import com.midas.api.tenant.repository.FnChequeCustomRepository;
import com.midas.api.tenant.repository.FnChequeHistRepository;
import com.midas.api.tenant.repository.FnChequeRepository;
import com.midas.api.tenant.service.FnChequeService;
import com.midas.api.tenant.service.FnCxmvService;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/fncheque")
public class FnChequeController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FnChequeRepository fnChequeRp;
	@Autowired
	private FnChequeHistRepository fnChequeHistRp;
	@Autowired
	private FnChequeCustomRepository fnChequeCustomRp;
	@Autowired
	private FnChequeService fnChequeService;
	@Autowired
	private FnCxmvService fnCxmvService;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/cheque/{id}")
	public ResponseEntity<FnCheque> fnCheque(@PathVariable("id") Long id) throws InterruptedException {
		Optional<FnCheque> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = fnChequeRp.findById(id);
		} else {
			obj = fnChequeRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<FnCheque>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/cheque")
	public ResponseEntity<?> cadastrarFnCheque(@RequestBody FnCheque fnCheque) throws Exception {
		System.out.println("CAIU AQUI");
		try {
			if (ca.hasAuth(prm, 50, "C", fnCheque.getCdbancos_id() + " - " + fnCheque.getEmissor())) {
				fnCheque = fnChequeService.cadastraFncxmvFntitulo(fnCheque);
				fnChequeRp.save(fnCheque);
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/cheque")
	public ResponseEntity<?> atualizarFnCheque(@RequestBody FnCheque fnCheque) throws Exception {
		if (ca.hasAuth(prm, 51, "A", fnCheque.getCdbancos_id() + " - " + fnCheque.getEmissor())) {
			fnChequeRp.save(fnCheque);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/cheque/{id}")
	public ResponseEntity<?> removerFnCheque(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 52, "R", "ID " + id)) {
			fnChequeRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cheque/vencidos")
	public ResponseEntity<List<FnCheque>> listaFnChequesVencidos() throws Exception {
		if (ca.hasAuth(prm, 53, "L", "LISTAGEM / CONSULTA")) {
			List<FnCheque> lista = null;
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				lista = fnChequeRp.listaChequesVencidos();
			} else {
				lista = fnChequeRp.listaChequesVencidosLocal(prm.cliente().getCdpessoaemp());
			}
			return new ResponseEntity<List<FnCheque>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cheque/historico/tipo/{tipo}/cxmv/{cxmv}")
	public ResponseEntity<List<FnChequeHist>> listaFnChequesHist(@PathVariable("tipo") String tipo,
			@PathVariable("cxmv") Long cxmv) throws Exception {
		try {
			List<FnChequeHist> lista = null;
			if (tipo.equals("ini")) {
				lista = fnChequeHistRp.listaHistoricoIni(cxmv);
			} else {
				lista = fnChequeHistRp.listaHistoricoFim(cxmv);
			}
			return new ResponseEntity<List<FnChequeHist>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cheque/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/lc/{local}/cx/{caixa}/dti/{dataini}/dtf/{datafim}/ove/{ove}/tipo/{tipo}/status/{status}")
	public ResponseEntity<Page<FnCheque>> listaFnChequesBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp,
			@PathVariable("local") Long local, @PathVariable("caixa") Integer caixa,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("ove") String ove, @PathVariable("tipo") String tipo, @PathVariable("status") String status)
			throws Exception {
		if (ca.hasAuth(prm, 53, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<FnCheque> lista = fnChequeCustomRp.listaFnCheques(local, caixa, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ove, ordem, ordemdir, tipo, status, pageable);
			return new ResponseEntity<Page<FnCheque>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cheque/valores/b/{busca}/lc/{local}/cx/{caixa}/dti/{dataini}/dtf/{datafim}/ove/{ove}/tipo/{tipo}/status/{status}")
	public ResponseEntity<List<FnCheque>> listaFnChequesBuscaValores(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("caixa") Integer caixa,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("ove") String ove, @PathVariable("tipo") String tipo, @PathVariable("status") String status)
			throws Exception {
		if (ca.hasAuth(prm, 11, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<FnCheque> lista = fnChequeCustomRp.listaFnChequeValores(local, caixa, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ove, tipo, status);
			return new ResponseEntity<List<FnCheque>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cheque/transferir/emlote/lc/{local}/cx/{caixa}/data/{data}/status/{status}/motivo/{motivo}")
	public ResponseEntity<?> transferirFnChequeLista(@RequestBody List<FnCheque> cheques,
			@PathVariable("local") Long local, @PathVariable("caixa") Integer caixa, @PathVariable("data") String data,
			@PathVariable("status") String status, @PathVariable("motivo") String motivo) throws Exception {
		if (ca.hasAuth(prm, 51, "A", "TRANSFERENCIA DE MULTIPLOS CHEQUES")) {
			if (cheques != null) {
				// Movimenta valores no caixa
				fnCxmvService.regTransfereCheques(local, caixa, cheques, data, status, motivo);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cheque/compensar/emlote/data/{data}")
	public ResponseEntity<?> compensarFnChequeLista(@RequestBody List<FnCheque> cheques,
			@PathVariable("data") String data) throws Exception {
		if (ca.hasAuth(prm, 51, "A", "COMPENSA MULTIPLOS CHEQUES")) {
			if (cheques != null) {
				for (FnCheque c : cheques) {
					fnChequeRp.transferirCheque(c.getCdpessoaempatual().getId(), c.getCdcaixaatual().getId(), "04",
							c.getId());
					fnChequeService.regHistorico(c, c.getCdcaixaatual().getId(), c.getFncxmv().getId(),
							c.getFncxmv().getId(), Date.valueOf(data), "04", "C", "");
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cheque/cancelar/emlote/data/{data}/motivo/{motivo}")
	public ResponseEntity<?> cancelarFnChequeLista(@RequestBody List<FnCheque> cheques,
			@PathVariable("data") String data, @PathVariable("motivo") String motivo) throws Exception {
		if (ca.hasAuth(prm, 60, "A", "CANCELAMENTO DE CHEQUE OU TROCA POR VALORES")) {
			if (cheques != null) {
				// Cancelar cheques
				for (FnCheque c : cheques) {
					fnChequeRp.atualizaStatusCheque("07", null, c.getId());
					fnChequeService.regHistorico(c, c.getCdcaixaatual().getId(), c.getFncxmv().getId(),
							c.getFncxmv().getId(), Date.valueOf(data), "07", "CL", motivo);
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
