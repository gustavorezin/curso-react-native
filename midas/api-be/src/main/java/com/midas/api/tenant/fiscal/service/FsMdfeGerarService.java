package com.midas.api.tenant.fiscal.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midas.api.constant.MidasConfig;
import com.midas.api.tenant.entity.CdCfgFiscalSerie;
import com.midas.api.tenant.entity.CdCidade;
import com.midas.api.tenant.entity.CdEstado;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdVeiculo;
import com.midas.api.tenant.entity.CdXmlAutoriza;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsCteInfCarga;
import com.midas.api.tenant.entity.FsCteInfDocEmi;
import com.midas.api.tenant.entity.FsCtePart;
import com.midas.api.tenant.entity.FsMdfe;
import com.midas.api.tenant.entity.FsMdfeAut;
import com.midas.api.tenant.entity.FsMdfeContr;
import com.midas.api.tenant.entity.FsMdfeDoc;
import com.midas.api.tenant.entity.FsMdfePart;
import com.midas.api.tenant.entity.FsMdfePerc;
import com.midas.api.tenant.entity.FsMdfeReboq;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfeFreteVol;
import com.midas.api.tenant.fiscal.util.GeraChavesUtil;
import com.midas.api.tenant.repository.CdCfgFiscalSerieRepository;
import com.midas.api.tenant.repository.CdCidadeRepository;
import com.midas.api.tenant.repository.CdEstadoRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdVeiculoRepository;
import com.midas.api.tenant.repository.CdXmlAutorizaRepository;
import com.midas.api.tenant.repository.FsMdfeAutRepository;
import com.midas.api.tenant.repository.FsMdfeContrRepository;
import com.midas.api.tenant.repository.FsMdfeDocRepository;
import com.midas.api.tenant.repository.FsMdfePartRepository;
import com.midas.api.tenant.repository.FsMdfePercRepository;
import com.midas.api.tenant.repository.FsMdfeReboqRepository;
import com.midas.api.tenant.repository.FsMdfeRepository;
import com.midas.api.util.DataUtil;
import com.midas.api.util.NumUtil;

@Service
public class FsMdfeGerarService {
	@Autowired
	private FsMdfeRepository fsMdfeRp;;
	@Autowired
	private FsMdfeDocRepository fsMdfeDocRp;
	@Autowired
	private FsMdfePartRepository fsMdfePartRp;
	@Autowired
	private FsMdfePercRepository fsMdfePercRp;
	@Autowired
	private FsMdfeReboqRepository fsMdfeReboqRp;
	@Autowired
	private FsMdfeAutRepository fsMdfeAutRp;
	@Autowired
	private FsMdfeContrRepository fsMdfeContrRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdVeiculoRepository cdVeiculoRp;
	@Autowired
	private CdEstadoRepository cdEstadoRp;
	@Autowired
	private CdCidadeRepository cdCidadeRp;
	@Autowired
	private CdXmlAutorizaRepository cdXmlAutorizaRp;
	@Autowired
	private CdCfgFiscalSerieRepository cdCfgFiscalSerieRp;
	@Autowired
	private MidasConfig mc;

