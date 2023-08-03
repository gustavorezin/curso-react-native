package com.midas.api.controller.app;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import com.midas.api.constant.CdStatus;
import com.midas.api.constant.MidasConfig;
import com.midas.api.dto.AuthResponse;
import com.midas.api.dto.ClienteDTO;
import com.midas.api.mt.config.DBContextHolder;
import com.midas.api.mt.entity.Cliente;
import com.midas.api.mt.entity.Tenant;
import com.midas.api.mt.repository.ClienteRepository;
import com.midas.api.mt.repository.TenantRepository;
import com.midas.api.mt.service.TenantCriaService;
import com.midas.api.security.CustomUserDetail;
import com.midas.api.security.UserTenantInformation;
import com.midas.api.tenant.entity.AuxEmail;
import com.midas.api.tenant.service.EmailService;
import com.midas.api.util.CryptPassUtil;
import com.midas.api.util.EmailHTMLUtil;
import com.midas.api.util.EmailServiceUtil;
import com.midas.api.util.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthenticationController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Map<String, String> mapValue = new HashMap<>();
	private Map<String, String> userDbMap = new HashMap<>();
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private TenantRepository tenantRp;
	@Autowired
	private ClienteRepository clienteRp;
	@Autowired
	private TenantCriaService tenantCriaService;
	@Autowired
	private EmailHTMLUtil emailHTML;
	@Autowired
	private EmailServiceUtil emailServiceUtil;
	@Autowired
	private EmailService emailService;
	@Autowired
	private CryptPassUtil cryptPass;
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	private MidasConfig mc;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody ClienteDTO clienteDTO) throws AuthenticationException {
		if (null == clienteDTO.getUsername() || clienteDTO.getUsername().isEmpty()) {
			return new ResponseEntity<>("Usuario deve ser preenchido -- username --", HttpStatus.BAD_REQUEST);
		}
		// COMPARA DADOS RECEBIDOS******************************************************
		Cliente cliente = clienteRp.findByEmaillogin(clienteDTO.getUsername());
		if (cliente == null) {
			return new ResponseEntity<>("Usuario nao encontrado!", HttpStatus.UNAUTHORIZED);
		}
		// CONFIGURA PARAMETROS DE ACESSO AO BANCO**************************************
		Tenant tenant = tenantRp.findById(cliente.getTenant().getId()).get();
		if (tenant == null) {
			throw new RuntimeException("Nao foi possivel efetuar o login, entre em contato com o suporte tecnico!");
		}
		if (cliente.getStatus().equals(CdStatus.INATIVO.toString())) {
			// throw new RuntimeException("Perfil INATIVO, entre em contato com o suporte
			// tecnico!");
			return new ResponseEntity<>("Perfil INATIVO, entre em contato com o suporte tecnico!",
					HttpStatus.BAD_REQUEST);
		}
		if (tenant.getStatus().equals(CdStatus.INATIVO.toString())) {
			// throw new RuntimeException("Perfil INSTALACAO, entre em contato com o suporte
			// tecnico!");
			return new ResponseEntity<>("Perfil em INSTALACAO, aguarde configuracao!",
					HttpStatus.PRECONDITION_REQUIRED);
		}
		// DESATIVA SISTEMA PRAZO DE TESTES**********************************
		if (tenant.getPerteste().equals("S")) {
			if (tenant.getDatafimteste().compareTo(new Date()) < 0) {
				tenantRp.ativarCadastroTeste("INATIVO", "N", tenant.getDatafimteste(), tenant.getId());
			}
		}
		// CARREGA BASE NA AUTENTICACAO PARA SER USADA*******************************
		loadCurrentDatabaseInstance(tenant.getDbname(), cliente.getEmaillogin());
		// CARREGA DADOS USUARIO NO FRONT END****************************************
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(clienteDTO.getUsername(), clienteDTO.getPassword(),
						Arrays.asList(new SimpleGrantedAuthority(cliente.getRole().getRole()))));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		CustomUserDetail userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		final String token = jwtTokenUtil.generateToken(userDetails.getUsername(), String.valueOf(tenant.getId()));
		// MAPA PARA APPLICATION SCOPE*************************************************
		setMetaDataAfterLogin();
		return ResponseEntity.ok(new AuthResponse(cliente, userDetails.getUsername(), token, tenant.getId(),
				tenant.getPerteste(), tenant.getDatafimteste(), mc.tempobuscaman, tenant.getModuloclienteitem()));
	}

	@PostMapping("/loginsuporte")
	public ResponseEntity<?> loginSuporte(@RequestBody ClienteDTO clienteDTO) throws AuthenticationException {
		if (null == clienteDTO.getUsername() || clienteDTO.getUsername().isEmpty()) {
			return new ResponseEntity<>("Usuario deve ser preenchido -- username --", HttpStatus.BAD_REQUEST);
		}
		// COMPARA DADOS RECEBIDOS******************************************************
		Cliente cliente = clienteRp.findByEmaillogin(clienteDTO.getUsername());
		if (cliente == null) {
			return new ResponseEntity<>("Usuario nao encontrado!", HttpStatus.UNAUTHORIZED);
		}
		// CONFIGURA PARAMETROS DE ACESSO AO BANCO**************************************
		Tenant tenant = tenantRp.findById(clienteDTO.getTenantid()).get();
		if (tenant.getStatus().equals(CdStatus.INATIVO.toString())) {
			// throw new RuntimeException("Perfil INSTALACAO, entre em contato com o suporte
			// tecnico!");
			return new ResponseEntity<>("Perfil em INSTALACAO, aguarde configuracao!",
					HttpStatus.PRECONDITION_REQUIRED);
		}
		if (tenant == null || (!clienteDTO.getUsername().equals("suporte@midassi.com")
				&& !clienteDTO.getUsername().equals("daniel@midassi.com")
				&& !clienteDTO.getUsername().equals("dev@midassi.com"))) {
			throw new RuntimeException("Nao foi possivel efetuar o login, entre em contato com o suporte tecnico!");
		}
		// CARREGA BASE NA AUTENTICACAO PARA SER USADA*******************************
		loadCurrentDatabaseInstance(tenant.getDbname(), cliente.getEmaillogin());
		// CARREGA DADOS USUARIO NO FRONT END****************************************
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(clienteDTO.getUsername(), clienteDTO.getPassword(),
						Arrays.asList(new SimpleGrantedAuthority(cliente.getRole().getRole()))));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		CustomUserDetail userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		final String token = jwtTokenUtil.generateToken(userDetails.getUsername(), String.valueOf(tenant.getId()));
		// MAPA PARA APPLICATION SCOPE*************************************************
		setMetaDataAfterLogin();
		return ResponseEntity.ok(new AuthResponse(cliente, userDetails.getUsername(), token, tenant.getId(), "N",
				tenant.getDatafimteste(), mc.tempobuscaman, tenant.getModuloclienteitem()));
	}

	// CADASTRO NOVO CLIENTE
	@PostMapping("/cadastrar")
	@Transactional("masterTransactionManager")
	public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente)
			throws MessagingException, IOException, SQLException {
		tenantCriaService.criar(cliente);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// VERIFICA EMAIL
	@PostMapping("/buscaemail")
	public ResponseEntity<Cliente> buscaEmail(@RequestBody String email) {
		Cliente cliente = clienteRp.findByEmaillogin(email);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	// ATIVAR CADASTRO
	@PostMapping("/cadastroativar")
	@Transactional("masterTransactionManager")
	public ResponseEntity<?> cadastroAtivar(@RequestBody String chave) {
		Cliente cliente = clienteRp.findClienteByChaveativa(chave);
		clienteRp.ativarCadastro("ATIVO", cliente.getId());
		// tenantRp.ativarCadastro(cliente.getTenant().getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// RECUPERAR SENHA
	@PostMapping("/recuperarsenha")
	@Transactional("masterTransactionManager")
	public ResponseEntity<Cliente> recuperarSenha(@RequestBody String email)
			throws UnsupportedEncodingException, MessagingException {
		Cliente cliente = clienteRp.findByEmaillogin(email);
		Random gerador = new Random();
		String novasenha = "Ap@" + gerador.nextInt(198423) + ".midas";
		String html = emailHTML.textoRecuperarSenha(novasenha);
		novasenha = cryptPass.cBrypt(novasenha);
		clienteRp.alterarSenha(novasenha, cliente.getId());
		// Envia email nova senha******
		AuxEmail em = emailService.criaAuxEmail(null, mc);
		emailServiceUtil.EnviaHTML(cliente.getEmaillogin(), "Recuperação de senha - MIDAS", html, null, null, em);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	// RECUPERAR ATIVACAO
	@PostMapping("/recuperarativacao")
	@Transactional("masterTransactionManager")
	public ResponseEntity<Cliente> recuperarAtivacao(@RequestBody String email)
			throws UnsupportedEncodingException, MessagingException {
		Cliente cliente = clienteRp.findByEmaillogin(email);
		String html = emailHTML.textoAtivacao(cliente.getChaveativa());
		// Envia email ******
		AuxEmail em = emailService.criaAuxEmail(null, mc);
		emailServiceUtil.EnviaHTML(cliente.getEmaillogin(), "Ativação de Cadastro - MIDAS", html, null, null, em);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	// CONTROLE*****************************************************************
	private void loadCurrentDatabaseInstance(String databaseName, String userName) {
		DBContextHolder.setCurrentDb(databaseName);
		mapValue.put(userName, databaseName);
	}

	@Bean(name = "userTenantInfo")
	@ApplicationScope
	public UserTenantInformation setMetaDataAfterLogin() {
		UserTenantInformation tenantInformation = new UserTenantInformation();
		if (mapValue.size() > 0) {
			for (String key : mapValue.keySet()) {
				if (null == userDbMap.get(key)) {
					// Here Assign putAll due to all time one come.
					userDbMap.putAll(mapValue);
				} else {
					userDbMap.put(key, mapValue.get(key));
				}
			}
			mapValue = new HashMap<>();
		}
		tenantInformation.setMap(userDbMap);
		return tenantInformation;
	}
}
