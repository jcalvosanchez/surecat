package com.htic.surecat.api;

import com.htic.surecat.core.dao.TestModuleDao;

public abstract class TestSource {

	private TestModuleDao testModuleDao;


	public TestSource (){
		injectDependencies(); //TODO rename me to inject
	}


	protected abstract void injectDependencies();


	//API
	/**
	 * Returns a TestModule from a given source. 
	 * Each implementation will obtain it from one source, ie excel file, csv file, ddbb, xml...)
	 */
	public TestModule populateTestModule() throws Exception {
		return testModuleDao.populate(this);
	}


	//Getters && Setters
	public TestModuleDao getTestModuleDao() {
		return testModuleDao;
	}
	public void setTestModuleDao(TestModuleDao testModuleDAO) {
		this.testModuleDao = testModuleDAO;
	}
}