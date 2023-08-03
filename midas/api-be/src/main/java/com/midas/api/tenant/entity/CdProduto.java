package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_produto")
public class CdProduto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer codigo;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@Column(length = 20)
	private String tipo;
	@Column(length = 120)
	private String nome;
	@Column(length = 1000)
	private String descricao;
	@Column(length = 60)
	private String ref;
	@Column(length = 8)
	private String ncm;
	@Column(length = 7)
	private String cest;
	@Column(length = 5)
	private String codserv;
	@Column(columnDefinition = "TEXT")
	private String infoncmserv;
	@Column(length = 14)
	private String cean;
	@Column(length = 14)
	private String ceantrib;
	@ManyToOne
	private CdProdutoUnmed cdprodutounmed;
	@ManyToOne
	private CdProdutoUnmed cdprodutounmedtrib;
	@ManyToOne
	private CdProdutoSubgrupo cdprodutosubgrupo;
	@ManyToOne
	private CdProdutoMarca cdprodutomarca;
	@Column(length = 5)
	private String mtipomedida;
	@Digits(integer = 10, fraction = 4)
	private BigDecimal maltura = BigDecimal.ZERO;
	@Digits(integer = 10, fraction = 4)
	private BigDecimal mcomp = BigDecimal.ZERO;
	@Digits(integer = 10, fraction = 4)
	private BigDecimal mlargura = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal mquad = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal mcub = BigDecimal.ZERO;
	@Column(length = 5)
	private String mtipopeso;
	@Digits(integer = 10, fraction = 4)
	private BigDecimal mpesol = BigDecimal.ZERO;
	@Digits(integer = 10, fraction = 4)
	private BigDecimal mpesob = BigDecimal.ZERO;
	@Digits(integer = 10, fraction = 4)
	private BigDecimal mpesoa = BigDecimal.ZERO;
	@Column(length = 3000)
	private String info;
	@Column(length = 2)
	private String origem;
	@Column(length = 9)
	private String codanp;
	@Column(length = 95)
	private String descanp;
	@Column(length = 12)
	private String cbenef;
	@Column(length = 2)
	private String tipoitem;
	@Lob
	private byte[] imagem;
	@Column(length = 12)
	private String codcontac;
	@Column(length = 20)
	private String codalt = "";
	@Column(length = 1)
	private String indescala = "X";
	@Column(length = 20)
	private String cnpjfab = "00000000000000";
	@Column(length = 8)
	private String status_loja = "INATIVO";
	@Column(length = 8)
	private String status;
	@JsonIgnoreProperties(value = { "cdproduto" }, allowSetters = true)
	@OneToMany(mappedBy = "cdproduto", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<EsEst> esestitem = new ArrayList<EsEst>();
	@JsonIgnoreProperties(value = { "cdproduto" }, allowSetters = true)
	@OneToMany(mappedBy = "cdproduto", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<CdProdutoLocal> cdprodutolocalitem = new ArrayList<CdProdutoLocal>();
	@JsonIgnoreProperties(value = { "cdproduto" }, allowSetters = true)
	@OneToMany(mappedBy = "cdproduto", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<CdProdutoDre> cdprodutodreitem = new ArrayList<CdProdutoDre>();
	@JsonIgnoreProperties(value = { "cdproduto" }, allowSetters = true)
	@OneToMany(mappedBy = "cdproduto", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<CdProdutoNsRel> cdprodutonsrelitem = new ArrayList<CdProdutoNsRel>();
	// Valores nao inseridos no DB - apenas visualizacao
	@Transient
	private BigDecimal valorunit = new BigDecimal(0);
	@Transient
	private BigDecimal qtdunit = new BigDecimal(0);

	public CdProduto() {
	}

	public CdProduto(Long id, Integer codigo, Date datacad, Date dataat, String tipo, String nome, String descricao,
			String ref, String ncm, String cest, String codserv, String infoncmserv, String cean, String ceantrib,
			CdProdutoUnmed cdprodutounmed, CdProdutoUnmed cdprodutounmedtrib, CdProdutoSubgrupo cdprodutosubgrupo,
			CdProdutoMarca cdprodutomarca, String mtipomedida, @Digits(integer = 10, fraction = 4) BigDecimal maltura,
			@Digits(integer = 10, fraction = 4) BigDecimal mcomp,
			@Digits(integer = 10, fraction = 4) BigDecimal mlargura,
			@Digits(integer = 16, fraction = 4) BigDecimal mquad, @Digits(integer = 16, fraction = 4) BigDecimal mcub,
			String mtipopeso, @Digits(integer = 10, fraction = 4) BigDecimal mpesol,
			@Digits(integer = 10, fraction = 4) BigDecimal mpesob,
			@Digits(integer = 10, fraction = 4) BigDecimal mpesoa, String info, String origem, String codanp,
			String descanp, String cbenef, String tipoitem, byte[] imagem, String codcontac, String codalt,
			String indescala, String cnpjfab, String status_loja, String status, List<EsEst> esestitem,
			List<CdProdutoLocal> cdprodutolocalitem, List<CdProdutoDre> cdprodutodreitem,
			List<CdProdutoNsRel> cdprodutonsrelitem, BigDecimal valorunit, BigDecimal qtdunit) {
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
		this.cdprodutounmedtrib = cdprodutounmedtrib;
		this.cdprodutosubgrupo = cdprodutosubgrupo;
		this.cdprodutomarca = cdprodutomarca;
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
		this.status_loja = status_loja;
		this.status = status;
		this.esestitem = esestitem;
		this.cdprodutolocalitem = cdprodutolocalitem;
		this.cdprodutodreitem = cdprodutodreitem;
		this.cdprodutonsrelitem = cdprodutonsrelitem;
		this.valorunit = valorunit;
		this.qtdunit = qtdunit;
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

	public CdProdutoUnmed getCdprodutounmedtrib() {
		return cdprodutounmedtrib;
	}

	public void setCdprodutounmedtrib(CdProdutoUnmed cdprodutounmedtrib) {
		this.cdprodutounmedtrib = cdprodutounmedtrib;
	}

	public CdProdutoSubgrupo getCdprodutosubgrupo() {
		return cdprodutosubgrupo;
	}

	public void setCdprodutosubgrupo(CdProdutoSubgrupo cdprodutosubgrupo) {
		this.cdprodutosubgrupo = cdprodutosubgrupo;
	}

	public CdProdutoMarca getCdprodutomarca() {
		return cdprodutomarca;
	}

	public void setCdprodutomarca(CdProdutoMarca cdprodutomarca) {
		this.cdprodutomarca = cdprodutomarca;
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

	public String getStatus_loja() {
		return status_loja;
	}

	public void setStatus_loja(String status_loja) {
		this.status_loja = status_loja;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<EsEst> getEsestitem() {
		return esestitem;
	}

	public void setEsestitem(List<EsEst> esestitem) {
		this.esestitem = esestitem;
	}

	public List<CdProdutoLocal> getCdprodutolocalitem() {
		return cdprodutolocalitem;
	}

	public void setCdprodutolocalitem(List<CdProdutoLocal> cdprodutolocalitem) {
		this.cdprodutolocalitem = cdprodutolocalitem;
	}

	public List<CdProdutoDre> getCdprodutodreitem() {
		return cdprodutodreitem;
	}

	public void setCdprodutodreitem(List<CdProdutoDre> cdprodutodreitem) {
		this.cdprodutodreitem = cdprodutodreitem;
	}

	public List<CdProdutoNsRel> getCdprodutonsrelitem() {
		return cdprodutonsrelitem;
	}

	public void setCdprodutonsrelitem(List<CdProdutoNsRel> cdprodutonsrelitem) {
		this.cdprodutonsrelitem = cdprodutonsrelitem;
	}

	public BigDecimal getValorunit() {
		return valorunit;
	}

	public void setValorunit(BigDecimal valorunit) {
		this.valorunit = valorunit;
	}

	public BigDecimal getQtdunit() {
		return qtdunit;
	}

	public void setQtdunit(BigDecimal qtdunit) {
		this.qtdunit = qtdunit;
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
		CdProduto other = (CdProduto) obj;
		return Objects.equals(id, other.id);
	}
}
