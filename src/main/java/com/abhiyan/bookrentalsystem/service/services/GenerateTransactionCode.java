package com.abhiyan.bookrentalsystem.service.services;

import java.util.Random;

public class GenerateTransactionCode {

    public String generateTransactionCode(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 90; // letter 'Z'
        int targetStringLength = 6;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;

    }

}
