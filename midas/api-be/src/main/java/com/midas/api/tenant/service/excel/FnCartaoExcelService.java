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

import com.midas.api.tenant.entity.FnCartao;
import com.midas.api.util.FnCartaoStatusNomeUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class FnCartaoExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Emitido por", "Caixa de operação", "Data emissão", "Data previsão",
			"Movimento para", "Referente à / Descrição", "Bandeira ou operadora", "Título Número", "Parcela",
			"Valor subtotal(R$)", "Valor descontos(R$)", "Valor juros(R$)", "Valor total(R$)", "Taxa(%)", "Taxa(R$)",
			"Valor final(R$)", "Status" };

	public static ByteArrayInputStream exportToExcel(List<FnCartao> lista) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "Financeiro | Extrato de cartões";
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 10; x < 17; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			for (FnCartao r : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(r.getId());
				row.createCell(1).setCellValue(r.getFncxmv().getCdpessoaemp().getNome());
				row.createCell(2).setCellValue(r.getFncxmv().getCdcaixa().getNome());
				row.createCell(3).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getDatacad()));
				row.createCell(4).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getDataprev()));
				row.createCell(5).setCellValue(r.getFncxmv().getFntitulo().getCdpessoapara().getNome());
				row.createCell(6).setCellValue(r.getFncxmv().getFntitulo().getRef());
				row.createCell(7).setCellValue(r.getCdcartao().getNome());
				row.createCell(8).setCellValue(r.getFncxmv().getFntitulo().getId() + "");
				row.createCell(9).setCellValue(r.getParcnum() + "/" + r.getQtdparc());
				row.createCell(10).setCellValue(MoedaUtil.moedaPadrao(r.getVsub()));
				row.createCell(11).setCellValue(MoedaUtil.moedaPadrao(r.getVdesc()));
				row.createCell(12).setCellValue(MoedaUtil.moedaPadrao(r.getVjuro()));
				row.createCell(13).setCellValue(MoedaUtil.moedaPadrao(r.getVtot()));
				row.createCell(14).setCellValue(MoedaUtil.moedaPadrao(r.getPtaxa()));
				row.createCell(15).setCellValue(MoedaUtil.moedaPadrao(r.getVtaxa()));
				row.createCell(16).setCellValue(MoedaUtil.moedaPadrao(r.getVfinal()));
				row.createCell(16).setCellValue(FnCartaoStatusNomeUtil.nomeSt(r.getStatus()));
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 10; x < 17; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
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
