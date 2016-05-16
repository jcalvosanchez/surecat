package com.htic.surecat.spreadsheet.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.htic.hticarq.people.model.User;
import com.htic.surecat.api.TestCase;
import com.htic.surecat.api.TestModule;
import com.htic.surecat.api.TestSuite;
import com.htic.surecat.spreadsheet.model.TestSourceSpreadSheet;

public class TestSourceDAOSpreadSheetTest {

	private static final String TEST_MODULE_FILE_PATH_XLS	= "src/test/config/surecat-persistence-spreadsheet-xls.properties";
	private static final String TEST_MODULE_FILE_PATH_XLSX	= "src/test/config/surecat-persistence-spreadsheet-xlsx.properties";

	private static final String TESTSUITE_01_01	= "TS-01.01";
	private static final String TESTSUITE_01_02	= "TS-01.02";
	private static final String TESTSUITE_01_03	= "TS-01.03";
	private static final String TESTSUITE_02_01	= "TS-02.01";
	private static final String TESTSUITE_02_02	= "TS-02.02";
	private static final String TESTSUITE_PN_01	= "TS-PN.01";
	private static final String TESTSUITE_PN_02	= "TS-PN.02";


	private static TestModule testModuleXls;
	private static TestModule testModuleXlsx;
	private TestSuite testSuite;
	private TestCase testCase;


	@BeforeClass
	public static void setUpOneTime() throws Exception {
//		testModuleXls	= TestSourcePoolSpreadSheet.getInstanceFromPropertiesFile(TEST_MODULE_FILE_PATH_XLS).populateTestModule();
		testModuleXls	= new TestSourceSpreadSheet(TEST_MODULE_FILE_PATH_XLS).populateTestModule();
		testModuleXlsx	= new TestSourceSpreadSheet(TEST_MODULE_FILE_PATH_XLSX).populateTestModule();
	}


	@Test
	public void testTestModuleDAOODTAdding(){
		testSuite  = testModuleXls.getTestSuite(TESTSUITE_01_01);
		while (testSuite.hasNextTestCase()) {
			testCase	= (TestCase) testSuite.nextTestCase();

			testCaseAdding(testCase);
		}

		testSuite  = testModuleXlsx.getTestSuite(TESTSUITE_01_01);
		while (testSuite.hasNextTestCase()) {
			testCase	= (TestCase) testSuite.nextTestCase();

			testCaseAdding(testCase);
		}
	}

	/**
	 * This Test Suite is used for testing Integer variables casting.
	 */
	private void testCaseAdding(TestCase testCase) {
		assertEquals(testCase.getData("result"), (Integer) testCase.getData("input1") + (Integer)testCase.getData("input2"));
	}

	@Test
	public void testTestModuleDAOODTDivision(){
		TestSuite testSuite  = testModuleXls.getTestSuite(TESTSUITE_01_02);
		while (testSuite.hasNextTestCase()) {
			testCase	= (TestCase) testSuite.nextTestCase();

			testDividing(testCase);
		}
		
		testSuite  = testModuleXlsx.getTestSuite(TESTSUITE_01_02);
		while (testSuite.hasNextTestCase()) {
			testCase	= (TestCase) testSuite.nextTestCase();

			testDividing(testCase);
		}
	}

	/**
	 * This Test Suite is used for testing Double variables casting.
	 */
	private void testDividing(TestCase testCase) {
		double delta	= (Double) testCase.getData("delta");
		double dividend	= (Double) testCase.getData("dividend");
		double divisor	= (Double) testCase.getData("divisor");
		double quotient	= (Double) testCase.getData("quotient");

		assertEquals(quotient, dividend / divisor, delta);
	}

	@Test
	public void testTestModuleODTDictionary() throws Exception {
		TestSuite testSuite  = testModuleXls.getTestSuite(TESTSUITE_01_03);
		while (testSuite.hasNextTestCase()) {
			testCase		= (TestCase) testSuite.nextTestCase();

			testMaps(testCase);
		}

		testSuite  = testModuleXlsx.getTestSuite(TESTSUITE_01_03);
		while (testSuite.hasNextTestCase()) {
			testCase		= (TestCase) testSuite.nextTestCase();

			testMaps(testCase);
		}
	}

	/**
	 * This Test Suite is used for testing Map data support.
	 * This Test Suite is used for testing String data support.
	 */
	private void testMaps(TestCase testCase) {
		Map<String, String> outputsExpected;
		Map<String, String> outputsObtained = new HashMap<String, String>();
		outputsExpected	= (Map<String, String>) testCase.getData("result");
		if (testCase.getData("candidate").equals("Números")) {
			outputsObtained.put("Uno", "Un");
			outputsObtained.put("Dos", "Deux");
			outputsObtained.put("Tres", "Trois");
		} else if (testCase.getData("candidate").equals("Días")) {
			outputsObtained.put("Lunes", "Lundi");
			outputsObtained.put("Martes", "Mardi");
			outputsObtained.put("Miércoles", "Mercredi");
		} else if (testCase.getData("candidate").equals("Meses")) {
			outputsObtained.put("Enero", "Janvier");
			outputsObtained.put("Febrero", "Février");
			outputsObtained.put("Marzo", "Mars");
		}

		assertEquals(outputsExpected, outputsObtained);
	}

