package com.midas.api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.midas.api.mt.entity.Acesso;
import com.midas.api.mt.entity.ClienteModulo;
import com.midas.api.mt.repository.AcessoRepository;
import com.midas.api.mt.repository.ClienteModuloRepository;
import com.midas.api.tenant.entity.SisAcesso;
import com.midas.api.tenant.entity.SisReguser;
import com.midas.api.tenant.repository.SisAcessoRepository;
import com.midas.api.tenant.repository.SisReguserRepository;

@Component
public class ControleAutoridade {
	@Autowired
	private AcessoRepository acessoRp;
	@Autowired
	private SisAcessoRepository sisAcessoRp;
	@Autowired
	private SisReguserRepository sisReguserRp;
	@Autowired
	private ClienteModuloRepository clienteModuloRp;

	// Controle de permissoes e acesso do sistema
	public boolean hasAuth(ClienteParam prm, Integer acesso, String tipo, String onde) {
		Integer role = prm.cliente().getRole().getId();
		SisAcesso ac = sisAcessoRp.findByRoleAndAcesso(role, acesso);
		// Grava processos usuarios
		if (prm.cliente().getSiscfg().isSis_gravar_proc_user() == true) {
			Optional<Acesso> a = acessoRp.findById(acesso);
			gravaReg(prm, role, acesso, a.get().getNome(), tipo, onde);
		}
		// ADMIN - CLIENTE - USUARIO / Usuarios com permissao total sempre
		if (role <= 3) {
			return true;
		}
		// Controle da permissao
		if (ac != null) {
			return true;
		}
		return false;
	}

	// Controle de modulos do sistema
	public boolean hasAuthModulo(ClienteParam prm, Integer modulo) {
		Integer role = prm.cliente().getRole().getId();
		ClienteModulo md = clienteModuloRp.findByTenantAndModulo(prm.cliente().getTenant().getId(), modulo);
		// ADMIN - CLIENTE - USUARIO / Usuarios com permissao total sempre
		if (role <= 3) {
			return true;
		}
		// Controle da permissao
		if (md != null) {
			return true;
		}
		return false;
	}

	private void gravaReg(ClienteParam prm, Integer role, Integer acesso, String descricao, String tipo, String onde) {
		String usuario = prm.cliente().getNome() + " " + prm.cliente().getSobrenome();
		SisReguser r = new SisReguser();
		r.setCdpessoaemp_id(prm.cliente().getCdpessoaemp());
		r.setAcesso(acesso);
		r.setDescricao(descricao);
		r.setRole(role);
		r.setUsuario_id(prm.cliente().getId());
		r.setUsuario(usuario.toUpperCase());
		r.setLogin(prm.cliente().getEmaillogin());
		r.setTipo(tipo);
		r.setOnde(onde);
		sisReguserRp.save(r);
	}
}
