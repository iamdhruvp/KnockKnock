package com.KnockKnock.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created on  : 03/05/20 - 1:51 AM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    :
 */
@Entity
@Table(name="ServiceSubCategory")
public class ServiceSubCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceSubCategoryId;

    @Column
    @Size(max=50)
    @NotNull
    private String serviceSubCategoryName;

    @Column
    @Size(max=200)
    @NotNull
    private String serviceSubCategoryDesc;

    @Column
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ServiceCategory serviceCategory;

}
