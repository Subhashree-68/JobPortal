package com.example.jobPortal.Repository;

import com.example.jobPortal.Entity.Application;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {


}
