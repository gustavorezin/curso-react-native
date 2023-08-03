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

import com.midas.api.tenant.entity.LcDoc;
import com.midas.api.util.LcDocTipoNomeUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class LcDocOutrosExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Tipo", "Número", "Hora cadastro", "Data emissão", "Data previsão",
			"Data atualizado", "Data entrega/finalizado", "Emitido por", "Emitido para", "Descrição", "Qtd. itens",
			"Qtd. total", "Valor custo total (R$)", "Informações destinatário", "Informações emitente",
			"Motivo cancelamento", "Status" };

	public static ByteArrayInputStream exportToExcel(List<LcDoc> lista, String tipo) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String infoNome = "Consumo interno";
			if (tipo.equals("07")) {
				infoNome = "Cotacao de precos";
			}
			String SHEET = infoNome;
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 11; x < 14; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			for (int x = 11; x < 14; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			for (LcDoc p : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(p.getId().toString());
				row.createCell(1).setCellValue(LcDocTipoNomeUtil.nomeTipoDoc(p.getTipo()));
				row.createCell(2).setCellValue(p.getNumero().toString());
				row.createCell(3).setCellValue(new SimpleDateFormat("HH:mm:ss").format(p.getHoracad()));
				row.createCell(4).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDataem()));
				row.createCell(5).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDataprev()));
				row.createCell(6).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDataat()));
				row.createCell(7).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDatafat()));
				row.createCell(8).setCellValue(p.getCdpessoaemp().getNome());
				row.createCell(9).setCellValue(p.getCdpessoapara().getNome());
				row.createCell(10).setCellValue(p.getDescgeral());
				row.createCell(11).setCellValue(p.getQtdit().toString());
				row.createCell(12).setCellValue(MoedaUtil.moedaPadrao4(p.getQtd()));
				if (p.getTipo().equals("06")) {
					row.createCell(13).setCellValue(MoedaUtil.moedaPadrao(p.getVtottrib()));
				}
				if (p.getTipo().equals("07")) {
					row.createCell(13).setCellValue("Não Definido");
				}
				row.createCell(14).setCellValue(p.getInfo());
				row.createCell(15).setCellValue(p.getInfolocal());
				row.createCell(16).setCellValue(p.getMotcan());
				row.createCell(17).setCellValue(LcDocTipoNomeUtil.nomeStDocEnt(p.getStatus()));
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 11; x < 14; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
				}
				// Alinha alguns a direita
				for (int x = 11; x < 14; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
					if (x == 13) {
						cell.setCellStyle(ExcelS.stiloCellRightBold(workbook));
					}
				}
				// Auto size
				for (int x = 0; x < 18; x++) {
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
