package com.epam.traffic.logers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * LogCreater is a class for connecting logging
 */
public class LogCreater {
    /**
     * This is the logging configuration block
     */
    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    /**
     * method getLogger(Class className) connects logging in a specific class
     * @param className is the class in which logging is connected
     * @return Logger
     */
    public static Logger getLogger(Class className) {
        return Logger.getLogger(className);
    }
}
