package com.midas.api.mt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// DADOS PARTICULARES
	@Column(length = 100, unique = true)
	private String emaillogin;
	@Column(length = 120)
	private String senhalogin;
	@Transient
	private String senhaloginaux;
	@Column(length = 60)
	private String nome;
	@Column(length = 60)
	private String sobrenome;
	@Column(length = 20)
	private String cpfcnpj;
	@Column(length = 9)
	private String cep;
	@Column(length = 60)
	private String logradouro;
	@Column(length = 60)
	private String complemento;
	@Column(length = 60)
	private String bairro;
	@Column(length = 60)
	private String cidade;
	@Column(length = 2)
	private String uf;
	@Column(length = 20)
	private String celular;
	@Column(length = 20)
	private String telefone;
	@Column(length = 10)
	private String status;
	@Column(length = 50)
	private String chaveativa;
	@ManyToOne
	private Role role;
	// DADOS PARA CONEXAO
	@ManyToOne
	private Tenant tenant;
	@OneToOne(orphanRemoval = true)
	private ClienteCfg clientecfg;
	@ManyToOne
	private SisCfg siscfg;
	private Long cdpessoaemp;
	@Column(length = 60)
	private String cdpessoaempnome;
	private Long cdpessoavendedor;
	@Column(length = 1)
	private String aclocal;
	@Lob
	private byte[] imagem;

	public Cliente() {
	}

	public Cliente(Long id, String emaillogin, String senhalogin, String senhaloginaux, String nome, String sobrenome,
			String cpfcnpj, String cep, String logradouro, String complemento, String bairro, String cidade, String uf,
			String celular, String telefone, String status, String chaveativa, Role role, Tenant tenant,
			ClienteCfg clientecfg, SisCfg siscfg, Long cdpessoaemp, String cdpessoaempnome, Long cdpessoavendedor,
			String aclocal, byte[] imagem) {
		super();
		this.id = id;
		this.emaillogin = emaillogin;
		this.senhalogin = senhalogin;
		this.senhaloginaux = senhaloginaux;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpfcnpj = cpfcnpj;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.celular = celular;
		this.telefone = telefone;
		this.status = status;
		this.chaveativa = chaveativa;
		this.role = role;
		this.tenant = tenant;
		this.clientecfg = clientecfg;
		this.siscfg = siscfg;
		this.cdpessoaemp = cdpessoaemp;
		this.cdpessoaempnome = cdpessoaempnome;
		this.cdpessoavendedor = cdpessoavendedor;
		this.aclocal = aclocal;
		this.imagem = imagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmaillogin() {
		return emaillogin;
	}

	public void setEmaillogin(String emaillogin) {
		this.emaillogin = CaracterUtil.remLower(emaillogin);
	}

	public String getSenhalogin() {
		return senhalogin;
	}

	public void setSenhalogin(String senhalogin) {
		this.senhalogin = senhalogin;
	}

	public String getSenhaloginaux() {
		return senhaloginaux;
	}

	public void setSenhaloginaux(String senhaloginaux) {
		this.senhaloginaux = senhaloginaux;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChaveativa() {
		return chaveativa;
	}

	public void setChaveativa(String chaveativa) {
		this.chaveativa = chaveativa;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public ClienteCfg getClientecfg() {
		return clientecfg;
	}

	public void setClientecfg(ClienteCfg clientecfg) {
		this.clientecfg = clientecfg;
	}

	public SisCfg getSiscfg() {
		return siscfg;
	}

	public void setSiscfg(SisCfg siscfg) {
		this.siscfg = siscfg;
	}

	public Long getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(Long cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public String getCdpessoaempnome() {
		return cdpessoaempnome;
	}

	public void setCdpessoaempnome(String cdpessoaempnome) {
		this.cdpessoaempnome = cdpessoaempnome;
	}

	public Long getCdpessoavendedor() {
		return cdpessoavendedor;
	}

	public void setCdpessoavendedor(Long cdpessoavendedor) {
		this.cdpessoavendedor = cdpessoavendedor;
	}

	public String getAclocal() {
		return aclocal;
	}

	public void setAclocal(String aclocal) {
		this.aclocal = aclocal;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
}
