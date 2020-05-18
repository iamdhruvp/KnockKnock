package com.KnockKnock.Repositories;

import com.KnockKnock.Entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Login findByMobileNo(String mobile);
}
