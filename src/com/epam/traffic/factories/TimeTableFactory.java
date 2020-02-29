package com.epam.traffic.factories;

import com.epam.traffic.enums.TrainType;
import com.epam.traffic.interfaces.TimeTableDAO;
import com.epam.traffic.logers.LogCreater;
import com.epam.traffic.managers.ConnectionManager;
import com.epam.traffic.managers.SqlManager;
import com.epam.traffic.models.TimeTable;
import org.apache.log4j.Logger;
import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
/**
 * TimeTableFactory it's DAO factory to get timetable data
 */
public class TimeTableFactory implements TimeTableDAO {
    /**
     * timeTables is a collection of TimeTable class objects
     */
    List<TimeTable> timeTables;
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
    private static Logger logger = LogCreater.getLogger(TimeTableFactory.class);

    /**
     * method getAllTimeTable() gets all objects of class TimeTable train schedules from the database
     * @return collection of TimeTable class objects
     */
    @Override
    public List<TimeTable> getAllTimeTable() {
        timeTables = null;
        Statement statement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            statement = connection.createStatement();
            query.append(SqlManager.getQuery("SELECT_TIMETABLE_ALL"));
            query.append(SqlManager.getQuery("DELIMITER"));
            resultSet = statement.executeQuery(query.toString());
            timeTables = getTimeTable(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(statement);
            ConnectionManager.closeConnection();
        }
        return timeTables;
    }

    /**
     * method getTimeTableByTrainType(TrainType trainType) gets all objects of class TimeTable train schedules
     * from the database by train type
     * @param trainType is a train type enumeration element
     * @return collection of TimeTable class objects
     */
    @Override
    public List<TimeTable> getTimeTableByTrainType(int trainType) {
        timeTables = null;
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("SELECT_TIMETABLE_ALL"));
            query.append(SqlManager.getQuery("SELECT_TIMETABLE_BY_TRAIN_TYPE"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, trainType);
            resultSet = preparedStatement.executeQuery();
            timeTables = getTimeTable(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection();
        }
        return timeTables;
    }

    /**
     * method getTimeTableByTrainTypeAndStation(TrainType trainType, String station) gets all objects of class TimeTable
     * train schedules from the database according
     * to the type of trains passing through a particular station
     * @param trainType is a train type enumeration element
     * @param station is the name of the station
     * @return collection of TimeTable class objects
     */
    @Override
    public List<TimeTable> getTimeTableByTrainTypeAndStation(int trainType, int station) {
        timeTables = null;
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("SELECT_TIMETABLE_ALL"));
            query.append(SqlManager.getQuery("SELECT_TIMETABLE_BY_TRAIN_TYPE"));
            query.append(SqlManager.getQuery("SELECT_TIMETABLE_BY_STATION"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, trainType);
            preparedStatement.setInt(2, station);
            resultSet = preparedStatement.executeQuery();
            timeTables = getTimeTable(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionManager.closeResultSet(resultSet);
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection();
        }
        return timeTables;
    }

    /**
     * method addTimeTable(int trainId, int stationId, DayOfWeek dayOfWeek, Time arrivalTime, Time departureTime) is
     * a method that adds a new record to the train schedule table in the database
     * @param trainId is train id in the database
     * @param stationId is station id in the database
     * @param dayOfWeek is the serial number of the day of the week
     * @param arrivalTime is the train arrival time
     * @param departureTime is the train departure time
     */
    @Override
    public void addTimeTable(int trainId, int stationId, DayOfWeek dayOfWeek, Time arrivalTime, Time departureTime) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("ADD_TIME_TABLE"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, trainId);
            preparedStatement.setInt(2, stationId);
            preparedStatement.setInt(3, dayOfWeek.getValue());
            preparedStatement.setTime(4, arrivalTime);
            preparedStatement.setTime(5, departureTime);
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
     * method removeTimeTable(int id) is a method that removes an entry from the train schedule table
     * in the database by id
     * @param id is the identifier of the entry in the train schedule table
     */
    @Override
    public void removeTimeTable(int id) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("REMOVE_TIME_TABLE"));
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
     * method updateTimeTable(int trainId, int stationId, DayOfWeek dayOfWeek, Time arrivalTime, Time departureTime,
     * int id) is a method that changes the value of the data in the record in the train schedule table by id
     * @param trainId is train id in the database
     * @param stationId is station id in the database
     * @param dayOfWeek is the serial number of the day of the week
     * @param arrivalTime is the train arrival time
     * @param departureTime is the train departure time
     * @param id is the identifier of the entry in the train schedule table
     */
    @Override
    public void updateTimeTable(int trainId, int stationId, DayOfWeek dayOfWeek, Time arrivalTime, Time departureTime, int id) {
        PreparedStatement preparedStatement = null;
        query = new StringBuilder();
        try {
            connection = ConnectionManager.createConnection();
            query.append(SqlManager.getQuery("UPDATE_TIME_TABLE_BY_ID"));
            query.append(SqlManager.getQuery("DELIMITER"));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, trainId);
            preparedStatement.setInt(2, stationId);
            preparedStatement.setInt(3, dayOfWeek.getValue());
            preparedStatement.setTime(4, arrivalTime);
            preparedStatement.setTime(5, departureTime);
            preparedStatement.setInt(6, id);
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
     * method getTimeTable(ResultSet resultSet) gets a collection of TimeTable objects
     * from a response from the database
     * @param resultSet stores an object with the result of a database query
     * @return collection of TimeTable objects
     * @throws SQLException
     */
    private List<TimeTable> getTimeTable(ResultSet resultSet) throws SQLException {
        /**
         * temp is a variable storing an intermediate list of stations
         */
        List<TimeTable> temp = new ArrayList<>();
        TimeTable timeTable;
        while(resultSet.next()){
            /**
             * idTimeTable is a variable storing id records in the train schedule table in the database
             */
            int idTimeTable = resultSet.getInt("idtimetable");
            /**
             * idTrain is a variable storing train id in the database
             */
            int idTrain = resultSet.getInt("trainid");
            /**
             * idStation is a variable storing station id in the database
             */
            int idStation = resultSet.getInt("stationid");
            /**
             * day is a variable storing the ordinal number of the day of the week from
             * the schedule table in the database
             */
            int day = resultSet.getInt("day");
            /**
             * departureTime is a variable storing the departure time from the schedule table in the database
             */
            Time departureTime = resultSet.getTime("departureTime");
            /**
             * arrivalTime is a variable storing the arrival time from the schedule table in the database
             */
            Time arrivalTime = resultSet.getTime("arrivalTime");
            timeTable = new TimeTable(idTrain, idStation, arrivalTime, departureTime, DayOfWeek.of(day));
            timeTable.setTimeTableId(idTimeTable);
            temp.add(timeTable);
        }
        return temp;
    }
}