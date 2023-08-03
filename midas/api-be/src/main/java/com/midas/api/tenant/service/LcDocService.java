package com.midas.api.tenant.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.security.ClienteParam;
import com.midas.api.tenant.entity.CdCaixa;
import com.midas.api.tenant.entity.CdCcusto;
import com.midas.api.tenant.entity.CdCondPag;
import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.CdNfeCfgSimples;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdPlconMicro;
import com.midas.api.tenant.entity.CdProdutoNfecfg;
import com.midas.api.tenant.entity.CdProdutoNs;
import com.midas.api.tenant.entity.CdProdutoNsRel;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcDocItem;
import com.midas.api.tenant.entity.LcDocItemCot;
import com.midas.api.tenant.entity.LcDocNfref;
import com.midas.api.tenant.fiscal.service.FsNfeTributoService;
import com.midas.api.tenant.repository.CdCaixaRepository;
import com.midas.api.tenant.repository.CdCcustoRepository;
import com.midas.api.tenant.repository.CdCondPagRepository;
import com.midas.api.tenant.repository.CdNfeCfgRepository;
import com.midas.api.tenant.repository.CdNfeCfgSimplesRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdPlconMicroRepository;
import com.midas.api.tenant.repository.CdProdutoNfecfgRepository;
import com.midas.api.tenant.repository.CdProdutoNsRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.repository.FsNfeRepository;
import com.midas.api.tenant.repository.LcDocItemCotRepository;
import com.midas.api.tenant.repository.LcDocItemRepository;
import com.midas.api.tenant.repository.LcDocNfrefRepository;
import com.midas.api.tenant.repository.LcDocRepository;
import com.midas.api.util.NumUtil;

@Service
public class LcDocService {
	@Autowired
	private LcDocRepository lcDocRp;
	@Autowired
	private LcDocItemRepository lcDocItemRp;
	@Autowired
	private CdCaixaRepository cdCaixaRp;
	@Autowired
	private CdCondPagRepository cdCondPagRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private FnTituloService fnTituloService;
	@Autowired
	private EsEstService esEstService;
	@Autowired
	private FsNfeTributoService fsNfeTributoService;
	@Autowired
	private LcDocItemCotRepository lcDocItemCotRp;
	@Autowired
	private CdNfeCfgSimplesRepository cdNfeCfgSimplesRp;
	@Autowired
	private CdNfeCfgRepository cdNfeCfgRp;
	@Autowired
	private CdProdutoNfecfgRepository cdProdutoNfecfgRp;
	@Autowired
	private CdCcustoRepository cdCcustoRp;
	@Autowired
	private CdPlconMicroRepository cdPlconMicroRp;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private CdProdutoNsRepository cdProdutoNsRp;
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private LcDocNfrefRepository docNfrefRp;
	@Autowired
	private ClienteParam prm;

