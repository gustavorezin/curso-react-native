package com.midas.api.controller.app;

import java.io.Serializable;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.mt.entity.Acesso;
import com.midas.api.mt.repository.AcessoRepository;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.SisAcesso;
import com.midas.api.tenant.repository.SisAcessoRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/sisacesso")
public class SisAcessoController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SisAcessoRepository sisAcessoRp;
	@Autowired
	private AcessoRepository acessoRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	// CONTROLE DE ACESSOS DO SISTEMA****************************************
	@RequestAuthorization
	@PostMapping("/acesso")
	public ResponseEntity<?> cadastrarSisAcesso(@RequestBody SisAcesso sisAcesso) throws Exception {
		if (ca.hasAuth(prm, 27, "C", sisAcesso.getNome())) {
			sisAcessoRp.save(sisAcesso);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/acesso/{id}")
	public ResponseEntity<?> removerSisAcesso(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 27, "R", "ID " + id)) {
			sisAcessoRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@DeleteMapping("/acesso/{role}/local/{local}")
	public ResponseEntity<?> removerSisAcessoLocal(@PathVariable("role") Integer role,
			@PathVariable("local") String local) {
		if (ca.hasAuth(prm, 27, "R", "REMOCAO DE ACESSO NO LOCAL " + local + " PARA ROLE " + role)) {
			sisAcessoRp.deleteByRoleAndLocal(role, local);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping("/acesso/{role}/local/{local}")
	public ResponseEntity<?> adicionarSisAcessoLocal(@PathVariable("role") Integer role,
			@PathVariable("local") String local) {
		if (ca.hasAuth(prm, 27, "C", "ADICAO DE ACESSO NO LOCAL " + local + " PARA ROLE " + role)) {
			// Primeiro remove todos
			sisAcessoRp.deleteByRoleAndLocal(role, local);
			List<Acesso> listaAcessos = acessoRp.findAllByLocalOrderByNomeAsc(local);
			for (Acesso a : listaAcessos) {
				SisAcesso sa = new SisAcesso();
				sa.setRole(role);
				sa.setAcesso(a.getId());
				sa.setNome(a.getNome());
				sa.setLocal(a.getLocal());
				sisAcessoRp.save(sa);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/acesso/usuario/{role}/local/{local}/b/{busca}")
	public ResponseEntity<Page<SisAcesso>> listaSisAcessos(@PathVariable("role") Integer role,
			@PathVariable("local") String local, @PathVariable("busca") String busca) throws Exception {
		if (prm.cliente().getRole().getId() <= 3 && role > 3) {
			Pageable pageable = PageRequest.of(0, 1200, Sort.by(Sort.Direction.ASC, "nome"));
			busca = CaracterUtil.buscaContexto(busca);
			Page<SisAcesso> lista = sisAcessoRp.findAllByRoleIdBusca(role, local, busca, pageable);
			return new ResponseEntity<Page<SisAcesso>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/acesso/acessos/{role}/local/{local}")
	public ResponseEntity<List<Acesso>> listaSisAcessosSelecao(@PathVariable("role") Integer role,
			@PathVariable("local") String local) throws Exception {
		if (prm.cliente().getRole().getId() <= 3 && role > 3) {
			// Remove itens que ja estao selecionados
			List<Acesso> listaAcessos = acessoRp.findAllByLocalOrderByNomeAsc(local);
			List<SisAcesso> meusAcessos = sisAcessoRp.findAllByRoleId(role, local);
			for (int x = 0; x < meusAcessos.size(); x++) {
				remove(listaAcessos, meusAcessos, x);
			}
			return new ResponseEntity<List<Acesso>>(listaAcessos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// REMOCAO DE ITENS JA SELECIONADOS PARA USUARIO
	private List<Acesso> remove(List<Acesso> listaAcessos, List<SisAcesso> meusAcessos, int x) {
		listaAcessos.stream().filter(producer -> producer.getId().equals(meusAcessos.get(x).getAcesso())).findFirst()
				.map(p -> {
					listaAcessos.remove(p);
					return p;
				});
		return listaAcessos;
	}
}
