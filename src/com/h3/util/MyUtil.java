package com.h3.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {

	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");

	private MyUtil() {
		// Do nothing
	}

	public static String getFormattedDateTime(Date dateTime) {
		return simpleDateFormat.format(dateTime);
	}
}
