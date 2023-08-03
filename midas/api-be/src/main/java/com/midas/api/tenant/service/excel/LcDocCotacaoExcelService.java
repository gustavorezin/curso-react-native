package com.midas.api.tenant.service.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.tenant.entity.LcDocItem;
import com.midas.api.util.MoedaUtil;

@Service
public class LcDocCotacaoExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID Único", "Código interno", "Nome ou descrição do item", "QTD. Solicitada",
			"QTD. Ofertada", "V. UN. CUSTO R$", "PERC. DESCONTO %", "CUSTO TOTAL R$" };

	public static ByteArrayInputStream exportToExcel(LcDoc doc) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String infoNome = "Cotacao padrao";
			String SHEET = infoNome;
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho inicial--------------------------------
			Row headerRow = sheet.createRow(0);
			Cell cellIni = headerRow.createCell(0);
			cellIni.setCellValue("PEDIDO DE COTAÇÃO PADRONIZADO");
			cellIni.setCellStyle(ExcelS.stiloHeaderP1(workbook));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
			// Observacoes--------------------------------------
			Row headerRowObs = sheet.createRow(1);
			Cell cellObs = headerRowObs.createCell(0);
			cellObs.setCellValue(
					"Mantenha o padrão fornecido neste documento. Sua alteração pode causar a recusa do arquivo");
			cellObs.setCellStyle(ExcelS.stiloRowSec(workbook));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
			// Info altera--------------------------------------
			Row headerRowInfo = sheet.createRow(2);
			Cell cellInfo = headerRowInfo.createCell(0);
			cellInfo.setCellValue(
					"Altere apenas os itens de valores e quantidades marcados em 'CINZA' no padrão '1500,00' ou '37,52' por exemplo");
			cellInfo.setCellStyle(ExcelS.stiloRowSec(workbook));
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));
			// Cabecalho itens--------------------------------
			Row headerRowCab = sheet.createRow(4);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRowCab.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			for (int x = 3; x < 8; x++) {
				Cell cell = headerRowCab.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			// Linha itens-----------------------------------
			int rowIdx = 5;
			for (LcDocItem p : doc.getLcdocitem()) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(p.getId().toString());
				row.createCell(1).setCellValue(p.getCdproduto().getId().toString());
				row.createCell(2).setCellValue(p.getCdproduto().getNome());
				row.createCell(3).setCellValue(MoedaUtil.moedaPadrao(p.getQtd()));
				Double vlrzero = 0.00;
				row.createCell(4).setCellValue(vlrzero);
				row.createCell(5).setCellValue(vlrzero);
				row.createCell(6).setCellValue(vlrzero);
				row.createCell(7).setCellValue(vlrzero);
				// Alinha alguns a direita
				for (int x = 0; x < 8; x++) {
					sheet.autoSizeColumn(x);
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRow(workbook));
					if (x == 3) {
						cell.setCellStyle(ExcelS.stiloCellRightBoldColor(workbook, "B", 9));
					}
					if (x == 4 || x == 5 || x == 6 || x == 7) {
						cell.setCellStyle(ExcelS.stiloCellRightBoldColor(workbook, "G", 9));
					}
				}
			}
			// Info rodape--------------------------------------
			Row headerRowRod = sheet.createRow(rowIdx + 2);
			Cell cellRod = headerRowRod.createCell(0);
			cellRod.setCellValue("Gerado por Midas Software LTDA. www.midassi.com");
			cellRod.setCellStyle(ExcelS.stiloRowSec(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowIdx + 2, rowIdx + 2, 0, 7));
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Falha ao exportar EXCEL: " + e.getMessage());
		}
	}
}
