package com.abhiyan.bookrentalsystem.service.services;
import java.text.ParseException;
import java.time.LocalDate;

public class StringToDate {

    public LocalDate StringToDate(String publishedDate) throws ParseException {
        String sDate = publishedDate;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(sDate);
        return localDate;
    }

}
