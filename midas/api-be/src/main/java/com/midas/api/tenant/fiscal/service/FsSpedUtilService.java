package com.midas.api.tenant.fiscal.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdCaixa;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.CdProdutoUnmed;
import com.midas.api.tenant.entity.EsEst;
import com.midas.api.tenant.entity.EsEstMov;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfeCobrDup;
import com.midas.api.tenant.entity.FsNfeFreteVol;
import com.midas.api.tenant.entity.FsNfeItem;
import com.midas.api.tenant.entity.FsNfeRef;
import com.midas.api.tenant.fiscal.util.FsTipoNomeUtil;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.repository.CdCaixaRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdProdutoRepository;
import com.midas.api.tenant.repository.CdProdutoUnmedRepository;
import com.midas.api.tenant.repository.EsEstMovRepository;
import com.midas.api.tenant.repository.EsEstRepository;
import com.midas.api.tenant.repository.FsCteRepository;
import com.midas.api.tenant.repository.FsNfeItemRepository;
import com.midas.api.tenant.repository.FsNfePagRepository;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.MoedaUtil;
import com.midas.api.util.NumUtil;
import com.midas.api.util.PessoaUtil;

@Service
public class FsSpedUtilService {
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private FsNfeItemRepository fsNfeItemRp;
	@Autowired
	private FsCteRepository fsCteRp;
	@Autowired
	private CdProdutoRepository cdProdutoRp;
	@Autowired
	private CdProdutoUnmedRepository cdProdutoUnmedRp;
	@Autowired
	private EsEstMovRepository esEstMovRp;
	@Autowired
	private CdCaixaRepository cdCaixaRp;
	@Autowired
	private EsEstRepository esEstRp;
	@Autowired
	private FsNfePagRepository fsNfePagRp;
	private static final String qL = System.getProperty("line.separator");

	// BLOCO 0
	// ####################################################################################
	// 0000 - ABERTURA E IDENTIFICACAO DA ENTIDADE*****************************
	public String bloco0000(Date ini, Date fim, CdPessoa emi) throws Exception {
		StringBuilder s = new StringBuilder();
		String dataini = DataUtil.dataPadraoBrSemBarra(ini);
		String datafim = DataUtil.dataPadraoBrSemBarra(fim);
		s.append("|0000|");
		s.append("011|"); // VERSAO lAYOUT
		s.append("0|"); // FINALIDADE DO ARQUIVO
		s.append(dataini + "|");
		s.append(datafim + "|");
		s.append(emi.getNome() + "|");
		s.append(emi.getCpfcnpj() + "|");
		s.append("|");// CPF
		s.append(emi.getCdestado().getUf() + "|");
		s.append(FsUtil.cpND(emi.getIerg(), "") + "|");
		s.append(emi.getCdcidade().getIbge() + "|");
		s.append(FsUtil.cpND(emi.getIm(), "") + "|");
		s.append(FsUtil.cpND(emi.getIsuf(), "") + "|");
		s.append("B|"); // PERFIL B
		s.append("0|"); // INDUSTRIAL APENAS
		s.append(qL);
		return s.toString();
	}

