package com.midas.api.controller.app;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.mt.entity.SisCfg;
import com.midas.api.mt.repository.SisCfgRepository;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.SisReguser;
import com.midas.api.tenant.repository.SisCustomRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/sis")
public class SisController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SisCfgRepository sisCfgRp;
	@Autowired
	private SisCustomRepository sisCustomRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	// CONFIGURACOES GERAIS****************************************
	@RequestAuthorization
	@PostMapping("/siscfg")
	public ResponseEntity<SisCfg> sisCfg() throws InterruptedException {
		if (prm.cliente().getRole().getId() <= 3) {
			SisCfg obj = prm.cliente().getSiscfg();
			return new ResponseEntity<SisCfg>(obj, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PutMapping("/siscfg")
	public ResponseEntity<SisCfg> atualizarSisCfg(@RequestBody SisCfg sisCfg) throws Exception {
		if (ca.hasAuth(prm, 35, "A", "ALTERACAO DE CONFIGURACAO DO SISTEMA")) {
			sisCfg.setDataat(new Date(System.currentTimeMillis()));
			sisCfgRp.save(sisCfg);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<SisCfg>(sisCfg, HttpStatus.OK);
	}

	// REGISTRO DE ACOES DO USUARIO****************************************
	@RequestAuthorization
	@GetMapping("/sisreguser/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/lc/{local}/ipp/{itenspp}/dti/{dataini}/dtf/{datafim}")
	public ResponseEntity<Page<SisReguser>> listaSisReguserBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("local") Long local,
			@PathVariable("itenspp") int itenspp, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim) throws Exception {
		if (ca.hasAuth(prm, 4, "L", "LISTAGEM / CONSULTA ACOES POR USUARIO")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<SisReguser> lista = sisCustomRp.listaSisReguser(local, Date.valueOf(dataini), Date.valueOf(datafim),
					busca, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<SisReguser>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
