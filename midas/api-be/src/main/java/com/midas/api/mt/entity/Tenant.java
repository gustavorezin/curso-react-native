package com.midas.api.mt.entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tenant")
public class Tenant implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String dbname;
	@Column(length = 100)
	private String url;
	@Column(length = 50)
	private String username;
	@Column(length = 100)
	private String password;
	@Column(length = 50)
	private String driverclass;
	@Column(length = 100)
	private String mdump;
	@Column(length = 100)
	private String backupfd;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date backupult = new Date();
	@JsonFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date backupulthr = new Date();
	@Column(length = 100)
	private String tempfd;
	@Column(length = 1)
	private String sqlconfig;
	@Column(length = 1)
	private String perteste;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datafimteste = new Date();
	@Column(length = 10)
	private String status;
	@JsonIgnoreProperties(value = { "tenant" }, allowSetters = true)
	@OneToMany(mappedBy = "tenant", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<ClienteModulo> moduloclienteitem = new ArrayList<ClienteModulo>();

	public Tenant() {
	}

	public Tenant(Long id, String dbname, String url, String username, String password, String driverclass,
			String mdump, String backupfd, Date backupult, Date backupulthr, String tempfd, String sqlconfig,
			String perteste, Date datafimteste, String status, List<ClienteModulo> moduloclienteitem) {
		super();
		this.id = id;
		this.dbname = dbname;
		this.url = url;
		this.username = username;
		this.password = password;
		this.driverclass = driverclass;
		this.mdump = mdump;
		this.backupfd = backupfd;
		this.backupult = backupult;
		this.backupulthr = backupulthr;
		this.tempfd = tempfd;
		this.sqlconfig = sqlconfig;
		this.perteste = perteste;
		this.datafimteste = datafimteste;
		this.status = status;
		this.moduloclienteitem = moduloclienteitem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverclass() {
		return driverclass;
	}

	public void setDriverclass(String driverclass) {
		this.driverclass = driverclass;
	}

	public String getMdump() {
		return mdump;
	}

	public void setMdump(String mdump) {
		this.mdump = mdump;
	}

	public String getBackupfd() {
		return backupfd;
	}

	public void setBackupfd(String backupfd) {
		this.backupfd = backupfd;
	}

	public Date getBackupult() {
		return backupult;
	}

	public void setBackupult(Date backupult) {
		this.backupult = backupult;
	}

	public Date getBackupulthr() {
		return backupulthr;
	}

	public void setBackupulthr(Date backupulthr) {
		this.backupulthr = backupulthr;
	}

	public String getTempfd() {
		return tempfd;
	}

	public void setTempfd(String tempfd) {
		this.tempfd = tempfd;
	}

	public String getSqlconfig() {
		return sqlconfig;
	}

	public void setSqlconfig(String sqlconfig) {
		this.sqlconfig = sqlconfig;
	}

	public String getPerteste() {
		return perteste;
	}

	public void setPerteste(String perteste) {
		this.perteste = perteste;
	}

	public Date getDatafimteste() {
		return datafimteste;
	}

	public void setDatafimteste(Date datafimteste) {
		this.datafimteste = datafimteste;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ClienteModulo> getModuloclienteitem() {
		return moduloclienteitem;
	}

	public void setModuloclienteitem(List<ClienteModulo> moduloclienteitem) {
		this.moduloclienteitem = moduloclienteitem;
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
		Tenant other = (Tenant) obj;
		return Objects.equals(id, other.id);
	}
}
