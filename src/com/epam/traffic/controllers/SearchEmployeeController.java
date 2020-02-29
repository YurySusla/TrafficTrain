package com.epam.traffic.controllers;

import com.epam.traffic.abstracts.AbstractEmployee;
import com.epam.traffic.factories.EmployeesFactory;
import com.epam.traffic.interfaces.EmployeesDAO;
import com.epam.traffic.parsers.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchEmployeeController is a controller for finding employees under certain conditions
 */
public class SearchEmployeeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * setting request encoding in utf-8
         */
        req.setCharacterEncoding("utf-8");
        /**
         * jsonObject is an object of type json obtained from the request and converted to json format
         */
        JSONObject jsonObject = JSONParser.getRequest(req);
        /**
         * abstractEmployees is a variable for storing a list of employees
         */
        List<AbstractEmployee> abstractEmployees = new ArrayList<>();
        /**
         * typeOfSearch is a variable obtained from jsonObject and storing
         * the user's choice for the display of employees
         */
        int typeOfSearch = jsonObject.getInt("type_of_choice");
        /**
         * employeesDAO is an instance of a factory for working with a database of employees
         */
        EmployeesDAO employeesDAO = new EmployeesFactory();
        switch (typeOfSearch){
            /**
             * method getAllEmployees() is the  EmployeesDAO factory method,
             * which returns all employees from the database
             */
            case 1: abstractEmployees = employeesDAO.getAllEmployees();
                break;
            case 2:
                /**
                 * minAge is a variable storing the minimum age of employees.
                 * Its value is derived from jsonObject
                 */
                int minAge = jsonObject.getInt("min_age");
                /**
                 * maxAge is a variable storing the maximum age of employees.
                 * Its value is derived from jsonObject
                 */
                int maxAge = jsonObject.getInt("max_age");
                /**
                 * In the for cycle, all employees obtained from the database are selected
                 * by age from a given range
                 */
                for (AbstractEmployee abstractEmployee: employeesDAO.getAllEmployees()) {
                    if(abstractEmployee.getAge() <= maxAge && abstractEmployee.getAge() >= minAge){
                        abstractEmployees.add(abstractEmployee);
                    }
                }
                break;
            case 3:
                /**
                 * minExperience is a variable that stores the minimum length of service for employees.
                 * Its value is derived from jsonObject
                 */
                int minExperience = jsonObject.getInt("min_experience");
                /**
                 * maxExperience is a variable that stores the maximum length of service for employees.
                 * Its value is derived from jsonObject
                 */
                int maxExperience = jsonObject.getInt("max_experience");
                /**
                 * In the for cycle, all employees obtained from the database are selected
                 * by experience from a given range
                 */
                for (AbstractEmployee abstractEmployee: employeesDAO.getAllEmployees()) {
                    if(abstractEmployee.getExperience() <= maxExperience
                            && abstractEmployee.getExperience() >= minExperience){
                        abstractEmployees.add(abstractEmployee);
                    }
                }
                break;
            case 4:
                /**
                 * position is a variable storing an employee’s position.
                 * Its value is derived from jsonObject
                 */
                int position = jsonObject.getInt("position");
                /**
                 * method getEmployeeByPosition(position) is the  EmployeesDAO factory method,
                 * which returns all employees from a database with a certain position
                 */
                abstractEmployees = employeesDAO.getEmployeeByPosition(position);
                break;
            case 5:
                /**
                 * train is a variable storing id of train in the database.
                 * Its value is derived from jsonObject
                 */
                int train = jsonObject.getInt("train_id");
                /**
                 * dayOfWeek is a variable storing the day of the week.
                 * Its value is derived from jsonObject
                 */
                DayOfWeek dayOfWeek = DayOfWeek.of(jsonObject.getInt("day_of_week"));
                /**
                 * method getEmployeeByTrainAndDay(train, dayOfWeek) is the  EmployeesDAO factory method,
                 * which returns all employees of a particular train on a specific day of the week
                 */
                abstractEmployees = employeesDAO.getEmployeeByTrainAndDay(train, dayOfWeek);
                break;
        }
        /**
         * jsonArray is an array of JSON objects.
         */
        JSONArray jsonArray = new JSONArray();
        /**
         * jsonOutputObject is an object of type JSON for transmitting a response to the user
         */
        JSONObject jsonOutputObject;
        /**
         * In the loop, we go through the entire list of conditionally received employees
         * and form an answer in JSON format
         */
        for (AbstractEmployee abstractEmployee : abstractEmployees) {
            jsonOutputObject = new JSONObject();
            jsonOutputObject.put("name",abstractEmployee.getName());
            jsonOutputObject.put("surname",abstractEmployee.getSurname());
            jsonOutputObject.put("age",abstractEmployee.getAge());
            jsonOutputObject.put("experience", abstractEmployee.getExperience());
            jsonOutputObject.put("profession", abstractEmployee.getProfession());
            jsonOutputObject.put("id",abstractEmployee.getEmployeeId());
            jsonArray.put(jsonOutputObject);
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().write(jsonArray.toString());
    }
}

