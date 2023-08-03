package com.midas.api.tenant.service.integra;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.midas.api.tenant.entity.CdClimbacfg;
import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.CdProdutoCat;
import com.midas.api.tenant.entity.CdProdutoCor;
import com.midas.api.tenant.entity.CdProdutoCorRel;
import com.midas.api.tenant.entity.CdProdutoGrupo;
import com.midas.api.tenant.entity.CdProdutoMarca;
import com.midas.api.tenant.entity.CdProdutoPreco;
import com.midas.api.tenant.entity.CdProdutoTam;
import com.midas.api.tenant.entity.CdProdutoTamRel;
import com.midas.api.tenant.entity.EsEst;
import com.midas.api.tenant.entity.climba.CdCadastrosClimba;
import com.midas.api.tenant.entity.climba.CdProdutoAttrClimba;
import com.midas.api.tenant.entity.climba.CdProdutoClimba;
import com.midas.api.tenant.entity.climba.CdProdutoPrecosClimba;
import com.midas.api.tenant.entity.climba.CdProdutoVariantesClimba;
import com.midas.api.tenant.entity.climba.LcPedidoClimba;
import com.midas.api.tenant.entity.climba.RetornosClimba;
import com.midas.api.tenant.repository.CdClimbacfgRepository;
import com.midas.api.tenant.repository.CdProdutoCorRelRepository;
import com.midas.api.tenant.repository.CdProdutoCorRepository;
import com.midas.api.tenant.repository.CdProdutoPrecoRepository;
import com.midas.api.tenant.repository.CdProdutoTamRelRepository;
import com.midas.api.tenant.repository.CdProdutoTamRepository;
import com.midas.api.util.HttpRequest;
import com.midas.api.util.LerArqUtil;
import com.midas.api.util.integra.ClimbaUtil;

@Service
public class ClimbaService {
	@Autowired
	private CdClimbacfgRepository cdClimbacfgRp;
	@Autowired
	private CdProdutoCorRelRepository cdProdutoCorRelRp;
	@Autowired
	private CdProdutoTamRelRepository cdProdutoTamRelRp;
	@Autowired
	private CdProdutoCorRepository cdProdutoCorRp;
	@Autowired
	private CdProdutoTamRepository cdProdutoTamRp;
	@Autowired
	private CdProdutoPrecoRepository cdProdutoPrecoRp;

	// CADASTROS PADROES****************************************************
	// Marcas---------------------------------------------------------------
	public String[] cadMarca(CdProdutoMarca cd, String hr) {
		String retorno[] = new String[3];
		retorno[0] = "201";
		// Se vai para o site ou nao------------------------------------------
		if (cd.getStatus_loja().equals("ATIVO")) {
			// Verifica todas as integracoes----------------------------------
			for (CdClimbacfg cl : cdClimbacfgRp.findAllByAtivos("ATIVO")) {
				String rota = "brands";
				if (hr.equals("PUT")) {
					rota = "brands/" + cd.getId();
				}
				CdCadastrosClimba c = new CdCadastrosClimba();
				c.setId(cd.getId() + "");
				c.setName(cd.getNome());
				c.setDescription(cd.getInfo() + "");
				// Converte objeto para json----------------------------------
				String json = LerArqUtil.setJsonString(c);
				HttpRequest results = ClimbaUtil.httpResquest(json, cl, rota, hr);
				String ret = results.body();
				RetornosClimba rets = new Gson().fromJson(ret, RetornosClimba.class);
				// Se erro----------------------------------------------------
				if (rets.getCode() != null) {
					retorno[0] = rets.getCode();
					retorno[1] = cl.getLoja_nome() + " - " + rets.getCode() + " | "
							+ ClimbaUtil.respClimba(rets.getCode());
					// Se nao encontrada, cadastra----------------------------
					if (rets.getCode().equals("2") || rets.getCode().equals("4")) {
						cadMarca(cd, "POST");
						retorno[0] = "201";
					}
				}
			}
		}
		return retorno;
	}

