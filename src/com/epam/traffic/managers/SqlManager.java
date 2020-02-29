package com.epam.traffic.managers;

import java.util.ResourceBundle;
/**
 * SqlManager is a singleton for reading database connection SQL commands file
 */
public final class SqlManager {
    /**
     * resourceBundleSQL is an instance of the ResourceBundle class
     */
    private final static ResourceBundle resourceBundleSQL = ResourceBundle.getBundle("com/epam/traffic/query");
    /**
     * constructor SqlManager() is the default constructor
     */
    private SqlManager() { }
    /**
     * method getQuery(String key) reads SQL queries from a configuration file
     * @param key is query
     * @return value of query
     */
    public static String getQuery(String key){
        return resourceBundleSQL.getString(key);
    }
}
