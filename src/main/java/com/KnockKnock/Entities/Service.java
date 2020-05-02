package com.KnockKnock.Entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created on  : 03/05/20 - 2:04 AM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    :
 */
@Entity
@Table(name="Service")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceId;

    @Column
    @Size(max=50)
    @NotNull
    private String serviceName;

    @Column
    @Size(max=200)
    @NotNull
    private String serviceDesc;

    @Column
    @Size(max=50)
    @NotNull
    private String serviceSubCategoryName;

    @Column
    @Max(100)
    @NotNull
    private Float serviceCommission;

    @Column
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceSubCategory serviceSubCategory;
}
