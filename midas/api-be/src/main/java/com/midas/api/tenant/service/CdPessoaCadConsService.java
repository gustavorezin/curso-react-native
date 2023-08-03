package com.midas.api.tenant.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.security.ClienteParam;
import com.midas.api.tenant.entity.CdCfgFiscalSerie;
import com.midas.api.tenant.entity.CdCidade;
import com.midas.api.tenant.entity.CdEstado;
import com.midas.api.tenant.entity.CdNfeCfgSimples;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdPessoaGrupo;
import com.midas.api.tenant.entity.ReceitaWSCNPJ;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.repository.CdCfgFiscalSerieRepository;
import com.midas.api.tenant.repository.CdCidadeRepository;
import com.midas.api.tenant.repository.CdEstadoRepository;
import com.midas.api.tenant.repository.CdNfeCfgSimplesRepository;
import com.midas.api.tenant.repository.CdPessoaGrupoRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.util.CaracterUtil;

@Service
public class CdPessoaCadConsService {
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdEstadoRepository cdEstadoRp;
	@Autowired
	private CdCidadeRepository cdCidadeRp;
	@Autowired
	private CdPessoaGrupoRepository cdGruPesRp;
	@Autowired
	private CdNfeCfgSimplesRepository cdNfeCfgSimplesRp;
	@Autowired
	private CdCfgFiscalSerieRepository cdCfgFiscalSerieRp;

	// CADASTRO PESSOA
	// RECEITA*******************************************************************
	public String[] cadastrar(ReceitaWSCNPJ rc, ClienteParam prm, String tipopessoa, Integer cdgrupes, Integer crt,
			String indiedest) throws SQLException {
		String cnpj = CaracterUtil.remPout(rc.getCnpj());
		String[] ret = new String[2];
		CdPessoa cons = cdPessoaRp.findFirstByCpfcnpj(cnpj);
		if (cons == null) {
			CdPessoa c = new CdPessoa();
			c.setTipo(tipopessoa);
			c.setEmp(prm.cliente().getCdpessoaemp());
			CdPessoaGrupo gp = cdGruPesRp.getById(cdgrupes);
			c.setCdpessoagrupo(gp);
			c.setNome(CaracterUtil.tamMax(rc.getNome(), 60));
			c.setFantasia(CaracterUtil.tamMax(rc.getFantasia(), 60));
			c.setCpfcnpj(cnpj);
			c.setIerg(CaracterUtil.remPout(rc.getIe()));
			c.setIndiedest(indiedest);
			c.setFoneum(CaracterUtil.tamMax(CaracterUtil.remPout(rc.getTelefone()).replaceAll(" ", ""), 12));
			c.setEmail(FsUtil.cpND(rc.getEmail(), "email@email.com"));
			c.setRua(CaracterUtil.tamMax(rc.getLogradouro(), 60));
			c.setNum(CaracterUtil.tamMax(rc.getNumero(), 60));
			c.setComp(CaracterUtil.tamMax(rc.getComplemento(), 60));
			c.setBairro(CaracterUtil.tamMax(rc.getBairro(), 60));
			CdEstado es = cdEstadoRp.findByUf(rc.getUf());
			c.setCdestado(es);
			CdCidade ci = cdCidadeRp.findByNomeAndCdestadoId(rc.getMunicipio(), es.getId());
			c.setCdcidade(ci);
			c.setCep(CaracterUtil.remPout(rc.getCep()));
			c.setIdestrangeiro("-");
			c.setCrt(crt);
			c.setStatus("ATIVO");
			c.setCdpessoaresp_id(0L);
			// Verifica acesso Representante
			if (prm.cliente().getRole().getId() == 8) {
				if (prm.cliente().getCdpessoavendedor() != null) {
					c.setCdpessoaresp_id(prm.cliente().getCdpessoavendedor());
				}
			}
			cdPessoaRp.save(c);
			ret[0] = "ok";
			return ret;
		} else {
			ret[0] = "jacadastrado";
			ret[1] = cons.getTipo();
			return ret;
		}
	}

	// INCLUI CONFIGURACAO INICIAL
	public void geraCfgFiscalIni(String tipocad) {
		if (tipocad.equals("EMPRESA")) {
			// Ver ja tem item configurado, se nao, configura
			List<CdPessoa> pes = cdPessoaRp.findAllByTipoTodos("EMPRESA");
			for (CdPessoa p : pes) {
				// Aliquota simples
				CdNfeCfgSimples vr = cdNfeCfgSimplesRp.getByCdpessoaemp(p.getId());
				if (vr == null) {
					CdNfeCfgSimples c = new CdNfeCfgSimples();
					c.setCdpessoaemp(p);
					c.setAliq(new BigDecimal(0));
					cdNfeCfgSimplesRp.save(c);
				}
				// Serie da nfe 55 - nf
				CdCfgFiscalSerie fs = cdCfgFiscalSerieRp.getByCdpessoaemp(p.getId(), "55");
				if (fs == null) {
					CdCfgFiscalSerie c = new CdCfgFiscalSerie();
					c.setCdpessoaemp(p);
					c.setModelo("55");
					cdCfgFiscalSerieRp.save(c);
				}
				// Serie da nfe 57 - cte
				CdCfgFiscalSerie fsc = cdCfgFiscalSerieRp.getByCdpessoaemp(p.getId(), "57");
				if (fsc == null) {
					CdCfgFiscalSerie c = new CdCfgFiscalSerie();
					c.setCdpessoaemp(p);
					c.setModelo("57");
					cdCfgFiscalSerieRp.save(c);
				}
				// Serie da nfe 58 - mdf
				CdCfgFiscalSerie fsm = cdCfgFiscalSerieRp.getByCdpessoaemp(p.getId(), "58");
				if (fsm == null) {
					CdCfgFiscalSerie c = new CdCfgFiscalSerie();
					c.setCdpessoaemp(p);
					c.setModelo("58");
					cdCfgFiscalSerieRp.save(c);
				}
				// Serie da nfe 65 - nfc
				CdCfgFiscalSerie fsn = cdCfgFiscalSerieRp.getByCdpessoaemp(p.getId(), "65");
				if (fsn == null) {
					CdCfgFiscalSerie c = new CdCfgFiscalSerie();
					c.setCdpessoaemp(p);
					c.setModelo("65");
					cdCfgFiscalSerieRp.save(c);
				}
				// Serie da nfe 99 - nfs
				CdCfgFiscalSerie fss = cdCfgFiscalSerieRp.getByCdpessoaemp(p.getId(), "99");
				if (fss == null) {
					CdCfgFiscalSerie c = new CdCfgFiscalSerie();
					c.setCdpessoaemp(p);
					c.setModelo("99");
					cdCfgFiscalSerieRp.save(c);
				}
			}
		}
	}
}
