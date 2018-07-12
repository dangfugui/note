package com.dang.utils.io;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dang on 2017/5/9.
 */
public class PropertiesUtil {
    private static Properties properties = createProperties();

    public static String getProperty(String name) {
        return properties.getProperty(name);
    }

    public static int getInt(String name) {
        return Integer.parseInt(properties.getProperty(name));
    }

    private static Properties createProperties() {
        properties = new Properties();
        String[] fileNames = {"application.properties"};
        try {
            for (String file : fileNames) {
                InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(file);
                if (inputStream != null) {
                    properties.load(inputStream);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}
