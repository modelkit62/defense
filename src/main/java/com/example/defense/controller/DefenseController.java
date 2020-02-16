package com.example.defense.controller;

import com.example.defense.model.Train;
import com.example.defense.service.SearchRequest;
import com.example.defense.service.TrainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController

public class DefenseController{

    private TrainService trainService;

    public DefenseController(TrainService trainService){
        this.trainService = trainService;
    }

    @GetMapping("/trains")
    public Collection<Train> getTrains(@RequestBody Train train){

        SearchRequest searchRequest = new SearchRequest(train.getLine(), train.getDate(), train.getDestiny());

        List<Train> found = trainService.search(searchRequest);

        return displayTrains(found);
    }

    private static Collection<Train> displayTrains(List<Train> found){
        if(found.isEmpty()){
            throw new NullPointerException("There is no train match!");
        } else{
            return found;
        }
    }
}
