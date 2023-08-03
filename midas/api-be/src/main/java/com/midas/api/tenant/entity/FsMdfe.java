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
@Table(name = "fs_mdfe")
public class FsMdfe implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 1)
	private String tipo;
	private Integer cuf;
	private Integer tpamb;
	private Integer tpemit;
	private Integer tptransp;
	private Integer modelo = 58;
	private Integer serie;
	private Integer nmdf;
	private Integer cmdf;
	@Column(length = 44)
	private String chaveac;
	private Integer cdv;
	@Column(length = 1)
	private String modal;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dhemi = new Date();
	@JsonFormat(pattern = "HH:mm:ss", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.TIME)
	private Date dhemihr = new Date();
	private Integer tpemis;
	private Integer procemi;
	@Column(length = 20)
	private String verproc;
	@OneToOne
	private CdCidade cdcidadeini;
	@OneToOne
	private CdEstado cdestadoini;
	@OneToOne
	private CdEstado cdestadofim;
	// Totalizadores
	@SuppressWarnings("unused")
	private Integer qcte = 0;
	@SuppressWarnings("unused")
	private Integer qnfe = 0;
	private Integer qmdfe;
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vcarga;
	@Column(length = 2)
	private String cunid;
	@Digits(integer = 11, fraction = 4)
	private BigDecimal qcarga;
	// Informacoes adicionais
	@Column(length = 2000)
	private String infadfisco;
	@Column(length = 2000)
	private String infcpl;
	@Lob
	private String xml;
	@Column(length = 20)
	private String nprot;
	@Column(length = 20)
	private String nrecibo;
	@Column(length = 1)
	private String st_imp = "S";
	@OneToOne
	private CdPessoa cdpessoaseg;
	private Integer respseg = 1;
	// Dados veiculo
	@OneToOne
	private CdVeiculo cdveiculo;
	@Column(length = 7)
	private String v_placa;
	@Column(length = 2)
	private String v_ufplaca;
	@Column(length = 9)
	private String v_antt;
	@Column(length = 6)
	private String v_tara;
	@Column(length = 12)
	private String ciot;
	@Column(length = 14)
	private String ciot_cpfcnpj;
	@Column(length = 2)
	private String pp_tipo = "05";
	@Column(length = 120)
	private String pp_nome;
	@Column(length = 14)
	private String pp_cean;
	@Column(length = 8)
	private String pp_ncm;
	private Integer pp_lotacao = 0;
	@Column(length = 8)
	private String pp_cepcar;
	@Column(length = 8)
	private String pp_cepdesc;
	@Column(length = 1)
	private String ocultamenu = "S";
	@Column(length = 1)
	private String encerrado = "N";
	private Integer status;
	@JsonIgnoreProperties(value = { "fsmdfe" }, allowSetters = true)
	@OneToMany(mappedBy = "fsmdfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("cdcidade_id ASC")
	private List<FsMdfeDoc> fsmdfedocitems = new ArrayList<FsMdfeDoc>();
	@JsonIgnoreProperties(value = { "fsmdfe" }, allowSetters = true)
	@OneToMany(mappedBy = "fsmdfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("xmun ASC")
	private List<FsMdfePart> fsmdfepartitems = new ArrayList<FsMdfePart>();
	@JsonIgnoreProperties(value = { "fsmdfe" }, allowSetters = true)
	@OneToMany(mappedBy = "fsmdfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsMdfePerc> fsmdfepercitems = new ArrayList<FsMdfePerc>();
	@JsonIgnoreProperties(value = { "fsmdfe" }, allowSetters = true)
	@OneToMany(mappedBy = "fsmdfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsMdfeReboq> fsmdfereboqitems = new ArrayList<FsMdfeReboq>();
	@JsonIgnoreProperties(value = { "fsmdfe" }, allowSetters = true)
	@OneToMany(mappedBy = "fsmdfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsMdfeAut> fsmdfeautitems = new ArrayList<FsMdfeAut>();
	@OneToMany(mappedBy = "fsmdfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsMdfeEvento> fsmdfeeventos = new ArrayList<FsMdfeEvento>();
	@JsonIgnoreProperties(value = { "fsmdfe" }, allowSetters = true)
	@OneToMany(mappedBy = "fsmdfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsMdfeAverb> fsmdfeaverbitems = new ArrayList<FsMdfeAverb>();
	@JsonIgnoreProperties(value = { "fsmdfe" }, allowSetters = true)
	@OneToMany(mappedBy = "fsmdfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsMdfeContr> fsmdfecontritems = new ArrayList<FsMdfeContr>();
	@JsonIgnoreProperties(value = { "fsmdfe" }, allowSetters = true)
	@OneToMany(mappedBy = "fsmdfe", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsMdfePag> fsmdfepagitems = new ArrayList<FsMdfePag>();

	public FsMdfe() {
		super();
	}

	public FsMdfe(Long id, CdPessoa cdpessoaemp, String tipo, Integer cuf, Integer tpamb, Integer tpemit,
			Integer tptransp, Integer modelo, Integer serie, Integer nmdf, Integer cmdf, String chaveac, Integer cdv,
			String modal, Date dhemi, Date dhemihr, Integer tpemis, Integer procemi, String verproc,
			CdCidade cdcidadeini, CdEstado cdestadoini, CdEstado cdestadofim, Integer qcte, Integer qnfe, Integer qmdfe,
			@Digits(integer = 13, fraction = 2) BigDecimal vcarga, String cunid,
			@Digits(integer = 11, fraction = 4) BigDecimal qcarga, String infadfisco, String infcpl, String xml,
			String nprot, String nrecibo, String st_imp, CdPessoa cdpessoaseg, Integer respseg, CdVeiculo cdveiculo,
			String v_placa, String v_ufplaca, String v_antt, String v_tara, String ciot, String ciot_cpfcnpj,
			String pp_tipo, String pp_nome, String pp_cean, String pp_ncm, Integer pp_lotacao, String pp_cepcar,
			String pp_cepdesc, String ocultamenu, String encerrado, Integer status, List<FsMdfeDoc> fsmdfedocitems,
			List<FsMdfePart> fsmdfepartitems, List<FsMdfePerc> fsmdfepercitems, List<FsMdfeReboq> fsmdfereboqitems,
			List<FsMdfeAut> fsmdfeautitems, List<FsMdfeEvento> fsmdfeeventos, List<FsMdfeAverb> fsmdfeaverbitems,
			List<FsMdfeContr> fsmdfecontritems, List<FsMdfePag> fsmdfepagitems) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.tipo = tipo;
		this.cuf = cuf;
		this.tpamb = tpamb;
		this.tpemit = tpemit;
		this.tptransp = tptransp;
		this.modelo = modelo;
		this.serie = serie;
		this.nmdf = nmdf;
		this.cmdf = cmdf;
		this.chaveac = chaveac;
		this.cdv = cdv;
		this.modal = modal;
		this.dhemi = dhemi;
		this.dhemihr = dhemihr;
		this.tpemis = tpemis;
		this.procemi = procemi;
		this.verproc = verproc;
		this.cdcidadeini = cdcidadeini;
		this.cdestadoini = cdestadoini;
		this.cdestadofim = cdestadofim;
		this.qcte = qcte;
		this.qnfe = qnfe;
		this.qmdfe = qmdfe;
		this.vcarga = vcarga;
		this.cunid = cunid;
		this.qcarga = qcarga;
		this.infadfisco = infadfisco;
		this.infcpl = infcpl;
		this.xml = xml;
		this.nprot = nprot;
		this.nrecibo = nrecibo;
		this.st_imp = st_imp;
		this.cdpessoaseg = cdpessoaseg;
		this.respseg = respseg;
		this.cdveiculo = cdveiculo;
		this.v_placa = v_placa;
		this.v_ufplaca = v_ufplaca;
		this.v_antt = v_antt;
		this.v_tara = v_tara;
		this.ciot = ciot;
		this.ciot_cpfcnpj = ciot_cpfcnpj;
		this.pp_tipo = pp_tipo;
		this.pp_nome = pp_nome;
		this.pp_cean = pp_cean;
		this.pp_ncm = pp_ncm;
		this.pp_lotacao = pp_lotacao;
		this.pp_cepcar = pp_cepcar;
		this.pp_cepdesc = pp_cepdesc;
		this.ocultamenu = ocultamenu;
		this.encerrado = encerrado;
		this.status = status;
		this.fsmdfedocitems = fsmdfedocitems;
		this.fsmdfepartitems = fsmdfepartitems;
		this.fsmdfepercitems = fsmdfepercitems;
		this.fsmdfereboqitems = fsmdfereboqitems;
		this.fsmdfeautitems = fsmdfeautitems;
		this.fsmdfeeventos = fsmdfeeventos;
		this.fsmdfeaverbitems = fsmdfeaverbitems;
		this.fsmdfecontritems = fsmdfecontritems;
		this.fsmdfepagitems = fsmdfepagitems;
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

	public Integer getCuf() {
		return cuf;
	}

	public void setCuf(Integer cuf) {
		this.cuf = cuf;
	}

	public Integer getTpamb() {
		return tpamb;
	}

	public void setTpamb(Integer tpamb) {
		this.tpamb = tpamb;
	}

	public Integer getTpemit() {
		return tpemit;
	}

	public void setTpemit(Integer tpemit) {
		this.tpemit = tpemit;
	}

	public Integer getTptransp() {
		return tptransp;
	}

	public void setTptransp(Integer tptransp) {
		this.tptransp = tptransp;
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

	public Integer getNmdf() {
		return nmdf;
	}

	public void setNmdf(Integer nmdf) {
		this.nmdf = nmdf;
	}

	public Integer getCmdf() {
		return cmdf;
	}

	public void setCmdf(Integer cmdf) {
		this.cmdf = cmdf;
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

	public String getModal() {
		return modal;
	}

	public void setModal(String modal) {
		this.modal = modal;
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

	public Integer getTpemis() {
		return tpemis;
	}

	public void setTpemis(Integer tpemis) {
		this.tpemis = tpemis;
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

	public CdCidade getCdcidadeini() {
		return cdcidadeini;
	}

	public void setCdcidadeini(CdCidade cdcidadeini) {
		this.cdcidadeini = cdcidadeini;
	}

	public CdEstado getCdestadoini() {
		return cdestadoini;
	}

	public void setCdestadoini(CdEstado cdestadoini) {
		this.cdestadoini = cdestadoini;
	}

	public CdEstado getCdestadofim() {
		return cdestadofim;
	}

	public void setCdestadofim(CdEstado cdestadofim) {
		this.cdestadofim = cdestadofim;
	}

	public void setQcte(Integer qcte) {
		this.qcte = qcte;
	}

	public Integer getQcte() {
		Integer calc = 0;
		if (!fsmdfedocitems.isEmpty()) {
			for (FsMdfeDoc item : fsmdfedocitems) {
				if (item.getTipo().equals("C")) {
					calc++;
				}
			}
		}
		return qcte = calc;
	}

	public void setQcte() {
		Integer calc = 0;
		if (!fsmdfedocitems.isEmpty()) {
			for (FsMdfeDoc item : fsmdfedocitems) {
				if (item.getTipo().equals("C")) {
					calc++;
				}
			}
		}
		this.qcte = calc;
	}

	public void setQnfe(Integer qnfe) {
		this.qnfe = qnfe;
	}

	public Integer getQnfe() {
		Integer calc = 0;
		if (!fsmdfedocitems.isEmpty()) {
			for (FsMdfeDoc item : fsmdfedocitems) {
				if (item.getTipo().equals("N")) {
					calc++;
				}
			}
		}
		return qnfe = calc;
	}

	public void setQnfe() {
		Integer calc = 0;
		if (!fsmdfedocitems.isEmpty()) {
			for (FsMdfeDoc item : fsmdfedocitems) {
				if (item.getTipo().equals("N")) {
					calc++;
				}
			}
		}
		this.qnfe = calc;
	}

	public Integer getQmdfe() {
		return qmdfe;
	}

	public void setQmdfe(Integer qmdfe) {
		this.qmdfe = qmdfe;
	}

	public void setVcarga(BigDecimal vcarga) {
		this.vcarga = vcarga;
	}

	public BigDecimal getVcarga() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsmdfedocitems.isEmpty()) {
			for (FsMdfeDoc item : fsmdfedocitems) {
				calc = calc.add(item.getValor());
			}
		}
		return vcarga = calc;
	}

	public void setVcarga() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsmdfedocitems.isEmpty()) {
			for (FsMdfeDoc item : fsmdfedocitems) {
				calc = calc.add(item.getValor());
			}
		}
		this.vcarga = calc;
	}

	public String getCunid() {
		return cunid;
	}

	public void setCunid(String cunid) {
		this.cunid = cunid;
	}

	public void setQcarga(BigDecimal qcarga) {
		this.qcarga = qcarga;
	}

	public BigDecimal getQcarga() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsmdfedocitems.isEmpty()) {
			for (FsMdfeDoc item : fsmdfedocitems) {
				calc = calc.add(item.getPeso());
			}
		}
		return qcarga = calc;
	}

	public void setQcarga() {
		BigDecimal calc = new BigDecimal(0);
		if (!fsmdfedocitems.isEmpty()) {
			for (FsMdfeDoc item : fsmdfedocitems) {
				calc = calc.add(item.getPeso());
			}
		}
		this.qcarga = calc;
	}

	public String getInfadfisco() {
		return infadfisco;
	}

	public void setInfadfisco(String infadfisco) {
		this.infadfisco = infadfisco;
	}

	public String getInfcpl() {
		return infcpl;
	}

	public void setInfcpl(String infcpl) {
		this.infcpl = infcpl;
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

	public String getSt_imp() {
		return st_imp;
	}

	public void setSt_imp(String st_imp) {
		this.st_imp = st_imp;
	}

	public CdPessoa getCdpessoaseg() {
		return cdpessoaseg;
	}

	public void setCdpessoaseg(CdPessoa cdpessoaseg) {
		this.cdpessoaseg = cdpessoaseg;
	}
	
	public Integer getRespseg() {
		return respseg;
	}

	public void setRespseg(Integer respseg) {
		this.respseg = respseg;
	}

	public CdVeiculo getCdveiculo() {
		return cdveiculo;
	}

	public void setCdveiculo(CdVeiculo cdveiculo) {
		this.cdveiculo = cdveiculo;
	}

	public String getV_placa() {
		return v_placa;
	}

	public void setV_placa(String v_placa) {
		this.v_placa = v_placa;
	}

	public String getV_ufplaca() {
		return v_ufplaca;
	}

	public void setV_ufplaca(String v_ufplaca) {
		this.v_ufplaca = v_ufplaca;
	}

	public String getV_antt() {
		return v_antt;
	}

	public void setV_antt(String v_antt) {
		this.v_antt = v_antt;
	}

	public String getV_tara() {
		return v_tara;
	}

	public void setV_tara(String v_tara) {
		this.v_tara = v_tara;
	}

	public String getCiot() {
		return ciot;
	}

	public void setCiot(String ciot) {
		this.ciot = ciot;
	}

	public String getCiot_cpfcnpj() {
		return ciot_cpfcnpj;
	}

	public void setCiot_cpfcnpj(String ciot_cpfcnpj) {
		this.ciot_cpfcnpj = ciot_cpfcnpj;
	}

	public String getPp_tipo() {
		return pp_tipo;
	}

	public void setPp_tipo(String pp_tipo) {
		this.pp_tipo = pp_tipo;
	}

	public String getPp_nome() {
		return pp_nome;
	}

	public void setPp_nome(String pp_nome) {
		this.pp_nome = CaracterUtil.remUpper(pp_nome);
	}

	public String getPp_cean() {
		return pp_cean;
	}

	public void setPp_cean(String pp_cean) {
		this.pp_cean = pp_cean;
	}

	public String getPp_ncm() {
		return pp_ncm;
	}

	public void setPp_ncm(String pp_ncm) {
		this.pp_ncm = pp_ncm;
	}

	public Integer getPp_lotacao() {
		return pp_lotacao;
	}

	public void setPp_lotacao(Integer pp_lotacao) {
		this.pp_lotacao = pp_lotacao;
	}

	public String getPp_cepcar() {
		return pp_cepcar;
	}

	public void setPp_cepcar(String pp_cepcar) {
		this.pp_cepcar = pp_cepcar;
	}

	public String getPp_cepdesc() {
		return pp_cepdesc;
	}

	public void setPp_cepdesc(String pp_cepdesc) {
		this.pp_cepdesc = pp_cepdesc;
	}

	public String getOcultamenu() {
		return ocultamenu;
	}

	public void setOcultamenu(String ocultamenu) {
		this.ocultamenu = ocultamenu;
	}

	public String getEncerrado() {
		return encerrado;
	}

	public void setEncerrado(String encerrado) {
		this.encerrado = encerrado;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<FsMdfeDoc> getFsmdfedocitems() {
		return fsmdfedocitems;
	}

	public void setFsmdfedocitems(List<FsMdfeDoc> fsmdfedocitems) {
		this.fsmdfedocitems = fsmdfedocitems;
	}

	public List<FsMdfePart> getFsmdfepartitems() {
		return fsmdfepartitems;
	}

	public void setFsmdfepartitems(List<FsMdfePart> fsmdfepartitems) {
		this.fsmdfepartitems = fsmdfepartitems;
	}

	public List<FsMdfePerc> getFsmdfepercitems() {
		return fsmdfepercitems;
	}

	public void setFsmdfepercitems(List<FsMdfePerc> fsmdfepercitems) {
		this.fsmdfepercitems = fsmdfepercitems;
	}

	public List<FsMdfeReboq> getFsmdfereboqitems() {
		return fsmdfereboqitems;
	}

	public void setFsmdfereboqitems(List<FsMdfeReboq> fsmdfereboqitems) {
		this.fsmdfereboqitems = fsmdfereboqitems;
	}

	public List<FsMdfeAut> getFsmdfeautitems() {
		return fsmdfeautitems;
	}

	public void setFsmdfeautitems(List<FsMdfeAut> fsmdfeautitems) {
		this.fsmdfeautitems = fsmdfeautitems;
	}

	public List<FsMdfeEvento> getFsmdfeeventos() {
		return fsmdfeeventos;
	}

	public void setFsmdfeeventos(List<FsMdfeEvento> fsmdfeeventos) {
		this.fsmdfeeventos = fsmdfeeventos;
	}

	public List<FsMdfeAverb> getFsmdfeaverbitems() {
		return fsmdfeaverbitems;
	}

	public void setFsmdfeaverbitems(List<FsMdfeAverb> fsmdfeaverbitems) {
		this.fsmdfeaverbitems = fsmdfeaverbitems;
	}

	public List<FsMdfeContr> getFsmdfecontritems() {
		return fsmdfecontritems;
	}

	public void setFsmdfecontritems(List<FsMdfeContr> fsmdfecontritems) {
		this.fsmdfecontritems = fsmdfecontritems;
	}

	public List<FsMdfePag> getFsmdfepagitems() {
		return fsmdfepagitems;
	}

	public void setFsmdfepagitems(List<FsMdfePag> fsmdfepagitems) {
		this.fsmdfepagitems = fsmdfepagitems;
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
		FsMdfe other = (FsMdfe) obj;
		return Objects.equals(id, other.id);
	}
}
