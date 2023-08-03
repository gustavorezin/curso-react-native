package com.midas.api.controller.app;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.constant.MidasConfig;
import com.midas.api.mt.config.DBContextHolder;
import com.midas.api.mt.entity.Cliente;
import com.midas.api.mt.entity.ClienteCfg;
import com.midas.api.mt.entity.Role;
import com.midas.api.mt.entity.Tenant;
import com.midas.api.mt.repository.ClienteRepository;
import com.midas.api.mt.repository.ClientecfgRepository;
import com.midas.api.mt.repository.RoleRepository;
import com.midas.api.mt.repository.TenantRepository;
import com.midas.api.mt.service.TenantCriaService;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.AuxEmail;
import com.midas.api.tenant.service.EmailService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.CryptPassUtil;
import com.midas.api.util.EmailHTMLUtil;
import com.midas.api.util.EmailServiceUtil;

@RestController
@RequestMapping("/private/cliente")
public class CdClienteController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ClienteRepository clienteRp;
	@Autowired
	private ClientecfgRepository clientecfgRp;
	@Autowired
	private RoleRepository roleRp;
	@Autowired
	private TenantRepository tenantRp;
	@Autowired
	private CryptPassUtil cryptPass;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private TenantCriaService tenantCriaService;
	@Autowired
	private EmailHTMLUtil emailHTML;
	@Autowired
	private EmailServiceUtil emailServiceUtil;
	@Autowired
	private EmailService emailService;
	@Autowired
	private MidasConfig propertiesRead;

	/*
	 * CLIENTE CADASTRO PARA CONTRANTANTE DO SERVICO - TRATADO COMO USUARIO SISTEMA
	 * 
	 * Gestao multi-empresa-local - gerencia o banco principal
	 * 
	 */
	@RequestAuthorization
	@PostMapping("/cliente")
	public ResponseEntity<Cliente> cdCliente(@RequestBody Long id) {
		Optional<Cliente> obj = null;
		if (ca.hasAuth(prm, 4, "L", "LISTAGEM / CONSULTA")) {
			Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
			obj = clienteRp.findByIdAndTenant(id, tn);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Cliente>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/minhaconta")
	public ResponseEntity<Cliente> minhaConta(@RequestBody Long id) {
		Optional<Cliente> obj = null;
		if (ca.hasAuth(prm, 1, "L", "LISTAGEM / CONSULTA")) {
			Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
			obj = clienteRp.findByIdAndTenant(id, tn);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Cliente>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/buscaemail")
	public ResponseEntity<Cliente> buscaEmail(@RequestBody String email) {
		Cliente cliente = clienteRp.findByEmaillogin(email);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/completacadastro")
	public ResponseEntity<Cliente> completaCadastro(@RequestBody Cliente cliente) throws Exception {
		Cliente obj = clienteRp.getById(cliente.getId());
		obj.setCpfcnpj(cliente.getCpfcnpj());
		obj.setCep(cliente.getCep());
		obj.setLogradouro(cliente.getLogradouro());
		obj.setComplemento(cliente.getComplemento());
		obj.setBairro(cliente.getBairro());
		obj.setCidade(cliente.getCidade());
		obj.setUf(cliente.getUf());
		obj.setCelular(cliente.getCelular());
		obj.setTelefone(cliente.getTelefone());
		clienteRp.save(obj);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("masterTransactionManager")
	@PostMapping("/cliente/{enviasenha}")
	public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cdCliente,
			@PathVariable("enviasenha") String enviasenha) throws Exception {
		if (ca.hasAuth(prm, 4, "C", cdCliente.getNome() + " " + cdCliente.getSobrenome())) {
			// Se envia senha por email
			if (enviasenha.equals("S")) {
				String html = emailHTML.textoAcessoSalvo(cdCliente.getSenhalogin());
				// Envia email nova senha******
				AuxEmail em = emailService.criaAuxEmail(null, propertiesRead);
				emailServiceUtil.EnviaHTML(cdCliente.getEmaillogin(), "Novo acesso criado - MIDAS", html, null, null,
						em);
			}
			if (cdCliente.getRole().getId() <= 2) {
				// Mantem ROLE padrao
				Role rl = roleRp.getById(3);
				cdCliente.setRole(rl);
			}
			ClienteCfg cfg = tenantCriaService.criaClienteCfg(null, cdCliente.getRole().getId());
			cdCliente.setNome(StringUtils.capitalize(cdCliente.getNome()));
			cdCliente.setSobrenome(StringUtils.capitalize(cdCliente.getSobrenome()));
			Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
			cdCliente.setTenant(tn);
			cdCliente.setClientecfg(cfg);
			// Cliente principal
			Cliente c = clienteRp.findByTenantUnico(tn.getId(), 2);
			cdCliente.setSiscfg(c.getSiscfg());
			cdCliente.setSenhalogin(cryptPass.cBrypt(cdCliente.getSenhalogin()));
			cdCliente.setCep("00000000");// Apenas para registro
			clienteRp.save(cdCliente);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("masterTransactionManager")
	@PutMapping("/cliente/enviasenha/{enviasenha}")
	public ResponseEntity<?> atualizarSenha(@RequestBody Cliente cdCliente,
			@PathVariable("enviasenha") String enviasenha) throws Exception {
		if (ca.hasAuth(prm, 4, "A", cdCliente.getEmaillogin())) {
			// Se envia senha por email
			if (enviasenha.equals("S")) {
				String html = emailHTML.textoRecuperarSenha(cdCliente.getSenhalogin());
				// Envia email nova senha******
				AuxEmail em = emailService.criaAuxEmail(null, propertiesRead);
				emailServiceUtil.EnviaHTML(cdCliente.getEmaillogin(), "Nova senha - MIDAS", html, null, null, em);
			}
			cdCliente.setSenhalogin(cryptPass.cBrypt(cdCliente.getSenhalogin()));
			clienteRp.save(cdCliente);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("masterTransactionManager")
	@PutMapping("/cliente")
	public ResponseEntity<?> atualizarCliente(@RequestBody Cliente cdCliente) throws Exception {
		if (ca.hasAuth(prm, 4, "A", cdCliente.getNome() + " " + cdCliente.getSobrenome())) {
			Cliente cl = clienteRp.getById(cdCliente.getId());
			if (cl.getRole().getId() > 2 && cdCliente.getRole().getId() > 2) {
				clienteRp.save(cdCliente);
			} else {
				// Mantem mesmo ROLE
				cdCliente.setRole(cl.getRole());
				clienteRp.save(cdCliente);
			}
			cdCliente.setNome(StringUtils.capitalize(cdCliente.getNome()));
			cdCliente.setSobrenome(StringUtils.capitalize(cdCliente.getSobrenome()));
			clienteRp.save(cdCliente);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("masterTransactionManager")
	@PutMapping("/clientecfg")
	public ResponseEntity<?> atualizarClienteCfg(@RequestBody ClienteCfg cdClienteCfg) throws Exception {
		if (ca.hasAuth(prm, 4, "A", "ID " + cdClienteCfg.getId())) {
			clientecfgRp.save(cdClienteCfg);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("masterTransactionManager")
	@PutMapping("/cliente/clientecfg/{id}/modulo/{modulo}")
	public ResponseEntity<?> atualizarClienteCfg(@PathVariable("id") Long id, @PathVariable("modulo") String modulo)
			throws Exception {
		if (ca.hasAuth(prm, 4, "A", "ID ALTERACAO DE MODULO DE USUARIO " + id)) {
			Cliente cl = clienteRp.getById(id);
			clientecfgRp.updateModuloCliente(modulo, cl.getClientecfg().getId());
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<?> removerCliente(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 4, "R", "ID " + id)) {
			Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
			Optional<Cliente> obj = clienteRp.findByIdAndTenant(id, tn);
			if (obj.get().getId() <= 2) {
				// Nao remove se for o contratante - dono
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			} else {
				clienteRp.deleteById(id);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("masterTransactionManager")
	@PutMapping("/atualizasenha")
	public ResponseEntity<?> atualizarSenha(@RequestBody Cliente cliente) {
		if (ca.hasAuth(prm, 1, "A", cliente.getEmaillogin())) {
			Optional<Cliente> obj = clienteRp.findById(cliente.getId());
			if (cryptPass.cBryptCompara(cliente.getSenhaloginaux(), obj.get().getSenhalogin()) == true) {
				clienteRp.alterarSenha(cryptPass.cBrypt(cliente.getSenhalogin()), cliente.getId());
			} else {
				// Senha atual incorreta return new
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cliente/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<Cliente>> listaClientesBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 4, "L", "LISTAGEM / CONSULTA")) {
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
			Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<Cliente> lista = clienteRp.findAllByTenantNomeBusca(tn, busca, pageable);
			return new ResponseEntity<Page<Cliente>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
