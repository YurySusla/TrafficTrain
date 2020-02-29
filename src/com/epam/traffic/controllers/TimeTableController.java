package com.epam.traffic.controllers;

import com.epam.traffic.converters.TimeConverter;
import com.epam.traffic.factories.*;
import com.epam.traffic.interfaces.*;
import com.epam.traffic.parsers.JSONParser;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.time.DayOfWeek;

/**
 * TimeTableController is a controller that adds, modifies, and deletes an entry in the schedule table
 * in the database based on data received from the user
 */
public class TimeTableController extends HttpServlet {
    /**
     * method doPost(HttpServletRequest req, HttpServletResponse resp) is a method that adds an entry to the table
     * of train schedules based on data received from the user in the format
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
         * timeTableDAO is a factory instance for working with the schedule database
         */
        TimeTableDAO timeTableDAO = new TimeTableFactory();
        /**
         * trainId is a variable storing train id in a database whose value is obtained from jsonObject
         */
        int trainId = Integer.parseInt(jsonObject.getString("train_id"));
        /**
         * stationId is a variable storing station id in a database whose value is obtained from jsonObject
         */
        int stationId = Integer.parseInt(jsonObject.getString("station_id"));
        /**
         * day is a variable storing day in a database whose value is obtained from jsonObject
         */
        int day = Integer.parseInt(jsonObject.getString("day"));
        /**
         * arrivalJsonTime is a variable storing the string value of the arrival time obtained from jsonObject
         */
        String arrivalJsonTime = TimeConverter.convertTime(jsonObject.getString("arrival_time"));
        /**
         * arrivalTime is a variable storing the arrival time in the format Time
         */
        Time arrivalTime = Time.valueOf(arrivalJsonTime);
        /**
         * departureJsonTime is a variable storing the string value of the departure time obtained from jsonObject
         */
        String departureJsonTime = TimeConverter.convertTime(jsonObject.getString("departure_time"));
        /**
         * departureTime is a variable storing the departure time in the format Time
         */
        Time departureTime = Time.valueOf(departureJsonTime);
        /**
         * method addTimeTable(trainId, stationId, DayOfWeek.of(day), arrivalTime, departureTime) is a factory method
         * that adds an entry to the schedule table in the database based on the values received from the user
         */
        timeTableDAO.addTimeTable(trainId, stationId, DayOfWeek.of(day), arrivalTime, departureTime);
    }

    /**
     * method doPut(HttpServletRequest req, HttpServletResponse resp) is a method that changes the data
     * in the train schedule based on the values received from the user
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
         * timeTableDAO is a factory instance for working with the schedule database
         */
        TimeTableDAO timeTableDAO = new TimeTableFactory();
        /**
         * trainId is a variable storing train id in a database whose value is obtained from jsonObject
         */
        int trainId = Integer.parseInt(jsonObject.getString("train_id"));
        /**
         * stationId is a variable storing station id in a database whose value is obtained from jsonObject
         */
        int stationId = Integer.parseInt(jsonObject.getString("station_id"));
        /**
         * day is a variable storing day in a database whose value is obtained from jsonObject
         */
        int day = Integer.parseInt(jsonObject.getString("day"));
        /**
         * arrivalJsonTime is a variable storing the string value of the arrival time obtained from jsonObject
         */
        String arrivalJsonTime = TimeConverter.convertTime(jsonObject.getString("arrival_time"));
        /**
         * arrivalTime is a variable storing the arrival time in the format Time
         */
        Time arrivalTime = Time.valueOf(arrivalJsonTime);
        /**
         * departureJsonTime is a variable storing the string value of the departure time obtained from jsonObject
         */
        String departureJsonTime = TimeConverter.convertTime(jsonObject.getString("departure_time"));
        /**
         * departureTime is a variable storing the departure time in the format Time
         */
        Time departureTime = Time.valueOf(departureJsonTime);
        /**
         * timeTableId is a variable storing id entries in the schedule table,
         * the value of which is obtained from jsonObject
         */
        int timeTableId = Integer.parseInt(jsonObject.getString("time_table_id"));
        /**
         * method updateTimeTable(trainId, stationId, DayOfWeek.of(day), arrivalTime, departureTime, timeTableId)
         * is a factory method that works with a train schedule table that changes the value of a record by id
         */
        timeTableDAO.updateTimeTable(trainId, stationId, DayOfWeek.of(day), arrivalTime, departureTime, timeTableId);
    }

    /**
     * method doDelete(HttpServletRequest req, HttpServletResponse resp) is a method that deletes an entry
     * in the train schedule table based on data received from the user
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
         * timeTableDAO is a factory instance for working with the schedule database
         */
        TimeTableDAO timeTableDAO = new TimeTableFactory();
        /**
         * timeTableId is a variable storing id entries in the schedule table,
         * the value of which is obtained from jsonObject
         */
        int timeTableId = Integer.parseInt(jsonObject.getString("timetable_id"));
        /**
         * method removeTimeTable(timeTableId) is a factory method that works with a train schedule table
         * in a database that deletes a record by id
         */
        timeTableDAO.removeTimeTable(timeTableId);
    }
}
