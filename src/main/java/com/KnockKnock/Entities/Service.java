package com.KnockKnock.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceSubCategory serviceSubCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "professional")
    private Set<ProfessionalService> professionalServices = new HashSet<>();

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getServiceSubCategoryName() {
        return serviceSubCategoryName;
    }

    public void setServiceSubCategoryName(String serviceSubCategoryName) {
        this.serviceSubCategoryName = serviceSubCategoryName;
    }

    public Float getServiceCommission() {
        return serviceCommission;
    }

    public void setServiceCommission(Float serviceCommission) {
        this.serviceCommission = serviceCommission;
    }

    public ServiceSubCategory getServiceSubCategory() {
        return serviceSubCategory;
    }

    public void setServiceSubCategory(ServiceSubCategory serviceSubCategory) {
        this.serviceSubCategory = serviceSubCategory;
    }

    public Set<ProfessionalService> getProfessionalServices() {
        return professionalServices;
    }

    public void setProfessionalServices(Set<ProfessionalService> professionalServices) {
        this.professionalServices = professionalServices;
    }

    public Service(@Size(max = 50) @NotNull String serviceName, @Size(max = 200) @NotNull String serviceDesc, @Size(max = 50) @NotNull String serviceSubCategoryName, @Max(100) @NotNull Float serviceCommission, @NotNull ServiceSubCategory serviceSubCategory, Set<ProfessionalService> professionalServices) {
        this.serviceName = serviceName;
        this.serviceDesc = serviceDesc;
        this.serviceSubCategoryName = serviceSubCategoryName;
        this.serviceCommission = serviceCommission;
        this.serviceSubCategory = serviceSubCategory;
        this.professionalServices = professionalServices;
    }

    public Service() {
    }
}
