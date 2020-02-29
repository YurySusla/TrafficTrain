package com.epam.traffic.factories;

import com.epam.traffic.abstracts.AbstractStation;
import com.epam.traffic.enums.StationType;
import com.epam.traffic.interfaces.StationDAO;
import com.epam.traffic.logers.LogCreater;
import com.epam.traffic.managers.ConnectionManager;
import com.epam.traffic.managers.SqlManager;
import com.epam.traffic.models.stations.RailroadStation;
import com.epam.traffic.models.stations.TrainStop;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * StationsFactory it's DAO factory for getting station data from a database
 */
public class StationsFactory implements StationDAO {
    /**
     * stations is a collection of children of the AbstractStation class.
     */
    List<AbstractStation> stations;
    /**
     * connection stores the database connection object
     */
    Connection connection;
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
    private static Logger logger = LogCreater.getLogger(StationsFactory.class);

    /**
     * method getAllStations() gets all children of AbstractStation class from the database
     * @return collection of children of the AbstractStation class
     */
    @Override
    public List<AbstractStation> getAllStations() {
        stations = null;
        Statement statement = null;
        query = new StringBuilder();
            try {
                connection = ConnectionManager.createConnection();
                statement = connection.createStatement();
                query.append(SqlManager.getQuery("SELECT_ALL_STATIONS"));
                query.append(SqlManager.getQuery("DELIMITER"));
                synchronized (StationsFactory.class){
                resultSet = statement.executeQuery(query.toString());
                stations = getStations(resultSet);
                }
                while(stations == null){
                    ConnectionManager.closeResultSet(resultSet);
                    ConnectionManager.closeStatement(statement);
                    statement = connection.createStatement();
                    synchronized (StationsFactory.class){
                    resultSet = statement.executeQuery(query.toString());
                    stations = getStations(resultSet);
                }
              }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            } finally {
                ConnectionManager.closeResultSet(resultSet);
                ConnectionManager.closeStatement(statement);
                ConnectionManager.closeConnection();
            }
            return stations;
    }

    /**
     * method addStation(String name, String stationType, String isWaitingHall) is a method that adds an entry
     * to the station table in the database
     * @param name is a variable storing the name of the station
     * @param stationType is a variable storing the type of station
     * @param isWaitingHall is a variable storing a hall waiting sign at the station
     */
    @Override
    public void addStation(String name, String stationType, String isWaitingHall) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("ADD_STATION_TO_STATIONS"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, name);
            switch (stationType){
                case "railroad station" : preparedStatement.setInt(2, 1);
                    break;
                case "train stop" : preparedStatement.setInt(2, 0);
                    break;
            }
            if(isWaitingHall.equals("true")){
                preparedStatement.setInt(3,1);
            } else {
                preparedStatement.setInt(3,0);
            }
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
     * method removeStation(int id) is a method that deletes a record from the station table in the database
     * @param id is id in the station table in the database
     */
    @Override
    public void removeStation(int id) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("REMOVE_STATION_FROM_STATIONS"));
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
     * method updateStation(String name, String stationType, String isWaitingHall, int id) is a method
     * that updates the record data in the train table by id
     * @param name is a variable storing the name of the station
     * @param stationType is a variable storing the type of station
     * @param isWaitingHall is a variable storing a hall waiting sign at the station
     * @param id is id in the station table in the database
     */
    @Override
    public void updateStation(String name, String stationType, String isWaitingHall, int id) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("UPDATE_STATION_BY_ID"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, name);
            switch (stationType){
                case "railroad station" : preparedStatement.setInt(2, 1);
                    break;
                case "train stop" : preparedStatement.setInt(2, 0);
                    break;
            }

            if(isWaitingHall.equals("true")){
                preparedStatement.setInt(3,1);
            } else {
                preparedStatement.setInt(3,0);
            }
            preparedStatement.setInt(4, id);
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
     * method getStations(ResultSet resultSet) gets a collection of objects of child classes AbstractStation
     * from a response from the database
     * @param resultSet stores an object with the result of a database query
     * @return collection of children of the AbstractStation class
     * @throws SQLException
     */
        private List<AbstractStation> getStations(ResultSet resultSet) throws SQLException {
            /**
             * temp is a variable for intermediate storage of the list of stations
             */
            List<AbstractStation> temp = new ArrayList<>();
            AbstractStation abstractStation = null;
            try {
                while(resultSet.next()){
                    /**
                     * name is a variable storing the name of the station whose value is obtained from the database
                     */
                    String name = resultSet.getString("nameOfStation");
                    /**
                     * waitingHall is a variable storing a sign of a waiting room at the station,
                     * the value of which is obtained from the database
                     */
                    int waitingHall = resultSet.getInt("waitingHall");
                    /**
                     * id is a variable storing id records in the station table in the database
                     */
                    int id = resultSet.getInt("idstation");
                    switch (resultSet.getInt("isRailroadStation")){
                        case 1: abstractStation = new RailroadStation(name, waitingHall);
                            break;
                        case 0: abstractStation = new TrainStop(name, waitingHall);
                            break;
                    }
                    abstractStation.setStationId(id);
                    temp.add(abstractStation);
                }
            } catch (NullPointerException e){
                logger.error(e.getMessage());
                temp = null;
            } finally {
                return temp;
            }
        }
}
