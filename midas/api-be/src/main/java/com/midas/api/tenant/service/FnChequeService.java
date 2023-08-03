package com.midas.api.tenant.service;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdCaixa;
import com.midas.api.tenant.entity.FnCheque;
import com.midas.api.tenant.entity.FnChequeHist;
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.repository.CdCaixaRepository;
import com.midas.api.tenant.repository.FnChequeHistRepository;
import com.midas.api.util.NumUtil;

@Service
public class FnChequeService {
	@Autowired
	private FnChequeHistRepository fnChequeHistRp;
	@Autowired
	private CdCaixaRepository cdCaixaRp;
	@Autowired
	private FnTituloService fnTituloService;
	@Autowired
	private FnCxmvService fnCxmvService;

	// Registro de historico
	public FnChequeHist regHistorico(FnCheque fn, Integer cx, Long fncxmvini, Long fncxmvfim, Date data, String status,
			String tipo, String motivo) {
		CdCaixa cxa = cdCaixaRp.getById(cx);
		FnChequeHist c = new FnChequeHist();
		c.setFncheque(fn);
		c.setCdcaixa(cxa);
		c.setFncxmvini(fncxmvini);
		c.setFncxmvfim(fncxmvfim);
		c.setDatacad(data);
		c.setStatus(status);
		c.setMotivo(motivo);
		c.setInfo(montaInfo(tipo));
		return fnChequeHistRp.save(c);
	}
	
	// Cadastra fncxmv e fntitulo automatico
	public FnCheque cadastraFncxmvFntitulo(FnCheque fnCheque) {
		BigDecimal valorZero = BigDecimal.ZERO;
		Time hora = new Time(new Date().getTime());
		Integer transacao = NumUtil.geraNumAleaInteger();
		String tipo = fnCheque.getTipo().equals("01") ? "P" : "R";
		FnTitulo titulo = fnTituloService.regUnicoTitulo(fnCheque.getFncxmv().getCdpessoaemp(), tipo,
				fnCheque.getFncxmv().getCdpessoaemp(), fnCheque.getFncxmv().getCdcaixa(), "02",
				"ENTRADA DE CHEQUE MANUAL", fnCheque.getDatacad(), fnCheque.getDatacad(), fnCheque.getDatacad(),
				fnCheque.getDatacad(), "00", 0L, 0L, "", 0L, 0, 1, 1, valorZero, valorZero, valorZero,
				valorZero, valorZero, valorZero, valorZero, valorZero, valorZero,
				valorZero, fnCheque.getInfo(), "N", "N", "N", valorZero, "N", "N", 0L, valorZero, valorZero, "N");
		FnCxmv fnCxmv = fnCxmvService.regUnico(titulo.getCdpessoaemp(), titulo.getCdcaixapref(), titulo, titulo.getFpagpref(),
				titulo.getDatabaixa(), hora, titulo.getInfo(), titulo.getVparc(), valorZero, titulo.getVdesc(), valorZero, titulo.getVjuro(),
				titulo.getVsaldo(), transacao, "INATIVO", null, null);
		fnCheque.setFncxmv(fnCxmv);
		return fnCheque;
	}

	private String montaInfo(String tipo) {
		String montatexto = "";
		// Transferencia
		if (tipo.equals("T")) {
			montatexto = "TRANSFERENCIA ENTRE CAIXAS OU CONTAS BANCARIAS";
		}
		// Compensacao
		if (tipo.equals("C")) {
			montatexto = "COMPENSACAO DO CHEQUE PELO EMITENTE, BANCO OU OPERADORA";
		}
		// Troco de caixa
		if (tipo.equals("TR")) {
			montatexto = "TROCO DE OPERACAO DE CAIXA";
		}
		// Cancelamento
		if (tipo.equals("CL")) {
			montatexto = "CANCELAMENTO DE CHEQUE OU TROCA POR VALORES";
		}
		// Pagamento de conta
		if (tipo.equals("PC")) {
			montatexto = "UTILIZADO COMO PAGAMENTO DE CONTA(S)";
		}
		// Estorno de troco ou cheque em caixa
		if (tipo.equals("ET")) {
			montatexto = "CHEQUE ESTORNADO VIA MOVIMENTACAO DE CAIXA";
		}
		return montatexto;
	}
}
