package com.example.jobPortal.serviceImpl;

import com.example.jobPortal.Dto.JobDto;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Dto.SpecificationDto;
import com.example.jobPortal.Entity.Job;
import com.example.jobPortal.Enum.Role;
import com.example.jobPortal.Repository.JobRepository;
import com.example.jobPortal.Repository.UserRepository;
import com.example.jobPortal.Service.JobService;
import com.example.jobPortal.specification.JobSpecification;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
@Autowired
JobRepository jobRepository;
@Autowired
UserRepository userRepository;
@Autowired
JobSpecification jobSpecification;
	@Override
	public Response<?> createJob(JobDto jobDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("COMPANY_ADMIN"))) {
//			if(auth.getAuthorities().equals(Role.COMPANY_ADMIN)) {
				if(jobDto.getId()==null) {
					Job job=new Job();
					job.setTitle(jobDto.getTitle());
					job.setDescription(jobDto.getDescription());
					job.setCompany(jobDto.getCompany());
					job.setLocation(jobDto.getLocation());
					job.setSalary(jobDto.getSalary());
					job.setCreatedAt(new Date());
					job.setCreatedBy(userRepository.findByEmail(auth.getName()).get());
					jobRepository.save(job);
					return new Response<>(HttpStatus.OK.value(),"Job posted successfully",null);
				}
				else {
					Optional<Job> optionalJob=jobRepository.findById(jobDto.getId());
					if(optionalJob.isEmpty()) {
						return new Response<>(HttpStatus.BAD_REQUEST.value(),"Job title not found",null);
					}
					Job job = optionalJob.get();
					job.setTitle(jobDto.getTitle());
					job.setDescription(jobDto.getDescription());
					job.setCompany(jobDto.getCompany());
					job.setLocation(jobDto.getLocation());
					job.setSalary(jobDto.getSalary());
					job.setUpdatedAt(new Date());
					jobRepository.save(job);	
					return new Response<>(HttpStatus.OK.value(),"Job updated successfully",null);
				}
//			}
		}
		return new Response<>(HttpStatus.BAD_REQUEST.value(),"Not authorized",null);
	}
	@Override
	public Response<?> filterJobWithPagination(Pageable pageable,SpecificationDto specificationDto) {
		Specification<Job> spec = Specification.where(null);
		if(specificationDto.getTitle()!=null) {
			spec=spec.and(jobSpecification.containsTitle(specificationDto.getTitle()));
		}
		if(specificationDto.getLocation()!=null) {
			spec=spec.and(jobSpecification.hasLocation(specificationDto.getLocation()));
		}
		if(specificationDto.getCompany()!=null) {
			spec=spec.and(jobSpecification.hasCompany(specificationDto.getCompany()));
		}
		if(specificationDto.getMaxSalary()!=null) {
			spec=spec.and(jobSpecification.hasSalaryLessThan(specificationDto.getMaxSalary()));
		}
		if(specificationDto.getMinSalary()!=null) {
			spec=spec.and(jobSpecification.hasSalaryGreaterThan(specificationDto.getMinSalary()));
		}
		Page<Job> jobs = jobRepository.findAll(spec, pageable);
		Page<JobDto> job =jobs.map(Job::convertToDto);
		return new Response<>(HttpStatus.OK.value(),"Success",job);
	}
	@Override
	public Response<?> getAllJob() {
		List<Job> findAll = jobRepository.findAll();
		return new Response<>(HttpStatus.OK.value(),"Success",findAll);
	}
	@Override
	public Response<?> getAuth() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return new Response<>(HttpStatus.OK.value(),"Success",auth);
	}
}
