package com.KnockKnock.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Long serviceSubCategoryId;

    @Column
    @Size(max=50)
    @NotNull
    private String serviceSubCategoryName;

    @Column
    @Size(max=200)
    @NotNull
    private String serviceSubCategoryDesc;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ServiceCategory serviceCategory;

    public Long getServiceSubCategoryId() {
        return serviceSubCategoryId;
    }

    public void setServiceSubCategoryId(Long serviceSubCategoryId) {
        this.serviceSubCategoryId = serviceSubCategoryId;
    }

    public String getServiceSubCategoryName() {
        return serviceSubCategoryName;
    }

    public void setServiceSubCategoryName(String serviceSubCategoryName) {
        this.serviceSubCategoryName = serviceSubCategoryName;
    }

    public String getServiceSubCategoryDesc() {
        return serviceSubCategoryDesc;
    }

    public void setServiceSubCategoryDesc(String serviceSubCategoryDesc) {
        this.serviceSubCategoryDesc = serviceSubCategoryDesc;
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public ServiceSubCategory(@Size(max = 50) @NotNull String serviceSubCategoryName, @Size(max = 200) @NotNull String serviceSubCategoryDesc, @NotNull ServiceCategory serviceCategory) {
        this.serviceSubCategoryName = serviceSubCategoryName;
        this.serviceSubCategoryDesc = serviceSubCategoryDesc;
        this.serviceCategory = serviceCategory;
    }

    public ServiceSubCategory() {
    }
}
