package com.epam.traffic.models.stations;

import com.epam.traffic.abstracts.AbstractStation;
/**
 * TrainStop this is a class inherited from AbstractStation
 */
public class TrainStop extends AbstractStation {
    /**
     * constructor  TrainStop(String name, int waitingHall)
     * creates an object by the constructor of the parent class
     * @param name is the name of the station
     * @param waitingHall indicates whether there is a waiting room
     */
    public TrainStop(String name, int waitingHall) {
        super(name, waitingHall);
    }
    /**
     * constructor TrainStop() is the constructor for creating the default TrainStop class object
     */
    public TrainStop() {
    }
    /**
     * method getTypeOfStation() is an overridden class TrainStop method
     * @return type of Station as a string
     */
    @Override
    public String getTypeOfStation() {
        return "train stop";
    }
}
