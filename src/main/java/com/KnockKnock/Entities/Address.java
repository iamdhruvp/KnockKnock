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
@Table(name="Address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Column
    @Size(max=50)
    @NotNull
    private String addressName;

    @Column
    @Size(max=200)
    @NotNull
    private String addressLine;

    @Column
    @Size(max=200)
    @NotNull
    private String addressLandmark;

    @Column
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private City addressCity;

    @Column
    @NotNull
    private Boolean isDefaultAddress;

}
