package com.midas.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.midas.api.mt.entity.Cliente;
import com.midas.api.mt.entity.ClienteModulo;

public class AuthResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private String userName;
	private String token;
	private Long clienteid;
	private String perteste;
	private Date datafimteste;
	private Integer tempobuscaautonfe;
	private List<ClienteModulo> moduloclienteitem;

	public AuthResponse(Cliente cliente, String userName, String token, Long clienteid, String perteste,
			Date datafimteste, Integer tempobuscaautonfe, List<ClienteModulo> moduloclienteitem) {
		super();
		this.cliente = cliente;
		this.userName = userName;
		this.token = token;
		this.clienteid = clienteid;
		this.perteste = perteste;
		this.datafimteste = datafimteste;
		this.tempobuscaautonfe = tempobuscaautonfe;
		this.moduloclienteitem = moduloclienteitem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public AuthResponse setCliente(Cliente cliente) {
		this.cliente = cliente;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public AuthResponse setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getToken() {
		return token;
	}

	public AuthResponse setToken(String token) {
		this.token = token;
		return this;
	}

	public Long getClienteid() {
		return clienteid;
	}

	public AuthResponse setClienteid(Long clienteid) {
		this.clienteid = clienteid;
		return this;
	}

	public String getPerteste() {
		return perteste;
	}

	public AuthResponse setPerteste(String perteste) {
		this.perteste = perteste;
		return this;
	}

	public Date getDatafimteste() {
		return datafimteste;
	}

	public AuthResponse setDatafimteste(Date datafimteste) {
		this.datafimteste = datafimteste;
		return this;
	}

	public Integer getTempobuscaautonfe() {
		return tempobuscaautonfe;
	}

	public AuthResponse setTempobuscaautonfe(Integer tempobuscaautonfe) {
		this.tempobuscaautonfe = tempobuscaautonfe;
		return this;
	}

	public List<ClienteModulo> getModuloclienteitem() {
		return moduloclienteitem;
	}

	public AuthResponse setModuloclienteitem(List<ClienteModulo> moduloclienteitem) {
		this.moduloclienteitem = moduloclienteitem;
		return this;
	}
}
