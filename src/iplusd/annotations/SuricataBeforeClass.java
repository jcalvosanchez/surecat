package com.htic.suricata.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SuricataBeforeClass {

	/**
	 * La key de internacionalización de la columna para la exportación a excel.
	 */
	SuricataImplementationType[] implementation() default SuricataImplementationType.SPREADSHEET;

	/**
	 * El tipo de dato de la columna para la exportación a excel.
	 */
	String parametersFilePath();

}