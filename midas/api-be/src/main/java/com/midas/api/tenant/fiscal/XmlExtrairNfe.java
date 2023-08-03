package com.midas.api.tenant.fiscal;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.midas.api.tenant.entity.CdPessoa;
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
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.repository.CdPessoaRepository;
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
import com.midas.api.util.DataUtil;

import br.inf.portalfiscal.nfe.TNfeProc;

@Component
public class XmlExtrairNfe {
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private FsNfeRefRepository fsNfeRefRp;
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
	private CdPessoaRepository cdPessoaRp;

	@Transactional("tenantTransactionManager")
	public void ExtraiNFe(String xml, Optional<CdPessoa> cdpessoaemp, TNfeProc nfe) throws Exception {
		if (nfe != null) {
			// IDENTIFICACAO##############################################################################
			FsNfe nf = new FsNfe();
			nf.setCdpessoaemp(cdpessoaemp.get());
			nf.setTipo("R");
			nf.setCnf(Long.valueOf(nfe.getNFe().getInfNFe().getIde().getCNF()));
			nf.setNatop(nfe.getNFe().getInfNFe().getIde().getNatOp());
			nf.setModelo(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getMod()));
			nf.setSerie(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getSerie()));
			nf.setNnf(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getNNF()));
			nf.setDhemi(DataUtil.dUtil(nfe.getNFe().getInfNFe().getIde().getDhEmi()));
			nf.setDhemihr(DataUtil.hUtil(nfe.getNFe().getInfNFe().getIde().getDhEmi().substring(11, 19)));
			nf.setDhsaient(DataUtil.dUtil(nfe.getNFe().getInfNFe().getIde().getDhSaiEnt()));
			nf.setTpnf(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getTpNF()));
			nf.setIddest(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getIdDest()));
			nf.setCmunfg(nfe.getNFe().getInfNFe().getIde().getCMunFG());
			nf.setTpimp(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getTpImp()));
			nf.setTpemis(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getTpEmis()));
			nf.setChaveac(nfe.getProtNFe().getInfProt().getChNFe());
			nf.setCdv(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getCDV()));
			nf.setTpamb(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getTpAmb()));
			nf.setFinnfe(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getFinNFe()));
			nf.setIndfinal(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getIndFinal()));
			nf.setIndpres(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getIndPres()));
			nf.setProcemi(Integer.valueOf(nfe.getNFe().getInfNFe().getIde().getProcEmi()));
			nf.setVerproc(nfe.getNFe().getInfNFe().getIde().getVerProc());
			// Cpf ou Cnpj
			if (nfe.getNFe().getInfNFe().getEmit().getCPF() != null) {
				nf.setCpfcnpjemit(nfe.getNFe().getInfNFe().getEmit().getCPF());
			}
			if (nfe.getNFe().getInfNFe().getEmit().getCNPJ() != null) {
				nf.setCpfcnpjemit(nfe.getNFe().getInfNFe().getEmit().getCNPJ());
			}
			nf.setXml(xml);
			if (nfe.getNFe().getInfNFe().getInfAdic() != null) {
				nf.setInfadfisco(FsUtil.cpN(nfe.getNFe().getInfNFe().getInfAdic().getInfAdFisco()));
				nf.setInfcpl(FsUtil.cpN(nfe.getNFe().getInfNFe().getInfAdic().getInfCpl()));
			}
			// Compra--------
			if (nfe.getNFe().getInfNFe().getCompra() != null) {
				nf.setXnemp(FsUtil.cpN(nfe.getNFe().getInfNFe().getCompra().getXNEmp()));
				nf.setXped(FsUtil.cpN(nfe.getNFe().getInfNFe().getCompra().getXPed()));
				nf.setXcont(FsUtil.cpN(nfe.getNFe().getInfNFe().getCompra().getXCont()));
			}
			// Exportacao-----
			if (nfe.getNFe().getInfNFe().getExporta() != null) {
				nf.setUfsaidapais(FsUtil.cpN(String.valueOf(nfe.getNFe().getInfNFe().getExporta().getUFSaidaPais())));
				nf.setXlocexporta(FsUtil.cpN(nfe.getNFe().getInfNFe().getExporta().getXLocExporta()));
				nf.setXlocdespacho(FsUtil.cpN(nfe.getNFe().getInfNFe().getExporta().getXLocDespacho()));
			}
			nf.setCdnfecfg(null);
			nf.setStatus(Integer.valueOf(nfe.getProtNFe().getInfProt().getCStat()));
			// EMITENTE#####################################################################################
			FsNfePart emit = new FsNfePart();
			String cfpcnpjemit = "";
			emit.setTipo("E");
			// Cpf ou Cnpj
			if (nfe.getNFe().getInfNFe().getEmit().getCPF() != null) {
				emit.setCpfcnpj(nfe.getNFe().getInfNFe().getEmit().getCPF());
				cfpcnpjemit = nfe.getNFe().getInfNFe().getEmit().getCPF();
			}
			if (nfe.getNFe().getInfNFe().getEmit().getCNPJ() != null) {
				emit.setCpfcnpj(nfe.getNFe().getInfNFe().getEmit().getCNPJ());
				cfpcnpjemit = nfe.getNFe().getInfNFe().getEmit().getCNPJ();
			}
			emit.setIe(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getIE()));
			emit.setXnome(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getXNome()));
			emit.setXfant(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getXFant()));
			emit.setXlgr(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getEnderEmit().getXLgr()));
			emit.setNro(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getEnderEmit().getNro()));
			emit.setXcpl(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getEnderEmit().getXCpl()));
			emit.setXbairro(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getEnderEmit().getXBairro()));
			emit.setCmun(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getEnderEmit().getCMun()));
			emit.setXmun(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getEnderEmit().getXMun()));
			emit.setUf(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getEnderEmit().getUF().toString()));
			emit.setCep(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getEnderEmit().getCEP()));
			emit.setCpais(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getEnderEmit().getCPais()));
			emit.setXpais(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getEnderEmit().getXPais()));
			emit.setFone(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getEnderEmit().getFone()));
			emit.setIest(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getIEST()));
			emit.setIm(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getIM()));
			emit.setCnae(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getCNAE()));
			emit.setCrt(FsUtil.cpN(nfe.getNFe().getInfNFe().getEmit().getCRT()));
			fsNfePartRp.save(emit);
			// Marca conferido
			CdPessoa para = cdPessoaRp.findFirstByCpfcnpj(cfpcnpjemit);
			nf.setSt_fornec("N");
			nf.setSt_cob("N");
			nf.setSt_est("N");
			nf.setSt_custos("N");
			if (para != null) {
				nf.setSt_fornec("S");
			}
			nf.setFsnfepartemit(emit);
			// DESTINATARIO#####################################################################################
			FsNfePart dest = new FsNfePart();
			dest.setTipo("D");
			// Cpf ou Cnpj
			if (nfe.getNFe().getInfNFe().getDest().getCPF() != null) {
				dest.setCpfcnpj(nfe.getNFe().getInfNFe().getDest().getCPF());
			}
			if (nfe.getNFe().getInfNFe().getEmit().getCNPJ() != null) {
				dest.setCpfcnpj(nfe.getNFe().getInfNFe().getDest().getCNPJ());
			}
			dest.setIe(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getIE()));
			dest.setXnome(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getXNome()));
			dest.setXfant(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getXNome()));
			dest.setXlgr(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEnderDest().getXLgr()));
			dest.setNro(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEnderDest().getNro()));
			dest.setXcpl(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEnderDest().getXCpl()));
			dest.setXbairro(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEnderDest().getXBairro()));
			dest.setCmun(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEnderDest().getCMun()));
			dest.setXmun(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEnderDest().getXMun()));
			dest.setUf(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEnderDest().getUF().toString()));
			dest.setCep(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEnderDest().getCEP()));
			dest.setCpais(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEnderDest().getCPais()));
			dest.setXpais(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEnderDest().getXPais()));
			dest.setFone(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEnderDest().getFone()));
			dest.setIm(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getIM()));
			dest.setIdestrangeiro(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getIdEstrangeiro()));
			dest.setIndiedest(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getIndIEDest()));
			dest.setIsuf(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getISUF()));
			dest.setEmail(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getEmail()));
			fsNfePartRp.save(dest);
			nf.setFsnfepartdest(dest);
			// TRANSPORTADORA#####################################################################################
			FsNfePart transp = new FsNfePart();
			// Define transporte como Emitente, se houver, muda para o correto abaixo
			nf.setFsnfeparttransp(emit);
			if (nfe.getNFe().getInfNFe().getTransp().getTransporta() != null) {
				nf.setFsnfeparttransp(transp);
				transp.setTipo("T");
				// Cpf ou Cnpj
				if (nfe.getNFe().getInfNFe().getDest().getCPF() != null) {
					transp.setCpfcnpj(nfe.getNFe().getInfNFe().getTransp().getTransporta().getCPF());
				}
				if (nfe.getNFe().getInfNFe().getEmit().getCNPJ() != null) {
					transp.setCpfcnpj(nfe.getNFe().getInfNFe().getTransp().getTransporta().getCNPJ());
				}
				transp.setIe(FsUtil.cpN(nfe.getNFe().getInfNFe().getDest().getIE()));
				transp.setXnome(FsUtil.cpN(nfe.getNFe().getInfNFe().getTransp().getTransporta().getXNome()));
				transp.setXlgr(FsUtil.cpN(nfe.getNFe().getInfNFe().getTransp().getTransporta().getXEnder()));
				transp.setXmun(FsUtil.cpN(nfe.getNFe().getInfNFe().getTransp().getTransporta().getXMun()));
				transp.setUf(FsUtil.cpN(String.valueOf(nfe.getNFe().getInfNFe().getTransp().getTransporta().getUF())));
				fsNfePartRp.save(transp);
				nf.setFsnfeparttransp(transp);
			}
			// LOCAL-ENTREGA##################################################################################
			FsNfePart entr = new FsNfePart();
			// Define local de entrega como Emitente, se houver, muda para o correto abaixo
			nf.setFsnfepartent(emit);
			if (nfe.getNFe().getInfNFe().getEntrega() != null) {
				entr.setTipo("L");
				if (nfe.getNFe().getInfNFe().getEntrega().getCNPJ() != null) {
					entr.setCpfcnpj(nfe.getNFe().getInfNFe().getEntrega().getCNPJ());
				}
				if (nfe.getNFe().getInfNFe().getEntrega().getCPF() != null) {
					entr.setCpfcnpj(nfe.getNFe().getInfNFe().getEntrega().getCPF());
				}
				entr.setXnome(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getXNome()));
				entr.setXlgr(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getXLgr()));
				entr.setNro(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getNro()));
				entr.setXcpl(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getXCpl()));
				entr.setXbairro(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getXBairro()));
				entr.setCmun(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getCMun()));
				entr.setXmun(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getXMun()));
				entr.setUf(FsUtil.cpN(String.valueOf(nfe.getNFe().getInfNFe().getEntrega().getUF())));
				entr.setCep(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getCEP()));
				entr.setCpais(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getCPais()));
				entr.setXpais(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getXPais()));
				entr.setFone(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getFone()));
				entr.setEmail(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getEmail()));
				entr.setIe(FsUtil.cpN(nfe.getNFe().getInfNFe().getEntrega().getIE()));
				fsNfePartRp.save(entr);
				nf.setFsnfepartent(entr);
			}
			// FRETE#################################################################################
			FsNfeFrete fr = new FsNfeFrete();
			if (nfe.getNFe().getInfNFe().getTransp() != null) {
				fr.setModfrete(FsUtil.cpN(nfe.getNFe().getInfNFe().getTransp().getModFrete()));
			}
			// VEICULO TRANSPORTE
			if (nfe.getNFe().getInfNFe().getTransp().getVeicTransp() != null) {
				fr.setC_placa(FsUtil.cpN(nfe.getNFe().getInfNFe().getTransp().getVeicTransp().getPlaca()));
				fr.setC_uf(FsUtil.cpN(String.valueOf(nfe.getNFe().getInfNFe().getTransp().getVeicTransp().getUF())));
				fr.setC_rntc(FsUtil.cpN(nfe.getNFe().getInfNFe().getTransp().getVeicTransp().getRNTC()));
			}
			fsNfeFreteRp.save(fr);
			// VOLUMES
			int volsize = nfe.getNFe().getInfNFe().getTransp().getVol().size();
			for (int i = 0; i < volsize; i++) {
				FsNfeFreteVol v = new FsNfeFreteVol();
				v.setFsnfefrete(fr);
				v.setQvol(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTransp().getVol().get(i).getQVol()));
				v.setEsp(FsUtil.cpN(nfe.getNFe().getInfNFe().getTransp().getVol().get(i).getEsp()));
				v.setMarca(FsUtil.cpN(nfe.getNFe().getInfNFe().getTransp().getVol().get(i).getMarca()));
				v.setNvol(FsUtil.cpN(nfe.getNFe().getInfNFe().getTransp().getVol().get(i).getNVol()));
				v.setPesol(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTransp().getVol().get(i).getPesoL()));
				v.setPesob(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTransp().getVol().get(i).getPesoB()));
				fsNfeFreteVolRp.save(v);
			}
			nf.setFsnfefrete(fr);
			// DUP-COBRANCA##########################################################################
			FsNfeCobr cb = new FsNfeCobr();
			if (nfe.getNFe().getInfNFe().getCobr() != null) {
				if (nfe.getNFe().getInfNFe().getCobr().getFat() != null) {
					cb.setNfat(FsUtil.cpN(nfe.getNFe().getInfNFe().getCobr().getFat().getNFat()));
					cb.setVorig(FsUtil.cpBD(nfe.getNFe().getInfNFe().getCobr().getFat().getVOrig()));
					cb.setVdesc(FsUtil.cpBD(nfe.getNFe().getInfNFe().getCobr().getFat().getVDesc()));
					cb.setVliq(FsUtil.cpBD(nfe.getNFe().getInfNFe().getCobr().getFat().getVLiq()));
				}
			}
			fsNfeCobrRp.save(cb);
			nf.setFsnfecobr(cb);
			if (nfe.getNFe().getInfNFe().getCobr() != null) {
				int dupsize = nfe.getNFe().getInfNFe().getCobr().getDup().size();
				for (int i = 0; i < dupsize; i++) {
					FsNfeCobrDup d = new FsNfeCobrDup();
					d.setFsnfecobr(cb);
					d.setNdup(FsUtil.cpN(nfe.getNFe().getInfNFe().getCobr().getDup().get(i).getNDup()));
					d.setDvenc(FsUtil.cpDT(nfe.getNFe().getInfNFe().getCobr().getDup().get(i).getDVenc()));
					d.setVdup(FsUtil.cpBD(nfe.getNFe().getInfNFe().getCobr().getDup().get(i).getVDup()));
					fsNfeCobrDupRp.save(d);
				}
				// Marca conferida cobranca
				if (dupsize == 0) {
					nf.setSt_cob("S");
				}
			} else {
				nf.setSt_cob("S");
			}
			// TOTAIS
			// #############################################################################################
			FsNfeTotIcms to = new FsNfeTotIcms();
			to.setVbc(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVBC()));
			to.setVicms(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVICMS()));
			to.setVbcst(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVBCST()));
			to.setVst(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVST()));
			to.setVprod(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVProd()));
			to.setVfrete(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVFrete()));
			to.setVseg(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVSeg()));
			to.setVdesc(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVDesc()));
			to.setVii(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVII()));
			to.setVipi(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVIPI()));
			to.setVpis(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVPIS()));
			to.setVcofins(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVCOFINS()));
			to.setVoutro(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVOutro()));
			to.setVnf(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVNF()));
			to.setVtottrib(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVTotTrib()));
			to.setVicmsdeson(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVICMSDeson()));
			to.setVicmsufdest(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVICMSUFDest()));
			to.setVicmsufremet(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVICMSUFRemet()));
			to.setVfcpufdest(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVFCPUFDest()));
			to.setVfcp(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVFCP()));
			to.setVfcpst(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVFCPST()));
			to.setVfcpstret(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVFCPSTRet()));
			to.setVipidevol(FsUtil.cpBD(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVIPIDevol()));
			nf.setFsnfetoticms(to);
			fsNfeTotIcmsRp.save(to);
			nf.setFsnfetoticms(to);
			// SALVA TUDO DO FS_NFE
			FsNfe nota = fsNfeRp.save(nf);
			// NOTAS-REFERENTES####################################################################################
			int rfsize = nfe.getNFe().getInfNFe().getIde().getNFref().size();
			for (int rf = 0; rf < rfsize; rf++) {
				String chaveac = FsUtil.cpN(nfe.getNFe().getInfNFe().getIde().getNFref().get(rf).getRefNFe());
				if(!chaveac.equals("")) {
					FsNfeRef nferef = new FsNfeRef();
					nferef.setFsnfe(nf);
					nferef.setRefnfe(chaveac);
					fsNfeRefRp.save(nferef);					
				}
			}
			// PAGAMENTOS####################################################################################
			if (nfe.getNFe().getInfNFe().getPag() != null) {
				if (nfe.getNFe().getInfNFe().getPag().getDetPag() != null) {
					int pagsize = nfe.getNFe().getInfNFe().getPag().getDetPag().size();
					for (int ps = 0; ps < pagsize; ps++) {
						FsNfePag nfep = new FsNfePag();
						nfep.setTpag(FsUtil.cpN(nfe.getNFe().getInfNFe().getPag().getDetPag().get(ps).getTPag()));
						nfep.setVpag(FsUtil.cpBD(nfe.getNFe().getInfNFe().getPag().getDetPag().get(ps).getVPag()));
						nfep.setFsnfe(nf);
						nfePagRp.save(nfep);
					}
				}
			}
			// AUTORIZADOS####################################################################################
			int autsize = nfe.getNFe().getInfNFe().getAutXML().size();
			for (int au = 0; au < autsize; au++) {
				FsNfeAut nfeaut = new FsNfeAut();
				nfeaut.setFsnfe(nf);
				if (nfe.getNFe().getInfNFe().getAutXML().get(au).getCNPJ() != null) {
					nfeaut.setCpfcnpj(nfe.getNFe().getInfNFe().getAutXML().get(au).getCNPJ());
				}
				if (nfe.getNFe().getInfNFe().getAutXML().get(au).getCPF() != null) {
					nfeaut.setCpfcnpj(nfe.getNFe().getInfNFe().getAutXML().get(au).getCPF());
				}
				fsNfeAutRp.save(nfeaut);
			}
			// ITENS###############################################################################################
			int itemsize = nfe.getNFe().getInfNFe().getDet().size();
			for (int i = 0; i < itemsize; i++) {
				FsNfeItem ni = new FsNfeItem();
				ni.setFsnfe(nf);
				ni.setCprod(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getCProd()));
				ni.setCean(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getCEAN()));
				ni.setXprod(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getXProd()));
				ni.setNcm(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getNCM()));
				ni.setCest(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getCEST()));
				ni.setIndescala(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getIndEscala()));
				ni.setCfop(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getCFOP()));
				ni.setUcom(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getUCom()));
				ni.setQcom(FsUtil.cpBD(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getQCom()));
				ni.setVuncom(FsUtil.cpBD(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getVUnCom()));
				BigDecimal vprod = FsUtil.cpBD(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getVProd());
				BigDecimal vdesc = FsUtil.cpBD(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getVDesc());
				BigDecimal voutro = FsUtil.cpBD(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getVOutro());
				ni.setVprod(vprod);
				ni.setCeantrib(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getCEANTrib()));
				ni.setUtrib(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getUTrib()));
				ni.setQtrib(FsUtil.cpBD(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getQTrib()));
				ni.setVuntrib(FsUtil.cpBD(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getVUnTrib()));
				ni.setVfrete(FsUtil.cpBD(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getVFrete()));
				ni.setVseg(FsUtil.cpBD(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getVSeg()));
				ni.setVdesc(vdesc);
				ni.setVoutro(voutro);
				BigDecimal vtot = vprod.subtract(vdesc).add(voutro);
				ni.setVtot(vtot);
				ni.setIndtot(FsUtil.cpInt(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getIndTot()));
				ni.setXped(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getXPed()));
				ni.setNitemped(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getNItemPed()));
				// Para combustiveis - 3 campos obrigatorios pelo manual
				if (nfe.getNFe().getInfNFe().getDet().get(i).getProd().getComb() != null) {
					ni.setCprodanp(
							FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getComb().getCProdANP()));
					ni.setDescanp(
							FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getComb().getDescANP()));
					ni.setUfcons(FsUtil
							.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getComb().getUFCons().toString()));
				}
				ni.setCbenef(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getCBenef()));
				// DADOS DE VINCULOS INTERNOS---------------------------------------------
				String codnofornec = FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getCProd());
				String cnpjfornec = "";
				// Cpf ou Cnpj
				if (nfe.getNFe().getInfNFe().getEmit().getCPF() != null) {
					cnpjfornec = nfe.getNFe().getInfNFe().getEmit().getCPF();
				}
				if (nfe.getNFe().getInfNFe().getEmit().getCNPJ() != null) {
					cnpjfornec = nfe.getNFe().getInfNFe().getEmit().getCNPJ();
				}
				FsNfeItem itemJaUsado = fsNfeItemRp.verificaItemEntFornec(cnpjfornec, codnofornec);
				ni.setCfopconv(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getCFOP()));
				ni.setQtdconv(FsUtil.cpBD(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getQCom()));
				ni.setUconv(FsUtil.cpN(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getUCom()));
				ni.setVconv(FsUtil.cpBD(nfe.getNFe().getInfNFe().getDet().get(i).getProd().getVUnCom()));
				if (itemJaUsado != null) {
					ni.setIdprod(itemJaUsado.getIdprod());
					ni.setXprodconv(itemJaUsado.getXprodconv());
					ni.setCfopconv(itemJaUsado.getCfopconv());
					ni.setUconv(itemJaUsado.getUconv());
				}
				ni.setCdnfecfg(null);
				// TRIBUTOS ICMS----------------------------------------------------------
				String CSOSN = FsUtil.eXmlDet(xml, "ICMS", i, "CSOSN");
				String CST = FsUtil.eXmlDet(xml, "ICMS", i, "CST");
				FsNfeItemIcms ic = new FsNfeItemIcms();
				ic.setFsnfe_id(nota.getId());
				ic.setOrig(FsUtil.eXmlSub(xml, "orig", i));
				if (!CSOSN.equals("")) {
					ic.setCst(CSOSN);
				}
				if (!CST.equals("")) {
					ic.setCst(CST);
				}
				ic.setModbc(FsUtil.eXmlDetInt(xml, "ICMS", i, "modBC"));
				ic.setPredbc(FsUtil.eXmlDetBD(xml, "ICMS", i, "pRedBC"));
				ic.setVbc(FsUtil.eXmlDetBD(xml, "ICMS", i, "vBC"));
				ic.setPicms(FsUtil.eXmlDetBD(xml, "ICMS", i, "pICMS"));
				ic.setVicms(FsUtil.eXmlDetBD(xml, "ICMS", i, "vICMS"));
				ic.setModbcst(FsUtil.eXmlDetInt(xml, "ICMS", i, "modBCST"));
				ic.setPmvast(FsUtil.eXmlDetBD(xml, "ICMS", i, "pMVAST"));
				ic.setPredbcst(FsUtil.eXmlDetBD(xml, "ICMS", i, "pRedBCST"));
				ic.setVbcst(FsUtil.eXmlDetBD(xml, "ICMS", i, "vBCST"));
				ic.setPicmsst(FsUtil.eXmlDetBD(xml, "ICMS", i, "pICMSST"));
				ic.setVicmsst(FsUtil.eXmlDetBD(xml, "ICMS", i, "vICMSST"));
				ic.setVbcstret(FsUtil.eXmlDetBD(xml, "ICMS", i, "vBCSTRet"));
				ic.setVicmsstret(FsUtil.eXmlDetBD(xml, "ICMS", i, "vICMSSTRet"));
				ic.setVbcstdest(FsUtil.eXmlDetBD(xml, "ICMS", i, "vBCSTDest"));
				ic.setVicmsstdest(FsUtil.eXmlDetBD(xml, "ICMS", i, "vICMSSTDest"));
				ic.setMotdesicms(FsUtil.eXmlDetInt(xml, "ICMS", i, "motDesICMS"));
				ic.setPbcop(FsUtil.eXmlDetBD(xml, "ICMS", i, "pBCOp"));
				ic.setUfst(FsUtil.eXmlDet(xml, "ICMS", i, "UFST"));
				ic.setPcredsn(FsUtil.eXmlDetBD(xml, "ICMS", i, "pCredSN"));
				ic.setVcredicmssn(FsUtil.eXmlDetBD(xml, "ICMS", i, "vCredICMSSN"));
				ic.setVicmsdeson(FsUtil.eXmlDetBD(xml, "ICMS", i, "vICMSDeson"));
				ic.setVicmsop(FsUtil.eXmlDetBD(xml, "ICMS", i, "vICMSOp"));
				ic.setPdif(FsUtil.eXmlDetBD(xml, "ICMS", i, "pDif"));
				ic.setVicmsdif(FsUtil.eXmlDetBD(xml, "ICMS", i, "vICMSDif"));
				ic.setVbcfcp(FsUtil.eXmlDetBD(xml, "ICMS", i, "vBCFCP"));
				ic.setPfcp(FsUtil.eXmlDetBD(xml, "ICMS", i, "pFCP"));
				ic.setVfcp(FsUtil.eXmlDetBD(xml, "ICMS", i, "vFCP"));
				ic.setVbcfcpst(FsUtil.eXmlDetBD(xml, "ICMS", i, "vBCFCPST"));
				ic.setPfcpst(FsUtil.eXmlDetBD(xml, "ICMS", i, "pFCPST"));
				ic.setVfcpst(FsUtil.eXmlDetBD(xml, "ICMS", i, "vFCPST"));
				ic.setVbcfcpstret(FsUtil.eXmlDetBD(xml, "ICMS", i, "vBCFCPSTRet"));
				ic.setPfcpstret(FsUtil.eXmlDetBD(xml, "ICMS", i, "pFCPSTRet"));
				ic.setVfcpstret(FsUtil.eXmlDetBD(xml, "ICMS", i, "vFCPSTRet"));
				ic.setPst(FsUtil.eXmlDetBD(xml, "ICMS", i, "pST"));
				ic.setPfcpdif(FsUtil.eXmlDetBD(xml, "ICMS", i, "pFCPDif"));
				ic.setVfcpdif(FsUtil.eXmlDetBD(xml, "ICMS", i, "vFCPDif"));
				ic.setVfcpefet(FsUtil.eXmlDetBD(xml, "ICMS", i, "vFCPEfet"));
				ic.setVicmsstdeson(FsUtil.eXmlDetBD(xml, "ICMS", i, "vICMSSTDeson"));
				ic.setMotdesicmsst(FsUtil.eXmlDetInt(xml, "ICMS", i, "motDesICMSST"));
				fsNfeItemIcmsRp.save(ic);
				ni.setFsnfeitemicms(ic);
				// PIS---------------------------------------------------------------------
				String PIS = FsUtil.eXmlDet(xml, "PIS", i, "CST");
				String PISNT = FsUtil.eXmlDet(xml, "PISNT", i, "CST");
				String PISST = FsUtil.eXmlDet(xml, "PISST", i, "vPIS");
				FsNfeItemPis pi = new FsNfeItemPis();
				pi.setFsnfe_id(nota.getId());
				pi.setCst(PIS);
				if (!PISNT.equals("")) {
					pi.setCst(PISNT);
				}
				pi.setVbc(FsUtil.eXmlSubBD(xml, "vBC", i));
				pi.setPpis(FsUtil.eXmlSubBD(xml, "pPIS", i));
				pi.setVpis(FsUtil.eXmlSubBD(xml, "vPIS", i));
				pi.setQbcprod(FsUtil.eXmlSubBD(xml, "qBCProd", i));
				pi.setValiqprod(FsUtil.eXmlSubBD(xml, "vAliqProd", i));
				// Verifica se PI ST existe
				if (!PISST.equals("")) {
					pi.setVbcst(FsUtil.eXmlSubBD(xml, "vBC", i));
					pi.setPpisst(FsUtil.eXmlSubBD(xml, "pPIS", i));
					pi.setVpisst(FsUtil.eXmlSubBD(xml, "vPIS", i));
					pi.setQbcprodst(FsUtil.eXmlSubBD(xml, "qBCProd", i));
					pi.setValiqprodst(FsUtil.eXmlSubBD(xml, "vAliqProd", i));
					pi.setIndsomapisst(FsUtil.eXmlSubInt(xml, "indSomaPISST", i));
				}
				fsNfeItemPisRp.save(pi);
				ni.setFsnfeitempis(pi);
				// COFINS-------------------------------------------------------------------
				String COFINS = FsUtil.eXmlDet(xml, "COFINS", i, "CST");
				String COFINSNT = FsUtil.eXmlDet(xml, "COFINSNT", i, "CST");
				String COFINSST = FsUtil.eXmlDet(xml, "COFINSST", i, "vCOFINS");
				FsNfeItemCofins co = new FsNfeItemCofins();
				co.setFsnfe_id(nota.getId());
				co.setCst(COFINS);
				if (!COFINSNT.equals("")) {
					co.setCst(COFINSNT);
				}
				co.setVbc(FsUtil.eXmlSubBD(xml, "vBC", i));
				co.setPcofins(FsUtil.eXmlSubBD(xml, "pCOFINS", i));
				co.setVcofins(FsUtil.eXmlSubBD(xml, "vCOFINS", i));
				co.setQbcprod(FsUtil.eXmlSubBD(xml, "qBCProd", i));
				co.setValiqprod(FsUtil.eXmlSubBD(xml, "vAliqProd", i));
				// Verifica se COFINS ST existe
				if (!COFINSST.equals("")) {
					co.setVbcst(FsUtil.eXmlSubBD(xml, "vBC", i));
					co.setPcofinsst(FsUtil.eXmlSubBD(xml, "pCOFINS", i));
					co.setVcofinsst(FsUtil.eXmlSubBD(xml, "vCOFINS", i));
					co.setQbcprodst(FsUtil.eXmlSubBD(xml, "qBCProd", i));
					co.setValiqprodst(FsUtil.eXmlSubBD(xml, "vAliqProd", i));
					co.setIndsomacofinsst(FsUtil.eXmlSubInt(xml, "indSomaCOFINSST", i));
				}
				fsNfeItemCofinsRp.save(co);
				ni.setFsnfeitemcofins(co);
				// IPI----------------------------------------------------------------------
				FsNfeItemIpi ip = new FsNfeItemIpi();
				ip.setFsnfe_id(nota.getId());
				ip.setCnpjprod(FsUtil.eXmlDet(xml, "IPI", i, "CNPJProd"));
				ip.setCselo(FsUtil.eXmlDet(xml, "IPI", i, "cSelo"));
				ip.setQselo(FsUtil.eXmlDetBD(xml, "IPI", i, "qSelo"));
				ip.setCenq(FsUtil.eXmlDet(xml, "IPI", i, "cEnq"));
				ip.setCst(FsUtil.eXmlDet(xml, "IPI", i, "CST"));
				ip.setVbc(FsUtil.eXmlDetBD(xml, "IPI", i, "vBC"));
				ip.setPipi(FsUtil.eXmlDetBD(xml, "IPI", i, "pIPI"));
				ip.setVipi(FsUtil.eXmlDetBD(xml, "IPI", i, "vIPI"));
				ip.setQunid(FsUtil.eXmlDetBD(xml, "IPI", i, "qUnid"));
				ip.setVunid(FsUtil.eXmlDetBD(xml, "IPI", i, "vUnid"));
				fsNfeItemIpiRp.save(ip);
				ni.setFsnfeitemipi(ip);
				// SALVA ITEM
				fsNfeItemRp.save(ni);
			}
		}
	}
}