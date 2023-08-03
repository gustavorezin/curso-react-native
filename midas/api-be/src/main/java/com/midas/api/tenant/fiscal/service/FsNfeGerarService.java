package com.midas.api.tenant.fiscal.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.constant.MidasConfig;
import com.midas.api.security.ClienteParam;
import com.midas.api.tenant.entity.CdCfgFiscalSerie;
import com.midas.api.tenant.entity.CdNfeCfgSimples;
import com.midas.api.tenant.entity.CdXmlAutoriza;
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfeAut;
import com.midas.api.tenant.entity.FsNfeCobr;
import com.midas.api.tenant.entity.FsNfeCobrDup;
import com.midas.api.tenant.entity.FsNfeFrete;
import com.midas.api.tenant.entity.FsNfeFreteVol;
import com.midas.api.tenant.entity.FsNfeItem;
import com.midas.api.tenant.entity.FsNfeItemCofins;
import com.midas.api.tenant.entity.FsNfeItemIcms;
import com.midas.api.tenant.entity.FsNfeItemIpi;
import com.midas.api.tenant.entity.FsNfeItemPis;
import com.midas.api.tenant.entity.FsNfePag;
import com.midas.api.tenant.entity.FsNfePart;
import com.midas.api.tenant.entity.FsNfeRef;
import com.midas.api.tenant.entity.FsNfeTotIcms;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcDocItem;
import com.midas.api.tenant.entity.LcDocNfref;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.fiscal.util.GeraChavesUtil;
import com.midas.api.tenant.repository.CdCfgFiscalSerieRepository;
import com.midas.api.tenant.repository.CdNfeCfgSimplesRepository;
import com.midas.api.tenant.repository.CdXmlAutorizaRepository;
import com.midas.api.tenant.repository.FnCxmvRepository;
import com.midas.api.tenant.repository.FsNfeAutRepository;
import com.midas.api.tenant.repository.FsNfeCobrDupRepository;
import com.midas.api.tenant.repository.FsNfeCobrRepository;
import com.midas.api.tenant.repository.FsNfeFreteRepository;
import com.midas.api.tenant.repository.FsNfeFreteVolRepository;
import com.midas.api.tenant.repository.FsNfeItemCofinsRepository;
import com.midas.api.tenant.repository.FsNfeItemIcmsRepository;
import com.midas.api.tenant.repository.FsNfeItemIpiRepository;
import com.midas.api.tenant.repository.FsNfeItemPisRepository;
import com.midas.api.tenant.repository.FsNfeItemRepository;
import com.midas.api.tenant.repository.FsNfePagRepository;
import com.midas.api.tenant.repository.FsNfePartRepository;
import com.midas.api.tenant.repository.FsNfeRefRepository;
import com.midas.api.tenant.repository.FsNfeRepository;
import com.midas.api.tenant.repository.FsNfeTotIcmsRepository;
import com.midas.api.tenant.repository.LcDocItemRepository;
import com.midas.api.tenant.service.FnTituloService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.NumUtil;

@Service
public class FsNfeGerarService {
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private FsNfePartRepository fsNfePartRp;
	@Autowired
	private FsNfeItemRepository fsNfeItemRp;
	@Autowired
	private FsNfeItemIcmsRepository fsNfeItemIcmsRp;
	@Autowired
	private FsNfeItemPisRepository fsNfeItemPisRp;
	@Autowired
	private FsNfeItemCofinsRepository fsNfeItemCofinsRp;
	@Autowired
	private FsNfeItemIpiRepository fsNfeItemIpiRp;
	@Autowired
	private FsNfeCobrRepository fsNfeCobrRp;
	@Autowired
	private FsNfeCobrDupRepository fsNfeCobrDupRp;
	@Autowired
	private FsNfeFreteRepository fsNfeFreteRp;
	@Autowired
	private FsNfeFreteVolRepository fsNfeFreteVolRp;
	@Autowired
	private FsNfeTotIcmsRepository fsNfeTotIcmsRp;
	@Autowired
	private FsNfeAutRepository fsNfeAutRp;
	@Autowired
	private FsNfePagRepository nfePagRp;
	@Autowired
	private CdXmlAutorizaRepository cdXmlAutorizaRp;
	@Autowired
	private CdCfgFiscalSerieRepository cdCfgFiscalSerieRp;
	@Autowired
	private CdNfeCfgSimplesRepository cdNfeCfgSimplesRp;
	@Autowired
	private FnCxmvRepository fnCxmvRp;
	@Autowired
	private FsNfeRefRepository fsNfeRefRp;
	@Autowired
	private LcDocItemRepository lcDocItemRp;
	@Autowired
	private FnTituloService fnTituloService;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private MidasConfig mc;

