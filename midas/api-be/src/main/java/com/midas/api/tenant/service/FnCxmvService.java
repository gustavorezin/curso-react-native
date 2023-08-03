package com.midas.api.tenant.service;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.security.ClienteParam;
import com.midas.api.tenant.entity.CdCaixa;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdPlconMicro;
import com.midas.api.tenant.entity.FnCartao;
import com.midas.api.tenant.entity.FnCheque;
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnCxmvDTO;
import com.midas.api.tenant.entity.FnCxmvDre;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FnTituloDre;
import com.midas.api.tenant.repository.CdCaixaRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdPlconMicroRepository;
import com.midas.api.tenant.repository.FnChequeRepository;
import com.midas.api.tenant.repository.FnCxmvRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.NumUtil;

@Service
public class FnCxmvService {
	@Autowired
	private FnCxmvRepository fnCxmvRp;
	@Autowired
	private FnCxmvService fnCxmvService;
	@Autowired
	private FnChequeService fnChequeService;
	@Autowired
	private FnChequeRepository fnChequeRp;
	@Autowired
	private FnTituloService fnTituloService;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdCaixaRepository cdCaixaRp;
	@Autowired
	private FnDreService fnDreService;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private CdPlconMicroRepository cdPlconMicroRp;
	@Autowired
	private FnCcustoService fnCcustoService;

	// Registro de Fncx apenas neste local quando necessario
	public FnCxmv regUnico(CdPessoa cdpessoaemp, CdCaixa cdcaixa, FnTitulo titulo, String fpag, Date datacad,
			Time horacad, String info, BigDecimal vsub, BigDecimal pdesc, BigDecimal vdesc, BigDecimal pjuro,
			BigDecimal vjuro, BigDecimal vtot, Integer transacao, String status, List<FnCheque> fnchequeitem,
			List<FnCartao> fncartaoitem) {
		FnCxmv c = new FnCxmv();
		c.setCdpessoaemp(cdpessoaemp);
		c.setCdcaixa(cdcaixa);
		c.setFntitulo(titulo);
		c.setFpag(fpag);
		c.setDatacad(datacad);
		c.setHoracad(horacad);
		c.setInfo(info);
		c.setVsub(vsub);
		c.setPdesc(pdesc);
		c.setVdesc(vdesc);
		c.setPjuro(pjuro);
		c.setVjuro(vjuro);
		c.setVtot(vtot);
		c.setTransacao(transacao);
		c.setStatus(status);
		c.setFnchequeitem(fnchequeitem);
		c.setFncartaoitem(fncartaoitem);
		return fnCxmvRp.save(c);
	}

	// Movimenta transferencia de cheques
	// *****************************************************************
	public void regTransfereCheques(Long local, Integer caixa, List<FnCheque> cheques, String data, String status,
			String motivo) {
		CdPessoa cdpessoaemps = cheques.get(0).getCdpessoaempatual();
		CdCaixa cdcaixas = cheques.get(0).getCdcaixaatual();
		BigDecimal vlrChequesMov = new BigDecimal(0);
		BigDecimal valorZero = new BigDecimal(0);
		for (FnCheque c : cheques) {
			// Verifica status e local para gerar alteracoes
			String st = c.getStatus();
			if (!status.equals("X")) {
				st = status;
			}
			fnChequeRp.transferirCheque(local, caixa, st, c.getId());
			if (!c.getStatus().equals(st) || !c.getCdpessoaempatual().getId().equals(local)
					|| !c.getCdcaixaatual().getId().equals(caixa)) {
				// Totais
				vlrChequesMov = vlrChequesMov.add(c.getVtot());
			}
		}
		// ENTRADA NO CAIXA FINAL----------------------------------------------
		String ref = "TRANSFERENCIA - ENTRADA DE CHEQUES";
		CdPessoa cdpessoaempe = cdPessoaRp.getById(local);
		CdCaixa cdcaixae = cdCaixaRp.getById(caixa);
		java.sql.Date Datamv = java.sql.Date.valueOf(data);
		FnTitulo titulo = fnTituloService.regUnicoTitulo(cdpessoaempe, "TR", cdpessoaempe, cdcaixae, "99", ref, Datamv,
				Datamv, Datamv, Datamv, "00", 0L, 0L, "", 0L, 0, 1, 1, vlrChequesMov, valorZero, valorZero, valorZero,
				valorZero, valorZero, valorZero, vlrChequesMov, vlrChequesMov, valorZero, "", "N", "N", "N", valorZero,
				"N", "N", 0L, valorZero, valorZero, "S");
		FnCxmv fncxmvfim = fnCxmvService.regUnico(cdpessoaempe, cdcaixae, titulo, "99", Datamv,
				new Time(new Date().getTime()), motivo, vlrChequesMov, valorZero, valorZero, valorZero, valorZero,
				vlrChequesMov, null, "ATIVO", null, null);
		// SAIDA NO CAIXA FINAL----------------------------------------------
		String refs = "TRANSFERENCIA - SAIDA DE CHEQUES";
		FnTitulo titulos = fnTituloService.regUnicoTitulo(cdpessoaemps, "TP", cdpessoaemps, cdcaixas, "99", refs,
				Datamv, Datamv, Datamv, Datamv, "00", 0L, 0L, "", 0L, 0, 1, 1, vlrChequesMov, valorZero, valorZero,
				valorZero, valorZero, valorZero, valorZero, vlrChequesMov, vlrChequesMov, valorZero, "", "N", "N", "N",
				valorZero, "N", "N", 0L, valorZero, valorZero, "S");
		FnCxmv fncxmvini = fnCxmvService.regUnico(cdpessoaemps, cdcaixas, titulos, "99", Datamv,
				new Time(new Date().getTime()), motivo, vlrChequesMov, valorZero, valorZero, valorZero, valorZero,
				vlrChequesMov, null, "ATIVO", null, null);
		// CICLO PARA GERACAO DO FN_CXMV NO HISTORICO DO CHEQUE--------------------
		for (FnCheque c : cheques) {
			// Verifica status e local para gerar alteracoes
			String st = c.getStatus();
			if (!status.equals("X")) {
				st = status;
			}
			if (!c.getStatus().equals(st) || !c.getCdpessoaempatual().getId().equals(local)
					|| !c.getCdcaixaatual().getId().equals(caixa)) {
				fnChequeService.regHistorico(c, caixa, fncxmvini.getId(), fncxmvfim.getId(),
						java.sql.Date.valueOf(data), st, "T", motivo);
			}
		}
	}