	@Transactional("tenantTransactionManager")
	public FsMdfe gerarMdfe(String ambiente, List<FsNfe> nfes, List<FsCte> ctes, Long cdpessoaemp, Long cdpessoamot,
			Long cdpessoaseg, Integer tpemit, Integer cdveiculo, Integer cdestado) throws Exception {
		CdPessoa emp = cdPessoaRp.findById(cdpessoaemp).get();
		CdPessoa mot = cdPessoaRp.findById(cdpessoamot).get();
		CdPessoa seg = cdpessoaseg != null ? cdPessoaRp.findById(cdpessoaseg).get() : emp;
		CdVeiculo veiculo = cdVeiculoRp.findById(cdveiculo).get();
		CdEstado uffim = cdEstadoRp.findById(cdestado).get();
		// DADOS DA CHAVE------------------------------------
		CdCfgFiscalSerie cdSerie = cdCfgFiscalSerieRp.getByCdpessoaemp(emp.getId(), "58");
		String dataAAMM = DataUtil.anoMesAtualAAMM();
		Integer serie = cdSerie.getSerieatual();
		FsMdfe ultMdfe = fsMdfeRp.ultimaByIdAndCdpessoaempSerieModelo(emp.getId(), serie, ambiente, "E");
		Integer numAdd = 1;
		if (ultMdfe != null) {
			numAdd = ultMdfe.getNmdf() + 1;
		}
		String chaveac = GeraChavesUtil.geraChavePadrao(emp.getCdestado().getId(), dataAAMM, emp.getCpfcnpj(), 58,
				serie, 1, numAdd, numAdd, "MDFe").replace("MDFe", "");
		// DIGITO CHAVE--------------------------------------
		String cDv = chaveac.substring(43, 44);
		FsMdfe m = new FsMdfe();
		m.setCdpessoaemp(emp);
		m.setTipo("E");
		m.setCuf(emp.getCdestado().getId());
		m.setTpamb(Integer.valueOf(ambiente));
		m.setTpemit(tpemit);
		m.setTptransp(Integer.valueOf(emp.getTptransp()));
		m.setSerie(serie);
		m.setNmdf(numAdd);
		m.setCmdf(numAdd);
		m.setCdv(Integer.valueOf(cDv));
		m.setChaveac(chaveac);
		m.setModal("1");
		m.setTpemis(1);
		m.setProcemi(0);
		m.setVerproc(mc.MidasVersao);
		m.setCdcidadeini(emp.getCdcidade());
		m.setCdestadoini(emp.getCdestado());
		m.setCdestadofim(uffim);
		m.setQcte(ctes == null ? 0 : ctes.size());
		m.setQnfe(nfes == null ? 0 : nfes.size());
		m.setQmdfe(0);
		m.setVcarga(totais(nfes, ctes)[0]);
		m.setCunid(null); // COD UN MEDIDA | 01-KG / 02-TON
		m.setQcarga(totais(nfes, ctes)[1]);
		m.setInfadfisco("");
		m.setInfcpl("");
		m.setCdpessoaseg(seg);
		m.setCdveiculo(veiculo);
		m.setV_placa(veiculo.getPlaca());
		m.setV_ufplaca(veiculo.getUf());
		m.setV_antt(veiculo.getAntt());
		m.setV_tara(veiculo.getTara());
		m.setStatus(1);
		fsMdfeRp.save(m);
		// DOCS
		if (nfes != null) {
			gerarDocsNfe(nfes, m);
		} else {
			gerarDocsCte(ctes, m);
			// CONTRATANTES
			gerarContratantesCte(ctes, m);
		}
		// EMITENTE
		part(emp, "E", m);
		// MOTORISTA
		part(mot, "M", m);
		// XMLS AUTORIZADOS
		xmlsAutorizados(m);
		return m;
	}

	// ATUALIZA MDFE **********************
	public void atualizaFsMdfeAdicionais(Long id) throws Exception {
		FsMdfe m = fsMdfeRp.getById(id);
		m.setV_placa(m.getCdveiculo().getPlaca());
		m.setV_ufplaca(m.getCdveiculo().getUf());
		m.setV_antt(m.getCdveiculo().getAntt());
		m.setV_tara(m.getCdveiculo().getTara());
		fsMdfeRp.save(m);
	}

	public FsMdfePart part(CdPessoa pes, String tipo, FsMdfe m) {
		FsMdfePart p = new FsMdfePart();
		p.setFsmdfe(m);
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
		p.setCep(pes.getCep());
		p.setUf(pes.getCdestado().getUf());
		p.setFone(pes.getFoneum());
		p.setEmail(pes.getEmail());
		fsMdfePartRp.save(p);
		return p;
	}

	public FsMdfePerc perc(CdEstado es, FsMdfe m) {
		FsMdfePerc p = new FsMdfePerc();
		p.setFsmdfe(m);
		p.setCdestado(es);
		fsMdfePercRp.save(p);
		return p;
	}

	public FsMdfeReboq reboq(CdVeiculo v, FsMdfe m) {
		FsMdfeReboq r = new FsMdfeReboq();
		r.setFsmdfe(m);
		r.setCdveiculo(v);
		r.setPlaca(v.getPlaca());
		r.setUfplaca(v.getUf());
		r.setAntt(v.getAntt());
		fsMdfeReboqRp.save(r);
		return r;
	}

	private void gerarDocsNfe(List<FsNfe> nfes, FsMdfe m) {
		List<FsMdfeDoc> docs = nfes.stream().map(n -> {
			CdCidade cidade = cdCidadeRp.findByIbge(n.getFsnfepartdest().getCmun());
			FsMdfeDoc d = new FsMdfeDoc();
			d.setFsmdfe(m);
			d.setCdcidade(cidade);
			d.setChave(n.getChaveac());
			d.setValor(n.getFsnfetoticms().getVnf());
			BigDecimal peso = BigDecimal.ZERO;
			for (FsNfeFreteVol v : n.getFsnfefrete().getFsnfefretevols()) {
				peso = peso.add(v.getPesob());
			}
			d.setPeso(peso.setScale(4, RoundingMode.HALF_EVEN));
			d.setTipo("N");
			return d;
		}).collect(Collectors.toList());
		fsMdfeDocRp.saveAll(docs);
	}

