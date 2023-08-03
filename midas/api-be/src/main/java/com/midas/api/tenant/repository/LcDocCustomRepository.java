package com.midas.api.tenant.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcDocDTO;

@Repository
public class LcDocCustomRepository {
	private final EntityManager em;

	@Autowired
	public LcDocCustomRepository(@Qualifier("tenantEntityManagerFactory") EntityManager em) {
		this.em = em;
	}

	public Page<LcDocDTO> listaLcDocsDTO(List<LcDoc> lista, Pageable pageable) {
		List<LcDocDTO> dtos = new ArrayList<LcDocDTO>();
		for (LcDoc l : lista) {
			LcDocDTO dt = new LcDocDTO();
			dt.setId(l.getId());
			dt.setCdpessoapara(l.getCdpessoapara());
			dt.setCdpessoaemp(l.getCdpessoaemp());
			dt.setCdveiculo(l.getCdveiculo());
			dt.setNumero(l.getNumero());
			dt.setStatus(l.getStatus());
			dt.setDatafat(l.getDatafat());
			dt.setDataem(l.getDataem());
			dt.setVtottrib(l.getVtottrib());
			dt.setTipo(l.getTipo());
			dt.setCategoria(l.getCategoria());
			dt.setDescgeral(l.getDescgeral());
			dt.setAdminconf(l.getAdminconf());
			dt.setBoldoc(l.getBoldoc());
			dt.setEntregast(l.getEntregast());
			dt.setPriori(l.getPriori());
			dt.setTpdocfiscal(l.getTpdocfiscal());
			dt.setDocfiscal(l.getDocfiscal());
			dt.setNumnota(l.getNumnota());
			dt.setDocfiscal_nfse(l.getDocfiscal_nfse());
			dt.setNumnota_nfse(l.getNumnota_nfse());
			dt.setTproma(l.getTproma());
			dt.setLcroma(l.getLcroma());
			dt.setNumroma(l.getNumroma());
			dt.setLcdocorig(l.getLcdocorig());
			dt.setNumdocorig(l.getNumdocorig());
			dt.setLcdocdevo(l.getLcdocdevo());
			dt.setNumdocdevo(l.getNumdocdevo());
			dtos.add(dt);
		}
		int total = lista.size();
		int toIndex = (pageable.getPageNumber() + 1) * pageable.getPageSize();
		int fromIndex = Math.max(toIndex - pageable.getPageSize(), 0);
		if (toIndex > total) {
			toIndex = total;
		}
		Page<LcDocDTO> pages = new PageImpl<LcDocDTO>(dtos.subList(fromIndex, toIndex), pageable, total);
		return pages;
	}

	public List<LcDoc> listaLcDocs(Long local, Long para, Long vep, String tipo, String categoria, String ofe,
			Date dataini, Date datafim, String busca, Integer coduf, String ordem, String ordemdir, String status,
			Pageable pageable) {
		String query = retLcDoc(local, para, vep, tipo, categoria, ofe, dataini, datafim, busca, coduf, ordem, ordemdir,
				status);
		List<LcDoc> results = em.createQuery(query, LcDoc.class).getResultList();
		return results;
	}

	public List<LcDoc> listaLcDocExcel(Long local, Long para, Long vep, String tipo, String categoria, String ofe,
			Date dataini, Date datafim, String busca, Integer coduf, String ordem, String ordemdir, String status) {
		String query = retLcDoc(local, para, vep, tipo, categoria, ofe, dataini, datafim, busca, coduf, ordem, ordemdir,
				status);
		List<LcDoc> results = em.createQuery(query, LcDoc.class).getResultList();
		return results;
	}

	public List<LcDoc> listaLcDocsValores(Long local, Long para, Long vep, String tipo, String categoria, String ofe,
			Date dataini, Date datafim, String busca, Integer coduf, String status) {
		String query = retLcDoc(local, para, vep, tipo, categoria, ofe, dataini, datafim, busca, coduf, "id", "ASC",
				status);
		List<LcDoc> results = em.createQuery(query, LcDoc.class).getResultList();
		return results;
	}

	private String retLcDoc(Long local, Long para, Long vep, String tipo, String categoria, String ofe, Date dataini,
			Date datafim, String busca, Integer coduf, String ordem, String ordemdir, String status) {
		String query = "SELECT c FROM LcDoc c WHERE ";
		// Local
		if (local > 0) {
			query += "c.cdpessoaemp = " + local + " AND ";
		}
		// Para - cliente
		if (para > 0) {
			query += "c.cdpessoapara = " + para + " AND ";
		}
		// Estado
		if (coduf > 0) {
			query += "c.cdpessoapara.cdestado.id = " + coduf + " AND ";
		}
		// Vendedor
		if (vep > 0) {
			query += "c.cdpessoavendedor = " + vep + " AND ";
		}
		// Status
		if (!status.equals("0")) {
			query += "c.status = '" + status + "' AND ";
		}
		// Categoria
		if (!categoria.equals("00")) {
			query += "c.categoria = '" + categoria + "' AND ";
		}
		// Datas
		if (ofe.equals("E")) {
			query += "c.dataem BETWEEN '" + dataini + "' AND '" + datafim + "'";
		}
		if (ofe.equals("F")) {
			query += "c.datafat BETWEEN '" + dataini + "' AND '" + datafim + "'";
		}
		// Tipo
		if (!tipo.equals("XX")) {
			query += " AND c.tipo = '" + tipo + "' ";
		}
		// Busca
		if (!busca.equals("") && !busca.equals("undefined")) {
			query += " AND (CONVERT(c.id, CHAR) LIKE '%" + busca + "%' OR c.cdpessoapara.cpfcnpj LIKE '%" + busca
					+ "%' OR c.cdpessoapara.nome LIKE '%" + busca + "%' OR c.ordemcps LIKE '%" + busca
					+ "%' OR c.info LIKE '%" + busca + "%' " + " OR c.motcan LIKE '%" + busca
					+ "%' OR c.infolocal LIKE '%" + busca + "%'" + " OR c.numero LIKE '%" + busca
					+ "%' OR c.cdveiculo.placa LIKE '%" + busca + "%')";
		}
		// Ordem
		query += "ORDER BY c." + ordem + " " + ordemdir;
		return query;
	}
}