	// 0001 - ABERTURA DO BLOCO 0**********************************************
	public String bloco0001() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|0001|");
		s.append("0|"); // BLOCO COM DADOS INFORMADOS (1 = SEM)
		s.append(qL);
		return s.toString();
	}

	// 0005 - DADOS COMPLEMENTARES DA ENTIDADE*********************************
	public String bloco0005(CdPessoa emi) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|0005|");
		s.append(FsUtil.cpND(emi.getFantasia(), emi.getNome()) + "|");
		s.append(emi.getCep() + "|");
		s.append(emi.getRua() + "|");
		s.append(emi.getNum() + "|");
		s.append(FsUtil.cpND(emi.getComp(), "") + "|");
		s.append(emi.getBairro() + "|");
		s.append(emi.getFoneum() + "|");
		s.append("|"); // FAX
		s.append(emi.getEmail() + "|");
		s.append(qL);
		return s.toString();
	}

	// 0100 - DADOS DO CONTABILISTA********************************************
	public String bloco0100(CdPessoa emi) throws Exception {
		StringBuilder s = new StringBuilder();
		CdPessoa cont = cdPessoaRp.findFirstByTipoLocal("CONTADOR", "ATIVO", emi.getId());
		if (cont != null) {
			s.append("|0100|");
			s.append(cont.getNome() + "|");
			s.append((PessoaUtil.informaCPF_CNPJ(cont.getCpfcnpj()) == "CPF" ? cont.getCpfcnpj() : "") + "|");
			s.append(cont.getNumconreg() + "|");
			s.append((PessoaUtil.informaCPF_CNPJ(cont.getCpfcnpj()) == "CNPJ" ? cont.getCpfcnpj() : "") + "|");
			s.append(cont.getCep() + "|");
			s.append(cont.getRua() + "|");
			s.append(cont.getNum() + "|");
			s.append(FsUtil.cpND(cont.getComp(), "") + "|");
			s.append(cont.getBairro() + "|");
			s.append(cont.getFoneum() + "|");
			s.append("|"); // FAX
			s.append(cont.getEmail() + "|");
			s.append(cont.getCdcidade().getIbge() + "|");
		} else {
			s.append("|0100||||||||||||||"); // SE NAO TEM CONTADOR = TUDO EM BRANCO
		}
		s.append(qL);
		return s.toString();
	}

	// 0150 - TABELA DE CADASTRO DO PARTICIPANTE*******************************
	public String[] bloco0150(Date ini, Date fim, CdPessoa emi) throws Exception {
		StringBuilder s = new StringBuilder();
		List<CdPessoa> pes = cdPessoaRp.findAllByTodosBGCpfCnpj();
		for (CdPessoa p : pes) {
			s.append(bloco0150util(p));
		}
		// Bancos - especial bloco 1600 e 1601
		List<CdCaixa> bcs = cdCaixaRp.findAllByTipoLocal(emi.getId(), "02");
		for (CdCaixa c : bcs) {
			s.append(bloco0150utilBc(c));
		}
		String[] retorno = new String[2];
		retorno[0] = s.toString();
		retorno[1] = NumUtil.qtdLinhas(s.toString()).toString();
		return retorno;
	}

	public String bloco0150util(CdPessoa part) {
		StringBuilder s = new StringBuilder();
		s.append("|0150|");
		s.append(part.getCpfcnpj() + "|");
		s.append(part.getNome() + "|");
		s.append(part.getCodpais() + "|");
		s.append((PessoaUtil.informaCPF_CNPJ(part.getCpfcnpj()) == "CNPJ" ? part.getCpfcnpj() : "") + "|");
		s.append((PessoaUtil.informaCPF_CNPJ(part.getCpfcnpj()) == "CPF" ? part.getCpfcnpj() : "") + "|");
		s.append(FsUtil.cpND(CaracterUtil.remPout(part.getIerg()), "") + "|");
		s.append(part.getCdcidade().getIbge() + "|");
		s.append(FsUtil.cpND(part.getIsuf(), "") + "|");
		s.append(part.getRua() + "|");
		s.append(part.getNum() + "|");
		s.append(FsUtil.cpND(part.getComp(), "") + "|");
		s.append(part.getBairro() + "|");
		s.append(qL);
		return s.toString();
	}

	public String bloco0150utilBc(CdCaixa c) {
		StringBuilder s = new StringBuilder();
		s.append("|0150|");
		s.append("B_" + c.getCpfcnpj() + "|");
		s.append(c.getNome() + "|");
		s.append("1058|");
		s.append((PessoaUtil.informaCPF_CNPJ(c.getCpfcnpj()) == "CNPJ" ? c.getCpfcnpj() : "") + "|");
		s.append((PessoaUtil.informaCPF_CNPJ(c.getCpfcnpj()) == "CPF" ? c.getCpfcnpj() : "") + "|");
		s.append("|");
		s.append(c.getCdcidade().getIbge() + "|");
		s.append("|");
		s.append(c.getRua() + "|");
		s.append(c.getNum() + "|");
		s.append("|");
		s.append(c.getBairro() + "|");
		s.append(qL);
		return s.toString();
	}

	// 0190 - IDENTIFICACAO DAS UNIDADES DE MEDIDA*****************************
	public String[] bloco0190() throws Exception {
		StringBuilder s = new StringBuilder();
		List<CdProdutoUnmed> unmed = cdProdutoUnmedRp.findAll();
		for (CdProdutoUnmed u : unmed) {
			s.append("|0190|");
			s.append(u.getId() + "|");
			s.append(u.getSigla() + "|");
			s.append(qL);
		}
		String[] retorno = new String[2];
		retorno[0] = s.toString();
		retorno[1] = NumUtil.qtdLinhas(s.toString()).toString();
		return retorno;
	}

	// 0200 - TABELA DE IDENTIFICACAO DO ITEM (PRODUTO E SERVICOS)*************
	public String[] bloco0200(Date ini, Date fim, CdPessoa emi) throws Exception {
		StringBuilder s = new StringBuilder();
		List<CdProduto> ps = cdProdutoRp.findAllByTipo("PRODUTO");
		for (CdProduto i : ps) {
			Integer idprod = i.getCodigo();
			if (idprod == null) {
				idprod = 0;
			}
			String tipoitem = i.getTipoitem();
			s.append("|0200|");
			s.append(i.getCodigo() + "|");
			s.append(i.getNome() + "|");
			s.append(FsUtil.cpND(i.getCean(), "") + "|");
			s.append("|"); // CODIGO ANTERIOR DO ITEM
			s.append(i.getCdprodutounmed().getSigla() + "|");
			s.append(tipoitem + "|");
			s.append(i.getNcm() + "|");
			s.append("|"); // CODIGO EX-TIPI
			s.append(i.getNcm().substring(0, 2) + "|"); // CODIGO GENERO ITEM
			s.append("|"); // CODIGO LISTA DE SERVICO
			s.append("0,00|");
			s.append(FsUtil.cpND(i.getCest(), "") + "|");
			s.append(qL);
		}
		String[] retorno = new String[2];
		retorno[0] = s.toString();
		retorno[1] = NumUtil.qtdLinhas(s.toString()).toString();
		return retorno;
	}

	// 0400 - TABELA DE NATUREZA DA OPERACAO/PRESTACAO*************************
	public String[] bloco0400(Date ini, Date fim, CdPessoa emi) throws Exception {
		StringBuilder s = new StringBuilder();
		List<FsNfeItem> fsnfeitems = fsNfeItemRp.itemFsNfeEntSaiCFOPGroup(emi.getId(), ini, fim);
		for (FsNfeItem it : fsnfeitems) {
			s.append("|0400|");
			s.append(it.getCfop() + "|");
			s.append(it.getFsnfe().getNatop() + "|");
			s.append(qL);
		}
		String[] retorno = new String[2];
		retorno[0] = s.toString();
		retorno[1] = NumUtil.qtdLinhas(s.toString()).toString();
		return retorno;
	}

	// 0990 - ENCERRAMENTO DO BLOCO 0******************************************
	public String bloco0990(String linhas) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|0990|");
		s.append((NumUtil.qtdLinhas(linhas) + 1) + "|");
		s.append(qL);
		return s.toString();
	}

	// BLOCO C
	// ####################################################################################
	public String[] blocoC(List<FsNfe> fsnfe, Date ini, Date fim, CdPessoa emi) throws Exception {
		StringBuilder s = new StringBuilder();
		Integer c100 = 0;
		Integer c110 = 0;
		Integer c113 = 0;
		Integer c140 = 0;
		Integer c141 = 0;
		Integer c160 = 0;
		Integer c170 = 0;
		Integer c190 = 0;
		// C001 - ABERTURA DO BLOCO C**************************************
		s.append("|C001|");
		String indmov = (fsnfe.size() > 0) ? "0" : "1";
		s.append(indmov + "|");
		s.append(qL);
		for (FsNfe n : fsnfe) {
			String tipo = n.getTipo().equals("E") ? "0" : "1";
			// APENAS NOTAS EMITIDAS E CANCELADAS - 101
			if (n.getStatus().equals(100) || n.getStatus().equals(101)) {
				// C100 - REGISTRANDO A ENT. OU SAI. DE PROD. OU OUTRAS SITUACOES**
				s.append("|C100|");
				if (n.getTipo().equals("E")) {
					s.append(n.getTpnf() + "|"); // Entrada ou saida
				} else {
					s.append("0|"); // Entrada
				}
				s.append(tipo + "|");
				String part = n.getTipo().equals("E") ? n.getFsnfepartdest().getCpfcnpj()
						: n.getFsnfepartemit().getCpfcnpj();
				s.append(part + "|");
				s.append(n.getModelo() + "|");
				s.append(FsTipoNomeUtil.nomeStSped(n.getStatus().toString()) + "|");
				s.append(n.getSerie() + "|");
				s.append(n.getNnf() + "|");
				s.append(n.getChaveac() + "|");
				s.append(DataUtil.dataPadraoBrSemBarra(n.getDhemi()) + "|");
				s.append(DataUtil.dataPadraoBrSemBarra(n.getDhsaient()) + "|");
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVnf()) + "|");
				s.append(n.getFsnfepags().get(0).getTpag() + "|"); // Pagto
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVdesc()) + "|");
				s.append("0,00|");// 15 VL_ABAT_NT
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVprod()) + "|");
				s.append(n.getFsnfefrete().getModfrete() + "|");
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVfrete()) + "|");
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVseg()) + "|");
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVoutro()) + "|");
				String vbc = n.getFsnfetoticms().getVbc() != null
						? MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVbc())
						: "0,00";
				s.append(vbc + "|");
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVicms()) + "|");
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVbcst()) + "|");
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVst()) + "|");
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVipi()) + "|");
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVpis()) + "|");
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfetoticms().getVcofins()) + "|");
				s.append("|"); // 28 VL_PIS_ST
				s.append("|"); // 29 VL_COFINS_ST
				s.append(qL);
				c100++;
			}
			// APENAS NOTAS EMITIDAS - 100
			if (n.getStatus() == 100) {
				// C110 - INFORAMACAO ADD AO FISCO*********************************
				if (n.getInfadfisco() != null && !n.getInfadfisco().equals("")) {
					s.append("|C110|");
					s.append(n.getNnf() + "|");
					s.append(n.getInfadfisco() + "|");
					s.append(qL);
					c110++;
				}
				// C113 - DOCUMENTO FISCAL REFERENCIADO****************************
				for (FsNfeRef r : n.getFsnferefs()) {
					String chaveac = r.getRefnfe();
					if (chaveac != null && !chaveac.equals("")) {
						s.append("|C113|");
						String tipoC113 = chaveac.substring(34, 35);
						if (r.getFsnfe().getTipo().equals("E")) {
							s.append(tipoC113 + "|"); // Tipo emissao
							s.append("1|");
						}
						if (r.getFsnfe().getTipo().equals("R")) {
							s.append(0 + "|"); // Tipo emissao
							s.append("0|");
						}
						String cnpj = chaveac.substring(6, 20);
						s.append(cnpj + "|"); // Codigo participante
						s.append(chaveac.substring(20, 22) + "|"); // Modelo
						s.append(chaveac.substring(22, 25) + "|"); // Serie
						s.append("|"); // Subserie
						s.append(chaveac.substring(25, 34) + "|"); // Numero
						String aaaa = "20" + chaveac.substring(2, 4);
						String mm = chaveac.substring(4, 6);
						s.append("01" + mm + "" + aaaa + "|");
						s.append(chaveac + "|"); // Chave de acesso
						s.append(qL);
						c113++;
					}
				}
				// C140 - FATURA***************************************************
				s.append("|C140|");
				if (n.getTipo().equals("E")) {
					s.append("0|"); // EMISSAO PROPRIA
				} else {
					s.append("1|");
				}
				s.append("99|");
				s.append(n.getFsnfepags().get(0).getTpag() + "|");
				s.append(n.getFsnfecobr().getNfat() + "|");
				s.append(n.getFsnfecobr().getFsnfecobrdups().size() + "|");
				s.append(MoedaUtil.moedaPadraoSP(n.getFsnfecobr().getVorig()) + "|");
				s.append(qL);
				c140++;
				// C141 - VENCIMENTO DA FATURA - DUPLICATAS************************
				for (FsNfeCobrDup d : n.getFsnfecobr().getFsnfecobrdups()) {
					s.append("|C141|");
					s.append(d.getNdup() + "|");
					s.append(DataUtil.dataPadraoBrSemBarra(d.getDvenc()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(d.getVdup()) + "|");
					s.append(qL);
					c141++;
				}
				// C160 - VOLUMES TRANSPORTADOS************************************
				if (n.getTipo().equals("E")) {
					if (n.getCdpessoaemp().getCpfcnpj() != n.getFsnfeparttransp().getCpfcnpj()) {
						for (FsNfeFreteVol v : n.getFsnfefrete().getFsnfefretevols()) {
							s.append("|C160|");
							s.append(n.getFsnfeparttransp().getCpfcnpj() + "|");
							s.append(FsUtil.cpND(n.getFsnfefrete().getC_placa(), "") + "|");
							s.append(v.getQvol() + "|");
							s.append(MoedaUtil.moedaPadraoSP(v.getPesob()) + "|");
							s.append(MoedaUtil.moedaPadraoSP(v.getPesol()) + "|");
							String placauf = n.getFsnfefrete().getC_uf();
							if (placauf != null) {
								if (placauf.equals("XX")) {
									placauf = "";
								}
							}
							s.append(FsUtil.cpND(placauf, "") + "|");
							s.append(qL);
							c160++;
						}
					}
				}
				// C170 - ITENS DO DOCUMENTO***************************************
				int iditem = 1;
				for (FsNfeItem i : n.getFsnfeitems()) {
					String codcontacontabil = "";
					Long idprod = i.getIdprod();
					if (idprod == null) {
						idprod = 0L;
					}
					CdProduto pr = cdProdutoRp.findByIdCodigoUnico(idprod);
					if (pr != null) {
						codcontacontabil = pr.getCodcontac();
					}
					s.append("|C170|");
					s.append(iditem + "|");
					if (n.getTipo().equals("E")) {
						s.append(i.getCprod() + "|"); // Emissao
					} else {
						s.append(i.getIdprod() + "|"); // Importacao
					}
					s.append(i.getXprod() + "|");
					s.append(MoedaUtil.moedaPadraoSP5(i.getQcom()) + "|");
					// Emissao ou importacao - R = RECEBIDA / E - EMITIDA
					if (n.getTipo().equals("E")) {
						s.append(i.getUcom() + "|");
					} else {
						s.append(i.getUconv() + "|");
					}
					s.append(MoedaUtil.moedaPadraoSP(i.getVprod()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getVdesc()) + "|");
					// MOVIMENTO FISICO DO PRODUTO 0 - SIM Se finalidade = 1
					if (i.getFsnfe().getFinnfe().equals(1)) {
						s.append("0|");
					} else {
						s.append("1|");
					}
					s.append(i.getFsnfeitemicms().getCst() + "|");
					// Emissao ou importacao - R = RECEBIDA / E - EMITIDA
					if (n.getTipo().equals("E")) {
						s.append(i.getCfop() + "|");
						s.append(i.getCfop() + "|");
					} else {
						s.append(i.getCfopconv() + "|");
						s.append(i.getCfopconv() + "|");
					}
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemicms().getVbc()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemicms().getPicms()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemicms().getVicms()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemicms().getVbcst()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemicms().getPicmsst()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemicms().getVicmsst()) + "|");
					s.append("0|"); // 19 IND_APUR - mensal 0
					if (n.getTipo().equals("E")) {
						s.append(i.getFsnfeitemipi().getCst() + "|");
					} else {
						String cstipi = FsUtil.cstIPIConv(i.getFsnfeitemipi().getCst(), i.getFsnfeitemipi().getVipi());
						s.append(cstipi + "|");
					}
					s.append(i.getFsnfeitemipi().getCenq() + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemipi().getVbc()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemipi().getPipi()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemipi().getVipi()) + "|");
					s.append(i.getFsnfeitempis().getCst() + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitempis().getVbc()) + "|");
					s.append(MoedaUtil.moedaPadraoSP4(i.getFsnfeitempis().getPpis()) + "|");
					s.append(MoedaUtil.moedaPadraoSP3(i.getFsnfeitempis().getQbcprod()) + "|");
					s.append(MoedaUtil.moedaPadraoSP4(i.getFsnfeitempis().getVpis()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitempis().getVpis()) + "|");
					s.append(i.getFsnfeitemcofins().getCst() + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemcofins().getVbc()) + "|");
					s.append(MoedaUtil.moedaPadraoSP4(i.getFsnfeitemcofins().getPcofins()) + "|");
					s.append(MoedaUtil.moedaPadraoSP3(i.getFsnfeitemcofins().getQbcprod()) + "|");
					s.append(MoedaUtil.moedaPadraoSP4(i.getFsnfeitemcofins().getVcofins()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemcofins().getVcofins()) + "|");
					s.append(codcontacontabil + "|"); // 37 COD_CTA
					s.append("0,00|"); // 38 VL_ABAT_NT
					s.append(qL);
					iditem++;
					c170++;
				}
				// C190 - REGISTRO ANALÍTICO DO DOCUMENTO**************************
				List<FsNfeItem> fsnfeitems = fsNfeItemRp.itemFsNfeEntSaiCFOPCSTALIQGroup(n.getCdpessoaemp().getId(),
						n.getId());
				for (FsNfeItem i : fsnfeitems) {
					BigDecimal vOper = new BigDecimal(0);
					BigDecimal vBcIcms = new BigDecimal(0);
					BigDecimal vIcms = new BigDecimal(0);
					BigDecimal vBcIcmsST = new BigDecimal(0);
					BigDecimal vIcmsST = new BigDecimal(0);
					BigDecimal vRedBc = new BigDecimal(0);
					BigDecimal vIPI = new BigDecimal(0);
					for (FsNfeItem item : n.getFsnfeitems()) {
						if (item.getCfop().equals(i.getCfop())
								&& item.getFsnfeitemicms().getCst().equals(i.getFsnfeitemicms().getCst())
								&& item.getFsnfeitemicms().getPicms().compareTo(i.getFsnfeitemicms().getPicms()) == 0) {
							vOper = vOper.add(item.getVtot().add(item.getVtottrib()));
							vBcIcms = vBcIcms.add(item.getFsnfeitemicms().getVbc());
							vIcms = vIcms.add(item.getFsnfeitemicms().getVicms());
							vBcIcmsST = vBcIcmsST.add(item.getFsnfeitemicms().getVbcst());
							vIcmsST = vIcmsST.add(item.getFsnfeitemicms().getVicmsst());
							BigDecimal vRedbcItem = item.getFsnfeitemicms().getVbc()
									.multiply(item.getFsnfeitemicms().getPredbc()).divide(new BigDecimal(100))
									.setScale(2, RoundingMode.HALF_UP);
							vRedBc = vRedBc.add(vRedbcItem);
							vIPI = vIPI.add(item.getFsnfeitemipi().getVipi());
						}
					}
					s.append("|C190|");
					s.append(i.getFsnfeitemicms().getCst() + "|");
					s.append(i.getCfop() + "|");
					s.append(MoedaUtil.moedaPadraoSP(i.getFsnfeitemicms().getPicms()) + "|");
					s.append(MoedaUtil.moedaPadraoSP(vOper) + "|");
					s.append(MoedaUtil.moedaPadraoSP(vBcIcms) + "|");
					s.append(MoedaUtil.moedaPadraoSP(vIcms) + "|");
					s.append(MoedaUtil.moedaPadraoSP(vBcIcmsST) + "|");
					s.append(MoedaUtil.moedaPadraoSP(vIcmsST) + "|");
					s.append(MoedaUtil.moedaPadraoSP(vRedBc) + "|");
					s.append(MoedaUtil.moedaPadraoSP(vIPI) + "|");
					s.append("|"); // 12 COD_OBS
					if (fsnfeitems.size() > 1) {
						s.append(qL);
					} else {
						s.append(qL);
					}
				}
				c190++;
			}
		}
		// C990 - ENCERRAMENTO DO BLOCO C**************************************
		s.append("|C990|");
		s.append(NumUtil.qtdLinhas(s.toString()) + "|");
		s.append(qL);
		String[] retorno = new String[9];
		retorno[0] = s.toString();
		retorno[1] = c100.toString();
		retorno[2] = c110.toString();
		retorno[3] = c113.toString();
		retorno[4] = c140.toString();
		retorno[5] = c141.toString();
		retorno[6] = c160.toString();
		retorno[7] = c170.toString();
		retorno[8] = c190.toString();
		return retorno;
	}

	// BLOCO D
	// ####################################################################################
	public String[] blocoD(Date ini, Date fim, CdPessoa emi) throws Exception {
		StringBuilder s = new StringBuilder();
		Integer d100 = 0;
		Integer d190 = 0;
		List<FsCte> fscte = fsCteRp.listaCteEntsai(emi.getId(), ini, fim);
		// D001 - ABERTURA DO BLOCO D******************************************
		s.append("|D001|");
		String indmov = (fscte.size() > 0) ? "0" : "1";
		s.append(indmov + "|");
		s.append(qL);
		for (FsCte c : fscte) {
			// D100 - REGISTRANDO A ENT. OU SAI. DE PROD. OU OUTRAS SITUACOES**
			s.append("|D100|");
			String tipoD100 = "0";
			int indemit = 1;
			if (c.getTipo().equals("E")) {
				indemit = 0;
				tipoD100 = "1";
			}
			s.append(tipoD100 + "|");
			s.append(indemit + "|");
			String part = c.getTipo().equals("E") ? c.getFsctepartdest().getCpfcnpj()
					: c.getFsctepartemit().getCpfcnpj();
			s.append(part + "|");
			s.append(c.getModelo() + "|");
			s.append(FsTipoNomeUtil.nomeStSped(c.getStatus().toString()) + "|");
			s.append(c.getSerie() + "|");
			s.append("|");// Subserie
			s.append(c.getNct() + "|");
			s.append(c.getChaveac() + "|");
			s.append(DataUtil.dataPadraoBrSemBarra(c.getDhemi()) + "|");
			s.append(DataUtil.dataPadraoBrSemBarra(c.getDhsaient()) + "|");
			s.append(c.getTpcte() + "|");
			s.append(c.getFscteinf().getChcte_sub() + "|"); // Chave do sustituto se houver
			s.append(MoedaUtil.moedaPadraoSP(c.getFsctevprest().getVtprest()) + "|");
			s.append("0,00|"); // V. Desc
			s.append("1|"); // Por conta Dest/Rem.
			s.append(MoedaUtil.moedaPadraoSP(c.getFsctevprest().getVtprest()) + "|");
			s.append(MoedaUtil.moedaPadraoSP(c.getFscteicms().getVbc()) + "|");
			s.append(MoedaUtil.moedaPadraoSP(c.getFscteicms().getVicms()) + "|");
			s.append("|"); // Valor nao tributado
			s.append("|"); // 22 COD_INF
			s.append("|"); // 23 COD_CTA
			s.append(c.getFsctepartrem().getCmun() + "|");
			s.append(c.getFsctepartdest().getCmun() + "|");
			s.append(qL);
			d100++;
			// D190 - REGISTRO ANALÍTICO DOS DOCUMENTOS************************
			s.append("|D190|");
			s.append(c.getFscteicms().getCst() + "|");
			s.append(c.getCfop() + "|");
			s.append(MoedaUtil.moedaPadraoSP(c.getFscteicms().getPicms()) + "|");
			s.append(MoedaUtil.moedaPadraoSP(c.getFsctevprest().getVtprest()) + "|");
			s.append(MoedaUtil.moedaPadraoSP(c.getFscteicms().getVbc()) + "|");
			s.append(MoedaUtil.moedaPadraoSP(c.getFscteicms().getVicms()) + "|");
			BigDecimal vRedBc = c.getFsctevprest().getVtprest().multiply(c.getFscteicms().getPredbc())
					.divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
			s.append(MoedaUtil.moedaPadraoSP(vRedBc) + "|");
			s.append("|");
			s.append(qL);
			d190++;
		}
		// D990 - ENCERRAMENTO DO BLOCO D**************************************
		s.append("|D990|");
		s.append(NumUtil.qtdLinhas(s.toString()) + "|");
		s.append(qL);
		String[] retorno = new String[3];
		retorno[0] = s.toString();
		retorno[1] = d100.toString();
		retorno[2] = d190.toString();
		return retorno;
	}

	// BLOCO E
	// ####################################################################################
	// E001 - ABERTURA DO BLOCO E**********************************************
	public String blocoE001() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|E001|");
		s.append("1|"); // BLOCO COM DADOS INFORMADOS (1 = SEM)
		s.append(qL);
		return s.toString();
	}
	// E100 - PERIODO DA APURACAO DO ICMS*************************************
	/*
	 * public String blocoE100(Date ini, Date fim) throws Exception { String dataini
	 * = DataUtil.dataPadraoBrSemBarra(ini); String datafim =
	 * DataUtil.dataPadraoBrSemBarra(fim); StringBuilder s = new StringBuilder();
	 * s.append("|E100|"); s.append(dataini + "|"); s.append(datafim + "|");
	 * s.append(qL); return s.toString(); }
	 * 
	 * // E110 - APURACAO DO ICMS – OPERACOES PROPRIAS****************************
	 * public String blocoE110() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E110|"); s.append(qL); return s.toString(); }
	 * 
	 * // E111 - AJUSTE/BENEFICIO/INCENTIVO DA APURACAO DO ICMS*******************
	 * public String blocoE111() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E111|"); s.append(qL); return s.toString(); }
	 * 
	 * // E113 - INFORMACOES ADICIONAIS DOS AJUSTES DA APURACAO DO ICMS***********
	 * public String blocoE113() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E113|"); s.append(qL); return s.toString(); }
	 * 
	 * // E116 - OBRIGACOES DO ICMS RECOLHIDO OU A RECOLHER***********************
	 * public String blocoE116() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E116|"); s.append(qL); return s.toString(); }
	 * 
	 * // E200 - PERIODO DA APURACAO DO ICMS - ST*********************************
	 * public String blocoE200() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E200|"); s.append(qL); return s.toString(); }
	 * 
	 * // E210 - APURACAO DO ICMS – ST********************************************
	 * public String blocoE210() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E210|"); s.append(qL); return s.toString(); }
	 * 
	 * // E220 - AJUSTE/BENEFÍCIO/INCENTIVO DA APURACAO DO ICMS - ST**************
	 * public String blocoE220() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E220|"); s.append(qL); return s.toString(); }
	 * 
	 * // E230 - INFORMACOES ADICIONAIS DOS AJUSTES DA APURACAO DO ICMS - ST******
	 * public String blocoE230() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E230|"); s.append(qL); return s.toString(); }
	 * 
	 * // E240 - IDENTIFICACAO DOS DOCUMENTOS FISCAIS - ST************************
	 * public String blocoE240() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E240|"); s.append(qL); return s.toString(); }
	 * 
	 * // E250 - OBRIGACOES DO ICMS RECOLHIDO OU A RECOLHER - ST******************
	 * public String blocoE250() throws Exception { / StringBuilder s = new
	 * StringBuilder(); s.append("|E250|"); s.append(qL); return s.toString(); }
	 * 
	 * // E500 - PERIODO DE APURACAO DO IPI***************************************
	 * public String blocoE500() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E500|"); s.append(qL); return s.toString(); }
	 * 
	 * // E510 - CONSOLIDACAO DOS VALORES DO IPI**********************************
	 * public String blocoE510() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E510|"); s.append(qL); return s.toString(); }
	 * 
	 * // E520 - APURACAO DO IPI**************************************************
	 * public String blocoE520() throws Exception { StringBuilder s = new
	 * StringBuilder(); s.append("|E520|"); s.append(qL); return s.toString(); }
	 */

	// E990 - ENCERRAMENTO DO BLOCO E******************************************
	public String blocoE990(String linhas) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|E990|");
		s.append((NumUtil.qtdLinhas(linhas) + 1) + "|");
		s.append(qL);
		return s.toString();
	}

	// BLOCO G
	// ####################################################################################
	// G001 - ABERTURA DO BLOCO G**********************************************
	public String blocoG001() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|G001|");
		s.append("1|"); // BLOCO SEM DADOS INFORMADOS (0 = COM) - NAO UTILIZADO POR ENQUANTO
		s.append(qL);
		return s.toString();
	}

	// G990 - ABERTURA DO BLOCO G**********************************************
	public String blocoG990(String linhas) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|G990|");
		s.append((NumUtil.qtdLinhas(linhas) + 1) + "|");
		s.append(qL);
		return s.toString();
	}

	// BLOCO H
	// ####################################################################################
	// H001 - ABERTURA DO BLOCO H**********************************************
	public String blocoH001() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|H001|");
		s.append("0|"); // BLOCO SEM DADOS INFORMADOS (1 = SEM)
		s.append(qL);
		return s.toString();
	}

	// H005 - TOTAIS DO INVENTARIO*********************************************
	public String blocoH005(CdPessoa emi, Date fim) throws Exception {
		StringBuilder s = new StringBuilder();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fim);
		Integer ano = calendar.get(Calendar.YEAR) - 1;
		Date data = java.sql.Date.valueOf(ano + "-12-31");
		// Soma estoque-custo
		BigDecimal vCustoTotal = new BigDecimal(0);
		List<CdProduto> prs = cdProdutoRp.findAll();
		for (CdProduto p : prs) {
			EsEstMov ess = esEstMovRp.findUltimoProdutoDataUltima(emi.getId(), p.getId(), data);
			if (ess != null) {
				EsEst est = esEstRp.findByEmpProdutoIdCodigo(emi.getId(), ess.getCdproduto().getId(),
						ess.getCdproduto().getCodigo());
				if (est != null) {
					if (ess.getQtdfim().compareTo(BigDecimal.ZERO) > 0
							&& est.getVcusto().compareTo(BigDecimal.ZERO) > 0) {
						BigDecimal vTotItem = est.getVcusto().multiply(ess.getQtdfim());
						vCustoTotal = vCustoTotal.add(vTotItem);
					}
				}
			}
		}
		s.append("|H005|");
		s.append(DataUtil.dataPadraoBrSemBarra(data) + "|");
		s.append(MoedaUtil.moedaPadraoSP(vCustoTotal) + "|");
		s.append("01|");
		s.append(qL);
		return s.toString();
	}

	// H010 - INVENTARIO*******************************************************
	public String blocoH010(CdPessoa emi, Date fim) throws Exception {
		StringBuilder s = new StringBuilder();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fim);
		Integer ano = calendar.get(Calendar.YEAR) - 1;
		Date data = java.sql.Date.valueOf(ano + "-12-31");
		List<CdProduto> prs = cdProdutoRp.findAll();
		for (CdProduto p : prs) {
			EsEstMov ess = esEstMovRp.findUltimoProdutoDataUltima(emi.getId(), p.getId(), data);
			if (ess != null) {
				EsEst est = esEstRp.findByEmpProdutoIdCodigo(emi.getId(), ess.getCdproduto().getId(),
						ess.getCdproduto().getCodigo());
				if (est != null) {
					if (ess.getQtdfim().compareTo(BigDecimal.ZERO) > 0
							&& est.getVcusto().compareTo(BigDecimal.ZERO) > 0) {
						BigDecimal vTotItem = est.getVcusto().multiply(ess.getQtdfim());
						s.append("|H010|");
						s.append(est.getCdproduto().getCodigo() + "|");
						s.append(est.getCdproduto().getCdprodutounmed().getSigla() + "|");
						s.append(MoedaUtil.moedaPadraoSP3(ess.getQtdfim()) + "|");
						s.append(MoedaUtil.moedaPadraoSP6(est.getVcusto()) + "|");
						s.append(MoedaUtil.moedaPadraoSP(vTotItem) + "|");
						s.append("0|");
						s.append("|");
						s.append("|");
						s.append(est.getCdproduto().getCodcontac() + "|");
						s.append("|");
						s.append(qL);
					}
				}
			}
		}
		return s.toString();
	}

	// H990 - ENCERRAMENTO DO BLOCO H******************************************
	public String blocoH990(String linhas) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|H990|");
		s.append((NumUtil.qtdLinhas(linhas) + 1) + "|");
		s.append(qL);
		return s.toString();
	}

	// BLOCO K
	// ####################################################################################
	// K001 - ABERTURA DO BLOCO K**********************************************
	public String blocoK001() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|K001|");
		s.append("0|"); // BLOCO COM DADOS INFORMADOS (1 = SEM)
		s.append(qL);
		return s.toString();
	}

	// K010 - TIPO DE LAYOUT ADOTADO*******************************************
	public String blocoK010() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|K010|");
		s.append("0|"); // LAYOUT ADOTADO - SIMPLIFICADO
		s.append(qL);
		return s.toString();
	}

	// K100 - PERIODO DE APURACAO DO ICMS/IPI**********************************
	public String blocoK100(Date ini, Date fim) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|K100|");
		s.append(DataUtil.dataPadraoBrSemBarra(ini) + "|");
		s.append(DataUtil.dataPadraoBrSemBarra(fim) + "|");
		s.append(qL);
		return s.toString();
	}

	// K200 - ESTOQUE ESCRITURADO**********************************************
	public String blocoK200(CdPessoa emi, Date fim) throws Exception {
		StringBuilder s = new StringBuilder();
		List<CdProduto> prs = cdProdutoRp.findAll();
		for (CdProduto p : prs) {
			if (p.getTipo().equals("PRODUTO")) {
				EsEstMov ess = esEstMovRp.findUltimoProdutoDataUltima(emi.getId(), p.getId(), fim);
				BigDecimal qtdFim = new BigDecimal(0);
				if (ess != null) {
					if (ess.getQtdfim().compareTo(BigDecimal.ZERO) > 0) {
						qtdFim = ess.getQtdfim();
					}
				}
				s.append("|K200|");
				s.append(DataUtil.dataPadraoBrSemBarra(fim) + "|");
				s.append(p.getCodigo() + "|");
				s.append(MoedaUtil.moedaPadraoSP3(qtdFim) + "|");
				s.append("0|");// Com informante do arquivo
				s.append("|");
				s.append(qL);
			}
		}
		return s.toString();
	}

	// K220 - OUTRAS MOVIMENTACOES INTERNAS ENTRE MERCADORIASs*****************
	public String blocoK220() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|K220|");
		s.append(qL);
		return s.toString();
	}

	// K230 - ITENS PRODUZIDOS*************************************************
	public String blocoK230(CdPessoa emi, Date ini, Date fim) throws Exception {
		StringBuilder s = new StringBuilder();
		List<EsEstMov> ess = esEstMovRp.findAllLocalEmpTipoTpdData(emi.getId(), "E", "05", ini, fim);
		for (EsEstMov e : ess) {
			s.append("|K230|");
			s.append("|");
			s.append("|");
			s.append("|");
			s.append(e.getCdproduto().getCodigo() + "|");
			s.append(MoedaUtil.moedaPadraoSP6(e.getQtd()) + "|");
			s.append(qL);
		}
		return s.toString();
	}

	// K250 - INDUSTRIALIZACAO EFETUADA POR TERCEIROS: ITENS PRODUZIDOS********
	public String blocoK250(CdPessoa emi, Date ini, Date fim) throws Exception {
		StringBuilder s = new StringBuilder();
		List<EsEstMov> ess = esEstMovRp.findAllLocalEmpTipoTpdData(emi.getId(), "E", "07", ini, fim);
		for (EsEstMov e : ess) {
			s.append("|K250|");
			s.append(DataUtil.dataPadraoBrSemBarra(e.getData()) + "|");
			s.append(e.getCdproduto().getCodigo() + "|");
			s.append(MoedaUtil.moedaPadraoSP6(e.getQtd()) + "|");
			s.append(qL);
		}
		return s.toString();
	}

	// K270 - CORRECAO DE APONTAMENTO DOS REGISTROS****************************
	public String blocoK270() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|K270|");
		s.append(qL);
		return s.toString();
	}

	// K280 - CORRECAO DE APONTAMENTO - ESTOQUE ESRITURADO*********************
	public String blocoK280(CdPessoa emi, Date ini, Date fim) throws Exception {
		StringBuilder s = new StringBuilder();
		for (EsEstMov em : esEstMovRp.findAllLocalEmpTpdData(emi.getId(), "02", ini, fim)) {
			s.append("|K280|");
			s.append(DataUtil.dataPadraoBrSemBarra(em.getData()) + "|");
			s.append(em.getCdproduto().getCodigo() + "|");
			s.append(MoedaUtil.moedaPadraoSP6(em.getQtd()) + "|");
			s.append("|");
			s.append("0|");
			s.append("|");
			s.append(qL);
		}
		return s.toString();
	}

	// K290 - PROD. CONJ. - ORDEM DE PRODUCAO**********************************
	public String blocoK290(CdPessoa emi, Date ini, Date fim) throws Exception {
		StringBuilder s = new StringBuilder();
		List<EsEstMov> ess = esEstMovRp.findAllLocalEmpTipoTpdDataGroupDoc(emi.getId(), "E", "05", ini, fim);
		for (EsEstMov e : ess) {
			s.append("|K290|");
			s.append(DataUtil.dataPadraoBrSemBarra(ini) + "|");
			s.append(DataUtil.dataPadraoBrSemBarra(fim) + "|");
			s.append(e.getIddoc() + "|");
			s.append(qL);
		}
		return s.toString();
	}

	// K291 - PROD. CONJ. - ITENS PRODUZIDOS***********************************
	public String blocoK291(CdPessoa emi, Date ini, Date fim) throws Exception {
		StringBuilder s = new StringBuilder();
		List<EsEstMov> ess = esEstMovRp.findAllLocalEmpTipoTpdData(emi.getId(), "E", "05", ini, fim);
		for (EsEstMov e : ess) {
			s.append("|K291|");
			s.append(e.getCdproduto().getCodigo() + "|");
			s.append(MoedaUtil.moedaPadraoSP6(e.getQtd()) + "|");
			s.append(qL);
		}
		return s.toString();
	}

	// K300 - PROD. CONJ. - INDUSTRIALIZACAO EFETUADA P TERCEIROS**************
	public String blocoK300() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|K300|");
		s.append(qL);
		return s.toString();
	}

	// K301 - PROD. CONJ. - INDUSTRIALIZACAO EFETUADA P TERCEIROS: ITENS PROD.*
	public String blocoK301() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|K301|");
		s.append(qL);
		return s.toString();
	}

	// K990 - ENCERRAMENTO DO BLOCO K******************************************
	public String blocoK990(String linhas) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|K990|");
		s.append((NumUtil.qtdLinhas(linhas) + 1) + "|");
		s.append(qL);
		return s.toString();
	}

	// BLOCO 1
	// ####################################################################################
	// 1001 - ABERTURA DO BLOCO K**********************************************
	public String bloco1001() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|1001|");
		s.append("0|"); // BLOCO COM DADOS INFORMADOS (1 = SEM)
		s.append(qL);
		return s.toString();
	}

	// 1010 - OBRIGATORIEDADE DE REGISTROS DO BLOCO
	// 1*************************************
	public String bloco1010() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|1010|");
		s.append("N|");
		s.append("N|");
		s.append("N|");
		s.append("N|");
		s.append("N|");
		s.append("N|");
		s.append("N|");
		s.append("N|");
		s.append("N|");
		s.append("N|");
		s.append("N|");
		s.append("N|");
		s.append("N|");
		s.append(qL);
		return s.toString();
	}

	// 1600 - BLOCO RECEBE CARTAO CREDITO E DEBITO*****************************
	public String[] bloco1600(CdPessoa emi, Date ini, Date fim) throws Exception {
		StringBuilder s = new StringBuilder();
		// Caixas banco
		List<CdCaixa> bcs = cdCaixaRp.findAllByTipoLocal(emi.getId(), "02");
		// Credito
		for (CdCaixa c : bcs) {
			BigDecimal vmov = fsNfePagRp.sumNfeEntsaiPagsDocsCxTp(emi.getId(), ini, fim, "03", c.getId());
			if (vmov != null) {
				if (vmov.compareTo(BigDecimal.ZERO) > 0) {
					s.append("|1600|");
					s.append("B_" + c.getCpfcnpj() + "|");
					s.append(MoedaUtil.moedaPadraoSP(vmov) + "|");
					s.append("0,00|");
					s.append(qL);
				}
			}
		}
		// Debito
		for (CdCaixa c : bcs) {
			BigDecimal vmov = fsNfePagRp.sumNfeEntsaiPagsDocsCxTp(emi.getId(), ini, fim, "04", c.getId());
			if (vmov != null) {
				if (vmov.compareTo(BigDecimal.ZERO) > 0) {
					s.append("|1600|");
					s.append("B_" + c.getCpfcnpj() + "|");
					s.append("0,00|");
					s.append(MoedaUtil.moedaPadraoSP(vmov) + "|");
					s.append(qL);
				}
			}
		}
		String[] retorno = new String[2];
		retorno[0] = s.toString();
		retorno[1] = NumUtil.qtdLinhas(s.toString()).toString();
		return retorno;
	}

	// 1601 - BLOCO DE OPERACOES BANCARIAS GERAIS******************************
	public String[] bloco1601(CdPessoa emi, Date ini, Date fim) throws Exception {
		StringBuilder s = new StringBuilder();
		// Caixas banco
		List<CdCaixa> bcs = cdCaixaRp.findAllByTipoLocal(emi.getId(), "02");
		for (CdCaixa c : bcs) {
			BigDecimal vmov = fsNfePagRp.sumNfeEntsaiPagsDocsCx(emi.getId(), ini, fim, c.getId());
			if (vmov != null) {
				if (vmov.compareTo(BigDecimal.ZERO) > 0) {
					s.append("|1601|");
					s.append("B_" + c.getCpfcnpj() + "|");
					s.append("|");
					s.append(MoedaUtil.moedaPadraoSP(vmov) + "|");
					s.append("0,00|");
					s.append("0,00|");
					s.append(qL);
				}
			}
		}
		String[] retorno = new String[2];
		retorno[0] = s.toString();
		retorno[1] = NumUtil.qtdLinhas(s.toString()).toString();
		return retorno;
	}

	// 1990 - ENCERRAMENTO DO BLOCO 1******************************************
	public String bloco1990(String linhas) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|1990|");
		s.append((NumUtil.qtdLinhas(linhas) + 1) + "|");
		s.append(qL);
		return s.toString();
	}

	// BLOCO 9
	// ####################################################################################
	// 9001 - ABERTURA DO BLOCO 9**********************************************
	public String bloco9001() throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|9001|");
		s.append("0|"); // BLOCO COM DADOS INFORMADOS (1 = SEM)
		s.append(qL);
		return s.toString();
	}

	// 9900 - REGISTROS DO ARQUIVO*********************************************
	public String bloco9900(String lin0150, String lin0190, String lin0200, String lin0400, String linC100,
			String linC110, String linC113, String linC140, String linC141, String linC160, String linC170,
			String linC190, String linD100, String linD190, String lin1600, String lin1601) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|9001|");
		s.append("|9900|0000|1|").append(qL);
		s.append("|9900|0001|1|").append(qL);
		s.append("|9900|0005|1|").append(qL);
		s.append("|9900|0100|1|").append(qL);
		s.append("|9900|0150|" + lin0150 + "|").append(qL);
		s.append("|9900|0190|" + lin0190 + "|").append(qL);
		s.append("|9900|0200|" + lin0200 + "|").append(qL);
		s.append("|9900|0400|" + lin0400 + "|").append(qL);
		s.append("|9900|0990|1|").append(qL);
		s.append("|9900|C001|1|").append(qL);
		s.append("|9900|C100|" + linC100 + "|").append(qL);
		s.append("|9900|C110|" + linC110 + "|").append(qL);
		s.append("|9900|C113|" + linC113 + "|").append(qL);
		s.append("|9900|C140|" + linC140 + "|").append(qL);
		s.append("|9900|C141|" + linC141 + "|").append(qL);
		s.append("|9900|C160|" + linC160 + "|").append(qL);
		s.append("|9900|C170|" + linC170 + "|").append(qL);
		s.append("|9900|C190|" + linC190 + "|").append(qL);
		s.append("|9900|C990|1|").append(qL);
		s.append("|9900|D001|1|").append(qL);
		s.append("|9900|D100|" + linD100 + "|").append(qL);
		s.append("|9900|D190|" + linD190 + "|").append(qL);
		s.append("|9900|D990|1|").append(qL);
		s.append("|9900|E001|1|").append(qL);
		s.append("|9900|E100|" + 0 + "|").append(qL);
		s.append("|9900|E110|" + 0 + "|").append(qL);
		s.append("|9900|E111|" + 0 + "|").append(qL);
		s.append("|9900|E113|" + 0 + "|").append(qL);
		s.append("|9900|E116|" + 0 + "|").append(qL);
		s.append("|9900|E200|" + 0 + "|").append(qL);
		s.append("|9900|E210|" + 0 + "|").append(qL);
		s.append("|9900|E220|" + 0 + "|").append(qL);
		s.append("|9900|E230|" + 0 + "|").append(qL);
		s.append("|9900|E240|" + 0 + "|").append(qL);
		s.append("|9900|E250|" + 0 + "|").append(qL);
		s.append("|9900|E500|" + 0 + "|").append(qL);
		s.append("|9900|E510|" + 0 + "|").append(qL);
		s.append("|9900|E520|" + 0 + "|").append(qL);
		s.append("|9900|E990|1|").append(qL);
		s.append("|9900|G001|1|").append(qL);
		s.append("|9900|G990|1|").append(qL);
		s.append("|9900|H001|1|").append(qL);
		s.append("|9900|H005|" + 0 + "|").append(qL);
		s.append("|9900|H010|" + 0 + "|").append(qL);
		s.append("|9900|H990|1|").append(qL);
		s.append("|9900|K001|1|").append(qL);
		s.append("|9900|K100|" + 0 + "|").append(qL);
		s.append("|9900|K200|" + 0 + "|").append(qL);
		s.append("|9900|K220|" + 0 + "|").append(qL);
		s.append("|9900|K230|" + 0 + "|").append(qL);
		s.append("|9900|K250|" + 0 + "|").append(qL);
		s.append("|9900|K270|" + 0 + "|").append(qL);
		s.append("|9900|K280|" + 0 + "|").append(qL);
		s.append("|9900|K290|" + 0 + "|").append(qL);
		s.append("|9900|K291|" + 0 + "|").append(qL);
		s.append("|9900|K300|" + 0 + "|").append(qL);
		s.append("|9900|K301|" + 0 + "|").append(qL);
		s.append("|9900|K990|1|").append(qL);
		s.append("|9900|1001|" + 0 + "|").append(qL);
		s.append("|9900|1010|1|").append(qL);
		s.append("|9900|1600|" + lin1600 + "|").append(qL);
		s.append("|9900|1601|" + lin1601 + "|").append(qL);
		s.append("|9900|1990|1|").append(qL);
		s.append("|9900|9001|1|").append(qL);
		s.append("|9900|9900|1|").append(qL);
		s.append("|9900|9990|1|").append(qL);
		s.append("|9900|9999|1|").append(qL);
		return s.toString();
	}

	// 9990 - ENCERRAMENTO DO BLOCO 1******************************************
	public String bloco9990(String linhas) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|9990|");
		s.append((NumUtil.qtdLinhas(linhas) + 1) + "|");
		s.append(qL);
		return s.toString();
	}

	// 9999 - ENCERRAMENTO DO ARQUIVO DIGITAL**********************************
	public String bloco9999(String linhas) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("|9999|");
		s.append((NumUtil.qtdLinhas(linhas) + 1) + "|");
		s.append(qL);// EM BRANCO - FINALIZA ARQUIVO
		return s.toString();
	}
}
