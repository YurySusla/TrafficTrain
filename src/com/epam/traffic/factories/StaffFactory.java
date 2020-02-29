package com.epam.traffic.factories;

import com.epam.traffic.interfaces.StaffDAO;
import com.epam.traffic.logers.LogCreater;
import com.epam.traffic.managers.ConnectionManager;
import com.epam.traffic.managers.SqlManager;
import com.epam.traffic.models.Staff;
import org.apache.log4j.Logger;
import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
/**
 * StaffFactory it's DAO factory to get train personnel data
 */
public class StaffFactory implements StaffDAO {
    /**
     * staffs is a collection of Staff class objects
     */
    List<Staff> staffs;
    /**
     * connection stores the database connection object
     */
    Connection connection;
    /**
     * resultSet stores an object with the result of a database query
     */
    ResultSet resultSet = null;
    /**
     * query is a SQL database query
     */
    StringBuilder query = null;
    /**
     * logger is a logging object
     */
    private static Logger logger = LogCreater.getLogger(EmployeesFactory.class);

    /**
     * method getAllStaff() gets all objects of class Staff from the database
     * @return staff list
     */
    @Override
    public List<Staff> getAllStaff() {
        staffs = null;
        /**
         * statement instance of Statement to execute SQL queries
         */
        Statement statement = null;
        try {
            connection = ConnectionManager.createConnection();
            statement = connection.createStatement();
            query = new StringBuilder();
            query.append(SqlManager.getQuery("SELECT_ALL_STAFF"));
            query.append(SqlManager.getQuery("DELIMITER"));
            resultSet = statement.executeQuery(query.toString());
            staffs = getStaff(resultSet);
            while (staffs == null){
                ConnectionManager.closeResultSet(resultSet);
                ConnectionManager.closeStatement(statement);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query.toString());
                staffs = getStaff(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(statement);
            ConnectionManager.closeConnection();
        }
        return staffs;
    }

    /**
     * method addStaff(int trainId, int employeeId, int dayOfWeek) is a method that adds an entry
     * to the staff table in the database
     * @param trainId is the train identifier in the database
     * @param employeeId is the user id in the database
     * @param dayOfWeek is the serial number of the day of the week
     */
    @Override
    public void addStaff(int trainId, int employeeId, int dayOfWeek) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("ADD_STAFF"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, trainId);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.setInt(3, dayOfWeek);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection();
        }
    }

    /**
     * method removeStaff(int id) is a method that removes an entry from the staff table in the database
     * @param id is a variable storing id records in the staff table in the database
     */
    @Override
    public void removeStaff(int id) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("REMOVE_STAFF"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection();
        }
    }

    /**
     * method updateStaff(int trainId, int employeeId, int dayOfWeek, int id)
     * @param trainId is the train identifier in the database
     * @param employeeId is the user id in the database
     * @param dayOfWeek is the serial number of the day of the week
     * @param id is a variable storing id records in the staff table in the databas
     */
    @Override
    public void updateStaff(int trainId, int employeeId, int dayOfWeek, int id) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("UPDATE_STAFF"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, trainId);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.setInt(3, dayOfWeek);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection();
        }
    }

    /**
     * method getStaff(ResultSet resultSet) gets a collection of Staff objects
     * from a response from the database
     * @param resultSet stores an object with the result of a database query
     * @return collection of Staff objects
     * @throws SQLException
     */
    private List<Staff> getStaff(ResultSet resultSet) throws SQLException {
        /**
         * temp is a variable storing a list of staff objects
         */
        List<Staff> temp = new ArrayList<>();
        /**
         * staff
         */
        Staff staff = null;
        try{
            while (resultSet.next()) {
                /**
                 * trainId is a variable storing train id from the database
                 */
                int trainId = resultSet.getInt("train_id");
                /**
                 * id is a variable storing id records in the staff table in the database
                 */
                int id = resultSet.getInt("id_staff");
                /**
                 * employeeId is a variable storing employee id from the database
                 */
                int employeeId = resultSet.getInt("employee_id");
                /**
                 * day is a variable storing the ordinal number of the day of the week
                 * from the staff table in the database
                 */
                int day = resultSet.getInt("day");
                staff = new Staff(trainId, employeeId, DayOfWeek.of(day));
                staff.setStaffId(id);
                temp.add(staff);
            }
        } catch (NullPointerException e){
            logger.error(e.getMessage());
            temp = null;
        } finally {
            return temp;
        }
    }
}