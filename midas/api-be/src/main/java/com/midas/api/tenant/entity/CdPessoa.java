package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_pessoa")
public class CdPessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@Column(length = 20)
	private String tipo;
	private Long emp;
	@ManyToOne
	private CdPessoaGrupo cdpessoagrupo;
	@Column(length = 60)
	private String nome;
	@Column(length = 60)
	private String fantasia;
	@Column(length = 20)
	private String cpfcnpj;
	@Column(length = 20)
	private String ierg;
	@Column(length = 1)
	private String indiedest;
	@Column(length = 15)
	private String isuf;
	@Column(length = 15)
	private String im;
	@Column(length = 20)
	private String idestrangeiro;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date nascfund;
	@Column(length = 16)
	private String foneum;
	@Column(length = 16)
	private String fonedois;
	@Column(length = 16)
	private String fonetres;
	@Column(length = 250)
	private String email;
	@Column(length = 60)
	private String nomefin;
	@Column(length = 250)
	private String emailfin;
	@Column(length = 16)
	private String fonefin;
	@Column(length = 60)
	private String nomefat;
	@Column(length = 250)
	private String emailfat;
	@Column(length = 16)
	private String fonefat;
	@Column(length = 60)
	private String rua;
	@Column(length = 60)
	private String num;
	@Column(length = 60)
	private String comp;
	@Column(length = 60)
	private String bairro;
	@ManyToOne
	private CdCidade cdcidade;
	@ManyToOne
	private CdEstado cdestado;
	@Column(length = 8)
	private String cep;
	@Column(length = 4)
	private String codpais = "1058";// 1058 - Define sempre Brasil
	@Column(length = 2000)
	private String info;
	@Column(length = 2000)
	private String infodoc;
	@Column(length = 500)
	private String infodocemp;
	private Integer cdpessoasetor_id;
	private Long cdpessoaresp_id;
	@Column(length = 60)
	private String favorecido;
	@Column(length = 20)
	private String banco;
	@Column(length = 20)
	private String agbanco;
	@Column(length = 10)
	private String tipconta;
	@Column(length = 20)
	private String numconta;
	@Column(length = 60)
	private String formenvio;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pcom = new BigDecimal(0);
	@Digits(integer = 12, fraction = 2)
	private BigDecimal vsal = new BigDecimal(0);
	private Integer crt = 1; // DEFINE SEMPRE SIMPLES NACIONAL
	@Column(length = 7)
	private String cnae;
	@Column(length = 30)
	private String numconreg;
	@Column(length = 20)
	private String numapol;
	@Lob
	private byte[] imagem;
	@Column(length = 20)
	private String cedente_id_tecno;
	@Column(length = 40)
	private String cedente_token_tecno;
	@Column(length = 5)
	private String nfce_idcsc = "";
	@Column(length = 45)
	private String nfce_csc = "";
	@Column(length = 20)
	private String alvara = "";
	@Column(length = 1)
	private String tptransp = "1";
	@Column(length = 9)
	private String rntctransp = "";
	@Column(length = 2)
	private String fpag = "01";
	private Integer condpag = 0;
	@Column(length = 1)
	private String condpagperm = "N";
	private Integer cdprodutotab_id = 0;
	private Integer cdnfecfg_id = 0;
	@Column(length = 8)
	private String status;
	@JsonIgnoreProperties(value = { "cdpessoamotora" }, allowSetters = true)
	@OneToMany(mappedBy = "cdpessoamotora", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<CdVeiculoRel> cdveiculorelitems = new ArrayList<CdVeiculoRel>();
	@JsonIgnoreProperties(value = { "cdpessoavend" }, allowSetters = true)
	@OneToMany(mappedBy = "cdpessoavend", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<CdCaixaOpera> cdcaixaoperaitems = new ArrayList<CdCaixaOpera>();
	@JsonIgnoreProperties(value = { "cdpessoavend" }, allowSetters = true)
	@OneToMany(mappedBy = "cdpessoavend", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<CdProdutoTabVend> cdprodutotabvenditems = new ArrayList<CdProdutoTabVend>();
	@JsonIgnoreProperties(value = { "cdpessoavend" }, allowSetters = true)
	@OneToMany(mappedBy = "cdpessoavend", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<CdNfeCfgVend> cdnfecfgvenditems = new ArrayList<CdNfeCfgVend>();
	@JsonIgnoreProperties(value = { "cdpessoaemp" }, allowSetters = true)
	@OneToMany(mappedBy = "cdpessoaemp", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<CdNfeMe> cdnfemeitems = new ArrayList<CdNfeMe>();

	public CdPessoa() {
	}

	public CdPessoa(Long id, Date datacad, Date dataat, String tipo, Long emp, CdPessoaGrupo cdpessoagrupo, String nome,
			String fantasia, String cpfcnpj, String ierg, String indiedest, String isuf, String im,
			String idestrangeiro, Date nascfund, String foneum, String fonedois, String fonetres, String email,
			String nomefin, String emailfin, String fonefin, String nomefat, String emailfat, String fonefat,
			String rua, String num, String comp, String bairro, CdCidade cdcidade, CdEstado cdestado, String cep,
			String codpais, String info, String infodoc, String infodocemp, Integer cdpessoasetor_id,
			Long cdpessoaresp_id, String favorecido, String banco, String agbanco, String tipconta, String numconta,
			String formenvio, @Digits(integer = 3, fraction = 2) BigDecimal pcom,
			@Digits(integer = 12, fraction = 2) BigDecimal vsal, Integer crt, String cnae, String numconreg,
			String numapol, byte[] imagem, String cedente_id_tecno, String cedente_token_tecno, String nfce_idcsc,
			String nfce_csc, String alvara, String tptransp, String rntctransp, String fpag, Integer condpag,
			String condpagperm, Integer cdprodutotab_id, Integer cdnfecfg_id, String status,
			List<CdVeiculoRel> cdveiculorelitems, List<CdCaixaOpera> cdcaixaoperaitems,
			List<CdProdutoTabVend> cdprodutotabvenditems, List<CdNfeCfgVend> cdnfecfgvenditems,
			List<CdNfeMe> cdnfemeitems) {
		super();
		this.id = id;
		this.datacad = datacad;
		this.dataat = dataat;
		this.tipo = tipo;
		this.emp = emp;
		this.cdpessoagrupo = cdpessoagrupo;
		this.nome = nome;
		this.fantasia = fantasia;
		this.cpfcnpj = cpfcnpj;
		this.ierg = ierg;
		this.indiedest = indiedest;
		this.isuf = isuf;
		this.im = im;
		this.idestrangeiro = idestrangeiro;
		this.nascfund = nascfund;
		this.foneum = foneum;
		this.fonedois = fonedois;
		this.fonetres = fonetres;
		this.email = email;
		this.nomefin = nomefin;
		this.emailfin = emailfin;
		this.fonefin = fonefin;
		this.nomefat = nomefat;
		this.emailfat = emailfat;
		this.fonefat = fonefat;
		this.rua = rua;
		this.num = num;
		this.comp = comp;
		this.bairro = bairro;
		this.cdcidade = cdcidade;
		this.cdestado = cdestado;
		this.cep = cep;
		this.codpais = codpais;
		this.info = info;
		this.infodoc = infodoc;
		this.infodocemp = infodocemp;
		this.cdpessoasetor_id = cdpessoasetor_id;
		this.cdpessoaresp_id = cdpessoaresp_id;
		this.favorecido = favorecido;
		this.banco = banco;
		this.agbanco = agbanco;
		this.tipconta = tipconta;
		this.numconta = numconta;
		this.formenvio = formenvio;
		this.pcom = pcom;
		this.vsal = vsal;
		this.crt = crt;
		this.cnae = cnae;
		this.numconreg = numconreg;
		this.numapol = numapol;
		this.imagem = imagem;
		this.cedente_id_tecno = cedente_id_tecno;
		this.cedente_token_tecno = cedente_token_tecno;
		this.nfce_idcsc = nfce_idcsc;
		this.nfce_csc = nfce_csc;
		this.alvara = alvara;
		this.tptransp = tptransp;
		this.rntctransp = rntctransp;
		this.fpag = fpag;
		this.condpag = condpag;
		this.condpagperm = condpagperm;
		this.cdprodutotab_id = cdprodutotab_id;
		this.cdnfecfg_id = cdnfecfg_id;
		this.status = status;
		this.cdveiculorelitems = cdveiculorelitems;
		this.cdcaixaoperaitems = cdcaixaoperaitems;
		this.cdprodutotabvenditems = cdprodutotabvenditems;
		this.cdnfecfgvenditems = cdnfecfgvenditems;
		this.cdnfemeitems = cdnfemeitems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getEmp() {
		return emp;
	}

	public void setEmp(Long emp) {
		this.emp = emp;
	}

	public CdPessoaGrupo getCdpessoagrupo() {
		return cdpessoagrupo;
	}

	public void setCdpessoagrupo(CdPessoaGrupo cdpessoagrupo) {
		this.cdpessoagrupo = cdpessoagrupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = CaracterUtil.remUpper(fantasia);
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getIerg() {
		return ierg;
	}

	public void setIerg(String ierg) {
		this.ierg = CaracterUtil.remPout(ierg);
	}

	public String getIndiedest() {
		return indiedest;
	}

	public void setIndiedest(String indiedest) {
		this.indiedest = indiedest;
	}

	public String getIsuf() {
		return isuf;
	}

	public void setIsuf(String isuf) {
		this.isuf = isuf;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getIdestrangeiro() {
		return idestrangeiro;
	}

	public void setIdestrangeiro(String idestrangeiro) {
		this.idestrangeiro = idestrangeiro;
	}

	public Date getNascfund() {
		return nascfund;
	}

	public void setNascfund(Date nascfund) {
		this.nascfund = nascfund;
	}

	public String getFoneum() {
		return foneum;
	}

	public void setFoneum(String foneum) {
		this.foneum = foneum;
	}

	public String getFonedois() {
		return fonedois;
	}

	public void setFonedois(String fonedois) {
		this.fonedois = fonedois;
	}

	public String getFonetres() {
		return fonetres;
	}

	public void setFonetres(String fonetres) {
		this.fonetres = fonetres;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = CaracterUtil.remLower(email);
	}

	public String getNomefin() {
		return nomefin;
	}

	public void setNomefin(String nomefin) {
		this.nomefin = CaracterUtil.remUpper(nomefin);
	}

	public String getEmailfin() {
		return emailfin;
	}

	public void setEmailfin(String emailfin) {
		this.emailfin = CaracterUtil.remLower(emailfin);
	}

	public String getFonefin() {
		return fonefin;
	}

	public void setFonefin(String fonefin) {
		this.fonefin = fonefin;
	}

	public String getNomefat() {
		return nomefat;
	}

	public void setNomefat(String nomefat) {
		this.nomefat = CaracterUtil.remLower(nomefat);
	}

	public String getEmailfat() {
		return emailfat;
	}

	public void setEmailfat(String emailfat) {
		this.emailfat = CaracterUtil.remLower(emailfat);
	}

	public String getFonefat() {
		return fonefat;
	}

	public void setFonefat(String fonefat) {
		this.fonefat = fonefat;
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

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = CaracterUtil.remUpper(comp);
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCodpais() {
		return codpais;
	}

	public void setCodpais(String codpais) {
		this.codpais = codpais;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfodoc() {
		return infodoc;
	}

	public void setInfodoc(String infodoc) {
		this.infodoc = infodoc;
	}

	public String getInfodocemp() {
		return infodocemp;
	}

	public void setInfodocemp(String infodocemp) {
		this.infodocemp = infodocemp;
	}

	public Integer getCdpessoasetor_id() {
		return cdpessoasetor_id;
	}

	public void setCdpessoasetor_id(Integer cdpessoasetor_id) {
		this.cdpessoasetor_id = cdpessoasetor_id;
	}

	public Long getCdpessoaresp_id() {
		return cdpessoaresp_id;
	}

	public void setCdpessoaresp_id(Long cdpessoaresp_id) {
		this.cdpessoaresp_id = cdpessoaresp_id;
	}

	public String getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(String favorecido) {
		this.favorecido = CaracterUtil.remUpper(favorecido);
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = CaracterUtil.remUpper(banco);
	}

	public String getAgbanco() {
		return agbanco;
	}

	public void setAgbanco(String agbanco) {
		this.agbanco = agbanco;
	}

	public String getTipconta() {
		return tipconta;
	}

	public void setTipconta(String tipconta) {
		this.tipconta = tipconta;
	}

	public String getNumconta() {
		return numconta;
	}

	public void setNumconta(String numconta) {
		this.numconta = numconta;
	}

	public String getFormenvio() {
		return formenvio;
	}

	public void setFormenvio(String formenvio) {
		this.formenvio = CaracterUtil.remUpper(formenvio);
	}

	public BigDecimal getPcom() {
		return pcom;
	}

	public void setPcom(BigDecimal pcom) {
		this.pcom = pcom;
	}

	public BigDecimal getVsal() {
		return vsal;
	}

	public void setVsal(BigDecimal vsal) {
		this.vsal = vsal;
	}

	public Integer getCrt() {
		return crt;
	}

	public void setCrt(Integer crt) {
		this.crt = crt;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public String getNumconreg() {
		return numconreg;
	}

	public void setNumconreg(String numconreg) {
		this.numconreg = numconreg;
	}

	public String getNumapol() {
		return numapol;
	}

	public void setNumapol(String numapol) {
		this.numapol = numapol;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getCedente_id_tecno() {
		return cedente_id_tecno;
	}

	public void setCedente_id_tecno(String cedente_id_tecno) {
		this.cedente_id_tecno = cedente_id_tecno;
	}

	public String getCedente_token_tecno() {
		return cedente_token_tecno;
	}

	public void setCedente_token_tecno(String cedente_token_tecno) {
		this.cedente_token_tecno = cedente_token_tecno;
	}

	public String getNfce_idcsc() {
		return nfce_idcsc;
	}

	public void setNfce_idcsc(String nfce_idcsc) {
		this.nfce_idcsc = nfce_idcsc;
	}

	public String getNfce_csc() {
		return nfce_csc;
	}

	public void setNfce_csc(String nfce_csc) {
		this.nfce_csc = nfce_csc;
	}

	public String getAlvara() {
		return alvara;
	}

	public void setAlvara(String alvara) {
		this.alvara = CaracterUtil.remUpper(alvara);
	}

	public String getTptransp() {
		return tptransp;
	}

	public void setTptransp(String tptransp) {
		this.tptransp = tptransp;
	}

	public String getRntctransp() {
		return rntctransp;
	}

	public void setRntctransp(String rntctransp) {
		this.rntctransp = rntctransp;
	}

	public String getFpag() {
		return fpag;
	}

	public void setFpag(String fpag) {
		this.fpag = fpag;
	}

	public Integer getCondpag() {
		return condpag;
	}

	public void setCondpag(Integer condpag) {
		this.condpag = condpag;
	}

	public String getCondpagperm() {
		return condpagperm;
	}

	public void setCondpagperm(String condpagperm) {
		this.condpagperm = condpagperm;
	}

	public Integer getCdprodutotab_id() {
		return cdprodutotab_id;
	}

	public void setCdprodutotab_id(Integer cdprodutotab_id) {
		this.cdprodutotab_id = cdprodutotab_id;
	}

	public Integer getCdnfecfg_id() {
		return cdnfecfg_id;
	}

	public void setCdnfecfg_id(Integer cdnfecfg_id) {
		this.cdnfecfg_id = cdnfecfg_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CdVeiculoRel> getCdveiculorelitems() {
		return cdveiculorelitems;
	}

	public void setCdveiculorelitems(List<CdVeiculoRel> cdveiculorelitems) {
		this.cdveiculorelitems = cdveiculorelitems;
	}

	public List<CdCaixaOpera> getCdcaixaoperaitems() {
		return cdcaixaoperaitems;
	}

	public void setCdcaixaoperaitems(List<CdCaixaOpera> cdcaixaoperaitems) {
		this.cdcaixaoperaitems = cdcaixaoperaitems;
	}

	public List<CdProdutoTabVend> getCdprodutotabvenditems() {
		return cdprodutotabvenditems;
	}

	public void setCdprodutotabvenditems(List<CdProdutoTabVend> cdprodutotabvenditems) {
		this.cdprodutotabvenditems = cdprodutotabvenditems;
	}

	public List<CdNfeCfgVend> getCdnfecfgvenditems() {
		return cdnfecfgvenditems;
	}

	public void setCdnfecfgvenditems(List<CdNfeCfgVend> cdnfecfgvenditems) {
		this.cdnfecfgvenditems = cdnfecfgvenditems;
	}

	public List<CdNfeMe> getCdnfemeitems() {
		return cdnfemeitems;
	}

	public void setCdnfemeitems(List<CdNfeMe> cdnfemeitems) {
		this.cdnfemeitems = cdnfemeitems;
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
		CdPessoa other = (CdPessoa) obj;
		return Objects.equals(id, other.id);
	}
}
