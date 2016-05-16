package com.htic.surecat.spreadsheet.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.htic.surecat.api.TestSource;
import com.htic.surecat.spreadsheet.model.TestSourceSpreadSheet;

//TODO: Use Pool<TestSource>
public class TestSourcePoolSpreadSheet {

	private static Map<String, TestSource> instancesPool = null;


	/**
	 * filePath is used as key to avoid building instances already done.
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public static TestSource getInstanceFromPropertiesFile(String filePath) throws InvalidFormatException, IOException {
		if(instancesPool == null) {
			instancesPool = new HashMap<String, TestSource>();
		}
		if (!instancesPool.containsKey(filePath)) {
			instancesPool.put(filePath, new TestSourceSpreadSheet(filePath));
		}

		return instancesPool.get(filePath);
	}
}