	// REGISTRO DE TROCO DE CAIXA
	// ***********************************************************************
	public List<FnCxmv> regTrocoCaixa(BigDecimal vTroco, String databaixa, String troco, String fpagtroco,
			String infotroco, List<FnCxmv> fncxmvs) {
		FnTitulo titulo = fncxmvs.get(0).getFntitulo();
		java.sql.Date datamov = java.sql.Date.valueOf(databaixa);
		infotroco = CaracterUtil.remUpper(infotroco);
		BigDecimal valorZero = new BigDecimal(0);
		List<FnCxmv> fncxmvsaju = new ArrayList<FnCxmv>();
		for (FnCxmv c : fncxmvs) {
			if (c.getVtot().compareTo(vTroco) > 0 && vTroco.compareTo(new BigDecimal(0)) > 0) {
				// Desconta troco da primeira parcela ou da proxima
				BigDecimal vTot = c.getVtot().subtract(vTroco);
				c.setVsub(vTot);
				c.setVtot(vTot);
				Integer transacao = NumUtil.geraNumAleaInteger();
				// MOVIMENTO NORMAL DE TROCO
				if (troco.equals("S") && !fpagtroco.equals("05")) {
					// Lanca movimento
					String refentra = "OUTRAS ENTRADAS - TROCO TIT. ORIG. " + titulo.getId();
					String refsai = "OUTRAS SAIDAS - TROCO TIT. ORIG. " + titulo.getId();
					Integer idplanoe = 0;
					Integer idplanos = 0;
					CdPlconMicro pme = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("03", c.getCdpessoaemp().getId());// Entrada
					CdPlconMicro pms = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("04", c.getCdpessoaemp().getId());// Saida
					if (pme != null) {
						idplanoe = pme.getId();
					}
					if (pms != null) {
						idplanos = pms.getId();
					}
					// Entrada do troco-------------
					FnTitulo titulos = fnTituloService.regUnicoTitulo(c.getCdpessoaemp(), "R", c.getCdpessoaemp(),
							c.getCdcaixa(), fpagtroco, refentra, datamov, datamov, datamov, datamov, "00", 0L, 0L, "",
							0L, 0, 1, 1, vTroco, valorZero, valorZero, valorZero, valorZero, valorZero, valorZero,
							vTroco, vTroco, valorZero, infotroco, "N", "N", "S", valorZero, "N", "N", 0L, valorZero,
							valorZero, "S");
					fnDreService.dreFnTitulo100(titulos, idplanoe);
					// Centro de custos
					fnCcustoService.ccFnTituloCc(titulo, titulos);
					FnCxmv fnr = fnCxmvService.regUnico(c.getCdpessoaemp(), c.getCdcaixa(), titulos, fpagtroco, datamov,
							new Time(new Date().getTime()), infotroco, vTroco, valorZero, valorZero, valorZero,
							valorZero, vTroco, transacao, "ATIVO", null, null);
					fnDreService.dreFnCxmv100(fnr, idplanoe);
					// Saida do troco-------------
					FnTitulo titulos2 = fnTituloService.regUnicoTitulo(c.getCdpessoaemp(), "P", c.getCdpessoaemp(),
							c.getCdcaixa(), fpagtroco, refsai, datamov, datamov, datamov, datamov, "00", 0L, 0L, "", 0L,
							0, 1, 1, vTroco, valorZero, valorZero, valorZero, valorZero, valorZero, valorZero, vTroco,
							vTroco, valorZero, infotroco, "N", "N", "S", valorZero, "N", "N", 0L, valorZero, valorZero,
							"S");
					fnDreService.dreFnTitulo100(titulos2, idplanos);
					// Centro de custos
					fnCcustoService.ccFnTituloCc(titulo, titulos2);
					FnCxmv fns = fnCxmvService.regUnico(c.getCdpessoaemp(), c.getCdcaixa(), titulos2, fpagtroco,
							datamov, new Time(new Date().getTime()), infotroco, vTroco, valorZero, valorZero, valorZero,
							valorZero, vTroco, transacao, "ATIVO", null, null);
					fnDreService.dreFnCxmv100(fns, idplanos);
					// Lanca cheques troco - Verifica se ja tem ID - se cadastrado
					for (FnCheque ch : c.getFnchequeitem()) {
						if (ch.getId() != null) {
							if (ch.getSelemcaixatroco().equals("S")) {
								// Opcional para cheques usados em troco de caixa
								fnChequeService.regHistorico(ch, ch.getCdcaixaatual().getId(), fns.getId(), fnr.getId(),
										datamov, "05", "TR", "");
								fnChequeRp.atualizaStatusCheque("05", transacao, ch.getId());
							}
						}
					}
				}
				// LANCA CREDITOS
				if (troco.equals("S") && fpagtroco.equals("05")) {
					// Dados de entrada ou saida
					String ref = "RECEBIMENTO ANTECIPADO - CREDITO TIT. ORIG. " + titulo.getId();
					if (titulo.getTipo().equals("P")) {
						ref = "PAGAMENTO ANTECIPADO - CREDITO TIT. ORIG. " + titulo.getId();
					}
					FnTitulo titulos = fnTituloService.regUnicoTitulo(c.getCdpessoaemp(), titulo.getTipo(),
							titulo.getCdpessoapara(), c.getCdcaixa(), fpagtroco, ref, datamov, datamov, datamov,
							datamov, "00", 0L, 0L, "", 0L, 0, 1, 1, vTroco, valorZero, valorZero, valorZero, valorZero,
							valorZero, valorZero, vTroco, vTroco, valorZero, infotroco, "S", "N", "N", vTroco, "N", "N",
							0L, valorZero, valorZero, "S");
					// DREs
					for (FnTituloDre fd : titulo.getFntitulodreitem()) {
						fnDreService.dreFnTituloPerc(titulos, fd.getPvalor(), fd.getCdplconmicro().getId());
					}
					// Centro de custos
					fnCcustoService.ccFnTituloCc(titulo, titulos);
					FnCxmv fnCxmv = fnCxmvService.regUnico(c.getCdpessoaemp(), c.getCdcaixa(), titulos, fpagtroco,
							datamov, new Time(new Date().getTime()), infotroco, vTroco, valorZero, valorZero, valorZero,
							valorZero, vTroco, transacao, "ATIVO", null, null);
					// DRE
					for (FnTituloDre fd : titulo.getFntitulodreitem()) {
						fnDreService.dreFnCxmvPerc(fnCxmv, fd.getPvalor(), fd.getCdplconmicro().getId());
					}
				}
				// Zera troco para parar ao ajuste
				vTroco = new BigDecimal(0);
			}
			fncxmvsaju.add(c);
		}
		return fncxmvsaju;
	}

