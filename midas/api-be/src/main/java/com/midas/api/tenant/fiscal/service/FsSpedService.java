package com.midas.api.tenant.fiscal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsSped;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.FsNfeRepository;
import com.midas.api.tenant.repository.FsSpedRepository;
import com.midas.api.util.DataUtil;

@Service
public class FsSpedService {
	@Autowired
	private FsSpedRepository fsSpedRp;
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private FsSpedUtilService fsSpedUtilService;

	// CRIA DADOS INICIAIS PARA EMPRESAS*******************
	public void criaSpedPeriodo() throws Exception {
		// Verifica se ja existe
		Integer mes = Integer.valueOf(DataUtil.mesAtual());
		Integer ano = Integer.valueOf(DataUtil.anoAtual());
		List<CdPessoa> emps = cdPessoaRp.findAllByTipoTodosAtivo("EMPRESA", "ATIVO");
		for (CdPessoa e : emps) {
			FsSped sped = fsSpedRp.verificaSped(e.getId(), mes, ano);
			if (sped == null) {
				FsSped sp = new FsSped();
				sp.setCdpessoaemp(e);
				sp.setMes(mes);
				sp.setAno(ano);
				fsSpedRp.save(sp);
			}
		}
	}

	// FUNCAO PRINCIPAL GERA SPED****************************
	public byte[] geraSped(FsSped fssped) throws Exception {
		// CALCULOS DATAS INICIAL E FINAL---------------------------
		Date dtini = DataUtil.dUtil(fssped.getAno() + "-" + fssped.getMes() + "-01");
		Date dtfim = DataUtil.dUtil(DataUtil.ultimoDiaMes(dtini));
		List<FsNfe> fsnfe = fsNfeRp.listaNfeEntsai(fssped.getCdpessoaemp().getId(), dtini, dtfim);
		byte[] retorno = null;
		StringBuilder st = new StringBuilder();
		// 0000
		st.append(fsSpedUtilService.bloco0000(dtini, dtfim, fssped.getCdpessoaemp()));
		// 0001
		st.append(fsSpedUtilService.bloco0001());
		// 0005
		st.append(fsSpedUtilService.bloco0005(fssped.getCdpessoaemp()));
		// 0100
		st.append(fsSpedUtilService.bloco0100(fssped.getCdpessoaemp()));
		// 0150
		String[] lin0150 = fsSpedUtilService.bloco0150(dtini, dtfim, fssped.getCdpessoaemp());
		st.append(lin0150[0]);
		// 0190
		String[] lin0190 = fsSpedUtilService.bloco0190();
		st.append(lin0190[0]);
		// 0200
		String[] lin0200 = fsSpedUtilService.bloco0200(dtini, dtfim, fssped.getCdpessoaemp());
		st.append(lin0200[0]);
		// 0400
		String[] lin0400 = fsSpedUtilService.bloco0400(dtini, dtfim, fssped.getCdpessoaemp());
		st.append(lin0400[0]);
		// 0990
		st.append(fsSpedUtilService.bloco0990(st.toString()));
		// Bloco C
		String[] blocoC = fsSpedUtilService.blocoC(fsnfe, dtini, dtfim, fssped.getCdpessoaemp());
		st.append(blocoC[0]);
		// Bloco D
		String[] blocoD = fsSpedUtilService.blocoD(dtini, dtfim, fssped.getCdpessoaemp());
		st.append(blocoD[0]);
		// Bloco E
		StringBuilder blocoE = new StringBuilder();
		// E001
		blocoE.append(fsSpedUtilService.blocoE001());
		// E100
		/*
		 * blocoE.append(fsSpedUtilService.blocoE100(dtini, dtfim)); // E110
		 * blocoE.append(fsSpedUtilService.blocoE110()); // E111
		 * blocoE.append(fsSpedUtilService.blocoE111()); // E113
		 * blocoE.append(fsSpedUtilService.blocoE113()); // E116
		 * blocoE.append(fsSpedUtilService.blocoE116()); // E200
		 * blocoE.append(fsSpedUtilService.blocoE200()); // E210
		 * blocoE.append(fsSpedUtilService.blocoE210()); // E220
		 * blocoE.append(fsSpedUtilService.blocoE220()); // E230
		 * blocoE.append(fsSpedUtilService.blocoE230()); // E240
		 * blocoE.append(fsSpedUtilService.blocoE240()); // E250
		 * blocoE.append(fsSpedUtilService.blocoE250()); // E500
		 * blocoE.append(fsSpedUtilService.blocoE500()); // E510
		 * blocoE.append(fsSpedUtilService.blocoE510()); // E520
		 * blocoE.append(fsSpedUtilService.blocoE520());
		 */
		// E990
		blocoE.append(fsSpedUtilService.blocoE990(blocoE.toString()));
		st.append(blocoE);
		// Bloco G
		StringBuilder blocoG = new StringBuilder();
		// G001
		blocoG.append(fsSpedUtilService.blocoG001());
		// G990
		blocoG.append(fsSpedUtilService.blocoG990(blocoG.toString()));
		st.append(blocoG);
		// Bloco H
		StringBuilder blocoH = new StringBuilder();
		// H001
		blocoH.append(fsSpedUtilService.blocoH001());
		// H005
		blocoH.append(fsSpedUtilService.blocoH005(fssped.getCdpessoaemp(), dtfim));
		// E010
		blocoH.append(fsSpedUtilService.blocoH010(fssped.getCdpessoaemp(), dtfim));
		// H990
		blocoH.append(fsSpedUtilService.blocoH990(blocoH.toString()));
		st.append(blocoH);
		// Bloco K
		StringBuilder blocoK = new StringBuilder();
		// K001
		blocoK.append(fsSpedUtilService.blocoK001());
		// K100
		blocoK.append(fsSpedUtilService.blocoK100(dtini, dtfim));
		// K200
		blocoK.append(fsSpedUtilService.blocoK200(fssped.getCdpessoaemp(), dtfim));
		// K220
		// blocoK.append(fsSpedUtilService.blocoK220());
		// K230
		// blocoK.append(fsSpedUtilService.blocoK230(fssped.getCdpessoaemp(), dtini,
		// dtfim));
		// K250
		// blocoK.append(fsSpedUtilService.blocoK250(fssped.getCdpessoaemp(), dtini,
		// dtfim));
		// K270
		// blocoK.append(fsSpedUtilService.blocoK270());
		// K280
		blocoK.append(fsSpedUtilService.blocoK280(fssped.getCdpessoaemp(), dtini, dtfim));
		// K290
		// blocoK.append(fsSpedUtilService.blocoK290(fssped.getCdpessoaemp(), dtini,
		// dtfim));
		// K291
		// blocoK.append(fsSpedUtilService.blocoK291(fssped.getCdpessoaemp(), dtini,
		// dtfim));
		// K300
		// blocoK.append(fsSpedUtilService.blocoK300());
		// K301
		// blocoK.append(fsSpedUtilService.blocoK301());
		// K990
		blocoK.append(fsSpedUtilService.blocoK990(blocoK.toString()));
		st.append(blocoK);
		// Bloco 1
		StringBuilder bloco1 = new StringBuilder();
		// 1001
		bloco1.append(fsSpedUtilService.bloco1001());
		// 1010
		bloco1.append(fsSpedUtilService.bloco1010());
		// 1600
		String[] lin1600 = fsSpedUtilService.bloco1600(fssped.getCdpessoaemp(), dtini, dtfim);
		bloco1.append(lin1600[0]);
		// 1601
		String[] lin1601 = fsSpedUtilService.bloco1601(fssped.getCdpessoaemp(), dtini, dtfim);
		bloco1.append(lin1601[0]);
		// 1990
		bloco1.append(fsSpedUtilService.bloco1990(bloco1.toString()));
		st.append(bloco1);
		// Bloco 9
		StringBuilder bloco9 = new StringBuilder();
		// 9001
		bloco9.append(fsSpedUtilService.bloco9001());
		// 9900
		bloco9.append(fsSpedUtilService.bloco9900(lin0150[1], lin0190[1], lin0200[1], lin0400[1], blocoC[1]/* c100 */,
				blocoC[2]/* c110 */, blocoC[3]/* c113 */, blocoC[4]/* c140 */, blocoC[5]/* c141 */, blocoC[6]/* c160 */,
				blocoC[7]/* c170 */, blocoC[8]/* c190 */, blocoD[1]/* d100 */, blocoD[2]/* d190 */, lin1600[1],
				lin1601[1]));
		// 9990
		bloco9.append(fsSpedUtilService.bloco9990(bloco9.toString()));
		st.append(bloco9);
		// 9999
		st.append(fsSpedUtilService.bloco9999(st.toString()));
		// Remove campos null
		String stRet = st.toString();
		stRet = stRet.replaceAll("null", "");
		retorno = stRet.getBytes();
		// Atualiza gerado
		fssped.setGerado("S");
		fsSpedRp.save(fssped);
		return retorno;
	}
}
