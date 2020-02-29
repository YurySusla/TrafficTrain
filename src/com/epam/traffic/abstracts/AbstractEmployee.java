package com.epam.traffic.abstracts;

/**
 * This is an abstract class for a Employee class.
 */

public abstract class AbstractEmployee {
    /**
     *  employeeId describes the employee id
     */
    private int employeeId;
    /**
     * name describes the name of the employee
     */
    private String name;
    /**
     * surname describes the surname of the employee
     */
    private String surname;
    /**
     * age describes the age of the employee
     */
    private int age;
    /**
     * experience describes the experience of the employee
     */
    private int experience;

    /**
     * AbstractEmployee(String name, String surname, int age, int experience) is
     * the constructor initializes an object of class Employee in four parameters:
     * @param name describes the name of the employee
     * @param surname describes the surname of the employee
     * @param age describes the age of the employee
     * @param experience describes the experience of the employee
     */
    public AbstractEmployee(String name, String surname, int age, int experience) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.experience = experience;
    }

    /**
     * AbstractEmployee() is the default constructor
     */
    public AbstractEmployee() {
    }
    /**
     * method getEmployeeId() is getters for variable employeeId
     * @return employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }
    /**
     * setEmployeeId(int employeeId) is setters for variable employeeId
     * @param employeeId describes the employee id
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * method getName() is getters for variable name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setName(String name) is setters for variable name
     * @param name describes the name of the employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getSurname() is getters for variable surname
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * setSurname(String surname) is setters for variable surname
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * getAge() is getters for variable age
     * @return age
     */
    public int getAge() {
        return age;
    }
    /**
     * setAge(int age) is setters for variable age
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * getExperience() is getters for variable experience
     * @return experience
     */
    public int getExperience() {
        return experience;
    }
    /**
     * setExperience(int experience) is setters for variable experience
     * @param experience
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * getProfession() method is an abstract method for describing a profession
     * @return returns a description of the profession
     */
    public abstract String getProfession();

    /**
     * toString() used to get a string representation of an AbstractEmployee object
     * @return a string
     */
    @Override
    public String toString() {
        return "name=" + name + ';' +
                "surname=" + surname + ';' +
                "age=" + age + ';' +
                "experience=" + experience  + ';' + getProfession();
    }
}
