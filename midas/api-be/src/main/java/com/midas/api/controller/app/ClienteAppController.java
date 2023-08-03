package com.midas.api.controller.app;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.constant.MidasConfig;
import com.midas.api.mt.entity.Cliente;
import com.midas.api.mt.repository.ClienteRepository;
import com.midas.api.mt.repository.TenantRepository;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.AuxEmail;
import com.midas.api.tenant.service.EmailService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.EmailHTMLUtil;
import com.midas.api.util.EmailServiceUtil;

@RestController
@RequestMapping("/private/clienteapp")
public class ClienteAppController {
	@Autowired
	private ClienteRepository clienteRp;
	@Autowired
	private TenantRepository tenantRp;
	@Autowired
	private EmailServiceUtil emailServiceUtil;
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailHTMLUtil emailHTML;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private MidasConfig propertiesRead;

	// APENAS ADMIN MIDAS TEM ACESSO
	// **********************************************************************************
	@RequestAuthorization
	@GetMapping("/cliente/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<Cliente>> tenant(@PathVariable("pagina") int pagina, @PathVariable("busca") String busca,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("itenspp") int itenspp) throws Exception {
		if (prm.cliente().getRole().getId() == 1) {
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
			Page<Cliente> lista = clienteRp.findAllByNomeBusca(busca, pageable);
			return new ResponseEntity<Page<Cliente>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("masterTransactionManager")
	@PutMapping("/ativacao/{id}/onde/{onde}/status/{status}")
	public ResponseEntity<?> atualizarCliente(@PathVariable("id") Long id, @PathVariable("onde") String onde,
			@PathVariable("status") String status) throws Exception {
		if (prm.cliente().getRole().getId() == 1) {
			Cliente cl = clienteRp.getById(id);
			// Ativa Login
			if (onde.equals("login")) {
				clienteRp.ativarCadastro(status, id);
			}
			// Ativa Banco de dados
			if (onde.equals("db")) {
				tenantRp.ativarCadastro(status, cl.getTenant().getId());
			}
			// SE ATIVACAO COMPLETA --------------
			if (status.equals("ATIVO")) {
				String html = emailHTML.textoAtivacaoDb();
				AuxEmail em = emailService.criaAuxEmail(null, propertiesRead);
				emailServiceUtil.EnviaHTML(cl.getEmaillogin(), "Ativação de Sistema - MIDAS", html, null, null, em);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("masterTransactionManager")
	@PutMapping("/liberarmaisdias/{id}")
	public ResponseEntity<?> liberarMaisDias(@PathVariable("id") Long id) throws Exception {
		if (prm.cliente().getRole().getId() == 1) {
			Cliente cl = clienteRp.getById(id);
			clienteRp.ativarCadastro("ATIVO", id);
			Date datafimteste = DataUtil.addRemDias(new Date(), 7, "A");
			tenantRp.ativarCadastroTeste("ATIVO", "S", datafimteste, cl.getTenant().getId());
			String html = emailHTML.textoAtivacaoDb();
			AuxEmail em = emailService.criaAuxEmail(null, propertiesRead);
			emailServiceUtil.EnviaHTML(cl.getEmaillogin(), "Mais 7 Dias de Demonstração Grátis - MIDAS", html, null,
					null, em);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("masterTransactionManager")
	@PutMapping("/ativarcliente/{id}")
	public ResponseEntity<?> ativarCliente(@PathVariable("id") Long id) throws Exception {
		if (prm.cliente().getRole().getId() == 1) {
			Cliente cl = clienteRp.getById(id);
			clienteRp.ativarCadastro("ATIVO", id);
			Date datafimteste = DataUtil.addRemDias(new Date(), 7, "A");
			tenantRp.ativarCadastroTeste("ATIVO", "N", datafimteste, cl.getTenant().getId());
			String html = emailHTML.textoAtivacaoDb();
			AuxEmail em = emailService.criaAuxEmail(null, propertiesRead);
			emailServiceUtil.EnviaHTML(cl.getEmaillogin(), "Seja Bem-vindo - MIDAS", html, null, null, em);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
