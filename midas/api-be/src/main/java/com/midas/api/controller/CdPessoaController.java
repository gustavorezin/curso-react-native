package com.midas.api.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
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

import com.midas.api.mt.repository.ClienteRepository;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.CdCaixaOpera;
import com.midas.api.tenant.entity.CdNfeCfgVend;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.entity.CdPessoaGrupo;
import com.midas.api.tenant.entity.CdPessoaSetor;
import com.midas.api.tenant.entity.CdProdutoTabVend;
import com.midas.api.tenant.entity.LcDocItemCot;
import com.midas.api.tenant.entity.ReceitaWSCNPJ;
import com.midas.api.tenant.repository.CdCaixaOperaRepository;
import com.midas.api.tenant.repository.CdCustomRepository;
import com.midas.api.tenant.repository.CdNfeCfgVendRepository;
import com.midas.api.tenant.repository.CdPessoaGrupoRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.CdPessoaSetorRepository;
import com.midas.api.tenant.repository.CdProdutoTabVendRepository;
import com.midas.api.tenant.repository.LcDocItemCotRepository;
import com.midas.api.tenant.service.CdPessoaCadConsService;
import com.midas.api.util.CNPJConsUtil;
import com.midas.api.util.CPFConsUtil;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.DataUtil;
import com.midas.api.util.ImageUtil;
import com.midas.api.util.PessoaUtil;

