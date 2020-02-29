package com.epam.traffic.models.trains;

import com.epam.traffic.abstracts.AbstractTrain;

/**
 * LongDistanceTrain this is a class inherited from AbstractTrain
 */
public class LongDistanceTrain extends AbstractTrain {
    /**
     * constructor  LongDistanceTrain(String number, int distance, int isBrandedTrain)
     * creates an object by the constructor of the parent class
     * @param number is the number of the train
     * @param distance is the distance between stations
     * @param isBrandedTrain indicates if the train is branded
     */
    public LongDistanceTrain(String number, int distance, int isBrandedTrain) {
        super(number, distance, isBrandedTrain);
    }
    /**
     * constructor LongDistanceTrain() is the constructor for creating the default LongDistanceTrain class object
     */
    public LongDistanceTrain() {
    }
    /**
     * method getTypeOfTrain() is an overridden class LongDistanceTrain method
     * @return type of Train as a string
     */
    @Override
    public String getTypeOfTrain() {
        return "long-distance";
    }
}
