package com.htic.surecat.spreadsheet.model;

import java.io.IOException;
import java.util.Properties;

import com.htic.hticarq.poi.model.ExcelCoordinates;
import com.htic.surecat.core.model.TestModulePopulationAlgorithm;
import com.htic.surecat.core.model.TestSourceException;
import com.htic.surecat.core.util.SurecatConstants;
import com.htic.surecat.core.util.SurecatPropertiesUtil;

public class TemplateSpreadSheet {

	//File path
	private String filePath;

	//Test Module
	private ExcelCoordinates testModuleExceptionHeaderPosition;
	private TestModulePopulationAlgorithm testModulePopulationAlgorithm = TestModulePopulationAlgorithm.ODT;

	//Test Suite
	private ExcelCoordinates testSuiteCodePosition;
	private ExcelCoordinates testSuiteDescriptionPosition;
	private ExcelCoordinates testSuiteObservationsPosition;

	//Test Case
	private ExcelCoordinates testCaseCodePosition;
	private ExcelCoordinates testCaseHeaderPosition;
	private ExcelCoordinates testCaseDataPosition;


	public TemplateSpreadSheet(String spreadSheetPropertiesFilePath) throws IOException {
		java.util.Properties properties = SurecatPropertiesUtil.getPropertiesFromFile(spreadSheetPropertiesFilePath);

		String propKeyRootname	= SurecatConstants.APP_NAME.concat(".").concat(SurecatPropertiesUtil.SPREAD_SHEET_MODULE_NAME);

		filePath	= properties.getProperty(propKeyRootname.concat(".").concat(SurecatPropertiesUtil.PROPNAME_TESTSOURCE).concat(".").concat(SurecatPropertiesUtil.PROPNAME_FILEPATH));

		testModuleExceptionHeaderPosition = populateExcelCoordinatesParameter(properties, propKeyRootname.concat(".").concat(SurecatPropertiesUtil.PROPNAME_TESTMODULE).concat(".").concat(SurecatPropertiesUtil.PROPNAME_EXCEPTIONS), false, true, true);
//		testModulePopulationAlgorithm = TestModulePopulationAlgorithm.fromString(POIExcelUtils.getStringValue(workbook, SHEET_METAINDEX, POPULATIONALGORITHM_READMODE_ROW, POPULATIONALGORITHM_READMODE_CELL));

		testSuiteCodePosition = populateExcelCoordinatesParameter(properties, propKeyRootname.concat(".").concat(SurecatPropertiesUtil.PROPNAME_TESTSUITE).concat(".").concat(SurecatPropertiesUtil.PROPNAME_CODE), false, true, true);
		testSuiteDescriptionPosition = populateExcelCoordinatesParameter(properties, propKeyRootname.concat(".").concat(SurecatPropertiesUtil.PROPNAME_TESTSUITE).concat(".").concat(SurecatPropertiesUtil.PROPNAME_DESCRIPTION), false, true, true);
		testSuiteObservationsPosition = populateExcelCoordinatesParameter(properties, propKeyRootname.concat(".").concat(SurecatPropertiesUtil.PROPNAME_TESTSUITE).concat(".").concat(SurecatPropertiesUtil.PROPNAME_OBSERVATIONS), false, true, true);

		testCaseCodePosition = populateExcelCoordinatesParameter(properties, propKeyRootname.concat(".").concat(SurecatPropertiesUtil.PROPNAME_TESTCASE).concat(".").concat(SurecatPropertiesUtil.PROPNAME_CODE), false, false, true);
		testCaseHeaderPosition = populateExcelCoordinatesParameter(properties, propKeyRootname.concat(".").concat(SurecatPropertiesUtil.PROPNAME_TESTCASE).concat(".").concat(SurecatPropertiesUtil.PROPNAME_HEADER), false, true, false);
		testCaseDataPosition = populateExcelCoordinatesParameter(properties, propKeyRootname.concat(".").concat(SurecatPropertiesUtil.PROPNAME_TESTCASE).concat(".").concat(SurecatPropertiesUtil.PROPNAME_DATA), false, true, true);
	}


