package com.htic.surecat.spreadsheet.poi;

import java.lang.reflect.InvocationTargetException;

import com.htic.surecat.core.model.TestCaseAttribute;

public class POIExcelUtils extends com.htic.suricataarq.poi.util.POIExcelUtils {

	public static final int FILE_FORMAT_EXCEL_1997	= 1;
	public static final int FILE_FORMAT_EXCEL_2003	= 2;
	public static final int FILE_FORMAT_OPENOFFICE_SPREADSHEET	= 3;


	/**
	 * @param attributeValue, as a String Object
	 * @param attributeType
	 * @return attributeValue, as a <code>attributeType</code> Object
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public static TestCaseAttribute recoverAttributeValue(TestCaseAttribute testCaseAttribute) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		Object attributeValue	= testCaseAttribute.getAttributeValue();
		String attributeType	= testCaseAttribute.getAttributeType();

		if (attributeValue != null) {
			try {
				if (attributeType.equals("Integer")) {
					testCaseAttribute.setAttributeValue(new Integer (new Double((String)attributeValue).intValue()));
				} else {
					testCaseAttribute.setAttributeValue(Class.forName("java.lang."+attributeType).getConstructor(new Class[] {String.class}).newInstance(new Object[]{attributeValue}));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return testCaseAttribute;
	}

}