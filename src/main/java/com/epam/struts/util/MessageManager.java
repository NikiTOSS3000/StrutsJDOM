package com.epam.struts.util;

import java.util.ResourceBundle;

public final class MessageManager {
    private static ResourceBundle resource = ResourceBundle.getBundle("com.epam.struts.resources.message");

    public static String getStr(String key) {
        return resource.getString(key);
    }
}
