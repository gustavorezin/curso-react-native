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

import com.midas.api.tenant.entity.CdProduto;
import com.midas.api.tenant.fiscal.util.FsTipoNomeUtil;
import com.midas.api.util.MoedaUtil;

@Service
public class CdProdutoExcelService {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "Código", "Tipo", "Cadastrado em", "Nome", "NCM / Cód. Serv", "CEST", "Descrição",
			"Referência", "EAN", "EAN Trib.", "UN", "UN Trib.", "Categoria", "Grupo", "Subgrupo", "Marca", "Medida",
			"Altura", "Comprimento", "Largura", "Med. Quadrada", "Med. Cúbica", "Peso", "Peso Amostra", "Peso Bruto", "Peso Líquido",
			"Origem", "Tipo de item", "Status" };

	public static ByteArrayInputStream exportToExcel(List<CdProduto> lista) throws Exception {
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
			int rowIdx = 1;
			for (CdProduto p : lista) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(p.getId() + "");
				row.createCell(1).setCellValue(p.getCodigo() + "");
				row.createCell(2).setCellValue(p.getTipo());
				row.createCell(3).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getDatacad()));
				row.createCell(4).setCellValue(p.getNome());
				if (p.getTipo().equals("PRODUTO")) {
					row.createCell(5).setCellValue(p.getNcm());
				} else {
					row.createCell(5).setCellValue(p.getCodserv());
				}
				row.createCell(6).setCellValue(p.getCest());
				row.createCell(7).setCellValue(p.getDescricao());
				row.createCell(8).setCellValue(p.getRef());
				row.createCell(9).setCellValue(p.getCean());
				row.createCell(10).setCellValue(p.getCeantrib());
				row.createCell(11).setCellValue(p.getCdprodutounmed().getSigla());
				row.createCell(12).setCellValue(p.getCdprodutounmedtrib().getSigla());
				row.createCell(13)
						.setCellValue(p.getCdprodutosubgrupo().getCdprodutogrupo().getCdprodutocat().getNome());
				row.createCell(14).setCellValue(p.getCdprodutosubgrupo().getCdprodutogrupo().getNome());
				row.createCell(15).setCellValue(p.getCdprodutosubgrupo().getNome());
				row.createCell(16).setCellValue(p.getCdprodutomarca().getNome());
				row.createCell(17).setCellValue(p.getMtipomedida());
				row.createCell(18).setCellValue(MoedaUtil.moedaPadrao4(p.getMaltura()));
				row.createCell(19).setCellValue(MoedaUtil.moedaPadrao4(p.getMcomp()));
				row.createCell(20).setCellValue(MoedaUtil.moedaPadrao4(p.getMlargura()));
				row.createCell(21).setCellValue(MoedaUtil.moedaPadrao4(p.getMquad()));
				row.createCell(22).setCellValue(MoedaUtil.moedaPadrao4(p.getMcub()));
				row.createCell(23).setCellValue(p.getMtipopeso());
				row.createCell(24).setCellValue(MoedaUtil.moedaPadrao4(p.getMpesoa()));
				row.createCell(25).setCellValue(MoedaUtil.moedaPadrao4(p.getMpesob()));
				row.createCell(26).setCellValue(MoedaUtil.moedaPadrao4(p.getMpesol()));
				row.createCell(27).setCellValue(FsTipoNomeUtil.nomeOrigemEs(p.getOrigem()));
				row.createCell(28).setCellValue(FsTipoNomeUtil.nomeTipoItemEs(p.getTipoitem()));
				row.createCell(29).setCellValue(p.getStatus());
				row.setRowStyle(ExcelS.stiloRow(workbook));
				// Auto size
				for (int x = 0; x <= 29; x++) {
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
