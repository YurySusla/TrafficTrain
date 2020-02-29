package com.epam.traffic.models.trains;

import com.epam.traffic.abstracts.AbstractTrain;
/**
 * SuburbanTrain this is a class inherited from AbstractTrain
 */
public class SuburbanTrain extends AbstractTrain {
    /**
     * constructor  SuburbanTrain(String number, int distance, int isBrandedTrain)
     * creates an object by the constructor of the parent class
     * @param number is the number of the train
     * @param distance is the distance between stations
     * @param isBrandedTrain indicates if the train is branded
     */
    public SuburbanTrain(String number, int distance, int isBrandedTrain) {
        super(number, distance, isBrandedTrain);
    }
    /**
     * constructor SuburbanTrain() is the constructor for creating the default SuburbanTrain class object
     */
    public SuburbanTrain(){
        super();
    }
    /**
     * method getTypeOfTrain() is an overridden class SuburbanTrain method
     * @return type of Train as a string
     */
    @Override
    public String getTypeOfTrain() {
        return "suburban";
    }
}
