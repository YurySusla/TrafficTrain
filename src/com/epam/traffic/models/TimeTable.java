package com.epam.traffic.models;

import java.sql.Time;
import java.time.DayOfWeek;

/**
 * TimeTable is the train schedule class
 */
public class TimeTable {
    /**
     * timeTableId is a variable that stores id records in the database schedule table
     */
    private int timeTableId;
    /**
     * trainId is a variable that stores id records in the database train
     */
    private int trainId;
    /**
     * stationId is a variable that stores id records in the database station list
     */
    private int stationId;
    /**
     * dayOfWeek is a variable that stores the day ordinal of the week
     */
    private DayOfWeek dayOfWeek;
    /**
     * arrivalTime is a variable that stores the arrival time
     */
    private Time arrivalTime;
    /**
     * departureTime is a variable that stores departure time
     */
    private Time departureTime;

    /**
     * constructor TimeTable() is the constructor for objects like TimeTable by default
     */
    public TimeTable() {
    }

    /**
     * method getDayOfWeek() is getters for variable dayOfWeek
     * @return dayOfWeek the day ordinal of the week
     */
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * method setStationId(int stationId) is setters for variable stationId
     * @param dayOfWeek describes the day ordinal of the week
     */
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * constructor TimeTable(int trainId, int idStation, Time arrivalTime, Time departureTime, DayOfWeek dayOfWeek)
     * is the constructor initializes an object of class TimeTable in five parameters:
     * @param trainId trainId is a variable that stores id records in the database train
     * @param stationId is a variable that stores id records in the database station list
     * @param arrivalTime is a variable that stores the arrival time
     * @param departureTime is a variable that stores departure time
     * @param dayOfWeek is a variable that stores the day ordinal of the week
     */
    public TimeTable(int trainId, int stationId, Time arrivalTime, Time departureTime, DayOfWeek dayOfWeek) {
        this.trainId = trainId;
        this.stationId = stationId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * method getTimeTableId() is getters for variable timeTableId
     * @return timeTableId
     */
    public int getTimeTableId() {
        return timeTableId;
    }

    /**
     * method setTimeTableId(int timeTableId) is setters for variable timeTableId
     * @param timeTableId describes id records in the database schedule table
     */
    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
    }

    /**
     * method getTrainId() is getters for variable trainId
     * @return trainId
     */
    public int getTrainId() {
        return trainId;
    }

    /**
     * method setTrainId(int trainId) is setters for variable trainId
     * @param trainId describes id records in the database train
     */
    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }
    /**
     * method getStationId() is getters for variable stationId
     * @return stationId
     */
    public int getStationId() {
        return stationId;
    }

    /**
     * method setStationId(int stationId) is setters for variable stationId
     * @param stationId describes id records in the database station list
     */
    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    /**
     * method getArrivalTime() is getters for variable arrivalTime
     * @return arrivalTime
     */
    public Time getArrivalTime() {
        return arrivalTime;
    }

    /**
     * method setArrivalTime(Time arrivalTime) is setters for variable arrivalTime
     * @param arrivalTime describes arrival time
     */
    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * method getDepartureTime() is getters for variable departureTime
     * @return departureTime
     */
    public Time getDepartureTime() {
        return departureTime;
    }

    /**
     * method setDepartureTime(Time departureTime) is setters for variable departureTime
     * @param departureTime describes departure time
     */
    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }
}
