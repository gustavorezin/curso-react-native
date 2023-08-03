package com.midas.api.tenant.entity.tecno;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.midas.api.constant.MidasConfig;
import com.midas.api.security.ClienteParam;
import com.midas.api.tenant.entity.AuxDados;
import com.midas.api.tenant.entity.CdBolcfg;
import com.midas.api.tenant.entity.CdEmail;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.util.HttpRequest;
import com.midas.api.util.LerArqUtil;
import com.midas.api.util.integra.TecnoUtil;

@Component
public class TecnoCadUtil {
	// BUSCA CEDENTE JA CADASTRADO NA TECNOSPEED----------------
	public AuxDados getCedente(CdPessoa e, MidasConfig mc, ClienteParam prm) {
		AuxDados a = new AuxDados();
		// Converte objeto para json
		String rota = "cedentes?cpf_cnpj=" + e.getCpfcnpj();
		HttpRequest results = TecnoUtil.httpResquestGet(mc, prm, rota, e);
		int status = results.code();
		String retorno = results.body();
		// Se retorna OK
		if (status == 200) {
			TecnoRetornoListStatus ret = new Gson().fromJson(retorno, TecnoRetornoListStatus.class);
			for (TecnoRetornoDadosList es : ret.get_dados()) {
				// Monta resposta
				a.setCampo1(status + "");
				a.setCampo2(es.getCpf_cnpj());
				a.setCampo3(es.getSituacao());
			}
		}
		if (status != 200) {
			TecnoRetornoListStatus ret = new Gson().fromJson(retorno, TecnoRetornoListStatus.class);
			// Monta resposta principal
			AuxDados ap = new AuxDados();
			ap.setCampo1(status + "");
			ap.setCampo2("Problemas ao buscar dados!");
			ap.setCampo3(ret.get_mensagem() + "!");
		}
		return a;
	}

	// CADASTRO CEDENTE NA TECNOSPEED--------------------------
	public List<AuxDados> configurarCedente(CdPessoa e, MidasConfig mc, ClienteParam prm) {
		List<AuxDados> aux = new ArrayList<AuxDados>();
		TecnoCedente c = new TecnoCedente();
		c.setCedenteNomeFantasia(e.getFantasia());
		c.setCedenteRazaoSocial(e.getNome());
		c.setCedenteCPFCNPJ(e.getCpfcnpj());
		c.setCedenteTelefone(e.getFoneum());
		c.setCedenteEmail(e.getEmail());
		c.setCedenteEnderecoLogradouro(e.getRua());
		c.setCedenteEnderecoNumero(e.getNum());
		c.setCedenteEnderecoComplemento(e.getComp());
		c.setCedenteEnderecoBairro(e.getBairro());
		c.setCedenteEnderecoCEP(e.getCep());
		c.setCedenteEnderecoCidadeIBGE(e.getCdcidade().getIbge());
		// Converte objeto para json
		String json = LerArqUtil.setJsonString(c);
		HttpRequest results = null;
		if (e.getCedente_id_tecno() == null) {
			String rota = "cedentes";
			results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
		} else {
			String rota = "cedentes/" + e.getCedente_id_tecno();
			results = TecnoUtil.httpResquestPut(mc, prm, rota, json, e);
		}
		int status = results.code();
		String retorno = results.body();
		// Se retorna OK
		if (status == 200) {
			TecnoRetornoDados ret = new Gson().fromJson(retorno, TecnoRetornoDados.class);
			// Monta resposta
			AuxDados a = new AuxDados();
			a.setCampo1(status + "");
			a.setCampo2("Cedente configurado com sucesso!");
			a.setCampo3(ret.get_dados().getId());
			a.setCampo4(ret.get_dados().getToken_cedente());
			aux.add(a);
		}
		if (status != 200) {
			TecnoRetornoListStatus ret = new Gson().fromJson(retorno, TecnoRetornoListStatus.class);
			// Monta resposta principal
			AuxDados ap = new AuxDados();
			ap.setCampo1(status + "");
			ap.setCampo2("Problemas ao configurar!");
			ap.setCampo3(ret.get_mensagem() + "!");
			aux.add(ap);
			for (TecnoRetornoDadosList es : ret.get_dados()) {
				// Monta resposta
				AuxDados a = new AuxDados();
				a.setCampo1(status + "");
				a.setCampo2(es.get_campo());
				a.setCampo3(es.get_erro());
				System.out.println(es.get_campo() + " -- " + es.get_erro());
				aux.add(a);
			}
		}
		return aux;
	}

	// CADASTRO CONTA NA TECNOSPEED-----------------------------
	public List<AuxDados> configurarConta(CdBolcfg bc, CdPessoa e, MidasConfig mc, ClienteParam prm) {
		List<AuxDados> aux = new ArrayList<AuxDados>();
		TecnoConta tec = new TecnoConta();
		tec.setContaCodigoBanco(bc.getBanco());
		tec.setContaAgencia(bc.getAgencia());
		tec.setContaAgenciaDV(bc.getAgenciadv());
		tec.setContaNumero(bc.getConta());
		tec.setContaNumeroDV(bc.getContadv());
		tec.setContaTipo(bc.getTipo());
		tec.setContaCodigoBeneficiario(bc.getCodben());
		tec.setContaCodigoEmpresa(bc.getCodemp());
		if (bc.getStatus().equals("ATIVO")) {
			tec.setAtivo(true);
		} else {
			tec.setAtivo(false);
		}
		// Converte objeto para json
		String json = LerArqUtil.setJsonString(tec);
		HttpRequest results = null;
		if (bc.getId_conta_tecno() == 0) {
			String rota = "cedentes/contas";
			results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
		} else {
			String rota = "cedentes/contas/" + bc.getId_conta_tecno();
			results = TecnoUtil.httpResquestPut(mc, prm, rota, json, e);
		}
		int status = results.code();
		String retorno = results.body();
		// Se retorna OK
		if (status == 200) {
			TecnoRetornoDados ret = new Gson().fromJson(retorno, TecnoRetornoDados.class);
			// Monta resposta
			AuxDados a = new AuxDados();
			a.setCampo1(status + "");
			a.setCampo2(ret.get_dados().getId());
			aux.add(a);
		}
		if (status != 200) {
			TecnoRetornoListStatus ret = new Gson().fromJson(retorno, TecnoRetornoListStatus.class);
			for (TecnoRetornoDadosList es : ret.get_dados()) {
				// Monta resposta
				AuxDados a = new AuxDados();
				a.setCampo1(status + "");
				a.setCampo2(es.get_campo());
				a.setCampo3(es.get_erro());
				aux.add(a);
			}
		}
		return aux;
	}

