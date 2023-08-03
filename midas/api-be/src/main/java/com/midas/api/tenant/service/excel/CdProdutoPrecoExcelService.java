package com.midas.api.tenant.service.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.midas.api.security.ClienteParam;
import com.midas.api.tenant.entity.CdProdutoPreco;
import com.midas.api.tenant.fiscal.util.FsTipoNomeUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class CdProdutoPrecoExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Código", "Tipo", "Nome", "NCM / Cód. Serv", "CEST", "Descrição", "Referência",
			"EAN", "EAN Trib.", "UN", "UN Trib.", "Marca", "Medida", "Altura", "Comprimento", "Largura",
			"Med. Quadrada", "Med. Cúbica", "Peso", "Peso Amostra", "Peso Bruto", "Peso Líquido", "Origem",
			"Tipo de item", "Status", "Valor de custo R$", "Valor de comissão R$", "Valor de venda R$",
			"Valor de venda ideal R$", "Valor de lucro R$" };

	public static ByteArrayInputStream exportToExcel(List<CdProdutoPreco> lista, ClienteParam prm) throws Exception {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			String SHEET = "Cadastros de produtos";
			Sheet sheet = workbook.createSheet(SHEET);
			// Cabecalho
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
				cell.setCellStyle(ExcelS.stiloHeader(workbook));
			}
			// Alinha alguns a direita
			for (int x = 25; x <= 30; x++) {
				Cell cell = headerRow.getCell(x);
				cell.setCellStyle(ExcelS.stiloHeaderRight(workbook));
			}
			int rowIdx = 1;
			for (CdProdutoPreco p : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(p.getId() + "");
				row.createCell(1).setCellValue(p.getCdproduto().getCodigo() + "");
				row.createCell(2).setCellValue(p.getCdproduto().getTipo());
				row.createCell(3).setCellValue(p.getCdproduto().getNome());
				if (p.getCdproduto().getTipo().equals("PRODUTO")) {
					row.createCell(4).setCellValue(p.getCdproduto().getNcm());
				} else {
					row.createCell(4).setCellValue(p.getCdproduto().getCodserv());
				}
				row.createCell(5).setCellValue(p.getCdproduto().getCest());
				row.createCell(6).setCellValue(p.getCdproduto().getDescricao());
				row.createCell(7).setCellValue(p.getCdproduto().getRef());
				row.createCell(8).setCellValue(p.getCdproduto().getCean());
				row.createCell(9).setCellValue(p.getCdproduto().getCeantrib());
				row.createCell(10).setCellValue(p.getCdproduto().getCdprodutounmed().getSigla());
				row.createCell(11).setCellValue(p.getCdproduto().getCdprodutounmedtrib().getSigla());
				row.createCell(12).setCellValue(p.getCdproduto().getCdprodutomarca().getNome());
				row.createCell(13).setCellValue(p.getCdproduto().getMtipomedida());
				row.createCell(14).setCellValue(MoedaUtil.moedaPadrao4(p.getCdproduto().getMaltura()));
				row.createCell(15).setCellValue(MoedaUtil.moedaPadrao4(p.getCdproduto().getMcomp()));
				row.createCell(16).setCellValue(MoedaUtil.moedaPadrao4(p.getCdproduto().getMlargura()));
				row.createCell(17).setCellValue(MoedaUtil.moedaPadrao4(p.getCdproduto().getMquad()));
				row.createCell(18).setCellValue(MoedaUtil.moedaPadrao4(p.getCdproduto().getMcub()));
				row.createCell(19).setCellValue(p.getCdproduto().getMtipopeso());
				row.createCell(20).setCellValue(MoedaUtil.moedaPadrao4(p.getCdproduto().getMpesoa()));
				row.createCell(21).setCellValue(MoedaUtil.moedaPadrao4(p.getCdproduto().getMpesob()));
				row.createCell(22).setCellValue(MoedaUtil.moedaPadrao4(p.getCdproduto().getMpesol()));
				row.createCell(23).setCellValue(FsTipoNomeUtil.nomeOrigemEs(p.getCdproduto().getOrigem()));
				row.createCell(24).setCellValue(FsTipoNomeUtil.nomeTipoItemEs(p.getCdproduto().getTipoitem()));
				row.createCell(25).setCellValue(p.getCdproduto().getStatus());
				// Verifica se represetante
				if (prm.cliente().getRole().getId() == 8) {
					BigDecimal zero = new BigDecimal(0);
					row.createCell(26).setCellValue(MoedaUtil.moedaPadrao4(zero));
					row.createCell(27).setCellValue(MoedaUtil.moedaPadrao4(zero));
					row.createCell(28).setCellValue(MoedaUtil.moedaPadrao4(p.getVvenda()));
					row.createCell(29).setCellValue(MoedaUtil.moedaPadrao4(zero));
					row.createCell(30).setCellValue(MoedaUtil.moedaPadrao4(zero));
				} else {
					row.createCell(26).setCellValue(MoedaUtil.moedaPadrao4(p.getVcusto()));
					row.createCell(27).setCellValue(MoedaUtil.moedaPadrao4(p.getVcom()));
					row.createCell(28).setCellValue(MoedaUtil.moedaPadrao4(p.getVvenda()));
					row.createCell(29).setCellValue(MoedaUtil.moedaPadrao4(p.getVvendaideal()));
					row.createCell(30).setCellValue(MoedaUtil.moedaPadrao4(p.getVlucro()));
				}
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Alinha alguns a direita
				for (int x = 26; x <= 30; x++) {
					Cell cell = row.getCell(x);
					cell.setCellStyle(ExcelS.stiloCellRightBold(workbook));
				}
				// Auto size
				for (int x = 0; x <= 30; x++) {
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
