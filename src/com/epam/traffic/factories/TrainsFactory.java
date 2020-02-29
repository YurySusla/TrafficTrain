package com.epam.traffic.factories;

import com.epam.traffic.abstracts.AbstractTrain;
import com.epam.traffic.interfaces.TrainsDAO;
import com.epam.traffic.logers.LogCreater;
import com.epam.traffic.managers.ConnectionManager;
import com.epam.traffic.managers.SqlManager;
import com.epam.traffic.models.trains.LongDistanceTrain;
import com.epam.traffic.models.trains.SuburbanTrain;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TrainsFactory it's DAO factory to get train data
 */
public class TrainsFactory implements TrainsDAO {
    /**
     * trains is a collection of children of the AbstractTrain class
     */
    List<AbstractTrain> trains = null;
    /**
     * connection stores the database connection object
     */
    Connection connection = null;
    /**
     * resultSet stores an object with the result of a database query
     */
    ResultSet resultSet = null;
    /**
     * query is a SQL database query
     */
    StringBuilder query = null;
    /**
     * logger is a logging object
     */
    private static Logger logger = LogCreater.getLogger(TrainsFactory.class);

    /**
     * method getAllTrains() gets all children of AbstractTrain class from the database
     * @return collection of children of the AbstractTrain class
     */
    @Override
    public List<AbstractTrain> getAllTrains() {
        trains = null;
        Statement statement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            statement = connection.createStatement();
            query.append(SqlManager.getQuery("SELECT_ALL_TRAINS"));
            query.append(SqlManager.getQuery("DELIMITER"));
            synchronized (TrainsFactory.class){
            resultSet = statement.executeQuery(query.toString());
            trains = getTrains(resultSet);
            }
            while(trains != null){
                ConnectionManager.closeResultSet(resultSet);
                ConnectionManager.closeStatement(statement);
                statement = connection.createStatement();
                synchronized (TrainsFactory.class){
                resultSet = statement.executeQuery(query.toString());
                trains = getTrains(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(statement);
            ConnectionManager.closeConnection();
        }
        return trains;
    }

    /**
     * method getAllTrainsByStationAndTime(String station, Time begin, Time end) gets all children of AbstractTrain class
     * from the database departing from a specific station in a certain period of time
     * @param station is the name of the station
     * @param begin is the beginning of a period of time
     * @param end is the end of a period of time
     * @return collection of children of the AbstractTrain class
     */
    @Override
    public List<AbstractTrain> getAllTrainsByStationAndTime(int station, Time end, Time begin) {
        trains = null;
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("SELECT_ALL_TRAINS"));
            query.append(SqlManager.getQuery("SELECT_ALL_TRAINS_BY_STATION_AND_TIME"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, station);
            preparedStatement.setString(2, begin.toString());
            preparedStatement.setString(3, end.toString());
            resultSet = preparedStatement.executeQuery();
            trains = getTrains(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection();
        }
        return trains;
    }

    /**
     * method removeTrain(int id) is a method that deletes an entry from the train table in the database by id
     * @param id is the train identifier in the database
     */
    @Override
    public void removeTrain(int id) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("REMOVE_TRAIN_FROM_TRAINS"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection();
        }
    }

    /**
     * method updateTrain(String number, String type, String isBrandedTrain, int distance, int id) this is a method
     * that changes the train data in the train table of the database by id
     * @param number is the train number
     * @param type s a type of train
     * @param isBrandedTrain is a sign that the train is branded
     * @param distance is the distance between the start and end stations
     * @param id is the train identifier in the database
     */
    @Override
    public void updateTrain(String number, String type, String isBrandedTrain, int distance, int id) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("UPDATE_TRAIN_BY_ID"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());

            switch (type){
                case "suburban" : preparedStatement.setInt(1, 0);
                    break;
                case "long-distance": preparedStatement.setInt(1, 1);
                    break;
            }

            preparedStatement.setInt(2, distance);
            if(isBrandedTrain.equals("true")){
                preparedStatement.setInt(3, 1);
            } else {
                preparedStatement.setInt(3, 0);
            }
            preparedStatement.setString(4, number);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection();
        }
    }

    /**
     * method addTrain(String number, String type, String isBraindedTrain, int distance) this is a method
     * that adds an entry to the train table in the database
     * @param number is the train number
     * @param type s a type of train
     * @param isBrandedTrain is a sign that the train is branded
     * @param distance is the distance between the start and end stations
     */
    @Override
    public void addTrain(String number, String type, String isBrandedTrain, int distance) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("ADD_TRAIN_TO_TRAINS"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());

            switch (type){
                case "suburban" : preparedStatement.setInt(1, 0);
                    break;
                case "long-distance": preparedStatement.setInt(1, 1);
                    break;
            }

            preparedStatement.setInt(2, distance);
            if(isBrandedTrain.equals("true")){
                preparedStatement.setInt(3, 1);
            } else {
                preparedStatement.setInt(3, 0);
            }
            preparedStatement.setString(4, number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection();
        }
    }

    /**
     * method getTrains(ResultSet resultSet) gets a collection of AbstractTrain objects
     * from a response from the database
     * @param resultSet stores an object with the result of a database query
     * @return collection of children of the AbstractTrain class
     * @throws SQLException
     */
    private List<AbstractTrain> getTrains(ResultSet resultSet) throws SQLException {
        /**
         * temp is a variable storing an intermediate list of trains
         */
        List<AbstractTrain> temp = new ArrayList<>();
        AbstractTrain abstractTrain = null;
        while (resultSet.next()) {
            /**
             * numberOfTrain is a variable storing the train number obtained from the database
             */
            String numberOfTrain = resultSet.getString("numberOfTrain");
            /**
             * distance is a variable storing the distance between the start and end stations of the train,
             * obtained from the database
             */
            int distance = resultSet.getInt("distance");
            /**
             * brandedTrain is a variable storing the value of the sign obtained from the database that
             * the train is branded
             */
            int brandedTrain = resultSet.getInt("isBrandedTrain");
            /**
             * id is a variable storing the id received from the train database
             */
            int id = resultSet.getInt("idtrain");
            switch (resultSet.getInt("typeOfTrain")) {
                case 1:
                    abstractTrain = new LongDistanceTrain(numberOfTrain, distance, brandedTrain);
                    break;
                case 0:
                    abstractTrain = new SuburbanTrain(numberOfTrain, distance, brandedTrain);
                    break;
            }
            abstractTrain.setTrainId(id);
            temp.add(abstractTrain);
        }
        setArrivalAndDepartureTimeStation(temp);
        return temp;
    }

    /**
     * method setArrivalAndDepartureTimeStation(List<AbstractTrain> trains) sets the start and end stations
     * for trains, as well as the departure and arrival times
     * @param trains collection of children of the AbstractTrain class
     * @throws SQLException
     */
    private void setArrivalAndDepartureTimeStation(List<AbstractTrain> trains) throws SQLException{
        Statement statement = null;
        query = new StringBuilder();
        /**
         * isNullResult is a variable storing the sign of equality of request to null
         */
        boolean isNullResult = false;
        try {
            connection = ConnectionManager.createConnection();
            statement = connection.createStatement();
            query.append(SqlManager.getQuery("SELECT_TRAIN_TIME_STATION"));
            query.append(SqlManager.getQuery("DELIMITER"));
            synchronized (TrainsFactory.class){
            resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                /**
                 * arrivalTime  a variable storing the value of the arrival time
                 * at the end station learned from the database
                 */
                String arrivalTime = resultSet.getString("arrivalTime");
                /**
                 * departureTime is a variable storing the value of the departure time
                 * from the initial station learned from the database
                 */
                String departureTime = resultSet.getString("departureTime");
                /**
                 * station is a variable storing the intermediate name of the
                 * start or end station obtained from the database
                 */
                String station = resultSet.getString("nameOfStation");
                /**
                 * trainNumber is a variable storing the number of the train received from the database
                 */
                String trainNumber = resultSet.getString("numberOfTrain");
                for (AbstractTrain train : trains){
                    if(train.getNumber().equals(trainNumber)){
                        if(arrivalTime != null){
                            train.setArrivalTime(Time.valueOf(arrivalTime));
                            train.setArrivalStation(station);
                        }
                        else{
                            train.setDepartureTime(Time.valueOf(departureTime));
                            train.setDepartureStation(station);
                        }
                    }
                }
            }
          }
        } catch (NullPointerException e){
            logger.error(e.getMessage());
            isNullResult = true;
        }
        finally {
            if(isNullResult){
                trains = null;
            }
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(statement);
            ConnectionManager.closeConnection();
        }
    }
}