	// INFORMACAO PARA PAGAMENTO DE CONTA COM CHEQUES EM CAIXA
	// **************************************************
	public void reChequesEmCaixaUsados(FnCheque ch, FnCxmv c, Date datamov) {
		String motivo = "TIT. ORIG. " + c.getFntitulo().getId() + " DE " + c.getFntitulo().getCdpessoapara().getNome();
		fnChequeService.regHistorico(ch, ch.getCdcaixaatual().getId(), c.getId(), ch.getFncxmv().getId(), datamov, "03",
				"PC", motivo);
		fnChequeRp.atualizaStatusCheque("03", null, ch.getId());
	}

	// INFORMACAO PARA GERAR JUROS SEPARADOS NOS TITULOS
	// ********************************************************
	public void regJurosSepara(FnTitulo titulo, FnCxmv c, BigDecimal pJuro, BigDecimal vJuro, Integer transacao) {
		String tipo = "R";
		String ref = "OUTRAS ENTRADAS DE JUROS - TIT. ORIG. " + titulo.getId();
		Integer idplano = 0;
		BigDecimal valorZero = new BigDecimal(0);
		CdPlconMicro pm = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("01", c.getCdpessoaemp().getId());// Entrada
		if (titulo.getTipo().equals("P")) {
			tipo = "P";
			ref = "OUTRAS SAIDAS DE JUROS - TIT. ORIG. " + titulo.getId();
			pm = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("02", c.getCdpessoaemp().getId());// Plano saida de juro
		}
		// Se for nulo - fica sem plano mesmo - se nao add abaixo
		if (pm != null) {
			idplano = pm.getId();
		}
		FnTitulo tituloJuro = fnTituloService.regUnicoTitulo(c.getCdpessoaemp(), tipo, titulo.getCdpessoapara(),
				c.getCdcaixa(), "99", ref, c.getDatacad(), c.getDatacad(), c.getDatacad(), c.getDatacad(), "00", 0L, 0L,
				"", 0L, 0, 1, 1, vJuro, valorZero, valorZero, valorZero, valorZero, valorZero, valorZero, vJuro, vJuro,
				valorZero, "", "N", "N", "N", valorZero, "N", "N", 0L, valorZero, valorZero, "S");
		fnDreService.dreFnTituloPerc(tituloJuro, new BigDecimal(100), idplano);
		// Centro de custos
		fnCcustoService.ccFnTituloCc(titulo, tituloJuro);
		FnCxmv fnCxmv = fnCxmvService.regUnico(c.getCdpessoaemp(), c.getCdcaixa(), tituloJuro, "99", c.getDatacad(),
				new Time(new Date().getTime()), "", vJuro, valorZero, valorZero, valorZero, valorZero, vJuro, transacao,
				"ATIVO", null, null);
		fnDreService.dreFnCxmv100(fnCxmv, idplano);
	}

