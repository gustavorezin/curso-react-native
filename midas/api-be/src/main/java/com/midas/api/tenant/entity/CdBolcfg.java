package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "cd_bolcfg")
public class CdBolcfg implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdCaixa cdcaixa;
	@Column(length = 5)
	private String banco;
	@Column(length = 10)
	private String tipo;
	@Column(length = 10)
	private String agencia;
	@Column(length = 5)
	private String agenciadv;
	@Column(length = 20)
	private String conta;
	@Column(length = 5)
	private String contadv;
	@Column(length = 15)
	private String codben;
	@Column(length = 20)
	private String codemp;
	@Column(length = 100)
	private String localpaginfo;
	@Column(length = 2)
	private String codprotesto;
	private Integer diasprotesto = 0;
	@Column(length = 80)
	private String msgum;
	@Column(length = 80)
	private String msgdois;
	@Column(length = 80)
	private String msgtres;
	@Column(length = 80)
	private String msgquatro;
	@Column(length = 80)
	private String msgcinco;
	@Column(length = 80)
	private String msgseis;
	@Column(length = 80)
	private String msgsete;
	@Column(length = 20)
	private String numconvenio;
	@Column(length = 10)
	private String carteira;
	@Column(length = 10)
	private String especie;
	@Column(length = 5)
	private String cnab;
	@Column(length = 10)
	private String varcart;
	private boolean reinciarrem = true;
	private Integer numremessa;
	private Integer nossonumatual;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pmulta;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pjuro;
	private Integer id_conta_tecno = 0;
	private Integer id_conv_tecno = 0;
	@Column(length = 8)
	private String status;

	public CdBolcfg() {
	}

	public CdBolcfg(Integer id, CdCaixa cdcaixa, String banco, String tipo, String agencia, String agenciadv,
			String conta, String contadv, String codben, String codemp, String localpaginfo, String codprotesto,
			Integer diasprotesto, String msgum, String msgdois, String msgtres, String msgquatro, String msgcinco,
			String msgseis, String msgsete, String numconvenio, String carteira, String especie, String cnab,
			String varcart, boolean reinciarrem, Integer numremessa, Integer nossonumatual,
			@Digits(integer = 3, fraction = 2) BigDecimal pmulta, @Digits(integer = 3, fraction = 2) BigDecimal pjuro,
			Integer id_conta_tecno, Integer id_conv_tecno, String status) {
		super();
		this.id = id;
		this.cdcaixa = cdcaixa;
		this.banco = banco;
		this.tipo = tipo;
		this.agencia = agencia;
		this.agenciadv = agenciadv;
		this.conta = conta;
		this.contadv = contadv;
		this.codben = codben;
		this.codemp = codemp;
		this.localpaginfo = localpaginfo;
		this.codprotesto = codprotesto;
		this.diasprotesto = diasprotesto;
		this.msgum = msgum;
		this.msgdois = msgdois;
		this.msgtres = msgtres;
		this.msgquatro = msgquatro;
		this.msgcinco = msgcinco;
		this.msgseis = msgseis;
		this.msgsete = msgsete;
		this.numconvenio = numconvenio;
		this.carteira = carteira;
		this.especie = especie;
		this.cnab = cnab;
		this.varcart = varcart;
		this.reinciarrem = reinciarrem;
		this.numremessa = numremessa;
		this.nossonumatual = nossonumatual;
		this.pmulta = pmulta;
		this.pjuro = pjuro;
		this.id_conta_tecno = id_conta_tecno;
		this.id_conv_tecno = id_conv_tecno;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdCaixa getCdcaixa() {
		return cdcaixa;
	}

	public void setCdcaixa(CdCaixa cdcaixa) {
		this.cdcaixa = cdcaixa;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getAgenciadv() {
		return agenciadv;
	}

	public void setAgenciadv(String agenciadv) {
		this.agenciadv = agenciadv;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getContadv() {
		return contadv;
	}

	public void setContadv(String contadv) {
		this.contadv = contadv;
	}

	public String getCodben() {
		return codben;
	}

	public void setCodben(String codben) {
		this.codben = codben;
	}

	public String getCodemp() {
		return codemp;
	}

	public void setCodemp(String codemp) {
		this.codemp = codemp;
	}

	public String getLocalpaginfo() {
		return localpaginfo;
	}

	public void setLocalpaginfo(String localpaginfo) {
		this.localpaginfo = localpaginfo;
	}

	public String getCodprotesto() {
		return codprotesto;
	}

	public void setCodprotesto(String codprotesto) {
		this.codprotesto = codprotesto;
	}

	public Integer getDiasprotesto() {
		return diasprotesto;
	}

	public void setDiasprotesto(Integer diasprotesto) {
		this.diasprotesto = diasprotesto;
	}

	public String getMsgum() {
		return msgum;
	}

	public void setMsgum(String msgum) {
		this.msgum = msgum;
	}

	public String getMsgdois() {
		return msgdois;
	}

	public void setMsgdois(String msgdois) {
		this.msgdois = msgdois;
	}

	public String getMsgtres() {
		return msgtres;
	}

	public void setMsgtres(String msgtres) {
		this.msgtres = msgtres;
	}

	public String getMsgquatro() {
		return msgquatro;
	}

	public void setMsgquatro(String msgquatro) {
		this.msgquatro = msgquatro;
	}

	public String getMsgcinco() {
		return msgcinco;
	}

	public void setMsgcinco(String msgcinco) {
		this.msgcinco = msgcinco;
	}

	public String getMsgseis() {
		return msgseis;
	}

	public void setMsgseis(String msgseis) {
		this.msgseis = msgseis;
	}

	public String getMsgsete() {
		return msgsete;
	}

	public void setMsgsete(String msgsete) {
		this.msgsete = msgsete;
	}

	public String getNumconvenio() {
		return numconvenio;
	}

	public void setNumconvenio(String numconvenio) {
		this.numconvenio = numconvenio;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getCnab() {
		return cnab;
	}

	public void setCnab(String cnab) {
		this.cnab = cnab;
	}

	public String getVarcart() {
		return varcart;
	}

	public void setVarcart(String varcart) {
		this.varcart = varcart;
	}

	public boolean isReinciarrem() {
		return reinciarrem;
	}

	public void setReinciarrem(boolean reinciarrem) {
		this.reinciarrem = reinciarrem;
	}

	public Integer getNumremessa() {
		return numremessa;
	}

	public void setNumremessa(Integer numremessa) {
		this.numremessa = numremessa;
	}

	public Integer getNossonumatual() {
		return nossonumatual;
	}

	public void setNossonumatual(Integer nossonumatual) {
		this.nossonumatual = nossonumatual;
	}

	public BigDecimal getPmulta() {
		return pmulta;
	}

	public void setPmulta(BigDecimal pmulta) {
		this.pmulta = pmulta;
	}

	public BigDecimal getPjuro() {
		return pjuro;
	}

	public void setPjuro(BigDecimal pjuro) {
		this.pjuro = pjuro;
	}

	public Integer getId_conta_tecno() {
		return id_conta_tecno;
	}

	public void setId_conta_tecno(Integer id_conta_tecno) {
		this.id_conta_tecno = id_conta_tecno;
	}

	public Integer getId_conv_tecno() {
		return id_conv_tecno;
	}

	public void setId_conv_tecno(Integer id_conv_tecno) {
		this.id_conv_tecno = id_conv_tecno;
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
		CdBolcfg other = (CdBolcfg) obj;
		return Objects.equals(id, other.id);
	}
}
