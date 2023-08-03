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
import com.midas.api.tenant.entity.FnRecAv;
import com.midas.api.tenant.repository.FnRecAvCustomRepository;
import com.midas.api.tenant.repository.FnRecAvRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/fnrecav")
public class FnRecAvController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FnRecAvRepository fnRecAvRp;
	@Autowired
	private FnRecAvCustomRepository fnRecAvCustomRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/recav/{id}")
	public ResponseEntity<FnRecAv> fnRecAv(@PathVariable("id") Long id) {
		Optional<FnRecAv> obj = fnRecAvRp.findById(id);
		return new ResponseEntity<FnRecAv>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/recav")
	public ResponseEntity<FnRecAv> cadastrarFnRecAv(@RequestBody FnRecAv fnRecAv) throws Exception {
		FnRecAv obj = null;
		if (ca.hasAuth(prm, 11, "C", fnRecAv.getCdpessoapara().getNome())) {
			obj = fnRecAvRp.save(fnRecAv);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<FnRecAv>(obj, HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/recav")
	public ResponseEntity<FnRecAv> atualizarFnRecAv(@RequestBody FnRecAv fnRecAv) throws Exception {
		FnRecAv obj = null;
		if (ca.hasAuth(prm, 11, "A", fnRecAv.getCdpessoapara().getNome())) {
			obj = fnRecAvRp.save(fnRecAv);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<FnRecAv>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/recav/{id}")
	public ResponseEntity<?> removerFnRecAv(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 11, "R", "ID " + id)) {
			fnRecAvRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/recav/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/lc/{local}/dti/{dataini}/dtf/{datafim}/tipo/{tipo}")
	public ResponseEntity<Page<FnRecAv>> listaFnRecAvsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp,
			@PathVariable("local") Long local, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("tipo") String tipo) throws Exception {
		if (ca.hasAuth(prm, 11, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<FnRecAv> lista = fnRecAvCustomRp.listaFnRecAv(local, tipo, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<FnRecAv>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/recav/valores/b/{busca}/lc/{local}/dti/{dataini}/dtf/{datafim}/tipo/{tipo}")
	public ResponseEntity<List<FnRecAv>> listaFnRecAvsBuscaValores(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("tipo") String tipo) throws Exception {
		if (ca.hasAuth(prm, 11, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<FnRecAv> lista = fnRecAvCustomRp.listaFnRecAvValores(local, tipo, Date.valueOf(dataini),
					Date.valueOf(datafim), busca);
			return new ResponseEntity<List<FnRecAv>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
