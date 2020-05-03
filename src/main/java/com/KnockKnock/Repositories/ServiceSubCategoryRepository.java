package com.KnockKnock.Repositories;

import com.KnockKnock.Entities.ServiceSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceSubCategoryRepository extends JpaRepository<ServiceSubCategory, Long> {
}
