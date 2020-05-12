package com.KnockKnock.Repositories;

import com.KnockKnock.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    @Query(value="select DISTINCT(city_country) from city",nativeQuery = true)
    List<Object> getAllCountry();

    @Query(value="select DISTINCT(city_state) from city where city_country=?1",nativeQuery = true)
    List<Object> getAllState(String count);

    @Query(value="select DISTINCT(city_name) from city where city_state=?1",nativeQuery = true)
    List<Object> getAllCity(String state);
}
