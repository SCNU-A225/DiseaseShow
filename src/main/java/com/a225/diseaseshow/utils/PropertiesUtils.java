package com.a225.diseaseshow.utils;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

public class PropertiesUtils {
    private static final String PROPERTY_NAME = "application.yml";
    private static final YamlPropertiesFactoryBean yamlFactory;

    static {
        Resource resource = new ClassPathResource(PROPERTY_NAME);
        yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(resource);
    }

    public static Object getProperty(Object key){
        try {
            Properties properties = yamlFactory.getObject();
            assert properties != null;
            return properties.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
