package com.KnockKnock.Repositories;

import com.KnockKnock.Entities.Login;
import com.KnockKnock.Entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Professional findByLogin(Login log);
    List<Professional> findByProfessionalIdIn(List<Long> pIds);
    Professional findByProfessionalId(Long pid);
}
