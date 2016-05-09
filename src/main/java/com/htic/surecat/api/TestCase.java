package com.htic.surecat.api;

import java.util.Map;

public class TestCase {

	/**
	 * TestCase identifier. Must be unique within its TestSuite.
	 */
	private String code;

	private Map<String, Object> data;


	//API
	public boolean equalsTo(Object object) {
		boolean areEquals			= true;
		TestCase candidateTestCase	= null;

		if (object == null) {
			areEquals = false;
		} else {
			try {
				candidateTestCase	= (TestCase) object;
			} catch (ClassCastException e) {
				areEquals = false;
			}
		}

		if (areEquals) {
			if (!this.getCode().equals(candidateTestCase.getCode())){
				areEquals = false;
			}

			//FIXME: checkTestModules - Wrong algorith, depends on Object.equals
			if (!this.getData().values().containsAll(candidateTestCase.getData().values())
			||   candidateTestCase.getData().values().containsAll(this.getData().values())) {
				areEquals = false;
			}
		}

		return areEquals;
	}

	public Object getData (String varName) {
		return (Object) this.getData().get(varName);
	}


	//Getters && Setters
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}