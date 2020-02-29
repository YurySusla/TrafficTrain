package com.epam.traffic.abstracts;
/**
 * This is an abstract class for a Station class.
 */
public abstract class AbstractStation {
    /**
     * stationId describes the station id
     */
    private int stationId;
    /**
     * name describes the name of the station
     */
    private String name;
    /**
     * waitingHall indicates whether there is a waiting room at the station
     */
    private boolean waitingHall;
    /**
     * AbstractStation() is the default constructor
     */
    public AbstractStation() {
    }
    /**
     * AbstractStation(String name, int waitingHall) is
     * the constructor initializes an object of class Station in two parameters:
     * @param name describes the name of the station
     * @param waitingHall indicates whether there is a waiting room at the station
     */

    public AbstractStation(String name, int waitingHall) {
        this.name = name;
        setWaitingHall(waitingHall);
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
     * @param stationId describes the station id
     */
    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
    /**
     * method getName() is getters for variable name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * setName(String name) is setters for variable name
     * @param name describes the name of the station
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * isWaitingHall() is getters for variable waitingHall
     * @return waitingHall
     */
    public boolean isWaitingHall() {
        return this.waitingHall;
    }
    /**
     * setWaitingHall(int waitingHall) is setters for variable age
     * @param waitingHall indicates whether there is a waiting room at the station
     */
    public void setWaitingHall(int waitingHall) {
        if(waitingHall > 0){
            this.waitingHall = true;
        }
        else{
            this.waitingHall = false;
        }
    }
    /**
     * getTypeOfStation() is an abstract method returning the type of station
     * @return the type of station
     */
    public abstract String getTypeOfStation();
    /**
     * toString() used to get a string representation of an AbstractStation object
     * @return a string
     */
    @Override
    public String toString() {
        return "name=" + name + ';' + "waitingHall=" + waitingHall + ';' + getTypeOfStation();
    }
}
