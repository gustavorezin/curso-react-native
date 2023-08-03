package com.midas.api.controller.integra;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.climba.LcPedidoClimba;
import com.midas.api.tenant.service.integra.ClimbaService;

@RestController
@RequestMapping("/private/lcclimba")
public class LcClimbaController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ClimbaService climbaService;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	// INTEGRACAO CLIMBA****************************************

	@RequestAuthorization
	@GetMapping("/orders/currentPage/{currentPage}/perPage/{perPage}/status/"
			+ "{status}/dateStart/{dateStart}/dateEnd/{dateEnd}")
	public ResponseEntity<Page<LcPedidoClimba>> listaLcPedidosClimba(@PathVariable("currentPage") int currentPage,
			@PathVariable("perPage") int perPage, @PathVariable("status") String status,
			@PathVariable("dateStart") String dateStart, @PathVariable("dateEnd") String dateEnd) throws Exception {
		if (ca.hasAuth(prm, 86, "L", "LISTAGEM / CONSULTA")) {
			Page<LcPedidoClimba> pages = climbaService.listaPedidos(dateStart, dateEnd, status, perPage, currentPage);
			return new ResponseEntity<Page<LcPedidoClimba>>(pages, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
