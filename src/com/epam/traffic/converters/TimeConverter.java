package com.epam.traffic.converters;

/**
 * TimeConverter is a class for converting user input to the time needed to send to the database
 */
public class TimeConverter {
    /**
     * method convertTime(String jsonTime) is a method that receives a string variable
     * and performs conversion for further casting to type Time
     * @param jsonTime
     * @return
     */
    public static String convertTime(String jsonTime){
        if(jsonTime.length()<6){
            /**
             * stringBuilder is an instance of class StringBuilder needed to convert
             * the input string with the addition of zeros
             */
            StringBuilder stringBuilder = new StringBuilder(jsonTime);
            stringBuilder.append(':');
            stringBuilder.append("00");
            return stringBuilder.toString();
        } else {
            return jsonTime;
        }
    }
}
