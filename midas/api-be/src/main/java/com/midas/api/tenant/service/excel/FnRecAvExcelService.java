package com.midas.api.tenant.service.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.FnRecAv;
import com.midas.api.util.MoedaUtil;

@Service
public class FnRecAvExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Data emissão", "Hora", "Emitido por", "Emitido para", "Referente à", "Tipo",
			"Valor subtotal(R$)", "Valor desconto(R$)", "Valor juro(R$)", "Valor total(R$)" };

	public static ByteArrayInputStream exportToExcel(List<FnRecAv> lista) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "Financeiro | Recibo Avulso";
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 7; x < 11; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			for (FnRecAv r : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(r.getId());
				row.createCell(1).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getData()));
				row.createCell(2).setCellValue(new SimpleDateFormat("HH:mm:ss").format(r.getHora()));
				row.createCell(3).setCellValue(r.getCdpessoaemp().getNome());
				row.createCell(4).setCellValue(r.getCdpessoapara().getNome());
				String tipo = "Pagamento";
				if (r.getTipo().equals("R")) {
					tipo = "Recebimento";
				}
				row.createCell(5).setCellValue(r.getRef());
				row.createCell(6).setCellValue(tipo);
				row.createCell(7).setCellValue(MoedaUtil.moedaPadrao(r.getVsub()));
				row.createCell(8).setCellValue(MoedaUtil.moedaPadrao(r.getVdesc()));
				row.createCell(9).setCellValue(MoedaUtil.moedaPadrao(r.getVjuro()));
				row.createCell(10).setCellValue(MoedaUtil.moedaPadrao(r.getVtot()));
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 7; x < 11; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
				}
				// Auto size
				for (int x = 0; x < 11; x++) {
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
