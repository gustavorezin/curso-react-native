package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class LcDocDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String tipo;
	private Long numero;
	private Date datacad = new Date();
	private Time horacad = new Time(new Date().getTime());
	private Date dataat = new Date();
	private Date dataem = new Date();
	private Date dataprev = new Date();
	private Date datafat = new Date();
	private CdPessoa cdpessoaemp;
	private CdPessoa cdpessoapara;
	private Integer qtdit = 0;
	private BigDecimal qtd = BigDecimal.ZERO;
	private BigDecimal vsub = BigDecimal.ZERO;
	private BigDecimal vdesc = BigDecimal.ZERO;
	private BigDecimal vtransp = BigDecimal.ZERO;
	private BigDecimal pdescext = BigDecimal.ZERO;
	private BigDecimal vdescext = BigDecimal.ZERO;
	private BigDecimal vtot = BigDecimal.ZERO;
	private BigDecimal vbcipi = BigDecimal.ZERO;
	private BigDecimal vipi = BigDecimal.ZERO;
	private BigDecimal vbcicmsst = BigDecimal.ZERO;
	private BigDecimal vicmsst = BigDecimal.ZERO;
	private BigDecimal vbcfcpst = BigDecimal.ZERO;
	private BigDecimal vfcpst = BigDecimal.ZERO;
	private BigDecimal vtottrib = BigDecimal.ZERO;
	private BigDecimal vtribcob = BigDecimal.ZERO;
	private BigDecimal vbcpis = BigDecimal.ZERO;
	private BigDecimal vpis = BigDecimal.ZERO;
	private BigDecimal vbcpisst = BigDecimal.ZERO;
	private BigDecimal vpisst = BigDecimal.ZERO;
	private BigDecimal vbccofins = BigDecimal.ZERO;
	private BigDecimal vcofins = BigDecimal.ZERO;
	private BigDecimal vbccofinsst = BigDecimal.ZERO;
	private BigDecimal vcofinsst = BigDecimal.ZERO;
	private BigDecimal vbcicms = BigDecimal.ZERO;
	private BigDecimal vicms = BigDecimal.ZERO;
	private BigDecimal vbccredsn = BigDecimal.ZERO;
	private BigDecimal vcredsn = BigDecimal.ZERO;
	private BigDecimal vicmsufdest = BigDecimal.ZERO;
	private BigDecimal vfcpufdest = BigDecimal.ZERO;
	private BigDecimal vicmsufremet = BigDecimal.ZERO;
	private BigDecimal vbciss = BigDecimal.ZERO;
	private BigDecimal viss = BigDecimal.ZERO;
	private BigDecimal pcom = BigDecimal.ZERO;
	private BigDecimal vcom = BigDecimal.ZERO;
	private String fpag;
	private String usacfgfiscal = "N";
	private String motcan;
	private String categoria;
	private String modfrete = "9"; // 9 - Sem frete
	private String vcplaca = "";
	private String vcantt = "";
	private String vcuf = "XX"; // XX - Nao informado
	private String info;
	private String infolocal;
	private String tpdocfiscal = "00";
	private Long docfiscal = 0L;
	private Integer numnota = 0;
	private Long docfiscal_nfse = 0L;
	private Integer numnota_nfse = 0;
	private String infodocfiscal;
	private BigDecimal mpesol = BigDecimal.ZERO;
	private BigDecimal mpesob = BigDecimal.ZERO;
	private BigDecimal mmcub = BigDecimal.ZERO;
	private String ordemcps;
	private String cobauto = "N";
	private String lcmesmomes = "N";
	private Integer cdplconmicro_id = 0;
	private Integer cdccusto_id = 0;
	private Integer diavencefixo = 0;
	private String reservaest = "N";
	private String ngparcela = "N";
	private String exufsaida = "XX";
	private String exlocalemb = "";
	private String exlocaldesp;
	private String cnemp;
	private String cped;
	private String ccont;
	private String faladocom;
	private String localatend;
	private BigDecimal kmrodado = BigDecimal.ZERO;
	private BigDecimal vdesloca = BigDecimal.ZERO;
	private String priori = "1";// BAIXA
	private CdPessoa cdpessoatec;
	private String nomenota = "CONSUMIDOR";
	private String cpfcnpjnota = "00000000000";
	private String boldoc = "N";
	private String adminconf = "N";
	private String descgeral = "";
	private CdVeiculo cdveiculo;
	// Romaneios
	private String tproma = "00";
	private Long lcroma = 0L;
	private Integer numroma = 0;
	private String entregast = "0";
	private Long lcdocorig;
	private Long numdocorig;
	private Long lcdocdevo;
	private Long numdocdevo;
	private String status;

	public LcDocDTO() {
	}

	public LcDocDTO(Long id, String tipo, Long numero, Date datacad, Time horacad, Date dataat, Date dataem,
			Date dataprev, Date datafat, CdPessoa cdpessoaemp, CdPessoa cdpessoapara, Integer qtdit, BigDecimal qtd,
			BigDecimal vsub, BigDecimal vdesc, BigDecimal vtransp, BigDecimal pdescext, BigDecimal vdescext,
			BigDecimal vtot, BigDecimal vbcipi, BigDecimal vipi, BigDecimal vbcicmsst, BigDecimal vicmsst,
			BigDecimal vbcfcpst, BigDecimal vfcpst, BigDecimal vtottrib, BigDecimal vtribcob, BigDecimal vbcpis,
			BigDecimal vpis, BigDecimal vbcpisst, BigDecimal vpisst, BigDecimal vbccofins, BigDecimal vcofins,
			BigDecimal vbccofinsst, BigDecimal vcofinsst, BigDecimal vbcicms, BigDecimal vicms, BigDecimal vbccredsn,
			BigDecimal vcredsn, BigDecimal vicmsufdest, BigDecimal vfcpufdest, BigDecimal vicmsufremet,
			BigDecimal vbciss, BigDecimal viss, BigDecimal pcom, BigDecimal vcom, String fpag, String usacfgfiscal,
			String motcan, String categoria, String modfrete, String vcplaca, String vcantt, String vcuf, String info,
			String infolocal, String tpdocfiscal, Long docfiscal, Integer numnota, Long docfiscal_nfse,
			Integer numnota_nfse, String infodocfiscal, BigDecimal mpesol, BigDecimal mpesob, BigDecimal mmcub,
			String ordemcps, String cobauto, String lcmesmomes, Integer cdplconmicro_id, Integer cdccusto_id,
			Integer diavencefixo, String reservaest, String ngparcela, String exufsaida, String exlocalemb,
			String exlocaldesp, String cnemp, String cped, String ccont, String faladocom, String localatend,
			BigDecimal kmrodado, BigDecimal vdesloca, String priori, CdPessoa cdpessoatec, String nomenota,
			String cpfcnpjnota, String boldoc, String adminconf, String descgeral, CdVeiculo cdveiculo, String tproma,
			Long lcroma, Integer numroma, String entregast, Long lcdocorig, Long numdocorig, Long lcdocdevo,
			Long numdocdevo, String status) {
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
		this.cdveiculo = cdveiculo;
		this.tproma = tproma;
		this.lcroma = lcroma;
		this.numroma = numroma;
		this.entregast = entregast;
		this.lcdocorig = lcdocorig;
		this.numdocorig = numdocorig;
		this.lcdocdevo = lcdocdevo;
		this.numdocdevo = numdocdevo;
		this.status = status;
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

	public Integer getQtdit() {
		return qtdit;
	}

	public void setQtdit(Integer qtdit) {
		this.qtdit = qtdit;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public BigDecimal getVsub() {
		return vsub;
	}

	public void setVsub(BigDecimal vsub) {
		this.vsub = vsub;
	}

	public BigDecimal getVdesc() {
		return vdesc;
	}

	public void setVdesc(BigDecimal vdesc) {
		this.vdesc = vdesc;
	}

	public BigDecimal getVtransp() {
		return vtransp;
	}

	public void setVtransp(BigDecimal vtransp) {
		this.vtransp = vtransp;
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
		return vtot;
	}

	public void setVtot(BigDecimal vtot) {
		this.vtot = vtot;
	}

	public BigDecimal getVbcipi() {
		return vbcipi;
	}

	public void setVbcipi(BigDecimal vbcipi) {
		this.vbcipi = vbcipi;
	}

	public BigDecimal getVipi() {
		return vipi;
	}

	public void setVipi(BigDecimal vipi) {
		this.vipi = vipi;
	}

	public BigDecimal getVbcicmsst() {
		return vbcicmsst;
	}

	public void setVbcicmsst(BigDecimal vbcicmsst) {
		this.vbcicmsst = vbcicmsst;
	}

	public BigDecimal getVicmsst() {
		return vicmsst;
	}

	public void setVicmsst(BigDecimal vicmsst) {
		this.vicmsst = vicmsst;
	}

	public BigDecimal getVbcfcpst() {
		return vbcfcpst;
	}

	public void setVbcfcpst(BigDecimal vbcfcpst) {
		this.vbcfcpst = vbcfcpst;
	}

	public BigDecimal getVfcpst() {
		return vfcpst;
	}

	public void setVfcpst(BigDecimal vfcpst) {
		this.vfcpst = vfcpst;
	}

	public BigDecimal getVtottrib() {
		return vtottrib;
	}

	public void setVtottrib(BigDecimal vtottrib) {
		this.vtottrib = vtottrib;
	}

	public BigDecimal getVtribcob() {
		return vtribcob;
	}

	public void setVtribcob(BigDecimal vtribcob) {
		this.vtribcob = vtribcob;
	}

	public BigDecimal getVbcpis() {
		return vbcpis;
	}

	public void setVbcpis(BigDecimal vbcpis) {
		this.vbcpis = vbcpis;
	}

	public BigDecimal getVpis() {
		return vpis;
	}

	public void setVpis(BigDecimal vpis) {
		this.vpis = vpis;
	}

	public BigDecimal getVbcpisst() {
		return vbcpisst;
	}

	public void setVbcpisst(BigDecimal vbcpisst) {
		this.vbcpisst = vbcpisst;
	}

	public BigDecimal getVpisst() {
		return vpisst;
	}

	public void setVpisst(BigDecimal vpisst) {
		this.vpisst = vpisst;
	}

	public BigDecimal getVbccofins() {
		return vbccofins;
	}

	public void setVbccofins(BigDecimal vbccofins) {
		this.vbccofins = vbccofins;
	}

	public BigDecimal getVcofins() {
		return vcofins;
	}

	public void setVcofins(BigDecimal vcofins) {
		this.vcofins = vcofins;
	}

	public BigDecimal getVbccofinsst() {
		return vbccofinsst;
	}

	public void setVbccofinsst(BigDecimal vbccofinsst) {
		this.vbccofinsst = vbccofinsst;
	}

	public BigDecimal getVcofinsst() {
		return vcofinsst;
	}

	public void setVcofinsst(BigDecimal vcofinsst) {
		this.vcofinsst = vcofinsst;
	}

	public BigDecimal getVbcicms() {
		return vbcicms;
	}

	public void setVbcicms(BigDecimal vbcicms) {
		this.vbcicms = vbcicms;
	}

	public BigDecimal getVicms() {
		return vicms;
	}

	public void setVicms(BigDecimal vicms) {
		this.vicms = vicms;
	}

	public BigDecimal getVbccredsn() {
		return vbccredsn;
	}

	public void setVbccredsn(BigDecimal vbccredsn) {
		this.vbccredsn = vbccredsn;
	}

	public BigDecimal getVcredsn() {
		return vcredsn;
	}

	public void setVcredsn(BigDecimal vcredsn) {
		this.vcredsn = vcredsn;
	}

	public BigDecimal getVicmsufdest() {
		return vicmsufdest;
	}

	public void setVicmsufdest(BigDecimal vicmsufdest) {
		this.vicmsufdest = vicmsufdest;
	}

	public BigDecimal getVfcpufdest() {
		return vfcpufdest;
	}

	public void setVfcpufdest(BigDecimal vfcpufdest) {
		this.vfcpufdest = vfcpufdest;
	}

	public BigDecimal getVicmsufremet() {
		return vicmsufremet;
	}

	public void setVicmsufremet(BigDecimal vicmsufremet) {
		this.vicmsufremet = vicmsufremet;
	}

	public BigDecimal getVbciss() {
		return vbciss;
	}

	public void setVbciss(BigDecimal vbciss) {
		this.vbciss = vbciss;
	}

	public BigDecimal getViss() {
		return viss;
	}

	public void setViss(BigDecimal viss) {
		this.viss = viss;
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

	public String getFpag() {
		return fpag;
	}

	public void setFpag(String fpag) {
		this.fpag = fpag;
	}

	public String getUsacfgfiscal() {
		return usacfgfiscal;
	}

	public void setUsacfgfiscal(String usacfgfiscal) {
		this.usacfgfiscal = usacfgfiscal;
	}

	public String getMotcan() {
		return motcan;
	}

	public void setMotcan(String motcan) {
		this.motcan = motcan;
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
		this.vcplaca = vcplaca;
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
		this.infodocfiscal = infodocfiscal;
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

	public BigDecimal getMmcub() {
		return mmcub;
	}

	public void setMmcub(BigDecimal mmcub) {
		this.mmcub = mmcub;
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
		this.exlocalemb = exlocalemb;
	}

	public String getExlocaldesp() {
		return exlocaldesp;
	}

	public void setExlocaldesp(String exlocaldesp) {
		this.exlocaldesp = exlocaldesp;
	}

	public String getCnemp() {
		return cnemp;
	}

	public void setCnemp(String cnemp) {
		this.cnemp = cnemp;
	}

	public String getCped() {
		return cped;
	}

	public void setCped(String cped) {
		this.cped = cped;
	}

	public String getCcont() {
		return ccont;
	}

	public void setCcont(String ccont) {
		this.ccont = ccont;
	}

	public String getFaladocom() {
		return faladocom;
	}

	public void setFaladocom(String faladocom) {
		this.faladocom = faladocom;
	}

	public String getLocalatend() {
		return localatend;
	}

	public void setLocalatend(String localatend) {
		this.localatend = localatend;
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
		this.nomenota = nomenota;
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
		this.descgeral = descgeral;
	}

	public CdVeiculo getCdveiculo() {
		return cdveiculo;
	}

	public void setCdveiculo(CdVeiculo cdveiculo) {
		this.cdveiculo = cdveiculo;
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
		LcDocDTO other = (LcDocDTO) obj;
		return Objects.equals(id, other.id);
	}
}
