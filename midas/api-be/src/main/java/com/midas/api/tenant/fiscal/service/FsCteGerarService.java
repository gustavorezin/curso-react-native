package com.midas.api.tenant.fiscal.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midas.api.constant.MidasConfig;
import com.midas.api.tenant.entity.CdCfgFiscalSerie;
import com.midas.api.tenant.entity.CdCteCfg;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdVeiculo;
import com.midas.api.tenant.entity.CdXmlAutoriza;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsCteAut;
import com.midas.api.tenant.entity.FsCteIcms;
import com.midas.api.tenant.entity.FsCteInf;
import com.midas.api.tenant.entity.FsCtePart;
import com.midas.api.tenant.entity.FsCteMot;
import com.midas.api.tenant.entity.FsCteVprest;
import com.midas.api.tenant.fiscal.util.GeraChavesUtil;
import com.midas.api.tenant.repository.CdCfgFiscalSerieRepository;
import com.midas.api.tenant.repository.CdCteCfgRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdVeiculoRepository;
import com.midas.api.tenant.repository.CdXmlAutorizaRepository;
import com.midas.api.tenant.repository.FsCteAutRepository;
import com.midas.api.tenant.repository.FsCteIcmsRepository;
import com.midas.api.tenant.repository.FsCteInfRepository;
import com.midas.api.tenant.repository.FsCtePartRepository;
import com.midas.api.tenant.repository.FsCteRepository;
import com.midas.api.tenant.repository.FsCteMotRepository;
import com.midas.api.tenant.repository.FsCteVprestRepository;
import com.midas.api.util.DataUtil;

@Service
public class FsCteGerarService {
	@Autowired
	private FsCteRepository fsCteRp;;
	@Autowired
	private FsCtePartRepository fsCtePartRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdVeiculoRepository cdVeiculoRp;
	@Autowired
	private CdCteCfgRepository cdCteCfgRp;
	@Autowired
	private FsCteIcmsRepository fsCteIcmsRp;
	@Autowired
	private FsCteInfRepository fsCteInfRp;
	@Autowired
	private FsCteMotRepository fsCteMotRp;
	@Autowired
	private FsCteVprestRepository fsCteVprestRp;
	@Autowired
	private FsCteAutRepository fsCteAutRp;
	@Autowired
	private CdXmlAutorizaRepository cdXmlAutorizaRp;
	@Autowired
	private CdCfgFiscalSerieRepository cdCfgFiscalSerieRp;
	@Autowired
	private MidasConfig mc;

	@Transactional("tenantTransactionManager")
	public FsCte gerarCte(String ambiente, Long cdpessoaemp, Integer cfop, Long cdpessoarem, Long cdpessoadest, Long cdpessoamot, Integer cdveiculo)
			throws Exception {
		CdPessoa emp = cdPessoaRp.findById(cdpessoaemp).get();
		CdPessoa rem = cdPessoaRp.findById(cdpessoarem).get();
		CdPessoa dest = cdPessoaRp.findById(cdpessoadest).get();
		CdPessoa mot = cdPessoaRp.findById(cdpessoamot).get();
		CdVeiculo veic = cdVeiculoRp.findById(cdveiculo).get();
		CdCteCfg ctecfg = cdCteCfgRp.findById(cfop).get();
		CdCfgFiscalSerie cdSerie = cdCfgFiscalSerieRp.getByCdpessoaemp(emp.getId(), "57");
		Integer serie = cdSerie.getSerieatual();
		FsCte ultCte = fsCteRp.ultimaByCdpessoaempSerieTpamb(emp.getId(), 1, ambiente);
		String dataAAMM = DataUtil.anoMesAtualAAMM();
		Integer numAdd = 1;
		if (ultCte != null) {
			numAdd = ultCte.getNct() + 1;
		}
		String chaveac = GeraChavesUtil.geraChavePadrao(emp.getCdestado().getId(), dataAAMM, emp.getCpfcnpj(), 57,
				serie, 1, numAdd, numAdd, "CTe").replace("CTe", "");
		// DIGITO CHAVE--------------------------------------
		String cDv = chaveac.substring(43, 44);
		FsCte c = new FsCte();
		c.setCdpessoaemp(emp);
		c.setNct(numAdd);
		c.setCct(numAdd);
		c.setCdv(Integer.valueOf(cDv));
		c.setChaveac(chaveac);
		c.setSerie(serie);
		c.setDhemi(new Date());
		c.setDhemihr(new Date());
		c.setDhsaient(new Date());
		c.setTpamb(Integer.valueOf(ambiente));
		c.setTipo("E"); // emitida
		c.setTpimp(1); // retrato
		c.setTpcte(0); // normal
		c.setTpemis(1); // normal
		c.setTpserv(0); // normal
		c.setTomador(0); // remetente
		c.setModal("01"); // rodoviario
		c.setProcemi(0); // aplicativo contribuinte
		c.setRetira(0); // nao retira
		c.setVerproc(mc.MidasVersao);
		c.setFscteicms(icms(c, ctecfg));
		c.setFscteinf(inf(c));
		c.setFsctepartemit(part(emp, "E", null));
		c.setFsctepartrem(part(rem, "R", null));
		c.setFsctepartdest(part(dest, "D", null));
		c.setFsctepartexp(part(rem, "X", null));
		c.setFsctepartrec(part(dest, "C", null));
		c.setFsctevprest(vprest(c));
		c.setCmunenv(emp.getCdcidade().getIbge());
		c.setXmunenv(emp.getCdcidade().getNome());
		c.setUfenv(emp.getCdestado().getUf());
		c.setCmunini(rem.getCdcidade().getIbge());
		c.setXmunini(rem.getCdcidade().getNome());
		c.setUfini(rem.getCdestado().getUf());
		c.setCmunfim(dest.getCdcidade().getIbge());
		c.setXmunfim(dest.getCdcidade().getNome());
		c.setUffim(dest.getCdestado().getUf());
		c.setInfadfisco("");
		c.setCpfcnpjemit(emp.getCpfcnpj());
		c.setCfop(ctecfg.getCfop());
		c.setNatop(ctecfg.getNatop());
		c.setCdctecfg(ctecfg);
		c.setStatus(1); // 1 - em edicao
		c.setInfcpl(ctecfg.getMsgcont());
		fsCteRp.save(c);
		mot(c, veic, mot);
		// XMLS AUTORIZADOS
		xmlsAutorizados(c);
		return c;
	}

