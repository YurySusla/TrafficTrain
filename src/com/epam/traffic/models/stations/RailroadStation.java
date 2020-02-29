package com.epam.traffic.models.stations;

import com.epam.traffic.abstracts.AbstractStation;

/**
 * RailroadStation this is a class inherited from AbstractStation
 */
public class RailroadStation extends AbstractStation {
    /**
     * constructor  RailroadStation(String name, int waitingHall)
     * creates an object by the constructor of the parent class
     * @param name is the name of the station
     * @param waitingHall indicates whether there is a waiting room
     */
    public RailroadStation(String name, int waitingHall) {
        super(name, waitingHall);
    }
    /**
     * constructor RailroadStation() is the constructor for creating the default RailroadStation class object
     */
    public RailroadStation() {
    }
    /**
     * method getTypeOfStation() is an overridden class RailroadStation method
     * @return type of Station as a string
     */
    @Override
    public String getTypeOfStation() {
        return "railroad station";
    }
}
