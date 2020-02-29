package com.epam.traffic.controllers;

import com.epam.traffic.factories.EmployeesFactory;
import com.epam.traffic.interfaces.EmployeesDAO;
import com.epam.traffic.parsers.JSONParser;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HRController is a controller for deleting, changing and adding data about employees at the request of a user
 */
public class HRController extends HttpServlet {
    /**
     * method doPost (HttpServletRequest req, HttpServletResponse resp) receives data for adding an employee
     * in the format json and transfers them to the factory working with the employee database
     * @param req is a request from the user
     * @param resp this is user response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * setting request encoding in utf-8
         */
        req.setCharacterEncoding("UTF-8");
        /**
         * jsonObject is an object of type json obtained from the request and converted to json format
         */
        JSONObject jsonObject = JSONParser.getRequest(req);
        /**
         * employeesDAO is an instance of a factory for working with a database of employees
         */
        EmployeesDAO employeesDAO = new EmployeesFactory();
        /**
         * name is a variable obtained from jsonObject and storing the name of the employee
         */
        String name = jsonObject.getString("name");
        /**
         * surname is a variable obtained from jsonObject and storing the surname of the employee
         */
        String surname = jsonObject.getString("surname");
        /**
         * age is a variable obtained from jsonObject and storing the age of the employee
         */
        int age = Integer.parseInt(jsonObject.getString("age"));
        /**
         * experience is a variable obtained from jsonObject and storing the experience of the employee
         */
        int experience = Integer.parseInt(jsonObject.getString("experience"));
        /**
         * profession is a variable obtained from jsonObject and storing the experience of the employee
         */
        String profession = jsonObject.getString("profession");
        /**
         * method addEmployee(name, surname, age, experience, profession) is the  EmployeesDAO factory method,
         * which adds to the employee with a specific name, surname, age, experience
         * and position in the employee database
         */
        employeesDAO.addEmployee(name, surname, age, experience, profession);
    }

    /**
     * method doPut(HttpServletRequest req, HttpServletResponse resp) receives data from the request
     * in json format for editing data about employees and sends it to the factory working with the employee database
     * @param req is a request from the user
     * @param resp this is user response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        /**
         * jsonObject is an object of type json obtained from the request and converted to json format
         */
        JSONObject jsonObject = JSONParser.getRequest(req);
        /**
         * employeesDAO is an instance of a factory for working with a database of employees
         */
        EmployeesDAO employeesDAO = new EmployeesFactory();
        /**
         * name is a variable obtained from jsonObject and storing the name of the employee
         */
        String name = jsonObject.getString("name");
        /**
         * surname is a variable obtained from jsonObject and storing the surname of the employee
         */
        String surname = jsonObject.getString("surname");
        /**
         * age is a variable obtained from jsonObject and storing the age of the employee
         */
        int age = Integer.parseInt(jsonObject.getString("age"));
        /**
         * experience is a variable obtained from jsonObject and storing the experience of the employee
         */
        int experience = Integer.parseInt(jsonObject.getString("experience"));
        /**
         * profession is a variable obtained from jsonObject and storing the experience of the employee
         */
        String profession = jsonObject.getString("profession");
        /**
         * id is a variable obtained from jsonObject and storing id of the employee
         */
        int id  = Integer.parseInt(jsonObject.getString("id"));
        /**
         * method updateEmployee(name, surname, age, experience, profession,id) is the factory method
         * EmployeesDAO, which edits employee data in the database with a specific id
         */
        employeesDAO.updateEmployee(name, surname, age, experience, profession,id);
    }

    /**
     * method doDelete(HttpServletRequest req, HttpServletResponse resp) gets data in json format for deleting employee
     * and sends it to the factory working with the employee database
     * @param req is a request from the user
     * @param resp this is user response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * jsonObject is an object of type json obtained from the request and converted to json format
         */
        JSONObject jsonObject = JSONParser.getRequest(req);
        /**
         * employeesDAO is an instance of a factory for working with a database of employees
         */
        EmployeesDAO employeesDAO = new EmployeesFactory();
        /**
         * id is a variable obtained from jsonObject and storing id of the employee
         */
        int id  = Integer.parseInt(jsonObject.getString("id"));
        /**
         * method removeEmployee(id) is the factory method EmployeesDAO,
         * which edits employee data in the database with a specific id
         */
        employeesDAO.removeEmployee(id);
    }
}