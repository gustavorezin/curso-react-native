package com.midas.api.controller;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.CdCert;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.fiscal.util.CertLeituraUtil;
import com.midas.api.tenant.repository.CdCertRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.util.LerArqUtil;

@RestController
@RequestMapping("/private/cdcert")
public class CdCertController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdCertRepository cdCertRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private CertLeituraUtil certLeitura;

	@RequestAuthorization
	@GetMapping("/cert/{id}")
	public ResponseEntity<CdCert> cdCert(@PathVariable("id") Integer id) {
		Optional<CdCert> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = cdCertRp.findById(id);
		} else {
			obj = cdCertRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<CdCert>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/cert")
	public ResponseEntity<?> cadastrarCdCert(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file,
			@RequestParam("senha") String senha) throws Exception {
		if (ca.hasAuth(prm, 3, "C", file.getName())) {
			try {
				File cert = LerArqUtil.multipartToFile(file, file.getOriginalFilename());
				X509Certificate certificate = certLeitura.lerCertificado(cert.getAbsolutePath(), senha.toCharArray());
				// Verifica se local ja possui certificado
				if (cdCertRp.verificaExiste(id) == 0) {
					// Verifica se pertence ao local
					Optional<CdPessoa> p = cdPessoaRp.findById(id);
					String cpfcnpjcert = certLeitura.getCpfCnpj(certificate);
					if (p.get().getCpfcnpj().equals(cpfcnpjcert)) {
						cdCertRp.addCert(p.get().getId(), certificate.getNotBefore(), certificate.getNotAfter(),
								cert.getName(), Files.readAllBytes(cert.toPath()), senha);
						return new ResponseEntity<String>(HttpStatus.OK);
					} else {
						// Nao pertence ao local
						return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
					}
				} else {
					// Ja possui certificado instalado
					return new ResponseEntity<String>(HttpStatus.PRECONDITION_REQUIRED);
				}
			} catch (Exception e) {
				return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@DeleteMapping("/cert/{id}")
	public ResponseEntity<?> removerCdCert(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 3, "R", "ID " + id)) {
			cdCertRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cert/download/{id}")
	public ResponseEntity<byte[]> downloadCert(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 3, "D", "ID DOWNLOAD" + id)) {
			Optional<CdCert> obj = cdCertRp.findById(id);
			byte[] file = obj.get().getArquivo();
			return new ResponseEntity<byte[]>(file, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cert/listatodos")
	public ResponseEntity<List<CdCert>> listaTodos() {
		List<CdCert> lista = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			lista = cdCertRp.findByStatus("ATIVO");
		} else {
			lista = cdCertRp.findAllLocalTodos(prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<List<CdCert>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cert/p/{pagina}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdCert>> listaCdCertsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 3, "L", "LISTAGEM / CONSULTA")) {
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
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdCert> lista = null;
			if (aclocal.equals("S")) {
				lista = cdCertRp.findAll(pageable);
			} else {
				lista = cdCertRp.findAllLocal(prm.cliente().getCdpessoaemp(), pageable);
			}
			return new ResponseEntity<Page<CdCert>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/cert/vence")
	public ResponseEntity<Boolean> cdCertVencimentos() {
		Boolean retorno = false;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			if (cdCertRp.verificaExpira(30) > 0) {
				retorno = true;
			}
		} else {
			if (cdCertRp.verificaExpiraLocal(prm.cliente().getCdpessoaemp(), 30) > 0) {
				retorno = true;
			}
		}
		return new ResponseEntity<Boolean>(retorno, HttpStatus.OK);
	}
}
