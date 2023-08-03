package com.midas.api.controller.app;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.midas.api.constant.CdBolcfgProtesto;
import com.midas.api.constant.CdCaixaTipo;
import com.midas.api.constant.CdCartaoTipo;
import com.midas.api.constant.CdEmailLocal;
import com.midas.api.constant.CdEmailTipo;
import com.midas.api.constant.CdMedida;
import com.midas.api.constant.CdPeso;
import com.midas.api.constant.CdPessoaCrt;
import com.midas.api.constant.CdPessoaTipo;
import com.midas.api.constant.CdProdutoTipo;
import com.midas.api.constant.DocFiscalTipoER;
import com.midas.api.constant.FnCartaoStatus;
import com.midas.api.constant.FnChequeStatus;
import com.midas.api.constant.FnChequeStatusTr;
import com.midas.api.constant.FnChequeTipo;
import com.midas.api.constant.FnContaTipo;
import com.midas.api.constant.FnDiaFixo;
import com.midas.api.constant.FnDreTipoLocal;
import com.midas.api.constant.FnFormaPag;
import com.midas.api.constant.FnFormaPagM;
import com.midas.api.constant.FnFormaPagT;
import com.midas.api.constant.FnFormaPagTroco;
import com.midas.api.constant.FnRecDespTipo;
import com.midas.api.constant.FnTecnoTipoImpressao;
import com.midas.api.constant.FnTipoPR;
import com.midas.api.constant.FsCteCargaUnid;
import com.midas.api.constant.FsCteModal;
import com.midas.api.constant.FsCteToma;
import com.midas.api.constant.FsCteTpCte;
import com.midas.api.constant.FsCteTpEmis;
import com.midas.api.constant.FsCteTpServ;
import com.midas.api.constant.FsMdfeModal;
import com.midas.api.constant.FsMdfeTpCar;
import com.midas.api.constant.FsMdfeTpCompPag;
import com.midas.api.constant.FsMdfeTpProdPred;
import com.midas.api.constant.FsMdfeTpProp;
import com.midas.api.constant.FsMdfeTpRod;
import com.midas.api.constant.FsMdfeTpTransp;
import com.midas.api.constant.FsModelo;
import com.midas.api.constant.FsNfeCst;
import com.midas.api.constant.FsNfeFinNfe;
import com.midas.api.constant.FsNfeIdDest;
import com.midas.api.constant.FsNfeIndFinal;
import com.midas.api.constant.FsNfeIndIeDest;
import com.midas.api.constant.FsNfeIndPres;
import com.midas.api.constant.FsNfeIpi;
import com.midas.api.constant.FsNfeManConsSt;
import com.midas.api.constant.FsNfeModFrete;
import com.midas.api.constant.FsNfeModbc;
import com.midas.api.constant.FsNfeModbcSt;
import com.midas.api.constant.FsNfeOrigem;
import com.midas.api.constant.FsNfePisCofins;
import com.midas.api.constant.FsNfeTipo;
import com.midas.api.constant.FsNfeTipoItem;
import com.midas.api.constant.FsNfeTpEmis;
import com.midas.api.constant.FsNfseCadMun;
import com.midas.api.constant.FsNfseFin;
import com.midas.api.constant.FsNfseFpag;
import com.midas.api.constant.FsNfseIncCult;
import com.midas.api.constant.FsNfseLocalServ;
import com.midas.api.constant.FsNfseNatOp;
import com.midas.api.constant.FsNfseOperacao;
import com.midas.api.constant.FsNfseOptSn;
import com.midas.api.constant.FsNfseOrgaoPublico;
import com.midas.api.constant.FsNfseRegEspTrib;
import com.midas.api.constant.FsNfseRegimeTrib;
import com.midas.api.constant.FsNfseStEspecial;
import com.midas.api.constant.FsNfseTipoFone;
import com.midas.api.constant.FsStatus;
import com.midas.api.constant.ImpressaoEtiquetaDoc;
import com.midas.api.constant.ImpressaoEtiquetaProd;
import com.midas.api.constant.ImpressaoReciboTipo;
import com.midas.api.constant.LcCatDoc;
import com.midas.api.constant.LcCatDocRep;
import com.midas.api.constant.LcClimbaStatusDoc;
import com.midas.api.constant.LcDocEntregaStatus;
import com.midas.api.constant.LcDocPriori;
import com.midas.api.constant.LcDocStatus;
import com.midas.api.constant.LcTpDoc;
import com.midas.api.constant.ModuloTipo;
import com.midas.api.constant.PlconNovoCliente;
import com.midas.api.constant.SisListasExibe;
import com.midas.api.constant.SisListasExibicao;
import com.midas.api.constant.SisModuloTipo;
import com.midas.api.constant.SisTamanhoFonte;
import com.midas.api.constant.TecnoSituacaoTipo;
import com.midas.api.constant.TpDoc;
import com.midas.api.mt.config.DBContextHolder;
import com.midas.api.mt.entity.CdChequeMotivos;
import com.midas.api.mt.entity.Cep;
import com.midas.api.mt.entity.Role;
import com.midas.api.mt.entity.Tenant;
import com.midas.api.mt.repository.CdChequesMotivosRepository;
import com.midas.api.mt.repository.ClienteRepository;
import com.midas.api.mt.repository.RoleRepository;
import com.midas.api.mt.repository.TenantRepository;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.CdCidade;
import com.midas.api.tenant.entity.CdEstado;
import com.midas.api.tenant.entity.CdPessoa;
import com.midas.api.tenant.repository.CdCertRepository;
import com.midas.api.tenant.repository.CdCidadeRepository;
import com.midas.api.tenant.repository.CdEstadoRepository;
import com.midas.api.tenant.repository.CdPessoaRepository;
import com.midas.api.tenant.repository.EsEstRepository;
import com.midas.api.tenant.repository.FnCartaoRepository;
import com.midas.api.tenant.repository.FnChequeRepository;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.repository.FsCteManRepository;
import com.midas.api.tenant.repository.FsCteRepository;
import com.midas.api.tenant.repository.FsMdfeRepository;
import com.midas.api.tenant.repository.FsNfeManRepository;
import com.midas.api.tenant.repository.FsNfeRepository;
import com.midas.api.tenant.repository.FsNfseRepository;
import com.midas.api.tenant.repository.LcDocRepository;
import com.midas.api.tenant.repository.LcRomaRepository;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.CepConsUtil;
import com.midas.api.util.DataBaseBackupUtil;
import com.midas.api.util.ImageUtil;

