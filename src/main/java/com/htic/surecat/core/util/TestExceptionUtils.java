package com.htic.surecat.core.util;

import java.util.Map;

import com.htic.surecat.api.TestExceptionExpected;
import com.htic.surecat.core.model.TestSourceException;

@Deprecated
public class TestExceptionUtils {

	public static boolean isExpectedException(Map<String, TestExceptionExpected> testExceptions, Object expectedAliasException, Exception actualException) {
		TestExceptionExpected testException = null;

		try {
			//TODO: 2 causes of exception here
			if (expectedAliasException instanceof String) {
				testException = testExceptions.get(expectedAliasException);
			}
		} catch (Exception e2) {}

		if (testException == null) {
			throw new TestSourceException(TestSourceException.TESTEXCEPTIONS_NOT_FOUND_IDENTIFIER);
		} else {
			return (actualException.getClass().getName().equals(testException.getExceptionPackage().concat(".").concat(testException.getExceptionClassName())));
		}
	}

}