	// NOVO DOC***************************************************************
	public LcDoc docInicia(LcDoc lcDoc) throws SQLException {
		Optional<CdCaixa> cx = cdCaixaRp.findAllByLocal(lcDoc.getCdpessoaemp().getId());
		if (lcDoc.getFpag() == null) {
			lcDoc.setFpag("01");
		}
		lcDoc.setCdcaixa(cx.get());
		Optional<CdCondPag> cp = cdCondPagRp.findById(1);
		lcDoc.setCdcondpag(cp.get());
		// Gera numeracao
		LcDoc ultimo = lcDocRp.ultimoLcDoc(lcDoc.getTipo(), lcDoc.getCdpessoaemp().getId());
		Long numero = 1L;
		if (ultimo != null) {
			numero = ultimo.getNumero() + 1;
		}
		lcDoc.setNumero(numero);
		// Centro de custos padrao, se houver-----
		CdCcusto ccc = cdCcustoRp.findByLocalCdpessoaemp(lcDoc.getCdpessoaemp().getId());// PADRAO
		if (ccc != null) {
			lcDoc.setCdccusto_id(ccc.getId());
		}
		// Verifica vendedor - se nao, pega primeiro da lista
		Optional<CdPessoa> cliente = cdPessoaRp.findById(lcDoc.getCdpessoapara().getId());
		Long vendedor = cliente.get().getCdpessoaresp_id();
		if (vendedor != null) {
			if (vendedor > 0) {
				CdPessoa ve = cdPessoaRp.findFirstByTipoLocalId("VENDEDOR", "ATIVO", lcDoc.getCdpessoaemp().getId(),
						vendedor);
				if (ve != null) {
					lcDoc.setCdpessoavendedor(ve);
					lcDoc.setPcom(ve.getPcom());
				} else {
					CdPessoa vee = cdPessoaRp.findFirstByTipoLocal("VENDEDOR", "ATIVO", lcDoc.getCdpessoaemp().getId());
					lcDoc.setCdpessoavendedor(vee);
					lcDoc.setPcom(vee.getPcom());
				}
			} else {
				CdPessoa ve = cdPessoaRp.findFirstByTipoLocal("VENDEDOR", "ATIVO", lcDoc.getCdpessoaemp().getId());
				lcDoc.setCdpessoavendedor(ve);
				lcDoc.setPcom(ve.getPcom());
			}
		} else {
			CdPessoa ve = cdPessoaRp.findFirstByTipoLocal("VENDEDOR", "ATIVO", lcDoc.getCdpessoaemp().getId());
			lcDoc.setCdpessoavendedor(ve);
			lcDoc.setPcom(ve.getPcom());
		}
		// Define como transporte proprio - proprio emitente
		lcDoc.setCdpessoatransp(lcDoc.getCdpessoaemp());
		// Define como local de entrega proprio cliente
		lcDoc.setCdpessoaentrega(lcDoc.getCdpessoapara());
		// Informacao adicional a ser aplicada na nota
		if (lcDoc.getCdnfecfg().getId() != null) {
			CdNfeCfg cdNfeCfg = cdNfeCfgRp.getById(lcDoc.getCdnfecfg().getId());
			lcDoc.setInfodocfiscal(cdNfeCfg.getInfo());
		} else {
			lcDoc.setCdnfecfg(null);
		}
		// Verifica se informou veiculo
		if (lcDoc.getCdveiculo().getId() == null) {
			lcDoc.setCdveiculo(null);
		}
		// Verifica se responsavel tecnico
		if (lcDoc.getCdpessoatec() == null || lcDoc.getCdpessoatec().getId() == null) {
			lcDoc.setCdpessoatec(null);
		}
		// Verifica info para documento
		if (cliente.get().getInfodoc() != null) {
			if (!cliente.get().getInfodoc().equals("")) {
				lcDoc.setInfo(cliente.get().getInfodoc());
			}
		}
		// Verifica Form. pag. pessoa
		if (cliente.get().getFpag() != null) {
			if (!cliente.get().getFpag().equals("00")) {
				lcDoc.setFpag(cliente.get().getFpag());
			}
		}
		// Verifica Cond. pag. pessoa
		if (cliente.get().getCondpag() != null) {
			if (cliente.get().getCondpag() > 0) {
				Optional<CdCondPag> cpc = cdCondPagRp.findById(cliente.get().getCondpag());
				lcDoc.setCdcondpag(cpc.get());
			}
		}
		// Modo de frete padrao
		String modfrete = prm.cliente().getSiscfg().getSis_modfrete_nfe();
		lcDoc.setModfrete(modfrete);
		return lcDoc;
	}

	// DUPLICA DOCUMENTO****************************************************
	public LcDoc duplicarDoc(LcDoc lcDoc, String cotacaoTipo, CdNfeCfg nfecfg) throws Exception {
		LcDoc docOriginal = lcDocRp.findById(lcDoc.getId()).get();
		// ID gerarao
		LcDoc ultimoID = lcDocRp.ultimoLcDoc(lcDoc.getCdpessoaemp().getId());
		Long numeroID = 1L;
		if (ultimoID != null) {
			numeroID = ultimoID.getId() + 1;
		}
		lcDoc.setId(numeroID);
		// Gera numeracao
		LcDoc ultimo = lcDocRp.ultimoLcDoc(lcDoc.getTipo(), lcDoc.getCdpessoaemp().getId());
		Long numero = 1L;
		if (ultimo != null) {
			numero = ultimo.getNumero() + 1;
		}
		lcDoc.setNumero(numero);
		BigDecimal zero = BigDecimal.ZERO;
		// Zera
		lcDoc.setDocfiscal(0L);
		lcDoc.setTpdocfiscal("00");
		lcDoc.setNumnota(0);
		lcDoc.setDocfiscal_nfse(0L);
		lcDoc.setNumnota_nfse(0);
		lcDoc.setLcroma(0L);
		lcDoc.setNumroma(0);
		lcDoc.setTproma("00");
		lcDoc.setLcdocorig(docOriginal.getId());
		lcDoc.setNumdocorig(docOriginal.getNumero());
		lcDoc.setLcdocdevo(0L);
		lcDoc.setNumdocdevo(0L);
		lcDoc.setOrdemcps(null);
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setDatacad(new Date(System.currentTimeMillis()));
		lcDoc.setDataem(new Date(System.currentTimeMillis()));
		lcDoc.setDatafat(new Date(System.currentTimeMillis()));
		lcDoc.setDataprev(new Date(System.currentTimeMillis()));
		lcDoc.setHoracad(new Time(System.currentTimeMillis()));
		lcDoc.setPdescext(zero);
		lcDoc.setVdescext(zero);
		lcDoc.setMotcan("");
		lcDoc.setEntregast("0");
		lcDoc.setAdminconf("N");
		lcDoc.setBoldoc("N");
		lcDoc.setNomenota("");
		lcDoc.setCpfcnpjnota("");
		lcDoc.setStatus("1");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Verifica se compra
		if (doc.getTipo().equals("08")) {
			if (nfecfg != null) {
				doc.setCdnfecfg(nfecfg);
				doc.setInfodocfiscal(nfecfg.getInfo());
				doc = lcDocRp.save(doc);
			}
		}
		// Itens
		duplicaItens(doc, docOriginal.getLcdocitem(), cotacaoTipo);
		return doc;
	}

