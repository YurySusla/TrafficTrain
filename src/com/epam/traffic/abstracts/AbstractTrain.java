package com.epam.traffic.abstracts;

import java.sql.Time;

/**
 * This is an abstract class for a Train class.
 */

public abstract class AbstractTrain {
    /**
     * trainId describes the train id
     */
    private int trainId;
    /**
     * number describes the number of the station
     */
    private String number;
    /**
     * distance describes the distance between stations
     */
    private int distance;
    /**
     * brandedTrain indicates if the train is branded
     */
    private boolean brandedTrain;
    /**
     * arrivalTime stores arrival time
     */
    private Time arrivalTime;
    /**
     * arrivalStation stores arrival station
     */
    private String arrivalStation;
    /**
     * departureTime stores departure time
     */
    private Time departureTime;
    /**
     * departureStation stores departure station
     */
    private String departureStation;

    /**
     * AbstractTrain(String number, int distance, int isBrandedTrain) is
     * the constructor initializes an object of class Station in two parameters:
     * @param number describes the number of the station
     * @param distance describes the distance between stations
     * @param isBrandedTrain indicates if the train is branded
     */
    public AbstractTrain(String number, int distance, int isBrandedTrain) {
        this.number = number;
        this.distance = distance;
        setBrandedTrain(isBrandedTrain);
    }
    /**
     * AbstractTrain() initializes the default object
     */
    public AbstractTrain() {
    }
    /**
     * method getNumber() is getters for variable number
     * @return number
     */
    public String getNumber() {
        return number;
    }
    /**
     * method setNumber(String number) is setters for variable number
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }
    /**
     * method getDistance() is getters for variable distance
     * @return distance
     */
    public int getDistance() {
        return distance;
    }
    /**
     * method setDistance(int distance) is setters for variable distance
     * @param distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }
    /**
     * method isBrandedTrain() is getters for variable brandedTrain
     * @return brandedTrain
     */
    public boolean isBrandedTrain() {
        return brandedTrain;
    }
    /**
     * method setBrandedTrain(int isBrandedTrain) is setters for variable isBrandedTrain
     * @param isBrandedTrain indicates if the train is branded
     */
    public void setBrandedTrain(int isBrandedTrain) {
        if(isBrandedTrain > 0){
            this.brandedTrain = true;
        }
        else{
            this.brandedTrain = false;
        }
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
     * @param trainId describes the number of the station
     */
    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    /**
     * method isBrandedTrain() is getters for variable brandedTrain
     * @return brandedTrain
     */
    public Time getArrivalTime() {
        return arrivalTime;
    }
    /**
     * method setArrivalTime(Time arrivalTime) is setters for variable arrivalTime
     * @param arrivalTime
     */
    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    /**
     * method getArrivalStation() is getters for variable arrivalStation
     * @return arrivalStation
     */
    public String getArrivalStation() {
        return arrivalStation;
    }
    /**
     * method setArrivalStation(String arrivalStation) is setters for variable arrivalStation
     * @param arrivalStation
     */
    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
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
     * @param departureTime
     */
    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }
    /**
     * method getDepartureStation() is getters for variable departureStation
     * @return departureStation
     */
    public String getDepartureStation() {
        return departureStation;
    }
    /**
     * method setDepartureStation(String departureStation) is setters for variable departureStation
     * @param departureStation
     */
    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    /**
     * abstract method getTypeOfTrain() is an abstract method that determines the type of train
     * @return the type of train
     */
    public abstract String getTypeOfTrain();
    /**
     * toString() used to get a string representation of an AbstractTrain object
     * @return a string
     */
    @Override
    public String toString() {
        return "number=" + number + ';' + "distance=" + distance +
                "brandedTrain=" + brandedTrain + ";" +
                "arrivalTime=" + arrivalTime + ';' +
                "arrivalStation=" + arrivalStation + ';' +
                "departureTime=" + departureTime + ';' +
                "departureStation=" + departureStation + ';' + getTypeOfTrain();
    }
}