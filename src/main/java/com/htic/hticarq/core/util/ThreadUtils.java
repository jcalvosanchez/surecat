package com.htic.hticarq.core.util;

public class ThreadUtils {

	public static StackTraceElement[] getCurrentThreadStack() {
		return Thread.currentThread().getStackTrace();
	}

	public static String printStackTrace(StackTraceElement[] stack) {
		String result = "";

		for (int i=0;i<stack.length;i++) {
			StackTraceElement traceElement = stack[i];
			result = result.concat("[").concat(StringUtils.intToString(i, (("" + stack.length).length()))).concat("]")
						.concat(" - ")
						.concat("className = ").concat(traceElement.getClassName())
						.concat(" - methodName = ").concat(traceElement.getMethodName())
						.concat(" - lineNumber = ").concat(""+traceElement.getLineNumber())
						.concat("\n");
		}

		return result;
	}
}