package com.midas.api.controller;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.FsSped;
import com.midas.api.tenant.fiscal.service.FsSpedService;
import com.midas.api.tenant.repository.FsCteRepository;
import com.midas.api.tenant.repository.FsNfeRepository;
import com.midas.api.tenant.repository.FsSpedRepository;
import com.midas.api.tenant.service.EmailService;
import com.midas.api.util.DataUtil;

@RestController
@RequestMapping("/private/fs")
public class FsSpedController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FsSpedRepository fsSpedRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private FsSpedService fsSpedService;
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private FsCteRepository fsCteRp;
	@Autowired
	private EmailService emailService;

	// DOCUMENTOS FISCAIS - SPED****************************************
	@RequestAuthorization
	@GetMapping("/sped/{id}")
	public ResponseEntity<FsSped> fsSped(@PathVariable("id") Integer id) {
		Optional<FsSped> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = fsSpedRp.findById(id);
		} else {
			obj = fsSpedRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<FsSped>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/sped/gerar/{id}")
	public ResponseEntity<byte[]> geraArquivoSped(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Integer id) throws Exception {
		if (ca.hasAuth(prm, 71, "E", "GERACAO E DOWNLOAD DO ARQUIVO " + id)) {
			try {
				FsSped fssped = fsSpedRp.findById(id).get();
				Date dtini = Date
						.valueOf(DataUtil.dataPadraoSQL(DataUtil.dUtil(fssped.getAno() + "-" + fssped.getMes() + "-01")));
				Date dtfim = Date.valueOf(DataUtil.dataPadraoSQL(DataUtil.dUtil(DataUtil.ultimoDiaMes(dtini))));
				// Verifica notas pendentes
				Integer qtdnfe = fsNfeRp.verificaNfeStImpImpLocalDataEntSaiTipo(fssped.getCdpessoaemp().getId(), dtini,
						dtfim, "R");
				Integer qtdcte = fsCteRp.verificaCteStImpImpLocalDataEntSaiTipo(fssped.getCdpessoaemp().getId(), dtini,
						dtfim, "R");
				if (qtdnfe == 0 && qtdcte == 0) {
					byte[] contents = fsSpedService.geraSped(fssped);
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.parseMediaType("application/text"));
					headers.set("Content-Disposition", "inline");
					return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/sped/envioemail/{id}")
	public ResponseEntity<?> envioNFeEmail(HttpServletRequest request, @PathVariable("id") Integer id)
			throws Exception {
		if (ca.hasAuth(prm, 71, "E", "ID Documento para Envio por E-mail SPED " + id)) {
			FsSped fssped = fsSpedRp.findById(id).get();
			Date dtini = Date
					.valueOf(DataUtil.dataPadraoSQL(DataUtil.dUtil(fssped.getAno() + "-" + fssped.getMes() + "-01")));
			Date dtfim = Date.valueOf(DataUtil.dataPadraoSQL(DataUtil.dUtil(DataUtil.ultimoDiaMes(dtini))));
			// Verifica notas pendentes
			Integer qtdnfe = fsNfeRp.verificaNfeStImpImpLocalDataEntSaiTipo(fssped.getCdpessoaemp().getId(), dtini,
					dtfim, "R");
			Integer qtdcte = fsCteRp.verificaCteStImpImpLocalDataEntSaiTipo(fssped.getCdpessoaemp().getId(), dtini,
					dtfim, "R");
			if (qtdnfe == 0 && qtdcte == 0) {
				emailService.enviaSPEDEmail(request, fssped);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/sped/p/{pagina}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/lc/{local}")
	public ResponseEntity<Page<FsSped>> listaFsSpedBusca(@PathVariable("pagina") int pagina,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("itenspp") int itenspp, @PathVariable("local") Long local) throws Exception {
		if (ca.hasAuth(prm, 71, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
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
			Page<FsSped> lista = null;
			if (local > 0) {
				lista = fsSpedRp.findAllLocal(local, pageable);
			} else {
				lista = fsSpedRp.findAll(pageable);
			}
			return new ResponseEntity<Page<FsSped>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
