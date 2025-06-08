package com.example.jobPortal.serviceImpl;

import com.example.jobPortal.Dto.ApplicationDto;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Entity.Application;
import com.example.jobPortal.Entity.Job;
import com.example.jobPortal.Repository.ApplicationRepository;
import com.example.jobPortal.Repository.JobRepository;
import com.example.jobPortal.Repository.UserRepository;
import com.example.jobPortal.Service.ApplicationService;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	JobRepository jobRepository;
	@Autowired
	ApplicationRepository applicationRepository;

	@Override
	public Response<?> applyForJob(ApplicationDto applicationDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CANDIDATE"))) {
			System.out.println("hii");
			if (applicationDto.getId() == null) {
				Application application = new Application();
				application.setUser(userRepository.findByEmail(auth.getName()).get());
				Optional<Job> jobId = jobRepository.findById(applicationDto.getJobId());
				if (jobId.isPresent()) {
					application.setJob(jobId.get());
				} else {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Job not found", null);
				}
				application.setResumeUrl(applicationDto.getResumeUrl());
				application.setAppliedAt(new Date());
				applicationRepository.save(application);
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Job applied successfully", null);
			} else {
				Optional<Application> optionalAppl = applicationRepository.findById(applicationDto.getId());
				if (optionalAppl.isPresent()) {
					Application application = optionalAppl.get();
					application.setUser(userRepository.findByEmail(auth.getName()).get());
					Optional<Job> jobId = jobRepository.findById(applicationDto.getJobId());
					if (jobId.isPresent()) {
						application.setJob(jobId.get());
					} else {
						return new Response<>(HttpStatus.BAD_REQUEST.value(), "Job not found", null);
					}
					application.setResumeUrl(applicationDto.getResumeUrl());
					application.setAppliedAt(new Date());
					applicationRepository.save(application);
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Job updated successfully", null);
				}
			}
		}
		return new Response<>(HttpStatus.BAD_REQUEST.value(), "Not applicable", null);
	}

	@Override
	public Response<?> viewAllApplication() {
		List<ApplicationDto> applList= applicationRepository.findAll().stream().map(Application::convertToDto).toList();
		return new Response<>(HttpStatus.OK.value(), "Data retrieved successfully", applList);
	}

	
}
