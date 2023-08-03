package com.midas.api.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.midas.api.mt.entity.CdCodserv;
import com.midas.api.mt.entity.CdNcm;
import com.midas.api.mt.repository.CdCodservRepository;
import com.midas.api.mt.repository.CdNcmRepository;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.CdNfeCfg;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.CdProdutoComp;
import com.midas.api.tenant.entity.CdProdutoCompModel;
import com.midas.api.tenant.entity.CdProdutoCompModelItem;
import com.midas.api.tenant.entity.CdProdutoDTO;
import com.midas.api.tenant.entity.CdProdutoDre;
import com.midas.api.tenant.entity.CdProdutoLocal;
import com.midas.api.tenant.entity.CdProdutoMarkup;
import com.midas.api.tenant.entity.CdProdutoNfecfg;
import com.midas.api.tenant.entity.CdProdutoNs;
import com.midas.api.tenant.entity.CdProdutoNsRel;
import com.midas.api.tenant.entity.CdProdutoPreco;
import com.midas.api.tenant.entity.CdProdutoPrecoDTO;
import com.midas.api.tenant.entity.CdProdutoTab;
import com.midas.api.tenant.entity.CdProdutoTabVend;
import com.midas.api.tenant.entity.EsEst;
import com.midas.api.tenant.repository.CdCustomRepository;
import com.midas.api.tenant.repository.CdNfeCfgRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdProdutoCompModelItemRepository;
import com.midas.api.tenant.repository.CdProdutoCompModelRepository;
import com.midas.api.tenant.repository.CdProdutoCompRepository;
import com.midas.api.tenant.repository.CdProdutoDreRepository;
import com.midas.api.tenant.repository.CdProdutoLocalRepository;
import com.midas.api.tenant.repository.CdProdutoMarkupRepository;
import com.midas.api.tenant.repository.CdProdutoNfecfgRepository;
import com.midas.api.tenant.repository.CdProdutoNsRelRepository;
import com.midas.api.tenant.repository.CdProdutoNsRepository;
import com.midas.api.tenant.repository.CdProdutoPrecoRepository;
import com.midas.api.tenant.repository.CdProdutoRepository;
import com.midas.api.tenant.repository.CdProdutoTabRepository;
import com.midas.api.tenant.repository.EsEstRepository;
import com.midas.api.tenant.service.CdProdutoService;
import com.midas.api.tenant.service.EsEstService;
import com.midas.api.tenant.service.integra.ClimbaService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.ImageUtil;
import com.midas.api.util.MoedaUtil;

