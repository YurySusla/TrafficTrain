package com.epam.traffic.factories;

import com.epam.traffic.abstracts.AbstractEmployee;
import com.epam.traffic.enums.Positions;
import com.epam.traffic.interfaces.EmployeesDAO;
import com.epam.traffic.logers.LogCreater;
import com.epam.traffic.managers.SqlManager;
import com.epam.traffic.managers.ConnectionManager;
import com.epam.traffic.models.employees.AssistantDriver;
import com.epam.traffic.models.employees.Chief;
import com.epam.traffic.models.employees.Conductor;
import com.epam.traffic.models.employees.Driver;
import org.apache.log4j.Logger;
import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeesFactory it's DAO factory for getting employee data from a database
 */
public class EmployeesFactory implements EmployeesDAO {
    /**
     * employees is a collection of children of the AbstractEmployee class.
     */
    List<AbstractEmployee> employees;
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
     * method getAllEmployees() gets all employees from the database
     * @return collection of children of the AbstractEmployee class
     */
    @Override
    public List<AbstractEmployee> getAllEmployees()
    {
        employees = null;
        query = new StringBuilder();
        /**
         * statement instance of Statement to execute SQL queries
         */
        Statement statement = null;
        try {
            connection = ConnectionManager.createConnection();
            statement = connection.createStatement();
            query.append(SqlManager.getQuery("SELECT_EMPLOYEE"));
            query.append(SqlManager.getQuery("DELIMITER"));
            resultSet = statement.executeQuery(query.toString());
            employees = getEmployees(resultSet);
            while(employees == null){
                ConnectionManager.closeResultSet(resultSet);
                ConnectionManager.closeStatement(statement);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query.toString());
                employees = getEmployees(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(statement);
            ConnectionManager.closeConnection();
        }
       return employees;
    }

    /**
     * method getEmployeeByPosition(Positions position) finds all employees of a certain profession
     * @param position this is an element of recounting Positions
     * @return collection of children of the AbstractEmployee class
     */
    @Override
    public List<AbstractEmployee> getEmployeeByPosition(int position) {
        employees = null;
        query = new StringBuilder();
        /**
         * statement instance of PreparedStatement to execute SQL queries
         */
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("SELECT_EMPLOYEE"));
            query.append(SqlManager.getQuery("SELECT_EMPLOYEE_DRIVERS"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1,position);
            resultSet = preparedStatement.executeQuery();
            employees = getEmployees(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection();
        }
        return employees;
    }

    /**
     * method getEmployeeByTrainAndDay(String train, DayOfWeek dayOfWeek) finds all employees of a particular train
     * on a specific day
     * @param train is the train number
     * @param dayOfWeek is a day of the week
     * @return collection of children of the AbstractEmployee class
     */
    @Override
    public List<AbstractEmployee> getEmployeeByTrainAndDay(int train, DayOfWeek dayOfWeek) {
        employees = null;
        query = new StringBuilder();
        /**
         * day is a variable storing the value of the day of the week
         */
        int day = dayOfWeek.getValue();
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("SELECT_EMPLOYEE"));
            query.append(SqlManager.getQuery("SELECT_EMPLOYEE_TRAIN"));
            query.append(SqlManager.getQuery("SELECT_EMPLOYEE_DAY"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, day);
            preparedStatement.setInt(2,train);
            resultSet = preparedStatement.executeQuery();
            employees = getEmployees(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection();
        }
        return employees;
    }

    /**
     * method addEmployee(String name, String surname, int age, int experience, String profession) is a method
     * that creates an entry in the table of employees according
     * to the input parameters: name, surname, age, length of service and position
     * @param name is a variable storing the name of the employee
     * @param surname this is a variable storing the surname of the employee
     * @param age this is a variable storing the age of the employee
     * @param experience this is a variable storing the experience of the employee
     * @param profession this is a variable storing the profession of the employee
     */
    @Override
    public void addEmployee(String name, String surname, int age, int experience, String profession) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("ADD_EMPLOYEE_TO_EMPLOYEES"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, experience);
            switch (profession){
                case "assistant": preparedStatement.setInt(5,2);
                    break;
                case "chief": preparedStatement.setInt(5,3);
                    break;
                case "conductor": preparedStatement.setInt(5,4);
                    break;
                case "driver": preparedStatement.setInt(5,1);
                    break;
            }
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
     * method removeEmployee(int id) is a method that removes an employee record from a table in a database by id
     * @param id is the employee id in the database
     */
    @Override
    public void removeEmployee(int id) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("REMOVE_EMPLOYEE_FROM_EMPLOYEES"));
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
     * method updateEmployee(String name, String surname, int age, int experience, String profession, int id)
     * is a method that modifies employee data in a database
     * @param name is a variable storing the name of the employee
     * @param surname this is a variable storing the surname of the employee
     * @param age this is a variable storing the age of the employee
     * @param experience this is a variable storing the experience of the employee
     * @param profession this is a variable storing the profession of the employee
     * @param id is the employee id in the database
     */
    @Override
    public void updateEmployee(String name, String surname, int age, int experience, String profession, int id) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("UPDATE_EMPLOYEE_BY_ID"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, experience);
            switch (profession){
                case "assistant": preparedStatement.setInt(5,2);
                    break;
                case "chief": preparedStatement.setInt(5,3);
                    break;
                case "conductor": preparedStatement.setInt(5,4);
                    break;
                case "driver": preparedStatement.setInt(5,1);
                    break;
            }
            preparedStatement.setInt(6, id);
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
     * method getEmployees(ResultSet resultSet) gets a collection of AbstractEmployee objects
     * from a response from the database
     * @param resultSet stores an object with the result of a database query
     * @return collection of children of the AbstractEmployee class
     * @throws SQLException
     */
    private List<AbstractEmployee> getEmployees(ResultSet resultSet) throws SQLException {
        List<AbstractEmployee> temp = new ArrayList<>();
            AbstractEmployee employee = null;
            try {
                while(resultSet.next()){
                    /**
                     * name is a variable storing the name of the employee whose value is obtained from the database
                     */
                    String name = resultSet.getString("nameOfEmployee");
                    /**
                     * surname is a variable storing the surname of the employee whose value is obtained from the database
                     */
                    String surname = resultSet.getString("surname");
                    /**
                     * age is a variable storing the age of the employee whose value is obtained from the database
                     */
                    int age = resultSet.getInt("age");
                    /**
                     * experience is a variable storing the experience of the employee
                     * whose value is obtained from the database
                     */
                    int experience = resultSet.getInt("experience");
                    switch (resultSet.getInt("position")){
                        case 1: employee = new Driver(name, surname, age, experience);
                            break;
                        case 2: employee = new AssistantDriver(name, surname, age, experience);
                            break;
                        case 3: employee = new Chief(name, surname, age, experience);
                            break;
                        case 4: employee = new Conductor(name, surname, age, experience);
                            break;
                    }
                    /**
                     * id is a variable storing the id of the employee whose value is obtained from the database
                     */
                    int id = resultSet.getInt("idemployee");
                    employee.setEmployeeId(id);
                    temp.add(employee);
                }
            } catch (NullPointerException e){
                logger.error(e.getMessage());
                temp = null;
            } finally {
                return temp;
        }
    }
}
