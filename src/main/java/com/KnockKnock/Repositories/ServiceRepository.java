package com.KnockKnock.Repositories;

import com.KnockKnock.Entities.Service;
import com.KnockKnock.Entities.ServiceSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Iterable<Service> findByServiceSubCategory(ServiceSubCategory serviceSubCategory);
}
