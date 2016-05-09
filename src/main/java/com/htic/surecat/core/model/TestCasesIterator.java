package com.htic.surecat.core.model;

import java.util.Iterator;
import java.util.Map.Entry;

import com.htic.surecat.api.TestCase;

public class TestCasesIterator implements Iterator<TestCase>{

	private Iterator<Entry<String, TestCase>> testCasesIterator;


	@Override
	public boolean hasNext() {
		return testCasesIterator.hasNext();
	}

	@Override
	public TestCase next() {
		return (TestCase) testCasesIterator.next().getValue();
	}

	@Override
	public void remove() {
		testCasesIterator.remove();
	}


	//Getters && Setters
	public Iterator<Entry<String, TestCase>> getTestCasesIterator() {
		return testCasesIterator;
	}
	public void setTestCasesIterator(Iterator<Entry<String, TestCase>> testCasesIterator) {
		this.testCasesIterator = testCasesIterator;
	}
}