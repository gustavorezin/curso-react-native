package com.midas.api.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.midas.api.mt.entity.Cliente;
import com.midas.api.mt.entity.ClienteCfg;

@Component
public class ClienteParam {
	// Parametros de configuracao para cada cliente
	public String acesso() {
		CustomUserDetail userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String retorno = userDetails.getAuthorities().toString();
		return retorno;
	}

	public Cliente cliente() {
		CustomUserDetail userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Cliente cliente = userDetails.getCliente();
		return cliente;
	}

	public ClienteCfg cfg() {
		CustomUserDetail userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		ClienteCfg config = userDetails.getCliente().getClientecfg();
		return config;
	}
}
