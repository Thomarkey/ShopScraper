package org.example.api;

public class Helper {


    public static double parseNumber(String value) {
        // Remove any non-numeric characters from the string
        String numericString = value.replaceAll("[^\\d.]", "");

        // Parse the numeric string to a double
        return Double.parseDouble(numericString);
    }


    public static Double parseSizeNumber(String value) {
        try {
            return Double.parseDouble(value.replace("ca.", "").replaceAll("[^\\d.,]+", "").replace(",", "."));
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
