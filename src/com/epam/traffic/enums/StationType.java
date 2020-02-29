package com.epam.traffic.enums;
/**
 * StationType is a listing the type of trains
 */
public enum StationType {
    RAILROAD_STATION(1), TRAIN_STOP(0);
    /**
     * valueOfType stores position in enum
     */
    private int valueOfType;
    /**
     * constructor StationType(int valueOfType) assigns a variable a specific value from enum
     * @param valueOfType stores position in enum
     */
    StationType(int valueOfType) {
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