	// GERA DEVOLUCAO ITENS****************************************************
	public LcDoc devolucaoItensDoc(LcDoc lcDoc, LcDoc lcDocOriginal) throws Exception {
		// ID gerarao
		LcDoc ultimoID = lcDocRp.ultimoLcDoc(lcDoc.getCdpessoaemp().getId());
		Long numeroID = 1L;
		if (ultimoID != null) {
			numeroID = ultimoID.getId() + 1;
		}
		lcDoc.setId(numeroID);
		// Gera numeracao
		LcDoc ultimo = lcDocRp.ultimoLcDoc(lcDoc.getTipo(), lcDoc.getCdpessoaemp().getId());
		Long numero = 1L;
		if (ultimo != null) {
			numero = ultimo.getNumero() + 1;
		}
		lcDoc.setNumero(numero);
		BigDecimal zero = BigDecimal.ZERO;
		// Zera
		lcDoc.setDocfiscal(0L);
		lcDoc.setTpdocfiscal("00");
		lcDoc.setNumnota(0);
		lcDoc.setDocfiscal_nfse(0L);
		lcDoc.setNumnota_nfse(0);
		lcDoc.setLcroma(0L);
		lcDoc.setNumroma(0);
		lcDoc.setTproma("00");
		lcDoc.setLcdocorig(lcDocOriginal.getId());
		lcDoc.setNumdocorig(lcDocOriginal.getNumero());
		lcDoc.setOrdemcps(null);
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setDatacad(new Date(System.currentTimeMillis()));
		lcDoc.setDataem(new Date(System.currentTimeMillis()));
		lcDoc.setDatafat(new Date(System.currentTimeMillis()));
		lcDoc.setDataprev(new Date(System.currentTimeMillis()));
		lcDoc.setHoracad(new Time(System.currentTimeMillis()));
		lcDoc.setPdescext(zero);
		lcDoc.setVdescext(zero);
		lcDoc.setMotcan("");
		lcDoc.setEntregast("0");
		lcDoc.setAdminconf("N");
		lcDoc.setBoldoc("N");
		lcDoc.setNomenota("");
		lcDoc.setCpfcnpjnota("");
		lcDoc.setFpag("90");
		lcDoc.setStatus("1");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Atualiza tbm original
		lcDocRp.updateDocDevo(doc.getId(), doc.getNumero(), lcDocOriginal.getId());
		// Se tiver NFe - passa referenciadas
		if (lcDocOriginal.getDocfiscal() != null) {
			if (lcDocOriginal.getDocfiscal() > 0) {
				FsNfe nf = fsNfeRp.findById(lcDocOriginal.getDocfiscal()).get();
				LcDocNfref lr = new LcDocNfref();
				lr.setLcdoc(doc);
				lr.setRefnf(nf.getChaveac());
				docNfrefRp.save(lr);
			}
		}
		return doc;
	}

	public void duplicaItens(LcDoc doc, List<LcDocItem> lcDocItens, String cotacaoTipo) {
		// Verifica se compra
		if (!doc.getTipo().equals("08")) {
			for (LcDocItem i : lcDocItens) {
				LcDocItem lci = new LcDocItem(i);
				lci.setId(null);
				lci.setLcdoc(doc);
				lci.setDatacad(new Date(System.currentTimeMillis()));
				lci.setDataat(new Date(System.currentTimeMillis()));
				lcDocItemRp.save(lci);
			}
		} else {
			for (LcDocItem i : lcDocItens) {
				LcDocItem lci = new LcDocItem(i);
				lci.setId(null);
				lci.setLcdoc(doc);
				lci.setDatacad(new Date(System.currentTimeMillis()));
				lci.setDataat(new Date(System.currentTimeMillis()));
				lci.setCdnfecfg(doc.getCdnfecfg());
				// Cotacao por fornecedor unico
				if (cotacaoTipo.equals("1")) {
					LcDocItemCot co = lcDocItemCotRp.findByPessoaAndItem(doc.getCdpessoapara().getId(), i.getId());
					if (co != null) {
						lci.setQtd(co.getQtd());
						lci.setVuni(co.getVuni());
						lci.setVsub(co.getVsub());
						lci.setPdesc(co.getPdesc());
						lci.setVdesc(co.getVdesc());
						lci.setVtot(co.getVtot());
						lci.setVtottrib(co.getVtot());
						lcDocItemRp.save(lci);
					}
				}
				// Cotacao por melhor preco
				if (cotacaoTipo.equals("2")) {
					LcDocItemCot co = lcDocItemCotRp.findByMelhorPrecoAndPessoaAndItem(doc.getCdpessoapara().getId(),
							i.getId());
					if (co != null) {
						lci.setQtd(co.getQtd());
						lci.setVuni(co.getVuni());
						lci.setVsub(co.getVsub());
						lci.setPdesc(co.getPdesc());
						lci.setVdesc(co.getVdesc());
						lci.setVtot(co.getVtot());
						lci.setVtottrib(co.getVtot());
						lcDocItemRp.save(lci);
					}
				}
			}
		}
	}

