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
@Table(name = "fs_nfe")
public class FsNfe implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 1)
	private String tipo;
	private Long cnf;
	@Column(length = 60)
	private String natop;
	private Integer modelo;
	private Integer serie;
	private Integer nnf;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dhemi = new Date();
	@JsonFormat(pattern = "HH:mm:ss", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.TIME)
	private Date dhemihr = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dhsaient = new Date();
	private Integer tpnf;
	private Integer iddest;
	@Column(length = 7)
	private String cmunfg;
	@OneToMany(mappedBy = "fsnfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsNfeRef> fsnferefs = new ArrayList<FsNfeRef>();
	private Integer tpimp;
	private Integer tpemis;
	@Column(length = 44)
	private String chaveac;
	private Integer cdv;
	private Integer tpamb;
	private Integer finnfe;
	private Integer indfinal;
	private Integer indpres;
	private Integer procemi;
	@Column(length = 20)
	private String verproc;
	@Column(length = 15)
	private String cpfcnpjemit;
	@Lob
	private String xml;
	@Column(length = 20)
	private String nprot;
	@OneToOne(orphanRemoval = true)
	private FsNfePart fsnfepartemit;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsNfePart fsnfepartdest;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsNfePart fsnfeparttransp;
	@OneToOne(orphanRemoval = true)
	private FsNfePart fsnfepartent;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsNfeCobr fsnfecobr;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsNfeFrete fsnfefrete;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsNfeTotIcms fsnfetoticms;
	@Column(length = 2000)
	private String infadfisco;
	@Column(length = 5000)
	private String infcpl;
	@Column(length = 22)
	private String xnemp;
	@Column(length = 60)
	private String xped;
	@Column(length = 60)
	private String xcont;
	@Column(length = 2)
	private String ufsaidapais;
	@Column(length = 60)
	private String xlocexporta;
	@Column(length = 60)
	private String xlocdespacho;
	@Column(length = 1)
	private String st_fornec;
	@Column(length = 1)
	private String st_cob;
	@Column(length = 1)
	private String st_est;
	@Column(length = 1)
	private String st_custos;
	@Column(length = 2)
	private String tpdoc = "00";
	private Long lcdoc = 0L;
	private Long numdoc = 0L;
	@ManyToOne
	private CdNfeCfg cdnfecfg;
	@Column(length = 1)
	private String usacfgfiscal = "N";
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_nacional = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_importado = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_estadual = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_municipal = new BigDecimal(0);
	private Long doc_mdfe = 0L;
	private Integer num_mdfe;
	private Integer status;
	@JsonIgnoreProperties(value = { "fsnfe" }, allowSetters = true)
	@OneToMany(mappedBy = "fsnfe", orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.JOIN)
	@OrderBy("id ASC")
	private List<FsNfeItem> fsnfeitems = new ArrayList<FsNfeItem>();
	@OneToMany(mappedBy = "fsnfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsNfeAut> fsnfeauts = new ArrayList<FsNfeAut>();
	@OneToMany(mappedBy = "fsnfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsNfePag> fsnfepags = new ArrayList<FsNfePag>();
	@OneToMany(mappedBy = "fsnfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsNfeEvento> fsnfeeventos = new ArrayList<FsNfeEvento>();

	public FsNfe() {
	}

	public FsNfe(Long id, CdPessoa cdpessoaemp, String tipo, Long cnf, String natop, Integer modelo, Integer serie,
			Integer nnf, Date dhemi, Date dhemihr, Date dhsaient, Integer tpnf, Integer iddest, String cmunfg,
			List<FsNfeRef> fsnferefs, Integer tpimp, Integer tpemis, String chaveac, Integer cdv, Integer tpamb,
			Integer finnfe, Integer indfinal, Integer indpres, Integer procemi, String verproc, String cpfcnpjemit,
			String xml, String nprot, FsNfePart fsnfepartemit, FsNfePart fsnfepartdest, FsNfePart fsnfeparttransp,
			FsNfePart fsnfepartent, FsNfeCobr fsnfecobr, FsNfeFrete fsnfefrete, FsNfeTotIcms fsnfetoticms,
			String infadfisco, String infcpl, String xnemp, String xped, String xcont, String ufsaidapais,
			String xlocexporta, String xlocdespacho, String st_fornec, String st_cob, String st_est, String st_custos,
			String tpdoc, Long lcdoc, Long numdoc, CdNfeCfg cdnfecfg, String usacfgfiscal,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_nacional,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_importado,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_estadual,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_municipal, Long doc_mdfe, Integer num_mdfe,
			Integer status, List<FsNfeItem> fsnfeitems, List<FsNfeAut> fsnfeauts, List<FsNfePag> fsnfepags,
			List<FsNfeEvento> fsnfeeventos) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.tipo = tipo;
		this.cnf = cnf;
		this.natop = natop;
		this.modelo = modelo;
		this.serie = serie;
		this.nnf = nnf;
		this.dhemi = dhemi;
		this.dhemihr = dhemihr;
		this.dhsaient = dhsaient;
		this.tpnf = tpnf;
		this.iddest = iddest;
		this.cmunfg = cmunfg;
		this.fsnferefs = fsnferefs;
		this.tpimp = tpimp;
		this.tpemis = tpemis;
		this.chaveac = chaveac;
		this.cdv = cdv;
		this.tpamb = tpamb;
		this.finnfe = finnfe;
		this.indfinal = indfinal;
		this.indpres = indpres;
		this.procemi = procemi;
		this.verproc = verproc;
		this.cpfcnpjemit = cpfcnpjemit;
		this.xml = xml;
		this.nprot = nprot;
		this.fsnfepartemit = fsnfepartemit;
		this.fsnfepartdest = fsnfepartdest;
		this.fsnfeparttransp = fsnfeparttransp;
		this.fsnfepartent = fsnfepartent;
		this.fsnfecobr = fsnfecobr;
		this.fsnfefrete = fsnfefrete;
		this.fsnfetoticms = fsnfetoticms;
		this.infadfisco = infadfisco;
		this.infcpl = infcpl;
		this.xnemp = xnemp;
		this.xped = xped;
		this.xcont = xcont;
		this.ufsaidapais = ufsaidapais;
		this.xlocexporta = xlocexporta;
		this.xlocdespacho = xlocdespacho;
		this.st_fornec = st_fornec;
		this.st_cob = st_cob;
		this.st_est = st_est;
		this.st_custos = st_custos;
		this.tpdoc = tpdoc;
		this.lcdoc = lcdoc;
		this.numdoc = numdoc;
		this.cdnfecfg = cdnfecfg;
		this.usacfgfiscal = usacfgfiscal;
		this.vibpt_nacional = vibpt_nacional;
		this.vibpt_importado = vibpt_importado;
		this.vibpt_estadual = vibpt_estadual;
		this.vibpt_municipal = vibpt_municipal;
		this.doc_mdfe = doc_mdfe;
		this.num_mdfe = num_mdfe;
		this.status = status;
		this.fsnfeitems = fsnfeitems;
		this.fsnfeauts = fsnfeauts;
		this.fsnfepags = fsnfepags;
		this.fsnfeeventos = fsnfeeventos;
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

	public Long getCnf() {
		return cnf;
	}

	public void setCnf(Long cnf) {
		this.cnf = cnf;
	}

	public String getNatop() {
		return natop;
	}

	public void setNatop(String natop) {
		this.natop = CaracterUtil.remUpper(natop);
	}

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public Integer getNnf() {
		return nnf;
	}

	public void setNnf(Integer nnf) {
		this.nnf = nnf;
	}

	public Date getDhemi() {
		return dhemi;
	}

	public void setDhemi(Date dhemi) {
		this.dhemi = dhemi;
	}

	public Date getDhemihr() {
		return dhemihr;
	}

	public void setDhemihr(Date dhemihr) {
		this.dhemihr = dhemihr;
	}

	public Date getDhsaient() {
		return dhsaient;
	}

	public void setDhsaient(Date dhsaient) {
		this.dhsaient = dhsaient;
	}

	public Integer getTpnf() {
		return tpnf;
	}

	public void setTpnf(Integer tpnf) {
		this.tpnf = tpnf;
	}

	public Integer getIddest() {
		return iddest;
	}

	public void setIddest(Integer iddest) {
		this.iddest = iddest;
	}

	public String getCmunfg() {
		return cmunfg;
	}

	public void setCmunfg(String cmunfg) {
		this.cmunfg = cmunfg;
	}

	public List<FsNfeRef> getFsnferefs() {
		return fsnferefs;
	}

	public void setFsnferefs(List<FsNfeRef> fsnferefs) {
		this.fsnferefs = fsnferefs;
	}

	public Integer getTpimp() {
		return tpimp;
	}

	public void setTpimp(Integer tpimp) {
		this.tpimp = tpimp;
	}

	public Integer getTpemis() {
		return tpemis;
	}

	public void setTpemis(Integer tpemis) {
		this.tpemis = tpemis;
	}

	public String getChaveac() {
		return chaveac;
	}

	public void setChaveac(String chaveac) {
		this.chaveac = chaveac;
	}

	public Integer getCdv() {
		return cdv;
	}

	public void setCdv(Integer cdv) {
		this.cdv = cdv;
	}

	public Integer getTpamb() {
		return tpamb;
	}

	public void setTpamb(Integer tpamb) {
		this.tpamb = tpamb;
	}

	public Integer getFinnfe() {
		return finnfe;
	}

	public void setFinnfe(Integer finnfe) {
		this.finnfe = finnfe;
	}

	public Integer getIndfinal() {
		return indfinal;
	}

	public void setIndfinal(Integer indfinal) {
		this.indfinal = indfinal;
	}

	public Integer getIndpres() {
		return indpres;
	}

	public void setIndpres(Integer indpres) {
		this.indpres = indpres;
	}

	public Integer getProcemi() {
		return procemi;
	}

	public void setProcemi(Integer procemi) {
		this.procemi = procemi;
	}

	public String getVerproc() {
		return verproc;
	}

	public void setVerproc(String verproc) {
		this.verproc = verproc;
	}

	public String getCpfcnpjemit() {
		return cpfcnpjemit;
	}

	public void setCpfcnpjemit(String cpfcnpjemit) {
		this.cpfcnpjemit = cpfcnpjemit;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getNprot() {
		return nprot;
	}

	public void setNprot(String nprot) {
		this.nprot = nprot;
	}

	public FsNfePart getFsnfepartemit() {
		return fsnfepartemit;
	}

	public void setFsnfepartemit(FsNfePart fsnfepartemit) {
		this.fsnfepartemit = fsnfepartemit;
	}

	public FsNfePart getFsnfepartdest() {
		return fsnfepartdest;
	}

	public void setFsnfepartdest(FsNfePart fsnfepartdest) {
		this.fsnfepartdest = fsnfepartdest;
	}

	public FsNfePart getFsnfeparttransp() {
		return fsnfeparttransp;
	}

	public void setFsnfeparttransp(FsNfePart fsnfeparttransp) {
		this.fsnfeparttransp = fsnfeparttransp;
	}

	public FsNfePart getFsnfepartent() {
		return fsnfepartent;
	}

	public void setFsnfepartent(FsNfePart fsnfepartent) {
		this.fsnfepartent = fsnfepartent;
	}

	public FsNfeCobr getFsnfecobr() {
		return fsnfecobr;
	}

	public void setFsnfecobr(FsNfeCobr fsnfecobr) {
		this.fsnfecobr = fsnfecobr;
	}

	public FsNfeFrete getFsnfefrete() {
		return fsnfefrete;
	}

	public void setFsnfefrete(FsNfeFrete fsnfefrete) {
		this.fsnfefrete = fsnfefrete;
	}

	public FsNfeTotIcms getFsnfetoticms() {
		return fsnfetoticms;
	}

	public void setFsnfetoticms(FsNfeTotIcms fsnfetoticms) {
		this.fsnfetoticms = fsnfetoticms;
	}

	public String getInfadfisco() {
		return infadfisco;
	}

	public void setInfadfisco(String infadfisco) {
		this.infadfisco = CaracterUtil.remUpperEnter(infadfisco);
	}

	public String getInfcpl() {
		return infcpl;
	}

	public void setInfcpl(String infcpl) {
		this.infcpl = CaracterUtil.remUpperEnter(infcpl);
	}

	public String getXnemp() {
		return xnemp;
	}

	public void setXnemp(String xnemp) {
		this.xnemp = CaracterUtil.remUpper(xnemp);
	}

	public String getXped() {
		return xped;
	}

	public void setXped(String xped) {
		this.xped = CaracterUtil.remUpper(xped);
	}

	public String getXcont() {
		return xcont;
	}

	public void setXcont(String xcont) {
		this.xcont = CaracterUtil.remUpper(xcont);
	}

	public String getUfsaidapais() {
		return ufsaidapais;
	}

	public void setUfsaidapais(String ufsaidapais) {
		this.ufsaidapais = ufsaidapais;
	}

	public String getXlocexporta() {
		return xlocexporta;
	}

	public void setXlocexporta(String xlocexporta) {
		this.xlocexporta = CaracterUtil.remUpper(xlocexporta);
	}

	public String getXlocdespacho() {
		return xlocdespacho;
	}

	public void setXlocdespacho(String xlocdespacho) {
		this.xlocdespacho = CaracterUtil.remUpper(xlocdespacho);
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

	public String getSt_est() {
		return st_est;
	}

	public void setSt_est(String st_est) {
		this.st_est = st_est;
	}

	public String getSt_custos() {
		return st_custos;
	}

	public void setSt_custos(String st_custos) {
		this.st_custos = st_custos;
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

	public String getUsacfgfiscal() {
		return usacfgfiscal;
	}

	public void setUsacfgfiscal(String usacfgfiscal) {
		this.usacfgfiscal = usacfgfiscal;
	}

	public void setVibpt_nacional(BigDecimal vibpt_nacional) {
		this.vibpt_nacional = vibpt_nacional;
	}

	public void setVibpt_importado(BigDecimal vibpt_importado) {
		this.vibpt_importado = vibpt_importado;
	}

	public void setVibpt_estadual(BigDecimal vibpt_estadual) {
		this.vibpt_estadual = vibpt_estadual;
	}

	public void setVibpt_municipal(BigDecimal vibpt_municipal) {
		this.vibpt_municipal = vibpt_municipal;
	}

	public BigDecimal getVibpt_nacional() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsnfeitems.isEmpty()) {
			for (FsNfeItem item : fsnfeitems) {
				calc = calc.add(item.getVibpt_nacional());
			}
		}
		return this.vibpt_nacional = calc;
	}

	public void setVibpt_nacional() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsnfeitems.isEmpty()) {
			for (FsNfeItem item : fsnfeitems) {
				calc = calc.add(item.getVibpt_nacional());
			}
		}
		this.vibpt_nacional = calc;
	}

	public BigDecimal getVibpt_importado() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsnfeitems.isEmpty()) {
			for (FsNfeItem item : fsnfeitems) {
				calc = calc.add(item.getVibpt_importado());
			}
		}
		return this.vibpt_importado = calc;
	}

	public void setVibpt_importado() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsnfeitems.isEmpty()) {
			for (FsNfeItem item : fsnfeitems) {
				calc = calc.add(item.getVibpt_importado());
			}
		}
		this.vibpt_importado = calc;
	}

	public BigDecimal getVibpt_estadual() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsnfeitems.isEmpty()) {
			for (FsNfeItem item : fsnfeitems) {
				calc = calc.add(item.getVibpt_estadual());
			}
		}
		return this.vibpt_estadual = calc;
	}

	public void setVibpt_estadual() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsnfeitems.isEmpty()) {
			for (FsNfeItem item : fsnfeitems) {
				calc = calc.add(item.getVibpt_estadual());
			}
		}
		this.vibpt_estadual = calc;
	}

	public BigDecimal getVibpt_municipal() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsnfeitems.isEmpty()) {
			for (FsNfeItem item : fsnfeitems) {
				calc = calc.add(item.getVibpt_municipal());
			}
		}
		return this.vibpt_municipal = calc;
	}

	public void setVibpt_municipal() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsnfeitems.isEmpty()) {
			for (FsNfeItem item : fsnfeitems) {
				calc = calc.add(item.getVibpt_municipal());
			}
		}
		this.vibpt_municipal = calc;
	}
	
	public Long getDoc_mdfe() {
		return doc_mdfe;
	}

	public void setDoc_mdfe(Long doc_mdfe) {
		this.doc_mdfe = doc_mdfe;
	}

	public Integer getNum_mdfe() {
		return num_mdfe;
	}

	public void setNum_mdfe(Integer num_mdfe) {
		this.num_mdfe = num_mdfe;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<FsNfeItem> getFsnfeitems() {
		return fsnfeitems;
	}

	public void setFsnfeitems(List<FsNfeItem> fsnfeitems) {
		this.fsnfeitems = fsnfeitems;
	}

	public List<FsNfeAut> getFsnfeauts() {
		return fsnfeauts;
	}

	public void setFsnfeauts(List<FsNfeAut> fsnfeauts) {
		this.fsnfeauts = fsnfeauts;
	}

	public List<FsNfePag> getFsnfepags() {
		return fsnfepags;
	}

	public void setFsnfepags(List<FsNfePag> fsnfepags) {
		this.fsnfepags = fsnfepags;
	}

	public List<FsNfeEvento> getFsnfeeventos() {
		return fsnfeeventos;
	}

	public void setFsnfeeventos(List<FsNfeEvento> fsnfeeventos) {
		this.fsnfeeventos = fsnfeeventos;
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
		FsNfe other = (FsNfe) obj;
		return Objects.equals(id, other.id);
	}
}
