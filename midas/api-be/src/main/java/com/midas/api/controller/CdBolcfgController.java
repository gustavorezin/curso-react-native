package com.midas.api.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.midas.api.constant.MidasConfig;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.AuxDados;
import com.midas.api.tenant.entity.CdBolcfg;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.tecno.TecnoCadUtil;
import com.midas.api.tenant.repository.CdBolcfgRepository;
import com.midas.api.tenant.repository.CdCaixaRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;

@RestController
@RequestMapping("/private/cdbolcfg")
public class CdBolcfgController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdBolcfgRepository cdBolcfgRp;
	@Autowired
	private CdCaixaRepository cdCaixaRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private TecnoCadUtil tecnoCadUtil;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/bolcfg/{id}")
	public ResponseEntity<CdBolcfg> cdBolcfg(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdBolcfg> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdBolcfgRp.findById(id);
		} else {
			obj = cdBolcfgRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdBolcfg>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/bolcfg")
	public ResponseEntity<?> cadastrarCdBolcfg(@RequestBody CdBolcfg cdBolcfg) throws Exception {
		if (ca.hasAuth(prm, 75, "C", cdBolcfg.getConta() + "-" + cdBolcfg.getContadv())) {
			// Primeiro valida cadastro na TecnoSpeed
			CdPessoa e = cdCaixaRp.findById(cdBolcfg.getCdcaixa().getId()).get().getCdpessoaemp();
			List<AuxDados> aux = tecnoCadUtil.configurarConta(cdBolcfg, e, mc, prm);
			// Verifica conta primeiro
			if (aux.get(0).getCampo1().equals("200")) {
				cdBolcfg.setId_conta_tecno(Integer.valueOf(aux.get(0).getCampo2()));
				// Verifica conveio
				List<AuxDados> auxcv = tecnoCadUtil.configurarConvenio(cdBolcfg, e, mc, prm);
				cdBolcfg.setId_conv_tecno(Integer.valueOf(auxcv.get(0).getCampo2()));
				if (auxcv.get(0).getCampo1().equals("200")) {
					cdBolcfgRp.save(cdBolcfg);
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/bolcfg")
	public ResponseEntity<?> atualizarCdBolcfg(@RequestBody CdBolcfg cdBolcfg) throws Exception {
		if (ca.hasAuth(prm, 75, "A", cdBolcfg.getConta() + "-" + cdBolcfg.getContadv())) {
			// Primeiro valida cadastro na TecnoSpeed
			CdPessoa e = cdCaixaRp.findById(cdBolcfg.getCdcaixa().getId()).get().getCdpessoaemp();
			List<AuxDados> aux = tecnoCadUtil.configurarConta(cdBolcfg, e, mc, prm);
			List<AuxDados> auxcv = tecnoCadUtil.configurarConvenio(cdBolcfg, e, mc, prm);
			if (aux.get(0).getCampo1().equals("200") && auxcv.get(0).getCampo1().equals("200")) {
				cdBolcfgRp.save(cdBolcfg);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/bolcfg/{id}")
	public ResponseEntity<?> removerCdBolcfg(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 75, "R", "ID " + id)) {
			// Primeiro valida cadastro na TecnoSpeed
			CdBolcfg bc = cdBolcfgRp.findById(id).get();
			CdPessoa e = cdCaixaRp.findById(bc.getCdcaixa().getId()).get().getCdpessoaemp();
			AuxDados aux = tecnoCadUtil.removerConta(bc, e, mc, prm);
			if (aux.getCampo1().equals("200")) {
				cdBolcfgRp.deleteById(id);
			}
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/bolcfg/removeintegraemp/{idlocal}")
	public ResponseEntity<?> removerIntegracaoCdBolcfg(@PathVariable("idlocal") Long idlocal) {
		if (ca.hasAuth(prm, 75, "R", "ID REMOVE INTEGRACAO " + idlocal)) {
			cdPessoaRp.updateClienteIdTecno(null, null, idlocal);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/bolcfg/emp/{emp}")
	public ResponseEntity<List<CdBolcfg>> listaCdBolcfgsEmpresa(@PathVariable("emp") Long emp) throws Exception {
		List<CdBolcfg> lista = cdBolcfgRp.findAllByLocal(emp);
		return new ResponseEntity<List<CdBolcfg>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/bolcfg/ativo/emp/{emp}")
	public ResponseEntity<List<CdBolcfg>> listaCdBolcfgsAtivoEmpresa(@PathVariable("emp") Long emp) throws Exception {
		List<CdBolcfg> lista = cdBolcfgRp.findAllByAtivosLocal(emp, "ATIVO");
		return new ResponseEntity<List<CdBolcfg>>(lista, HttpStatus.OK);
	}
}
