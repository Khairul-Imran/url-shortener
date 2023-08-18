package com.myprojects.urlshortener.utility;

import java.security.SecureRandom;

public class HashGenerator {
    private static final String BASE_62_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int HASH_LENGTH = 7; // Length of hash that will be generated.

    public static String generateRandomHash() {
        SecureRandom random = new SecureRandom();
        StringBuilder hash = new StringBuilder(HASH_LENGTH);

        for (int i = 0; i < HASH_LENGTH; i++) {
            int randomIndex = random.nextInt(BASE_62_CHARS.length());
            char randomChar = BASE_62_CHARS.charAt(randomIndex);
            hash.append(randomChar);
        }
        return hash.toString();
    }
}
