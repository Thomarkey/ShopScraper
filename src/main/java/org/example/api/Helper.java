package org.example.api;


public class Helper {


    public static double parseNumber(String value) {
        // Remove any non-numeric characters from the string
        String numericString = value.replaceAll("[^\\d.]", "");

        // Parse the numeric string to a double

        return Double.parseDouble(numericString);
    }

    //keep the DOT
    public static double parseSizeNumber(String value) {
        // Remove any non-numeric characters from the string
        String numericString = value.replaceAll("[^\\d]", "");

        // Parse the numeric string to a double
        return Double.parseDouble(numericString);
    }


}
