package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "fs_nfse")
public class FsNfse implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 1)
	private String tipo;
	private Integer rpsnumero;
	@Column(length = 5)
	private String rpsserie;
	private Integer rpstipo;
	private Integer nnfs;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date demis = new Date();
	@JsonFormat(pattern = "HH:mm:ss", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.TIME)
	private Date demishora = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dcompetencia = new Date();
	@Column(length = 1)
	private String local;
	@Column(length = 1)
	private String localprestserv;
	@Column(length = 1)
	private String natop;
	@Column(length = 1)
	private String operacao;
	@Column(length = 2)
	private String regesptrib;
	@Column(length = 1)
	private String optsn;
	@Column(length = 1)
	private String inccult;
	private Integer tpamb;
	@Column(length = 1)
	private String fpag;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal serquantidade = BigDecimal.ZERO;
	@Lob
	private String discriminacao;
	@Column(length = 1)
	private String st_fornec;
	@Column(length = 1)
	private String st_cob;
	@Column(length = 2)
	private String tpdoc = "00";
	private Long lcdoc = 0L;
	private Long numdoc = 0L;
	@Column(length = 30)
	private String numprocesso;
	@Column(length = 1000)
	private String outrasinfo;
	@Lob
	private String xml;
	@Column(length = 1)
	private String statusrps;
	private Integer status;
	@Column(length = 20)
	private String verproc;
	@Column(length = 30)
	private String cverifica;
	@ManyToOne
	private CdNfeCfg cdnfecfg;
	@OneToOne(orphanRemoval = true)
	private FsNfsePart fsnfsepartprest;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsNfsePart fsnfseparttoma;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsNfsePart fsnfsepartlocal;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsNfseTot fsnfsetot;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsNfseCobr fsnfsecobr;
	@JsonIgnoreProperties(value = { "fsnfse" }, allowSetters = true)
	@OneToMany(mappedBy = "fsnfse", orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.JOIN)
	@OrderBy("id ASC")
	private List<FsNfseItem> fsnfseitems = new ArrayList<FsNfseItem>();
	@OneToMany(mappedBy = "fsnfse", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsNfseEvento> fsnfseeventos = new ArrayList<FsNfseEvento>();

	public FsNfse() {
	}

	public FsNfse(Long id, CdPessoa cdpessoaemp, String tipo, Integer rpsnumero, String rpsserie, Integer rpstipo,
			Integer nnfs, Date demis, Date demishora, Date dcompetencia, String local, String localprestserv,
			String natop, String operacao, String regesptrib, String optsn, String inccult, Integer tpamb, String fpag,
			@Digits(integer = 16, fraction = 4) BigDecimal serquantidade, String discriminacao, String st_fornec,
			String st_cob, String tpdoc, Long lcdoc, Long numdoc, String numprocesso, String outrasinfo, String xml,
			String statusrps, Integer status, String verproc, String cverifica, CdNfeCfg cdnfecfg,
			FsNfsePart fsnfsepartprest, FsNfsePart fsnfseparttoma, FsNfsePart fsnfsepartlocal, FsNfseTot fsnfsetot,
			FsNfseCobr fsnfsecobr, List<FsNfseItem> fsnfseitems, List<FsNfseEvento> fsnfseeventos) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.tipo = tipo;
		this.rpsnumero = rpsnumero;
		this.rpsserie = rpsserie;
		this.rpstipo = rpstipo;
		this.nnfs = nnfs;
		this.demis = demis;
		this.demishora = demishora;
		this.dcompetencia = dcompetencia;
		this.local = local;
		this.localprestserv = localprestserv;
		this.natop = natop;
		this.operacao = operacao;
		this.regesptrib = regesptrib;
		this.optsn = optsn;
		this.inccult = inccult;
		this.tpamb = tpamb;
		this.fpag = fpag;
		this.serquantidade = serquantidade;
		this.discriminacao = discriminacao;
		this.st_fornec = st_fornec;
		this.st_cob = st_cob;
		this.tpdoc = tpdoc;
		this.lcdoc = lcdoc;
		this.numdoc = numdoc;
		this.numprocesso = numprocesso;
		this.outrasinfo = outrasinfo;
		this.xml = xml;
		this.statusrps = statusrps;
		this.status = status;
		this.verproc = verproc;
		this.cverifica = cverifica;
		this.cdnfecfg = cdnfecfg;
		this.fsnfsepartprest = fsnfsepartprest;
		this.fsnfseparttoma = fsnfseparttoma;
		this.fsnfsepartlocal = fsnfsepartlocal;
		this.fsnfsetot = fsnfsetot;
		this.fsnfsecobr = fsnfsecobr;
		this.fsnfseitems = fsnfseitems;
		this.fsnfseeventos = fsnfseeventos;
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

	public Integer getRpsnumero() {
		return rpsnumero;
	}

	public void setRpsnumero(Integer rpsnumero) {
		this.rpsnumero = rpsnumero;
	}

	public String getRpsserie() {
		return rpsserie;
	}

	public void setRpsserie(String rpsserie) {
		this.rpsserie = rpsserie;
	}

	public Integer getRpstipo() {
		return rpstipo;
	}

	public void setRpstipo(Integer rpstipo) {
		this.rpstipo = rpstipo;
	}

	public Integer getNnfs() {
		return nnfs;
	}

	public void setNnfs(Integer nnfs) {
		this.nnfs = nnfs;
	}

	public Date getDemis() {
		return demis;
	}

	public void setDemis(Date demis) {
		this.demis = demis;
	}

	public Date getDemishora() {
		return demishora;
	}

	public void setDemishora(Date demishora) {
		this.demishora = demishora;
	}

	public Date getDcompetencia() {
		return dcompetencia;
	}

	public void setDcompetencia(Date dcompetencia) {
		this.dcompetencia = dcompetencia;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getLocalprestserv() {
		return localprestserv;
	}

	public void setLocalprestserv(String localprestserv) {
		this.localprestserv = localprestserv;
	}

	public String getNatop() {
		return natop;
	}

	public void setNatop(String natop) {
		this.natop = natop;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getNumprocesso() {
		return numprocesso;
	}

	public void setNumprocesso(String numprocesso) {
		this.numprocesso = numprocesso;
	}

	public String getRegesptrib() {
		return regesptrib;
	}

	public void setRegesptrib(String regesptrib) {
		this.regesptrib = regesptrib;
	}

	public String getOptsn() {
		return optsn;
	}

	public void setOptsn(String optsn) {
		this.optsn = optsn;
	}

	public String getInccult() {
		return inccult;
	}

	public void setInccult(String inccult) {
		this.inccult = inccult;
	}

	public Integer getTpamb() {
		return tpamb;
	}

	public void setTpamb(Integer tpamb) {
		this.tpamb = tpamb;
	}

	public String getFpag() {
		return fpag;
	}

	public void setFpag(String fpag) {
		this.fpag = fpag;
	}

	public BigDecimal getSerquantidade() {
		return serquantidade;
	}

	public void setSerquantidade(BigDecimal serquantidade) {
		this.serquantidade = serquantidade;
	}

	public String getDiscriminacao() {
		return discriminacao;
	}

	public void setDiscriminacao(String discriminacao) {
		this.discriminacao = discriminacao;
	}

	public String getSt_fornec() {
		return st_fornec;
	}

	public void setSt_fornec(String st_fornec) {
		this.st_fornec = st_fornec;
	}

	public String getSt_cob() {
		return st_cob;
	}

	public void setSt_cob(String st_cob) {
		this.st_cob = st_cob;
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

	public CdNfeCfg getCdnfecfg() {
		return cdnfecfg;
	}

	public void setCdnfecfg(CdNfeCfg cdnfecfg) {
		this.cdnfecfg = cdnfecfg;
	}

	public String getOutrasinfo() {
		return outrasinfo;
	}

	public void setOutrasinfo(String outrasinfo) {
		this.outrasinfo = CaracterUtil.remUpper(outrasinfo);
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getStatusrps() {
		return statusrps;
	}

	public void setStatusrps(String statusrps) {
		this.statusrps = statusrps;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVerproc() {
		return verproc;
	}

	public void setVerproc(String verproc) {
		this.verproc = verproc;
	}

	public String getCverifica() {
		return cverifica;
	}

	public void setCverifica(String cverifica) {
		this.cverifica = cverifica;
	}

	public FsNfsePart getFsnfsepartprest() {
		return fsnfsepartprest;
	}

	public void setFsnfsepartprest(FsNfsePart fsnfsepartprest) {
		this.fsnfsepartprest = fsnfsepartprest;
	}

	public FsNfsePart getFsnfseparttoma() {
		return fsnfseparttoma;
	}

	public void setFsnfseparttoma(FsNfsePart fsnfseparttoma) {
		this.fsnfseparttoma = fsnfseparttoma;
	}

	public FsNfsePart getFsnfsepartlocal() {
		return fsnfsepartlocal;
	}

	public void setFsnfsepartlocal(FsNfsePart fsnfsepartlocal) {
		this.fsnfsepartlocal = fsnfsepartlocal;
	}

	public FsNfseTot getFsnfsetot() {
		return fsnfsetot;
	}

	public void setFsnfsetot(FsNfseTot fsnfsetot) {
		this.fsnfsetot = fsnfsetot;
	}

	public FsNfseCobr getFsnfsecobr() {
		return fsnfsecobr;
	}

	public void setFsnfsecobr(FsNfseCobr fsnfsecobr) {
		this.fsnfsecobr = fsnfsecobr;
	}

	public List<FsNfseItem> getFsnfseitems() {
		return fsnfseitems;
	}

	public void setFsnfseitems(List<FsNfseItem> fsnfseitems) {
		this.fsnfseitems = fsnfseitems;
	}

	public List<FsNfseEvento> getFsnfseeventos() {
		return fsnfseeventos;
	}

	public void setFsnfseeventos(List<FsNfseEvento> fsnfseeventos) {
		this.fsnfseeventos = fsnfseeventos;
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
		FsNfse other = (FsNfse) obj;
		return Objects.equals(id, other.id);
	}
}
