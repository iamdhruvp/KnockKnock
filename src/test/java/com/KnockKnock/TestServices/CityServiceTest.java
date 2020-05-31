package com.KnockKnock.TestServices;


import com.KnockKnock.Entities.City;
import com.KnockKnock.Repositories.CityRepository;
import com.KnockKnock.Services.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CityServiceTest {

    @Autowired
    private CityService cityService;

    @MockBean
    private CityRepository cityRepository;

    @Test
    public void getAllState()
    {
        String country="india";
        when(cityRepository.getAllState(country)).thenReturn(Collections.singletonList((List<City>) Stream.of(
                new City("bangalore", "karnataka", "india"),
                new City("mehsana", "gujarat", "india")

        ).collect((Collectors.toList()))));
        assertEquals(1, cityService.getAllState(country).size());



    }

    @Test
    public void getAllCity()
    {
        String state="gujarat";
        when(cityRepository.getAllCity(state)).thenReturn(Collections.singletonList((List<City>) Stream.of(
                new City("mehsana", "gujarat", "india")

        ).collect((Collectors.toList()))));
        assertEquals(1, cityService.getAllCity(state).size());



    }

}
