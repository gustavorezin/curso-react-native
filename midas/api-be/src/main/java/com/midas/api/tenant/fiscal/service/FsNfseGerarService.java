package com.midas.api.tenant.fiscal.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdCfgFiscalSerie;
import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FsNfse;
import com.midas.api.tenant.entity.FsNfseCobr;
import com.midas.api.tenant.entity.FsNfseCobrDup;
import com.midas.api.tenant.entity.FsNfseItem;
import com.midas.api.tenant.entity.FsNfseItemTrib;
import com.midas.api.tenant.entity.FsNfsePart;
import com.midas.api.tenant.entity.FsNfseTot;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcDocItem;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.repository.CdCfgFiscalSerieRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.FsNfseCobrDupRepository;
import com.midas.api.tenant.repository.FsNfseCobrRepository;
import com.midas.api.tenant.repository.FsNfseItemRepository;
import com.midas.api.tenant.repository.FsNfseItemTribRepository;
import com.midas.api.tenant.repository.FsNfsePartRepository;
import com.midas.api.tenant.repository.FsNfseRepository;
import com.midas.api.tenant.repository.FsNfseTotRepository;
import com.midas.api.tenant.repository.LcDocItemRepository;
import com.midas.api.util.NumUtil;

@Service
public class FsNfseGerarService {
	@Autowired
	private FsNfseRepository fsNfseRp;
	@Autowired
	private FsNfsePartRepository fsNfsePartRp;
	@Autowired
	private FsNfseItemRepository fsNfseItemRp;
	@Autowired
	private FsNfseItemTribRepository fsNfseItemTribRp;
	@Autowired
	private FsNfseCobrRepository fsNfseCobrRp;
	@Autowired
	private FsNfseCobrDupRepository fsNfseCobrDupRp;
	@Autowired
	private FsNfseTotRepository fsNfseTotRp;
	@Autowired
	private CdCfgFiscalSerieRepository cdCfgFiscalSerieRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private LcDocItemRepository lcDocItemRp;

	public FsNfse gerarNFSe(LcDoc lcDoc, String ambiente, String localprestserv, String operacao, String fpag)
			throws Exception {
		CdPessoa emp = cdPessoaRp.findById(lcDoc.getCdpessoaemp().getId()).get();
		CdPessoa tom = cdPessoaRp.findById(lcDoc.getCdpessoapara().getId()).get();
		// BUSCA DADOS DO SIMPLES, MESMO SE NAO HOUVER
		CdCfgFiscalSerie cdSerie = cdCfgFiscalSerieRp.getByCdpessoaemp(emp.getId(), "99");
		Integer serie = cdSerie.getSerieatual();
		FsNfse ultNfse = fsNfseRp.ultimaByIdAndCdpessoaempSerie(emp.getId(), serie, ambiente, "E");
		CdNfeCfg cdnfecfg = lcDoc.getCdnfecfg();
		Integer numAdd = 1;
		if (ultNfse != null) {
			numAdd = ultNfse.getRpsnumero() + 1;
		}
		// IDENTIFICACAO###########################################################################
		FsNfse nfs = new FsNfse();
		nfs.setCdpessoaemp(lcDoc.getCdpessoaemp());
		nfs.setTipo("E");
		nfs.setRpsnumero(numAdd);
		nfs.setRpsserie(String.valueOf(serie));
		nfs.setRpstipo(1); // 1 - Recibo Provisório de Serviços
		nfs.setNnfs(0); // gera no envio da nota
		nfs.setDemis(new Date());
		nfs.setDemishora(new Date());
		nfs.setDcompetencia(new Date());
		nfs.setLocal("1"); // 1 - Na empresa
		nfs.setLocalprestserv(localprestserv);
		nfs.setNatop(cdnfecfg.getNatop_nfse());
		nfs.setOperacao(operacao);
		nfs.setNumprocesso("");
		nfs.setRegesptrib(cdnfecfg.getRegesptrib_nfse());
		nfs.setOptsn(cdnfecfg.getOptsn_nfse());
		nfs.setInccult(cdnfecfg.getInccult_nfse());
		nfs.setTpamb(Integer.valueOf(ambiente));
		nfs.setFpag(fpag);
		nfs.setDiscriminacao(cdnfecfg.getDescatvprin_nfse());
		nfs.setSt_fornec("S");
		nfs.setSt_cob("S");
		nfs.setTpdoc(lcDoc.getTipo());
		nfs.setLcdoc(lcDoc.getId());
		nfs.setNumdoc(lcDoc.getNumero());
		nfs.setCdnfecfg(cdnfecfg);
		nfs.setOutrasinfo(FsUtil.montaInfoAddNFSe(lcDoc));
		nfs.setXml("");
		nfs.setStatusrps("1"); // 1 - Normal
		nfs.setStatus(1); // 1 - Em edicao
		nfs.setVerproc("1.00");
		nfs.setFsnfsepartprest(part(emp, "P"));
		nfs.setFsnfseparttoma(part(tom, "T"));
		nfs.setFsnfsepartlocal(part(emp, "L"));
		FsNfse nota = fsNfseRp.save(nfs);
		// ITENS
		itens(nota, lcDoc);
		// TOTAIS
		total(nota, lcDoc);
		dupCobranca(nota, lcDoc);
		return nota;
	}

