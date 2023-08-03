package com.midas.api.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

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

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.FnCxmvDre;
import com.midas.api.tenant.entity.FnTituloCcusto;
import com.midas.api.tenant.entity.FnTituloDre;
import com.midas.api.tenant.repository.FnCxmvDreRepository;
import com.midas.api.tenant.repository.FnTituloCcustoCustomRepository;
import com.midas.api.tenant.repository.FnTituloCcustoRepository;
import com.midas.api.tenant.repository.FnTituloDreRepository;

@RestController
@RequestMapping("/private/fndemonstrativo")
public class FnDemonstrativoController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FnTituloDreRepository fnTituloDreRp;
	@Autowired
	private FnCxmvDreRepository fnCxmvDreRp;
	@Autowired
	private FnTituloCcustoRepository fnTituloCcustoRp;
	@Autowired
	private FnTituloCcustoCustomRepository fnTituloCcustoCustomRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	@RequestAuthorization
	@GetMapping("/dre/competencia/lc/{local}/anoini/{anoini}/anofim/{anofim}")
	public ResponseEntity<List<FnTituloDre>> listaFnTituloDreCompetencia(@PathVariable("local") Long local,
			@PathVariable("anoini") Integer anoini, @PathVariable("anofim") Integer anofim) throws Exception {
		if (ca.hasAuth(prm, 63, "L", "LISTAGEM DE DRE")) {
			if (anoini <= anofim) {
				// Verifica se tem acesso a todos locais
				if (prm.cliente().getAclocal().equals("N")) {
					local = prm.cliente().getCdpessoaemp();
				}
				List<FnTituloDre> titulos = fnTituloDreRp.findAllByLocalCompetencia(local,
						Date.valueOf(anoini + "-01-01"), Date.valueOf(anofim + "-12-31"));
				return new ResponseEntity<List<FnTituloDre>>(titulos, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/dre/caixa/lc/{local}/anoini/{anoini}/anofim/{anofim}")
	public ResponseEntity<List<FnCxmvDre>> listaFnCxmvDreCaixa(@PathVariable("local") Long local,
			@PathVariable("anoini") Integer anoini, @PathVariable("anofim") Integer anofim) throws Exception {
		if (ca.hasAuth(prm, 63, "L", "LISTAGEM DE DRE")) {
			if (anoini <= anofim) {
				// Verifica se tem acesso a todos locais
				if (prm.cliente().getAclocal().equals("N")) {
					local = prm.cliente().getCdpessoaemp();
				}
				List<FnCxmvDre> cxmvs = fnCxmvDreRp.findAllByLocalCaixa(local, Date.valueOf(anoini + "-01-01"),
						Date.valueOf(anofim + "-12-31"));
				return new ResponseEntity<List<FnCxmvDre>>(cxmvs, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/ccusto/pagos/centro/{centro}/dti/{dataini}/dtf/{datafim}")
	public ResponseEntity<List<FnTituloCcusto>> listaFnCcustoPagos(@PathVariable("centro") Integer centro,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim) throws Exception {
		if (ca.hasAuth(prm, 63, "L", "LISTAGEM DE CENTRO DE CUSTO")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			List<FnTituloCcusto> titulos = null;
			if (local > 0) {
				titulos = fnTituloCcustoRp.findAllByCdCustoPagosLocal(local, centro, Date.valueOf(dataini),
						Date.valueOf(datafim));
			} else {
				titulos = fnTituloCcustoRp.findAllByCdCustoPagos(centro, Date.valueOf(dataini), Date.valueOf(datafim));
			}
			return new ResponseEntity<List<FnTituloCcusto>>(titulos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/ccusto/emaberto/centro/{centro}/dti/{dataini}/dtf/{datafim}")
	public ResponseEntity<List<FnTituloCcusto>> listaFnCcustoEmaberto(@PathVariable("centro") Integer centro,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim) throws Exception {
		if (ca.hasAuth(prm, 63, "L", "LISTAGEM DE CENTRO DE CUSTO")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			List<FnTituloCcusto> titulos = null;
			if (local > 0) {
				titulos = fnTituloCcustoRp.findAllByCdCustoEmabertoLocal(local, centro, Date.valueOf(dataini),
						Date.valueOf(datafim));
			} else {
				titulos = fnTituloCcustoRp.findAllByCdCustoEmaberto(centro, Date.valueOf(dataini),
						Date.valueOf(datafim));
			}
			return new ResponseEntity<List<FnTituloCcusto>>(titulos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/ccusto/p/{pagina}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/tp/{tipo}/centro/{centro}/dti/{dataini}/dtf/{datafim}/st/{status}")
	public ResponseEntity<Page<FnTituloCcusto>> listaFnCcustoListaGeral(@PathVariable("pagina") int pagina,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("itenspp") int itenspp, @PathVariable("tipo") String tipo,
			@PathVariable("centro") Integer centro, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("status") String status) throws Exception {
		if (ca.hasAuth(prm, 63, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<FnTituloCcusto> lista = fnTituloCcustoCustomRp.listaFnTituloCcusto(local, tipo, centro,
					Date.valueOf(dataini), Date.valueOf(datafim), status, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<FnTituloCcusto>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
