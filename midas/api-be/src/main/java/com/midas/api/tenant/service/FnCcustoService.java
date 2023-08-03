package com.midas.api.tenant.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdCcusto;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FnTituloCcusto;
import com.midas.api.tenant.repository.CdCcustoRepository;
import com.midas.api.tenant.repository.FnTituloCcustoRepository;

@Service
public class FnCcustoService {
	@Autowired
	private FnTituloCcustoRepository fnTituloCcustoRp;
	@Autowired
	private CdCcustoRepository cdCcustoRp;

	// PADRAO SALVA FN_TITULO CENTRO CUSTO
	public void ccFnTituloCc(FnTitulo ant, FnTitulo novoT) {
		for (FnTituloCcusto fd : ant.getFntituloccustoitem()) {
			FnTituloCcusto fc = new FnTituloCcusto();
			fc.setCdccusto(fd.getCdccusto());
			fc.setFntitulo(novoT);
			fc.setPvalor(fd.getPvalor());
			fnTituloCcustoRp.save(fc);
		}
	}

	// PADRAO SALVA FN_TITULO CENTRO CUSTO
	public void ccFnTitulo100(FnTitulo novoT, Integer ccusto) {
		if (ccusto > 0) {
			Optional<CdCcusto> c = cdCcustoRp.findById(ccusto);
			FnTituloCcusto fc = new FnTituloCcusto();
			fc.setCdccusto(c.get());
			fc.setFntitulo(novoT);
			fc.setPvalor(new BigDecimal(100));
			fnTituloCcustoRp.save(fc);
		}
	}
}