	private ExcelCoordinates populateExcelCoordinatesParameter(Properties properties, String propKey, boolean sheetRequired, boolean rowRequired, boolean columnRequired) {
		ExcelCoordinates excelCoordinates = new ExcelCoordinates();

		try {
			try {
				excelCoordinates.setSheetIndex(Integer.parseInt(properties.getProperty(propKey.concat(".").concat(SurecatPropertiesUtil.PROPNAME_SHEETINDEX)).trim()));
			} catch (NullPointerException e) {
				if (sheetRequired) throw new TestSourceException(TestSourceException.MISSING_REQUIRED_PARAMETER);
			}

			try {
				excelCoordinates.setRow(Integer.parseInt(properties.getProperty(propKey.concat(".").concat(SurecatPropertiesUtil.PROPNAME_ROW)).trim()));
			} catch (NullPointerException e) {
				if (rowRequired) throw new TestSourceException(TestSourceException.MISSING_REQUIRED_PARAMETER);
			}

			try {
				excelCoordinates.setColumn(Integer.parseInt(properties.getProperty(propKey.concat(".").concat(SurecatPropertiesUtil.PROPNAME_COLUMN)).trim()));
			} catch (NullPointerException e) {
				if (columnRequired) throw new TestSourceException(TestSourceException.MISSING_REQUIRED_PARAMETER);
			}
		} catch (NumberFormatException e) {
			throw new TestSourceException(TestSourceException.ERROR_VALIDATION_PARAMETER);
		}

		return excelCoordinates;
	}

	@Deprecated
	public int getTestSuiteFirstSheet() {
		return 1;
	}

	//Getters && Setters
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public ExcelCoordinates getTestModuleExceptionHeaderPosition() {
		return testModuleExceptionHeaderPosition;
	}
	public void setTestModuleExceptionHeaderPosition(ExcelCoordinates testModuleExceptionHeaderPosition) {
		this.testModuleExceptionHeaderPosition = testModuleExceptionHeaderPosition;
	}
	public ExcelCoordinates getTestSuiteCodePosition() {
		return testSuiteCodePosition;
	}
	public void setTestSuiteCodePosition(ExcelCoordinates testSuiteCodePosition) {
		this.testSuiteCodePosition = testSuiteCodePosition;
	}
	public ExcelCoordinates getTestSuiteDescriptionPosition() {
		return testSuiteDescriptionPosition;
	}
	public void setTestSuiteDescriptionPosition(ExcelCoordinates testSuiteDescriptionPosition) {
		this.testSuiteDescriptionPosition = testSuiteDescriptionPosition;
	}
	public ExcelCoordinates getTestSuiteObservationsPosition() {
		return testSuiteObservationsPosition;
	}
	public void setTestSuiteObservationsPosition(ExcelCoordinates testSuiteObservationsPosition) {
		this.testSuiteObservationsPosition = testSuiteObservationsPosition;
	}
	public ExcelCoordinates getTestCaseCodePosition() {
		return testCaseCodePosition;
	}
	public void setTestCaseCodePosition(ExcelCoordinates testCaseCodePosition) {
		this.testCaseCodePosition = testCaseCodePosition;
	}
	public ExcelCoordinates getTestCaseHeaderPosition() {
		return testCaseHeaderPosition;
	}
	public void setTestCaseHeaderPosition(ExcelCoordinates testCaseHeaderPosition) {
		this.testCaseHeaderPosition = testCaseHeaderPosition;
	}
	public ExcelCoordinates getTestCaseDataPosition() {
		return testCaseDataPosition;
	}
	public void setTestCaseDataPosition(ExcelCoordinates testCaseDataPosition) {
		this.testCaseDataPosition = testCaseDataPosition;
	}
}