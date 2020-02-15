package com.example.defense.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
public class SearchRequest{

    private Integer line;
    private String origin;
    private String destiny;

    public SearchRequest(Integer line, String origin, String destiny){
        this.line = validateLine(line);
        this.origin = validateString(origin);
        this.destiny = validateString(destiny);;
    }

    private Integer validateLine(Integer line){
        if(line < 1 || line > 10){
            throw new IllegalArgumentException("The line is between 1-10");
        }
        return line;
    }

    private String validateString(String origin){
        if(null == origin || origin.trim().isEmpty() ){
            throw new IllegalArgumentException("Origin and Destiny can't be null");
        }
        return origin;
    }
}