	public FsNfsePart part(CdPessoa pes, String tipo) {
		FsNfsePart p = new FsNfsePart();
		int indiceVirgula = pes.getEmail().indexOf(","); // se tiver virgula traz o indice dela, se nao tiver = -1
		String email = indiceVirgula > 0 ? pes.getEmail().substring(0, indiceVirgula) : pes.getEmail();
		p.setTipo(tipo);
		p.setCpfcnpj(pes.getCpfcnpj());
		p.setIe(pes.getIerg());
		p.setXnome(pes.getNome());
		p.setXfant(pes.getFantasia());
		p.setTipoend("PRINCIPAL");
		p.setXlgr(pes.getRua());
		p.setNro(pes.getNum());
		p.setXcpl(pes.getComp());
		p.setXbairro(pes.getBairro());
		p.setCmun(pes.getCdcidade().getIbge());
		p.setXmun(pes.getCdcidade().getNome());
		p.setUf(pes.getCdestado().getUf());
		p.setUfcod(pes.getCdestado().getId());
		p.setCep(pes.getCep());
		p.setCpais(pes.getCodpais());
		p.setXpais("BRASIL");
		p.setFone(pes.getFoneum());
		p.setTipofone("CO"); // CO - COMERCIAL
		p.setIm(pes.getIm());
		p.setStespecial("0"); // 0 - OUTRO
		p.setRegesptrib("0"); // 0 - NAO
		p.setCadmun("4"); // 4 - CONSUMIDOR FINAL
		p.setOrgaopublico("3"); // 3 - NAO
		p.setEmail(email);
		fsNfsePartRp.save(p);
		return p;
	}

