package com.midas.api.controller;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.AuxDados;
import com.midas.api.tenant.entity.CdCaixaOpera;
import com.midas.api.tenant.entity.CdEstado;
import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.CdProdutoPreco;
import com.midas.api.tenant.entity.CdProdutoTab;
import com.midas.api.tenant.entity.CdVeiculo;
import com.midas.api.tenant.entity.EsEstExt;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfeItem;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcDocDTO;
import com.midas.api.tenant.entity.LcDocEquip;
import com.midas.api.tenant.entity.LcDocItem;
import com.midas.api.tenant.entity.LcDocItemCot;
import com.midas.api.tenant.entity.LcDocNfref;
import com.midas.api.tenant.entity.LcDocPed;
import com.midas.api.tenant.entity.climba.LcPedidoClimba;
import com.midas.api.tenant.entity.climba.LcPedidoItemsClimba;
import com.midas.api.tenant.repository.CdCaixaOperaRepository;
import com.midas.api.tenant.repository.CdEstadoRepository;
import com.midas.api.tenant.repository.CdNfeCfgRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdProdutoPrecoRepository;
import com.midas.api.tenant.repository.CdProdutoRepository;
import com.midas.api.tenant.repository.CdProdutoTabRepository;
import com.midas.api.tenant.repository.CdVeiculoRepository;
import com.midas.api.tenant.repository.EsEstExtRepository;
import com.midas.api.tenant.repository.EsEstMovRepository;
import com.midas.api.tenant.repository.FnCxmvRepository;
import com.midas.api.tenant.repository.LcDocCustomRepository;
import com.midas.api.tenant.repository.LcDocEquipRepository;
import com.midas.api.tenant.repository.LcDocItemCotRepository;
import com.midas.api.tenant.repository.LcDocItemRepository;
import com.midas.api.tenant.repository.LcDocNfrefRepository;
import com.midas.api.tenant.repository.LcDocPedRepository;
import com.midas.api.tenant.repository.LcDocRepository;
import com.midas.api.tenant.service.EmailService;
import com.midas.api.tenant.service.EsEstService;
import com.midas.api.tenant.service.FnCxmvPDVService;
import com.midas.api.tenant.service.FnTituloService;
import com.midas.api.tenant.service.LcDocService;
import com.midas.api.tenant.service.excel.LerExcelCotacaoService;
import com.midas.api.tenant.service.integra.ClimbaService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.LerArqUtil;

