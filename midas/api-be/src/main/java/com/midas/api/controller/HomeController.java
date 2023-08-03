package com.midas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.constant.MidasConfig;
import com.midas.api.mt.config.DBContextHolder;
import com.midas.api.mt.entity.Tenant;
import com.midas.api.mt.repository.TenantRepository;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.config.TenantAwareThread;
import com.midas.api.tenant.entity.CdCaixa;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdPlconConta;
import com.midas.api.tenant.fiscal.service.FsSpedService;
import com.midas.api.tenant.repository.CdCaixaRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdPlconContaRepository;
import com.midas.api.tenant.service.FnTituloService;
import com.midas.api.tenant.service.integra.TecnoBoletoService;
import com.midas.api.util.DataBaseBackupUtil;
import com.midas.api.util.LerArqUtil;
import com.midas.api.util.RegUserUtil;
import com.midas.api.util.SQLExecUtil;

@RestController
@RequestMapping("/private/home")
public class HomeController {
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdCaixaRepository caixaRp;
	@Autowired
	private TenantRepository tenantRp;
	@Autowired
	private CdPlconContaRepository cdPlconContaRp;
	@Autowired
	private FnTituloService fnTituloService;
	@Autowired
	private DataBaseBackupUtil dataBaseBackupUtil;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private RegUserUtil regUserUtil;
	@Autowired
	private FsSpedService fsSpedService;
	@Autowired
	private TecnoBoletoService tecnoBoletoService;

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/sejabemvindo")
	public ResponseEntity<String> sejaBemVindo() throws Exception {
		String onde = "0";
		// Verifica se ja tem DRE configurada
		List<CdPlconConta> pls = cdPlconContaRp.findAll();
		if (pls.size() == 0) {
			onde = "3";
		}
		// Verifica se ja tem Local/Empresa/Emitente
		List<CdCaixa> cxs = caixaRp.findAll();
		if (cxs.size() == 0) {
			onde = "2";
		}
		// Verifica se ja tem Local/Empresa/Emitente
		CdPessoa local = cdPessoaRp.findFirstByTipo("EMPRESA");
		if (local == null) {
			onde = "1";
		}
		// Funcoes de inicializacao
		threadExecutor();
		// CRIA SPED
		fsSpedService.criaSpedPeriodo();
		// CRIA PASTAS
		Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
		LerArqUtil.criaPastaPadrao(tn);
		return new ResponseEntity<String>(onde, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/gerasqlpadrao")
	public ResponseEntity<?> geraSQLPadrao() throws Exception {
		// Cria padroes
		execPadrao();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/cobauto")
	public ResponseEntity<String> cobAuto() throws Exception {
		fnTituloService.cobAuto();
		// Efetua baixa auto de boletos
		List<CdPessoa> emps = cdPessoaRp.findAllByTipoTodosAtivo("EMPRESA", "ATIVO");
		for (CdPessoa e : emps) {
			if (e.getCedente_id_tecno() != null) {
				if (!e.getCedente_id_tecno().equals("")) {
					tecnoBoletoService.consultaBoletosBaixa(e);
				}
			}
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// FUNCOES DE INICIO DO SISTEMA
	@Transactional("tenantTransactionManager")
	private void threadExecutor() throws InterruptedException {
		new TenantAwareThread(() -> {
			try {
				// BACKUP
				dataBaseBackupUtil.backupApp(DBContextHolder.getCurrentDb(), "N");
				Thread.sleep(4000);
				// REGUSER LIMPA
				regUserUtil.cleanRegUser(DBContextHolder.getCurrentDb());
				Thread.sleep(4000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	// PADROES BANCO DE DADOS
	private void execPadrao() throws Exception {
		// CRIA PADROES NO BANCO NOVO
		Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
		if (tn.getSqlconfig().equals("N")) {
			SQLExecUtil.executarSqlPadrao("sql/sqlpadrao.sql", mc);
			tenantRp.updateSqlPadrao(tn.getId());
		}
	}
}
