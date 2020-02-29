package com.epam.traffic.enums;
/**
 * TrainType is a listing the type of trains
 */
public enum TrainType {
    LONG_DISTANCE(1),SUBURBAN(0);
    /**
     * valueOfType stores position in enum
     */
    private int valueOfType;
    /**
     * constructor TrainType(int valueOfType) assigns a variable a specific value from enum
     * @param valueOfType stores position in enum
     */
    TrainType(int valueOfType) {
        this.valueOfType = valueOfType;
    }
    /**
     * method getValue() returns the position value of an element from enum
     * @return position in enum
     */
    public int getValue(){
        return this.valueOfType;
    }
}
