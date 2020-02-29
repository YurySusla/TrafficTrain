package com.epam.traffic.interfaces;

import com.epam.traffic.enums.TrainType;
import com.epam.traffic.models.TimeTable;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;

/**
 * TimeTableDAO is the interface for the timetable
 */
public interface TimeTableDAO {
    /**
     * abstract method getAllTimeTable() gets all objects of class TimeTable train schedules from the database
     * @return collection of TimeTable class objects
     */
    List<TimeTable> getAllTimeTable();
    /**
     * abstract method getTimeTableByTrainType(TrainType trainType) gets all objects of class TimeTable train schedules
     * from the database by train type
     * @param trainType is a train type enumeration element
     * @return collection of TimeTable class objects
     */
    List<TimeTable> getTimeTableByTrainType(int trainType);
    /**
     * abstract method getTimeTableByTrainTypeAndStation(TrainType trainType, String station) gets all objects of class TimeTable
     * train schedules from the database according
     * to the type of trains passing through a particular station
     * @param trainType is a train type enumeration element
     * @param station is the name of the station
     * @return collection of TimeTable class objects
     */
    List<TimeTable> getTimeTableByTrainTypeAndStation(int trainType, int station);
    /**
     * abstract method addTimeTable(int trainId, int stationId, DayOfWeek dayOfWeek, Time arrivalTime, Time departureTime) is
     * a method that adds a new record to the train schedule table in the database
     * @param trainId is train id in the database
     * @param stationId is station id in the database
     * @param dayOfWeek is the serial number of the day of the week
     * @param arrivalTime is the train arrival time
     * @param departureTime is the train departure time
     */
    void addTimeTable(int trainId, int stationId, DayOfWeek dayOfWeek, Time arrivalTime, Time departureTime);
    /**
     * abstract method removeTimeTable(int id) is a method that removes an entry from the train schedule table
     * in the database by id
     * @param id is the identifier of the entry in the train schedule table
     */
    void removeTimeTable(int id);
    /**
     * abstract method updateTimeTable(int trainId, int stationId, DayOfWeek dayOfWeek, Time arrivalTime, Time departureTime,
     * int id) is a method that changes the value of the data in the record in the train schedule table by id
     * @param trainId is train id in the database
     * @param stationId is station id in the database
     * @param dayOfWeek is the serial number of the day of the week
     * @param arrivalTime is the train arrival time
     * @param departureTime is the train departure time
     * @param id is the identifier of the entry in the train schedule table
     */
    void updateTimeTable(int trainId, int stationId, DayOfWeek dayOfWeek, Time arrivalTime, Time departureTime, int id);
}
