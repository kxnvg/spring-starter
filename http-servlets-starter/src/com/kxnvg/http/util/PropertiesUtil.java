package com.kxnvg.http.util;


import java.util.Properties;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;


@UtilityClass
public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    @SneakyThrows
    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}

