package com.htic.surecat.spreadsheet.api;

import java.util.HashMap;
import java.util.Map;

import com.htic.surecat.api.TestSource;
import com.htic.surecat.spreadsheet.model.TestSourceSpreadSheet;

//TODO: Use Pool<TestSource>
public class TestSourcePoolSpreadSheet {

	private static Map<String, TestSource> instancesPool = null;


	/**
	 * filePath is used as key to avoid building instances already done.
	 */
	public static TestSource getInstanceFromPropertiesFile(String filePath) {
		if(instancesPool == null)
			instancesPool = new HashMap<String, TestSource>();
		if (!instancesPool.containsKey(filePath)) {
			instancesPool.put(filePath, TestSourceSpreadSheet.getInstanceFromPropertiesFile(filePath));
		}

		return instancesPool.get(filePath);
	}
}