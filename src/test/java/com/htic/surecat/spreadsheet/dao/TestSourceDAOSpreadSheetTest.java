package com.htic.surecat.spreadsheet.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.htic.hticarq.people.model.User;
import com.htic.surecat.api.TestCase;
import com.htic.surecat.api.TestModule;
import com.htic.surecat.api.TestSource;
import com.htic.surecat.api.TestSuite;
import com.htic.surecat.spreadsheet.api.TestSourcePoolSpreadSheet;

public class TestSourceDAOSpreadSheetTest {

//	private static final String TEST_MODULE_FILE_PATH_XLS	= "src/test/resources/com/suricata/surecat/spreadsheet/dao/Suricata_TestModule_SpreadSheetDAO.xls";
//	private static final String TEST_MODULE_FILE_PATH_XLSX	= "src/test/resources/com/suricata/surecat/spreadsheet/dao/Suricata_TestModule_SpreadSheetDAO.xlsx";

	private static final String TESTMODULE_SPREADSHEET_PERSISTENCE_PROPERTIES_PATH	= "src/test/config/com/htic/surecat/spreadsheet/surecat-spreadsheet-persistence.properties";
//	private static final String SURICATA_SPREADSHEET_PERSISTENCE_PROPERTIES_PATH	= "src/test/resources/com/htic/suricata/spreadsheet/dao/persistence/Suricata_TestModule_SpreadSheetDAO.xlsx";

	private static final String TESTSUITE_01_01	= "TS-01.01";
	private static final String TESTSUITE_01_02	= "TS-01.02";
	private static final String TESTSUITE_01_03	= "TS-01.03";
	private static final String TESTSUITE_02_01	= "TS-02.01";
	private static final String TESTSUITE_02_02	= "TS-02.02";
	private static final String TESTSUITE_PN_01	= "TS-PN.01";
	private static final String TESTSUITE_PN_02	= "TS-PN.02";


	private static TestModule testModule;
	private TestCase testCase;


	@BeforeClass
	public static void setUpOneTime() throws Exception {
		TestSource testSource = TestSourcePoolSpreadSheet.getInstanceFromPropertiesFile(TESTMODULE_SPREADSHEET_PERSISTENCE_PROPERTIES_PATH);

		testModule	= testSource.populateTestModule();
	}


	/**
	 * This Test Suite is used for testing Integer variables casting.
	 */
	@Test
	public void testTestModuleDAOODTAdding(){
		TestSuite testSuite  = testModule.getTestSuite(TESTSUITE_01_01);
		while (testSuite.hasNextTestCase()) {
			testCase	= (TestCase) testSuite.nextTestCase();

			assertEquals(testCase.getData("result"), (Integer) testCase.getData("input1") + (Integer)testCase.getData("input2"));
		}
	}

	/**
	 * This Test Suite is used for testing Double variables casting.
	 */
	@Test
	public void testTestModuleDAOODTDivision(){
		double dividend, divisor, quotient, delta;
		TestSuite testSuite  = testModule.getTestSuite(TESTSUITE_01_02);
		while (testSuite.hasNextTestCase()) {
			testCase	= (TestCase) testSuite.nextTestCase();

			delta		= (Double) testCase.getData("delta");
			dividend	= (Double) testCase.getData("dividend");
			divisor		= (Double) testCase.getData("divisor");
			quotient	= (Double) testCase.getData("quotient");

			assertEquals(quotient, dividend / divisor, delta);
		}
	}

	/**
	 * This Test Suite is used for testing Map data support.
	 * This Test Suite is used for testing String data support.
	 */
	@Test
	public void testTestModuleODTDictionary() throws Exception {
		Map<String, String> outputsExpected;
		Map<String, String> outputsObtained;

		TestSuite testSuite  = testModule.getTestSuite(TESTSUITE_01_03);
		while (testSuite.hasNextTestCase()) {
			testCase		= (TestCase) testSuite.nextTestCase();
			outputsObtained	= new HashMap<String, String>();

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
	}

	/**
	 * This Test Suite is used for testing bean 1-level population.
	 * This Test Suite is used for testing Boolean data support.
	 */
	@Test
	public void testTestModuleODTLogin() throws Exception {
		TestSuite testSuite  = testModule.getTestSuite(TESTSUITE_02_01);
		while (testSuite.hasNextTestCase()) {
			testCase		= (TestCase) testSuite.nextTestCase();
			User user	= (User) testCase.getData("user");

			assertEquals(testCase.getData("result"), user.getUsername().equals("jcalvo"));
		}
	}

	/**
	 * This Test Suite is used for testing Bean nested population (2-level, 3-level, 4-level).
	 * This Test Suite is used for testing null values.
	 */
	@Test
	public void testTestModuleODTNestedPopulation() throws Exception {
		TestSuite testSuite  = testModule.getTestSuite(TESTSUITE_02_02);
		while (testSuite.hasNextTestCase()) {
			testCase		= (TestCase) testSuite.nextTestCase();
			User user		= (User) testCase.getData("user");

			assertEquals(null, user.getPassword());
			assertEquals(testCase.getData("result"), user.getPerson().getName().equalsIgnoreCase("Jero"));
			assertEquals(testCase.getData("result"), user.getPerson().getSimplePostalAddress().getProvince().equalsIgnoreCase("Salamanca"));
			assertEquals(testCase.getData("result"), user.getPerson().getPostalAddress().getProvince().getCode().intValue() == 37
													&& user.getPerson().getPostalAddress().getProvince().getDescription().equalsIgnoreCase("Salamanca"));
		}
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