	// FUNCAO PRINCIPAL PARA SEPARACAO DE JUROS
	// *****************************************************************
	public void separaJuros(FnTitulo t, FnCxmv fnCxmv, List<FnCxmvDre> fnFnCxmvDreObj,
			List<FnTituloDre> fnFnTituloDreObj, FnTituloDre fnt, FnCxmvDre fndc, String local, Integer conta,
			Integer transacao) {
		boolean sepJuros = prm.cliente().getSiscfg().isSis_separa_juros_plcon();
		if (sepJuros == true && (t.getVjuro().compareTo(new BigDecimal(0)) > 0
				|| fnCxmv.getVjuro().compareTo(new BigDecimal(0)) > 0)) {
			fnCxmvService.regJurosSepara(t, fnCxmv, fnCxmv.getPjuro(), fnCxmv.getVjuro(), transacao);
			// Se vem por titulos de baixa ------------
			if (local.equals("01") || local.equals("04")) {
				// Remove juros apos
				fnTituloRp.removeJurosParcial(t.getId());
				fnCxmvRp.removeJuros(fnCxmv.getId());
				// Array para pegar percentual
				List<BigDecimal> percs = new ArrayList<BigDecimal>();
				for (FnTituloDre fnd : t.getFntitulodreitem()) {
					percs.add(fnd.getPvalor());
				}
			}
			// Se vem por negociacao ------------------
			if (local.equals("02")) {
				// Remove juros apos
				fnTituloRp.removeJurosParcial(t.getId());
				fnCxmvRp.removeJuros(fnCxmv.getId());
			}
			// Se vem por movimento manual de caixa ---
			if (local.equals("03")) {
				// Remove juros apos
				fnTituloRp.removeJuros(t.getId());
				fnCxmvRp.removeJuros(fnCxmv.getId());
			}
		}
	}

	// MONTA CAIXA DTO*****************************************
	public List<FnCxmvDTO> listaCxDTO(List<FnCxmv> listaValores) {
		List<FnCxmvDTO> listadto = new ArrayList<FnCxmvDTO>();
		for (FnCxmv c : listaValores) {
			FnCxmvDTO cd = new FnCxmvDTO();
			cd.setCdcaixa(c.getCdcaixa());
			cd.setCdpessoaemp(c.getCdpessoaemp());
			cd.setDataat(c.getDataat());
			cd.setDatacad(c.getDatacad());
			cd.setFntitulo(c.getFntitulo());
			cd.setFpag(c.getFpag());
			cd.setHoraat(c.getHoraat());
			cd.setHoracad(c.getHoracad());
			cd.setId(c.getId());
			cd.setInfo(c.getInfo());
			cd.setPdesc(c.getPdesc());
			cd.setPjuro(c.getPjuro());
			cd.setStatus(c.getStatus());
			cd.setTransacao(c.getTransacao());
			cd.setVdesc(c.getVdesc());
			cd.setVjuro(c.getVjuro());
			cd.setVsub(c.getVsub());
			cd.setVtot(c.getVtot());
			listadto.add(cd);
		}
		return listadto;
	}
}
