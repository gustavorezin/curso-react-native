package com.midas.api.tenant.service.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.entity.EsEst;
import com.midas.api.tenant.fiscal.util.FsTipoNomeUtil;
import com.midas.api.tenant.fiscal.util.FsUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class EsEstExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Local de controle", "Código", "Produto", "UN", "UN", "Últ. Atualização", "Tipo item",
			"Estoque mínimo", "Estoque atual", "Valor custo", "Valor custo total", "Status" };

	public static ByteArrayInputStream exportToExcel(List<EsEst> lista) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "Estoque";
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 7; x <= 10; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			for (EsEst p : lista) {
				CdProduto prod = p.getCdproduto();
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(p.getCdpessoaemp().getNome());
				row.createCell(1).setCellValue(prod.getCodigo() + "");
				row.createCell(2).setCellValue(prod.getNome());
				row.createCell(3).setCellValue(prod.getCdprodutounmed().getSigla());
				row.createCell(4).setCellValue(prod.getCdprodutounmed().getNome());
				row.createCell(5).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDataat()));
				row.createCell(6).setCellValue(FsTipoNomeUtil.nomeTipoItemEs(prod.getTipoitem()));
				row.createCell(7).setCellValue(FsUtil.cpND(p.getQtdmin() + "", "") + "");
				row.createCell(8).setCellValue(p.getQtd() + "");
				row.createCell(9).setCellValue(MoedaUtil.moedaPadrao4(p.getVcusto()));
				BigDecimal vcustoprod = new BigDecimal(0);
				if (p.getQtd() != new BigDecimal(0))
					vcustoprod = p.getQtd().multiply(p.getVcusto());
				row.createCell(10).setCellValue(MoedaUtil.moedaPadrao4(vcustoprod));
				row.createCell(11).setCellValue(prod.getStatus());
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 7; x <= 10; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
					if (x == 8 || x == 10) {
						cell.setCellStyle(ExcelS.stiloCellRightBoldColor(workbook, "", 8));
					}
				}
				// Se qtd < qtdmin deixar com fonte amarela
				if (p.getQtd().compareTo(p.getQtdmin()) == -1) { // Forma do bigdecimal entender que é menor que o
																	// paramentro passado
					row.getCell(8).setCellStyle(ExcelS.stiloCellRightBoldColor(workbook, "O", 8));
				}
				// Se qtd < 0 deixar com fonte vermelha
				if (p.getQtd().compareTo(BigDecimal.ZERO) == -1) { // Forma do bigdecimal entender que é menor que o
																	// paramentro passado
					row.getCell(8).setCellStyle(ExcelS.stiloCellRightBoldColor(workbook, "R", 8));
				}
				// Auto size
				for (int x = 0; x < 12; x++) {
					sheet.autoSizeColumn(x);
				}
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Falha ao exportar EXCEL: " + e.getMessage());
		}
	}
}
