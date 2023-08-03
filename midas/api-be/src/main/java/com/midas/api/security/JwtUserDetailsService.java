package com.midas.api.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.midas.api.mt.entity.Cliente;
import com.midas.api.mt.repository.ClienteRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private ClienteRepository clienteRp;

	@Override
	public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Cliente cliente = clienteRp.findByEmaillogin(username);
		CustomUserDetail customUserDetail = new CustomUserDetail();
		customUserDetail.setCliente(cliente);
		customUserDetail.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(cliente.getRole().getRole())));
		return customUserDetail;
	}
}
