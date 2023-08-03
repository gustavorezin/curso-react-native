package com.midas.api.tenant.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdPlconMicro;
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnCxmvDre;
import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FnTituloDre;
import com.midas.api.tenant.repository.CdPlconMicroRepository;
import com.midas.api.tenant.repository.FnCxmvDreRepository;
import com.midas.api.tenant.repository.FnTituloDreRepository;

@Service
public class FnDreService {
	@Autowired
	private FnCxmvDreRepository fnCxmvDreRp;
	@Autowired
	private FnTituloDreRepository fnTituloDreRp;
	@Autowired
	private CdPlconMicroRepository plconMicroRp;

	// PADRAO SALVA FN_TITULO DRE
	public FnTituloDre dreFnTitulo100(FnTitulo novoT, Integer plcon) {
		if (plcon > 0) {
			Optional<CdPlconMicro> plm = plconMicroRp.findById(plcon);
			FnTituloDre fdN = new FnTituloDre();
			fdN.setFntitulo(novoT);
			fdN.setCdplconmicro(plm.get());
			fdN.setPvalor(new BigDecimal(100));
			fdN.setNegocia("N");
			return fnTituloDreRp.save(fdN);
		}
		return null;
	}

	// PELO PERC. SALVA FN_TITULO DRE
	public FnTituloDre dreFnTituloPerc(FnTitulo novoT, BigDecimal perc, Integer plcon) {
		if (plcon > 0) {
			Optional<CdPlconMicro> plm = plconMicroRp.findById(plcon);
			FnTituloDre fdN = new FnTituloDre();
			fdN.setFntitulo(novoT);
			fdN.setCdplconmicro(plm.get());
			fdN.setPvalor(perc);
			fdN.setNegocia("N");
			return fnTituloDreRp.save(fdN);
		}
		return null;
	}

	// PADRAO SALVA FN_TITULO DRE
	public FnTituloDre dreFnTituloNegocia(FnTitulo novoT, FnTituloDre fd) {
		FnTituloDre fdN = new FnTituloDre();
		fdN.setFntitulo(novoT);
		fdN.setCdplconmicro(fd.getCdplconmicro());
		fdN.setPvalor(fd.getPvalor());
		fdN.setNegocia("S");
		return fnTituloDreRp.save(fdN);
	}

	// PADRAO SALVA FN_CXMV DRE
	public FnCxmvDre dreFnCxmv(FnCxmv novoC, FnTituloDre td, FnTitulo t) {
		FnCxmvDre fd = new FnCxmvDre();
		fd.setFncxmv(novoC);
		fd.setCdplconmicro(td.getCdplconmicro());
		fd.setPvalor(td.getPvalor());
		return fnCxmvDreRp.save(fd);
	}

	// PADRAO SALVA FN_CXMV DRE
	public FnCxmvDre dreFnCxmv100(FnCxmv novoC, Integer plcon) {
		if (plcon > 0) {
			Optional<CdPlconMicro> plm = plconMicroRp.findById(plcon);
			FnCxmvDre fdN = new FnCxmvDre();
			fdN.setFncxmv(novoC);
			fdN.setCdplconmicro(plm.get());
			fdN.setPvalor(new BigDecimal(100));
			return fnCxmvDreRp.save(fdN);
		}
		return null;
	}

	// PELO PERC. SALVA FN_CXMV DRE
	public void dreFnCxmvPerc(FnCxmv novoC, BigDecimal pvalor, Integer plcon) {
		if (plcon > 0) {
			Optional<CdPlconMicro> plm = plconMicroRp.findById(plcon);
			FnCxmvDre fdN = new FnCxmvDre();
			fdN.setFncxmv(novoC);
			fdN.setCdplconmicro(plm.get());
			fdN.setPvalor(pvalor);
			fnCxmvDreRp.save(fdN);
		}
	}

	// CONTROLE DE DRE PARA BAIXA POR CAIXA - BAIXA UNICA POR LISTA
	public List<FnCxmvDre> dreCxmv(List<FnCxmv> fnCxmvs) {
		List<FnCxmvDre> objs = new ArrayList<FnCxmvDre>();
		for (FnCxmv c : fnCxmvs) {
			for (FnTituloDre td : c.getFntitulo().getFntitulodreitem()) {
				FnCxmvDre fd = new FnCxmvDre();
				fd.setFncxmv(c);
				fd.setCdplconmicro(td.getCdplconmicro());
				Integer conta = 1;
				if (c.getFntitulo().getFntitulorelitem().size() > 0) {
					conta = c.getFntitulo().getFntitulorelitem().size();
				}
				fd.setPvalor(td.getPvalor().divide(new BigDecimal(conta), 8, RoundingMode.HALF_UP));
				fnCxmvDreRp.save(fd);
				// Adiciona lista
				objs.add(fd);
			}
		}
		return objs;
	}

	// CONTROLE DE DRE PARA BAIXA POR CAIXA - BAIXA UNICA
	public void dreCxmvUnico(FnCxmv c) {
		for (FnTituloDre td : c.getFntitulo().getFntitulodreitem()) {
			FnCxmvDre fd = new FnCxmvDre();
			fd.setFncxmv(c);
			fd.setCdplconmicro(td.getCdplconmicro());
			fd.setPvalor(td.getPvalor());
			fnCxmvDreRp.save(fd);
		}
	}

	// CONTROLE DE DRE PARA BAIXA EM LOTE
	public List<FnCxmvDre> dreTituloLote(FnTitulo t, FnCxmv fncxmv) {
		List<FnCxmvDre> objs = new ArrayList<FnCxmvDre>();
		for (FnTituloDre td : t.getFntitulodreitem()) {
			FnCxmvDre fd = new FnCxmvDre();
			fd.setFncxmv(fncxmv);
			fd.setCdplconmicro(td.getCdplconmicro());
			fd.setPvalor(td.getPvalor());
			fnCxmvDreRp.save(fd);
			objs.add(fd);
		}
		return objs;
	}
}