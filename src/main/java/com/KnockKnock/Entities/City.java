package com.KnockKnock.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created on  : 03/05/20 - 12:18 AM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    :
 */
@Entity
@Table (name="City")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;

    @Column
    @Size(max=20)
    @NotNull
    private String cityName;

    @Column
    @Size(max=20)
    @NotNull
    private String cityState;

    @Column
    @Size(max=20)
    @NotNull
    private String cityCountry;

    @Column(unique = true)
    @Size(max=6)
    @NotNull
    private String cityPincode;

    public City() {
    }

    public City(@Size(max = 20) @NotNull String cityName, @Size(max = 20) @NotNull String cityState, @Size(max = 20) @NotNull String cityCountry, @Size(max = 6) @NotNull String cityPincode) {
        this.cityName = cityName;
        this.cityState = cityState;
        this.cityCountry = cityCountry;
        this.cityPincode = cityPincode;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public void setCityCountry(String cityCountry) {
        this.cityCountry = cityCountry;
    }

    public String getCityPincode() {
        return cityPincode;
    }

    public void setCityPincode(String cityPincode) {
        this.cityPincode = cityPincode;
    }
}
