package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fs_ctepart")
public class FsCtePart implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 1)
	private String tipo;
	@Column(length = 14)
	private String cpfcnpj;
	@Column(length = 14)
	private String ie;
	@Column(length = 14)
	private String iest;
	@Column(length = 9)
	private String isuf;
	@Column(length = 60)
	private String xnome;
	@Column(length = 60)
	private String xfant;
	@Column(length = 14)
	private String fone;
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
	@Column(length = 8)
	private String cep;
	@Column(length = 2)
	private String uf;
	@Column(length = 4)
	private String cpais;
	@Column(length = 6)
	private String xpais;
	@Column(length = 60)
	private String email;
	private Long idpessoa;

	public FsCtePart() {
	}

	public FsCtePart(Long id, String tipo, String cpfcnpj, String ie, String iest, String isuf, String xnome,
			String xfant, String fone, String xlgr, String nro, String xcpl, String xbairro, String cmun, String xmun,
			String cep, String uf, String cpais, String xpais, String email, Long idpessoa) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.cpfcnpj = cpfcnpj;
		this.ie = ie;
		this.iest = iest;
		this.isuf = isuf;
		this.xnome = xnome;
		this.xfant = xfant;
		this.fone = fone;
		this.xlgr = xlgr;
		this.nro = nro;
		this.xcpl = xcpl;
		this.xbairro = xbairro;
		this.cmun = cmun;
		this.xmun = xmun;
		this.cep = cep;
		this.uf = uf;
		this.cpais = cpais;
		this.xpais = xpais;
		this.email = email;
		this.idpessoa = idpessoa;
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

	public String getIest() {
		return iest;
	}

	public void setIest(String iest) {
		this.iest = iest;
	}

	public String getIsuf() {
		return isuf;
	}

	public void setIsuf(String isuf) {
		this.isuf = isuf;
	}

	public String getXnome() {
		return xnome;
	}

	public void setXnome(String xnome) {
		this.xnome = xnome;
	}

	public String getXfant() {
		return xfant;
	}

	public void setXfant(String xfant) {
		this.xfant = xfant;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getXlgr() {
		return xlgr;
	}

	public void setXlgr(String xlgr) {
		this.xlgr = xlgr;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public String getXcpl() {
		return xcpl;
	}

	public void setXcpl(String xcpl) {
		this.xcpl = xcpl;
	}

	public String getXbairro() {
		return xbairro;
	}

	public void setXbairro(String xbairro) {
		this.xbairro = xbairro;
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
		this.xmun = xmun;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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
		this.xpais = xpais;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdpessoa() {
		return idpessoa;
	}

	public void setIdpessoa(Long idpessoa) {
		this.idpessoa = idpessoa;
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
		FsCtePart other = (FsCtePart) obj;
		return Objects.equals(id, other.id);
	}
}
