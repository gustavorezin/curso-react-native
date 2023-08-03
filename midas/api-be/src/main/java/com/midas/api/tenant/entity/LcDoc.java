package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
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
@Table(name = "lc_doc")
public class LcDoc implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 2)
	private String tipo;
	private Long numero;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	private Time horacad = new Time(new Date().getTime());
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataem = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataprev = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datafat = new Date();
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@ManyToOne
	private CdPessoa cdpessoapara;
	@ManyToOne
	private CdPessoa cdpessoaentrega;
	@SuppressWarnings("unused")
	private Integer qtdit = 0;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal qtd = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vsub = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vdesc = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vtransp = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pdescext = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vdescext = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vtot = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcipi = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vipi = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcicmsst = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vicmsst = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcfcpst = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vfcpst = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vtottrib = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vtribcob = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcpis = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vpis = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcpisst = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vpisst = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbccofins = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vcofins = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbccofinsst = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vcofinsst = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcicms = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vicms = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbccredsn = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vcredsn = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsufdest = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vfcpufdest = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsufremet = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbciss = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal viss = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pcom = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vcom = BigDecimal.ZERO;
	@Column(length = 2)
	private String fpag;
	@ManyToOne
	private CdCondPag cdcondpag;
	@ManyToOne
	private CdCaixa cdcaixa;
	@ManyToOne
	private CdPessoa cdpessoatransp;
	@ManyToOne
	private CdPessoa cdpessoavendedor;
	@ManyToOne
	private CdNfeCfg cdnfecfg;
	@Column(length = 1)
	private String usacfgfiscal = "N";
	@Column(length = 400)
	private String motcan;
	@Column(length = 2)
	private String categoria;
	@Column(length = 2)
	private String modfrete = "9"; // 9 - Sem frete
	@Column(length = 8)
	private String vcplaca = "";
	@Column(length = 10)
	private String vcantt = "";
	@Column(length = 2)
	private String vcuf = "XX"; // XX - Nao informado
	@Column(length = 3500)
	private String info;
	@Column(length = 3500)
	private String infolocal;
	@Column(length = 2)
	private String tpdocfiscal = "00";
	private Long docfiscal = 0L;
	private Integer numnota = 0;
	private Long docfiscal_nfse = 0L;
	private Integer numnota_nfse = 0;
	@Column(length = 5000)
	private String infodocfiscal;
	@Digits(integer = 20, fraction = 8)
	private BigDecimal mpesol = BigDecimal.ZERO;
	@Digits(integer = 20, fraction = 8)
	private BigDecimal mpesob = BigDecimal.ZERO;
	@Digits(integer = 24, fraction = 8)
	private BigDecimal mmcub = BigDecimal.ZERO;
	@Column(length = 40)
	private String ordemcps;
	@Column(length = 1)
	private String cobauto = "N";
	@Column(length = 1)
	private String lcmesmomes = "N";
	private Integer cdplconmicro_id = 0;
	private Integer cdccusto_id = 0;
	private Integer diavencefixo = 0;
	@Column(length = 1)
	private String reservaest = "N";
	@Column(length = 1)
	private String ngparcela = "N";
	@Column(length = 2)
	private String exufsaida = "XX";
	@Column(length = 60)
	private String exlocalemb = "";
	@Column(length = 60)
	private String exlocaldesp;
	@Column(length = 22)
	private String cnemp;
	@Column(length = 60)
	private String cped;
	@Column(length = 60)
	private String ccont;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_nacional = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_importado = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_estadual = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_municipal = BigDecimal.ZERO;
	@Column(length = 1)
	private String ocultamenu = "S";
	@ManyToOne
	private CdProdutoTab cdprodutotab;
	@ManyToOne
	private CdVeiculo cdveiculo;
	@Column(length = 60)
	private String faladocom;
	@Column(length = 60)
	private String localatend;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal kmrodado = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vdesloca = BigDecimal.ZERO;
	@Column(length = 1)
	private String priori = "1";// BAIXA
	@ManyToOne
	private CdPessoa cdpessoatec;
	@Column(length = 60)
	private String nomenota = "CONSUMIDOR";
	@Column(length = 20)
	private String cpfcnpjnota = "00000000000";
	@Column(length = 1)
	private String boldoc = "N";
	@Column(length = 1)
	private String adminconf = "N";
	@Column(length = 45)
	private String descgeral = "";
	// Romaneios
	@Column(length = 2)
	private String tproma = "00";
	private Long lcroma = 0L;
	private Integer numroma = 0;
	@Column(length = 2)
	private String entregast = "0";
	private Long lcdocorig = 0L;
	private Long numdocorig = 0L;
	private Long lcdocdevo = 0L;
	private Long numdocdevo = 0L;
	@Column(length = 2)
	private String status;
	@JsonIgnoreProperties(value = { "lcdoc" }, allowSetters = true)
	@OneToMany(mappedBy = "lcdoc", orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.JOIN)
	@OrderBy("id ASC")
	private List<LcDocItem> lcdocitem = new ArrayList<LcDocItem>();
	@OneToMany(mappedBy = "lcdoc", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("vence ASC")
	// @Where(clause = "tipo = 'R'")
	private List<FnTitulo> lcdoctitulo = new ArrayList<FnTitulo>();
	@JsonIgnoreProperties(value = { "lcdoc" }, allowSetters = true)
	@OneToMany(mappedBy = "lcdoc", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<LcDocPed> lcdocpeditem = new ArrayList<LcDocPed>();
	@JsonIgnoreProperties(value = { "lcdoc" }, allowSetters = true)
	@OneToMany(mappedBy = "lcdoc", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<LcDocEquip> lcdocequipitem = new ArrayList<LcDocEquip>();
	@OneToMany(mappedBy = "lcdoc", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<LcDocNfref> lcdocnfref = new ArrayList<LcDocNfref>();

	public LcDoc() {
	}

	public LcDoc(Long id, String tipo, Long numero, Date datacad, Time horacad, Date dataat, Date dataem, Date dataprev,
			Date datafat, CdPessoa cdpessoaemp, CdPessoa cdpessoapara, CdPessoa cdpessoaentrega, Integer qtdit,
			@Digits(integer = 18, fraction = 6) BigDecimal qtd, @Digits(integer = 18, fraction = 6) BigDecimal vsub,
			@Digits(integer = 18, fraction = 6) BigDecimal vdesc,
			@Digits(integer = 18, fraction = 2) BigDecimal vtransp,
			@Digits(integer = 3, fraction = 2) BigDecimal pdescext,
			@Digits(integer = 12, fraction = 2) BigDecimal vdescext,
			@Digits(integer = 18, fraction = 6) BigDecimal vtot, @Digits(integer = 18, fraction = 6) BigDecimal vbcipi,
			@Digits(integer = 12, fraction = 6) BigDecimal vipi,
			@Digits(integer = 18, fraction = 6) BigDecimal vbcicmsst,
			@Digits(integer = 12, fraction = 6) BigDecimal vicmsst,
			@Digits(integer = 18, fraction = 6) BigDecimal vbcfcpst,
			@Digits(integer = 12, fraction = 6) BigDecimal vfcpst,
			@Digits(integer = 18, fraction = 6) BigDecimal vtottrib,
			@Digits(integer = 18, fraction = 6) BigDecimal vtribcob,
			@Digits(integer = 18, fraction = 6) BigDecimal vbcpis, @Digits(integer = 12, fraction = 2) BigDecimal vpis,
			@Digits(integer = 18, fraction = 6) BigDecimal vbcpisst,
			@Digits(integer = 12, fraction = 2) BigDecimal vpisst,
			@Digits(integer = 18, fraction = 6) BigDecimal vbccofins,
			@Digits(integer = 12, fraction = 2) BigDecimal vcofins,
			@Digits(integer = 18, fraction = 6) BigDecimal vbccofinsst,
			@Digits(integer = 12, fraction = 2) BigDecimal vcofinsst,
			@Digits(integer = 18, fraction = 6) BigDecimal vbcicms,
			@Digits(integer = 12, fraction = 2) BigDecimal vicms,
			@Digits(integer = 18, fraction = 6) BigDecimal vbccredsn,
			@Digits(integer = 12, fraction = 2) BigDecimal vcredsn,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsufdest,
			@Digits(integer = 15, fraction = 6) BigDecimal vfcpufdest,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsufremet,
			@Digits(integer = 18, fraction = 6) BigDecimal vbciss, @Digits(integer = 18, fraction = 6) BigDecimal viss,
			@Digits(integer = 3, fraction = 2) BigDecimal pcom, @Digits(integer = 12, fraction = 2) BigDecimal vcom,
			String fpag, CdCondPag cdcondpag, CdCaixa cdcaixa, CdPessoa cdpessoatransp, CdPessoa cdpessoavendedor,
			CdNfeCfg cdnfecfg, String usacfgfiscal, String motcan, String categoria, String modfrete, String vcplaca,
			String vcantt, String vcuf, String info, String infolocal, String tpdocfiscal, Long docfiscal,
			Integer numnota, Long docfiscal_nfse, Integer numnota_nfse, String infodocfiscal,
			@Digits(integer = 20, fraction = 8) BigDecimal mpesol,
			@Digits(integer = 20, fraction = 8) BigDecimal mpesob, @Digits(integer = 24, fraction = 8) BigDecimal mmcub,
			String ordemcps, String cobauto, String lcmesmomes, Integer cdplconmicro_id, Integer cdccusto_id,
			Integer diavencefixo, String reservaest, String ngparcela, String exufsaida, String exlocalemb,
			String exlocaldesp, String cnemp, String cped, String ccont,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_nacional,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_importado,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_estadual,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_municipal, String ocultamenu,
			CdProdutoTab cdprodutotab, CdVeiculo cdveiculo, String faladocom, String localatend,
			@Digits(integer = 12, fraction = 2) BigDecimal kmrodado,
			@Digits(integer = 12, fraction = 2) BigDecimal vdesloca, String priori, CdPessoa cdpessoatec,
			String nomenota, String cpfcnpjnota, String boldoc, String adminconf, String descgeral, String tproma,
			Long lcroma, Integer numroma, String entregast, Long lcdocorig, Long numdocorig, Long lcdocdevo,
			Long numdocdevo, String status, List<LcDocItem> lcdocitem, List<FnTitulo> lcdoctitulo,
			List<LcDocPed> lcdocpeditem, List<LcDocEquip> lcdocequipitem, List<LcDocNfref> lcdocnfref) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.numero = numero;
		this.datacad = datacad;
		this.horacad = horacad;
		this.dataat = dataat;
		this.dataem = dataem;
		this.dataprev = dataprev;
		this.datafat = datafat;
		this.cdpessoaemp = cdpessoaemp;
		this.cdpessoapara = cdpessoapara;
		this.cdpessoaentrega = cdpessoaentrega;
		this.qtdit = qtdit;
		this.qtd = qtd;
		this.vsub = vsub;
		this.vdesc = vdesc;
		this.vtransp = vtransp;
		this.pdescext = pdescext;
		this.vdescext = vdescext;
		this.vtot = vtot;
		this.vbcipi = vbcipi;
		this.vipi = vipi;
		this.vbcicmsst = vbcicmsst;
		this.vicmsst = vicmsst;
		this.vbcfcpst = vbcfcpst;
		this.vfcpst = vfcpst;
		this.vtottrib = vtottrib;
		this.vtribcob = vtribcob;
		this.vbcpis = vbcpis;
		this.vpis = vpis;
		this.vbcpisst = vbcpisst;
		this.vpisst = vpisst;
		this.vbccofins = vbccofins;
		this.vcofins = vcofins;
		this.vbccofinsst = vbccofinsst;
		this.vcofinsst = vcofinsst;
		this.vbcicms = vbcicms;
		this.vicms = vicms;
		this.vbccredsn = vbccredsn;
		this.vcredsn = vcredsn;
		this.vicmsufdest = vicmsufdest;
		this.vfcpufdest = vfcpufdest;
		this.vicmsufremet = vicmsufremet;
		this.vbciss = vbciss;
		this.viss = viss;
		this.pcom = pcom;
		this.vcom = vcom;
		this.fpag = fpag;
		this.cdcondpag = cdcondpag;
		this.cdcaixa = cdcaixa;
		this.cdpessoatransp = cdpessoatransp;
		this.cdpessoavendedor = cdpessoavendedor;
		this.cdnfecfg = cdnfecfg;
		this.usacfgfiscal = usacfgfiscal;
		this.motcan = motcan;
		this.categoria = categoria;
		this.modfrete = modfrete;
		this.vcplaca = vcplaca;
		this.vcantt = vcantt;
		this.vcuf = vcuf;
		this.info = info;
		this.infolocal = infolocal;
		this.tpdocfiscal = tpdocfiscal;
		this.docfiscal = docfiscal;
		this.numnota = numnota;
		this.docfiscal_nfse = docfiscal_nfse;
		this.numnota_nfse = numnota_nfse;
		this.infodocfiscal = infodocfiscal;
		this.mpesol = mpesol;
		this.mpesob = mpesob;
		this.mmcub = mmcub;
		this.ordemcps = ordemcps;
		this.cobauto = cobauto;
		this.lcmesmomes = lcmesmomes;
		this.cdplconmicro_id = cdplconmicro_id;
		this.cdccusto_id = cdccusto_id;
		this.diavencefixo = diavencefixo;
		this.reservaest = reservaest;
		this.ngparcela = ngparcela;
		this.exufsaida = exufsaida;
		this.exlocalemb = exlocalemb;
		this.exlocaldesp = exlocaldesp;
		this.cnemp = cnemp;
		this.cped = cped;
		this.ccont = ccont;
		this.vibpt_nacional = vibpt_nacional;
		this.vibpt_importado = vibpt_importado;
		this.vibpt_estadual = vibpt_estadual;
		this.vibpt_municipal = vibpt_municipal;
		this.ocultamenu = ocultamenu;
		this.cdprodutotab = cdprodutotab;
		this.cdveiculo = cdveiculo;
		this.faladocom = faladocom;
		this.localatend = localatend;
		this.kmrodado = kmrodado;
		this.vdesloca = vdesloca;
		this.priori = priori;
		this.cdpessoatec = cdpessoatec;
		this.nomenota = nomenota;
		this.cpfcnpjnota = cpfcnpjnota;
		this.boldoc = boldoc;
		this.adminconf = adminconf;
		this.descgeral = descgeral;
		this.tproma = tproma;
		this.lcroma = lcroma;
		this.numroma = numroma;
		this.entregast = entregast;
		this.lcdocorig = lcdocorig;
		this.numdocorig = numdocorig;
		this.lcdocdevo = lcdocdevo;
		this.numdocdevo = numdocdevo;
		this.status = status;
		this.lcdocitem = lcdocitem;
		this.lcdoctitulo = lcdoctitulo;
		this.lcdocpeditem = lcdocpeditem;
		this.lcdocequipitem = lcdocequipitem;
		this.lcdocnfref = lcdocnfref;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public Time getHoracad() {
		return horacad;
	}

	public void setHoracad(Time horacad) {
		this.horacad = horacad;
	}

	public Date getDataat() {
		return dataat;
	}

	public void setDataat(Date dataat) {
		this.dataat = dataat;
	}

	public Date getDataem() {
		return dataem;
	}

	public void setDataem(Date dataem) {
		this.dataem = dataem;
	}

	public Date getDataprev() {
		return dataprev;
	}

	public void setDataprev(Date dataprev) {
		this.dataprev = dataprev;
	}

	public Date getDatafat() {
		return datafat;
	}

	public void setDatafat(Date datafat) {
		this.datafat = datafat;
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public CdPessoa getCdpessoapara() {
		return cdpessoapara;
	}

	public void setCdpessoapara(CdPessoa cdpessoapara) {
		this.cdpessoapara = cdpessoapara;
	}

	public CdPessoa getCdpessoaentrega() {
		return cdpessoaentrega;
	}

	public void setCdpessoaentrega(CdPessoa cdpessoaentrega) {
		this.cdpessoaentrega = cdpessoaentrega;
	}

	public CdPessoa getCdpessoavendedor() {
		return cdpessoavendedor;
	}

	public void setCdpessoavendedor(CdPessoa cdpessoavendedor) {
		this.cdpessoavendedor = cdpessoavendedor;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getModfrete() {
		return modfrete;
	}

	public void setModfrete(String modfrete) {
		this.modfrete = modfrete;
	}

	public String getVcplaca() {
		return vcplaca;
	}

	public void setVcplaca(String vcplaca) {
		this.vcplaca = CaracterUtil.remUpper(vcplaca);
	}

	public String getVcantt() {
		return vcantt;
	}

	public void setVcantt(String vcantt) {
		this.vcantt = vcantt;
	}

	public String getVcuf() {
		return vcuf;
	}

	public void setVcuf(String vcuf) {
		this.vcuf = vcuf;
	}

	public void setQtdit(Integer qtdit) {
		this.qtdit = qtdit;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public void setVsub(BigDecimal vsub) {
		this.vsub = vsub;
	}

	public void setVdesc(BigDecimal vdesc) {
		this.vdesc = vdesc;
	}

	public void setVtot(BigDecimal vtot) {
		this.vtot = vtot;
	}

	public void setVbcipi(BigDecimal vbcipi) {
		this.vbcipi = vbcipi;
	}

	public void setVipi(BigDecimal vipi) {
		this.vipi = vipi;
	}

	public void setVbcicmsst(BigDecimal vbcicmsst) {
		this.vbcicmsst = vbcicmsst;
	}

	public void setVicmsst(BigDecimal vicmsst) {
		this.vicmsst = vicmsst;
	}

	public void setVtottrib(BigDecimal vtottrib) {
		this.vtottrib = vtottrib;
	}

	public void setVbcpis(BigDecimal vbcpis) {
		this.vbcpis = vbcpis;
	}

	public void setVpis(BigDecimal vpis) {
		this.vpis = vpis;
	}

	public void setVbcpisst(BigDecimal vbcpisst) {
		this.vbcpisst = vbcpisst;
	}

	public void setVpisst(BigDecimal vpisst) {
		this.vpisst = vpisst;
	}

	public void setVbccofins(BigDecimal vbccofins) {
		this.vbccofins = vbccofins;
	}

	public void setVcofins(BigDecimal vcofins) {
		this.vcofins = vcofins;
	}

	public void setVbccofinsst(BigDecimal vbccofinsst) {
		this.vbccofinsst = vbccofinsst;
	}

	public void setVcofinsst(BigDecimal vcofinsst) {
		this.vcofinsst = vcofinsst;
	}

	public void setVbcicms(BigDecimal vbcicms) {
		this.vbcicms = vbcicms;
	}

	public void setVicms(BigDecimal vicms) {
		this.vicms = vicms;
	}

	public void setVbccredsn(BigDecimal vbccredsn) {
		this.vbccredsn = vbccredsn;
	}

	public void setVcredsn(BigDecimal vcredsn) {
		this.vcredsn = vcredsn;
	}

	public Integer getQtdit() {
		return qtdit = lcdocitem.size();
	}

	public void setQtdit() {
		this.qtdit = lcdocitem.size();
	}

	public void setMpesol() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getMpesol());
			}
		}
		this.mpesol = calc;
	}

	public BigDecimal getMpesol() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getMpesol());
			}
		}
		return mpesol = calc;
	}

	public void setMpesol(BigDecimal mpesol) {
		this.mpesol = mpesol;
	}

	public void setMpesob() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getMpesob());
			}
		}
		this.mpesob = calc;
	}

	public BigDecimal getMpesob() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getMpesob());
			}
		}
		return mpesob = calc;
	}

	public void setMpesob(BigDecimal mpesob) {
		this.mpesob = mpesob;
	}

	public void setMmcub() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getMmcub());
			}
		}
		this.mmcub = calc;
	}

	public BigDecimal getMmcub() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getMmcub());
			}
		}
		return mmcub = calc;
	}

	public void setMmcub(BigDecimal mmcub) {
		this.mmcub = mmcub;
	}

	public BigDecimal getQtd() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getQtd());
			}
		}
		return qtd = calc;
	}

	public void setQtd() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getQtd());
			}
		}
		this.qtd = calc;
	}

	public BigDecimal getVsub() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVsub());
			}
		}
		return vsub = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVsub() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVsub());
			}
		}
		this.vsub = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVdesc() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVdesc());
			}
		}
		return vdesc = calc;
	}

	public void setVdesc() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVdesc());
			}
		}
		this.vdesc = calc;
	}

	public BigDecimal getVtransp() {
		return vtransp;
	}

	public void setVtransp(BigDecimal vtransp) {
		this.vtransp = vtransp;
	}

	public BigDecimal getVbcpis() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcpis());
			}
		}
		return this.vbcpis = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public void setVbcpis() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcpis());
			}
		}
		this.vbcpis = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public BigDecimal getVpis() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVpis());
			}
		}
		return this.vpis = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVpis() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVpis());
			}
		}
		this.vpis = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVbcpisst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcpisst());
			}
		}
		return this.vbcpisst = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public void setVbcpisst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcpisst());
			}
		}
		this.vbcpisst = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public BigDecimal getVpisst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVpisst());
			}
		}
		return this.vpisst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVpisst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVpisst());
			}
		}
		this.vpisst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVbccofins() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbccofins());
			}
		}
		return this.vbccofins = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public void setVbccofins() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbccofins());
			}
		}
		this.vbccofins = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public BigDecimal getVcofins() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVcofins());
			}
		}
		return this.vcofins = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVcofins() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVcofins());
			}
		}
		this.vcofins = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVbccofinsst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbccofinsst());
			}
		}
		return this.vbccofinsst = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public void setVbccofinsst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbccofinsst());
			}
		}
		this.vbccofinsst = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public BigDecimal getVcofinsst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVcofinsst());
			}
		}
		return this.vcofinsst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVcofinsst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVcofinsst());
			}
		}
		this.vcofinsst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVbcicms() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcicms());
			}
		}
		return this.vbcicms = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public void setVbcicms() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcicms());
			}
		}
		this.vbcicms = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public BigDecimal getVicms() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVicms());
			}
		}
		return this.vicms = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVicms() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVicms());
			}
		}
		this.vicms = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVbccredsn() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbccredsn());
			}
		}
		return this.vbccredsn = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public void setVbccredsn() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbccredsn());
			}
		}
		this.vbccredsn = calc.setScale(6, RoundingMode.HALF_UP);
	}

	public BigDecimal getVcredsn() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVcredsn());
			}
		}
		return this.vcredsn = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVcredsn() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVcredsn());
			}
		}
		this.vcredsn = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVicmsufdest() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVicmsufdest());
			}
		}
		return this.vicmsufdest = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVicmsufdest() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVicmsufdest());
			}
		}
		this.vicmsufdest = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVfcpufdest() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVfcpufdest());
			}
		}
		return this.vfcpufdest = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVfcpufdest() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVfcpufdest());
			}
		}
		this.vfcpufdest = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVicmsufremet() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVicmsufremet());
			}
		}
		return this.vicmsufremet = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVicmsufremet() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVicmsufremet());
			}
		}
		this.vicmsufremet = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVbciss() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbciss());
			}
		}
		return this.vbciss = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVbciss() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbciss());
			}
		}
		this.vbciss = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getViss() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getViss());
			}
		}
		return this.viss = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setViss() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getViss());
			}
		}
		this.viss = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getPcom() {
		return pcom;
	}

	public void setPcom(BigDecimal pcom) {
		this.pcom = pcom;
	}

	public BigDecimal getVcom() {
		return vcom;
	}

	public void setVcom(BigDecimal vcom) {
		this.vcom = vcom;
	}

	public BigDecimal getPdescext() {
		return pdescext;
	}

	public void setPdescext(BigDecimal pdescext) {
		this.pdescext = pdescext;
	}

	public BigDecimal getVdescext() {
		return vdescext;
	}

	public void setVdescext(BigDecimal vdescext) {
		this.vdescext = vdescext;
	}

	public BigDecimal getVtot() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVtot());
			}
		}
		return this.vtot = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVtot() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVtot());
			}
		}
		this.vtot = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVbcipi() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcipi());
			}
		}
		return this.vbcipi = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVbcipi() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcipi());
			}
		}
		this.vbcipi = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVipi() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVipi());
			}
		}
		return this.vipi = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVipi() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVipi());
			}
		}
		this.vipi = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVbcicmsst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcicmsst());
			}
		}
		return this.vbcicmsst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVbcicmsst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcicmsst());
			}
		}
		this.vbcicmsst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVicmsst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVicmsst());
			}
		}
		return this.vicmsst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVicmsst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVicmsst());
			}
		}
		this.vicmsst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVbcfcpst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcfcpst());
			}
		}
		return this.vbcfcpst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVbcfcpst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVbcfcpst());
			}
		}
		this.vbcfcpst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVfcpst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVfcpst());
			}
		}
		return this.vfcpst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVfcpst() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVfcpst());
			}
		}
		this.vfcpst = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVtottrib() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVtottrib());
			}
		}
		calc = calc.add(getVdesloca());
		calc = calc.subtract(getVdescext());
		return this.vtottrib = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public void setVtottrib() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVtottrib());
			}
		}
		calc = calc.add(getVdesloca());
		calc = calc.subtract(getVdescext());
		this.vtottrib = calc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVtribcob() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVtribcob());
			}
		}
		return this.vtribcob = calc;
	}

	public void setVtribcob() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVtribcob());
			}
		}
		this.vtribcob = calc;
	}

	public BigDecimal getVibpt_nacional() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVibpt_nacional());
			}
		}
		return this.vibpt_nacional = calc;
	}

	public void setVibpt_nacional() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVibpt_nacional());
			}
		}
		this.vibpt_nacional = calc;
	}

	public BigDecimal getVibpt_importado() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVibpt_importado());
			}
		}
		return this.vibpt_importado = calc;
	}

	public void setVibpt_importado() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVibpt_importado());
			}
		}
		this.vibpt_importado = calc;
	}

	public BigDecimal getVibpt_estadual() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVibpt_estadual());
			}
		}
		return this.vibpt_estadual = calc;
	}

	public void setVibpt_estadual() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVibpt_estadual());
			}
		}
		this.vibpt_estadual = calc;
	}

	public BigDecimal getVibpt_municipal() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVibpt_municipal());
			}
		}
		return this.vibpt_municipal = calc;
	}

	public void setVibpt_municipal() {
		BigDecimal calc = BigDecimal.ZERO;
		if (!lcdocitem.isEmpty()) {
			for (LcDocItem item : lcdocitem) {
				calc = calc.add(item.getVibpt_municipal());
			}
		}
		this.vibpt_municipal = calc;
	}

	public void setVtribcob(BigDecimal vtribcob) {
		this.vtribcob = vtribcob;
	}

	public String getFpag() {
		return fpag;
	}

	public void setFpag(String fpag) {
		this.fpag = fpag;
	}

	public CdCondPag getCdcondpag() {
		return cdcondpag;
	}

	public void setCdcondpag(CdCondPag cdcondpag) {
		this.cdcondpag = cdcondpag;
	}

	public CdCaixa getCdcaixa() {
		return cdcaixa;
	}

	public void setCdcaixa(CdCaixa cdcaixa) {
		this.cdcaixa = cdcaixa;
	}

	public CdPessoa getCdpessoatransp() {
		return cdpessoatransp;
	}

	public void setCdpessoatransp(CdPessoa cdpessoatransp) {
		this.cdpessoatransp = cdpessoatransp;
	}

	public String getMotcan() {
		return motcan;
	}

	public void setMotcan(String motcan) {
		this.motcan = CaracterUtil.remUpper(motcan);
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfolocal() {
		return infolocal;
	}

	public void setInfolocal(String infolocal) {
		this.infolocal = infolocal;
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

	public Long getDocfiscal_nfse() {
		return docfiscal_nfse;
	}

	public void setDocfiscal_nfse(Long docfiscal_nfse) {
		this.docfiscal_nfse = docfiscal_nfse;
	}

	public Integer getNumnota_nfse() {
		return numnota_nfse;
	}

	public void setNumnota_nfse(Integer numnota_nfse) {
		this.numnota_nfse = numnota_nfse;
	}

	public String getInfodocfiscal() {
		return infodocfiscal;
	}

	public void setInfodocfiscal(String infodocfiscal) {
		this.infodocfiscal = CaracterUtil.rem(infodocfiscal);
	}

	public void setVbcfcpst(BigDecimal vbcfcpst) {
		this.vbcfcpst = vbcfcpst;
	}

	public void setVfcpst(BigDecimal vfcpst) {
		this.vfcpst = vfcpst;
	}

	public void setVicmsufdest(BigDecimal vicmsufdest) {
		this.vicmsufdest = vicmsufdest;
	}

	public void setVfcpufdest(BigDecimal vfcpufdest) {
		this.vfcpufdest = vfcpufdest;
	}

	public void setVicmsufremet(BigDecimal vicmsufremet) {
		this.vicmsufremet = vicmsufremet;
	}

	public void setVbciss(BigDecimal vbciss) {
		this.vbciss = vbciss;
	}

	public void setViss(BigDecimal viss) {
		this.viss = viss;
	}

	public String getOrdemcps() {
		return ordemcps;
	}

	public void setOrdemcps(String ordemcps) {
		this.ordemcps = ordemcps;
	}

	public String getCobauto() {
		return cobauto;
	}

	public void setCobauto(String cobauto) {
		this.cobauto = cobauto;
	}

	public String getLcmesmomes() {
		return lcmesmomes;
	}

	public void setLcmesmomes(String lcmesmomes) {
		this.lcmesmomes = lcmesmomes;
	}

	public Integer getCdplconmicro_id() {
		return cdplconmicro_id;
	}

	public void setCdplconmicro_id(Integer cdplconmicro_id) {
		this.cdplconmicro_id = cdplconmicro_id;
	}

	public Integer getCdccusto_id() {
		return cdccusto_id;
	}

	public void setCdccusto_id(Integer cdccusto_id) {
		this.cdccusto_id = cdccusto_id;
	}

	public Integer getDiavencefixo() {
		return diavencefixo;
	}

	public void setDiavencefixo(Integer diavencefixo) {
		this.diavencefixo = diavencefixo;
	}

	public String getReservaest() {
		return reservaest;
	}

	public void setReservaest(String reservaest) {
		this.reservaest = reservaest;
	}

	public String getNgparcela() {
		return ngparcela;
	}

	public void setNgparcela(String ngparcela) {
		this.ngparcela = ngparcela;
	}

	public String getExufsaida() {
		return exufsaida;
	}

	public void setExufsaida(String exufsaida) {
		this.exufsaida = exufsaida;
	}

	public String getExlocalemb() {
		return exlocalemb;
	}

	public void setExlocalemb(String exlocalemb) {
		this.exlocalemb = CaracterUtil.remUpper(exlocalemb);
	}

	public String getExlocaldesp() {
		return exlocaldesp;
	}

	public void setExlocaldesp(String exlocaldesp) {
		this.exlocaldesp = CaracterUtil.remUpper(exlocaldesp);
	}

	public String getCnemp() {
		return cnemp;
	}

	public void setCnemp(String cnemp) {
		this.cnemp = CaracterUtil.remUpper(cnemp);
	}

	public String getCped() {
		return cped;
	}

	public void setCped(String cped) {
		this.cped = CaracterUtil.remUpper(cped);
	}

	public String getCcont() {
		return ccont;
	}

	public void setCcont(String ccont) {
		this.ccont = CaracterUtil.remUpper(ccont);
	}

	public String getOcultamenu() {
		return ocultamenu;
	}

	public void setOcultamenu(String ocultamenu) {
		this.ocultamenu = ocultamenu;
	}

	public CdProdutoTab getCdprodutotab() {
		return cdprodutotab;
	}

	public void setCdprodutotab(CdProdutoTab cdprodutotab) {
		this.cdprodutotab = cdprodutotab;
	}

	public CdVeiculo getCdveiculo() {
		return cdveiculo;
	}

	public void setCdveiculo(CdVeiculo cdveiculo) {
		this.cdveiculo = cdveiculo;
	}

	public String getFaladocom() {
		return faladocom;
	}

	public void setFaladocom(String faladocom) {
		this.faladocom = CaracterUtil.remUpper(faladocom);
	}

	public String getLocalatend() {
		return localatend;
	}

	public void setLocalatend(String localatend) {
		this.localatend = CaracterUtil.remUpper(localatend);
	}

	public BigDecimal getKmrodado() {
		return kmrodado;
	}

	public void setKmrodado(BigDecimal kmrodado) {
		this.kmrodado = kmrodado;
	}

	public BigDecimal getVdesloca() {
		return vdesloca;
	}

	public void setVdesloca(BigDecimal vdesloca) {
		this.vdesloca = vdesloca;
	}

	public String getPriori() {
		return priori;
	}

	public void setPriori(String priori) {
		this.priori = priori;
	}

	public CdPessoa getCdpessoatec() {
		return cdpessoatec;
	}

	public void setCdpessoatec(CdPessoa cdpessoatec) {
		this.cdpessoatec = cdpessoatec;
	}

	public String getNomenota() {
		return nomenota;
	}

	public void setNomenota(String nomenota) {
		this.nomenota = CaracterUtil.remUpper(nomenota);
	}

	public String getCpfcnpjnota() {
		return cpfcnpjnota;
	}

	public void setCpfcnpjnota(String cpfcnpjnota) {
		this.cpfcnpjnota = cpfcnpjnota;
	}

	public String getBoldoc() {
		return boldoc;
	}

	public void setBoldoc(String boldoc) {
		this.boldoc = boldoc;
	}

	public String getAdminconf() {
		return adminconf;
	}

	public void setAdminconf(String adminconf) {
		this.adminconf = adminconf;
	}

	public String getDescgeral() {
		return descgeral;
	}

	public void setDescgeral(String descgeral) {
		this.descgeral = CaracterUtil.remUpper(descgeral);
	}

	public String getTproma() {
		return tproma;
	}

	public void setTproma(String tproma) {
		this.tproma = tproma;
	}

	public Long getLcroma() {
		return lcroma;
	}

	public void setLcroma(Long lcroma) {
		this.lcroma = lcroma;
	}

	public Integer getNumroma() {
		return numroma;
	}

	public void setNumroma(Integer numroma) {
		this.numroma = numroma;
	}

	public String getEntregast() {
		return entregast;
	}

	public void setEntregast(String entregast) {
		this.entregast = entregast;
	}

	public Long getLcdocorig() {
		return lcdocorig;
	}

	public void setLcdocorig(Long lcdocorig) {
		this.lcdocorig = lcdocorig;
	}

	public Long getNumdocorig() {
		return numdocorig;
	}

	public void setNumdocorig(Long numdocorig) {
		this.numdocorig = numdocorig;
	}

	public Long getLcdocdevo() {
		return lcdocdevo;
	}

	public void setLcdocdevo(Long lcdocdevo) {
		this.lcdocdevo = lcdocdevo;
	}

	public Long getNumdocdevo() {
		return numdocdevo;
	}

	public void setNumdocdevo(Long numdocdevo) {
		this.numdocdevo = numdocdevo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<LcDocItem> getLcdocitem() {
		return lcdocitem;
	}

	public void setLcdocitem(List<LcDocItem> lcdocitem) {
		this.lcdocitem = lcdocitem;
	}

	public List<FnTitulo> getLcdoctitulo() {
		return lcdoctitulo;
	}

	public void setLcdoctitulo(List<FnTitulo> lcdoctitulo) {
		this.lcdoctitulo = lcdoctitulo;
	}

	public List<LcDocPed> getLcdocpeditem() {
		return lcdocpeditem;
	}

	public void setLcdocpeditem(List<LcDocPed> lcdocpeditem) {
		this.lcdocpeditem = lcdocpeditem;
	}

	public List<LcDocEquip> getLcdocequipitem() {
		return lcdocequipitem;
	}

	public void setLcdocequipitem(List<LcDocEquip> lcdocequipitem) {
		this.lcdocequipitem = lcdocequipitem;
	}

	public List<LcDocNfref> getLcdocnfref() {
		return lcdocnfref;
	}

	public void setLcdocnfref(List<LcDocNfref> lcdocnfref) {
		this.lcdocnfref = lcdocnfref;
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
		LcDoc other = (LcDoc) obj;
		return Objects.equals(id, other.id);
	}
}
