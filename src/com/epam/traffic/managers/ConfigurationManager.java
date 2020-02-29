package com.epam.traffic.managers;

import java.util.ResourceBundle;

/**
 * ConfigurationManager is a singleton for reading database connection configuration file
 */
public final class ConfigurationManager {
    /**
     * resourceBundleConfig is an instance of the ResourceBundle class
     */
    private final static ResourceBundle resourceBundleConfig = ResourceBundle.getBundle("com/epam/traffic/config");

    /**
     * constructor ConfigurationManager() is the default constructor
     */
    private ConfigurationManager() { }

    /**
     * method getProperty(String key) reads property values from a configuration file
     * @param key is property
     * @return value of property
     */
    public static String getProperty(String key) {
        return resourceBundleConfig.getString(key);
    }
}
