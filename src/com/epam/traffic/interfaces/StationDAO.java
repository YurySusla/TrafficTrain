package com.epam.traffic.interfaces;

import com.epam.traffic.abstracts.AbstractStation;
import java.util.List;

/**
 * StationDAO is the DAO interface for stations
 */
public interface StationDAO {
    /**
     * abstract method getAllStations() gets all children of AbstractStation class from the database
     * @return collection of children of the AbstractStation class
     */
    List<AbstractStation> getAllStations();
    /**
     * abstract method removeStation(int id) is a method that deletes a record from the station table in the database
     * @param id is id in the station table in the database
     */
    void removeStation(int id);
    /**
     * abstract method addStation(String name, String stationType, String isWaitingHall) is a method that adds an entry
     * to the station table in the database
     * @param name is a variable storing the name of the station
     * @param stationType is a variable storing the type of station
     * @param isWaitingHall is a variable storing a hall waiting sign at the station
     */
    void addStation(String name, String stationType, String isWaitingHall);
    /**
     * abstract method updateStation(String name, String stationType, String isWaitingHall, int id) is a method
     * that updates the record data in the train table by id
     * @param name is a variable storing the name of the station
     * @param stationType is a variable storing the type of station
     * @param isWaitingHall is a variable storing a hall waiting sign at the station
     * @param id is id in the station table in the database
     */
    void updateStation(String name, String stationType, String isWaitingHall, int id);
}
