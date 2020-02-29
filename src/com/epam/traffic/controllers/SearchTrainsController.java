package com.epam.traffic.controllers;

import com.epam.traffic.abstracts.AbstractTrain;
import com.epam.traffic.converters.TimeConverter;
import com.epam.traffic.factories.TrainsFactory;
import com.epam.traffic.interfaces.TrainsDAO;
import com.epam.traffic.parsers.JSONParser;
import com.epam.traffic.sorters.SortTrainByTime;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchTrainsController is a controller that generates a response to the user
 * about trains based on the data received for the condition
 */
public class SearchTrainsController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /**
         * setting request encoding in utf-8
         */
        req.setCharacterEncoding("utf-8");
        /**
         * jsonObject is an object of type json obtained from the request and converted to json format
         */
        JSONObject jsonObject = JSONParser.getRequest(req);
        /**
         * typeOfSearch is a variable obtained from jsonObject and storing
         * the user's choice for the display of train
         */
        int typeOfSearch = jsonObject.getInt("type_of_choice");
        /**
         * trainsDAO is a copy of the factory for working with trains from the database
         */
        TrainsDAO trainsDAO = new TrainsFactory();
        /**
         * abstractTrains is a variable storing AbstractTrain type list
         */
        List<AbstractTrain> abstractTrains = new ArrayList<>();
        switch (typeOfSearch) {
            case 1:
                /**
                 * method getAllTrains() is the  TrainsDAO factory method,
                 * which returns all trains
                 */
                abstractTrains = trainsDAO.getAllTrains();
                break;
            case 2:
                /**
                 * stationId is a variable obtained from jsonObject and storing the name of the station
                 */
                int stationId = jsonObject.getInt("station_id");
                /**
                 * endTime is a variable obtained from jsonObject and storing the beginning of a time interval
                 */
                String endTime = TimeConverter.convertTime(jsonObject.getString("end_time"));
                Time end = Time.valueOf(endTime);
                /**
                 * beginTime is a variable obtained from jsonObject and storing the end of a time interval
                 */
                String beginTime = TimeConverter.convertTime(jsonObject.getString("begin_time"));
                Time begin = Time.valueOf(beginTime);
                /**
                 * method getAllTrainsByStationAndTime(stationId,end,begin) is the  TrainsDAO factory method,
                 * which receives from the database all trains departing in a certain period
                 * of time from a certain station
                 */
                abstractTrains = trainsDAO.getAllTrainsByStationAndTime(stationId,end,begin);
                break;
            case 3:
                /**
                 * In this block of code, we get a list of all trains and sort it by the time
                 * it takes to travel between the start and end stations
                 */
                abstractTrains = trainsDAO.getAllTrains();
                abstractTrains.sort(new SortTrainByTime());
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
         * In the loop, we go through the entire list of conditionally received trains and form an answer in JSON format
         */
        for (AbstractTrain abstractTrain : abstractTrains) {
            jsonOutputObject = new JSONObject();
            jsonOutputObject.put("number",abstractTrain.getNumber());
            jsonOutputObject.put("train_type",abstractTrain.getTypeOfTrain());
            jsonOutputObject.put("distance", abstractTrain.getDistance());
            jsonOutputObject.put("is_branded", abstractTrain.isBrandedTrain());
            jsonOutputObject.put("id", abstractTrain.getTrainId());
            jsonOutputObject.put("arrival_time", abstractTrain.getArrivalTime());
            jsonOutputObject.put("arrival_station", abstractTrain.getArrivalStation());
            jsonOutputObject.put("departure_time", abstractTrain.getDepartureTime());
            jsonOutputObject.put("departure_station", abstractTrain.getDepartureStation());
            jsonArray.put(jsonOutputObject);
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().write(jsonArray.toString());
    }
}
