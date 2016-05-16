package com.htic.surecat.spreadsheet.model;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import com.htic.surecat.api.TestSource;
import com.htic.surecat.core.model.TestSourceException;
import com.htic.surecat.spreadsheet.dao.TestModuleDaoSpreadSheet;
import com.htic.surecat.spreadsheet.dao.TestSourceDaoSpreadSheet;

public class TestSourceSpreadSheet extends TestSource {

	private TestSourceDaoSpreadSheet testSourceDaoSpreadSheet;
	private TemplateSpreadSheet spreadSheetTemplate;
	private Workbook workbook;


	public TestSourceSpreadSheet(String filePath) throws IOException, InvalidFormatException {
		this(new TemplateSpreadSheet(filePath));
	}

	public TestSourceSpreadSheet(TemplateSpreadSheet templateSpreadSheet) throws InvalidFormatException, IOException {
		super();

		if (templateSpreadSheet.getFilePath() == null) {
			throw new TestSourceException(TestSourceException.MISSING_REQUIRED_PARAMETER);
		}
		setSpreadSheetTemplate(templateSpreadSheet);
		setWorkbook(testSourceDaoSpreadSheet.populateWorkbook(templateSpreadSheet));
	}


	@Override
	protected void injectDependencies() {
		this.testSourceDaoSpreadSheet = new TestSourceDaoSpreadSheet ();
		setTestModuleDao(new TestModuleDaoSpreadSheet());
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
}