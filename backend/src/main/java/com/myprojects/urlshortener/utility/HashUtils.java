package com.myprojects.urlshortener.utility;

public class HashUtils {

    public static boolean isHash(String input) {
        return input.matches("^[a-zA-Z0-9]+$") && input.length() == 7;
    }

}
