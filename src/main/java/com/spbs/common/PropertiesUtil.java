package com.spbs.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {
    public static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    public static Properties properties;

    static {
        String strPro = "UtilHelper.properties";
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(strPro), "utf-8"));
        } catch (Exception e) {
            logger.error("读取配置文件异常", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key.trim());
        if (!StringUtils.isBlank(value)) {
            return value.trim();
        }
        return null;
    }

    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            value = defaultValue;
        }
        return value.trim();
    }
}
