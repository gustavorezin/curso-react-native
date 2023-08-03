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

import com.midas.api.tenant.entity.FnTituloCcusto;
import com.midas.api.tenant.entity.FnTituloDre;
import com.midas.api.tenant.fiscal.util.FnPagNomeUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class FnTituloCcustoExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Título", "Emitido por", "Caixa preferencial", "Forma de pagamento preferencial",
			"Tipo", "Emitido para", "Data emissão", "Data vencimento", "Baixa (Última)", "Referente à", "Classificação",
			"Centro de custos", "Parcela(s)", "Valor parcela(R$)", "Valor descontos(R$)", "Valor juros(R$)",
			"Valor total(R$)", "Valor pago/recebido(R$)", "Saldo Restante (R$)", "Informações gerais" };

	public static ByteArrayInputStream exportToExcel(List<FnTituloCcusto> abertos, List<FnTituloCcusto> pagos)
			throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "Financeiro | Centro de custos";
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 13; x < 19; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			// LISTA ABERTOS ***************************************************************
			Row headerRowAberto = sheet.createRow(rowIdx++);
			Cell cellAb = headerRowAberto.createCell(0);
			cellAb.setCellValue("Em aberto / Parciais");
			cellAb.setCellStyle(ExcelS.stiloHeaderSec(workbook));
			for (int col = 1; col < HEADERs.length; col++) {
				Cell cell = headerRowAberto.createCell(col);
				cell.setCellValue("");
				cell.setCellStyle(ExcelS.stiloHeaderSec(workbook));
			}
			// LISTA ABERTOS ***************************************************************
			for (FnTituloCcusto r : abertos) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(r.getFntitulo().getId());
				row.createCell(1).setCellValue(r.getFntitulo().getCdpessoaemp().getNome());
				String tipo = "Pagamento";
				if (r.getFntitulo().getTipo().equals("R")) {
					tipo = "Recebimento";
				}
				row.createCell(2).setCellValue(r.getFntitulo().getCdcaixapref().getNome());
				row.createCell(3).setCellValue(FnPagNomeUtil.nomePg(r.getFntitulo().getFpagpref()));
				row.createCell(4).setCellValue(tipo);
				row.createCell(5).setCellValue(r.getFntitulo().getCdpessoapara().getNome());
				row.createCell(6).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getFntitulo().getDatacad()));
				row.createCell(7).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getFntitulo().getVence()));
				if (r.getFntitulo().getVpago().compareTo(r.getFntitulo().getVsaldo()) == 1) {
					row.createCell(8)
							.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getFntitulo().getDatabaixa()));
				} else {
					row.createCell(8).setCellValue("Sem baixas");
				}
				row.createCell(9).setCellValue(r.getFntitulo().getRef());
				// Verifica se tem mais de um plano
				if (r.getFntitulo().getFntitulodreitem().size() == 1) {
					FnTituloDre fd = r.getFntitulo().getFntitulodreitem().get(0);
					String plano = fd.getCdplconmicro().getMascfinal() + " " + fd.getCdplconmicro().getNome();
					row.createCell(10).setCellValue(plano);
				}
				// Verifica se tem mais de um centro
				if (r.getFntitulo().getFntituloccustoitem().size() == 1) {
					FnTituloCcusto fd = r.getFntitulo().getFntituloccustoitem().get(0);
					String centro = fd.getCdccusto().getNome();
					row.createCell(11).setCellValue(centro);
				}
				row.createCell(12).setCellValue(r.getFntitulo().getParcnum() + "/" + r.getFntitulo().getQtdparc());
				row.createCell(13).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVparc()));
				row.createCell(14).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVdesc()));
				row.createCell(15).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVjuro()));
				row.createCell(16).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVtot()));
				row.createCell(17).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVpago()));
				row.createCell(18).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVsaldo()));
				row.createCell(19).setCellValue(r.getFntitulo().getInfo());
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 13; x < 19; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
				}
				// Auto size
				for (int x = 0; x < 20; x++) {
					sheet.autoSizeColumn(x);
				}
				// Verifica se tem mais de um plano
				if (r.getFntitulo().getFntitulodreitem().size() > 1) {
					for (FnTituloDre fd : r.getFntitulo().getFntitulodreitem()) {
						Row row2 = sheet.createRow(rowIdx++);
						String plano = fd.getCdplconmicro().getMascfinal() + " " + fd.getCdplconmicro().getNome();
						row2.createCell(10).setCellValue(plano);
						BigDecimal vlrparc = r.getFntitulo().getVparc().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(13).setCellValue(MoedaUtil.moedaPadrao(vlrparc));
						BigDecimal vlrdesc = r.getFntitulo().getVdesc().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(14).setCellValue(MoedaUtil.moedaPadrao(vlrdesc));
						BigDecimal vlrjuro = r.getFntitulo().getVjuro().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(15).setCellValue(MoedaUtil.moedaPadrao(vlrjuro));
						BigDecimal vlrtot = r.getFntitulo().getVtot().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(16).setCellValue(MoedaUtil.moedaPadrao(vlrtot));
						BigDecimal vlrpago = r.getFntitulo().getVpago().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(17).setCellValue(MoedaUtil.moedaPadrao(vlrpago));
						BigDecimal vlrsaldo = r.getFntitulo().getVsaldo().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(18).setCellValue(MoedaUtil.moedaPadrao(vlrsaldo));
						row2.setRowStyle(ExcelS.stiloRow(workbook));
						// Alinha alguns a direita
						Cell cellum = row2.getCell(10);
						cellum.setCellStyle(ExcelS.stiloRowSec(workbook));
						for (int x = 13; x < 19; x++) {
							Cell cell = row2.getCell(x);
							cell.setCellStyle(ExcelS.stiloRowSecRight(workbook));
						}
					}
				}
				// Verifica se tem mais de um centro
				if (r.getFntitulo().getFntituloccustoitem().size() > 1) {
					for (FnTituloCcusto fd : r.getFntitulo().getFntituloccustoitem()) {
						Row row2 = sheet.createRow(rowIdx++);
						String centro = fd.getCdccusto().getNome();
						row2.createCell(11).setCellValue(centro);
						BigDecimal vlrparc = r.getFntitulo().getVparc().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(13).setCellValue(MoedaUtil.moedaPadrao(vlrparc));
						BigDecimal vlrdesc = r.getFntitulo().getVdesc().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(14).setCellValue(MoedaUtil.moedaPadrao(vlrdesc));
						BigDecimal vlrjuro = r.getFntitulo().getVjuro().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(15).setCellValue(MoedaUtil.moedaPadrao(vlrjuro));
						BigDecimal vlrtot = r.getFntitulo().getVtot().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(16).setCellValue(MoedaUtil.moedaPadrao(vlrtot));
						BigDecimal vlrpago = r.getFntitulo().getVpago().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(17).setCellValue(MoedaUtil.moedaPadrao(vlrpago));
						BigDecimal vlrsaldo = r.getFntitulo().getVsaldo().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(18).setCellValue(MoedaUtil.moedaPadrao(vlrsaldo));
						row2.setRowStyle(ExcelS.stiloRow(workbook));
						// Alinha alguns a direita
						Cell cellum = row2.getCell(11);
						cellum.setCellStyle(ExcelS.stiloRowSec(workbook));
						for (int x = 13; x < 19; x++) {
							Cell cell = row2.getCell(x);
							cell.setCellStyle(ExcelS.stiloRowSecRight(workbook));
						}
					}
				}
			}
			// LISTA BAIXADOS
			// ***************************************************************
			Row headerRowBaixados = sheet.createRow(rowIdx++);
			Cell cellBa = headerRowBaixados.createCell(0);
			cellBa.setCellValue("Baixados / Parciais");
			cellBa.setCellStyle(ExcelS.stiloHeaderSec(workbook));
			for (int col = 1; col < HEADERs.length; col++) {
				Cell cell = headerRowBaixados.createCell(col);
				cell.setCellValue("");
				cell.setCellStyle(ExcelS.stiloHeaderSec(workbook));
			}
			// LISTA BAIXADOS
			// ***************************************************************
			for (FnTituloCcusto r : pagos) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(r.getFntitulo().getId());
				row.createCell(1).setCellValue(r.getFntitulo().getCdpessoaemp().getNome());
				String tipo = "Pagamento";
				if (r.getFntitulo().getTipo().equals("R")) {
					tipo = "Recebimento";
				}
				row.createCell(2).setCellValue(r.getFntitulo().getCdcaixapref().getNome());
				row.createCell(3).setCellValue(FnPagNomeUtil.nomePg(r.getFntitulo().getFpagpref()));
				row.createCell(4).setCellValue(tipo);
				row.createCell(5).setCellValue(r.getFntitulo().getCdpessoapara().getNome());
				row.createCell(6).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getFntitulo().getDatacad()));
				row.createCell(7).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getFntitulo().getVence()));
				if (r.getFntitulo().getVpago().compareTo(r.getFntitulo().getVsaldo()) == 1) {
					row.createCell(8)
							.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(r.getFntitulo().getDatabaixa()));
				} else {
					row.createCell(8).setCellValue("Sem baixas");
				}
				row.createCell(9).setCellValue(r.getFntitulo().getRef());
				// Verifica se tem mais de um plano
				if (r.getFntitulo().getFntitulodreitem().size() == 1) {
					FnTituloDre fd = r.getFntitulo().getFntitulodreitem().get(0);
					String plano = fd.getCdplconmicro().getMascfinal() + " " + fd.getCdplconmicro().getNome();
					row.createCell(10).setCellValue(plano);
				}
				// Verifica se tem mais de um centro
				if (r.getFntitulo().getFntituloccustoitem().size() == 1) {
					FnTituloCcusto fd = r.getFntitulo().getFntituloccustoitem().get(0);
					String centro = fd.getCdccusto().getNome();
					row.createCell(11).setCellValue(centro);
				}
				row.createCell(12).setCellValue(r.getFntitulo().getParcnum() + "/" + r.getFntitulo().getQtdparc());
				row.createCell(13).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVparc()));
				row.createCell(14).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVdesc()));
				row.createCell(15).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVjuro()));
				row.createCell(16).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVtot()));
				row.createCell(17).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVpago()));
				row.createCell(18).setCellValue(MoedaUtil.moedaPadrao(r.getFntitulo().getVsaldo()));
				row.createCell(19).setCellValue(r.getFntitulo().getInfo());
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 13; x < 19; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
				}
				// Verifica se tem mais de um plano
				if (r.getFntitulo().getFntitulodreitem().size() > 1) {
					for (FnTituloDre fd : r.getFntitulo().getFntitulodreitem()) {
						Row row2 = sheet.createRow(rowIdx++);
						String plano = fd.getCdplconmicro().getMascfinal() + " " + fd.getCdplconmicro().getNome();
						row2.createCell(10).setCellValue(plano);
						BigDecimal vlrparc = r.getFntitulo().getVparc().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(13).setCellValue(MoedaUtil.moedaPadrao(vlrparc));
						BigDecimal vlrdesc = r.getFntitulo().getVdesc().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(14).setCellValue(MoedaUtil.moedaPadrao(vlrdesc));
						BigDecimal vlrjuro = r.getFntitulo().getVjuro().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(15).setCellValue(MoedaUtil.moedaPadrao(vlrjuro));
						BigDecimal vlrtot = r.getFntitulo().getVtot().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(16).setCellValue(MoedaUtil.moedaPadrao(vlrtot));
						BigDecimal vlrpago = r.getFntitulo().getVpago().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(17).setCellValue(MoedaUtil.moedaPadrao(vlrpago));
						BigDecimal vlrsaldo = r.getFntitulo().getVsaldo().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(18).setCellValue(MoedaUtil.moedaPadrao(vlrsaldo));
						row2.setRowStyle(ExcelS.stiloRow(workbook));
						// Alinha alguns a direita
						Cell cellum = row2.getCell(10);
						cellum.setCellStyle(ExcelS.stiloRowSec(workbook));
						for (int x = 13; x < 19; x++) {
							Cell cell = row2.getCell(x);
							cell.setCellStyle(ExcelS.stiloRowSecRight(workbook));
						}
					}
				}
				// Verifica se tem mais de um centro
				if (r.getFntitulo().getFntituloccustoitem().size() > 1) {
					for (FnTituloCcusto fd : r.getFntitulo().getFntituloccustoitem()) {
						Row row2 = sheet.createRow(rowIdx++);
						String centro = fd.getCdccusto().getNome();
						row2.createCell(11).setCellValue(centro);
						BigDecimal vlrparc = r.getFntitulo().getVparc().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(13).setCellValue(MoedaUtil.moedaPadrao(vlrparc));
						BigDecimal vlrdesc = r.getFntitulo().getVdesc().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(14).setCellValue(MoedaUtil.moedaPadrao(vlrdesc));
						BigDecimal vlrjuro = r.getFntitulo().getVjuro().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(15).setCellValue(MoedaUtil.moedaPadrao(vlrjuro));
						BigDecimal vlrtot = r.getFntitulo().getVtot().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(16).setCellValue(MoedaUtil.moedaPadrao(vlrtot));
						BigDecimal vlrpago = r.getFntitulo().getVpago().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(17).setCellValue(MoedaUtil.moedaPadrao(vlrpago));
						BigDecimal vlrsaldo = r.getFntitulo().getVsaldo().multiply(fd.getPvalor())
								.divide(new BigDecimal(100));
						row2.createCell(18).setCellValue(MoedaUtil.moedaPadrao(vlrsaldo));
						row2.setRowStyle(ExcelS.stiloRow(workbook));
						// Alinha alguns a direita
						Cell cellum = row2.getCell(11);
						cellum.setCellStyle(ExcelS.stiloRowSec(workbook));
						for (int x = 13; x < 19; x++) {
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
