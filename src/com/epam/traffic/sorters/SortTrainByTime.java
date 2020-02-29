package com.epam.traffic.sorters;

import com.epam.traffic.abstracts.AbstractTrain;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;

/**
 * SortTrainByTime sorts trains according to their travel time between the starting and ending stations
 */
public class SortTrainByTime implements Comparator<AbstractTrain> {
    @Override
    public int compare(AbstractTrain o1, AbstractTrain o2) {
        Duration duration1 = difference(o1.getArrivalTime(),o1.getDepartureTime());
        Duration duration2 = difference(o2.getArrivalTime(), o2.getDepartureTime());
        return duration1.compareTo(duration2);
    }

    private Duration difference(Time arrivalTime, Time departureTime){
        LocalTime arrival = arrivalTime.toLocalTime();
        LocalTime departure = departureTime.toLocalTime();
        Duration duration = Duration.between(arrival, departure);
        return duration;
    }
}
