package com.htic.surecat.spreadsheet.dao.impl;

import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.htic.hticarq.core.model.exception.NotImplementedException;
import com.htic.surecat.api.TestCase;
import com.htic.surecat.api.TestSource;
import com.htic.surecat.api.TestSuite;
import com.htic.surecat.spreadsheet.dao.TestCaseSpreadSheetDAO;
import com.htic.surecat.spreadsheet.model.TemplateSpreadSheet;
import com.htic.surecat.spreadsheet.model.TestSourceSpreadSheet;
import com.htic.surecat.spreadsheet.poi.POIExcelUtils;

public class TestCaseSpreadSheetPDTDAO implements TestCaseSpreadSheetDAO {

	@Override
	public TestCase populateTestCase(TestSource testSource, TestSuite testSuite, int dataRowIndex) {
		TestCase testCase;
		HashMap<String, Object> data;
		testCase	= new TestCase ();
		data		= new HashMap<String, Object>();

		Sheet sheet								= ((TestSourceSpreadSheet)testSource).getWorkbook().getSheet(testSuite.getCode());
		TemplateSpreadSheet spreadSheetTemplate = ((TestSourceSpreadSheet)testSource).getSpreadSheetTemplate();
		Row dataRow	= sheet.getRow(dataRowIndex);
		for (int dataCellIndex = spreadSheetTemplate.getTestCaseIdCell() + 1; dataCellIndex < dataRow.getLastCellNum(); dataCellIndex++) {
			data.put(POIExcelUtils.getStringValue(sheet, spreadSheetTemplate.getTestCaseHeaderRow()+2, dataCellIndex), POIExcelUtils.getValue(sheet, dataRowIndex, dataCellIndex));
		}

		testCase.setData(data);
		testCase.setCode(POIExcelUtils.getStringValue(sheet, dataRowIndex, spreadSheetTemplate.getTestCaseIdCell()));

		return testCase;
	}

	@Override
	public TestCase populateTestCase(TestSource testSource, TestSuite testSuite, String testCaseCode) throws Exception {
		throw new NotImplementedException();
	}
}