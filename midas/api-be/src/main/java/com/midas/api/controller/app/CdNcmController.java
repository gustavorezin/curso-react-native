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

import com.midas.api.mt.entity.CdNcm;
import com.midas.api.mt.repository.CdNcmRepository;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/cdncm")
public class CdNcmController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdNcmRepository cdNcmRp;

	@RequestAuthorization
	@GetMapping("/ncm/{ncm}")
	public ResponseEntity<CdNcm> cdNcm(@PathVariable("ncm") String ncm) {
		Optional<CdNcm> obj = cdNcmRp.findByNcm(ncm);
		return new ResponseEntity<CdNcm>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/ncm/lista/{ncm}")
	public ResponseEntity<List<CdNcm>> listaNcm(@PathVariable("ncm") String ncm) {
		List<CdNcm> lista = cdNcmRp.listaNcm(ncm);
		return new ResponseEntity<List<CdNcm>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/ncm/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdNcm>> listaCdNcmBusca(@PathVariable("pagina") int pagina,
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
		Page<CdNcm> lista = cdNcmRp.findAllByNcmBusca(busca, pageable);
		return new ResponseEntity<Page<CdNcm>>(lista, HttpStatus.OK);
	}
}
