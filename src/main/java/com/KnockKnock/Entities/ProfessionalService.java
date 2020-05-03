package com.KnockKnock.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created on  : 04/05/20 - 12:32 AM
 * Project     : KnockKnock
 * Author      : dhruv
 * Comments    :
 */
@Entity
class ProfessionalService implements Serializable {

    @EmbeddedId
    private ProfessionalServiceId id = new ProfessionalServiceId();

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

    @Embeddable
    public static class ProfessionalServiceId implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long professionalId;
        private Long serviceId;

        public Long getProfessionalId() {
            return professionalId;
        }

        public void setProfessionalId(Long professionalId) {
            this.professionalId = professionalId;
        }

        public Long getServiceId() {
            return serviceId;
        }

        public void setServiceId(Long serviceId) {
            this.serviceId = serviceId;
        }

        public ProfessionalServiceId(Long professionalId, Long serviceId) {
            this.professionalId = professionalId;
            this.serviceId = serviceId;
        }

        public ProfessionalServiceId() {
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((professionalId == null) ? 0 : professionalId.hashCode());
            result = prime * result
                    + ((serviceId == null) ? 0 : serviceId.hashCode());
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
            return Objects.equals(getProfessionalId(), other.getProfessionalId()) && Objects.equals(getServiceId(), other.getServiceId());
        }
    }

}
