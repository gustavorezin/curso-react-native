package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "fs_mdfepart")
public class FsMdfePart implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fsmdfepartitems" }, allowSetters = true)
	@ManyToOne
	private FsMdfe fsmdfe;
	@Column(length = 1)
	private String tipo;
	@Column(length = 14)
	private String cpfcnpj;
	@Column(length = 14)
	private String ie;
	@Column(length = 60)
	private String xnome;
	@Column(length = 60)
	private String xfant;
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
	@Column(length = 12)
	private String fone;
	@Column(length = 60)
	private String email;

	public FsMdfePart() {
		super();
	}

	

	public FsMdfePart(Long id, FsMdfe fsmdfe, String tipo, String cpfcnpj, String ie, String xnome, String xfant,
			String xlgr, String nro, String xcpl, String xbairro, String cmun, String xmun, String cep, String uf,
			String fone, String email) {
		super();
		this.id = id;
		this.fsmdfe = fsmdfe;
		this.tipo = tipo;
		this.cpfcnpj = cpfcnpj;
		this.ie = ie;
		this.xnome = xnome;
		this.xfant = xfant;
		this.xlgr = xlgr;
		this.nro = nro;
		this.xcpl = xcpl;
		this.xbairro = xbairro;
		this.cmun = cmun;
		this.xmun = xmun;
		this.cep = cep;
		this.uf = uf;
		this.fone = fone;
		this.email = email;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public FsMdfe getFsmdfe() {
		return fsmdfe;
	}



	public void setFsmdfe(FsMdfe fsmdfe) {
		this.fsmdfe = fsmdfe;
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
		this.xnome = xnome;
	}

	public String getXfant() {
		return xfant;
	}

	public void setXfant(String xfant) {
		this.xfant = xfant;
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

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
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
		FsMdfePart other = (FsMdfePart) obj;
		return Objects.equals(id, other.id);
	}
}
