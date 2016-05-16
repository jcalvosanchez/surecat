package com.htic.hticarq.core.io;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;

import com.htic.hticarq.core.util.ARQPropertiesUtil;

public class ARQPropertiesTest {

	private static final String PROPERTIESFILEPATH	= "src/test/config/surecat-util-stringutils.properties";


	@Test
	public void testGetPropertiesFromFile() throws Exception {
		Properties properties = ARQPropertiesUtil.getPropertiesFromFile(PROPERTIESFILEPATH);

		assertEquals("src/test/resources/arq-utils.xls", properties.getProperty("suricata.testsource.spreadsheet.filepath"));
		assertEquals("TS-StringUtils.removeChar", properties.getProperty("stringutils.removechar"));
		assertEquals("stringChain", properties.getProperty("stringutils.removechar.stringchain"));
		assertEquals("character", properties.getProperty("stringutils.removechar.character"));
		assertEquals("result", properties.getProperty("stringutils.removechar.result"));
	}

}