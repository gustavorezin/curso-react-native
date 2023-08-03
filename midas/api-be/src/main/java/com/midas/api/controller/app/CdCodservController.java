package com.midas.api.controller.app;

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

import com.midas.api.mt.entity.CdCodserv;
import com.midas.api.mt.repository.CdCodservRepository;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdcodserv")
public class CdCodservController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdCodservRepository cdCodservRp;

	@RequestAuthorization
	@GetMapping("/codserv/{id}")
	public ResponseEntity<CdCodserv> cdCodserv(@PathVariable("id") Integer id) {
		Optional<CdCodserv> obj = cdCodservRp.findById(id);
		return new ResponseEntity<CdCodserv>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/codserv/lista/{busca}")
	public ResponseEntity<List<CdCodserv>> listaCdCodserv(@PathVariable("busca") String busca) {
		List<CdCodserv> lista = cdCodservRp.listaTodos(busca);
		return new ResponseEntity<List<CdCodserv>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/codserv/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdCodserv>> listaCdCodservBusca(@PathVariable("pagina") int pagina,
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
		Page<CdCodserv> lista = cdCodservRp.findAllBusca(busca, pageable);
		return new ResponseEntity<Page<CdCodserv>>(lista, HttpStatus.OK);
	}
}
