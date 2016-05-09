package com.htic.surecat.spreadsheet.dao.impl;

import org.apache.poi.ss.usermodel.Sheet;

import com.htic.surecat.api.TestModule;
import com.htic.surecat.api.TestSource;
import com.htic.surecat.api.TestSuite;
import com.htic.surecat.core.dao.TestSuiteDAO;
import com.htic.surecat.spreadsheet.model.TemplateSpreadSheet;
import com.htic.surecat.spreadsheet.model.TestSourceSpreadSheet;
import com.htic.surecat.spreadsheet.poi.POIExcelUtils;

/**
 * Design Patter: Template Method, define the skeleton of an algorithm in an operation, deferring some steps to client subclasses.
 * Algorithm: Read Mode.
 * Template Method: populateTestSuite.
 * Generalization Class: TestSuiteSpreadSheetBaseDAO.
 * Realization Classes: TestSuiteSpreadSheetPDTDAO, TestSuiteSpreadSheetODTDAO.
 */
public abstract class TestSuiteSpreadSheetBaseDAO implements TestSuiteDAO {

	@Override
	public TestSuite populateTestSuite(TestSource testSource, TestModule testModule, String testSuiteCode) throws Exception {
		TestSuite testSuite = new TestSuite();

		testSuite = populateTestSuiteMetaData (testSource, testSuite, testSuiteCode);
		testSuite = populateTestSuiteData(testSource, testModule, testSuite, testSuiteCode);

		return testSuite;
	}


	protected TestSuite populateTestSuiteMetaData(TestSource testSource, TestSuite testSuite, String testSuiteCode) {
		Sheet sheet								= ((TestSourceSpreadSheet)testSource).getWorkbook().getSheet(testSuiteCode);
		TemplateSpreadSheet spreadSheetTemplate = ((TestSourceSpreadSheet)testSource).getSpreadSheetTemplate();

		testSuite.setDescription(POIExcelUtils.getStringValue(sheet, spreadSheetTemplate.getTestSuiteDescriptionRow(), spreadSheetTemplate.getTestSuiteDescriptionCell()));
		testSuite.setCode(POIExcelUtils.getStringValue(sheet, spreadSheetTemplate.getTestSuiteCodeRow(), spreadSheetTemplate.getTestSuiteCodeCell()));
		testSuite.setObservations(POIExcelUtils.getStringValue(sheet, spreadSheetTemplate.getTestSuiteObservationsRow(), spreadSheetTemplate.getTestSuiteObservationsCell()));

		return testSuite;
	}

	protected abstract TestSuite populateTestSuiteData(TestSource testSource, TestModule testModule, TestSuite testSuite, String testSuiteCode) throws Exception;

}