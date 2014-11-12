/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

package com.h3.util;

import java.util.ResourceBundle;

public class ResourcesLoader {
    
    public static String getString(String key) {
        return ResourceBundle.getBundle("com.streetsmartcontrols.i18n.text").getString(key);
    }
}