@RestController
@RequestMapping("/private/lcdoc")
public class LcDocController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private LcDocRepository lcDocRp;
	@Autowired
	private LcDocCustomRepository lcCustomRp;
	@Autowired
	private LcDocItemRepository lcDocItemRp;
	@Autowired
	private FnTituloService fnTituloService;
	@Autowired
	private FnCxmvRepository fnCxmvRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private LcDocService lcDocService;
	@Autowired
	private CdEstadoRepository cdEstadoRp;
	@Autowired
	private EsEstMovRepository esEstMovRp;
	@Autowired
	private EsEstExtRepository esEstExtRp;
	@Autowired
	private LcDocPedRepository lcDocPedRp;
	@Autowired
	private LcDocEquipRepository lcDocEquipRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdNfeCfgRepository cdNfeCfgRp;
	@Autowired
	private CdVeiculoRepository cdVeiculoRp;
	@Autowired
	private CdProdutoRepository cdProdutoRp;
	@Autowired
	private CdProdutoTabRepository cdProdutoTabRp;
	@Autowired
	private CdProdutoPrecoRepository cdProdutoPrecoRp;
	@Autowired
	private LcDocNfrefRepository lcDocNfrefRp;
	@Autowired
	private CdCaixaOperaRepository caixaOperaRp;
	@Autowired
	private FnCxmvPDVService cxmvPDVService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private EsEstService esEstService;
	@Autowired
	private LcDocItemCotRepository lcDocItemCotRp;
	@Autowired
	private ClimbaService climbaService;
	@Autowired
	private LerExcelCotacaoService lerExcelCotacaoService;
	// TODO criar menu para Transferencias entre estoques - como no antigo
	// TODO fazer devolucao compra pela nfe

	// DOC ****************************************
	@RequestAuthorization
	@GetMapping("/doc/{id}")
	public ResponseEntity<LcDoc> lcDoc(@PathVariable("id") Long id) throws Exception {
		Optional<LcDoc> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = lcDocRp.findById(id);
		} else {
			obj = lcDocRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		// Verifica se tem acesso Represetante
		if (prm.cliente().getRole().getId() == 8) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/doc/ext/{id}")
	public ResponseEntity<LcDoc> lcDocExt(@PathVariable("id") Long id) throws Exception {
		Optional<LcDoc> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = lcDocRp.findById(id);
		} else {
			obj = lcDocRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<LcDoc>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/doc")
	public ResponseEntity<LcDoc> novoLcDoc(@RequestBody LcDoc lcDoc) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 19, "N", lcDoc.getCdpessoapara().getNome())) {
			lcDoc = lcDocService.docInicia(lcDoc);
			doc = lcDocRp.save(lcDoc);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	public static class LcDocEsEstExtDTO {
		private LcDoc lcDoc;
		private List<EsEstExt> listaEsEstExt;

		public LcDoc getLcDoc() {
			return lcDoc;
		}

		public void setLcDoc(LcDoc lcDoc) {
			this.lcDoc = lcDoc;
		}

		public List<EsEstExt> getListaEsEstExt() {
			return listaEsEstExt;
		}

		public void setListaEsEstExt(List<EsEstExt> listaEsEstExt) {
			this.listaEsEstExt = listaEsEstExt;
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/doc/estext")
	public ResponseEntity<LcDoc> novoLcDocEstoqueExterno(@RequestBody LcDocEsEstExtDTO dto) throws Exception {
		LcDoc lcDoc = dto.getLcDoc();
		List<EsEstExt> listaEsEstExt = dto.getListaEsEstExt();
		LcDoc doc = null;
		if (ca.hasAuth(prm, 19, "N", lcDoc.getCdpessoapara().getNome())) {
			lcDoc = lcDocService.docInicia(lcDoc);
			lcDoc.setReservaest("S");
			doc = lcDocRp.save(lcDoc);
			for (EsEstExt estExt : listaEsEstExt) {
				EsEstExt estExtAtual = esEstExtRp.findById(estExt.getId()).get();
				// Ajeita estoque
				esEstService.ajustaEstoqueExterno(estExt.getQtd(), estExtAtual, "ENTRADA");
				// Ajuta tabela estoque externo
				BigDecimal qtdNova = estExtAtual.getQtd().subtract(estExt.getQtd());
				if (qtdNova.compareTo(BigDecimal.ZERO) <= 0) {
					esEstExtRp.deleteById(estExtAtual.getId());
				} else {
					estExtAtual.setQtd(qtdNova);
					esEstExtRp.save(estExtAtual);
				}
				LcDocItem lcDocItem = new LcDocItem();
				lcDocItem.setCdproduto(estExt.getCdproduto());
				lcDocItem.setDescricao(estExt.getDescricao());
				lcDocItem.setQtd(estExt.getQtd());
				// Valor conforme tabela de preço
				lcDocItem.setVuni(BigDecimal.ZERO);
				CdProdutoTab cdProdutoTab = cdProdutoTabRp.findById(doc.getCdprodutotab().getId()).get();
				if (cdProdutoTab != null) {
					CdProdutoPreco cdProdutoPreco = cdProdutoPrecoRp
							.findByCdprodutoAndCdprodutotab(estExt.getCdproduto(), cdProdutoTab);
					if (cdProdutoPreco != null) {
						lcDocItem.setVuni(cdProdutoPreco.getVvenda().setScale(6, RoundingMode.HALF_UP));
					}
				}
				BigDecimal vtot = lcDocItem.getQtd().multiply(lcDocItem.getVuni()).setScale(6, RoundingMode.HALF_UP);
				lcDocItem.setVsub(vtot);
				lcDocItem.setVtot(vtot);
				lcDocService.addItemDoc(doc.getId(), lcDocItem);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/doc/devitem")
	public ResponseEntity<LcDoc> novoLcDocDevolucaoItem(@RequestBody LcDoc lcDoc) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 19, "N", lcDoc.getCdpessoapara().getNome())) {
			// Original
			LcDoc docOrig = lcDocRp.findById(lcDoc.getId()).get();
			lcDoc.setId(null);
			doc = lcDocService.devolucaoItensDoc(lcDoc, docOrig);
			for (LcDocItem docItem : lcDoc.getLcdocitem()) {
				LcDocItem lci = new LcDocItem(docItem);
				lci.setId(null);
				lci.setLcdoc(doc);
				lci = lcDocService.addItemDoc(doc.getId(), lci);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	public static class LcDocFsNfeDTO {
		private LcDoc lcDoc;
		private FsNfe fsNfe;

		public LcDoc getLcDoc() {
			return lcDoc;
		}

		public void setLcDoc(LcDoc lcDoc) {
			this.lcDoc = lcDoc;
		}

		public FsNfe getFsNfe() {
			return fsNfe;
		}

		public void setFsNfe(FsNfe fsNfe) {
			this.fsNfe = fsNfe;
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/doc/devcompra")
	public ResponseEntity<LcDoc> novoLcDocDevolucaoCompra(@RequestBody LcDocFsNfeDTO dto) throws Exception {
		LcDoc lcDoc = dto.getLcDoc();
		List<FsNfeItem> listaFsNfeItem = dto.getFsNfe().getFsnfeitems();
		LcDoc doc = null;
		if (ca.hasAuth(prm, 19, "N", lcDoc.getCdpessoapara().getNome())) {
			FsNfe nfe = dto.getFsNfe();
			lcDoc = lcDocService.docInicia(lcDoc);
			lcDoc.setInfodocfiscal("DEVOLUCAO NF. " + nfe.getNnf() + ". " + lcDoc.getInfodocfiscal());
			lcDoc.setUsacfgfiscal("S");
			doc = lcDocRp.save(lcDoc);
			LcDocNfref nfref = new LcDocNfref();
			nfref.setRefnf(nfe.getChaveac());
			nfref.setLcdoc(doc);
			lcDocNfrefRp.save(nfref);
			for (FsNfeItem fsNfeItem : listaFsNfeItem) {
				LcDocItem lcDocItem = new LcDocItem();
				CdProduto cdProduto = cdProdutoRp.getById(fsNfeItem.getIdprod());
				lcDocItem.setCdproduto(cdProduto);
				lcDocItem.setDescricao(cdProduto.getNome());
				lcDocItem.setQtd(fsNfeItem.getQcom());
				// Valor conforme tabela de preço
				lcDocItem.setVuni(fsNfeItem.getVuncom());
				lcDocItem.setVsub(fsNfeItem.getVtot());
				lcDocItem.setVtot(fsNfeItem.getVtot());
				lcDocService.addItemDoc(doc.getId(), lcDocItem);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	public static class LcDocLcPedClimbaDTO {
		private LcDoc lcDoc;
		private LcPedidoClimba lcPedidoClimba;

		public LcDoc getLcDoc() {
			return lcDoc;
		}

		public void setLcDoc(LcDoc lcDoc) {
			this.lcDoc = lcDoc;
		}

		public LcPedidoClimba getLcPedidoClimba() {
			return lcPedidoClimba;
		}

		public void setLcPedidoClimba(LcPedidoClimba lcPedidoClimba) {
			this.lcPedidoClimba = lcPedidoClimba;
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/doc/orders/importapedido")
	public ResponseEntity<LcDoc> importaLcPedidoClimba(@RequestBody LcDocLcPedClimbaDTO dto) throws Exception {
		LcDoc lcDoc = dto.getLcDoc();
		List<LcPedidoItemsClimba> listaPedItem = dto.getLcPedidoClimba().getItems();
		LcDoc doc = null;
		if (ca.hasAuth(prm, 86, "N", lcDoc.getCdpessoapara().getNome())) {
			LcPedidoClimba climba = dto.getLcPedidoClimba();
			lcDoc = lcDocService.docInicia(lcDoc);
			lcDoc.setInfo("PEDIDO EFETUADO VIA INTEGRACAO CLIMBA. PED. NUMERO: " + climba.getId() + ". ");
			lcDoc.setUsacfgfiscal("S");
			lcDoc.setReservaest("S");
			doc = lcDocRp.save(lcDoc);
			for (LcPedidoItemsClimba pedItem : listaPedItem) {
				LcDocItem lcDocItem = new LcDocItem();
				CdProduto cdProduto = cdProdutoRp.getById(Long.valueOf(pedItem.getProductId()));
				lcDocItem.setCdproduto(cdProduto);
				lcDocItem.setDescricao(cdProduto.getNome());
				lcDocItem.setQtd(new BigDecimal(pedItem.getQuantity()));
				lcDocItem.setVuni(pedItem.getSellingPrice());
				lcDocItem.setVsub(pedItem.getSellingPrice());
				lcDocItem.setVdesc(pedItem.getDiscountValue());
				lcDocItem.setVtot(pedItem.getPrice());
				lcDocService.addItemDoc(doc.getId(), lcDocItem);
			}
			// Atualiza pedido na Climba-----------------
			climbaService.importaPedidoErp(climba.getId());
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/doc/pdv/{idlocal}")
	public ResponseEntity<LcDoc> novoLcDocPDV(@PathVariable("idlocal") Long idlocal) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 19, "N", "NOVA VENDA NO PDV")) {
			LcDoc lcDoc = new LcDoc();
			// Seta padroes----------
			CdPessoa p = cdPessoaRp.findById(idlocal).get();
			Long cdpessoavendedor = prm.cliente().getCdpessoavendedor();
			// Cfg. fiscal-----------
			CdNfeCfg cfgf = cdNfeCfgRp.findByCdpessoaempNFCe(idlocal);
			// Veiculo padrao - OS---
			CdVeiculo v = cdVeiculoRp.findFirstByEmp(idlocal);
			// Tabela padrao---------
			CdProdutoTab pt = cdProdutoTabRp.findByCdpessoaempPDV(idlocal);
			// Caixa de operacao------
			CdCaixaOpera cx = caixaOperaRp.findAllByVendedor(cdpessoavendedor);
			// Valida config inicial
			if (cdpessoavendedor != null) {
				if (p != null && cdpessoavendedor > 0 && pt != null && cx != null && cfgf != null) {
					lcDoc.setCategoria("03");// Define orcamento para facilitar o caixa
					lcDoc.setTipo("01");
					lcDoc.setCdpessoaemp(p);
					lcDoc.setCdpessoapara(p);
					lcDoc.setCdpessoatec(p);
					lcDoc.setCdnfecfg(cfgf);
					lcDoc.setCdveiculo(v);
					lcDoc.setCdprodutotab(pt);
					// Status
					lcDoc.setStatus("1");
					lcDoc = lcDocService.docInicia(lcDoc);
					lcDoc.setCdcaixa(cx.getCdcaixa());
					lcDoc.setCdpessoavendedor(cx.getCdpessoavend());
					lcDoc.setReservaest("S");
					doc = lcDocRp.save(lcDoc);
				} else {
					return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/doc/duplicar")
	public ResponseEntity<LcDoc> duplicarLcDoc(@RequestBody LcDoc lcDoc) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 19, "N", lcDoc.getCdpessoapara().getNome())) {
			doc = lcDocService.duplicarDoc(lcDoc, "0", null);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc")
	public ResponseEntity<LcDoc> atualizarLcDoc(@RequestBody LcDoc lcDoc) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 20, "A", "ID " + lcDoc.getId() + " - " + lcDoc.getCdpessoapara().getNome())) {
			doc = lcDocRp.save(lcDoc);
			lcDocService.atualizaDocs(doc.getId(), "S", "N");
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc/finaliza")
	public ResponseEntity<LcDoc> finalizarLcDoc(@RequestBody LcDoc lcDoc) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 21, "F", "ID " + lcDoc.getId() + " - " + lcDoc.getCdpessoapara().getNome())) {
			doc = lcDocService.finalizaDoc(lcDoc);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc/finaliza/pdv/{id}/vtroco/{vtroco}")
	public ResponseEntity<LcDoc> finalizarLcDocPDV(@PathVariable("id") Long id,
			@PathVariable("vtroco") BigDecimal vtroco, @RequestBody List<AuxDados> auxdados) throws Exception {
		LcDoc doc = lcDocRp.findById(id).get();
		if (ca.hasAuth(prm, 21, "F", "ID PDV " + doc.getId() + " - " + doc.getCdpessoapara().getNome())) {
			doc = lcDocService.finalizaDocPDV(doc);
			cxmvPDVService.efetuaRecebimento(doc, auxdados, vtroco);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc/finalizadiversos/cred/{geracred}/geraest/{geraest}/remcob/{remcob}")
	public ResponseEntity<LcDoc> finalizarLcDocDiversos(@RequestBody LcDoc lcDoc,
			@PathVariable("geracred") String geracred, @PathVariable("geraest") String geraest,
			@PathVariable("remcob") String remcob) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 21, "F", "ID " + lcDoc.getId() + " - " + lcDoc.getCdpessoapara().getNome())) {
			// Devolucao de venda---------
			if (lcDoc.getTipo().equals("03")) {
				doc = lcDocService.finalizaDocDevolucao(lcDoc, geracred, remcob);
			}
			// Devolucao de compra---------
			if (lcDoc.getTipo().equals("04")) {
				doc = lcDocService.finalizaDocDevolucaoCompra(lcDoc, geracred);
			}
			// Consumo interno-------------
			if (lcDoc.getTipo().equals("06")) {
				doc = lcDocService.finalizaDocConsumoInterno(lcDoc);
			}
			// Cotacao de preco-------------
			if (lcDoc.getTipo().equals("07")) {
				doc = lcDocService.finalizaDocCotacaoPreco(lcDoc);
			}
			// Pedido de compra-------------
			if (lcDoc.getTipo().equals("08")) {
				doc = lcDocService.finalizaDocPedidoCompra(lcDoc, geraest);
			}
			// Remessa----------------------
			if (lcDoc.getTipo().equals("09")) {
				doc = lcDocService.finalizaDocRemessa(lcDoc);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc/reabre")
	public ResponseEntity<LcDoc> reabrirLcDoc(@RequestBody LcDoc lcDoc) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 22, "B", "ID " + lcDoc.getId() + " - " + lcDoc.getCdpessoapara().getNome())) {
			doc = lcDocService.reabrirDoc(lcDoc);
			if (lcDoc.getReservaest().equals("N")) {
				esEstMovRp.removeItem("04", doc.getId(), doc.getNumero());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc/reabrediversos/geraest/{geraest}")
	public ResponseEntity<LcDoc> reabrirLcDocDiversos(@RequestBody LcDoc lcDoc, @PathVariable("geraest") String geraest)
			throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 22, "B", "ID " + lcDoc.getId() + " - " + lcDoc.getCdpessoapara().getNome())) {
			// Devolucao de venda---------
			if (lcDoc.getTipo().equals("03")) {
				doc = lcDocService.reabrirDocDevolucao(lcDoc);
			}
			// Devolucao de compra---------
			if (lcDoc.getTipo().equals("04")) {
				doc = lcDocService.reabrirDocDevolucaoCompra(lcDoc);
			}
			// Consumo interno-------------
			if (lcDoc.getTipo().equals("06")) {
				doc = lcDocService.reabrirDocConsumoInterno(lcDoc);
			}
			// Cotacao de preco-------------
			if (lcDoc.getTipo().equals("07")) {
				doc = lcDocService.reabrirDocCotacaoPreco(lcDoc);
			}
			// Pedido de compra-------------
			if (lcDoc.getTipo().equals("08")) {
				doc = lcDocService.reabrirDocPedido(lcDoc, geraest);
			}
			// Remessa-------------
			if (lcDoc.getTipo().equals("09")) {
				doc = lcDocService.reabrirDocRemessa(lcDoc, geraest);
			}
			esEstMovRp.removeItem("04", doc.getId(), doc.getNumero());
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc/cancela")
	public ResponseEntity<LcDoc> cancelarLcDoc(@RequestBody LcDoc lcDoc) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 23, "X", "ID " + lcDoc.getId() + " - " + lcDoc.getCdpessoapara().getNome())) {
			Integer qtdTMov = fnCxmvRp.qtdTitulosPagos(lcDoc.getId());
			if (qtdTMov.equals(0)) {
				doc = lcDocService.cancelarDoc(lcDoc);
				if (lcDoc.getReservaest().equals("N")) {
					esEstMovRp.removeItem("04", doc.getId(), doc.getNumero());
				}
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc/canceladiversos/geraest/{geraest}")
	public ResponseEntity<LcDoc> cancelarLcDocDiversos(@RequestBody LcDoc lcDoc,
			@PathVariable("geraest") String geraest) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 23, "X", "ID " + lcDoc.getId() + " - " + lcDoc.getCdpessoapara().getNome())) {
			// Devolucao de venda---------
			if (lcDoc.getTipo().equals("03")) {
				doc = lcDocService.cancelarDocDevolucao(lcDoc);
			}
			// Devolucao de compra---------
			if (lcDoc.getTipo().equals("04")) {
				doc = lcDocService.cancelarDocDevolucaoCompra(lcDoc);
			}
			// Consumo interno-------------
			if (lcDoc.getTipo().equals("06")) {
				doc = lcDocService.cancelarDocConsumoInterno(lcDoc);
			}
			// Cotacao de preco-------------
			if (lcDoc.getTipo().equals("07")) {
				doc = lcDocService.cancelarDocCotacaoPreco(lcDoc);
			}
			// Pedido de compra-------------
			if (lcDoc.getTipo().equals("08")) {
				doc = lcDocService.cancelarDocPedidoCompra(lcDoc, geraest);
			}
			// Remessa-------------
			if (lcDoc.getTipo().equals("09")) {
				doc = lcDocService.cancelarDocRemessa(lcDoc, geraest);
			}
			esEstMovRp.removeItem("04", doc.getId(), doc.getNumero());
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/doc/remover/{id}")
	public ResponseEntity<?> removerLcDoc(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 70, "R", "ID " + id)) {
			LcDoc doc = lcDocRp.findById(id).get();
			if (doc.getReservaest().equals("N")) {
				esEstMovRp.removeItem("04", doc.getId(), doc.getNumero());
			} else {
				// Gera estoque
				esEstService.geradorLcDoc(doc, "E", "N", "N");
			}
			// Verifica Doc devolvido
			lcDocRp.updateDocDevo(0L, 0L, doc.getLcdocorig());
			lcDocRp.delete(doc);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc/doctitulo")
	public ResponseEntity<LcDoc> atualizarFnTitulo(@RequestBody LcDoc lcDoc) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 20, "A", "ATUALIZACAO DE COBRANCA PARA DOCUMENTO " + lcDoc.getId())) {
			doc = lcDocRp.save(lcDoc);
			// Verifica se documento tem valor para poder parcelar
			if (doc.getVtot().compareTo(BigDecimal.ZERO) == 0) {
				// Remove antigos
				fnTituloService.removeParcelaDocs(doc, "R");
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			} else {
				// Pagar ou receber
				String tipoPR = lcDocService.tipoPR(doc);
				fnTituloService.parcelaDocs(doc, tipoPR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc/adminconf/{id}/adc/{adc}")
	public ResponseEntity<?> atualizarLcDocAdminConf(@PathVariable("id") Long id, @PathVariable("adc") String adc)
			throws Exception {
		if (ca.hasAuth(prm, 80, "A", "ATUALIZACAO DE CONFERENCIA DE DOCUMENTO " + id)) {
			lcDocRp.updateDocAdminConf(adc, id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc/atualizaentregas/st/{st}")
	public ResponseEntity<?> atualizaStatusEntregas(@RequestBody List<LcDoc> lcDocs, @PathVariable("st") String st)
			throws Exception {
		if (ca.hasAuth(prm, 80, "A", "ATUALIZACAO DE STATUS DE ENTREGAS")) {
			for (LcDoc l : lcDocs) {
				lcDocRp.updateLcDocEntregaSt(st, l.getId());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDoc>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/doc/atualizacomdoc/{id}/vend/{vend}/pcom/{pcom}")
	public ResponseEntity<?> atualizaComissoesDT(@PathVariable("id") Long id, @PathVariable("vend") Long vend,
			@PathVariable("pcom") BigDecimal pcom) throws Exception {
		if (ca.hasAuth(prm, 80, "A", "ATUALIZACAO DE COMISSOES DE VENDAS E TITULOS ")) {
			LcDoc doc = lcDocRp.findById(id).get();
			String japago = "N";
			// Verifica se ja foi pago
			for (FnTitulo t : doc.getLcdoctitulo()) {
				if (t.getTipo().equals("P") && t.getComissao().equals("S")) {
					if (t.getVpago().compareTo(BigDecimal.ZERO) > 0) {
						japago = "S";
					}
				}
			}
			if (japago.equals("N")) {
				fnTituloService.atualizaComDocs(doc, id, vend, pcom);
				return new ResponseEntity<LcDoc>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/doc/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/lc/{local}/para/{para}/vep/{vep}/tp/{tipo}/cat/{cat}/ipp/{itenspp}/ofe/{ofe}/"
			+ "dti/{dataini}/dtf/{datafim}/coduf/{coduf}/st/{st}")
	public ResponseEntity<Page<LcDocDTO>> listaLcDocsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("local") Long local,
			@PathVariable("para") Long para, @PathVariable("vep") Long vep, @PathVariable("tipo") String tipo,
			@PathVariable("cat") String cat, @PathVariable("itenspp") int itenspp, @PathVariable("ofe") String ofe,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("coduf") Integer coduf, @PathVariable("st") String st) throws Exception {
		if (ca.hasAuth(prm, 24, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Resp. Vendedores
			if (prm.cliente().getRole().getId() == 8) {
				if (prm.cliente().getCdpessoavendedor() != null) {
					vep = prm.cliente().getCdpessoavendedor();
				}
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			List<LcDoc> lista = lcCustomRp.listaLcDocs(local, para, vep, tipo, cat, ofe, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, coduf, ordem, ordemdir, st, pageable);
			Page<LcDocDTO> pages = lcCustomRp.listaLcDocsDTO(lista, pageable);
			return new ResponseEntity<Page<LcDocDTO>>(pages, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/doc/valores/b/{busca}/lc/{local}/para/{para}/vep/{vep}/tp/{tipo}/cat/{cat}/ofe/{ofe}/dti/{dataini}/dtf/{datafim}/coduf/{coduf}/st/{st}")
	public ResponseEntity<List<LcDocDTO>> listaLcDocsBuscaValores(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("para") Long para, @PathVariable("vep") Long vep,
			@PathVariable("tipo") String tipo, @PathVariable("cat") String cat, @PathVariable("ofe") String ofe,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("coduf") Integer coduf, @PathVariable("st") String st) throws Exception {
		if (ca.hasAuth(prm, 24, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Resp. Vendedores
			if (prm.cliente().getRole().getId() == 8) {
				if (prm.cliente().getCdpessoavendedor() != null) {
					vep = prm.cliente().getCdpessoavendedor();
				}
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<LcDoc> lista = lcCustomRp.listaLcDocsValores(local, para, vep, tipo, cat, ofe, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, coduf, st);
			// Utilizado para maximizar o tempo da consulta
			List<LcDocDTO> ldto = new ArrayList<LcDocDTO>();
			for (LcDoc c : lista) {
				LcDocDTO dto = new LcDocDTO();
				dto.setId(c.getId());
				dto.setVtottrib(c.getVtottrib());
				dto.setStatus(c.getStatus());
				ldto.add(dto);
			}
			return new ResponseEntity<List<LcDocDTO>>(ldto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/doc/tp/{tipo}/st/{st}")
	public ResponseEntity<List<LcDoc>> listaLcDocsStatusTipo(@PathVariable("tipo") String tipo,
			@PathVariable("st") String st) throws Exception {
		if (ca.hasAuth(prm, 24, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			String aclocal = prm.cliente().getAclocal();
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			List<LcDoc> lista = null;
			if (aclocal.equals("S")) {
				if (local > 0) {
					lista = lcDocRp.listaDocStatusLocal(local, tipo, st);
				} else {
					lista = lcDocRp.listaDocStatus(tipo, st);
				}
			} else {
				lista = lcDocRp.listaDocStatusLocal(prm.cliente().getCdpessoaemp(), tipo, st);
			}
			return new ResponseEntity<List<LcDoc>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/doc/faturamento/anual/local/{local}/ano/{ano}/tpdoc/{tpdoc}")
	public ResponseEntity<List<BigDecimal>> listaLcDocsFaturaAnual(@PathVariable("local") Long local,
			@PathVariable("ano") Integer ano, @PathVariable("tpdoc") String tpdoc) throws Exception {
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
		}
		Long respvend = 0L;
		if (prm.cliente().getRole().getId() == 8) {
			if (prm.cliente().getCdpessoavendedor() != null) {
				respvend = prm.cliente().getCdpessoavendedor();
			}
		}
		List<BigDecimal> aux = new ArrayList<BigDecimal>();
		// Lista meses
		for (int mes = 0; mes < 12; mes++) {
			java.util.Date menuMesIni = DataUtil.addRemMeses(Date.valueOf(ano + "-01-01"), mes, "A");
			java.util.Date menuMesFim = DataUtil.addRemMeses(Date.valueOf(ano + "-01-31"), mes, "A");
			// Calculos
			Date dataini = new Date(menuMesIni.getTime());
			Date datafim = new Date(menuMesFim.getTime());
			BigDecimal valor = new BigDecimal(0);
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				if (local > 0) {
					if (respvend > 0) {
						valor = lcDocRp.faturaMesAnoLocalPeriodoResp(local, respvend, "2", dataini, datafim, tpdoc);
					} else {
						valor = lcDocRp.faturaMesAnoLocalPeriodo(local, "2", dataini, datafim, tpdoc);
					}
				} else {
					if (respvend > 0) {
						valor = lcDocRp.faturaMesAnoPeriodoResp(respvend, "2", dataini, datafim, tpdoc);
					} else {
						valor = lcDocRp.faturaMesAnoPeriodo("2", dataini, datafim, tpdoc);
					}
				}
			} else {
				if (respvend > 0) {
					valor = lcDocRp.faturaMesAnoLocalPeriodoResp(local, respvend, "2", dataini, datafim, tpdoc);
				} else {
					valor = lcDocRp.faturaMesAnoLocalPeriodo(local, "2", dataini, datafim, tpdoc);
				}
			}
			aux.add(valor);
		}
		return new ResponseEntity<List<BigDecimal>>(aux, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/doc/faturamento/estados/anual/local/{local}/ano/{ano}/tpdoc/{tpdoc}")
	public ResponseEntity<List<BigDecimal>> listaLcDocsFaturaAnualEstados(@PathVariable("local") Long local,
			@PathVariable("ano") Integer ano, @PathVariable("tpdoc") String tpdoc) throws Exception {
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
		}
		Long respvend = 0L;
		if (prm.cliente().getRole().getId() == 8) {
			if (prm.cliente().getCdpessoavendedor() != null) {
				respvend = prm.cliente().getCdpessoavendedor();
			}
		}
		List<BigDecimal> aux = new ArrayList<BigDecimal>();
		Date dataini = Date.valueOf(ano + "-01-01");
		Date datafim = Date.valueOf(ano + "-12-31");
		// Lista estados
		List<CdEstado> ufs = cdEstadoRp.findAllByOrderByUfAsc();
		for (CdEstado es : ufs) {
			BigDecimal valor = new BigDecimal(0);
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				if (local > 0) {
					if (respvend > 0) {
						valor = lcDocRp.faturaMesAnoLocalPeriodoEstadoResp(local, respvend, "2", dataini, datafim,
								es.getId(), tpdoc);
					} else {
						valor = lcDocRp.faturaMesAnoLocalPeriodoEstado(local, "2", dataini, datafim, es.getId(), tpdoc);
					}
				} else {
					if (respvend > 0) {
						valor = lcDocRp.faturaMesAnoPeriodoEstadoResp(respvend, "2", dataini, datafim, es.getId(),
								tpdoc);
					} else {
						valor = lcDocRp.faturaMesAnoPeriodoEstado("2", dataini, datafim, es.getId(), tpdoc);
					}
				}
			} else {
				if (respvend > 0) {
					valor = lcDocRp.faturaMesAnoLocalPeriodoEstadoResp(local, respvend, "2", dataini, datafim,
							es.getId(), tpdoc);
				} else {
					valor = lcDocRp.faturaMesAnoLocalPeriodoEstado(local, "2", dataini, datafim, es.getId(), tpdoc);
				}
			}
			aux.add(valor);
		}
		return new ResponseEntity<List<BigDecimal>>(aux, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/doc/envioemail/{id}/email/{email}")
	public ResponseEntity<?> envioNFeEmailOutros(HttpServletRequest request, @PathVariable("id") Long id,
			@PathVariable("email") String email) throws Exception {
		if (ca.hasAuth(prm, 24, "E", "ID Documento para Envio por E-mail " + id)) {
			emailService.enviaDocEmail(request, id, email);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// DOC ITEM ****************************************
	@RequestAuthorization
	@GetMapping("/docitem/{id}")
	public ResponseEntity<LcDocItem> lcDocItem(@PathVariable("id") Long id) throws InterruptedException {
		Optional<LcDocItem> obj = lcDocItemRp.findById(id);
		return new ResponseEntity<LcDocItem>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/docitem")
	public ResponseEntity<LcDocItem> atualizarLcDocItem(@RequestBody LcDocItem lcDocItem) throws Exception {
		LcDocItem docitem = null;
		if (ca.hasAuth(prm, 69, "A", "ID DOC " + lcDocItem.getLcdoc().getId() + " - " + lcDocItem.getDescricao())) {
			// Calculos IBPT
			docitem = lcDocItemRp.save(lcDocItem);
			lcDocService.atualizaDocs(lcDocItem.getLcdoc().getId(), "S", "N");
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcDocItem>(docitem, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/docitem/{id}")
	public ResponseEntity<?> addLcDocItem(@PathVariable("id") Long id, @RequestBody LcDocItem lcDocItem)
			throws Exception {
		if (ca.hasAuth(prm, 20, "A", "ITEM " + lcDocItem.getCdproduto().getNome())) {
			lcDocService.addItemDoc(id, lcDocItem);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/docitem/{id}")
	public ResponseEntity<?> removerlcDocItem(@PathVariable("id") Long id, @RequestBody LcDocItem lcDocItem)
			throws Exception {
		try {
			if (ca.hasAuth(prm, 20, "R",
					"ID ITEM " + lcDocItem.getCdproduto().getId() + " - " + lcDocItem.getDescricao())) {
				lcDocService.removeItemDoc(id, lcDocItem);
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/docitem/remtodos/{id}")
	public ResponseEntity<?> removerlcDocItemTodos(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 20, "R", "ID DOC " + id)) {
			lcDocService.removeItemDocTodos(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	// DOC NF REFS ****************************************
	@RequestAuthorization
	@GetMapping("/docnfref/{id}")
	public ResponseEntity<LcDocNfref> lcDocNfref(@PathVariable("id") Long id) throws InterruptedException {
		Optional<LcDocNfref> obj = lcDocNfrefRp.findById(id);
		return new ResponseEntity<LcDocNfref>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/docnfref/{id}")
	public ResponseEntity<?> addLcDocNfref(@PathVariable("id") Long id, @RequestBody LcDocNfref lcDocNfref)
			throws Exception {
		if (ca.hasAuth(prm, 43, "A", "DOC " + lcDocNfref.getRefnf())) {
			LcDocNfref chave = lcDocNfrefRp.findByDocAndChave(id, lcDocNfref.getRefnf());
			if (chave == null) {
				LcDoc doc = lcDocRp.getById(id);
				lcDocNfref.setLcdoc(doc);
				lcDocNfrefRp.save(lcDocNfref);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/docnfref/{id}")
	public ResponseEntity<?> remLcDocNfref(@PathVariable("id") Long id, @RequestBody LcDocNfref lcDocNfref)
			throws Exception {
		if (ca.hasAuth(prm, 43, "R", "ID DOC " + lcDocNfref.getId() + " - " + lcDocNfref.getRefnf())) {
			lcDocNfrefRp.delete(lcDocNfref);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	// COTACAO DE FRETE
	@RequestAuthorization
	@PostMapping("/doc/cotarfrete")
	public ResponseEntity<?> cotarFreteLcDocs(@RequestBody List<AuxDados> auxdados) throws Exception {
		if (ca.hasAuth(prm, 24, "Y", "CONSULTA DE COTAÇÃO PARA FRETE")) {
			emailService.enviaEmailCotacaoFrete(auxdados);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// DOC PEDIDO CLIENTE ****************************************
	@RequestAuthorization
	@GetMapping("/docped/{id}")
	public ResponseEntity<LcDocPed> lcDocPed(@PathVariable("id") Long id) throws InterruptedException {
		Optional<LcDocPed> obj = lcDocPedRp.findById(id);
		return new ResponseEntity<LcDocPed>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/docped")
	public ResponseEntity<?> addLcDocPed(@RequestBody LcDocPed lcDocPed) throws Exception {
		if (ca.hasAuth(prm, 20, "A", "PEDIDO CLIENTE " + lcDocPed.getLcdoc().getId())) {
			lcDocPedRp.save(lcDocPed);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/docped/{id}/iddoc/{iddoc}")
	public ResponseEntity<?> removerlcDocPed(@PathVariable("id") Long id, @PathVariable("id") Long iddoc)
			throws Exception {
		if (ca.hasAuth(prm, 20, "R", "ID PEDIDO CLIENTE " + iddoc)) {
			lcDocPedRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	// DOC EQUIPAMENTO CLIENTE ****************************************
	@RequestAuthorization
	@GetMapping("/docequip/{id}")
	public ResponseEntity<LcDocEquip> lcDocEquip(@PathVariable("id") Long id) throws InterruptedException {
		Optional<LcDocEquip> obj = lcDocEquipRp.findById(id);
		return new ResponseEntity<LcDocEquip>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/docequip")
	public ResponseEntity<?> addLcDocEquip(@RequestBody LcDocEquip lcDocEquip) throws Exception {
		if (ca.hasAuth(prm, 20, "A", "EQUIPAMENTO CLIENTE " + lcDocEquip.getLcdoc().getId())) {
			lcDocEquipRp.save(lcDocEquip);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/docequip/{id}/iddoc/{iddoc}")
	public ResponseEntity<?> removerLcDocEquip(@PathVariable("id") Long id, @PathVariable("id") Long iddoc)
			throws Exception {
		if (ca.hasAuth(prm, 20, "R", "ID EQUIPAMENTO CLIENTE " + iddoc)) {
			lcDocEquipRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	// COTACOES***********************************************
	@RequestAuthorization
	@GetMapping("/docitemcot/{id}")
	public ResponseEntity<LcDocItemCot> lcDocItemCotRp(@PathVariable("id") Long id) throws InterruptedException {
		Optional<LcDocItemCot> obj = lcDocItemCotRp.findById(id);
		return new ResponseEntity<LcDocItemCot>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/docitemcot/f/{idfornec}/d/{iddoc}")
	public ResponseEntity<?> addLcDocItemCotFornec(@PathVariable("idfornec") Long idfornec,
			@PathVariable("iddoc") Long iddoc) throws Exception {
		if (ca.hasAuth(prm, 82, "A", "ID ADICIONAR FORNECEDOR A COTACAO " + iddoc)) {
			if (lcDocItemCotRp.qtdFonecLcdoc(iddoc, idfornec) == 0) {
				lcDocService.insereCotFornecItem(idfornec, iddoc);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/docitemcot")
	public ResponseEntity<?> atualizaLcDocItemCotFornec(@RequestBody LcDocItemCot lcDocItemCot) throws Exception {
		if (ca.hasAuth(prm, 82, "A", "ID ATUALIZA ITEM DE COTACAO " + lcDocItemCot.getLcdocitem().getLcdoc().getId())) {
			lcDocItemCotRp.save(lcDocItemCot);
			lcDocService.ajustaMelhorPrecoCot(lcDocItemCot.getLcdocitem().getId());
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/docitemcot/cotacaofornec/{id}/email/{email}")
	public ResponseEntity<?> cotarFornecedoresLcDocs(HttpServletRequest request, @PathVariable("id") Long id,
			@PathVariable("email") String email) throws Exception {
		if (ca.hasAuth(prm, 82, "Y", "ENVIO DE COTACAO PARA FORNECEDORES VIA E-MAIL")) {
			LcDoc doc = lcDocRp.findById(id).get();
			emailService.enviaCotacaoEmail(request, doc, email);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/docitemcot/impexcel")
	public ResponseEntity<?> importExcelFornecCotacao(@RequestParam("id") Long id,
			@RequestParam("file") MultipartFile file, @RequestParam("idforcec") Long idforcec) throws Exception {
		if (ca.hasAuth(prm, 3, "C", file.getName())) {
			File excel = LerArqUtil.multipartToFile(file, file.getOriginalFilename());
			lerExcelCotacaoService.lerExcelCotacao(excel, id, idforcec);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/docitemcot/compracotacao/para/{para}/nfecfg/{nfecfg}")
	public ResponseEntity<LcDoc> compraCotacao(@RequestBody LcDoc lcDoc, @PathVariable("para") Long para,
			@PathVariable("nfecfg") Integer nfecfg) throws Exception {
		LcDoc doc = null;
		if (ca.hasAuth(prm, 82, "N", "ID PEDIDO DE COMPRA VIA COTACAO " + lcDoc.getId())) {
			CdPessoa p = cdPessoaRp.findById(para).get();
			CdNfeCfg c = cdNfeCfgRp.findById(nfecfg).get();
			lcDoc.setCdpessoapara(p);
			lcDoc.setCdnfecfg(c);
			doc = lcDocService.duplicarDoc(lcDoc, "1", c);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/docitemcot/compramelhorcotacao/nfecfg/{nfecfg}")
	public ResponseEntity<?> compraMelhorCotacao(@RequestBody LcDoc lcDoc, @PathVariable("nfecfg") Integer nfecfg)
			throws Exception {
		if (ca.hasAuth(prm, 82, "N", "ID PEDIDO DE COMPRA MELHORES PRECOS DA COTACAO" + lcDoc.getId())) {
			// Cria varios baseado nos melhores precos
			CdNfeCfg c = cdNfeCfgRp.findById(nfecfg).get();
			List<LcDocItemCot> agruForn = lcDocItemCotRp.listaPorFonecLcdocMP(lcDoc.getId());
			for (LcDocItemCot ct : agruForn) {
				lcDoc.setCdpessoapara(ct.getCdpessoacot());
				lcDoc.setCdnfecfg(c);
				lcDocService.duplicarDoc(lcDoc, "2", c);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
