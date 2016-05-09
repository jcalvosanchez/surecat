package com.htic.surecat.spreadsheet.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.htic.hticarq.core.model.exception.NotImplementedException;
import com.htic.hticarq.core.util.BeanUtils;
import com.htic.surecat.api.TestCase;
import com.htic.surecat.api.TestSource;
import com.htic.surecat.api.TestSuite;
import com.htic.surecat.core.model.TestCaseAttribute;
import com.htic.surecat.spreadsheet.dao.TestCaseSpreadSheetDAO;
import com.htic.surecat.spreadsheet.model.TemplateSpreadSheet;
import com.htic.surecat.spreadsheet.model.TestSourceSpreadSheet;
import com.htic.surecat.spreadsheet.poi.POIExcelUtils;

public class TestCaseSpreadSheetODTDAO implements TestCaseSpreadSheetDAO {

	@Override
	public TestCase populateTestCase(TestSource testSource, TestSuite testSuite, int dataRowIndex) throws Exception {
		TestCase testCase;
		Map<String, Object> testCaseData;
		boolean isPreviouslyInstantiated;
		TestCaseAttribute testCaseAttribute;
		Object classInstance;

		testCase			= new TestCase ();
		testCaseData		= new HashMap<String, Object>();
		testCaseData.clear();

		Sheet sheet								= ((TestSourceSpreadSheet)testSource).getWorkbook().getSheet(testSuite.getCode());
		TemplateSpreadSheet spreadSheetTemplate = ((TestSourceSpreadSheet)testSource).getSpreadSheetTemplate();

		for (int i = 0; i < testSuite.getTestCaseAttributes().size() && spreadSheetTemplate.getTestCaseIdCell() + 1 + i < sheet.getRow(dataRowIndex).getLastCellNum(); i++) {
			
			testCaseAttribute = testSuite.getTestCaseAttributes().get(i);

			testCaseAttribute.setAttributeValue(POIExcelUtils.getStringValue(sheet, dataRowIndex, spreadSheetTemplate.getTestCaseIdCell() + 1 + i));

			testCaseAttribute = POIExcelUtils.recoverAttributeValue (testCaseAttribute);

			classInstance = testCaseData.get(testCaseAttribute.getVarName());
			//Creates the instance if does not exist in the Bean Factory
			if (classInstance == null) {
				if (testCaseAttribute.getPackageName() != null && !testCaseAttribute.getPackageName().equals("")
				&&  testCaseAttribute.getClassName() != null && !testCaseAttribute.getClassName().equals("")) {
					classInstance = Class.forName(testCaseAttribute.getPackageName().concat(".").concat(testCaseAttribute.getClassName())).getConstructor().newInstance();
				} else {
					classInstance = testCaseAttribute.getAttributeValue();
				}
				isPreviouslyInstantiated	= false;
			} else {
				isPreviouslyInstantiated	= true;
			}

			if (testCaseAttribute.getPackageName() != null && testCaseAttribute.getPackageName().equals("java.util")
			&&  testCaseAttribute.getClassName() != null && testCaseAttribute.getClassName().equals("ArrayList")) {
				if (testCaseAttribute.getAttributeValue() != null) {
					((ArrayList<Object>) classInstance).add((Object)testCaseAttribute.getAttributeValue());
				}
			} else 	if (testCaseAttribute.getPackageName() != null && testCaseAttribute.getPackageName().equals("java.util")
				   &&  testCaseAttribute.getClassName() != null && testCaseAttribute.getClassName().equals("HashMap")) {
						if (testCaseAttribute.getAttributeValue() != null) {
							((HashMap<String, Object>) classInstance).put(testCaseAttribute.getAttributeName(), (Object)testCaseAttribute.getAttributeValue());
						}
			} else if (testCaseAttribute.getAttributeName() != null && !testCaseAttribute.getAttributeName().equals("")) {
				classInstance = BeanUtils.invokeRecursiveSetter (classInstance, testCaseAttribute.getAttributeType(), testCaseAttribute.getAttributeName(), testCaseAttribute.getAttributeValue());
			}

			if (isPreviouslyInstantiated) {
				testCaseData.remove(testCaseAttribute.getVarName());
			}

			testCaseData.put(testCaseAttribute.getVarName(), classInstance);
		}

		testCase.setData(testCaseData);
		testCase.setCode(POIExcelUtils.getStringValue(sheet, dataRowIndex, spreadSheetTemplate.getTestCaseIdCell()));

		return testCase;
	}

	@Override
	public TestCase populateTestCase(TestSource testSource, TestSuite testSuite, String testCaseCode) throws Exception {
		throw new NotImplementedException();
	}
}