package com.htic.surecat.core.model;

/**
 * @deprecated, there is no point not to use ODT
 * @author jcalvo
 */
@Deprecated
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