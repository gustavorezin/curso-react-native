package com.midas.api.tenant.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdCartao;
import com.midas.api.tenant.entity.CdPlconMicro;
import com.midas.api.tenant.entity.FnCartao;
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.repository.CdPlconMicroRepository;
import com.midas.api.tenant.repository.FnCartaoRepository;
import com.midas.api.tenant.repository.FnCxmvRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.util.DataUtil;

@Service
public class FnCartaoService {
	@Autowired
	private FnCartaoRepository fnCartaoRp;
	@Autowired
	private FnTituloService fnTituloService;
	@Autowired
	private FnCxmvService fnCxmvService;
	@Autowired
	private FnCxmvRepository fnCxmvRp;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private CdPlconMicroRepository cdPlconMicroRp;
	@Autowired
	private FnDreService fnDreService;

	// Calculos e taxas
	public FnCartao regCalculo(FnCartao fn) {
		// Data previsao cair valor
		CdCartao cart = fn.getCdcartao();
		Date dataprev = DataUtil.addRemDias(fn.getDatacad(), cart.getDias(), "A");
		fn.setDataprev(dataprev);
		// Se recebido ou pago conta
		if (!fn.getFncxmv().getFntitulo().getTipo().equals("R")) {
			fn.setPtaxa(new BigDecimal(0));
			fn.setVtaxa(new BigDecimal(0));
		}
		fn.setStatus("01");
		return fnCartaoRp.save(fn);
	}

	// Calculos e taxas
	public FnCartao regCalculoParcela(FnCxmv c, FnCartao cc, Date databaixa, int i, int conta) {
		BigDecimal valorZero = new BigDecimal(0);
		// Data previsao cair valor
		CdCartao cart = cc.getCdcartao();
		// Data previsao cair valor
		java.util.Date dataprev = DataUtil.addRemDias(databaixa, cart.getDias(), "A");
		dataprev = DataUtil.addRemMeses(dataprev, conta, "A");
		// Rateio valores
		BigDecimal vParc = cc.getVsub().divide(new BigDecimal(i), 2, RoundingMode.HALF_EVEN);
		BigDecimal vDesc = cc.getVdesc().divide(new BigDecimal(i), 2, RoundingMode.HALF_EVEN);
		BigDecimal vJuro = cc.getVjuro().divide(new BigDecimal(i), 2, RoundingMode.HALF_EVEN);
		BigDecimal vTot = cc.getVtot().divide(new BigDecimal(i), 2, RoundingMode.HALF_EVEN);
		FnCxmv cx = fnCxmvService.regUnico(c.getCdpessoaemp(), c.getCdcaixa(), c.getFntitulo(), c.getFpag(), databaixa,
				c.getHoracad(), c.getInfo(), vParc, valorZero, vDesc, valorZero, vJuro, vTot, null, "PENDENTE", null,
				null);
		FnCartao fn = new FnCartao();
		fn.setFncxmv(cx);
		fn.setCdcartao(cart);
		fn.setDatacad(databaixa);
		fn.setDataprev(dataprev);
		fn.setQtdparc(i);
		fn.setParcnum(conta + 1);
		fn.setVsub(vParc);
		fn.setVdesc(vDesc);
		fn.setVjuro(vJuro);
		fn.setVtot(vTot);
		fn.setPtaxa(cart.getTaxa());
		// Calculo taxa
		BigDecimal vTaxa = vTot.multiply(cart.getTaxa()).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
		BigDecimal vFinal = vTot.subtract(vTaxa);
		fn.setVtaxa(vTaxa);
		fn.setVfinal(vFinal);
		// Se recebido ou pago conta
		if (!fn.getFncxmv().getFntitulo().getTipo().equals("R")) {
			fn.setPtaxa(new BigDecimal(0));
			fn.setVtaxa(new BigDecimal(0));
		}
		fn.setStatus("01");
		return fnCartaoRp.save(fn);
	}

	// Grava operacao e taxas
	public void regOperacao(FnCartao c, Integer transacao, String status) throws ParseException {
		BigDecimal valorZero = new BigDecimal(0);
		// DEVOLVE PENDENTE / RECUSADO / DEVOLVIDO / ESTORNO --------------
		if (!status.equals("02")) {
			// Cancela lancamentos
			List<FnCxmv> obj = fnCxmvRp.getTaxaSimilar(c.getVtaxa(), c.getFncxmv().getCdcaixa().getId(), "99", "P",
					c.getTransacao());
			for (FnCxmv cx : obj) {
				fnCxmvRp.delete(cx);
				fnTituloRp.delete(cx.getFntitulo());
			}
			// Atualiza Ativo no caixa se todos estiverem como 02 - Entrada confirmada
			if (fnCartaoRp.verificaEntradaTodos(c.getFncxmv().getId()) != 0) {
				fnCxmvRp.atualizarStatusTransacao("PENDENTE", null, new java.sql.Date(c.getDatacad().getTime()),
						c.getFncxmv().getId());
			}
		}
		// ENTRADA CONFIRMADA---------------------------------------------
		if (status.equals("02")) {
			FnCxmv fnCxmv = c.getFncxmv();
			String tipo = "P";
			String ref = "TAXA DE OP. CARTOES DE CREDITO, DEBITO OU VALE";
			Integer idplanos = 0;
			CdPlconMicro pms = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("05", fnCxmv.getCdpessoaemp().getId());// Saida
			if (pms != null) {
				idplanos = pms.getId();
			}
			// Novo movimento para pagamento da taxa
			if (c.getFncxmv().getFntitulo().getTipo().equals("R")) {
				FnTitulo titulo = fnTituloService.regUnicoTitulo(fnCxmv.getCdpessoaemp(), tipo, fnCxmv.getCdpessoaemp(),
						fnCxmv.getCdcaixa(), fnCxmv.getFpag(), ref, c.getDataprev(), c.getDataprev(), c.getDataprev(),
						c.getDataprev(), "00", 0L, 0L, "", 0L, 0, 1, 1, c.getVtaxa(), valorZero, valorZero, valorZero,
						valorZero, valorZero, valorZero, c.getVtaxa(), c.getVtaxa(), valorZero, "", "N", "N", "N",
						valorZero, "N", "N", 0L, valorZero, valorZero, "S");
				fnDreService.dreFnTitulo100(titulo, idplanos);
				FnCxmv fns = fnCxmvService.regUnico(fnCxmv.getCdpessoaemp(), fnCxmv.getCdcaixa(), titulo, "99",
						c.getDataprev(), c.getFncxmv().getHoracad(), "", c.getVtaxa(), valorZero, valorZero, valorZero,
						valorZero, c.getVtaxa(), transacao, "ATIVO", null, null);
				fnDreService.dreFnCxmv100(fns, idplanos);
			}
			// Atualiza Ativo no caixa se todos estiverem como 02 - Entrada confirmada
			if (fnCartaoRp.verificaEntradaTodos(fnCxmv.getId()) == 0) {
				fnCxmvRp.atualizarStatusTransacao("ATIVO", transacao, new java.sql.Date(c.getDataprev().getTime()),
						c.getFncxmv().getId());
			}
		}
	}
}
