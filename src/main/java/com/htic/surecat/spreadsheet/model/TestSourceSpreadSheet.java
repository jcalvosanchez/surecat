package com.htic.surecat.spreadsheet.model;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;

import com.htic.surecat.api.TestSource;
import com.htic.surecat.core.model.TestModulePopulationAlgorithm;
import com.htic.surecat.core.model.TestSourceException;
import com.htic.surecat.core.model.TestSourceProperties;
import com.htic.surecat.spreadsheet.dao.impl.TestModuleSpreadSheetDAO;
import com.htic.surecat.spreadsheet.dao.impl.TestSourceSpreadSheetDAO;
import com.htic.surecat.spreadsheet.dao.impl.TestSuiteSpreadSheetDAO;

public class TestSourceSpreadSheet extends TestSource {

	private Workbook workbook;
	private TemplateSpreadSheet spreadSheetTemplate;
	private TestModulePopulationAlgorithm testModulePopulationAlgorithm;


	private TestSourceSpreadSheet(TestSourceProperties testSourceParameters) {
		super(testSourceParameters);

		TestSourceSpreadSheetDAO testSourceSpreadSheetDAO	= new TestSourceSpreadSheetDAO ();
		try {
			setWorkbook(testSourceSpreadSheetDAO.populateWorkbook((TestSourcePropertiesSpreadSheet)testSourceParameters));
			setSpreadSheetTemplate(testSourceSpreadSheetDAO.populateSpreadSheetTemplate(workbook));
			setTestModulePopulationAlgorithm(testSourceSpreadSheetDAO.populateTestModulePopulationAlgorithm(workbook));
		} catch (Exception e) {
			//TODO Kill this catch, is only for debugging purposes
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	public static TestSource getInstanceFromPropertiesFile(String filePath) throws TestSourceException {
		TestSourcePropertiesSpreadSheet tspss;

		try {
			tspss = new TestSourcePropertiesSpreadSheet(filePath);
		} catch (IOException e) {
			throw new TestSourceException();
		}

		return new TestSourceSpreadSheet(tspss);
	}


	@Override
	protected void inyectDependencies() {
		setTestModuleDAO(new TestModuleSpreadSheetDAO());
		setTestSuiteDAO(new TestSuiteSpreadSheetDAO());
	}


	//Getters && Setters
	public Workbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}
	public TemplateSpreadSheet getSpreadSheetTemplate() {
		return spreadSheetTemplate;
	}
	public void setSpreadSheetTemplate(TemplateSpreadSheet spreadSheetTemplate) {
		this.spreadSheetTemplate = spreadSheetTemplate;
	}
	public TestModulePopulationAlgorithm getTestModulePopulationAlgorithm() {
		return testModulePopulationAlgorithm;
	}
	public void setTestModulePopulationAlgorithm(TestModulePopulationAlgorithm testModulePopulationAlgorithm) {
		this.testModulePopulationAlgorithm = testModulePopulationAlgorithm;
	}
}