package com.htic.surecat.core.model;

import java.io.IOException;

import com.htic.hticarq.core.io.ARQProperties;

public class TestSourceProperties {

	private ARQProperties arqProperties;


	public TestSourceProperties (String pathParamFile) throws IOException {
		arqProperties = new ARQProperties();
		arqProperties.loadPropertiesFromFile(pathParamFile);

		populateDefaultValues();
	}


	protected void populateDefaultValues() throws TestSourceException {}


	//API
	public String getProperty(String key) {
		return arqProperties.getProperty(key);
	}
}