	private void gerarDocsCte(List<FsCte> ctes, FsMdfe m) {
		List<FsMdfeDoc> docs = ctes.stream().map(c -> {
			CdCidade cidade = cdCidadeRp.findByIbge(c.getFsctepartdest().getCmun());
			FsMdfeDoc d = new FsMdfeDoc();
			d.setFsmdfe(m);
			d.setCdcidade(cidade);
			d.setChave(c.getChaveac());
			d.setValor(c.getFscteinf().getVcarga());
			BigDecimal peso = BigDecimal.ZERO;
			for (FsCteInfCarga v : c.getFscteinf().getFscteinfcarga()) {
				if (v.getTpmed().equals("TON")) {
					peso = NumUtil.paraKg(v.getQcarga(), BigDecimal.ONE, v.getTpmed());
				} else {
					peso = v.getQcarga();
				}
			}
			d.setPeso(peso.setScale(4, RoundingMode.HALF_EVEN));
			d.setTipo("C");
			return d;
		}).collect(Collectors.toList());
		fsMdfeDocRp.saveAll(docs);
	}

	private void gerarContratantesCte(List<FsCte> ctes, FsMdfe m) {
		Set<String> cpfCnpjs = new HashSet<>();
		List<FsMdfeContr> contratantes = new ArrayList<>();
		for (FsCte cte : ctes) {
			FsMdfeContr contratante = new FsMdfeContr();
			int toma = cte.getTomador();
			FsCtePart part = null;
			FsCteInfDocEmi docant = null;
			switch (toma) {
			case 1:
				part = cte.getFsctepartexp();
				break;
			case 2:
				part = cte.getFsctepartrec();
				break;
			case 3:
				part = cte.getFsctepartdest();
				break;
			case 4:
				docant = cte.getFscteinf().getFscteinfdocemi().get(0);
				break;
			default:
				part = cte.getFsctepartrem();
				break;
			}
			String cpfcnpj = toma != 4 ? part.getCpfcnpj() : docant.getCpfcnpj();
			boolean adicionado = cpfCnpjs.add(cpfcnpj);
			if (adicionado) {
				contratante.setFsmdfe(m);
				String nome = toma != 4 ? part.getXnome() : docant.getXnome();
				contratante.setNome(nome);
				contratante.setCpfcnpj(cpfcnpj);
				contratante.setIdest(null);
				contratantes.add(contratante);
			}
		}
		fsMdfeContrRp.saveAll(contratantes);
	}

	private BigDecimal[] totais(List<FsNfe> nfes, List<FsCte> ctes) {
		BigDecimal vcargatot = BigDecimal.ZERO;
		BigDecimal qcargatot = BigDecimal.ZERO;
		if (nfes != null) {
			for (FsNfe n : nfes) {
				vcargatot = vcargatot.add(n.getFsnfetoticms().getVnf());
				for (FsNfeFreteVol v : n.getFsnfefrete().getFsnfefretevols()) {
					qcargatot = qcargatot.add(v.getPesob());
				}
			}
		}
		if (ctes != null) {
			for (FsCte c : ctes) {
				vcargatot = vcargatot.add(c.getFscteinf().getVcarga());
				for (FsCteInfCarga v : c.getFscteinf().getFscteinfcarga()) {
					qcargatot = qcargatot.add(v.getQcarga());
				}
			}
		}
		BigDecimal[] retorno = new BigDecimal[2];
		retorno[0] = vcargatot;
		retorno[1] = qcargatot;
		return retorno;
	}

	private void xmlsAutorizados(FsMdfe m) {
		List<CdXmlAutoriza> xmlAuts = cdXmlAutorizaRp.findAllByTpdocAndLocalAutoAdd(m.getCdpessoaemp().getId(), "58",
				"S");
		for (CdXmlAutoriza xa : xmlAuts) {
			FsMdfeAut mdfeaut = new FsMdfeAut();
			mdfeaut.setFsmdfe(m);
			mdfeaut.setCpfcnpj(xa.getCnpj());
			fsMdfeAutRp.save(mdfeaut);
		}
	}
}