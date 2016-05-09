package com.htic.surecat.spreadsheet.model;

import java.io.IOException;

import com.htic.hticarq.core.util.StringUtils;
import com.htic.hticarq.poi.model.ExcelCoordinates;
import com.htic.surecat.core.model.TestSourceException;
import com.htic.surecat.core.model.TestSourceProperties;
import com.htic.surecat.core.util.SurecatConstants;

public class TestSourcePropertiesSpreadSheet extends TestSourceProperties {

	//Mandatory properties
	private String spreadSheetFilePath;

	//Required properties
	private ExcelCoordinates testSuiteCodePosition;
	private ExcelCoordinates testCaseHeaderPosition;
	private ExcelCoordinates testCaseDataPosition;

	//Optional properties
	private boolean metadataSheetIndex;
	private ExcelCoordinates testCaseCodePosition;
	private ExcelCoordinates testSuiteDescriptionPosition;
	private ExcelCoordinates testSuiteObservationsPosition;
	private ExcelCoordinates testModuleExceptionHeaderPosition;


	public TestSourcePropertiesSpreadSheet(String pathParamFile) throws IOException {
		super(pathParamFile);
	
		populateMandatoryParameters();
		populateRequiredParameters();
		populateOptionalParameters();
	}


	@Override
	protected void populateDefaultValues() {
		setMetadataSheetIndex(false);
		setTestCaseCodePosition(new ExcelCoordinates(-1, -1, 0));
	}

	private void populateMandatoryParameters() {
		String propKeyRootname, propKey, propValue;

		propKeyRootname	= StringUtils.removeChar(SurecatConstants.APP_NAME.toLowerCase(), ' ').concat(".").concat(StringUtils.removeChar(SurecatConstants.SPREAD_SHEET_MODULE_NAME.toLowerCase(), ' '));

		propKey		= propKeyRootname.concat(".").concat(SurecatConstants.PROPNAME_TESTSOURCE).concat(".").concat(SurecatConstants.PROPNAME_FILEPATH);
		propValue	= getProperty(propKey);

		if (propValue == null) {
			throw new TestSourceException(TestSourceException.MISSING_REQUIRED_PARAMETER);
		} else {
			this.setSpreadSheetFilePath(propValue);
		}
	}

	private void populateRequiredParameters() {
		String propKeyRootname	= StringUtils.removeChar(SurecatConstants.APP_NAME.toLowerCase(), ' ').concat(".").concat(StringUtils.removeChar(SurecatConstants.SPREAD_SHEET_MODULE_NAME.toLowerCase(), ' '));

		populateTestSuiteCodePosition(propKeyRootname);
		populateTestCaseHeaderPosition(propKeyRootname);
		populateTestCaseDataPosition(propKeyRootname);
	}

	private void populateOptionalParameters() {
		String propKeyRootname	= StringUtils.removeChar(SurecatConstants.APP_NAME.toLowerCase(), ' ')
				.concat(".").concat(StringUtils.removeChar(SurecatConstants.SPREAD_SHEET_MODULE_NAME.toLowerCase(), ' '));

	}

	private void populateTestSuiteCodePosition(String propKeyRootname) {
		String propKey	= propKeyRootname.concat(".").concat(SurecatConstants.PROPNAME_TESTSUITE).concat(".").concat(SurecatConstants.PROPNAME_CODE);

		ExcelCoordinates excelCoordinates = this.getTestSuiteCodePosition() == null ?
				new ExcelCoordinates ():
				this.getTestSuiteCodePosition();

		this.setTestSuiteCodePosition(populateExcelCoordinatesParameter(excelCoordinates, propKey, false, true, true));
	}

	private void populateTestCaseHeaderPosition(String propKeyRootname) {
		String propKey	= propKeyRootname.concat(".").concat(SurecatConstants.PROPNAME_TESTCASE).concat(".").concat(SurecatConstants.PROPNAME_HEADER);

		ExcelCoordinates excelCoordinates = this.getTestCaseHeaderPosition() == null ?
				new ExcelCoordinates ():
				this.getTestCaseHeaderPosition();

		this.setTestCaseHeaderPosition(populateExcelCoordinatesParameter(excelCoordinates, propKey, false, true, false));
	}

	private void populateTestCaseDataPosition(String propKeyRootname) {
		String propKey	= propKeyRootname.concat(".").concat(SurecatConstants.PROPNAME_TESTCASE).concat(".").concat(SurecatConstants.PROPNAME_DATA);

		ExcelCoordinates excelCoordinates = this.getTestCaseDataPosition() == null ?
				new ExcelCoordinates ():
				this.getTestCaseDataPosition();

		this.setTestCaseDataPosition(populateExcelCoordinatesParameter(excelCoordinates, propKey, false, true, false));
	}

