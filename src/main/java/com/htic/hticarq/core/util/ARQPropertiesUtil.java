package com.htic.hticarq.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ARQPropertiesUtil {

	public static java.util.Properties getPropertiesFromFile (String filePath) throws IOException {
		InputStream is = null;
		java.util.Properties properties = null;

		try {
			is = new FileInputStream(new File(filePath));
			properties = new java.util.Properties();
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			is.close();
		}

		return properties;
	}

	public static java.util.Properties getPropertiesFromFiles (String[] filePaths) throws IOException {
		java.util.Properties properties = new java.util.Properties();
		
		for (int i=0;i<filePaths.length;i++) {
			properties.putAll(getPropertiesFromFile(filePaths[i]));
		}
		
		return properties;
	}

//	public static java.util.Properties getPropertiesFromResource (String resourcePath) throws IOException {
//		InputStream is = null;
//		java.util.Properties properties = null;
//	
//		try {
//			is = getClass().getClassLoader().getResourceAsStream(resourcePath);
//			properties = new java.util.Properties();
//			properties.load(is);
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			is.close();
//		}
//
//		return properties;
//	}
}