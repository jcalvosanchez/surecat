package com.htic.surecat.core.model;

public enum TestModulePopulationAlgorithm {

	PDT, ODT, AUTO;

	public static TestModulePopulationAlgorithm fromString(String enumAsString) {
		TestModulePopulationAlgorithm tmpa = null;
		try {
			tmpa = TestModulePopulationAlgorithm.valueOf(enumAsString);
		} catch (IllegalArgumentException e1) {
		}
		return tmpa;
	}
}