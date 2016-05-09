package com.htic.surecat.api;

import com.htic.surecat.core.dao.TestModuleDAO;
import com.htic.surecat.core.dao.TestSuiteDAO;
import com.htic.surecat.core.model.TestSourceProperties;

public abstract class TestSource {

	private TestSourceProperties testSourceParameters;

	private TestModuleDAO testModuleDAO;
	private TestSuiteDAO testSuiteDAO;


	public TestSource (TestSourceProperties testSourceParameters){
		setTestSourceParameters(testSourceParameters);

		inyectDependencies(); //TODO rename me to inject
	}


	protected abstract void inyectDependencies();


	//API
	/**
	 * Returns a TestModule from a given source. 
	 * Each implementation will obtain it from one source, ie excel file, csv file, ddbb, xml...)
	 */
	public TestModule populateTestModule() throws Exception {
		return testModuleDAO.populate(this);
	}

	public TestSuite populateTestSuite(TestModule testModule, String testSuiteCode) throws Exception {
		return testSuiteDAO.populateTestSuite (this, testModule, testSuiteCode);
	}


	//Getters && Setters
	public TestSourceProperties getTestSourceParameters() {
		return testSourceParameters;
	}
	public void setTestSourceParameters(TestSourceProperties testSourceParameters) {
		this.testSourceParameters = testSourceParameters;
	}
	public TestModuleDAO getTestModuleDAO() {
		return testModuleDAO;
	}
	public void setTestModuleDAO(TestModuleDAO testModuleDAO) {
		this.testModuleDAO = testModuleDAO;
	}
	public TestSuiteDAO getTestSuiteDAO() {
		return testSuiteDAO;
	}
	public void setTestSuiteDAO(TestSuiteDAO testSuiteDAO) {
		this.testSuiteDAO = testSuiteDAO;
	}
}