package com.htic.surecat.core.model;

public class TestSourceException extends RuntimeException {

	private static final long serialVersionUID = 3756355806617948694L;

	public static final String MISSING_REQUIRED_PARAMETER	= "missing.required.parameter";
	public static final String ERROR_VALIDATION_PARAMETER	= "error.validation.parameter";
	public static final String TESTPARAMETERS_READDATAMODE_NOTSUPPORTED	= "testparameters.readdatamode.notsupported";

	public static final String TESTEXCEPTIONS_NOT_UNIQUE_IDENTIFIER		= "testexceptions.not.unique.identifier";
	public static final String TESTEXCEPTIONS_NOT_FOUND_IDENTIFIER		= "testexceptions.not.found.identifier";

	public static final String TESTSUITE_NOT_UNIQUE_IDENTIFIER			= "testsuite.not.unique.identifier";
	public static final String TESTSUITE_NOT_VALID_IDENTIFIER			= "testsuite.not.valid.identifier";

	public static final String TESTCASE_NOT_UNIQUE_IDENTIFIER			= "testcase.not.unique.identifier";
	public static final String TESTCASE_NOT_VALID_IDENTIFIER			= "testcase.not.valid.identifier";


	public TestSourceException (){
		super();
	}

	//TODO: Pasar tb el parámetro asociado, missing required parameter pues debería decirme cual
	public TestSourceException (String message) {
		super (message);
	}
}