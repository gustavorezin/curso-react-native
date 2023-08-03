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

import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_caixa")
public class CdCaixa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 2)
	private String tipo;
	@Column(length = 60)
	private String nome;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 20)
	private String cpfcnpj;
	@Column(length = 60)
	private String rua;
	@Column(length = 20)
	private String num;
	@Column(length = 60)
	private String bairro;
	@ManyToOne
	private CdCidade cdcidade;
	@ManyToOne
	private CdEstado cdestado;
	@Column(length = 4)
	private String codpais = "1058";
	@Column(length = 8)
	private String status;

	public CdCaixa() {
	}

	public CdCaixa(Integer id, String tipo, String nome, CdPessoa cdpessoaemp, String cpfcnpj, String rua, String num,
			String bairro, CdCidade cdcidade, CdEstado cdestado, String codpais, String status) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.cdpessoaemp = cdpessoaemp;
		this.cpfcnpj = cpfcnpj;
		this.rua = rua;
		this.num = num;
		this.bairro = bairro;
		this.cdcidade = cdcidade;
		this.cdestado = cdestado;
		this.codpais = codpais;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = CaracterUtil.remUpper(rua);
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = CaracterUtil.remUpper(num);
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = CaracterUtil.remUpper(bairro);
	}

	public CdCidade getCdcidade() {
		return cdcidade;
	}

	public void setCdcidade(CdCidade cdcidade) {
		this.cdcidade = cdcidade;
	}

	public CdEstado getCdestado() {
		return cdestado;
	}

	public void setCdestado(CdEstado cdestado) {
		this.cdestado = cdestado;
	}

	public String getCodpais() {
		return codpais;
	}

	public void setCodpais(String codpais) {
		this.codpais = codpais;
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
		CdCaixa other = (CdCaixa) obj;
		return Objects.equals(id, other.id);
	}
}