	// Tamanhos e cores----------------------------------------------------
	public String[] cadCorTam(CdProdutoCor cd, CdProdutoTam cdT, String hr, String attr) {
		String retorno[] = new String[3];
		retorno[0] = "201";
		// Verifica todas as integracoes----------------------------------
		for (CdClimbacfg cl : cdClimbacfgRp.findAllByAtivos("ATIVO")) {
			String rota = "attributes";
			if (hr.equals("PUT")) {
				if (cd != null) {
					rota = "attributes/" + attr + "/" + cd.getId();
				} else {
					rota = "attributes/" + attr + "/" + cdT.getId();
				}
			}
			CdCadastrosClimba c = new CdCadastrosClimba();
			c.setAttributeGroupId(attr);
			if (cd != null) {
				c.setId(cd.getId() + "");
				c.setName(cd.getCor());
			} else {
				c.setId(cdT.getId() + "");
				c.setName(cdT.getTamanho());
			}
			// Converte objeto para json----------------------------------
			String json = LerArqUtil.setJsonString(c);
			HttpRequest results = ClimbaUtil.httpResquest(json, cl, rota, hr);
			String ret = results.body();
			RetornosClimba rets = new Gson().fromJson(ret, RetornosClimba.class);
			// Se erro----------------------------------------------------
			if (rets.getCode() != null) {
				retorno[0] = rets.getCode();
				retorno[1] = cl.getLoja_nome() + " - " + rets.getCode() + " | " + ClimbaUtil.respClimba(rets.getCode());
				// Se nao encontrada, cadastra----------------------------
				if (rets.getCode().equals("2") || rets.getCode().equals("4")) {
					cadCorTam(cd, cdT, "POST", attr);
					retorno[0] = "201";
				}
			}
		}
		return retorno;
	}

	public String[] cadCategoria(CdProdutoCat cd, String hr) {
		String retorno[] = new String[3];
		retorno[0] = "201";
		// Se vai para o site ou nao------------------------------------------
		if (cd.getStatus_loja().equals("ATIVO")) {
			// Verifica todas as integracoes----------------------------------
			for (CdClimbacfg cl : cdClimbacfgRp.findAllByAtivos("ATIVO")) {
				String rota = "categories";
				if (hr.equals("PUT")) {
					rota = "categories/" + cd.getId();
				}
				CdCadastrosClimba c = new CdCadastrosClimba();
				c.setId(cd.getId() + "");
				c.setParentId("");
				c.setName(cd.getNome());
				c.setDescription(cd.getInfo() + "");
				c.setOrder("");
				// Converte objeto para json----------------------------------
				String json = LerArqUtil.setJsonString(c);
				HttpRequest results = ClimbaUtil.httpResquest(json, cl, rota, hr);
				String ret = results.body();
				RetornosClimba rets = new Gson().fromJson(ret, RetornosClimba.class);
				// Se erro----------------------------------------------------
				if (rets.getCode() != null) {
					retorno[0] = rets.getCode();
					retorno[1] = cl.getLoja_nome() + " - " + rets.getCode() + " | "
							+ ClimbaUtil.respClimba(rets.getCode());
					// Se nao encontrada, cadastra----------------------------
					if (rets.getCode().equals("2") || rets.getCode().equals("4")) {
						cadCategoria(cd, "POST");
						retorno[0] = "201";
					}
				}
			}
		}
		return retorno;
	}

	public String[] cadGrupo(CdProdutoGrupo cd, String hr) {
		String retorno[] = new String[3];
		retorno[0] = "201";
		// Se vai para o site ou nao------------------------------------------
		if (cd.getStatus_loja().equals("ATIVO")) {
			// Verifica todas as integracoes----------------------------------
			for (CdClimbacfg cl : cdClimbacfgRp.findAllByAtivos("ATIVO")) {
				String pID = cd.getCdprodutocat().getId() + "." + cd.getId();
				String rota = "categories";
				if (hr.equals("PUT")) {
					rota = "categories/" + pID;
				}
				CdCadastrosClimba c = new CdCadastrosClimba();
				c.setId(pID + "");
				c.setParentId(cd.getCdprodutocat().getId() + "");
				c.setName(cd.getNome());
				c.setDescription(cd.getInfo() + "");
				c.setOrder("");
				// Converte objeto para json----------------------------------
				String json = LerArqUtil.setJsonString(c);
				HttpRequest results = ClimbaUtil.httpResquest(json, cl, rota, hr);
				String ret = results.body();
				RetornosClimba rets = new Gson().fromJson(ret, RetornosClimba.class);
				// Se erro----------------------------------------------------
				if (rets.getCode() != null) {
					retorno[0] = rets.getCode();
					retorno[1] = cl.getLoja_nome() + " - " + rets.getCode() + " | "
							+ ClimbaUtil.respClimba(rets.getCode());
					// Se nao encontrada, cadastra----------------------------
					if (rets.getCode().equals("2") || rets.getCode().equals("4")) {
						cadGrupo(cd, "POST");
						retorno[0] = "201";
					}
				}
			}
		}
		return retorno;
	}

