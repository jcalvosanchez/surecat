package com.htic.hticarq.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {

	public static boolean writeFile(String inputPath, OutputStream outputStream) {
		InputStream is = null;
		try {
			is				= new FileInputStream(new File(inputPath));
			byte[] bytes	= new byte[1024];
			try {
				int len = 0;
				while ((len = is.read(bytes)) >= 0) {
					outputStream.write(bytes, 0, len);
				}
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;
	}
}