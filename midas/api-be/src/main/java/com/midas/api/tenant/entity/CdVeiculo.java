package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_veiculo")
public class CdVeiculo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@Column(length = 7)
	private String placa;
	@Column(length = 11)
	private String renavam;
	@Column(length = 9)
	private String antt;
	@Column(length = 2)
	private String uf;
	@Column(length = 6)
	private String tara;
	@Column(length = 6)
	private String capkg;
	@Column(length = 6)
	private String capmc;
	@Column(length = 2)
	private String tprod;
	@Column(length = 2)
	private String tpcar;
	@Column(length = 14)
	private String pcpfcnpj;
	@Column(length = 60)
	private String pnome;
	@Column(length = 14)
	private String pie;
	@Column(length = 2)
	private String puf;
	@Column(length = 1)
	private String ptpprop;
	@Column(length = 60)
	private String marcadesc = "";
	@Column(length = 10)
	private String anomod = "";
	@Column(length = 20)
	private String cor = "";
	@Column(length = 40)
	private String nchassi = "";
	@Column(length = 40)
	private String nmotor = "";
	@Column(length = 40)
	private String tpcomb = "";
	@Column(length = 8)
	private String status;

	public CdVeiculo() {
	}

	public CdVeiculo(Integer id, CdPessoa cdpessoaemp, Date datacad, Date dataat, String placa, String renavam,
			String antt, String uf, String tara, String capkg, String capmc, String tprod, String tpcar,
			String pcpfcnpj, String pnome, String pie, String puf, String ptpprop, String marcadesc, String anomod,
			String cor, String nchassi, String nmotor, String tpcomb, String status) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.datacad = datacad;
		this.dataat = dataat;
		this.placa = placa;
		this.renavam = renavam;
		this.antt = antt;
		this.uf = uf;
		this.tara = tara;
		this.capkg = capkg;
		this.capmc = capmc;
		this.tprod = tprod;
		this.tpcar = tpcar;
		this.pcpfcnpj = pcpfcnpj;
		this.pnome = pnome;
		this.pie = pie;
		this.puf = puf;
		this.ptpprop = ptpprop;
		this.marcadesc = marcadesc;
		this.anomod = anomod;
		this.cor = cor;
		this.nchassi = nchassi;
		this.nmotor = nmotor;
		this.tpcomb = tpcomb;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
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

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = CaracterUtil.remUpper(placa);
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getAntt() {
		return antt;
	}

	public void setAntt(String antt) {
		this.antt = antt;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = CaracterUtil.remUpper(uf);
	}

	public String getTara() {
		return tara;
	}

	public void setTara(String tara) {
		this.tara = tara;
	}

	public String getCapkg() {
		return capkg;
	}

	public void setCapkg(String capkg) {
		this.capkg = capkg;
	}

	public String getCapmc() {
		return capmc;
	}

	public void setCapmc(String capmc) {
		this.capmc = capmc;
	}

	public String getTprod() {
		return tprod;
	}

	public void setTprod(String tprod) {
		this.tprod = tprod;
	}

	public String getTpcar() {
		return tpcar;
	}

	public void setTpcar(String tpcar) {
		this.tpcar = tpcar;
	}

	public String getPcpfcnpj() {
		return pcpfcnpj;
	}

	public void setPcpfcnpj(String pcpfcnpj) {
		this.pcpfcnpj = pcpfcnpj;
	}

	public String getPnome() {
		return pnome;
	}

	public void setPnome(String pnome) {
		this.pnome = CaracterUtil.remUpper(pnome);
	}

	public String getPie() {
		return pie;
	}

	public void setPie(String pie) {
		this.pie = pie;
	}

	public String getPuf() {
		return puf;
	}

	public void setPuf(String puf) {
		this.puf = CaracterUtil.remUpper(puf);
	}

	public String getPtpprop() {
		return ptpprop;
	}

	public void setPtpprop(String ptpprop) {
		this.ptpprop = ptpprop;
	}

	public String getMarcadesc() {
		return marcadesc;
	}

	public void setMarcadesc(String marcadesc) {
		this.marcadesc = CaracterUtil.remUpper(marcadesc);
	}

	public String getAnomod() {
		return anomod;
	}

	public void setAnomod(String anomod) {
		this.anomod = anomod;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = CaracterUtil.remUpper(cor);
	}

	public String getNchassi() {
		return nchassi;
	}

	public void setNchassi(String nchassi) {
		this.nchassi = CaracterUtil.rem(nchassi);
	}

	public String getNmotor() {
		return nmotor;
	}

	public void setNmotor(String nmotor) {
		this.nmotor = CaracterUtil.rem(nmotor);
	}

	public String getTpcomb() {
		return tpcomb;
	}

	public void setTpcomb(String tpcomb) {
		this.tpcomb = CaracterUtil.remUpper(tpcomb);
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
		CdVeiculo other = (CdVeiculo) obj;
		return Objects.equals(id, other.id);
	}
}
