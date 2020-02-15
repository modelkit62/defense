package com.example.defense.controller;

import com.example.defense.model.Train;
import com.example.defense.service.SearchRequest;
import com.example.defense.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
// @RequestMapping("/")
public class DefenseController{

    @Autowired
    TrainService trainService;

    @GetMapping("/trains")
    public String getTrains(@RequestBody Train train){

        SearchRequest searchRequest = new SearchRequest(train.getLine(), train.getOrigin(), train.getDestiny());

        // TrainService trainService = this.trainService.trainSearch();
        List<Train> found = trainService.search(searchRequest);

        return displayTrains(found);

    }

    private static String displayTrains(List<Train> found){
        if(found.isEmpty()){
            return "NO TRAIN FOUND";
        } else{
            return Arrays.toString(found.toArray());
        }
    }
}
