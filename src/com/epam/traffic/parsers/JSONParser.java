package com.epam.traffic.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * class JSONParser is a class that converts a request from a user into an JSON object
 */
public class JSONParser {
    /**
     * static method getRequest(HttpServletRequest request) object of type converts HttpServletRequest request
     * to JSON object
     * @param request is a request from a user
     * @return JSON object
     * @throws IOException
     */
    public static JSONObject getRequest(HttpServletRequest request) throws IOException {
        /**
         * stringBuffer is an object of type StringBuilder that converts the incoming HttpServletRequest request
         * into a string
         */
        StringBuilder stringBuffer = new StringBuilder();
        /**
         * temp is a variable that stores the temporary result of converting the request to a string
         */
        String temp;
        /**
         * reader is a variable that stores converted HttpServletRequest request to BufferedReader
         */
        BufferedReader reader = request.getReader();
        /**
         * jsonObjectRequest this is the variable that stores the output JSON object
         */
        JSONObject jsonObjectRequest = null;
        while ((temp = reader.readLine()) != null){
            stringBuffer.append(temp);
        }
        try {
            jsonObjectRequest = new JSONObject(stringBuffer.toString());
        } catch (JSONException e){
            System.out.println(e.fillInStackTrace());
        }
        return jsonObjectRequest;
    }
}
