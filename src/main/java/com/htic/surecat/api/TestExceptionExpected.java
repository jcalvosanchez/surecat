package com.htic.surecat.api;

public class TestExceptionExpected {

	/**
	 * TestException identifier. Must be unique within its TestModule.
	 */
	private String exceptionAlias;

	//TestException Data
	private String exceptionPackage;
	private String exceptionClassName;
	private String exceptionDescription;


	//Getters && Setters
	public String getExceptionAlias() {
		return exceptionAlias;
	}
	public void setExceptionAlias(String exceptionAlias) {
		this.exceptionAlias = exceptionAlias;
	}
	public String getExceptionPackage() {
		return exceptionPackage;
	}
	public void setExceptionPackage(String exceptionPackage) {
		this.exceptionPackage = exceptionPackage;
	}
	public String getExceptionClassName() {
		return exceptionClassName;
	}
	public void setExceptionClassName(String exceptionClassName) {
		this.exceptionClassName = exceptionClassName;
	}
	public String getExceptionDescription() {
		return exceptionDescription;
	}
	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}
}