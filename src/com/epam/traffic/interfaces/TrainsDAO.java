package com.epam.traffic.interfaces;

import com.epam.traffic.abstracts.AbstractTrain;
import java.sql.Time;
import java.util.List;

/**
 * TrainsDAO is the DAO interface for trains
 */
public interface TrainsDAO {
    /**
     * abstract method getAllTrains() gets all children of AbstractTrain class from the database
     * @return collection of children of the AbstractTrain class
     */
    List<AbstractTrain> getAllTrains();
    /**
     * abstract method getAllTrainsByStationAndTime(String station, Time begin, Time end) gets all children of AbstractTrain class
     * from the database departing from a specific station in a certain period of time
     * @param station is the name of the station
     * @param begin is the beginning of a period of time
     * @param end is the end of a period of time
     * @return collection of children of the AbstractTrain class
     */
    List<AbstractTrain> getAllTrainsByStationAndTime(int station, Time begin, Time end);
    /**
     * abstract method removeTrain(int id) is a method that deletes an entry from the train table in the database by id
     * @param id is the train identifier in the database
     */
    void removeTrain(int id);
    /**
     * abstract method updateTrain(String number, String type, String isBrandedTrain, int distance, int id) this is a method
     * that changes the train data in the train table of the database by id
     * @param number is the train number
     * @param type s a type of train
     * @param isBrandedTrain is a sign that the train is branded
     * @param distance is the distance between the start and end stations
     * @param id is the train identifier in the database
     */
    void updateTrain(String number, String type, String isBrandedTrain, int distance, int id);
    /**
     * abstract method addTrain(String number, String type, String isBraindedTrain, int distance) this is a method
     * that adds an entry to the train table in the database
     * @param number is the train number
     * @param type s a type of train
     * @param isBrandedTrain is a sign that the train is branded
     * @param distance is the distance between the start and end stations
     */
    void addTrain(String number, String type, String isBrandedTrain, int distance);
}