	// FINALIZA DOC************************************************************
	public LcDoc finalizaDoc(LcDoc lcDoc) throws Exception {
		// Dados atualizados
		lcDoc.setDatafat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("2");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Pagar ou receber
		String tipoPR = tipoPR(doc);
		// Atualiza outros dados
		atualizaDocs(doc.getId(), "S", "N");
		// Cobranca
		fnTituloService.finalizaDocs(lcDoc, tipoPR);
		// Gera estoque
		esEstService.geradorLcDoc(lcDoc, "S", "S", "N");
		return doc;
	}

	// FINALIZA DOC PDV************************************************************
	public LcDoc finalizaDocPDV(LcDoc lcDoc) throws Exception {
		// Plano de contas padrao, se houver-----
		Integer idplanocom = 0;
		CdPlconMicro pmc = cdPlconMicroRp.findByLocalTipoAndCdpessoaemp("10", lcDoc.getCdpessoaemp().getId());// PDV
		if (pmc != null) {
			idplanocom = pmc.getId();
		}
		// Centro de custos padrao, se houver-----
		Integer idcentro = 0;
		CdCcusto ccc = cdCcustoRp.findByLocalCdpessoaemp(lcDoc.getCdpessoaemp().getId());// PADRAO
		if (ccc != null) {
			idcentro = ccc.getId();
		}
		// Dados atualizados
		lcDoc.setDatafat(new Date(System.currentTimeMillis()));
		lcDoc.setCdplconmicro_id(idplanocom);
		lcDoc.setCdccusto_id(idcentro);
		lcDoc.setMotcan("");
		lcDoc.setStatus("2");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Pagar ou receber
		String tipoPR = tipoPR(doc);
		// Cobranca
		fnTituloService.finalizaDocs(doc, tipoPR);
		// Gera estoque
		esEstService.geradorLcDoc(doc, "S", "S", "N");
		return doc;
	}

	// FINALIZA DOC DEVOLUCAO***************************************************
	public LcDoc finalizaDocDevolucao(LcDoc lcDoc, String geraCred, String remcob) throws Exception {
		// Dados atualizados
		lcDoc.setDatafat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("2");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Cobranca
		fnTituloService.finalizaDocsDevolve(doc, geraCred, "V");
		// Gera estoque
		esEstService.geradorLcDoc(doc, "E", "S", "N");
		// Remove cobranca-titulos do DOC original
		if (remcob.equals("S")) {
			fnTituloRp.removeParcelasTipo(lcDoc.getLcdocorig(), "R");// Titulos
			fnTituloRp.removeParcelasTipo(lcDoc.getLcdocorig(), "P");// Comissoes
		}
		return doc;
	}

	// FINALIZA DOC DEVOLUCAO COMPRA*********************************************
	public LcDoc finalizaDocDevolucaoCompra(LcDoc lcDoc, String geraCred) throws Exception {
		// Dados atualizados
		lcDoc.setDatafat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("2");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Cobranca
		fnTituloService.finalizaDocsDevolve(lcDoc, geraCred, "C");
		// Gera estoque
		esEstService.geradorLcDoc(doc, "S", "S", "N");
		return doc;
	}

	// FINALIZA DOC REMESSA******************************************************
	public LcDoc finalizaDocRemessa(LcDoc lcDoc) throws Exception {
		// Dados atualizados
		lcDoc.setDatafat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("2");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Gera estoque
		esEstService.geradorLcDoc(doc, "S", "S", "N");
		return doc;
	}

	// FINALIZA DOC CONSUMO INTERNO**********************************************
	public LcDoc finalizaDocConsumoInterno(LcDoc lcDoc) throws Exception {
		// Dados atualizados
		lcDoc.setDatafat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("2");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Gera estoque
		esEstService.geradorLcDoc(doc, "S", "S", "N");
		return doc;
	}

	// FINALIZA DOC COTACAO PRECO**********************************************
	public LcDoc finalizaDocCotacaoPreco(LcDoc lcDoc) throws Exception {
		// Dados atualizados
		lcDoc.setDatafat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("2");
		LcDoc doc = lcDocRp.save(lcDoc);
		return doc;
	}

	// FINALIZA DOC PEDIDO
	// COMPRA************************************************************
	public LcDoc finalizaDocPedidoCompra(LcDoc lcDoc, String geraest) throws Exception {
		// Dados atualizados
		lcDoc.setDatafat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("2");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Pagar ou receber
		String tipoPR = tipoPR(doc);
		// Atualiza outros dados
		atualizaDocs(doc.getId(), "S", "N");
		// Cobranca
		fnTituloService.finalizaDocs(lcDoc, tipoPR);
		// Gera estoque
		if (geraest.equals("S")) {
			esEstService.geradorLcDoc(lcDoc, "E", "S", "N");
		}
		return doc;
	}

