package com.KnockKnock.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created on  : 03/05/20 - 1:47 AM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    :
 */
@Entity
@Table(name = "ServiceCategory")
public class ServiceCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceCategoryId;

    @Column
    @Size(max=20)
    @NotNull
    private String serviceCategoryName;

    public ServiceCategory() {
    }

    public Integer getServiceCategoryId() {
        return serviceCategoryId;
    }

    public void setServiceCategoryId(Integer serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    public String getServiceCategoryName() {
        return serviceCategoryName;
    }

    public void setServiceCategoryName(String serviceCategoryName) {
        this.serviceCategoryName = serviceCategoryName;
    }

    public ServiceCategory(@Size(max = 20) @NotNull String serviceCategoryName) {
        this.serviceCategoryName = serviceCategoryName;
    }
}
