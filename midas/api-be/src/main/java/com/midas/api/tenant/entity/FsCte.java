package com.midas.api.tenant.entity;

import java.io.Serializable;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fs_cte")
public class FsCte implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 1)
	private String tipo;
	private Integer cct;
	@Column(length = 4)
	private String cfop;
	@Column(length = 60)
	private String natop;
	private Integer modelo = 57;
	private Integer serie;
	private Integer nct;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dhemi = new Date();
	@JsonFormat(pattern = "HH:mm:ss", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.TIME)
	private Date dhemihr = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dhsaient = new Date();
	private Integer tpimp;
	private Integer tpemis;
	@Column(length = 44)
	private String chaveac;
	private Integer cdv;
	private Integer tpamb;
	private Integer tpcte;
	private Integer procemi;
	@Column(length = 20)
	private String verproc;
	@Column(length = 7)
	private String cmunenv;
	@Column(length = 60)
	private String xmunenv;
	@Column(length = 2)
	private String ufenv;
	@Column(length = 2)
	private String modal;
	private Integer tpserv;
	@Column(length = 7)
	private String cmunini;
	@Column(length = 60)
	private String xmunini;
	@Column(length = 2)
	private String ufini;
	@Column(length = 7)
	private String cmunfim;
	@Column(length = 60)
	private String xmunfim;
	@Column(length = 2)
	private String uffim;
	private Integer retira;
	@Column(length = 160)
	private String xdetretira;
	@Column(length = 15)
	private String cpfcnpjemit;
	private Integer tomador;
	@Column(length = 2000)
	private String infadfisco;
	@Column(length = 2000)
	private String infcpl;
	@Lob
	private String xml;
	@Column(length = 20)
	private String nprot = "";
	@Column(length = 20)
	private String nrecibo = "";
	@OneToOne(orphanRemoval = true)
	private FsCtePart fsctepartemit;
	@OneToOne(orphanRemoval = true)
	private FsCtePart fsctepartrem;
	@OneToOne(orphanRemoval = true)
	private FsCtePart fsctepartexp;
	@OneToOne(orphanRemoval = true)
	private FsCtePart fsctepartrec;
	@OneToOne(orphanRemoval = true)
	private FsCtePart fsctepartdest;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsCteVprest fsctevprest;
	@OneToOne(orphanRemoval = true)
	private FsCteIcms fscteicms;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsCteInf fscteinf;
	@Column(length = 1)
	private String st_cob = "N";
	@Column(length = 1)
	private String st_transp = "N";
	private String ocultamenu = "S";
	@ManyToOne
	private CdCteCfg cdctecfg;
	private Long doc_mdfe = 0L;
	private Integer num_mdfe;
	private Integer status;
	@OneToMany(mappedBy = "fscte", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsCteAut> fscteauts = new ArrayList<FsCteAut>();
	@OneToMany(mappedBy = "fscte", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsCteEvento> fscteeventos = new ArrayList<FsCteEvento>();
	@OneToMany(mappedBy = "fscte", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsCteMot> fsctemots = new ArrayList<FsCteMot>();

	public FsCte() {
		super();
	}

	public FsCte(Long id, CdPessoa cdpessoaemp, String tipo, Integer cct, String cfop, String natop, Integer modelo,
			Integer serie, Integer nct, Date dhemi, Date dhemihr, Date dhsaient, Integer tpimp, Integer tpemis,
			String chaveac, Integer cdv, Integer tpamb, Integer tpcte, Integer procemi, String verproc, String cmunenv,
			String xmunenv, String ufenv, String modal, Integer tpserv, String cmunini, String xmunini, String ufini,
			String cmunfim, String xmunfim, String uffim, Integer retira, String xdetretira, String cpfcnpjemit,
			Integer tomador, String infadfisco, String infcpl, String xml, String nprot, String nrecibo,
			FsCtePart fsctepartemit, FsCtePart fsctepartrem, FsCtePart fsctepartexp, FsCtePart fsctepartrec,
			FsCtePart fsctepartdest, FsCteVprest fsctevprest, FsCteIcms fscteicms, FsCteInf fscteinf, String st_cob,
			String st_transp, String ocultamenu, CdCteCfg cdctecfg, Long doc_mdfe, Integer num_mdfe, Integer status,
			List<FsCteAut> fscteauts, List<FsCteEvento> fscteeventos, List<FsCteMot> fsctemots) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.tipo = tipo;
		this.cct = cct;
		this.cfop = cfop;
		this.natop = natop;
		this.modelo = modelo;
		this.serie = serie;
		this.nct = nct;
		this.dhemi = dhemi;
		this.dhemihr = dhemihr;
		this.dhsaient = dhsaient;
		this.tpimp = tpimp;
		this.tpemis = tpemis;
		this.chaveac = chaveac;
		this.cdv = cdv;
		this.tpamb = tpamb;
		this.tpcte = tpcte;
		this.procemi = procemi;
		this.verproc = verproc;
		this.cmunenv = cmunenv;
		this.xmunenv = xmunenv;
		this.ufenv = ufenv;
		this.modal = modal;
		this.tpserv = tpserv;
		this.cmunini = cmunini;
		this.xmunini = xmunini;
		this.ufini = ufini;
		this.cmunfim = cmunfim;
		this.xmunfim = xmunfim;
		this.uffim = uffim;
		this.retira = retira;
		this.xdetretira = xdetretira;
		this.cpfcnpjemit = cpfcnpjemit;
		this.tomador = tomador;
		this.infadfisco = infadfisco;
		this.infcpl = infcpl;
		this.xml = xml;
		this.nprot = nprot;
		this.nrecibo = nrecibo;
		this.fsctepartemit = fsctepartemit;
		this.fsctepartrem = fsctepartrem;
		this.fsctepartexp = fsctepartexp;
		this.fsctepartrec = fsctepartrec;
		this.fsctepartdest = fsctepartdest;
		this.fsctevprest = fsctevprest;
		this.fscteicms = fscteicms;
		this.fscteinf = fscteinf;
		this.st_cob = st_cob;
		this.st_transp = st_transp;
		this.ocultamenu = ocultamenu;
		this.cdctecfg = cdctecfg;
		this.doc_mdfe = doc_mdfe;
		this.num_mdfe = num_mdfe;
		this.status = status;
		this.fscteauts = fscteauts;
		this.fscteeventos = fscteeventos;
		this.fsctemots = fsctemots;
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

	public Integer getCct() {
		return cct;
	}

	public void setCct(Integer numAdd) {
		this.cct = numAdd;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getNatop() {
		return natop;
	}

	public void setNatop(String natop) {
		this.natop = natop;
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

	public Integer getNct() {
		return nct;
	}

	public void setNct(Integer nct) {
		this.nct = nct;
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

	public Integer getTpcte() {
		return tpcte;
	}

	public void setTpcte(Integer tpcte) {
		this.tpcte = tpcte;
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

	public String getCmunenv() {
		return cmunenv;
	}

	public void setCmunenv(String cmunenv) {
		this.cmunenv = cmunenv;
	}

	public String getXmunenv() {
		return xmunenv;
	}

	public void setXmunenv(String xmunenv) {
		this.xmunenv = xmunenv;
	}

	public String getUfenv() {
		return ufenv;
	}

	public void setUfenv(String ufenv) {
		this.ufenv = ufenv;
	}

	public String getModal() {
		return modal;
	}

	public void setModal(String modal) {
		this.modal = modal;
	}

	public Integer getTpserv() {
		return tpserv;
	}

	public void setTpserv(Integer tpserv) {
		this.tpserv = tpserv;
	}

	public String getCmunini() {
		return cmunini;
	}

	public void setCmunini(String cmunini) {
		this.cmunini = cmunini;
	}

	public String getXmunini() {
		return xmunini;
	}

	public void setXmunini(String xmunini) {
		this.xmunini = xmunini;
	}

	public String getUfini() {
		return ufini;
	}

	public void setUfini(String ufini) {
		this.ufini = ufini;
	}

	public String getCmunfim() {
		return cmunfim;
	}

	public void setCmunfim(String cmunfim) {
		this.cmunfim = cmunfim;
	}

	public String getXmunfim() {
		return xmunfim;
	}

	public void setXmunfim(String xmunfim) {
		this.xmunfim = xmunfim;
	}

	public String getUffim() {
		return uffim;
	}

	public void setUffim(String uffim) {
		this.uffim = uffim;
	}

	public Integer getRetira() {
		return retira;
	}

	public void setRetira(Integer retira) {
		this.retira = retira;
	}

	public String getXdetretira() {
		return xdetretira;
	}

	public void setXdetretira(String xdetretira) {
		this.xdetretira = xdetretira;
	}

	public String getCpfcnpjemit() {
		return cpfcnpjemit;
	}

	public void setCpfcnpjemit(String cpfcnpjemit) {
		this.cpfcnpjemit = cpfcnpjemit;
	}

	public Integer getTomador() {
		return tomador;
	}

	public void setTomador(Integer tomador) {
		this.tomador = tomador;
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

	public String getNrecibo() {
		return nrecibo;
	}

	public void setNrecibo(String nrecibo) {
		this.nrecibo = nrecibo;
	}

	public FsCtePart getFsctepartemit() {
		return fsctepartemit;
	}

	public void setFsctepartemit(FsCtePart fsctepartemit) {
		this.fsctepartemit = fsctepartemit;
	}

	public FsCtePart getFsctepartrem() {
		return fsctepartrem;
	}

	public void setFsctepartrem(FsCtePart fsctepartrem) {
		this.fsctepartrem = fsctepartrem;
	}

	public FsCtePart getFsctepartexp() {
		return fsctepartexp;
	}

	public void setFsctepartexp(FsCtePart fsctepartexp) {
		this.fsctepartexp = fsctepartexp;
	}

	public FsCtePart getFsctepartrec() {
		return fsctepartrec;
	}

	public void setFsctepartrec(FsCtePart fsctepartrec) {
		this.fsctepartrec = fsctepartrec;
	}

	public FsCtePart getFsctepartdest() {
		return fsctepartdest;
	}

	public void setFsctepartdest(FsCtePart fsctepartdest) {
		this.fsctepartdest = fsctepartdest;
	}

	public FsCteVprest getFsctevprest() {
		return fsctevprest;
	}

	public void setFsctevprest(FsCteVprest fsctevprest) {
		this.fsctevprest = fsctevprest;
	}

	public FsCteIcms getFscteicms() {
		return fscteicms;
	}

	public void setFscteicms(FsCteIcms fscteicms) {
		this.fscteicms = fscteicms;
	}

	public FsCteInf getFscteinf() {
		return fscteinf;
	}

	public void setFscteinf(FsCteInf fscteinf) {
		this.fscteinf = fscteinf;
	}

	public String getSt_cob() {
		return st_cob;
	}

	public void setSt_cob(String st_cob) {
		this.st_cob = st_cob;
	}

	public String getSt_transp() {
		return st_transp;
	}

	public void setSt_transp(String st_transp) {
		this.st_transp = st_transp;
	}

	public String getOcultamenu() {
		return ocultamenu;
	}

	public void setOcultamenu(String ocultamenu) {
		this.ocultamenu = ocultamenu;
	}

	public CdCteCfg getCdctecfg() {
		return cdctecfg;
	}

	public void setCdctecfg(CdCteCfg cdctecfg) {
		this.cdctecfg = cdctecfg;
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

	public List<FsCteAut> getFscteauts() {
		return fscteauts;
	}

	public void setFscteauts(List<FsCteAut> fscteauts) {
		this.fscteauts = fscteauts;
	}

	public List<FsCteEvento> getFscteeventos() {
		return fscteeventos;
	}

	public void setFscteeventos(List<FsCteEvento> fscteeventos) {
		this.fscteeventos = fscteeventos;
	}

	public List<FsCteMot> getFsctemots() {
		return fsctemots;
	}

	public void setFsctemots(List<FsCteMot> fsctemots) {
		this.fsctemots = fsctemots;
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
		FsCte other = (FsCte) obj;
		return Objects.equals(id, other.id);
	}
}
