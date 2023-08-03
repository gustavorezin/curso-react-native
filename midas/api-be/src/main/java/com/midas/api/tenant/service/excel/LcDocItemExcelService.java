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

import com.midas.api.tenant.entity.LcDocItem;
import com.midas.api.tenant.fiscal.util.FsTipoNomeUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class LcDocItemExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Código", "Nome ou descrição", "Tipo", "NCM/Cód.Serv.", "Referência", "EAN",
			"EAN Trib.", "UN", "UN Trib.", "Categoria", "Grupo", "Subgrupo", "Marca", "Tipo de item",
			"QTD. Total do período", "Valor unitário médio R$", "Valor total médio R$" };

	public static ByteArrayInputStream exportToExcel(List<LcDocItem> lista) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "Mais comercializados";
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 15; x < 18; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			for (int x = 15; x < 18; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			for (LcDocItem p : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(p.getId().toString());
				row.createCell(1).setCellValue(p.getCdproduto().getCodigo() + "");
				row.createCell(2).setCellValue(p.getCdproduto().getNome());
				row.createCell(3).setCellValue(p.getCdproduto().getTipo());
				if (p.getCdproduto().getTipo().equals("PRODUTO")) {
					row.createCell(4).setCellValue(p.getCdproduto().getNcm());
				} else {
					row.createCell(4).setCellValue(p.getCdproduto().getCodserv());
				}
				row.createCell(5).setCellValue(p.getCdproduto().getRef());
				row.createCell(6).setCellValue(p.getCdproduto().getCean());
				row.createCell(7).setCellValue(p.getCdproduto().getCeantrib());
				row.createCell(8).setCellValue(p.getCdproduto().getCdprodutounmed().getSigla());
				row.createCell(9).setCellValue(p.getCdproduto().getCdprodutounmedtrib().getSigla());
				row.createCell(10).setCellValue(
						p.getCdproduto().getCdprodutosubgrupo().getCdprodutogrupo().getCdprodutocat().getNome());
				row.createCell(11).setCellValue(p.getCdproduto().getCdprodutosubgrupo().getCdprodutogrupo().getNome());
				row.createCell(12).setCellValue(p.getCdproduto().getCdprodutosubgrupo().getNome());
				row.createCell(13).setCellValue(p.getCdproduto().getCdprodutomarca().getNome());
				row.createCell(14).setCellValue(FsTipoNomeUtil.nomeTipoItemEs(p.getCdproduto().getTipoitem()));
				row.createCell(15).setCellValue(MoedaUtil.moedaPadrao4(p.getQtd()));
				row.createCell(16).setCellValue(MoedaUtil.moedaPadrao4(p.getVuni()));
				row.createCell(17).setCellValue(MoedaUtil.moedaPadrao4(p.getVtottrib()));
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 15; x < 18; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
					if (x == 15) {
						cell.setCellStyle(ExcelS.stiloCellRightBold(workbook));
					}
				}
				// Auto size
				for (int x = 0; x < 20; x++) {
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
