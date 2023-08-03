package com.midas.api.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.constant.FsCteWebService;
import com.midas.api.constant.FsMdfeWebService;
import com.midas.api.constant.MidasConfig;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdPlconConta;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.CdProdutoPreco;
import com.midas.api.tenant.entity.EsEst;
import com.midas.api.tenant.entity.FnCartao;
import com.midas.api.tenant.entity.FnCheque;
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnRecAv;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FnTituloCcusto;
import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.entity.FsMdfe;
import com.midas.api.tenant.entity.FsNfe;
import com.midas.api.tenant.entity.FsNfse;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcDocItem;
import com.midas.api.tenant.fiscal.util.FnPagNomeUtil;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.tenant.repository.CdCustomRepository;
import com.midas.api.tenant.repository.CdPlconContaRepository;
import com.midas.api.tenant.repository.CdProdutoPrecoRepository;
import com.midas.api.tenant.repository.EsCustomRepository;
import com.midas.api.tenant.repository.FnCartaoCustomRepository;
import com.midas.api.tenant.repository.FnChequeCustomRepository;
import com.midas.api.tenant.repository.FnCxmvCustomRepository;
import com.midas.api.tenant.repository.FnRecAvCustomRepository;
import com.midas.api.tenant.repository.FnRecAvRepository;
import com.midas.api.tenant.repository.FnTituloCcustoRepository;
import com.midas.api.tenant.repository.FnTituloCustomRepository;
import com.midas.api.tenant.repository.FsCteRepository;
import com.midas.api.tenant.repository.FsCustomRepository;
import com.midas.api.tenant.repository.FsMdfeRepository;
import com.midas.api.tenant.repository.FsNfeItemRepository;
import com.midas.api.tenant.repository.FsNfeRepository;
import com.midas.api.tenant.repository.FsNfseRepository;
import com.midas.api.tenant.repository.LcDocCustomRepository;
import com.midas.api.tenant.repository.LcDocRepository;
import com.midas.api.tenant.service.ReportService;
import com.midas.api.tenant.service.excel.CdDreExcelService;
import com.midas.api.tenant.service.excel.CdPessoaExcelService;
import com.midas.api.tenant.service.excel.CdProdutoExcelService;
import com.midas.api.tenant.service.excel.CdProdutoPrecoExcelService;
import com.midas.api.tenant.service.excel.EsEstExcelService;
import com.midas.api.tenant.service.excel.FnCartaoExcelService;
import com.midas.api.tenant.service.excel.FnChequeExcelService;
import com.midas.api.tenant.service.excel.FnCxmvExcelService;
import com.midas.api.tenant.service.excel.FnRecAvExcelService;
import com.midas.api.tenant.service.excel.FnTituloCcustoExcelService;
import com.midas.api.tenant.service.excel.FnTituloExcelService;
import com.midas.api.tenant.service.excel.FsCteExcelService;
import com.midas.api.tenant.service.excel.FsMdfeExcelService;
import com.midas.api.tenant.service.excel.FsNfeExcelService;
import com.midas.api.tenant.service.excel.LcDocCotacaoExcelService;
import com.midas.api.tenant.service.excel.LcDocExcelService;
import com.midas.api.tenant.service.excel.LcDocItemExcelService;
import com.midas.api.tenant.service.excel.LcDocOutrosExcelService;
import com.midas.api.util.DataUtil;
import com.midas.api.util.NumExtensoUtil;