	// REMOVE CONTA E CONVEIO JUNTOS TECOSPEED----------------
	public AuxDados removerConta(CdBolcfg bc, CdPessoa e, MidasConfig mc, ClienteParam prm) {
		AuxDados a = new AuxDados();
		// Converte objeto para json
		String rota = "cedentes/contas/" + bc.getId_conta_tecno();
		HttpRequest results = TecnoUtil.httpResquestDelete(mc, prm, rota, e);
		int status = results.code();
		String retorno = results.body();
		TecnoRetornoDados ret = new Gson().fromJson(retorno, TecnoRetornoDados.class);
		// Monta resposta
		a.setCampo1(status + "");
		a.setCampo2(ret.get_status());
		a.setCampo3(ret.get_mensagem());
		return a;
	}

	// CADASTRO CONVENIO TECNO SPEED--------------------------
	public List<AuxDados> configurarConvenio(CdBolcfg bc, CdPessoa e, MidasConfig mc, ClienteParam prm) {
		List<AuxDados> aux = new ArrayList<AuxDados>();
		// Converte objeto para json
		TecnoConvenio tec = new TecnoConvenio();
		tec.setConta(bc.getId_conta_tecno());
		tec.setConvenioNumero(bc.getNumconvenio());
		tec.setConvenioCarteira(bc.getCarteira());
		tec.setConvenioCarteiraCodigo(bc.getCarteira());
		tec.setConvenioDescricao("CONVENIO PADRAO");
		tec.setConvenioEspecie(bc.getEspecie());
		tec.setConvenioNumeroRemessa(bc.getNumremessa() + "");
		tec.setConvenioPadraoCNAB(bc.getCnab());
		tec.setConvenioReiniciarDiariamente(bc.isReinciarrem());
		String json = LerArqUtil.setJsonString(tec);
		HttpRequest results = null;
		if (bc.getId_conv_tecno() == 0) {
			String rota = "cedentes/contas/convenios";
			results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
		} else {
			String rota = "cedentes/contas/convenios/" + bc.getId_conv_tecno();
			results = TecnoUtil.httpResquestPut(mc, prm, rota, json, e);
		}
		int status = results.code();
		String retorno = results.body();
		// Se retorna OK
		if (status == 200) {
			TecnoRetornoDados ret = new Gson().fromJson(retorno, TecnoRetornoDados.class);
			// Monta resposta
			AuxDados a = new AuxDados();
			a.setCampo1(status + "");
			a.setCampo2(ret.get_dados().getId());
			aux.add(a);
		}
		if (status != 200) {
			TecnoRetornoListStatus ret = new Gson().fromJson(retorno, TecnoRetornoListStatus.class);
			for (TecnoRetornoDadosList es : ret.get_dados()) {
				// Monta resposta
				AuxDados a = new AuxDados();
				a.setCampo1(status + "");
				a.setCampo2(es.get_campo());
				a.setCampo3(es.get_erro());
				aux.add(a);
			}
		}
		return aux;
	}

	// CADASTRO EMAIL NA TECNOSPEED--------------------------
	public List<AuxDados> configurarEmail(CdPessoa e, MidasConfig mc, ClienteParam prm, CdEmail em) {
		List<AuxDados> aux = new ArrayList<AuxDados>();
		TecnoEmail c = new TecnoEmail();
		// GMAIL
		if (em.getTipo().equals("GMAIL")) {
			c.setServico("Gmail");
		}
		// HOTMAIL
		if (em.getTipo().equals("HOTMAIL")) {
			c.setServico("Hotmail");
		}
		// OUTROS
		if (em.getTipo().equals("OUTROS")) {
			c.setServico("Personalizado");
		}
		c.setEmailremetente(em.getEmail());
		c.setUsuario(em.getLogin());
		c.setSenha(em.getSenha());
		TecnoEmailConfig tc = new TecnoEmailConfig();
		tc.setSmtp(em.getSmtp());
		if (em.getSslsmtp().equals("S")) {
			tc.setRequiretls(true);
		} else {
			tc.setRequiretls(false);
		}
		tc.setPort(em.getPortasmtp());
		c.setConfig(tc);
		// Converte objeto para json
		String json = LerArqUtil.setJsonString(c);
		String rota = "email/config";
		HttpRequest results = TecnoUtil.httpResquestPost(mc, prm, rota, json, e);
		int status = results.code();
		// Se retorna OK
		if (status == 200) {
			// Monta resposta
			AuxDados a = new AuxDados();
			a.setCampo1(status + "");
			a.setCampo2("E-mail configurado com sucesso!");
			aux.add(a);
		}
		if (status != 200) {
			// Monta resposta principal
			AuxDados ap = new AuxDados();
			ap.setCampo1(status + "");
			ap.setCampo2("Problemas ao configurar e-mail!");
			aux.add(ap);
		}
		return aux;
	}
}
