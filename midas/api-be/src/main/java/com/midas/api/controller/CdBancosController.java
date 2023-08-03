package com.midas.api.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.mt.entity.CdBancos;
import com.midas.api.mt.repository.CdBancosRepository;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdbancos")
public class CdBancosController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdBancosRepository cdBancosRp;

	@RequestAuthorization
	@GetMapping("/bancos/{codigo}")
	public ResponseEntity<CdBancos> cdBanco(@PathVariable("codigo") String codigo) {
		Optional<CdBancos> obj = cdBancosRp.findByCodigo(codigo);
		return new ResponseEntity<CdBancos>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/bancos")
	public ResponseEntity<List<CdBancos>> cdBancoListaHomologados() {
		List<CdBancos> lista = cdBancosRp.findAllByBolhom("S");
		return new ResponseEntity<List<CdBancos>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/bancos/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdBancos>> listaCdBancosBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
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
		Page<CdBancos> lista = cdBancosRp.findAllByNomeBusca(busca, pageable);
		return new ResponseEntity<Page<CdBancos>>(lista, HttpStatus.OK);
	}
}
