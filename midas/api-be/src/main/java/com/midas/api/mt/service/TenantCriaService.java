package com.midas.api.mt.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.midas.api.constant.MidasConfig;
import com.midas.api.mt.entity.CdModulo;
import com.midas.api.mt.entity.Cliente;
import com.midas.api.mt.entity.ClienteCfg;
import com.midas.api.mt.entity.ClienteModulo;
import com.midas.api.mt.entity.Role;
import com.midas.api.mt.entity.SisCfg;
import com.midas.api.mt.entity.Tenant;
import com.midas.api.mt.repository.CdModuloRepository;
import com.midas.api.mt.repository.ClienteModuloRepository;
import com.midas.api.mt.repository.ClienteRepository;
import com.midas.api.mt.repository.ClientecfgRepository;
import com.midas.api.mt.repository.RoleRepository;
import com.midas.api.mt.repository.SisCfgRepository;
import com.midas.api.mt.repository.TenantRepository;
import com.midas.api.tenant.entity.AuxEmail;
import com.midas.api.tenant.service.EmailService;
import com.midas.api.util.CryptPassUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.EmailHTMLUtil;
import com.midas.api.util.EmailServiceUtil;

@Service
public class TenantCriaService {
	@Autowired
	private CryptPassUtil cryptPass;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TenantRepository tenantRp;
	@Autowired
	private RoleRepository roleRp;
	@Autowired
	private ClienteRepository clienteRp;
	@Autowired
	private ClientecfgRepository clientecfgRp;
	@Autowired
	private SisCfgRepository sisCfgRp;
	@Autowired
	private EmailHTMLUtil emailHTML;
	@Autowired
	private EmailServiceUtil emailServiceUtil;
	@Autowired
	private EmailService emailService;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private ClienteModuloRepository clienteModuloRp;
	@Autowired
	private CdModuloRepository cdModuloRp;

	// CONFIGURA NOVO CLIENTE/TENANT
	public void criar(Cliente cliente) throws MessagingException, IOException, SQLException {
		// Configura base****************
		Long ultBase = tenantRp.getMaxTenantId();
		ultBase = ultBase + 1;
		String nomeBase = mc.DbName + "" + ultBase;
		Tenant tn = new Tenant();
		tn.setDbname(nomeBase);
		String url = mc.Url + "" + nomeBase;
		tn.setUrl(url);
		tn.setUsername(cryptPass.cBase64(mc.Username));
		tn.setPassword(cryptPass.cBase64(mc.Password));
		tn.setDriverclass(mc.Driverclass);
		tn.setMdump(mc.MysqlDump);
		tn.setBackupfd(mc.BackupFd);
		tn.setTempfd(mc.TempFd);
		tn.setSqlconfig("N");
		tn.setPerteste("S"); // Periodo de teste
		tn.setDatafimteste(DataUtil.addRemDias(new Date(), 15, "A")); // Fim do teste
		tn.setStatus("INATIVO");
		Tenant tenant = tenantRp.save(tn);
		// Apenas modulo financeiro inicialmente-----
		Optional<CdModulo> cmm = cdModuloRp.findById(9);
		ClienteModulo cm = new ClienteModulo();
		cm.setCdmodulo(cmm.get());
		cm.setTenant(tenant);
		clienteModuloRp.save(cm);
		// Cria Base
		jdbcTemplate.execute("CREATE DATABASE " + nomeBase);
		// Cria chave para ativacao de cadastro
		@SuppressWarnings("static-access")
		String chaveativa = cryptPass.cSHA1(cliente.getEmaillogin() + "" + new Date().getTime());
		// Cadastra cliente***************
		jdbcTemplate.execute("INSERT INTO cliente_cfg (id) VALUES (null)");
		Long intCfg = clientecfgRp.getMaxClienteCfgId();
		ClienteCfg cfg = clientecfgRp.getById(intCfg);
		jdbcTemplate.execute(
				"INSERT INTO sis_cfg (id,datacad,dataat,sis_gravar_proc_user,sis_tam_imagem_pixel,sis_separa_juros_plcon,sis_busca_auto_nfe,"
						+ "sis_mt_auto_nfe,sis_email_auto_nfe,sis_amb_nfe,sis_amb_mdfe,sis_mostra_veiculo,sis_amb_boleto,sis_amb_nfse,sis_usa_balanca,"
						+ "sis_usa_codigo_padrao,sis_busca_auto_cte,sis_assinafn_auto,sis_tecno_tipo_imp,sis_modfrete_nfe,sis_usa_codigo_alterpdv,sis_impref_doc, "
						+ "sis_impref_nf) "
						+ "VALUES (null,NOW(),NOW(), true, 450,true,false,true,false,2,2,false,2,2,false,true,false,true,'2','9',false,false,true)");
		Integer intSc = sisCfgRp.getMaxSisCfgId();
		SisCfg sc = sisCfgRp.getById(intSc);
		// Dados clientes*************
		Cliente obj = new Cliente();
		obj.setEmaillogin(cliente.getEmaillogin());
		obj.setSenhalogin(cryptPass.cBrypt(cliente.getSenhalogin()));
		obj.setNome(cliente.getNome());
		obj.setSobrenome(cliente.getSobrenome());
		obj.setCelular(cliente.getCelular());
		obj.setCep("00000000");// Define zeros para deixar usuario entrar no periodo de teste
		obj.setStatus("INATIVO");
		Role role = roleRp.getById(2);
		obj.setRole(role);
		obj.setChaveativa(chaveativa);
		obj.setTenant(tn);
		obj.setClientecfg(cfg);
		obj.setSiscfg(sc);
		obj.setCdpessoaemp(0L);
		obj.setAclocal("S");// Acesso a todos locais
		clienteRp.save(obj);
		// Config. Cliente***********
		criaClienteCfg(intCfg, 2);
		// Cria tabelas e configura DB
		// DataSourceUtil.createAndConfigureDataSource(tenant); REMOVIDO parece travar
		// Envia email para ativacao******
		String html = emailHTML.textoAtivacao(chaveativa);
		AuxEmail em = emailService.criaAuxEmail(null, mc);
		emailServiceUtil.EnviaHTML(cliente.getEmaillogin(), "Ativação de Cadastro - MIDAS", html, null, null, em);
		// Envia email para suporte gerar dados
		String htmlsup = emailHTML.novoCliente(cliente.getEmaillogin(), nomeBase);
		// Aviso de novo cliente a ser instalado
		emailServiceUtil.EnviaHTML("suporte@midassi.com", "Novo cliente - MIDAS | " + cliente.getEmaillogin(), htmlsup,
				null, null, em);
	}

	public ClienteCfg criaClienteCfg(Long intCfg, Integer role) {
		ClienteCfg cfgCf = new ClienteCfg();
		cfgCf.setId(intCfg);
		cfgCf.setItensporpagina(10);
		cfgCf.setPlanofundo("bg-img-num6");
		cfgCf.setCortema("");// Escuro
		// Admin, Cliente, Usuario, Restrito
		if (role <= 3 || role == 7) {
			cfgCf.setModulo("completo");
		}
		// Vendedor
		if (role == 4) {
			cfgCf.setModulo("vendas");
		}
		// Financeiro
		if (role == 5) {
			cfgCf.setModulo("financeiro");
		}
		// Producao
		if (role == 6) {
			cfgCf.setModulo("producao");
		}
		// Representante
		if (role == 8) {
			cfgCf.setModulo("representantes");
		}
		return clientecfgRp.save(cfgCf);
	}
}