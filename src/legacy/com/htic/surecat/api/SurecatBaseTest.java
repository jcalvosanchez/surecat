package com.htic.surecat.api;

import com.htic.hticarq.core.model.exception.NotImplementedException;
import com.htic.surecat.spreadsheet.api.TestSourcePoolSpreadSheet;

/**
 * Deprecated, use composition instead of inheritance.
 */
@Deprecated
public class SurecatBaseTest {

	private String testModuleName;
	private String testSuiteName;

	private static TestSource testSource;
	private static TestModule testModule;


	//Before
	protected static void loadTestModuleFromPropertiesFile(String suricataParametersFilepath) throws Exception {
		testSource = TestSourcePoolSpreadSheet.getInstanceFromPropertiesFile(suricataParametersFilepath);

		testModule	= testSource.populateTestModule();
	}

	protected void setTestModuleName(String name) {
		testModuleName = name;
	}

	protected void setTestSuiteName(String name) {
		testSuiteName = name;
	}


	//Testing
//	protected TestCasesIterator getTestCasesIterator () {
//		return testModule.getTestSuite(testSource.getTestSourceParameters().getProperty(testModuleName.concat(".").concat(testSuiteName))).getTestCasesIterator();
//	}

	protected String getTestSourceProperty(String key) {
		return testSource.getTestSourceParameters().getProperty(testModuleName.concat(".").concat(testSuiteName).concat(".").concat(key));
	}

	protected TestCase nextTestCase() {
		throw new NotImplementedException();
	}

	//After


	//protected Getters && Setters
	protected static TestModule getTestModule() {
		return testModule;
	}
	protected static void setTestModule(TestModule testModule) {
		SurecatBaseTest.testModule = testModule;
	}
}