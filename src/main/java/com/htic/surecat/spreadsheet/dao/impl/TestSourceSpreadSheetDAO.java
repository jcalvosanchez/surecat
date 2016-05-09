package com.htic.surecat.spreadsheet.dao.impl;

import java.io.File;

import org.apache.poi.ss.usermodel.Workbook;

import com.htic.surecat.core.model.TestModulePopulationAlgorithm;
import com.htic.surecat.core.model.TestSourceException;
import com.htic.surecat.spreadsheet.model.TemplateSpreadSheet;
import com.htic.surecat.spreadsheet.model.TestSourcePropertiesSpreadSheet;
import com.htic.surecat.spreadsheet.poi.POIExcelUtils;

public class TestSourceSpreadSheetDAO {

	private static final int SHEET_METAINDEX		= 0;

	private static final int POPULATIONALGORITHM_READMODE_ROW	= 5;
	private static final int POPULATIONALGORITHM_READMODE_CELL	= 0;

	private static final int TESTEXCEPTION_HEADERROW_ROW	= 10;
	private static final int TESTEXCEPTION_HEADERROW_CELL	= 1;
	private static final int TESTEXCEPTION_HEADERCELL_ROW	= 10;
	private static final int TESTEXCEPTION_HEADERCELL_CELL	= 2;

	private static final int TESTSUITE_FIRSTSHEET_ROW		= 13;
	private static final int TESTSUITE_FIRSTSHEET_CELL		= 0;
	private static final int TESTSUITE_CODEROW_ROW			= 13;
	private static final int TESTSUITE_CODEROW_CELL			= 1;
	private static final int TESTSUITE_CODECELL_ROW			= 13;
	private static final int TESTSUITE_CODECELL_CELL		= 2;
	private static final int TESTSUITE_DESCRIPTIONROW_ROW	= 13;
	private static final int TESTSUITE_DESCRIPTIONROW_CELL	= 3;
	private static final int TESTSUITE_DESCRIPTIONCELL_ROW	= 13;
	private static final int TESTSUITE_DESCRIPTIONCELL_CELL	= 4;
	private static final int TESTSUITE_OBSERVATIONSROW_ROW	= 13;
	private static final int TESTSUITE_OBSERVATIONSROW_CELL	= 5;
	private static final int TESTSUITE_OBSERVATIONSCELL_ROW	= 13;
	private static final int TESTSUITE_OBSERVATIONSCELL_CELL= 6;

	private static final int TESTCASE_HEADERROW_ROW		= 16;
	private static final int TESTCASE_HEADERROW_CELL	= 0;
	private static final int TESTCASE_DATAROW_ROW		= 16;
	private static final int TESTCASE_DATAROW_CELL		= 1;
	private static final int TESTCASE_IDCELL_ROW		= 16;
	private static final int TESTCASE_IDCELL_CELL		= 2;


//	@Override
//	public TestSource getTestSource(TestSourceParameters tsParameters) throws Exception {
//		Workbook workbook	= populateWorkbook((TestSourceSpreadSheetParameters)tsParameters);
//
//		TestSourceSpreadSheet tsssSpreadSheet	= new TestSourceSpreadSheet();
//
//		tsssSpreadSheet.setWorkbook(workbook);
//		tsssSpreadSheet.setSpreadSheetTemplate(populateSpreadSheetTemplate (workbook));
//		tsssSpreadSheet.setTestModulePopulationAlgorithm(populateTestModulePopulationAlgorithm (workbook));
//
//		return tsssSpreadSheet;
//	}

	public Workbook populateWorkbook(TestSourcePropertiesSpreadSheet tspss) throws Exception {
		//HSSFWorkbook wb	= POIExcelUtils.getHSSFWorkbook(new File (tmsParameters.getPathFile()));
		//Workbook wb	= POIExcelUtils.getWorkbook(new File (tmsParameters.getPathFile()));

		return POIExcelUtils.getWorkbook(new File (tspss.getSpreadSheetFilePath()), 0);
	}

	public TemplateSpreadSheet populateSpreadSheetTemplate(Workbook workbook) {
		TemplateSpreadSheet ssTemplate = new TemplateSpreadSheet();

		//Optinal parameters
		try {
			ssTemplate.setTestModuleTestExpectedExceptionsHeaderRow(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTEXCEPTION_HEADERROW_ROW, TESTEXCEPTION_HEADERROW_CELL));
			ssTemplate.setTestModuleTestExpectedExceptionsHeaderCell(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTEXCEPTION_HEADERCELL_ROW, TESTEXCEPTION_HEADERCELL_CELL));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Required Parameters
		try {
			ssTemplate.setTestSuiteFirstSheet(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTSUITE_FIRSTSHEET_ROW, TESTSUITE_FIRSTSHEET_CELL));
			ssTemplate.setTestSuiteCodeRow(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTSUITE_CODEROW_ROW, TESTSUITE_CODEROW_CELL));
			ssTemplate.setTestSuiteCodeCell(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTSUITE_CODECELL_ROW, TESTSUITE_CODECELL_CELL));
			ssTemplate.setTestSuiteDescriptionRow(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTSUITE_DESCRIPTIONROW_ROW, TESTSUITE_DESCRIPTIONROW_CELL));
			ssTemplate.setTestSuiteDescriptionCell(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTSUITE_DESCRIPTIONCELL_ROW, TESTSUITE_DESCRIPTIONCELL_CELL));
			ssTemplate.setTestSuiteObservationsRow(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTSUITE_OBSERVATIONSROW_ROW, TESTSUITE_OBSERVATIONSROW_CELL));
			ssTemplate.setTestSuiteObservationsCell(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTSUITE_OBSERVATIONSCELL_ROW, TESTSUITE_OBSERVATIONSCELL_CELL));

			ssTemplate.setTestCaseHeaderRow(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTCASE_HEADERROW_ROW, TESTCASE_HEADERROW_CELL));
			ssTemplate.setTestCaseDataRow(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTCASE_DATAROW_ROW, TESTCASE_DATAROW_CELL));
			ssTemplate.setTestCaseIdCell(POIExcelUtils.getIntegerIntValue(workbook, SHEET_METAINDEX, TESTCASE_IDCELL_ROW, TESTCASE_IDCELL_CELL));
		} catch (Exception e1) {
			throw new TestSourceException(TestSourceException.MISSING_REQUIRED_PARAMETER);
		}

		return ssTemplate;
	}

	public TestModulePopulationAlgorithm populateTestModulePopulationAlgorithm(Workbook workbook) {
		TestModulePopulationAlgorithm tmpa;

		try {
			tmpa = TestModulePopulationAlgorithm.fromString(POIExcelUtils.getStringValue(workbook, SHEET_METAINDEX, POPULATIONALGORITHM_READMODE_ROW, POPULATIONALGORITHM_READMODE_CELL));
		} catch (Exception e) {
			throw new TestSourceException(TestSourceException.MISSING_REQUIRED_PARAMETER);
		}

		return tmpa;
	}
}