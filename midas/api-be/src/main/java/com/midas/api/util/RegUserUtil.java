package com.midas.api.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.repository.SisReguserRepository;

@Service
public class RegUserUtil {
	@Autowired
	private SisReguserRepository sisReguserRp;
	private static final Logger log = LoggerFactory.getLogger(RegUserUtil.class);

	public void cleanRegUser(String database) throws Exception {
		// REMOVE SE 15 DIAS
		try {
			Date dias = DataUtil.addRemDias(new Date(System.currentTimeMillis()), 15, "R");
			sisReguserRp.removeDias(dias);
			// log.info("Registro de usuários limpo para DB: " + database);
		} catch (Exception e) {
			log.error("Exception: RegUser erro : {}", e.getMessage());
		}
	}
}