@RestController
@RequestMapping("/private/app")
public class AppController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CdPessoaRepository cdPessoaRp;
	@Autowired
	private ClienteRepository clienteRp;
	@Autowired
	private CdEstadoRepository cdEstadoRp;
	@Autowired
	private CdCidadeRepository cdCidadeRp;
	@Autowired
	private RoleRepository roleRp;
	@Autowired
	private TenantRepository tenantRp;
	@Autowired
	private FnTituloRepository fnTituloRp;
	@Autowired
	private DataBaseBackupUtil dataBaseBackupUtil;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private CdCertRepository cdCertRp;
	@Autowired
	private FnChequeRepository fnChequeRp;
	@Autowired
	private FnCartaoRepository fnCartaoRp;
	@Autowired
	private CdChequesMotivosRepository cdChequesMotivosRp;
	@Autowired
	private FsNfeManRepository fsNfeManRp;
	@Autowired
	private FsNfeRepository fsNfeRp;
	@Autowired
	private LcDocRepository lcDocRp;
	@Autowired
	private EsEstRepository esEstRp;
	@Autowired
	private FsMdfeRepository fsMdfeRp;
	@Autowired
	private FsNfseRepository fsNfseRp;
	@Autowired
	private FsCteManRepository fsCteManRp;
	@Autowired
	private FsCteRepository fsCteRp;
	@Autowired
	private LcRomaRepository lcRomaRp;

	@RequestAuthorization
	@PostMapping("/buscacep")
	public ResponseEntity<Cep> buscaCep(@RequestBody String cepconsulta) throws IOException {
		Cep cep = CepConsUtil.consulta(cepconsulta);
		CdCidade cidade = cdCidadeRp.findByIbge(cep.getIbge());
		CdEstado estado = cdEstadoRp.findByUf(cep.getUf());
		if (cidade != null) {
			cep.setCidade(cidade.getId());
		}
		if (estado != null) {
			cep.setEstado(estado.getId());
		}
		return new ResponseEntity<>(cep, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listaestados")
	public ResponseEntity<List<CdEstado>> listaEstados() throws IOException {
		List<CdEstado> lista = cdEstadoRp.findAll(Sort.by("nome"));
		return new ResponseEntity<List<CdEstado>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listacidades/{estado}")
	public ResponseEntity<List<CdCidade>> listaCidades(@PathVariable("estado") Integer estado) throws IOException {
		List<CdCidade> lista = cdCidadeRp.findAllByCdestado(estado);
		return new ResponseEntity<List<CdCidade>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listacidades/uf/{uf}")
	public ResponseEntity<List<CdCidade>> listaCidadesUf(@PathVariable("uf") String uf) throws IOException {
		List<CdCidade> lista = cdCidadeRp.findAllByCdestadoUf(uf);
		return new ResponseEntity<List<CdCidade>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/buscacpfcnpj")
	public ResponseEntity<CdPessoa> buscaCpfcnpj(@RequestParam("cpfcnpj") String cpfcnpj) throws IOException {
		cpfcnpj = CaracterUtil.remPout(cpfcnpj);
		CdPessoa pessoa = cdPessoaRp.findFirstByCpfcnpj(cpfcnpj);
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasexibicao")
	public ResponseEntity<List<SisListasExibicao>> listasExibicao() throws IOException {
		List<SisListasExibicao> lista = new ArrayList<SisListasExibicao>();
		for (SisListasExibe c : SisListasExibe.values()) {
			SisListasExibicao obj = new SisListasExibicao();
			obj.setQtd(c.getId());
			lista.add(obj);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listastamanhofonte")
	public ResponseEntity<ArrayList<String>> listasTamanhoFonte() throws IOException {
		SisTamanhoFonte[] tipos = SisTamanhoFonte.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (SisTamanhoFonte tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasrole")
	public ResponseEntity<List<Role>> listasRole() throws IOException {
		List<Role> lista = roleRp.findAll();
		return new ResponseEntity<List<Role>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/role/{id}")
	public ResponseEntity<Role> getRole(@PathVariable("id") Integer id) throws IOException {
		Optional<Role> obj = roleRp.findById(id);
		return new ResponseEntity<Role>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasformapagamento")
	public ResponseEntity<ArrayList<String>> listasFormaPagamento() throws IOException {
		FnFormaPag[] tipos = FnFormaPag.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnFormaPag tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasplconnovocliente")
	public ResponseEntity<ArrayList<String>> listasPlconNovoCliente() throws IOException {
		PlconNovoCliente[] tipos = PlconNovoCliente.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (PlconNovoCliente tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasformapagamentom")
	public ResponseEntity<ArrayList<String>> listasFormaPagamentoM() throws IOException {
		FnFormaPagM[] tipos = FnFormaPagM.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnFormaPagM tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasformapagamentot")
	public ResponseEntity<ArrayList<String>> listasFormaPagamentoT() throws IOException {
		FnFormaPagT[] tipos = FnFormaPagT.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnFormaPagT tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasformapagamentotroco")
	public ResponseEntity<ArrayList<String>> listasFormaPagamentoTroco() throws IOException {
		FnFormaPagTroco[] tipos = FnFormaPagTroco.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnFormaPagTroco tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfndretipolocal")
	public ResponseEntity<ArrayList<String>> listasFnDreTipoLocal() throws IOException {
		FnDreTipoLocal[] tipos = FnDreTipoLocal.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnDreTipoLocal tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfnchequestatus")
	public ResponseEntity<ArrayList<String>> listasFnChequeStatus() throws IOException {
		FnChequeStatus[] tipos = FnChequeStatus.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnChequeStatus tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfnchequestatustr")
	public ResponseEntity<ArrayList<String>> listasFnChequeStatustr() throws IOException {
		FnChequeStatusTr[] tipos = FnChequeStatusTr.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnChequeStatusTr tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfnchequetipo")
	public ResponseEntity<ArrayList<String>> listasFnChequeTipo() throws IOException {
		FnChequeTipo[] tipos = FnChequeTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnChequeTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfncartaostatus")
	public ResponseEntity<ArrayList<String>> listasFnCartaoStatus() throws IOException {
		FnCartaoStatus[] tipos = FnCartaoStatus.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnCartaoStatus tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listascdcartaotipo")
	public ResponseEntity<ArrayList<String>> listasCdCartaoTipo() throws IOException {
		CdCartaoTipo[] tipos = CdCartaoTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (CdCartaoTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfndiafixo")
	public ResponseEntity<ArrayList<String>> listasFnDiaFixo() throws IOException {
		FnDiaFixo[] tipos = FnDiaFixo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnDiaFixo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfntecnotipoimpressao")
	public ResponseEntity<ArrayList<String>> listasFnTecnoTipoImpressao() throws IOException {
		FnTecnoTipoImpressao[] tipos = FnTecnoTipoImpressao.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnTecnoTipoImpressao tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listastpdoc")
	public ResponseEntity<ArrayList<String>> listasTpDoc() throws IOException {
		TpDoc[] tipos = TpDoc.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (TpDoc tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listaslctpdoc")
	public ResponseEntity<ArrayList<String>> listasLcTpDoc() throws IOException {
		LcTpDoc[] tipos = LcTpDoc.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (LcTpDoc tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listaslccatdoc")
	public ResponseEntity<ArrayList<String>> listasLcCatDoc() throws IOException {
		LcCatDoc[] tipos = LcCatDoc.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (LcCatDoc tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listaslccatdocrep")
	public ResponseEntity<ArrayList<String>> listasLcCatDocRep() throws IOException {
		LcCatDocRep[] tipos = LcCatDocRep.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (LcCatDocRep tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasdocstatus")
	public ResponseEntity<ArrayList<String>> listasDocStatus() throws IOException {
		LcDocStatus[] tipos = LcDocStatus.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (LcDocStatus tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasdocentregastatus")
	public ResponseEntity<ArrayList<String>> listasDocEntregaStatus() throws IOException {
		LcDocEntregaStatus[] tipos = LcDocEntregaStatus.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (LcDocEntregaStatus tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasdocpriori")
	public ResponseEntity<ArrayList<String>> listasLcDocPriori() throws IOException {
		LcDocPriori[] tipos = LcDocPriori.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (LcDocPriori tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasclimbadocstatus")
	public ResponseEntity<ArrayList<String>> listasClimbaDocStatus() throws IOException {
		LcClimbaStatusDoc[] tipos = LcClimbaStatusDoc.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (LcClimbaStatusDoc tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listaspessoatipo")
	public ResponseEntity<ArrayList<String>> listasPessoaTipo() throws IOException {
		CdPessoaTipo[] tipos = CdPessoaTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (CdPessoaTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listaspessoacrt")
	public ResponseEntity<ArrayList<String>> listasPessoaCrt() throws IOException {
		CdPessoaCrt[] tipos = CdPessoaCrt.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (CdPessoaCrt tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listascdemailtipo")
	public ResponseEntity<ArrayList<String>> listasCdEmailTipo() throws IOException {
		CdEmailTipo[] tipos = CdEmailTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (CdEmailTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listascaixatipo")
	public ResponseEntity<ArrayList<String>> listasCaixaTipo() throws IOException {
		CdCaixaTipo[] tipos = CdCaixaTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (CdCaixaTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasprodutotipo")
	public ResponseEntity<ArrayList<String>> listasProdutoTipo() throws IOException {
		CdProdutoTipo[] tipos = CdProdutoTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (CdProdutoTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listascdmedida")
	public ResponseEntity<ArrayList<String>> listasCdMedida() throws IOException {
		CdMedida[] tipos = CdMedida.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (CdMedida tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listascdpeso")
	public ResponseEntity<ArrayList<String>> listasCdPeso() throws IOException {
		CdPeso[] tipos = CdPeso.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (CdPeso tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasrecdesptipo")
	public ResponseEntity<ArrayList<String>> listasRecDespTipo() throws IOException {
		FnRecDespTipo[] tipos = FnRecDespTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnRecDespTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfncontatipo")
	public ResponseEntity<ArrayList<String>> listasFnContaTipo() throws IOException {
		FnContaTipo[] tipos = FnContaTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnContaTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listascdbolcfgprotesto")
	public ResponseEntity<ArrayList<String>> listasCdBolcfgProtesto() throws IOException {
		CdBolcfgProtesto[] tipos = CdBolcfgProtesto.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (CdBolcfgProtesto tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listastecnosituacaotipo")
	public ResponseEntity<ArrayList<String>> listasTecnoSituacaoTipo() throws IOException {
		TecnoSituacaoTipo[] tipos = TecnoSituacaoTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (TecnoSituacaoTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listassismodulotipo")
	public ResponseEntity<ArrayList<String>> listasSisModuloTipo() throws IOException {
		SisModuloTipo[] tipos = SisModuloTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (SisModuloTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasmodulotipo")
	public ResponseEntity<ArrayList<String>> listasModuloTipo() throws IOException {
		ModuloTipo[] tipos = ModuloTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (ModuloTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasimpressaorecibotipo")
	public ResponseEntity<ArrayList<String>> listasImpressaoReciboTipo() throws IOException {
		ImpressaoReciboTipo[] tipos = ImpressaoReciboTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (ImpressaoReciboTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasimpressaoetiqueta/local/{local}")
	public ResponseEntity<ArrayList<String>> listasImpressaoEtiqueta(@PathVariable("local") String local)
			throws IOException {
		ArrayList<String> tiposLista = new ArrayList<String>();
		if (local.equals("lcdoc")) {
			ImpressaoEtiquetaDoc[] tipos = ImpressaoEtiquetaDoc.values();
			for (ImpressaoEtiquetaDoc tp : tipos) {
				tiposLista.add(tp.getDescricao().toString());
			}
		} else if (local.equals("cdproduto")) {
			ImpressaoEtiquetaProd[] tipos = ImpressaoEtiquetaProd.values();
			for (ImpressaoEtiquetaProd tp : tipos) {
				tiposLista.add(tp.getDescricao().toString());
			}
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasdocfiscaltipoer")
	public ResponseEntity<ArrayList<String>> listasDocFiscalTipoER() throws IOException {
		DocFiscalTipoER[] tipos = DocFiscalTipoER.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (DocFiscalTipoER tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/uploadimagemcliente")
	public ResponseEntity<String> uploadImagemCliente(@RequestParam("id") Long id,
			@RequestParam("file") MultipartFile file) {
		if (ca.hasAuth(prm, 1, "A", file.getName())) {
			try {
				clienteRp.updateImagem(ImageUtil.resizeImage(file.getBytes(), 600), id);
				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@PostMapping("/removeimagemcliente")
	public ResponseEntity<String> removeImagemCliente(@RequestBody Long id) {
		if (ca.hasAuth(prm, 1, "R", "ID " + id)) {
			clienteRp.updateImagem(null, id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// ENUMS RELACIONADOS A
	// NFE-NFECE########################################################
	@RequestAuthorization
	@GetMapping("/listasfsnfemodfrete")
	public ResponseEntity<ArrayList<String>> listasFsNfeModFrete() throws IOException {
		FsNfeModFrete[] tipos = FsNfeModFrete.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeModFrete tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfetipo")
	public ResponseEntity<ArrayList<String>> listasFsNfeTipo() throws IOException {
		FsNfeTipo[] tipos = FsNfeTipo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeTipo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfetipoitem")
	public ResponseEntity<ArrayList<String>> listasFsNfeTipoItem() throws IOException {
		FsNfeTipoItem[] tipos = FsNfeTipoItem.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		// Verifica representante
		Long respvend = 0L;
		if (prm.cliente().getRole().getId() == 8) {
			respvend = 1L;
		}
		if (respvend > 0) {
			for (FsNfeTipoItem tp : tipos) {
				String t = tp.getDescricao();
				if (t.equals("00") || t.equals("04")) {
					tiposLista.add(tp.getDescricao().toString());
				}
			}
		} else {
			for (FsNfeTipoItem tp : tipos) {
				tiposLista.add(tp.getDescricao().toString());
			}
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfeiddest")
	public ResponseEntity<ArrayList<String>> listasFsNfeIdDest() throws IOException {
		FsNfeIdDest[] tipos = FsNfeIdDest.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeIdDest tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfeindiedest")
	public ResponseEntity<ArrayList<String>> listasFsNfeIndIeDest() throws IOException {
		FsNfeIndIeDest[] tipos = FsNfeIndIeDest.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeIndIeDest tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfetpemis")
	public ResponseEntity<ArrayList<String>> listasFsNfeTpEmis() throws IOException {
		FsNfeTpEmis[] tipos = FsNfeTpEmis.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeTpEmis tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfefinnfe")
	public ResponseEntity<ArrayList<String>> listasFsNfeFinNfe() throws IOException {
		FsNfeFinNfe[] tipos = FsNfeFinNfe.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeFinNfe tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfeindfinal")
	public ResponseEntity<ArrayList<String>> listasFsNfeIndFinal() throws IOException {
		FsNfeIndFinal[] tipos = FsNfeIndFinal.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeIndFinal tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfeindpres")
	public ResponseEntity<ArrayList<String>> listasFsNfeIndPres() throws IOException {
		FsNfeIndPres[] tipos = FsNfeIndPres.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeIndPres tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfepiscofins")
	public ResponseEntity<ArrayList<String>> listasFsNfePisCofins() throws IOException {
		FsNfePisCofins[] tipos = FsNfePisCofins.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfePisCofins tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfeipi")
	public ResponseEntity<ArrayList<String>> listasFsNfeIpi() throws IOException {
		FsNfeIpi[] tipos = FsNfeIpi.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeIpi tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfeorigem")
	public ResponseEntity<ArrayList<String>> listasFsNfeOrigem() throws IOException {
		FsNfeOrigem[] tipos = FsNfeOrigem.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeOrigem tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfecst")
	public ResponseEntity<ArrayList<String>> listasFsNfeCst() throws IOException {
		FsNfeCst[] tipos = FsNfeCst.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeCst tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfemodbc")
	public ResponseEntity<ArrayList<String>> listasFsNfeModbc() throws IOException {
		FsNfeModbc[] tipos = FsNfeModbc.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeModbc tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfemodbcst")
	public ResponseEntity<ArrayList<String>> listasFsNfeModbcSt() throws IOException {
		FsNfeModbcSt[] tipos = FsNfeModbcSt.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeModbcSt tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfntipopr")
	public ResponseEntity<ArrayList<String>> listasFnTipoPR() throws IOException {
		FnTipoPR[] tipos = FnTipoPR.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FnTipoPR tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	// ENUMS RELACIONADOS A
	// CTE########################################################
	@RequestAuthorization
	@GetMapping("/listasfsctetpemis")
	public ResponseEntity<ArrayList<String>> listasFsCteTpEmis() throws IOException {
		FsCteTpEmis[] tipos = FsCteTpEmis.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsCteTpEmis tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsctetpcte")
	public ResponseEntity<ArrayList<String>> listasFsCteTpCte() throws IOException {
		FsCteTpCte[] tipos = FsCteTpCte.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsCteTpCte tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsctemodal")
	public ResponseEntity<ArrayList<String>> listasFsCteModal() throws IOException {
		FsCteModal[] tipos = FsCteModal.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsCteModal tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsctetpserv")
	public ResponseEntity<ArrayList<String>> listasFsCteTpServ() throws IOException {
		FsCteTpServ[] tipos = FsCteTpServ.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsCteTpServ tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsctetoma")
	public ResponseEntity<ArrayList<String>> listasFsCteToma() throws IOException {
		FsCteToma[] tipos = FsCteToma.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsCteToma tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsctecargaunid")
	public ResponseEntity<ArrayList<String>> listasFsCteCargaUnid() throws IOException {
		FsCteCargaUnid[] tipos = FsCteCargaUnid.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsCteCargaUnid tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	// ENUMS RELACIONADOS A
	// MDFE ########################################################3
	@RequestAuthorization
	@GetMapping("/listasfsmdfetprod")
	public ResponseEntity<ArrayList<String>> listasFsMdfeTprod() throws IOException {
		FsMdfeTpRod[] tipos = FsMdfeTpRod.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsMdfeTpRod tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsmdfetpcar")
	public ResponseEntity<ArrayList<String>> listasFsMdfeTpcar() throws IOException {
		FsMdfeTpCar[] tipos = FsMdfeTpCar.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsMdfeTpCar tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsmdfetpprop")
	public ResponseEntity<ArrayList<String>> listasFsMdfeTpprop() throws IOException {
		FsMdfeTpProp[] tipos = FsMdfeTpProp.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsMdfeTpProp tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsmdfetptransp")
	public ResponseEntity<ArrayList<String>> listasFsMdfeTptransp() throws IOException {
		FsMdfeTpTransp[] tipos = FsMdfeTpTransp.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsMdfeTpTransp tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsmdfetpprodpred")
	public ResponseEntity<ArrayList<String>> listasFsMdfeTpprodpred() throws IOException {
		FsMdfeTpProdPred[] tipos = FsMdfeTpProdPred.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsMdfeTpProdPred tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsmdfetpcomppag")
	public ResponseEntity<ArrayList<String>> listasFsMdfeTpcomppag() throws IOException {
		FsMdfeTpCompPag[] tipos = FsMdfeTpCompPag.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsMdfeTpCompPag tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsmdfemodal")
	public ResponseEntity<ArrayList<String>> listasFsMdfeModal() throws IOException {
		FsMdfeModal[] tipos = FsMdfeModal.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsMdfeModal tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfemanst")
	public ResponseEntity<ArrayList<String>> listasFsNfeManSt() throws IOException {
		FsNfeManConsSt[] tipos = FsNfeManConsSt.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfeManConsSt tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasemaillocal")
	public ResponseEntity<ArrayList<String>> listasEmailLocal() throws IOException {
		CdEmailLocal[] tipos = CdEmailLocal.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (CdEmailLocal tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsmodelo")
	public ResponseEntity<ArrayList<String>> listasFsModelo() throws IOException {
		FsModelo[] tipos = FsModelo.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsModelo tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsstatus")
	public ResponseEntity<ArrayList<String>> listasFsStatus() throws IOException {
		FsStatus[] tipos = FsStatus.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsStatus tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	// ENUMS RELACIONADOS A
	// NFS-E########################################################
	@RequestAuthorization
	@GetMapping("/listasfsnfsenatop")
	public ResponseEntity<ArrayList<String>> listasFsNfseNatOp() throws IOException {
		FsNfseNatOp[] tipos = FsNfseNatOp.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseNatOp tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfsefin")
	public ResponseEntity<ArrayList<String>> listasFsNfseFin() throws IOException {
		FsNfseFin[] tipos = FsNfseFin.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseFin tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfseregimetrib")
	public ResponseEntity<ArrayList<String>> listasFsNfseRegimeTrib() throws IOException {
		FsNfseRegimeTrib[] tipos = FsNfseRegimeTrib.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseRegimeTrib tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfselocalserv")
	public ResponseEntity<ArrayList<String>> listasFsNfseLocalServ() throws IOException {
		FsNfseLocalServ[] tipos = FsNfseLocalServ.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseLocalServ tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfseoperacao")
	public ResponseEntity<ArrayList<String>> listasFsNfseOperacao() throws IOException {
		FsNfseOperacao[] tipos = FsNfseOperacao.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseOperacao tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfsefpag")
	public ResponseEntity<ArrayList<String>> listasFsNfseFpag() throws IOException {
		FsNfseFpag[] tipos = FsNfseFpag.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseFpag tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfseoptsn")
	public ResponseEntity<ArrayList<String>> listasFsNfseOptSn() throws IOException {
		FsNfseOptSn[] tipos = FsNfseOptSn.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseOptSn tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfseinccult")
	public ResponseEntity<ArrayList<String>> listasFsNfseIncCult() throws IOException {
		FsNfseIncCult[] tipos = FsNfseIncCult.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseIncCult tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfsecadmun")
	public ResponseEntity<ArrayList<String>> listasFsNfseCadMun() throws IOException {
		FsNfseCadMun[] tipos = FsNfseCadMun.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseCadMun tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfseorgaopublico")
	public ResponseEntity<ArrayList<String>> listasFsNfseOrgaoPublico() throws IOException {
		FsNfseOrgaoPublico[] tipos = FsNfseOrgaoPublico.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseOrgaoPublico tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfseregesptrib")
	public ResponseEntity<ArrayList<String>> listasFsNfseRegEspTrib() throws IOException {
		FsNfseRegEspTrib[] tipos = FsNfseRegEspTrib.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseRegEspTrib tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfsestespecial")
	public ResponseEntity<ArrayList<String>> listasFsNfseStEspecial() throws IOException {
		FsNfseStEspecial[] tipos = FsNfseStEspecial.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseStEspecial tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/listasfsnfsetipofone")
	public ResponseEntity<ArrayList<String>> listasFsNfseTipoFone() throws IOException {
		FsNfseTipoFone[] tipos = FsNfseTipoFone.values();
		ArrayList<String> tiposLista = new ArrayList<String>();
		for (FsNfseTipoFone tp : tipos) {
			tiposLista.add(tp.getDescricao().toString());
		}
		return new ResponseEntity<>(tiposLista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/cheques/motivos")
	public ResponseEntity<List<CdChequeMotivos>> cdChequeMotivos() {
		List<CdChequeMotivos> lista = cdChequesMotivosRp.findAll(Sort.by("motivo").ascending());
		return new ResponseEntity<List<CdChequeMotivos>>(lista, HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/modulomenu")
	public ResponseEntity<Boolean> moduloMenu() throws Exception {
		if (ca.hasAuth(prm, 45, "A", "")) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/backup")
	public ResponseEntity<String[]> backupSistema() throws Exception {
		if (ca.hasAuth(prm, 36, "L", "CONSULTA DE BACKUP DO SISTEMA")) {
			Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
			String rp[] = new String[4];
			rp[0] = tn.getId().toString();
			rp[1] = tn.getDbname();
			rp[2] = tn.getBackupult().toString();
			rp[3] = tn.getBackupulthr().toString();
			return new ResponseEntity<String[]>(rp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/backup/download")
	public ResponseEntity<byte[]> downloadBackup() throws IOException {
		if (ca.hasAuth(prm, 36, "D", "DOWNLOAD BACKUP")) {
			Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
			Path sqlFileZip = Paths.get(tn.getBackupfd() + tn.getDbname() + ".zip");
			byte[] file = Files.readAllBytes(sqlFileZip);
			return new ResponseEntity<byte[]>(file, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/backup/novo")
	public ResponseEntity<String[]> novoBackupSistema() throws Exception {
		if (ca.hasAuth(prm, 36, "A", "NOVO BACKUP DO SISTEMA")) {
			dataBaseBackupUtil.backupApp(DBContextHolder.getCurrentDb(), "S");
			Tenant tn = tenantRp.findByDbname(DBContextHolder.getCurrentDb());
			String rp[] = new String[4];
			rp[0] = tn.getId().toString();
			rp[1] = tn.getDbname();
			rp[2] = tn.getBackupult().toString();
			rp[3] = tn.getBackupulthr().toString();
			return new ResponseEntity<String[]>(rp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/central/{modulo}")
	public ResponseEntity<String[]> centralNotificacoes(@PathVariable("modulo") String modulo) throws Exception {
		if (ca.hasAuth(prm, 40, "L", "CONSULTA DE NOTIFICACOES")) {
			String rp[] = new String[4];
			String avisausuario = "N";
			Long local = prm.cliente().getCdpessoaemp();
			// BACKUP
			Integer bkp = tenantRp.getBackupUlt(prm.cliente().getTenant().getId());
			if (bkp == 0) {
				avisausuario = "S";
			}
			// CERTIFICADOS e TITULOS PAGAR OU RECEBER e CHEQUES
			// Verifica se tem acesso a todos locais
			String aclocal = prm.cliente().getAclocal();
			if (aclocal.equals("S")) {
				// Certificados
				if (cdCertRp.verificaExpira(30) > 0) {
					avisausuario = "S";
				}
				if (modulo.equals("VENDAS") || modulo.equals("PDV") || modulo.equals("COMPLETO")) {
					// Em edicao
					if (lcDocRp.verificaDocStatus("01", "1") > 0) {
						avisausuario = "S";
					}
					if (fsNfeRp.verificaNfeStTipo("E", 1) > 0) {
						avisausuario = "S";
					}
				}
				if (modulo.equals("OS") || modulo.equals("COMPLETO")) {
					// Em edicao
					if (lcDocRp.verificaDocStatus("02", "1") > 0) {
						avisausuario = "S";
					}
					if (fsNfseRp.verificaNfseStTipo("E", 1) > 0) {
						avisausuario = "S";
					}
				}
				if (modulo.equals("FINANCEIRO") || modulo.equals("COMPLETO")) {
					// Titulos
					if (fnTituloRp.verificaTitulosVencidos() > 0) {
						avisausuario = "S";
					}
					// Cheques
					if (fnChequeRp.verificaChequesVencidos() > 0) {
						avisausuario = "S";
					}
					// Cartoes
					if (fnCartaoRp.verificaCartaoPrevistos() > 0) {
						avisausuario = "S";
					}
				}
				if (modulo.equals("COMPRAS") || modulo.equals("COMPLETO")) {
					// Nfe manifesto
					if (fsNfeManRp.verificaNfemanManifestoStImp() > 0) {
						avisausuario = "S";
					}
					// Nfe
					if (fsNfeRp.verificaNfeStImp() > 0) {
						avisausuario = "S";
					}
					// Cte manifesto
					if (fsCteManRp.verificaCtemanManifestoStImp() > 0) {
						avisausuario = "S";
					}
					// Cte
					if (fsCteRp.verificaCteStImp() > 0) {
						avisausuario = "S";
					}
					// Estoque critico-reposicao
					if (esEstRp.verficaEstMin() > 0) {
						avisausuario = "S";
					}
					// Cotacao
					if (lcDocRp.verificaDocStatus("07", "1") > 0) {
						avisausuario = "S";
					}
					// Pedido compra
					if (lcDocRp.verificaDocStatus("08", "1") > 0) {
						avisausuario = "S";
					}
				}
				if (modulo.equals("PRODUCAO") || modulo.equals("COMPLETO")) {
					// Consumo interno
					if (lcDocRp.verificaDocStatus("06", "1") > 0) {
						avisausuario = "S";
					}
				}
				if (modulo.equals("FISCAL") || modulo.equals("COMPLETO")) {
					// Nfe manifesto
					if (fsNfeManRp.verificaNfemanManifestoStImp() > 0) {
						avisausuario = "S";
					}
					// Nfe
					if (fsNfeRp.verificaNfeStImp() > 0) {
						avisausuario = "S";
					}
					// Mdfe
					if (fsMdfeRp.verificaMdfeEncerrados() > 0) {
						avisausuario = "S";
					}
				}
				if (modulo.equals("EXPEDICAO") || modulo.equals("COMPLETO")) {
					// Romaneios
					if (lcRomaRp.verificaLcRomaSt() > 0) {
						avisausuario = "S";
					}
				}
			} else {
				// Certificados
				if (cdCertRp.verificaExpiraLocal(local, 30) > 0) {
					avisausuario = "S";
				}
				if (modulo.equals("VENDAS") || modulo.equals("PDV") || modulo.equals("COMPLETO")) {
					// Em edicao
					if (lcDocRp.verificaDocStatusLocal(local, "01", "1") > 0) {
						avisausuario = "S";
					}
					if (fsNfeRp.verificaNfeStTipoLocal(local, "E", 1) > 0) {
						avisausuario = "S";
					}
				}
				if (modulo.equals("OS") || modulo.equals("COMPLETO")) {
					// Em edicao
					if (lcDocRp.verificaDocStatusLocal(local, "02", "1") > 0) {
						avisausuario = "S";
					}
					if (fsNfseRp.verificaNfseStTipoLocal(local, "E", 1) > 0) {
						avisausuario = "S";
					}
				}
				if (modulo.equals("FINANCEIRO") || modulo.equals("COMPLETO")) {
					// Titulos
					if (fnTituloRp.verificaTitulosVencidosLocal(local) > 0) {
						avisausuario = "S";
					}
					// Cheques
					if (fnChequeRp.verificaChequesVencidosLocal(local) > 0) {
						avisausuario = "S";
					}
					// Cartoes
					if (fnCartaoRp.verificaCartaoPrevistosLocal(local) > 0) {
						avisausuario = "S";
					}
				}
				if (modulo.equals("COMPRAS") || modulo.equals("COMPLETO")) {
					// Nfe manifesto
					if (fsNfeManRp.verificaNfemanManifestoStImpLocal(local) > 0) {
						avisausuario = "S";
					}
					// Nfe
					if (fsNfeRp.verificaNfeStImpImpLocal(local) > 0) {
						avisausuario = "S";
					}
					// Cte manifesto
					if (fsCteManRp.verificaCtemanManifestoStImpLocal(local) > 0) {
						avisausuario = "S";
					}
					// Cte
					if (fsCteRp.verificaCteStImpImpLocal(local) > 0) {
						avisausuario = "S";
					}
					// Estoque critico-reposicao
					if (esEstRp.verficaEstMinLocal(local) > 0) {
						avisausuario = "S";
					}
					// Cotacao
					if (lcDocRp.verificaDocStatusLocal(local, "07", "1") > 0) {
						avisausuario = "S";
					}
					// Pedido compra
					if (lcDocRp.verificaDocStatusLocal(local, "08", "1") > 0) {
						avisausuario = "S";
					}
					// Consumo interno
					if (lcDocRp.verificaDocStatusLocal(local, "06", "1") > 0) {
						avisausuario = "S";
					}
				}
				if (modulo.equals("PRODUCAO") || modulo.equals("COMPLETO")) {
				}
				if (modulo.equals("FISCAL") || modulo.equals("COMPLETO")) {
					// Nfe manifesto
					if (fsNfeManRp.verificaNfemanManifestoStImpLocal(local) > 0) {
						avisausuario = "S";
					}
					// Nfe
					if (fsNfeRp.verificaNfeStImpImpLocal(local) > 0) {
						avisausuario = "S";
					}
					// Mdfe
					if (fsMdfeRp.verificaMdfeEncerradosLocal(local) > 0) {
						avisausuario = "S";
					}
				}
				if (modulo.equals("EXPEDICAO") || modulo.equals("COMPLETO")) {
					// Romaneios
					if (lcRomaRp.verificaLcRomaStLocal(local) > 0) {
						avisausuario = "S";
					}
				}
			}
			rp[0] = avisausuario;
			return new ResponseEntity<String[]>(rp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
