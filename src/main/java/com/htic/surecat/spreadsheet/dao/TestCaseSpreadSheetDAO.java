package com.htic.surecat.spreadsheet.dao;

import com.htic.surecat.api.TestCase;
import com.htic.surecat.api.TestSource;
import com.htic.surecat.api.TestSuite;
import com.htic.surecat.core.dao.TestCaseDAO;

public interface TestCaseSpreadSheetDAO extends TestCaseDAO {

	public TestCase populateTestCase(TestSource testSource, TestSuite testSuite, int dataRowIndex) throws Exception;

}