	@Test
	public void testTestModuleODTLogin() throws Exception {
		TestSuite testSuite  = testModuleXls.getTestSuite(TESTSUITE_02_01);
		while (testSuite.hasNextTestCase()) {
			testCase		= (TestCase) testSuite.nextTestCase();

			testLogin(testCase);
		}

		testSuite  = testModuleXlsx.getTestSuite(TESTSUITE_02_01);
		while (testSuite.hasNextTestCase()) {
			testCase		= (TestCase) testSuite.nextTestCase();

			testLogin(testCase);
		}
	}

	/**
	 * This Test Suite is used for testing bean 1-level population.
	 * This Test Suite is used for testing Boolean data support.
	 */
	private void testLogin(TestCase testCase) {
		User user	= (User) testCase.getData("user");
		assertEquals(testCase.getData("result"), user.getUsername().equals("jcalvo"));
	}

	@Test
	public void testTestModuleODTNestedPopulation() throws Exception {
		TestSuite testSuite  = testModuleXls.getTestSuite(TESTSUITE_02_02);
		while (testSuite.hasNextTestCase()) {
			testCase		= (TestCase) testSuite.nextTestCase();

			testLoginAdvanced(testCase);
		}

		testSuite  = testModuleXlsx.getTestSuite(TESTSUITE_02_02);
		while (testSuite.hasNextTestCase()) {
			testCase		= (TestCase) testSuite.nextTestCase();

			testLoginAdvanced(testCase);
		}
	}

	/**
	 * This Test Suite is used for testing Bean nested population (2-level, 3-level, 4-level).
	 * This Test Suite is used for testing null values.
	 */
	private void testLoginAdvanced(TestCase testCase) {
		User user		= (User) testCase.getData("user");

		assertEquals(null, user.getPassword());
		assertEquals(testCase.getData("result"), user.getPerson().getName().equalsIgnoreCase("Jero"));
		assertEquals(testCase.getData("result"), user.getPerson().getSimplePostalAddress().getProvince().equalsIgnoreCase("Salamanca"));
		assertEquals(testCase.getData("result"), user.getPerson().getPostalAddress().getProvince().getCode().intValue() == 37
												&& user.getPerson().getPostalAddress().getProvince().getDescription().equalsIgnoreCase("Salamanca"));
	}

//	/**
//	 * This Test Suite is used for testing expected Exceptions.
//	 */
//	@Test
//	public void testTestModuleODTPrimeNumbersExceptions() throws Exception {
//		PrimeFactorsBO primeFactorsBO = new PrimeFactorsBOImpl();
//
//		TestSuite testSuite  = testModule.getTestSuite(TESTSUITE_PN_01);
//		while (testSuite.hasNextTestCase()) {
//			testCase		= (TestCase) testSuite.nextTestCase();
//
//			try {
//				IntegerVO numberVO	=  new IntegerVO ((String)testCase.getData("candidate"));
//
//				assertEquals(testCase.getData("result"), primeFactorsBO.isPrimeNumber(numberVO.getNumber().intValue()));
//			} catch (Exception e) {
////				assertEquals (true, TestExceptionUtils.isExpectedException (testModule.getTestExpectedExceptions(), testCase.getData("expectedAliasException"), e));
//				assertEquals (true, testModule.assertIsExceptionExpected (testCase.getData("expectedAliasException"), e));
//			}
//		}
//	}
//
//	/**
//	 * This Test Suite is used for testing List data.
//	 */
//	@Test
//	public void testTestModuleODTPrimeFactorsASC() throws Exception {
//		PrimeFactorsBO primeFactorsBO = new PrimeFactorsBOImpl();
//		List<Integer> outputsExpected;
//		List<Integer> outputsObtained;
//
//		TestSuite testSuite  = testModule.getTestSuite(TESTSUITE_PN_02);
//		while (testSuite.hasNextTestCase()) {
//			testCase		= (TestCase) testSuite.nextTestCase();
//
//			outputsExpected	= (ArrayList<Integer>) testCase.getData("result");
//			outputsObtained	= primeFactorsBO.getPrimeFactors(((Integer)testCase.getData("candidate")).intValue());
//
//			assertEquals(outputsExpected, outputsObtained);
//		}
//	}
}