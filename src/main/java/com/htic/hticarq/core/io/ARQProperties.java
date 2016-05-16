package com.htic.hticarq.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ARQProperties {

	private java.util.Properties properties;


	public ARQProperties(){
		properties = new java.util.Properties();
	}


	public void loadPropertiesFromFile (String filePath) throws IOException {
		InputStream is = null;
		try {
			is = new FileInputStream(new File(filePath));
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			is.close();
		}
	}

	public void loadPropertiesFromResource (String resourcePath) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = null;
		try {
			is = classLoader.getResourceAsStream(resourcePath);
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			is.close();
		}
	}

	/**
	 * A rollback is performed is any of the filePaths fails.
	 */
	public void loadPropertiesFromResources (String[] filePaths) throws IOException {
		java.util.Properties propertiesBackUp = properties;
		try {
			for (int i=0;i<filePaths.length;i++) {
				loadPropertiesFromResource(filePaths[i]);
			}
		} catch (IOException e) {
			properties = propertiesBackUp;
			throw e;
		}
	}

	//TODO - Refactor
	public String getProperty(String key){
		return properties.getProperty(key);
//		try {
//			return new String(properties.get(key).toString().getBytes("ISO-8859-1"),"UTF-8");
//		} catch (NullPointerException e) {
//			return "";
//		}catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			return null;
//		}
	}
}