package com.midas.api.mt.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "sis_cfg")
public class SisCfg implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@Column(length = 1)
	private boolean sis_gravar_proc_user = true;
	private Integer sis_tam_imagem_pixel = 500;
	@Column(length = 1)
	private boolean sis_separa_juros_plcon = true;
	@Column(length = 1)
	private boolean sis_busca_auto_nfe = false;
	@Column(length = 1)
	private boolean sis_mt_auto_nfe = false;
	@Column(length = 1)
	private boolean sis_email_auto_nfe = false;
	@Column(length = 1)
	private String sis_amb_nfe = "1";
	@Column(length = 1)
	private String sis_amb_nfse = "1";
	@Column(length = 1)
	private String sis_amb_mdfe = "1";
	@Column(length = 1)
	private String sis_amb_cte = "1";
	@Column(length = 1)
	private boolean sis_mostra_veiculo = false;
	@Column(length = 1)
	private String sis_amb_boleto = "2";
	@Column(length = 1)
	private boolean sis_usa_balanca = false;
	private Integer sis_balanca_codini = 0;
	private Integer sis_balanca_codfim = 0;
	private Integer sis_balanca_valorini = 0;
	private Integer sis_balanca_valorfim = 0;
	@Column(length = 1)
	private boolean sis_usa_codigo_padrao = false;
	@Column(length = 1)
	private boolean sis_busca_auto_cte = false;
	@Column(length = 1)
	private boolean sis_assinafn_auto = true;
	@Column(length = 1)
	private String sis_tecno_tipo_imp = "2";
	@Column(length = 1)
	private String sis_modfrete_nfe = "9";
	@Column(length = 1)
	private boolean sis_usa_codigo_alterpdv = false;
	@Column(length = 1)
	private boolean sis_impref_doc = true;
	@Column(length = 1)
	private boolean sis_impref_nf = true;

	public SisCfg() {
	}

	public SisCfg(Integer id, Date datacad, Date dataat, boolean sis_gravar_proc_user, Integer sis_tam_imagem_pixel,
			boolean sis_separa_juros_plcon, boolean sis_busca_auto_nfe, boolean sis_mt_auto_nfe,
			boolean sis_email_auto_nfe, String sis_amb_nfe, String sis_amb_nfse, String sis_amb_mdfe,
			String sis_amb_cte, boolean sis_mostra_veiculo, String sis_amb_boleto, boolean sis_usa_balanca,
			Integer sis_balanca_codini, Integer sis_balanca_codfim, Integer sis_balanca_valorini,
			Integer sis_balanca_valorfim, boolean sis_usa_codigo_padrao, boolean sis_busca_auto_cte,
			boolean sis_assinafn_auto, String sis_tecno_tipo_imp, String sis_modfrete_nfe,
			boolean sis_usa_codigo_alterpdv, boolean sis_impref_doc, boolean sis_impref_nf) {
		super();
		this.id = id;
		this.datacad = datacad;
		this.dataat = dataat;
		this.sis_gravar_proc_user = sis_gravar_proc_user;
		this.sis_tam_imagem_pixel = sis_tam_imagem_pixel;
		this.sis_separa_juros_plcon = sis_separa_juros_plcon;
		this.sis_busca_auto_nfe = sis_busca_auto_nfe;
		this.sis_mt_auto_nfe = sis_mt_auto_nfe;
		this.sis_email_auto_nfe = sis_email_auto_nfe;
		this.sis_amb_nfe = sis_amb_nfe;
		this.sis_amb_nfse = sis_amb_nfse;
		this.sis_amb_mdfe = sis_amb_mdfe;
		this.sis_amb_cte = sis_amb_cte;
		this.sis_mostra_veiculo = sis_mostra_veiculo;
		this.sis_amb_boleto = sis_amb_boleto;
		this.sis_usa_balanca = sis_usa_balanca;
		this.sis_balanca_codini = sis_balanca_codini;
		this.sis_balanca_codfim = sis_balanca_codfim;
		this.sis_balanca_valorini = sis_balanca_valorini;
		this.sis_balanca_valorfim = sis_balanca_valorfim;
		this.sis_usa_codigo_padrao = sis_usa_codigo_padrao;
		this.sis_busca_auto_cte = sis_busca_auto_cte;
		this.sis_assinafn_auto = sis_assinafn_auto;
		this.sis_tecno_tipo_imp = sis_tecno_tipo_imp;
		this.sis_modfrete_nfe = sis_modfrete_nfe;
		this.sis_usa_codigo_alterpdv = sis_usa_codigo_alterpdv;
		this.sis_impref_doc = sis_impref_doc;
		this.sis_impref_nf = sis_impref_nf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isSis_gravar_proc_user() {
		return sis_gravar_proc_user;
	}

	public void setSis_gravar_proc_user(boolean sis_gravar_proc_user) {
		this.sis_gravar_proc_user = sis_gravar_proc_user;
	}

	public Integer getSis_tam_imagem_pixel() {
		return sis_tam_imagem_pixel;
	}

	public void setSis_tam_imagem_pixel(Integer sis_tam_imagem_pixel) {
		this.sis_tam_imagem_pixel = sis_tam_imagem_pixel;
	}

	public boolean isSis_separa_juros_plcon() {
		return sis_separa_juros_plcon;
	}

	public void setSis_separa_juros_plcon(boolean sis_separa_juros_plcon) {
		this.sis_separa_juros_plcon = sis_separa_juros_plcon;
	}

	public boolean isSis_busca_auto_nfe() {
		return sis_busca_auto_nfe;
	}

	public void setSis_busca_auto_nfe(boolean sis_busca_auto_nfe) {
		this.sis_busca_auto_nfe = sis_busca_auto_nfe;
	}

	public boolean isSis_mt_auto_nfe() {
		return sis_mt_auto_nfe;
	}

	public void setSis_mt_auto_nfe(boolean sis_mt_auto_nfe) {
		this.sis_mt_auto_nfe = sis_mt_auto_nfe;
	}

	public boolean isSis_email_auto_nfe() {
		return sis_email_auto_nfe;
	}

	public void setSis_email_auto_nfe(boolean sis_email_auto_nfe) {
		this.sis_email_auto_nfe = sis_email_auto_nfe;
	}

	public String getSis_amb_nfe() {
		return sis_amb_nfe;
	}

	public void setSis_amb_nfe(String sis_amb_nfe) {
		this.sis_amb_nfe = sis_amb_nfe;
	}

	public String getSis_amb_nfse() {
		return sis_amb_nfse;
	}

	public void setSis_amb_nfse(String sis_amb_nfse) {
		this.sis_amb_nfse = sis_amb_nfse;
	}

	public String getSis_amb_mdfe() {
		return sis_amb_mdfe;
	}

	public void setSis_amb_mdfe(String sis_amb_mdfe) {
		this.sis_amb_mdfe = sis_amb_mdfe;
	}

	public String getSis_amb_cte() {
		return sis_amb_cte;
	}

	public void setSis_amb_cte(String sis_amb_cte) {
		this.sis_amb_cte = sis_amb_cte;
	}

	public boolean isSis_mostra_veiculo() {
		return sis_mostra_veiculo;
	}

	public void setSis_mostra_veiculo(boolean sis_mostra_veiculo) {
		this.sis_mostra_veiculo = sis_mostra_veiculo;
	}

	public String getSis_amb_boleto() {
		return sis_amb_boleto;
	}

	public void setSis_amb_boleto(String sis_amb_boleto) {
		this.sis_amb_boleto = sis_amb_boleto;
	}

	public boolean isSis_usa_balanca() {
		return sis_usa_balanca;
	}

	public void setSis_usa_balanca(boolean sis_usa_balanca) {
		this.sis_usa_balanca = sis_usa_balanca;
	}

	public Integer getSis_balanca_codini() {
		return sis_balanca_codini;
	}

	public void setSis_balanca_codini(Integer sis_balanca_codini) {
		this.sis_balanca_codini = sis_balanca_codini;
	}

	public Integer getSis_balanca_codfim() {
		return sis_balanca_codfim;
	}

	public void setSis_balanca_codfim(Integer sis_balanca_codfim) {
		this.sis_balanca_codfim = sis_balanca_codfim;
	}

	public Integer getSis_balanca_valorini() {
		return sis_balanca_valorini;
	}

	public void setSis_balanca_valorini(Integer sis_balanca_valorini) {
		this.sis_balanca_valorini = sis_balanca_valorini;
	}

	public Integer getSis_balanca_valorfim() {
		return sis_balanca_valorfim;
	}

	public void setSis_balanca_valorfim(Integer sis_balanca_valorfim) {
		this.sis_balanca_valorfim = sis_balanca_valorfim;
	}

	public boolean isSis_usa_codigo_padrao() {
		return sis_usa_codigo_padrao;
	}

	public void setSis_usa_codigo_padrao(boolean sis_usa_codigo_padrao) {
		this.sis_usa_codigo_padrao = sis_usa_codigo_padrao;
	}

	public boolean isSis_busca_auto_cte() {
		return sis_busca_auto_cte;
	}

	public void setSis_busca_auto_cte(boolean sis_busca_auto_cte) {
		this.sis_busca_auto_cte = sis_busca_auto_cte;
	}

	public boolean isSis_assinafn_auto() {
		return sis_assinafn_auto;
	}

	public void setSis_assinafn_auto(boolean sis_assinafn_auto) {
		this.sis_assinafn_auto = sis_assinafn_auto;
	}

	public String getSis_tecno_tipo_imp() {
		return sis_tecno_tipo_imp;
	}

	public void setSis_tecno_tipo_imp(String sis_tecno_tipo_imp) {
		this.sis_tecno_tipo_imp = sis_tecno_tipo_imp;
	}

	public String getSis_modfrete_nfe() {
		return sis_modfrete_nfe;
	}

	public void setSis_modfrete_nfe(String sis_modfrete_nfe) {
		this.sis_modfrete_nfe = sis_modfrete_nfe;
	}

	public boolean isSis_usa_codigo_alterpdv() {
		return sis_usa_codigo_alterpdv;
	}

	public void setSis_usa_codigo_alterpdv(boolean sis_usa_codigo_alterpdv) {
		this.sis_usa_codigo_alterpdv = sis_usa_codigo_alterpdv;
	}

	public boolean isSis_impref_doc() {
		return sis_impref_doc;
	}

	public void setSis_impref_doc(boolean sis_impref_doc) {
		this.sis_impref_doc = sis_impref_doc;
	}

	public boolean isSis_impref_nf() {
		return sis_impref_nf;
	}

	public void setSis_impref_nf(boolean sis_impref_nf) {
		this.sis_impref_nf = sis_impref_nf;
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
		SisCfg other = (SisCfg) obj;
		return Objects.equals(id, other.id);
	}
}
