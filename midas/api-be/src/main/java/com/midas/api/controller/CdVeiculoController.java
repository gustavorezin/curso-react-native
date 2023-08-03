package com.midas.api.controller;

import java.io.Serializable;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdVeiculo;
import com.midas.api.tenant.entity.CdVeiculoRel;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdVeiculoRelRepository;
import com.midas.api.tenant.repository.CdVeiculoRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdveiculo")
public class CdVeiculoController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdVeiculoRepository cdVeiculoRp;
	@Autowired
	private CdVeiculoRelRepository cdVeiculoRelRp;
	@Autowired 
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/veiculo/{id}")
	public ResponseEntity<CdVeiculo> cdVeiculo(@PathVariable("id") Integer id) {
		Optional<CdVeiculo> obj = cdVeiculoRp.findById(id);
		return new ResponseEntity<CdVeiculo>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/veiculo")
	public ResponseEntity<?> cadastrarCdVeiculo(@RequestBody CdVeiculo cdVeiculo) throws Exception {
		if (ca.hasAuth(prm, 10, "C", cdVeiculo.getPlaca())) {
			cdVeiculoRp.save(cdVeiculo);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/veiculo/{idveic}/rel/{id}")
	public ResponseEntity<?> cadastrarCdVeiculo(@PathVariable("idveic") Integer idveic, @PathVariable("id") Long id)
			throws Exception {
		if (ca.hasAuth(prm, 10, "A", "RELACAO " + idveic + " ID " + id)) {
			if (cdVeiculoRp.verVinculo(id, idveic) == 0) {
				cdVeiculoRp.addVinculo(id, idveic);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/veiculo")
	public ResponseEntity<?> atualizarCdVeiculo(@RequestBody CdVeiculo cdVeiculo) throws Exception {
		if (ca.hasAuth(prm, 10, "A", cdVeiculo.getPlaca())) {
			cdVeiculo.setDataat(new Date(System.currentTimeMillis()));
			cdVeiculoRp.save(cdVeiculo);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/veiculo/{id}")
	public ResponseEntity<?> removerCdVeiculo(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 10, "R", "ID " + id)) {
			// Nao remover item principal
			if (id > 1) {
				cdVeiculoRp.deleteById(id);
				return ResponseEntity.ok(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.LOCKED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@DeleteMapping("/veiculo/{idveic}/rel/{id}")
	public ResponseEntity<?> removerCdVeiculoRel(@PathVariable("idveic") Integer idveic, @PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 10, "R", "RELACAO " + idveic + " ID " + id)) {
			cdVeiculoRp.removeVinculo(id, idveic);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/veiculo/todos")
	public ResponseEntity<List<CdVeiculo>> listaCdVeiculoTodos() throws Exception {
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		List<CdVeiculo> lista = null;
		if (aclocal.equals("S")) {
			lista = cdVeiculoRp.findAll();
		} else {
			lista = cdVeiculoRp.findAllLocalTodos(prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<List<CdVeiculo>>(lista, HttpStatus.OK);
	}
	
	@RequestAuthorization
	@GetMapping("/veiculo/todos/rel")
	public ResponseEntity<List<CdVeiculoRel>> listaCdVeiculoTodosRel() throws Exception {
		List<CdVeiculoRel> lista = null;
		System.out.println("CHEGOU AQUI");
		lista = cdVeiculoRelRp.findAllGroupByVeiculo();
		System.out.println(lista.size());
		return new ResponseEntity<List<CdVeiculoRel>>(lista, HttpStatus.OK);
	}
	
	@RequestAuthorization
	@GetMapping("/veiculo/motoristas/idveic/{idveic}")
	public ResponseEntity<List<CdPessoa>> listaCdVeiculoMotoristasRel(@PathVariable("idveic") int idveic) throws Exception {
		List<CdPessoa> lista = null;
		lista = cdPessoaRp.findAllCdVeiculoRel(idveic);
		
		return new ResponseEntity<List<CdPessoa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/veiculo/busca")
	public ResponseEntity<List<CdVeiculo>> listaCdVeiculosBusca(@RequestParam("busca") String busca) throws Exception {
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		List<CdVeiculo> lista = null;
		if (aclocal.equals("S")) {
			lista = cdVeiculoRp.findAllByNomeBusca(busca);
		} else {
			lista = cdVeiculoRp.findAllByNomeBuscaLocal(prm.cliente().getCdpessoaemp(), busca);
		}
		return new ResponseEntity<List<CdVeiculo>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/veiculo/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdVeiculo>> listaCdVeiculosBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 10, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdVeiculo> lista = null;
			if (aclocal.equals("S")) {
				lista = cdVeiculoRp.findAllByNomeBusca(busca, pageable);
			} else {
				lista = cdVeiculoRp.findAllByNomeBuscaLocal(prm.cliente().getCdpessoaemp(), busca, pageable);
			}
			return new ResponseEntity<Page<CdVeiculo>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
