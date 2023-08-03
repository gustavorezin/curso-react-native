package com.midas.api.controller;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.midas.api.constant.MidasConfig;
import com.midas.api.mt.entity.CdNcm;
import com.midas.api.mt.repository.CdNcmRepository;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.AuxDados;
import com.midas.api.tenant.entity.CdCert;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.EsEst;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FsInut;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfeAut;
import com.midas.api.tenant.entity.FsNfeCobrDup;
import com.midas.api.tenant.entity.FsNfeEvento;
import com.midas.api.tenant.entity.FsNfeFreteVol;
import com.midas.api.tenant.entity.FsNfeItem;
import com.midas.api.tenant.entity.FsNfeMan;
import com.midas.api.tenant.entity.FsNfeManCons;
import com.midas.api.tenant.entity.FsNfePag;
import com.midas.api.tenant.entity.FsNfeRef;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.fiscal.GeraXMLNFe;
import com.midas.api.tenant.fiscal.service.FsNfeEnvioService;
import com.midas.api.tenant.fiscal.service.FsNfeGerarService;
import com.midas.api.tenant.fiscal.service.FsNfeService;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.fiscal.util.GeraChavesUtil;
import com.midas.api.tenant.repository.CdCertRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdProdutoCompRepository;
import com.midas.api.tenant.repository.CdProdutoRepository;
import com.midas.api.tenant.repository.EsEstMovRepository;
import com.midas.api.tenant.repository.EsEstRepository;
import com.midas.api.tenant.repository.FnCxmvRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.repository.FsCustomRepository;
import com.midas.api.tenant.repository.FsInutRepository;
import com.midas.api.tenant.repository.FsNfeAutRepository;
import com.midas.api.tenant.repository.FsNfeCobrDupRepository;
import com.midas.api.tenant.repository.FsNfeEventoRepository;
import com.midas.api.tenant.repository.FsNfeFreteVolRepository;
import com.midas.api.tenant.repository.FsNfeItemCofinsRepository;
import com.midas.api.tenant.repository.FsNfeItemPisRepository;
import com.midas.api.tenant.repository.FsNfeItemRepository;
import com.midas.api.tenant.repository.FsNfeManConsRepository;
import com.midas.api.tenant.repository.FsNfeManRepository;
import com.midas.api.tenant.repository.FsNfePagRepository;
import com.midas.api.tenant.repository.FsNfeRefRepository;
import com.midas.api.tenant.repository.FsNfeRepository;
import com.midas.api.tenant.repository.LcDocRepository;
import com.midas.api.tenant.service.CdProdutoService;
import com.midas.api.tenant.service.EmailService;
import com.midas.api.tenant.service.EsEstService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.LerArqUtil;
import com.midas.api.util.NumUtil;

