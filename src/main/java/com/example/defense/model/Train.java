package com.example.defense.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    private Integer line;
    private String origin;
    private String destiny;

}
