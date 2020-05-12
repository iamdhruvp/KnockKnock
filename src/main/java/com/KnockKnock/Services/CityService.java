package com.KnockKnock.Services;


import com.KnockKnock.Repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public List<Object> getAllCountry()
    {
       return cityRepository.getAllCountry();
    }
    public List<Object> getAllState(String count)
    {
        return cityRepository.getAllState(count);
    }

    public List<Object> getAllCity(String state)
    {
        return cityRepository.getAllCity(state);
    }
}
