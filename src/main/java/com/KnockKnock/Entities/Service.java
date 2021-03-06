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
    private Long serviceId;

    @Column
    @Size(max=50)
    @NotNull
    private String serviceName;

    @Column
    @Size(max=200)
    @NotNull
    private String serviceDesc;

    @Column
    @Max(100)
    @NotNull
    private Float serviceCommission;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ServiceSubCategory serviceSubCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "service")
    private Set<ProfessionalService> professionalServices = new HashSet<>();

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
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

    public Service(@Size(max = 50) @NotNull String serviceName, @Size(max = 200) @NotNull String serviceDesc, @Max(100) @NotNull Float serviceCommission, @NotNull ServiceSubCategory serviceSubCategory, Set<ProfessionalService> professionalServices) {
        this.serviceName = serviceName;
        this.serviceDesc = serviceDesc;
        this.serviceCommission = serviceCommission;
        this.serviceSubCategory = serviceSubCategory;
        this.professionalServices = professionalServices;
    }

    public Service() {
    }
}
