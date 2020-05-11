package com.KnockKnock.Repositories;

import com.KnockKnock.Entities.ProfessionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalServiceRepository extends JpaRepository<ProfessionalService, ProfessionalService.ProfessionalServiceId> {
}