@RestController
@RequestMapping("/private/cdpessoa")
public class CdPessoaController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private CdPessoaGrupoRepository cdGruPesRp;
	@Autowired
	private CdPessoaSetorRepository cdSetorPesRp;
	@Autowired
	private ClienteRepository clienteRp;
	@Autowired
	private CdCaixaOperaRepository caixaOperaRp;
	@Autowired
	private CdCustomRepository cdCustomRp;
	@Autowired
	private CdProdutoTabVendRepository cdProdutoTabVendRp;
	@Autowired
	private CdNfeCfgVendRepository cdNfeCfgVendRp;
	@Autowired
	private LcDocItemCotRepository lcDocItemCotRp;
	@Autowired
	private CdPessoaCadConsService cadConsService;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	// PESSOA CADASTRO****************************************
	@RequestAuthorization
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<CdPessoa> cdPessoa(@PathVariable("id") Long id) {
		Optional<CdPessoa> obj = cdPessoaRp.findById(id);
		return new ResponseEntity<CdPessoa>(obj.get(), HttpStatus.OK);
	}
	
	@RequestAuthorization
	@GetMapping("/pessoa/cpfcnpj/{cpfcnpj}")
	public ResponseEntity<CdPessoa> cdPessoa(@PathVariable("cpfcnpj") String cpfcnpj) {
		CdPessoa obj = cdPessoaRp.findFirstByCpfcnpj(cpfcnpj);
		return new ResponseEntity<CdPessoa>(obj, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/pessoa/conscnpj/{cnpj}")
	public ResponseEntity<ReceitaWSCNPJ> consCnpj(@PathVariable("cnpj") String cnpj) throws Exception {
		if (ca.hasAuth(prm, 30, "Y", cnpj)) {
			ReceitaWSCNPJ obj = CNPJConsUtil.consulta(cnpj);
			if (obj.getNome() == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<ReceitaWSCNPJ>(obj, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/pessoa/conscpf/captcha")
	public ResponseEntity<String[]> consCpfCaptcha() throws Exception {
		if (ca.hasAuth(prm, 30, "Y", "CAPTCHA")) {
			String[] captcha = CPFConsUtil.getCaptcha();
			return new ResponseEntity<String[]>(captcha, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/pessoa/conscpf")
	public ResponseEntity<ReceitaWSCNPJ> consCpf(@RequestParam("cpf") String cpf, @RequestParam("nasc") String nasc,
			@RequestParam("captcha") String captcha, @RequestParam("cookies") String cookies) throws Exception {
		if (ca.hasAuth(prm, 30, "Y", cpf)) {
			nasc = DataUtil.dataPadraoBrSemBarra(Date.valueOf(nasc));
			ReceitaWSCNPJ obj = CPFConsUtil.consulta(cpf, nasc, captcha, cookies);
			if (obj.toString().equals("")) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<ReceitaWSCNPJ>(obj, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/pessoa")
	public ResponseEntity<?> cadastrarCdPessoa(@RequestBody CdPessoa cdPessoa) throws Exception {
		if (ca.hasAuth(prm, 84, "C", cdPessoa.getNome()) && cdPessoa.getTipo().equals("CLIENTE")) {
			// Verifica se ja tem
			CdPessoa pessoa = cdPessoaRp.findFirstByCpfcnpj(cdPessoa.getCpfcnpj());
			if (pessoa == null || PessoaUtil.verCPF_CNPJ(cdPessoa.getCpfcnpj()) == true) {
				// Verifica se responsavel se indicou se for representante
				if (prm.cliente().getRole().getId() == 8) {
					if (prm.cliente().getCdpessoavendedor() != null) {
						cdPessoa.setCdpessoaresp_id(prm.cliente().getCdpessoavendedor());
					}
				}
				cdPessoaRp.save(cdPessoa);
				// Gera simples se necessario
				cadConsService.geraCfgFiscalIni(cdPessoa.getTipo());
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FOUND);
			}
		} else if (ca.hasAuth(prm, 34, "C", cdPessoa.getNome()) && !cdPessoa.getTipo().equals("CLIENTE")) {
			// Verifica se ja tem
			CdPessoa pessoa = cdPessoaRp.findFirstByCpfcnpj(cdPessoa.getCpfcnpj());
			if (pessoa == null || PessoaUtil.verCPF_CNPJ(cdPessoa.getCpfcnpj()) == true) {
				cdPessoaRp.save(cdPessoa);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/pessoa/cadcons/tipopessoa/{tipopessoa}/cdgrupes/{cdgrupes}/crt/{crt}/indiedest/{indiedest}")
	public ResponseEntity<String[]> cadastrarCdPessoaConsultaReceita(@RequestBody ReceitaWSCNPJ receitaWSCNPJ,
			@PathVariable("tipopessoa") String tipopessoa, @PathVariable("cdgrupes") Integer cdgrupes,
			@PathVariable("crt") Integer crt, @PathVariable("indiedest") String indiedest) throws Exception {
		if (ca.hasAuth(prm, 30, "C", receitaWSCNPJ.getNome())) {
			String retorno[] = cadConsService.cadastrar(receitaWSCNPJ, prm, tipopessoa, cdgrupes, crt, indiedest);
			// Gera simples se necessario
			cadConsService.geraCfgFiscalIni(tipopessoa);
			return new ResponseEntity<String[]>(retorno, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/pessoa/inicial")
	public ResponseEntity<?> cadastrarCdPessoaInicial(@RequestBody CdPessoa cdPessoa) throws Exception {
		if (ca.hasAuth(prm, 34, "C", cdPessoa.getNome())) {
			CdPessoaGrupo gp = cdGruPesRp.getById(1);
			cdPessoa.setTipo("EMPRESA");
			cdPessoa.setStatus("ATIVO");
			cdPessoa.setCdpessoagrupo(gp);
			CdPessoa p = cdPessoaRp.save(cdPessoa);
			// Relaciona ao cliente principal
			clienteRp.updateLocal(p.getId(), p.getNome(), prm.cliente().getId());
			// Gera simples se necessario
			cadConsService.geraCfgFiscalIni("EMPRESA");
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/pessoa")
	public ResponseEntity<?> atualizarCdPessoa(@RequestBody CdPessoa cdPessoa) throws Exception {
		if (ca.hasAuth(prm, 81, "A", cdPessoa.getNome()) && cdPessoa.getTipo().equals("CLIENTE")) {
			// Verifica se ja tem
			CdPessoa pessoa = cdPessoaRp.findFirstByCpfcnpjDifId(cdPessoa.getCpfcnpj(), cdPessoa.getId());
			if (pessoa == null || PessoaUtil.verCPF_CNPJ(cdPessoa.getCpfcnpj()) == true) {
				// Verifica se responsavel se indicou se for representante
				if (prm.cliente().getRole().getId() == 8) {
					if (prm.cliente().getCdpessoavendedor() != null) {
						cdPessoa.setCdpessoaresp_id(prm.cliente().getCdpessoavendedor());
					}
				}
				// Pega novamente para nao zerar imagem
				CdPessoa pi = cdPessoaRp.findById(cdPessoa.getId()).get();
				cdPessoa.setImagem(pi.getImagem());
				cdPessoaRp.save(cdPessoa);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FOUND);
			}
		} else if (ca.hasAuth(prm, 34, "A", cdPessoa.getNome()) && !cdPessoa.getTipo().equals("CLIENTE")) {
			// Verifica se ja tem
			CdPessoa pessoa = cdPessoaRp.findFirstByCpfcnpjDifId(cdPessoa.getCpfcnpj(), cdPessoa.getId());
			if (pessoa == null || PessoaUtil.verCPF_CNPJ(cdPessoa.getCpfcnpj()) == true) {
				CdPessoa pi = cdPessoaRp.findById(cdPessoa.getId()).get();
				cdPessoa.setImagem(pi.getImagem());
				cdPessoaRp.save(cdPessoa);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@DeleteMapping("/pessoa/{id}")
	public ResponseEntity<?> removerCdPessoa(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 32, "R", "ID " + id)) {
			// Nao permite remover vendedor numero 2 padrao
			if (id.equals(2L)) {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			} else {
				cdPessoaRp.deleteById(id);
				return ResponseEntity.ok(HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/pessoa/buscatipo")
	public ResponseEntity<List<CdPessoa>> listaCdPessoasBusca(@RequestParam("tipo") String tipo,
			@RequestParam("busca") String busca) throws Exception {
		// Verifica se responsavel se indicou se for representante
		Long respvend = 0L;
		if (prm.cliente().getRole().getId() == 8) {
			if (prm.cliente().getCdpessoavendedor() != null) {
				respvend = prm.cliente().getCdpessoavendedor();
			}
		}
		List<CdPessoa> lista = null;
		if (tipo == null || (tipo != null && tipo.trim().isEmpty()) || tipo.equalsIgnoreCase("undefined")) {
			// Apenas clientes do representante
			if (respvend > 0) {
				lista = cdPessoaRp.findAllByNomeFilterByAtivoResp(respvend, busca);
			} else {
				lista = cdPessoaRp.findAllByNomeFilterByAtivo(busca);
			}
		} else {
			// Apenas clientes do representante
			if (respvend > 0) {
				lista = cdPessoaRp.findAllByNomeFilterByTipoAtivoResp(respvend, tipo, busca);
			} else {
				lista = cdPessoaRp.findAllByNomeFilterByTipoAtivo(tipo, busca);
			}
		}
		return new ResponseEntity<List<CdPessoa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/pessoa/buscatipotodos")
	public ResponseEntity<List<CdPessoa>> listaCdPessoasBuscaTodosAI(@RequestParam("tipo") String tipo,
			@RequestParam("busca") String busca) throws Exception {
		// Verifica se responsavel se indicou se for representante - ativos e inativos
		Long respvend = 0L;
		if (prm.cliente().getRole().getId() == 8) {
			if (prm.cliente().getCdpessoavendedor() != null) {
				respvend = prm.cliente().getCdpessoavendedor();
			}
		}
		List<CdPessoa> lista = null;
		if (tipo == null || (tipo != null && tipo.trim().isEmpty()) || tipo.equalsIgnoreCase("undefined")) {
			// Apenas clientes do representante
			if (respvend > 0) {
				lista = cdPessoaRp.findAllByNomeFilterByResp(respvend, busca);
			} else {
				lista = cdPessoaRp.findAllByNomeFilter(busca);
			}
		} else {
			// Apenas clientes do representante
			if (respvend > 0) {
				lista = cdPessoaRp.findAllByNomeFilterByTipoResp(respvend, tipo, busca);
			} else {
				lista = cdPessoaRp.findAllByNomeFilterByTipo(tipo, busca);
			}
		}
		return new ResponseEntity<List<CdPessoa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/pessoa/buscatipolocal")
	public ResponseEntity<List<CdPessoa>> listaCdPessoasBuscaLocal(@RequestParam("tipo") String tipo,
			@RequestParam("busca") String busca, @RequestParam("local") Long local) throws Exception {
		List<CdPessoa> lista = cdPessoaRp.findAllByNomeFilterByTipoAtivoLocal(local, tipo, busca);
		return new ResponseEntity<List<CdPessoa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/pessoa/buscaporvendedor")
	public ResponseEntity<List<CdPessoa>> listaCdPessoaBuscaPorVendedor(@RequestParam("busca") String busca,
			@RequestParam("vendedor") Long vendedor) throws Exception {
		List<CdPessoa> lista = cdPessoaRp.findAllByNomeFilterByAtivoResp(vendedor, busca);
		return new ResponseEntity<List<CdPessoa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/pessoa/tp/{tipo}")
	public ResponseEntity<List<CdPessoa>> listaCdPessoasTipoTodas(@PathVariable("tipo") String tipo) throws Exception {
		List<CdPessoa> lista = cdPessoaRp.findAllByTipoTodos(tipo);
		return new ResponseEntity<List<CdPessoa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/pessoa/tp/{tipo}/status/{status}")
	public ResponseEntity<List<CdPessoa>> listaCdPessoasTipoTodas(@PathVariable("tipo") String tipo,
			@PathVariable("status") String status) throws Exception {
		List<CdPessoa> lista = cdPessoaRp.findAllByTipoTodosAtivo(tipo, status);
		return new ResponseEntity<List<CdPessoa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/pessoa/tp/{tipo}/status/{status}/local/{local}")
	public ResponseEntity<List<CdPessoa>> listaCdPessoasTipoTodasLocal(@PathVariable("tipo") String tipo,
			@PathVariable("status") String status, @PathVariable("local") Long local) throws Exception {
		List<CdPessoa> lista = null;
		// Filtro especial para Representante
		Long id = prm.cliente().getCdpessoavendedor();
		if (prm.cliente().getRole().getId() == 8 && id != null) {
			lista = cdPessoaRp.findAllByRespAtivo(id, status);
		} else {
			lista = cdPessoaRp.findAllByTipoTodosLocalAtivo(local, tipo, status);
		}
		return new ResponseEntity<List<CdPessoa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/pessoa/qtd/tp/{tipo}/local/{local}/status/{status}")
	public ResponseEntity<Integer> listaCdPessoasTipoTodasQtd(@PathVariable("tipo") String tipo,
			@PathVariable("local") Long local, @PathVariable("status") String status) throws Exception {
		Integer qtd = 0;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			if (!status.equals("X")) {
				qtd = cdPessoaRp.findAllByTipoTodosQtdStatus(tipo, status);
				if (local > 0) {
					qtd = cdPessoaRp.findAllByTipoTodosQtdLocalStatus(local, tipo, status);
				}
			} else {
				qtd = cdPessoaRp.findAllByTipoTodosQtd(tipo);
				if (local > 0) {
					qtd = cdPessoaRp.findAllByTipoTodosLocalQtd(local, tipo);
				}
			}
		} else {
			local = prm.cliente().getCdpessoaemp();
			if (!status.equals("X")) {
				qtd = cdPessoaRp.findAllByTipoTodosQtdLocalStatus(local, tipo, status);
			} else {
				qtd = cdPessoaRp.findAllByTipoTodosLocalQtd(local, tipo);
			}
		}
		return new ResponseEntity<Integer>(qtd, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/pessoa/qtdclienterep/local/{local}")
	public ResponseEntity<Integer> listaCdPessoasTipoTodasQtdRep(@PathVariable("local") Long local) throws Exception {
		Integer qtd = 0;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		Long respvend = 0L;
		if (prm.cliente().getCdpessoavendedor() != null) {
			respvend = prm.cliente().getCdpessoavendedor();
		}
		if (aclocal.equals("S")) {
			qtd = cdPessoaRp.findAllByTipoTodosQtdRep("CLIENTE", respvend);
			if (local > 0) {
				qtd = cdPessoaRp.findAllByTipoTodosLocalQtdResp(local, "CLIENTE", respvend);
			}
		} else {
			local = prm.cliente().getCdpessoaemp();
			qtd = cdPessoaRp.findAllByTipoTodosLocalQtdResp(local, "CLIENTE", respvend);
		}
		return new ResponseEntity<Integer>(qtd, HttpStatus.OK);
	}

	// Usado para controle de locais de emissao
	@RequestAuthorization
	@GetMapping("/pessoa/local")
	public ResponseEntity<List<CdPessoa>> listaCdPessoasTipo() throws Exception {
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		List<CdPessoa> lista = null;
		if (aclocal.equals("S")) {
			lista = cdPessoaRp.findAllByTipoTodos("EMPRESA");
		} else {
			lista = cdPessoaRp.findAllByTipoTodosLocal(prm.cliente().getCdpessoaemp(), "EMPRESA");
		}
		return new ResponseEntity<List<CdPessoa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/pessoa/localativos")
	public ResponseEntity<List<CdPessoa>> listaCdPessoasTipoAtivos() throws Exception {
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		List<CdPessoa> lista = null;
		if (aclocal.equals("S")) {
			lista = cdPessoaRp.findAllByTipoTodosAtivo("EMPRESA", "ATIVO");
		} else {
			lista = cdPessoaRp.findAllByTipoTodosLocalIdAtivo(prm.cliente().getCdpessoaemp(), "EMPRESA", "ATIVO");
		}
		return new ResponseEntity<List<CdPessoa>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/pessoa/forneccot/{id}")
	public ResponseEntity<List<CdPessoa>> listaCdFornecCotacao(@PathVariable("id") Long id) throws Exception {
		List<CdPessoa> lista = new ArrayList<CdPessoa>();
		List<LcDocItemCot> cots = lcDocItemCotRp.listaPorFonecLcdoc(id);
		for (LcDocItemCot c : cots) {
			lista.add(c.getCdpessoacot());
		}
		return new ResponseEntity<List<CdPessoa>>(lista, HttpStatus.OK);
	}

	// PARA POSSIVEIS BLOQUEIOS APENAS DE CLIENTES
	@RequestAuthorization
	@GetMapping("/pessoa/cliente/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/tp/{tipo}/lc/{local}/"
			+ "dti/{dataini}/dtf/{datafim}/respvend/{respvend}/status/{status}/uf/{uf}/cidade/{cidade}")
	public ResponseEntity<Page<CdPessoa>> listaCdPessoasClienteBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp,
			@PathVariable("tipo") String tipo, @PathVariable("local") Long local,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("respvend") Long respvend, @PathVariable("status") String status,
			@PathVariable("uf") Integer uf, @PathVariable("cidade") Integer cidade) throws Exception {
		if (ca.hasAuth(prm, 33, "L", "LISTAGEM / CONSULTA")) { // Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Verifica acesso Representante
			if (prm.cliente().getRole().getId() == 8) {
				if (prm.cliente().getCdpessoavendedor() != null) {
					respvend = prm.cliente().getCdpessoavendedor();
				}
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<CdPessoa> lista = cdCustomRp.listaCdPessoa(local, tipo, Date.valueOf(dataini), Date.valueOf(datafim),
					busca, uf, cidade, respvend, status, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<CdPessoa>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/pessoa/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}/tp/{tipo}/lc/{local}"
			+ "/dti/{dataini}/dtf/{datafim}/status/{status}/uf/{uf}/cidade/{cidade}")
	public ResponseEntity<Page<CdPessoa>> listaCdPessoasBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp,
			@PathVariable("tipo") String tipo, @PathVariable("local") Long local,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("status") String status, @PathVariable("uf") Integer uf,
			@PathVariable("cidade") Integer cidade) throws Exception {
		if (ca.hasAuth(prm, 34, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<CdPessoa> lista = cdCustomRp.listaCdPessoa(local, tipo, Date.valueOf(dataini), Date.valueOf(datafim),
					busca, uf, cidade, 0L, status, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<CdPessoa>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/pessoa/contatos/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdPessoa>> listaCdPessoasBuscaMeusContatos(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 39, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			Long respvend = 0L;
			if (prm.cliente().getCdpessoavendedor() != null) {
				respvend = prm.cliente().getCdpessoavendedor();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<CdPessoa> lista = cdCustomRp.listaCdPessoa(local, "", Date.valueOf("2000-01-01"),
					Date.valueOf("2060-01-01"), busca, 0, 0, respvend, "X", ordem, ordemdir, pageable);
			return new ResponseEntity<Page<CdPessoa>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/pessoa/uploadimagem")
	public ResponseEntity<String> uploadImagem(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) {
		if (ca.hasAuth(prm, 34, "A", file.getName())) {
			try {
				Integer imgSize = prm.cliente().getSiscfg().getSis_tam_imagem_pixel();
				cdPessoaRp.updateImagem(ImageUtil.resizeImage(file.getBytes(), imgSize), id);
				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/pessoa/removeimagem")
	public ResponseEntity<String> removeImagem(@RequestBody Long id) {
		if (ca.hasAuth(prm, 34, "R", "ID " + id)) {
			cdPessoaRp.updateImagem(null, id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/pessoa/alteratabpre/emlote/{novatabpre}")
	public ResponseEntity<?> alterarTabPrecoEmLote(@RequestBody List<CdPessoa> cdPessoas,
			@PathVariable("novatabpre") Integer novatabpre) {
		if (ca.hasAuth(prm, 34, "A", "ATUALIZACAO DE TABELAS DE PRECOS PARA CADASTRO EM LOTE ")) {
			for (CdPessoa p : cdPessoas) {
				cdPessoaRp.updatePessoaTabPreco(novatabpre, p.getId());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/pessoa/alteracfgfiscal/emlote/{novaconfig}")
	public ResponseEntity<?> alterarCfgFiscalEmLote(@RequestBody List<CdPessoa> cdPessoas,
			@PathVariable("novaconfig") Integer novaconfig) {
		if (ca.hasAuth(prm, 34, "A", "ATUALIZACAO DE CONFIGURACAO FISCAL PARA CADASTRO EM LOTE ")) {
			for (CdPessoa p : cdPessoas) {
				cdPessoaRp.updatePessoaCfgFiscal(novaconfig, p.getId());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	// TIPO GRUPO PESSOA****************************************
	@RequestAuthorization
	@GetMapping("/grupes/{id}")
	public ResponseEntity<CdPessoaGrupo> cdGruPes(@PathVariable("id") Integer id) {
		Optional<CdPessoaGrupo> obj = cdGruPesRp.findById(id);
		return new ResponseEntity<CdPessoaGrupo>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/grupes")
	public ResponseEntity<?> cadastrarCdGruPes(@RequestBody CdPessoaGrupo cdGruPes) throws Exception {
		if (ca.hasAuth(prm, 28, "C", cdGruPes.getNome())) {
			cdGruPesRp.save(cdGruPes);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/grupes")
	public ResponseEntity<?> atualizarCdGruPes(@RequestBody CdPessoaGrupo cdGruPes) throws Exception {
		if (ca.hasAuth(prm, 28, "A", cdGruPes.getNome())) {
			cdGruPesRp.save(cdGruPes);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/grupes/{id}")
	public ResponseEntity<?> removerCdGruPes(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 28, "R", "ID " + id)) {
			// Nao remover itens principais
			if (id > 9) {
				cdGruPesRp.deleteById(id);
				return ResponseEntity.ok(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.LOCKED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/grupes/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdPessoaGrupo>> listaCdGruPesBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 28, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdPessoaGrupo> lista = null;
			if (busca == null || (busca != null && busca.trim().isEmpty()) || busca.equalsIgnoreCase("undefined")) {
				lista = cdGruPesRp.findAll(pageable);
			} else {
				lista = cdGruPesRp.findAllByNomeBusca(busca, pageable);
			}
			return new ResponseEntity<Page<CdPessoaGrupo>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/grupes/tipo/{tipo}")
	public ResponseEntity<List<CdPessoaGrupo>> listaCdGruPesTipo(@PathVariable("tipo") String tipo) throws Exception {
		tipo = tipo.trim();
		List<CdPessoaGrupo> lista = cdGruPesRp.findByTipoOrderByNomeAsc(tipo);
		return new ResponseEntity<List<CdPessoaGrupo>>(lista, HttpStatus.OK);
	}

	// SETOR PESSOA - PARA FUNCIONARIOS****************************************
	@RequestAuthorization
	@GetMapping("/setorpes/{id}")
	public ResponseEntity<CdPessoaSetor> cdSetorPes(@PathVariable("id") Integer id) {
		Optional<CdPessoaSetor> obj = cdSetorPesRp.findById(id);
		return new ResponseEntity<CdPessoaSetor>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/setorpes")
	public ResponseEntity<?> cadastrarCdSetorPes(@RequestBody CdPessoaSetor cdSetorPes) throws Exception {
		if (ca.hasAuth(prm, 29, "C", cdSetorPes.getNome())) {
			cdSetorPesRp.save(cdSetorPes);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@PutMapping("/setorpes")
	public ResponseEntity<?> atualizarCdSetorPes(@RequestBody CdPessoaSetor cdSetorPes) throws Exception {
		if (ca.hasAuth(prm, 29, "A", cdSetorPes.getNome())) {
			cdSetorPesRp.save(cdSetorPes);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/setorpes/{id}")
	public ResponseEntity<?> removerCdSetorPes(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 29, "R", "ID " + id)) {
			// Verifica se tem vinculos - apenas colaborador
			if (cdSetorPesRp.qtdUtilizado(id) != 0) {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
			} else {
				cdSetorPesRp.deleteById(id);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/setorpes/todos")
	public ResponseEntity<List<CdPessoaSetor>> listaCdSetorPesTodos() throws Exception {
		List<CdPessoaSetor> lista = cdSetorPesRp.findAll();
		return new ResponseEntity<List<CdPessoaSetor>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/setorpes/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/ipp/{itenspp}")
	public ResponseEntity<Page<CdPessoaSetor>> listaCdSetorPesBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("itenspp") int itenspp) throws Exception {
		if (ca.hasAuth(prm, 29, "L", "LISTAGEM / CONSULTA")) {
			Sort sort = null;
			if (ordemdir.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, ordem);
			}
			if (ordemdir.equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, ordem);
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			Pageable pageable = PageRequest.of(pagina, itenspp, sort);
			Page<CdPessoaSetor> lista = null;
			if (busca == null || (busca != null && busca.trim().isEmpty()) || busca.equalsIgnoreCase("undefined")) {
				lista = cdSetorPesRp.findAll(pageable);
			} else {
				lista = cdSetorPesRp.findAllByNomeBusca(busca, pageable);
			}
			return new ResponseEntity<Page<CdPessoaSetor>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// CAIXA DE OPERACAO
	@RequestAuthorization
	@PostMapping("/pessoa/addcx")
	public ResponseEntity<?> salvarCdCaixaOpera(@RequestBody CdCaixaOpera cdCaixaOpera) throws Exception {
		if (ca.hasAuth(prm, 34, "A", cdCaixaOpera.getCdcaixa().getNome())) {
			caixaOperaRp.save(cdCaixaOpera);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/pessoa/remcx/{id}")
	public ResponseEntity<?> removerCdCaixaOpera(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 34, "R", "ID " + id)) {
			caixaOperaRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// TABELAS DE PRECO DO VENDEDOR
	@RequestAuthorization
	@PostMapping("/pessoa/addtabvend")
	public ResponseEntity<?> salvarCdProdutoTabVend(@RequestBody CdProdutoTabVend cdProdutoTabVend) throws Exception {
		if (ca.hasAuth(prm, 34, "A", "INCLUSAO DE TABELA PARA VENDEDOR")) {
			cdProdutoTabVendRp.save(cdProdutoTabVend);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/pessoa/remtabvend/{id}")
	public ResponseEntity<?> removerCdProdutoTabVend(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 34, "R", "ID " + id)) {
			cdProdutoTabVendRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// NFE CFG VENDEDOR
	@RequestAuthorization
	@PostMapping("/pessoa/addnfecfgvend")
	public ResponseEntity<?> salvarCdNfeCfgVend(@RequestBody CdNfeCfgVend cdNfeCfgVend) throws Exception {
		if (ca.hasAuth(prm, 34, "A", "INCLUSAO DE CONFIG. FISCAL PARA VENDEDOR")) {
			cdNfeCfgVendRp.save(cdNfeCfgVend);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/pessoa/remnfecfgvend/{id}")
	public ResponseEntity<?> removerCdNfeCfgVend(@PathVariable("id") Integer id) {
		if (ca.hasAuth(prm, 34, "R", "ID " + id)) {
			cdNfeCfgVendRp.deleteById(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