	public FsNfe gerarNFe(LcDoc lcDoc, String modelo, String ambiente, String consfinal, String indpres,
			BigDecimal qvol, String especie, String marca, String nrvol, BigDecimal pesol, BigDecimal pesob)
			throws Exception {
		// BUSCA DADOS DO SIMPLES, MESMO SE NAO HOUVER
		CdNfeCfgSimples cdNfeCfgSimples = cdNfeCfgSimplesRp.getByCdpessoaemp(lcDoc.getCdpessoaemp().getId());
		// DADOS DA CHAVE------------------------------------
		Integer coduf = lcDoc.getCdpessoaemp().getCdestado().getId();
		String dataAAMM = DataUtil.anoMesAtualAAMM();
		String cpfcnpjemit = lcDoc.getCdpessoaemp().getCpfcnpj();
		CdCfgFiscalSerie cdSerie = cdCfgFiscalSerieRp.getByCdpessoaemp(lcDoc.getCdpessoaemp().getId(), modelo);
		Integer serie = cdSerie.getSerieatual();
		Integer tipoem = 1; // Emissao normal
		Integer iddoc = lcDoc.getId().intValue();
		FsNfe ultNfe = fsNfeRp.ultimaByIdAndCdpessoaempSerieModelo(lcDoc.getCdpessoaemp().getId(), serie,
				Integer.valueOf(modelo), ambiente, "E");
		Integer numAdd = 1;
		if (ultNfe != null) {
			numAdd = ultNfe.getNnf() + 1;
		}
		String chaveDeAcesso = GeraChavesUtil.geraChavePadrao(coduf, dataAAMM, cpfcnpjemit, Integer.valueOf(modelo),
				serie, tipoem, numAdd, iddoc, "NFe").replace("NFe", "");
		// DIGITO CHAVE
		String cDv = chaveDeAcesso.substring(43, 44);
		// IDENTIFICACAO##############################################################################
		FsNfe nf = new FsNfe();
		nf.setCdpessoaemp(lcDoc.getCdpessoaemp());
		nf.setTipo("E");
		nf.setCnf(lcDoc.getId());
		nf.setNatop(lcDoc.getCdnfecfg().getNatop());
		nf.setModelo(Integer.valueOf(modelo));
		nf.setSerie(serie);
		nf.setNnf(numAdd);
		nf.setDhemi(new Date());
		nf.setDhemihr(new Date());
		nf.setDhsaient(new Date());
		nf.setTpnf(Integer.valueOf(lcDoc.getCdnfecfg().getTipoop()));
		nf.setIddest(FsUtil.idDest(lcDoc));
		nf.setCmunfg(lcDoc.getCdpessoaemp().getCdcidade().getIbge());
		nf.setTpimp(FsUtil.tpImp(modelo));
		nf.setTpemis(Integer.valueOf(1));// 1 - Emissao normal
		nf.setChaveac(chaveDeAcesso);
		nf.setCdv(Integer.valueOf(cDv));
		nf.setTpamb(Integer.valueOf(ambiente));
		nf.setFinnfe(Integer.valueOf(lcDoc.getCdnfecfg().getFinnfe()));
		nf.setIndfinal(Integer.valueOf(consfinal));
		nf.setIndpres(Integer.valueOf(indpres));
		nf.setProcemi(0);
		nf.setVerproc(mc.MidasVersao);
		nf.setCpfcnpjemit(lcDoc.getCdpessoaemp().getCpfcnpj());
		nf.setXml("");
		// NFC-e 65
		if (modelo.equals("65")) {
			nf.setInfadfisco("DAV NUMERO " + lcDoc.getNumero());
		} else {
			nf.setInfadfisco("");
		}
		nf.setInfcpl(FsUtil.montaInfoAddNFe(lcDoc, cdNfeCfgSimples, prm.cliente().getSiscfg()));
		// Compra---------
		nf.setXnemp(FsUtil.cpN(lcDoc.getCnemp()));
		nf.setXped(FsUtil.cpN(lcDoc.getCped()));
		nf.setXcont(FsUtil.cpN(lcDoc.getCcont()));
		// Exportacao-----
		nf.setUfsaidapais(FsUtil.cpN(lcDoc.getExufsaida()));
		nf.setXlocexporta(FsUtil.cpN(lcDoc.getExlocalemb()));
		nf.setXlocdespacho(FsUtil.cpN(lcDoc.getExlocaldesp()));
		// Documento-------
		nf.setLcdoc(lcDoc.getId());
		nf.setTpdoc(lcDoc.getTipo());
		nf.setNumdoc(lcDoc.getNumero());
		nf.setCdnfecfg(lcDoc.getCdnfecfg());
		// IBPT-------------
		nf.setVibpt_nacional(lcDoc.getVibpt_nacional());
		nf.setVibpt_importado(lcDoc.getVibpt_importado());
		nf.setVibpt_estadual(lcDoc.getVibpt_estadual());
		nf.setVibpt_municipal(lcDoc.getVibpt_municipal());
		nf.setStatus(1);// 1 - Em edicao
		// EMITENTE#####################################################################################
		FsNfePart emit = new FsNfePart();
		emit.setTipo("E");
		emit.setCpfcnpj(lcDoc.getCdpessoaemp().getCpfcnpj());
		emit.setIe(lcDoc.getCdpessoaemp().getIerg());
		emit.setXnome(lcDoc.getCdpessoaemp().getNome());
		emit.setXfant(lcDoc.getCdpessoaemp().getFantasia());
		emit.setXlgr(lcDoc.getCdpessoaemp().getRua());
		emit.setNro(lcDoc.getCdpessoaemp().getNum());
		emit.setXcpl(lcDoc.getCdpessoaemp().getComp());
		emit.setXbairro(lcDoc.getCdpessoaemp().getBairro());
		emit.setCmun(lcDoc.getCdpessoaemp().getCdcidade().getIbge());
		emit.setXmun(lcDoc.getCdpessoaemp().getCdcidade().getNome());
		emit.setUf(lcDoc.getCdpessoaemp().getCdestado().getUf());
		emit.setCep(lcDoc.getCdpessoaemp().getCep());
		emit.setCpais("1058");
		emit.setXpais("BRASIL");
		emit.setFone(lcDoc.getCdpessoaemp().getFoneum());
		emit.setIest(lcDoc.getCdnfecfg().getIest());
		emit.setIm(FsUtil.cpN(lcDoc.getCdpessoaemp().getIm()));
		emit.setCnae(FsUtil.cpN(lcDoc.getCdpessoaemp().getCnae()));
		emit.setCrt(String.valueOf(lcDoc.getCdpessoaemp().getCrt()));
		fsNfePartRp.save(emit);
		nf.setSt_fornec("S");
		nf.setSt_cob("S");
		nf.setSt_est("S");
		nf.setSt_custos("S");
		nf.setFsnfepartemit(emit);
		// DESTINATARIO#####################################################################################
		FsNfePart dest = new FsNfePart();
		dest.setTipo("D");
		dest.setCpfcnpj(lcDoc.getCdpessoapara().getCpfcnpj());
		dest.setIe(lcDoc.getCdpessoapara().getIerg());
		dest.setXnome(lcDoc.getCdpessoapara().getNome());
		dest.setXfant(lcDoc.getCdpessoapara().getFantasia());
		dest.setXlgr(lcDoc.getCdpessoapara().getRua());
		dest.setNro(lcDoc.getCdpessoapara().getNum());
		dest.setXcpl(lcDoc.getCdpessoapara().getComp());
		dest.setXbairro(lcDoc.getCdpessoapara().getBairro());
		dest.setCmun(lcDoc.getCdpessoapara().getCdcidade().getIbge());
		dest.setXmun(lcDoc.getCdpessoapara().getCdcidade().getNome());
		dest.setUfcod(lcDoc.getCdpessoapara().getCdcidade().getCdestado().getId());
		dest.setUf(lcDoc.getCdpessoapara().getCdestado().getUf());
		dest.setCep(lcDoc.getCdpessoapara().getCep());
		dest.setCpais(lcDoc.getCdpessoapara().getCodpais());
		dest.setXpais("BRASIL");
		// Verifica exportacao-------------------------------
		if (!lcDoc.getCdpessoapara().getCodpais().equals("1058")) {
			dest.setCpfcnpj("");
			dest.setIe("");
			dest.setIdestrangeiro(lcDoc.getCdpessoapara().getIdestrangeiro());
			dest.setCep("");
			dest.setXpais("EXTERIOR");
		}
		dest.setCrt(String.valueOf(lcDoc.getCdpessoapara().getCrt()));
		dest.setFone(FsUtil.cpND(lcDoc.getCdpessoapara().getFonefat(), lcDoc.getCdpessoapara().getFoneum()));
		dest.setIm(FsUtil.cpN(lcDoc.getCdpessoapara().getIm()));
		dest.setIndiedest(lcDoc.getCdpessoapara().getIndiedest());
		dest.setIsuf(FsUtil.cpN(lcDoc.getCdpessoapara().getIsuf()));
		dest.setEmail(FsUtil.cpND(lcDoc.getCdpessoapara().getEmailfat(), lcDoc.getCdpessoapara().getEmail()));
		fsNfePartRp.save(dest);
		nf.setFsnfepartdest(dest);
		// TRANSPORTADORA#####################################################################################
		FsNfePart transp = new FsNfePart();
		nf.setFsnfeparttransp(transp);
		transp.setTipo("T");
		transp.setCpfcnpj(lcDoc.getCdpessoatransp().getCpfcnpj());
		transp.setIe(lcDoc.getCdpessoatransp().getIerg());
		transp.setXnome(lcDoc.getCdpessoatransp().getNome());
		transp.setXlgr(lcDoc.getCdpessoatransp().getRua());
		transp.setXmun(lcDoc.getCdpessoatransp().getCdcidade().getNome());
		transp.setUf(lcDoc.getCdpessoatransp().getCdestado().getUf());
		fsNfePartRp.save(transp);
		nf.setFsnfeparttransp(transp);
		// LOCAL-ENTREGA##################################################################################
		FsNfePart entr = new FsNfePart();
		// Define local de entrega como Destinatario se for igual Cliente
		nf.setFsnfepartent(dest);
		if (!lcDoc.getCdpessoapara().equals(lcDoc.getCdpessoaentrega())) {
			entr.setTipo("L");
			entr.setCpfcnpj(lcDoc.getCdpessoaentrega().getCpfcnpj());
			entr.setXnome(lcDoc.getCdpessoaentrega().getNome());
			entr.setXlgr(lcDoc.getCdpessoaentrega().getRua());
			entr.setNro(lcDoc.getCdpessoaentrega().getNum());
			entr.setXcpl(lcDoc.getCdpessoaentrega().getComp());
			entr.setXbairro(lcDoc.getCdpessoaentrega().getBairro());
			entr.setCmun(lcDoc.getCdpessoaentrega().getCdcidade().getIbge());
			entr.setXmun(lcDoc.getCdpessoaentrega().getCdcidade().getNome());
			entr.setUf(lcDoc.getCdpessoaentrega().getCdestado().getUf());
			entr.setCep(lcDoc.getCdpessoaentrega().getCep());
			entr.setCpais(lcDoc.getCdpessoaentrega().getCodpais());
			entr.setFone(FsUtil.cpND(lcDoc.getCdpessoaentrega().getFonefat(), lcDoc.getCdpessoaentrega().getFoneum()));
			entr.setEmail(FsUtil.cpND(lcDoc.getCdpessoaentrega().getEmailfat(), lcDoc.getCdpessoaentrega().getEmail()));
			entr.setIe(lcDoc.getCdpessoaentrega().getIerg());
			fsNfePartRp.save(entr);
			nf.setFsnfepartent(entr);
		}
		// FRETE#################################################################################
		FsNfeFrete fr = new FsNfeFrete();
		fr.setModfrete(lcDoc.getModfrete());
		fr.setC_placa(lcDoc.getVcplaca());
		fr.setC_rntc(lcDoc.getVcantt());
		fr.setC_uf(lcDoc.getVcuf());
		fsNfeFreteRp.save(fr);
		// VOLUMES
		FsNfeFreteVol v = new FsNfeFreteVol();
		v.setFsnfefrete(fr);
		if (qvol.compareTo(new BigDecimal(1)) < 0) {
			v.setQvol(new BigDecimal(1));
		} else {
			v.setQvol(qvol.setScale(2, RoundingMode.HALF_UP));
		}
		v.setEsp(CaracterUtil.remUpper(especie));
		v.setMarca(CaracterUtil.remUpper(marca));
		v.setNvol(CaracterUtil.remUpper(nrvol));
		v.setPesol(pesol.setScale(3, RoundingMode.HALF_UP));
		v.setPesob(pesob.setScale(3, RoundingMode.HALF_UP));
		fsNfeFreteVolRp.save(v);
		fr.getFsnfefretevols().add(v);
		nf.setFsnfefrete(fr);
		// DUP-COBRANCA##########################################################################
		// Verifica se tem servicos e ignora tbm
		Integer qtdServ = lcDocItemRp.verificaPossuiItemTipoServico(lcDoc.getId());
		if (FsUtil.fPagCob(lcDoc) == false && qtdServ == 0) {
			FsNfeCobr cb = new FsNfeCobr();
			BigDecimal vlrOrig = lcDoc.getVtottrib().add(lcDoc.getVdesc());
			cb.setNfat(lcDoc.getNumero().toString());
			cb.setVorig(FsUtil.aBD2(vlrOrig));
			cb.setVdesc(FsUtil.aBD2(lcDoc.getVdesc()));
			cb.setVliq(FsUtil.aBD2(lcDoc.getVtottrib()));
			fsNfeCobrRp.save(cb);
			int numparc = 1;
			for (FnTitulo td : lcDoc.getLcdoctitulo()) {
				if (td.getTipo().equals("R")) {
					String parcela = NumUtil.geraNumEsq(numparc, 3);
					FsNfeCobrDup d = new FsNfeCobrDup();
					d.setFsnfecobr(cb);
					d.setNdup(parcela);
					d.setDvenc(td.getVence());
					d.setVdup(td.getVtot());
					fsNfeCobrDupRp.save(d);
					cb.getFsnfecobrdups().add(d);
					numparc++;
				}
			}
			nf.setFsnfecobr(cb);
		} else {
			FsNfeCobr cb = new FsNfeCobr();
			BigDecimal vlr = new BigDecimal(0);
			cb.setNfat("");
			cb.setVorig(vlr);
			cb.setVdesc(vlr);
			cb.setVliq(vlr);
			fsNfeCobrRp.save(cb);
			nf.setFsnfecobr(cb);
		}
		// TOTAIS
		// #############################################################################################
		// Verifica se servicos para ajustar Total
		BigDecimal vPROD = lcDoc.getVsub();
		BigDecimal vNF = lcDoc.getVtottrib();
		if (qtdServ > 0) {
			BigDecimal vServs = BigDecimal.ZERO;
			for (LcDocItem it : lcDoc.getLcdocitem()) {
				if (!it.getCdproduto().getTipo().equals("PRODUTO")) {
					vServs = vServs.add(it.getVtottrib());
				}
			}
			vPROD = vPROD.subtract(vServs);
			vNF = vNF.subtract(vServs);
		}
		FsNfeTotIcms to = new FsNfeTotIcms();
		to.setVbc(lcDoc.getVbcicms());
		to.setVicms(lcDoc.getVicms());
		to.setVbcst(lcDoc.getVbcicmsst());
		to.setVst(lcDoc.getVicmsst());
		to.setVprod(FsUtil.aBD2(vPROD));
		to.setVfrete(lcDoc.getVtransp());
		to.setVseg(new BigDecimal(0));
		BigDecimal vDescs = lcDoc.getVdesc().add(lcDoc.getVdescext());
		to.setVdesc(FsUtil.aBD2(vDescs));
		to.setVii(new BigDecimal(0));
		to.setVipi(lcDoc.getVipi());
		to.setVpis(lcDoc.getVpis());
		to.setVcofins(lcDoc.getVcofins());
		// Outros - por enquanto apenas deslocamento
		BigDecimal vOutro = lcDoc.getVdesloca();
		to.setVoutro(vOutro);
		to.setVdescext(lcDoc.getVdescext());
		to.setVnf(FsUtil.aBD2(vNF));
		to.setVtottrib(FsUtil.aBD2(lcDoc.getVtribcob()));
		to.setVicmsdeson(new BigDecimal(0));
		to.setVicmsufdest(lcDoc.getVicmsufdest());
		to.setVicmsufremet(lcDoc.getVicmsufremet());
		to.setVfcpufdest(lcDoc.getVfcpufdest());
		to.setVfcp(new BigDecimal(0));
		to.setVfcpst(lcDoc.getVfcpst());
		to.setVfcpstret(new BigDecimal(0));
		to.setVipidevol(new BigDecimal(0));
		nf.setFsnfetoticms(to);
		fsNfeTotIcmsRp.save(to);
		nf.setFsnfetoticms(to);
		// SALVA TUDO DO FS_NFE
		FsNfe nota = fsNfeRp.save(nf);
		// INFORMA NFE PARA TOTAIS
		to.setFsnfe_id(nota.getId());
		fsNfeTotIcmsRp.save(to);
		// PAGAMENTOS####################################################################################
		if (!lcDoc.getFpag().equals("90") && !lcDoc.getFpag().equals("99") && qtdServ == 0) {
			if (lcDoc.getLcdoctitulo().size() > 0) {
				for (FnTitulo td : lcDoc.getLcdoctitulo()) {
					// NF-e 55
					if (td.getTipo().equals("R") && modelo.equals("55")) {
						FsNfePag nfep = new FsNfePag();
						nfep.setTpag(lcDoc.getFpag());
						nfep.setVpag(td.getVtot());
						nfep.setFsnfe(nf);
						nfePagRp.save(nfep);
						nota.getFsnfepags().add(nfep);
					}
					// NF-e 55
					if (td.getTipo().equals("P") && td.getComissao().equals("N") && modelo.equals("55")) {
						FsNfePag nfep = new FsNfePag();
						nfep.setTpag(lcDoc.getFpag());
						nfep.setVpag(td.getVtot());
						nfep.setFsnfe(nf);
						nfePagRp.save(nfep);
						nota.getFsnfepags().add(nfep);
					}
					// NFC-e 65
					if (td.getTipo().equals("R") && modelo.equals("65")) {
						// Se foi pago tudo
						if (td.getVsaldo().compareTo(BigDecimal.ZERO) == 0) {
							List<FnCxmv> cxs = fnCxmvRp.findByFntituloBaixa(td.getId());
							/// Registra pelos pagamentos do caixa
							for (FnCxmv c : cxs) {
								FsNfePag nfep = new FsNfePag();
								nfep.setTpag(c.getFpag());
								nfep.setVpag(c.getVtot());
								nfep.setFsnfe(nf);
								nfePagRp.save(nfep);
								nota.getFsnfepags().add(nfep);
							}
						} else {
							FsNfePag nfep = new FsNfePag();
							nfep.setTpag(lcDoc.getFpag());
							nfep.setVpag(td.getVtot());
							nfep.setFsnfe(nf);
							nfePagRp.save(nfep);
							nota.getFsnfepags().add(nfep);
						}
					}
				}
			} else {
				FsNfePag nfep = new FsNfePag();
				nfep.setTpag("90");
				nfep.setVpag(new BigDecimal(0));
				nfep.setFsnfe(nf);
				nfePagRp.save(nfep);
				nota.getFsnfepags().add(nfep);
			}
		} else {
			FsNfePag nfep = new FsNfePag();
			nfep.setTpag("90");
			nfep.setVpag(new BigDecimal(0));
			nfep.setFsnfe(nf);
			nfePagRp.save(nfep);
			nota.getFsnfepags().add(nfep);
		}
		// AUTORIZADOS####################################################################################
		List<CdXmlAutoriza> xmlAuts = cdXmlAutorizaRp.findAllByTpdocAndLocalAutoAdd(lcDoc.getCdpessoaemp().getId(),
				modelo, "S");
		for (CdXmlAutoriza xa : xmlAuts) {
			FsNfeAut nfeaut = new FsNfeAut();
			nfeaut.setFsnfe(nf);
			nfeaut.setCpfcnpj(xa.getCnpj());
			fsNfeAutRp.save(nfeaut);
			nota.getFsnfeauts().add(nfeaut);
		}
		// NF-REFERENCIADA################################################################################
		List<LcDocNfref> nfRefs = lcDoc.getLcdocnfref();
		for (LcDocNfref xa : nfRefs) {
			FsNfeRef nr = new FsNfeRef();
			nr.setFsnfe(nf);
			nr.setRefnfe(xa.getRefnf());
			fsNfeRefRp.save(nr);
			nota.getFsnferefs().add(nr);
		}
		// ITENS###############################################################################################
		int numitem = 1;
		// Rateio desconto extra itens---------------------------
		BigDecimal vDescExtra = new BigDecimal(0);
		if (lcDoc.getVdescext().compareTo(BigDecimal.ZERO) > 0) {
			vDescExtra = lcDoc.getVdescext().divide(new BigDecimal(lcDoc.getLcdocitem().size()), 2,
					RoundingMode.HALF_UP);
			vDescExtra = vDescExtra.setScale(2, RoundingMode.HALF_UP);
		}
		// Rateio valor outros itens - apenas deslocamento por enquanto
		// ---------------------
		BigDecimal vOutros = new BigDecimal(0);
		if (lcDoc.getVdesloca().compareTo(BigDecimal.ZERO) > 0) {
			vOutros = lcDoc.getVdesloca().divide(new BigDecimal(lcDoc.getLcdocitem().size())).setScale(2,
					RoundingMode.HALF_UP);
			vOutros = vOutros.setScale(2, RoundingMode.HALF_UP);
		}
		for (LcDocItem it : lcDoc.getLcdocitem()) {
			if (it.getCdproduto().getTipo().equals("PRODUTO")) {
				FsNfeItem ni = new FsNfeItem();
				ni.setFsnfe(nf);
				ni.setCprod(it.getCdproduto().getCodigo().toString());
				ni.setCean(it.getCdproduto().getCean());
				String desc = it.getDescricao();
				String ref = "";
				int lr = 0;
				String ns = it.getNs();
				int lns = 0;
				StringBuilder xprod = new StringBuilder(desc);
				boolean impref = prm.cliente().getSiscfg().isSis_impref_nf();
				if(impref) {
					ref = it.getRef();
					if (ref != null) {
						if (!ref.equals("")) {
							ref = " REF.: " + ref;
							xprod.append(ref);
							lr = ref.length();
						}
					}
				}
				if (ns != null) {
					if (!ns.equals("")) {
						ns = " NS.: " + ns;
						xprod.append(ns);
						lns = ns.length();
					}
				}
				if (xprod.length() > 120) {
					int tamanhoDescMax = desc.length() - ((xprod.length() + lr + lns) - 117);
					desc = desc.substring(0, tamanhoDescMax).trim();
					xprod = new StringBuilder(desc).append("...").append(ref).append(ns);
				}
				String nomeprod = xprod.toString().replace("null", "");
				ni.setXprod(nomeprod);
				ni.setNcm(it.getCdproduto().getNcm());
				ni.setCest(it.getCdproduto().getCest());
				ni.setIndescala(it.getCdproduto().getIndescala());
				ni.setCnpjfab(it.getCdproduto().getCnpjfab());
				if (lcDoc.getUsacfgfiscal().equals("S")) {
					ni.setCfop(lcDoc.getCdnfecfg().getCfop());
				} else {
					ni.setCfop(it.getCdnfecfg().getCfop());
				}
				ni.setUcom(it.getCdproduto().getCdprodutounmed().getSigla());
				ni.setQcom(it.getQtd());
				ni.setVuncom(it.getVuni());
				ni.setVprod(it.getVsub());
				ni.setCeantrib(it.getCdproduto().getCeantrib());
				ni.setUtrib(it.getCdproduto().getCdprodutounmedtrib().getSigla());
				ni.setQtrib(it.getQtdtrib());
				ni.setVuntrib(it.getVunitrib());
				ni.setVfrete(it.getVfrete());
				ni.setVseg(new BigDecimal(0));
				if (numitem == 1) {
					BigDecimal vPrimDesc = fnTituloService.vlrPrimParc(lcDoc.getVdescext(), vDescExtra,
							lcDoc.getLcdocitem().size());
					ni.setVdesc(it.getVdesc().add(vPrimDesc));
					ni.setVdescext(vPrimDesc);
				} else {
					ni.setVdesc(it.getVdesc().add(vDescExtra));
					ni.setVdescext(vDescExtra);
				}
				ni.setVoutro(vOutros);
				ni.setVtot(it.getVtot());// Apenas sub e descontos, sem tributos
				ni.setVtottrib(it.getVtribcob());
				ni.setIndtot(1);
				ni.setXped(lcDoc.getNumero().toString());
				ni.setNitemped(String.valueOf(numitem));
				ni.setCdnfecfg(it.getCdnfecfg());
				// Para combustiveis - os 3 campos sao obrigatorios pelo manual
				if (it.getCdproduto().getCodanp() != null) {
					String codanp = it.getCdproduto().getCodanp();
					if (!codanp.equals("")) {
						ni.setCprodanp(codanp);
						ni.setDescanp(FsUtil.cpND(it.getCdproduto().getDescanp(), it.getDescricao()));
						ni.setUfcons(lcDoc.getCdpessoapara().getCdestado().getUf());
					}
				}
				ni.setCbenef(it.getCdproduto().getCbenef());
				ni.setVibpt_nacional(it.getVibpt_nacional());
				ni.setVibpt_importado(it.getVibpt_importado());
				ni.setVibpt_estadual(it.getVibpt_estadual());
				ni.setVibpt_municipal(it.getVibpt_municipal());
				// TRIBUTOS ICMS----------------------------------------------------------
				FsNfeItemIcms ic = new FsNfeItemIcms();
				ic.setFsnfe_id(nota.getId());
				ic.setOrig(it.getCdproduto().getOrigem());
				if (lcDoc.getUsacfgfiscal().equals("S")) {
					ic.setCst(lcDoc.getCdnfecfg().getCst());
				} else {
					ic.setCst(it.getCdnfecfg().getCst());
				}
				ic.setModbc(Integer.valueOf(lcDoc.getCdnfecfg().getIcms_modbc()));
				ic.setPredbc(lcDoc.getCdnfecfg().getIcms_redbc());
				ic.setVbc(it.getVbcicms());
				ic.setPicms(it.getPicms());
				ic.setVicms(it.getVicms());
				ic.setModbcst(Integer.valueOf(lcDoc.getCdnfecfg().getIcmsst_modbc()));
				ic.setPmvast(lcDoc.getCdnfecfg().getIcmsst_mva());
				ic.setPredbcst(lcDoc.getCdnfecfg().getIcmsst_redbc());
				ic.setVbcst(it.getVbcicmsst());
				ic.setPicmsst(it.getPicmsst());
				ic.setVicmsst(it.getVicmsst());
				ic.setVbcstret(new BigDecimal(0));
				ic.setVicmsstret(new BigDecimal(0));
				ic.setVbcstdest(new BigDecimal(0));
				ic.setVicmsstdest(new BigDecimal(0));
				ic.setMotdesicms(0);
				ic.setPbcop(new BigDecimal(0));
				ic.setUfst("");
				ic.setPcredsn(it.getPcredsn());
				ic.setVcredicmssn(it.getVcredsn());
				ic.setVicmsdeson(new BigDecimal(0));
				ic.setVicmsop(new BigDecimal(0));
				ic.setPdif(lcDoc.getCdnfecfg().getPicmsufdest());
				ic.setVicmsdif(it.getVicmsufdest());
				ic.setVicmsdifremet(it.getVicmsufremet());
				ic.setVbcfcp(new BigDecimal(0));
				ic.setPfcp(new BigDecimal(0));
				ic.setVfcp(new BigDecimal(0));
				ic.setVbcfcpst(it.getVbcfcpst());
				ic.setPfcpst(it.getPfcpst());
				ic.setVfcpst(it.getVfcpst());
				ic.setVbcfcpstret(new BigDecimal(0));
				ic.setPfcpstret(new BigDecimal(0));
				ic.setVfcpstret(new BigDecimal(0));
				ic.setPst(new BigDecimal(0));
				ic.setPfcpdif(lcDoc.getCdnfecfg().getPfcpufdest());
				ic.setVfcpdif(it.getVfcpufdest());
				ic.setVfcpefet(new BigDecimal(0));
				ic.setVicmsstdeson(new BigDecimal(0));
				ic.setMotdesicmsst(0);
				fsNfeItemIcmsRp.save(ic);
				ni.setFsnfeitemicms(ic);
				// PIS---------------------------------------------------------------------
				FsNfeItemPis pi = new FsNfeItemPis();
				pi.setFsnfe_id(nota.getId());
				pi.setCst(lcDoc.getCdnfecfg().getPis());
				pi.setVbc(it.getVbcpis());
				pi.setPpis(it.getPpis());
				pi.setVpis(it.getVpis());
				pi.setQbcprod(new BigDecimal(0));
				pi.setValiqprod(new BigDecimal(0));
				// PI ST
				pi.setVbcst(it.getVbcpisst());
				pi.setPpisst(it.getPpisst());
				pi.setVpisst(it.getVpisst());
				pi.setQbcprodst(new BigDecimal(0));
				pi.setValiqprodst(new BigDecimal(0));
				pi.setIndsomapisst(1);
				fsNfeItemPisRp.save(pi);
				ni.setFsnfeitempis(pi);
				// COFINS-------------------------------------------------------------------
				FsNfeItemCofins co = new FsNfeItemCofins();
				co.setFsnfe_id(nota.getId());
				co.setCst(lcDoc.getCdnfecfg().getCofins());
				co.setVbc(it.getVbccofins());
				co.setPcofins(it.getPcofins());
				co.setVcofins(it.getVcofins());
				co.setQbcprod(new BigDecimal(0));
				co.setValiqprod(new BigDecimal(0));
				// COFINS ST
				co.setVbcst(it.getVbccofinsst());
				co.setPcofinsst(it.getPcofinsst());
				co.setVcofinsst(it.getVcofinsst());
				co.setQbcprodst(new BigDecimal(0));
				co.setValiqprodst(new BigDecimal(0));
				co.setIndsomacofinsst(1);
				fsNfeItemCofinsRp.save(co);
				ni.setFsnfeitemcofins(co);
				// IPI----------------------------------------------------------------------
				FsNfeItemIpi ip = new FsNfeItemIpi();
				ip.setFsnfe_id(nota.getId());
				ip.setCnpjprod("");
				ip.setCselo("");
				ip.setQselo(new BigDecimal(0));
				ip.setCenq(lcDoc.getCdnfecfg().getIpi_classeenq());
				ip.setCst(lcDoc.getCdnfecfg().getIpi());
				ip.setVbc(it.getVbcipi());
				ip.setPipi(it.getPipi());
				ip.setVipi(it.getVipi());
				ip.setQunid(new BigDecimal(0));
				ip.setVunid(new BigDecimal(0));
				fsNfeItemIpiRp.save(ip);
				ni.setFsnfeitemipi(ip);
				// SALVA ITEM
				fsNfeItemRp.save(ni);
				nota.getFsnfeitems().add(ni);
				numitem++;
			}
		}
		return nota;
	}
}