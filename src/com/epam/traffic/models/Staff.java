package com.epam.traffic.models;

import java.time.DayOfWeek;

/**
 * Staff is the train staff class
 */
public class Staff {
    /**
     * staffId is a variable storing id records in the staff table in the database
     */
    private int staffId;
    /**
     * trainId is a variable storing train id in the database
     */
    private int trainId;
    /**
     * employeeId is a variable storing employee id in the database
     */
    private int employeeId;
    /**
     * dayOfWeek is a variable storing the day of the week
     */
    private DayOfWeek dayOfWeek;

    /**
     * constructor Staff() is the default Staff class constructor
     */
    public Staff() {
    }

    /**
     * constructor Staff(int trainId, int employeeId, DayOfWeek dayOfWeek) is the constructor of the Staff class,
     * initializing the object with 3 parameters:
     * @param trainId is a variable storing train id in the database
     * @param employeeId is a variable storing employee id in the database
     * @param dayOfWeek is a variable storing the day of the week
     */
    public Staff(int trainId, int employeeId, DayOfWeek dayOfWeek) {
        this.trainId = trainId;
        this.employeeId = employeeId;
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * method getStaffId() is is getters for variable staffId
     * @return staffId
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * method setStaffId(int staffId) is setters for variable staffId
     * @param staffId
     */
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    /**
     * method getTrainId() is is getters for variable trainId
     * @return trainId
     */
    public int getTrainId() {
        return trainId;
    }

    /**
     * method setTrainId(int trainId) is setters for variable trainId
     * @param trainId
     */
    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    /**
     * method getEmployeeId() is getters for variable employeeId
     * @return employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * method setEmployeeId(int employeeId) is setters for variable employeeId
     * @param employeeId
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * method getDayOfWeek() is getters for variable dayOfWeek
     * @return
     */
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * method setDayOfWeek(DayOfWeek dayOfWeek) is setters for variable dayOfWeek
     * @param dayOfWeek
     */
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
