package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_ctecfg")
public class CdCteCfg implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 4)
	private String cfop;
	@Column(length = 60)
	private String natop;
	@Column(length = 160)
	private String msgcont;
	@Column(length = 2)
	private String cst;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pibpt = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal icms_aliq = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal icms_redbc = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal icmsst_aliq = new BigDecimal(0);
	@Column(length = 8)
	private String status;

	public CdCteCfg() {
	}

	public CdCteCfg(Integer id, CdPessoa cdpessoaemp, String cfop, String natop, String msgcont, String cst,
			@Digits(integer = 3, fraction = 2) BigDecimal pibpt,
			@Digits(integer = 3, fraction = 2) BigDecimal icms_aliq,
			@Digits(integer = 3, fraction = 2) BigDecimal icms_redbc,
			@Digits(integer = 3, fraction = 2) BigDecimal icmsst_aliq, String status) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cfop = cfop;
		this.natop = natop;
		this.msgcont = msgcont;
		this.cst = cst;
		this.pibpt = pibpt;
		this.icms_aliq = icms_aliq;
		this.icms_redbc = icms_redbc;
		this.icmsst_aliq = icmsst_aliq;
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

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getNatop() {
		return natop;
	}

	public void setNatop(String natop) {
		this.natop = CaracterUtil.remUpper(natop);
	}

	public String getMsgcont() {
		return msgcont;
	}

	public void setMsgcont(String msgcont) {
		this.msgcont = CaracterUtil.remUpper(msgcont);
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public BigDecimal getPibpt() {
		return pibpt;
	}

	public void setPibpt(BigDecimal pibpt) {
		this.pibpt = pibpt;
	}

	public BigDecimal getIcms_aliq() {
		return icms_aliq;
	}

	public void setIcms_aliq(BigDecimal icms_aliq) {
		this.icms_aliq = icms_aliq;
	}

	public BigDecimal getIcms_redbc() {
		return icms_redbc;
	}

	public void setIcms_redbc(BigDecimal icms_redbc) {
		this.icms_redbc = icms_redbc;
	}

	public BigDecimal getIcmsst_aliq() {
		return icmsst_aliq;
	}

	public void setIcmsst_aliq(BigDecimal icmsst_aliq) {
		this.icmsst_aliq = icmsst_aliq;
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
		CdCteCfg other = (CdCteCfg) obj;
		return Objects.equals(id, other.id);
	}
}
