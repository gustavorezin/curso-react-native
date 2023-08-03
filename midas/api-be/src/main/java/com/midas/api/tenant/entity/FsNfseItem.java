package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fs_nfseitem")
public class FsNfseItem implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fsnfseitems" }, allowSetters = true)
	@ManyToOne
	private FsNfse fsnfse;
	private Integer nseq;
	@Column(length = 60)
	private String cprod;
	@Column(length = 120)
	private String xprod;
	@Column(length = 5)
	private String codserv;
	@Column(length = 10)
	private String unmed;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal qtd = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vunit = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vded = BigDecimal.ZERO;
	@Column(length = 2)
	private String tributavel;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vsub = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vdesc = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vtot = BigDecimal.ZERO;
	private Integer issretido;
	private Integer respretencao;
	@Column(length = 1)
	private String dedtp;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsNfseItemTrib fsnfseitemtrib;
	
	public FsNfseItem() {
	}
	
	public FsNfseItem(Long id, FsNfse fsnfse, Integer nseq, String cprod, String xprod, String codserv, String unmed,
			@Digits(integer = 16, fraction = 4) BigDecimal qtd, @Digits(integer = 16, fraction = 4) BigDecimal vunit,
			@Digits(integer = 16, fraction = 4) BigDecimal vded, String tributavel,
			@Digits(integer = 16, fraction = 4) BigDecimal vsub, @Digits(integer = 16, fraction = 4) BigDecimal vdesc,
			@Digits(integer = 16, fraction = 4) BigDecimal vtot, Integer issretido, Integer respretencao, String dedtp,
			FsNfseItemTrib fsnfseitemtrib) {
		super();
		this.id = id;
		this.fsnfse = fsnfse;
		this.nseq = nseq;
		this.cprod = cprod;
		this.xprod = xprod;
		this.codserv = codserv;
		this.unmed = unmed;
		this.qtd = qtd;
		this.vunit = vunit;
		this.vded = vded;
		this.tributavel = tributavel;
		this.vsub = vsub;
		this.vdesc = vdesc;
		this.vtot = vtot;
		this.issretido = issretido;
		this.respretencao = respretencao;
		this.dedtp = dedtp;
		this.fsnfseitemtrib = fsnfseitemtrib;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsNfse getFsnfse() {
		return fsnfse;
	}

	public void setFsnfse(FsNfse fsnfse) {
		this.fsnfse = fsnfse;
	}

	public Integer getNseq() {
		return nseq;
	}

	public void setNseq(Integer nseq) {
		this.nseq = nseq;
	}

	public String getCprod() {
		return cprod;
	}

	public void setCprod(String cprod) {
		this.cprod = CaracterUtil.remUpper(cprod);
	}

	public String getXprod() {
		return xprod;
	}

	public void setXprod(String xprod) {
		this.xprod = CaracterUtil.remUpper(xprod);
	}

	public String getCodserv() {
		return codserv;
	}

	public void setCodserv(String codserv) {
		this.codserv = codserv;
	}

	public String getUnmed() {
		return unmed;
	}

	public void setUnmed(String unmed) {
		this.unmed = CaracterUtil.remUpper(unmed);
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public BigDecimal getVunit() {
		return vunit;
	}

	public void setVunit(BigDecimal vunit) {
		this.vunit = vunit;
	}

	public BigDecimal getVded() {
		return vded;
	}

	public void setVded(BigDecimal vded) {
		this.vded = vded;
	}

	public String getTributavel() {
		return tributavel;
	}

	public void setTributavel(String tributavel) {
		this.tributavel = tributavel;
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

	public BigDecimal getVtot() {
		return vtot;
	}

	public void setVtot(BigDecimal vtot) {
		this.vtot = vtot;
	}

	public Integer getIssretido() {
		return issretido;
	}

	public void setIssretido(Integer issretido) {
		this.issretido = issretido;
	}

	public Integer getRespretencao() {
		return respretencao;
	}

	public void setRespretencao(Integer respretencao) {
		this.respretencao = respretencao;
	}

	public String getDedtp() {
		return dedtp;
	}

	public void setDedtp(String dedtp) {
		this.dedtp = dedtp;
	}

	public FsNfseItemTrib getFsnfseitemtrib() {
		return fsnfseitemtrib;
	}

	public void setFsnfseitemtrib(FsNfseItemTrib fsnfseitemtrib) {
		this.fsnfseitemtrib = fsnfseitemtrib;
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
		FsNfseItem other = (FsNfseItem) obj;
		return Objects.equals(id, other.id);
	}
}
