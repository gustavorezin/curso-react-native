package com.midas.api.tenant.service.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.CdPessoa;

@Service
public class CdPessoaExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Cadastrado em", "Nascimento / Fundação","Nome ou razão social", "Apelido ou fantasia", "CPF / CNPJ",
			"RG / IE", "Telefone principal", "E-mail", "Rua / Logradouro", "Número", "Complemento", "Bairro", "CEP",
			"Cidade", "UF", "Status" };

	public static ByteArrayInputStream exportToExcel(List<CdPessoa> lista, String pessoatipo) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "Cadastros | " + pessoatipo;
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			int rowIdx = 1;
			for (CdPessoa p : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(p.getId());
				row.createCell(1).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDatacad()));
				Date dataNascFund = p.getNascfund();
				if (dataNascFund != null) {					
					row.createCell(2).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(dataNascFund));
				} else {
					row.createCell(2).setCellValue("");
				}
				row.createCell(3).setCellValue(p.getNome());
				row.createCell(4).setCellValue(p.getFantasia());
				row.createCell(5).setCellValue(p.getCpfcnpj());
				row.createCell(6).setCellValue(p.getIerg());
				row.createCell(7).setCellValue(p.getFoneum());
				row.createCell(8).setCellValue(p.getEmail());
				row.createCell(9).setCellValue(p.getRua());
				row.createCell(10).setCellValue(p.getNum());
				row.createCell(11).setCellValue(p.getComp());
				row.createCell(12).setCellValue(p.getBairro());
				row.createCell(13).setCellValue(p.getCep());
				row.createCell(14).setCellValue(p.getCdcidade().getNome());
				row.createCell(15).setCellValue(p.getCdestado().getUf());
				row.createCell(16).setCellValue(p.getStatus());
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Auto size
				for (int x = 0; x <= 16; x++) {
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
