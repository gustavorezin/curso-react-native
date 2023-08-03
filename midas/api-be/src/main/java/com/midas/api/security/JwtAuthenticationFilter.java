package com.midas.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.midas.api.constant.JWTConstants;
import com.midas.api.mt.config.DBContextHolder;
import com.midas.api.mt.entity.Tenant;
import com.midas.api.mt.repository.TenantRepository;
import com.midas.api.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private TenantRepository tenantRp;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		String header = httpServletRequest.getHeader(JWTConstants.HEADER_STRING);
		String username = null;
		String audience = null; // tenantOrClientId
		String authToken = null;
		if (header != null && header.startsWith(JWTConstants.TOKEN_PREFIX)) {
			authToken = header.replace(JWTConstants.TOKEN_PREFIX, "");
			try {
				username = jwtTokenUtil.getUsernameFromToken(authToken);
				audience = jwtTokenUtil.getAudienceFromToken(authToken);
				Tenant tenant = tenantRp.findById(Long.valueOf(audience)).get();
				if (null == tenant) {
					logger.error("Ocorreu um erro durante a conexao com nome do inquilino/tenant");
					throw new BadCredentialsException("Tenant nao habilitado.");
				}
				DBContextHolder.setCurrentDb(tenant.getDbname());
			} catch (IllegalArgumentException ex) {
				logger.error("Erro durante a execucao do login com token.", ex);
			} catch (ExpiredJwtException ex) {
				logger.warn("Token expirado ou nao mais valido.", ex);
			} catch (SignatureException ex) {
				logger.error("Falha na autenticacao. Usuario ou senha invalidos.", ex);
			}
		} else {
			// logger.warn("Bearer nao encontrado. Ignorando cabecalho.");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			CustomUserDetail userDetails = (CustomUserDetail) jwtUserDetailsService.loadUserByUsername(username);
			if (jwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				// logger.info("Usuario autenticado " + username + ", atualizando contexto de
				// seguranca.");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