	// REABRIR DOC*************************************************************
	public LcDoc reabrirDoc(LcDoc lcDoc) throws Exception {
		// Gera estoque
		if (lcDoc.getReservaest().equals("N")) {
			esEstService.geradorLcDoc(lcDoc, "E", "N", "N");
		} else {
			esEstService.geradorLcDoc(lcDoc, "S", "N", "N");
		}
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setMotcan("");
		lcDoc.setStatus("1");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Atualiza outros dados
		atualizaDocs(doc.getId(), "N", "S");
		return doc;
	}

	// REABRIR DOC DEVOLUCAO***************************************************
	public LcDoc reabrirDocDevolucao(LcDoc lcDoc) throws Exception {
		// Gera estoque
		esEstService.geradorLcDoc(lcDoc, "S", "N", "N");
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("1");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Titulo de credito se houver
		fnTituloService.removeParcelaDocs(doc, "R");
		return doc;
	}

	// REABRIR DOC DEVOLUCAO COMPRA*********************************************
	public LcDoc reabrirDocDevolucaoCompra(LcDoc lcDoc) throws Exception {
		// Gera estoque
		esEstService.geradorLcDoc(lcDoc, "E", "N", "N");
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("1");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Titulo de credito se houver
		fnTituloService.removeParcelaDocs(doc, "P");
		return doc;
	}

	// REABRIR DOC CONSUMO INTERNO*********************************************
	public LcDoc reabrirDocConsumoInterno(LcDoc lcDoc) throws Exception {
		// Gera estoque
		esEstService.geradorLcDoc(lcDoc, "E", "N", "N");
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("1");
		LcDoc doc = lcDocRp.save(lcDoc);
		return doc;
	}

	// REABRIR DOC COTACAO DE PRECO*********************************************
	public LcDoc reabrirDocCotacaoPreco(LcDoc lcDoc) throws Exception {
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("1");
		LcDoc doc = lcDocRp.save(lcDoc);
		return doc;
	}

	// REABRIR DOC PEDIDO COMPRA***************************************************
	public LcDoc reabrirDocPedido(LcDoc lcDoc, String geraest) throws Exception {
		// Gera estoque
		if (geraest.equals("S")) {
			esEstService.geradorLcDoc(lcDoc, "S", "N", "N");
		}
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("1");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Titulo de credito se houver
		fnTituloService.removeParcelaDocs(doc, "P");
		return doc;
	}

	// REABRIR DOC REMESSA***************************************************
	public LcDoc reabrirDocRemessa(LcDoc lcDoc, String geraest) throws Exception {
		// Gera estoque
		if (geraest.equals("S")) {
			esEstService.geradorLcDoc(lcDoc, "E", "N", "N");
		}
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan("");
		lcDoc.setStatus("1");
		LcDoc doc = lcDocRp.save(lcDoc);
		return doc;
	}

	// CANCELAR DOC***********************************************************
	public LcDoc cancelarDoc(LcDoc lcDoc) throws Exception {
		// Gera estoque
		esEstService.geradorLcDoc(lcDoc, "E", "N", "S");
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setMotcan(lcDoc.getMotcan());
		lcDoc.setStatus("3");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Atualiza outros dados
		atualizaDocs(doc.getId(), "N", "S");
		return doc;
	}

	// CANCELAR DOC DEVOLUCAO**************************************************
	public LcDoc cancelarDocDevolucao(LcDoc lcDoc) throws Exception {
		// Gera estoque
		if (lcDoc.getStatus().equals("2")) {
			esEstService.geradorLcDoc(lcDoc, "S", "N", "S");
		}
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan(lcDoc.getMotcan());
		lcDoc.setStatus("3");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Titulo de credito se houver
		fnTituloService.removeParcelaDocs(doc, "R");
		return doc;
	}

	// CANCELAR DOC DEVOLUCAO COMPRA********************************************
	public LcDoc cancelarDocDevolucaoCompra(LcDoc lcDoc) throws Exception {
		// Gera estoque
		if (lcDoc.getStatus().equals("2")) {
			esEstService.geradorLcDoc(lcDoc, "E", "N", "S");
		}
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan(lcDoc.getMotcan());
		lcDoc.setStatus("3");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Titulo de credito se houver
		fnTituloService.removeParcelaDocs(doc, "P");
		return doc;
	}

	// CANCELAR DOC CONSUMO INTERNO*********************************************
	public LcDoc cancelarDocConsumoInterno(LcDoc lcDoc) throws Exception {
		// Gera estoque
		if (lcDoc.getStatus().equals("2")) {
			esEstService.geradorLcDoc(lcDoc, "E", "N", "S");
		}
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan(lcDoc.getMotcan());
		lcDoc.setStatus("3");
		LcDoc doc = lcDocRp.save(lcDoc);
		return doc;
	}

	// CANCELAR DOC COTACAO DE PRECO*********************************************
	public LcDoc cancelarDocCotacaoPreco(LcDoc lcDoc) throws Exception {
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan(lcDoc.getMotcan());
		lcDoc.setStatus("3");
		LcDoc doc = lcDocRp.save(lcDoc);
		return doc;
	}

