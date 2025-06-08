package com.example.jobPortal.Repository;

import com.example.jobPortal.Dto.JobDto;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> ,JpaSpecificationExecutor<Job>{

}
