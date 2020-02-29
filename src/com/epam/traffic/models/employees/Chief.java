package com.epam.traffic.models.employees;

import com.epam.traffic.abstracts.AbstractEmployee;
/**
 * Chief this is a class inherited from AbstractEmployee
 */
public class Chief extends AbstractEmployee {
    /**
     * constructor  Chief(String name, String surname, int age, int experience)
     * creates an object by the constructor of the parent class
     * @param name is the name of the employee
     * @param surname is the surname of the employee
     * @param age is the age of the employee
     * @param experience is the experience of the employee
     */
    public Chief(String name, String surname, int age, int experience) {
        super(name, surname, age, experience);
    }

    /**
     * constructor Chief() is the constructor for creating the default Chief class object
     */
    public Chief() {
    }
    /**
     * method getProfession() is an overridden class Chief method
     * @return
     */
    @Override
    public String getProfession() {
        return "chief";
    }
}
