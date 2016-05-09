package com.htic.surecat.api;

import java.util.HashMap;
import java.util.Map;

import com.htic.surecat.core.model.TestSourceException;

/**
 * A TestModule is a set of Acceptance Tests agreed between contractor and customer for a given project feature.</p>
 * <ul>
 * <li>Each TestModule is stored in given source, being an Excel file the recommended one because it's easy to create, maintain, interpretate and approve (ie attached to a meeting minutes).</li>
 * <li>It is a simple mechanism for decoupling Automated Acceptance Test Sources and Data used to feed them.</li> 
 * <li>One TestModule has one or more TestSuites.</li>
 * </ul>
 */
public class TestModule {

	//TestModule MetaData
	private Map<String, TestExceptionExpected> testExpectedExceptions;

	//TestModuleData
	private Map<String, TestSuite> testSuites;


	//API
	public void addSuite(TestSuite testSuite) {
		if (testSuite == null) {
			throw new TestSourceException(TestSourceException.TESTSUITE_NOT_VALID_IDENTIFIER);
		} else if (testSuites != null && testSuites.get(testSuite.getCode()) != null) {
			throw new TestSourceException(TestSourceException.TESTSUITE_NOT_UNIQUE_IDENTIFIER);
		} else {
			if (testSuites == null) {
				testSuites	= new HashMap<String, TestSuite> ();
			}
			testSuites.put(testSuite.getCode(), testSuite);
		}
	}
	public TestSuite getTestSuite (String testSuiteCode) {
		return this.getTestSuites().get(testSuiteCode);
	}
	public boolean assertIsExceptionExpected(Object aliasExpectedException, Exception actualException) {
		TestExceptionExpected testException = null;

		try {
			//TODO: 2 causes of exception here
			if (aliasExpectedException instanceof String) {
				testException = this.getTestExpectedExceptions().get(aliasExpectedException);
			}
		} catch (Exception e) {}

		if (testException == null) {
			throw new TestSourceException(TestSourceException.TESTEXCEPTIONS_NOT_FOUND_IDENTIFIER);
		} else {
			return (actualException.getClass().getName().equals(testException.getExceptionPackage().concat(".").concat(testException.getExceptionClassName())));
		}
	}


	//Getter && Setters
	public Map<String, TestSuite> getTestSuites() {
		return testSuites;
	}
	public void setTestSuite(Map<String, TestSuite> testSuites) {
		this.testSuites = testSuites;
	}
	public Map<String, TestExceptionExpected> getTestExpectedExceptions() {
		return testExpectedExceptions;
	}
	public void setTestExpectedExceptions(Map<String, TestExceptionExpected> testExpectedExceptions) {
		this.testExpectedExceptions = testExpectedExceptions;
	}
}