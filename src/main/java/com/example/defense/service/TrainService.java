package com.example.defense.service;

import com.example.defense.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainService {

    @Autowired
    private TrainStore trainStore;

    public TrainService(TrainStore trainStore) {
        this.trainStore = trainStore;
    }

    public List<Train> search(SearchRequest searchRequest){

        List<Train> trains = trainStore.getTrains();

        return trains.stream()
                .filter(f -> f.getLine().equals(searchRequest.getLine()))
                .collect(Collectors.toList());
    }

    public TrainService trainSearch(){
        return new TrainService(new TrainStoreImpl());
    }
}
