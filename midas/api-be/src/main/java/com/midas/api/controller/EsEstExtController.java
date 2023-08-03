package com.midas.api.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import com.midas.api.tenant.entity.EsEstExt;
import com.midas.api.tenant.repository.EsCustomRepository;
import com.midas.api.tenant.repository.EsEstExtRepository;
import com.midas.api.tenant.service.EsEstService;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/esestext")
public class EsEstExtController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private EsEstExtRepository esEstExtRp;
	@Autowired
	private EsCustomRepository esCustomRp;
	@Autowired
	private EsEstService esEstService;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	// TODO VER PARAMETROS HASAUTH
	// ESTOQUE EXTERNO ****************************************
	@RequestAuthorization
	@GetMapping("/estext")
	public ResponseEntity<List<EsEstExt>> listaEsEstExt() throws IOException {
		List<EsEstExt> lista = esEstExtRp.findAll();
		if (prm.cliente().getRole().getId() == 8) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<List<EsEstExt>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/estext/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/vep/{vep}/ipp/{itenspp}/dti/{dataini}/dtf/{datafim}")
	public ResponseEntity<Page<EsEstExt>> listaEsEstExtBusca(@PathVariable("pagina") int pagina,@PathVariable("busca") String busca, 
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir, @PathVariable("vep") Long vep, 
			@PathVariable("itenspp") int itenspp,@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim
			) throws Exception {
		if (ca.hasAuth(prm, 24, "L", "LISTAGEM / CONSULTA")) {
			// Resp. Vendedores
			if (prm.cliente().getRole().getId() == 8) {
				if (prm.cliente().getCdpessoavendedor() != null) {
					vep = prm.cliente().getCdpessoavendedor();
				}
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<EsEstExt> lista = esCustomRp.listaEsEstExt(vep, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<EsEstExt>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/estext")
	public ResponseEntity<?> addEsEstExt(@RequestBody EsEstExt esEstExt) throws Exception {
	    if (ca.hasAuth(prm, 73, "A", "ITEM " + esEstExt.getDescricao())) {
	        // Verificar se já existe um registro com o mesmo cdproduto e cdpessoavendedor
	        EsEstExt existeItem = esEstExtRp.findByCdprodutoAndCdpessoavendedor(
	            esEstExt.getCdproduto(), esEstExt.getCdpessoavendedor()
	        );
	        if (existeItem != null) {
	            // Item já existe, atualizar a quantidade e estoque
				BigDecimal qtdAtual = existeItem.getQtd();
	        	existeItem.setQtd(qtdAtual.add(esEstExt.getQtd()));
	        	existeItem.setData(esEstExt.getData());
	        	esEstService.ajustaEstoqueExterno(qtdAtual, existeItem, "ATUALIZA");
	            esEstExtRp.save(existeItem);
	            return new ResponseEntity<>(existeItem, HttpStatus.OK);
	        } else {
	            // Item não existe, adicionar um novo item
	        	esEstService.ajustaEstoqueExterno(BigDecimal.ZERO, esEstExt, "SAIDA");
	            esEstExtRp.save(esEstExt);
	            return new ResponseEntity<>(esEstExt, HttpStatus.OK);
	        }
	    } else {
	        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	    }
	}
	
	@RequestAuthorization
	@PutMapping("/estext/atualiza")
	public ResponseEntity<EsEstExt> atualizarEsEstExt(@RequestBody EsEstExt esEstExt) throws Exception {
		if (ca.hasAuth(prm, 35, "A", "ID ITEM " + esEstExt.getId() + " - " + esEstExt.getDescricao())) {
			EsEstExt estExtAtual = esEstExtRp.findById(esEstExt.getId()).get();
			BigDecimal qtdAtual = estExtAtual.getQtd();
			esEstService.ajustaEstoqueExterno(qtdAtual, esEstExt, "ATUALIZA");
			esEstExtRp.save(esEstExt);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<EsEstExt>(esEstExt, HttpStatus.OK);
	}
	
	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/estext")
	public ResponseEntity<?> removerEsEstExt(@RequestBody EsEstExt esEstExt)
			throws Exception {
		if (ca.hasAuth(prm, 73, "R", "ID ITEM " + esEstExt.getId() + " - " + esEstExt.getDescricao())) {
			esEstService.ajustaEstoqueExterno(esEstExt.getQtd(), esEstExt, "ENTRADA");
			esEstExtRp.delete(esEstExt);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/estext/remover/emlote")
	public ResponseEntity<?> removerEsEstExtEmLote(@RequestBody List<EsEstExt> listaEsEstExt) throws SQLException {
		Integer acesso = 16;
		if (ca.hasAuth(prm, acesso, "R", "REMOCAO DE ITENS EM LOTE")) {
			for (EsEstExt p : listaEsEstExt) {
				esEstService.ajustaEstoqueExterno(p.getQtd(), p, "ENTRADA");
				esEstExtRp.deleteById(p.getId());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
