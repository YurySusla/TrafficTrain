package com.epam.traffic.enums;

/**
 * UserGroup is a list of user groups
 */
public enum UserGroup {
    /**
     * valueOfUserGroup stores position in enum
     */
    HR(1), DISPATCHER(2), REFERENCE_CREATOR(3);
    private int valueOfUserGroup;

    /**
     * UserGroup(int valueOfUserGroup) assigns a variable a specific value from enum
     * @param valueOfUserGroup stores position in enum
     */
    UserGroup(int valueOfUserGroup) {
        this.valueOfUserGroup = valueOfUserGroup;
    }

    /**
     * method getValue() returns the position value of an element from enum
     * @return position in enum
     */
    public int getValue() {
        return this.valueOfUserGroup;
    }
}
