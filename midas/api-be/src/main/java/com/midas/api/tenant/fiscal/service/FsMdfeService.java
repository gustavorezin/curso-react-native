package com.midas.api.tenant.fiscal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.FsMdfePagPrazo;
import com.midas.api.tenant.repository.FsMdfePagPrazoRepository;
import com.midas.api.util.NumUtil;

@Service
public class FsMdfeService {
	@Autowired
	private FsMdfePagPrazoRepository fsMdfePagPrazoRp;

	// Numero parcela prazo frete
	public void nparcelaPrazo(Integer idmdfepag) throws Exception {
		List<FsMdfePagPrazo> prazos = fsMdfePagPrazoRp.listaMdfePagPrazo(idmdfepag);
		int parc = 1;
		for(FsMdfePagPrazo p : prazos) {
			p.setNparcela(NumUtil.geraNumEsq(parc, 3));
			parc++;
			fsMdfePagPrazoRp.save(p);
		}
	}
}
