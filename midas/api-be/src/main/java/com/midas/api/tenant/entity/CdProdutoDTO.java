package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;

import com.midas.api.util.CaracterUtil;

public class CdProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer codigo;
	private Date datacad;
	private Date dataat;
	private String tipo;
	private String nome;
	private String descricao;
	private String ref;
	private String ncm;
	private String cest;
	private String codserv;
	private String infoncmserv;
	private String cean;
	private String ceantrib;
	private CdProdutoUnmed cdprodutounmed;
	private String mtipomedida;
	private BigDecimal maltura = BigDecimal.ZERO;
	private BigDecimal mcomp = BigDecimal.ZERO;
	private BigDecimal mlargura = BigDecimal.ZERO;
	private BigDecimal mquad = BigDecimal.ZERO;
	private BigDecimal mcub = BigDecimal.ZERO;
	private String mtipopeso;
	private BigDecimal mpesol = BigDecimal.ZERO;
	private BigDecimal mpesob = BigDecimal.ZERO;
	private BigDecimal mpesoa = BigDecimal.ZERO;
	private String info;
	private String origem;
	private String codanp;
	private String descanp;
	private String cbenef;
	private String tipoitem;
	private byte[] imagem;
	private String codcontac;
	private String codalt = "";
	private String indescala = "X";
	private String cnpjfab = "00000000000000";
	@Column(length = 8)
	private String status;

	public CdProdutoDTO() {
	}

	public CdProdutoDTO(Long id, Integer codigo, Date datacad, Date dataat, String tipo, String nome, String descricao,
			String ref, String ncm, String cest, String codserv, String infoncmserv, String cean, String ceantrib,
			CdProdutoUnmed cdprodutounmed, CdProdutoUnmed cdprodutounmedtrib, CdProdutoSubgrupo cdprodutosubgrupo,
			CdProdutoMarca cdprodutomarca, String mtipomedida, BigDecimal maltura, BigDecimal mcomp,
			BigDecimal mlargura, BigDecimal mquad, BigDecimal mcub, String mtipopeso, BigDecimal mpesol,
			BigDecimal mpesob, BigDecimal mpesoa, String info, String origem, String codanp, String descanp,
			String cbenef, String tipoitem, byte[] imagem, String codcontac, String codalt, String indescala,
			String cnpjfab, String status) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.datacad = datacad;
		this.dataat = dataat;
		this.tipo = tipo;
		this.nome = nome;
		this.descricao = descricao;
		this.ref = ref;
		this.ncm = ncm;
		this.cest = cest;
		this.codserv = codserv;
		this.infoncmserv = infoncmserv;
		this.cean = cean;
		this.ceantrib = ceantrib;
		this.cdprodutounmed = cdprodutounmed;
		this.mtipomedida = mtipomedida;
		this.maltura = maltura;
		this.mcomp = mcomp;
		this.mlargura = mlargura;
		this.mquad = mquad;
		this.mcub = mcub;
		this.mtipopeso = mtipopeso;
		this.mpesol = mpesol;
		this.mpesob = mpesob;
		this.mpesoa = mpesoa;
		this.info = info;
		this.origem = origem;
		this.codanp = codanp;
		this.descanp = descanp;
		this.cbenef = cbenef;
		this.tipoitem = tipoitem;
		this.imagem = imagem;
		this.codcontac = codcontac;
		this.codalt = codalt;
		this.indescala = indescala;
		this.cnpjfab = cnpjfab;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public Date getDataat() {
		return dataat;
	}

	public void setDataat(Date dataat) {
		this.dataat = dataat;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = CaracterUtil.remUpper(descricao);
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = CaracterUtil.remUpper(ref);
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getCest() {
		return cest;
	}

	public void setCest(String cest) {
		this.cest = cest;
	}

	public String getCodserv() {
		return codserv;
	}

	public void setCodserv(String codserv) {
		this.codserv = codserv;
	}

	public String getInfoncmserv() {
		return infoncmserv;
	}

	public void setInfoncmserv(String infoncmserv) {
		this.infoncmserv = CaracterUtil.remUpper(infoncmserv);
	}

	public String getCean() {
		return cean;
	}

	public void setCean(String cean) {
		this.cean = cean;
	}

	public String getCeantrib() {
		return ceantrib;
	}

	public void setCeantrib(String ceantrib) {
		this.ceantrib = ceantrib;
	}

	public CdProdutoUnmed getCdprodutounmed() {
		return cdprodutounmed;
	}

	public void setCdprodutounmed(CdProdutoUnmed cdprodutounmed) {
		this.cdprodutounmed = cdprodutounmed;
	}

	public String getMtipomedida() {
		return mtipomedida;
	}

	public void setMtipomedida(String mtipomedida) {
		this.mtipomedida = mtipomedida;
	}

	public BigDecimal getMaltura() {
		return maltura;
	}

	public void setMaltura(BigDecimal maltura) {
		this.maltura = maltura;
	}

	public BigDecimal getMcomp() {
		return mcomp;
	}

	public void setMcomp(BigDecimal mcomp) {
		this.mcomp = mcomp;
	}

	public BigDecimal getMlargura() {
		return mlargura;
	}

	public void setMlargura(BigDecimal mlargura) {
		this.mlargura = mlargura;
	}

	public BigDecimal getMquad() {
		return mquad;
	}

	public void setMquad(BigDecimal mquad) {
		this.mquad = mquad;
	}

	public BigDecimal getMcub() {
		return mcub;
	}

	public void setMcub(BigDecimal mcub) {
		this.mcub = mcub;
	}

	public String getMtipopeso() {
		return mtipopeso;
	}

	public void setMtipopeso(String mtipopeso) {
		this.mtipopeso = mtipopeso;
	}

	public BigDecimal getMpesol() {
		return mpesol;
	}

	public void setMpesol(BigDecimal mpesol) {
		this.mpesol = mpesol;
	}

	public BigDecimal getMpesob() {
		return mpesob;
	}

	public void setMpesob(BigDecimal mpesob) {
		this.mpesob = mpesob;
	}

	public BigDecimal getMpesoa() {
		return mpesoa;
	}

	public void setMpesoa(BigDecimal mpesoa) {
		this.mpesoa = mpesoa;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = CaracterUtil.remUpper(info);
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getCodanp() {
		return codanp;
	}

	public void setCodanp(String codanp) {
		this.codanp = codanp;
	}

	public String getDescanp() {
		return descanp;
	}

	public void setDescanp(String descanp) {
		this.descanp = CaracterUtil.remUpper(descanp);
	}

	public String getCbenef() {
		return cbenef;
	}

	public void setCbenef(String cbenef) {
		this.cbenef = CaracterUtil.remUpper(cbenef);
	}

	public String getTipoitem() {
		return tipoitem;
	}

	public void setTipoitem(String tipoitem) {
		this.tipoitem = tipoitem;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getCodcontac() {
		return codcontac;
	}

	public void setCodcontac(String codcontac) {
		this.codcontac = codcontac;
	}

	public String getCodalt() {
		return codalt;
	}

	public void setCodalt(String codalt) {
		this.codalt = codalt;
	}

	public String getIndescala() {
		return indescala;
	}

	public void setIndescala(String indescala) {
		this.indescala = indescala;
	}

	public String getCnpjfab() {
		return cnpjfab;
	}

	public void setCnpjfab(String cnpjfab) {
		this.cnpjfab = cnpjfab;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdProdutoDTO other = (CdProdutoDTO) obj;
		return Objects.equals(id, other.id);
	}
}
