package com.htic.hticarq.core.io;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ARQPropertiesTest {

	private static final String PROPERTIESFILEPATH	= "src/test/config/surecat-util-stringutils.properties";


	@Test
	public void loadPropertiesFromFileTest() throws Exception {
		ARQProperties arqProperties = new ARQProperties();
		arqProperties.loadPropertiesFromFile(PROPERTIESFILEPATH);

		assertEquals("src/test/resources/com/htic/arq/core/util/arq-utils.xls", arqProperties.getProperty("suricata.testsource.spreadsheet.filepath"));
		assertEquals("TS-StringUtils.removeChar", arqProperties.getProperty("stringutils.removechar"));
		assertEquals("stringChain", arqProperties.getProperty("stringutils.removechar.stringchain"));
		assertEquals("character", arqProperties.getProperty("stringutils.removechar.character"));
		assertEquals("result", arqProperties.getProperty("stringutils.removechar.result"));
	}

}