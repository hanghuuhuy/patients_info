/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

package com.h3.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static Date str2Date(String str) {
        if (isEmpty(str)) {
            return null;
        }

		// 2013-12-17 21:34:14
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (Exception e) {

        }

        return date;
    }

    public static String date2Str(Date date) {

        if (date == null) {
            return null;
        }

		// 2013-12-17 21:34:14
        String str = null;
        try {
            str = dateFormat.format(date);
        } catch (Exception e) {

        }

        return str;
    }
    
    public static String boolean2Str(Boolean value) {
        
       if (value == null)
           return "No";
       
       return value ? "Yes" : "No";
    }
}