	public void itens(FsNfse nfse, LcDoc lcDoc) {
		int nseq = 1;
		CdNfeCfg cdNfeCfg = lcDoc.getCdnfecfg();
		for (LcDocItem it : lcDoc.getLcdocitem()) {
			if (it.getCdproduto().getTipo().equals("SERVIÇO")) {
				// TODO VER DESCONTO EXTRA (TEM ESTILO PRONTO FsNfeGerarService)
				FsNfseItem item = new FsNfseItem();
				item.setFsnfse(nfse);
				item.setNseq(nseq);
				item.setCprod(it.getCdproduto().getCodigo().toString());
				String desc = it.getDescricao();
				String ref = it.getRef();
				StringBuilder xprod = new StringBuilder(desc);
				if (ref != null && !ref.equals("")) {
					ref = " REF.:" + ref;
					xprod.append(ref);
					if (xprod.length() > 120) {
						int tamanhoDescMax = desc.length() - (xprod.length() - 117); // 117 pois tem mais os "..." que
																						// sao
						// adicionados
						desc = desc.substring(0, tamanhoDescMax).trim();
						xprod = new StringBuilder(desc).append("...").append(ref);
					}
				}
				item.setXprod(xprod.toString());
				item.setCodserv(it.getCdproduto().getCodserv());
				item.setUnmed(it.getCdproduto().getCdprodutounmed().getSigla());
				item.setQtd(it.getQtd().setScale(4, RoundingMode.HALF_EVEN));
				item.setVunit(it.getVuni().setScale(4, RoundingMode.HALF_EVEN));
				item.setVded(BigDecimal.ZERO);
				item.setTributavel("S");
				item.setVsub(it.getVsub().setScale(4, RoundingMode.HALF_EVEN));
				item.setVdesc(it.getVdesc().setScale(4, RoundingMode.HALF_EVEN));
				item.setVtot(it.getVtottrib().setScale(4, RoundingMode.HALF_EVEN));
				item.setIssretido(null);
				item.setRespretencao(1);
				item.setDedtp("M");
				// TRIBUTOS ----------------------------------------------------------
				FsNfseItemTrib itemTrib = new FsNfseItemTrib();
				// calculos
				BigDecimal vbc = it.getVbciss();
				BigDecimal vissret = vbc.multiply(cdNfeCfg.getIssret_aliq_nfse()).divide(new BigDecimal(100));
				BigDecimal vinss = vbc.multiply(cdNfeCfg.getInss_aliq_nfse()).divide(new BigDecimal(100));
				BigDecimal vir = vbc.multiply(cdNfeCfg.getIr_aliq_nfse()).divide(new BigDecimal(100));
				BigDecimal vcsll = vbc.multiply(cdNfeCfg.getCsll_aliq_nfse()).divide(new BigDecimal(100));
				BigDecimal vliquido = vbc.subtract(vissret).subtract(vinss).subtract(it.getVcofins())
						.subtract(it.getVpis()).subtract(vcsll);
				itemTrib.setValiqiss(it.getPiss());
				itemTrib.setValiqissret(cdNfeCfg.getIssret_aliq_nfse());
				itemTrib.setVbc(FsUtil.arredondaBigDec(vbc, 4));
				itemTrib.setVbcretido(FsUtil.arredondaBigDec(vbc, 4));
				itemTrib.setVredbcretido(BigDecimal.ZERO);
				itemTrib.setViss(FsUtil.arredondaBigDec(it.getViss(), 4));
				itemTrib.setVissretido(FsUtil.arredondaBigDec(vissret, 4));
				itemTrib.setValiqinss(cdNfeCfg.getInss_aliq_nfse());
				itemTrib.setVinss(FsUtil.arredondaBigDec(vinss, 4));
				itemTrib.setValiqir(cdNfeCfg.getIr_aliq_nfse());
				itemTrib.setVir(FsUtil.arredondaBigDec(vir, 4));
				itemTrib.setValiqcofins(it.getPcofins());
				itemTrib.setVcofins(FsUtil.arredondaBigDec(it.getVcofins(), 4));
				itemTrib.setValiqcsll(cdNfeCfg.getCsll_aliq_nfse());
				itemTrib.setVcsll(FsUtil.arredondaBigDec(vcsll, 4));
				itemTrib.setValiqpis(it.getPpis());
				itemTrib.setVpis(FsUtil.arredondaBigDec(it.getVpis(), 4));
				itemTrib.setVliquido(FsUtil.arredondaBigDec(vliquido, 4));
				fsNfseItemTribRp.save(itemTrib);
				item.setFsnfseitemtrib(itemTrib);
				fsNfseItemRp.save(item);
				nfse.getFsnfseitems().add(item);
				nseq++;
			}
		}
		nfse.setSerquantidade(BigDecimal.valueOf(nseq));
	}