	public FsCteInf inf(FsCte c) {
		FsCteInf i = new FsCteInf();
		i.setChcte_anu(null);
		i.setChcte_comp(null);
		i.setChcte_sub(null);
		i.setDemi_anu(null);
		i.setProdpred(null);
		i.setRefcte_sub(null);
		i.setRefcteanu_sub(null);
		i.setRefnfe_sub(null);
		i.setRntrc(null);
		i.setVcarga(new BigDecimal(0));
		fsCteInfRp.save(i);
		return i;
	}

	public FsCtePart part(CdPessoa pes, String tipo, Long id) {
		FsCtePart p = new FsCtePart();
		p.setId(id);
		p.setTipo(tipo);
		p.setCpfcnpj(pes.getCpfcnpj());
		p.setIe(pes.getIerg());
		p.setXnome(pes.getNome());
		p.setXfant(pes.getFantasia());
		p.setXlgr(pes.getRua());
		p.setNro(pes.getNum());
		p.setXcpl(pes.getComp());
		p.setXbairro(pes.getBairro());
		p.setCmun(pes.getCdcidade().getIbge());
		p.setXmun(pes.getCdcidade().getNome());
		p.setCpais(pes.getCodpais());
		p.setXpais("BRASIL");
		p.setCep(pes.getCep());
		p.setUf(pes.getCdestado().getUf());
		p.setFone(pes.getFoneum());
		p.setEmail(pes.getEmail());
		p.setIdpessoa(pes.getId());
		fsCtePartRp.save(p);
		return p;
	}

	public FsCteVprest vprest(FsCte c) {
		FsCteVprest i = new FsCteVprest();
		i.setVrec(new BigDecimal(0));
		i.setVtprest(new BigDecimal(0));
		fsCteVprestRp.save(i);
		return i;
	}
	
	public void mot(FsCte c, CdVeiculo veic, CdPessoa mot) {
		FsCteMot m = new FsCteMot();
		m.setFscte(c);
		m.setCdpessoamot(mot);
		m.setCdveiculo(veic);
		m.setNome(mot.getNome());
		m.setCpfcnpj(mot.getCpfcnpj());
		m.setV_placa(veic.getPlaca());
		m.setV_ufplaca(veic.getUf());
		m.setV_antt(veic.getAntt());
		fsCteMotRp.save(m);
	}

	private void xmlsAutorizados(FsCte c) {
		List<CdXmlAutoriza> xmlAuts = cdXmlAutorizaRp.findAllByTpdocAndLocalAutoAdd(c.getCdpessoaemp().getId(), "57",
				"S");
		for (CdXmlAutoriza xa : xmlAuts) {
			FsCteAut cteaut = new FsCteAut();
			cteaut.setFscte(c);
			cteaut.setCpfcnpj(xa.getCnpj());
			fsCteAutRp.save(cteaut);
		}
	}

	public FsCteIcms icms(FsCte c, CdCteCfg ctecfg) {
		FsCteIcms i = new FsCteIcms();
		i.setCst(ctecfg.getCst());
		i.setPicms(new BigDecimal(0));
		i.setPicmsstret(new BigDecimal(0));
		i.setPredbc(new BigDecimal(0));
		i.setVbc(new BigDecimal(0));
		i.setVbcstret(new BigDecimal(0));
		i.setVcred(new BigDecimal(0));
		i.setVicms(new BigDecimal(0));
		i.setVicmsstret(new BigDecimal(0));
		fsCteIcmsRp.save(i);
		return i;
	}
}