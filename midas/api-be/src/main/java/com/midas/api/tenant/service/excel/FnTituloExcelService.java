package com.midas.api.tenant.service.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.FnTitulo;
import com.midas.api.tenant.entity.FnTituloCcusto;
import com.midas.api.tenant.entity.FnTituloDre;
import com.midas.api.tenant.fiscal.util.FnPagNomeUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class FnTituloExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Emitido por", "Número Doc.", "Número NF", "Caixa preferencial",
			"Forma de pagamento preferencial", "Tipo", "Emitido para", "Data emissão", "Data vencimento",
			"Baixa (Última)", "Referente à", "Classificação", "Centro de custos", "Parcela(s)", "Valor parcela(R$)",
			"Valor FCP ST(R$)", "Valor ICMS ST(R$)", "Valor IPI(R$)", "Valor frete(R$)", "Valor descontos(R$)",
			"Valor juros(R$)", "Valor total(R$)", "Valor pago/recebido(R$)", "Saldo Restante (R$)",
			"Informações gerais" };

	public static ByteArrayInputStream exportToExcel(List<FnTitulo> lista, String tp) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String nometp = "pagar";
			if (tp.equals("R")) {
				nometp = "receber";
			}
			String SHEET = "Financeiro | Contas a " + nometp;
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 15; x < 25; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			for (FnTitulo r : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(r.getId().toString());
				row.createCell(1).setCellValue(r.getCdpessoaemp().getNome());
				row.createCell(2).setCellValue(r.getNumdoc() != null ? r.getNumdoc().toString() : "0");
				row.createCell(3).setCellValue(r.getNumnota() != null ? r.getNumnota().toString() : "0");
				row.createCell(4).setCellValue(r.getCdcaixapref().getNome());
				row.createCell(5).setCellValue(FnPagNomeUtil.nomePg(r.getFpagpref()));
				String tipo = "Pagamento";
				if (r.getTipo().equals("R")) {
					tipo = "Recebimento";
				}
				row.createCell(6).setCellValue(tipo);
				row.createCell(7).setCellValue(r.getCdpessoapara().getNome());
				row.createCell(8).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getDatacad()));
				row.createCell(9).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getVence()));
				if (r.getVpago().compareTo(r.getVsaldo()) == 1) {
					row.createCell(10).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getDatabaixa()));
				} else {
					row.createCell(10).setCellValue("Sem baixas");
				}
				row.createCell(11).setCellValue(r.getRef());
				// Verifica se tem mais de um plano
				if (r.getFntitulodreitem().size() == 1) {
					FnTituloDre fd = r.getFntitulodreitem().get(0);
					String plano = fd.getCdplconmicro().getMascfinal() + " " + fd.getCdplconmicro().getNome();
					row.createCell(12).setCellValue(plano);
				}
				// Verifica se tem mais de um centro
				if (r.getFntituloccustoitem().size() == 1) {
					FnTituloCcusto fd = r.getFntituloccustoitem().get(0);
					String centro = fd.getCdccusto().getNome();
					row.createCell(13).setCellValue(centro);
				}
				row.createCell(14).setCellValue(r.getParcnum() + "/" + r.getQtdparc());
				row.createCell(15).setCellValue(MoedaUtil.moedaPadrao(r.getVparc()));
				row.createCell(16).setCellValue(MoedaUtil.moedaPadrao(r.getVfcpst()));
				row.createCell(17).setCellValue(MoedaUtil.moedaPadrao(r.getVicmsst()));
				row.createCell(18).setCellValue(MoedaUtil.moedaPadrao(r.getVipi()));
				row.createCell(19).setCellValue(MoedaUtil.moedaPadrao(r.getVfrete()));
				row.createCell(20).setCellValue(MoedaUtil.moedaPadrao(r.getVdesc()));
				row.createCell(21).setCellValue(MoedaUtil.moedaPadrao(r.getVjuro()));
				row.createCell(22).setCellValue(MoedaUtil.moedaPadrao(r.getVtot()));
				row.createCell(23).setCellValue(MoedaUtil.moedaPadrao(r.getVpago()));
				row.createCell(24).setCellValue(MoedaUtil.moedaPadrao(r.getVsaldo()));
				row.createCell(25).setCellValue(r.getInfo());
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 15; x < 25; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
					if (x == 22 || x == 23 || x == 24) {
						cell.setCellStyle(ExcelS.stiloCellRightBold(workbook));
					}
				}
				// Auto size
				for (int x = 0; x < 25; x++) {
					sheet.autoSizeColumn(x);
				}
				// Verifica se tem mais de um plano
				if (r.getFntitulodreitem().size() > 1) {
					for (FnTituloDre fd : r.getFntitulodreitem()) {
						Row row2 = sheet.createRow(rowIdx++);
						String plano = fd.getCdplconmicro().getMascfinal() + " " + fd.getCdplconmicro().getNome();
						row2.createCell(12).setCellValue(plano);
						BigDecimal vlrparc = r.getVparc().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(15).setCellValue(MoedaUtil.moedaPadrao(vlrparc));
						BigDecimal vlrdesc = r.getVdesc().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(16).setCellValue(MoedaUtil.moedaPadrao(vlrdesc));
						BigDecimal vlrjuro = r.getVjuro().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(17).setCellValue(MoedaUtil.moedaPadrao(vlrjuro));
						BigDecimal vlrtot = r.getVtot().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(18).setCellValue(MoedaUtil.moedaPadrao(vlrtot));
						BigDecimal vlrpago = r.getVpago().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(19).setCellValue(MoedaUtil.moedaPadrao(vlrpago));
						BigDecimal vlrsaldo = r.getVsaldo().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(20).setCellValue(MoedaUtil.moedaPadrao(vlrsaldo));
						row2.setRowStyle(ExcelS.stiloRow(workbook));
						// Alinha alguns a direita
						Cell cellum = row2.getCell(12);
						cellum.setCellStyle(ExcelS.stiloRowSec(workbook));
						for (int x = 15; x <= 20; x++) {
							Cell cell = row2.getCell(x);
							cell.setCellStyle(ExcelS.stiloRowSecRight(workbook));
						}
					}
				}
				// Verifica se tem mais de um centro
				if (r.getFntituloccustoitem().size() > 1) {
					for (FnTituloCcusto fd : r.getFntituloccustoitem()) {
						Row row2 = sheet.createRow(rowIdx++);
						String centro = fd.getCdccusto().getNome();
						row2.createCell(13).setCellValue(centro);
						BigDecimal vlrparc = r.getVparc().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(15).setCellValue(MoedaUtil.moedaPadrao(vlrparc));
						BigDecimal vlrdesc = r.getVdesc().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(16).setCellValue(MoedaUtil.moedaPadrao(vlrdesc));
						BigDecimal vlrjuro = r.getVjuro().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(17).setCellValue(MoedaUtil.moedaPadrao(vlrjuro));
						BigDecimal vlrtot = r.getVtot().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(18).setCellValue(MoedaUtil.moedaPadrao(vlrtot));
						BigDecimal vlrpago = r.getVpago().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(19).setCellValue(MoedaUtil.moedaPadrao(vlrpago));
						BigDecimal vlrsaldo = r.getVsaldo().multiply(fd.getPvalor()).divide(new BigDecimal(100));
						row2.createCell(20).setCellValue(MoedaUtil.moedaPadrao(vlrsaldo));
						row2.setRowStyle(ExcelS.stiloRow(workbook));
						// Alinha alguns a direita
						Cell cellum = row2.getCell(13);
						cellum.setCellStyle(ExcelS.stiloRowSec(workbook));
						for (int x = 15; x <= 20; x++) {
							Cell cell = row2.getCell(x);
							cell.setCellStyle(ExcelS.stiloRowSecRight(workbook));
						}
					}
				}
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Falha ao exportar EXCEL: " + e.getMessage());
		}
	}
}
