/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

package com.h3.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class SysUtil {

	// Convert an exception stack trace to string
	public static String getStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);

		return sw.toString();
	}

	// Convert an exception stack trace to string
	public static String getStackTrace() {
		StringWriter sw = new StringWriter();
		new Throwable("").printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	public static byte[] stream2Bytes(InputStream is) {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		try {
			int nRead;
			byte[] data = new byte[1024];
	
			while ((nRead = is.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}
	
			buffer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return buffer.toByteArray();
	}
}
