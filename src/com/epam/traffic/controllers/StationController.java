package com.epam.traffic.controllers;

import com.epam.traffic.abstracts.AbstractStation;
import com.epam.traffic.factories.StationsFactory;
import com.epam.traffic.interfaces.StationDAO;
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
 * StationController it is a controller that generates a response about all stations to the user,
 * as well as deletes, modifies and adds stations to the database based on the request data
 */
public class StationController extends HttpServlet {
    /**
     * method doGet(HttpServletRequest req, HttpServletResponse resp) this is a method that generates a response
     * to the user about all stations from the database
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
         * stationDAO is a factory instance for working with stations from the database
         */
        StationDAO stationDAO = new StationsFactory();
        /**
         * abstractStations is a variable storing AbstractStation type list
         */
        List<AbstractStation> abstractStations = stationDAO.getAllStations();
        /**
         * jsonArray is an array of JSON objects.
         */
        JSONArray jsonArray = new JSONArray();
        /**
         * jsonOutputObject is an object of type JSON for transmitting a response to the user
         */
        JSONObject jsonObject;
        /**
         * In a cycle we look through the list of stations received from the database and form a response
         * to the user in JSON format
         */
        for (AbstractStation abstractStation : abstractStations) {
            jsonObject = new JSONObject();
            jsonObject.put("name",  abstractStation.getName());
            jsonObject.put("type", abstractStation.getTypeOfStation());
            jsonObject.put("isWaitingHall", abstractStation.isWaitingHall());
            jsonObject.put("id", abstractStation.getStationId());
            jsonArray.put(jsonObject);
        }
        resp.setContentType("application/json");
        resp.getWriter().write(jsonArray.toString());
    }

    /**
     * method doPost(HttpServletRequest req, HttpServletResponse resp) is a method that adds a station
     * based on data received from a user request.
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
        req.setCharacterEncoding("UTF-8");
        /**
         * jsonObject is an object of type json obtained from the request and converted to json format
         */
        JSONObject jsonObject = JSONParser.getRequest(req);
        /**
         * stationDAO is a factory instance for working with stations from the database
         */
        StationDAO stationDAO = new StationsFactory();
        /**
         * stationName is a variable storing the name of the station whose value is obtained from jsonObject
         */
        String stationName = jsonObject.getString("name");
        /**
         * stationType is a variable storing the type of station whose value is obtained from jsonObject
         */
        String stationType = jsonObject.getString("station_type");
        /**
         * isWaitingHall is a variable that stores information about the availability of a waiting room
         * at a station, the value of which is obtained from jsonObject
         */
        String isWaitingHall = jsonObject.getString("is_waiting_hall");
        /**
         * method addStation(stationName, stationType, isWaitingHall) is a StationDAO method for working
         * with a station table that adds a new record to the database
         */
        stationDAO.addStation(stationName, stationType, isWaitingHall);
    }

    /**
     * method doPut(HttpServletRequest req, HttpServletResponse resp) is a method that changes station data
     * based on a request received from a user
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
        req.setCharacterEncoding("UTF-8");
        /**
         * jsonObject is an object of type json obtained from the request and converted to json format
         */
        JSONObject jsonObject = JSONParser.getRequest(req);
        /**
         * stationDAO is a factory instance for working with stations from the database
         */
        StationDAO stationDAO = new StationsFactory();
        /**
         * stationName is a variable storing the name of the station whose value is obtained from jsonObject
         */
        String stationName = jsonObject.getString("name");
        /**
         * stationType is a variable storing the type of station whose value is obtained from jsonObject
         */
        String stationType = jsonObject.getString("station_type");
        /**
         * isWaitingHall is a variable that stores information about the availability of a waiting room
         * at a station, the value of which is obtained from jsonObject
         */
        String isWaitingHall = jsonObject.getString("is_waiting_hall");
        /**
         * stationId is a variable that stores id stations in a database whose value is obtained from jsonObject
         */
        int stationId = Integer.parseInt(jsonObject.getString("id"));
        /**
         * method updateStation(stationName, stationType, isWaitingHall, stationId) is a factory method that works
         * with a table of stations in a database and modifies data on a id station.
         */
        stationDAO.updateStation(stationName, stationType, isWaitingHall, stationId);
    }

    /**
     * method doDelete(HttpServletRequest req, HttpServletResponse resp) is a method that deletes a station
     * based on data received from the user
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
         * stationDAO is a factory instance for working with stations from the database
         */
        StationDAO stationDAO = new StationsFactory();
        /**
         * stationId this is a variable that stores id stations in a database whose value is obtained from jsonObject
         */
        int stationId = Integer.parseInt(jsonObject.getString("id"));
        /**
         * method removeStation(stationId) is a method that removes a station record from a database
         * based on a id station
         */
        stationDAO.removeStation(stationId);
    }
}
