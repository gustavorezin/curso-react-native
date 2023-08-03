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

import com.midas.api.tenant.entity.FsCte;
import com.midas.api.tenant.fiscal.util.FsTipoNomeUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class FsCteExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Série", "Tipo", "Modelo", "Número CT", "Ambiente", "Data emissão",
			"Hora emissão", "Data entrada/saída", "Emitido por", "Remetente", "Destinatário", "Destino",
			"Valor receber(R$)", "Valor serviços(R$)", "Chave de acesso", "Status" };

	public static ByteArrayInputStream exportToExcel(List<FsCte> lista) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "CT-e";
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 13; x <= 14; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			for (FsCte p : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(p.getId().toString());
				row.createCell(1).setCellValue(p.getSerie() + "");
				row.createCell(2).setCellValue(FsTipoNomeUtil.nomeTipoEmis(String.valueOf(p.getTpemis())));
				row.createCell(3).setCellValue(FsTipoNomeUtil.nomeModelo(String.valueOf(p.getModelo())));
				row.createCell(4).setCellValue(p.getNct() != null ? p.getNct().toString() : "0");
				row.createCell(5).setCellValue(FsTipoNomeUtil.nomeAmb(String.valueOf(p.getTpamb())));
				row.createCell(6).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDhemi()));
				row.createCell(7).setCellValue(new SimpleDateFormat("HH:mm:ss").format(p.getDhemihr()));
				row.createCell(8).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDhsaient()));
				row.createCell(9).setCellValue(p.getFsctepartemit().getXnome());
				row.createCell(10).setCellValue(p.getFsctepartrem().getXnome());
				row.createCell(11).setCellValue(p.getFsctepartdest().getXnome());
				row.createCell(12).setCellValue(p.getUffim());
				row.createCell(13).setCellValue(MoedaUtil.moedaPadrao(p.getFsctevprest().getVrec()));
				row.createCell(14).setCellValue(MoedaUtil.moedaPadrao(p.getFsctevprest().getVtprest()));
				row.createCell(15).setCellValue(p.getChaveac() + "");
				row.createCell(16).setCellValue(FsTipoNomeUtil.nomeStatus(String.valueOf(p.getStatus())));
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 13; x <= 14; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
					cell.setCellStyle(ExcelS.stiloCellRightBold(workbook));
				}
				// Fundo amarelo na celula AMBIENTE caso for homologacao
				if (p.getTpamb() == 2) {
					Cell cell = row.getCell(5);
					cell.setCellStyle(ExcelS.stiloCellAmarelo(workbook));
				}
				// Auto size
				for (int x = 0; x < 17; x++) {
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
