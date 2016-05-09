package com.htic.surecat.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.htic.surecat.core.model.TestCaseAttribute;
import com.htic.surecat.core.model.TestCasesIterator;
import com.htic.surecat.core.model.TestSourceException;

public class TestSuite {
//TODO: Meter aquí el testcasesiterator para que el código quedase así:
//	@Test
//	public void testTestModuleDAOODTAdding(){
//		TestSuite testSuite = testModule.getTestSuite(TESTSUITE_01_01);
//		while (testSuite.hasNextTestCase()) {
//			testCase	= (TestCase) testSuite.nextTestCase();
//
//			assertEquals(testCase.getData("result"), (Integer) testCase.getData("input1") + (Integer)testCase.getData("input2"));
//		}
//	}
	/**
	 * TestSuite identifier. Must be unique within its TestModule.
	 */
	private String code;

	//TestSuite MetaData
	private String observations;
	private String description;

	private List<TestCaseAttribute> testCaseAttributes;

	//TestSuite Data
	private Map<String, TestCase> testCasesData;

	//TestSuite Internal Private Data
	private static TestCasesIterator testCasesIterator;


	//API
	public void addTestCase(TestCase testCase) {
		if (testCase == null) {
			throw new TestSourceException(TestSourceException.TESTCASE_NOT_VALID_IDENTIFIER);
		} else if (testCasesData != null && testCasesData.get(testCase.getCode()) != null) {
			throw new TestSourceException(TestSourceException.TESTCASE_NOT_UNIQUE_IDENTIFIER);
		} else {
			if (testCasesData == null) {
				testCasesData = new HashMap<String, TestCase>();
			}
			testCasesData.put(testCase.getCode(), testCase);
		}
	}
	public TestCase getTestCase (String codeTestCase) {
		return this.getTestCasesData().get(codeTestCase);
	}

	private TestCasesIterator getTestCasesIterator() {
		synchronized(TestCasesIterator.class) {
			if (testCasesIterator == null) {
				testCasesIterator = new TestCasesIterator();
				testCasesIterator.setTestCasesIterator(this.getTestCasesData().entrySet().iterator());
			}
		}

		return testCasesIterator;
	}
	public boolean hasNextTestCase() {
		return getTestCasesIterator().hasNext();
	}
	public TestCase nextTestCase() {
		return (TestCase) getTestCasesIterator().next();
	}
//	public boolean equalsTo (Object object) {
//		boolean areEquals				= true;
//		TestSuite candidateTestSuite	= null;
//		Iterator<Entry<String, TestCase>> testCasesIterator;
//		TestCase testCase;
//
//		if (object == null) {
//			areEquals = false;
//		} else {
//			try {
//				candidateTestSuite	= (TestSuite) object;
//			} catch (ClassCastException e) {
//				areEquals = false;
//			}
//		}
//
//		if (areEquals) {
//			if (!this.getCode().equals(candidateTestSuite.getCode())
//			||  !this.getObservations().equals(candidateTestSuite.getObservations())
//			||  !this.getDescription().equals(candidateTestSuite.getDescription())){
//				areEquals = false;
//			} else {
//				if (
//				(	
//						!this.getHeaderDataCategory().containsAll(candidateTestSuite.getHeaderDataCategory())
//					||  !this.getHeaderPackageName().containsAll(candidateTestSuite.getHeaderPackageName())
//					||  !this.getHeaderClassName().containsAll(candidateTestSuite.getHeaderClassName())
//					||  !this.getHeaderVariableName().containsAll(candidateTestSuite.getHeaderVariableName())
//					||  !this.getHeaderAttributeName().containsAll(candidateTestSuite.getHeaderAttributeName())
//					||  !this.getHeaderAttributePrimitiveType().containsAll(candidateTestSuite.getHeaderAttributePrimitiveType())
//				)
//				||
//				(	
//						!candidateTestSuite.getHeaderDataCategory().containsAll(this.getHeaderDataCategory())
//					||  !candidateTestSuite.getHeaderPackageName().containsAll(this.getHeaderPackageName())
//					||  !candidateTestSuite.getHeaderClassName().containsAll(this.getHeaderClassName())
//					||  !candidateTestSuite.getHeaderVariableName().containsAll(this.getHeaderVariableName())
//					||  !candidateTestSuite.getHeaderAttributeName().containsAll(this.getHeaderAttributeName())
//					||  !candidateTestSuite.getHeaderAttributePrimitiveType().containsAll(this.getHeaderAttributePrimitiveType())
//				)
//				){
//					areEquals = false;
//				} else {
//					testCasesIterator = this.getTestCasesIterator();
//					while (areEquals && testCasesIterator.hasNext()) {
//						testCase	= (TestCase) testCasesIterator.next().getValue();
//						areEquals	= testCase.equalsTo(candidateTestSuite.getTestCase(testCase.getCode()));
//					}
//
//					testCasesIterator = candidateTestSuite.getTestCasesIterator();
//					while (areEquals && testCasesIterator.hasNext()) {
//						testCase	= (TestCase) testCasesIterator.next().getValue();
//						areEquals	= testCase.equalsTo(this.getTestCase(testCase.getCode()));
//					}
//				}
//			}
//		}
//
//		return areEquals;
//	}


	//Getters && Setters
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Map<String, TestCase> getTestCasesData() {
		return testCasesData;
	}
	public void setTestCasesData(Map<String, TestCase> testCasesData) {
		this.testCasesData = testCasesData;
	}
	public List<TestCaseAttribute> getTestCaseAttributes() {
		return testCaseAttributes;
	}
	public void setTestCaseAttributes(List<TestCaseAttribute> testCaseAttributes) {
		this.testCaseAttributes = testCaseAttributes;
	}
}