	// CANCELAR DOC PEDIDO COMPRA**************************************************
	public LcDoc cancelarDocPedidoCompra(LcDoc lcDoc, String geraest) throws Exception {
		// Gera estoque
		if (geraest.equals("S")) {
			if (lcDoc.getStatus().equals("2")) {
				esEstService.geradorLcDoc(lcDoc, "S", "N", "S");
			}
		}
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan(lcDoc.getMotcan());
		lcDoc.setStatus("3");
		LcDoc doc = lcDocRp.save(lcDoc);
		// Titulo de credito se houver
		fnTituloService.removeParcelaDocs(doc, "P");
		return doc;
	}

	// CANCELAR DOC REMESSA**************************************************
	public LcDoc cancelarDocRemessa(LcDoc lcDoc, String geraest) throws Exception {
		// Gera estoque
		if (geraest.equals("S")) {
			if (lcDoc.getStatus().equals("2")) {
				esEstService.geradorLcDoc(lcDoc, "E", "N", "S");
			}
		}
		// Dados atualizados
		lcDoc.setDatafat(lcDoc.getDatacad());
		lcDoc.setDataat(new Date(System.currentTimeMillis()));
		lcDoc.setMotcan(lcDoc.getMotcan());
		lcDoc.setStatus("3");
		LcDoc doc = lcDocRp.save(lcDoc);
		return doc;
	}

	// CALCULOS **************************************************************
	public void atualizaDocs(Long id, String salvaPadrao, String reabOuCance) throws Exception {
		LcDoc doc = lcDocRp.getById(id);
		doc.setDataat(new Date(System.currentTimeMillis()));
		lcDocRp.save(doc);
		// Pagar ou receber
		String tipoPR = tipoPR(doc);
		// Se salva pela acao principal da tela
		if (salvaPadrao.equals("N")) {
			// Financeiro - verifica se tem valor
			if (doc.getVtot().compareTo(BigDecimal.ZERO) == 0) {
				fnTituloService.removeParcelaDocs(doc, tipoPR);
			} else {
				fnTituloService.parcelaDocs(doc, tipoPR);
			}
			// Se vem de Reabertura ou Cancelamento - ajusta pois nao ajusta na funcao acima
			// 'fnTituloService.parcelaDocs'
			if (reabOuCance.equals("S")) {
				fnTituloService.atualizaParcelaDocs(doc);
			}
		}
		// Apenas se ja tiver em aberto
		if (doc.getStatus().equals("1")) {
			// Calculo tributos -----------
			calculoTributoLcDoc(doc);
			// Atualiza tributos LcDoc
			lcDocRp.updateVtotTrib(doc.getId());
			lcDocRp.updateVtotTribDescExtraDesloca(doc.getId());
		}
	}

	// ADD ITEM DOC**********************************************************
	public LcDocItem addItemDoc(Long id, LcDocItem lcDocItem) throws Exception {
		LcDoc doc = lcDocRp.getById(id);
		lcDocItem.setLcdoc(doc);
		// ADICIONA MEDIDAS INTERNAMENTE-----------------
		BigDecimal pesolkg = NumUtil.paraKg(lcDocItem.getCdproduto().getMpesol(), lcDocItem.getQtd(),
				lcDocItem.getCdproduto().getMtipopeso());
		lcDocItem.setMpesol(pesolkg);
		BigDecimal pesobkg = NumUtil.paraKg(lcDocItem.getCdproduto().getMpesob(), lcDocItem.getQtd(),
				lcDocItem.getCdproduto().getMtipopeso());
		lcDocItem.setMpesob(pesobkg);
		BigDecimal metrocub = NumUtil.paraMetro(lcDocItem.getCdproduto().getMcub(), lcDocItem.getQtd(),
				lcDocItem.getCdproduto().getMtipomedida());
		lcDocItem.setMmcub(metrocub);
		// Verifica unidade tributavel diferente---------
		String unMedCom = lcDocItem.getCdproduto().getCdprodutounmed().getSigla();
		String unMedTrib = lcDocItem.getCdproduto().getCdprodutounmedtrib().getSigla();
		if (!unMedCom.equals(unMedTrib)) {
			// Apenas para medidas de peso padroes
			if (unMedTrib.equals("MG") || unMedTrib.equals("G") || unMedTrib.equals("KG") || unMedTrib.equals("TON")) {
				BigDecimal[] retorno = calcUnTrib(lcDocItem, pesobkg, unMedTrib);
				lcDocItem.setQtdtrib(retorno[0]);
				lcDocItem.setVunitrib(retorno[1]);
			} else {
				lcDocItem.setQtdtrib(lcDocItem.getQtd());
				lcDocItem.setVunitrib(lcDocItem.getVuni());
			}
		} else {
			lcDocItem.setQtdtrib(lcDocItem.getQtd());
			lcDocItem.setVunitrib(lcDocItem.getVuni());
		}
		// Verifica Config. Fiscal Item produto----------
		CdNfeCfg cdNfeCfg = verificaTribItem(doc, lcDocItem);
		lcDocItem.setCdnfecfg(cdNfeCfg);
		// Verifica se responsavel tecnico---------------
		if (lcDocItem.getCdpessoatec() == null || lcDocItem.getCdpessoatec().getId() == null) {
			lcDocItem.setCdpessoatec(null);
		}
		// Verifica se possui numero de serie-------------
		if (lcDocItem.getCdproduto().getCdprodutonsrelitem().size() > 0) {
			for (CdProdutoNsRel ns : lcDocItem.getCdproduto().getCdprodutonsrelitem()) {
				if (ns.getCdpessoaemp_id().equals(doc.getCdpessoaemp().getId())) {
					String nums = nsProduto(ns.getCdprodutons_id());
					lcDocItem.setNs(nums);
				}
			}
		}
		LcDocItem docItem = lcDocItemRp.save(lcDocItem);
		atualizaDocs(id, "S", "N");
		// Verifica se tem reserva de produto
		if (doc.getReservaest().equals("S")) {
			// Gera estoque
			esEstService.geradorLcDocUnico(doc, lcDocItem, "S");
		}
		// Se cotacao, gera item novo para forencedores
		if (doc.getTipo().equals("07")) {
			geraCotFornecItem(lcDocItem);
		}
		return docItem;
	}

