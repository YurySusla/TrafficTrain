package com.epam.traffic.models.employees;

import com.epam.traffic.abstracts.AbstractEmployee;

/**
 * AssistantDriver this is a class inherited from AbstractEmployee
 */
public class AssistantDriver extends AbstractEmployee {
    /**
     * constructor  AssistantDriver(String name, String surname, int age, int experience)
     * creates an object by the constructor of the parent class
     * @param name is the name of the employee
     * @param surname is the surname of the employee
     * @param age is the age of the employee
     * @param experience is the experience of the employee
     */
    public AssistantDriver(String name, String surname, int age, int experience) {
        super(name, surname, age, experience);
    }

    /**
     * constructor AssistantDriver() is the constructor for creating the default AssistantDriver class object
     */
    public AssistantDriver() {
    }

    /**
     * method getProfession() is an overridden class AssistantDriver method
     * @return
     */
    @Override
    public String getProfession() {
        return "assistant";
    }
}
