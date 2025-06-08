package com.example.jobPortal.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.jobPortal.Dto.JobDto;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Dto.SpecificationDto;

public interface JobService {

	Response<?> createJob(JobDto jobDto);

	Response<?> filterJobWithPagination(Pageable pageable,SpecificationDto specificationDto);

	Response<?> getAllJob();

	Response<?> getAuth();
}
