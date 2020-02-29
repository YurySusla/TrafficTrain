package com.epam.traffic.factories;

import com.epam.traffic.enums.UserGroup;
import com.epam.traffic.interfaces.UserDAO;
import com.epam.traffic.logers.LogCreater;
import com.epam.traffic.managers.ConnectionManager;
import com.epam.traffic.managers.SqlManager;
import com.epam.traffic.models.User;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * UserFactory it's DAO factory to get user data
 */
public class UserFactory implements UserDAO {
    /**
     * users is a variable that stores the list of users from the user table in the database
     */
    List<User> users;
    /**
     * user is a temporary object of class User
     */
    User user;
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
     * method List<User> getAllUsers() is a method that retrieves data about all users
     * from the user table in the database
     * @return a list of users
     */
    @Override
    public List<User> getAllUsers() {
        users = new ArrayList<>();
        user = null;
        query = new StringBuilder();
        /**
         * statement instance of Statement to execute SQL queries
         */
        Statement statement = null;
        try {
            connection = ConnectionManager.createConnection();
            statement = connection.createStatement();
            query.append(SqlManager.getQuery("SELECT_ALL_USERS"));
            query.append(SqlManager.getQuery("DELIMITER"));
            resultSet = statement.executeQuery(query.toString());
            while(resultSet.next()){
                /**
                 * login is a variable that stores the user login received from the database
                 */
                String login = resultSet.getString("login");
                /**
                 * password is a variable that stores the user password received from the database
                 */
                String password = resultSet.getString("password");
                /**
                 * role is a variable that stores the user role received from the database
                 */
                int role = resultSet.getInt("role");
                /**
                 * id is a variable that stores the user id received from the database
                 */
                int id = resultSet.getInt("iduser");
                /**
                 * userGroup is a user group enumeration object
                 */
                UserGroup userGroup = getUserGroup(role);
                user = new User(login, password);
                user.setUserGroup(userGroup);
                user.setUserId(id);
                users.add(user);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(statement);
            ConnectionManager.closeConnection();
        }
        return users;
    }

    /**
     * method isUserExist(User inputUser) is a method that checks for the presence of a user
     * in the list of users by login and password received from the database
     * @param inputUser object of the User class with which the comparison is made
     * @return an object of the User class from a list that matches the input object
     */
    @Override
    public User isUserExist(User inputUser) {
        List<User> users = getAllUsers();
        User tempUser = null;
        for (User user: users)  {
            if(inputUser.equals(user)){
                tempUser = user;
            }
        }
        return tempUser;
    }

    /**
     * method getUserGroup(int role) is a method that determines which group the user belongs to.
     * @param role is the numerical value of the group from the user table in the database
     * @return user group
     */
    private UserGroup getUserGroup(int role){
            UserGroup userGroup = null;
            switch (role){
                case 1: userGroup = UserGroup.HR;
                    break;
                case 2: userGroup = UserGroup.DISPATCHER;
                    break;
                case 3: userGroup = UserGroup.REFERENCE_CREATOR;
                    break;
            }
        return userGroup;
    }
}
