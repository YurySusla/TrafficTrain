package com.epam.traffic.controllers;

import com.epam.traffic.abstracts.AbstractEmployee;
import com.epam.traffic.abstracts.AbstractTrain;
import com.epam.traffic.factories.EmployeesFactory;
import com.epam.traffic.factories.StaffFactory;
import com.epam.traffic.factories.TrainsFactory;
import com.epam.traffic.interfaces.EmployeesDAO;
import com.epam.traffic.interfaces.StaffDAO;
import com.epam.traffic.interfaces.TrainsDAO;
import com.epam.traffic.models.Staff;
import com.epam.traffic.parsers.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * StaffController is a controller that generates a response to the user about the workings of a particular train
 * on a specific day of the week, and also adds, deletes and modifies data in the database
 */
public class StaffController extends HttpServlet {
    /**
     * method doGet(HttpServletRequest req, HttpServletResponse resp) is a method that generates a response
     * to the user about the workings of a particular train on a specific day of the week
     * @param req is a request from the user
     * @param resp is a response from the user
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * setting request encoding in utf-8
         */
        req.setCharacterEncoding("utf-8");
        /**
         * staffDAO is a factory instance for working with staff from a database
         */
        StaffDAO staffDAO = new StaffFactory();
        /**
         * trainsDAO is an instance of a factory for working with trains from a database
         */
        TrainsDAO trainsDAO = new TrainsFactory();
        /**
         * employeesDAO is a factory instance for working with employees from a database
         */
        EmployeesDAO employeesDAO = new EmployeesFactory();
        /**
         * staffList is a variable storing a list of train staff
         */
        List<Staff> staffList = staffDAO.getAllStaff();
        /**
         * abstractTrains is a variable storing AbstractTrain type list
         */
        List<AbstractTrain> abstractTrains = trainsDAO.getAllTrains();
        /**
         * abstractEmployees is a variable storing a list of employees
         */
        List<AbstractEmployee> abstractEmployees = employeesDAO.getAllEmployees();
        /**
         * jsonArray is an array of JSON objects.
         */
        JSONArray jsonArray = new JSONArray();
        /**
         * jsonOutputObject is an object of type JSON for transmitting a response to the user
         */
        JSONObject jsonObject;
        /**
         * In the cycle, we look through all the data from the list of staff of train employees
         * and find the necessary information by identifiers from the lists of trains and employees.
         */
        for (Staff staff: staffList) {
            jsonObject = new JSONObject();
            jsonObject.put("staff_id", staff.getStaffId());
            jsonObject.put("train_id", staff.getTrainId());
            /**
             * In the cycle, we look through the information about the trains and find the train number we need
             */
            for(AbstractTrain abstractTrain : abstractTrains){
                if(abstractTrain.getTrainId() == staff.getTrainId()){
                    jsonObject.put("train_number", abstractTrain.getNumber());
                }
            }
            jsonObject.put("employee_id", staff.getEmployeeId());
            /**
             * In the cycle, we look through information about employees and find the name,
             * surname and position of the employee we need
             */
            for (AbstractEmployee abstractEmployee: abstractEmployees) {
                if(abstractEmployee.getEmployeeId() == staff.getEmployeeId()){
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(abstractEmployee.getName());
                    stringBuilder.append(" ");
                    stringBuilder.append(abstractEmployee.getSurname());
                    jsonObject.put("employee_info", stringBuilder.toString());
                    jsonObject.put("employee_profession", abstractEmployee.getProfession());
                }
            }
            resp.setCharacterEncoding("utf-8");
            jsonObject.put("day", staff.getDayOfWeek().getValue());
            jsonArray.put(jsonObject);
        }
        resp.setContentType("application/json");
        resp.getWriter().write(jsonArray.toString());
    }

    /**
     * method doPost(HttpServletRequest req, HttpServletResponse resp) adds a new employee from the data
     * received from the request
     * @param req is a request from the user
     * @param resp is a response from the user
     * @throws ServletException
     * @throws IOException
     */
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
         * staffDAO is a factory instance for working with staff from a database
         */
        StaffDAO staffDAO = new StaffFactory();
        /**
         * trainId is a variable storing train id in the database and getting the value from jsonObject
         */
        int trainId = Integer.parseInt(jsonObject.getString("train_id"));
        /**
         * emloyeeId is a variable storing employee id in the database and getting the value from jsonObject
         */
        int emloyeeId = Integer.parseInt(jsonObject.getString("employee_id"));
        /**
         * day is a variable storing the day of the week and getting the value from jsonObject
         */
        int day = Integer.parseInt(jsonObject.getString("day"));
        /**
         * method addStaff(trainId, emloyeeId, day) is a StaffDAO method that works with the staff table
         * in the database, which adds a new record from the data received from the request
         */
        staffDAO.addStaff(trainId, emloyeeId, day);
    }

    /**
     * method doPut(HttpServletRequest req, HttpServletResponse resp) is a method that changes data
     * in the staff based on the data received from the request
     * @param req is a request from the user
     * @param resp is a response from the user
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * setting request encoding in utf-8
         */
        req.setCharacterEncoding("utf-8");
        /**
         * jsonObject is an object of type json obtained from the request and converted to json format
         */
        JSONObject jsonObject = JSONParser.getRequest(req);
        /**
         * staffDAO is a factory instance for working with staff from a database
         */
        StaffDAO staffDAO = new StaffFactory();
        /**
         * trainId is a variable storing train id in the database and getting the value from jsonObject
         */
        int trainId = Integer.parseInt(jsonObject.getString("train_id"));
        /**
         * emloyeeId is a variable storing employee id in the database and getting the value from jsonObject
         */
        int emloyeeId = Integer.parseInt(jsonObject.getString("employee_id"));
        /**
         * day is a variable storing the day of the week and getting the value from jsonObject
         */
        int day = Integer.parseInt(jsonObject.getString("day"));
        /**
         * staffId is a variable storing staff id in the database and getting the value from jsonObject
         */
        int staffId = Integer.parseInt(jsonObject.getString("staff_id"));
        /**
         *  method updateStaff(trainId, emloyeeId, day, staffId) is a StaffDAO method that works with the staff table
         * in the database, which changes the value of the record from the staff table based
         * on the data received from the request
         */
        staffDAO.updateStaff(trainId, emloyeeId, day, staffId);
    }

    /**
     * method doDelete(HttpServletRequest req, HttpServletResponse resp) is a method that removes an employee
     * with a specific id obtained from the request
     * @param req is a request from the user
     * @param resp is a response from the user
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
         * staffDAO is a factory instance for working with staff from a database
         */
        StaffDAO staffDAO = new StaffFactory();
        /**
         * staffId is a variable storing staff id in the database and getting the value from jsonObject
         */
        int staffId = Integer.parseInt(jsonObject.getString("staff_id"));
        /**
         * method removeStaff(staffId) is a StaffDAO method that works with the staff table
         * in the database, which deletes a record with a specific id
         */
        staffDAO.removeStaff(staffId);
    }
}