	// Produtos---------------------------------------------------------------
	public String[] cadProduto(CdProduto cd, String hr) {
		String retorno[] = new String[3];
		retorno[0] = "201";
		// Se vai para o site ou nao------------------------------------------
		if (cd.getStatus_loja().equals("ATIVO")) {
			// Verifica todas as integracoes----------------------------------
			for (CdClimbacfg cl : cdClimbacfgRp.findAllByAtivos("ATIVO")) {
				String rota = "products";
				if (hr.equals("PUT")) {
					rota = "products/" + cd.getId();
				}
				CdProdutoClimba c = new CdProdutoClimba();
				c.setId(cd.getId() + "");
				if (cd.getStatus().equals("ATIVO")) {
					c.setStatus("1");
				} else {
					c.setStatus("0");
				}
				List<String> categories = new ArrayList<String>();
				categories.add(cd.getCdprodutosubgrupo().getCdprodutogrupo().getCdprodutocat().getId() + "."
						+ cd.getCdprodutosubgrupo().getCdprodutogrupo().getId());
				c.setCategories(categories);
				c.setBrandId(cd.getCdprodutomarca().getId() + "");
				c.setName(cd.getNome());
				c.setDescription(cd.getDescricao() + "");
				c.setShortDescription(cd.getRef() + "");
				// Variantes------------------------------------------------
				List<CdProdutoVariantesClimba> pvs = new ArrayList<CdProdutoVariantesClimba>();
				CdProdutoVariantesClimba pv = new CdProdutoVariantesClimba();
				pv.setSku(cd.getCodigo() + "");
				pv.setManufacturerCode(cd.getCean() + "");
				List<CdProdutoAttrClimba> pas = new ArrayList<CdProdutoAttrClimba>();
				// Cores se houver------------------------------------------
				List<CdProdutoCorRel> cr = cdProdutoCorRelRp.findAllByIdProduto(cd.getId());
				for (CdProdutoCorRel cpr : cr) {
					CdProdutoCor cc = cdProdutoCorRp.findCorById(cpr.getCdprodutocor_id());
					if (cc != null) {
						CdProdutoAttrClimba pa = new CdProdutoAttrClimba();
						pa.setId(cc.getId() + "");
						pa.setName(cc.getCor());
						pa.setAttributeGroupId("1");
						pas.add(pa);
					}
				}
				// Tamanhos se houver---------------------------------------
				List<CdProdutoTamRel> ct = cdProdutoTamRelRp.findAllByIdProduto(cd.getId());
				for (CdProdutoTamRel cpr : ct) {
					CdProdutoTam cc = cdProdutoTamRp.findTamById(cpr.getCdprodutotam_id());
					if (cc != null) {
						CdProdutoAttrClimba pa = new CdProdutoAttrClimba();
						pa.setId(cc.getId() + "");
						pa.setName(cc.getTamanho());
						pa.setAttributeGroupId("2");
						pas.add(pa);
					}
				}
				pv.setAttributes(pas);
				pv.setQuantity(qtdEstoque(cd));
				pv.setTypeSalesUnit(cd.getCdprodutounmed().getNome());
				pv.setDescription("");
				// Medidas---------------------------------------------------
				Integer pesoB = cd.getMpesob().setScale(0, RoundingMode.HALF_UP).intValueExact();
				Integer pesoL = cd.getMpesol().setScale(0, RoundingMode.HALF_UP).intValueExact();
				Integer altura = cd.getMaltura().setScale(0, RoundingMode.HALF_UP).intValueExact();
				Integer largura = cd.getMlargura().setScale(0, RoundingMode.HALF_UP).intValueExact();
				Integer compr = cd.getMcomp().setScale(0, RoundingMode.HALF_UP).intValueExact();
				pv.setGrossWeight(pesoB);
				pv.setNetWeight(pesoL);
				pv.setHeight(altura);
				pv.setWidth(largura);
				pv.setLength(compr);
				pv.setBarCode(cd.getCean() + "");
				// Precos
				List<CdProdutoPrecosClimba> pres = new ArrayList<CdProdutoPrecosClimba>();
				List<CdProdutoPreco> precos = cdProdutoPrecoRp.findAllByProdutoStatusLoja(cd.getId(), "ATIVO");
				for (CdProdutoPreco ps : precos) {
					CdProdutoPrecosClimba pr = new CdProdutoPrecosClimba();
					pr.setPriceListId(ps.getCdprodutotab().getId() + "");
					pr.setPrice(valInteger(ps.getVvenda()));
					pr.setPriceFrom(0);
					pr.setCashPrice(valInteger(ps.getVvenda()));
					pres.add(pr);
				}
				pv.setPrices(pres);
				pvs.add(pv);
				c.setProductVariants(pvs);
				// Converte objeto para json----------------------------------
				String json = LerArqUtil.setJsonString(c);
				HttpRequest results = ClimbaUtil.httpResquest(json, cl, rota, hr);
				String ret = results.body();
				RetornosClimba rets = new Gson().fromJson(ret, RetornosClimba.class);
				// Se erro----------------------------------------------------
				if (rets.getCode() != null) {
					retorno[0] = rets.getCode();
					retorno[1] = cl.getLoja_nome() + " - " + rets.getCode() + " | "
							+ ClimbaUtil.respClimba(rets.getCode());
					// Se nao encontrada, cadastra----------------------------
					if (rets.getCode().equals("2") || rets.getCode().equals("4") || rets.getCode().equals("404")) {
						cadProduto(cd, "POST");
						retorno[0] = "201";
					}
				}
			}
		}
		return retorno;
	}