@RestController
@RequestMapping("/private/fs")
public class FsNfeController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private FsNfeItemRepository fsNfeItemRp;
	@Autowired
	private FsNfeManRepository fsNfeManRp;
	@Autowired
	private FsNfeManConsRepository fsNfeManConsRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private FsCustomRepository fsCustomRp;
	@Autowired
	private LcDocRepository lcDocRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private FsNfeService fsNfeService;
	@Autowired
	private FsNfeEnvioService fsNfeServiceEnvio;
	@Autowired
	private EsEstMovRepository esEstMovRp;
	@Autowired
	private EsEstService esEstService;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private FsNfeGerarService gerarNfe;
	@Autowired
	private GeraXMLNFe geraXMLNFe;
	@Autowired
	private EmailService emailService;
	@Autowired
	private FsNfeEventoRepository fsNfeEventoRp;
	@Autowired
	private FsNfeRefRepository fsNfeRefRp;
	@Autowired
	private FsNfePagRepository fsNfePagRp;
	@Autowired
	private FsNfeCobrDupRepository fsNfeCobrDupRp;
	@Autowired
	private FsNfeFreteVolRepository fsNfeFreteVolRp;
	@Autowired
	private FsNfeAutRepository fsNfeAutRp;
	@Autowired
	private CdNcmRepository cdNcmRp;
	@Autowired
	private CdProdutoRepository cdProdutoRp;
	@Autowired
	private CdProdutoService cdProdutoService;
	@Autowired
	private EsEstRepository esEstRp;
	@Autowired
	private FnCxmvRepository fnCxmvRp;
	@Autowired
	private CdProdutoCompRepository cdProdutoCompRp;
	@Autowired
	private CdCertRepository cdCertRp;
	@Autowired
	private FsInutRepository fsInutRp;
	@Autowired
	private FsNfeItemPisRepository fsNfeItemPisRp;
	@Autowired
	private FsNfeItemCofinsRepository fsNfeItemCofinsRp;
	// TODO fazer selecionar vendas de um cliente e gerar uma nota so

	// DOCUMENTOS FISCAIS - NFE****************************************
	@RequestAuthorization
	@GetMapping("/nfe/{id}")
	public ResponseEntity<FsNfe> fsNfe(@PathVariable("id") Long id) {
		Optional<FsNfe> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = fsNfeRp.findById(id);
		} else {
			obj = fsNfeRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<FsNfe>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfe")
	public ResponseEntity<FsNfe> atualizarFsNfe(@RequestBody FsNfe fsnfe) throws Exception {
		FsNfe nfe = null;
		if (ca.hasAuth(prm, 18, "A", "ID " + fsnfe.getId() + " - " + fsnfe.getFsnfepartdest().getXnome())) {
			// DADOS DA CHAVE------------------------------------
			Integer coduf = fsnfe.getCdpessoaemp().getCdestado().getId();
			String dataAAMM = DataUtil.anoMesAAMM(fsnfe.getDhemi());
			String cpfcnpjemit = fsnfe.getCdpessoaemp().getCpfcnpj();
			Integer serie = fsnfe.getSerie();
			Integer tipoem = fsnfe.getTpemis();
			Integer modelo = fsnfe.getModelo();
			Integer numero = fsnfe.getNnf();
			Integer iddoc = fsnfe.getLcdoc().intValue();
			String chaveDeAcesso = GeraChavesUtil
					.geraChavePadrao(coduf, dataAAMM, cpfcnpjemit, modelo, serie, tipoem, numero, iddoc, "NFe")
					.replace("NFe", "");
			// DIGITO CHAVE
			String cDv = chaveDeAcesso.substring(43, 44);
			fsnfe.setChaveac(chaveDeAcesso);
			fsnfe.setCdv(Integer.valueOf(cDv));
			fsnfe.setDhemi(new Date(System.currentTimeMillis()));
			fsnfe.setDhsaient(new Date(System.currentTimeMillis()));
			nfe = fsNfeRp.save(fsnfe);
			fsNfeService.calculoTributoFsNfe(nfe);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<FsNfe>(nfe, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfe/item/{idfsnfe}")
	public ResponseEntity<FsNfeItem> fsNfeItem(@PathVariable("idfsnfe") Long idfsnfe) {
		Optional<FsNfeItem> obj = fsNfeItemRp.findById(idfsnfe);
		return new ResponseEntity<FsNfeItem>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfe/man/{id}")
	public ResponseEntity<FsNfeMan> fsNfeMan(@PathVariable("id") Long id) {
		Optional<FsNfeMan> obj = fsNfeManRp.findById(id);
		return new ResponseEntity<FsNfeMan>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfe/man/ultimo/{idlocal}")
	public ResponseEntity<FsNfeMan> fsNfeManUltimoLocal(@PathVariable("idlocal") Long idlocal) {
		FsNfeMan obj = fsNfeManRp.findLastByLocal(idlocal);
		return new ResponseEntity<FsNfeMan>(obj, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/nfe/mancons/{idlocal}")
	public ResponseEntity<FsNfeManCons> fsNfeManConsLocal(@PathVariable("idlocal") Long idlocal) {
		FsNfeManCons obj = fsNfeManConsRp.findByLocal(idlocal);
		if (obj == null) {
			CdPessoa local = cdPessoaRp.getById(idlocal);
			FsNfeManCons c = new FsNfeManCons();
			c.setCdpessoaemp(local);
			c.setData(new java.sql.Date(System.currentTimeMillis()));
			c.setHora(DataUtil.addRemMinTime(new Time(System.currentTimeMillis()), mc.tempobuscaman + 1, "R"));
			obj = fsNfeManConsRp.save(c);
		}
		return new ResponseEntity<FsNfeManCons>(obj, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfe/man/marcarimportada/{id}/st/{st}")
	public ResponseEntity<?> atualizarFsNfeManImportado(@PathVariable("id") Long id, @PathVariable("st") String st)
			throws Exception {
		if (ca.hasAuth(prm, 17, "A", "ID MARCAR MANIFESTO COMO IMPORTADO " + id)) {
			fsNfeManRp.updateSituacaoImpId(st, id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfe/man/marcarimportada/emlote/st/{st}")
	public ResponseEntity<?> atualizarFsNfeManImportadoEmLte(@RequestBody List<FsNfeMan> fsNfeMan,
			@PathVariable("st") String st) throws Exception {
		if (ca.hasAuth(prm, 17, "A", "ID MARCAR MANIFESTO COMO IMPORTADO EM LOTE")) {
			for (FsNfeMan m : fsNfeMan) {
				fsNfeManRp.updateSituacaoImpId(st, m.getId());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/nfe/{id}/tipo/{tipo}")
	public ResponseEntity<?> removerFsnfe(@PathVariable("id") Long id, @PathVariable("tipo") String tipo) {
		FsNfe fsnfe = fsNfeRp.findById(id).get();
		Integer acesso = 16;
		if (tipo.equals("E")) {
			acesso = 18;
		}
		if (ca.hasAuth(prm, acesso, "R", "ID " + id)) {
			fsNfeRp.deleteById(id);
			// Desvincula, titulos e manifesto
			lcDocRp.updateDocFiscalNfe(0, "00", 0L, id);
			if (fsnfe.getTipo().equals("E")) {
				fnTituloRp.attParaDocCobDocFiscal("S", 0, "00", 0L, id);
			} else {
				fnTituloRp.attParaDocCobDocFiscal("N", 0, "00", 0L, id);
			}
			fsNfeManRp.updateSituacaoImp("N", fsnfe.getChaveac());
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfe/remover/emlote/tipo/{tipo}")
	public ResponseEntity<?> removerFsnfeEmLote(@RequestBody List<FsNfe> fsNfe, @PathVariable("tipo") String tipo) {
		Integer acesso = 16;
		if (tipo.equals("E")) {
			acesso = 18;
		}
		if (ca.hasAuth(prm, acesso, "R", "REMOCAO DE NOTAS FISCAIS EM LOTE")) {
			for (FsNfe n : fsNfe) {
				fsNfeRp.deleteById(n.getId());
				// Desvincula venda e titulos
				if (n.getTipo().equals("E")) {
					fnTituloRp.attParaDocCobDocFiscal("S", 0, "00", 0L, n.getId());
				} else {
					fnTituloRp.attParaDocCobDocFiscal("N", 0, "00", 0L, n.getId());
				}
				fnTituloRp.attParaDocCobDocFiscal("S", 0, "00", 0L, n.getId());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/{id}/item")
	public ResponseEntity<?> adicionarFsNfeItem(@RequestBody FsNfeItem fsNfeItem, @PathVariable("id") Long id)
			throws Exception {
		if (ca.hasAuth(prm, 16, "N", fsNfeItem.getXprod())) {
			// Validacao NCM
			CdNcm ncm = cdNcmRp.findNcm(fsNfeItem.getNcm());
			if (ncm != null || fsNfeItem.getNcm().equals("00000000")) {
				FsNfe fsnfe = fsNfeRp.getById(id);
				fsNfeService.addItemManual(fsNfeItem, fsnfe);
				fsNfeService.calculoTributoFsNfe(fsnfe);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfe/{id}/item")
	public ResponseEntity<?> atualizarFsNfeItem(@RequestBody FsNfeItem fsNfeItem, @PathVariable("id") Long id)
			throws Exception {
		if (ca.hasAuth(prm, 16, "A", fsNfeItem.getXprod())) {
			FsNfe fsnfe = fsNfeRp.getById(id);
			fsNfeItem.setFsnfe(fsnfe);
			fsNfeItemRp.save(fsNfeItem);
			fsNfeService.calculoTributoFsNfe(fsnfe);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@DeleteMapping("/nfe/remnfeitem/{id}")
	public ResponseEntity<?> remFsNfeItem(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "R", "ID REMOVER ITEM " + id)) {
			fsNfeItemRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfe/atualiza/nfe/{id}/cfop/{cfop}")
	public ResponseEntity<?> atualizarFsNfeCFOPs(@PathVariable("id") Long id, @PathVariable("cfop") String cfop)
			throws Exception {
		if (ca.hasAuth(prm, 16, "A", "ID ATUALIZAR CFOP DE TODOS OS ITENS " + id)) {
			fsNfeItemRp.updateCFOPs(cfop, id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfe/atualiza/nfe/{id}/pc/{pc}")
	public ResponseEntity<?> atualizarFsNfePCs(@PathVariable("id") Long id, @PathVariable("pc") String pc)
			throws Exception {
		if (ca.hasAuth(prm, 16, "A", "ID ATUALIZAR PIS E COFINS DE TODOS OS ITENS " + id)) {
			fsNfeItemPisRp.updatePis(pc, id);
			fsNfeItemCofinsRp.updateCofins(pc, id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/nfe/atualiza/nfe/{id}/dhsaient/{dhsaient}")
	public ResponseEntity<?> atualizarFsNfeDataEntSai(@PathVariable("id") Long id,
			@PathVariable("dhsaient") String dhsaient) throws Exception {
		if (ca.hasAuth(prm, 16, "A", "ID ATUALIZAR DATA DE ENTRADA-SAIDA " + id)) {
			fsNfeRp.updateDataEntSai(Date.valueOf(dhsaient), id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfe/conferida")
	public ResponseEntity<?> atualizarFsNfeConferida(@RequestParam("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 16, "A", "ID " + id)) {
			FsNfe fsnfe = fsNfeRp.getById(id);
			fsnfe.setSt_fornec("S");
			fsnfe.setSt_cob("S");
			fsnfe.setSt_est("S");
			fsnfe.setSt_custos("S");
			fsNfeRp.save(fsnfe);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfe/conferida/emlote")
	public ResponseEntity<?> atualizarFsNfeConferidaLista(@RequestBody List<FsNfe> fsNfe) throws Exception {
		if (ca.hasAuth(prm, 16, "A", "CONFERE MULTIPLAS NF-E")) {
			if (fsNfe.size() > 0) {
				for (FsNfe f : fsNfe) {
					FsNfe fsnfe = fsNfeRp.getById(f.getId());
					fsnfe.setSt_fornec("S");
					fsnfe.setSt_cob("S");
					fsnfe.setSt_est("S");
					fsnfe.setSt_custos("S");
					fsNfeRp.save(fsnfe);
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/nfe/{id}/grupes/{idgrupes}/importafornecedor")
	public ResponseEntity<?> importaFornecedor(@PathVariable("id") Long id, @PathVariable("idgrupes") Integer idgrupes)
			throws Exception {
		if (ca.hasAuth(prm, 16, "C", "ID IMPORTA CADASTRO FORNECEDOR NF-E" + id)) {
			if (fsNfeService.importaFornecedor(id, idgrupes) == "ok") {
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/{id}/importacobranca")
	public ResponseEntity<?> importaCobranca(@RequestBody List<FnTitulo> fnTitulos, @PathVariable("id") Long id)
			throws Exception {
		if (ca.hasAuth(prm, 16, "C", "ID IMPORTA COBRANCA NF-E " + id)) {
			String retorno = fsNfeService.importaCobranca(fnTitulos, id);
			if (retorno == "ok") {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			if (retorno == "naocadastrado") {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
			if (retorno == "") {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return null;
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/nfe/{id}/devolvecobranca")
	public ResponseEntity<?> devolveCobranca(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 16, "C", "ID DEVOLVE COBRANCA NF-E")) {
			Optional<FsNfe> n = fsNfeRp.findById(id);
			Integer qtdmovs = fnTituloRp.verificaTitulosMovimentadosNota(String.valueOf(n.get().getModelo()),
					n.get().getId(), n.get().getNnf(), "S");
			if (qtdmovs == 0) {
				fsNfeRp.entradaCobSt("N", id);
				List<FnTitulo> ts = fnTituloRp.titulosNota(String.valueOf(n.get().getModelo()), n.get().getId(),
						n.get().getNnf(), "S");
				for (FnTitulo f : ts) {
					fnTituloRp.delete(f);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return null;
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/nfe/{id}/importaestoque")
	public ResponseEntity<?> importaEstoque(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 16, "C", "ID IMPORTA ESTOQUE NF-E")) {
			String retorno = fsNfeService.importaEstoque(id);
			if (retorno == "ok") {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			if (retorno == "naovinculado") {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
			if (retorno == "naocadastrado") {
				return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
			}
			if (retorno == "") {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return null;
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/nfe/{id}/devolveestoque")
	public ResponseEntity<?> devolveEstoque(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 16, "C", "ID DEVOLVE ESTOQUE NF-E")) {
			Optional<FsNfe> n = fsNfeRp.findById(id);
			fsNfeRp.entradaEstSt("N", id);
			esEstService.geradorFsNfe(n.get(), "S");
			esEstMovRp.removeItem("01", n.get().getId(), n.get().getNnf().longValue(), "E");
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return null;
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfe/atualizacustos/{idnfe}/lc/{local}")
	public ResponseEntity<EsEst> atualizarEsEstMovCustos(@PathVariable("idnfe") Long idnfe,
			@PathVariable("local") Long local, @RequestBody List<AuxDados> auxDados) throws Exception {
		EsEst esEst = null;
		if (ca.hasAuth(prm, 16, "A", "ID NFE ALTERA-ATUALIZA CUSTO ESTOQUE" + idnfe)) {
			for (AuxDados a : auxDados) {
				if (a.getCampo1() != null) {
					CdProduto p = cdProdutoRp.findByIdCodigoUnico(Long.valueOf(a.getCampo1()));
					esEstRp.updateVCustoProdLocal(new BigDecimal(a.getCampo16()), p.getId(), local);
					// Atualiza custos na composicao
					cdProdutoCompRp.updateVCustoProd(new BigDecimal(a.getCampo16()), p.getId(), local);
					fsNfeRp.entradaCustoSt("S", idnfe);
					cdProdutoService.atualizaTabPreco(local, p, new BigDecimal(a.getCampo16()));
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<EsEst>(esEst, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfe/{idfsnfe}/compraanterior/{idprod}")
	public ResponseEntity<List<FsNfe>> fsNfeItemCompraAnterior(@PathVariable("idfsnfe") Long idfsnfe,
			@PathVariable("idprod") Long idprod) {
		List<FsNfe> lista = fsNfeRp.findAllByIdprod(idprod, idfsnfe);
		return new ResponseEntity<List<FsNfe>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfe/{idfsnfe}/ultimascompras")
	public ResponseEntity<List<AuxDados>> fsNfeItemUltimos90Dias(@PathVariable("idfsnfe") Long idfsnfe) {
		List<AuxDados> lista = fsNfeService.dadosUltimasCompras(idfsnfe);
		return new ResponseEntity<List<AuxDados>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfe/p/{pagina}/b/{busca}/oensai/{oensai}/o/{ordem}/d/{ordemdir}/lc/{local}/tp/{tipo}/uffim/{uffim}/ipp/{itenspp}"
			+ "/dti/{dataini}/dtf/{datafim}/modelo/{modelo}/st/{st}/status/{status}")
	public ResponseEntity<Page<FsNfe>> listaFsNfesBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("oensai") String oensai,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("local") Long local, @PathVariable("tipo") String tipo, @PathVariable("uffim") String uffim,
			@PathVariable("itenspp") int itenspp, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("modelo") Integer modelo,
			@PathVariable("st") String st, @PathVariable("status") Integer status) throws Exception {
		if (ca.hasAuth(prm, 16, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<FsNfe> lista = fsCustomRp.listaFsNfe(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo, busca,
					uffim, modelo, st, status, oensai, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<FsNfe>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfe/entrada/p/{pagina}/b/{busca}/oensai/{oensai}/o/{ordem}/d/{ordemdir}/lc/{local}/tp/{tipo}/uffim/{uffim}/ipp/{itenspp}"
			+ "/dti/{dataini}/dtf/{datafim}/modelo/{modelo}/st/{st}/status/{status}")
	public ResponseEntity<Page<FsNfe>> listaFsNfesEntBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("oensai") String oensai,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("local") Long local, @PathVariable("tipo") String tipo, @PathVariable("uffim") String uffim,
			@PathVariable("itenspp") int itenspp, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("modelo") Integer modelo,
			@PathVariable("st") String st, @PathVariable("status") Integer status) throws Exception {
		if (ca.hasAuth(prm, 16, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<FsNfe> lista = fsCustomRp.listaFsNfe(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo, busca,
					uffim, modelo, st, status, oensai, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<FsNfe>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfe/valores/b/{busca}/oensai/{oensai}/lc/{local}/tp/{tipo}/uffim/{uffim}/dti/{dataini}/dtf/{datafim}/modelo/{modelo}/st/{st}/status/{status}")
	public ResponseEntity<List<FsNfe>> listaFsNfesValores(@PathVariable("busca") String busca,
			@PathVariable("oensai") String oensai, @PathVariable("local") Long local, @PathVariable("tipo") String tipo,
			@PathVariable("uffim") String uffim, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("modelo") Integer modelo,
			@PathVariable("st") String st, @PathVariable("status") Integer status) throws Exception {
		if (ca.hasAuth(prm, 43, "L", "LISTAGEM / CONSULTA VALORES")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<FsNfe> lista = fsCustomRp.listaFsNfeValores(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo,
					busca, uffim, modelo, st, status, oensai);
			return new ResponseEntity<List<FsNfe>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfe/faturamento/anual/local/{local}/ano/{ano}/tp/{tipo}")
	public ResponseEntity<List<BigDecimal>> listaFsNfeFaturaAnual(@PathVariable("local") Long local,
			@PathVariable("ano") Integer ano, @PathVariable("tipo") String tipo) throws Exception {
		// Verifica se tem acesso a todos locais
		if (prm.cliente().getAclocal().equals("N")) {
			local = prm.cliente().getCdpessoaemp();
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
					valor = fsNfeRp.faturaMesAnoLocalPeriodo(local, dataini, datafim, tipo, 100, 150);
				} else {
					valor = fsNfeRp.faturaMesAnoPeriodo(dataini, datafim, tipo, 100, 150);
				}
			} else {
				valor = fsNfeRp.faturaMesAnoLocalPeriodo(local, dataini, datafim, tipo, 100, 150);
			}
			aux.add(valor);
		}
		return new ResponseEntity<List<BigDecimal>>(aux, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfe/tp/{tipo}/status/{status}")
	public ResponseEntity<List<FsNfe>> listaNfeStatusTipo(@PathVariable("tipo") String tipo,
			@PathVariable("status") Integer status) throws Exception {
		if (ca.hasAuth(prm, 24, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			String aclocal = prm.cliente().getAclocal();
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			List<FsNfe> lista = null;
			if (aclocal.equals("S")) {
				if (local > 0) {
					lista = fsNfeRp.listaNfeStTipoLocal(local, tipo, status);
				} else {
					lista = fsNfeRp.listaNfeStTipo(tipo, status);
				}
			} else {
				lista = fsNfeRp.listaNfeStTipoLocal(prm.cliente().getCdpessoaemp(), tipo, status);
			}
			return new ResponseEntity<List<FsNfe>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfe/pendente/local/{local}")
	public ResponseEntity<List<FsNfe>> listaFsNfesSt(@PathVariable("local") Long local) throws Exception {
		if (ca.hasAuth(prm, 17, "L", "LISTAGEM / CONSULTA NF-E")) {
			List<FsNfe> lista = null;
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				if (local > 0) {
					lista = fsNfeRp.listaNfeStImpLocal(local);
				} else {
					lista = fsNfeRp.listaNfetImp();
				}
			} else {
				lista = fsNfeRp.listaNfeStImpLocal(prm.cliente().getCdpessoaemp());
			}
			return new ResponseEntity<List<FsNfe>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfe/man/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/lc/{local}/ipp/{itenspp}/dti/{dataini}/dtf/{datafim}/st/{st}/stimp/{stimp}")
	public ResponseEntity<Page<FsNfeMan>> listaFsNfesManBuscaMan(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("local") Long local,
			@PathVariable("itenspp") int itenspp, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("st") String st, @PathVariable("stimp") String stimp)
			throws Exception {
		if (ca.hasAuth(prm, 17, "L", "LISTAGEM / CONSULTA MANIFESTO")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<FsNfeMan> lista = fsCustomRp.listaFsNfeMan(local, Date.valueOf(dataini), Date.valueOf(datafim), busca,
					st, stimp, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<FsNfeMan>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfe/man/valores/b/{busca}/lc/{local}/dti/{dataini}/dtf/{datafim}/st/{st}/stimp/{stimp}")
	public ResponseEntity<List<FsNfeMan>> listaFsNfesBuscaManValores(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("st") String st, @PathVariable("stimp") String stimp)
			throws Exception {
		if (ca.hasAuth(prm, 17, "L", "LISTAGEM / CONSULTA VALORES MANIFESTO")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<FsNfeMan> lista = fsCustomRp.listaFsNfeManValores(local, Date.valueOf(dataini), Date.valueOf(datafim),
					busca, st, stimp);
			return new ResponseEntity<List<FsNfeMan>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfe/man/pendente/local/{local}")
	public ResponseEntity<List<FsNfeMan>> listaFsNfesManSt(@PathVariable("local") Long local) throws Exception {
		if (ca.hasAuth(prm, 17, "L", "LISTAGEM / CONSULTA MANIFESTO")) {
			List<FsNfeMan> lista = null;
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				if (local > 0) {
					lista = fsNfeManRp.listaNfemanManifestoStImpLocal(local);
				} else {
					lista = fsNfeManRp.listaNfemanManifestoStImp();
				}
			} else {
				lista = fsNfeManRp.listaNfemanManifestoStImpLocal(prm.cliente().getCdpessoaemp());
			}
			return new ResponseEntity<List<FsNfeMan>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/uploadnfeimporta")
	public ResponseEntity<String> uploadNfeImporta(@RequestParam("idlocal") Long idlocal,
			@RequestParam("file") MultipartFile file) throws Exception {
		if (ca.hasAuth(prm, 16, "C", "IMPORTA XML NF-E")) {
			try {
				String retorno = "ok";
				File xmlimportado = LerArqUtil.multipartToFile(file, file.getOriginalFilename());
				retorno = fsNfeService.importarXMLNfe(xmlimportado, idlocal);
				xmlimportado.delete();
				return new ResponseEntity<String>(retorno, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/uploadnfeimportamultiplos")
	public ResponseEntity<List<AuxDados>> uploadNfeImportaMultiplos(@RequestParam("idlocal") Long idlocal,
			@RequestParam("files") MultipartFile[] files) throws Exception {
		if (ca.hasAuth(prm, 16, "C", "IMPORTA MULTIPLOS XML NF-E")) {
			try {
				List<AuxDados> aux = new ArrayList<AuxDados>();
				for (int i = 0; i < files.length; i++) {
					File xmlimportado = LerArqUtil.multipartToFile(files[i], files[i].getOriginalFilename());
					String[] resposta = fsNfeService.importarXMLNfeMultiplos(xmlimportado, idlocal);
					AuxDados a = new AuxDados();
					a.setCampo1(resposta[0]);
					a.setCampo2(resposta[1]);
					a.setCampo3(resposta[2]);
					a.setCampo4(resposta[3]);
					a.setCampo5(resposta[4]);
					a.setCampo6(resposta[5]);
					aux.add(a);
					xmlimportado.delete();
				}
				return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<List<AuxDados>>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/nfe/conserv")
	public ResponseEntity<String> conServicoNfe(HttpServletRequest request, @RequestParam("idlocal") Long idlocal,
			@RequestParam("modelo") String modelo) throws Exception {
		if (ca.hasAuth(prm, 18, "Y", "ID " + idlocal)) {
			String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
			String status = fsNfeServiceEnvio.consultaServico(idlocal, ambiente, modelo, request);
			return new ResponseEntity<>(status, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/nfe/conscad")
	public ResponseEntity<String[]> consCad(HttpServletRequest request, @RequestParam("idlocal") Long idlocal,
			@RequestParam("cpfcnpj") String cpfcnpj, @RequestParam("uf") String uf) throws Exception {
		if (ca.hasAuth(prm, 30, "Y", cpfcnpj)) {
			String[] ret = fsNfeServiceEnvio.consultaCadastroIE(idlocal, cpfcnpj, uf, request);
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/nfe/distnfe")
	public ResponseEntity<String[]> disNfe(HttpServletRequest request, @RequestParam("idlocal") Long idlocal)
			throws Exception {
		// Controle modulo
		String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
		if (ca.hasAuthModulo(prm, 13)) {
			// Autoridade
			if (ca.hasAuth(prm, 17, "Y", "CONSULTA NF-E DIST. CONTRA CNPJ")) {
				String ret[] = fsNfeService.consultaDist(idlocal, ambiente, request);
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		}
	}

	@RequestAuthorization
	@PostMapping("/nfe/distnfeauto")
	public ResponseEntity<String[]> disNfeAuto(HttpServletRequest request) throws Exception {
		// Controle modulo
		String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
		if (ca.hasAuthModulo(prm, 13) && prm.cliente().getSiscfg().isSis_busca_auto_nfe() == true) {
			// Autoridade
			if (ca.hasAuth(prm, 17, "Y", "CONSULTA NF-E DIST. CONTRA CNPJ")) {
				String ret[] = new String[4];
				for (CdCert cp : cdCertRp.findByStatus("ATIVO")) {
					ret = fsNfeService.consultaDist(cp.getCdpessoaemp().getId(), ambiente, request);
				}
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/inutnfe")
	public ResponseEntity<String[]> inutilizaNfe(HttpServletRequest request, @RequestParam("idlocal") Long idlocal,
			@RequestParam("modelo") String modelo, @RequestParam("serie") String serie,
			@RequestParam("numNI") String numNI, @RequestParam("numNF") String numNF,
			@RequestParam("justifica") String justifica) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "INUTILIZACAO NF-E | " + justifica)) {
			String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
			String ret[] = fsNfeServiceEnvio.inutilizaNFe(idlocal, ambiente, modelo, serie, numNI, numNF, justifica,
					request);
			// Registra itens
			if (ret[0].equals("102") || ret[0].equals("256") || ret[0].equals("563")) {
				fsNfeService.inutilizaDoc(idlocal, ambiente, Integer.valueOf(modelo), Integer.valueOf(serie),
						Integer.valueOf(numNI), Integer.valueOf(numNF), justifica);
			}
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfe/inutnfe/local/{local}/ano/{ano}/modelo/{modelo}")
	public ResponseEntity<List<FsInut>> listaFsInut(@PathVariable("local") Long local, @PathVariable("ano") Integer ano,
			@PathVariable("modelo") Integer modelo) throws Exception {
		if (ca.hasAuth(prm, 17, "L", "LISTAGEM / CONSULTA INUTILIZADAS")) {
			List<FsInut> lista = null;
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				if (local > 0) {
					lista = fsInutRp.listaFsInutLocalAno(local, ano, modelo);
				} else {
					lista = fsInutRp.listaFsInutAno(ano, modelo);
				}
			} else {
				lista = fsInutRp.listaFsInutLocalAno(prm.cliente().getCdpessoaemp(), ano, modelo);
			}
			return new ResponseEntity<List<FsInut>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/manifestanfe")
	public ResponseEntity<String[]> manifestaNfe(HttpServletRequest request, @RequestParam("idlocal") Long idlocal,
			@RequestParam("chave") String chave, @RequestParam("tpevento") String tpevento,
			@RequestParam("justifica") String justifica) throws Exception {
		if (ca.hasAuth(prm, 17, "E", "MANIFESTACAO NF-E " + chave)) {
			String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
			String nprot = "";
			String modelo = "55";// 55 - Para manifesto
			String uf = "91"; // AMBIENTE NACIONAL
			String ufn = "AN"; // AMBIENTE NACIONAL
			String ret[] = fsNfeServiceEnvio.eventoNFe(null, idlocal, ambiente, modelo, uf, ufn, chave, nprot, tpevento,
					justifica, request);
			// Ajusta situacao
			if (ret[0].equals("135")) {
				fsNfeManRp.updateSituacao(Integer.valueOf(tpevento), chave);
			}
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/manifestanfe/emlote/tpevento/{tpevento}")
	public ResponseEntity<List<AuxDados>> manifestaNfeEmLote(HttpServletRequest request,
			@RequestBody List<FsNfeMan> fsnfeMans, @PathVariable("tpevento") String tpevento) throws Exception {
		String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
		if (ca.hasAuth(prm, 17, "E", "MANIFESTACAO NF-E EM LOTE")) {
			String justifica = "";
			String nprot = "";
			String modelo = "55";// 55 - Para manifesto
			String uf = "91"; // AMBIENTE NACIONAL
			String ufn = "AN"; // AMBIENTE NACIONAL
			List<AuxDados> aux = new ArrayList<AuxDados>();
			for (FsNfeMan n : fsnfeMans) {
				String resposta[] = fsNfeServiceEnvio.eventoNFe(null, n.getCdpessoaemp().getId(), ambiente, modelo, uf,
						ufn, n.getChave(), nprot, tpevento, justifica, request);
				// Ajusta situacao
				if (resposta[0].equals("135")) {
					fsNfeManRp.updateSituacao(Integer.valueOf(tpevento), n.getChave());
				}
				AuxDados a = new AuxDados();
				a.setCampo1(resposta[0]);
				a.setCampo2(resposta[1]);
				a.setCampo3(resposta[2]);
				a.setCampo4(n.getNnf());
				a.setCampo5(String.valueOf(n.getDhemi()));
				a.setCampo6(n.getNome());
				a.setCampo7(n.getChave());
				a.setCampo8(String.valueOf(n.getVnf()));
				aux.add(a);
			}
			return new ResponseEntity<List<AuxDados>>(aux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/downloadxmlnfe")
	public ResponseEntity<String[]> downloadXmlNfe(HttpServletRequest request, @RequestParam("idlocal") Long idlocal,
			@RequestParam("chave") String chave) throws Exception {
		if (ca.hasAuth(prm, 17, "Y", "DOWNLOAD XML NF-E " + chave)) {
			String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
			String ret[] = fsNfeServiceEnvio.dwnloadXmlNFe(idlocal, ambiente, chave, request);
			ret[3] = "ok";
			String chaveprotNFe = "";
			if (!ret[2].equals("")) {
				chaveprotNFe = FsUtil.eXmlDet(ret[2], "protNFe", 0, "chNFe");
			}
			if (!chaveprotNFe.equals("")) {
				ret[3] = fsNfeService.importarXMLNfeChave(ret[2], idlocal);
				if (ret[3].equals("ok") || ret[3].equals("jaimportado")) {
					fsNfeManRp.updateSituacaoImp("S", chave);
				}
			}
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/nfe/downloadxmlnfearquivo")
	public ResponseEntity<byte[]> downloadXmlNfeArquivo(HttpServletRequest request,
			@RequestParam("idlocal") Long idlocal, @RequestParam("chave") String chave) throws Exception {
		if (ca.hasAuth(prm, 17, "Y", "DOWNLOAD ARQUIVO XML NF-E " + chave)) {
			String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
			String ret[] = fsNfeServiceEnvio.dwnloadXmlNFe(idlocal, ambiente, chave, request);
			String chaveprotNFe = "";
			if (!ret[2].equals("")) {
				chaveprotNFe = FsUtil.eXmlDet(ret[2], "protNFe", 0, "chNFe");
			}
			if (!chaveprotNFe.equals("")) {
				byte[] contents = ret[2].getBytes();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.parseMediaType("application/xml"));
				headers.set("Content-Disposition", "attachment; filename=NFe" + chaveprotNFe + "-proc.xml");
				return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/geranfe")
	public ResponseEntity<FsNfe> geraNfe(HttpServletRequest request, @RequestParam("iddoc") Long iddoc,
			@RequestParam("marca") String marca, @RequestParam("especie") String especie,
			@RequestParam("nrvol") String nrvol, @RequestParam("qvol") BigDecimal qvol,
			@RequestParam("pesol") BigDecimal pesol, @RequestParam("pesob") BigDecimal pesob,
			@RequestParam("modelo") String modelo, @RequestParam("consfinal") String consfinal,
			@RequestParam("indpres") String indpres) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID Documento para NF " + iddoc)) {
			Optional<LcDoc> lcDoc = lcDocRp.findById(iddoc);
			// Se nao foi emitido---------------
			if (lcDoc.get().getNumnota().equals(0)) {
				String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
				FsNfe nfe = gerarNfe.gerarNFe(lcDoc.get(), modelo, ambiente, consfinal, indpres, qvol, especie, marca,
						nrvol, pesol, pesob);
				FsNfe n = fsNfeRp.findById(nfe.getId()).get();
				lcDocRp.updateDocFiscal(nfe.getNnf(), nfe.getModelo().toString(), nfe.getId(), iddoc);
				fnTituloRp.attParaCobDocFiscal("S", nfe.getNnf(), nfe.getModelo().toString(), nfe.getId(), iddoc);
				return new ResponseEntity<FsNfe>(n, HttpStatus.OK);
			} else {
				return new ResponseEntity<FsNfe>(HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/nfe/envianfe/{id}")
	public ResponseEntity<String[]> enviarNfe(HttpServletRequest request, @PathVariable("id") Long id)
			throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID Envio NFE " + id)) {
			FsNfe n = fsNfeRp.findById(id).get();
			LcDoc lcDoc = new LcDoc();
			// LcDoc
			if (n.getLcdoc() != null) {
				if (n.getLcdoc() > 0) {
					lcDoc = lcDocRp.findById(n.getLcdoc()).get();
				}
			}
			String xml = geraXMLNFe.geraXMLNFe(n, lcDoc, mc);
			String ret[] = fsNfeServiceEnvio.envioNFe(n, xml, request);
			// Se Autorizado------------------
			if (ret[0].equals("100")) {
				// Manda o ID da FsNfe para tela
				ret[9] = prm.cliente().getSiscfg().isSis_email_auto_nfe() + "";
				ret[10] = n.getId().toString();
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			}
			// Se Denegada------------------
			if (ret[0].equals("205") || ret[0].equals("302")) {
				// Manda o ID da FsNfe para tela
				ret[9] = prm.cliente().getSiscfg().isSis_email_auto_nfe() + "";
				ret[10] = n.getId().toString();
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			}
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/geranfedireta")
	public ResponseEntity<String[]> geraNfeDireta(HttpServletRequest request, @RequestParam("iddoc") Long iddoc,
			@RequestParam("marca") String marca, @RequestParam("especie") String especie,
			@RequestParam("nrvol") String nrvol, @RequestParam("qvol") BigDecimal qvol,
			@RequestParam("pesol") BigDecimal pesol, @RequestParam("pesob") BigDecimal pesob,
			@RequestParam("modelo") String modelo, @RequestParam("consfinal") String consfinal,
			@RequestParam("indpres") String indpres) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID Documento para NF " + iddoc)) {
			Optional<LcDoc> lcDoc = lcDocRp.findById(iddoc);
			// Se nao foi emitido---------------
			if (lcDoc.get().getNumnota().equals(0)) {
				String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
				FsNfe nfe = gerarNfe.gerarNFe(lcDoc.get(), modelo, ambiente, consfinal, indpres, qvol, especie, marca,
						nrvol, pesol, pesob);
				String xml = geraXMLNFe.geraXMLNFe(nfe, lcDoc.get(), mc);
				String ret[] = fsNfeServiceEnvio.envioNFe(nfe, xml, request);
				if (ret[0].equals("100") || ret[0].equals("205") || ret[0].equals("302")) {
					// Se Autorizado------------------
					if (ret[0].equals("100")) {
						lcDocRp.updateDocFiscal(nfe.getNnf(), nfe.getModelo().toString(), nfe.getId(), iddoc);
						fnTituloRp.attParaCobDocFiscal("S", nfe.getNnf(), nfe.getModelo().toString(), nfe.getId(),
								iddoc);
						// Manda o ID da FsNfe para tela
						ret[9] = prm.cliente().getSiscfg().isSis_email_auto_nfe() + "";
						ret[10] = nfe.getId().toString();
						return new ResponseEntity<String[]>(ret, HttpStatus.OK);
					}
					// Se Denegada------------------
					if (ret[0].equals("205") || ret[0].equals("302")) {
						lcDocRp.updateDocFiscal(nfe.getNnf(), nfe.getModelo().toString(), nfe.getId(), iddoc);
						// Manda o ID da FsNfe para tela
						ret[9] = prm.cliente().getSiscfg().isSis_email_auto_nfe() + "";
						ret[10] = nfe.getId().toString();
						return new ResponseEntity<String[]>(ret, HttpStatus.OK);
					}
					return new ResponseEntity<String[]>(ret, HttpStatus.OK);
				} else {
					fsNfeRp.deleteById(nfe.getId());
					return new ResponseEntity<String[]>(ret, HttpStatus.OK);
				}
			} else {
				// Ja emitido---------------------
				String ret[] = new String[2];
				ret[0] = "0";
				ret[1] = "Documento fiscal já emitido anteriormente!";
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/geranfcedireta")
	public ResponseEntity<String[]> geraNfceDireta(HttpServletRequest request, @RequestParam("iddoc") Long iddoc)
			throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID Documento para NFC " + iddoc)) {
			Optional<LcDoc> lcDoc = lcDocRp.findById(iddoc);
			// Se nao foi emitido---------------
			if (lcDoc.get().getNumnota().equals(0)) {
				String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
				String especie = "PRODUTOS DIVERSOS";
				String marca = "DIVERSOS";
				String nrvol = "";
				BigDecimal qvol = new BigDecimal(lcDoc.get().getQtdit());
				BigDecimal pesol = lcDoc.get().getMpesol();
				BigDecimal pesob = lcDoc.get().getMpesob();
				String indpres = "1";// 1 - Operação presencial;
				FsNfe nfe = gerarNfe.gerarNFe(lcDoc.get(), "65", ambiente, "1", indpres, qvol, especie, marca, nrvol,
						pesol, pesob);
				String xml = geraXMLNFe.geraXMLNFe(nfe, lcDoc.get(), mc);
				String ret[] = fsNfeServiceEnvio.envioNFe(nfe, xml, request);
				// Se Autorizado------------------
				if (ret[0].equals("100")) {
					lcDocRp.updateDocFiscal(nfe.getNnf(), "65", nfe.getId(), iddoc);
					fnTituloRp.attParaCobDocFiscal("S", nfe.getNnf(), nfe.getModelo().toString(), nfe.getId(), iddoc);
					// Manda o ID da FsNfe para tela
					ret[9] = prm.cliente().getSiscfg().isSis_email_auto_nfe() + "";
					ret[10] = nfe.getId().toString();
					return new ResponseEntity<String[]>(ret, HttpStatus.OK);
				} else {
					fsNfeRp.deleteById(nfe.getId());
					return new ResponseEntity<String[]>(ret, HttpStatus.OK);
				}
			} else {
				// Ja emitido---------------------
				String ret[] = new String[2];
				ret[0] = "0";
				ret[1] = "Documento fiscal já emitido anteriormente!";
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/nfe/envioemail/{id}")
	public ResponseEntity<?> envioNFeEmail(HttpServletRequest request, @PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID Documento para Envio por E-mail NF-e " + id)) {
			emailService.enviaNFeEmail(request, id, null);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/nfe/envioemail/{id}/email/{email}")
	public ResponseEntity<?> envioNFeEmailOutros(HttpServletRequest request, @PathVariable("id") Long id,
			@PathVariable("email") String email) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID Documento para Envio por E-mail NF-e " + id)) {
			emailService.enviaNFeEmail(request, id, email);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/envioxmlemail")
	public ResponseEntity<?> envioNFeEmailXML(HttpServletRequest request, @RequestParam("local") Long local,
			@RequestParam("dtini") String dtini, @RequestParam("dtfim") String dtfim,
			@RequestParam("dests") String dests, @RequestParam("info") String info,
			@RequestParam("enviopdf") String enviopdf, @RequestParam("modelo") String modelo,
			@RequestParam("tipo") String tipo) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "XML de Arquivos de NF-e para envio por E-mail " + dests)) {
			emailService.enviaNFeEmailXML(request, local, Date.valueOf(dtini), Date.valueOf(dtfim), dests, info,
					enviopdf, modelo, tipo);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/corrigirnfe")
	public ResponseEntity<String[]> corrigirNfe(HttpServletRequest request, @RequestParam("idnfe") Long idnfe,
			@RequestParam("correcao") String correcao) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID CORRECAO DE NF-E " + idnfe)) {
			FsNfe nfe = fsNfeRp.findById(idnfe).get();
			String tpevento = "110110";
			String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
			String modelo = nfe.getModelo().toString();
			String chave = nfe.getChaveac();
			String nprot = nfe.getNprot();
			String uf = nfe.getCdpessoaemp().getCdestado().getId().toString();
			String ufn = nfe.getCdpessoaemp().getCdestado().getUf();
			correcao = correcao.replaceAll("[\\r\\n]+", " ");
			String ret[] = fsNfeServiceEnvio.eventoNFe(idnfe, nfe.getCdpessoaemp().getId(), ambiente, modelo, uf, ufn,
					chave, nprot, tpevento, correcao, request);
			// Ajusta situacao
			if (ret[0].equals("135")) {
				FsNfeEvento e = new FsNfeEvento();
				e.setFsnfe(nfe);
				e.setNumseq(Integer.valueOf(ret[4]));
				e.setTpevento(tpevento);
				e.setXjust(CaracterUtil.remUpper(correcao));
				e.setXml(ret[3]);
				fsNfeEventoRp.save(e);
			}
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/cancelanfe")
	public ResponseEntity<String[]> cancelaNfe(HttpServletRequest request, @RequestParam("idnfe") Long idnfe,
			@RequestParam("justifica") String justifica) throws Exception {
		if (ca.hasAuth(prm, 18, "X", "ID CANCELAMENTO DE NF-E " + idnfe)) {
			FsNfe nfe = fsNfeRp.findById(idnfe).get();
			Integer qtdTMov = fnCxmvRp.qtdTitulosPagos(nfe.getLcdoc());
			if (qtdTMov.equals(0)) {
				String tpevento = "110111";
				String ambiente = prm.cliente().getSiscfg().getSis_amb_nfe();
				String modelo = nfe.getModelo().toString();
				String chave = nfe.getChaveac();
				String nprot = nfe.getNprot();
				String uf = nfe.getCdpessoaemp().getCdestado().getId().toString();
				String ufn = nfe.getCdpessoaemp().getCdestado().getUf();
				justifica = justifica.replaceAll("[\\r\\n]+", " ");
				String ret[] = fsNfeServiceEnvio.eventoNFe(idnfe, nfe.getCdpessoaemp().getId(), ambiente, modelo, uf,
						ufn, chave, nprot, tpevento, justifica, request);
				// Ajusta situacao
				if (ret[0].equals("135")) {
					FsNfeEvento e = new FsNfeEvento();
					e.setFsnfe(nfe);
					e.setNumseq(Integer.valueOf(ret[4]));
					e.setTpevento(tpevento);
					e.setXjust(CaracterUtil.remUpper(justifica));
					e.setXml(ret[3]);
					fsNfeEventoRp.save(e);
					fsNfeRp.updateXMLNFeStatus(nfe.getXml(), 101, nprot, idnfe);
				}
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfe/anexapedido/idnfe/{idnfe}/iddoc/{iddoc}/op/{op}")
	public ResponseEntity<?> anexaCompraNfe(@PathVariable("idnfe") Long idnfe, @PathVariable("iddoc") Long iddoc,
			@PathVariable("op") String op) throws Exception {
		if (ca.hasAuth(prm, 16, "A", "ID NOTA PEDIDO COMPRA ANEXA NF-E " + idnfe)) {
			// Anexar
			if (op.equals("A")) {
				FsNfe n = fsNfeRp.findById(idnfe).get();
				LcDoc d = lcDocRp.findById(iddoc).get();
				// NFe para Doc
				lcDocRp.updateDocFiscal(n.getNnf(), n.getModelo() + "", idnfe, iddoc);
				// Doc para NFe
				fsNfeRp.updateDocFiscal(d.getNumero(), iddoc, d.getTipo(), idnfe);
			}
			// Desvincular
			if (op.equals("D")) {
				// NFe para Doc
				lcDocRp.updateDocFiscal(0, "00", 0L, iddoc);
				// Doc para NFe
				fsNfeRp.updateDocFiscal(0L, 0L, "00", idnfe);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	/* FUNCOES ADICIONAIS EXTRAS */
	@RequestAuthorization
	@PostMapping("/nfe/addnferef")
	public ResponseEntity<?> addFsNfeRef(@RequestParam("id") Long id, @RequestParam("chave") String chave)
			throws Exception {
		if (ca.hasAuth(prm, 18, "N", "ID ADICIONAR CHAVE REFERENCIADA " + id + " - " + chave)) {
			FsNfe fsnfe = fsNfeRp.getById(id);
			FsNfeRef r = new FsNfeRef();
			r.setFsnfe(fsnfe);
			r.setRefnfe(chave);
			fsNfeRefRp.save(r);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/nfe/remnferef/{id}")
	public ResponseEntity<?> remFsNfeRef(@PathVariable("id") Integer id) throws Exception {
		if (ca.hasAuth(prm, 18, "R", "ID REMOVER CHAVE REFERENCIADA " + id)) {
			fsNfeRefRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/nfe/addnfepag")
	public ResponseEntity<?> addFsNfePag(@RequestParam("id") Long id, @RequestParam("tpag") String tpag,
			@RequestParam("vpag") BigDecimal vpag) throws Exception {
		if (ca.hasAuth(prm, 18, "N", "ID ADICIONAR PAGAMENTO " + id + " - " + tpag)) {
			FsNfe fsnfe = fsNfeRp.getById(id);
			FsNfePag p = new FsNfePag();
			p.setFsnfe(fsnfe);
			p.setTpag(tpag);
			p.setVpag(vpag);
			fsNfePagRp.save(p);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/nfe/remnfepag/{id}/idnfe/{idnfe}")
	public ResponseEntity<?> remFsNfePag(@PathVariable("id") Long id, @PathVariable("idnfe") Long idnfe)
			throws Exception {
		if (ca.hasAuth(prm, 18, "R", "ID REMOVER PAGAMENTO " + id)) {
			Optional<FsNfe> fsnfe = fsNfeRp.findById(idnfe);
			if (fsnfe.get().getFsnfepags().size() > 1) {
				fsNfePagRp.deleteById(id);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/nfe/addnfedup")
	public ResponseEntity<?> addFsNfeDup(@RequestParam("id") Long id, @RequestParam("dvenc") String dvenc,
			@RequestParam("vdup") BigDecimal vdup) throws Exception {
		if (ca.hasAuth(prm, 18, "N", "ID ADICIONAR DUPLICATA " + id + " - " + dvenc)) {
			FsNfe fsnfe = fsNfeRp.findById(id).get();
			List<FsNfeCobrDup> dups = fsnfe.getFsnfecobr().getFsnfecobrdups();
			int conta = dups.size() - 1;
			Integer numparc = 1;
			if (dups.size() > 0) {
				numparc = Integer.valueOf(dups.get(conta).getNdup()) + 1;
			}
			String parcela = NumUtil.geraNumEsq(numparc, 3);
			FsNfeCobrDup p = new FsNfeCobrDup();
			p.setFsnfecobr(fsnfe.getFsnfecobr());
			p.setNdup(parcela);
			p.setDvenc(Date.valueOf(dvenc));
			p.setVdup(vdup);
			fsNfeCobrDupRp.save(p);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/nfe/remnfedup/{id}")
	public ResponseEntity<?> remFsNfeDup(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "R", "ID REMOVER DUPLICATA " + id)) {
			fsNfeCobrDupRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/nfe/addnfevol")
	public ResponseEntity<?> addFsNfeVol(@RequestParam("id") Long id, @RequestParam("qvol") BigDecimal qvol,
			@RequestParam("marca") String marca, @RequestParam("esp") String esp, @RequestParam("nvol") String nvol,
			@RequestParam("pesol") BigDecimal pesol, @RequestParam("pesob") BigDecimal pesob) throws Exception {
		if (ca.hasAuth(prm, 18, "N", "ID ADICIONAR VOLUMES " + id + " - " + marca)) {
			FsNfe fsnfe = fsNfeRp.findById(id).get();
			FsNfeFreteVol v = new FsNfeFreteVol();
			v.setFsnfefrete(fsnfe.getFsnfefrete());
			v.setQvol(qvol);
			v.setMarca(marca);
			v.setEsp(esp);
			v.setNvol(nvol);
			v.setPesol(pesol);
			v.setPesob(pesob);
			fsNfeFreteVolRp.save(v);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/nfe/remnfevol/{id}")
	public ResponseEntity<?> remFsNfeVol(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "R", "ID REMOVER VOLUMES " + id)) {
			fsNfeFreteVolRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/nfe/addnfeaut")
	public ResponseEntity<?> addFsNfeCNPJAut(@RequestParam("id") Long id, @RequestParam("cpfcnpj") String cpfcnpj)
			throws Exception {
		if (ca.hasAuth(prm, 18, "N", "ID ADICIONAR CNPJs AUTORIZADOS " + id + " - " + cpfcnpj)) {
			FsNfe fsnfe = fsNfeRp.findById(id).get();
			FsNfeAut aut = fsNfeAutRp.getCpfCnpjNfe(id, cpfcnpj);
			if (aut == null) {
				FsNfeAut v = new FsNfeAut();
				v.setFsnfe(fsnfe);
				v.setCpfcnpj(cpfcnpj);
				fsNfeAutRp.save(v);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/nfe/remnfeaut/{id}")
	public ResponseEntity<?> remFsNfeCNPJAut(@PathVariable("id") Integer id) throws Exception {
		if (ca.hasAuth(prm, 18, "R", "ID REMOVER CNPJs AUTORIZADOS " + id)) {
			fsNfeAutRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
