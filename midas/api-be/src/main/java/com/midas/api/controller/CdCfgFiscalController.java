package com.midas.api.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.CdCfgFiscalSerie;
import com.midas.api.tenant.repository.CdCfgFiscalSerieRepository;
import com.midas.api.tenant.service.CdPessoaCadConsService;

@RestController
@RequestMapping("/private/cdcfgfiscal")
public class CdCfgFiscalController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdCfgFiscalSerieRepository cdCfgFiscalSerieRp;
	@Autowired
	private CdPessoaCadConsService cadConsService;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	// CONFIGURACAO DA SERIE DE DOCUMENTOS FISCAIS *******************************
	@RequestAuthorization
	@GetMapping("/cfgfiscal/serie/{id}")
	public ResponseEntity<CdCfgFiscalSerie> cdCfgFiscalSerie(@PathVariable("id") Integer id)
			throws InterruptedException {
		Optional<CdCfgFiscalSerie> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdCfgFiscalSerieRp.findById(id);
		} else {
			obj = cdCfgFiscalSerieRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdCfgFiscalSerie>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cfgfiscal/serie/modelo/{modelo}")
	public ResponseEntity<List<CdCfgFiscalSerie>> listaCdCfgFiscalSerie(@PathVariable("modelo") String modelo)
			throws InterruptedException {
		List<CdCfgFiscalSerie> lista = null;
		cadConsService.geraCfgFiscalIni("EMPRESA");
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			if (modelo.equals("55")) {
				lista = cdCfgFiscalSerieRp.findAllByModeloNFe(modelo, "65");
			} else {
				lista = cdCfgFiscalSerieRp.findAllByModelo(modelo);
			}
		} else {
			if (modelo.equals("55")) {
				lista = cdCfgFiscalSerieRp.findByCdpessoaempModeloNFe(prm.cliente().getCdpessoaemp(), modelo, "65");
			} else {
				lista = cdCfgFiscalSerieRp.findByCdpessoaempModelo(prm.cliente().getCdpessoaemp(), modelo);
			}
		}
		return new ResponseEntity<List<CdCfgFiscalSerie>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/cfgfiscal/serie")
	public ResponseEntity<?> atualizarCdCfgFiscalSerie(@RequestBody List<CdCfgFiscalSerie> cdCfgFiscalSerie)
			throws Exception {
		if (ca.hasAuth(prm, 68, "A", cdCfgFiscalSerie.get(0).getCdpessoaemp().getNome())) {
			cdCfgFiscalSerieRp.saveAll(cdCfgFiscalSerie);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
