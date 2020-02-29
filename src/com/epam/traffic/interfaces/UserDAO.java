package com.epam.traffic.interfaces;

import com.epam.traffic.models.User;

import java.util.List;

public interface UserDAO {
    /**
     * abstract method List<User> getAllUsers() is a method that retrieves data about all users
     * from the user table in the database
     * @return a list of users
     */
    List<User> getAllUsers();
    /**
     * abstract method isUserExist(User inputUser) is a method that checks for the presence of a user
     * in the list of users by login and password received from the database
     * @param inputUser object of the User class with which the comparison is made
     * @return an object of the User class from a list that matches the input object
     */
    User isUserExist(User inputUser);
}
