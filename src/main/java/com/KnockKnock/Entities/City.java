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
}
