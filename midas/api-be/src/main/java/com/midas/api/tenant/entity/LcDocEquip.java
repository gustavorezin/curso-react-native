package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "lc_docequip")
public class LcDocEquip implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private LcDoc lcdoc;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	@Column(length = 60)
	private String descricao;
	@Column(length = 40)
	private String nserie;
	@Column(length = 60)
	private String acessorio;
	@Column(length = 60)
	private String defeito;

	public LcDocEquip() {
	}

	public LcDocEquip(Long id, LcDoc lcdoc, Date data, String descricao, String nserie, String acessorio,
			String defeito) {
		super();
		this.id = id;
		this.lcdoc = lcdoc;
		this.data = data;
		this.descricao = descricao;
		this.nserie = nserie;
		this.acessorio = acessorio;
		this.defeito = defeito;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LcDoc getLcdoc() {
		return lcdoc;
	}

	public void setLcdoc(LcDoc lcdoc) {
		this.lcdoc = lcdoc;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = CaracterUtil.remUpper(descricao);
	}

	public String getNserie() {
		return nserie;
	}

	public void setNserie(String nserie) {
		this.nserie = CaracterUtil.remUpper(nserie);
	}

	public String getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(String acessorio) {
		this.acessorio = CaracterUtil.remUpper(acessorio);
	}

	public String getDefeito() {
		return defeito;
	}

	public void setDefeito(String defeito) {
		this.defeito = CaracterUtil.remUpper(defeito);
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
		LcDocEquip other = (LcDocEquip) obj;
		return Objects.equals(id, other.id);
	}
}
