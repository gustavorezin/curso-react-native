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
import com.midas.api.tenant.fiscal.util.FnPagNomeUtil;
import com.midas.api.tenant.fiscal.util.FsModFreteNomeUtil;
import com.midas.api.util.LcDocTipoNomeUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class LcDocExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Tipo", "Categoria", "Número", "Hora cadastro", "Data emissão", "Data atualizado",
			"Data previsão", "Data faturamento", "Emitido por", "Emitido para", "Vendedor", "Número NF", "Qtd. itens",
			"Qtd. total", "Metro cúbico", "Peso bruto(Kg)", "Peso líquido(Kg)", "Modelo de frete", "Forma pagamento",
			"Condição pagamento", "Caixa", "Ordem de compra/serviço", "Valor subtotal(R$)", "Valor desconto(R$)",
			"Valor transporte(R$)", "Desconto extra(%)", "Valor desc. extra(R$)", "Valor total(R$)",
			"Valor tributos(R$)", "Valor total trib.(R$)", "Comissão(%)", "Valor comissão(R$)", "Informações cliente",
			"Informações emitente", "Motivo cancelamento", "Placa", "Status", "Conferido" };

	public static ByteArrayInputStream exportToExcel(List<LcDoc> lista) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "Lançamentos - Doc";
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 13; x < 18; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			for (int x = 23; x < 33; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			for (LcDoc p : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(p.getId().toString());
				row.createCell(1).setCellValue(LcDocTipoNomeUtil.nomeTipoDoc(p.getTipo()));
				// Se pedido de compra
				if (!p.getTipo().equals("08")) {
					row.createCell(2).setCellValue(LcDocTipoNomeUtil.nomeCat(p.getCategoria()));
				} else {
					row.createCell(2).setCellValue("PEDIDO");
				}
				row.createCell(3).setCellValue(p.getNumero().toString());
				row.createCell(4).setCellValue(new SimpleDateFormat("HH:mm:ss").format(p.getHoracad()));
				row.createCell(5).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDataem()));
				row.createCell(6).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDataat()));
				row.createCell(7).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDataprev()));
				row.createCell(8).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDatafat()));
				row.createCell(9).setCellValue(p.getCdpessoaemp().getNome());
				row.createCell(10).setCellValue(p.getCdpessoapara().getNome());
				// Se pedido de compra
				if (!p.getTipo().equals("08")) {
					row.createCell(11).setCellValue(p.getCdpessoavendedor().getNome());
				} else {
					row.createCell(11).setCellValue("");
				}
				row.createCell(12).setCellValue(p.getNumnota() != null ? p.getNumnota().toString() : "0");
				row.createCell(13).setCellValue(p.getQtdit().toString());
				row.createCell(14).setCellValue(MoedaUtil.moedaPadrao4(p.getQtd()));
				row.createCell(15).setCellValue(MoedaUtil.moedaPadrao4(p.getMmcub()));
				row.createCell(16).setCellValue(MoedaUtil.moedaPadrao4(p.getMpesob()));
				row.createCell(17).setCellValue(MoedaUtil.moedaPadrao4(p.getMpesol()));
				row.createCell(18).setCellValue(FsModFreteNomeUtil.nomeFrete(p.getModfrete()));
				row.createCell(19).setCellValue(FnPagNomeUtil.nomePg(p.getFpag()));
				row.createCell(20).setCellValue(p.getCdcondpag().getNome());
				row.createCell(21).setCellValue(p.getCdcaixa().getNome());
				row.createCell(22).setCellValue(p.getOrdemcps());
				row.createCell(23).setCellValue(MoedaUtil.moedaPadrao(p.getVsub()));
				row.createCell(24).setCellValue(MoedaUtil.moedaPadrao(p.getVdesc()));
				row.createCell(25).setCellValue(MoedaUtil.moedaPadrao(p.getVtransp()));
				row.createCell(26).setCellValue(MoedaUtil.moedaPadrao(p.getPdescext()));
				row.createCell(27).setCellValue(MoedaUtil.moedaPadrao(p.getVdescext()));
				row.createCell(28).setCellValue(MoedaUtil.moedaPadrao(p.getVtot()));
				row.createCell(29).setCellValue(MoedaUtil.moedaPadrao(p.getVtribcob()));
				row.createCell(30).setCellValue(MoedaUtil.moedaPadrao(p.getVtottrib()));
				// Se pedido de compra
				if (!p.getTipo().equals("08")) {
					row.createCell(31).setCellValue(MoedaUtil.moedaPadrao(p.getPcom()));
					row.createCell(32).setCellValue(MoedaUtil.moedaPadrao(p.getVcom()));
				} else {
					row.createCell(31).setCellValue("0,00");
					row.createCell(32).setCellValue("0,00");
				}
				row.createCell(33).setCellValue(p.getInfo());
				row.createCell(34).setCellValue(p.getInfolocal());
				row.createCell(35).setCellValue(p.getMotcan());
				row.createCell(36).setCellValue(p.getCdveiculo().getPlaca());
				row.createCell(37).setCellValue(LcDocTipoNomeUtil.nomeStDoc(p.getStatus()));
				row.createCell(38).setCellValue(p.getAdminconf());
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 13; x < 18; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
				}
				// Alinha alguns a direita
				for (int x = 23; x < 33; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloRowRight(workbook));
					if (x == 28 || x == 29 || x == 30) {
						cell.setCellStyle(ExcelS.stiloCellRightBold(workbook));
					}
				}
				// Auto size
				for (int x = 0; x < 39; x++) {
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
