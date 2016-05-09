package com.htic.surecat.spreadsheet.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.htic.surecat.api.TestModule;
import com.htic.surecat.api.TestSource;
import com.htic.surecat.api.TestSuite;
import com.htic.surecat.core.model.TestCaseAttribute;
import com.htic.surecat.spreadsheet.model.TemplateSpreadSheet;
import com.htic.surecat.spreadsheet.model.TestSourceSpreadSheet;
import com.htic.surecat.spreadsheet.poi.POIExcelUtils;

public class TestSuiteSpreadSheetODTDAO extends TestSuiteSpreadSheetBaseDAO {

	private TestCaseSpreadSheetODTDAO testCaseDAO;


	public TestSuiteSpreadSheetODTDAO () {
		this.setTestCaseDAO(new TestCaseSpreadSheetODTDAO());
	}


	protected TestSuite populateTestSuiteMetaData(TestSource testSource, TestSuite testSuite, String testSuiteCode) {
		List<TestCaseAttribute> testCaseAttributes	= new ArrayList<TestCaseAttribute>();
		TestCaseAttribute testCaseAttribute;
		Sheet sheet									= ((TestSourceSpreadSheet)testSource).getWorkbook().getSheet(testSuiteCode);
		TemplateSpreadSheet spreadSheetTemplate 	= ((TestSourceSpreadSheet)testSource).getSpreadSheetTemplate();

		testSuite = super.populateTestSuiteMetaData(testSource, testSuite, testSuiteCode);

		// Reads Test Case Header
		Row dataRow	= sheet.getRow(spreadSheetTemplate.getTestCaseHeaderRow());

		for (int dataCellIndex = spreadSheetTemplate.getTestCaseIdCell() + 1; dataCellIndex < dataRow.getLastCellNum(); dataCellIndex++) {
			testCaseAttribute = new TestCaseAttribute();
			testCaseAttribute.setCategory(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+1, spreadSheetTemplate.getTestCaseIdCell() + dataCellIndex));
			testCaseAttribute.setVarName(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+2, spreadSheetTemplate.getTestCaseIdCell() + dataCellIndex));
			testCaseAttribute.setPackageName(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+3, spreadSheetTemplate.getTestCaseIdCell() + dataCellIndex));
			testCaseAttribute.setClassName(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+4, spreadSheetTemplate.getTestCaseIdCell() + dataCellIndex));
			testCaseAttribute.setAttributeName(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+5, spreadSheetTemplate.getTestCaseIdCell() + dataCellIndex));
			testCaseAttribute.setAttributeType(POIExcelUtils.getStringValue(sheet, dataRow.getRowNum()+6, spreadSheetTemplate.getTestCaseIdCell() + dataCellIndex));
			
			testCaseAttributes.add(testCaseAttribute);
		}

		testSuite.setTestCaseAttributes(testCaseAttributes);

		return testSuite;
	}


	protected TestSuite populateTestSuiteData(TestSource testSource, TestModule testModule, TestSuite testSuite, String testSuiteCode) throws Exception {
		Sheet sheet								= ((TestSourceSpreadSheet)testSource).getWorkbook().getSheet(testSuiteCode);
		TemplateSpreadSheet spreadSheetTemplate = ((TestSourceSpreadSheet)testSource).getSpreadSheetTemplate();

		// Reads Test Case Data
		for (int dataRowIndex = spreadSheetTemplate.getTestCaseDataRow() + 2; dataRowIndex <= sheet.getLastRowNum(); dataRowIndex++) {
			testSuite.addTestCase(testCaseDAO.populateTestCase(testSource, testSuite, dataRowIndex));
		}

		return testSuite;
	}


	//Getters && Setters
	public TestCaseSpreadSheetODTDAO getTestCaseDAO() {
		return testCaseDAO;
	}
	public void setTestCaseDAO(TestCaseSpreadSheetODTDAO testCaseDAO) {
		this.testCaseDAO = testCaseDAO;
	}
}