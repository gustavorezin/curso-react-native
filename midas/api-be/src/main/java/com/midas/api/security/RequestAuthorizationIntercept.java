package com.midas.api.security;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.midas.api.mt.config.DBContextHolder;

@Aspect
@Component
public class RequestAuthorizationIntercept {
	@Autowired
	ApplicationContext applicationContext;

	@Around("@annotation(com.midas.api.security.RequestAuthorization)")
	public Object checkPermission(ProceedingJoinPoint pjp) throws Throwable {
		UserTenantInformation tenantInformation = applicationContext.getBean(UserTenantInformation.class);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
		if (null == userDetails) {
			throw new RuntimeException("Acesso negado. Efetue login novamente ou entre em contato com suporte.");
		}
		Map<String, String> map = tenantInformation.getMap();
		String tenantName = map.get(userDetails.getUsername());
		if (tenantName != null && tenantName.equals(DBContextHolder.getCurrentDb())) {
			return pjp.proceed();
		}
		throw new RuntimeException("Acesso negado. Efetue login novamente ou entre em contato com suporte.");
	}
}
