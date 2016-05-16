package com.htic.surecat.spreadsheet.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.htic.surecat.api.TestCase;
import com.htic.surecat.api.TestSuite;
import com.htic.surecat.core.dao.TestSuiteDAO;
import com.htic.surecat.core.model.TestCaseAttribute;
import com.htic.surecat.spreadsheet.model.TemplateSpreadSheet;
import com.htic.surecat.spreadsheet.poi.POIExcelUtils;

/**
 * Design Patter: Template Method, define the skeleton of an algorithm in an operation, deferring some steps to client subclasses.
 * Algorithm: Read Mode.
 * Template Method: populateTestSuite.
 * Generalization Class: TestSuiteSpreadSheetBaseDAO.
 * Realization Classes: TestSuiteSpreadSheetPDTDAO, TestSuiteSpreadSheetODTDAO.
 */
public class TestSuiteDaoSpreadSheet implements TestSuiteDAO {

	private TestCaseDaoSpreadSheet testCaseDao;


	public TestSuiteDaoSpreadSheet () {
		this.setTestCaseDao(new TestCaseDaoSpreadSheet());
	}

	public TestSuite populateTestSuite(TemplateSpreadSheet spreadSheetTemplate, Sheet sheet) throws Exception {
		TestSuite testSuite = new TestSuite();

		testSuite = populateTestSuiteMetaData (testSuite, spreadSheetTemplate, sheet);
		testSuite = populateTestSuiteData(testSuite, spreadSheetTemplate, sheet);

		return testSuite;
	}


	protected TestSuite populateTestSuiteMetaData(TestSuite testSuite, TemplateSpreadSheet spreadSheetTemplate, Sheet sheet) {
		List<TestCaseAttribute> testCaseAttributes	= new ArrayList<TestCaseAttribute>();
		TestCaseAttribute testCaseAttribute;

		testSuite.setCode(POIExcelUtils.getStringValue(sheet, spreadSheetTemplate.getTestSuiteCodePosition().getRow(), spreadSheetTemplate.getTestSuiteCodePosition().getColumn()));
		testSuite.setDescription(POIExcelUtils.getStringValue(sheet, spreadSheetTemplate.getTestSuiteDescriptionPosition().getRow(), spreadSheetTemplate.getTestSuiteDescriptionPosition().getColumn()));
		testSuite.setObservations(POIExcelUtils.getStringValue(sheet, spreadSheetTemplate.getTestSuiteObservationsPosition().getRow(), spreadSheetTemplate.getTestSuiteObservationsPosition().getColumn()));

		// Reads Test Case Header
		Row dataRow	= sheet.getRow(spreadSheetTemplate.getTestCaseHeaderPosition().getRow());

		for (int dataCellIndex = spreadSheetTemplate.getTestCaseCodePosition().getColumn() + 1; dataCellIndex < dataRow.getLastCellNum(); dataCellIndex++) {
			testCaseAttribute = new TestCaseAttribute();
			testCaseAttribute.setCategory(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+1, spreadSheetTemplate.getTestCaseCodePosition().getColumn() + dataCellIndex));
			testCaseAttribute.setVarName(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+2, spreadSheetTemplate.getTestCaseCodePosition().getColumn() + dataCellIndex));
			testCaseAttribute.setPackageName(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+3, spreadSheetTemplate.getTestCaseCodePosition().getColumn() + dataCellIndex));
			testCaseAttribute.setClassName(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+4, spreadSheetTemplate.getTestCaseCodePosition().getColumn() + dataCellIndex));
			testCaseAttribute.setAttributeName(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+5, spreadSheetTemplate.getTestCaseCodePosition().getColumn() + dataCellIndex));
			testCaseAttribute.setAttributeType(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+6, spreadSheetTemplate.getTestCaseCodePosition().getColumn() + dataCellIndex));

			testCaseAttributes.add(testCaseAttribute);
		}

		testSuite.setTestCaseAttributes(testCaseAttributes);

		return testSuite;
	}


	protected TestSuite populateTestSuiteData(TestSuite testSuite, TemplateSpreadSheet spreadSheetTemplate, Sheet sheet) throws Exception {
		// Reads Test Case Data
		for (int dataRowIndex = spreadSheetTemplate.getTestCaseDataPosition().getRow(); dataRowIndex <= sheet.getLastRowNum(); dataRowIndex++) {
			TestCase testCase = testCaseDao.populateTestCase(testSuite, spreadSheetTemplate, sheet, dataRowIndex);
			testSuite.addTestCase(testCase);
		}

		return testSuite;
	}
	
	//Getters && Setters
	public TestCaseDaoSpreadSheet getTestCaseDao() {
		return testCaseDao;
	}
	public void setTestCaseDao(TestCaseDaoSpreadSheet testCaseDao) {
		this.testCaseDao = testCaseDao;
	}
}