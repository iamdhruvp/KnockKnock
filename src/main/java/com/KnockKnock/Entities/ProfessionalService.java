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
public class ProfessionalService implements Serializable {

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
