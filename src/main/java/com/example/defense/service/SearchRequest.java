package com.example.defense.service;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

@Data
@NoArgsConstructor
public class SearchRequest{

    private Integer line;
    private String date;
    private String destiny;

    public SearchRequest(Integer line, String date, String destiny){
        this.line = validateLine(line);
        this.date = validateInputDate(date);
        this.destiny = validateString(destiny);
    }

    private Integer validateLine(Integer line){
        if(line < 1 || line > 10){
            throw new IllegalArgumentException("The line is between 1-10");
        }
        return line;
    }

    private String validateInputDate(String date){
        LocalDate localDate;
        try{
            localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch(DateTimeParseException ex) {
            String msg = String.format("Could not parse this %s, porque es otro formato", date);
            throw new IllegalArgumentException(msg, ex);
        }
        return localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)); // LONG, MEDIUM, SHORT
    }

    private String validateString(String destiny){
        if(null == destiny || destiny.trim().isEmpty() ){
            throw new IllegalArgumentException("Origin and Destiny can't be null");
        }
        return destiny;
    }
}
