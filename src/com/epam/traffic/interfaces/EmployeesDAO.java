package com.epam.traffic.interfaces;

import com.epam.traffic.abstracts.AbstractEmployee;
import com.epam.traffic.enums.Positions;
import java.time.DayOfWeek;
import java.util.List;

/**
 * EmployeesDAO is an DAO interface for employees
 */
public interface EmployeesDAO {
    /**
     * abstract method getAllEmployees() gets all employees from the database
     * @return collection of children of the AbstractEmployee class
     */
    List<AbstractEmployee> getAllEmployees();

    /**
     * abstract method getEmployeeByPosition(Positions position) finds all employees of a certain profession
     * @param position this is an element of recounting Positions
     * @return collection of children of the AbstractEmployee class
     * */
    List<AbstractEmployee> getEmployeeByPosition(int position);

    /**
     * abstract method getEmployeeByTrainAndDay(String train, DayOfWeek dayOfWeek) finds all employees of a particular train
     * on a specific day
     * @param train is the train number
     * @param dayOfWeek is a day of the week
     * @return collection of children of the AbstractEmployee class
     */

    List<AbstractEmployee> getEmployeeByTrainAndDay(int train, DayOfWeek dayOfWeek);

    /**
     * abstract method addEmployee(String name, String surname, int age, int experience, String profession) is a method
     * that creates an entry in the table of employees according
     * to the input parameters: name, surname, age, length of service and position
     * @param name is a variable storing the name of the employee
     * @param surname this is a variable storing the surname of the employee
     * @param age this is a variable storing the age of the employee
     * @param experience this is a variable storing the experience of the employee
     * @param profession this is a variable storing the profession of the employee
     */
    void addEmployee(String name, String surname, int age, int experience, String profession);

    /**
     * abstract method removeEmployee(int id) is a method that removes an employee record from a table in a database by id
     * @param id is the employee id in the database
     */
    void removeEmployee(int id);

    /**
     * abstract method updateEmployee(String name, String surname, int age, int experience, String profession, int id)
     * is a method that modifies employee data in a database
     * @param name is a variable storing the name of the employee
     * @param surname this is a variable storing the surname of the employee
     * @param age this is a variable storing the age of the employee
     * @param experience this is a variable storing the experience of the employee
     * @param profession this is a variable storing the profession of the employee
     * @param id is the employee id in the database
     */
    void updateEmployee(String name, String surname, int age, int experience, String profession, int id);
}
