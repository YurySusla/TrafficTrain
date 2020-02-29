package com.epam.traffic.models;

import com.epam.traffic.enums.UserGroup;

/**
 * class User is the User class
 */
public class User {
    /**
     * userId is a variable that id records in the user table in the database
     */
    private int userId;
    /**
     * login is a variable that user login
     */
    private String login;
    /**
     * password is a variable that user password
     */
    private String password;
    /**
     * userGroup is a variable that user group
     */
    private UserGroup userGroup;

    /**
     * constructor User() is the constructor for objects like User by default
     */
    public User() {
    }

    /**
     * constructor User(String login, String password) is the constructor initializes
     * an object of class User in two parameters:
     * @param login is a variable that user login
     * @param password is a variable that user password
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * method getLogin() is getters for variable login
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * method setLogin(String login) is setters for variable login
     * @param login describes user login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * method getPassword() is getters for variable password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * method setPassword(String password) is setters for variable password
     * @param password describes user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * method getUserGroup() is getters for variable userGroup
     * @return userGroup
     */
    public UserGroup getUserGroup() {
        return userGroup;
    }

    /**
     * method setUserGroup(UserGroup userGroup) is setters for variable userGroup
     * @param userGroup describes user group
     */
    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    /**
     * method getUserId() is getters for variable userId
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * method setUserId(int userId) is setters for variable userId
     * @param userId describes id records in the user table in the database
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * method hashCode() defines a user hash code
     * @return hash code
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    /**
     * method equals(Object obj) compares with an input instance of type obj
     * @param obj is an instance of type User
     * @return a boolean comparison value
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        User otherUser = (User)obj;
        if(!login.equals(otherUser.getLogin()))
        {
            return false;
        }
        if(!password.equals(otherUser.getPassword())){
            return false;
        }
        return true;
    }
}
