package com.htic.surecat.spreadsheet.dao.impl;

import com.htic.hticarq.core.model.exception.NotImplementedException;
import com.htic.surecat.api.TestModule;
import com.htic.surecat.api.TestSource;
import com.htic.surecat.api.TestSuite;
import com.htic.surecat.core.dao.TestSuiteDAO;
import com.htic.surecat.core.model.TestSourceException;
import com.htic.surecat.spreadsheet.model.TestSourceSpreadSheet;

public class TestSuiteSpreadSheetDAO implements TestSuiteDAO {

	private TestSuiteSpreadSheetPDTDAO testSuitePDT;
	private TestSuiteSpreadSheetODTDAO testSuiteODT;


	public TestSuiteSpreadSheetDAO (){
		this.setTestSuitePDT(new TestSuiteSpreadSheetPDTDAO());
		this.setTestSuiteODT(new TestSuiteSpreadSheetODTDAO());
	}


	@Override
	public TestSuite populateTestSuite(TestSource testSource, TestModule testModule, String testSuiteCode) throws Exception {
		TestSuite testSuite = null;

		switch (((TestSourceSpreadSheet)testSource).getTestModulePopulationAlgorithm()) {
		case PDT:
			testSuite = testSuitePDT.populateTestSuite(testSource, testModule, testSuiteCode);
			break;
		case ODT:
			testSuite = testSuiteODT.populateTestSuite(testSource, testModule, testSuiteCode);
			break;
		case AUTO:
			throw new NotImplementedException();
		default:
			throw new TestSourceException (TestSourceException.TESTPARAMETERS_READDATAMODE_NOTSUPPORTED);
		}

		return testSuite;
	}


	//Getters && Setters
	public TestSuiteSpreadSheetPDTDAO getTestSuitePDT() {
		return testSuitePDT;
	}
	public void setTestSuitePDT(TestSuiteSpreadSheetPDTDAO testSuitePDT) {
		this.testSuitePDT = testSuitePDT;
	}
	public TestSuiteSpreadSheetODTDAO getTestSuiteODT() {
		return testSuiteODT;
	}
	public void setTestSuiteODT(TestSuiteSpreadSheetODTDAO testSuiteODT) {
		this.testSuiteODT = testSuiteODT;
	}
}