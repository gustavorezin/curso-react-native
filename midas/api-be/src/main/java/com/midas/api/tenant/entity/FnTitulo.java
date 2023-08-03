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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fn_titulo")
public class FnTitulo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private CdPessoa cdpessoaemp;
	@Column(length = 2)
	private String tipo;
	@ManyToOne
	private CdPessoa cdpessoapara;
	@ManyToOne
	private CdCaixa cdcaixapref;
	@Column(length = 2)
	private String fpagpref;
	@Column(length = 80)
	private String ref;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date vence = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date databaixa = new Date();
	@Column(length = 2)
	private String tpdoc;
	private Long lcdoc;
	private Long numdoc = 0L;
	@Column(length = 2)
	private String tpdocfiscal;
	private Long docfiscal;
	private Integer numnota = 0;
	private Integer qtdparc;
	private Integer parcnum;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vparc = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vicmsst = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vipi = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vfcpst = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vfrete = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vdesc = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vjuro = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vtot = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vpago = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vsaldo = BigDecimal.ZERO;
	@Column(length = 500)
	private String info;
	@Column(length = 1)
	private String cred = "N";
	@Column(length = 1)
	private String negocia = "N";
	@Column(length = 1)
	private String troco = "N";
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vcredsaldo = BigDecimal.ZERO;
	private Integer transacao;
	@Column(length = 1)
	private String cobauto = "N";
	@Column(length = 1)
	private String pgtoprogramado = "N";
	@Column(length = 20)
	private String tecno_idintegracao = null;
	@Column(length = 1)
	private String comissao = "N";
	private Long fntitulo_com = 0L;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vbccom = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pcom = BigDecimal.ZERO;
	@Column(length = 1)
	private String paracob;
	@JsonIgnoreProperties(value = { "fntitulo" }, allowSetters = true)
	@OneToMany(mappedBy = "fntitulo", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FnTituloRel> fntitulorelitem = new ArrayList<FnTituloRel>();
	@JsonIgnoreProperties(value = { "fntitulo" }, allowSetters = true)
	@OneToMany(mappedBy = "fntitulo", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FnTituloDre> fntitulodreitem = new ArrayList<FnTituloDre>();
	@JsonIgnoreProperties(value = { "fntitulo" }, allowSetters = true)
	@OneToMany(mappedBy = "fntitulo", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FnTituloCcusto> fntituloccustoitem = new ArrayList<FnTituloCcusto>();

	public FnTitulo() {
	}

	public FnTitulo(Long id, CdPessoa cdpessoaemp, String tipo, CdPessoa cdpessoapara, CdCaixa cdcaixapref,
			String fpagpref, String ref, Date datacad, Date dataat, Date vence, Date databaixa, String tpdoc,
			Long lcdoc, Long numdoc, String tpdocfiscal, Long docfiscal, Integer numnota, Integer qtdparc,
			Integer parcnum, @Digits(integer = 18, fraction = 2) BigDecimal vparc,
			@Digits(integer = 12, fraction = 2) BigDecimal vicmsst, @Digits(integer = 12, fraction = 2) BigDecimal vipi,
			@Digits(integer = 12, fraction = 2) BigDecimal vfcpst,
			@Digits(integer = 12, fraction = 2) BigDecimal vfrete, @Digits(integer = 18, fraction = 2) BigDecimal vdesc,
			@Digits(integer = 18, fraction = 2) BigDecimal vjuro, @Digits(integer = 18, fraction = 2) BigDecimal vtot,
			@Digits(integer = 18, fraction = 2) BigDecimal vpago, @Digits(integer = 18, fraction = 2) BigDecimal vsaldo,
			String info, String cred, String negocia, String troco,
			@Digits(integer = 18, fraction = 2) BigDecimal vcredsaldo, Integer transacao, String cobauto,
			String pgtoprogramado, String tecno_idintegracao, String comissao, Long fntitulo_com,
			@Digits(integer = 18, fraction = 2) BigDecimal vbccom, @Digits(integer = 3, fraction = 2) BigDecimal pcom,
			String paracob, List<FnTituloRel> fntitulorelitem, List<FnTituloDre> fntitulodreitem,
			List<FnTituloCcusto> fntituloccustoitem) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.tipo = tipo;
		this.cdpessoapara = cdpessoapara;
		this.cdcaixapref = cdcaixapref;
		this.fpagpref = fpagpref;
		this.ref = ref;
		this.datacad = datacad;
		this.dataat = dataat;
		this.vence = vence;
		this.databaixa = databaixa;
		this.tpdoc = tpdoc;
		this.lcdoc = lcdoc;
		this.numdoc = numdoc;
		this.tpdocfiscal = tpdocfiscal;
		this.docfiscal = docfiscal;
		this.numnota = numnota;
		this.qtdparc = qtdparc;
		this.parcnum = parcnum;
		this.vparc = vparc;
		this.vicmsst = vicmsst;
		this.vipi = vipi;
		this.vfcpst = vfcpst;
		this.vfrete = vfrete;
		this.vdesc = vdesc;
		this.vjuro = vjuro;
		this.vtot = vtot;
		this.vpago = vpago;
		this.vsaldo = vsaldo;
		this.info = info;
		this.cred = cred;
		this.negocia = negocia;
		this.troco = troco;
		this.vcredsaldo = vcredsaldo;
		this.transacao = transacao;
		this.cobauto = cobauto;
		this.pgtoprogramado = pgtoprogramado;
		this.tecno_idintegracao = tecno_idintegracao;
		this.comissao = comissao;
		this.fntitulo_com = fntitulo_com;
		this.vbccom = vbccom;
		this.pcom = pcom;
		this.paracob = paracob;
		this.fntitulorelitem = fntitulorelitem;
		this.fntitulodreitem = fntitulodreitem;
		this.fntituloccustoitem = fntituloccustoitem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public CdPessoa getCdpessoapara() {
		return cdpessoapara;
	}

	public void setCdpessoapara(CdPessoa cdpessoapara) {
		this.cdpessoapara = cdpessoapara;
	}

	public CdCaixa getCdcaixapref() {
		return cdcaixapref;
	}

	public void setCdcaixapref(CdCaixa cdcaixapref) {
		this.cdcaixapref = cdcaixapref;
	}

	public String getFpagpref() {
		return fpagpref;
	}

	public void setFpagpref(String fpagpref) {
		this.fpagpref = fpagpref;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = CaracterUtil.remUpper(ref);
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

	public Date getVence() {
		return vence;
	}

	public Date getDatabaixa() {
		return databaixa;
	}

	public void setDatabaixa(Date databaixa) {
		this.databaixa = databaixa;
	}

	public void setVence(Date vence) {
		this.vence = vence;
	}

	public String getTpdoc() {
		return tpdoc;
	}

	public void setTpdoc(String tpdoc) {
		this.tpdoc = tpdoc;
	}

	public Long getLcdoc() {
		return lcdoc;
	}

	public void setLcdoc(Long lcdoc) {
		this.lcdoc = lcdoc;
	}

	public Long getNumdoc() {
		return numdoc;
	}

	public void setNumdoc(Long numdoc) {
		this.numdoc = numdoc;
	}

	public String getTpdocfiscal() {
		return tpdocfiscal;
	}

	public void setTpdocfiscal(String tpdocfiscal) {
		this.tpdocfiscal = tpdocfiscal;
	}

	public Long getDocfiscal() {
		return docfiscal;
	}

	public void setDocfiscal(Long docfiscal) {
		this.docfiscal = docfiscal;
	}

	public Integer getNumnota() {
		return numnota;
	}

	public void setNumnota(Integer numnota) {
		this.numnota = numnota;
	}

	public Integer getQtdparc() {
		return qtdparc;
	}

	public void setQtdparc(Integer qtdparc) {
		this.qtdparc = qtdparc;
	}

	public Integer getParcnum() {
		return parcnum;
	}

	public void setParcnum(Integer parcnum) {
		this.parcnum = parcnum;
	}

	public BigDecimal getVparc() {
		return vparc;
	}

	public void setVparc(BigDecimal vparc) {
		this.vparc = vparc;
	}

	public BigDecimal getVicmsst() {
		return vicmsst;
	}

	public void setVicmsst(BigDecimal vicmsst) {
		this.vicmsst = vicmsst;
	}

	public BigDecimal getVipi() {
		return vipi;
	}

	public void setVipi(BigDecimal vipi) {
		this.vipi = vipi;
	}

	public BigDecimal getVfcpst() {
		return vfcpst;
	}

	public void setVfcpst(BigDecimal vfcpst) {
		this.vfcpst = vfcpst;
	}

	public BigDecimal getVfrete() {
		return vfrete;
	}

	public void setVfrete(BigDecimal vfrete) {
		this.vfrete = vfrete;
	}

	public BigDecimal getVdesc() {
		return vdesc;
	}

	public void setVdesc(BigDecimal vdesc) {
		this.vdesc = vdesc;
	}

	public BigDecimal getVjuro() {
		return vjuro;
	}

	public void setVjuro(BigDecimal vjuro) {
		this.vjuro = vjuro;
	}

	public BigDecimal getVtot() {
		return vtot;
	}

	public void setVtot(BigDecimal vtot) {
		this.vtot = vtot;
	}

	public BigDecimal getVpago() {
		return vpago;
	}

	public void setVpago(BigDecimal vpago) {
		this.vpago = vpago;
	}

	public BigDecimal getVsaldo() {
		return vsaldo;
	}

	public void setVsaldo(BigDecimal vsaldo) {
		this.vsaldo = vsaldo;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = CaracterUtil.remUpper(info);
	}

	public String getCred() {
		return cred;
	}

	public void setCred(String cred) {
		this.cred = cred;
	}

	public String getNegocia() {
		return negocia;
	}

	public void setNegocia(String negocia) {
		this.negocia = negocia;
	}

	public String getTroco() {
		return troco;
	}

	public void setTroco(String troco) {
		this.troco = troco;
	}

	public BigDecimal getVcredsaldo() {
		return vcredsaldo;
	}

	public void setVcredsaldo(BigDecimal vcredsaldo) {
		this.vcredsaldo = vcredsaldo;
	}

	public Integer getTransacao() {
		return transacao;
	}

	public void setTransacao(Integer transacao) {
		this.transacao = transacao;
	}

	public String getCobauto() {
		return cobauto;
	}

	public void setCobauto(String cobauto) {
		this.cobauto = cobauto;
	}

	public String getPgtoprogramado() {
		return pgtoprogramado;
	}

	public void setPgtoprogramado(String pgtoprogramado) {
		this.pgtoprogramado = pgtoprogramado;
	}

	public String getTecno_idintegracao() {
		return tecno_idintegracao;
	}

	public void setTecno_idintegracao(String tecno_idintegracao) {
		this.tecno_idintegracao = tecno_idintegracao;
	}

	public Long getFntitulo_com() {
		return fntitulo_com;
	}

	public void setFntitulo_com(Long fntitulo_com) {
		this.fntitulo_com = fntitulo_com;
	}

	public BigDecimal getVbccom() {
		return vbccom;
	}

	public void setVbccom(BigDecimal vbccom) {
		this.vbccom = vbccom;
	}

	public BigDecimal getPcom() {
		return pcom;
	}

	public void setPcom(BigDecimal pcom) {
		this.pcom = pcom;
	}

	public String getComissao() {
		return comissao;
	}

	public void setComissao(String comissao) {
		this.comissao = comissao;
	}

	public String getParacob() {
		return paracob;
	}

	public void setParacob(String paracob) {
		this.paracob = paracob;
	}

	public List<FnTituloRel> getFntitulorelitem() {
		return fntitulorelitem;
	}

	public void setFntitulorelitem(List<FnTituloRel> fntitulorelitem) {
		this.fntitulorelitem = fntitulorelitem;
	}

	public List<FnTituloDre> getFntitulodreitem() {
		return fntitulodreitem;
	}

	public void setFntitulodreitem(List<FnTituloDre> fntitulodreitem) {
		this.fntitulodreitem = fntitulodreitem;
	}

	public List<FnTituloCcusto> getFntituloccustoitem() {
		return fntituloccustoitem;
	}

	public void setFntituloccustoitem(List<FnTituloCcusto> fntituloccustoitem) {
		this.fntituloccustoitem = fntituloccustoitem;
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
		FnTitulo other = (FnTitulo) obj;
		return Objects.equals(id, other.id);
	}
}
