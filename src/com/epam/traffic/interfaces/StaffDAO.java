package com.epam.traffic.interfaces;

import com.epam.traffic.models.Staff;

import java.time.DayOfWeek;
import java.util.List;

/**
 * StaffDAO is the DAO interface for train personnel
 */
public interface StaffDAO {
    /**
     * abstract method getAllStaff() gets all objects of class Staff from the database
     * @return staff list
     */
    List<Staff> getAllStaff();
    /**
     * abstract method addStaff(int trainId, int employeeId, int dayOfWeek) is a method that adds an entry
     * to the staff table in the database
     * @param trainId is the train identifier in the database
     * @param employeeId is the user id in the database
     * @param dayOfWeek is the serial number of the day of the week
     */
    void addStaff(int trainId, int employeeId, int dayOfWeek);
    /**
     * abstract method removeStaff(int id) is a method that removes an entry from the staff table in the database
     * @param id is a variable storing id records in the staff table in the database
     */
    void removeStaff(int id);
    /**
     * abstract method updateStaff(int trainId, int employeeId, int dayOfWeek, int id)
     * @param trainId is the train identifier in the database
     * @param employeeId is the user id in the database
     * @param dayOfWeek is the serial number of the day of the week
     * @param id is a variable storing id records in the staff table in the databas
     */
    void updateStaff(int trainId, int employeeId, int dayOfWeek, int id);
}
