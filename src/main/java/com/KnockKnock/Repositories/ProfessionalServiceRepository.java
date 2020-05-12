package com.KnockKnock.Repositories;

import com.KnockKnock.Entities.ProfessionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalServiceRepository extends JpaRepository<ProfessionalService, ProfessionalService.ProfessionalServiceId> {
    //@Query("SELECT professional_professional_id From professional_service WHERE service_service_id IN (SELECT service_service_id FROM (SELECT service_service_id FROM professional_service WHERE service_service_id IN (:serviceIDs)) AS s) GROUP BY professional_professional_id HAVING COUNT(*) = :serviceCount")
    @Query("SELECT p.id.professionalProfessionalId From ProfessionalService p WHERE p.id.serviceServiceId IN (:serviceIDs) GROUP BY p.id.professionalProfessionalId HAVING COUNT(p.id.serviceServiceId) >= :serviceCount")
    List<Long> findProfessionalIdGivenServices(@Param("serviceIDs") List<Long> serviceIDs, @Param("serviceCount") Long serviceCount);

}
