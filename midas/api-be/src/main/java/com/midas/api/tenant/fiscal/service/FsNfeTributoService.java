package com.midas.api.tenant.fiscal.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.mt.entity.CdNcm;
import com.midas.api.mt.repository.CdNcmRepository;
import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.CdNfeCfgSimples;
import com.midas.api.tenant.entity.FsNfeItem;
import com.midas.api.tenant.entity.LcDocItem;
import com.midas.api.tenant.repository.FsNfeItemCofinsRepository;
import com.midas.api.tenant.repository.FsNfeItemIcmsRepository;
import com.midas.api.tenant.repository.FsNfeItemIpiRepository;
import com.midas.api.tenant.repository.FsNfeItemPisRepository;
import com.midas.api.tenant.repository.FsNfeItemRepository;
import com.midas.api.tenant.repository.FsNfeTotIcmsRepository;
import com.midas.api.tenant.repository.LcDocItemRepository;

@Service
public class FsNfeTributoService {
	@Autowired
	private LcDocItemRepository lcDocItemRp;
	@Autowired
	private FsNfeTotIcmsRepository fsNfeTotIcmsRp;
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
	private CdNcmRepository cdNcmRp;

	// TRIBUTOS DO ITEM DO DOC
	public void fsNfeTrib(CdNfeCfg nfecfg, CdNfeCfgSimples nfecfgsn, LcDocItem lcDocItem, FsNfeItem fsNfeItem,
			BigDecimal vFrete) throws Exception {
		// Totalizadores
		BigDecimal vbccredsn = BigDecimal.ZERO;
		BigDecimal vcredsn = BigDecimal.ZERO;
		BigDecimal vbcpis = BigDecimal.ZERO;
		BigDecimal vpis = BigDecimal.ZERO;
		BigDecimal vbcpisst = BigDecimal.ZERO;
		BigDecimal vpisst = BigDecimal.ZERO;
		BigDecimal vbccofins = BigDecimal.ZERO;
		BigDecimal vcofins = BigDecimal.ZERO;
		BigDecimal vbccofinsst = BigDecimal.ZERO;
		BigDecimal vcofinsst = BigDecimal.ZERO;
		BigDecimal vbcipi = BigDecimal.ZERO;
		BigDecimal vipi = BigDecimal.ZERO;
		BigDecimal vicms = BigDecimal.ZERO;
		BigDecimal vbcicms = BigDecimal.ZERO;
		BigDecimal vbcicmsst = BigDecimal.ZERO;
		BigDecimal vicmsst = BigDecimal.ZERO;
		BigDecimal vbcfcpst = BigDecimal.ZERO;
		BigDecimal vfcpst = BigDecimal.ZERO;
		BigDecimal vtottrib = BigDecimal.ZERO;
		// ICMS DIFAL
		BigDecimal vicmsdifremet = BigDecimal.ZERO;
		BigDecimal vicmsdif = BigDecimal.ZERO;
		BigDecimal vfcpdif = BigDecimal.ZERO;
		// IBPT
		BigDecimal vnacional = BigDecimal.ZERO;
		BigDecimal vimportado = BigDecimal.ZERO;
		BigDecimal vestadual = BigDecimal.ZERO;
		BigDecimal vmunicipal = BigDecimal.ZERO;
		// ISS
		BigDecimal vbciss = BigDecimal.ZERO;
		BigDecimal viss = BigDecimal.ZERO;
		// Calculo aliquota simples ------------------------------------------
		if (nfecfgsn != null) {
			if (nfecfgsn.getAliq().compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal[] retorno = vCreSn(lcDocItem, fsNfeItem, nfecfgsn);
				vbccredsn = retorno[0];
				vcredsn = retorno[1];
			}
		}
		// Calculo do Pis ----------------------------------------------------
		if (nfecfg.getPis_aliq().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] retorno = vPis(lcDocItem, fsNfeItem, nfecfg);
			vbcpis = retorno[0];
			vpis = retorno[1];
		}
		// Calculo do Pis ST -------------------------------------------------
		if (nfecfg.getPis_aliqst().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] retorno = vPisSt(lcDocItem, fsNfeItem, nfecfg);
			vbcpisst = retorno[0];
			vpisst = retorno[1];
		}
		// Calculo do Cofins -------------------------------------------------
		if (nfecfg.getCofins_aliq().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] retorno = vCofins(lcDocItem, fsNfeItem, nfecfg);
			vbccofins = retorno[0];
			vcofins = retorno[1];
		}
		// Calculo do Cofins ST ----------------------------------------------
		if (nfecfg.getCofins_aliqst().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] retorno = vCofinsSt(lcDocItem, fsNfeItem, nfecfg);
			vbccofinsst = retorno[0];
			vcofinsst = retorno[1];
		}
		// Calculo do IPI ----------------------------------------------------
		if (nfecfg.getIpi_aliq().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] retorno = vIpi(lcDocItem, fsNfeItem, nfecfg);
			vbcipi = retorno[0];
			vipi = retorno[1];
		}
		// Calculo do ICMS ---------------------------------------------------
		if (nfecfg.getIcms_aliq().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] retorno = vIcms(lcDocItem, fsNfeItem, nfecfg);
			vbcicms = retorno[0];
			vicms = retorno[1];
		}
		// Calculo do ICMS ST ------------------------------------------------
		if (nfecfg.getIcmsst_aliq().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] retorno = vIcmsSt(lcDocItem, fsNfeItem, nfecfg);
			vbcicmsst = retorno[0];
			vicmsst = retorno[1];
			vbcfcpst = retorno[2];
			vfcpst = retorno[3];
		}
		// Calculo do ICMS UF DESTINO
		// ---------------------------------------------------
		if (nfecfg.getPicmsufdest().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] retorno = vIcmsUFDest(lcDocItem, fsNfeItem, nfecfg);
			vicmsdif = retorno[0];
			vfcpdif = retorno[1];
			vicmsdifremet = retorno[2];
		}
		// Calculo imposto servicos---------------------------
		if (nfecfg.getIss_aliq_nfse().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] retorno = vISS(lcDocItem, nfecfg);
			vbciss = retorno[0];
			viss = retorno[1];
		}
		// Calculo total com tributos
		BigDecimal vtribcob = vipi.add(vicmsst).add(vfcpst);
		// Se documento--------------
		if (lcDocItem != null) {
			vtottrib = vtottrib.add(lcDocItem.getVtot()).add(vtribcob).add(vFrete);
			// Calculo IBPT
			BigDecimal[] vibpt = valoresIBPT(vtottrib, lcDocItem.getCdproduto().getNcm());
			vnacional = vibpt[0];
			vimportado = vibpt[1];
			vestadual = vibpt[2];
			vmunicipal = vibpt[3];
			// Atualiza todos os tributos
			lcDocItemRp.updateTributos(vbccredsn, nfecfgsn.getAliq(), vcredsn, vbcpis, nfecfg.getPis_aliq(), vpis,
					vbcpisst, nfecfg.getPis_aliqst(), vpisst, vbccofins, nfecfg.getCofins_aliq(), vcofins, vbccofinsst,
					nfecfg.getCofins_aliqst(), vcofinsst, vbcipi, nfecfg.getIpi_aliq(), vipi, vbcicms,
					nfecfg.getIcms_aliq(), vicms, vbcicmsst, nfecfg.getIcmsst_aliq(), vicmsst, vFrete, vbcfcpst,
					nfecfg.getIcmsst_aliqfcp(), vfcpst, nfecfg.getPfcpufdest(), nfecfg.getPicmsufdest(),
					nfecfg.getPicmsinter(), nfecfg.getPicmsinterpart(), vfcpdif, vicmsdif, vicmsdifremet, vtottrib,
					vtribcob, vnacional, vimportado, vestadual, vmunicipal, vbciss, nfecfg.getIss_aliq_nfse(), viss,
					lcDocItem.getId());
		}
		// Se nota fiscal------------
		if (fsNfeItem != null) {
			vtottrib = fsNfeItem.getVtot().add(vtribcob).add(vFrete);
			// Calculo IBPT
			BigDecimal[] vibpt = valoresIBPT(vtottrib, fsNfeItem.getNcm());
			vnacional = vibpt[0];
			vimportado = vibpt[1];
			vestadual = vibpt[2];
			vmunicipal = vibpt[3];
			fsNfeItemRp.updateVtotTrib(vtribcob, vFrete, vnacional, vimportado, vestadual, vmunicipal,
					fsNfeItem.getId());
			fsNfeItemIcmsRp.updateTributos(nfecfg.getCst(), Integer.valueOf(nfecfg.getIcms_modbc()),
					nfecfg.getIcms_redbc(), vbcicms, nfecfg.getIcms_aliq(), vicms,
					Integer.valueOf(nfecfg.getIcmsst_modbc()), nfecfg.getIcmsst_mva(), nfecfg.getIcmsst_redbc(),
					vbcicmsst, nfecfg.getIcmsst_aliq(), vicmsst, nfecfgsn.getAliq(), vcredsn, vbcfcpst,
					nfecfg.getIcmsst_aliqfcp(), vfcpst, vfcpdif, vicmsdif, vicmsdifremet,
					fsNfeItem.getFsnfeitemicms().getId());
			fsNfeItemPisRp.updateTributos(nfecfg.getPis(), vbcpis, nfecfg.getPis_aliq(), vpis, vbcpisst,
					nfecfg.getPis_aliqst(), vpisst, fsNfeItem.getFsnfeitempis().getId());
			fsNfeItemCofinsRp.updateTributos(nfecfg.getCofins(), vbccofins, nfecfg.getCofins_aliq(), vcofins,
					vbccofinsst, nfecfg.getCofins_aliqst(), vcofinsst, fsNfeItem.getFsnfeitemcofins().getId());
			fsNfeItemIpiRp.updateTributos(nfecfg.getIpi(), vbcipi, nfecfg.getIpi_aliq(), vipi,
					fsNfeItem.getFsnfeitemipi().getId());
			// Calculo geral
			fsNfeTotIcmsRp.updateTributos(fsNfeItem.getFsnfe().getId());
		}
	}

	// CALCULO ALIQUOTA SIMPLES NACIONAL ***********************************
	private BigDecimal[] vCreSn(LcDocItem lcDocItem, FsNfeItem fsNfeItem, CdNfeCfgSimples nfecfgsn) {
		BigDecimal retorno[] = new BigDecimal[2];
		BigDecimal vbccredsn = BigDecimal.ZERO;
		if (lcDocItem != null) {
			vbccredsn = lcDocItem.getVtot();
		}
		if (fsNfeItem != null) {
			vbccredsn = fsNfeItem.getVtot();
		}
		BigDecimal vcredsn = vbccredsn.multiply(nfecfgsn.getAliq()).divide(new BigDecimal(100));
		retorno[0] = vbccredsn;
		retorno[1] = vcredsn;
		return retorno;
	}

	// CALCULO PIS *********************************************************
	private BigDecimal[] vPis(LcDocItem lcDocItem, FsNfeItem fsNfeItem, CdNfeCfg nfecfg) {
		BigDecimal retorno[] = new BigDecimal[2];
		BigDecimal vbcpis = BigDecimal.ZERO;
		if (lcDocItem != null) {
			vbcpis = lcDocItem.getVtot();
		}
		if (fsNfeItem != null) {
			vbcpis = fsNfeItem.getVtot();
		}
		// Se remove Icms do Vbc Pis
		if (nfecfg.getAplicadescpc().equals("S")) {
			BigDecimal vicms = vIcms(lcDocItem, fsNfeItem, nfecfg)[1];
			vbcpis = vbcpis.subtract(vicms);
		}
		BigDecimal vpis = vbcpis.multiply(nfecfg.getPis_aliq()).divide(new BigDecimal(100));
		retorno[0] = vbcpis;
		retorno[1] = vpis.setScale(2, RoundingMode.HALF_UP);
		return retorno;
	}

	// CALCULO PIS ST ******************************************************
	private BigDecimal[] vPisSt(LcDocItem lcDocItem, FsNfeItem fsNfeItem, CdNfeCfg nfecfg) {
		BigDecimal retorno[] = new BigDecimal[2];
		BigDecimal vbcpisst = BigDecimal.ZERO;
		if (lcDocItem != null) {
			vbcpisst = lcDocItem.getVtot();
		}
		if (fsNfeItem != null) {
			vbcpisst = fsNfeItem.getVtot();
		}
		// Se remove Icms do Vbc Pis
		if (nfecfg.getAplicadescpc().equals("S")) {
			BigDecimal vicms = vIcms(lcDocItem, fsNfeItem, nfecfg)[1];
			vbcpisst = vbcpisst.subtract(vicms);
		}
		BigDecimal vpisst = vbcpisst.multiply(nfecfg.getPis_aliqst()).divide(new BigDecimal(100));
		retorno[0] = vbcpisst;
		retorno[1] = vpisst.setScale(2, RoundingMode.HALF_UP);
		return retorno;
	}

	// CALCULO COFINS ******************************************************
	private BigDecimal[] vCofins(LcDocItem lcDocItem, FsNfeItem fsNfeItem, CdNfeCfg nfecfg) {
		BigDecimal retorno[] = new BigDecimal[2];
		BigDecimal vbccofins = BigDecimal.ZERO;
		if (lcDocItem != null) {
			vbccofins = lcDocItem.getVtot();
		}
		if (fsNfeItem != null) {
			vbccofins = fsNfeItem.getVtot();
		}
		// Se remove Icms do Vbc Pis
		if (nfecfg.getAplicadescpc().equals("S")) {
			BigDecimal vicms = vIcms(lcDocItem, fsNfeItem, nfecfg)[1];
			vbccofins = vbccofins.subtract(vicms);
		}
		BigDecimal vcofins = vbccofins.multiply(nfecfg.getCofins_aliq()).divide(new BigDecimal(100));
		retorno[0] = vbccofins;
		retorno[1] = vcofins.setScale(2, RoundingMode.HALF_UP);
		return retorno;
	}

	// CALCULO COFINS *******************************************************
	private BigDecimal[] vCofinsSt(LcDocItem lcDocItem, FsNfeItem fsNfeItem, CdNfeCfg nfecfg) {
		BigDecimal retorno[] = new BigDecimal[2];
		BigDecimal vbccofinsst = BigDecimal.ZERO;
		if (lcDocItem != null) {
			vbccofinsst = lcDocItem.getVtot();
		}
		if (fsNfeItem != null) {
			vbccofinsst = fsNfeItem.getVtot();
		}
		// Se remove Icms do Vbc Pis
		if (nfecfg.getAplicadescpc().equals("S")) {
			BigDecimal vicms = vIcms(lcDocItem, fsNfeItem, nfecfg)[1];
			vbccofinsst = vbccofinsst.subtract(vicms);
		}
		BigDecimal vcofinsst = vbccofinsst.multiply(nfecfg.getCofins_aliqst()).divide(new BigDecimal(100));
		retorno[0] = vbccofinsst;
		retorno[1] = vcofinsst.setScale(2, RoundingMode.HALF_UP);
		return retorno;
	}

	// CALCULO ICMS ********************************************************
	private BigDecimal[] vIcms(LcDocItem lcDocItem, FsNfeItem fsNfeItem, CdNfeCfg nfecfg) {
		BigDecimal retorno[] = new BigDecimal[2];
		BigDecimal vbcicms = BigDecimal.ZERO;
		if (lcDocItem != null) {
			vbcicms = lcDocItem.getVtot().setScale(2, RoundingMode.HALF_UP);
		}
		if (fsNfeItem != null) {
			vbcicms = fsNfeItem.getVtot().setScale(2, RoundingMode.HALF_UP);
		}
		// Calculo RBC de houver -----------------
		if (nfecfg.getIcms_redbc().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal vBc = vbcicms;
			vBc = vBc.multiply(nfecfg.getIcms_redbc()).divide(new BigDecimal(100));
			vbcicms = vbcicms.subtract(vBc);
		}
		// Calculo do MVA ------------------------
		if (nfecfg.getIcms_mva().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal vmva = BigDecimal.ZERO;
			vmva = vbcicms.multiply(nfecfg.getIcms_mva()).divide(new BigDecimal(100));
			vbcicms = vbcicms.add(vmva);
		}
		BigDecimal vicms = vbcicms.multiply(nfecfg.getIcms_aliq()).divide(new BigDecimal(100));
		retorno[0] = vbcicms.setScale(2, RoundingMode.HALF_UP);
		;
		retorno[1] = vicms.setScale(2, RoundingMode.HALF_UP);
		return retorno;
	}

	// CALCULO ICMS ST *****************************************************
	private BigDecimal[] vIcmsSt(LcDocItem lcDocItem, FsNfeItem fsNfeItem, CdNfeCfg nfecfg) {
		BigDecimal retorno[] = new BigDecimal[4];
		BigDecimal vprods = BigDecimal.ZERO;
		BigDecimal vbcicmsst = BigDecimal.ZERO;
		BigDecimal vicmsst = BigDecimal.ZERO;
		BigDecimal vicms = BigDecimal.ZERO;
		BigDecimal vbcfcpst = BigDecimal.ZERO;
		BigDecimal vfcpst = BigDecimal.ZERO;
		BigDecimal vipi = BigDecimal.ZERO;
		String modfrete = "0";
		BigDecimal vfrete = BigDecimal.ZERO;
		if (lcDocItem != null) {
			vprods = lcDocItem.getVtot().setScale(2, RoundingMode.HALF_UP);
			;
			vipi = lcDocItem.getVipi();
			modfrete = lcDocItem.getLcdoc().getModfrete();
			vfrete = lcDocItem.getVfrete();
		}
		if (fsNfeItem != null) {
			vprods = fsNfeItem.getVtot().setScale(2, RoundingMode.HALF_UP);
			vipi = fsNfeItem.getFsnfeitemipi().getVipi();
			modfrete = fsNfeItem.getFsnfe().getFsnfefrete().getModfrete();
			vfrete = fsNfeItem.getVfrete();
		}
		// Calculo RBC de houver -----------------
		if (nfecfg.getIcmsst_redbc().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal vBc = vprods;
			vBc = vBc.multiply(nfecfg.getIcmsst_redbc()).divide(new BigDecimal(100));
			vprods = vprods.subtract(vBc);
		}
		vbcicmsst = vprods.add(vipi);
		// Verifica cobranca frete - FOB ---------
		if (modfrete.equals("1")) {
			vbcicmsst = vbcicmsst.add(vfrete);
		}
		// Calculo do MVA ------------------------
		if (nfecfg.getIcmsst_mva().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal vmva = BigDecimal.ZERO;
			vmva = vbcicmsst.multiply(nfecfg.getIcmsst_mva()).divide(new BigDecimal(100));
			vbcicmsst = vbcicmsst.add(vmva);
		}
		// Calculo ICMS ST com aliquota ----------
		vicmsst = vbcicmsst.multiply(nfecfg.getIcmsst_aliq()).divide(new BigDecimal(100));
		// Diminuindo o ICMS ---------------------
		vicms = vIcms(lcDocItem, fsNfeItem, nfecfg)[1];
		vicmsst = vicmsst.subtract(vicms);
		// Calculo FCP se houver -----------------
		if (nfecfg.getIcmsst_aliqfcp().compareTo(BigDecimal.ZERO) > 0) {
			vbcfcpst = vbcicmsst;
			vfcpst = vbcfcpst.multiply(nfecfg.getIcmsst_aliqfcp()).divide(new BigDecimal(100));
		}
		retorno[0] = vbcicmsst.setScale(2, RoundingMode.HALF_UP);
		retorno[1] = vicmsst.setScale(2, RoundingMode.HALF_UP);
		retorno[2] = vbcfcpst;
		retorno[3] = vfcpst.setScale(2, RoundingMode.HALF_UP);
		return retorno;
	}

	// CALCULO ICMS UF
	// DESTINO********************************************************
	private BigDecimal[] vIcmsUFDest(LcDocItem lcDocItem, FsNfeItem fsNfeItem, CdNfeCfg nfecfg) {
		BigDecimal retorno[] = new BigDecimal[3];
		// VBC e a mesma do ICMS normal e do FCP tambem
		BigDecimal vbcicmsdest = BigDecimal.ZERO;
		BigDecimal vicmsdest = BigDecimal.ZERO;
		BigDecimal vicmsufs = BigDecimal.ZERO;
		BigDecimal vicmsdifremet = BigDecimal.ZERO;
		BigDecimal vicmsdif = BigDecimal.ZERO;
		BigDecimal vfcpdif = BigDecimal.ZERO;
		BigDecimal vbcicmsremet = BigDecimal.ZERO;
		BigDecimal vicmsremet = BigDecimal.ZERO;
		BigDecimal vicmsufsremet = BigDecimal.ZERO;
		BigDecimal picmsremet = BigDecimal.ZERO;
		if (lcDocItem != null) {
			vbcicmsdest = lcDocItem.getVtot();
			vbcicmsremet = lcDocItem.getVtot();
		}
		if (fsNfeItem != null) {
			vbcicmsdest = fsNfeItem.getVtot();
			vbcicmsremet = fsNfeItem.getVtot();
		}
		// Verifica BC e seu percentual(geralmente 100 porc.)
		if (nfecfg.getPicmsinterpart().compareTo(BigDecimal.ZERO) > 0) {
			vbcicmsdest = vbcicmsdest.multiply(nfecfg.getPicmsinterpart()).divide(new BigDecimal(100)).setScale(6,
					RoundingMode.HALF_UP);
		}
		// Calculo ICMS na UF-----------------
		if (nfecfg.getPicmsufdest().compareTo(BigDecimal.ZERO) > 0) {
			vicmsdest = vbcicmsdest.multiply(nfecfg.getPicmsufdest()).divide(new BigDecimal(100)).setScale(6,
					RoundingMode.HALF_UP);
		}
		// Calculo ICMS das UFs Envolvidas----
		if (nfecfg.getPicmsinter().compareTo(BigDecimal.ZERO) > 0) {
			vicmsufs = vbcicmsdest.multiply(nfecfg.getPicmsinter()).divide(new BigDecimal(100)).setScale(6,
					RoundingMode.HALF_UP);
		}
		vicmsdif = vicmsdest.subtract(vicmsufs);
		// Calculo FCP na UF Destino-----------------
		if (nfecfg.getPfcpufdest().compareTo(BigDecimal.ZERO) > 0) {
			vfcpdif = vbcicmsdest.multiply(nfecfg.getPfcpufdest()).divide(new BigDecimal(100)).setScale(6,
					RoundingMode.HALF_UP);
		}
		// Calculo ICMS na UF Remetente - Diferenca do resto
		// porc.##################################################################
		if (new BigDecimal(100).compareTo(nfecfg.getPicmsinterpart()) == 1) {
			picmsremet = new BigDecimal(100).subtract(nfecfg.getPicmsinterpart());
			vbcicmsremet = vbcicmsremet.multiply(picmsremet).divide(new BigDecimal(100)).setScale(6,
					RoundingMode.HALF_UP);
			if (nfecfg.getPicmsufdest().compareTo(BigDecimal.ZERO) > 0) {
				vicmsremet = vbcicmsremet.multiply(nfecfg.getPicmsufdest()).divide(new BigDecimal(100)).setScale(6,
						RoundingMode.HALF_UP);
			}
			// Calculo ICMS das UFs Remet. Envolvidas----
			if (nfecfg.getPicmsinter().compareTo(BigDecimal.ZERO) > 0) {
				vicmsufsremet = vbcicmsremet.multiply(nfecfg.getPicmsinter()).divide(new BigDecimal(100)).setScale(6,
						RoundingMode.HALF_UP);
			}
		}
		vicmsdifremet = vicmsremet.subtract(vicmsufsremet);
		retorno[0] = vicmsdif.setScale(2, RoundingMode.HALF_UP);
		retorno[1] = vfcpdif.setScale(2, RoundingMode.HALF_UP);
		retorno[2] = vicmsdifremet.setScale(2, RoundingMode.HALF_UP);
		return retorno;
	}

	// CALCULO IPI *********************************************************
	private BigDecimal[] vIpi(LcDocItem lcDocItem, FsNfeItem fsNfeItem, CdNfeCfg nfecfg) {
		BigDecimal retorno[] = new BigDecimal[2];
		BigDecimal vbcipi = BigDecimal.ZERO;
		if (lcDocItem != null) {
			vbcipi = lcDocItem.getVtot();
		}
		if (fsNfeItem != null) {
			vbcipi = fsNfeItem.getVtot();
		}
		BigDecimal vipi = vbcipi.multiply(nfecfg.getIpi_aliq()).divide(new BigDecimal(100));
		retorno[0] = vbcipi;
		retorno[1] = vipi.setScale(2, RoundingMode.HALF_UP);
		return retorno;
	}

	// CALCULO IBPT
	private BigDecimal[] valoresIBPT(BigDecimal valor, String ncmitem) throws Exception {
		BigDecimal retorno[] = new BigDecimal[4];
		BigDecimal vnacional = BigDecimal.ZERO;
		BigDecimal vimportado = BigDecimal.ZERO;
		BigDecimal vestadual = BigDecimal.ZERO;
		BigDecimal vmunicipal = BigDecimal.ZERO;
		CdNcm ncm = cdNcmRp.findNcm(ncmitem);
		if (ncm != null) {
			// Nacional------------
			if (ncm.getIbpt_nacional().compareTo(BigDecimal.ZERO) > 0 && valor.compareTo(BigDecimal.ZERO) > 0) {
				vnacional = valor.multiply(ncm.getIbpt_nacional()).divide(new BigDecimal(100)).setScale(2,
						RoundingMode.HALF_UP);
			}
			// Importado-----------
			if (ncm.getIbpt_importado().compareTo(BigDecimal.ZERO) > 0 && valor.compareTo(BigDecimal.ZERO) > 0) {
				vimportado = valor.multiply(ncm.getIbpt_importado()).divide(new BigDecimal(100)).setScale(2,
						RoundingMode.HALF_UP);
			}
			// Estadual------------
			if (ncm.getIbpt_estadual().compareTo(BigDecimal.ZERO) > 0 && valor.compareTo(BigDecimal.ZERO) > 0) {
				vestadual = valor.multiply(ncm.getIbpt_estadual()).divide(new BigDecimal(100)).setScale(2,
						RoundingMode.HALF_UP);
			}
			// Municipal-----------
			if (ncm.getIbpt_municipal().compareTo(BigDecimal.ZERO) > 0 && valor.compareTo(BigDecimal.ZERO) > 0) {
				vmunicipal = valor.multiply(ncm.getIbpt_municipal()).divide(new BigDecimal(100)).setScale(2,
						RoundingMode.HALF_UP);
			}
		}
		retorno[0] = vnacional;
		retorno[1] = vimportado;
		retorno[2] = vestadual;
		retorno[3] = vmunicipal;
		return retorno;
	}

	// CALCULO ALIQUOTA IMPOSTO DE SERVICOS ***********************************
	private BigDecimal[] vISS(LcDocItem lcDocItem, CdNfeCfg nfecfg) {
		BigDecimal retorno[] = new BigDecimal[2];
		BigDecimal vbciss = BigDecimal.ZERO;
		BigDecimal viss = BigDecimal.ZERO;
		// Apenas servicos
		if (lcDocItem != null) {
			if (!lcDocItem.getCdproduto().getTipo().equals("PRODUTO")) {
				vbciss = lcDocItem.getVtot();
			}
		}
		if (nfecfg.getIss_aliq_nfse().compareTo(BigDecimal.ZERO) > 0) {
			viss = vbciss.multiply(nfecfg.getIss_aliq_nfse()).divide(new BigDecimal(100));
		}
		retorno[0] = vbciss;
		retorno[1] = viss;
		return retorno;
	}
}