	// CONTADOR DE NUMERO DE SERIE QUANDO DISPONIVEL*********************
	private String nsProduto(Integer idns) {
		CdProdutoNs pns = cdProdutoNsRp.findById(idns).get();
		String nums = pns.getPum() + "" + pns.getPdois() + "" + pns.getContador();
		// Atualiza contador
		Integer contador = pns.getContador() + 1;
		cdProdutoNsRp.updateContador(contador, idns);
		return nums;
	}

	// CALCULO UNIDADE TRIBUTAVEL DIFERENTE*****************************
	private BigDecimal[] calcUnTrib(LcDocItem lcDocItem, BigDecimal qtdTrib, String unMedTrib) {
		BigDecimal retorno[] = new BigDecimal[2];
		BigDecimal vuniTrib = new BigDecimal(0);
		BigDecimal peso = lcDocItem.getCdproduto().getMpesob();
		BigDecimal qtd = lcDocItem.getQtd();
		BigDecimal vuni = lcDocItem.getVuni();
		BigDecimal vSub = lcDocItem.getVsub();
		if (peso.compareTo(BigDecimal.ZERO) > 0) {
			qtdTrib = peso.multiply(qtd).setScale(6, RoundingMode.HALF_UP);
			vuniTrib = vSub.divide(qtdTrib, 6, RoundingMode.HALF_UP);
		} else {
			qtdTrib = qtd;
			vuniTrib = vuni;
		}
		retorno[0] = qtdTrib;
		retorno[1] = vuniTrib;
		return retorno;
	}

	// REMOVE ITEM / DOC*****************************************************
	public void removeItemDoc(Long id, LcDocItem lcDocItem) throws Exception {
		// Zera valores para novo calculo geral
		LcDoc doc = lcDocRp.getById(id);
		lcDocItem.setLcdoc(doc);
		lcDocItem.setVsub(new BigDecimal(0));
		lcDocItem.setVtot(new BigDecimal(0));
		lcDocItem = lcDocItemRp.save(lcDocItem);
		// Verifica se tem reserva de produto
		if (doc.getReservaest().equals("S")) {
			// Gera estoque
			esEstService.geradorLcDocUnico(doc, lcDocItem, "E");
		}
		lcDocItemRp.delete(lcDocItem);
		atualizaDocs(id, "S", "N");
	}

	// REMOVE TODOS OS ITENS DOC*********************************************
	public void removeItemDocTodos(Long id) throws Exception {
		// Zera valores para novo calculo geral
		LcDoc doc = lcDocRp.getById(id);
		for (LcDocItem lcDocItem : doc.getLcdocitem()) {
			lcDocItem.setLcdoc(doc);
			lcDocItem.setVsub(new BigDecimal(0));
			lcDocItem.setVtot(new BigDecimal(0));
			lcDocItem = lcDocItemRp.save(lcDocItem);
			// Verifica se tem reserva de produto
			if (doc.getReservaest().equals("S")) {
				// Gera estoque
				esEstService.geradorLcDocUnico(doc, lcDocItem, "E");
			}
			lcDocItemRp.delete(lcDocItem);
		}
		atualizaDocs(id, "S", "N");
	}

	// DIVISAO FRETE
	private BigDecimal divisaoFrete(LcDoc lcDoc) throws Exception {
		BigDecimal vFrete = new BigDecimal(0);
		if (lcDoc.getVtransp().compareTo(BigDecimal.ZERO) > 0) {
			if (lcDoc.getLcdocitem().size() > 0) {
				vFrete = lcDoc.getVtransp().divide(new BigDecimal(lcDoc.getLcdocitem().size()), 6,
						RoundingMode.HALF_EVEN);
			}
		}
		return vFrete;
	}

