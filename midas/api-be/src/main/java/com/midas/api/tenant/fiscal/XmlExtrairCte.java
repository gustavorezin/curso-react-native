package com.midas.api.tenant.fiscal;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsCteAut;
import com.midas.api.tenant.entity.FsCteIcms;
import com.midas.api.tenant.entity.FsCteInf;
import com.midas.api.tenant.entity.FsCteInfCarga;
import com.midas.api.tenant.entity.FsCteInfDocEmi;
import com.midas.api.tenant.entity.FsCteInfDocEmiCte;
import com.midas.api.tenant.entity.FsCteInfNfe;
import com.midas.api.tenant.entity.FsCtePart;
import com.midas.api.tenant.entity.FsCteVprest;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.FsCteAutRepository;
import com.midas.api.tenant.repository.FsCteIcmsRepository;
import com.midas.api.tenant.repository.FsCteInfCargaRepository;
import com.midas.api.tenant.repository.FsCteInfDocEmiCteRepository;
import com.midas.api.tenant.repository.FsCteInfDocEmiRepository;
import com.midas.api.tenant.repository.FsCteInfNfeRepository;
import com.midas.api.tenant.repository.FsCteInfRepository;
import com.midas.api.tenant.repository.FsCtePartRepository;
import com.midas.api.tenant.repository.FsCteRepository;
import com.midas.api.tenant.repository.FsCteVprestRepository;
import com.midas.api.util.DataUtil;

import br.inf.portalfiscal.cte.CteProc;

@Component
public class XmlExtrairCte {
	@Autowired
	private FsCteRepository fsCteRp;
	@Autowired
	private FsCtePartRepository fsCtePartRp;
	@Autowired
	private FsCteVprestRepository fsCteVprestRp;
	@Autowired
	private FsCteIcmsRepository fsCteIcmsRp;
	@Autowired
	private FsCteInfRepository fsCteInfRp;
	@Autowired
	private FsCteInfCargaRepository fsCteInfCargaRp;
	@Autowired
	private FsCteInfNfeRepository fsCteInfNfeRp;
	@Autowired
	private FsCteInfDocEmiRepository fsCteInfDocEmiRp;
	@Autowired
	private FsCteInfDocEmiCteRepository fsCteInfDocEmiCteRp;
	@Autowired
	private FsCteAutRepository fsCteAutRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;

