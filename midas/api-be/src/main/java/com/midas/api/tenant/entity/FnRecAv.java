package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fn_recav")
public class FnRecAv implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@ManyToOne
	private CdPessoa cdpessoapara;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	@JsonFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date hora = new Date();
	@Column(length = 20)
	private String num;
	@Column(length = 160)
	private String ref;
	@Column(length = 160)
	private String fpag;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vsub = new BigDecimal(0);
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vdesc = new BigDecimal(0);
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vjuro = new BigDecimal(0);
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vtot = new BigDecimal(0);
	@Column(length = 1)
	private String tipo;

	public FnRecAv() {
	}

	public FnRecAv(Long id, CdPessoa cdpessoaemp, CdPessoa cdpessoapara, Date data, Date hora, String num, String ref,
			String fpag, @Digits(integer = 18, fraction = 2) BigDecimal vsub,
			@Digits(integer = 18, fraction = 2) BigDecimal vdesc, @Digits(integer = 18, fraction = 2) BigDecimal vjuro,
			@Digits(integer = 18, fraction = 2) BigDecimal vtot, String tipo) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cdpessoapara = cdpessoapara;
		this.data = data;
		this.hora = hora;
		this.num = num;
		this.ref = ref;
		this.fpag = fpag;
		this.vsub = vsub;
		this.vdesc = vdesc;
		this.vjuro = vjuro;
		this.vtot = vtot;
		this.tipo = tipo;
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

	public CdPessoa getCdpessoapara() {
		return cdpessoapara;
	}

	public void setCdpessoapara(CdPessoa cdpessoapara) {
		this.cdpessoapara = cdpessoapara;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = CaracterUtil.remUpper(ref);
	}

	public String getFpag() {
		return fpag;
	}

	public void setFpag(String fpag) {
		this.fpag = fpag;
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

	public BigDecimal getVjuro() {
		return vjuro;
	}

	public void setVjuro(BigDecimal vjuro) {
		this.vjuro = vjuro;
	}

	public BigDecimal getVtot() {
		return vtot;
	}

	public void setVtot(BigDecimal vtot) {
		this.vtot = vtot;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		FnRecAv other = (FnRecAv) obj;
		return Objects.equals(id, other.id);
	}
}
