package com.midas.api.tenant.service.excel;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.repository.LcDocItemCotRepository;
import com.midas.api.tenant.service.LcDocService;
import com.midas.api.util.MoedaUtil;

@Service
public class LerExcelCotacaoService {
	@Autowired
	private LcDocItemCotRepository lcDocItemCotRp;
	@Autowired
	private LcDocService lcDocService;

	// Leitura do excel do fornecedor
	public void lerExcelCotacao(File excelFile, Long iddoc, Long idfornec) throws Exception {
		FileInputStream file = new FileInputStream(excelFile);
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		int i = 0;
		for (Row row : sheet) {
			// Le dados a partir da 5/6 linha
			if (row.getRowNum() > 5) {
				// Dados padroes
				Long iditem = 0L;
				BigDecimal qtdOferta = new BigDecimal(0);
				BigDecimal vUni = new BigDecimal(0);
				BigDecimal vSub = new BigDecimal(0);
				BigDecimal pDesc = new BigDecimal(0);
				BigDecimal vDesc = new BigDecimal(0);
				BigDecimal vTot = new BigDecimal(0);
				// 0 - CODIGO
				iditem = Long.valueOf(sheet.getRow(i).getCell(0).toString());
				// 4 - QTD. OEFERTADA
				qtdOferta = MoedaUtil.moedaStringBigPDP(sheet.getRow(i).getCell(4).toString());
				// 5 - VALOR UNITARIO CUSTO
				vUni = MoedaUtil.moedaStringBigPDP(sheet.getRow(i).getCell(5).toString());
				// Vtot calculo internamente para forencedor nao errar
				vSub = qtdOferta.multiply(vUni).setScale(6, RoundingMode.HALF_UP);
				// 6 - PERCENTUAL DESCONTO
				pDesc = MoedaUtil.moedaStringBigPDP(sheet.getRow(i).getCell(6).toString());
				// Vdesc calculado internamente
				vDesc = vSub.multiply(pDesc).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
				// Vtot calculo internamente para forencedor nao errar
				vTot = qtdOferta.multiply(vUni).subtract(vDesc).setScale(6, RoundingMode.HALF_UP);
				// Atualiza itens conforme arquivo e fornecedor indicado
				lcDocItemCotRp.updateItemCotFor(qtdOferta, vUni, vSub, pDesc, vDesc, vTot, iditem, idfornec);
				lcDocService.ajustaMelhorPrecoCot(iditem);
			}
			i++;
		}
	}
}