	private ExcelCoordinates populateExcelCoordinatesParameter(ExcelCoordinates excelCoordinates, String propKey, boolean sheetRequired, boolean rowRequired, boolean columnRequired) {
		String propKeySheetIndex, propKeyRow, propKeyColumn, propValueSheetIndex, propValueRow, propValueColumn;

		propKeySheetIndex	= propKey.concat(".").concat(SurecatConstants.PROPNAME_POSITION).concat(".").concat(SurecatConstants.PROPNAME_SHEETINDEX);
		propValueSheetIndex	= getProperty(propKeySheetIndex);

		propKeyRow		= propKey.concat(".").concat(SurecatConstants.PROPNAME_POSITION).concat(".").concat(SurecatConstants.PROPNAME_ROW);
		propValueRow	= getProperty(propKeyRow);

		propKeyColumn	= propKey.concat(".").concat(SurecatConstants.PROPNAME_POSITION).concat(".").concat(SurecatConstants.PROPNAME_COLUMN);
		propValueColumn	= getProperty(propKeyColumn);

		try {
			try {
				excelCoordinates.setSheetIndex(Integer.parseInt(propValueSheetIndex.trim()));
			} catch (NullPointerException e) {
				if (sheetRequired) throw new TestSourceException(TestSourceException.MISSING_REQUIRED_PARAMETER);
			}

			try {
				excelCoordinates.setRow(Integer.parseInt(propValueRow.trim()));
			} catch (NullPointerException e) {
				if (rowRequired) throw new TestSourceException(TestSourceException.MISSING_REQUIRED_PARAMETER);
			}

			try {
				excelCoordinates.setColumn(Integer.parseInt(propValueColumn.trim()));
			} catch (NullPointerException e) {
				if (columnRequired) throw new TestSourceException(TestSourceException.MISSING_REQUIRED_PARAMETER);
			}
		} catch (NumberFormatException e) {
			throw new TestSourceException(TestSourceException.ERROR_VALIDATION_PARAMETER);
		}

		return excelCoordinates;
	}


	//Private Getters && Setters
	private void setSpreadSheetFilePath(String pathFile) {
		this.spreadSheetFilePath = pathFile;
	}
	private void setTestSuiteCodePosition(ExcelCoordinates testSuiteCodePosition) {
		this.testSuiteCodePosition = testSuiteCodePosition;
	}
	private void setTestCaseHeaderPosition(ExcelCoordinates testCaseHeaderPosition) {
		this.testCaseHeaderPosition = testCaseHeaderPosition;
	}
	private void setTestCaseDataPosition(ExcelCoordinates testCaseDataPosition) {
		this.testCaseDataPosition = testCaseDataPosition;
	}
	private void setMetadataSheetIndex(boolean metadataSheetIndex) {
		this.metadataSheetIndex = metadataSheetIndex;
	}
	private void setTestSuiteDescriptionPosition(ExcelCoordinates testSuiteDescriptionPosition) {
		this.testSuiteDescriptionPosition = testSuiteDescriptionPosition;
	}
	private void setTestSuiteObservationsPosition(ExcelCoordinates testSuiteObservationsPosition) {
		this.testSuiteObservationsPosition = testSuiteObservationsPosition;
	}
	private void setTestModuleExceptionHeaderPosition(ExcelCoordinates testModuleExceptionHeaderPosition) {
		this.testModuleExceptionHeaderPosition = testModuleExceptionHeaderPosition;
	}
	private void setTestCaseCodePosition(ExcelCoordinates testCaseCodePosition) {
		this.testCaseCodePosition = testCaseCodePosition;
	}

	//Getters && Setters
	public String getSpreadSheetFilePath() {
		return spreadSheetFilePath;
	}
	public ExcelCoordinates getTestSuiteCodePosition() {
		return testSuiteCodePosition;
	}
	public ExcelCoordinates getTestCaseHeaderPosition() {
		return testCaseHeaderPosition;
	}
	public ExcelCoordinates getTestCaseDataPosition() {
		return testCaseDataPosition;
	}
	public boolean isMetadataSheetIndex() {
		return metadataSheetIndex;
	}
	public ExcelCoordinates getTestSuiteDescriptionPosition() {
		return testSuiteDescriptionPosition;
	}
	public ExcelCoordinates getTestSuiteObservationsPosition() {
		return testSuiteObservationsPosition;
	}
	public ExcelCoordinates getTestModuleExceptionHeaderPosition() {
		return testModuleExceptionHeaderPosition;
	}
	public ExcelCoordinates getTestCaseCodePosition() {
		return testCaseCodePosition;
	}
}