package com.example.jobPortal.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobPortal.Dto.JobDto;
import com.example.jobPortal.Dto.LoginRequest;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Dto.SpecificationDto;
import com.example.jobPortal.Entity.Job;
import com.example.jobPortal.Repository.JobRepository;
import com.example.jobPortal.Service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
	@Autowired
	JobService jobService;
	
	 @PostMapping("/create")
	 public ResponseEntity<?> createJob(@RequestBody @Valid JobDto jobDto){
		 
		Response<?> response= jobService.createJob(jobDto);
		 return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
	 }
	 @GetMapping("/filter/with/pagination")
	 public ResponseEntity<?> filterJobWithPagination(  @RequestParam(defaultValue = "0") int pageNo,
														 @RequestParam(defaultValue = "5") int pageSize,
														 @RequestParam(defaultValue = "id") String sortBy,
														 @RequestParam(defaultValue = "true") Boolean ascending,
														 @RequestBody SpecificationDto specificationDto)
	 {
		 Sort sort=ascending?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		 Pageable pageable=PageRequest.of(pageNo,pageSize,sort);
		 Response<?> response= jobService.filterJobWithPagination(pageable,specificationDto);
		 return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
	 }
	 @GetMapping("/view/all")
	 public ResponseEntity<?> getAllJob(){
		 Response<?> response=jobService.getAllJob();
		 return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
	 }
	 @GetMapping("/hii")
	 public ResponseEntity<?> getAuth(){
		 Response<?> response=jobService.getAuth();
		 return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
	 }
	 
}
