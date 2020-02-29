package com.epam.traffic.models.employees;

import com.epam.traffic.abstracts.AbstractEmployee;
/**
 * Driver this is a class inherited from AbstractEmployee
 */
public class Driver extends AbstractEmployee {
    /**
     * constructor  Driver(String name, String surname, int age, int experience)
     * creates an object by the constructor of the parent class
     * @param name is the name of the employee
     * @param surname is the surname of the employee
     * @param age is the age of the employee
     * @param experience is the experience of the employee
     */
    public Driver(String name, String surname, int age, int experience) {
        super(name, surname, age, experience);
    }
    /**
     * constructor Driver() is the constructor for creating the default Driver class object
     */
    public Driver() {
    }
    /**
     * method getProfession() is an overridden class Driver method
     * @return
     */
    @Override
    public String getProfession() {
        return "driver";
    }
}
