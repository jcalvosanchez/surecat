package com.htic.surecat.spreadsheet.dao.impl;

import org.apache.poi.ss.usermodel.Sheet;

import com.htic.surecat.api.TestModule;
import com.htic.surecat.api.TestSource;
import com.htic.surecat.api.TestSuite;
import com.htic.surecat.spreadsheet.model.TemplateSpreadSheet;
import com.htic.surecat.spreadsheet.model.TestSourceSpreadSheet;

public class TestSuiteSpreadSheetPDTDAO extends TestSuiteSpreadSheetBaseDAO {

	private TestCaseSpreadSheetPDTDAO testCaseDAO;


	public TestSuiteSpreadSheetPDTDAO () {
		this.setTestCaseDAO(new TestCaseSpreadSheetPDTDAO());
	}


	protected TestSuite populateTestSuiteMetaData(TestSource testSource, TestModule testModule, TestSuite testSuite, String testSuiteCode) {
		return populateTestSuiteMetaData(testSource, testSuite, testSuiteCode);
	}


	protected TestSuite populateTestSuiteData(TestSource testSource, TestModule testModule, TestSuite testSuite, String testSuiteCode) {
		Sheet sheet								= ((TestSourceSpreadSheet)testSource).getWorkbook().getSheet(testSuiteCode);
		TemplateSpreadSheet spreadSheetTemplate = ((TestSourceSpreadSheet)testSource).getSpreadSheetTemplate();

		// Reads test case data
		for (int dataRowIndex = spreadSheetTemplate.getTestCaseDataRow() + 2; dataRowIndex <= sheet.getLastRowNum(); dataRowIndex++) {
			testSuite.addTestCase(testCaseDAO.populateTestCase(testSource, testSuite, dataRowIndex));
		}

		return testSuite;
	}


	//Getters && Setters
	public TestCaseSpreadSheetPDTDAO getTestCaseDAO() {
		return testCaseDAO;
	}
	public void setTestCaseDAO(TestCaseSpreadSheetPDTDAO testCaseDAO) {
		this.testCaseDAO = testCaseDAO;
	}
}