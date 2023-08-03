package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fs_nfsepart")
public class FsNfsePart implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 1)
	private String tipo;
	@Column(length = 14)
	private String cpfcnpj;
	@Column(length = 20)
	private String ie;
	@Column(length = 60)
	private String xnome;
	@Column(length = 60)
	private String xfant;
	@Column(length = 10)
	private String tipoend;
	@Column(length = 60)
	private String xlgr;
	@Column(length = 60)
	private String nro;
	@Column(length = 60)
	private String xcpl;
	@Column(length = 60)
	private String xbairro;
	@Column(length = 7)
	private String cmun;
	@Column(length = 60)
	private String xmun;
	@Column(length = 2)
	private String uf;
	private Integer ufcod;
	@Column(length = 8)
	private String cep;
	@Column(length = 4)
	private String cpais;
	@Column(length = 6)
	private String xpais;
	@Column(length = 14)
	private String fone;
	@Column(length = 2)
	private String tipofone;
	@Column(length = 20)
	private String im;
	@Column(length = 1)
	private String stespecial;
	@Column(length = 1)
	private String regesptrib;
	@Column(length = 1)
	private String cadmun;
	@Column(length = 1)
	private String orgaopublico;
	@Column(length = 250)
	private String email;

	public FsNfsePart() {
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

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getXnome() {
		return xnome;
	}

	public void setXnome(String xnome) {
		this.xnome = CaracterUtil.remUpper(xnome);
	}

	public String getXfant() {
		return xfant;
	}

	public void setXfant(String xfant) {
		this.xfant = CaracterUtil.remUpper(xfant);
	}

	public String getTipoend() {
		return tipoend;
	}

	public void setTipoend(String tipoend) {
		this.tipoend = CaracterUtil.remUpper(tipoend);
	}

	public String getXlgr() {
		return xlgr;
	}

	public void setXlgr(String xlgr) {
		this.xlgr = CaracterUtil.remUpper(xlgr);
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = CaracterUtil.remUpper(nro);
	}

	public String getXcpl() {
		return xcpl;
	}

	public void setXcpl(String xcpl) {
		this.xcpl = CaracterUtil.remUpper(xcpl);
	}

	public String getXbairro() {
		return xbairro;
	}

	public void setXbairro(String xbairro) {
		this.xbairro = CaracterUtil.remUpper(xbairro);
	}

	public String getCmun() {
		return cmun;
	}

	public void setCmun(String cmun) {
		this.cmun = cmun;
	}

	public String getXmun() {
		return xmun;
	}

	public void setXmun(String xmun) {
		this.xmun = CaracterUtil.remUpper(xmun);
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getUfcod() {
		return ufcod;
	}

	public void setUfcod(Integer ufcod) {
		this.ufcod = ufcod;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCpais() {
		return cpais;
	}

	public void setCpais(String cpais) {
		this.cpais = cpais;
	}

	public String getXpais() {
		return xpais;
	}

	public void setXpais(String xpais) {
		this.xpais = CaracterUtil.remUpper(xpais);
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}
	
	public String getTipofone() {
		return tipofone;
	}

	public void setTipofone(String tipofone) {
		this.tipofone = CaracterUtil.remUpper(tipofone);
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getStespecial() {
		return stespecial;
	}

	public void setStespecial(String stespecial) {
		this.stespecial = stespecial;
	}

	public String getRegesptrib() {
		return regesptrib;
	}

	public void setRegesptrib(String regesptrib) {
		this.regesptrib = regesptrib;
	}

	public String getCadmun() {
		return cadmun;
	}

	public void setCadmun(String cadmun) {
		this.cadmun = cadmun;
	}

	public String getOrgaopublico() {
		return orgaopublico;
	}

	public void setOrgaopublico(String orgaopublico) {
		this.orgaopublico = orgaopublico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		FsNfsePart other = (FsNfsePart) obj;
		return Objects.equals(id, other.id);
	}
}
