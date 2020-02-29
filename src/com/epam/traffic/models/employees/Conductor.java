package com.epam.traffic.models.employees;

import com.epam.traffic.abstracts.AbstractEmployee;
/**
 * Conductor this is a class inherited from AbstractEmployee
 */
public class Conductor extends AbstractEmployee {
    /**
     * constructor  Conductor(String name, String surname, int age, int experience)
     * creates an object by the constructor of the parent class
     * @param name is the name of the employee
     * @param surname is the surname of the employee
     * @param age is the age of the employee
     * @param experience is the experience of the employee
     */
    public Conductor(String name, String surname, int age, int experience) {
        super(name, surname, age, experience);
    }
    /**
     * constructor Conductor() is the constructor for creating the default Conductor class object
     */
    public Conductor() {
    }
    /**
     * method getProfession() is an overridden class Conductor method
     * @return
     */
    @Override
    public String getProfession() {
        return "conductor";
    }
}