	public void total(FsNfse nfse, LcDoc lcDoc) {
		BigDecimal vservicos = BigDecimal.ZERO;
		BigDecimal vliquido = BigDecimal.ZERO;
		for (FsNfseItem it : nfse.getFsnfseitems()) {
			vservicos = vservicos.add(it.getVtot());
			vliquido = vliquido.add(it.getFsnfseitemtrib().getVliquido());
		}
		;
		FsNfseTot tot = new FsNfseTot();
		tot.setFsnfse_id(nfse.getId());
		tot.setVservicos(vservicos);
		tot.setVliquido(vliquido);
		tot.setVdeducoes(BigDecimal.ZERO);
		tot.setVpis(lcDoc.getVpis());
		tot.setVcofins(lcDoc.getVcofins());
		tot.setVir(BigDecimal.ZERO);
		tot.setVcsll(BigDecimal.ZERO);
		tot.setViss(lcDoc.getViss());
		tot.setVissretido(BigDecimal.ZERO);
		tot.setVbc(FsUtil.aBD2(lcDoc.getVbciss()));
		fsNfseTotRp.save(tot);
		nfse.setFsnfsetot(tot);
	}

	public void dupCobranca(FsNfse nfse, LcDoc lcDoc) throws Exception {
		// Verifica se nao possui servicos e usa parcela DOC
		Integer qtdProd = lcDocItemRp.verificaPossuiItemTipo(lcDoc.getId());
		if (FsUtil.fPagCob(lcDoc) == false && qtdProd == 0) {
			FsNfseCobr nfseCobr = new FsNfseCobr();
			BigDecimal vlrOrig = lcDoc.getVtottrib().add(lcDoc.getVdesc());
			nfseCobr.setNfat(lcDoc.getNumero().toString());
			nfseCobr.setVorig(FsUtil.aBD2(vlrOrig));
			nfseCobr.setVdesc(FsUtil.aBD2(lcDoc.getVdesc()));
			nfseCobr.setVliq(FsUtil.aBD2(lcDoc.getVtottrib()));
			fsNfseCobrRp.save(nfseCobr);
			// Se tiver algum produto na OS - ignora parcela
			/*
			 * FsNfseCobrDup d = new FsNfseCobrDup(); d.setFsnfsecobr(nfseCobr);
			 * d.setNdup("001"); d.setDvenc(nfse.getDemis());
			 * d.setVdesc(nfseCobr.getVdesc()); d.setVdup(nfseCobr.getVliq());
			 * fsNfseCobrDupRp.save(d); nfseCobr.getFsnfsecobrdups().add(d);
			 */
			// Usa parcelas normais do DOC
			int conta = 1;
			String contador = "";
			for (FnTitulo t : lcDoc.getLcdoctitulo()) {
				contador = NumUtil.geraNumEsq(conta, 3);
				FsNfseCobrDup d = new FsNfseCobrDup();
				d.setFsnfsecobr(nfseCobr);
				d.setNdup(contador);
				d.setDvenc(t.getVence());
				d.setVdesc(t.getVdesc());
				d.setVdup(t.getVtot());
				fsNfseCobrDupRp.save(d);
				nfseCobr.getFsnfsecobrdups().add(d);
				conta++;
			}
			nfse.setFsnfsecobr(nfseCobr);
		} else {
			FsNfseCobr nfseCobr = new FsNfseCobr();
			BigDecimal vlr = BigDecimal.ZERO;
			nfseCobr.setNfat("0");
			nfseCobr.setVorig(vlr);
			nfseCobr.setVdesc(vlr);
			nfseCobr.setVliq(vlr);
			fsNfseCobrRp.save(nfseCobr);
			nfse.setFsnfsecobr(nfseCobr);
		}
	}
}