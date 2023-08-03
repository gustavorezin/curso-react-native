package com.midas.api.tenant.service.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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
import com.midas.api.tenant.entity.FnCxmv;
import com.midas.api.tenant.entity.FnCxmvDre;
import com.midas.api.tenant.entity.FnTituloCcusto;
import com.midas.api.tenant.entity.FnTituloDre;
import com.midas.api.tenant.fiscal.util.FnPagNomeUtil;
import com.midas.api.tenant.fiscal.util.FsTipoNomeUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class FnCxmvExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Emitido em", "Número Doc.", "Tipo nota", "Número NF", "Caixa de operação",
			"Tipo", "Emitido para", "Data movimento", "Referente à", "Título número", "Parcela(s)",
			"Forma de pagamento", "Classificação", "Centro de custos", "Valor subtotal(R$)", "Valor descontos(R$)",
			"Valor juros(R$)", "Valor final(R$)", "Status", "Informações gerais" };

	public static ByteArrayInputStream exportToExcel(List<FnCxmv> lista, BigDecimal saldoAnterior) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "Financeiro | Fechamento de caixa ";
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 15; x < 19; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			for (FnCxmv r : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(r.getId().toString());
				row.createCell(1).setCellValue(r.getCdpessoaemp().getNome());
				row.createCell(2)
						.setCellValue(r.getFntitulo().getNumdoc() != null ? r.getFntitulo().getNumdoc() + "" : "0");
				row.createCell(3).setCellValue(FsTipoNomeUtil.nomeModelo(r.getFntitulo().getTpdocfiscal()));
				row.createCell(4)
						.setCellValue(r.getFntitulo().getNumnota() != null ? r.getFntitulo().getNumnota() + "" : "0");
				row.createCell(5).setCellValue(r.getCdcaixa().getNome());
				String tipo = "SAÍDA";
				if (r.getFntitulo().getTipo().equals("R")) {
					tipo = "ENTRADA";
				}
				if (r.getFntitulo().getTipo().equals("TP") || r.getFntitulo().getTipo().equals("TR")) {
					tipo = "TRANSFERÊNCIA";
				}
				row.createCell(6).setCellValue(tipo);
				String para = infoPara(r.getFntitulo().getCdpessoaemp(), r.getFntitulo().getCdpessoapara(),
						r.getFntitulo().getTipo());
				row.createCell(7).setCellValue(para);
				row.createCell(8).setCellValue(
						new SimpleDateFormat("dd/MM/yyyy").format(r.getDatacad()) + " - " + r.getHoracad());
				row.createCell(9).setCellValue(r.getFntitulo().getRef());
				row.createCell(10).setCellValue(r.getFntitulo().getId() + "");
				row.createCell(11).setCellValue(r.getFntitulo().getParcnum() + "/" + r.getFntitulo().getQtdparc());
				row.createCell(12).setCellValue(FnPagNomeUtil.nomePg(r.getFpag()));
				// Verifica se tem mais de um plano
				if (r.getFncxmvdreitem().size() == 1) {
					FnCxmvDre fd = r.getFncxmvdreitem().get(0);
					String plano = fd.getCdplconmicro().getMascfinal() + " " + fd.getCdplconmicro().getNome();
					row.createCell(13).setCellValue(plano);
				}
				// Verifica se tem mais de um centro
				if (r.getFntitulo().getFntituloccustoitem().size() == 1) {
					FnTituloCcusto fd = r.getFntitulo().getFntituloccustoitem().get(0);
					String centro = fd.getCdccusto().getNome();
					row.createCell(14).setCellValue(centro);
				}
				row.createCell(15).setCellValue(MoedaUtil.moedaPadrao(r.getVsub()));
				row.createCell(16).setCellValue(MoedaUtil.moedaPadrao(r.getVdesc()));
				row.createCell(17).setCellValue(MoedaUtil.moedaPadrao(r.getVjuro()));
				row.createCell(18).setCellValue(MoedaUtil.moedaPadrao(r.getVtot()));
				row.createCell(19).setCellValue(r.getStatus());
				row.createCell(20).setCellValue(r.getInfo());
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 15; x < 19; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
					if (x == 18) {
						cell.setCellStyle(ExcelS.stiloCellRightBold(workbook));
					}
				}
				// Auto size
				for (int x = 0; x < 21; x++) {
					sheet.autoSizeColumn(x);
				}
				// Verifica se tem mais de um plano
				if (r.getFntitulo().getFntitulodreitem().size() > 1) {
					for (FnTituloDre fd : r.getFntitulo().getFntitulodreitem()) {
						Row row2 = sheet.createRow(rowIdx++);
						String plano = fd.getCdplconmicro().getMascfinal() + " " + fd.getCdplconmicro().getNome();
						row2.createCell(13).setCellValue(plano);
						BigDecimal vlrparc = r.getVsub().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(15).setCellValue(MoedaUtil.moedaPadrao(vlrparc));
						BigDecimal vlrdesc = r.getVdesc().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(16).setCellValue(MoedaUtil.moedaPadrao(vlrdesc));
						BigDecimal vlrjuro = r.getVjuro().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(17).setCellValue(MoedaUtil.moedaPadrao(vlrjuro));
						BigDecimal vlrtot = r.getVtot().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(18).setCellValue(MoedaUtil.moedaPadrao(vlrtot));
						row2.setRowStyle(ExcelS.stiloRow(workbook));
						// Alinha alguns a direita
						Cell cellum = row2.getCell(13);
						cellum.setCellStyle(ExcelS.stiloRowSec(workbook));
						// Alinha alguns a direita
						for (int x = 15; x < 19; x++) {
							Cell cell = row2.getCell(x);
							cell.setCellStyle(ExcelS.stiloRowSecRight(workbook));
						}
					}
				}
				// Verifica se tem mais de um plano
				if (r.getFntitulo().getFntituloccustoitem().size() > 1) {
					for (FnTituloCcusto fd : r.getFntitulo().getFntituloccustoitem()) {
						Row row2 = sheet.createRow(rowIdx++);
						String centro = fd.getCdccusto().getNome();
						row2.createCell(14).setCellValue(centro);
						BigDecimal vlrparc = r.getVsub().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(15).setCellValue(MoedaUtil.moedaPadrao(vlrparc));
						BigDecimal vlrdesc = r.getVdesc().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(16).setCellValue(MoedaUtil.moedaPadrao(vlrdesc));
						BigDecimal vlrjuro = r.getVjuro().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(17).setCellValue(MoedaUtil.moedaPadrao(vlrjuro));
						BigDecimal vlrtot = r.getVtot().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(18).setCellValue(MoedaUtil.moedaPadrao(vlrtot));
						row2.setRowStyle(ExcelS.stiloRow(workbook));
						// Alinha alguns a direita
						Cell cellum = row2.getCell(14);
						cellum.setCellStyle(ExcelS.stiloRowSec(workbook));
						// Alinha alguns a direita
						for (int x = 15; x < 19; x++) {
							Cell cell = row2.getCell(x);
							cell.setCellStyle(ExcelS.stiloRowSecRight(workbook));
						}
					}
				}
			}
			// CRIA LINHA SALDO
			Row row = sheet.createRow(rowIdx + 1);
			row.createCell(6).setCellValue("SALDO");
			row.createCell(7).setCellValue("SALDO ANTERIOR");
			row.createCell(8).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			row.createCell(15).setCellValue(MoedaUtil.moedaPadrao(saldoAnterior));
			Cell cell = row.getCell(15);
			cell.setCellStyle(ExcelS.stiloCellRightBold(workbook));
			row.setRowStyle(ExcelS.stiloRow(workbook));
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Falha ao exportar EXCEL: " + e.getMessage());
		}
	}

	private static String infoPara(CdPessoa local01, CdPessoa local02, String tipo) {
		String ret = local02.getNome();
		if (local01 == local02) {
			ret = "LANÇAMENTO MANUAL DE CAIXA";
			if (tipo.equals("TP") || tipo.equals("TR")) {
				ret = "TRANSFERÊNCIA ENTRE CONTAS";
			}
		}
		return ret;
	}
}
