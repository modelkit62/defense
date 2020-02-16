package com.example.defense.controller;

import com.example.defense.model.Train;
import com.example.defense.service.SearchRequest;
import com.example.defense.service.TrainService;
import com.example.defense.service.TrainStore;
import com.example.defense.service.TrainStoreImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DefenseControllerTest{

    @Mock
    TrainStore trainStore = new TrainStoreImpl();

    @InjectMocks
    private TrainService trainService;

    @Autowired
    private MockMvc mockMvc;

    List<Train> trains;

    @Test
    @DisplayName("My first test!")
    void getTrains() throws Exception{

        trains = new ArrayList<>();
        Train trainR = Train.builder().line(10).date("06-05-1999").destiny("Chicago").build();
        trains.add(trainR);

        Train train = Train.builder().line(10).date("03-10-1999").destiny("London").build();

        SearchRequest searchRequest = new SearchRequest(10, "22-09-2010", "Liverpool");

        when(trainService.search(searchRequest)).thenReturn(trains);

        mockMvc.perform(get("/trains").contentType(MediaType.APPLICATION_JSON).content(asJsonString(train)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$[1].destiny"), is("San Antoni")));
    }

    private String asJsonString(Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        } catch(JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}