package com.midas.api.tenant.service.excel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class ExcelS {
	/*
	 * Estilos
	 */
	public static CellStyle stiloHeaderP1(Workbook workbook) {
		// Estilo
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setBold(true);
		font.setFontHeight(9);
		font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		style.setFont(font);
		return style;
	}

	public static CellStyle stiloHeaderRight(Workbook workbook) {
		// Estilo
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setBold(true);
		font.setFontHeight(9);
		font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.RIGHT);
		return style;
	}

	public static CellStyle stiloHeader(Workbook workbook) {
		// Estilo
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setBold(true);
		font.setFontHeight(9);
		font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		style.setFont(font);
		return style;
	}

	public static CellStyle stiloRow(Workbook workbook) {
		// Estilo
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontHeight(8);
		style.setFont(font);
		return style;
	}

	public static CellStyle stiloRowRight(Workbook workbook) {
		// Estilo
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontHeight(8);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.RIGHT);
		return style;
	}

	public static CellStyle stiloCellRightBold(Workbook workbook) {
		// Estilo
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontHeight(8);
		font.setBold(true);
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFont(font);
		return style;
	}

	public static CellStyle stiloRowSec(Workbook workbook) {
		// Estilo
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontHeight(7);
		font.setBold(true);
		style.setFont(font);
		return style;
	}

	public static CellStyle stiloRowSecRight(Workbook workbook) {
		// Estilo
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontHeight(7);
		font.setBold(true);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.RIGHT);
		return style;
	}

	public static CellStyle stiloCellRightBoldColor(Workbook workbook, String cor, int fontsize) {
		// Estilo
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontHeight(fontsize);
		font.setBold(true);
		// Cor fonte
		if (cor.equals("B")) {
			font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		}
		if (cor.equals("R")) {
			font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
		}
		if (cor.equals("O")) {
			font.setColor(HSSFColor.HSSFColorPredefined.LIGHT_ORANGE.getIndex());
		}
		if (cor.equals("G")) {
			font.setColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
		}
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFont(font);
		return style;
	}

	public static CellStyle stiloHeaderSec(Workbook workbook) {
		// Estilo
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setBold(true);
		font.setFontHeight(9);
		font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		style.setFont(font);
		return style;
	}

	public static CellStyle stiloCellAmarelo(Workbook workbook) {
		// Estilo
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontHeight(8);
		style.setFont(font);
		return style;
	}
}
