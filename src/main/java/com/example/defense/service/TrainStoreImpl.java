package com.example.defense.service;

import com.example.defense.model.Train;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainStoreImpl implements TrainStore{

    private List<Train> trains;

    @Override
    public List<Train> getTrains(){

        trains = new ArrayList<>();

        Train train1 = new Train(10, "Mislata", "Mercado");
        Train train2 = new Train(1, "Mestalla", "Nazaret");
        trains.add(train1);
        trains.add(train2);
        return trains;

        /*return List.of (
                new Train (10, "Mislata", "Mercado"),
                new Train (1, "Mestalla", "Nazaret")
        );*/

    }
}
