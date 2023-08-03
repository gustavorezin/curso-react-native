package com.midas.api.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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

import com.midas.api.constant.MidasConfig;
import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.FsNfse;
import com.midas.api.tenant.entity.FsNfseCobrDup;
import com.midas.api.tenant.entity.FsNfseEvento;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.fiscal.GeraXMLNFSe;
import com.midas.api.tenant.fiscal.service.FsNfseEnvioService;
import com.midas.api.tenant.fiscal.service.FsNfseGerarService;
import com.midas.api.tenant.repository.FnTituloRepository;
import com.midas.api.tenant.repository.FsCustomRepository;
import com.midas.api.tenant.repository.FsNfseCobrDupRepository;
import com.midas.api.tenant.repository.FsNfseEventoRepository;
import com.midas.api.tenant.repository.FsNfseRepository;
import com.midas.api.tenant.repository.LcDocRepository;
import com.midas.api.tenant.service.EmailService;
import com.midas.api.util.CaracterUtil;
import com.midas.api.util.NumUtil;

@RestController
@RequestMapping("/private/fs")
public class FsNfseController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FsNfseRepository fsNfseRp;
	@Autowired
	private FsNfseCobrDupRepository fsNfseCobrDupRp;
	@Autowired
	private FsCustomRepository fsCustomRp;
	@Autowired
	private LcDocRepository lcDocRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;
	@Autowired
	private FsNfseEventoRepository fsNfseEventoRp;
	@Autowired
	private MidasConfig mc;
	@Autowired
	private EmailService emailService;
	@Autowired
	private FsNfseGerarService gerarNfse;
	@Autowired
	private FsNfseEnvioService fsNfseServiceEnvio;
	@Autowired
	private GeraXMLNFSe geraXMLNFSe;
	@Autowired
	private FnTituloRepository fnTituloRp;
	// TODO Autorizacoes incorretas, estao iguais a nfe

	// DOCUMENTOS FISCAIS DE SERVICO - NFSE**************************
	@RequestAuthorization
	@GetMapping("/nfse/{id}")
	public ResponseEntity<FsNfse> fsNfse(@PathVariable("id") Long id) {
		Optional<FsNfse> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = fsNfseRp.findById(id);
		} else {
			obj = fsNfseRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<FsNfse>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfse")
	public ResponseEntity<FsNfse> atualizarFsNfse(@RequestBody FsNfse fsnfse) throws Exception {
		FsNfse nfse = null;
		if (ca.hasAuth(prm, 18, "A", "ID " + fsnfse.getId() + " - " + fsnfse.getFsnfseparttoma().getXnome())) {
			fsnfse.setDemis(new Date(System.currentTimeMillis()));
			fsnfse.setDcompetencia(new Date(System.currentTimeMillis()));
			nfse = fsNfseRp.save(fsnfse);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<FsNfse>(nfse, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/nfse/{id}")
	public ResponseEntity<?> removerFsnfse(@PathVariable("id") Long id) {
		if (ca.hasAuth(prm, 16, "R", "ID " + id)) {
			fsNfseRp.deleteById(id);
			// Desvincula venda e titulos
			lcDocRp.updateDocFiscalNfseDocfiscal(0, 0L, id);
			// fnTituloRp.attParaDocCobDocFiscal("N", 0, "", 0L, id);//VER exemplo NFE
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/nfse/remover/emlote")
	public ResponseEntity<?> removerFsnfseEmLote(@RequestBody List<FsNfse> fsNfe) {
		if (ca.hasAuth(prm, 16, "R", "REMOCAO DE NOTAS FISCAIS DE SERVICO EM LOTE")) {
			for (FsNfse n : fsNfe) {
				fsNfseRp.deleteById(n.getId());
				// Desvincula venda e titulos
				lcDocRp.updateDocFiscalNfseDocfiscal(0, 0L, n.getId());
				// fnTituloRp.attParaDocCobDocFiscal("N", 0, "", 0L, n.getId());//VER exemplo
				// NFE
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@PostMapping("/nfse/addnfsedup")
	public ResponseEntity<?> addFsNfseDup(@RequestParam("id") Long id, @RequestParam("dvenc") String dvenc,
			@RequestParam("vdesc") BigDecimal vdesc, @RequestParam("vdup") BigDecimal vdup) throws Exception {
		if (ca.hasAuth(prm, 18, "N", "ID ADICIONAR DUPLICATA " + id + " - " + dvenc)) {
			FsNfse fsnfse = fsNfseRp.findById(id).get();
			List<FsNfseCobrDup> dups = fsnfse.getFsnfsecobr().getFsnfsecobrdups();
			int conta = dups.size() - 1;
			Integer numparc = 1;
			if (dups.size() > 0) {
				numparc = Integer.valueOf(dups.get(conta).getNdup()) + 1;
			}
			String parcela = NumUtil.geraNumEsq(numparc, 3);
			FsNfseCobrDup p = new FsNfseCobrDup();
			p.setFsnfsecobr(fsnfse.getFsnfsecobr());
			p.setNdup(parcela);
			p.setDvenc(Date.valueOf(dvenc));
			p.setVdesc(vdesc);
			p.setVdup(vdup);
			fsNfseCobrDupRp.save(p);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@DeleteMapping("/nfse/remnfsedup/{id}")
	public ResponseEntity<?> remFsNfseDup(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "R", "ID REMOVER DUPLICATA " + id)) {
			fsNfseCobrDupRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/nfse/p/{pagina}/b/{busca}/oensai/{oensai}/o/{ordem}/d/{ordemdir}/lc/{local}/tp/{tipo}/ipp/{itenspp}"
			+ "/dti/{dataini}/dtf/{datafim}/st/{st}/status/{status}")
	public ResponseEntity<Page<FsNfse>> listaFsNfsesBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("oensai") String oensai,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("local") Long local, @PathVariable("tipo") String tipo, @PathVariable("itenspp") int itenspp,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
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
			Page<FsNfse> lista = fsCustomRp.listaFsNfse(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo,
					busca, st, status, oensai, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<FsNfse>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfse/entrada/p/{pagina}/b/{busca}/oensai/{oensai}/o/{ordem}/d/{ordemdir}/lc/{local}/tp/{tipo}/ipp/{itenspp}"
			+ "/dti/{dataini}/dtf/{datafim}/st/{st}/status/{status}")
	public ResponseEntity<Page<FsNfse>> listaFsNfsesEntBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("oensai") String oensai,
			@PathVariable("ordem") String ordem, @PathVariable("ordemdir") String ordemdir,
			@PathVariable("local") Long local, @PathVariable("tipo") String tipo, @PathVariable("itenspp") int itenspp,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
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
			Page<FsNfse> lista = fsCustomRp.listaFsNfse(local, Date.valueOf(dataini), Date.valueOf(datafim), tipo,
					busca, st, status, oensai, ordem, ordemdir, pageable);
			return new ResponseEntity<Page<FsNfse>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfse/valores/b/{busca}/oensai/{oensai}/lc/{local}/tp/{tipo}/dti/{dataini}/dtf/{datafim}/st/{st}/status/{status}")
	public ResponseEntity<List<FsNfse>> listaFsNfsesValores(@PathVariable("busca") String busca,
			@PathVariable("oensai") String oensai, @PathVariable("local") Long local, @PathVariable("tipo") String tipo,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("st") String st, @PathVariable("status") Integer status) throws Exception {
		if (ca.hasAuth(prm, 43, "L", "LISTAGEM / CONSULTA VALORES")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<FsNfse> lista = fsCustomRp.listaFsNfseValores(local, Date.valueOf(dataini), Date.valueOf(datafim),
					tipo, busca, st, status, oensai);
			return new ResponseEntity<List<FsNfse>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/nfse/tp/{tipo}/status/{status}")
	public ResponseEntity<List<FsNfse>> listaNfseStatusTipo(@PathVariable("tipo") String tipo,
			@PathVariable("status") Integer status) throws Exception {
		if (ca.hasAuth(prm, 24, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			String aclocal = prm.cliente().getAclocal();
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			List<FsNfse> lista = null;
			if (aclocal.equals("S")) {
				if (local > 0) {
					lista = fsNfseRp.listaNfseStTipoLocal(local, tipo, status);
				} else {
					lista = fsNfseRp.listaNfseStTipo(tipo, status);
				}
			} else {
				lista = fsNfseRp.listaNfseStTipoLocal(prm.cliente().getCdpessoaemp(), tipo, status);
			}
			return new ResponseEntity<List<FsNfse>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfse/geranfse/local/{localprestserv}/op/{operacao}/fpag/{fpag}")
	public ResponseEntity<FsNfse> geraNfse(HttpServletRequest request,
			@PathVariable("localprestserv") String localprestserv, @PathVariable("operacao") String operacao,
			@PathVariable("fpag") String fpag, @RequestParam("iddoc") Long iddoc) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID Documento para NFS " + iddoc)) {
			Optional<LcDoc> lcDoc = lcDocRp.findById(iddoc);
			// Se nao foi emitido---------------
			if (lcDoc.get().getDocfiscal_nfse() == 0L) {
				String ambiente = prm.cliente().getSiscfg().getSis_amb_nfse();
				FsNfse nfse = gerarNfse.gerarNFSe(lcDoc.get(), ambiente, localprestserv, operacao, fpag);
				FsNfse n = fsNfseRp.findById(nfse.getId()).get();
				lcDocRp.updateDocFiscalNfse(nfse.getNnfs(), nfse.getId(), iddoc);
				
				return new ResponseEntity<FsNfse>(n, HttpStatus.OK);
			} else {
				return new ResponseEntity<FsNfse>(HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/nfse/envianfse/{id}")
	public ResponseEntity<String[]> enviarNfse(HttpServletRequest request, @PathVariable("id") Long id)
			throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID Envio NFSE " + id)) {
			FsNfse nfse = fsNfseRp.findById(id).get();
			
			String xml = geraXMLNFSe.geraXMLNFSe(nfse, mc);
			nfse.setXml(xml);
			String ret[] = fsNfseServiceEnvio.envioNFSe(nfse, xml/* , request */);
			// Se Autorizado------------------
			if (ret[0].equals("100")) {
				nfse.setStatus(100);
				nfse.setNnfs(Integer.valueOf(ret[2]));
				nfse.setCverifica(ret[3]);
				nfse.setXml(ret[4]);
				lcDocRp.updateDocFiscalNfseDocfiscal(nfse.getNnfs(), nfse.getId(), nfse.getId());
				LcDoc lcDoc = new LcDoc();
				// LcDoc - vinculos NFe-------
				if (nfse.getLcdoc() != null) {
					if (nfse.getLcdoc() > 0) {
						lcDoc = lcDocRp.findById(nfse.getLcdoc()).get();
						//Se nao houver NF-e
						if(!lcDoc.getTpdocfiscal().equals("55"))
						fnTituloRp.attParaCobDocFiscal("S", nfse.getNnfs(), "99", nfse.getId(),
								lcDoc.getId());
					}
				}
				
			}
			fsNfseRp.save(nfse);
			return new ResponseEntity<String[]>(ret, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfse/geranfsedireta/local/{localprestserv}/op/{operacao}/fpag/{fpag}")
	public ResponseEntity<String[]> geraNfseDireta(HttpServletRequest request,
			@PathVariable("localprestserv") String localprestserv, @PathVariable("operacao") String operacao,
			@PathVariable("fpag") String fpag, @RequestParam("iddoc") Long iddoc) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID Documento para NFS " + iddoc)) {
			Optional<LcDoc> lcDoc = lcDocRp.findById(iddoc);
			// Se nao foi emitido---------------
			if (lcDoc.get().getNumnota_nfse().equals(0)) {
				String ambiente = prm.cliente().getSiscfg().getSis_amb_nfse();
				FsNfse nfse = gerarNfse.gerarNFSe(lcDoc.get(), ambiente, localprestserv, operacao, fpag);
				String xml = geraXMLNFSe.geraXMLNFSe(nfse, mc);
				String ret[] = fsNfseServiceEnvio.envioNFSe(nfse, xml/* , request */);
				// Se Autorizado------------------
				if (ret[0].equals("100")) {
					nfse.setStatus(100);
					nfse.setNnfs(Integer.valueOf(ret[2]));
					nfse.setCverifica(ret[3]);
					nfse.setXml(ret[4]);
					lcDocRp.updateDocFiscalNfse(nfse.getNnfs(), nfse.getId(), iddoc);
					// LcDoc - vinculos NFe-------
					if (nfse.getLcdoc() != null) {
						if (nfse.getLcdoc() > 0) {
							//Se nao houver NF-e
							if(!lcDoc.get().getTpdocfiscal().equals("55"))
							fnTituloRp.attParaCobDocFiscal("S", nfse.getNnfs(), "99", nfse.getId(),
									lcDoc.get().getId());
						}
					}
					return new ResponseEntity<String[]>(ret, HttpStatus.OK);
				} else {
					fsNfseRp.deleteById(nfse.getId());
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
	@PostMapping("/nfse/cancelanfse")
	public ResponseEntity<String[]> cancelaNfse(HttpServletRequest request, @RequestParam("idnfse") Long idnfse,
			@RequestParam("justifica") String justifica) throws Exception {
		if (ca.hasAuth(prm, 18, "X", "ID CANCELAMENTO DE NF-E " + idnfse)) {
			FsNfse nfse = fsNfseRp.findById(idnfse).get();
			justifica = CaracterUtil.remUpper(justifica);
			if (nfse.getStatus() != 101) {
				String ret[] = fsNfseServiceEnvio.cancelaNFSe(nfse, justifica); // Ajusta situacao
				if (ret[0].equals("101")) {
					FsNfseEvento e = new FsNfseEvento();
					e.setFsnfse(nfse);
					e.setXjust(justifica);
					e.setXml(ret[3]);
					fsNfseEventoRp.save(e);
					fsNfseRp.updateXMLNFSeStatus(nfse.getXml(), 101, idnfse);
				}
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			} else {
				// Ja cancelado---------------------
				String ret[] = new String[2];
				ret[0] = "0";
				ret[1] = "Documento fiscal já cancelado anteriormente!";
				return new ResponseEntity<String[]>(ret, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/nfse/envioemail/{id}")
	public ResponseEntity<?> envioNFSeEmail(HttpServletRequest request, @PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID Documento para Envio por E-mail NFS-e " + id)) {
			emailService.enviaNFSeEmail(request, id, null);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/nfse/envioemail/{id}/email/{email}")
	public ResponseEntity<?> envioNFSeEmailOutros(HttpServletRequest request, @PathVariable("id") Long id,
			@PathVariable("email") String email) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "ID Documento para Envio por E-mail NFS-e " + id)) {
			emailService.enviaNFSeEmail(request, id, email);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/nfse/envioxmlemail")
	public ResponseEntity<?> envioNFSeEmailXML(HttpServletRequest request, @RequestParam("local") Long local,
			@RequestParam("dtini") String dtini, @RequestParam("dtfim") String dtfim,
			@RequestParam("dests") String dests, @RequestParam("info") String info,
			@RequestParam("enviopdf") String enviopdf, @RequestParam("tipo") String tipo) throws Exception {
		if (ca.hasAuth(prm, 18, "E", "XML de Arquivos de NFS-e para envio por E-mail " + dests)) {
			emailService.enviaNFSeEmailXML(request, local, Date.valueOf(dtini), Date.valueOf(dtfim), dests, info,
					enviopdf, tipo);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
