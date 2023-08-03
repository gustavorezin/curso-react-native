package com.midas.api.controller;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.midas.api.constant.MidasConfig;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdPlconConta;
import com.midas.api.tenant.entity.CdPlconMacro;
import com.midas.api.tenant.entity.CdPlconMicro;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdPlconContaRepository;
import com.midas.api.tenant.repository.CdPlconMacroRepository;
import com.midas.api.tenant.repository.CdPlconMicroRepository;
import com.midas.api.util.SQLExecUtil;

@RestController
@RequestMapping("/private/cdplcon")
public class CdPlconController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdPlconContaRepository cdPlconContaRp;
	@Autowired
	private CdPlconMacroRepository cdPlconMacroRp;
	@Autowired
	private CdPlconMicroRepository cdPlconMicroRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private MidasConfig mc;

	@RequestAuthorization
	@GetMapping("/conta/{id}")
	public ResponseEntity<CdPlconConta> cdPlconConta(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdPlconConta> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdPlconContaRp.findById(id);
		} else {
			obj = cdPlconContaRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdPlconConta>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/novocliente/{tipo}")
	public ResponseEntity<?> novoClientecdPlconConta(@PathVariable("tipo") String tipo)
			throws InterruptedException, FileNotFoundException {
		String caminho = "sql/plcons/sql" + tipo + ".sql";
		SQLExecUtil.executarSqlPadrao(caminho, mc);
		// Atualiza empresa-local padrao
		CdPessoa local = cdPessoaRp.findFirstByTipo("EMPRESA");
		if (local != null) {
			cdPlconContaRp.atualizarLocal(local.getId());
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/conta")
	public ResponseEntity<?> cadastrarCdPlconConta(@RequestBody CdPlconConta cdPlconConta) throws Exception {
		if (ca.hasAuth(prm, 38, "C", cdPlconConta.getNome())) {
			// Verifica mascara
			CdPlconConta conta = cdPlconContaRp.getMascConta(cdPlconConta.getCdpessoaemp().getId(),
					cdPlconConta.getMasc());
			if (conta != null) {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
			cdPlconContaRp.save(cdPlconConta);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/conta")
	public ResponseEntity<?> atualizarCdPlconConta(@RequestBody CdPlconConta cdPlconConta) throws Exception {
		if (ca.hasAuth(prm, 38, "A", cdPlconConta.getNome())) {
			// Verifica mascara
			CdPlconConta conta = cdPlconContaRp.getMascContaDif(cdPlconConta.getCdpessoaemp().getId(),
					cdPlconConta.getMasc(), cdPlconConta.getId());
			if (conta != null) {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
			cdPlconContaRp.save(cdPlconConta);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/conta/exibe/{exibe}/id/{id}")
	public ResponseEntity<?> atualizarExibeConta(@PathVariable("exibe") String exibe, @PathVariable("id") Integer id)
			throws Exception {
		if (ca.hasAuth(prm, 38, "A", "EXIBIR OU NAO - ID " + id)) {
			cdPlconContaRp.exibirConta(exibe, id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/conta/{id}")
	public ResponseEntity<?> removerCdPlconConta(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 38, "R", "ID CONTA" + id)) {
			cdPlconContaRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/conta/lista/{idlocal}")
	public ResponseEntity<List<CdPlconConta>> cdPlconContaLista(@PathVariable("idlocal") Long idlocal)
			throws InterruptedException {
		List<CdPlconConta> lista = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			lista = cdPlconContaRp.findAllByLocal(idlocal);
		} else {
			lista = cdPlconContaRp.findAllByLocal(prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<List<CdPlconConta>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/macro/{id}")
	public ResponseEntity<CdPlconMacro> cdPlconMacro(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdPlconMacro> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdPlconMacroRp.findById(id);
		} else {
			obj = cdPlconMacroRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdPlconMacro>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/macro/conta/{conta}")
	public ResponseEntity<?> cadastrarCdPlconConta(@PathVariable("conta") Integer conta,
			@RequestBody CdPlconMacro cdPlconMacro) throws Exception {
		if (ca.hasAuth(prm, 38, "C", cdPlconMacro.getNome())) {
			// Verifica mascara
			CdPlconMacro contam = cdPlconMacroRp.getMascMacro(conta, cdPlconMacro.getMasc());
			if (contam != null) {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
			CdPlconConta ct = cdPlconContaRp.getById(conta);
			cdPlconMacro.setCdplconconta(ct);
			cdPlconMacroRp.save(cdPlconMacro);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/macro/conta/{conta}")
	public ResponseEntity<?> atualizarCdPlconMacro(@PathVariable("conta") Integer conta,
			@RequestBody CdPlconMacro cdPlconMacro) throws Exception {
		if (ca.hasAuth(prm, 38, "A", cdPlconMacro.getNome())) {
			// Verifica mascara
			CdPlconMacro contam = cdPlconMacroRp.getMascMacroDif(conta, cdPlconMacro.getMasc(), cdPlconMacro.getId());
			if (contam != null) {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
			CdPlconConta ct = cdPlconContaRp.getById(conta);
			cdPlconMacro.setCdplconconta(ct);
			cdPlconMacroRp.save(cdPlconMacro);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/macro/exibe/{exibe}/id/{id}")
	public ResponseEntity<?> atualizarExibeMacro(@PathVariable("exibe") String exibe, @PathVariable("id") Integer id)
			throws Exception {
		if (ca.hasAuth(prm, 38, "A", "EXIBIR OU NAO - ID " + id)) {
			cdPlconMacroRp.exibirMacro(exibe, id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/macro/{id}")
	public ResponseEntity<?> removerCdPlconMacro(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 38, "R", "ID MACRO" + id)) {
			cdPlconMacroRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/macro/lista/{idlocal}/conta/{conta}")
	public ResponseEntity<List<CdPlconMacro>> cdPlconMacroLista(@PathVariable("idlocal") Long idlocal,
			@PathVariable("conta") Integer conta) throws InterruptedException {
		List<CdPlconMacro> lista = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			lista = cdPlconMacroRp.findAllByLocal(idlocal, conta);
		} else {
			lista = cdPlconMacroRp.findAllByLocal(prm.cliente().getCdpessoaemp(), conta);
		}
		return new ResponseEntity<List<CdPlconMacro>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/micro/{id}")
	public ResponseEntity<CdPlconMicro> cdPlconMicro(@PathVariable("id") Integer id) throws InterruptedException {
		Optional<CdPlconMicro> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdPlconMicroRp.findById(id);
		} else {
			obj = cdPlconMicroRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdPlconMicro>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/micro/macro/{macro}")
	public ResponseEntity<?> cadastrarCdPlconMicro(@PathVariable("macro") Integer macro,
			@RequestBody CdPlconMicro cdPlconMicro) throws Exception {
		if (ca.hasAuth(prm, 38, "C", cdPlconMicro.getNome())) {
			// Verifica mascara
			CdPlconMicro contam = cdPlconMicroRp.getMascMicro(macro, cdPlconMicro.getMasc());
			if (contam != null) {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
			// Configura outros planos com outra definicao
			cdPlconMicroRp.alteraTipoLocalSimilarSemID(cdPlconMicro.getTipolocal());
			Optional<CdPlconMacro> mc = cdPlconMacroRp.findById(macro);
			cdPlconMicro.setCdplconmacro(mc.get());
			cdPlconMicro.setMascfinal(
					mc.get().getCdplconconta().getMasc() + "." + mc.get().getMasc() + "." + cdPlconMicro.getMasc());
			cdPlconMicroRp.save(cdPlconMicro);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/micro/macro/{macro}")
	public ResponseEntity<?> atualizarCdPlconMicro(@PathVariable("macro") Integer macro,
			@RequestBody CdPlconMicro cdPlconMicro) throws Exception {
		if (ca.hasAuth(prm, 38, "A", cdPlconMicro.getNome())) {
			// Verifica mascara
			CdPlconMicro contam = cdPlconMicroRp.getMascMicroDif(macro, cdPlconMicro.getMasc(), cdPlconMicro.getId());
			if (contam != null) {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
			// Configura outros planos com outra definicao
			cdPlconMicroRp.alteraTipoLocalSimilar(cdPlconMicro.getId(), cdPlconMicro.getTipolocal());
			Optional<CdPlconMacro> mc = cdPlconMacroRp.findById(macro);
			cdPlconMicro.setCdplconmacro(mc.get());
			cdPlconMicro.setMascfinal(
					mc.get().getCdplconconta().getMasc() + "." + mc.get().getMasc() + "." + cdPlconMicro.getMasc());
			cdPlconMicroRp.save(cdPlconMicro);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/micro/exibe/{exibe}/id/{id}")
	public ResponseEntity<?> atualizarExibeMicro(@PathVariable("exibe") String exibe, @PathVariable("id") Integer id)
			throws Exception {
		if (ca.hasAuth(prm, 38, "A", "EXIBIR OU NAO - ID " + id)) {
			cdPlconMicroRp.exibirMicro(exibe, id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/micro/{id}")
	public ResponseEntity<?> removerCdPlconMicro(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 38, "R", "ID MICRO" + id)) {
			cdPlconMicroRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/planos/{idlocal}")
	public ResponseEntity<List<CdPlconConta>> listaCdPlconConta(@PathVariable("idlocal") Long idlocal)
			throws Exception {
		if (ca.hasAuth(prm, 38, "L", "GESTAO E CADASTRO")) {
			List<CdPlconConta> lista = cdPlconContaRp.findAllByLocal(idlocal);
			return new ResponseEntity<List<CdPlconConta>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/planos/tipo/{tipo}")
	public ResponseEntity<List<CdPlconConta>> listaCdPlconContaTipo(@PathVariable("tipo") String tipo)
			throws Exception {
		List<CdPlconConta> lista = cdPlconContaRp.findAllByTipo(tipo);
		return new ResponseEntity<List<CdPlconConta>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/planos/{idlocal}/tipo/{tipo}")
	public ResponseEntity<List<CdPlconConta>> listaCdPlconContaLocalTipo(@PathVariable("idlocal") Long idlocal,
			@PathVariable("tipo") String tipo) throws Exception {
		List<CdPlconConta> lista = cdPlconContaRp.findAllByLocalandTipo(idlocal, tipo);
		return new ResponseEntity<List<CdPlconConta>>(lista, HttpStatus.OK);
	}
}
