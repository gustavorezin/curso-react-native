package com.midas.api.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.midas.api.security.ClienteParam;
import com.midas.api.security.ControleAutoridade;
import com.midas.api.security.RequestAuthorization;
import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcRoma;
import com.midas.api.tenant.entity.LcRomaItem;
import com.midas.api.tenant.repository.LcDocRepository;
import com.midas.api.tenant.repository.LcRomaCustomRepository;
import com.midas.api.tenant.repository.LcRomaItemRepository;
import com.midas.api.tenant.repository.LcRomaRepository;
import com.midas.api.util.CaracterUtil;

@RestController
@RequestMapping("/private/lcroma")
public class LcRomaController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private LcRomaRepository lcRomaRp;
	@Autowired
	private LcRomaCustomRepository lcCustomRp;
	@Autowired
	private LcRomaItemRepository lcRomaItemRp;
	@Autowired
	private LcDocRepository lcDocRp;
	@Autowired
	private ClienteParam prm;
	@Autowired
	private ControleAutoridade ca;

	// ROMA ****************************************
	@RequestAuthorization
	@GetMapping("/roma/{id}")
	public ResponseEntity<LcRoma> lcRoma(@PathVariable("id") Long id) throws Exception {
		Optional<LcRoma> obj = null;
		// Verifica se tem acesso a todos locais
		String aclocal = prm.cliente().getAclocal();
		if (aclocal.equals("S")) {
			obj = lcRomaRp.findById(id);
		} else {
			obj = lcRomaRp.findByIdAndCdpessoaemp(id, prm.cliente().getCdpessoaemp());
		}
		return new ResponseEntity<LcRoma>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/roma")
	public ResponseEntity<LcRoma> novoLcRoma(@RequestBody LcRoma lcRoma) throws Exception {
		LcRoma doc = null;
		if (ca.hasAuth(prm, 83, "N", lcRoma.getCdpessoatransp().getNome())) {
			Integer numero = 1;
			LcRoma roma = lcRomaRp.ultimoLcRoma(lcRoma.getTipo(), lcRoma.getCdpessoaemp().getId());
			if (roma != null) {
				numero = roma.getNumero() + 1;
			}
			lcRoma.setNumero(numero);
			doc = lcRomaRp.save(lcRoma);
			// Insere LcDocs
			int ordem = 1;
			for (LcRomaItem ri : lcRoma.getLcromaitem()) {
				LcRomaItem rie = new LcRomaItem();
				rie.setLcroma(doc);
				rie.setLcdoc(ri.getLcdoc());
				rie.setOrdem(ordem);
				lcRomaItemRp.save(rie);
				// Seta LcDoc
				lcDocRp.updateLcRomaDoc(lcRoma.getTipo(), doc.getId(), numero, "3", ri.getLcdoc().getId());
				ordem++;
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcRoma>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/roma")
	public ResponseEntity<LcRoma> atualizarLcRoma(@RequestBody LcRoma lcRoma) throws Exception {
		LcRoma doc = null;
		if (ca.hasAuth(prm, 83, "A", "ID " + lcRoma.getId() + " - " + lcRoma.getCdpessoatransp().getNome())) {
			// Reabre
			if (lcRoma.getStatus().equals("1")) {
				lcRoma.setMotcan("");
				lcRoma.setDataent(new Date(System.currentTimeMillis()));
				// Atualiza status entrega
				lcDocRp.updateLcDocEntregaStRoma("5", lcRoma.getId());
			}
			// Finaliza
			if (lcRoma.getStatus().equals("2")) {
				lcRoma.setMotcan("");
				lcRoma.setDataent(new Date(System.currentTimeMillis()));
				// Atualiza status entrega
				lcDocRp.updateLcDocEntregaStRoma("6", lcRoma.getId());
			}
			// Cancela
			if (lcRoma.getStatus().equals("3")) {
				// Remove docs
				lcDocRp.updateLcRomaDocTodos("7", lcRoma.getId());
				lcRoma.setDataent(new Date(System.currentTimeMillis()));
			}
			doc = lcRomaRp.save(lcRoma);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<LcRoma>(doc, HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@DeleteMapping("/roma/remover/{id}")
	public ResponseEntity<?> removerLcRoma(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 83, "R", "ID " + id)) {
			LcRoma doc = lcRomaRp.findById(id).get();
			// Remove docs
			lcDocRp.updateLcRomaDocTodos("2", id);
			lcRomaRp.delete(doc);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@GetMapping("/roma/tp/{tipo}/st/{st}")
	public ResponseEntity<List<LcRoma>> listaLcRomasStatusTipo(@PathVariable("tipo") String tipo,
			@PathVariable("st") String st) throws Exception {
		if (ca.hasAuth(prm, 83, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			Long local = 0L;
			String aclocal = prm.cliente().getAclocal();
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			List<LcRoma> lista = null;
			if (aclocal.equals("S")) {
				if (local > 0) {
					lista = lcRomaRp.listaRomaStatusLocal(local, tipo, st);
				} else {
					lista = lcRomaRp.listaRomaStatus(tipo, st);
				}
			} else {
				lista = lcRomaRp.listaRomaStatusLocal(prm.cliente().getCdpessoaemp(), tipo, st);
			}
			return new ResponseEntity<List<LcRoma>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/roma/p/{pagina}/b/{busca}/o/{ordem}/d/{ordemdir}/lc/{local}/transp/{transp}/tp/{tipo}/ipp/{itenspp}/"
			+ "dti/{dataini}/dtf/{datafim}/st/{st}")
	public ResponseEntity<Page<LcRoma>> listaLcRomasBusca(@PathVariable("pagina") int pagina,
			@PathVariable("busca") String busca, @PathVariable("ordem") String ordem,
			@PathVariable("ordemdir") String ordemdir, @PathVariable("local") Long local,
			@PathVariable("transp") Long transp, @PathVariable("tipo") String tipo,
			@PathVariable("itenspp") int itenspp, @PathVariable("dataini") String dataini,
			@PathVariable("datafim") String datafim, @PathVariable("st") String st) throws Exception {
		if (ca.hasAuth(prm, 24, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			// Ajuste pagina deve iniciar negativa
			pagina = pagina - 1;
			busca = CaracterUtil.buscaContexto(busca);
			Pageable pageable = PageRequest.of(pagina, itenspp);
			Page<LcRoma> lista = lcCustomRp.listaLcRomas(local, transp, tipo, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, ordem, ordemdir, st, pageable);
			return new ResponseEntity<Page<LcRoma>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestAuthorization
	@GetMapping("/roma/valores/b/{busca}/lc/{local}/transp/{transp}/tp/{tipo}/dti/{dataini}/dtf/{datafim}/st/{st}")
	public ResponseEntity<List<LcRoma>> listaLcRomasBuscaValores(@PathVariable("busca") String busca,
			@PathVariable("local") Long local, @PathVariable("transp") Long transp, @PathVariable("tipo") String tipo,
			@PathVariable("dataini") String dataini, @PathVariable("datafim") String datafim,
			@PathVariable("st") String st) throws Exception {
		if (ca.hasAuth(prm, 24, "L", "LISTAGEM / CONSULTA")) {
			// Verifica se tem acesso a todos locais
			if (prm.cliente().getAclocal().equals("N")) {
				local = prm.cliente().getCdpessoaemp();
			}
			busca = CaracterUtil.buscaContexto(busca);
			List<LcRoma> lista = lcCustomRp.listaLcRomasValores(local, transp, tipo, Date.valueOf(dataini),
					Date.valueOf(datafim), busca, st);
			return new ResponseEntity<List<LcRoma>>(lista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// ROMA ITEM ****************************************
	@RequestAuthorization
	@GetMapping("/romaitem/{id}")
	public ResponseEntity<LcRomaItem> lcRomaItem(@PathVariable("id") Long id) throws InterruptedException {
		Optional<LcRomaItem> obj = lcRomaItemRp.findById(id);
		return new ResponseEntity<LcRomaItem>(obj.get(), HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/romaitem/{id}")
	public ResponseEntity<?> addLcRomaItem(@PathVariable("id") Long id, @RequestBody LcRomaItem lcRomaItem)
			throws Exception {
		if (ca.hasAuth(prm, 83, "A", "ITEM " + lcRomaItem.getLcdoc().getNumero())) {
			// Seta LcDoc
			lcDocRp.updateLcRomaDoc(lcRomaItem.getLcroma().getTipo(), lcRomaItem.getLcroma().getId(),
					lcRomaItem.getLcroma().getNumero(), "3", id);
			lcRomaItemRp.save(lcRomaItem);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/romaitem/{id}")
	public ResponseEntity<?> removerlcRomaItem(@PathVariable("id") Long id, @RequestBody LcRomaItem lcRomaItem)
			throws Exception {
		if (ca.hasAuth(prm, 83, "R", "ID ITEM " + lcRomaItem.getLcdoc().getNumero())) {
			// Remove docs
			lcDocRp.updateLcRomaDoc("00", 0L, 0, "2", id);
			lcRomaItemRp.deleteById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/romaitem/iddoc/{iddoc}")
	public ResponseEntity<?> removerlcRomaItemDoc(@PathVariable("iddoc") Long iddoc) throws Exception {
		if (ca.hasAuth(prm, 83, "R", "ID DOC ITEM " + iddoc)) {
			LcRomaItem ri = lcRomaItemRp.findByLcDoc(iddoc);
			// Remove docs
			lcDocRp.updateLcRomaDoc("00", 0L, 0, "2", iddoc);
			lcRomaItemRp.deleteById(ri.getId());
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PutMapping("/romaitem/remtodos/{id}")
	public ResponseEntity<?> removerlcRomaItemTodos(@PathVariable("id") Long id) throws Exception {
		if (ca.hasAuth(prm, 83, "R", "ID ROMA " + id)) {
			// Remove docs
			lcDocRp.updateLcRomaDocTodos("2", id);
			lcRomaItemRp.removeItemDocTodos(id);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@GetMapping("/romaitem/atualizarordem/iditem/{iditem}/ordem/{ordem}")
	public ResponseEntity<?> atualziarlcRomaItemOrdem(@PathVariable("iditem") Long iditem,
			@PathVariable("ordem") Integer ordem) throws Exception {
		if (ca.hasAuth(prm, 83, "A", "ID ITEM ORDENACAO ROMA " + iditem)) {
			lcRomaItemRp.atualizaItemDocOrdem(ordem, iditem);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestAuthorization
	@Transactional("tenantTransactionManager")
	@PostMapping("/romaitem/anexadoc/idroma/{idroma}/iddoc/{iddoc}")
	public ResponseEntity<?> anexaDocRoma(@PathVariable("idroma") Long idroma, @PathVariable("iddoc") Long iddoc)
			throws Exception {
		if (ca.hasAuth(prm, 83, "A", "ID PEDIDO ANEXA ROMANEIO " + iddoc)) {
			// Anexar
			LcRoma r = lcRomaRp.findById(idroma).get();
			LcDoc d = lcDocRp.findById(iddoc).get();
			// Ultima sequencia
			Integer ordem = 1;
			LcRomaItem romaitem = lcRomaItemRp.ultimoLcRomaItem(idroma);
			if (romaitem != null) {
				ordem = romaitem.getOrdem() + 1;
			}
			LcRomaItem rie = new LcRomaItem();
			rie.setLcroma(r);
			rie.setLcdoc(d);
			rie.setOrdem(ordem);
			lcRomaItemRp.save(rie);
			// Romaneio para Doc
			lcDocRp.updateLcRomaDoc(r.getTipo(), r.getId(), r.getNumero(), "3", iddoc);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