	public Page<LcPedidoClimba> listaPedidos(String dtini, String dtfim, String status, Integer perPage,
			Integer currentPage) {
		// Verifica todas as integracoes----------------------------------
		for (CdClimbacfg cl : cdClimbacfgRp.findAllByAtivos("ATIVO")) {
			String rota = "orders?dateStart=" + dtini + "%2000:00:00&dateEnd=" + dtfim + "%2000:00:00&perPage="
					+ perPage + "&currentPage=" + currentPage;
			if (!status.equals("0")) {
				rota = rota + "&status=" + status;
			}
			HttpRequest results = ClimbaUtil.httpResquest("", cl, rota, "GET");
			String ret = results.body();
			Sort sort = Sort.by(Sort.Direction.DESC, "ASC");
			Pageable pageable = PageRequest.of(currentPage, perPage, sort);
			RetornosClimba rets = new Gson().fromJson(ret, RetornosClimba.class);
			// Pages-------------------------------------------------------
			List<LcPedidoClimba> peds = rets.getData();
			int total = rets.getData().size();
			int toIndex = (pageable.getPageNumber() + 1) * pageable.getPageSize();
			int fromIndex = Math.max(toIndex - pageable.getPageSize(), 0);
			if (toIndex > total) {
				toIndex = total;
			}
			Page<LcPedidoClimba> pages = new PageImpl<LcPedidoClimba>(peds.subList(fromIndex, toIndex), pageable,
					total);
			return pages;
		}
		return null;
	}

	public String[] importaPedidoErp(String id) throws Exception {
		String retorno[] = new String[3];
		retorno[0] = "201";
		String rota = "orders/" + id;
		// Verifica todas as integracoes----------------------------------
		for (CdClimbacfg cl : cdClimbacfgRp.findAllByAtivos("ATIVO")) {
			String json = "{\"orderExportedToErp\": true }";
			java.net.http.HttpRequest results = ClimbaUtil.httpResquestPatch(json, cl, rota);
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(results, BodyHandlers.ofString());
			System.out.println(response.body());
			String ret = response.body();
			RetornosClimba rets = new Gson().fromJson(ret, RetornosClimba.class);
			// Se erro----------------------------------------------------
			if (rets.getCode() != null) {
				retorno[0] = rets.getCode();
				retorno[1] = cl.getLoja_nome() + " - " + rets.getCode() + " | " + ClimbaUtil.respClimba(rets.getCode());
			}
		}
		return retorno;
	}

	// Ajuste de estoque************************************
	private Integer qtdEstoque(CdProduto cd) {
		Integer retorno = 0;
		BigDecimal qtdEst = BigDecimal.ZERO;
		for (EsEst e : cd.getEsestitem()) {
			qtdEst = qtdEst.add(e.getQtd());
		}
		Integer qtd = qtdEst.setScale(0, RoundingMode.HALF_UP).intValueExact();
		retorno = qtd;
		return retorno;
	}

	// Ajuste de valores integer****************************
	private Integer valInteger(BigDecimal valor) {
		BigDecimal vConv = valor.setScale(2, RoundingMode.HALF_UP);
		String vConvS = (vConv + "").replace(".", "").replace(",", "");
		Integer retorno = Integer.valueOf(vConvS);
		return retorno;
	}
}