	@Transactional("tenantTransactionManager")
	public void ExtraiCTe(String xml, Optional<CdPessoa> cdpessoaemp, CteProc cte) throws Exception {
		if (cte != null) {
			// IDENTIFICACAO##############################################################################
			FsCte ct = new FsCte();
			ct.setCdpessoaemp(cdpessoaemp.get());
			ct.setTipo("R");
			ct.setCct(Integer.valueOf(cte.getCTe().getInfCte().getIde().getCCT()));
			ct.setCfop(cte.getCTe().getInfCte().getIde().getCFOP());
			ct.setNatop(cte.getCTe().getInfCte().getIde().getNatOp());
			ct.setModelo(Integer.valueOf(cte.getCTe().getInfCte().getIde().getMod()));
			ct.setSerie(Integer.valueOf(cte.getCTe().getInfCte().getIde().getSerie()));
			ct.setNct(Integer.valueOf(cte.getCTe().getInfCte().getIde().getNCT()));
			ct.setDhemi(DataUtil.dUtil(cte.getCTe().getInfCte().getIde().getDhEmi()));
			ct.setDhemihr(DataUtil.hUtil(cte.getCTe().getInfCte().getIde().getDhEmi().substring(11, 19)));
			ct.setDhsaient(DataUtil.dUtil(cte.getCTe().getInfCte().getIde().getDhEmi()));
			ct.setTpimp(Integer.valueOf(cte.getCTe().getInfCte().getIde().getTpImp()));
			ct.setTpemis(Integer.valueOf(cte.getCTe().getInfCte().getIde().getTpEmis()));
			ct.setChaveac(cte.getProtCTe().getInfProt().getChCTe());
			ct.setCdv(Integer.valueOf(cte.getCTe().getInfCte().getIde().getCDV()));
			ct.setTpamb(Integer.valueOf(cte.getCTe().getInfCte().getIde().getTpAmb()));
			ct.setTpcte(Integer.valueOf(cte.getCTe().getInfCte().getIde().getTpCTe()));
			ct.setProcemi(Integer.valueOf(cte.getCTe().getInfCte().getIde().getProcEmi()));
			ct.setVerproc(cte.getCTe().getInfCte().getIde().getVerProc());
			ct.setCmunenv(cte.getCTe().getInfCte().getIde().getCMunEnv());
			ct.setXmunenv(cte.getCTe().getInfCte().getIde().getXMunEnv());
			ct.setUfenv(cte.getCTe().getInfCte().getIde().getUFEnv().toString());
			ct.setModal(cte.getCTe().getInfCte().getIde().getModal());
			ct.setTpserv(Integer.valueOf(cte.getCTe().getInfCte().getIde().getTpServ()));
			ct.setCmunini(cte.getCTe().getInfCte().getIde().getCMunIni());
			ct.setXmunini(cte.getCTe().getInfCte().getIde().getXMunIni());
			ct.setUfini(cte.getCTe().getInfCte().getIde().getUFIni().toString());
			ct.setCmunfim(cte.getCTe().getInfCte().getIde().getCMunFim());
			ct.setXmunfim(cte.getCTe().getInfCte().getIde().getXMunFim());
			ct.setUffim(cte.getCTe().getInfCte().getIde().getUFFim().toString());
			ct.setRetira(Integer.valueOf(cte.getCTe().getInfCte().getIde().getRetira()));
			ct.setXdetretira(cte.getCTe().getInfCte().getIde().getXDetRetira());
			// TOMADOR
			Integer tomador = 0;
			if (cte.getCTe().getInfCte().getIde().getToma3() != null) {
				tomador = Integer.valueOf(cte.getCTe().getInfCte().getIde().getToma3().getToma());
			}
			if (cte.getCTe().getInfCte().getIde().getToma4() != null) {
				tomador = Integer.valueOf(cte.getCTe().getInfCte().getIde().getToma4().getToma());
			}
			ct.setTomador(tomador);
			ct.setCpfcnpjemit(cte.getCTe().getInfCte().getEmit().getCNPJ());
			ct.setXml(xml);
			// Verifica se já tem fornecedor cadastrado
			String cfpcnpjemit = cte.getCTe().getInfCte().getEmit().getCNPJ();
			CdPessoa para = cdPessoaRp.findFirstByCpfcnpj(cfpcnpjemit);
			if (para != null) {
				ct.setSt_transp("S");
			}
			ct.setStatus(Integer.valueOf(cte.getProtCTe().getInfProt().getCStat()));
			// EMITENTE#####################################################################################
			FsCtePart emit = new FsCtePart();
			ct.setFsctepartemit(emit);
			emit.setTipo("E");
			// Cnpj
			if (cte.getCTe().getInfCte().getEmit().getCNPJ() != null) {
				emit.setCpfcnpj(cte.getCTe().getInfCte().getEmit().getCNPJ());
			}
			emit.setIe(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getIE()));
			emit.setIest(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getIEST()));
			emit.setXnome(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getXNome()));
			emit.setXfant(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getXFant()));
			emit.setXlgr(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getEnderEmit().getXLgr()));
			emit.setNro(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getEnderEmit().getNro()));
			emit.setXcpl(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getEnderEmit().getXCpl()));
			emit.setXbairro(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getEnderEmit().getXBairro()));
			emit.setCmun(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getEnderEmit().getCMun()));
			emit.setXmun(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getEnderEmit().getXMun()));
			emit.setUf(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getEnderEmit().getUF().toString()));
			emit.setCep(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getEnderEmit().getCEP()));
			emit.setFone(FsUtil.cpN(cte.getCTe().getInfCte().getEmit().getEnderEmit().getFone()));
			fsCtePartRp.save(emit);
			// REMETENTE#####################################################################################
			FsCtePart rem = new FsCtePart();
			ct.setFsctepartrem(rem);
			rem.setTipo("R");
			// Cpf ou Cnpj
			if (cte.getCTe().getInfCte().getRem().getCPF() != null) {
				rem.setCpfcnpj(cte.getCTe().getInfCte().getRem().getCPF());
			}
			if (cte.getCTe().getInfCte().getRem().getCNPJ() != null) {
				rem.setCpfcnpj(cte.getCTe().getInfCte().getRem().getCNPJ());
			}
			rem.setIe(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getIE()));
			rem.setXnome(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getXNome()));
			rem.setXfant(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getXFant()));
			rem.setXlgr(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getEnderReme().getXLgr()));
			rem.setNro(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getEnderReme().getNro()));
			rem.setXcpl(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getEnderReme().getXCpl()));
			rem.setXbairro(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getEnderReme().getXBairro()));
			rem.setCmun(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getEnderReme().getCMun()));
			rem.setXmun(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getEnderReme().getXMun()));
			rem.setUf(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getEnderReme().getUF().toString()));
			rem.setCep(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getEnderReme().getCEP()));
			rem.setFone(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getFone()));
			rem.setEmail(FsUtil.cpN(cte.getCTe().getInfCte().getRem().getEmail()));
			fsCtePartRp.save(rem);
			/*
			 * //
			 * TOMADOR######################################################################
			 * ############### FsCtePart tom = new FsCtePart(); // Define Remetente se for
			 * 3, se 4, abaixo insere ct.setFscteparttom(rem); tom.setTipo("T"); if
			 * (cte.getCTe().getInfCte().getIde().getToma4() != null) {
			 * ct.setFscteparttom(tom); // Cpf ou Cnpj if
			 * (cte.getCTe().getInfCte().getIde().getToma4().getCPF() != null) {
			 * tom.setCpfcnpj(cte.getCTe().getInfCte().getIde().getToma4().getCPF()); } if
			 * (cte.getCTe().getInfCte().getIde().getToma4().getCNPJ() != null) {
			 * tom.setCpfcnpj(cte.getCTe().getInfCte().getIde().getToma4().getCNPJ()); }
			 * tom.setIe(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().getIE()));
			 * tom.setXnome(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().getXNome
			 * ()));
			 * tom.setXfant(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().getXFant
			 * ())); tom.setXlgr(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().
			 * getEnderToma().getXLgr()));
			 * tom.setNro(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().
			 * getEnderToma().getNro()));
			 * tom.setXcpl(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().
			 * getEnderToma().getXCpl()));
			 * tom.setXbairro(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().
			 * getEnderToma().getXBairro()));
			 * tom.setCmun(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().
			 * getEnderToma().getCMun()));
			 * tom.setXmun(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().
			 * getEnderToma().getXMun()));
			 * tom.setUf(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().
			 * getEnderToma().getUF().toString()));
			 * tom.setCep(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().
			 * getEnderToma().getCEP()));
			 * tom.setFone(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().getFone()
			 * ));
			 * tom.setEmail(FsUtil.cpN(cte.getCTe().getInfCte().getIde().getToma4().getEmail
			 * ())); fsCtePartRp.save(tom); }
			 */
			// EXPEDIDOR#####################################################################################
			FsCtePart exp = new FsCtePart();
			// Define Remetente, se nao, abaixo insere
			ct.setFsctepartexp(rem);
			exp.setTipo("X");
			if (cte.getCTe().getInfCte().getExped() != null) {
				ct.setFsctepartexp(exp);
				// Cpf ou Cnpj
				if (cte.getCTe().getInfCte().getExped().getCPF() != null) {
					exp.setCpfcnpj(cte.getCTe().getInfCte().getExped().getCPF());
				}
				if (cte.getCTe().getInfCte().getExped().getCNPJ() != null) {
					exp.setCpfcnpj(cte.getCTe().getInfCte().getExped().getCNPJ());
				}
				exp.setIe(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getIE()));
				exp.setXnome(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getXNome()));
				exp.setXfant(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getXNome()));
				exp.setXlgr(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getEnderExped().getXLgr()));
				exp.setNro(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getEnderExped().getNro()));
				exp.setXcpl(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getEnderExped().getXCpl()));
				exp.setXbairro(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getEnderExped().getXBairro()));
				exp.setCmun(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getEnderExped().getCMun()));
				exp.setXmun(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getEnderExped().getXMun()));
				exp.setUf(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getEnderExped().getUF().toString()));
				exp.setCep(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getEnderExped().getCEP()));
				exp.setFone(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getFone()));
				exp.setEmail(FsUtil.cpN(cte.getCTe().getInfCte().getExped().getEmail()));
				fsCtePartRp.save(exp);
			}
			// DESTINATARIO#####################################################################################
			FsCtePart dest = new FsCtePart();
			ct.setFsctepartdest(dest);
			dest.setTipo("D");
			// Cpf ou Cnpj
			if (cte.getCTe().getInfCte().getDest().getCPF() != null) {
				dest.setCpfcnpj(cte.getCTe().getInfCte().getDest().getCPF());
			}
			if (cte.getCTe().getInfCte().getDest().getCNPJ() != null) {
				dest.setCpfcnpj(cte.getCTe().getInfCte().getDest().getCNPJ());
			}
			dest.setIe(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getIE()));
			dest.setIsuf(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getISUF()));
			dest.setXnome(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getXNome()));
			dest.setXfant(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getXNome()));
			dest.setXlgr(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getEnderDest().getXLgr()));
			dest.setNro(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getEnderDest().getNro()));
			dest.setXcpl(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getEnderDest().getXCpl()));
			dest.setXbairro(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getEnderDest().getXBairro()));
			dest.setCmun(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getEnderDest().getCMun()));
			dest.setXmun(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getEnderDest().getXMun()));
			dest.setUf(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getEnderDest().getUF().toString()));
			dest.setCep(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getEnderDest().getCEP()));
			dest.setFone(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getFone()));
			dest.setEmail(FsUtil.cpN(cte.getCTe().getInfCte().getDest().getEmail()));
			fsCtePartRp.save(dest);
			// RECEBEDOR#####################################################################################
			FsCtePart rec = new FsCtePart();
			ct.setFsctepartrec(dest);
			rec.setTipo("C");
			// Define Destinatario, se nao, abaixo insere
			if (cte.getCTe().getInfCte().getReceb() != null) {
				ct.setFsctepartrec(rec);
				// Cpf ou Cnpj
				if (cte.getCTe().getInfCte().getReceb().getCPF() != null) {
					rec.setCpfcnpj(cte.getCTe().getInfCte().getReceb().getCPF());
				}
				if (cte.getCTe().getInfCte().getReceb().getCNPJ() != null) {
					rec.setCpfcnpj(cte.getCTe().getInfCte().getReceb().getCNPJ());
				}
				rec.setIe(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getIE()));
				rec.setXnome(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getXNome()));
				rec.setXfant(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getXNome()));
				rec.setXlgr(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getEnderReceb().getXLgr()));
				rec.setNro(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getEnderReceb().getNro()));
				rec.setXcpl(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getEnderReceb().getXCpl()));
				rec.setXbairro(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getEnderReceb().getXBairro()));
				rec.setCmun(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getEnderReceb().getCMun()));
				rec.setXmun(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getEnderReceb().getXMun()));
				rec.setUf(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getEnderReceb().getUF().toString()));
				rec.setCep(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getEnderReceb().getCEP()));
				rec.setFone(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getFone()));
				rec.setEmail(FsUtil.cpN(cte.getCTe().getInfCte().getReceb().getEmail()));
				fsCtePartRp.save(rec);
			}
			// VALORES#####################################################################################
			FsCteVprest vp = new FsCteVprest();
			vp.setVtprest(FsUtil.cpBD(cte.getCTe().getInfCte().getVPrest().getVTPrest()));
			vp.setVrec(FsUtil.cpBD(cte.getCTe().getInfCte().getVPrest().getVRec()));
			fsCteVprestRp.save(vp);
			ct.setFsctevprest(vp);
			// ICMS#####################################################################################
			FsCteIcms ic = new FsCteIcms();
			// Simples nacional
			if (cte.getCTe().getInfCte().getImp().getICMS().getICMSSN() != null) {
				ic.setCst(cte.getCTe().getInfCte().getImp().getICMS().getICMSSN().getCST());
				ic.setPredbc(new BigDecimal(0));
				ic.setVbc(new BigDecimal(0));
				ic.setPicms(new BigDecimal(0));
				ic.setVicms(new BigDecimal(0));
				ic.setVbcstret(new BigDecimal(0));
				ic.setPicmsstret(new BigDecimal(0));
				ic.setVicmsstret(new BigDecimal(0));
				ic.setVcred(new BigDecimal(0));
				fsCteIcmsRp.save(ic);
			}
			// 00
			if (cte.getCTe().getInfCte().getImp().getICMS().getICMS00() != null) {
				ic.setCst(cte.getCTe().getInfCte().getImp().getICMS().getICMS00().getCST());
				ic.setPredbc(new BigDecimal(0));
				ic.setVbc(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS00().getVBC()));
				ic.setPicms(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS00().getPICMS()));
				ic.setVicms(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS00().getVICMS()));
				ic.setVbcstret(new BigDecimal(0));
				ic.setPicmsstret(new BigDecimal(0));
				ic.setVicmsstret(new BigDecimal(0));
				ic.setVcred(new BigDecimal(0));
				fsCteIcmsRp.save(ic);
			}
			// 20
			if (cte.getCTe().getInfCte().getImp().getICMS().getICMS20() != null) {
				ic.setCst(cte.getCTe().getInfCte().getImp().getICMS().getICMS20().getCST());
				ic.setPredbc(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS20().getPRedBC()));
				ic.setVbc(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS20().getVBC()));
				ic.setPicms(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS20().getPICMS()));
				ic.setVicms(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS20().getVICMS()));
				ic.setVbcstret(new BigDecimal(0));
				ic.setPicmsstret(new BigDecimal(0));
				ic.setVicmsstret(new BigDecimal(0));
				ic.setVcred(new BigDecimal(0));
				fsCteIcmsRp.save(ic);
			}
			// 45
			if (cte.getCTe().getInfCte().getImp().getICMS().getICMS45() != null) {
				ic.setCst(cte.getCTe().getInfCte().getImp().getICMS().getICMS45().getCST());
				ic.setPredbc(new BigDecimal(0));
				ic.setVbc(new BigDecimal(0));
				ic.setPicms(new BigDecimal(0));
				ic.setVicms(new BigDecimal(0));
				ic.setVbcstret(new BigDecimal(0));
				ic.setPicmsstret(new BigDecimal(0));
				ic.setVicmsstret(new BigDecimal(0));
				ic.setVcred(new BigDecimal(0));
				fsCteIcmsRp.save(ic);
			}
			// 60
			if (cte.getCTe().getInfCte().getImp().getICMS().getICMS60() != null) {
				ic.setCst(cte.getCTe().getInfCte().getImp().getICMS().getICMS60().getCST());
				ic.setPredbc(new BigDecimal(0));
				ic.setVbc(new BigDecimal(0));
				ic.setPicms(new BigDecimal(0));
				ic.setVicms(new BigDecimal(0));
				ic.setVbcstret(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS60().getVBCSTRet()));
				ic.setPicmsstret(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS60().getPICMSSTRet()));
				ic.setVicmsstret(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS60().getVICMSSTRet()));
				ic.setVcred(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS60().getVCred()));
				fsCteIcmsRp.save(ic);
			}
			// 90
			if (cte.getCTe().getInfCte().getImp().getICMS().getICMS90() != null) {
				ic.setCst(cte.getCTe().getInfCte().getImp().getICMS().getICMS90().getCST());
				ic.setPredbc(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS90().getPRedBC()));
				ic.setVbc(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS90().getVBC()));
				ic.setPicms(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS90().getPICMS()));
				ic.setVicms(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS90().getVICMS()));
				ic.setVbcstret(new BigDecimal(0));
				ic.setPicmsstret(new BigDecimal(0));
				ic.setVicmsstret(new BigDecimal(0));
				ic.setVcred(FsUtil.cpBD(cte.getCTe().getInfCte().getImp().getICMS().getICMS90().getVCred()));
				fsCteIcmsRp.save(ic);
			}
			ct.setFscteicms(ic);
			// INF-NORM-SUBS-ANULA############################################################################
			FsCteInf inf = new FsCteInf();
			if (cte.getCTe().getInfCte().getInfCTeNorm() != null) {
				inf.setVcarga(FsUtil.cpBD(cte.getCTe().getInfCte().getInfCTeNorm().getInfCarga().getVCarga()));
				inf.setProdpred(FsUtil.cpN(cte.getCTe().getInfCte().getInfCTeNorm().getInfCarga().getProPred()));
				// NORMAL
				if (cte.getCTe().getInfCte().getInfCTeNorm().getInfModal() != null) {
					if (cte.getCTe().getInfCte().getInfCTeNorm().getInfModal().getRodo() != null) {
						inf.setRntrc(FsUtil
								.cpN(cte.getCTe().getInfCte().getInfCTeNorm().getInfModal().getRodo().getRNTRC()));
					}
					// SUBSTITUTO
					if (cte.getCTe().getInfCte().getInfCTeNorm().getInfCteSub() != null) {
						inf.setChcte_sub(
								FsUtil.cpN(cte.getCTe().getInfCte().getInfCTeNorm().getInfCteSub().getChCte()));
						// SE TEM REFs
						if (cte.getCTe().getInfCte().getInfCTeNorm().getInfCteSub().getTomaICMS() != null) {
							inf.setRefnfe_sub(FsUtil.cpN(
									cte.getCTe().getInfCte().getInfCTeNorm().getInfCteSub().getTomaICMS().getRefNFe()));
							inf.setRefcte_sub(FsUtil.cpN(
									cte.getCTe().getInfCte().getInfCTeNorm().getInfCteSub().getTomaICMS().getRefCte()));
						}
						inf.setRefcteanu_sub(
								FsUtil.cpN(cte.getCTe().getInfCte().getInfCTeNorm().getInfCteSub().getRefCteAnu()));
					}
					// COMPLEMENTO
					if (cte.getCTe().getInfCte().getInfCteComp() != null) {
						inf.setChcte_comp(FsUtil.cpN(cte.getCTe().getInfCte().getInfCteComp().getChCTe()));
					}
					// ANULA
					if (cte.getCTe().getInfCte().getInfCteAnu() != null) {
						inf.setChcte_anu(FsUtil.cpN(cte.getCTe().getInfCte().getInfCteAnu().getChCte()));
						inf.setDemi_anu(FsUtil.cpDT(cte.getCTe().getInfCte().getInfCteAnu().getDEmi()));
					}
				}
				fsCteInfRp.save(inf);
				// CARGA
				int cargasize = cte.getCTe().getInfCte().getInfCTeNorm().getInfCarga().getInfQ().size();
				for (int i = 0; i < cargasize; i++) {
					FsCteInfCarga d = new FsCteInfCarga();
					d.setFscteinf(inf);
					d.setCunid(FsUtil
							.cpN(cte.getCTe().getInfCte().getInfCTeNorm().getInfCarga().getInfQ().get(i).getCUnid()));
					d.setTpmed(FsUtil
							.cpN(cte.getCTe().getInfCte().getInfCTeNorm().getInfCarga().getInfQ().get(i).getTpMed()));
					d.setQcarga(FsUtil
							.cpBD(cte.getCTe().getInfCte().getInfCTeNorm().getInfCarga().getInfQ().get(i).getQCarga()));
					fsCteInfCargaRp.save(d);
				}
				// NFEs
				int nfessize = cte.getCTe().getInfCte().getInfCTeNorm().getInfDoc().getInfNFe().size();
				for (int i = 0; i < nfessize; i++) {
					FsCteInfNfe n = new FsCteInfNfe();
					n.setFscteinf(inf);
					n.setChave(cte.getCTe().getInfCte().getInfCTeNorm().getInfDoc().getInfNFe().get(i).getChave());
					fsCteInfNfeRp.save(n);
				}
				// DOC ANTERIOR E EMISSOR
				if (cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt() != null) {
					int docemisize = cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt().size();
					for (int i = 0; i < docemisize; i++) {
						// EMISSOR
						FsCteInfDocEmi de = new FsCteInfDocEmi();
						de.setFscteinf(inf);
						// Cpf ou Cnpj
						if (cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt().get(i)
								.getCPF() != null) {
							de.setCpfcnpj(cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt().get(i)
									.getCPF());
						}
						if (cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt().get(i)
								.getCNPJ() != null) {
							de.setCpfcnpj(cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt().get(i)
									.getCNPJ());
							de.setIe(FsUtil.cpN(cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt()
									.get(i).getIE()));
						}
						de.setXnome(
								cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt().get(i).getXNome());
						if (cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt().get(i)
								.getUF() != null) {
							de.setUf(cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt().get(i).getUF()
									.toString());
						}
						fsCteInfDocEmiRp.save(de);
						// CTEs ANT
						int docemiantsize = cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt().get(i)
								.getIdDocAnt().size();
						for (int x = 0; x < docemiantsize; x++) {
							FsCteInfDocEmiCte ec = new FsCteInfDocEmiCte();
							ec.setFscteinfdocemi(de);
							int docemiantelesize = cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt()
									.get(i).getIdDocAnt().get(x).getIdDocAntEle().size();
							for (int y = 0; y < docemiantelesize; y++) {
								ec.setChave(cte.getCTe().getInfCte().getInfCTeNorm().getDocAnt().getEmiDocAnt().get(i)
										.getIdDocAnt().get(x).getIdDocAntEle().get(y).getChCTe());
								fsCteInfDocEmiCteRp.save(ec);
							}
						}
					}
				}
			}
			ct.setFscteinf(inf);
			// SALVA TUDO DO
			// FS_CTE###########################################################################
			fsCteRp.save(ct);
			// AUTORIZADOS####################################################################################
			int autsize = cte.getCTe().getInfCte().getAutXML().size();
			for (int au = 0; au < autsize; au++) {
				FsCteAut cteaut = new FsCteAut();
				cteaut.setFscte(ct);
				if (cte.getCTe().getInfCte().getAutXML().get(au).getCNPJ() != null) {
					cteaut.setCpfcnpj(cte.getCTe().getInfCte().getAutXML().get(au).getCNPJ());
				}
				if (cte.getCTe().getInfCte().getAutXML().get(au).getCPF() != null) {
					cteaut.setCpfcnpj(cte.getCTe().getInfCte().getAutXML().get(au).getCPF());
				}
				fsCteAutRp.save(cteaut);
			}
		}
	}
}