package com.KnockKnock.Entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created on  : 04/05/20 - 12:32 AM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    :
 */
@JsonFilter("professionalOnly")
@Entity
public class ProfessionalService implements Serializable {

    @EmbeddedId
    private ProfessionalServiceId id = new ProfessionalServiceId();
    //@JsonIgnore
    @ManyToOne
    @MapsId("professionalProfessionalId")
    private Professional professional;

    @ManyToOne
    @MapsId("serviceServiceId")
    private Service service;

    @Column
    @NotNull
    private Float serviceCost;

    @Column
    private Float serviceExtraCost;

    @Column
    private String serviceExtraCostDesc;

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date ServiceEstimatedTime=new Date(2323223232L);

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "professionalServices")
    private Set<Booking> bookings = new HashSet<>();



    public ProfessionalService(ProfessionalServiceId id, Professional professional, Service service, @NotNull Float serviceCost, Float serviceExtraCost, String serviceExtraCostDesc, @NotNull Date serviceEstimatedTime, Set<Booking> bookings) {
        this.id = id;
        this.professional = professional;
        this.service = service;
        this.serviceCost = serviceCost;
        this.serviceExtraCost = serviceExtraCost;
        this.serviceExtraCostDesc = serviceExtraCostDesc;
        ServiceEstimatedTime = serviceEstimatedTime;
        this.bookings = bookings;
    }

    public ProfessionalServiceId getId() {
        return id;
    }

    public void setId(ProfessionalServiceId id) {
        this.id = id;
    }

    public Professional getProfessional() { return professional; }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Float getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Float serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Float getServiceExtraCost() {
        return serviceExtraCost;
    }

    public void setServiceExtraCost(Float serviceExtraCost) {
        this.serviceExtraCost = serviceExtraCost;
    }

    public String getServiceExtraCostDesc() {
        return serviceExtraCostDesc;
    }

    public void setServiceExtraCostDesc(String serviceExtraCostDesc) {
        this.serviceExtraCostDesc = serviceExtraCostDesc;
    }

    public Date getServiceEstimatedTime() {
        return ServiceEstimatedTime;
    }

    public void setServiceEstimatedTime(Date serviceEstimatedTime) {
        ServiceEstimatedTime = serviceEstimatedTime;
    }

    public ProfessionalService() {
    }

    @Embeddable
    public static class ProfessionalServiceId implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long professionalProfessionalId;
        private Long serviceServiceId;

        public ProfessionalServiceId() {
        }

        public ProfessionalServiceId(Long professionalProfessionalId, Long serviceServiceId) {
            this.professionalProfessionalId = professionalProfessionalId;
            this.serviceServiceId = serviceServiceId;
        }

        public Long getProfessionalProfessionalId() {
            return professionalProfessionalId;
        }

        public void setProfessionalProfessionalId(Long professionalProfessionalId) {
            this.professionalProfessionalId = professionalProfessionalId;
        }

        public Long getServiceServiceId() {
            return serviceServiceId;
        }

        public void setServiceServiceId(Long serviceServiceId) {
            this.serviceServiceId = serviceServiceId;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((professionalProfessionalId == null) ? 0 : professionalProfessionalId.hashCode());
            result = prime * result
                    + ((serviceServiceId == null) ? 0 : serviceServiceId.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ProfessionalServiceId other = (ProfessionalServiceId) obj;
            return Objects.equals(getProfessionalProfessionalId(), other.getProfessionalProfessionalId()) && Objects.equals(getServiceServiceId(), other.getServiceServiceId());
        }
    }

}