	// CALCULO DOS TRIBUTOS DA EMISSAO *************************************
	public void calculoTributoLcDoc(LcDoc lcDoc) throws Exception {
		// Divisao frete
		BigDecimal vFrete = divisaoFrete(lcDoc);
		// Busca simples, mesmo se nao for ou houver config.
		CdNfeCfgSimples csn = cdNfeCfgSimplesRp.getByCdpessoaemp(lcDoc.getCdpessoaemp().getId());
		// Força usar Cfg. fiscal do documento
		if (lcDoc.getUsacfgfiscal().equals("S")) {
			lcDocItemRp.updateCdNfeCfg(lcDoc.getCdnfecfg(), lcDoc.getId());
		}
		// Verifica se possui NfeCfg
		if (lcDoc.getCdnfecfg() != null) {
			for (LcDocItem i : lcDoc.getLcdocitem()) {
				// Verifica se todos os itens possuem CdNfeCfg - se nao, usa do Doc
				CdNfeCfg ncfg = lcDoc.getCdnfecfg();
				if (!i.getCdnfecfg().equals(lcDoc.getCdnfecfg()) && lcDoc.getUsacfgfiscal().equals("N")) {
					ncfg = i.getCdnfecfg();
				}
				fsNfeTributoService.fsNfeTrib(ncfg, csn, i, null, vFrete);
			}
		} else {
			// Se nao tiver NfeCfg - seta vtottrib e outros
			lcDocItemRp.updateVOutros(vFrete, lcDoc.getId());
			lcDocItemRp.updateVtotTrib(lcDoc.getId());
		}
	}

	// VERIFICA SE TEM TRIBUTACAO ESPECIFICA NO CADASTRO DO PRODUTO
	private CdNfeCfg verificaTribItem(LcDoc lcDoc, LcDocItem lcDocItem) {
		if (lcDoc.getCdnfecfg() != null) {
			CdNfeCfg cdNfeCfg = lcDoc.getCdnfecfg();
			CdProdutoNfecfg cfgItem = cdProdutoNfecfgRp.getCdNfeCfgDestinoItemAtivo(lcDoc.getCdpessoaemp().getId(),
					lcDoc.getCdnfecfg().getTipoop(), lcDoc.getCdpessoapara().getCrt(),
					lcDoc.getCdpessoapara().getCdestado().getId(), lcDocItem.getCdproduto().getId());
			if (cfgItem != null) {
				cdNfeCfg = cfgItem.getCdnfecfg();
			}
			return cdNfeCfg;
		} else
			return null;
	}

	// INSERE NOVO FORNECEDOR
	public void insereCotFornecItem(Long idfornec, Long iddoc) {
		List<LcDocItemCot> cots = lcDocItemCotRp.listaPorItemLcdoc(iddoc);
		CdPessoa cpc = cdPessoaRp.findById(idfornec).get();
		if (cots.size() > 0) {
			for (LcDocItemCot c : cots) {
				LcDocItemCot cn = new LcDocItemCot();
				cn.setLcdocitem(c.getLcdocitem());
				cn.setCdpessoacot(cpc);
				cn.setQtd(c.getLcdocitem().getQtd());
				lcDocItemCotRp.save(cn);
			}
		} else {
			LcDoc doc = lcDocRp.findById(iddoc).get();
			for (LcDocItem c : doc.getLcdocitem()) {
				LcDocItemCot cn = new LcDocItemCot();
				cn.setLcdocitem(c);
				cn.setCdpessoacot(cpc);
				cn.setQtd(c.getQtd());
				lcDocItemCotRp.save(cn);
			}
		}
	}

	// GERA COTACAO ITEM NOVO FORNECEDORES
	private void geraCotFornecItem(LcDocItem lcDocItem) {
		List<LcDocItemCot> cots = lcDocItemCotRp.listaPorFonecLcdoc(lcDocItem.getLcdoc().getId());
		for (LcDocItemCot c : cots) {
			LcDocItemCot cn = new LcDocItemCot();
			cn.setLcdocitem(lcDocItem);
			cn.setCdpessoacot(c.getCdpessoacot());
			cn.setQtd(lcDocItem.getQtd());
			lcDocItemCotRp.save(cn);
		}
	}

	// AJUSTE MENOR PRECO COTACAO DE ITEM
	public void ajustaMelhorPrecoCot(Long iditem) {
		// Verifica melhor preco via preco total
		LcDocItemCot itemMP = lcDocItemCotRp.verificaMPreco(iditem);
		lcDocItemCotRp.updateMpreco(itemMP.getId(), itemMP.getLcdocitem().getId());
		lcDocItemCotRp.updatePpreco(itemMP.getId(), itemMP.getLcdocitem().getId());
	}

	// VERIFICA TIPO DE PAGAMENTO
	public String tipoPR(LcDoc lcdoc) {
		String tipo = "R";
		// Por enquanto apenas para Compra-Entrada manual
		if (lcdoc.getTipo().equals("08")) {
			tipo = "P";
		}
		return tipo;
	}
}
