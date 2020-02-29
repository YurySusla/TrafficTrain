package com.epam.traffic.controllers;

import com.epam.traffic.factories.TrainsFactory;
import com.epam.traffic.interfaces.TrainsDAO;
import com.epam.traffic.parsers.JSONParser;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TrainController is a controller that deletes, modifies and adds data to the train table
 */
public class TrainController extends HttpServlet {
    /**
     * method doPost(HttpServletRequest req, HttpServletResponse resp) is a method that adds an entry
     * to the train table from data received from the user in JSON format
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
         * trainsDAO is a copy of the factory for working with trains from the database
         */
        TrainsDAO trainsDAO = new TrainsFactory();
        /**
         * trainNumber is a variable storing the train number obtained from jsonObject
         */
        String trainNumber = jsonObject.getString("number");
        /**
         * trainType is a variable storing the type of train obtained from jsonObject
         */
        String trainType = jsonObject.getString("train_type");
        /**
         * isBrandedTrain is a variable storing the presence of the train's corporate status obtained from jsonObject
         */
        String isBrandedTrain = jsonObject.getString("is_branded");
        /**
         * distance is a variable storing the distance between the start and end station of a train,
         * obtained from jsonObject
         */
        int distance = Integer.parseInt(jsonObject.getString("distance"));
        /**
         * method addTrain(trainNumber, trainType, isBrandedTrain, distance) is a method of a TrainsDAO working
         * with a train table that adds a record with data received from the user
         */
        trainsDAO.addTrain(trainNumber, trainType, isBrandedTrain, distance);
    }

    /**
     * method doPut(HttpServletRequest req, HttpServletResponse resp) this is a method
     * that modifies data about a specific train obtained from a user’s request in JSON format
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
         * trainsDAO is a copy of the factory for working with trains from the database
         */
        TrainsDAO trainsDAO = new TrainsFactory();
        /**
         * trainNumber is a variable storing the train number obtained from jsonObject
         */
        String trainNumber = jsonObject.getString("number");
        /**
         * trainType is a variable storing the type of train obtained from jsonObject
         */
        String trainType = jsonObject.getString("train_type");
        /**
         * isBrandedTrain is a variable storing the presence of the train's corporate status obtained from jsonObject
         */
        String isBrandedTrain = jsonObject.getString("is_branded");
        /**
         * distance is a variable storing the distance between the start and end station of a train,
         * obtained from jsonObject
         */
        int distance = Integer.parseInt(jsonObject.getString("distance"));
        /**
         * trainId is a variable storing train id in a database whose value is obtained from jsonObject
         */
        int trainId = Integer.parseInt(jsonObject.getString("id"));
        /**
         * method updateTrain(trainNumber, trainType, isBrandedTrain, distance, trainId) is a TrainsDAO method
         * that updates the record in the train table by train id and data received from the user’s request
         */
        trainsDAO.updateTrain(trainNumber, trainType, isBrandedTrain, distance, trainId);
    }

    /**
     * method doDelete(HttpServletRequest req, HttpServletResponse resp) this is a method that deletes a train
     * on id received from a user request in JSON format
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
         * trainsDAO is a copy of the factory for working with trains from the database
         */
        TrainsDAO trainsDAO = new TrainsFactory();
        /**
         * trainId is a variable storing train id in a database whose value is obtained from jsonObject
         */
        int trainId = Integer.parseInt(jsonObject.getString("id"));
        /**
         * method removeTrain(trainId) is a TrainsDAO method which deletes an entry from the train table by id
         */
        trainsDAO.removeTrain(trainId);
    }
}
