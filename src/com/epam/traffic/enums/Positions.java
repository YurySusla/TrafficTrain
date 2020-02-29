package com.epam.traffic.enums;

/**
 * Positions is a listing of the professions of train workers
 */
public enum Positions {
    DRIVER(1),ASSISTANT(2),CHIEF(3),CONDUCTOR(4);
    /**
     * valueOfPosition stores position in enum
     */
    private int valueOfPosition;

    /**
     * constructor Positions(int valueOfPosition) assigns a variable a specific value from enum
     * @param valueOfPosition stores position in enum
     */
    Positions(int valueOfPosition) {
        this.valueOfPosition = valueOfPosition;
    }

    /**
     * method getValue() returns the position value of an element from enum
     * @return position in enum
     */
    public int getValue(){
        return this.valueOfPosition;
    }
}
