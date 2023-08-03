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

import com.midas.api.tenant.entity.FnCheque;
import com.midas.api.util.FnChequeStatusTipoNomeUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class FnChequeExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Emitido por", "Caixa de operação", "Título emitido para",
			"Referente à / Descrição", "Título Número", "Parcela", "Emissor", "CPF/CNPJ", "Banco", "Agência", "Conta",
			"Série", "Número cheque", "Data entrada", "Data emissão", "Data vencimento", "Local atual / Último",
			"Caixa atual / Último", "Tipo", "Valor subtotal(R$)", "Valor descontos(R$)", "Valor juros(R$)",
			"Valor total(R$)", "Status" };

	public static ByteArrayInputStream exportToExcel(List<FnCheque> lista) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "Financeiro | Extrato de cheques";
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 20; x < 24; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			for (FnCheque r : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(r.getId());
				row.createCell(1).setCellValue(r.getFncxmv().getCdpessoaemp().getNome());
				row.createCell(2).setCellValue(r.getFncxmv().getCdcaixa().getNome());
				row.createCell(3).setCellValue(r.getFncxmv().getFntitulo().getCdpessoapara().getNome());
				row.createCell(4).setCellValue(r.getFncxmv().getFntitulo().getRef());
				row.createCell(5).setCellValue(r.getFncxmv().getFntitulo().getId() + "");
				row.createCell(6).setCellValue(
						r.getFncxmv().getFntitulo().getParcnum() + "/" + r.getFncxmv().getFntitulo().getQtdparc());
				row.createCell(7).setCellValue(r.getEmissor());
				row.createCell(8).setCellValue(r.getCpfcnpj());
				row.createCell(9).setCellValue(r.getCdbancos_id());
				row.createCell(10).setCellValue(r.getAgencia());
				row.createCell(11).setCellValue(r.getConta());
				row.createCell(12).setCellValue(r.getSerie());
				row.createCell(13).setCellValue(r.getNum());
				row.createCell(14).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getDatacad()));
				row.createCell(15).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getData()));
				row.createCell(16).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getVence()));
				row.createCell(17).setCellValue(r.getCdpessoaempatual().getNome());
				row.createCell(18).setCellValue(r.getCdcaixaatual().getNome());
				row.createCell(19).setCellValue(FnChequeStatusTipoNomeUtil.nomeTipo(r.getTipo()));
				row.createCell(20).setCellValue(MoedaUtil.moedaPadrao(r.getVsub()));
				row.createCell(21).setCellValue(MoedaUtil.moedaPadrao(r.getVdesc()));
				row.createCell(22).setCellValue(MoedaUtil.moedaPadrao(r.getVjuro()));
				row.createCell(23).setCellValue(MoedaUtil.moedaPadrao(r.getVtot()));
				row.createCell(24).setCellValue(FnChequeStatusTipoNomeUtil.nomeSt(r.getStatus()));
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 20; x < 24; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
				}
				// Auto size
				for (int x = 0; x < 25; x++) {
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
