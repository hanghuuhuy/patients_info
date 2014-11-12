/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

package com.h3.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class FileUtils {

    public static void download(String urlStr, String destiFile) {

        try {
           
            URL url = new URL(urlStr);
            
            URLConnection connection = url.openConnection();
//            connection.setRequestProperty(urlStr, urlStr);

            InputStream is = connection.getInputStream();
            OutputStream os = new FileOutputStream(destiFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
