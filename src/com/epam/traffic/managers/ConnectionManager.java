package com.epam.traffic.managers;

import com.epam.traffic.logers.LogCreater;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * ConnectionManager is a class for working with a database
 */
public class ConnectionManager {
    /**
     * connection stores the database connection object
     */
    private static Connection connection = null;
    /**
     * logger is a logging object
     */
    private static Logger logger = LogCreater.getLogger(ConnectionManager.class);

    /**
     * In this block, the jdbc driver is dynamically loaded.
     */
    static {
            try {
                Class.forName(ConfigurationManager.getProperty("db.driver"));
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage());
            }
        }

    /**
     * method createConnection() creates a new connection if it is not created or returns the current
     * state if the connection is already created
     * @return connection status
     * @throws SQLException
     */
    public static Connection createConnection() throws SQLException {
            if (connection == null || connection.isClosed()) {
                try {
                    Properties connInfo = new Properties();
                    connInfo.put("user", "root");
                    connInfo.put("password", "123456");
                    connInfo.put("useUnicode","true");
                    connInfo.put("characterEncoding","utf-8");
                    connection = DriverManager.getConnection(ConfigurationManager.getProperty("db.url"), connInfo);
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
            return connection;
    }

    /**
     *  method closeConnection() closes the connection to the database server
     */
    public static void closeConnection() {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
    }

    /**
     * method closeStatement(Statement statement) closes the statement instance to work with the database
     * @param statement is an instance of the Statement class
     */
    public static void closeStatement(Statement statement) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
    }

    /**
     * method closeResultSet(ResultSet resultSet) closes an instance of the ResultSet class
     * @param resultSet is an instance of the ResultSet class
     */
    public static void closeResultSet(ResultSet resultSet) {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
    }
}
