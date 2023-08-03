package com.midas.api.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.EsEst;
import com.midas.api.tenant.entity.EsEstMov;
import com.midas.api.tenant.repository.CdProdutoCompRepository;
import com.midas.api.tenant.repository.EsCustomRepository;
import com.midas.api.tenant.repository.EsEstRepository;
import com.midas.api.tenant.service.CdProdutoService;
import com.midas.api.tenant.service.EsEstService;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/esest")
public class EsController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private EsEstRepository esEstRp;
	@Autowired
	private EsCustomRepository esCustomRp;
	@Autowired
	private EsEstService esEstService;
	@Autowired
	private CdProdutoCompRepository cdProdutoCompRp;
	@Autowired
	private CdProdutoService cdProdutoService;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	// PRODUTO ESTOQUE****************************************
	@RequestAuthorization
	@GetMapping("/es/{id}")
	public ResponseEntity<EsEst> cdEsEst(@PathVariable("id") Long id) {
		Optional<EsEst> obj = esEstRp.findById(id);
		return new ResponseEntity<EsEst>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/es/prod/{id}/local/{local}")
	public ResponseEntity<EsEst> cdEsEstProdutoLocal(@PathVariable("id") Long id, @PathVariable("local") Long local) {
		EsEst obj = esEstRp.findByEmpProduto(local, id);
		return new ResponseEntity<EsEst>(obj, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/es/ajustarestoque")
	public ResponseEntity<?> ajustarEstoque(@RequestParam("idest") Long idest, @RequestParam("tipo") String tipo,
			@RequestParam("qtdajuste") BigDecimal qtdajuste, @RequestParam("info") String info) throws Exception {
		if (ca.hasAuth(prm, 17, "A", "ID AJUSTE DE ESTOQUE " + idest)) {
			EsEst e = esEstRp.findById(idest).get();
			BigDecimal zero = BigDecimal.ZERO;
			esEstService.geradorAjusteEstoque(e, qtdajuste, zero, zero, zero, tipo, "03", 0, info);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/es/ajustarvcusto")
	public ResponseEntity<?> ajustarCustoEstoque(@RequestParam("idest") Long idest,
			@RequestParam("vcustoajuste") BigDecimal vcustoajuste) throws Exception {
		if (ca.hasAuth(prm, 17, "A", "ID AJUSTE DE CUSTO DE ESTOQUE " + idest)) {
			EsEst e = esEstRp.findById(idest).get();
			esEstRp.updateVCustoProd(vcustoajuste, idest);
			// Atualiza custos na composicao
			cdProdutoCompRp.updateVCustoProd(vcustoajuste, e.getCdproduto().getId(), e.getCdpessoaemp().getId());
			cdProdutoService.atualizaTabPreco(e.getCdpessoaemp().getId(), e.getCdproduto(), vcustoajuste);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/es")
	public ResponseEntity<EsEst> atualizarEsEst(@RequestBody EsEst es) throws Exception {
		EsEst esEst = null;
		if (ca.hasAuth(prm, 17, "A", "ALTERA-ATUALIZA CADASTRO DO ESTOQUE" + es.getCdproduto().getNome() + " - ")) {
			esEst = esEstRp.save(es);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<EsEst>(esEst, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/es/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/lc/{local}/dti/{dataini}/dtf/{datafim}/ti/{tipoitem}/st/{st}")
	public ResponseEntity<Page<EsEst>> listaEsEst(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp,
			@PathVariable("local") Long local, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("tipoitem") String tipoitem,
			@PathVariable("st") String st) throws Exception {
		if (ca.hasAuthModulo(prm, 17) && ca.hasAuth(prm, 72, "L", "LISTAGEM / CONSULTA")) {
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
			Page<EsEst> lista = esCustomRp.listaEsEst(local, Date.valueOf(dataini), Date.valueOf(datafim), busca,
					tipoitem, st, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<EsEst>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/es/valores/b/{busca}/lc/{local}/dti/{dataini}/dtf/{datafim}/ti/{tipoitem}/st/{st}")
	public ResponseEntity<List<EsEst>> listaEsEstValores(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("tipoitem") String tipoitem,
			@PathVariable("st") String st) throws Exception {
		if (ca.hasAuth(prm, 72, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<EsEst> lista = esCustomRp.listaEsEstValores(local, Date.valueOf(dataini), Date.valueOf(datafim), busca,
					tipoitem, st);
			return new ResponseEntity<List<EsEst>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/es/qtdestmin")
	public ResponseEntity<Integer> qtdEstMin() throws Exception {
		if (ca.hasAuth(prm, 17, "L", "LISTAGEM / CONSULTA DE ITENS DE ESTOQUE MINIMO")) {
			Integer qtd = 0;
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				qtd = esEstRp.verficaEstMin();
			} else {
				qtd = esEstRp.verficaEstMinLocal(prm.cliente().getCdpessoaemp());
			}
			return new ResponseEntity<Integer>(qtd, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// MOVIMENTO - HISTORICO
	@RequestAuthorization
	@GetMapping("/es/mov/p/{pagina}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/lc/{local}/dti/{dataini}/dtf/{datafim}/prod/{idprod}/tpdoc/{tpdoc}")
	public ResponseEntity<Page<EsEstMov>> listaEsEst(@PathVariable("pagina") int pagina,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("itenspp") int itenspp, @PathVariable("local") Long local,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("idprod") Long idprod, @PathVariable("tpdoc") String tpdoc) throws Exception {
		if (ca.hasAuthModulo(prm, 17) && ca.hasAuth(prm, 72, "L", "LISTAGEM / CONSULTA")) {
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
			Page<EsEstMov> lista = esCustomRp.listaEsEstMov(local, Date.valueOf(dataini), Date.valueOf(datafim), idprod,
					tpdoc, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<EsEstMov>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/es/mov/lancarproducao")
	public ResponseEntity<?> lancarProducao(@RequestBody EsEstMov esEstMov) throws Exception {
		if (ca.hasAuth(prm, 74, "N", "LANCAMENTO DE PRODUCAO AVULSO " + esEstMov.getCdproduto().getNome())) {
			// Movimenta item principal - acabado
			EsEst ea = esEstRp.findByEmpProduto(esEstMov.getCdpessoaemp().getId(), esEstMov.getCdproduto().getId());
			esEstService.geradorAjusteEstoque(ea, esEstMov.getQtd(), esEstMov.getVuni(), esEstMov.getVsub(),
					esEstMov.getVtot(), esEstMov.getTipo(), "05", esEstMov.getCdmaqequip_id(), esEstMov.getInfo());
			esEstService.movMatProdPrima(esEstMov);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
