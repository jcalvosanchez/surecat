package com.htic.suricataarq.poi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class POIExcelUtils {

	public static final int FILE_FORMAT_EXCEL_1997	= 1;
	public static final int FILE_FORMAT_EXCEL_2003	= 2;
	public static final int FILE_FORMAT_OPENOFFICE_SPREADSHEET	= 3;


	public static int getDefaultFileFormat(String filePath) {
		int result = 1;

		if (filePath.substring(filePath.lastIndexOf(".")).equalsIgnoreCase(".XLS") ) {
			result = POIExcelUtils.FILE_FORMAT_EXCEL_1997;
		} else if (filePath.substring(filePath.lastIndexOf(".")).equalsIgnoreCase(".XLSX") ) {
			result = POIExcelUtils.FILE_FORMAT_EXCEL_2003;
		} else if (filePath.substring(filePath.lastIndexOf(".")).equalsIgnoreCase(".ODS")) {
			result = FILE_FORMAT_OPENOFFICE_SPREADSHEET;
		}

		return result;
	}

	public static Workbook getWorkbook (File file, int fileFormat) throws IOException, InvalidFormatException {
		Workbook workbook = null;

		if (fileFormat == 0) {
			fileFormat = getDefaultFileFormat(file.getPath());
		}

		switch (fileFormat) {
		case FILE_FORMAT_EXCEL_1997:
			workbook = getHSSFWorkbook(file);
			break;
		case FILE_FORMAT_EXCEL_2003:
			workbook = getWorkbook(file);
			break;
		default:
			throw new RuntimeException("Unkown File Format");
		}

		return workbook;
	}

	/**
	 * This method only can be applied to old format XLS files (1997 format), 
	 * but it's much quicker than the new format process.
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static HSSFWorkbook getHSSFWorkbook (File file) throws IOException {
		InputStream stream	= null;
		HSSFWorkbook wb		= null;

		try {
			stream	= new FileInputStream(file);
			wb		= new HSSFWorkbook(stream);
		} catch (IOException e) {
			e.printStackTrace();
			throw (e);
		} finally {
			if (stream!=null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return wb;
	}

	/**
	 * This method can be applied for both, new and old format XLS files (1997 and 2003 format), 
	 * but it's much slower than the old format process.
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static Workbook getWorkbook (File file) throws IOException, InvalidFormatException {
		InputStream stream	= null;
		Workbook wb			= null;

		try {
			stream	= new FileInputStream(file);
			wb		= WorkbookFactory.create(stream);
		} catch (IOException e) {
			e.printStackTrace();
			throw (e);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			throw (e);
		} finally {
			if (stream!=null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return wb;
	}


	public static int getIntegerIntValue(Workbook workbook, int sheetIndex, int rowIndex, int cellIndex) {
		return getIntegerValue(workbook, sheetIndex, rowIndex, cellIndex).intValue();
	}

	public static Integer getIntegerValue(Workbook workbook, int sheetIndex, int rowIndex, int cellIndex) {
		String stringValue	= getStringValue(workbook.getSheetAt(sheetIndex), rowIndex, cellIndex);
		return stringValue == null ? null : new Integer (new Double(stringValue).intValue());
	}

	public static String getStringValue(Workbook workbook, int sheetIndex, int rowIndex, int cellIndex) {
		return getStringValue(workbook.getSheetAt(sheetIndex), rowIndex, cellIndex);
	}

	public static String getStringValue(Sheet sheet, int rowIndex, int cellIndex) {
		String result = null;

		Object value = getValue(sheet, rowIndex, cellIndex);
		if (value != null) {
			result = value.toString();
		}
		return result;
	}

	public static Object getValue(Sheet sheet, int rowIndex, int cellIndex) {
		Row row		= sheet.getRow(rowIndex);
		Cell cell	= row.getCell(cellIndex);

		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				return cell.getRichStringCellValue().getString();
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					return cell.getDateCellValue();
				} else {
					return cell.getNumericCellValue();
				}
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue();
			case Cell.CELL_TYPE_FORMULA:
				return cell.getCellFormula();
			default:
				return null;
		}
	}
}