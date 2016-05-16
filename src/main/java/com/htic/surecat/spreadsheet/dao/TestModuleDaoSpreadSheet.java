package com.htic.surecat.spreadsheet.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.htic.surecat.api.TestExceptionExpected;
import com.htic.surecat.api.TestModule;
import com.htic.surecat.api.TestSource;
import com.htic.surecat.api.TestSuite;
import com.htic.surecat.core.dao.TestModuleDao;
import com.htic.surecat.core.model.TestSourceException;
import com.htic.surecat.spreadsheet.model.TestSourceSpreadSheet;
import com.htic.surecat.spreadsheet.poi.POIExcelUtils;

public class TestModuleDaoSpreadSheet implements TestModuleDao {

	private TestSuiteDaoSpreadSheet testSuiteDao;


	public TestModuleDaoSpreadSheet () {
		setTestSuiteDao(new TestSuiteDaoSpreadSheet());
	}


	@Override
	public TestModule populate(TestSource testSource) throws Exception {
		TestModule testModule = new TestModule ();

		testModule = populateTestModuleMetaData ((TestSourceSpreadSheet) testSource, testModule);
		testModule = populateTestModuleData ((TestSourceSpreadSheet) testSource, testModule);

		return testModule;
	}


	//TODO Refactor, remove testModule as Parameter, return testExceptions
	private TestModule populateTestModuleMetaData(TestSourceSpreadSheet testSource, TestModule testModule) {
		Map<String, TestExceptionExpected> testExceptions = new HashMap<String, TestExceptionExpected> ();
		TestExceptionExpected testException;

		Sheet sheet = testSource.getWorkbook().getSheetAt(0);
		Row dataRow	= sheet.getRow(testSource.getSpreadSheetTemplate().getTestModuleExceptionHeaderPosition().getRow());

		for (int dataCellIndex = testSource.getSpreadSheetTemplate().getTestModuleExceptionHeaderPosition().getColumn() + 1; dataCellIndex < dataRow.getLastCellNum(); dataCellIndex++) {
			testException = new TestExceptionExpected();

			testException.setExceptionAlias(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+1, testSource.getSpreadSheetTemplate().getTestModuleExceptionHeaderPosition().getColumn() + dataCellIndex));
			testException.setExceptionPackage(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+2, testSource.getSpreadSheetTemplate().getTestModuleExceptionHeaderPosition().getColumn() + dataCellIndex));
			testException.setExceptionClassName(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+3, testSource.getSpreadSheetTemplate().getTestModuleExceptionHeaderPosition().getColumn() + dataCellIndex));
			testException.setExceptionDescription(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+4, testSource.getSpreadSheetTemplate().getTestModuleExceptionHeaderPosition().getColumn() + dataCellIndex));

			if (testExceptions.get(testException.getExceptionAlias()) != null) {
				throw new TestSourceException(TestSourceException.TESTEXCEPTIONS_NOT_UNIQUE_IDENTIFIER);
			} else {
				testExceptions.put(testException.getExceptionAlias(), testException);
			}
		}

		testModule.setTestExpectedExceptions(testExceptions);

		return testModule;
	}

	private TestModule populateTestModuleData(TestSourceSpreadSheet testSource, TestModule testModule) throws Exception {
		Map<String, TestSuite> testSuites	= new HashMap<String, TestSuite> ();
		TestSuite testSuite					= null;

		for (int sheetIndex = testSource.getSpreadSheetTemplate().getTestSuiteFirstSheet(); sheetIndex < testSource.getWorkbook().getNumberOfSheets(); sheetIndex++) {
			testSuite = testSuiteDao.populateTestSuite(testSource.getSpreadSheetTemplate(), testSource.getWorkbook().getSheet(testSource.getWorkbook().getSheetAt(sheetIndex).getSheetName()));

			if (testSuite != null) {
				if (testSuites.get(testSuite.getCode()) != null) {
					throw new TestSourceException(TestSourceException.TESTSUITE_NOT_UNIQUE_IDENTIFIER);
				} else {
					testSuites.put(testSuite.getCode(), testSuite);
				}
			}
		}

		testModule.setTestSuite(testSuites);

		return testModule;
	}


	//Getters && Setters
	public TestSuiteDaoSpreadSheet getTestSuiteDao() {
		return testSuiteDao;
	}
	public void setTestSuiteDao(TestSuiteDaoSpreadSheet testSuiteDao) {
		this.testSuiteDao = testSuiteDao;
	}
}