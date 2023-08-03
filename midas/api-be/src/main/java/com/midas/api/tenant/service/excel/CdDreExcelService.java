package com.midas.api.tenant.service.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdPlconConta;
import com.midas.api.tenant.entity.CdPlconMacro;
import com.midas.api.tenant.entity.CdPlconMicro;

@Service
public class CdDreExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Máscara", "Contas / Descrição", "Explicativo" };

	public static ByteArrayInputStream exportToExcel(List<CdPlconConta> lista) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "Cadastros | Plano de contas";
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			int rowIdx = 1;
			for (CdPlconConta p : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(p.getMasc() + "");
				row.createCell(1).setCellValue(p.getNome());
				row.createCell(2).setCellValue("");
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Contas Macro
				for (CdPlconMacro pm : p.getCdplconmacro()) {
					Row rowm = sheet.createRow(rowIdx++);
					rowm.createCell(0).setCellValue(p.getMasc() + "." + pm.getMasc() + "");
					rowm.createCell(1).setCellValue(pm.getNome());
					rowm.createCell(2).setCellValue("");
					rowm.setRowStyle(ExcelS.stiloRow(workbook));
					// Contas Micro
					for (CdPlconMicro pmi : pm.getCdplconmicro()) {
						Row rowmi = sheet.createRow(rowIdx++);
						rowmi.createCell(0).setCellValue(p.getMasc() + "." + pm.getMasc() + "." + pmi.getMasc());
						rowmi.createCell(1).setCellValue(pmi.getNome());
						rowmi.createCell(2).setCellValue(pmi.getExplica());
						rowmi.setRowStyle(ExcelS.stiloRow(workbook));
					}
				}
				// Auto size
				for (int x = 0; x <= 2; x++) {
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
