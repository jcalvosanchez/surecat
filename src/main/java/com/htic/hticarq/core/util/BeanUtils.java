package com.htic.hticarq.core.util;

import java.lang.reflect.Method;

public class BeanUtils {

	public static Object invokeRecursiveSetter(Object classInstance, String attributeType, String attributeName, Object attributeValue) throws Exception {
		if (attributeName.contains(".")) {
			String subClassname			= attributeName.substring(0, attributeName.indexOf("."));
			String subAttributeName		= attributeName.substring(attributeName.indexOf(".")+1);
			Object subClass				= invokeGetter (classInstance, subClassname);

			subClass = invokeRecursiveSetter(subClass, attributeType, subAttributeName, attributeValue);
			invokeSetter(classInstance, attributeType, subClassname, subClass);
		} else {
			invokeSetter(classInstance, attributeType, attributeName, attributeValue);
		}

		return classInstance;
	}

	public static void invokeSetter(Object classInstance, String attributeType, String attributeName, Object attributeValue) throws Exception {
		String methodName;
		Method method;
		Class<?> attributeClass;

		if (attributeValue != null) {
			attributeClass = attributeValue.getClass();
		} else {
			attributeClass = Class.forName("java.lang.".concat(attributeType));
		}

		methodName		= "set" + attributeName.substring(0,1).toUpperCase() + attributeName.substring(1);
		method			= classInstance.getClass().getMethod(methodName, attributeClass);
		method.invoke(classInstance, new Object[]{attributeValue});
	}

	public static Object invokeRecursiveGetter(Object classInstance, String attributeName) throws Exception {
//		Object result = null;

		if (attributeName.contains(".")) {
			String subClassname			= attributeName.substring(0, attributeName.indexOf("."));
			String subAttributeName		= attributeName.substring(attributeName.indexOf(".")+1);
			Object subClass				= invokeGetter (classInstance, subClassname);

			return invokeRecursiveGetter(subClass, subAttributeName);
		} else {
			return invokeGetter(classInstance, attributeName);
		}

//		return result;
	}

	public static Object invokeGetter(Object classInstance, String attributeName) throws Exception {
		String methodName;
		Object resultObject;
		Method method;

		methodName		= "get" + attributeName.substring(0,1).toUpperCase() + attributeName.substring(1);
		method			= classInstance.getClass().getMethod(methodName);
		resultObject= method.invoke(classInstance);
		if (resultObject == null) {
			resultObject = Class.forName(method.getReturnType().getCanonicalName()).getConstructor().newInstance();
		}

		return resultObject;
	}

}