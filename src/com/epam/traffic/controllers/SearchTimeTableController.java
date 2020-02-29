package com.epam.traffic.controllers;

import com.epam.traffic.abstracts.AbstractStation;
import com.epam.traffic.abstracts.AbstractTrain;
import com.epam.traffic.factories.StationsFactory;
import com.epam.traffic.factories.TimeTableFactory;
import com.epam.traffic.factories.TrainsFactory;
import com.epam.traffic.interfaces.StationDAO;
import com.epam.traffic.interfaces.TimeTableDAO;
import com.epam.traffic.interfaces.TrainsDAO;
import com.epam.traffic.models.TimeTable;
import com.epam.traffic.parsers.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchTimeTableController is a controller that generates a response to the user about the train schedule
 * based on the data received for the condition
 */
public class SearchTimeTableController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /**
         * setting request encoding in utf-8
         */
        req.setCharacterEncoding("utf-8");
        /**
         * timeTableDAO is a factory instance for working with the schedule database
         */
        TimeTableDAO timeTableDAO = new TimeTableFactory();
        /**
         * stationDAO is a factory instance for working with stations from the database
         */
        StationDAO stationDAO = new StationsFactory();
        /**
         * trainsDAO is a copy of the factory for working with trains from the database
         */
        TrainsDAO trainsDAO = new TrainsFactory();
        /**
         * timeTables is a variable storing TimeTable type list
         */
        List<TimeTable> timeTables = new ArrayList<>();
        /**
         * abstractTrains is a variable storing AbstractTrain type list
         */
        List<AbstractTrain> abstractTrains = trainsDAO.getAllTrains();
        /**
         * abstractStations is a variable storing AbstractStation type list
         */
        List<AbstractStation> abstractStations = stationDAO.getAllStations();
        /**
         * jsonObject is an object of type json obtained from the request and converted to json format
         */
        JSONObject jsonObject = JSONParser.getRequest(req);
        /**
         * typeOfSearch is a variable obtained from jsonObject and storing
         * the user's choice for the display of timetable
         */
        int typeOfSearch = jsonObject.getInt("type_of_choice");
        switch (typeOfSearch) {
            case 1:
                /**
                 * method getAllTimeTable() is the  TimeTableDAO factory method,
                 * which returns all schedule
                 */
                timeTables = timeTableDAO.getAllTimeTable();
                break;
            case 2:
                /**
                 * trainType is a variable obtained from jsonObject and storing the type of train
                 */
                int trainType = jsonObject.getInt("train_type");
                /**
                 * method getTimeTableByTrainType(trainType) is the  TimeTableDAO factory method,
                 * which returns all the schedules of trains of a certain type
                 */
                timeTables = timeTableDAO.getTimeTableByTrainType(trainType);
                break;
            case 3:
                /**
                 * station is a variable obtained from jsonObject and storing id of train
                 */
                int station = jsonObject.getInt("station_id");
                /**
                 * trainType is a variable obtained from jsonObject and storing the type of train
                 */
                trainType = jsonObject.getInt("train_type");
                /**
                 * method getTimeTableByTrainTypeAndStation(trainType, station) is the  TimeTableDAO factory method,
                 * which returns all the schedules of trains of a certain type
                 */
                timeTables = timeTableDAO.getTimeTableByTrainTypeAndStation(trainType, station);
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
         * In the cycle, we look through all the data from the schedule list and find the necessary information
         * by identifiers from the spikes of trains and stations
         */
        for (TimeTable timeTable: timeTables) {
            jsonOutputObject = new JSONObject();
            jsonOutputObject.put("timetable_id", timeTable.getTimeTableId());
            jsonOutputObject.put("train_id", timeTable.getTrainId());
            /**
             * In the cycle, we look through the information about each train from the list
             * and find the desired train number for the answer
             */
            for (AbstractTrain abstractTrain : abstractTrains ) {
                if(abstractTrain.getTrainId() == timeTable.getTrainId()){
                    jsonOutputObject.put("train_number", abstractTrain.getNumber());
                }
            }
            jsonOutputObject.put("station_id", timeTable.getStationId());
            /**
             * In the loop, we look through the information about each station from the list and
             * find the desired station name for the answer
             */
            for (AbstractStation abstractStation: abstractStations) {
                if(abstractStation.getStationId() == timeTable.getStationId()){
                    jsonOutputObject.put("station_name", abstractStation.getName());
                }
            }
            jsonOutputObject.put("day", timeTable.getDayOfWeek().getValue());
            /**
             * If there is no train arrival time in the database, then assign null to the variable
             */
            if(timeTable.getArrivalTime() == null){
                jsonOutputObject.put("arrival_time", "null");
            } else {
                jsonOutputObject.put("arrival_time", timeTable.getArrivalTime().toString());
            }
            /**
             * If there is no train departure time in the database, then assign null to the variable
             */
            if(timeTable.getDepartureTime() == null){
                jsonOutputObject.put("departure_time", "null");
            } else {
                jsonOutputObject.put("departure_time", timeTable.getDepartureTime().toString());
            }
            jsonArray.put(jsonOutputObject);
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().write(jsonArray.toString());

    }
}
