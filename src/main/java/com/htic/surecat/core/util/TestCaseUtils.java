package com.htic.surecat.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.htic.surecat.api.TestCase;

public class TestCaseUtils {

	/**
	 * Returns all data of the TestCase which header starts with the given prefix.
	 * 	- As Integer Data.
	 * 	- the result list is NOT sorted.
	 *  
	 * @param testCase
	 * @param prefixHeader
	 * @return
	 */
	public static List<Integer> getDataNotSortedFromPrefixHeaderAsInteger (TestCase testCase, String prefixHeader) {
		List<Integer> result = new ArrayList<Integer>();

		Iterator<Map.Entry<String, Object>> dataIterator = testCase.getData().entrySet().iterator();
		while (dataIterator.hasNext()) {
			Map.Entry<String, Object> dataIteration = dataIterator.next();
			if (dataIteration.getKey().startsWith(prefixHeader)) {
				if (dataIteration.getValue()!=null) {
					result.add(((Double)dataIteration.getValue()).intValue());
				}
			}
		}

		return result;
	}

	/**
	 * Returns all data of the TestCase which header starts with the given prefix.
	 * 	- As Integer Data.
	 * 	- the result list is sorted as the headers are.
	 * 
	 * @param testCase
	 * @param prefixHeader
	 * @return
	 */
	public static List<Integer> getDataSortedFromPrefixHeaderAsInteger (TestCase testCase, String prefixHeader) {
		List<Integer> result = new ArrayList<Integer>();
		int i = 1;
		Object dataIteration = testCase.getData().get(prefixHeader + i);

		while (dataIteration != null) {
			
			result.add(((Double)dataIteration).intValue());

			i++;
			dataIteration = testCase.getData().get(prefixHeader + i);
		}

		return result;
	}

	/**
	 * Returns all data of the TestCase which header starts with the given prefix.
	 * 	- the result list is sorted as the headers are.
	 * 
	 * @param testCase
	 * @param prefixHeader
	 * @return
	 */
	public static List<Object> getDataSortedFromPrefixHeader (TestCase testCase, String prefixHeader) {
		List<Object> result = new ArrayList<Object>();
		int i = 1;
		Object dataIteration = testCase.getData().get(prefixHeader + i);

		while (dataIteration != null) {
			
			result.add(dataIteration);

			i++;
			dataIteration = testCase.getData().get(prefixHeader + i);
		}

		return result;
	}
}