@RestController
@RequestMapping("/private/cdproduto")
public class CdProdutoController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdProdutoRepository cdProdutoRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private EsEstService esEstService;
	@Autowired
	private CdNcmRepository cdNcmRp;
	@Autowired
	private CdCodservRepository cdCodservRp;
	@Autowired
	private CdProdutoLocalRepository cdProdutoLocalRp;
	@Autowired
	private CdProdutoService cdProdutoService;
	@Autowired
	private CdProdutoDreRepository cdProdutoDreRp;
	@Autowired
	private CdProdutoNfecfgRepository cdProdutoNfecfgRp;
	@Autowired
	private CdProdutoCompRepository cdProdutoCompRp;
	@Autowired
	private CdProdutoTabRepository cdProdutoTabRp;
	@Autowired
	private CdProdutoMarkupRepository cdProdutoMarkupRp;
	@Autowired
	private CdProdutoPrecoRepository cdProdutoPrecoRp;
	@Autowired
	private CdCustomRepository cdCustomRp;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private EsEstRepository esEstRp;
	@Autowired
	private CdProdutoCompModelRepository cdProdutoCompModelRp;
	@Autowired
	private CdProdutoCompModelItemRepository cdProdutoCompModelItemRp;
	@Autowired
	private CdNfeCfgRepository cdNfeCfgRp;
	@Autowired
	private CdProdutoNsRepository cdProdutoNsRp;
	@Autowired
	private CdProdutoNsRelRepository cdProdutoNsRelRp;
	@Autowired
	private ClimbaService climbaService;

	// PRODUTO CADASTRO****************************************
	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/produto/{id}")
	public ResponseEntity<CdProduto> cdProduto(@PathVariable("id") Long id) {
		Optional<CdProduto> obj = cdProdutoRp.findById(id);
		// Verifica se ja tem tabelas configuradas
		cdProdutoService.configTabPreco(obj.get());
		return new ResponseEntity<CdProduto>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/paradoc/{id}")
	public ResponseEntity<CdProduto> cdProdutoDoc(@PathVariable("id") Long id) {
		Optional<CdProduto> obj = cdProdutoRp.findByIdTipo(id, id.intValue(), String.valueOf(id));
		return new ResponseEntity<CdProduto>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/paradocext/{id}")
	public ResponseEntity<CdProduto> cdProdutoDocExt(@PathVariable("id") Long id) {
		Optional<CdProduto> obj = cdProdutoRp.findByIdTipoExt(id, id.intValue(), String.valueOf(id));
		return new ResponseEntity<CdProduto>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/paradocpdv/{codigo}")
	public ResponseEntity<CdProduto> cdProdutoDocPDV(@PathVariable("codigo") String codigo) {
		Optional<CdProduto> obj = null;
		Long id = Long.valueOf(codigo);
		// Usa apenas codigo alternativo
		boolean codalt = prm.cliente().getSiscfg().isSis_usa_codigo_alterpdv();
		// Verifica uso de balanca
		if (prm.cliente().getSiscfg().isSis_usa_balanca() == true) {
			// Busca normal
			if (codalt == false) {
				obj = cdProdutoRp.findByIdTipo(id, id.intValue(), codigo);
			}
			// Busca apenas com codigo alternativo
			if (codalt == true) {
				obj = cdProdutoRp.findByIdTipoSemID(id.intValue(), codigo);
			}
			if (obj.isEmpty()) {
				// Quebra codigo barras e pega codigo configurado
				Integer iniCod = prm.cliente().getSiscfg().getSis_balanca_codini();
				Integer fimCod = prm.cliente().getSiscfg().getSis_balanca_codfim();
				Integer iniVlr = prm.cliente().getSiscfg().getSis_balanca_valorini();
				Integer fimVlr = prm.cliente().getSiscfg().getSis_balanca_valorfim();
				// Verifica se codigo é maior que valor
				if (fimVlr <= codigo.length()) {
					String codigoCB = codigo.substring(iniCod, fimCod);
					String valorCB = codigo.substring(iniVlr, fimVlr);
					// Busca normal
					if (codalt == false) {
						obj = cdProdutoRp.findByIdTipo(Long.valueOf(codigoCB), Integer.valueOf(codigoCB), codigoCB);
					}
					// Busca apenas com codigo alternativo
					if (codalt == true) {
						obj = cdProdutoRp.findByIdTipoSemID(Integer.valueOf(codigoCB), codigoCB);
					}
					obj.get().setValorunit(MoedaUtil.moedaStringPadraoSQL(valorCB));
				}
			}
		} else {
			obj = cdProdutoRp.findByIdTipo(id, id.intValue(), codigo);
		}
		return new ResponseEntity<CdProduto>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/produto/produto")
	public ResponseEntity<CdProduto> cadastrarCdProduto(@RequestBody CdProduto cdProduto) throws Exception {
		CdProduto pr = null;
		if (ca.hasAuth(prm, 6, "C", cdProduto.getNome())) {
			// Validacao NCM
			CdNcm ncm = cdNcmRp.findNcm(cdProduto.getNcm());
			// PRODUTO
			if (ncm != null || cdProduto.getNcm().equals("00000000")) {
				// Verifica se codigo padrao-----
				if (prm.cliente().getSiscfg().isSis_usa_codigo_padrao() == true) {
					Integer codigo = cdProdutoService.retCodigoItem(cdProduto);
					cdProduto.setCodigo(codigo);
					pr = cdProdutoRp.save(cdProduto);
				} else {
					pr = cdProdutoRp.save(cdProduto);
					pr.setCodigo(pr.getId().intValue());
					pr = cdProdutoRp.save(pr);
				}
				esEstService.estInicia(pr, cdProduto);
				cdProdutoService.verificaDadosIniciais(pr);
				// Verifica se ja tem tabelas configuradas
				cdProdutoService.configTabPreco(pr);
				// Verifica integracao Climba--------------------
				String ret[] = climbaService.cadProduto(pr, "POST");
				if (ret[0].equals("201")) {
					return new ResponseEntity<CdProduto>(pr, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/produto/servico")
	public ResponseEntity<?> cadastrarCdServico(@RequestBody CdProduto cdProduto) throws Exception {
		CdProduto pr = null;
		if (ca.hasAuth(prm, 6, "C", cdProduto.getNome())) {
			// Validacao Codigo Servicos
			String codservico = CaracterUtil.remPout(cdProduto.getCodserv());
			CdCodserv codserv = cdCodservRp.findByCodserv(cdProduto.getCodserv());
			// SERVICO
			if (codserv != null || codservico.equals("0000")) {
				// Verifica se codigo padrao-----
				if (prm.cliente().getSiscfg().isSis_usa_codigo_padrao() == true) {
					Integer codigo = cdProdutoService.retCodigoItem(cdProduto);
					cdProduto.setCodigo(codigo);
					pr = cdProdutoRp.save(cdProduto);
				} else {
					pr = cdProdutoRp.save(cdProduto);
					pr.setCodigo(pr.getId().intValue());
					pr = cdProdutoRp.save(pr);
				}
				esEstService.estInicia(pr, cdProduto);
				cdProdutoService.verificaDadosIniciais(pr);
				// Verifica se ja tem tabelas configuradas
				cdProdutoService.configTabPreco(pr);
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
	@PutMapping("/produto/produto")
	public ResponseEntity<?> atualizarCdProduto(@RequestBody CdProduto cdProduto) throws Exception {
		CdProduto pr = cdProdutoRp.findById(cdProduto.getId()).get();
		if (ca.hasAuth(prm, 6, "A", cdProduto.getNome())) {
			cdProduto.setDataat(new Date(System.currentTimeMillis()));
			// Validacao NCM
			CdNcm ncm = cdNcmRp.findNcm(cdProduto.getNcm());
			// PRODUTO
			if (ncm != null || cdProduto.getNcm().equals("00000000")) {
				// Verifica se codigo padrao-----
				if (prm.cliente().getSiscfg().isSis_usa_codigo_padrao() == true) {
					if (!pr.getTipoitem().equals(cdProduto.getTipoitem())) {
						Integer codigo = cdProdutoService.retCodigoItem(cdProduto);
						cdProduto.setCodigo(codigo);
					}
				}
				pr = cdProdutoRp.save(cdProduto);
				esEstService.estInicia(pr, cdProduto);
				cdProdutoService.verificaDadosIniciais(pr);
				// Verifica se ja tem tabelas configuradas
				cdProdutoService.configTabPreco(pr);
				// Verifica integracao Climba--------------------
				String ret[] = climbaService.cadProduto(pr, "PUT");
				if (ret[0].equals("201")) {
					return new ResponseEntity<CdProduto>(pr, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/servico")
	public ResponseEntity<?> atualizarCdServico(@RequestBody CdProduto cdProduto) throws Exception {
		CdProduto pr = cdProdutoRp.findById(cdProduto.getId()).get();
		if (ca.hasAuth(prm, 6, "A", cdProduto.getNome())) {
			cdProduto.setDataat(new Date(System.currentTimeMillis()));
			// Validacao Codigo Servicos
			String codservico = CaracterUtil.remPout(cdProduto.getCodserv());
			CdCodserv codserv = cdCodservRp.findByCodserv(cdProduto.getCodserv());
			// SERVICO
			if (codserv != null || codservico.equals("0000")) {
				// Verifica se codigo padrao-----
				if (prm.cliente().getSiscfg().isSis_usa_codigo_padrao() == true) {
					if (!pr.getTipoitem().equals(cdProduto.getTipoitem())) {
						Integer codigo = cdProdutoService.retCodigoItem(cdProduto);
						cdProduto.setCodigo(codigo);
					}
				}
				pr = cdProdutoRp.save(cdProduto);
				esEstService.estInicia(pr, cdProduto);
				cdProdutoService.verificaDadosIniciais(pr);
				// Verifica se ja tem tabelas configuradas
				cdProdutoService.configTabPreco(pr);
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/produto/{id}")
	public ResponseEntity<?> removerCdProduto(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 6, "R", "ID " + id)) {
			cdProdutoRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/alterancm/emlote/{novoncm}")
	public ResponseEntity<?> alterarNCMEmLote(@RequestBody List<CdProduto> cdProdutos,
			@PathVariable("novoncm") String novoncm) {
		if (ca.hasAuth(prm, 6, "A", "ATUALIZACAO DE NCM DE PRODUTOS EM LOTE ")) {
			// Validacao NCM
			CdNcm ncm = cdNcmRp.findNcm(novoncm);
			// PRODUTO
			if (ncm != null || novoncm.equals("00000000")) {
				for (CdProduto cd : cdProdutos) {
					if (cd.getTipo().equals("PRODUTO")) {
						cdProdutoRp.updateNCM(novoncm, cd.getId());
					}
				}
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/alteracodserv/emlote/{novocodserv}")
	public ResponseEntity<?> alterarCodServEmLote(@RequestBody List<CdProduto> cdProdutos,
			@PathVariable("novocodserv") String novocodserv) {
		if (ca.hasAuth(prm, 6, "A", "ATUALIZACAO DE CODIGO DE SERVICOS EM LOTE ")) {
			// Validacao Codigo Servicos
			String codservico = CaracterUtil.remPout(novocodserv);
			CdCodserv codserv = cdCodservRp.findByCodserv(novocodserv);
			// SERVICO
			if (codserv != null || codservico.equals("0000")) {
				for (CdProduto cd : cdProdutos) {
					if (!cd.getTipo().equals("PRODUTO")) {
						cdProdutoRp.updateCS(novocodserv, cd.getId());
					}
				}
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/tp/{tipo}")
	public ResponseEntity<List<CdProduto>> listaCdProdutosTipo(@PathVariable("tipo") String tipo) throws Exception {
		List<CdProduto> lista = cdProdutoRp.findAllByTipo(tipo);
		return new ResponseEntity<List<CdProduto>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/produto/busca")
	public ResponseEntity<List<CdProduto>> listaCdProdutoBuscaAtivos(@RequestParam("busca") String busca)
			throws Exception {
		List<CdProduto> lista = cdProdutoRp.findAllByNomeFilterByAtivo(busca);
		return new ResponseEntity<List<CdProduto>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/produto/buscaav")
	public ResponseEntity<List<CdProduto>> listaCdProdutoBuscaAvancadaAtivos(@RequestParam("busca") String busca,
			@RequestParam("tipo") String tipo) throws Exception {
		List<CdProduto> lista = null;
		if (!tipo.equals("X")) {
			lista = cdProdutoRp.findAllByNomeFilterByAtivoTipoLimit(tipo, busca, 400);
		} else {
			lista = cdProdutoRp.findAllByNomeFilterByAtivoLimit(busca, 400);
		}
		return new ResponseEntity<List<CdProduto>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/produto/busca/acabadosimilar")
	public ResponseEntity<List<CdProduto>> listaCdProdutoBuscaStatusTipo(@RequestParam("busca") String busca)
			throws Exception {
		List<CdProduto> lista = cdProdutoRp.findAllByNomeFilterByTipoAcabadoAtivo(busca);
		return new ResponseEntity<List<CdProduto>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/tipo/{tipo}/tpitem/{tpitem}/cat/{cat}/gru/{gru}/grusub/{grusub}/status/"
			+ "{status}/dti/{dataini}/dtf/{datafim}")
	public ResponseEntity<Page<CdProdutoDTO>> listaCdProdutosBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp,
			@PathVariable("tipo") String tipo, @PathVariable("tpitem") String tpitem, @PathVariable("cat") Integer cat,
			@PathVariable("gru") Integer gru, @PathVariable("grusub") Integer grusub,
			@PathVariable("status") String status, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim) throws Exception {
		if (ca.hasAuth(prm, 7, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			List<CdProduto> lista = cdCustomRp.listaCdProdutoDTO(java.sql.Date.valueOf(dataini),
					java.sql.Date.valueOf(datafim), tipo, tpitem, cat, gru, grusub, busca, status, ordem, ordemdir,
					pageable);
			Page<CdProdutoDTO> pages = cdCustomRp.listaCdProdutosDTO(lista, pageable);
			return new ResponseEntity<Page<CdProdutoDTO>>(pages, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// TODO ver problema de fotos dos cadastros, no celular ela fica virada...
	@RequestAuthorization
	@PostMapping("/produto/uploadimagem")
	public ResponseEntity<String> uploadImagem(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) {
		if (ca.hasAuth(prm, 34, "A", file.getName())) {
			try {
				Integer imgSize = prm.cliente().getSiscfg().getSis_tam_imagem_pixel();
				cdProdutoRp.updateImagem(ImageUtil.resizeImage(file.getBytes(), imgSize), id);
				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/produto/removeimagem")
	public ResponseEntity<String> removeImagem(@RequestBody Long id) {
		if (ca.hasAuth(prm, 34, "R", "ID " + id)) {
			cdProdutoRp.updateImagem(null, id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// PRODUTO LOCAL****************************************
	@RequestAuthorization
	@GetMapping("/produto/local/{id}")
	public ResponseEntity<CdProdutoLocal> cdCdProdutoLocal(@PathVariable("id") Long id) {
		Optional<CdProdutoLocal> obj = cdProdutoLocalRp.findById(id);
		return new ResponseEntity<CdProdutoLocal>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/produto/local")
	public ResponseEntity<?> cadastrarCdProdutoLocal(@RequestBody CdProdutoLocal cdProdutoLocal) throws Exception {
		if (ca.hasAuth(prm, 6, "C", "LOCALIZACAO " + cdProdutoLocal.getSessao())) {
			cdProdutoLocalRp.save(cdProdutoLocal);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/produto/local")
	public ResponseEntity<?> atualizarCdProdutoLocal(@RequestBody CdProdutoLocal cdProdutoLocal) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "LOCALIZACAO " + cdProdutoLocal.getSessao())) {
			cdProdutoLocalRp.save(cdProdutoLocal);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/produto/local/{id}")
	public ResponseEntity<?> removerCdProdutoLocal(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 6, "R", "ID LOCALIZACAO " + id)) {
			cdProdutoLocalRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	// PRODUTO PLANO CONTA****************************************
	@RequestAuthorization
	@GetMapping("/produto/dre/{id}")
	public ResponseEntity<CdProdutoDre> cdCdProdutoDre(@PathVariable("id") Long id) {
		Optional<CdProdutoDre> obj = cdProdutoDreRp.findById(id);
		return new ResponseEntity<CdProdutoDre>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/produto/dre")
	public ResponseEntity<?> atualizarCdProdutoDre(@RequestBody CdProdutoDre cdProdutoDre) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "PLANO DE CONTAS - DRE " + cdProdutoDre.getCdproduto().getNome())) {
			cdProdutoDreRp.save(cdProdutoDre);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// PRODUTO NFE CFG****************************************
	@RequestAuthorization
	@GetMapping("/produto/nfecfg/{id}")
	public ResponseEntity<CdProdutoNfecfg> cdProdutoNfecfg(@PathVariable("id") Integer id) {
		Optional<CdProdutoNfecfg> obj = cdProdutoNfecfgRp.findById(id);
		return new ResponseEntity<CdProdutoNfecfg>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/nfecfg/prod/{prod}")
	public ResponseEntity<List<CdProdutoNfecfg>> cdProdutoNfecfgLista(@PathVariable("prod") Long prod) {
		List<CdProdutoNfecfg> lista = cdProdutoNfecfgRp.findAllByCdprodutoID(prod);
		return new ResponseEntity<List<CdProdutoNfecfg>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/produto/nfecfg")
	public ResponseEntity<?> addCdProdutoNfecfg(@RequestBody CdProdutoNfecfg cdProdutoNfecfg) throws Exception {
		if (ca.hasAuth(prm, 6, "C", "CONFIGURACAO FISCAL NFE PRODUTO " + cdProdutoNfecfg.getCdproduto().getNome())) {
			CdProdutoNfecfg cdpn = cdProdutoNfecfgRp.findByCdprodutoAndCdnfecfg(cdProdutoNfecfg.getCdproduto(),
					cdProdutoNfecfg.getCdnfecfg());
			CdProdutoNfecfg cdpn2 = cdProdutoNfecfgRp.getCdNfeCfgDestinoSimilar(
					cdProdutoNfecfg.getCdnfecfg().getCdpessoaemp().getId(), cdProdutoNfecfg.getCdproduto().getId(),
					cdProdutoNfecfg.getCdnfecfg().getCrtdest(),
					cdProdutoNfecfg.getCdnfecfg().getCdestadoaplic().getId());
			if (cdpn == null && cdpn2 == null) {
				cdProdutoNfecfgRp.save(cdProdutoNfecfg);
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
	@PostMapping("/produto/nfecfg/emlote/{configfiscal}")
	public ResponseEntity<?> addCdProdutoNfecfgEmlote(@RequestBody List<CdProduto> itens,
			@PathVariable("configfiscal") Integer configfiscal) throws Exception {
		if (ca.hasAuth(prm, 6, "C", "CONFIGURACAO FISCAL NFE PRODUTO EM LOTE")) {
			CdNfeCfg cdNfecfg = cdNfeCfgRp.findById(configfiscal).get();
			for (CdProduto p : itens) {
				CdProdutoNfecfg cdpn = cdProdutoNfecfgRp.findByCdprodutoAndCdnfecfg(p, cdNfecfg);
				CdProdutoNfecfg cdpn2 = cdProdutoNfecfgRp.getCdNfeCfgDestinoSimilar(cdNfecfg.getCdpessoaemp().getId(),
						p.getId(), cdNfecfg.getCrtdest(), cdNfecfg.getCdestadoaplic().getId());
				if (cdpn == null && cdpn2 == null) {
					CdProdutoNfecfg cdProdutoNfecfg = new CdProdutoNfecfg();
					cdProdutoNfecfg.setCdnfecfg(cdNfecfg);
					cdProdutoNfecfg.setCdproduto(p);
					cdProdutoNfecfg.setUf(cdNfecfg.getCdestadoaplic().getUf());
					cdProdutoNfecfgRp.save(cdProdutoNfecfg);
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/produto/nfecfg/{id}")
	public ResponseEntity<?> removerCdProdutoNfecfg(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 6, "R", "ID CONFIGURACAO FISCAL NFE PRODUTO " + id)) {
			cdProdutoNfecfgRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	// PRODUTO FICHA TECNICA - COMPOSICAO****************************************
	@RequestAuthorization
	@GetMapping("/produto/comp/{id}")
	public ResponseEntity<CdProdutoComp> cdProdutoComp(@PathVariable("id") Long id) {
		Optional<CdProdutoComp> obj = cdProdutoCompRp.findById(id);
		return new ResponseEntity<CdProdutoComp>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/comp/prod/{prod}")
	public ResponseEntity<List<CdProdutoComp>> cdProdutoCompLista(@PathVariable("prod") Long prod) {
		List<CdProdutoComp> lista = cdProdutoCompRp.findByProduto(prod);
		return new ResponseEntity<List<CdProdutoComp>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/produto/comp")
	public ResponseEntity<?> addCdProdutoComp(@RequestBody CdProdutoComp cdProdutoComp) throws Exception {
		if (ca.hasAuth(prm, 6, "C", "COMPOSICAO PRODUTO " + cdProdutoComp.getCdproduto().getNome())) {
			CdProdutoComp c = cdProdutoCompRp.findByPC(cdProdutoComp.getCdprodutocomp().getId(),
					cdProdutoComp.getCdproduto().getId());
			if (c == null) {
				cdProdutoCompRp.save(cdProdutoComp);
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
	@PutMapping("/produto/comp")
	public ResponseEntity<?> atualizarCdProdutoComp(@RequestBody CdProdutoComp cdProdutoComp) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "COMPOSICAO PRODUTO " + cdProdutoComp.getCdproduto().getNome())) {
			cdProdutoCompRp.save(cdProdutoComp);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/produto/comp/{id}")
	public ResponseEntity<?> removerCdProdutoComp(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 6, "R", "ID COMPOSICAO PRODUTO " + id)) {
			cdProdutoCompRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	// PRODUTO TABELA PRECO****************************************
	@RequestAuthorization
	@GetMapping("/produto/tab/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdProdutoTab>> listaCdProdutoTabsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 7, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdProdutoTab> lista = null;
			if (local > 0) {
				lista = cdProdutoTabRp.findAllByNomeBuscaLocal(local, busca, pageable);
			} else {
				if (prm.cliente().getAclocal().equals("N")) {
					lista = cdProdutoTabRp.findAllByNomeBuscaLocal(local, busca, pageable);
				} else {
					lista = cdProdutoTabRp.findAllByNomeBusca(busca, pageable);
				}
			}
			return new ResponseEntity<Page<CdProdutoTab>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/produto/tab/lista")
	public ResponseEntity<List<CdProdutoTab>> listaCdProdutoTabsLista() throws Exception {
		if (ca.hasAuth(prm, 7, "L", "LISTAGEM / CONSULTA")) {
			List<CdProdutoTab> lista = cdProdutoTabRp.findAll();
			return new ResponseEntity<List<CdProdutoTab>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/produto/tab/lista/lc/{local}")
	public ResponseEntity<List<CdProdutoTab>> listaCdProdutoTabsLista(@PathVariable("local") Long local)
			throws Exception {
		if (ca.hasAuth(prm, 7, "L", "LISTAGEM / CONSULTA")) {
			List<CdProdutoTab> lista = cdProdutoTabRp.findAllByLocal(local);
			return new ResponseEntity<List<CdProdutoTab>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/produto/tab/paradoc/lc/{local}/uf/{uf}")
	public ResponseEntity<List<CdProdutoTab>> listaCdProdutoTabsDoc(@PathVariable("local") Long local,
			@PathVariable("uf") String uf) throws Exception {
		if (ca.hasAuth(prm, 20, "L", "LISTAGEM / CONSULTA")) {
			// Verifica controle representante
			Long respvend = 0L;
			if (prm.cliente().getRole().getId() == 8) {
				if (prm.cliente().getCdpessoavendedor() != null) {
					respvend = prm.cliente().getCdpessoavendedor();
				}
			}
			List<CdProdutoTab> lista = new ArrayList<CdProdutoTab>();
			if (!uf.equals("X")) {
				if (respvend > 0) {
					CdPessoa vend = cdPessoaRp.findById(respvend).get();
					for (CdProdutoTabVend pv : vend.getCdprodutotabvenditems()) {
						CdProdutoTab t = cdProdutoTabRp.findById(pv.getCdprodutotab()).get();
						if (t.getUfaplic().equals(uf) || t.getUfaplic().equals("XX")) {
							lista.add(t);
						}
					}
				} else {
					lista = cdProdutoTabRp.findAllByLocalAndUf(local, uf);
				}
			} else {
				if (respvend > 0) {
					CdPessoa vend = cdPessoaRp.findById(respvend).get();
					for (CdProdutoTabVend pv : vend.getCdprodutotabvenditems()) {
						CdProdutoTab t = cdProdutoTabRp.findById(pv.getCdprodutotab()).get();
						lista.add(t);
					}
				} else {
					lista = cdProdutoTabRp.findAllByLocal(local);
				}
			}
			return new ResponseEntity<List<CdProdutoTab>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/produto/tab/{id}")
	public ResponseEntity<CdProdutoTab> cdCdProdutoTab(@PathVariable("id") Integer id) {
		Optional<CdProdutoTab> obj = cdProdutoTabRp.findById(id);
		return new ResponseEntity<CdProdutoTab>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/produto/tab")
	public ResponseEntity<?> cadastrarCdProdutoTab(@RequestBody CdProdutoTab cdProdutoTab) throws Exception {
		if (ca.hasAuth(prm, 6, "C", "TABELA DE PRECO " + cdProdutoTab.getNome())) {
			CdProdutoTab tab = cdProdutoTabRp.save(cdProdutoTab);
			CdProdutoMarkup mk = cdProdutoMarkupRp.findById(cdProdutoTab.getCdprodutomarkup().getId()).get();
			cdProdutoService.novaTabPreco(tab, mk);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/tab")
	public ResponseEntity<?> atualizarCdProdutoTab(@RequestBody CdProdutoTab cdProdutoTab) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "TABELA DE PRECO " + cdProdutoTab.getNome())) {
			CdProdutoTab tab = cdProdutoTabRp.save(cdProdutoTab);
			cdProdutoService.configTabPrecoTab(tab);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/produto/tab/{id}")
	public ResponseEntity<?> removerCdProdutoTab(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 6, "R", "ID TABELA DE PRECO " + id)) {
			// Nao remover item principal
			if (id > 1) {
				cdProdutoTabRp.deleteById(id);
				return ResponseEntity.ok(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.LOCKED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// PRODUTO MARKUP****************************************
	@RequestAuthorization
	@GetMapping("/produto/markup/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdProdutoMarkup>> listaCdProdutoMarkupsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 7, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdProdutoMarkup> lista = cdProdutoMarkupRp.findAllByNomeBusca(busca, pageable);
			return new ResponseEntity<Page<CdProdutoMarkup>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/produto/markup/lista")
	public ResponseEntity<List<CdProdutoMarkup>> listaCdProdutoMarkupsLista() throws Exception {
		if (ca.hasAuth(prm, 7, "L", "LISTAGEM / CONSULTA")) {
			List<CdProdutoMarkup> lista = cdProdutoMarkupRp.findAll();
			return new ResponseEntity<List<CdProdutoMarkup>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/produto/markup/{id}")
	public ResponseEntity<CdProdutoMarkup> cdCdProdutoMarkup(@PathVariable("id") Integer id) {
		Optional<CdProdutoMarkup> obj = cdProdutoMarkupRp.findById(id);
		return new ResponseEntity<CdProdutoMarkup>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/produto/markup")
	public ResponseEntity<?> cadastrarCdProdutoMarkup(@RequestBody CdProdutoMarkup cdProdutoMarkup) throws Exception {
		if (ca.hasAuth(prm, 6, "C", "MARKUP " + cdProdutoMarkup.getDescricao())) {
			cdProdutoMarkupRp.save(cdProdutoMarkup);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/produto/markup")
	public ResponseEntity<?> atualizarCdProdutoMarkup(@RequestBody CdProdutoMarkup cdProdutoMarkup) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "MARKUP " + cdProdutoMarkup.getDescricao())) {
			cdProdutoMarkupRp.save(cdProdutoMarkup);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/markup/redefinir/{id}")
	public ResponseEntity<?> redefinirCdProdutoMarkup(@PathVariable("id") Integer id) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "ID REDEFINIR PRECOS VIA MARKUP " + id)) {
			cdProdutoService.configTabPreco(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/produto/markup/{id}")
	public ResponseEntity<?> removerCdProdutoMarkup(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 6, "R", "ID MARKUP " + id)) {
			// Nao remover item principal
			if (id > 1) {
				cdProdutoMarkupRp.deleteById(id);
				return ResponseEntity.ok(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.LOCKED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// PRODUTO PRECO****************************************
	@RequestAuthorization
	@GetMapping("/produto/preco/{id}")
	public ResponseEntity<CdProdutoPreco> cdProdutoPreco(@PathVariable("id") Long id) {
		// Verifica controle representante
		if (prm.cliente().getRole().getId() == 8) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} else {
			Optional<CdProdutoPreco> obj = cdProdutoPrecoRp.findById(id);
			return new ResponseEntity<CdProdutoPreco>(obj.get(), HttpStatus.OK);
		}
	}

	@RequestAuthorization
	@GetMapping("/produto/preco/{id}/prod/{prod}")
	public ResponseEntity<CdProdutoPreco> cdCdProdutoPrecoTabProd(@PathVariable("id") Integer id,
			@PathVariable("prod") Long prod) {
		Optional<CdProdutoPreco> obj = cdProdutoPrecoRp.findByTabProd(id, prod);
		return new ResponseEntity<CdProdutoPreco>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/preco/prod/{prod}")
	public ResponseEntity<List<CdProdutoPreco>> cdCdProdutoPrecoTabProdLista(@PathVariable("prod") Long prod) {
		List<CdProdutoPreco> lista = cdProdutoPrecoRp.findAllByCdprodutoID(prod);
		return new ResponseEntity<List<CdProdutoPreco>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/preco/tabela/{id}")
	public ResponseEntity<List<CdProdutoPreco>> cdCdProdutoPrecoLista(@PathVariable("id") Integer id) {
		List<CdProdutoPreco> lista = cdProdutoPrecoRp.findByTab(id);
		return new ResponseEntity<List<CdProdutoPreco>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/precodto/tabela/{id}")
	public ResponseEntity<List<CdProdutoPrecoDTO>> cdCdProdutoPrecoListaDTO(@PathVariable("id") Integer id) {
		List<CdProdutoPreco> lista = cdProdutoPrecoRp.findByTab(id);
		List<CdProdutoPrecoDTO> dtos = cdCustomRp.listaCdProdutoPrecoDTO(lista);
		return new ResponseEntity<List<CdProdutoPrecoDTO>>(dtos, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/preco/t/{t}/c/{c}/g/{g}/gs/{gs}/ti/{ti}")
	public ResponseEntity<List<CdProdutoPreco>> cdCdProdutoPrecoListaDiversos(@PathVariable("t") Integer t,
			@PathVariable("c") Integer c, @PathVariable("g") Integer g, @PathVariable("gs") Integer gs,
			@PathVariable("ti") String ti) {
		List<CdProdutoPreco> lista = cdCustomRp.listaCdProdutoPrecoDiversos(java.sql.Date.valueOf("1980-01-01"),
				java.sql.Date.valueOf("2060-01-01"), t, ti, c, g, gs, "ATIVO", "cdproduto.nome", "ASC");
		return new ResponseEntity<List<CdProdutoPreco>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/preco/lc/{local}/tabela/{id}/tipoitem/{tipoitem}/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/st/{status}")
	public ResponseEntity<Page<CdProdutoPreco>> cdCdProdutoPrecoListaLocal(@PathVariable("local") Long local,
			@PathVariable("id") Integer id, @PathVariable("tipoitem") String tipoitem,
			@PathVariable("pagina") int pagina, @PathVariable("busca") String busca,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("itenspp") int itenspp, @PathVariable("status") String status) {
		if (ca.hasAuth(prm, 79, "L", "PAINEL DE PRECOS")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdProdutoPreco> lista = cdCustomRp.listaCdProdutoPreco(local, id, tipoitem, busca, status, ordem,
					ordemdir, pageable);
			return new ResponseEntity<Page<CdProdutoPreco>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/preco")
	public ResponseEntity<?> atualizarCdProdutoPreco(@RequestBody CdProdutoPreco cdProdutoPreco) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "PRECOS " + cdProdutoPreco.getCdproduto().getNome())) {
			cdProdutoPreco.setDataat(new Date());
			cdProdutoPrecoRp.save(cdProdutoPreco);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/preco/diversos")
	public ResponseEntity<?> atualizarCdProdutoPrecoDiversos(@RequestBody List<CdProdutoPreco> precos)
			throws Exception {
		if (ca.hasAuth(prm, 6, "A", "PRECOS DIVERSOS")) {
			for (CdProdutoPreco ps : precos) {
				cdProdutoPrecoRp.save(ps);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/preco/reiniciar/{id}")
	public ResponseEntity<?> reiniciarCdProdutoPreco(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "ID REINICIAR PRECOS " + id)) {
			Optional<CdProduto> obj = cdProdutoRp.findById(id);
			cdProdutoPrecoRp.deleteByProd(id);
			// Verifica se ja tem tabelas configuradas
			cdProdutoService.configTabPreco(obj.get());
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/preco/custos/atualizar/{id}")
	public ResponseEntity<?> atualizarCdProdutoPrecoCustos(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "ID ATUALIZA PRECOS DE CUSTOS VIA COMPOSICAO " + id)) {
			Optional<CdProduto> obj = cdProdutoRp.findById(id);
			List<CdProdutoComp> comps = cdProdutoCompRp.findByProduto(id);
			// Todas as empresas, se houver
			List<CdPessoa> locais = cdPessoaRp.findAllByTipoTodos("EMPRESA");
			for (CdPessoa l : locais) {
				BigDecimal vCusto = new BigDecimal(0);
				for (CdProdutoComp c : comps) {
					if (c.getCdpessoaemp().getId().equals(l.getId())) {
						vCusto = vCusto.add(c.getVcusto().multiply(c.getQtd()));
					}
				}
				esEstRp.updateVCustoProdLocal(vCusto.setScale(8, RoundingMode.HALF_UP), obj.get().getId(), l.getId());
				cdProdutoService.atualizaTabPreco(l.getId(), obj.get(), vCusto.setScale(8, RoundingMode.HALF_UP));
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/preco/custos/atualizartodos/local/{local}")
	public ResponseEntity<?> atualizarCdProdutoPrecoCustosEmMassa(@PathVariable("local") Long local) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "ID ATUALIZA PRECOS DE CUSTOS VIA COMPOSICAO EM MASSA POR LOCAL " + local)) {
			// Informa todos os produtos
			List<CdProduto> prs = cdProdutoRp.findAllByStatus("ATIVO");
			for (CdProduto p : prs) {
				List<CdProdutoComp> comps = cdProdutoCompRp.findByProduto(p.getId());
				BigDecimal vCusto = new BigDecimal(0);
				for (CdProdutoComp c : comps) {
					if (c.getCdpessoaemp().getId().equals(local)) {
						vCusto = vCusto.add(c.getVcusto().multiply(c.getQtd()));
					}
					esEstRp.updateVCustoProdLocal(vCusto.setScale(8, RoundingMode.HALF_UP), p.getId(), local);
					cdProdutoService.atualizaTabPreco(local, p, vCusto.setScale(8, RoundingMode.HALF_UP));
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/preco/alterarparametros")
	public ResponseEntity<?> alterarParCdProdutoPreco(@RequestParam("id") Long id,
			@RequestParam("pdescmax") BigDecimal pdescmax, @RequestParam("pcom") BigDecimal pcom) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "ID ALTERAR PARAMETROS DE PRECOS " + id)) {
			Optional<CdProduto> obj = cdProdutoRp.findById(id);
			cdProdutoService.alterarParTabPreco(obj.get(), pdescmax, pcom);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/preco/alterarcusto")
	public ResponseEntity<?> alterarParCdProdutoPrecoCusto(@RequestParam("id") Long id,
			@RequestParam("vcusto") BigDecimal vcusto) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "ID ALTERAR PRECO DE CUSTO " + id)) {
			Optional<CdProdutoPreco> obj = cdProdutoPrecoRp.findById(id);
			cdProdutoService.alterarParTabPrecoCusto(obj.get(), vcusto);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// PRODUTO MODELAGEM DE ITENS PADROES********************************
	@RequestAuthorization
	@GetMapping("/produto/compmodel/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdProdutoCompModel>> listaCdProdutoCompModelBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 7, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdProdutoCompModel> lista = null;
			if (local > 0) {
				lista = cdProdutoCompModelRp.findAllByNomeBuscaLocal(local, busca, pageable);
			} else {
				if (prm.cliente().getAclocal().equals("N")) {
					lista = cdProdutoCompModelRp.findAllByNomeBuscaLocal(local, busca, pageable);
				} else {
					lista = cdProdutoCompModelRp.findAllByNomeBusca(busca, pageable);
				}
			}
			return new ResponseEntity<Page<CdProdutoCompModel>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/produto/compmodel/{id}")
	public ResponseEntity<CdProdutoCompModel> cdCdProdutoCompModel(@PathVariable("id") Long id) {
		Optional<CdProdutoCompModel> obj = cdProdutoCompModelRp.findById(id);
		return new ResponseEntity<CdProdutoCompModel>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/compmodel/lista/idlocal/{idlocal}")
	public ResponseEntity<List<CdProdutoCompModel>> cdCdProdutoCompModelLocal(@PathVariable("idlocal") Long idlocal) {
		List<CdProdutoCompModel> lista = cdProdutoCompModelRp.findByLocal(idlocal);
		return new ResponseEntity<List<CdProdutoCompModel>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/produto/compmodel")
	public ResponseEntity<?> cadastrarCdProdutoCompModel(@RequestBody CdProdutoCompModel cdProdutoCompModel)
			throws Exception {
		if (ca.hasAuth(prm, 6, "C",
				"MODELO DE COMPOSICAO DE ESTRUTURA DE PRODUTO " + cdProdutoCompModel.getDescricao())) {
			cdProdutoCompModelRp.save(cdProdutoCompModel);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/compmodel")
	public ResponseEntity<?> atualizarCdProdutoCompModelp(@RequestBody CdProdutoCompModel cdProdutoCompModel)
			throws Exception {
		if (ca.hasAuth(prm, 6, "A",
				"MODELO DE COMPOSICAO DE ESTRUTURA DE PRODUTO " + cdProdutoCompModel.getDescricao())) {
			cdProdutoCompModelRp.save(cdProdutoCompModel);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/produto/compmodel/{id}")
	public ResponseEntity<?> removerCdProdutoCompModel(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 6, "R", "ID MODELO DE COMPOSICAO DE ESTRUTURA DE PRODUTO " + id)) {
			cdProdutoCompModelRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/produto/compmodel/item")
	public ResponseEntity<?> cadastrarCdProdutoCompModelItem(@RequestBody CdProdutoCompModelItem cdProdutoCompModelItem)
			throws Exception {
		if (ca.hasAuth(prm, 6, "C", "ADICIONA ITEM AO MODELO DE COMPOSICAO DE ESTRUTURA DE PRODUTO "
				+ cdProdutoCompModelItem.getCdproduto().getNome())) {
			cdProdutoCompModelItemRp.save(cdProdutoCompModelItem);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/produto/compmodel/importa/idlocal/{idlocal}/idmodel/{idmodel}/idprod/{idprod}")
	public ResponseEntity<?> importarCdProdutoCompModel(@PathVariable("idlocal") Long idlocal,
			@PathVariable("idmodel") Long idmodel, @PathVariable("idprod") Long idprod) {
		if (ca.hasAuth(prm, 6, "I", "ID IMPORTAR MODELO DE COMPOSICAO DE ESTRUTURA DE PRODUTO " + idprod)) {
			CdPessoa p = cdPessoaRp.findById(idlocal).get();
			CdProduto pr = cdProdutoRp.findById(idprod).get();
			CdProdutoCompModel model = cdProdutoCompModelRp.findById(idmodel).get();
			for (CdProdutoCompModelItem pi : model.getCdprodutocompmodelitem()) {
				if (!pi.getCdproduto().getId().equals(pr.getId())) {
					EsEst e = esEstRp.findByEmpProduto(idlocal, pi.getCdproduto().getId());
					CdProdutoComp pc = new CdProdutoComp();
					pc.setCdpessoaemp(p);
					pc.setCdproduto(pr);
					pc.setCdprodutocomp(pi.getCdproduto());
					pc.setQtd(pi.getQtd());
					pc.setVcusto(e.getVcusto());
					cdProdutoCompRp.save(pc);
				}
			}
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/produto/compmodel/item/{id}")
	public ResponseEntity<?> removerCdProdutoCompModelItem(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 6, "R", "ID ITEM MODELO DE COMPOSICAO DE ESTRUTURA DE PRODUTO " + id)) {
			cdProdutoCompModelItemRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// PRODUTO NUMERO DE SERIE*********************
	@RequestAuthorization
	@GetMapping("/produto/pns/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdProdutoNs>> listaCdProdutoNsBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 7, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdProdutoNs> lista = null;
			if (local > 0) {
				lista = cdProdutoNsRp.findAllByLocal(local, pageable);
			} else {
				lista = cdProdutoNsRp.findAll(pageable);
			}
			return new ResponseEntity<Page<CdProdutoNs>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/produto/pns/{id}")
	public ResponseEntity<CdProdutoNs> cdProdutoNs(@PathVariable("id") Integer id) {
		Optional<CdProdutoNs> obj = cdProdutoNsRp.findById(id);
		return new ResponseEntity<CdProdutoNs>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/pns/local/{id}")
	public ResponseEntity<List<CdProdutoNs>> cdProdutoNsLocal(@PathVariable("id") Long id) {
		List<CdProdutoNs> lista = cdProdutoNsRp.findAllByLocal(id, "ATIVO");
		return new ResponseEntity<List<CdProdutoNs>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/produto/pns/todos")
	public ResponseEntity<List<CdProdutoNs>> cdProdutoNsTodos() {
		List<CdProdutoNs> lista = cdProdutoNsRp.findAll();
		return new ResponseEntity<List<CdProdutoNs>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/produto/pns")
	public ResponseEntity<?> cadastrarCdProdutoNs(@RequestBody CdProdutoNs cdProdutoNs) throws Exception {
		if (ca.hasAuth(prm, 6, "C", "NUMERO DE SERIE DE PRODUTO " + cdProdutoNs.getContador())) {
			cdProdutoNsRp.save(cdProdutoNs);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/produto/pns")
	public ResponseEntity<?> atualizarCdProdutoNsp(@RequestBody CdProdutoNs cdProdutoNs) throws Exception {
		if (ca.hasAuth(prm, 6, "A", "NUMERO DE SERIE DE PRODUTO " + cdProdutoNs.getContador())) {
			cdProdutoNsRp.save(cdProdutoNs);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/produto/pns/{id}")
	public ResponseEntity<?> removerCdProdutoNs(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 6, "R", "ID NUMERO DE SERIE DE PRODUTO " + id)) {
			cdProdutoNsRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/produto/apns")
	public ResponseEntity<?> adicionarCdProdutoNsRel(@RequestBody CdProdutoNsRel cdProdutoNsRel) throws Exception {
		if (ca.hasAuth(prm, 6, "C", "INCLUSAO NUMERO DE SERIE DE PRODUTO " + cdProdutoNsRel.getCdproduto().getNome())) {
			cdProdutoNsRelRp.save(cdProdutoNsRel);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/produto/apns/{id}")
	public ResponseEntity<?> removerCdProdutoNsRel(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 6, "R", "ID REMOCAO NUMERO DE SERIE DE PRODUTO " + id)) {
			cdProdutoNsRelRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