@RestController
@RequestMapping("/private/report")
public class ReportController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private ReportService reportService;
	@Autowired
	private NumExtensoUtil numEx;
	@Autowired
	private FnRecAvRepository fnRecAvRp;
	@Autowired
	private FnTituloCustomRepository fnTituloCustomRp;
	@Autowired
	private FnRecAvCustomRepository fnRecAvCustomRp;
	@Autowired
	private FnCxmvCustomRepository fnCxmvCustomRp;
	@Autowired
	private CdCustomRepository cdCustomRp;
	@Autowired
	private FnCartaoCustomRepository fnCartaoCustomRp;
	@Autowired
	private FnChequeCustomRepository fnChequeCustomRp;
	@Autowired
	private FnTituloCcustoRepository fnTituloCcustoRp;
	@Autowired
	private LcDocCustomRepository lcDocCustomRp;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private FsNfseRepository fsNfseRp;
	@Autowired
	private FsNfeItemRepository fsNfeItemRp;
	@Autowired
	private FsCustomRepository fsCustomRp;
	@Autowired
	private EsCustomRepository esCustomRp;
	@Autowired
	private FsMdfeRepository fsMdfeRp;
	@Autowired
	private FsCteRepository fsCteRp;
	@Autowired
	private LcDocRepository lcDocRp;
	@Autowired
	private CdProdutoPrecoRepository cdProdutoPrecoRp;
	@Autowired
	private CdPlconContaRepository cdPlconContaRp;

	// PESSOA
	// *************************************************************************************
	// 0003
	@RequestAuthorization
	@PostMapping("/pessoa/relatorio/pdf")
	public ResponseEntity<byte[]> cdPessoaPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("local") Long local, @RequestParam("pessoatipo") String pessoatipo,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("addend") String addend, @RequestParam("cidade") Integer cidade,
			@RequestParam("estado") Integer estado, @RequestParam("busca") String busca,
			@RequestParam("ordem") String ordem, @RequestParam("ordemdir") String ordemdir,
			@RequestParam("respvend") Long respvend, @RequestParam("status") String status,
			@RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 25, "I", "IMPRESSAO PDF")) {
			// Verifica acesso Representante
			if (prm.cliente().getRole().getId() == 8) {
				if (prm.cliente().getCdpessoavendedor() != null) {
					respvend = prm.cliente().getCdpessoavendedor();
				}
			}
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.cdPessoaPDF(local, pessoatipo, dataini, datafim, cidade, estado, busca, ordem, ordemdir,
					respvend, status, tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/pessoa/relatorio/excel")
	public ResponseEntity<byte[]> cdPessoaEXCEL(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("local") Long local, @RequestParam("pessoatipo") String pessoatipo,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("addend") String addend, @RequestParam("cidade") Integer cidade,
			@RequestParam("estado") Integer estado, @RequestParam("busca") String busca,
			@RequestParam("ordem") String ordem, @RequestParam("ordemdir") String ordemdir,
			@RequestParam("respvend") Long respvend, @RequestParam("status") String status) throws Exception {
		if (ca.hasAuth(prm, 25, "I", "EXPORTACAO EXCEL")) {
			// Verifica acesso Representante
			if (prm.cliente().getRole().getId() == 8) {
				if (prm.cliente().getCdpessoavendedor() != null) {
					respvend = prm.cliente().getCdpessoavendedor();
				}
			}
			List<CdPessoa> lista = cdCustomRp.listaCdPessoaExcel(local, pessoatipo, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, estado, cidade, respvend, status, ordem, ordemdir);
			ByteArrayInputStream in = CdPessoaExcelService.exportToExcel(lista, pessoatipo);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// RECIBO
	// *************************************************************************************
	// 0002
	@RequestAuthorization
	@GetMapping("/fnrecav/{id}/tipoimpressao/{tipoimpressao}")
	public ResponseEntity<byte[]> fnRecAv(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id, @PathVariable("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 11, "I", "IMPRESSAO RECIBO")) {
			Map<String, Object> par = new HashMap<String, Object>();
			Optional<FnRecAv> obj = fnRecAvRp.findById(id);
			String sql = " WHERE f.id = " + id;
			String vtotextenso = numEx.write(obj.get().getVtot());
			String formapag = FnPagNomeUtil.nomePg(obj.get().getFpag());
			par.put("sql", sql);
			par.put("vtotextenso", vtotextenso);
			par.put("formapag", formapag);
			// Assinatura
			String assinatura = reportService.assinaturaDigital();
			par.put("assinatura", assinatura);
			response.setContentType("application/pdf");
			String controle = "";
			if (tipoimpressao.equals("0")) {
				// Papel comum
				controle = "0002";
			}
			byte[] contents = reportService.geraRel(controle, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// 0001
	@RequestAuthorization
	@PostMapping("/recav/relatorio/pdf")
	public ResponseEntity<byte[]> fnRecAvPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("local") Long local, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("tipo") String tipo,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir) throws Exception {
		if (ca.hasAuth(prm, 26, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.fnRecAvPDF(local, dataini, datafim, tipo, busca, ordem, ordemdir);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel("0001", par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/recav/relatorio/excel/local")
	public ResponseEntity<byte[]> fnRecAvEXCEL(@RequestParam("local") Long local,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("tipo") String tipo, @RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir) throws Exception {
		if (ca.hasAuth(prm, 26, "I", "EXPORTACAO EXCEL")) {
			List<FnRecAv> lista = fnRecAvCustomRp.listaFnRecAvExcel(local, tipo, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ordem, ordemdir);
			ByteArrayInputStream in = FnRecAvExcelService.exportToExcel(lista);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// TITULO
	// *************************************************************************************
	// 0005, 0006, 0007, 0022, 0057, 0077, 0082
	@RequestAuthorization
	@PostMapping("/titulo/relatorio/pdf")
	public ResponseEntity<byte[]> fnTituloPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("local") Long local, @RequestParam("caixapref") Integer caixapref,
			@RequestParam("fpagpref") String fpagpref, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("tipo") String tipo,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("ove") String ove, @RequestParam("para") Long para,
			@RequestParam("resp") Long resp, @RequestParam("com") String com, @RequestParam("compago") String compago,
			@RequestParam("status") String status, @RequestParam("tipoimpressao") String tipoimpressao)
			throws Exception {
		if (ca.hasAuth(prm, 26, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.fnTituloPDF(local, caixapref, fpagpref, tipo, dataini, datafim, busca, ove, ordem,
					ordemdir, para, resp, com, compago, status, tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/titulo/recibo/{id}/tipopapel/{tipopapel}")
	public ResponseEntity<byte[]> fnTituloRecibo(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id, @PathVariable("tipopapel") String tipopapel) throws Exception {
		if (ca.hasAuth(prm, 11, "I", "IMPRESSAO RECIBO")) {
			Map<String, Object> par = new HashMap<String, Object>();
			String sql = " WHERE f.id = " + id + " GROUP BY f.id";
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			// Assinatura
			String assinatura = reportService.assinaturaDigital();
			par.put("assinatura", assinatura);
			response.setContentType("application/pdf");
			String controle = "";
			if (tipopapel.equals("0")) {
				controle = "0008"; // Papel comum
			} else if (tipopapel.equals("1")) {
				controle = "0008_1"; // Papel comum
			}
			byte[] contents = reportService.geraRel(controle, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/titulo/relatorio/excel/local")
	public ResponseEntity<byte[]> fnTituloEXCEL(@RequestParam("local") Long local,
			@RequestParam("caixapref") Integer caixapref, @RequestParam("fpagpref") String fpagpref,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("tipo") String tipo, @RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("ove") String ove, @RequestParam("para") Long para,
			@RequestParam("resp") Long resp, @RequestParam("cred") String cred, @RequestParam("com") String com,
			@RequestParam("compago") String compago, @RequestParam("status") String status) throws Exception {
		if (ca.hasAuth(prm, 44, "I", "EXPORTACAO EXCEL")) {
			try {
				String paracob = "S";
				List<FnTitulo> lista = fnTituloCustomRp.listaFnTituloExcel(local, caixapref, fpagpref, tipo,
						Date.valueOf(dataini), Date.valueOf(datafim), busca, ove, ordem, ordemdir, para, resp, cred,
						paracob, com, compago, status);
				ByteArrayInputStream in = FnTituloExcelService.exportToExcel(lista, tipo);
				byte[] contents = IOUtils.toByteArray(in);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
				headers.set("Content-Disposition", "attachment");
				return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// MOVIMENTO DE CAIXA
	// *************************************************************************
	@RequestAuthorization
	@PostMapping("/cxmv/relatorio/excel/local")
	public ResponseEntity<byte[]> fnCxmvEXCEL(@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("caixa") Integer caixa, @RequestParam("tipo") String tipo, @RequestParam("fpag") String fpag,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("para") Long para, @RequestParam("lanca") String lanca, @RequestParam("status") String status)
			throws Exception {
		if (ca.hasAuth(prm, 44, "I", "EXPORTACAO EXCEL")) {
			List<FnCxmv> lista = fnCxmvCustomRp.listaFnCxmvExcel(local, caixa, tipo, fpag, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ordem, ordemdir, para, lanca, status);
			BigDecimal saldoAnterior = fnCxmvCustomRp.retFnCxmvValoresSaldoAnterior(local, caixa, tipo, fpag,
					Date.valueOf(dataini), Date.valueOf(datafim), busca, para, lanca, status);
			ByteArrayInputStream in = FnCxmvExcelService.exportToExcel(lista, saldoAnterior);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// 0009, 0010, 0011, 0012, 0023
	@RequestAuthorization
	@PostMapping("/cxmv/relatorio/pdf/local")
	public ResponseEntity<byte[]> fnCxmvPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("caixa") Integer caixa, @RequestParam("tipo") String tipo, @RequestParam("fpag") String fpag,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("para") Long para, @RequestParam("lanca") String lanca, @RequestParam("status") String status,
			@RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 44, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.fnCxmvPDF(local, caixa, tipo, fpag, dataini, datafim, busca, ordem, ordemdir, para,
					lanca, status, tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// CARTAO
	// *************************************************************************************
	// 0013, 0014, 0015, 0016
	@RequestAuthorization
	@PostMapping("/cartao/relatorio/pdf/local")
	public ResponseEntity<byte[]> fnCartaoPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("caixa") Integer caixa, @RequestParam("cartao") Integer cartao,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("ope") String ope, @RequestParam("tipo") String tipo, @RequestParam("status") String status,
			@RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 44, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.fnCartaoPDF(local, caixa, cartao, dataini, datafim, busca, ope, ordem, ordemdir, tipo,
					status, tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/cartao/relatorio/excel/local")
	public ResponseEntity<byte[]> fnCartaoEXCEL(@RequestParam("busca") String busca,
			@RequestParam("ordem") String ordem, @RequestParam("ordemdir") String ordemdir,
			@RequestParam("local") Long local, @RequestParam("caixa") Integer caixa,
			@RequestParam("cartao") Integer cartao, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("ope") String ope, @RequestParam("tipo") String tipo,
			@RequestParam("status") String status) throws Exception {
		if (ca.hasAuth(prm, 44, "I", "EXPORTACAO EXCEL")) {
			List<FnCartao> lista = fnCartaoCustomRp.listaFnCartaoExcel(local, caixa, cartao, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ope, ordem, ordemdir, tipo, status);
			ByteArrayInputStream in = FnCartaoExcelService.exportToExcel(lista);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// CHEQUE
	// *************************************************************************************
	@RequestAuthorization
	@PostMapping("/cheque/relatorio/excel/local")
	public ResponseEntity<byte[]> fnChequeEXCEL(@RequestParam("busca") String busca,
			@RequestParam("ordem") String ordem, @RequestParam("ordemdir") String ordemdir,
			@RequestParam("local") Long local, @RequestParam("caixa") Integer caixa,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("ove") String ove, @RequestParam("tipo") String tipo, @RequestParam("status") String status)
			throws Exception {
		if (ca.hasAuth(prm, 44, "I", "EXPORTACAO EXCEL")) {
			List<FnCheque> lista = fnChequeCustomRp.listaFnChequeExcel(local, caixa, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ove, ordem, ordemdir, tipo, status);
			ByteArrayInputStream in = FnChequeExcelService.exportToExcel(lista);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// 0017, 0018, 0019, 0020, 0021
	@RequestAuthorization
	@PostMapping("/cheque/relatorio/pdf/local")
	public ResponseEntity<byte[]> fnChequePDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("caixa") Integer caixa, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("ove") String ove, @RequestParam("tipo") String tipo,
			@RequestParam("status") String status, @RequestParam("tipoimpressao") String tipoimpressao)
			throws Exception {
		if (ca.hasAuth(prm, 44, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.fnChequePDF(local, caixa, dataini, datafim, busca, ove, ordem, ordemdir, tipo, status,
					tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// CENTRO DE CUSTOS
	// ***************************************************************************
	// 0024, 0025
	@RequestAuthorization
	@PostMapping("/ccusto/relatorio/pdf")
	public ResponseEntity<byte[]> fnTituloCcustoPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("ccusto") Integer ccusto, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("tipoimpressao") String tipoimpressao)
			throws Exception {
		if (ca.hasAuth(prm, 44, "I", "IMPRESSAO PDF")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.fnTituloCcustoPDF(local, ccusto, dataini, datafim, ordem, ordemdir);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/ccusto/relatorio/excel/local")
	public ResponseEntity<byte[]> fnTituloCcustoEXCEL(@RequestParam("ccusto") Integer ccusto,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("ordem") String ordem, @RequestParam("ordemdir") String ordemdir) throws Exception {
		if (ca.hasAuth(prm, 44, "I", "EXPORTACAO EXCEL")) {
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			List<FnTituloCcusto> titulosAberto = null;
			// Lista para calculos de saldos -------------------------
			if (local > 0) {
				titulosAberto = fnTituloCcustoRp.findAllByCdCustoEmabertoLocal(local, ccusto, Date.valueOf(dataini),
						Date.valueOf(datafim));
			} else {
				titulosAberto = fnTituloCcustoRp.findAllByCdCustoEmaberto(ccusto, Date.valueOf(dataini),
						Date.valueOf(datafim));
			}
			List<FnTituloCcusto> titulosPagos = null;
			// Lista para calculos de saldos
			if (local > 0) {
				titulosPagos = fnTituloCcustoRp.findAllByCdCustoPagosLocal(local, ccusto, Date.valueOf(dataini),
						Date.valueOf(datafim));
			} else {
				titulosPagos = fnTituloCcustoRp.findAllByCdCustoPagos(ccusto, Date.valueOf(dataini),
						Date.valueOf(datafim));
			}
			ByteArrayInputStream in = FnTituloCcustoExcelService.exportToExcel(titulosAberto, titulosPagos);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// LCDOC
	// **************************************************************************************
	// 0026, 0032, 0058, 0060, 0064
	@RequestAuthorization
	@PostMapping("/lcdoc/relatorio/pdf")
	public ResponseEntity<byte[]> lcDocPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("para") Long para, @RequestParam("vep") Long vep, @RequestParam("tipo") String tipo,
			@RequestParam("cat") String cat, @RequestParam("ofe") String ofe, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("coduf") Integer coduf,
			@RequestParam("st") String st, @RequestParam("tipoimpressao") String tipoimpressao,
			@RequestParam("itenssel") Long[] itenssel) throws Exception {
		if (ca.hasAuth(prm, 66, "I", "IMPRESSAO PDF")) {
			// Resp. Vendedores
			if (prm.cliente().getRole().getId() == 8) {
				if (prm.cliente().getCdpessoavendedor() != null) {
					vep = prm.cliente().getCdpessoavendedor();
				}
			}
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.lcDocPDF(local, para, vep, tipo, cat, ofe, dataini, datafim, busca, coduf, ordem,
					ordemdir, st, tipoimpressao, itenssel);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// 0029_1, 0078
	@RequestAuthorization
	@GetMapping("/lcdoc/{id}/tipoimpressao/{tipoimpressao}/tipopapel/{tipopapel}")
	public ResponseEntity<byte[]> lcDocPDF(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id, @PathVariable("tipoimpressao") String tipoimpressao,
			@PathVariable("tipopapel") String tipopapel) throws Exception {
		if (ca.hasAuth(prm, 66, "I", "IMPRESSAO DOCUMENTO VENDA, OS OU DEVOLUCAO")) {
			Map<String, Object> par = new HashMap<String, Object>();
			boolean usaveic = prm.cliente().getSiscfg().isSis_mostra_veiculo();
			boolean impref = prm.cliente().getSiscfg().isSis_impref_doc();
			String sql = " WHERE doc.id = " + id;
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			par.put("usaveic", usaveic);
			par.put("impref", impref);
			response.setContentType("application/pdf");
			if (tipopapel.equals("1")) {
				tipoimpressao = "0029_1";
			} else if (tipopapel.equals("2")) {
				tipoimpressao = "0078";
			}
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/lcdoc/controle/{id}")
	public ResponseEntity<byte[]> lcDocControlePDF(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 66, "I", "IMPRESSAO DOCUMENTO VENDA, OS OU DEVOLUCAO")) {
			Map<String, Object> par = new HashMap<String, Object>();
			String sql = " WHERE doc.id = " + id;
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel("0030", par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/lcdoc/{id}/tipo/{tipo}")
	public ResponseEntity<byte[]> lcDocControleDiversos(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id, @PathVariable("tipo") String tipo) throws Exception {
		Integer codac = 66;
		if (tipo.equals("07")) {
			codac = 82;
		}
		if (ca.hasAuth(prm, codac, "I", "EXPORTACAO EXCEL")) {
			LcDoc doc = lcDocRp.findById(id).get();
			ByteArrayInputStream in = LcDocCotacaoExcelService.exportToExcel(doc);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/lcdoc/carne/{id}")
	public ResponseEntity<byte[]> lcDocCarnePDF(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 66, "I", "IMPRESSAO DOCUMENTO VENDA, OS OU DEVOLUCAO")) {
			Map<String, Object> par = new HashMap<String, Object>();
			String sql = " WHERE t.lcdoc = " + id + " AND t.tipo = 'R'";
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel("0073", par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/lcdoc/relatorio/excel/local")
	public ResponseEntity<byte[]> lcDocEXCEL(@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("para") Long para, @RequestParam("vep") Long vep, @RequestParam("tipo") String tipo,
			@RequestParam("cat") String cat, @RequestParam("ofe") String ofe, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("coduf") Integer coduf,
			@RequestParam("st") String st) throws Exception {
		if (ca.hasAuth(prm, 66, "I", "EXPORTACAO EXCEL")) {
			// Resp. Vendedores
			if (prm.cliente().getRole().getId() == 8) {
				if (prm.cliente().getCdpessoavendedor() != null) {
					vep = prm.cliente().getCdpessoavendedor();
				}
			}
			List<LcDoc> lista = lcDocCustomRp.listaLcDocExcel(local, para, vep, tipo, cat, ofe, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, coduf, ordem, ordemdir, st);
			ByteArrayInputStream in = null;
			// Documentos 01 a 04 e 08
			if (tipo.equals("01") || tipo.equals("02") || tipo.equals("03") || tipo.equals("04") || tipo.equals("08")) {
				in = LcDocExcelService.exportToExcel(lista);
			}
			// Documento 06 e outros
			if (tipo.equals("06") || tipo.equals("07")) {
				in = LcDocOutrosExcelService.exportToExcel(lista, tipo);
			}
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/lcdoc/mc/relatorio/excel/local")
	public ResponseEntity<byte[]> lcDocMaisComEXCEL(@RequestParam("local") Long local,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("tipo") String tipo, @RequestParam("iz") String iz) throws Exception {
		if (ca.hasAuth(prm, 66, "I", "EXPORTACAO EXCEL")) {
			// Lista de Docs inicialmente
			List<LcDoc> lista = lcDocCustomRp.listaLcDocExcel(local, 0L, 0L, tipo, "00", "F", Date.valueOf(dataini),
					Date.valueOf(datafim), "", 0, "id", "ASC", "2");
			List<LcDocItem> itens = reportService.itensMC(lista, iz);
			ByteArrayInputStream in = LcDocItemExcelService.exportToExcel(itens);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/lcdoc/ci/relatorio/excel/local")
	public ResponseEntity<byte[]> lcDocClientesInativosEXCEL(@RequestParam("local") Long local,
			@RequestParam("vend") Long vend, @RequestParam("dias") Integer dias) throws Exception {
		if (ca.hasAuth(prm, 66, "I", "EXPORTACAO EXCEL")) {
			// Lista de Docs inicialmente
			Date dataatual = Date.valueOf(DataUtil.dataBancoAtual());
			java.util.Date datab = DataUtil.addRemDias(dataatual, dias, "R");
			Date database = new Date(datab.getTime());
			List<CdPessoa> pessoas = reportService.clientesInativosNC(local, vend, database);
			ByteArrayInputStream in = CdPessoaExcelService.exportToExcel(pessoas, "TODOS");
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// ROMANEIOS***********************************
	@RequestAuthorization
	@GetMapping("/lcroma/{id}/tipoimpressao/{tipoimpressao}")
	public ResponseEntity<byte[]> lcRomaPDF(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id, @PathVariable("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 83, "I", "IMPRESSAO ROMANEIO")) {
			Map<String, Object> par = new HashMap<String, Object>();
			String sql = " WHERE roma.id = " + id;
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// ETIQUETAS
	// *************************************************************************************
	@RequestAuthorization
	@GetMapping("/etq/iddoc/{iddoc}/tipoetiqueta/{tipoetiqueta}/qtd/{qtd}")
	public ResponseEntity<byte[]> impEtiquetaDoc(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("iddoc") Long iddoc, @PathVariable("tipoetiqueta") String tipoetiqueta,
			@PathVariable("qtd") Integer qtd) throws Exception {
		if (ca.hasAuth(prm, 66, "I", "IMPRESSAO ETIQUETA")) {
			Map<String, Object> par = new HashMap<String, Object>();
			List<LcDoc> docs = reportService.listDocsEtq(iddoc, qtd);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRelBean(tipoetiqueta, par, request.getServletContext(), docs);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/etq/idprod/{idprod}/tipoetiqueta/{tipoetiqueta}/qtd/{qtd}/tabelapreco/{tabelapreco}/naoinformarvalores/{naoinformarvalores}")
	public ResponseEntity<byte[]> impEtiquetaProduto(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("idprod") Long idprod, @PathVariable("tipoetiqueta") String tipoetiqueta,
			@PathVariable("qtd") Integer qtd, @PathVariable("tabelapreco") Integer tabelapreco,
			@PathVariable("naoinformarvalores") boolean naoinformarvalores) throws Exception {
		if (ca.hasAuth(prm, 66, "I", "IMPRESSAO ETIQUETA " + idprod)) {
			Map<String, Object> par = new HashMap<String, Object>();
			par.put("naoinformarvalores", naoinformarvalores);
			CdProdutoPreco prodPreco = cdProdutoPrecoRp.findByTabProd(tabelapreco, idprod).get();
			List<CdProdutoPreco> prods = reportService.listProdutoEtq(prodPreco, qtd);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRelBean(tipoetiqueta, par, request.getServletContext(), prods);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/imprimircomps/idprod/{idprod}/impvalores/{impvalores}/qtd/{qtd}/local/{local}")
	public ResponseEntity<byte[]> impCompsProduto(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("idprod") Long idprod, @PathVariable("impvalores") String impvalores,
			@PathVariable("qtd") BigDecimal qtd, @PathVariable("local") Long local) throws Exception {
		if (ca.hasAuth(prm, 66, "I", "IMPRESSAO COMPOSICAO PRODUTO " + idprod)) {
			Map<String, Object> par = new HashMap<String, Object>();
			String sql = " WHERE p.id = " + idprod + " AND e.id = " + local + " ORDER BY c.tipo ASC";
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("impvalores", impvalores);
			par.put("qtd", qtd);
			response.setContentType("application/pdf");
			String tipoimpressao = "0080";
			if (impvalores.equals("N")) {
				tipoimpressao = "0081";
			}
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// NFE
	// ****************************************************************************************
	// 0031, 0033
	@RequestAuthorization
	@PostMapping("/fsnfe/relatorio/pdf")
	public ResponseEntity<byte[]> fsNFePDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("tipo") String tipo, @RequestParam("uffim") String uffim,
			@RequestParam("oensai") String oensai, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("modelo") Integer modelo,
			@RequestParam("st") String st, @RequestParam("status") Integer status,
			@RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 18, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.fsNfePDF(tipoimpressao, local, dataini, datafim, tipo, busca, uffim, modelo, st, status,
					oensai, ordem, ordemdir);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/fsnfe/{id}")
	public ResponseEntity<byte[]> fsNFeDANFE(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "I", "IMPRESSAO DANFE")) {
			Map<String, Object> par = new HashMap<String, Object>();
			String sql = " WHERE f.id = " + id;
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel("0027", par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/fsnfe/evento/{id}")
	public ResponseEntity<byte[]> fsNFeEventoDANFE(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "I", "IMPRESSAO EVENTOS DANFE")) {
			Map<String, Object> par = new HashMap<String, Object>();
			String sql = " WHERE f.id = " + id;
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel("0028", par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/fsnfe/nfce/{id}")
	public ResponseEntity<byte[]> fsNFCeDANFE(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "I", "IMPRESSAO NFCE DANFE")) {
			FsNfe nfe = fsNfeRp.findById(id).get();
			String qrCode = FsUtil.qrCodeNFCe(nfe)[0];
			String urlQrCode = FsUtil.qrCodeNFCe(nfe)[1];
			int qtdItens = fsNfeItemRp.getCountFsnfeitem(id);
			Map<String, Object> par = new HashMap<String, Object>();
			String sql = " WHERE f.id = " + id;
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			par.put("qrCode", qrCode);
			par.put("urlQrCode", urlQrCode);
			par.put("qtdItens", qtdItens);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel("0027_1", par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/fsnfe/xml/{id}")
	public ResponseEntity<byte[]> fsNFeXML(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "EXPORTACAO NFE XML")) {
			FsNfe nfe = fsNfeRp.findById(id).get();
			ByteArrayInputStream in = new ByteArrayInputStream(nfe.getXml().getBytes());
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/xml"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/fsnfe/relatorio/excel/local")
	public ResponseEntity<byte[]> fsNFeEXCEL(@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("tipo") String tipo, @RequestParam("uffim") String uffim,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("modelo") Integer modelo, @RequestParam("st") String st,
			@RequestParam("status") Integer status) throws Exception {
		if (ca.hasAuth(prm, 18, "I", "EXPORTACAO EXCEL")) {
			List<FsNfe> lista = fsCustomRp.listaFsNfeExcel(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo,
					busca, uffim, modelo, st, status, "E", ordem, ordemdir);
			ByteArrayInputStream in = FsNfeExcelService.exportToExcel(lista);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// NFSE
	// ***************************************************************************************
	// 0065
	@RequestAuthorization
	@PostMapping("/fsnfse/relatorio/pdf")
	public ResponseEntity<byte[]> fsNFSePDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("tipo") String tipo, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("st") String st,
			@RequestParam("status") Integer status, @RequestParam("tipoimpressao") String tipoimpressao)
			throws Exception {
		if (ca.hasAuth(prm, 18, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.fsNfsePDF(local, dataini, datafim, tipo, busca, st, status, ordem, ordemdir);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/fsnfse/{id}")
	public ResponseEntity<byte[]> fsNFSeDANFSE(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "I", "IMPRESSAO DANFSE")) {
			Map<String, Object> par = new HashMap<String, Object>();
			String sql = " WHERE f.id = " + id;
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel("0059", par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/fsnfse/xml/{id}")
	public ResponseEntity<byte[]> fsNFSeXML(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "EXPORTACAO NFSE XML")) {
			FsNfse nfse = fsNfseRp.findById(id).get();
			ByteArrayInputStream in = new ByteArrayInputStream(nfse.getXml().getBytes());
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/xml"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// CTE
	// ****************************************************************************************
	@RequestAuthorization
	@GetMapping("/fscte/{id}")
	public ResponseEntity<byte[]> fsCTeDACTE(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 43, "I", "IMPRESSAO DACTE")) {
			Map<String, Object> par = new HashMap<String, Object>();
			FsCte cte = fsCteRp.findById(id).get();
			String qrcode = FsCteWebService.CTeQrCode + "?chCTe=" + cte.getChaveac() + "&tpAmb=" + cte.getTpamb();
			String sql = " WHERE f.id = " + id;
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			par.put("qrcode", qrcode);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel("0047", par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// 0055, 0036, 0037
	@RequestAuthorization
	@PostMapping("/fscte/relatorio/pdf")
	public ResponseEntity<byte[]> fsCTePDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("tipo") String tipo, @RequestParam("oensai") String oensai,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("modelo") Integer modelo, @RequestParam("st") String st, @RequestParam("uffim") String uffim,
			@RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 43, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.fsCtePDF(local, dataini, datafim, tipo, busca, uffim, modelo, st, oensai, ordem,
					ordemdir, tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/fscte/relatorio/excel/local")
	public ResponseEntity<byte[]> fsCTeEXCEL(@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("tipo") String tipo, @RequestParam("oensai") String oensai,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("modelo") Integer modelo, @RequestParam("st") String st, @RequestParam("uffim") String uffim)
			throws Exception {
		if (ca.hasAuth(prm, 43, "I", "EXPORTACAO EXCEL")) {
			List<FsCte> lista = fsCustomRp.listaFsCteExcel(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo,
					busca, uffim, modelo, st, oensai, ordem, ordemdir);
			ByteArrayInputStream in = FsCteExcelService.exportToExcel(lista);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/fscte/xml/{id}")
	public ResponseEntity<byte[]> fsCTeXML(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 43, "E", "EXPORTACAO CTE XML")) {
			FsCte cte = fsCteRp.findById(id).get();
			ByteArrayInputStream in = new ByteArrayInputStream(cte.getXml().getBytes());
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/xml"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// MDFE
	// ***************************************************************************************
	// 00039
	@RequestAuthorization
	@PostMapping("/fsmdfe/relatorio/pdf")
	public ResponseEntity<byte[]> fsMDFePDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("tipo") String tipo, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("status") Integer status,
			@RequestParam("enc") String enc, @RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 73, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.fsMdfePDF(local, dataini, datafim, tipo, busca, status, enc, ordem, ordemdir);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/fsmdfe/relatorio/excel/local")
	public ResponseEntity<byte[]> fsMDFeEXCEL(@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("tipo") String tipo, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("status") Integer status,
			@RequestParam("enc") String enc) throws Exception {
		if (ca.hasAuth(prm, 18, "I", "EXPORTACAO EXCEL")) {
			List<FsMdfe> lista = fsCustomRp.listaFsMdfeExcel(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo,
					busca, status, enc, 58, ordem, ordemdir);
			ByteArrayInputStream in = FsMdfeExcelService.exportToExcel(lista);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/fsmdfe/{id}")
	public ResponseEntity<byte[]> fsMDFeDAMDFE(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 73, "I", "IMPRESSAO DAMDFE")) {
			Map<String, Object> par = new HashMap<String, Object>();
			FsMdfe mdfe = fsMdfeRp.findById(id).get();
			String qrcode = FsMdfeWebService.MDFeQrCode + "?chMDFe=" + mdfe.getChaveac() + "&tpAmb=" + mdfe.getTpamb();
			if (mdfe.getTpamb().equals(2)) {
				qrcode = FsMdfeWebService.MDFeQrCodeHom + "?chMDFe=" + mdfe.getChaveac() + "&tpAmb=" + mdfe.getTpamb();
			}
			String sql = " WHERE f.id = " + id;
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			par.put("qrcode", qrcode);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel("0040", par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/fsmdfe/xml/{id}")
	public ResponseEntity<byte[]> fsMDFeXML(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "EXPORTACAO MDFE XML")) {
			FsMdfe mdfe = fsMdfeRp.findById(id).get();
			ByteArrayInputStream in = new ByteArrayInputStream(mdfe.getXml().getBytes());
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/xml"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/fsmdfe/evento/{id}")
	public ResponseEntity<byte[]> fsMDFeEventoDAMDFE(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "I", "IMPRESSAO EVENTOS DAMDFE")) {
			Map<String, Object> par = new HashMap<String, Object>();
			FsMdfe mdfe = fsMdfeRp.findById(id).get();
			String qrcode = FsMdfeWebService.MDFeQrCode + "?chMDFe=" + mdfe.getChaveac() + "&tpAmb=" + mdfe.getTpamb();
			if (mdfe.getTpamb().equals(2)) {
				qrcode = FsMdfeWebService.MDFeQrCodeHom + "?chMDFe=" + mdfe.getChaveac() + "&tpAmb=" + mdfe.getTpamb();
			}
			String sql = " WHERE f.id = " + id;
			par.put("software", mc.MidasApresenta);
			par.put("site", mc.MidasSite);
			par.put("sql", sql);
			par.put("id", id);
			par.put("qrcode", qrcode);
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel("0041", par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// ESTOQUE
	// ************************************************************************************
	// 0034, 0042, 0035
	@RequestAuthorization
	@PostMapping("/esest/relatorio/pdf")
	public ResponseEntity<byte[]> esEstPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("tipoitem") String tipoitem, @RequestParam("st") String st,
			@RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 72, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.esEstPDF(local, dataini, datafim, tipoitem, busca, st, ordem, ordemdir, tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/esest/relatorio/excel/local")
	public ResponseEntity<byte[]> esEstEXCEL(@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("tipoitem") String tipoitem, @RequestParam("st") String st) throws Exception {
		if (ca.hasAuth(prm, 72, "I", "EXPORTACAO EXCEL")) {
			List<EsEst> lista = esCustomRp.listaEsEstExcel(local, Date.valueOf(dataini), Date.valueOf(datafim), busca,
					tipoitem, st, ordem, ordemdir);
			ByteArrayInputStream in = EsEstExcelService.exportToExcel(lista);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// 0034
	@RequestAuthorization
	@PostMapping("/esestmov/relatorio/pdf")
	public ResponseEntity<byte[]> esEstMovPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("ordem") String ordem, @RequestParam("ordemdir") String ordemdir,
			@RequestParam("local") Long local, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("idprod") Long idprod,
			@RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 72, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.esEstMovPDF(local, dataini, datafim, idprod, ordem, ordemdir, tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// 0070, 0071, 0072
	@RequestAuthorization
	@PostMapping("/esestext/relatorio/pdf")
	public ResponseEntity<byte[]> esEstExtPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("local") Long local, @RequestParam("vep") Long vep,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 66, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.esEstExtPDF(local, vep, dataini, datafim, busca, ordem, ordemdir, tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// PRODUCAO
	// ***********************************************************************************
	// 0044
	@RequestAuthorization
	@PostMapping("/producao/relatorio/pdf")
	public ResponseEntity<byte[]> producaoMovPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("ordem") String ordem, @RequestParam("ordemdir") String ordemdir,
			@RequestParam("local") Long local, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim, @RequestParam("idprod") Long idprod,
			@RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 74, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.producaoMovPDF(local, dataini, datafim, idprod, ordem, ordemdir, tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// PRODUTO
	// ************************************************************************************
	// 0038
	@RequestAuthorization
	@PostMapping("/produto/relatorio/pdf")
	public ResponseEntity<byte[]> cdProdutoPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("tipo") String tipo,
			@RequestParam("tpitem") String tpitem, @RequestParam("cat") Integer cat, @RequestParam("gru") Integer gru,
			@RequestParam("grusub") Integer grusub, @RequestParam("status") String status,
			@RequestParam("dataini") String dataini, @RequestParam("datafim") String datafim,
			@RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 72, "I", "IMPRESSAO PDF")) {
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.cdProdPDF(dataini, datafim, tipo, tpitem, cat, gru, grusub, busca, status, ordem,
					ordemdir, tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			;
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/produto/relatorio/excel")
	public ResponseEntity<byte[]> cdProdutoEXCEL(@RequestParam("busca") String busca,
			@RequestParam("ordem") String ordem, @RequestParam("ordemdir") String ordemdir,
			@RequestParam("tipo") String tipo, @RequestParam("tpitem") String tpitem, @RequestParam("cat") Integer cat,
			@RequestParam("gru") Integer gru, @RequestParam("grusub") Integer grusub,
			@RequestParam("status") String status, @RequestParam("dataini") String dataini,
			@RequestParam("datafim") String datafim) throws Exception {
		if (ca.hasAuth(prm, 72, "I", "EXPORTACAO EXCEL")) {
			List<CdProduto> lista = cdCustomRp.listaCdProdutoExcel(Date.valueOf(dataini), Date.valueOf(datafim), tipo,
					tpitem, cat, gru, grusub, busca, status, ordem, ordemdir);
			ByteArrayInputStream in = CdProdutoExcelService.exportToExcel(lista);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// PAINEL DE PREÇOS
	// ************************************************************
	// 0045, 00048, 0063
	@RequestAuthorization
	@PostMapping("/produto/preco/relatorio/pdf")
	public ResponseEntity<byte[]> cdProdutoPrecoPDF(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("busca") String busca, @RequestParam("local") Long local, @RequestParam("tab") Integer tab,
			@RequestParam("tipoitem") String tipoitem, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("status") String status,
			@RequestParam("tipoimpressao") String tipoimpressao) throws Exception {
		if (ca.hasAuth(prm, 79, "I", "IMPRESSAO PDF")) {
			// Verifica acesso Representante
			if (prm.cliente().getRole().getId() == 8) {
				if (tipoimpressao.equals("0045")) {
					tipoimpressao = "0045_1";
				}
				if (tipoimpressao.equals("0048")) {
					tipoimpressao = "0048_1";
				}
				if (tipoimpressao.equals("0063")) {
					tipoimpressao = "0063_1";
				}
			}
			Map<String, Object> par = new HashMap<String, Object>();
			par = reportService.cdProdPrecoPDF(busca, local, tab, tipoitem, status, ordem, ordemdir, tipoimpressao);
			par.put("tamanhoFonte", prm.cliente().getClientecfg().getTamanhofonte());
			response.setContentType("application/pdf");
			response.setContentType("application/pdf");
			byte[] contents = reportService.geraRel(tipoimpressao, par, request.getServletContext());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.set("Content-Disposition", "inline");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/produto/preco/relatorio/excel")
	public ResponseEntity<byte[]> cdProdutoPrecoEXCEL(@RequestParam("busca") String busca,
			@RequestParam("local") Long local, @RequestParam("tab") Integer tab,
			@RequestParam("tipoitem") String tipoitem, @RequestParam("ordem") String ordem,
			@RequestParam("ordemdir") String ordemdir, @RequestParam("status") String status) throws Exception {
		if (ca.hasAuth(prm, 79, "I", "EXPORTACAO EXCEL")) {
			List<CdProdutoPreco> lista = cdCustomRp.listaCdProdutoPrecoExcel(local, tab, tipoitem, busca, status, ordem,
					ordemdir);
			ByteArrayInputStream in = CdProdutoPrecoExcelService.exportToExcel(lista, prm);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/drecad/relatorio/excel")
	public ResponseEntity<byte[]> cdDreEXCEL(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("local") Long local) throws Exception {
		if (ca.hasAuth(prm, 25, "I", "EXPORTACAO EXCEL")) {
			List<CdPlconConta> lista = cdPlconContaRp.findAllByLocal(local);
			ByteArrayInputStream in = CdDreExcelService.exportToExcel(lista);
			byte[] contents = IOUtils.toByteArray(in);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.set("Content-Disposition", "attachment");
			return new ResponseEntity<byte[]>(contents, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
